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

import de.dlr.sc.virsat.model.extension.visualisation.treemanager.IVisUpdateHandler;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.TreeManager;

/**
 * SceneGraph CommunicationClient
 */
public class SceneGraphClient extends CommunicationClient {
	
	public static final int SCENEGRAPH_PORT_OUTGOING = SceneGraphServer.SCENEGRAPH_PORT_INCOMING;
	public static final int SCENEGRAPH_PORT_INCOMING = SceneGraphServer.SCENEGRAPH_PORT_OUTGOING;
	
	/**
	 * Constructor
	 * 
	 * @param treeManager	Local TreeManager of this Server which maintains the SceneGraph data.
	 * @param visUpdateHandler vis update handler
	 */
	public SceneGraphClient(TreeManager treeManager, IVisUpdateHandler visUpdateHandler) {
		super(treeManager, visUpdateHandler, SCENEGRAPH_PORT_OUTGOING, SCENEGRAPH_PORT_INCOMING);
		treeManager.setSceneGraphCommunicationHandler(this);
	}
}
