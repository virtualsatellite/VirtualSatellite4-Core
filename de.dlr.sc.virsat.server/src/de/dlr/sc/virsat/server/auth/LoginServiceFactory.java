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

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.Status;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

/**
 * This class provides the current LoginService as defined in the ServerConfiguration
 * Depending on the created Class it will perform additional setup
 */
public class LoginServiceFactory {

	public static final String URI_SCHEME_JAR = "jar";
	
	/**
	 * @return a concrete LoginService defined in the ServerConfiguration or null
	 */
	public LoginService getLoginService() {
		LoginService service = null;
		
		String serviceClassName = ServerConfiguration.getLoginServiceClass();
		
		try {
			service = (LoginService) Class.forName(serviceClassName).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Couldn't create instance for " + ServerConfiguration.getLoginServiceClass(), e));
		}
		
		if (service instanceof HashLoginService) {
			var authPropertiesFileName = ServerConfiguration.getAuthPropertiesFileUri();
			URI authPropertiesFileUri = null;
		
			// In case the syntax is wrong, we can try if it is a file uri.
			// Before the server was migrated to jetty 12, it accepted 
			// pure system file path. This has changed with the latest jetty
			// which is now using the java NIO in the back. This allows
			// accessing various resources for the configuration, not
			// just files in the local file system. 
			// Accordingly for legacy reasons, we could have a configuration which
			// is still using a file path. Therefore we try to create the path
			// as if it is a local file.
			try (ResourceFactory.Closeable resourceFactory = ResourceFactory.closeable()) {
				Activator.getDefault().getLog().info("Setting up hashLoginService with: " + authPropertiesFileName);
				authPropertiesFileUri = new URI(authPropertiesFileName);
					
				Resource authPropertiesFileResource;
				Activator.getDefault().getLog().info("Auth properties uri points outside jar/plugin");
			    authPropertiesFileResource = resourceFactory.newResource(authPropertiesFileUri);
				
				((HashLoginService) service).setConfig(authPropertiesFileResource);
			} catch (URISyntaxException e) {
				Activator.getDefault().getLog().error("The Auth Properties File could not be set to the LoginService :" + authPropertiesFileName);
			}
			
		}
		
		return service;
	}
}
