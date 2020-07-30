/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.units;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

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
 * @see de.dlr.sc.virsat.model.dvlm.units.UnitsFactory
 * @model kind="package"
 * @generated
 */
public interface UnitsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "units";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/u";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_u";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UnitsPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.units.impl.UnitsPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.units.impl.UnitManagementImpl <em>Unit Management</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.units.impl.UnitManagementImpl
	 * @see de.dlr.sc.virsat.model.dvlm.units.impl.UnitsPackageImpl#getUnitManagement()
	 * @generated
	 */
	int UNIT_MANAGEMENT = 0;

	/**
	 * The feature id for the '<em><b>Assigned Discipline</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_MANAGEMENT__ASSIGNED_DISCIPLINE = GeneralPackage.IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_MANAGEMENT__UUID = GeneralPackage.IASSIGNED_DISCIPLINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>System Of Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_MANAGEMENT__SYSTEM_OF_UNIT = GeneralPackage.IASSIGNED_DISCIPLINE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unit Management</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_MANAGEMENT_FEATURE_COUNT = GeneralPackage.IASSIGNED_DISCIPLINE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Contained IAssigned Disciplines</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_MANAGEMENT___GET_CONTAINED_IASSIGNED_DISCIPLINES = GeneralPackage.IASSIGNED_DISCIPLINE___GET_CONTAINED_IASSIGNED_DISCIPLINES;

	/**
	 * The number of operations of the '<em>Unit Management</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_MANAGEMENT_OPERATION_COUNT = GeneralPackage.IASSIGNED_DISCIPLINE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.units.UnitManagement <em>Unit Management</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit Management</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.units.UnitManagement
	 * @generated
	 */
	EClass getUnitManagement();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.units.UnitManagement#getSystemOfUnit <em>System Of Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>System Of Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.units.UnitManagement#getSystemOfUnit()
	 * @see #getUnitManagement()
	 * @generated
	 */
	EReference getUnitManagement_SystemOfUnit();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UnitsFactory getUnitsFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.units.impl.UnitManagementImpl <em>Unit Management</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.units.impl.UnitManagementImpl
		 * @see de.dlr.sc.virsat.model.dvlm.units.impl.UnitsPackageImpl#getUnitManagement()
		 * @generated
		 */
		EClass UNIT_MANAGEMENT = eINSTANCE.getUnitManagement();

		/**
		 * The meta object literal for the '<em><b>System Of Unit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNIT_MANAGEMENT__SYSTEM_OF_UNIT = eINSTANCE.getUnitManagement_SystemOfUnit();

	}

} //UnitsPackage
