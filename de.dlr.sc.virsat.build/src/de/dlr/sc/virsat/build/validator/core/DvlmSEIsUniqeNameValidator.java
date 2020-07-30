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

import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;

/**
 * Implementation of a Validator that checks if a Name has been used multiple times in a scope
 *  
 * @author fisc_ph
 *
 */
public class DvlmSEIsUniqeNameValidator extends AUniqueNameValidator implements IStructuralElementInstanceValidator {
	
	@Override
	public boolean validate(StructuralElementInstance sei) {
		Map<String, Set<IUuid>> fqnToIUuids = new HashMap<>();
		
		// Loop over all contained IName interfaces and see if one provides
		// exactly the same full qualified name as another
		EcoreUtil.getAllContents(sei.getCategoryAssignments(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment iInstance = (CategoryAssignment) object;
				String fqn = iInstance.getFullQualifiedInstanceName();
				if (!fqnToIUuids.containsKey(fqn)) {
					fqnToIUuids.put(fqn, new HashSet<IUuid>());
				}
				fqnToIUuids.get(fqn).add(iInstance);
			}
		});
		
		// Get all direct children to see if they show a duplicate name
		for (StructuralElementInstance childSei: sei.getChildren()) {
			String fqn = childSei.getFullQualifiedInstanceName();
			if (!fqnToIUuids.containsKey(fqn)) {
				fqnToIUuids.put(fqn, new HashSet<IUuid>());
			}
			fqnToIUuids.get(fqn).add(childSei);
		}
		
		boolean validationSuccessful = allUnique(fqnToIUuids);
		return validationSuccessful;
	}
}
