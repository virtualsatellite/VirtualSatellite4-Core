/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.contentProvider;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class for filtering content.
 */

public abstract class AFilteredContentProvider {

	protected Set<String> filterElementCategoryIds;
	protected Set<String> filterElementStructuralElementIds;

	/**
	 * Filters the objects for the desired ids
	 * @param objects the objects to be filtered
	 * @param categoryIdFilters the ids by which we filter categories and properties by
	 * @param structuralElementIdFilters the ids by which we filter structural elements by
	 * @return the objects after filtering them by id
	 */
	
	protected abstract Object[] filterIds(Object[] objects, Set<String> categoryIdFilters, Set<String> structuralElementIdFilters);

	protected Set<Class<?>> filterElementClasses;

	/**
	 * Default constructor
	 */
	
	public AFilteredContentProvider() {
		filterElementClasses = new HashSet<>();
		filterElementCategoryIds = new HashSet<>();
		filterElementStructuralElementIds = new HashSet<>();
	}

	/**
	 * This method adds a class filter to the GetElement Method
	 * @param filterClass The class that should explicitly be returned by the getElement method
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public AFilteredContentProvider addClassFilterToGetElement(Class<?> filterClass) {
		filterElementClasses.add(filterClass);
		return (this);
	}

	/**
	 * Use this method to add an ID for a specific type id of the Category and Property Concept
	 * @param filterId the ID of the object that should be returned by the GetElement method
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public AFilteredContentProvider addCategoryIdFilterToGetElement(String filterId) {
		filterElementCategoryIds.add(filterId);
		return (this);
	}
	
	/**
	 * Use this method to add an ID for a specific type id of the Structural Element Concept
	 * @param filterId the ID of the object that should be returned by the GetElement method
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public AFilteredContentProvider addStructuralElementIdFilterToGetElement(String filterId) {
		filterElementStructuralElementIds.add(filterId);
		return (this);
	}

	/**
	 * Use this method to filter objects for a list of given classes.
	 * Only the specified filtered classes will be returned
	 * @param objects Array of input objects
	 * @param classFilters A set of classes that should be accepted. all objects will be returned if list is empty 
	 * @return the objects that passed the filter (that have been selected by the filter)
	 */
	protected Object[] filterClasses(Object[] objects, Set<Class<?>> classFilters) {
		Set<Object> filteredObjects = new HashSet<>();
		// In case there are no filters specified we return all objects
		if (classFilters.isEmpty()) {
			return objects;
		}
		
		// Now filter the
		for (Object object : objects) {
			for (Class<?> clazz : classFilters) {
				if (clazz.isAssignableFrom(object.getClass())) {
					filteredObjects.add(object);
					// Object is accepted therefore go to the next
					// evaluate the next object
					break;
				}
			}
		}
		return filteredObjects.toArray();
	}

}