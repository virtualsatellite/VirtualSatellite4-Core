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
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.DeleteCommand;

import de.dlr.sc.virsat.model.concept.types.structural.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;

public class RepoModelAccessController {

	private VirSatTransactionalEditingDomain editingDomain;
	private VirSatResourceSet resourceSet;
	
	/**
	 * Create a new instance of the modelApi and connect it to the editingDomain
	 * @param editingDomain
	 */
	public RepoModelAccessController(VirSatTransactionalEditingDomain editingDomain) { 
		this.editingDomain = editingDomain;
		resourceSet = editingDomain.getResourceSet();
	}

	/**
	 * Get the roots seis and flatten them
	 * @return List<FlattenedStructuralElementInstance> flattened seis
	 */
	public List<FlattenedStructuralElementInstance> getRootSeis() {
		EList<StructuralElementInstance> rootSeis = resourceSet.getRepository().getRootEntities();
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
		Set<StructuralElementInstance> seis = resourceSet.getAllSeisInProject();
		
		for (StructuralElementInstance sei : seis) {
			if (sei.getUuid().toString().equals(uuid)) {
				return new FlattenedStructuralElementInstance(sei);
			}
		}
		
		return null;
	}
	
	/**
	 * Update or create a sei identified by the uuid
	 * @param newSei the sei to put
	 * @throws CoreException
	 * @throws IOException
	 */
	public void putSei(FlattenedStructuralElementInstance flatSei) throws CoreException, IOException {
		// Update the sei with the same uuid or create it
		StructuralElementInstance newSei = flatSei.unflatten();
		StructuralElementInstance oldSei = findSei(flatSei.getUuid().toString());
		
		if (oldSei != null) {
			// TODO: Is there a better way to do this than deleting and recreating? e.g. SetCommand?
			Command deleteCommand = DeleteCommand.create(editingDomain, oldSei);
			editingDomain.getCommandStack().execute(deleteCommand);
		}
		
		Command createAddSei = CreateAddSeiWithFileStructureCommand.create(editingDomain, resourceSet.getRepository(), newSei);
		editingDomain.getCommandStack().execute(createAddSei);
		
		// TODO: resolve changed inheritance
	}
	
	/**
	 * Creates a copy of the sei with a new uuid in the model
	 * @param flatSei the sei to copy the parameters from
	 * @return String containing the new uuid
	 * @throws CoreException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException 
	 */
	public String postSei(FlattenedStructuralElementInstance flatSei) throws CoreException, InstantiationException, IllegalAccessException, IOException {
		StructuralElementInstance sei = flatSei.unflatten();
		
		// Change the uuid of the sei
		VirSatUuid uuid = new VirSatUuid();
		sei.setUuid(uuid);
		
		Command createAddSei = CreateAddSeiWithFileStructureCommand.create(editingDomain, resourceSet.getRepository(), sei);
		editingDomain.getCommandStack().execute(createAddSei);
		return uuid.toString();
		
		// TODO: resolve inheritance
	}
	
	public void deleteSei(String uuid) throws CoreException, IOException {
		StructuralElementInstance sei = findSei(uuid);
		if (sei != null) {
			Command deleteCommand = DeleteCommand.create(editingDomain, sei);
			editingDomain.getCommandStack().execute(deleteCommand);
		}
	}
	
	public CategoryAssignment getCa(String uuid) throws CoreException {
		return findCa(uuid);
	}

	public APropertyInstance getProperty(String uuid) throws CoreException {
		return findProperty(uuid);
	}
	
	/**
	 * Finds a sei instance by it's uuid
	 * @param uuid the seis uuid
	 * @return the StructuralElementInstance
	 * @throws CoreException
	 */
	private StructuralElementInstance findSei(String uuid) throws CoreException {
		List<StructuralElementInstance> rootSeis = resourceSet.getRepository().getRootEntities();
		TreeIterator<Object> iterator = EcoreUtil.getAllContents(rootSeis, true);
		while (iterator.hasNext()) {
			Object currentSei = iterator.next();
			if (currentSei instanceof StructuralElementInstance) {
				if (((StructuralElementInstance) currentSei).getUuid().toString().equals(uuid)) {
					return (StructuralElementInstance) currentSei;
				}
			}
		}
		return null;
	}
	
	/**
	 * Finds a ca instance by it's uuid
	 * @param uuid the cas uuid
	 * @return the CategoryAssignment
	 * @throws CoreException
	 */
	private CategoryAssignment findCa(String uuid) throws CoreException {
		List<StructuralElementInstance> rootSeis = resourceSet.getRepository().getRootEntities();
		TreeIterator<Object> iterator = EcoreUtil.getAllContents(rootSeis, true);
		while (iterator.hasNext()) {
			Object currentSei = iterator.next();
			if (currentSei instanceof CategoryAssignment) {
				if (((CategoryAssignment) currentSei).getUuid().toString().equals(uuid)) {
					return (CategoryAssignment) currentSei;
				}
			}
		}
		return null;
	}
	
	/**
	 * Finds a property instance by it's uuid
	 * @param uuid the properties uuid
	 * @return the APropertyInstance
	 * @throws CoreException
	 */
	public APropertyInstance findProperty(String uuid) {
		List<StructuralElementInstance> rootSeis = resourceSet.getRepository().getRootEntities();
		TreeIterator<Object> iterator = EcoreUtil.getAllContents(rootSeis, true);
		while (iterator.hasNext()) {
			Object currentEntity = iterator.next();
			if (currentEntity instanceof APropertyInstance) {
				if (((APropertyInstance) currentEntity).getUuid().toString().equals(uuid)) {
					APropertyInstance propertyInstance = (APropertyInstance) currentEntity;
					return propertyInstance;
				}
			}
		}
		return null;
	}
}
