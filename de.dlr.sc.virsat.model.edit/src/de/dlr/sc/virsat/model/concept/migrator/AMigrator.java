/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.migrator;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.ReferenceChange;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.eobject.IdentifierEObjectMatcher;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.merge.AttributeChangeMerger;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.ConflictMerger;
import org.eclipse.emf.compare.merge.FeatureMapChangeMerger;
import org.eclipse.emf.compare.merge.IBatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.merge.IMerger.RegistryImpl;
import org.eclipse.emf.compare.merge.PseudoConflictMerger;
import org.eclipse.emf.compare.merge.ReferenceChangeMerger;
import org.eclipse.emf.compare.merge.ResourceAttachmentChangeMerger;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.common.IResourceSetFactory;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.MigratorRegistry;
import org.eclipse.emf.edapt.spi.history.Release;

import com.google.common.base.Function;

import de.dlr.sc.virsat.model.concept.calculation.QualifiedEquationObjectHelper;
import de.dlr.sc.virsat.model.concept.util.ConceptActivationHelper;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.model.ecore.xmi.impl.DvlmXMIResourceFactoryImpl;

/**
 * Abstract class to implement a migrator for a concept.
 * This class does a two step migration. First it migrates the current
 * and previous concept to the latest DVLM release. Then it starts comparing
 * both concepts and executes the migration.
 */

public abstract class AMigrator implements IMigrator {

	private IMerger.Registry mergerRegistry;
	private IMatchEngine.Factory.Registry matchRegistry;
	private ConceptActivationHelper activationHelper;
	private Concept newNonActiveConcept;
	
	/**
	 * Default Constructor
	 */
	
	public AMigrator() {
		setupMatchRegistry();
		setupMergeRegistry();
	}

	protected Map<String, String> oldToNewObjectIds;
	
	/**
	 * Creates the match registry that can respect possible name changes
	 * in the different concept versions
	 */
	private void setupMatchRegistry() {
		final int RANKING_MATCHER_ID = 20;
		oldToNewObjectIds = new HashMap<>();

		registerOldToNewIds();
		
		Function<EObject, String> fqnIdMatcher = new Function<EObject, String>() {
			public String apply(EObject input) {
				if (input instanceof IQualifiedName) {
					String oldObjectIdFqn =  ((IQualifiedName) input).getFullQualifiedName();
					
					// We also need to add the type of the object to the ID
					// Otherwise if two objects have the same ID (for example a property was changed
					// from being an IntProperty to be an EnumProperty)
					// then EMFCompare will try to compare all their features against each other and
					// crash since it doesn't foresee the case of comparing objects of different classes.
					
					String typeSuffix = "." + input.eClass().getName();
					if (oldToNewObjectIds.containsKey(oldObjectIdFqn)) {
						return oldToNewObjectIds.get(oldObjectIdFqn) + typeSuffix;
					} else {
						return oldObjectIdFqn + typeSuffix;
					}
				}
				
				if (input instanceof IQualifiedEquationObject) {
					String oldObjectIdFqn = QualifiedEquationObjectHelper.getFullQualifiedName((IQualifiedEquationObject) input);
					for (String oldId : oldToNewObjectIds.keySet()) {
						String newId = oldToNewObjectIds.get(oldId);
						oldObjectIdFqn = oldObjectIdFqn.replaceAll(oldId, newId);
					}
					return oldObjectIdFqn;
				}
				
				// a null return here tells the match engine to fall back to the other matchers
				return null;
			}
		};
		 
		final MatchEngineFactoryImpl matchEngineFactory = new MatchEngineFactoryImpl() {
			public IMatchEngine getMatchEngine() {
				if (matchEngine == null) {
					// Using this matcher as fall back, EMF Compare will still search for XMI IDs on EObjects
					// for which we had no custom id function.
					IEObjectMatcher fallBackMatcher = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.WHEN_AVAILABLE);
					IEObjectMatcher customIDMatcher = new IdentifierEObjectMatcher(fallBackMatcher, fqnIdMatcher);
					IComparisonFactory comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
					matchEngine = new DefaultMatchEngine(customIDMatcher, comparisonFactory);
				}
				
				return matchEngine;
			}
		};
		
