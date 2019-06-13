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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.model.extension.visualisation.comparison.CompareModelProperty;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModelIo;
import de.dlr.sc.virsat.model.extension.visualisation.ui.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.ui.VtkClientView;
import de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs.CompareModelPropertyDialog;


/**
 * This handler creates the Visualization Folder where the delta models shall be stored
 * @author fisc_ph
 *
 */
public class CompareModelPropertyHandler extends AbstractHandler implements IHandler {

	private static VtkClientView vtkViewer = null;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event);
		Object object = selection.getFirstElement();
		
		try {
			if (object instanceof VirSatProjectResource) {
				VirSatProjectResource vsBaseProject = (VirSatProjectResource) object;
	
				Shell shell = Display.getCurrent().getActiveShell();
				CompareModelPropertyDialog dialog = new CompareModelPropertyDialog(shell, vsBaseProject);
				
				if (dialog.open() == Window.OK) {
					VirSatProjectResource vsCompareProject = dialog.getComparisonProjectResource();
					AQudvTypeProperty property  = dialog.getComparisonProjectProperty();
					
					if (vsCompareProject != null && property != null) {
						new ProgressMonitorDialog(shell).run(true, false, (pm) -> {
							try {
								Repository vsBaseRepo = VirSatResourceSet.getResourceSet(vsBaseProject.getWrappedProject()).getRepository();
								Repository vsCompareRepo = VirSatResourceSet.getResourceSet(vsCompareProject.getWrappedProject()).getRepository();
								
								VisualisationDeltaModel deltaModel = new CompareModelProperty(pm).compare(vsBaseRepo, vsCompareRepo, property.getFullQualifiedName());
								IFile deltaModelFile;
								deltaModelFile = CreateDeltaModelFolderHandler.getNewComparisonFile(vsBaseProject, CreateDeltaModelFolderHandler.VIS_DELTA_MODEL_PARA_FILE_EXT);
								File file = new File(deltaModelFile.getLocation().toOSString());
								VisualisationDeltaModelIo.writeModel(deltaModel, new FileOutputStream(file));
								deltaModelFile.refreshLocal(IResource.DEPTH_INFINITE, null);
							} catch (CoreException | FileNotFoundException e) {
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "CompareModelPropertyHandler: Failed to create DeltaModel ", e));				
							}
						});
					}
					vtkViewer = VtkClientView.getViewer();
					vtkViewer.addDeltaComboInput();
				}
			}
		} catch (InvocationTargetException | InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "CompareModelPropertyHandler: Failed to create DeltaModel ", e));				
		}
			
		return null;
	}
}
