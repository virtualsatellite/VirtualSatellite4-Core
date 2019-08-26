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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;

/**
 * 
 * A class to check elements against a custom tree level structure which are
 * specified by implementations of {@link ILevel}s
 * 
 * An example of such a tree structure is a model that consists of a SYSTEM,
 * SUBSYTEM and EQUIPMENT levels These levels are implemented with the
 * {@link ILevel} interface
 * 
 * <pre>
 * 
 * System MySatellite
 *  |
 *  ----Subsystem AOCS
 *  |	|
 *  |	----Equipment ReactionWheel1
 *  |	----Equipment ReactionWheel2
 *  |
 *  ----NewElement    <-----  This class can check what level this element might be on
 * 
 * </pre>
 * 
 * 
 */
public class LevelChecker {

	protected List<ILevel> levels;

	/**
	 * @param levels
	 *            A list that specifies the concrete order of tree levels.
	 * 
	 *            Levels should not overlap - for any element only one level can
	 *            have true as result from the
	 *            {@link ILevel#isOnLevel(IBeanStructuralElementInstance)} method
	 */
	public LevelChecker(List<ILevel> levels) {
		this.levels = levels;
	}

	/**
	 * Checks which levels an element can be on
	 * 
	 * @param bean
	 *            the structural element bean to check
	 * @return a set of applicable levels from the list of levels passed in the
	 *         constructor
	 */
	public Set<ILevel> getApplicableLevels(IBeanStructuralElementInstance bean) {
		Set<ILevel> applicableLevels = new HashSet<>();
		return applicableLevels;
	}

	/**
	 * 
	 * @param bean
	 *            the structural element bean to check
	 * @param level
	 *            level from the list of levels passed in the constructor
	 * @return true if the the element can be on this level, false otherwise
	 */
	public boolean checkApplicable(IBeanStructuralElementInstance bean, ILevel level) {
		return getApplicableLevels(bean).contains(level);
	}

}
