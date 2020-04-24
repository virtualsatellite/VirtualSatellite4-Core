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

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Status;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

/**
 * This class provides the current LoginService as defined in the ServerConfiguration
 * Depending on the created Class it will perform additional setup
 */
public class LoginServiceFactory {

	/**
	 * @return a concrete LoginService defined in the ServerConfiguration or null
	 * @throws IOException 
	 */
	public LoginService getLoginService() {
		LoginService service = null;
		
		String serviceClassName = ServerConfiguration.getLoginServiceClass();
		
		try {
			service = (LoginService) Class.forName(serviceClassName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Couldn't create instance for " + ServerConfiguration.getLoginServiceClass(), e));
		}
		
		if (service instanceof HashLoginService) {
			
			String filePath = null;
			
			// Check if the file exists, else try to resolve it in the bundle
			if (new File(ServerConfiguration.getAuthPropertiesFile()).exists()) {
				filePath = ServerConfiguration.getAuthPropertiesFile();
			} else {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.WARNING, "No valid auth.propierties.file provided, trying to resolve it in the bundle", null));
				filePath = getResolvedFile(ServerConfiguration.getAuthPropertiesFile());
			}
			
			((HashLoginService) service).setConfig(filePath);
		}
		
		return service;
	}
	
	/**
	 * Try to resolve the file path in the bundle
	 * @return the file path in the bundle or null
	 */
	private String getResolvedFile(String path) {
		try {
			return Activator.getDefault().resolveBundlePath(path);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Could not resolve auth.propierties.file ", e));
		}
		return null;
	}

}
