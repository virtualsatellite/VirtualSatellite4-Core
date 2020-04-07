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
import org.zeromq.ZMQ;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLite.Builder;

import de.dlr.sc.virsat.model.extension.visualisation.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.IVisUpdateHandler;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.TreeManager;
import de.dlr.sc.visproto.VisProto.GeometryFile;
import de.dlr.sc.visproto.VisProto.SceneGraph;
import de.dlr.sc.visproto.VisProto.VisualisationMessage;

/**
 * Abstact base class for CommunicationClient and CommunicationServer cointaining all mutual methods.
 * These methods include receiving and sending data.
 * 
 * @author bara_at
 *
 */
public abstract class CommunicationHandler extends Thread {
	
	protected Activator activator = new Activator();
	protected IVisUpdateHandler visUpdateHandler;
	protected ZMQSocket publisherSocket;
	protected ZMQSocket subscriberSocket;
	protected TreeManager treeManager;
	private int portOutgoing;
	private int portIncoming;
	private boolean running;
	private Thread t;
	
	private static final int SUBSCRIBER_SOCKET_RECEIVE_TIMEOUT = 200;

	//private static final int THREAD_MAX_WAIT_TIME = 100;
	//private Vector<byte[]> newMessages = new Vector<byte[]>();

	/**
	 * Constructor
	 * 
	 * @param treeManager 	A new TreeManager which maintains the SceneGraph-data 
	 * for the SceneGraph of this server.
	 * @param visUpdateHandler vis update handler
	 * @param ipAddress 	IP address to which a CommunicationServer binds, and a 
	 * ComminicationClient connects.
	 * @param portOut 		Port number of the publisher socket.
	 * @param portIn 		Port number of the subscriber socket.
	 * @param bindSockets 	if true use ZMQ bind, otherwise ZMQ connect
	 */
	public CommunicationHandler(TreeManager treeManager, IVisUpdateHandler visUpdateHandler, String ipAddress, int portOut, int portIn, boolean bindSockets) {
		this.treeManager = treeManager;
		this.visUpdateHandler = visUpdateHandler;
		portOutgoing = portOut;
		portIncoming = portIn;
		
		publisherSocket = new ZMQSocket();
		publisherSocket.open(getEndpoint(ipAddress, portOutgoing), ZMQ.PUB, bindSockets);
		
		subscriberSocket = new ZMQSocket();
		subscriberSocket.open(getEndpoint(ipAddress, portIncoming), ZMQ.SUB, bindSockets);
		subscriberSocket.setReceiveTimeout(SUBSCRIBER_SOCKET_RECEIVE_TIMEOUT);
		subscriberSocket.subscribe();
		
		running = true;
		t = new Thread(this, "VisUpdateReceivingThread");
		t.start();
	}

	/**
	 * Send the passed sceneGraphBuilder to clients
	 * @param sceneGraph 	Protobuf SceneGraph to send
	 */
	public void sendSceneGraph(SceneGraph sceneGraph) {
		VisualisationMessage.Builder messageBuilder = VisualisationMessage.newBuilder();
		messageBuilder.setSceneGraph(sceneGraph);
		byte[] serializedSceneGraph = serializeProtobufMessage(messageBuilder);
		sendMessage(serializedSceneGraph);
	}
	
	/**
	 * Send the passed geometryFile to clients
	 * @param geometryFile 	Protobuf GeometryFile to send.
	 */
	public void sendGeometryFile(GeometryFile geometryFile) {
		VisualisationMessage.Builder messageBuilder = VisualisationMessage.newBuilder();
		messageBuilder.setGeometryFile(geometryFile);
		byte[] serializedMessage = serializeProtobufMessage(messageBuilder);
		sendMessage(serializedMessage);
	}

	/**
	 * Sends a message via publisher socket
	 * @param serializedMessage 
	 */
	protected void sendMessage(byte[] serializedMessage) {
		if (running) {
			publisherSocket.send(serializedMessage);
		}
	}
	
	/**
	 * @param protobufBuilder 	builder for any protobuf message such as SceneGraph or GeometryFile
	 * @return serialized message
	 */
	protected byte[] serializeProtobufMessage(Builder protobufBuilder) {
		MessageLite message = protobufBuilder.build();
		byte[] serializedMessage = message.toByteArray();
		return serializedMessage;
	}
	
	
	/**
	 * Generates Endpoint string for ZMQ for given port
	 * @param port 
	 * @param ipAddress 
	 * @return endpoint string
	 */
	public String getEndpoint(String ipAddress, int port) {
		return "tcp://" + ipAddress + ":" + port;
	}
	
	@Override
	public void run() {
		activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), "[" + this.getClass().getSimpleName() + "] Starting Vis Update Receiving Thread.", null));
		while (running) {
			byte[] message = subscriberSocket.receive();
			if (message != null) {
				try {
					VisualisationMessage protobufMessage = VisualisationMessage.parseFrom(message);
					processMessage(protobufMessage);
				} catch (InvalidProtocolBufferException e) {
					activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "[" + this.getClass().getSimpleName() + "] Protocol Buffer is invalid.", e));
				}
			}
		}
		activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), "[" + this.getClass().getSimpleName() + "] Closing Vis Update Receiving Thread.", null));
	}

	/**
	 * Processes the message obtained from the subscriber socket
	 * @param visualisationMessage 	One of three VisualisationMessages (SceneGraph, GeometryFile, Request) to be processed.
	 */
	protected void processMessage(VisualisationMessage visualisationMessage) {
		if (visualisationMessage.hasSceneGraph() || visualisationMessage.hasGeometryFile()) {
			treeManager.update(visualisationMessage);
		}
		visUpdateHandler.updateVisualisationData(visualisationMessage);
	}
	
	/**
	 * Terminates Sockets and releases Ports
	 */
	public void close() {
		try {
			running = false;
			t.join();
			publisherSocket.close();
			subscriberSocket.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
