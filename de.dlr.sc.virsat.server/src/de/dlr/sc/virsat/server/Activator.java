/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * Activator for the Server Plugin
 *
 */
public class Activator extends Plugin {

	// The plug in ID
	private static String pluginId;
	// The shared instance
	private static Activator plugin;
	
	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		pluginId = context.getBundle().getSymbolicName();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
	}

	public static Activator getDefault() {
		return plugin;
	}

	public static String getPluginId() {
		return pluginId;
	}
}
