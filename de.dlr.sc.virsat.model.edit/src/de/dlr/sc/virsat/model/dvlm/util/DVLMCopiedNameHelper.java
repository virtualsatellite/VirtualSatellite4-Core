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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.dlr.sc.virsat.model.dvlm.general.IName;

/**
 * Helper class to handle the names of copied DVLM objects based on the IName interface
 * This class understands the name pattern with such as "Equipment", "Equipment_2", "Equipment_3".
 * 
 * It also provides methods in case that collections of new objects get added to an existing one,
 * that correct new names will be generated.
 * 
 * @author fisc_ph
 *
 */
public class DVLMCopiedNameHelper {

	public static final String REGEX_COPIED_NAME_SEPARATOR = "_";
	public static final String REGEX_COPIED_GROUP_NAME = "name";
	public static final String REGEX_COPIED_GROUP_NUMBER = "number";

	// The full regex should look like this: "(?<name>.*)_(?<number>\\d+)";
	public static final String REGEX_COPIED_NAME_PATTERN = "(?<" + REGEX_COPIED_GROUP_NAME + ">.*)" + REGEX_COPIED_NAME_SEPARATOR + "(?<" + REGEX_COPIED_GROUP_NUMBER + ">\\d+)";
	public static final String COPIED_NAME_ORIGIN_NUMBER = "1";
	
	private Pattern copiedNamePattern;
	
	/**
	 * Standard Constructor
	 */
	public DVLMCopiedNameHelper() {
		copiedNamePattern = Pattern.compile(REGEX_COPIED_NAME_PATTERN);
	}
	
	/**
	 * This method extracts the uncopied name which means the name before
	 * the underscore followed by the cardinality number.
	 * @param fullName The full name such as "Equipment_2" or "Equipment"
	 * @return the first part of the name such as "Equipment"
	 */
	public String extractUncopiedName(String fullName) {
		Matcher matcher = copiedNamePattern.matcher(fullName);
		boolean matchFound = matcher.matches();
		
		if (matchFound) {
			return matcher.group(REGEX_COPIED_GROUP_NAME);
		}
		
		return fullName;
	}
	
	/**
	 * This method extracts the copy number which is indicating the
	 * count of copied written directly behind the last underscore.
	 * @param fullName The full name such as "Equipment_2" or "Equipment"
	 * @return The number as String. No Number will return "1"
	 */
	public String extractCurrentCopyNumber(String fullName) {
		Matcher matcher = copiedNamePattern.matcher(fullName);
		boolean matchFound = matcher.matches();
		
		if (matchFound) {
			return matcher.group(REGEX_COPIED_GROUP_NUMBER);
		}
		
		return COPIED_NAME_ORIGIN_NUMBER;
	}
	
	/**
	 * This method hands back the copy number encoded in the name as
	 * an integer value.
	 * @param fullName The full name such as "Equipment_2" or "Equipment"
	 * @return The copy number as an integer value. Smallest Value returned is 1.
	 */
	public int extractCurrentCopyValue(String fullName) {
		String currentCopyNumber = extractCurrentCopyNumber(fullName);
		return Integer.parseInt(currentCopyNumber);
	}
	
	/**
	 * This method is used to update the number of a given name. The given number
	 * will be encoded after the last underscore followed by a previous number.
	 * @param fullName The full name such as "Equipment_2" or "Equipment"
	 * @param copiedNumber the number to be encoded into the full name
	 * @return returns the full name with the encoded number. Setting a value
	 *  smaller than two will only return the uncopied name
	 */
	public String updateCopiedName(String fullName, int copiedNumber) {
		String uncopiedName = extractUncopiedName(fullName);
		
		//numberDigits get the bigger value, can't get into minus
		int numberDigits = Math.max(fullName.length() - uncopiedName.length() - 1, Integer.toUnsignedString(copiedNumber).length());
		if (copiedNumber > 1) {
			return uncopiedName + REGEX_COPIED_NAME_SEPARATOR + String.format("%0" + numberDigits + "d", copiedNumber);
		} else {
			return uncopiedName;
		}
	}

	/**
	 * This method creates a new name for a given full name by comparing it with a list
	 * of already existing names. The method crawls the list of existing names for matches
	 * on the uncopied names and so far highest copy count. The new name will be encoded with
	 * the highest count + 1.
	 * @param existingNames a list of Strings with existing names (fullNames)
	 * @param newFullName the existing full name.
	 * @return the new full name of a potentially copied object.
	 */
	public String createCopiedName(List<String> existingNames, String newFullName) {
		String uncopiedNewName = extractUncopiedName(newFullName);
		int highestExistingCopiedNumber = 0;
		
		// now find the names and their highest copied in the list of existing names
		for (String existingFullName : existingNames) {
			String existingUncopiedName = extractUncopiedName(existingFullName);

			// If the names are the same check for the copied Number 
			if (existingUncopiedName.equals(uncopiedNewName)) {
				int existingCopiedNumber = extractCurrentCopyValue(existingFullName);
				if (existingCopiedNumber > highestExistingCopiedNumber) {
					highestExistingCopiedNumber = existingCopiedNumber;
				}
			}
		}
		
		// Increase by one for the new name
		highestExistingCopiedNumber++;
		
		// Finally hand back the new name of the copy
		return updateCopiedName(newFullName, highestExistingCopiedNumber);
	}
	
	/**
	 * This method generates the new full names for a list of copied objects by comparing
	 * them with a list of already existing names. the method ensures that the full new names
	 * combined with the existing full names will be a list of unique full names.
	 * @param existingNames A List of objects based on IName.
	 * @param copiedNames A Collection of copied names which should be based on IName for the moment. 
	 * 	This parameter has to be opened up later on to also accept a list of PropertyInstances which are
	 *  not based on IName. This will be needed for correct handling of copying in Arrays
	 * @return The list with the copied names as it was given as input.
	 */
	public Collection<?> updateCopiedNames(List<? extends IName> existingNames, Collection<?> copiedNames) {
		// First gather all names from the list of already existing components such as SEIs or CAs
		List<String> existingFullNames = new ArrayList<>();
		existingNames.forEach((iName) -> existingFullNames.add(iName.getName()));
		
		// Now loop over the elements which are supposed to be added
		for (Object object : copiedNames) {
			if (object instanceof IName) {
				IName copiedName = (IName) object;
				String copiedFullName = copiedName.getName();
				
				// Now create a new name for the copied one with the context of the already existing ones
				// Once the new name is created directly update the copied object with the new name
				// This may cause problems using EMF Commands and maybe needs to be wrapped into some 
				// recording command
				String copiedNewFullName = createCopiedName(existingFullNames, copiedFullName);
				copiedName.setName(copiedNewFullName);
				
				// Now the newly generated names has to be considered as an existing as well
				existingFullNames.add(copiedNewFullName);
			}
		}
		
		// Just hand back the list for further operations
		return copiedNames;
	}
}
