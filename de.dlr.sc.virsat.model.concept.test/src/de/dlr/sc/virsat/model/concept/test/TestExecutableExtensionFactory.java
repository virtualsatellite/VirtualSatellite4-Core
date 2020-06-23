/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;

import com.google.inject.Injector;

import de.dlr.sc.virsat.model.concept.Activator;

public class TestExecutableExtensionFactory implements IExecutableExtensionFactory, IExecutableExtension {

	private String clazzName;
	
	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		clazzName = (String) data;
	}

	@Override
	public Object create() throws CoreException {
		try {
			Class<?> clazz = Activator.getDefault().getBundle().loadClass(clazzName);
			Injector injector = TestActivator.getInjector();
			Object result = injector.getInstance(clazz);
			return result;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
