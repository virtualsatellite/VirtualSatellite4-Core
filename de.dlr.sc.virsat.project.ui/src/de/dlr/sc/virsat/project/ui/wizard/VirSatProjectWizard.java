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
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * VirSat Project Wizard that deals with project layout and files
 */
public class VirSatProjectWizard extends BasicNewProjectResourceWizard implements INewWizard {

	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		this.setNeedsProgressMonitor(true);
	}
	
	public static final String ID_NEW_VIRSAT_PROJECT_WIZARD = "de.dlr.sc.virsat.project.ui.newProjectWizard";

	@Override
	public boolean performFinish() {
		AtomicReference<Boolean> result = new AtomicReference<>(true);
		
		try {
			// Run it in the dialog with a progressbar. run it in the thread of the dialog. Don't provide cancel (false, false)
			getContainer().run(false, false, (progressMonitor) -> {
				try {
					// Progressbar needs to be initialized before it can be used in the sub monitor
					progressMonitor.beginTask("Creating Virtual satellit project", 2);
					SubMonitor monitor = SubMonitor.convert(progressMonitor, 2);
					
					// First try to create the project with the standard eclipse wizzard
					// if it fails return and stop continuing
					monitor.split(1).setTaskName("Creating Eclipse project");
					if (!super.performFinish()) {
						result.set(false);
						return;
					} 
					
					// Now get the newly created project and do the virtual satellite specific work with it.
					IProject newProject = VirSatProjectWizard.this.getNewProject(); 
					ResourcesPlugin.getWorkspace().run(VirSatProjectCommons.createNewProjectRunnable(newProject), monitor.split(1));
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirSatProjectWizard: Failure at Project creation", e));
					result.set(false);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirSatProjectWizard: Failure at Project creation", e));
			result.set(false);
		}
		
		return result.get();
	}
}
