/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.ui;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
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

	public static final String IMG_THUMBS_UP = "Image_VirSat_Thumps_Up";
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		setPluginId(getDefault().getBundle().getSymbolicName());
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);
		reg.put(IMG_INHERITANCE, getImageDescriptor("resources/icons/VirSat_Inheritance.png"));
		reg.put(IMG_NOT_INHERITED, getImageDescriptor("resources/icons/VirSat_Not_Inherited.png"));
		reg.put(IMG_THUMBS_UP, getImageDescriptor("resources/icons/VirSat_No_Problems.gif"));
	}

	public static final String IMG_INHERITANCE = "Image_VirSat_Inheritance";
	public static final String IMG_NOT_INHERITED = "Image_VirSat_Not_Inherited";
	
	/**
	 * Returns the shared instance
	 *
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
