/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources.dmf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.compare.diff.IDiffEngine;
import org.eclipse.emf.compare.diff.IDiffProcessor;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.eobject.IdentifierEObjectMatcher;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;

import com.google.common.base.Function;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyinstancesSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.dmf.DObject;
import de.dlr.sc.virsat.model.dvlm.dmf.UnresolveableDObject;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * This command deals with saving DMF Resources
 * @author muel_s8
 *
 */

public class DmfResourceSaveCommand extends RecordingCommand {

	private StructuralElementInstance sei;
	private List<DObject> dObjects;
	private CategoryInstantiator caInstantiator = new CategoryInstantiator();
	private Repository repository;
	private ActiveConceptHelper acHelper;
	private List<DObject> savedDObjects =  new ArrayList<>();
	private IMerger.Registry mergerRegistry;
	private IMatchEngine.Factory.Registry matchRegistry;
	private IDiffEngine diffEngine;
	private BatchMerger merger;
	private VirSatTransactionalEditingDomain virSatEd;
	
	/**
	 * Standard constructor
	 * @param virSatEd the editing domain
	 * @param sei the structural element instance
	 * @param dObjects the dObjects that will be saved
	 */
	
	public DmfResourceSaveCommand(VirSatTransactionalEditingDomain virSatEd, StructuralElementInstance sei, List<DObject> dObjects) {
		super(virSatEd);
		this.sei = sei;
		this.dObjects = dObjects;
		this.repository = virSatEd.getResourceSet().getRepository();
		this.acHelper = new ActiveConceptHelper(repository);
		this.virSatEd = virSatEd;
		
		setupMatchRegistry();
		setupMergeRegistry();
		setupDiffEngine();
	}
	
	/**
	 * Creates the custom diff engine. We ignore changes to the UUID attribute since those changes are just
	 * a side effect of recreating property instances.
	 */
	private void setupDiffEngine() {
		IDiffProcessor diffProcessor = new DiffBuilder();
		diffEngine = new DefaultDiffEngine(diffProcessor) {
			@Override
			protected FeatureFilter createFeatureFilter() {
				return new FeatureFilter() {
					@Override
					protected boolean isIgnoredAttribute(EAttribute attribute) {
						return attribute == GeneralPackage.Literals.IUUID__UUID || super.isIgnoredAttribute(attribute);
					}
					
					@Override
					protected boolean isIgnoredReference(Match match, EReference reference) {
						return reference == PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE || super.isIgnoredReference(match, reference);
					}

					@Override
					public boolean checkForOrderingChanges(EStructuralFeature feature) {
						return false;
					}
				};
			}
		};
	}

	/**
	 * Creates the merge registry
	 */
	private void setupMergeRegistry() {
		mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
		merger = new BatchMerger(mergerRegistry);
	}

