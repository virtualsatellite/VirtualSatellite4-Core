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
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

public class FlattenedStructuralElementInstance {

	// API read only
	private String uuid;
	private String structuralElementFullQualifiedName;
	private String parent;
	
	// API read and write
	private String name;
	private String description;
	
	// API read all and write on existing SEIS
	private List<String> superSeis; // rw existing
	private List<String> childSeis; // rw existing
	private List<String> categoryAssignments; // rw? existing
	
	// TODO: for all IAssignableDisciplines -> change discipline
	
	public FlattenedStructuralElementInstance() { }
	
	/**
	 * Constructor to flatten an existing sei
	 * @param sei the sei to flatten
	 */
	public FlattenedStructuralElementInstance(StructuralElementInstance sei) {
		setUuid(sei.getUuid().toString());
		setName(sei.getName());
		setDescription(sei.getDescription());
		setSeFullQualifiedName(sei.getType().getFullQualifiedName());
		setParent(sei.getParent() != null ? sei.getParent().getUuid().toString() : null);
		setSuperSeis(collectParentUuids(sei));
		setChildSeis(collectChildUuids(sei));
		setCategoryAssignments(collectCategoryAssignmentUuids(sei));
	}

	/**
	 * Unflatten the properties of this instance into a existing sei
	 * @param editingDomain
	 * @param sei the sei to unflatten on
	 * @return StructuralElementInstance
	 * @throws CoreException
	 */
	public StructuralElementInstance unflatten(VirSatTransactionalEditingDomain editingDomain, StructuralElementInstance sei) throws CoreException {
		Repository repository = editingDomain.getResourceSet().getRepository();
		
		CompoundCommand compoundCommand = new CompoundCommand();
		
		Command commandSetName = SetCommand.create(editingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), getName());
//		alternativ. SetCommand.create(editingDomain, sei, GeneralPackage.Literals.INAME__NAME, getName());
		compoundCommand.append(commandSetName);
		
//		Command commandSetDescription = SetCommand.create(editingDomain, sei, GeneralPackage.eINSTANCE.getIDescription_Description(), getDescription());
		Command commandSetDescription = SetCommand.create(editingDomain, sei, GeneralPackage.Literals.IDESCRIPTION__DESCRIPTION, getDescription());
		compoundCommand.append(commandSetDescription);
		
		// Set or remove parents
		for (String uuid : getSuperSeis()) {
			StructuralElementInstance superSei = RepositoryUtility.findSei(uuid, repository);
			if (superSei != null) {
				if (!sei.getSuperSeis().contains(superSei)) {
					compoundCommand.append(new AddCommand(editingDomain, sei.getSuperSeis(), superSei));
				}
			}
			// TODO: remove old ones?
		}

		// Set all children
		for (String uuid : getChildSeis()) {
			StructuralElementInstance childSei = RepositoryUtility.findSei(uuid, repository);
			if (childSei != null && !sei.getChildren().contains(childSei)) {
				compoundCommand.append(new AddCommand(editingDomain, sei.getChildren(), childSei));
			}
			// TODO: remove old ones?
		}

		// Set all cas
		for (String uuid : getCategoryAssignments()) {
			CategoryAssignment ca = RepositoryUtility.findCa(uuid, repository);
			if (ca != null && !sei.getCategoryAssignments().contains(ca)) {
				compoundCommand.append(new AddCommand(editingDomain, sei.getCategoryAssignments(), ca));
			}
			// TODO: remove old ones?
		}

		editingDomain.getVirSatCommandStack().executeNoUndo(compoundCommand);
		// TODO: maybe return just the command
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

	private List<String> collectCategoryAssignmentUuids(StructuralElementInstance sei) {
		List<String> uuids = new ArrayList<String>();
		for (CategoryAssignment ca : sei.getCategoryAssignments()) {
			uuids.add(ca.getUuid().toString());
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

	public String getSeFullQualifiedName() {
		return structuralElementFullQualifiedName;
	}

	public void setSeFullQualifiedName(String fullQualifiedName) {
		this.structuralElementFullQualifiedName = fullQualifiedName;
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