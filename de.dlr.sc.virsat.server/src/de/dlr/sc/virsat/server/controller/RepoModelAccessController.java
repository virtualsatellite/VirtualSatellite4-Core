/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.structure.command.CreateRemoveSeiWithFileStructureCommand;
import de.dlr.sc.virsat.server.dataaccess.FlattenedCategoryAssignment;
import de.dlr.sc.virsat.server.dataaccess.FlattenedConcept;
import de.dlr.sc.virsat.server.dataaccess.FlattenedDiscipline;
import de.dlr.sc.virsat.server.dataaccess.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;

public class RepoModelAccessController {

	private VirSatTransactionalEditingDomain editingDomain;
	private VirSatResourceSet resourceSet;
	private Repository repository;
	
	/**
	 * Create a new instance of the modelApi and connect it to the editingDomain
	 * @param editingDomain
	 */
	public RepoModelAccessController(VirSatTransactionalEditingDomain editingDomain) { 
		this.editingDomain = editingDomain;
		resourceSet = editingDomain.getResourceSet();
		repository = resourceSet.getRepository();
	}
	
	// Unit Management
	
	// User Management
	/**
	 * Get the disciplines and flatten them
	 * @return List<FlattenedDiscipline> flattened disciplines
	 */
	public List<FlattenedDiscipline> getDisciplines() {
		EList<Discipline> disciplines = repository.getRoleManagement().getDisciplines();
		List<FlattenedDiscipline> flattenedDisciplines = new ArrayList<FlattenedDiscipline>();
		
		for (Discipline discipline : disciplines) {
			flattenedDisciplines.add(new FlattenedDiscipline(discipline));
		}
		return flattenedDisciplines;
	}
	
	// Concept
	/**
	 * Get the active concepts flatten them
	 * @return List<FlattenedConcept> flattened concepts
	 */
	public List<FlattenedConcept> getActiveConcepts() {
		List<Concept> concepts = repository.getActiveConcepts();
		List<FlattenedConcept> flattenedConcepts = new ArrayList<FlattenedConcept>();
		
		for (Concept concept : concepts) {
			flattenedConcepts.add(new FlattenedConcept(concept));
		}
		return flattenedConcepts;
	}
	
	// Seis
	/**
	 * Get the roots seis and flatten them
	 * @return List<FlattenedStructuralElementInstance> flattened seis
	 */
	public List<FlattenedStructuralElementInstance> getRootSeis() {
		EList<StructuralElementInstance> rootSeis = repository.getRootEntities();
		List<FlattenedStructuralElementInstance> flattenedSeis = new ArrayList<FlattenedStructuralElementInstance>();
		
		for (StructuralElementInstance sei : rootSeis) {
			flattenedSeis.add(new FlattenedStructuralElementInstance(sei));
		}
		
		return flattenedSeis;
	}
	
	/**
	 * Get sei by uuid
	 * @param uuid of the sei
	 * @return FlattenedStructuralElementInstance or null
	 * @throws CoreException
	 */
	public FlattenedStructuralElementInstance getSei(String uuid) throws CoreException {
		return new FlattenedStructuralElementInstance(RepositoryUtility.findSei(uuid, repository));
	}
	
	/**
	 * Update a sei identified by the uuid or throw exception if it doesn't exit
	 * @param flatSei the sei to put
	 * @throws CoreException
	 * @throws IOException
	 */
	public void putSei(FlattenedStructuralElementInstance flatSei) throws CoreException, IOException {
		StructuralElementInstance oldSei = RepositoryUtility.findSei(flatSei.getUuid(), repository);
		
		if (oldSei != null) {
			Command updateSeiCommand = flatSei.unflatten(editingDomain, oldSei);
			editingDomain.getVirSatCommandStack().executeNoUndo(updateSeiCommand);
		} else {
			throw new NullPointerException("No resource to update found, use a post request if you want to create a new Resource");
		}
	}
	
	public void deleteSei(String uuid) throws CoreException, IOException {
		StructuralElementInstance sei = RepositoryUtility.findSei(uuid, repository);
		if (sei != null) {
			Command removeCommand = CreateRemoveSeiWithFileStructureCommand.create(sei);
			editingDomain.getCommandStack().execute(removeCommand);
		}
	}
	
	// CAs
	
	public FlattenedCategoryAssignment getCa(String uuid) throws CoreException {
		return new FlattenedCategoryAssignment(RepositoryUtility.findCa(uuid, repository));
	}
	
	/**
	 * Update a ca identified by the uuid or throw exception if it doesn't exit
	 * @param flatCa the ca to put
	 * @throws CoreException
	 * @throws IOException
	 */
	public void putCa(FlattenedCategoryAssignment flatCa) throws CoreException, IOException {
		CategoryAssignment oldCa = RepositoryUtility.findCa(flatCa.getUuid(), repository);
		
		if (oldCa != null) {
			Command updateCaCommand = flatCa.unflatten(editingDomain, oldCa);
			editingDomain.getVirSatCommandStack().executeNoUndo(updateCaCommand);
		} else {
			throw new NullPointerException("No resource to update found, use a post request if you want to create a new Resource");
		}
	}
}
