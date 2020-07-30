/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.roles;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;

/**
 * Simple Helper method to determine the access rights on an EObject
 */
public class RightsHelper {
	
	/**
	 * Private Constructor for Helper Class
	 */
	private RightsHelper() {
	}

	/**
	 * this method get the discipline of an object
	 * @param eObj the object which has an discipline 
	 * @return the discipline of the object
	 */
	public static synchronized Discipline getDiscipline(EObject eObj) {
		EObject currentObject = eObj;
		while (!(currentObject instanceof IAssignedDiscipline)) {
			currentObject = currentObject.eContainer();
			
			if ((currentObject == null) || (currentObject instanceof Resource)) {
				return null;
			}
		}
		
		return ((IAssignedDiscipline) currentObject).getAssignedDiscipline();
	}
	
	/**
	 * This method checks if there exists a parent of eObj that can have a discipline.
	 * If no such parent exists, we dont need to do any rights management.
	 * @param eObj the object we check
	 * @return true iff a parent (or grandparent, etc.) that is an IAssignableDiscipline exists
	 */
	private static synchronized boolean hasParentThatCanHaveDiscipline(EObject eObj) {
		EObject currentObject = eObj;
		while (currentObject != null) {
			if (currentObject instanceof IAssignedDiscipline) {
				return true;
			}
			currentObject = currentObject.eContainer();
		}
		
		return false;
	}
	
	/**
	 * This method is use to check if there is possible write access to the given object.
	 * Write access is given in case the user from the given context matches to the one in
	 * the assigned discipline or if super user rights are set.
	 * @param eObject the object to check if there is write access
	 * @param userContext The User Context to be used when checking if the object is writable
	 * @return true if the object allows writing, else false. Returns false in case userContext is null
	 */
	public static synchronized boolean hasWritePermission(EObject eObject, IUserContext userContext) {
		if (userContext != null) {
			if (!hasParentThatCanHaveDiscipline(eObject)) {
				// If in the container hierarchy there exists no object with an assignable discipline
				// We can skip any further checks to the write permission since we cannot obtain a discipline
				// Note that this may be different than having an assignable discipline parent but no discipline assigned
				return true;
			}
			
			boolean hasWritePermission = false;
			String registeredUserInApplication = userContext.getUserName();
			
			// the owner of the command is our object we work on
			Discipline disc = getDiscipline(eObject);
			if (disc != null) {
				String userAssignedToDispline = disc.getUser();
				if (!userAssignedToDispline.isEmpty()) {
					hasWritePermission = userAssignedToDispline.equals(registeredUserInApplication);
				}	
			}
			
			return hasWritePermission || userContext.isSuperUser();
		}
		// False in case there is no suer context
		return false;
	}

	/**
	 * This method is intended for checking write access in single user mode.
	 * This mode is usually used in Virtual Satellite Desktop application, e.g. in
	 * the User Interface. The method will use the System User Registry as User Context
	 * @param eObject The eObject to be tested for write access
	 * @return true in case the user has access or is a super user.
	 */
	public static boolean hasSystemUserWritePermission(EObject eObject) {
		return hasWritePermission(eObject, UserRegistry.getInstance());
	}
}
