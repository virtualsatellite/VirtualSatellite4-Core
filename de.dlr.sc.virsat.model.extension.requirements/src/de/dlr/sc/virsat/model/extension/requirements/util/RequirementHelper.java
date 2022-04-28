/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.util;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;

/**
 * Helper class for the import of requirements
 *
 */
public class RequirementHelper {
	
	/**
	 * Clean the name of an entity by removing all invalid chars
	 * 
	 * @param rawName the name to be cleaned
	 * @return the clean name 
	 */
	public String cleanEntityName(String rawName) {
		return rawName.replaceAll(" ", "")
				.replaceAll("-", "")
				.replaceAll("_", "")
				.replaceAll("\\.", "")
				.replaceAll("\\r|\\n", "")
				.replaceAll("\\/", "")
				.replaceAll("\\\\", "");
	}
	
	/**
	 * Find a requirement of a specific name in a list of requirements, optionally as deep search
	 * @param reqList the requirement list
	 * @param identifier the identifier of the requirement to be searched (containing the requirement prefix ReqXXX)
	 * @param deepSearch weather the search should be extended to deeper levels (groups)
	 * @return the requirement to be searched
	 */
	public Requirement findRequirement(IBeanList<RequirementObject> reqList, String identifier, boolean deepSearch) {
		for (RequirementObject reqObject : reqList) {
			if (reqObject.getIdentifier().equals(identifier)) {
				return (Requirement) reqObject;
			} else if (reqObject.getName().equals(identifier)) {
				return (Requirement) reqObject;
			}
			if (deepSearch && reqObject instanceof RequirementGroup) {
				Requirement req = findRequirement(((RequirementGroup) reqObject).getChildren(), identifier, deepSearch);
				if (req != null) {
					return req;
				}
			}
		}
		return null;
	}
	
}
