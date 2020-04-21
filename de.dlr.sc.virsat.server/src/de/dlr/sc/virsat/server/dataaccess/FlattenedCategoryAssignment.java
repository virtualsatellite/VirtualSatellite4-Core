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

public class FlattenedCategoryAssignment {

	private String name;
	private String fullQualifiedInstanceName;
	private String type;
	private String uuid;
	private List<String> properties;
	
	public FlattenedCategoryAssignment() { }
	
	/**
	 * Create a FlattenedCategoryAssignment from the ca
	 * @param ca CategoryAssignment
	 */
	public FlattenedCategoryAssignment(CategoryAssignment ca) {
		// TODO: which Properties (and lists) do we want do read?
		setFullQualifiedInstanceName(ca.getFullQualifiedInstanceName());
		setName(ca.getName());
		setType(ca.getType().getFullQualifiedName());
		setUuid(ca.getUuid().toString());
		setProperties(resolveProperties(ca.getPropertyInstances()));
	}

	// TODO: return a complete property json string instead
	/**
	 * Resolves List<APropertyInstance> to a representative List<String>
	 * @param properties of the ca
	 * @return List<String>
	 */
	private List<String> resolveProperties(List<APropertyInstance> properties) {
		List<String> propertieStrings = new ArrayList<String>();
		
		for (APropertyInstance propertie : properties) {
			propertieStrings.add(propertie.getFullQualifiedInstanceName());
		}
		
		return propertieStrings;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullQualifiedInstanceName() {
		return fullQualifiedInstanceName;
	}

	public void setFullQualifiedInstanceName(String fullQualifiedInstanceName) {
		this.fullQualifiedInstanceName = fullQualifiedInstanceName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}
	
}
