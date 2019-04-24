/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.provider;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class implements a content provider for tables that display the content given by
 * an extension point from the plugin/platform
 * 
 * @author fisc_ph
 *
 */
public class ExtensionPointContentProvider implements IStructuredContentProvider {

	private String extensionPoint;
	
	/**
	 * Constuctor with the ID of the Extension point to be displayed by this provider
	 * @param extensionPoint the ID of the extension point
	 */
	public ExtensionPointContentProvider(String extensionPoint) {
		this.extensionPoint = extensionPoint;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		IExtensionRegistry registry;
		if (inputElement instanceof IExtensionRegistry) {
			registry = (IExtensionRegistry) inputElement;
		} else {
			registry = Platform.getExtensionRegistry();
		}
		IConfigurationElement[] configSections = registry.getConfigurationElementsFor(extensionPoint);
		return configSections;
	}

	@Override
	public void dispose() {
		// nothing to be done here
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// nothing to be done here
	}
}
