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
 * @see de.dlr.sc.virsat.model.dvlm.DVLMFactory
 * @model kind="package"
 * @generated
 */
public interface DVLMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dvlm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DVLMPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.impl.DVLMPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl <em>Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl
	 * @see de.dlr.sc.virsat.model.dvlm.impl.DVLMPackageImpl#getRepository()
	 * @generated
	 */
	int REPOSITORY = 0;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Assigned Discipline</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__ASSIGNED_DISCIPLINE = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Root Entities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__ROOT_ENTITIES = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Role Management</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__ROLE_MANAGEMENT = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Unit Management</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__UNIT_MANAGEMENT = GeneralPackage.IUUID_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Active Concepts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__ACTIVE_CONCEPTS = GeneralPackage.IUUID_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Suppressed Validators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__SUPPRESSED_VALIDATORS = GeneralPackage.IUUID_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Get Contained IAssigned Disciplines</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY___GET_CONTAINED_IASSIGNED_DISCIPLINES = GeneralPackage.IUUID_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.Repository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.Repository
	 * @generated
	 */
	EClass getRepository();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.dvlm.Repository#getRootEntities <em>Root Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Root Entities</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.Repository#getRootEntities()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_RootEntities();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.Repository#getRoleManagement <em>Role Management</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Role Management</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.Repository#getRoleManagement()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_RoleManagement();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.Repository#getUnitManagement <em>Unit Management</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Unit Management</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.Repository#getUnitManagement()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_UnitManagement();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.Repository#getActiveConcepts <em>Active Concepts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Active Concepts</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.Repository#getActiveConcepts()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_ActiveConcepts();

	/**
	 * Returns the meta object for the attribute list '{@link de.dlr.sc.virsat.model.dvlm.Repository#getSuppressedValidators <em>Suppressed Validators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Suppressed Validators</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.Repository#getSuppressedValidators()
	 * @see #getRepository()
	 * @generated
	 */
	EAttribute getRepository_SuppressedValidators();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DVLMFactory getDVLMFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl <em>Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl
		 * @see de.dlr.sc.virsat.model.dvlm.impl.DVLMPackageImpl#getRepository()
		 * @generated
		 */
		EClass REPOSITORY = eINSTANCE.getRepository();

		/**
		 * The meta object literal for the '<em><b>Root Entities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPOSITORY__ROOT_ENTITIES = eINSTANCE.getRepository_RootEntities();

		/**
		 * The meta object literal for the '<em><b>Role Management</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPOSITORY__ROLE_MANAGEMENT = eINSTANCE.getRepository_RoleManagement();

		/**
		 * The meta object literal for the '<em><b>Unit Management</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPOSITORY__UNIT_MANAGEMENT = eINSTANCE.getRepository_UnitManagement();

		/**
		 * The meta object literal for the '<em><b>Active Concepts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPOSITORY__ACTIVE_CONCEPTS = eINSTANCE.getRepository_ActiveConcepts();

		/**
		 * The meta object literal for the '<em><b>Suppressed Validators</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPOSITORY__SUPPRESSED_VALIDATORS = eINSTANCE.getRepository_SuppressedValidators();

	}

} //DVLMPackage
