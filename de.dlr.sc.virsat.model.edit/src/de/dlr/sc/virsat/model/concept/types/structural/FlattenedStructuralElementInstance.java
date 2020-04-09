/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.structural;

import java.util.ArrayList;
import java.util.List;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

public class FlattenedStructuralElementInstance {

	private VirSatUuid uuid;
	private String name;
	private String description;
	// TODO: just use the fullQualifiedName instead of the whole se?
//	public String fullQualifiedName;
	private StructuralElement se;
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
		setSe(sei.getType());
		setParent(sei.getParent() != null ? sei.getParent().getUuid() : null);
		setSuperSeis(collectParentUuids(sei));
		setChildSeis(collectChildUuids(sei));
		collectCategoryAssignmentUuids(sei);
	}
	
	/**
	 * Unflatten the properties of this instance into a sei
	 * @return StructuralElementInstance
	 */
	public StructuralElementInstance unflatten() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setUuid(getUuid());
		sei.setName(getName());
		sei.setDescription(getDescription());
		// TODO: ideally only use the fullQualifiedName here
		// How to obtain a se instance from the fullQualifiedName?
		sei.setType(getSe());
		// TODO: set lists and parents
		// How to convert from
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

	public StructuralElement getSe() {
		return se;
	}

	public void setSe(StructuralElement se) {
		this.se = se;
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