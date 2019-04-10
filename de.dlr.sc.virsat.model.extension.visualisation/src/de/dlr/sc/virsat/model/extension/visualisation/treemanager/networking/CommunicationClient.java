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

import com.google.protobuf.InvalidProtocolBufferException;

import de.dlr.sc.virsat.model.extension.visualisation.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.IVisUpdateHandler;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.TreeManager;
import de.dlr.sc.visproto.VisProto.HandshakeRequest;
import de.dlr.sc.visproto.VisProto.VisualisationMessage;
import de.dlr.sc.visproto.VisProto.VisualisationRequest;

/**
 * CommunicationClient is the CommunicationHandler for Visualisation Clients. Specifically 
 * for the DebugClient. 
 * 
 * @author bara_at
 *
 */
public class CommunicationClient extends CommunicationHandler {
	
	public static final String IP_ADDRESS = "127.0.0.1";	// localhost
	public static final int HANDSHAKE_REQUEST_RESEND_TIMEOUT = 500;
	
	/**
	 * CommunicationClient reverses Incoming Ports and Outgoing ports from the 
	 * CommunicationServer. 
	 * 
	 * @param treeManager 	A new TreeManager which maintains the SceneGraph data 
	 * for the SceneGraph of this client.
	 * @param visUpdateHandler Vis update handler
	 * @param portOut		The port to which this clients publisher socket connects.
	 * @param portIn		The port to which this clients subscriber socket connects.
	 */
	public CommunicationClient(TreeManager treeManager, IVisUpdateHandler visUpdateHandler, int portOut, int portIn) {
		super(treeManager, visUpdateHandler, IP_ADDRESS, portOut, portIn, false);
	}
	
	/**
	 * Sends a Request-Message to the server which will respond with the SceneGraph
	 * and all STL-Files it has in the protoGeometryFileMap.
	 */
	public void sendVisualisationRequest() {
		VisualisationMessage.Builder messageBuilder = VisualisationMessage.newBuilder();
		messageBuilder.setVisualisationRequest(VisualisationRequest.getDefaultInstance());
		byte[] serializedMessage = super.serializeProtobufMessage(messageBuilder);
		super.sendMessage(serializedMessage);
	}

	/**
	 * Sends a handshake request message to make sure connection is established
	 * before requesting visualisation data
	 */
	public void sendHandshakeRequest() {
		VisualisationMessage.Builder messageBuilder = VisualisationMessage.newBuilder();
		messageBuilder.setHandshakeRequest(HandshakeRequest.getDefaultInstance());
		byte[] serializedMessage = super.serializeProtobufMessage(messageBuilder);
		super.sendMessage(serializedMessage);
	}
	
	@Override
	public void run() {
		handshake();
		sendVisualisationRequest();
		super.run();
	}

	/**
	 * sends a handshake request and waits for a reply.
	 * resends a handshake request after timeout if necessary
	 */
	private void handshake() {
		activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), "[" + this.getClass().getSimpleName() + "] Sending handshake request."));
		sendHandshakeRequest();
		VisualisationMessage protobufMessage = null;
		int oldReceiveTimeout = subscriberSocket.getReceiveTimeout();
		subscriberSocket.setReceiveTimeout(HANDSHAKE_REQUEST_RESEND_TIMEOUT);
		while (protobufMessage == null || !protobufMessage.hasHandshakeReply()) {
			byte[] message = subscriberSocket.receive();
			if (message != null) {
				try {
					protobufMessage = VisualisationMessage.parseFrom(message);
					processMessage(protobufMessage);
				} catch (InvalidProtocolBufferException e) {
					activator.getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "[" + this.getClass().getSimpleName() + "] Protocol Buffer is invalid.", e));
				}
			} else {
				activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), "[" + this.getClass().getSimpleName() + "] Resending handshake request."));
				sendHandshakeRequest();
			}
		}
		subscriberSocket.setReceiveTimeout(oldReceiveTimeout);
	}
}
