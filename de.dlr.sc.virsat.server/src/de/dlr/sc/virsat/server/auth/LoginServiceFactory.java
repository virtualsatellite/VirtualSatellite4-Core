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
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

public class LoginServiceFactory {

	/**
	 * @return a concrete LoginService defined in the ServerConfiguration or null
	 */
	public LoginService getLoginService() {
		LoginService service = null;
		
		String serviceName = ServerConfiguration.getLoginServiceClass();
		
		if (serviceName.equals(HashLoginService.class.getName())) {
			String realmResourceName = "resources/auth.properties";
			try {
				// TODO: do we need a default path here?
				// this should probably be provided via command line in activator also
				// TODO: actually test hashed passwords
				URL realmProps = FileLocator.resolve(FileLocator.find(Activator.getDefault().getBundle(), new Path(realmResourceName)));
				service = new HashLoginService("MyRealm", realmProps.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		//TODO: for LDAP create a custom LoginService
		} else {
			// Unknown Service
			try {
				service = (LoginService) Class.forName(serviceName).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return service;
	}

}
