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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.tree.BeanStructuralTreeTraverser;

/**
 * <pre>
 * A class to check elements against a custom tree level structure which are
 * specified by implementations of {@link IHierarchyLevel}s
 * 
 * An example of such a tree structure is a model that consists of a SYSTEM,
 * SUBSYTEM and EQUIPMENT levels These levels are implemented with the
 * {@link IHierarchyLevel} interface
 * 
 * System MySatellite
 *  |
 *  ----Subsystem AOCS
 *  |	|
 *  |	----Equipment ReactionWheel1
 *  |	----Equipment ReactionWheel2
 *  |
 *  ----NewElement    <====  This class can check what level this element might be on
 *  	|
 *  	----Equipment Thruster
 * 
 * </pre>
 * 
 * 
 */
public class HierarchyLevelChecker {

	protected List<IHierarchyLevel> levels;

	/**
	 * @param levels
	 *            A list that specifies the concrete order of tree levels.
	 *            Levels should not overlap - any element should belong to no more than one level.
	 */
	public HierarchyLevelChecker(List<IHierarchyLevel> levels) {
		this.levels = levels;
	}

	/**
	 * Checks which levels an element can be on
	 * 
	 * @param bean
	 *            the structural element bean to check
	 * @return a set of applicable levels from the list of levels passed in the
	 *         constructor
	 * @throws IllegalArgumentException if an element with multiple levels is encountered in the structural tree
	 */
	public Set<IHierarchyLevel> getApplicableLevels(IBeanStructuralElementInstance bean) throws IllegalArgumentException {

		// Check if element belongs to level already - then it can by definition of this
		// checker only be on this level
		IHierarchyLevel currentLevel = getLevelOfBean(bean);
		if (currentLevel != null) {
			return Collections.singleton(currentLevel);
		}

		return deduceApplicableLevels(bean);
	}

