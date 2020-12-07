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
	public static final String LEVEL_ATTRIBUTE_NAME = "level";
	
	/**
	 * Get most relevant label provider from the registry or use the default one
	 * @return the label provider
	 */
	public ITransitionLabelProvider getLabelProvider() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_TRANSITION_LABEL_PROVIDER);
		int highestHierarchyLevel = 0;
		for (IConfigurationElement element : configElements) {
			String levelString = element.getAttribute(LEVEL_ATTRIBUTE_NAME);
			try {
				highestHierarchyLevel = Integer.max(Integer.parseInt(levelString), highestHierarchyLevel);
			} catch (NumberFormatException e) {
				Activator.getDefault().getLog().error("Can not cast level attribute of extension to integer!", e);
			}
		}
		for (IConfigurationElement element : configElements) {
			int level = Integer.parseInt(element.getAttribute(LEVEL_ATTRIBUTE_NAME));
			if (level == highestHierarchyLevel) {
				try {
					return (ITransitionLabelProvider) element.createExecutableExtension("class");
				} catch (CoreException e) {
					Activator.getDefault().getLog().error("Could not get label provider", e);
				}
			}
		}
		return new TransitionLabelProvider();
	}

}
