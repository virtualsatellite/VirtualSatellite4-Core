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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * This class wraps a content provider for tree structures and given a tree selection
 * returns a linearization of the entire sub content of the selected tree.
 * @author muel_s8
 *
 */

public class VirSatFilteredListContentProvider extends AFilteredContentProvider implements IStructuredContentProvider {

	/**
	 * Default constructor
	 */
	
	public VirSatFilteredListContentProvider() {
		super();
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof EObject) {
			EObject eObject = (EObject) inputElement;
			List<Object> objects = new ArrayList<>();

			EcoreUtil.getAllContents(eObject, true).forEachRemaining((containedObject) -> {
				objects.add(containedObject);
			});
			objects.add(inputElement);
			
			Object[] elements = objects.toArray();
			Object[] filteredClasses = filterClasses(elements, filterElementClasses);
			Object[] filteredIds = filterIds(filteredClasses, filterElementCategoryIds, filterElementStructuralElementIds);
			
			return filteredIds;
		} else if (inputElement instanceof Resource) {
			List<Object> objects = new ArrayList<>();
			for (EObject content : ((Resource) inputElement).getContents()) {
				objects.addAll(Arrays.asList(getElements(content)));
			}
			return objects.toArray();
		}
		
		return Collections.EMPTY_LIST.toArray();
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	protected Object[] filterIds(Object[] objects, Set<String> categoryIdFilters, Set<String> structuralElementIdFilters) {
		Set<Object> filteredObjects = new HashSet<>();
		
		// In case there are no filters specified we return all objects
		if (categoryIdFilters.isEmpty() && structuralElementIdFilters.isEmpty()) {
			return objects;
		}

		// Now filter the
		for (Object object : objects) {
			if (object instanceof ATypeInstance) {
				ATypeInstance aTypeInstance = (ATypeInstance) object;
				
				if (aTypeInstance.getType() != null && !Collections.disjoint(categoryIdFilters, ActiveConceptHelper.getAllFullQualifiedIds(aTypeInstance.getType()))) {
					filteredObjects.add(object);
				}
			} else if (object instanceof StructuralElementInstance) {
				StructuralElementInstance sei = (StructuralElementInstance) object;
				
				if (sei.getType() != null && structuralElementIdFilters.contains(ActiveConceptHelper.getFullQualifiedId(sei.getType()))) {
					filteredObjects.add(object);
				}
			} else {
				// All other objects will not be touched by the filter
				filteredObjects.add(object);
			}
		}
		return filteredObjects.toArray();
	}

}
