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
import de.dlr.sc.visproto.VisProto.GeometryFile;

/**
 * Responsible for Exchange of GeometryFile messages.
 */
public class GeometryFileServer extends CommunicationServer {

	public static final int GEOMETRYFILE_PORT_OUTGOING = 3177;
	public static final int GEOMETRYFILE_PORT_INCOMING = 3176;
	
	/**
	 * Constructor
	 * 
	 * @param treeManager	Local TreeManager of this Server which maintains the SceneGraph data.
	 * @param visUpdateHandler vis update handler
	 */
	public GeometryFileServer(TreeManager treeManager, IVisUpdateHandler visUpdateHandler) {
		super(treeManager, visUpdateHandler, GEOMETRYFILE_PORT_OUTGOING, GEOMETRYFILE_PORT_INCOMING);
		treeManager.setGeometryFileCommunicationHandler(this);
	}
	
	/**
	 * Sends all GeometryFiles to clients.
	 */
	@Override
	protected void processVisualisationRequest() {
		activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), 
				"[" + this.getClass().getSimpleName() + "] Send Geometry Files to new Client."));
		for (GeometryFile geometryFile : treeManager.getProtoGeometryFileMapValues()) {
			sendGeometryFile(geometryFile);
		}
	}
}
