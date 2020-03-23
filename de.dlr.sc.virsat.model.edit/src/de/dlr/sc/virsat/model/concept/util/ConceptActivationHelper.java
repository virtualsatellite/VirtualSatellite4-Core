/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.commons.datastructures.DependencyTree;
import de.dlr.sc.virsat.model.concept.migrator.ConceptMigrator;
import de.dlr.sc.virsat.model.concept.migrator.CreateMigrateConceptToLatestCommand;
import de.dlr.sc.virsat.model.concept.migrator.IMigrator;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferencePropertyHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;

/**
 * This class helps activating concepts. Concepts are copied into the 
 * repository. Furthermore, this class redirects references so that these can be 
 * resolved from a platform plugin. References to other concepts are redirected 
 * to their active version within the repository
 *
 */
public class ConceptActivationHelper {
	
	private static final String CONCEPT_PATH = "/concept/concept.xmi";
	protected Repository repository;
	
	public ConceptActivationHelper(Repository repository) {
		this.repository = repository;
	}
	
	public ConceptActivationHelper(Concept concept) {
		if (concept.eContainer() != null && concept.eContainer() instanceof Repository) {
			this.repository = ((Repository) concept.eContainer());
		} else {
			throw new IllegalArgumentException("Concept is not active! Repository for activation cannot be determined!");
		}
	}
	
	/**
	 * Activate type by redirecting it to type in the repository
	 * @param type the type to be activates
	 * @return the activated type
	 */
	public EObject getActiveType(EObject type) {
		
		// For EReferences to external EClasses ignore concept 
		// activation and convert URIs instead. The URI needs to be
		// transformed from a PluginResourceURI to a PlattformPluginURI so 
		// that it can be resolved from the VirSat project in the eclipse runtime
		// instance.
		// VirSat does not ensure external model's storage.
		// We only enable non-containment references.
		if (type instanceof EClass) {
			return new EReferencePropertyHelper().activateEClassType((EClass) type);
		}

		// In case we try to create a reference to an object which was not copied
		// we should try to redirect that reference to an already active and existing concept
		if (repository != null && repository.eResource() != null && type instanceof IConceptTypeDefinition) {
			IConceptTypeDefinition typeDefinition = (IConceptTypeDefinition) type;
	
			// Get the fragment URI of the concept we want to reference to
			String uriFragment = EcoreUtil.getURI(typeDefinition).fragment();
		
			// ask the repository if there is such an object with the given URI fragment
			Resource repoResource = repository.eResource();
			EObject repoTypeDefinition = repoResource.getEObject(uriFragment);

			// If not throw a warning that there is something missing
			if (repoTypeDefinition == null) {
				String fqId = ActiveConceptHelper.getFullQualifiedId(typeDefinition);
			
				throw new RuntimeException("Install missing concept first! Missing concept: " + fqId);
			}
			return repoTypeDefinition;
		}
		
		return type;
	}
	
	/**
	 * Handle the activation of concepts from their configuration elements
	 * @param conceptConfigurationElements an array of concept configurations
	 * @param editingDomain the editing domain
	 */
	public void activateConcepts(Object[] conceptConfigurationElements, EditingDomain editingDomain, IProgressMonitor progressMonitor) {
		
		List<Concept> concepts = new ArrayList<Concept>();
		for (Object acce : conceptConfigurationElements) {
			if (acce instanceof ActiveConceptConfigurationElement) {
				concepts.add(((ActiveConceptConfigurationElement) acce).loadConceptFromPlugin());
			}
		}
		activateConcepts(concepts, editingDomain, progressMonitor);
	}
	
