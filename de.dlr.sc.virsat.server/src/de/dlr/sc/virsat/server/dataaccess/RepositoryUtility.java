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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

public class RepositoryUtility {

	private RepositoryUtility() { }
	
	// TODO: use ecoreutil or VirSatEcoreUtil or resource insteadof RepositoryUtility

	/**
	 * Finds a sei instance by it's uuid
	 * @param uuid the seis uuid
	 * @return the StructuralElementInstance or null
	 * @throws CoreException
	 */
	public static StructuralElementInstance findSei(String uuid, Repository repository) throws CoreException {
		List<StructuralElementInstance> rootSeis = repository.getRootEntities();
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
	 * @return the CategoryAssignment or null
	 * @throws CoreException
	 */
	public static CategoryAssignment findCa(String uuid, Repository repository) throws CoreException {
		List<StructuralElementInstance> rootSeis = repository.getRootEntities();
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
	 * @return the APropertyInstance or null
	 * @throws CoreException
	 */
	public static APropertyInstance findProperty(String uuid, Repository repository) {
		List<StructuralElementInstance> rootSeis = repository.getRootEntities();
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
	
	/**
	 * Finds a discipline instance by it's uuid
	 * @param uuid
	 * @return the Disciplie or null
	 * @throws CoreException
	 */
	public static Discipline findDisciplie(String uuid, Repository repository) {
		EList<Discipline> disciplines = repository.getRoleManagement().getDisciplines();
		for (Discipline discipline : disciplines) {
			if (discipline.getUuid().toString().equals(uuid)) {
				return discipline;
			}
		}
		return null;
	}
	
	/**
	 * Finds a se instance by it's fullQualifiedName
	 * @param fullQualifiedName of the se
	 * @param repository to search
	 * @return the StructuralElement or null or null
	 * @throws CoreException
	 */
	public static StructuralElement findSe(String fullQualifiedName, Repository repository) throws CoreException {
		EList<Concept> concepts = repository.getActiveConcepts();
		for (Concept concept : concepts) {
			for (StructuralElement se : concept.getStructuralElements()) {
				if (se.getFullQualifiedName().equals(fullQualifiedName)) {
					return se;
				}
			}
		}
		return null;
	}
}
