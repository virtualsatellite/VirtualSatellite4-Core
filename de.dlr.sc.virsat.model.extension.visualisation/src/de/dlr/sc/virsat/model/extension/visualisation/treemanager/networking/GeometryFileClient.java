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
 * GeometryFile CommunicationClient
 */
public class GeometryFileClient extends CommunicationClient {
	
	public static final int GEOMETRYFILE_PORT_OUTGOING = GeometryFileServer.GEOMETRYFILE_PORT_INCOMING;
	public static final int GEOMETRYFILE_PORT_INCOMING = GeometryFileServer.GEOMETRYFILE_PORT_OUTGOING;
	
	/**
	 * Constructor
	 * 
	 * @param treeManager	Local TreeManager of this Server which maintains the SceneGraph data.
	 * @param visUpdateHandler vis update handler
	 */
	public GeometryFileClient(TreeManager treeManager, IVisUpdateHandler visUpdateHandler) {
		super(treeManager, visUpdateHandler, GEOMETRYFILE_PORT_OUTGOING, GEOMETRYFILE_PORT_INCOMING);
		treeManager.setGeometryFileCommunicationHandler(this);
	}
}
