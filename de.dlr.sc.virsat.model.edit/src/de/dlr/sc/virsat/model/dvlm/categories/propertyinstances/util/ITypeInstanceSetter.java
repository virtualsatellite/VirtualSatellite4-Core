/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util;

import java.util.List;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;

/**
 * A Type instance setter defines the value of a type instance and makes it non-editable
 * by a set command.
 * @author muel_s8
 *
 */

public interface ITypeInstanceSetter {
	/**
	 * This method gets all type instances affected by this set.
	 * @param instance the instance upon which this set execution is checked against
	 * @return the affected type instances.
	 */
	List<ATypeInstance> getAffectedTypeInstances(ATypeInstance instance);
	
	/**
	 * Checks if this affector is applicable for the passed instance
	 * @param instance The instance we wish to use this setter on
	 * @return Whether or not the setter is applicable for the passed instance
	 */
	boolean isApplicableFor(ATypeInstance instance);
}
