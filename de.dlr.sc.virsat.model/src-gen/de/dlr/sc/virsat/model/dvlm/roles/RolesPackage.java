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

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.roles.RolesFactory
 * @model kind="package"
 * @generated
 */
public interface RolesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "roles";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/r";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_r";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RolesPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.roles.impl.RolesPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.roles.impl.RoleManagementImpl <em>Role Management</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.roles.impl.RoleManagementImpl
	 * @see de.dlr.sc.virsat.model.dvlm.roles.impl.RolesPackageImpl#getRoleManagement()
	 * @generated
	 */
	int ROLE_MANAGEMENT = 0;

	/**
	 * The feature id for the '<em><b>Assigned Discipline</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_MANAGEMENT__ASSIGNED_DISCIPLINE = GeneralPackage.IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_MANAGEMENT__UUID = GeneralPackage.IASSIGNED_DISCIPLINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Disciplines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_MANAGEMENT__DISCIPLINES = GeneralPackage.IASSIGNED_DISCIPLINE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Role Management</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_MANAGEMENT_FEATURE_COUNT = GeneralPackage.IASSIGNED_DISCIPLINE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Contained IAssigned Disciplines</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_MANAGEMENT___GET_CONTAINED_IASSIGNED_DISCIPLINES = GeneralPackage.IASSIGNED_DISCIPLINE___GET_CONTAINED_IASSIGNED_DISCIPLINES;

	/**
	 * The number of operations of the '<em>Role Management</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_MANAGEMENT_OPERATION_COUNT = GeneralPackage.IASSIGNED_DISCIPLINE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.roles.impl.DisciplineImpl <em>Discipline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.roles.impl.DisciplineImpl
	 * @see de.dlr.sc.virsat.model.dvlm.roles.impl.RolesPackageImpl#getDiscipline()
	 * @generated
	 */
	int DISCIPLINE = 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__NAME = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__USER = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Discipline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Discipline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.roles.RoleManagement <em>Role Management</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Management</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.roles.RoleManagement
	 * @generated
	 */
	EClass getRoleManagement();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.roles.RoleManagement#getDisciplines <em>Disciplines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Disciplines</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.roles.RoleManagement#getDisciplines()
	 * @see #getRoleManagement()
	 * @generated
	 */
	EReference getRoleManagement_Disciplines();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.roles.Discipline <em>Discipline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discipline</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.roles.Discipline
	 * @generated
	 */
	EClass getDiscipline();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.roles.Discipline#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.roles.Discipline#getUser()
	 * @see #getDiscipline()
	 * @generated
	 */
	EAttribute getDiscipline_User();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RolesFactory getRolesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.roles.impl.RoleManagementImpl <em>Role Management</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.roles.impl.RoleManagementImpl
		 * @see de.dlr.sc.virsat.model.dvlm.roles.impl.RolesPackageImpl#getRoleManagement()
		 * @generated
		 */
		EClass ROLE_MANAGEMENT = eINSTANCE.getRoleManagement();

		/**
		 * The meta object literal for the '<em><b>Disciplines</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_MANAGEMENT__DISCIPLINES = eINSTANCE.getRoleManagement_Disciplines();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.roles.impl.DisciplineImpl <em>Discipline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.roles.impl.DisciplineImpl
		 * @see de.dlr.sc.virsat.model.dvlm.roles.impl.RolesPackageImpl#getDiscipline()
		 * @generated
		 */
		EClass DISCIPLINE = eINSTANCE.getDiscipline();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCIPLINE__USER = eINSTANCE.getDiscipline_User();

	}

} //RolesPackage
