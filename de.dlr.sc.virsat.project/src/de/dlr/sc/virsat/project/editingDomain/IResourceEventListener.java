/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;

import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Interface Definition for resource listeners
 *
 */
public interface IResourceEventListener {
	
	int EVENT_LOAD = 0;
	int EVENT_CHANGED = 1;
	int EVENT_UNLOAD = 2;
	int EVENT_RELOAD = 3;
	
	/**
	 * a method to create a resource event
	 * @param resources the resources on which the event should be generated
	 * @param event the code of the event to be generated
	 */
	void resourceEvent(Set<Resource> resources, int event);
};
