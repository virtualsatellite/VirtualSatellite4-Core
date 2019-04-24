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

/**
 * This exception is thrown when the current user tries to perform an operation on an object that does not belong to this user
 * @author kova_an
 *
 */
public class UserHasNoRightsException extends RuntimeException {

	/**
	 * Constructor
	 * @param message 
	 */
    public UserHasNoRightsException(String message) {
        super(message);
    }
	
	private static final long serialVersionUID = -5247900703100412212L;
}
