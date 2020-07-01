/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.vtkClient;

import java.awt.EventQueue;


import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.extension.visualisation.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.IVisUpdateHandler;
import de.dlr.sc.virsat.uiengine.ui.editor.GenericEditor;
import de.dlr.sc.visproto.VisProto.SceneGraphNode;
import de.dlr.sc.visproto.VisProto.VisualisationMessage;
import vtk.vtkProp;

/**
 * 
 * This class is responsible for the communication between the Protobuf
 * SceneGraph and the Vtk SceneGraph
 *
 */
public class VtkClientVisUpdateHandler implements IVisUpdateHandler, IPartListener2 {
	
	private static SceneGraphNode mNode = null;
	private static VtkClientVisUpdateHandler instance = null;
	
	/**
	 * Create a Helper for updating the visualisation
	 */
	private VtkClientVisUpdateHandler() {
	}

	/**
	 * Updates the VtkTreeManager
	 * 
	 * @param visualisationMessage 
	 */
	public synchronized void updateVisualisationData(VisualisationMessage visualisationMessage) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				mNode = visualisationMessage.getSceneGraph().getNode();
				VtkTreeManager visMan = VtkTreeManager.getInstance();
				visMan.clearInvalidActors();
				visMan.forceSceneryUpdate(mNode);
				updateVisualisationScene();
			}
		});	
	}
	
	/**
	 * Iterates through invalid and new actors, processes them and render the scene. This function needs to be called in AWT-0-Thread 
	 */
	public synchronized void updateVisualisationScene() {
		VtkTreeManager visMan = VtkTreeManager.getInstance();
		// remove invalid nodes safely
		for (vtkProp invalidActor : visMan.getInvalidActors()) {
			visMan.GetRenderer().RemoveActor(invalidActor);
		}
		for (vtkProp newActor : visMan.getNewActors()) {
			visMan.GetRenderer().AddActor(newActor);
		}
		VtkTreeManager.getInstance().clearNewActors();
		
		// render the scene
		visMan.Render();
	}

	/**
	 * Get the VisUpdatehandler Instance
	 * 
	 * @return Instance
	 */
	public static synchronized VtkClientVisUpdateHandler getInstance() {
		if (instance == null) {
			instance = new VtkClientVisUpdateHandler();
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow workbenchwindow = workbench.getActiveWorkbenchWindow();
			workbenchwindow.getPartService().addPartListener(instance);
		}
		return instance;
	}
	
	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(true);
		
		try {
			if ((part != null) && (part instanceof GenericEditor)) {
				GenericEditor ge = (GenericEditor) part;
				EObject editorObject = ge.getEditorModelObject();
				String uuid = null;
				if (editorObject instanceof ICategoryAssignmentContainer) {
					ICategoryAssignmentContainer caContainer = (ICategoryAssignmentContainer) editorObject;
					uuid = caContainer.getUuid().toString();
				} else if (editorObject instanceof CategoryAssignment) {
					CategoryAssignment ca = (CategoryAssignment) editorObject;
					ICategoryAssignmentContainer caContainer = ca.getCategoryAssignmentContainer();
					uuid = caContainer.getUuid().toString();
				}
				
				VtkTreeManager.getInstance().checkIdAndCreateAxes(uuid);
				VtkTreeManager.getInstance().highlightIfSelectedObject(uuid);
			}
		} catch (UnsatisfiedLinkError | NoClassDefFoundError e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not activate Visualization sever. Most likely VTK and ZeroMQ did not get loaded correctly. Error: " + e.getMessage()));
		}
	}		

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {	
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		partActivated(partRef);
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
	}
}
