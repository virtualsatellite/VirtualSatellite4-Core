/*******************************************************************************
 * Copyright (c) 2008-2022 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.classloader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * This class loader checks all bundles which are currently 
 * loaded in the bundle if it may hold the desired class.
 * @author fisc_ph
 *
 */
public class BundleDependencyClassLoader extends ClassLoader {

	Set<Bundle> bundles = new HashSet<>();
	
	/**
	 * Constructor for the Classloader
	 * @param bundleClass a class from the bundle to identify the current class loader
	 */
	public BundleDependencyClassLoader(Class<?> bundleClass) {
		super();
		
		Bundle bundle = FrameworkUtil.getBundle(bundleClass);
		
		bundles.add(bundle);
		bundles.addAll(Arrays.asList(bundle.getBundleContext().getBundles()));
	}
	
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		for (Bundle bundle : bundles) {
			try {
				Class<?> clazz = bundle.loadClass(name);
				if (clazz != null) {
					return clazz;
				}
			} catch (ClassNotFoundException e) {
				// Nothing special needs to be done
			}
		}
		
		return super.loadClass(name);
	}
}
