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
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.tree.ITreeTraverserMatcher;
import de.dlr.sc.virsat.model.dvlm.tree.TreeTraverser;

public class RepositoryUtility {

	private RepositoryUtility() { }

	/**
	 * Traverses a Repository and all Seis
	 */
	abstract static class UuidTraverser implements ITreeTraverserMatcher<IUuid> {

		@Override
		public void processMatch(IUuid treeNode, IUuid matchingParent) {
			// Will be handled in isMatching
		}

		@Override
		public Collection<? extends IUuid> getChildren(IUuid treeNode) {
			if (treeNode instanceof Repository) {
				return ((Repository) treeNode).getRootEntities();
			} else if (treeNode instanceof StructuralElementInstance) {
				return ((StructuralElementInstance) treeNode).getChildren();
			}
			return null;
		}
		
	}
	
	/**
	 * Finds a sei instance by it's uuid
	 * @param uuid the seis uuid
	 * @return the StructuralElementInstance or null
	 * @throws CoreException
	 */
	public static StructuralElementInstance findSei(String uuid, Repository repository) throws CoreException {
		
		List<StructuralElementInstance> match = new ArrayList<StructuralElementInstance>(); 
		
		TreeTraverser<IUuid> traverser = new TreeTraverser<IUuid>();
		traverser.traverse(repository, new UuidTraverser() {
			@Override
			public boolean isMatching(IUuid treeNode) {
				boolean isMatching = treeNode.getUuid().toString().equals(uuid);
				
				if (isMatching) {
					match.add((StructuralElementInstance) treeNode);
				}
				
				return isMatching;
			}
		});
		
		if (match.isEmpty()) {
			return null;
		} else {
			return match.get(0);
		}
	}
	
	/**
	 * Iterates over all seis in the repository and matches the uuid on eobject level
	 * @param uuid
	 * @param repository
	 * @return
	 */
	public static IUuid findObjectById(String uuid, Repository repository) {
		List<IUuid> match = new ArrayList<IUuid>(); 
		
		TreeTraverser<IUuid> traverser = new TreeTraverser<IUuid>();
		traverser.traverse(repository, new UuidTraverser() {
			@Override
			public boolean isMatching(IUuid treeNode) {
				boolean isMatching = false;
				
				if (treeNode instanceof StructuralElementInstance) {
					EObject objByUuid = treeNode.eResource().getEObject(uuid);
					isMatching = objByUuid != null;
					if (isMatching) {
						match.add((IUuid) objByUuid);
					}
				}
				
				return isMatching;
			}
		});
		
		if (match.isEmpty()) {
			return null;
		} else {
			return match.get(0);
		}
	}
	
	/**
	 * Finds a ca instance by it's uuid
	 * @param uuid the cas uuid
	 * @return the CategoryAssignment or null
	 */
	public static CategoryAssignment findCa(String uuid, Repository repository) {
		IUuid obj = findObjectById(uuid, repository);
		if (obj instanceof CategoryAssignment) {
			return (CategoryAssignment) obj;
		}
		
		return null;
	}
	
	/**
	 * Finds a property instance by it's uuid
	 * @param uuid the properties uuid
	 * @return the APropertyInstance or null
	 */
	public static APropertyInstance findProperty(String uuid, Repository repository) {
		IUuid obj = findObjectById(uuid, repository);
		if (obj instanceof APropertyInstance) {
			return (APropertyInstance) obj;
		}
		
		return null;
	}
	
	/**
	 * Finds a discipline instance by it's uuid
	 * @param uuid
	 * @return the Discipline or null
	 */
	public static Discipline findDiscipline(String uuid, Repository repository) {
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
	 */
	public static StructuralElement findSe(String fullQualifiedName, Repository repository) {
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
	
	/**
	 * Finds a unit instance by it's uuid
	 * @param uuid the units uuid
	 * @return the AUnit or null
	 */
	public static AUnit findUnit(String uuid, Repository repository) {
		List<AUnit> units = repository.getUnitManagement().getSystemOfUnit().getUnit();
		for (AUnit unit : units) {
			if (unit.getUuid().toString().equals(uuid)) {
				return unit;
			}
		}
		return null;
	}
	
	/**
	 * Finds a quantityKind instance by it's uuid
	 * @param uuid the quantityKinds uuid
	 * @return the AQuantityKind or null
	 */
	public static AQuantityKind findQuantityKind(String uuid, Repository repository) {
		List<SystemOfQuantities> systemsOfQuantities = repository.getUnitManagement().getSystemOfUnit().getSystemOfQuantities();
		for (SystemOfQuantities systemOfQuantities : systemsOfQuantities) {
			for (AQuantityKind quantityKind : systemOfQuantities.getQuantityKind()) {
				if (quantityKind.getUuid().toString().equals(uuid)) {
					return quantityKind;
				}
			}
		}
		return null;
	}
	
	/**
	 * Finds a prefix instance by it's uuid
	 * @param uuid the units uuid
	 * @return the Prefix or null
	 */
	public static Prefix findPrefix(String uuid, Repository repository) {
		List<Prefix> prefixes = repository.getUnitManagement().getSystemOfUnit().getPrefix();
		for (Prefix prefix : prefixes) {
			if (prefix.getUuid().toString().equals(uuid)) {
				return prefix;
			}
		}
		return null;
	}
	
	/**
	 * Finds a unit factor instance by it's uuid
	 * @param uuid the factors uuid
	 * @return the UnitFactor or null
	 */
	public static UnitFactor findUnitFactor(String uuid, Repository repository) {
		List<AUnit> units = repository.getUnitManagement().getSystemOfUnit().getUnit();
		for (AUnit unit : units) {
			if (unit instanceof DerivedUnit) {
				for (UnitFactor unitFactor : ((DerivedUnit) unit).getFactor()) {
					if (unitFactor.getUuid().toString().equals(uuid)) {
						return unitFactor;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Finds a quantityKind factor instance by it's uuid
	 * @param uuid the factors uuid
	 * @return the QuantityKindFactor or null
	 */
	public static QuantityKindFactor findQuantityKindFactor(String uuid, Repository repository) {
		List<SystemOfQuantities> systemsOfQuantities = repository.getUnitManagement().getSystemOfUnit().getSystemOfQuantities();
		for (SystemOfQuantities systemOfQuantities : systemsOfQuantities) {
			for (AQuantityKind quantityKind : systemOfQuantities.getQuantityKind()) {
				if (quantityKind instanceof DerivedQuantityKind) {
					for (QuantityKindFactor qkFactor : ((DerivedQuantityKind) quantityKind).getFactor()) {
						if (qkFactor.getUuid().toString().equals(uuid)) {
							return qkFactor;
						}
					}
				}
			}
		}
		return null;
	}
}
