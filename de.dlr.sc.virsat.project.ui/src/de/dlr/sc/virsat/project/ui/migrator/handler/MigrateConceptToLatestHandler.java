/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.migrator.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import de.dlr.sc.virsat.model.concept.migrator.ConceptMigrator;
import de.dlr.sc.virsat.model.concept.migrator.CreateMigrateConceptToLatestCommand;
import de.dlr.sc.virsat.model.concept.util.ConceptActivationHelper;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.ui.editingDomain.handler.AEditingDomainCommandHandler;

/**
 * Handles upgrading a concept to the latest available version in the repository
 * @author muel_s8
 *
 */

public class MigrateConceptToLatestHandler extends AEditingDomainCommandHandler implements IHandler {

	/**
	 * Migrates a set of concepts to the latest available version
	 * @param concepts the concepts to migrate
	 * @param ed the editing domain
	 */
	public static void migrateToLatest(Collection<Concept> concepts, TransactionalEditingDomain ed) {
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor progressMonitor) throws CoreException {
				CompoundCommand cc = new CompoundCommand();
				for (Concept concept : concepts) {
					
					//If concept is active check if new dependencies have to be added before migration
					if (concept.eContainer() != null && concept.eContainer() instanceof Repository) {
						Repository repository = (Repository) concept.eContainer();
						new ConceptActivationHelper(repository).handleNewDependencies(concept, ed, progressMonitor);
					}
					
					Command migrateToLatestCommand = CreateMigrateConceptToLatestCommand.create(concept, ed, progressMonitor);
					cc.append(migrateToLatestCommand);
				}
				ed.getCommandStack().execute(cc);
			}
		};
		
		try {
			operation.run(new NullProgressMonitor());
		} catch (InvocationTargetException | InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform migration!", e));
		}
	}
	
	/**
	 * Migrates a concept to the latest available version
	 * @param concept the concept to migrate
	 * @param ed the editing domain
	 */
	public static void migrateToLatest(Concept concept, TransactionalEditingDomain ed) {
		migrateToLatest(Collections.singleton(concept), ed);
	}
	
	@Override
	protected void execute() {
		if (firstSelectedEObject instanceof Concept) {
			Concept concept = (Concept) firstSelectedEObject;
			migrateToLatest(concept, ed);
		}
	}
	
	@Override
	public boolean isEnabled() {
		// Get the info of where to execute this handler
		ISelectionService  selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = selectionService.getSelection();
		
		initializeFieldsFromSelection(selection);
		
		if (firstSelectedEObject instanceof Concept) {
			Concept concept = (Concept) firstSelectedEObject;
			ConceptMigrator cmHelper = new ConceptMigrator(concept);
			return super.isEnabled() && cmHelper.needsMigration();
		}
		
		
		return false;
	}

}
