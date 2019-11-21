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
import org.zeromq.ZMQ.Context;

import de.dlr.sc.virsat.model.extension.visualisation.Activator;

/**
 * 
 * This class is responsible for the sockets and their communication
 *
 */
public class ZMQSocket {
	private Activator activator = new Activator();
	private ZMQ.Socket socket;
	
	private int receiveTimeout = 0;
	
	/**
	 * Initialize Context and Socket
	 */
	public ZMQSocket() {
		socket = null;
	}

	/**
	 * Setup Socket and open Port
	 * 
	 * @param endpoint String that contains the type of protocol and the port
	 * @param type Type of Socket
	 * @param bindSockets 
	 */
	public void open(String endpoint, int type, boolean bindSockets) {
		Context context = ZMQ.context(1);
		socket = context.socket(type);
		try {
			if (bindSockets) {
				socket.bind(endpoint);
			} else {
				socket.connect(endpoint);
			}
		} catch (Exception e) {
			activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Error during port binding.", e));
		}
		socket.setReceiveTimeOut(receiveTimeout);
	}

	/**
	 * Send byte-array
	 * 
	 * @param str byte-array containing the information
	 */
	public void send(byte[] str) {
		socket.send(str, 0);
	}
	
	/**
	 * Receive byte-array
	 * @return message received message byte array. Can be null if nothing received. Can be blocking or non-blocking, see {@link #setReceiveTimeout(int)}
	 */
	public byte[] receive() {
		return socket.recv();
	}
	
	/**
	 * Sets a timeout for {@link #receive()} method.
	 * @param receiveTimeout timeout in milliseconds. By default zero (non-blocking call). If set to -1, the timeout is infinite (blocking call)
	 */
	public void setReceiveTimeout(int receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
		socket.setReceiveTimeOut(receiveTimeout);
	}
	
	/**
	 * Gets receiveTimeout
	 * @return receiveTimeout in milliseconds
	 */
	public int getReceiveTimeout() {
		return receiveTimeout;
	}

	/**
	 * Set Subscriber Filter
	 */
	public void subscribe() {
		socket.subscribe("".getBytes());
	}
	
	/**
	 * Terminates the socket and releases port
	 */
	public void close() {
		socket.close();
	}
}
