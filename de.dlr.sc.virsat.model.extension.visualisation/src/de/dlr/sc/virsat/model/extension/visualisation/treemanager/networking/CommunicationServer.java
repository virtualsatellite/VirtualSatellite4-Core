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
import de.dlr.sc.visproto.VisProto.HandshakeReply;
import de.dlr.sc.visproto.VisProto.SceneGraph;
import de.dlr.sc.visproto.VisProto.VisualisationMessage;

/**
 * Responsible for Exchange of Data
 */
public abstract class CommunicationServer extends CommunicationHandler {
	
	private static final String IP_ADDRESS = "*"; 		// any ip address is permitted
	
	/**
	 * Constructor
	 * 
	 * @param treeManager	Local TreeManager of this Server which maintains the SceneGraph data.
	 * @param visUpdateHandler vis update handler
	 * @param portOut		Port to which this servers publisher socket binds.
	 * @param portIn		Port to which this servers subscriber socket binds.
	 */
	public CommunicationServer(TreeManager treeManager, IVisUpdateHandler visUpdateHandler, int portOut, int portIn) {
		super(treeManager, visUpdateHandler, IP_ADDRESS, portOut, portIn, true);
	}
	
	

	/**
	 * Performs Handshake and processes VisualisationRequest. Afterwards processes incoming messages from 
	 * Visualisation Client.
	 */
	@Override
	protected void processMessage(VisualisationMessage visualisationMessage) {
		if (visualisationMessage.hasHandshakeRequest()) {
			sendHandshakeReply();
		} else if (visualisationMessage.hasVisualisationRequest()) {
			processVisualisationRequest();
		} else {
			super.processMessage(visualisationMessage);
		}
	}
	
	/**
	 * Processes incoming VisualisationRequests from Visualisation Clients.
	 */
	protected abstract void processVisualisationRequest();
	
	@Override
	public void sendSceneGraph(SceneGraph sceneGraph) {
		VisualisationMessage.Builder messageBuilder = VisualisationMessage.newBuilder();
		messageBuilder.setSceneGraph(sceneGraph);
		byte[] serializedSceneGraph = serializeProtobufMessage(messageBuilder);
		sendMessage(serializedSceneGraph);
		if (visUpdateHandler != null) {
			visUpdateHandler.updateVisualisationData(messageBuilder.build());
		}
	}
	
	/**
	 * Send the handshake reply to clients
	 */
	public void sendHandshakeReply() {
		VisualisationMessage.Builder messageBuilder = VisualisationMessage.newBuilder();
		messageBuilder.setHandshakeReply(HandshakeReply.getDefaultInstance());
		byte[] serializedMessage = serializeProtobufMessage(messageBuilder);
		sendMessage(serializedMessage);
	}
	
}