	/**
	 * Creates the match registry that can respect possible name changes
	 * in the different concept versions
	 */
	private void setupMatchRegistry() {
		final int RANKING_MATCHER_ID = 20;
		
		Function<EObject, String> fqnIdMatcher = new Function<EObject, String>() {
			public String apply(EObject input) {
				if (input instanceof IQualifiedName) {
					// For Concept Elements we need the Qualified Name
					String oldObjectIdFqn =  ((IQualifiedName) input).getFullQualifiedName();
					return oldObjectIdFqn;
				}
				
				if (input instanceof CategoryAssignment) {
					// For category assignments we already have matching identifier using the UUID since the DObject also has
					// a UUID
					return ((CategoryAssignment) input).getUuid().toString();
				}
				
				if (input instanceof ATypeInstance) {
					// For other instances (actually it should just be property instances) we do not have UUID since the
					// DObject doesnt know about UUIDs for its attributes. Therefore we construct a qualified name for the instance.
					ATypeInstance ati = (ATypeInstance) input;
					CategoryAssignment containingCa = VirSatEcoreUtil.getEContainerOfClass(ati, CategoryAssignment.class);
					String propertyFqn = ActiveConceptHelper.getFullQualifedNameForInstance(ati, ati);
					String oldObjectIdFqn = containingCa.getUuid().toString() + "." +  propertyFqn;
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
		matchRegistry = MatchEngineFactoryRegistryImpl.createStandaloneInstance();
		matchEngineFactory.setRanking(RANKING_MATCHER_ID); // default engine ranking is 10, must be higher to override.
		matchRegistry.add(matchEngineFactory);
	}
	
	@Override
	protected void doExecute() {
		
		// Figure out which objects are resolveable and which are not
		// The unresolveable DObjects are not touched and left as is
		// The others will be overwritten.
		List<DObject> resoveableDObjects = new ArrayList<>();
		List<CategoryAssignment> savedCategoryAssignments = new ArrayList<>();
		dObjects.forEach(dObject -> {
			if (dObject instanceof UnresolveableDObject) {
				CategoryAssignment ca = findCategoryAssignmentForDObject(dObject);
				savedCategoryAssignments.add(ca);
			} else {
				resoveableDObjects.add(dObject);
			}
		});
		
		// ..and recreate them from the DObject
		resoveableDObjects.forEach(dObject -> {
			CategoryAssignment ca = saveDObject(dObject);
			savedCategoryAssignments.add(ca);
		});
		
		sei.getCategoryAssignments().retainAll(savedCategoryAssignments);
		
		// Finally set the references
		linkAllReferencedCategoryAssignments();
	}
	
	/**
	 * Finds a CategoryAssignment in the StructuralElementInstance that matches the passed
	 * dObject, that is, that it matches it by UUID
	 * @param dObject the dObject
	 * @return a category assignment if suitable one exists and null if not
	 */
	private CategoryAssignment findCategoryAssignmentForDObject(DObject dObject) {
		return (CategoryAssignment) safeGetSei().eResource().getEObject(dObject.getUuid().toString());
	}
	
	/**
	 * Saves a single DObject
	 * @param dObject the dObejct
	 * @return the CategoryAssignment corresponding to the passed dObject
	 */
	private CategoryAssignment saveDObject(DObject dObject) {
		CategoryAssignment caNew = getCategoryAssignment(dObject);
		initializePropertyInstances(caNew, dObject);
		savedDObjects.add(dObject);
		
		CategoryAssignment caOld = findCategoryAssignmentForDObject(dObject);
		if (caOld != null) {
			// If there is a category assignment for this UUID already on this object
			// then we perform a merge with the newly created ca and the existing ca
			
			IComparisonScope scope = new DefaultComparisonScope(caNew, caOld,  caOld);
			Comparison comparison = EMFCompare.builder().setMatchEngineFactoryRegistry(matchRegistry).setDiffEngine(diffEngine).build().compare(scope);
			List<Diff> differences = comparison.getDifferences();
			if (differences.size() > 0) {
				merger.copyAllLeftToRight(differences, new BasicMonitor());
			}
			caNew = caOld;
		} else {
			sei.getCategoryAssignments().add(caNew);
		}
		
		return caNew;
	}

	/**
	 * Gets the feature value of the dObject and if it is an array,
	 * it hands back the appropiate element.
	 * @param dObject the dObject
	 * @param feature the feature
	 * @param pi the property instance represented by the feature
	 * @return the direct value of the feature or if it is an array, the element corresponding
	 * to the index of the property instance
	 */
	@SuppressWarnings("unchecked")
	private Object dGet(DObject dObject, EStructuralFeature feature, EObject pi) {
		Object value = dObject.eGet(feature);
		if (value != null) {
			if (pi.eContainer() instanceof ArrayInstance) {
				List<Object> list = (List<Object>) value;
				ArrayInstance ai = (ArrayInstance) pi.eContainer();
				int index = ai.getArrayInstances().indexOf(pi);
				value = list.get(index);
			} 
		}
		
		return value;
	}
	
	/**
	 * Set the reference properties in the saved category assignments correctly
	 */
	@SuppressWarnings("unchecked")
	private void linkAllReferencedCategoryAssignments() {
		savedDObjects.forEach(dObject -> {
			CategoryAssignment ca = findCategoryAssignmentForDObject(dObject);
			EClass eClass = dObject.eClass();
			
			ca.getPropertyInstances().forEach(pi -> {
				String typeName = pi.getType().getName();
				EStructuralFeature feature = eClass.getEStructuralFeature(typeName);
				
				if (feature != null) {
					new PropertyinstancesSwitch<CategoryAssignment>() {
						@Override
						public CategoryAssignment caseReferencePropertyInstance(ReferencePropertyInstance rpi) {
							DObject referencedDObject = (DObject) dGet(dObject, feature, rpi);
							
							if (referencedDObject != null) {
								CategoryAssignment referencedCa = findCategoryAssignmentForDObject(referencedDObject);
								
								if (referencedCa != null) {
									// We are referencing an object in the same resource
									rpi.setReference(referencedCa);
								} else {
									// We have a cross reference to an object outside of the current resource
									Resource resourceOfReferencedDObject = referencedDObject.eResource();
									URI uriReferenced = resourceOfReferencedDObject.getURI();
									String uriFragmentReferenced = referencedDObject.getUuid().toString();
									String dvlmUriString = uriReferenced.toString().replaceAll("\\." + DmfResource.DMF_FILENAME_EXTENSION, "");
									URI dvlmUri = URI.createURI(dvlmUriString);
									URI uri = dvlmUri.appendFragment(uriFragmentReferenced);
									
									CategoryAssignment proxyCa = CategoriesFactory.eINSTANCE.createCategoryAssignment();
									((InternalEObject) proxyCa).eSetProxyURI(uri);
									rpi.setReference(proxyCa);
								}
							} else {
								rpi.setReference(null);
							}
							return ca;
						}
						
						
						@Override
						public CategoryAssignment caseArrayInstance(ArrayInstance ai) {
							List<Object> list = (List<Object>) dObject.eGet(feature);
							
							// We need to check on both sizes due to static array modifiers
							
							for (int i = 0; i < list.size() && i < ai.getArrayInstances().size(); ++i) {
								APropertyInstance pi = ai.getArrayInstances().get(i);
								doSwitch(pi);
							}
							
							return ca;
						}
					}.doSwitch(pi);
				}
			});
		});
	}

	/**
	 * Creates a new category assignment for the given dObject
	 * @param dObject the dObject
	 * @return a new category assignment
	 */
	private CategoryAssignment getCategoryAssignment(DObject dObject) {
		EClass eClass = dObject.eClass();
		EPackage ePackage = eClass.getEPackage();
		String conceptId = getConceptIdForEPackage(ePackage);

		Category cat = acHelper.getCategory(conceptId, eClass.getName());
		CategoryAssignment ca = caInstantiator.generateInstance(cat, dObject.getName());
		ca.setUuid(dObject.getUuid());
		
		return ca;
	}
	
	/**
	 * Gets the concept id for a given ePackage
	 * @param ePackage the ePackage
	 * @return the concept id
	 */
	private String getConceptIdForEPackage(EPackage ePackage) {
		for (Concept concept : repository.getActiveConcepts()) {
			if (VirSatEcoreUtil.getNameOfFqn(concept.getName()).equals(ePackage.getName())) {
				return concept.getName();
			}
		}
		
		return "";
	}
	
	/**
	 * Initializes the property instances of a category assignment using the features in a dObject
	 * @param ca the category assignment
	 * @param dObject the dObject
	 */
	@SuppressWarnings("unchecked")
	private void initializePropertyInstances(CategoryAssignment ca, DObject dObject) {
		ca.getPropertyInstances().forEach(pi -> {
			String typeName = pi.getType().getName();
			EStructuralFeature feature = dObject.eClass().getEStructuralFeature(typeName);
			if (feature != null) {
				new PropertyinstancesSwitch<CategoryAssignment>() {
					@Override
					public CategoryAssignment caseValuePropertyInstance(ValuePropertyInstance vpi) {
						return new PropertydefinitionsSwitch<CategoryAssignment>() {
							@Override
							public CategoryAssignment caseBooleanProperty(BooleanProperty bpd) {
								Object value = dGet(dObject, feature, vpi);
								if (value != null) {
									vpi.setValue(value.toString());
								}
								return ca;
							}
							@Override
							public CategoryAssignment caseStringProperty(StringProperty spd) {
								Object value =  dGet(dObject, feature, vpi);
								if (value != null) {
									vpi.setValue(value.toString());
								}
								return ca;
							}
							
							@Override
							public CategoryAssignment caseFloatProperty(FloatProperty fpd) {
								UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) vpi;
								Object value = dGet(dObject, feature, vpi);
								if (value != null) {
									double valueInBaseUnit = (double) value;
									double valueInTargetUnit = QudvUnitHelper.getInstance().convertFromBaseUnitToTargetUnit(uvpi.getUnit(), valueInBaseUnit);
									uvpi.setValue(String.valueOf(valueInTargetUnit));
								}
								return ca;
							}
							
							@Override
							public CategoryAssignment caseIntProperty(IntProperty ipd) {
								UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) vpi;
								Object value = dGet(dObject, feature, vpi);
								if (value != null) {
									int valueInBaseUnit = (int) value;
									int valueInTargetUnit = (int) QudvUnitHelper.getInstance().convertFromBaseUnitToTargetUnit(uvpi.getUnit(), valueInBaseUnit);
									uvpi.setValue(String.valueOf(valueInTargetUnit));
								}
								return ca;
							}
							
						}.doSwitch(vpi.getType());
					};
					
					@Override
					public CategoryAssignment caseResourcePropertyInstance(ResourcePropertyInstance rpi) {
						Object value = dGet(dObject, feature, rpi);
						if (value != null) {
							rpi.setResourceUri(value.toString());
						}
						return ca;
					}
					
					@Override
					public CategoryAssignment caseEReferencePropertyInstance(EReferencePropertyInstance erpi) {
						Object value = (EObject) dGet(dObject, feature, erpi);
						if (value instanceof EObject) {
							erpi.setReference((EObject) value);
						}
						return ca;
					}

					@Override
					public CategoryAssignment caseComposedPropertyInstance(ComposedPropertyInstance cpi) {
						Object value = dGet(dObject, feature, cpi);
						if (value != null) {
							DObject containedDObject = (DObject) value;
							CategoryAssignment caContained = getCategoryAssignment(containedDObject);
							initializePropertyInstances(caContained, containedDObject);
							savedDObjects.add(containedDObject);
							cpi.setTypeInstance(caContained);
						}
						return ca;
					}
					
					public CategoryAssignment caseEnumUnitPropertyInstance(EnumUnitPropertyInstance eupi) {
						Object value = dGet(dObject, feature, eupi);
						if (value instanceof Enumerator) {
							Enumerator enumerator = (Enumerator) value;
							EnumProperty ep = (EnumProperty) eupi.getType();
							EnumValueDefinition evd = new EnumPropertyHelper().getEvdForName(ep, enumerator.getName());
							eupi.setValue(evd);
						}
						return ca;
					};
					
					@Override
					public CategoryAssignment caseArrayInstance(ArrayInstance ai) {
	
						List<Object> list = (List<Object>) dObject.eGet(feature);
						AProperty property = (AProperty) pi.getType();
						
						for (int i = 0; i < list.size(); ++i) {
							Object object = list.get(i);
							APropertyInstance pi = null;
							
							if (property.getArrayModifier() instanceof DynamicArrayModifier) {
								if (object instanceof DObject) {
									DObject instanceDObject = (DObject) object;
									EClass instanceClass = instanceDObject.eClass();
									String instanceTypeName = instanceClass.getName();
									EPackage instancePackage = instanceClass.getEPackage();
									String conceptId = getConceptIdForEPackage(instancePackage);
									Category cat = acHelper.getCategory(conceptId, instanceTypeName);
									pi = caInstantiator.generateInstance(ai, cat);
								} else {
									pi = caInstantiator.generateInstance(ai);
								}
								
								ai.getArrayInstances().add(pi);
							} else {
								pi = ai.getArrayInstances().get(i);
							}
							
							doSwitch(pi);
						}
						
						return ca;
					}
				}.doSwitch(pi);
			}
		});
	}
	
	/**
	 * Get the current structural element. This method ensures that a potential proxy is resolved.
	 * @return the SEI
	 */
	public StructuralElementInstance safeGetSei() {
		if (sei.eIsProxy()) {
			sei = (StructuralElementInstance) EcoreUtil.resolve(sei, virSatEd.getResourceSet());
		}
		return sei;
	}
	
}
