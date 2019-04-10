/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.plugin;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * the activator is the entry point for this plugin
 * @author scha_vo
 *
 */
public class Activator implements BundleActivator {

	private static BundleContext context;

	/**
	 * getter method to retrieve the context
	 * @return {@link BundleContext} the context of the bundle in the OSGi Framework bundle context  
	 */
	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
