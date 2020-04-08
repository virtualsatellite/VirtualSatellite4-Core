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
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralElementInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

public class FlattenedStructuralElementInstance {

	public VirSatUuid uuid;
	public String name;
	public String description;
	public String fullQualifiedName;
	public VirSatUuid parent;
	public List<VirSatUuid> superSeis;
	public List<VirSatUuid> childSeis;
	public List<VirSatUuid> categoryAssignments;
	
	public FlattenedStructuralElementInstance() { }
	
	public FlattenedStructuralElementInstance(StructuralElementInstance sei) {
		uuid = sei.getUuid();
		name = sei.getName();
		description = sei.getDescription();
		fullQualifiedName = sei.getType().getFullQualifiedName();
		parent = sei.getParent() != null ? sei.getParent().getUuid() : null;
		superSeis = collectParentUuids(sei);
		childSeis = collectChildUuids(sei);
		collectCategoryAssignmentUuids(sei);
	}
	
	public StructuralElementInstance unflatten() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setUuid(uuid);
		sei.setName(name);
		sei.setDescription(description);
//		sei.setType(fullQualifiedName);
//		sei.setParent(parent);
		// TODO: set lists
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
	
}