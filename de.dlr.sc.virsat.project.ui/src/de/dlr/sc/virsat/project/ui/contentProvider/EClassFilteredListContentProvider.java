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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IStructuredContentProvider;

public class EClassFilteredListContentProvider extends VirSatFilteredListContentProvider implements IStructuredContentProvider {
	
	private Set<EClass> supportedEClasses = new HashSet<EClass>();
	
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof EObject) {
			List<Object> objects = new ArrayList<Object>();
			objects.addAll(Arrays.asList(super.getElements(inputElement)));
			objects.add(inputElement);
			return eClassFilteredList(objects);
		} else if (inputElement instanceof Resource) {
			List<Object> objects = new ArrayList<>();
			for (EObject content : ((Resource) inputElement).getContents()) {
				objects.addAll(Arrays.asList(super.getElements(content)));
			}
			return eClassFilteredList(objects);
		}
		
		return Collections.EMPTY_LIST.toArray();
	}

	public void addSupportedEClass(EClass eClass) {
		supportedEClasses.add(eClass);
		supportedEClasses.addAll(eClass.getEAllSuperTypes());
	}
	
	/**
	 * Filter elements for supported EClasses
	 * @param objects the list of objects to filter
	 * @return the filtered set as array
	 */
	protected Object[] eClassFilteredList(List<Object> objects) {
		Set<Object> filteredObjects = new HashSet<Object>();
		for (Object object : objects) {
			if (object instanceof EObject) {
				if (supportedEClasses.contains(((EObject) object).eClass())) {
					filteredObjects.add(object);
				}
			} else {
				filteredObjects.add(object);
			}
		}
		return filteredObjects.toArray();
	}
}
