/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.transitions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import de.dlr.sc.virsat.model.extension.statemachines.ui.Activator;

/**
 * Returns the relevant label provider for transitions
 *
 */
public class LabelProviderInstantiator {
	
	public static final String EXTENSION_POINT_ID_TRANSITION_LABEL_PROVIDER = "de.dlr.sc.virsat.model.extension.statemachines.transition.LabelProvider";
	
	/**
	 * Get most relevant label provider from the registry or use the default one
	 * @return the label provider
	 */
	public ITransitionLabelProvider getLabelProvider() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_TRANSITION_LABEL_PROVIDER);
		if (configElements.length > 0) {
			// Get the last registered configuration element
			IConfigurationElement extension = configElements[configElements.length - 1];
			try {
				return (ITransitionLabelProvider) extension.createExecutableExtension("class");
			} catch (CoreException e) {
				Activator.getDefault().getLog().error("Could not get label provider", e);
			}
		}
		return new TransitionLabelProvider();
	}

}
