/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import java.util.ArrayList;
import java.util.List;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;

/**
 * Representation of a model CA that contains only a list of the property instances uuids
 * It doesn't override the unflatten function because that list is read only,
 * this is because we don't add or delete the properties of the ca on this low level
 */
public class FlattenedCategoryAssignment extends AFlattenedCategoryAssignment {

	// API read
	private List<String> propertyUuids;
	
	public FlattenedCategoryAssignment(CategoryAssignment ca) {
		super(ca);
		
		setPropertyUuids(resolveProperties(ca.getPropertyInstances()));
	}

	/**
	 * Resolves List<APropertyInstance> to a representative List<String>
	 * @param properties of the ca
	 * @return List<String>
	 */
	private List<String> resolveProperties(List<APropertyInstance> properties) {
		List<String> propertyUuids = new ArrayList<String>();
		
		for (APropertyInstance property : properties) {
			propertyUuids.add(property.getUuid().toString());
		}
		
		return propertyUuids;
	}
	
	public List<String> getPropertyUuids() {
		return propertyUuids;
	}

	public void setPropertyUuids(List<String> propertyUuids) {
		this.propertyUuids = propertyUuids;
	}
}
