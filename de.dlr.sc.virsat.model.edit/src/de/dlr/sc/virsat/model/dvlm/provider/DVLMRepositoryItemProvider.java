/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * specified DVLM RepositoryItemProvider
 * overrides the getChildren method filtering them for containment in a resource   
 * @author lobe_el
 *
 */
public class DVLMRepositoryItemProvider extends RepositoryItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory to be used for initialization
	 */
	public DVLMRepositoryItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection<?> getChildren(Object object) {
		Collection<?> objects = super.getChildren(object);
		List<Object> filteredObjects = new ArrayList<Object>();
		for (Object obj : objects) {
			if (obj instanceof EObject) {
				EObject eObj = (EObject) obj;
				boolean containedInResource = eObj.eResource() != null;
				if (containedInResource) {
					filteredObjects.add(eObj);
				}
			}
		}
		return Collections.unmodifiableCollection(filteredObjects);
	}
	
}
