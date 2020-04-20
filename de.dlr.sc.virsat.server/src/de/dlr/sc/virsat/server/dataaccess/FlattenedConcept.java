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

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

public class FlattenedConcept {

	private String fullQualifiedName;
	private String name;
	private String version;
	private String description;
	
	public FlattenedConcept() { };
	
	public FlattenedConcept(Concept concept) {
		setFullQualifiedName(concept.getFullQualifiedName());
		setName(concept.getName());
		setVersion(concept.getVersion());
		setDescription(concept.getDescription());
		// TODO: which Properties (and lists) do we want do read?
	}

	public String getFullQualifiedName() {
		return fullQualifiedName;
	}

	public void setFullQualifiedName(String fullQualifiedName) {
		this.fullQualifiedName = fullQualifiedName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
