/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * VirSat Project Wizard that deals with project layout and files
 */
public class VirSatProjectWizard extends BasicNewProjectResourceWizard implements INewWizard {

	public static final String ID_NEW_VIRSAT_PROJECT_WIZARD = "de.dlr.sc.virsat.project.ui.newProjectWizard";

	@Override
	public boolean performFinish() {
		boolean performSuperFinish = super.performFinish();
		IProject newProject = getNewProject();
		return performSuperFinish && performFinishVirSat(newProject);
	}

	/**
	 * Finalize VirtualSatellite Project initialization
	 * 
	 * @param newProject The newly created project
	 * @return true in case the operation was correctly handed over to the workspace
	 */
	private boolean performFinishVirSat(IProject newProject) {
		// Set up the Workspace Modification Unit to create the VirSat DataModel folder
		// layout and to initialize the Data Model files with correct content
		VirSatResourceSet resSet = VirSatResourceSet.getResourceSet(newProject);
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(newProject);

		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor progressMonitor) throws CoreException {

				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectWizard: Started VirSat Project Initialization"));
				// also attach Xtext nature
				final String XTEXT_NATURE_ID = "org.eclipse.xtext.ui.shared.xtextNature";

				// Create the file structure and the files and add the Xtext Nature
				VirSatProjectCommons projectCommons = new VirSatProjectCommons(newProject);
				projectCommons.createProjectStructure(new NullProgressMonitor());
				projectCommons.attachProjectNature(XTEXT_NATURE_ID);

				// Create the actual resources and ResourceSet
				Command initializeProjectCommand = resSet.initializeModelsAndResourceSet(progressMonitor, ed);

				// Trigger the command stack to save all files after they have been created
				ed.getVirSatCommandStack().triggerSaveAll();
				ed.getVirSatCommandStack().execute(initializeProjectCommand);

				newProject.refreshLocal(IResource.DEPTH_INFINITE, progressMonitor);
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectWizard: Finished VirSat Project Initialization"));
			}
		};

		// Try to fire the operation that has been created
		try {
			operation.run(new NullProgressMonitor());
		} catch (InvocationTargetException | InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to initialize Project!", e));
			return false;
		}

		return true;
	}
}
