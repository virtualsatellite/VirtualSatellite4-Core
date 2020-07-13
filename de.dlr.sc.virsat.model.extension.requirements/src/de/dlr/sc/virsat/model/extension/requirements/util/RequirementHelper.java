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
		return rawName.replaceAll(" ", "").replaceAll("-", "").replaceAll("_", "");
	}
	
}
