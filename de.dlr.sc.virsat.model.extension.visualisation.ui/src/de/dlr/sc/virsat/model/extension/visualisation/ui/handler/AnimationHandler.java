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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.model.extension.visualisation.comparison.CompareModelGeometry;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.ui.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.ui.VtkClientView;
import de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs.AnimationDialog;


/**
 * This handler creates the Visualization delta models used for animations
 * @author liu_yg
 *
 */
public class AnimationHandler extends AbstractHandler implements IHandler {

	private List<VirSatProjectResource> listProjectSelected = null;
	private List<VisualisationDeltaModel> listDeltaModel = null;
    private VtkClientView vtkViewer = null;
	
	private static final int START_INDEX = 1; 
	
	@Override	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			Shell shell = Display.getCurrent().getActiveShell();
			AnimationDialog dialog = new AnimationDialog(shell);
			
			if (dialog.open() == Window.OK) {
				listProjectSelected = dialog.getProjectsResource();
				listDeltaModel = new ArrayList<VisualisationDeltaModel>();
				
				if (listProjectSelected.size() > 1) {
					new ProgressMonitorDialog(shell).run(true, false, (pm) -> {	
						for (int i = START_INDEX; i < listProjectSelected.size(); i++) {
							Repository vsBaseRepo = VirSatResourceSet.getResourceSet(listProjectSelected.get(i).getWrappedProject()).getRepository();
							Repository vsCompareRepo = VirSatResourceSet.getResourceSet(listProjectSelected.get(i - 1).getWrappedProject()).getRepository();
							
							VisualisationDeltaModel deltaModel = new CompareModelGeometry(pm).compare(vsBaseRepo, vsCompareRepo);	
							listDeltaModel.add(deltaModel);
						}
				    });				
					vtkViewer = VtkClientView.getViewer();
					vtkViewer.setAnimationProjectComboInput(listProjectSelected, listDeltaModel);
				}
			}
		
		} catch (InvocationTargetException | InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "AnimationHandler: Failed to create DeltaModel ", e));				
		}
				
		return null;
	}
	
	/**
	 * Call this method to get the projects list that was selected for animation
	 * @return the selected projects as list of VirSatProjectResource
	 */
	public List<VirSatProjectResource> getListProjectSelected()	{
		return listProjectSelected;
	}
	/**
	 * Call this method to get the visualisationDeltaModel list
	 * @return the visualisationDeltaModel list
	 */
	public List<VisualisationDeltaModel> getlistDeltaModel()	{
		return listDeltaModel;
	}
}
