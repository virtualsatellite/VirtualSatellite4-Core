/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.branding.ui;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements IStartup {

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
		final boolean switchPerpective = processPluginUpgrading();
	    
		if (switchPerpective) {
	        final IWorkbench workbench = PlatformUI.getWorkbench();
	        new UIJob("Switching to default perspectives") {
	            @Override
	            public IStatus runInUIThread(IProgressMonitor monitor) {
	                try {
	                    workbench.showPerspective("de.dlr.sc.virsat.branding.ui.default.perspective", workbench.getActiveWorkbenchWindow());
	                } catch (WorkbenchException e) {
	                    return new Status(IStatus.ERROR, pluginId, "Error while switching perspectives", e);
	                }
	                return Status.OK_STATUS;
	            }
	        }.run(new NullProgressMonitor());
		}
	}

	/**
	 * method to check if the plugin needs upgrading
	 * @return Boolean a boolean flag indicating if the update was successful
	 */
	private Boolean processPluginUpgrading() {
	    final Version version = getDefault().getBundle().getVersion();
	    final IPreferenceStore preferenceStore = getDefault().getPreferenceStore();
	    final String preferenceName = "lastVersionActivated";
	    final String lastVersionActivated = preferenceStore.getString(preferenceName);
	    final boolean upgraded = "".equals(lastVersionActivated) || (version.compareTo(new Version(lastVersionActivated)) > 0);
	    preferenceStore.setValue(preferenceName, version.toString());
	    return upgraded;
	}
	
	@Override
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
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(pluginId, path);
	}

	@Override
	public void earlyStartup() {
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
