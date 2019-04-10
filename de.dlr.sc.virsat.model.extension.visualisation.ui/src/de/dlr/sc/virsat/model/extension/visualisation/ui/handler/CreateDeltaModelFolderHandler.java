/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.handler;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;


import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.model.extension.visualisation.ui.Activator;

/**
 * This handler creates the Visualization Folder where the delta m shall be stored
 * @author fisc_ph
 *
 */
public class CreateDeltaModelFolderHandler extends AbstractHandler implements IHandler {
	
	public static final String VIS_DELTA_MODEL_FOLDER = "visDeltas";
	public static final String VIS_DELTA_MODEL_GEO_FILE_EXT = "vdmg";
	public static final String VIS_COLOR_MAP_MODEL_FILE_EXT = "vcmm";
	public static final String VIS_DELTA_MODEL_PARA_FILE_EXT = "vdmp";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event);
		Object object = selection.getFirstElement();
		
		if (object instanceof VirSatProjectResource) {
			VirSatProjectResource vsProject = (VirSatProjectResource) object;
			IProject project = vsProject.getWrappedProject();
			Shell shell = Display.getDefault().getActiveShell();
			
			try {
				new ProgressMonitorDialog(shell).run(true, false, (pm) -> {
					IFolder folderVisDeltaModels = project.getFolder(VIS_DELTA_MODEL_FOLDER);
					if (!folderVisDeltaModels.exists()) {
						try {
							folderVisDeltaModels.create(IResource.NONE, true, SubMonitor.convert(pm, 1));
							Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "CreateDeltaModelFolderHandler: Successfully created folder " + VIS_DELTA_MODEL_FOLDER, null));				
						} catch (CoreException e) {
							Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "CreateDeltaModelFolderHandler: Failed to create " + VIS_DELTA_MODEL_FOLDER, e));				
						}
					}
				});
			} catch (InvocationTargetException | InterruptedException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "CreateDeltaModelFolderHandler: Failed to create " + VIS_DELTA_MODEL_FOLDER, e));				
			}
		}
		
		return null;
	}
	
	public static final String VIS_DELTA_MODEL_FILE_COMPARE_PROPERTY = "Comparison";
	
	/**
	 * This method creates a new file in the workspace. In case a file already exists it will count up and create the next one
	 * @param vsProject the project in which to create a vis delta file
	 * @param fileExt the extension of IFile
	 * @return the iFile which got created
	 * @throws CoreException Exception that can be thrown in case the file cannot be created
	 */
	public static IFile getNewComparisonFile(VirSatProjectResource vsProject, String fileExt) throws CoreException {
		IProject project = vsProject.getWrappedProject();
		IFolder filderVisDeltaModels = project.getFolder(VIS_DELTA_MODEL_FOLDER);
		if (!filderVisDeltaModels.exists()) {
			filderVisDeltaModels.create(IResource.NONE, true, new NullProgressMonitor());
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "CreateDeltaModelFolderHandler: Successfully created folder " + VIS_DELTA_MODEL_FOLDER, null));				
		}
		
		IFile retFile = null;
		int fileCounter = 0;
		do {
			fileCounter++;
			retFile = filderVisDeltaModels.getFile(VIS_DELTA_MODEL_FILE_COMPARE_PROPERTY + "_" + fileCounter + "." + fileExt);
		} while (retFile.exists());
		
		return retFile;
	}
	
	public static final String VIS_COLOR_MAP_MODEL_FILE_COMPARE_PROPERTY = "ColorMap";
	
	/**
	 * This method creates a new file in the workspace. In case a file already exists it will count up and create the next one
	 * @param vsProject the project in which to create a vis delta file
	 * @param fileExt the extension of IFile
	 * @return the iFile which got created
	 * @throws CoreException Exception that can be thrown in case the file cannot be created
	 */
	public static IFile getNewColorMapFile(VirSatProjectResource vsProject, String fileExt) throws CoreException {
		IProject project = vsProject.getWrappedProject();
		IFolder filderVisDeltaModels = project.getFolder(VIS_DELTA_MODEL_FOLDER);
		if (!filderVisDeltaModels.exists()) {
			filderVisDeltaModels.create(IResource.NONE, true, new NullProgressMonitor());
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "CreateDeltaModelFolderHandler: Successfully created folder " + VIS_DELTA_MODEL_FOLDER, null));				
		}
		
		IFile retFile = null;
		int fileCounter = 0;
		do {
			fileCounter++;
			retFile = filderVisDeltaModels.getFile(VIS_COLOR_MAP_MODEL_FILE_COMPARE_PROPERTY + "_" + fileCounter + "." + fileExt);
		} while (retFile.exists());
		
		return retFile;
	}
	
}
