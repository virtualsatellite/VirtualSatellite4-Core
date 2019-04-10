/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.debugClient;

import java.sql.Timestamp;

import de.dlr.sc.virsat.model.extension.visualisation.treemanager.IVisUpdateHandler;
import de.dlr.sc.virsat.model.extension.visualisation.ui.DebugClientView;
import de.dlr.sc.visproto.VisProto.VisualisationMessage;

/**
 * This class gets messages from CommunicationHandler, updates IVisualisationTreeManager accordingly and writes into the debug log.
 * 
 * @author bara_at
 *
 */
public class DebugClientVisUpdateHandler implements IVisUpdateHandler {
	
	private static DebugClientVisUpdateHandler debugClientVisUpdateHandler;
	private static DebugClientView debugClientView;
	
	/**
	 * Singleton
	 */
	private DebugClientVisUpdateHandler() {
		
	}
	
	/**
	 * Returns the instance of this VisUpdateHandler or creates one if it doesn't exist.
	 * 
	 * @return debugClientVisUpdateHandler;
	 */
	public static synchronized IVisUpdateHandler getInstance() {
		if (debugClientVisUpdateHandler == null) {
			debugClientVisUpdateHandler = new DebugClientVisUpdateHandler();
		}
		return debugClientVisUpdateHandler;
	}

	@Override
	public synchronized void updateVisualisationData(VisualisationMessage visualisationMessage) {
		String timeOfUpdate = new Timestamp(System.currentTimeMillis()).toString();
		debugClientView.updateTimestamp(timeOfUpdate);
		StringBuilder logMessage = new StringBuilder();
		logMessage.append("[" + timeOfUpdate + "]");
		if (visualisationMessage.hasSceneGraph()) {
			logMessage.append(" ! SCENE GRAPH message received.\n");
			debugClientView.logSceneGraphMessage(visualisationMessage.getSceneGraph().toString());
		} else if (visualisationMessage.hasGeometryFile()) {
			logMessage.append(" ! GEOMETRY FILE message received.\n");
		} else if (visualisationMessage.hasHandshakeReply()) {
			logMessage.append(" ! HANDSHAKE REPLY message received.\n");
		} else {
			logMessage.append(" ! UNKNOWN message received.\n");
		}
		logMessage.append(" - Message Size: \t\t");
		logMessage.append(visualisationMessage.getSerializedSize()).append(" bytes\n");
		debugClientView.logDebugMessage(logMessage.toString());
	}
	
	/**
	 * Sets the DebugClientView for this VisUpdateHandler
	 * @param view 
	 */
	public static void setDebugClientView(DebugClientView view) {
		debugClientView = view;
	}
}
