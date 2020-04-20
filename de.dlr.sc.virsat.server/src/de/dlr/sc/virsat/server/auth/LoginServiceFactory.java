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

import java.io.IOException;

import org.eclipse.core.runtime.Status;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

public class LoginServiceFactory {

	/**
	 * @return a concrete LoginService defined in the ServerConfiguration or null
	 * @throws IOException 
	 */
	public LoginService getLoginService() throws IOException {
		LoginService service = null;
		
		String serviceName = ServerConfiguration.getLoginServiceClass();
		
		try {
			service = (LoginService) Class.forName(serviceName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Couldn't create instance for " + ServerConfiguration.getLoginServiceClass(), e));
		}
		
		if (service instanceof HashLoginService) {
			((HashLoginService) service).setConfig(Activator.getDefault().getAuthFilePathResolved());
		}
		
		return service;
	}

}
