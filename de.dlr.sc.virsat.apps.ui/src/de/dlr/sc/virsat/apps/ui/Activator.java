/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.ui;

import java.net.URL;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements IDebugEventSetListener {

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
		setPluginId(getDefault().getBundle().getSymbolicName());
		DebugPlugin.getDefault().addDebugEventListener(this);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		DebugPlugin.getDefault().removeDebugEventListener(this);
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

	@Override
	public void handleDebugEvents(DebugEvent[] events) {
		// Loop over all Debug events
		for (DebugEvent event : events) {
			// Check if an execution of a java program just stopped
			// may need some adjustments in the future to just react to real APP launches
			// but maybe some advanced C++ code generation together with VirSat wants some updates as well
			// Use Case is not a 100% clear yet. Thus we just update once things have been executed and terminated
			// Here we do listen to RUN and DEBUG
			if (event.getKind() == DebugEvent.TERMINATE) {
				IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
					@Override
					public void run(IProgressMonitor monitor) throws CoreException {
						Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.pluginId, "Try to Update Workspace due to terminated Application"));
						ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, monitor);
					}
				};
				
				try {
					ResourcesPlugin.getWorkspace().run(runnable, null);
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.pluginId, "Cannot refresh workspace", e));
				}
			}
		}
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
