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

import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

public class FlattenedStructuralElementInstance {

	private String uuid;
	private String name;
	private String description;
	private String fullQualifiedName;
	private String parent;
	private List<String> superSeis;
	private List<String> childSeis;
	private List<String> categoryAssignments;
	
	public FlattenedStructuralElementInstance() { }
	
	/**
	 * Constructor to flatten an existing sei
	 * @param sei the sei to flatten
	 */
	public FlattenedStructuralElementInstance(StructuralElementInstance sei) {
		setUuid(sei.getUuid().toString());
		setName(sei.getName());
		setDescription(sei.getDescription());
//		fullQualifiedName = sei.getType().getFullQualifiedName();
		setSe(sei.getType().getFullQualifiedName());
		setParent(sei.getParent() != null ? sei.getParent().getUuid().toString() : null);
		setSuperSeis(collectParentUuids(sei));
		setChildSeis(collectChildUuids(sei));
		collectCategoryAssignmentUuids(sei);
	}
	
	/**
	 * Unflatten the properties of this instance into a sei
	 * @return StructuralElementInstance
	 * @throws CoreException 
	 */
	public StructuralElementInstance unflatten(Repository repository) throws CoreException {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setUuid(new VirSatUuid(getUuid()));
		sei.setName(getName());
		sei.setDescription(getDescription());
		sei.setType(RepositoryUtility.findSe(getSe(), repository));
		if (getParent() != null) {
			sei.setParent(RepositoryUtility.findSei(getParent().toString(), repository));
		} else {
			sei.setParent(null);
		}
		// TODO: set lists???

		return sei;
	}
	
	private List<String> collectParentUuids(StructuralElementInstance sei) {
		List<String> uuids = new ArrayList<String>();
		for (StructuralElementInstance superSei : sei.getSuperSeis()) {
			uuids.add(superSei.getUuid().toString());
		}
		return uuids;
	}

	private List<String> collectChildUuids(StructuralElementInstance sei) {
		List<String> uuids = new ArrayList<String>();
		for (StructuralElementInstance childSei : sei.getChildren()) {
			uuids.add(childSei.getUuid().toString());
		}
		return uuids;
	}

	private List<VirSatUuid> collectCategoryAssignmentUuids(StructuralElementInstance sei) {
		List<VirSatUuid> uuids = new ArrayList<VirSatUuid>();
		for (CategoryAssignment ca : sei.getCategoryAssignments()) {
			uuids.add(ca.getUuid());
		}
		return uuids;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSe() {
		return fullQualifiedName;
	}

	public void setSe(String fullQualifiedName) {
		this.fullQualifiedName = fullQualifiedName;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<String> getSuperSeis() {
		return superSeis;
	}

	public void setSuperSeis(List<String> superSeis) {
		this.superSeis = superSeis;
	}

	public List<String> getChildSeis() {
		return childSeis;
	}

	public void setChildSeis(List<String> childSeis) {
		this.childSeis = childSeis;
	}

	public List<String> getCategoryAssignments() {
		return categoryAssignments;
	}

	public void setCategoryAssignments(List<String> categoryAssignments) {
		this.categoryAssignments = categoryAssignments;
	}
	
}