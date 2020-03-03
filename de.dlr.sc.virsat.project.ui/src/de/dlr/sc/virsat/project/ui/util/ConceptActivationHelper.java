/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import de.dlr.sc.virsat.commons.datastructures.DependencyTree;
import de.dlr.sc.virsat.model.concept.migrator.ConceptMigrator;
import de.dlr.sc.virsat.model.concept.migrator.IMigrator;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.project.ui.migrator.handler.MigrateConceptToLatestHandler;

/**
 *
 */
public class ConceptActivationHelper {
	
	protected Repository repository;
	private static final String CONCEPT_PATH = "/concept/concept.xmi";
	
	public ConceptActivationHelper(Repository repository) {
		this.repository = repository;
	}
	
	/**
	 * Handle the activation of concept configuration elements
	 * @param conceptConfigurationElements an iterable of concept configurations
	 * @param editingDomain the editing domain
	 */
	public void handleAddConcepts(Object[] conceptConfigurationElements, EditingDomain editingDomain) {
		
		List<Concept> concepts = new ArrayList<Concept>();
		for (Object acce : conceptConfigurationElements) {
			if (acce instanceof ActiveConceptConfigurationElement) {
				concepts.add(((ActiveConceptConfigurationElement) acce).loadConceptFromPlugin());
			}
		}
		handleAddConcepts(concepts, editingDomain);
	}
	
	/**
	 * Handle the activation of concept configuration elements
	 * @param conceptConfigurationElements an iterable of concept configurations
	 * @param editingDomain the editing domain
	 */
	public void handleAddConcepts(List<Concept> concepts, EditingDomain editingDomain) {
		
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
				handleAddConcept(selectedConcepts.get(conceptName), editingDomain);
			}
		}
	}
	
	/**
	 * Handle a selected concept to active. Checks if an older version is already in the repository and if so
	 * migrates the existing concept to the latest version, otherwise the concept will be simply added to the active concepts
	 * @param concept the selected concept
	 * @param editingDomain the editing domain
	 */
	protected void handleAddConcept(Concept concept, EditingDomain editingDomain) {
		boolean conceptIsInRepository = false;
		
		// Check if we already have this concept but with a different version added to the repository
		
		List<Concept> activeConcepts = repository.getActiveConcepts();
		for (Concept activeConcept : activeConcepts) {
			conceptIsInRepository = activeConcept.getName().equals(concept.getName());
			
			// There is a concept of an different version in the repository, ask if the user wants to migrate and do so
			if (conceptIsInRepository && !activeConcept.getVersion().equals(concept.getVersion())) {
				WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
					@Override
					protected void execute(IProgressMonitor progressMonitor) throws CoreException {
						if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Found older version in repository",
								"An older version of the concept " + concept.getName() + " has been detected in the repository. "
										+ "The concept will be migrated to the selected version. Do you want to proceed?")) {
							// Perform the migration
							MigrateConceptToLatestHandler.migrateToLatest(activeConcept, (TransactionalEditingDomain) editingDomain);
						} 
					}
				};
				
				try {
					operation.run(new NullProgressMonitor());
				} catch (InvocationTargetException | InterruptedException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform migration!", e));
				}
				
				break;
			}
		}
		
		if (!conceptIsInRepository) {
			Command cmd = ActiveConceptConfigurationElement.createCopyConceptToRepository(editingDomain, concept, repository);
			editingDomain.getCommandStack().execute(cmd);
		}
	}
	
	/**
	 * Active new required concepts to repository 
	 * @param concept the concept to new migrated
	 * @param editingDomain the editing domain
	 */
	public void handleNewDependencies(Concept concept, EditingDomain editingDomain) {
		Set<String> newRequiredConcepts = new HashSet<String>();
		List<Concept> concepts = new ArrayList<Concept>();
		try {
			List<IMigrator> migrators = new ConceptMigrator(concept).getSortedMigrators(concept);
			IMigrator previousMigrator = migrators.remove(0);
			for (IMigrator migrator : migrators) {
				migrator.getNewDependencies(concept, previousMigrator).stream().forEach(key -> newRequiredConcepts.add(key));
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform loading new depencies for migration!", e));
		}
		
		for (String conceptName : newRequiredConcepts) {
			concepts.add(ActiveConceptConfigurationElement.loadConceptFromPlugin(conceptName + CONCEPT_PATH));
		}
		
		handleAddConcepts(concepts, editingDomain);
	}

}
