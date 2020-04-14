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