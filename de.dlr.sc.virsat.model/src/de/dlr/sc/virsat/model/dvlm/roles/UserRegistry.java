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

import de.dlr.sc.virsat.external.lib.commons.cli.Activator;

/**
 * Implements the suer registry which is used to determine who is currently 
 * logged into the system or Virtual Satellite
 * @author fisc_ph
 *
 */
public class UserRegistry implements IUserContext {

	/**
	 * The constructor
	 */
	private UserRegistry() {
		userName = System.getProperty("user.name");
	}
	
	// Member for the singleton instance
	private static UserRegistry instance = null;
	
	private String userName;
	private int userValidity;
	
	boolean isSuperUser = false;
	
	private static final String SUPER_USER = "superUser";
	private static final String FORCED_USER = "forcedUser";
	private static final int VALIDITY_DAY = 365;
	
	/**
	 * check is the user the super user
	 * @return true if the user is the super user, else false
	 */
	@Override
	public boolean isSuperUser() {
		return isSuperUser;
	}

	/**
	 * set the super user
	 * @param isSuperUser is true for super user rigths
	 */
	public void setSuperUser(boolean isSuperUser) {
		this.isSuperUser = isSuperUser;
	}

	/**
	 * Factory method for the singleton instance
	 * @return instance of the UserRegistry
	 */
	public static synchronized UserRegistry getInstance() {
		// Create an instance in case it does not exist
		if (instance == null) {
			instance = new UserRegistry();
			instance.isSuperUser = Activator.getCommandLineManager().isCommandLineOptionSet(SUPER_USER);
			boolean isForcedUserSet = Activator.getCommandLineManager().isCommandLineOptionSet(FORCED_USER);
			if (isForcedUserSet) {
				String userName = Activator.getCommandLineManager().getCommandLineOptionParameter(FORCED_USER);
				instance.setUser(userName, VALIDITY_DAY);
			}
		}
		return instance;
	}
	
	/**
	 * this method set the name and the validity time of the user
	 * @param userName the name of the user
	 * @param userValidity the validity time of the user account
	 */
	public void setUser(String userName, int userValidity) {
		this.userName = userName;
		this.userValidity = userValidity;
	}
	
	/**
	 * this method returns the name of the user
	 * @return the user name
	 */
	@Override
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * this method get the validity time of the user account
	 * @return the valitify time
	 */
	public int getUserValidity() {
		return this.userValidity;
	}
	
}


