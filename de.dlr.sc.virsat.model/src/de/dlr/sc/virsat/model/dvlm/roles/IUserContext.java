/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.roles;

public interface IUserContext {

	/**
	 * check is the user the super user
	 * @return true if the user is the super user, else false
	 */
	boolean isSuperUser();

	/**
	 * this method returns the name of the user
	 * @return the user name
	 */
	String getUserName();

}