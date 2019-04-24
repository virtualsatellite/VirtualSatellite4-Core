/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.treemanager;

import java.util.List;

import org.eclipse.core.resources.IProject;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.visualisation.VisualisationTreeInstantiator;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Start all Managers
 */
public class StartManagers {
	private static TreeManager treeManager = new TreeManager();
	private static VisualisationTreeInstantiator visTreeInstantiator;
	private static List<StructuralElementInstance> rootSeisToVisualise = null;

	/**
	 * Private constructor
	 */
	private StartManagers() {
	}
	
	private static IProject project;
	private static VisualisationDeltaModel visDeltaModel;
	
	/**
	 * This method is usually called by the resource listener to make the visualization reload/
	 * reinitialize. This is needed when file resources change e.g. after an svn update
	 * @return true in case visualization got correctly
	 */
	public static boolean restartVis() {
		if (project != null) {			
			StartManagers.stopVis();
			return StartManagers.startVis(project, visDeltaModel);
		}
		return false;
	}
		
	
	/**
	 * Start Visualisation
	 * @param project project to visualise
	 * @param visDeltaModel visualidation delta model to overlay with actual vis values. Can be null
	 * @return true: The project has been loaded successful false: No existing project or root element
	 */
	public static boolean startVis(IProject project, VisualisationDeltaModel visDeltaModel) {
		if (project == null) {
			return false;
		}
		StartManagers.visDeltaModel = visDeltaModel;
		StartManagers.project = project;
		VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(project);
		Repository repository = resourceSet.getRepository();
		visTreeInstantiator = new VisualisationTreeInstantiator(treeManager, repository, visDeltaModel);
		visTreeInstantiator.setRootSeisToVisualise(rootSeisToVisualise);
		visTreeInstantiator.createVisualisationObjects();
		visTreeInstantiator.attachVisualisationChangeListeners();
		return true;
	}

	/**
	 * Clears the SceneGraph and remove the listeners to stop the visualisation
	 */
	public static void stopVis() {
		if (visTreeInstantiator != null) {
			visTreeInstantiator.removeVisualisationChangeListeners();
		}	
		treeManager.deleteWholeSceneGraph();
	}

	/**
	 * Returns the current protobuf tree manager
	 * @return protobuf tree manager
	 */
	public static TreeManager getTreeManager() {
		return treeManager;
	}
	
	/**
	 * Restarts visualisation, filtering root entities to show
	 * @param filteredRootSeisToVisualise list of root SEIs which should be visualised with their subtrees
	 */
	public static void applyRootEntitiesFilter(List<StructuralElementInstance> filteredRootSeisToVisualise) {
		rootSeisToVisualise = filteredRootSeisToVisualise;
		StartManagers.restartVis();
	}
}
