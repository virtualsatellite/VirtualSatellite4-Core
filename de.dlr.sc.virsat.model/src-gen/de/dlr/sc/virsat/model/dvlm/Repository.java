/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm;


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.Repository#getRootEntities <em>Root Entities</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.Repository#getRoleManagement <em>Role Management</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.Repository#getUnitManagement <em>Unit Management</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.Repository#getActiveConcepts <em>Active Concepts</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.Repository#getSuppressedValidators <em>Suppressed Validators</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.DVLMPackage#getRepository()
 * @model
 * @generated
 */
public interface Repository extends IUuid, IAssignedDiscipline {
	/**
	 * Returns the value of the '<em><b>Root Entities</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Entities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Entities</em>' reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.DVLMPackage#getRepository_RootEntities()
	 * @model
	 * @generated
	 */
	EList<StructuralElementInstance> getRootEntities();

	/**
	 * Returns the value of the '<em><b>Role Management</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Management</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Management</em>' reference.
	 * @see #setRoleManagement(RoleManagement)
	 * @see de.dlr.sc.virsat.model.dvlm.DVLMPackage#getRepository_RoleManagement()
	 * @model required="true"
	 * @generated
	 */
	RoleManagement getRoleManagement();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.Repository#getRoleManagement <em>Role Management</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Management</em>' reference.
	 * @see #getRoleManagement()
	 * @generated
	 */
	void setRoleManagement(RoleManagement value);

	/**
	 * Returns the value of the '<em><b>Unit Management</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit Management</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit Management</em>' reference.
	 * @see #setUnitManagement(UnitManagement)
	 * @see de.dlr.sc.virsat.model.dvlm.DVLMPackage#getRepository_UnitManagement()
	 * @model required="true"
	 * @generated
	 */
	UnitManagement getUnitManagement();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.Repository#getUnitManagement <em>Unit Management</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit Management</em>' reference.
	 * @see #getUnitManagement()
	 * @generated
	 */
	void setUnitManagement(UnitManagement value);

	/**
	 * Returns the value of the '<em><b>Active Concepts</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.concepts.Concept}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Concepts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Concepts</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.DVLMPackage#getRepository_ActiveConcepts()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Concept> getActiveConcepts();

	/**
	 * Returns the value of the '<em><b>Suppressed Validators</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suppressed Validators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suppressed Validators</em>' attribute list.
	 * @see de.dlr.sc.virsat.model.dvlm.DVLMPackage#getRepository_SuppressedValidators()
	 * @model
	 * @generated
	 */
	EList<String> getSuppressedValidators();

} // Repository
