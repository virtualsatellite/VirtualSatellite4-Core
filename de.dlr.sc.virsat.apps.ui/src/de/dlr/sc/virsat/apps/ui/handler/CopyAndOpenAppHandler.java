/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.ui.handler;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;

import de.dlr.sc.virsat.apps.initializer.VirsatAppsInitializer;
import de.dlr.sc.virsat.apps.ui.Activator;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * Abstract handler for copying and opening virsat apps
 * @author fisc_ph
 *
 */

public abstract class CopyAndOpenAppHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			
			// Get the currently selected VirSat Project to determine where the Apps folder is
			IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
			VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
			IProject virSatProject = selectionHelper.getProjectResource();

			if (virSatProject == null) {
				MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Could not initialize App", "Failed to find correct project. Please select the project in which to create the App");
				return null;
			}
				
			IFolder scriptFolder = virSatProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS);
			
			// Now create the new App with the given Name
			IFile scriptFile = scriptFolder.getFile(getAppName());
			
			if (scriptFile.exists()) {
				MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Could not initialize App", "The App you try to initialize is already created: " + getAppName());
				return null;
			}
			
			scriptFile.create(getAppStream(), true, new NullProgressMonitor());
		
			// And finally try to open the newly generated App in the correct Editor (Java Editor)
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
			IDE.openEditor(workbenchPage, scriptFile, true);
		
		} catch (CoreException | IOException e) {
			Status status = new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to copy and open app: " + getAppName(), e);
			Activator.getDefault().getLog().log(status);
			ErrorDialog.openError(Display.getDefault().getActiveShell(), "Failed to open app " + getAppName(), "Copying and opening of app " + getAppName() +  " failed.", status);
		}
		
		return null;
	}

	/**
	 * Get the input stream for reading the app
	 * @return an input stream for reading the app
	 * @throws IOException exception that will be thrown if obtaining the input stream fails
	 */
	protected abstract InputStream getAppStream() throws IOException;

	/**
	 * Get the file name of the app
	 * @return the file name of the app
	 */
	protected abstract String getAppName();
}
