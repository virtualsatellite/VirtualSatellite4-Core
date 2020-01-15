/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui;

import java.net.URL;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.css.swt.theme.IThemeManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("restriction") //Discouraged access to theme control - API may change in the future
public class Activator extends AbstractUIPlugin {

	private static final String SUPER_USER_THEME_ID = "de.dlr.sc.virsat.branding.ui.themesuperuser";

	// The plug-in ID
	private static String pluginId;

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * Bundle context start mehtod
	 * @param context the context for starting the bundle
	 * @throws Exception in case things go wrong
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		setPluginId(getDefault().getBundle().getSymbolicName());
		if (UserRegistry.getInstance().isSuperUser()) {
			setSuperUserTheme();
		}
	}

	/**
	 * Changes Eclipse theme to SuperUser theme
	 */
	private void setSuperUserTheme() {
        new UIJob("Setting SuperUser theme") {
            @Override
            public IStatus runInUIThread(IProgressMonitor monitor) {
        		Bundle bundle = FrameworkUtil.getBundle(getClass());
        		BundleContext context = bundle.getBundleContext();
        		ServiceReference<?> ref = context.getServiceReference(IThemeManager.class.getName());
        		IThemeManager manager = (IThemeManager) context.getService(ref);
        		Display display = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getDisplay();
        		IThemeEngine engine = manager.getEngineForDisplay(display);
        		engine.setTheme(SUPER_USER_THEME_ID, false);
                return Status.OK_STATUS;
            }
        }.run(new NullProgressMonitor());
	}

	/**
	 * Bundle context stop mehtod
	 * 
	 * @param context the context for stopping the bundle
	 * @throws Exception in case things go wrong
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
