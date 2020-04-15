/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth.userhandler;

import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

public class ServerUserHandlerFactory {

	/**
	 * @return a concrete IServerUserHandler defined in the ServerConfiguration or null
	 */
	public IServerUserHandler getServerUserHandler() {
		IServerUserHandler handler = null;
		
		String handlerName = ServerConfiguration.getUserHandlerClass();
		try {
			handler = (IServerUserHandler) Class.forName(handlerName).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return handler;
	}

}
