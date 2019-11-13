/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.general;


import de.dlr.sc.virsat.model.dvlm.roles.Discipline;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IAssigned Discipline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline#getAssignedDiscipline <em>Assigned Discipline</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIAssignedDiscipline()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IAssignedDiscipline extends EObject {
	/**
	 * Returns the value of the '<em><b>Assigned Discipline</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assigned Discipline</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assigned Discipline</em>' reference.
	 * @see #setAssignedDiscipline(Discipline)
	 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIAssignedDiscipline_AssignedDiscipline()
	 * @model required="true"
	 * @generated
	 */
	Discipline getAssignedDiscipline();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline#getAssignedDiscipline <em>Assigned Discipline</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assigned Discipline</em>' reference.
	 * @see #getAssignedDiscipline()
	 * @generated
	 */
	void setAssignedDiscipline(Discipline value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method hands back all contained IAssignedDiscipline objects
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<IAssignedDiscipline> getContainedIAssignedDisciplines();

} // IAssignedDiscipline