		matchEngineFactory.setRanking(RANKING_MATCHER_ID); // default engine ranking is 10, must be higher to override.
		matchRegistry = MatchEngineFactoryRegistryImpl.createStandaloneInstance();
		matchRegistry.add(matchEngineFactory);
	}

	
	/**
	 * Creates the merge registry for merging concepts
	 */
	private void setupMergeRegistry() {
		mergerRegistry = new RegistryImpl();

		final int RANKING_DEFAULT = 10;
		final int RANKING_CONFLICT_PSEUDO = 75;
		final int RANKING_CONFLICT = 100;

		final IMerger attributeMerger = new AttributeChangeMerger();
		final IMerger referenceMerger = new ReferenceChangeMerger() {
			@Override
			protected void removeFromTarget(ReferenceChange diff, boolean rightToLeft) {
 				Comparison comparison = diff.getMatch().getComparison();
				Match valueMatch = comparison.getMatch(diff.getValue());
				
				// Add null check to prevent exceptions caused by test setup - loaded concepts are loaded isolated from each 
				// other, so references to other concepts cannot be resolved
				if (valueMatch != null) {
					// Saving the match for later to be able to do custom migration and data model migration
					EObject right = valueMatch.getRight();
					super.removeFromTarget(diff, rightToLeft);
					valueMatch.setRight(right);
				}
			}
			
			@Override
			protected void addInTarget(ReferenceChange diff, boolean rightToLeft) {
				//Activate new types that are copied to repository via migration
				String fragement = EcoreUtil.getURI(diff.getValue()).fragment().replace("/", "");
				//Only activate if types are not in the concept resource itself
				if (newNonActiveConcept.eResource() != null && newNonActiveConcept.eResource().getEObject(fragement) == null) {
					EObject activeReferenceValue = activationHelper.getActiveType(diff.getValue());
					diff.setValue(activeReferenceValue);
				}
				super.addInTarget(diff, rightToLeft);
			}
			
		};
		IMerger featureMapMerger = new FeatureMapChangeMerger();
		IMerger resourceAttachmentMerger = new ResourceAttachmentChangeMerger();
		IMerger pseudoConflictMerger = new PseudoConflictMerger();
		IMerger conflictMerger = new ConflictMerger();

		attributeMerger.setRanking(RANKING_DEFAULT);
		referenceMerger.setRanking(RANKING_DEFAULT);
		featureMapMerger.setRanking(RANKING_DEFAULT);
		resourceAttachmentMerger.setRanking(RANKING_DEFAULT);
		pseudoConflictMerger.setRanking(RANKING_CONFLICT_PSEUDO);
		conflictMerger.setRanking(RANKING_CONFLICT);

		mergerRegistry.add(attributeMerger);
		mergerRegistry.add(referenceMerger);
		mergerRegistry.add(featureMapMerger);
		mergerRegistry.add(resourceAttachmentMerger);
		mergerRegistry.add(pseudoConflictMerger);
		mergerRegistry.add(conflictMerger);
	}
	
	protected ConceptMigrationHelper cmHelper;
	protected String resourcePath;

	/**
	 * Register id changes of concept objects.
	 * Override this method to inject the changes.
	 */
	protected void registerOldToNewIds() {
	}
	
	@Override
	public Set<String> getNewDependencies(Concept concept, IMigrator previousMigrator) {
		
		String conceptId = concept.getFullQualifiedName() + "/";
		Concept conceptNext = loadConceptXmi(conceptId + getResource());
		
		return getNewDependencies(concept, conceptNext);
	}
	
	/**
	 * Return new dependencies of new concept versions
	 * @param conceptCurrent the current concept as it is in the repository
	 * @param conceptNext the next concept version
	 * @return A set of new concept names
	 */
	public Set<String> getNewDependencies(Concept conceptCurrent, Concept conceptNext) {
		//new dependencies are dependencies of newer concept minus old dependencies
		Set<String> newDependencies = ActiveConceptHelper.getConceptDependencies(conceptNext);
		newDependencies.removeAll(ActiveConceptHelper.getConceptDependencies(conceptCurrent));
		
		return newDependencies;
	}
	
	@Override
	public void migrate(Concept conceptCurrent, IMigrator previousMigrator) {
		String conceptId = conceptCurrent.getFullQualifiedName() + "/";
		Concept conceptPrevious = loadConceptXmi(conceptId + previousMigrator.getResource());
		Concept conceptNext = loadConceptXmi(conceptId + getResource());
		
		migrate(conceptPrevious, conceptCurrent, conceptNext);
	}

	/**
	 * This migrates the current concept to the next version
	 * @param conceptPrevious the previous concept version. A comparison between conceptPrevious
	 * and the conceptCurrent should not yield any differences.
	 * @param conceptCurrent the current concept as it is in the repository
	 * @param conceptNext the next concept version
	 */
	public void migrate(Concept conceptPrevious, Concept conceptCurrent, Concept conceptNext) {
		IComparisonScope scope = new DefaultComparisonScope(conceptNext, conceptCurrent,  conceptPrevious);
		Comparison comparison = EMFCompare.builder().setMatchEngineFactoryRegistry(matchRegistry).build().compare(scope);
		activationHelper = new ConceptActivationHelper(conceptCurrent);
		newNonActiveConcept = conceptNext;

		List<Diff> differences = comparison.getDifferences();
		cmHelper = new ConceptMigrationHelper(conceptCurrent);
		
		conceptMigration(differences);
		dataModelMigration(differences);
	}
	
	/**
	 * In the first migration step the concept data model is updated
	 * @param differences the differences to the next concept version
	 */
	public void conceptMigration(List<Diff> differences) {
		IBatchMerger merger = new BatchMerger(mergerRegistry);
		merger.copyAllLeftToRight(differences, new BasicMonitor());
	}
	
	/**
	 * In the second migration step the data model is updated
	 * using the differences model from the concept comparison
	 * @param differences the differences to the next concept version
	 */
	public void dataModelMigration(List<Diff> differences) {
		Set<EquationDefinition> modifiedEquations = new HashSet<>();
		
		for (Diff diff : differences) {
			
			Match match = diff.getMatch();
			EObject affectedEObject = match.getRight();
			if (affectedEObject instanceof Concept) {
				Concept concept = (Concept) affectedEObject;
				diffConcept(diff, match, concept);
			} else if (affectedEObject instanceof Category) {
				Category category = (Category) affectedEObject;
				diffCategory(diff, match, category);
			} else if (affectedEObject instanceof StructuralElement) {
				StructuralElement structuralElement = (StructuralElement) affectedEObject;
				diffStructuralElement(diff, match, structuralElement);
			} else if (affectedEObject instanceof AProperty) {
				AProperty property = (AProperty) affectedEObject;
				diffProperty(diff, match, property);
			}  else if (affectedEObject != null) {
				EquationDefinition eqDefinition = VirSatEcoreUtil.getEContainerOfClass(affectedEObject, EquationDefinition.class);
				if (eqDefinition != null) {
					// We first cluster the modifications to the equations so that the event
					// doesnt fire for every modification within one equation
					modifiedEquations.add(eqDefinition);
				}
			}
		}
		
		for (EquationDefinition eqDefinition : modifiedEquations) {
			// Check if the containing category hasnt been deleted in the meantime
			if (eqDefinition.eContainer() != null) {
				changeEquationDefinition(eqDefinition);
			} 
		}
	}

	/**
	 * Handle a diff on the concept level
	 * @param diff the diff
	 * @param match the match
	 * @param concept the concept
	 */
	
	protected void diffConcept(Diff diff, Match match, Concept concept) {
		if (diff instanceof ReferenceChange) {
			ReferenceChange referenceChange = (ReferenceChange) diff;
			match = match.getComparison().getMatch(referenceChange.getValue());
			
			switch (diff.getKind()) {
				case ADD:
					EObject addedEObject = match.getRight();
					if (addedEObject instanceof StructuralElement) {
						StructuralElement structuralElement = (StructuralElement) addedEObject;
						addStructuralElement(diff, match, structuralElement);
					} else if (addedEObject instanceof Category) {
						Category category = (Category) addedEObject;
						addCategory(diff, match, category);
					} 
					break;
				case DELETE:
					EObject deletedEObject = match.getRight();
					if (deletedEObject instanceof StructuralElement) {
						StructuralElement structuralElement = (StructuralElement) deletedEObject;
						deleteStructuralElement(diff, match, structuralElement);
					} else if (deletedEObject instanceof Category) {
						Category category = (Category) deletedEObject;
						deleteCategory(diff, match, category);
					} 
					break;
				case CHANGE:
					break;
				case MOVE:
					break;
				default:
					break;
			}
		}
	}
	
	/**
	 * Handle a diff on the category level
	 * @param diff the diff
	 * @param match the match
	 * @param category the category
	 */
	
	protected void diffCategory(Diff diff, Match match, Category category) {
		if (diff instanceof ReferenceChange) {
			ReferenceChange referenceChange = (ReferenceChange) diff;
			match = match.getComparison().getMatch(referenceChange.getValue());
			
			switch (diff.getKind()) {
				case ADD:
					EObject addedEObject = match.getRight();
					if (addedEObject instanceof AProperty) {
						AProperty property = (AProperty) addedEObject;
						addProperty(diff, match, property);
					} else if (addedEObject instanceof EquationDefinition) {
						EquationDefinition eqDefinition = (EquationDefinition) addedEObject;
						addEquationDefinition(diff, match, eqDefinition);
					} else {
						changeCategory(diff, match, category);
					}
					break;
				case DELETE:
					EObject deletedEObject = match.getRight();
					if (deletedEObject instanceof AProperty) {
						AProperty property = (AProperty) deletedEObject;
						deleteProperty(diff, match, property);
					} else if (deletedEObject instanceof EquationDefinition) {
						EquationDefinition eqDefinition = (EquationDefinition) deletedEObject;
						removeEquationDefinition(diff, match, eqDefinition);
					} else {
						changeCategory(diff, match, category);
					}
					break;
				case CHANGE:
					changeCategory(diff, match, category);
					break;
				case MOVE:
					break;
				default:
					break;
			}
		} else {
			changeCategory(diff, match, category);
		}
	}


	/**
	 * Handle a diff on the StructuralElement level
	 * @param diff the diff
	 * @param match the match
	 * @param structuralElement the structural element
	 */
	
	protected void diffStructuralElement(Diff diff, Match match, StructuralElement structuralElement) {
		changeStructuralElement(diff, match, structuralElement);
	}
	
	/**
	 * Handle a diff on the property level
	 * @param diff the diff
	 * @param match the match
	 * @param property the property
	 */
	protected void diffProperty(Diff diff, Match match, AProperty property) {
		changeProperty(diff, match, property);
	}
	
	/**
	 * Event where a structural element has been added to the concept.
	 * Subclass for customized migration.
	 * @param diff the diff
	 * @param match the match
	 * @param structuralElement the structural element
	 */
	protected void addStructuralElement(Diff diff, Match match, StructuralElement structuralElement) {
		
	}
	
	/**
	 * Event where an attribute of a structural element have been changed.
	 * Subclass for customized migration.
	 * @param diff the diff
	 * @param match the match
	 * @param structuralElement the structural element
	 */
	protected void changeStructuralElement(Diff diff, Match match, StructuralElement structuralElement) {
	
	}
	
	/**
	 * Event where a structural element has been removed from the concept.
	 * Subclass for customized migration.
	 * @param diff the diff
	 * @param match the match
	 * @param structuralElement the structural element
	 */
	protected void deleteStructuralElement(Diff diff, Match match, StructuralElement structuralElement) {
		cmHelper.removeStructuralElement(structuralElement);
	}

	/**
	 * Event where a category has been added to the concept.
	 * Suclass for customized migration.
	 * @param diff the diff
	 * @param match the match
	 * @param category the category
	 */
	protected void addCategory(Diff diff, Match match, Category category) {

	}
	
	/**
	 * Event where an attribute of a category have been changed.
	 * Subclass for customized migration.
	 * @param diff the diff
	 * @param match the match
	 * @param category the category
	 */
	protected void changeCategory(Diff diff, Match match, Category category) {

	}
	
	/**
	 * Event where a category has been removed from the concept.
	 * Subclass for customized migration.
	 * @param diff the diff
	 * @param match the match
	 * @param category the category
	 */
	protected void deleteCategory(Diff diff, Match match, Category category) {
		cmHelper.removeCategory(category);
	}

	/**
	 * Event where a property has been added to the concept.
	 * Subclass for customized migration.
	 * @param diff the diff
	 * @param match the match
	 * @param property the property
	 */
	protected void addProperty(Diff diff, Match match, AProperty property) {
		Category category = (Category) property.eContainer();
		cmHelper.addProperty(property, category);
	}
	
	/**
	 * Event where an attribute of a property has been changed.
	 * Subclass for customized migration.
	 * @param diff the diff
	 * @param match the match
	 * @param property the property
	 */
	protected void changeProperty(Diff diff, Match match, AProperty property) {
		
	}
	
	/**
	 * Event where a property has been removed from the concept.
	 * Subclass for customized migration
	 * @param diff the diff
	 * @param match the match
	 * @param property the property
	 */
	protected void deleteProperty(Diff diff, Match match, AProperty property) {
		cmHelper.removeProperty(property);
	}
	
	/**
	 * Event where an equation definition has been added from the concept.
	 * Subclass for customized migration
	 * @param diff the diff
	 * @param match the match
	 * @param eqDefinition the equation definition
	 */
	protected void addEquationDefinition(Diff diff, Match match, EquationDefinition eqDefinition) {
		Category category = (Category) eqDefinition.eContainer();
		cmHelper.addEquationDefinition(eqDefinition, category);
	}
	
	/**
	 * Event where an equation definition has been removed from the concept.
	 * Subclass for customized migration
	 * @param diff the diff
	 * @param match the match
	 * @param eqDefinition the equation definition
	 */
	protected void removeEquationDefinition(Diff diff, Match match, EquationDefinition eqDefinition) {
		cmHelper.removeEquationDefinition(eqDefinition);
	}
	
	/**
	 * Event where an equation definition has been changed.
	 * Subclass for customized migration
	 * @param eqDefinition the quation definition
	 */
	protected void changeEquationDefinition(EquationDefinition eqDefinition) {
		cmHelper.changeEquationDefinition(eqDefinition, eqDefinition);
	}
	
	@Override
	public void setResource(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
	@Override
	public String getResource() {
		return resourcePath;
	}

	/**
	 * Loads the concept xmi at the given path
	 * @param resourceName the path to the concept xmi
	 * @return the loaded concept
	 */
	public Concept loadConceptXmi(String resourceName) {
		URI conceptResourceUri = URI.createPlatformPluginURI(resourceName, true);
		ResourceSet resSet = performMigration(conceptResourceUri);
		Resource resource = resSet.getResource(conceptResourceUri, true);
		
		Concept concept = (Concept) resource.getContents().get(0);
		return concept;
	}
		
	/**
	 * This method handles the creation the redirection of URIs for creating and redirecting input streams
	 * Override this method e.g. in the test cases to throw an exception when loading external resources,
	 * which is not desired. This method handles the redirection from the XTEXT representation of a concept
	 * to its XMI representation which can be automatically be migrated
	 * @param uri The URI for which to create a new stream
	 * @return the URI which is now redirected
	 */
	public static URI createInputStream(URI uri) {
		String platformresourceUri = uri.toPlatformString(true);
		platformresourceUri = platformresourceUri.replace(".concept", ".xmi");
		URI redirectedUri = URI.createPlatformPluginURI(platformresourceUri, true);
		return redirectedUri;
	}
	
	/**
	 * This methods tests the concept if it needs migration on EMF DVLM level
	 * @param conceptResourceUri The Resource URI to be used for the migration
	 * @return true ResourceSet in which the migrated Resource is loaded
	 */
	public static ResourceSet performMigration(URI conceptResourceUri) {
		/**
		 * ResourceSet factory for the EDAPT Migrator
		 * to understand that links which usually point to .concept files should be redirected to . xmi representation
		 * @author fisc_ph
		 *
		 */
		IResourceSetFactory resSetFactory = new IResourceSetFactory() {
			@Override
			public ResourceSet createResourceSet() {
				// Obtain a new resource set
				ResourceSet resourceSet = new ResourceSetImpl() {
					@Override
					public EObject getEObject(URI uri, boolean loadOnDemand) {
						if (uri.toString().contains("concept.concept")) {
							return null;
						}
						return super.getEObject(uri, loadOnDemand);
					}
				};
				
				Resource.Factory.Registry factoryRegistry = Resource.Factory.Registry.INSTANCE;
				Map<String, Object> extensionMap = factoryRegistry.getExtensionToFactoryMap();
				extensionMap.put("xmi", new DvlmXMIResourceFactoryImpl());
				extensionMap.put("concept", new DvlmXMIResourceFactoryImpl());

				resourceSet.setResourceFactoryRegistry(factoryRegistry);

				return resourceSet;
			}
		};
		
		String nsURI = ReleaseUtils.getNamespaceURI(conceptResourceUri);
		Migrator migrator = MigratorRegistry.getInstance().getMigrator(nsURI);
		
		if (migrator == null) {
			DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.ERROR, DVLMEditPlugin.PLUGIN_ID, 
					"Could not get DVLM migrator for concept resource: " + conceptResourceUri));
			//Check that all dependent concepts and their plugins are available in platform...  
			return new ResourceSetImpl();
		}
		
		migrator.setResourceSetFactory(resSetFactory);
		Release release = migrator.getRelease(conceptResourceUri).iterator().next();
		try {
			if (!release.isLatestRelease()) {
				migrator.setLevel(ValidationLevel.NONE);
				return migrator.migrateAndLoad(Collections.singletonList(conceptResourceUri), release, null, new NullProgressMonitor());
			} 
		} catch (MigrationException e) {
			DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.ERROR, DVLMEditPlugin.PLUGIN_ID, Status.ERROR, "Failed to migrate a Concept", e));
		}
		
		return resSetFactory.createResourceSet();
	}
}
