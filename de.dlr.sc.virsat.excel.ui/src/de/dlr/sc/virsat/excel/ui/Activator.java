/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.ui;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	private static String pluginId;

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		pluginId = getBundle().getSymbolicName();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Allows to access an URL stored in the plugin
	 * @param path the path to the resource to be accessed
	 * @return path to the plugins resource as URL
	 */
	public static URL getFileFromPlugin(String path) {
        Bundle bundle = Platform.getBundle(pluginId);
        URL fileURL = bundle.getEntry(path);
        return fileURL;
	}

	/**
	 * Method that directly hands back an image descriptor from the current Bundle resources
	 * @param path The path to the resource
	 * @return the according ImageDescriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return Activator.imageDescriptorFromPlugin(pluginId, path);
	}
	
	/**
	 * Returns the plugin id
	 * @return pluginId
	 */
	public static String getPluginId() {
		return pluginId;
	}
}
