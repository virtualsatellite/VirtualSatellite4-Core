/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth;

public class ServerConfiguration {

	private static ServerConfiguration instance = null;
	
	private String serverUserHandler;
	
	private ServerConfiguration() {
		readConfiguration();
	}
	
	public static ServerConfiguration getInstance() {
		if (instance == null) {
			instance = new ServerConfiguration();
		}
		return instance;
	}
	
	private void readConfiguration() { };
	
	public void writeConfiguration() { }

	public String getServerUserHandler() {
		return serverUserHandler;
	}

	public void setServerUserHandler(String serverUserHandler) {
		this.serverUserHandler = serverUserHandler;
	};
}
