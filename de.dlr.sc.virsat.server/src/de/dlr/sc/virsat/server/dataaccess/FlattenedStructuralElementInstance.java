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

	private VirSatUuid uuid;
	private String name;
	private String description;
	private String fullQualifiedName;
	private VirSatUuid parent;
	private List<VirSatUuid> superSeis;
	private List<VirSatUuid> childSeis;
	private List<VirSatUuid> categoryAssignments;
	
	public FlattenedStructuralElementInstance() { }
	
	/**
	 * Constructor to flatten an existing sei
	 * @param sei the sei to flatten
	 */
	public FlattenedStructuralElementInstance(StructuralElementInstance sei) {
		setUuid(sei.getUuid());
		setName(sei.getName());
		setDescription(sei.getDescription());
//		fullQualifiedName = sei.getType().getFullQualifiedName();
		setSe(sei.getType().getFullQualifiedName());
		setParent(sei.getParent() != null ? sei.getParent().getUuid() : null);
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
		sei.setUuid(getUuid());
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
	
	private List<VirSatUuid> collectParentUuids(StructuralElementInstance sei) {
		List<VirSatUuid> uuids = new ArrayList<VirSatUuid>();
		for (StructuralElementInstance superSei : sei.getSuperSeis()) {
			uuids.add(superSei.getUuid());
		}
		return uuids;
	}

	private List<VirSatUuid> collectChildUuids(StructuralElementInstance sei) {
		List<VirSatUuid> uuids = new ArrayList<VirSatUuid>();
		for (StructuralElementInstance childSei : sei.getChildren()) {
			uuids.add(childSei.getUuid());
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

	public VirSatUuid getUuid() {
		return uuid;
	}

	public void setUuid(VirSatUuid uuid) {
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

	public VirSatUuid getParent() {
		return parent;
	}

	public void setParent(VirSatUuid parent) {
		this.parent = parent;
	}

	public List<VirSatUuid> getSuperSeis() {
		return superSeis;
	}

	public void setSuperSeis(List<VirSatUuid> superSeis) {
		this.superSeis = superSeis;
	}

	public List<VirSatUuid> getChildSeis() {
		return childSeis;
	}

	public void setChildSeis(List<VirSatUuid> childSeis) {
		this.childSeis = childSeis;
	}

	public List<VirSatUuid> getCategoryAssignments() {
		return categoryAssignments;
	}

	public void setCategoryAssignments(List<VirSatUuid> categoryAssignments) {
		this.categoryAssignments = categoryAssignments;
	}
	
}