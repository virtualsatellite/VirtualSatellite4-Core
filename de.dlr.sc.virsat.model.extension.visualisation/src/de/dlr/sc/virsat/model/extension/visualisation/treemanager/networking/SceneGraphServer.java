/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking;

import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.model.extension.visualisation.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.IVisUpdateHandler;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.TreeManager;

/**
 * Responsible for Exchange of SceneGraph messages.
 */
public class SceneGraphServer extends CommunicationServer {
	public static final int SCENEGRAPH_PORT_OUTGOING = 3175;
	public static final int SCENEGRAPH_PORT_INCOMING = 3174;
	
	/**
	 * Constructor
	 * 
	 * @param treeManager	Local TreeManager of this Server which maintains the SceneGraph data.
	 * @param visUpdateHandler vis update handler
	 */
	public SceneGraphServer(TreeManager treeManager, IVisUpdateHandler visUpdateHandler) {
		super(treeManager, visUpdateHandler, SCENEGRAPH_PORT_OUTGOING, SCENEGRAPH_PORT_INCOMING);
		treeManager.setSceneGraphCommunicationHandler(this);
	}
	
	/**
	 * Sends SceneGraph to clients.
	 */
	@Override
	protected void processVisualisationRequest() {
		activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), 
				"[" + this.getClass().getSimpleName() + "] Send Scene Graph to new Client."));
		sendSceneGraph(treeManager.getSceneGraph().build());
	}
}
