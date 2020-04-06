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
import de.dlr.sc.virsat.model.extension.visualisation.comparison.ModelPropertyColorMap;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModelIo;
import de.dlr.sc.virsat.model.extension.visualisation.ui.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.ui.VtkClientView;
import de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs.ColorMapPropertyDialog;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * This handler creates the Visualization delta models used for color map
 * @author liu_yg
 *
 */
public class ColorMapPropertyHandler extends AbstractHandler implements IHandler {

	private static final String COLOR_MAP_FILE_EXT_SPLIT = "_";
	private VtkClientView vtkViewer = null;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event);
		Object object = selection.getFirstElement();
		
		try {
			if (object instanceof VirSatProjectResource) {
				VirSatProjectResource vsBaseProject = (VirSatProjectResource) object;
	
				Shell shell = Display.getCurrent().getActiveShell();
				ColorMapPropertyDialog dialog = new ColorMapPropertyDialog(shell, vsBaseProject);
				
				if (dialog.open() == Window.OK) {
					String propertyFQN = dialog.getComparisonProjectPropertyFQN(); 
					if (propertyFQN != null) {
						new ProgressMonitorDialog(shell).run(true, false, (pm) -> {
							try {
								Repository vsBaseRepo = VirSatResourceSet.getResourceSet(vsBaseProject.getWrappedProject()).getRepository();

								ModelPropertyColorMap compareModelPropertyColorMap = new ModelPropertyColorMap(pm);
								VisualisationDeltaModel deltaModel = compareModelPropertyColorMap.colorMap(vsBaseRepo, propertyFQN);
								
								double max = compareModelPropertyColorMap.getColorMapMaxValue();
								double min = compareModelPropertyColorMap.getColorMapMinValue();		

								String fileExt = CreateDeltaModelFolderHandler.VIS_COLOR_MAP_MODEL_FILE_EXT + COLOR_MAP_FILE_EXT_SPLIT + String.format("%.1f", min) + COLOR_MAP_FILE_EXT_SPLIT + String.format("%.1f", max);
								
								IFile deltaModelFile;
								deltaModelFile = CreateDeltaModelFolderHandler.getNewColorMapFile(vsBaseProject, fileExt);
								File file = new File(deltaModelFile.getLocation().toOSString());
								VisualisationDeltaModelIo.writeModel(deltaModel, new FileOutputStream(file));
								deltaModelFile.refreshLocal(IResource.DEPTH_INFINITE, null);
							} catch (CoreException | FileNotFoundException e) {
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "ColorMapPropertyHandler: Failed to create DeltaModel ", e));				
							}
						});
						vtkViewer = VtkClientView.getViewer();
						vtkViewer.addDeltaComboInput();
					}
				}
			}
		} catch (InvocationTargetException | InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "ColorMapPropertyHandler: Failed to create DeltaModel ", e));				
		}
			
		return null;
	}
}
