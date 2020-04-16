/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IRepositoryValidator;

/**
 * Implementation of a Validator that checks if a Name has been used multiple times in a scope
 *  
 * @author fisc_ph
 *
 */
public class DvlmRepositoryUniqeNameValidator extends AUniqueNameValidator implements IRepositoryValidator {

	@Override
	public boolean validate(Repository repo) {
		boolean validationOfDisciplinesSuccessful = validateDisciplines(repo);
		boolean validationOfRootSEIsSuccessful = validateRootSEIs(repo);
		return validationOfDisciplinesSuccessful && validationOfRootSEIsSuccessful;
	}
	
	/**
	 * This method checks all the Disciplines in the Repository if there are two which are named equally
	 * @param repository The one to be checked
	 * @return boolean value if the Validation was successful, that means has not found any equally named disciplines, or not
	 */
	private boolean validateDisciplines(Repository repository) {
		boolean validationSuccessful = true;
		
		if (repository.getRoleManagement() != null) {
			EList<Discipline> disciplines = repository.getRoleManagement().getDisciplines();
			
			Map<String, Set<IUuid>> nameToIUuids = new HashMap<>();
			disciplines.forEach((discipline) -> {
				String name = discipline.getName();
				if (!nameToIUuids.containsKey(name)) {
					nameToIUuids.put(name, new HashSet<IUuid>());
				}
				nameToIUuids.get(name).add(discipline);
			});
			validationSuccessful = allUnique(nameToIUuids);
		}
		return validationSuccessful;
	}

	/**
	 * This method checks all the RootEntities in the Repository if there are two which are named equally
	 * @param repository The one to be checked
	 * @return boolean value if the Validation was successful, that means has not found any equally named root SEIs, or not
	 */
	private boolean validateRootSEIs(Repository repository) {
		Map<String, Set<IUuid>> fqnToIUuids = new HashMap<>();
		
		for (StructuralElementInstance rootSei: repository.getRootEntities()) {
			// E.g. the ProductTree can be named "SpaceCraft" as well as the ConfigurationTree
			// But no ProductTrees should be named equally
			// Therefore we differ between the Types of the StructuralElements 
			// But if it is a proxy it needs not to be validated
			if (!rootSei.eIsProxy()) {
				String fqn = rootSei.getFullQualifiedInstanceName() + "(" + rootSei.getType().getName() + ")";
				if (!fqnToIUuids.containsKey(fqn)) {
					fqnToIUuids.put(fqn, new HashSet<IUuid>());
				}
				fqnToIUuids.get(fqn).add(rootSei);
			}
		}
		
		boolean validationSuccessful = allUnique(fqnToIUuids);
		return validationSuccessful;
	}

}
