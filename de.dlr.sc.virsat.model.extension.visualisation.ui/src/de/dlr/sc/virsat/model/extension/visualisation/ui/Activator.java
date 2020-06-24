/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui;

import java.net.URL;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import de.dlr.sc.virsat.model.extension.visualisation.treemanager.StartManagers;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.CommunicationServer;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.GeometryFileServer;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.SceneGraphServer;
import de.dlr.sc.virsat.model.extension.visualisation.ui.vtkClient.VtkClientVisUpdateHandler;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.dlr.sc.virsat.model.extension.visualisation.ui"; //$NON-NLS-1$
	// The plug-in ID
	private static String pluginId;
	
	// The shared instance
	private static Activator plugin;
	
	private CommunicationServer sceneGraphServer;
	private CommunicationServer geometryFileServer;
	private ResourceReloadListener resourceReloadListener;

	private boolean serverStarted = false;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * Implementation of a resource listener to detect file changes after svn updates
	 * for example. the listener is then calling the Visualization server to do an complete update
	 * @author fisc_ph
	 *
	 */
	public static class ResourceReloadListener implements VirSatTransactionalEditingDomain.IResourceEventListener {
		@Override
		public void resourceEvent(Set<Resource> resources, int event) {
			if (event == VirSatTransactionalEditingDomain.EVENT_RELOAD) {
				StartManagers.restartVis();
			}
		}
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		plugin = this;
		pluginId = getDefault().getBundle().getSymbolicName();
		// initialize the communication servers
		try {
		
			sceneGraphServer = new SceneGraphServer(StartManagers.getTreeManager(), VtkClientVisUpdateHandler.getInstance());
			geometryFileServer = new GeometryFileServer(StartManagers.getTreeManager(), VtkClientVisUpdateHandler.getInstance());
			
			resourceReloadListener = new ResourceReloadListener();
			VirSatTransactionalEditingDomain.addResourceEventListener(resourceReloadListener);
			
			serverStarted = true;
		} catch (Exception | UnsatisfiedLinkError | NoClassDefFoundError e) {
			getLog().log(new Status(Status.WARNING, pluginId, "Failed to start SceneGraphServer. Probably due to missing VTK and ZeroMQ libraries. Error: " + e.getMessage()));
		} 
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (serverStarted) {
			sceneGraphServer.close();
			geometryFileServer.close();
			VirSatTransactionalEditingDomain.removeResourceEventListener(resourceReloadListener);
		}
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
        Bundle bundle = Platform.getBundle(PLUGIN_ID);
        URL fileURL = bundle.getEntry(path);
        return fileURL;
	}
	/**
	 * Returns the plugin id
	 *
	 * @return pluginId
	 */
	public static String getPluginId() {
		return pluginId;
	}
}
