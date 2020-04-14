/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


/**
 * the activator is the entry point for this plugin
 */
public class Activator extends Plugin {

	private static String pluginId;
	private static Activator plugin;
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		Activator.pluginId = getDefault().getBundle().getSymbolicName();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return plugin;
	}

	public static String getPluginId() {
		return pluginId;
	}
}
