/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


/**
 * Equinox/OSGI Activator class of plugin
 * @author fisc_ph
 *
 */
public class Activator extends Plugin {

	private static String pluginId;
	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * Constructor for Equinox OSGI Activator
	 * @param context osgi/equinox context
	 * @throws Exception throws
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		setPluginId(getDefault().getBundle().getSymbolicName());
	}

	/**
	 * Method to retrieve a resource from plugin embedded resources
	 * @param path the resource path in the plugin starting with "resource/"
	 * @return an input stream to the resource content
	 * @throws IOException throws
	 */
	public InputStream getPluginResource(String path) throws IOException {
		URL url;
		url = new URL("platform:/plugin/" + pluginId + "/" + path);
		InputStream inputStream = url.openConnection().getInputStream();
		return inputStream;
	}
	
	/**
	 * Method to shutdown Equinox OSGI Plugin 
	 * @param context osgi/equinox context
	 * @throws Exception throws
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	/**
	 * Returns the plugin id
	 *
	 * @return pluginId
	 */
	public static String getPluginId() {
		return pluginId;
	}
	/**
	 * Sets the plugin id 
	 *
	 * @param pluginId the plugin id
	 */
	private static void setPluginId(String pluginId) {
		Activator.pluginId = pluginId;
	}
}
