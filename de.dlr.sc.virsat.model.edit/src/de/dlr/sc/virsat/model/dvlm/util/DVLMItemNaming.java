/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

/**
 * This class implements a method to get abbreviations of objects used in the ItemProvider 
 * @author lobe_el
 *
 */
public class DVLMItemNaming {
	
	/**
	 * Constructor
	 */
	private DVLMItemNaming() {
	}
	
	/**
	 * method which either gives the set shortName of the qualifiedName or computes it from the name
	 * @param iqn object with a qualified name, e.g. the type of a StructuralElementInstance or a CategoryAssignment
	 * @param defaultAbbr the default, if the objects are not named properly
	 * @return the abbreviation
	 */
	public static String getAbbreviation(IQualifiedName iqn, String defaultAbbr) {
		String abbr = "";
		if (iqn != null) {
			String shortName = iqn.getShortName();
			if (!shortName.isEmpty()) {
				return shortName;
			}
			abbr = computeAbbreviation(iqn.getName());
		}
		if (abbr.equals("")) {
			abbr = defaultAbbr;
		}
		return abbr;
	}

	/**
	 * method to extract all capital letters in a name,
	 * by convention this has to start with a capital letter
	 * @param name to get the capital letters from
	 * @return all the capital letters as an abbreviation
	 */
	private static String computeAbbreviation(String name) {
		String abbr = ""; 
		if (!name.isEmpty()) {
			String[] capitalLetters = name.split("[^A-Z]+");  
			abbr  = String.join("", capitalLetters);
		}
		return abbr;
	}
	
	
}
