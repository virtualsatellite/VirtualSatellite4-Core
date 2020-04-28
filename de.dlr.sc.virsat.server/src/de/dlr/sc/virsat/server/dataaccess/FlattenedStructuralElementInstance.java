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
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
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
	private String discipline;
	
	// API read all and write on existing SEIS
	private List<String> superSeis;
	private List<String> children;
	private List<String> categoryAssignments; // Maybe only read?
	
	public FlattenedStructuralElementInstance() { }
	
	/**
	 * Constructor to flatten an existing sei
	 * @param sei the sei to flatten
	 */
	public FlattenedStructuralElementInstance(StructuralElementInstance sei) {
		setUuid(sei.getUuid().toString());
		setSeFullQualifiedName(sei.getType().getFullQualifiedName());
		setParent(sei.getParent() != null ? sei.getParent().getUuid().toString() : null);
		
		setName(sei.getName());
		setDescription(sei.getDescription());
		if (sei.getAssignedDiscipline() != null)	{
			setDiscipline(sei.getAssignedDiscipline().getUuid().toString());
		} else {
			setDiscipline(null);
		}
		
		setSuperSeis(collectParentUuids(sei));
		setChildren(collectChildUuids(sei));
		setCategoryAssignments(collectCategoryAssignmentUuids(sei));
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

	/**
	 * Create a command to unflatten the properties of this instance into a existing sei
	 * @param editingDomain
	 * @param sei the sei to unflatten on
	 * @return Command
	 * @throws CoreException
	 */
	public Command unflatten(VirSatTransactionalEditingDomain editingDomain, StructuralElementInstance sei) throws CoreException {
		
		CompoundCommand updateSeiCommand = new CompoundCommand();
		
		// If the packages with eINSTNACEs don't work use Literals
		Command commandSetName = SetCommand.create(editingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), getName());
		updateSeiCommand.append(commandSetName);
		
		Command commandSetDescription = SetCommand.create(editingDomain, sei, GeneralPackage.eINSTANCE.getIDescription_Description(), getDescription());
		updateSeiCommand.append(commandSetDescription);
		
		Discipline discipline = RepositoryUtility.findDisciplie(getDiscipline(), editingDomain.getResourceSet().getRepository());
		Command commandSetDiscipline = SetCommand.create(editingDomain, sei, GeneralPackage.eINSTANCE.getIAssignedDiscipline_AssignedDiscipline(), discipline);
		updateSeiCommand.append(commandSetDiscipline);
		
		addAndRemoveParents(sei, editingDomain, updateSeiCommand);
		addAndRemoveChildren(sei, editingDomain, updateSeiCommand);
		addAndRemoveCategoryAssignments(sei, editingDomain, updateSeiCommand);
		
		return updateSeiCommand;
	}
	
	/**
	 * Add commands to add or remove parents from sei to updateSeiCommand
	 * @param sei
	 * @param editingDomain
	 * @param updateSeiCommand
	 * @throws CoreException
	 */
	private void addAndRemoveParents(StructuralElementInstance sei, VirSatTransactionalEditingDomain editingDomain, CompoundCommand updateSeiCommand) throws CoreException {
		// Add parents
		for (String uuid : getSuperSeis()) {
			StructuralElementInstance superSei = RepositoryUtility.findSei(uuid, editingDomain.getResourceSet().getRepository());
			if (superSei != null) {
				if (!sei.getSuperSeis().contains(superSei)) {
					updateSeiCommand.append(new AddCommand(editingDomain, sei.getSuperSeis(), superSei));
				}
			}
		}
		// Remove parents
		for (StructuralElementInstance superSei : sei.getSuperSeis()) {
			if (!getSuperSeis().contains(superSei.getUuid().toString())) {
				updateSeiCommand.append(new RemoveCommand(editingDomain, sei.getSuperSeis(), superSei));
			}
		}
	}
	
	/**
	 * Add commands to add or remove children from sei to updateSeiCommand
	 * @param sei
	 * @param editingDomain
	 * @param updateSeiCommand
	 * @throws CoreException
	 */
	private void addAndRemoveChildren(StructuralElementInstance sei, VirSatTransactionalEditingDomain editingDomain, CompoundCommand updateSeiCommand) throws CoreException {
		// Add children
		for (String uuid : getChildren()) {
			StructuralElementInstance childSei = RepositoryUtility.findSei(uuid, editingDomain.getResourceSet().getRepository());
			if (childSei != null && !sei.getChildren().contains(childSei)) {
				updateSeiCommand.append(new AddCommand(editingDomain, sei.getChildren(), childSei));
			}
		}
		// Remove children
		for (StructuralElementInstance childSei : sei.getChildren()) {
			if (!getSuperSeis().contains(childSei.getUuid().toString())) {
				updateSeiCommand.append(new RemoveCommand(editingDomain, sei.getChildren(), childSei));
			}
		}
	}
	
	/**
	 * Add commands to add or remove cas from sei to updateSeiCommand
	 * @param sei
	 * @param editingDomain
	 * @param updateSeiCommand
	 * @throws CoreException
	 */
	private void addAndRemoveCategoryAssignments(StructuralElementInstance sei, VirSatTransactionalEditingDomain editingDomain, CompoundCommand updateSeiCommand) throws CoreException {
		// Add cas
		for (String uuid : getCategoryAssignments()) {
			CategoryAssignment ca = RepositoryUtility.findCa(uuid, editingDomain.getResourceSet().getRepository());
			if (ca != null && !sei.getCategoryAssignments().contains(ca)) {
				updateSeiCommand.append(new AddCommand(editingDomain, sei.getCategoryAssignments(), ca));
			}
		}
		// Remove cas
		for (CategoryAssignment ca : sei.getCategoryAssignments()) {
			if (!getCategoryAssignments().contains(ca.getUuid().toString())) {
				updateSeiCommand.append(new RemoveCommand(editingDomain, sei.getCategoryAssignments(), ca));
			}
		}
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

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> children) {
		this.children = children;
	}

	public List<String> getCategoryAssignments() {
		return categoryAssignments;
	}

	public void setCategoryAssignments(List<String> categoryAssignments) {
		this.categoryAssignments = categoryAssignments;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
}