	/**
	 * Handle the activation of concepts. Resolves dependencies in correct order. 
	 * @param conceptConfigurationElements a list of concept configurations
	 * @param editingDomain the editing domain
	 */
	public void activateConcepts(List<Concept> concepts, EditingDomain editingDomain, IProgressMonitor progressMonitor) {
		
		// Correctly sort the selected concepts depending on their dependencies
		DependencyTree<String> dependencyTree = new DependencyTree<String>();
		Map<String, Concept> selectedConcepts = new HashMap<String, Concept>();
		
		// Create a map conceptName -> concept and add all concept names to the dependency tree
		for (Concept concept : concepts) {
			selectedConcepts.put(concept.getName(), concept);
			dependencyTree.addDependencies(concept.getName(), new String[] {});
		}
		
		// Now create the dependencies using the concept names as identifiers
		for (Concept concept : selectedConcepts.values()) {
			List<String> conceptDependencyIds = new ArrayList<>(ActiveConceptHelper.getConceptDependencies(concept));
			dependencyTree.addDependencies(concept.getName(), conceptDependencyIds);
		}
		
		List<String> orderedConcepts = dependencyTree.getLinearOrder();
		
		// And then install them by using the ordered list of concept names
		// and the map of concept names pointing to the already preloaded concepts
		for (String conceptName : orderedConcepts) {
			if (selectedConcepts.containsKey(conceptName)) {
				activateConcept(selectedConcepts.get(conceptName), editingDomain, progressMonitor);
			}
		}
	}
	
	/**
	 * Prepare a selected concept for activation. Checks if an older version is already in the repository and if so
	 * migrates it to the latest version, otherwise the concept will be simply added to the active concepts
	 * @param concept the selected concept
	 * @param editingDomain the editing domain
	 */
	protected void activateConcept(Concept concept, EditingDomain editingDomain, IProgressMonitor progressMonitor) {

		// Check if we already have this concept but with a different version added to the repository
		Concept activeConcept = new ActiveConceptHelper(repository).getConcept(concept.getName());

		boolean conceptIsInRepository = activeConcept != null;
		
		// There is a concept of a different version in the repository
		if (conceptIsInRepository && !activeConcept.getVersion().equals(concept.getVersion())) {
				
			//Check if new dependencies have to be added before migration
			if (activeConcept.eContainer() != null && activeConcept.eContainer() instanceof Repository) {
				Repository repository = (Repository) activeConcept.eContainer();
				new ConceptActivationHelper(repository).handleNewDependencies(activeConcept, editingDomain, progressMonitor);
			}
			
			try {
				Command migrateToLatestCommand = CreateMigrateConceptToLatestCommand.create(activeConcept, (TransactionalEditingDomain) editingDomain, progressMonitor);
				editingDomain.getCommandStack().execute(migrateToLatestCommand);
			} catch (CoreException e) {
				DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.ERROR, DVLMEditPlugin.PLUGIN_ID, "Failed to do migration on active concept: " + concept.getDisplayName(), e));
			}
			
		}
		
		if (!conceptIsInRepository) {
			Command cmd = ActiveConceptConfigurationElement.createCopyConceptToRepository(editingDomain, concept, repository);
			editingDomain.getCommandStack().execute(cmd);
		}
	}
	
	/**
	 * Activate new concept dependencies 
	 * @param concept the concept to be prepared for migration
	 * @param editingDomain the editing domain
	 */
	public void handleNewDependencies(Concept concept, EditingDomain editingDomain, IProgressMonitor progressMonitor) {
		Set<String> newRequiredConcepts = new HashSet<String>();
		List<Concept> concepts = new ArrayList<Concept>();
		try {
			List<IMigrator> migrators = new ConceptMigrator(concept).getSortedMigrators(concept);
			IMigrator previousMigrator = migrators.remove(0);
			for (IMigrator migrator : migrators) {
				migrator.getNewDependencies(concept, previousMigrator).stream().forEach(key -> newRequiredConcepts.add(key));
			}
		} catch (CoreException e) {
			DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.ERROR, DVLMEditPlugin.PLUGIN_ID, "Failed to perform loading new depencies for migration!", e));
		}
		
		for (String conceptName : newRequiredConcepts) {
			concepts.add(ActiveConceptConfigurationElement.loadConceptFromPlugin(conceptName + CONCEPT_PATH));
		}
		
		activateConcepts(concepts, editingDomain, progressMonitor);
	}
	

}
