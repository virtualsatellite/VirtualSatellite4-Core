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

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IMarker;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

/**
 * Class for checking Names to be unique
 * @author lobe_el
 *
 */
public abstract class AUniqueNameValidator extends ADvlmCoreValidator {
	
	public static final String MESSAGE_PREFIX = "There are duplicate names in namespace: \'";
	
	/**
	 * Method to iterate through a Map containing 
	 * @param nameToIUuids A map containing Names and the collected Objects which have this name
	 * @return boolean if the validation is successful, that means if there has not been an object which is named like another one
	 */
	boolean allUnique(Map<String, Set<IUuid>> nameToIUuids) {
		boolean allUnique = true;
		
		// Now see if there is a set with more than one object
		// if yes report these objects and set a resource marker
		for (Entry<String, Set<IUuid>> nameToIUuidsEntry : nameToIUuids.entrySet()) {
			String name = nameToIUuidsEntry.getKey();
			Set<IUuid> iUuids = nameToIUuidsEntry.getValue(); 
			if (iUuids.size() > 1) {
				allUnique = false;
				for (IUuid iUuid : iUuids) {
					vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, MESSAGE_PREFIX + name + "\'.", iUuid, GeneralPackage.Literals.INAME__NAME);
				} 
			}
			
		}
		return allUnique;
	}
	
}
