/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.structural.level;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;

/**
 * Interface for a level as basis to specify custom tree hierarchies
 * 
 */
public interface IHierarchyLevel {

	/**
	 * Method that checks if element is on the level
	 * 
	 * @param bean
	 *            the structural element instance bean under question
	 * @return returns true if a structural element instance bean belongs to this
	 *         level
	 */
	boolean isOnLevel(IBeanStructuralElementInstance bean);

	/**
	 * Specify if the level can be nested. Nested means that an element of that
	 * level can have children that are also part of this level
	 * 
	 * @return returns true if a the level can be nested
	 */
	boolean canBeNested();
	
	/**
	 * Specify if the level can be skipped
	 * @return true if it can be skipped, false otherwise
	 */
	boolean isOptional();

}
