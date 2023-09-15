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


import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Discipline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.roles.Discipline#getUser <em>User</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.roles.RolesPackage#getDiscipline()
 * @model
 * @generated
 */
public interface Discipline extends IUuid, IName {
	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute list.
	 * @see de.dlr.sc.virsat.model.dvlm.roles.RolesPackage#getDiscipline_User()
	 * @model default="" required="true"
	 * @generated
	 */
	EList<String> getUser();

	void setUser(String userName);

} // Discipline