	/**
	 * Deduces applicable levels based on the levels already present in the tree
	 * @param bean
	 *            the structural element bean to check
	 * @return set of applicable levels
	 */
	protected Set<IHierarchyLevel> deduceApplicableLevels(IBeanStructuralElementInstance bean) {
		Set<IHierarchyLevel> applicableLevels = new HashSet<>();
		int minLevelIndex = 0;
		int maxLevelIndex = levels.size() - 1;
		
		// Get first parent with level
		IBeanStructuralElementInstance parent = getFirstParentWithLevel(bean);
		// Get children with level
		List<IBeanStructuralElementInstance> children = getChildrenWithLevel(bean);

		// Check maximum and minimum level index
		if (parent != null) {
			int minLevelFromParent = getMinIndexFromParentLevel(parent);
			int maxLevelFromParent = getMaxIndexFromTreeDistanceOfParent(parent, bean);

			minLevelIndex = Math.max(minLevelIndex, minLevelFromParent);
			maxLevelIndex = Math.min(maxLevelIndex, maxLevelFromParent);
		}
		for (IBeanStructuralElementInstance child : children) {
			int maxLevelFromChild = getMaxIndexFromRelevantChild(child);
			int minLevelFromChild = getMinIndexFromTreeDistanceOfChild(child, bean);

			minLevelIndex = Math.max(minLevelIndex, minLevelFromChild);
			maxLevelIndex = Math.min(maxLevelIndex, maxLevelFromChild);
		}
		
		if (parent == null && children.isEmpty()) {
			maxLevelIndex = getMaxIndexFromTreeDistanceToRoot(bean);
		}

		if (minLevelIndex <= maxLevelIndex) {
			// Add applicable levels to set
			applicableLevels.addAll(levels.subList(minLevelIndex, maxLevelIndex + 1));
		}
		
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
	public boolean checkApplicable(IBeanStructuralElementInstance bean, IHierarchyLevel level) {
		return getApplicableLevels(bean).contains(level);
	}

	/**
	 * @param bean
	 *            the structural element bean to check
	 * @return true if an element is on a level that would not be applicable for it, false otherwise
	 */
	public boolean beanHasInapplicableLevel(IBeanStructuralElementInstance bean) {
		IHierarchyLevel level = getLevelOfBean(bean);
		return level != null && !deduceApplicableLevels(bean).contains(level);
	}

	/**
	 * @param bean
	 *            the structural element bean to check
	 * @return true if element belongs to more than one level, false otherwise
	 */
	public boolean beanHasAmbiguousLevel(IBeanStructuralElementInstance bean) {
		return getLevelsOfBean(bean).size() > 1;
	}
	
	/**
	 * Get the minimum level index from the next parent element with level
	 * 
	 * @param parent
	 *            the next parent with level
	 * @return the minumum level index
	 */
	private int getMinIndexFromParentLevel(IBeanStructuralElementInstance parent) {
		IHierarchyLevel parentLevel = getLevelOfBean(parent);

		int minLevelIndex = levels.indexOf(parentLevel);

		// If the level cannot be nested (->repeated) then the element needs to be of
		// the next level
		if (!parentLevel.canBeNested()) {
			minLevelIndex += 1;
		}

		return minLevelIndex;
	}

	/**
	 * Get the minimum level index from the next parent element with level
	 * 
	 * @param child
	 *            the next child with level
	 * @return the minumum level index
	 */
	private int getMaxIndexFromRelevantChild(IBeanStructuralElementInstance child) {
		IHierarchyLevel childLevel = getLevelOfBean(child);

		int maxLevelIndex = levels.indexOf(childLevel);

		// If the level cannot be nested (->repeated) then the element needs to be of
		// the previous level from the order
		if (!childLevel.canBeNested()) {
			maxLevelIndex -= 1;
		}

		return maxLevelIndex;
	}

	/**
	 * Get the minimum index considering the tree distance of the next relvant child
	 * with level
	 * 
	 * @param child
	 *            the next relevant child with level - the next relevant child is
	 *            the one with the least value of the difference from the level
	 *            index and the tree distance
	 * @param elementToCheck
	 *            the element to check
	 * @return the minimum level index
	 */
	private int getMinIndexFromTreeDistanceOfChild(IBeanStructuralElementInstance child,
			IBeanStructuralElementInstance elementToCheck) {
		IHierarchyLevel childLevel = getLevelOfBean(child);
		int treeDistance = getTreeDistance(child, elementToCheck);
		int minLevel = levels.indexOf(childLevel) - treeDistance;

		// Go through all the levels between the child level and the one with a
		// distance of the tree depth and check if their optional. If so, then the
		// minimum applicable level index decreases as we can also skip the level
		int minLevelFromDistance = minLevel;
		for (int i = levels.indexOf(childLevel) - 1; i >= 0 && i >= minLevelFromDistance; i--) {
			if (levels.get(i).isOptional()) {
				minLevel--;
			}
		}

		return minLevel;
	}

	/**
	 * Get the maximum level index considering the tree distance to the next parent
	 * element with level
	 * 
	 * @param parent
	 *            the next parent element with level
	 * @param elementToCheck
	 *            the element to check
	 * @return the maximum level index
	 */
	private int getMaxIndexFromTreeDistanceOfParent(IBeanStructuralElementInstance parent,
			IBeanStructuralElementInstance elementToCheck) {
		IHierarchyLevel parentLevel = getLevelOfBean(parent);
		int treeDistance = getTreeDistance(elementToCheck, parent);
		int maxLevel = levels.indexOf(parentLevel) + treeDistance;

		// Go through all the levels between the parent level and the one with a
		// distance of the tree depth and check if their optional. If so, then the
		// maximum applicable level index increases as we can also skip the level
		int maxLevelFromDistance = maxLevel;
		for (int i = levels.indexOf(parentLevel) + 1; i <= maxLevelFromDistance && i < levels.size(); i++) {
			if (levels.get(i).isOptional()) {
				maxLevel++;
			}
		}

		return maxLevel;
	}
	
	/**
	 * Get the maximum level index considering the tree distance to the root element
	 * 
	 * @param bean the element to check
	 * @return the maximum level index
	 */
	private int getMaxIndexFromTreeDistanceToRoot(IBeanStructuralElementInstance bean) {
		return getTreeDistanceToRootBean(bean);
	}

	/**
	 * Get the level an arbitrary bean is on
	 * 
	 * @param bean
	 *            the bean to get level from
	 * @return the level
	 * @throws IllegalArgumentException if a model element belongs to mulitple levels
	 */
	private IHierarchyLevel getLevelOfBean(IBeanStructuralElementInstance bean) throws IllegalArgumentException {
		List<IHierarchyLevel> foundLevels = getLevelsOfBean(bean);
		if (foundLevels.size() > 1) {
			throw new IllegalArgumentException("Element " + bean + " is on multiple levels: " + foundLevels);
		}
		if (foundLevels.size() == 1) {
			return foundLevels.get(0);
		}
		return null;
	}

	/**
	 * Get all levels a bean is on
	 * @param bean 
	 * @return list of levels
	 */
	private List<IHierarchyLevel> getLevelsOfBean(IBeanStructuralElementInstance bean) {
		return levels.stream()
				.filter(level -> level.isOnLevel(bean))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get the first parent with level or null if not existing
	 * 
	 * @param bean
	 *            the SEIBean
	 * @return the next parent with level
	 */
	private IBeanStructuralElementInstance getFirstParentWithLevel(IBeanStructuralElementInstance bean) {
		IBeanStructuralElementInstance parent = bean.getParent();

		if (parent != null) {
			IHierarchyLevel level = getLevelOfBean(parent);
			if (level != null) {
				return parent;
			} else {
				return getFirstParentWithLevel(parent);
			}
		} else {
			return null;
		}
	}

	/**
	 * Get the first parent with level or null if not existing
	 * 
	 * @param bean
	 *            the SEIBean
	 * @return the next parent with level
	 */
	private List<IBeanStructuralElementInstance> getChildrenWithLevel(IBeanStructuralElementInstance bean) {
		HasLevelTreeTraverserMatcher matcher = new HasLevelTreeTraverserMatcher(levels);
		new BeanStructuralTreeTraverser().traverse(bean, matcher);
		List<IBeanStructuralElementInstance> children = matcher.getElementsWithLevel();
		children.remove(bean);
		return children;
	}

	/**
	 * Get the tree distance from one tree element to another
	 * 
	 * @param startElement
	 *            the element to start from, has to be the one with a higher depth
	 * @param target
	 *            the target element, has to be the higher level element
	 * @return the distance of tree depth levels as int
	 */
	private int getTreeDistance(IBeanStructuralElementInstance startElement, IBeanStructuralElementInstance target) {
		return getTreeDistance(startElement, target, 0);
	}

	/**
	 * Get the tree distance from one tree element to anther
	 * 
	 * @param startElement
	 *            the element to start from, has to be the one with a higher depth
	 * @param target
	 *            the target element, has to be the higher level element
	 * @param startDistance
	 *            the current distance
	 * @return the distance of tree depth levels as int
	 */
	private int getTreeDistance(IBeanStructuralElementInstance startElement, IBeanStructuralElementInstance target,
			int startDistance) {
		IBeanStructuralElementInstance parent = startElement.getParent();
		if (parent == null) {
			return -1;
		}
		int currentDistance = startDistance + 1;
		if (parent.equals(target)) {
			return currentDistance;
		} else {
			return getTreeDistance(parent, target, currentDistance);
		}
	}
	
	/**
	 * Get the tree distance of the bean to its tree root
	 * 
	 * @param bean the bean to search the distance for
	 * @return the distance
	 */
	private int getTreeDistanceToRootBean(IBeanStructuralElementInstance bean) {
		return getTreeDistanceToRootBean(bean, 0);
	}
	
	/**
	 * Get the tree distance of the bean to its tree root
	 * 
	 * @param bean the bean to search the distance for
	 * @param startDistance the current distance
	 * @return the distance
	 */
	private int getTreeDistanceToRootBean(IBeanStructuralElementInstance bean, int startDistance) {
		if (bean.getParent() != null) {
			return getTreeDistanceToRootBean(bean.getParent(), ++startDistance);
		} else {
			return startDistance;
		}
	}

}
