/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
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
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory
 * @model kind="package"
 * @generated
 */
public interface PropertyinstancesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "propertyinstances";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/cp/cppi";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_cppi";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PropertyinstancesPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance <em>IUnit Property Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getIUnitPropertyInstance()
	 * @generated
	 */
	int IUNIT_PROPERTY_INSTANCE = 0;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE__UUID = CategoriesPackage.ATYPE_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE__COMMENT = CategoriesPackage.ATYPE_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE__SUPER_TIS = CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE__IS_INHERITED = CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE__TYPE = CategoriesPackage.ATYPE_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE__UNIT = CategoriesPackage.ATYPE_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>IUnit Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE_FEATURE_COUNT = CategoriesPackage.ATYPE_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = CategoriesPackage.ATYPE_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = CategoriesPackage.ATYPE_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>IUnit Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUNIT_PROPERTY_INSTANCE_OPERATION_COUNT = CategoriesPackage.ATYPE_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.APropertyInstanceImpl <em>AProperty Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.APropertyInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getAPropertyInstance()
	 * @generated
	 */
	int APROPERTY_INSTANCE = 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE__UUID = CategoriesPackage.ATYPE_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE__COMMENT = CategoriesPackage.ATYPE_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE__SUPER_TIS = CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE__IS_INHERITED = CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE__TYPE = CategoriesPackage.ATYPE_INSTANCE__TYPE;

	/**
	 * The number of structural features of the '<em>AProperty Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE_FEATURE_COUNT = CategoriesPackage.ATYPE_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = CategoriesPackage.ATYPE_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = CategoriesPackage.ATYPE_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>AProperty Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_INSTANCE_OPERATION_COUNT = CategoriesPackage.ATYPE_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ValuePropertyInstanceImpl <em>Value Property Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ValuePropertyInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getValuePropertyInstance()
	 * @generated
	 */
	int VALUE_PROPERTY_INSTANCE = 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE__UUID = APROPERTY_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE__COMMENT = APROPERTY_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE__SUPER_TIS = APROPERTY_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE__IS_INHERITED = APROPERTY_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE__TYPE = APROPERTY_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE__OVERRIDE = APROPERTY_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE__VALUE = APROPERTY_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Value Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE_FEATURE_COUNT = APROPERTY_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>Value Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PROPERTY_INSTANCE_OPERATION_COUNT = APROPERTY_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.UnitValuePropertyInstanceImpl <em>Unit Value Property Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.UnitValuePropertyInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getUnitValuePropertyInstance()
	 * @generated
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE = 3;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE__UUID = APROPERTY_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE__COMMENT = APROPERTY_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE__SUPER_TIS = APROPERTY_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE__IS_INHERITED = APROPERTY_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE__TYPE = APROPERTY_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE__OVERRIDE = APROPERTY_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE__VALUE = APROPERTY_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE__UNIT = APROPERTY_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Unit Value Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE_FEATURE_COUNT = APROPERTY_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The operation id for the '<em>Get Value To Base Unit</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE___GET_VALUE_TO_BASE_UNIT = APROPERTY_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Set Value As Base Unit</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE___SET_VALUE_AS_BASE_UNIT__DOUBLE = APROPERTY_INSTANCE_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Unit Value Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_VALUE_PROPERTY_INSTANCE_OPERATION_COUNT = APROPERTY_INSTANCE_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ReferencePropertyInstanceImpl <em>Reference Property Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ReferencePropertyInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getReferencePropertyInstance()
	 * @generated
	 */
	int REFERENCE_PROPERTY_INSTANCE = 4;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE__UUID = APROPERTY_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE__COMMENT = APROPERTY_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE__SUPER_TIS = APROPERTY_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE__IS_INHERITED = APROPERTY_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE__TYPE = APROPERTY_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE__OVERRIDE = APROPERTY_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE__REFERENCE = APROPERTY_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Reference Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE_FEATURE_COUNT = APROPERTY_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>Reference Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_INSTANCE_OPERATION_COUNT = APROPERTY_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EReferencePropertyInstanceImpl <em>EReference Property Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EReferencePropertyInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getEReferencePropertyInstance()
	 * @generated
	 */
	int EREFERENCE_PROPERTY_INSTANCE = 5;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE__UUID = APROPERTY_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE__COMMENT = APROPERTY_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE__SUPER_TIS = APROPERTY_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE__IS_INHERITED = APROPERTY_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE__TYPE = APROPERTY_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE__OVERRIDE = APROPERTY_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE__REFERENCE = APROPERTY_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>EReference Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE_FEATURE_COUNT = APROPERTY_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>EReference Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_INSTANCE_OPERATION_COUNT = APROPERTY_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ComposedPropertyInstanceImpl <em>Composed Property Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ComposedPropertyInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getComposedPropertyInstance()
	 * @generated
	 */
	int COMPOSED_PROPERTY_INSTANCE = 6;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE__UUID = APROPERTY_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE__COMMENT = APROPERTY_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE__SUPER_TIS = APROPERTY_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE__IS_INHERITED = APROPERTY_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE__TYPE = APROPERTY_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Type Instance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE = APROPERTY_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composed Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE_FEATURE_COUNT = APROPERTY_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>Composed Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_INSTANCE_OPERATION_COUNT = APROPERTY_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ArrayInstanceImpl <em>Array Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ArrayInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getArrayInstance()
	 * @generated
	 */
	int ARRAY_INSTANCE = 7;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE__UUID = APROPERTY_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE__COMMENT = APROPERTY_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE__SUPER_TIS = APROPERTY_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE__IS_INHERITED = APROPERTY_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE__TYPE = APROPERTY_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Array Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE__ARRAY_INSTANCES = APROPERTY_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE_FEATURE_COUNT = APROPERTY_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>Array Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INSTANCE_OPERATION_COUNT = APROPERTY_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ResourcePropertyInstanceImpl <em>Resource Property Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ResourcePropertyInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getResourcePropertyInstance()
	 * @generated
	 */
	int RESOURCE_PROPERTY_INSTANCE = 8;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE__UUID = APROPERTY_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE__COMMENT = APROPERTY_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE__SUPER_TIS = APROPERTY_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE__IS_INHERITED = APROPERTY_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE__TYPE = APROPERTY_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE__OVERRIDE = APROPERTY_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resource Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI = APROPERTY_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resource Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE_FEATURE_COUNT = APROPERTY_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The operation id for the '<em>Set Uri</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE___SET_URI__URI = APROPERTY_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Uri</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE___GET_URI = APROPERTY_INSTANCE_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Resource Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_INSTANCE_OPERATION_COUNT = APROPERTY_INSTANCE_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EnumUnitPropertyInstanceImpl <em>Enum Unit Property Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EnumUnitPropertyInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getEnumUnitPropertyInstance()
	 * @generated
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE = 9;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE__UUID = APROPERTY_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE__COMMENT = APROPERTY_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE__SUPER_TIS = APROPERTY_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE__IS_INHERITED = APROPERTY_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE__TYPE = APROPERTY_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE__OVERRIDE = APROPERTY_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE__UNIT = APROPERTY_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE__VALUE = APROPERTY_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Enum Unit Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE_FEATURE_COUNT = APROPERTY_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = APROPERTY_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = APROPERTY_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>Enum Unit Property Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_UNIT_PROPERTY_INSTANCE_OPERATION_COUNT = APROPERTY_INSTANCE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance <em>IUnit Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IUnit Property Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance
	 * @generated
	 */
	EClass getIUnitPropertyInstance();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance#getUnit()
	 * @see #getIUnitPropertyInstance()
	 * @generated
	 */
	EReference getIUnitPropertyInstance_Unit();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance <em>AProperty Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AProperty Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance
	 * @generated
	 */
	EClass getAPropertyInstance();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance <em>Value Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Property Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance
	 * @generated
	 */
	EClass getValuePropertyInstance();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance#getValue()
	 * @see #getValuePropertyInstance()
	 * @generated
	 */
	EAttribute getValuePropertyInstance_Value();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance <em>Unit Value Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit Value Property Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance
	 * @generated
	 */
	EClass getUnitValuePropertyInstance();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance#getValueToBaseUnit() <em>Get Value To Base Unit</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Value To Base Unit</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance#getValueToBaseUnit()
	 * @generated
	 */
	EOperation getUnitValuePropertyInstance__GetValueToBaseUnit();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance#setValueAsBaseUnit(double) <em>Set Value As Base Unit</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Value As Base Unit</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance#setValueAsBaseUnit(double)
	 * @generated
	 */
	EOperation getUnitValuePropertyInstance__SetValueAsBaseUnit__double();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance <em>Reference Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Property Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance
	 * @generated
	 */
	EClass getReferencePropertyInstance();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance#getReference()
	 * @see #getReferencePropertyInstance()
	 * @generated
	 */
	EReference getReferencePropertyInstance_Reference();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance <em>EReference Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EReference Property Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance
	 * @generated
	 */
	EClass getEReferencePropertyInstance();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance#getReference()
	 * @see #getEReferencePropertyInstance()
	 * @generated
	 */
	EReference getEReferencePropertyInstance_Reference();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance <em>Composed Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composed Property Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance
	 * @generated
	 */
	EClass getComposedPropertyInstance();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance#getTypeInstance <em>Type Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance#getTypeInstance()
	 * @see #getComposedPropertyInstance()
	 * @generated
	 */
	EReference getComposedPropertyInstance_TypeInstance();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance <em>Array Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance
	 * @generated
	 */
	EClass getArrayInstance();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance#getArrayInstances <em>Array Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Array Instances</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance#getArrayInstances()
	 * @see #getArrayInstance()
	 * @generated
	 */
	EReference getArrayInstance_ArrayInstances();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance <em>Resource Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Property Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance
	 * @generated
	 */
	EClass getResourcePropertyInstance();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance#getResourceUri <em>Resource Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Uri</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance#getResourceUri()
	 * @see #getResourcePropertyInstance()
	 * @generated
	 */
	EAttribute getResourcePropertyInstance_ResourceUri();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance#setUri(org.eclipse.emf.common.util.URI) <em>Set Uri</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Uri</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance#setUri(org.eclipse.emf.common.util.URI)
	 * @generated
	 */
	EOperation getResourcePropertyInstance__SetUri__URI();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance#getUri() <em>Get Uri</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Uri</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance#getUri()
	 * @generated
	 */
	EOperation getResourcePropertyInstance__GetUri();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance <em>Enum Unit Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Unit Property Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance
	 * @generated
	 */
	EClass getEnumUnitPropertyInstance();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance#getValue()
	 * @see #getEnumUnitPropertyInstance()
	 * @generated
	 */
	EReference getEnumUnitPropertyInstance_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PropertyinstancesFactory getPropertyinstancesFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance <em>IUnit Property Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getIUnitPropertyInstance()
		 * @generated
		 */
		EClass IUNIT_PROPERTY_INSTANCE = eINSTANCE.getIUnitPropertyInstance();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IUNIT_PROPERTY_INSTANCE__UNIT = eINSTANCE.getIUnitPropertyInstance_Unit();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.APropertyInstanceImpl <em>AProperty Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.APropertyInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getAPropertyInstance()
		 * @generated
		 */
		EClass APROPERTY_INSTANCE = eINSTANCE.getAPropertyInstance();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ValuePropertyInstanceImpl <em>Value Property Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ValuePropertyInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getValuePropertyInstance()
		 * @generated
		 */
		EClass VALUE_PROPERTY_INSTANCE = eINSTANCE.getValuePropertyInstance();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_PROPERTY_INSTANCE__VALUE = eINSTANCE.getValuePropertyInstance_Value();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.UnitValuePropertyInstanceImpl <em>Unit Value Property Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.UnitValuePropertyInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getUnitValuePropertyInstance()
		 * @generated
		 */
		EClass UNIT_VALUE_PROPERTY_INSTANCE = eINSTANCE.getUnitValuePropertyInstance();

		/**
		 * The meta object literal for the '<em><b>Get Value To Base Unit</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation UNIT_VALUE_PROPERTY_INSTANCE___GET_VALUE_TO_BASE_UNIT = eINSTANCE.getUnitValuePropertyInstance__GetValueToBaseUnit();

		/**
		 * The meta object literal for the '<em><b>Set Value As Base Unit</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation UNIT_VALUE_PROPERTY_INSTANCE___SET_VALUE_AS_BASE_UNIT__DOUBLE = eINSTANCE.getUnitValuePropertyInstance__SetValueAsBaseUnit__double();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ReferencePropertyInstanceImpl <em>Reference Property Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ReferencePropertyInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getReferencePropertyInstance()
		 * @generated
		 */
		EClass REFERENCE_PROPERTY_INSTANCE = eINSTANCE.getReferencePropertyInstance();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_PROPERTY_INSTANCE__REFERENCE = eINSTANCE.getReferencePropertyInstance_Reference();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EReferencePropertyInstanceImpl <em>EReference Property Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EReferencePropertyInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getEReferencePropertyInstance()
		 * @generated
		 */
		EClass EREFERENCE_PROPERTY_INSTANCE = eINSTANCE.getEReferencePropertyInstance();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EREFERENCE_PROPERTY_INSTANCE__REFERENCE = eINSTANCE.getEReferencePropertyInstance_Reference();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ComposedPropertyInstanceImpl <em>Composed Property Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ComposedPropertyInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getComposedPropertyInstance()
		 * @generated
		 */
		EClass COMPOSED_PROPERTY_INSTANCE = eINSTANCE.getComposedPropertyInstance();

		/**
		 * The meta object literal for the '<em><b>Type Instance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE = eINSTANCE.getComposedPropertyInstance_TypeInstance();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ArrayInstanceImpl <em>Array Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ArrayInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getArrayInstance()
		 * @generated
		 */
		EClass ARRAY_INSTANCE = eINSTANCE.getArrayInstance();

		/**
		 * The meta object literal for the '<em><b>Array Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_INSTANCE__ARRAY_INSTANCES = eINSTANCE.getArrayInstance_ArrayInstances();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ResourcePropertyInstanceImpl <em>Resource Property Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ResourcePropertyInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getResourcePropertyInstance()
		 * @generated
		 */
		EClass RESOURCE_PROPERTY_INSTANCE = eINSTANCE.getResourcePropertyInstance();

		/**
		 * The meta object literal for the '<em><b>Resource Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI = eINSTANCE.getResourcePropertyInstance_ResourceUri();

		/**
		 * The meta object literal for the '<em><b>Set Uri</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation RESOURCE_PROPERTY_INSTANCE___SET_URI__URI = eINSTANCE.getResourcePropertyInstance__SetUri__URI();

		/**
		 * The meta object literal for the '<em><b>Get Uri</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation RESOURCE_PROPERTY_INSTANCE___GET_URI = eINSTANCE.getResourcePropertyInstance__GetUri();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EnumUnitPropertyInstanceImpl <em>Enum Unit Property Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EnumUnitPropertyInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl#getEnumUnitPropertyInstance()
		 * @generated
		 */
		EClass ENUM_UNIT_PROPERTY_INSTANCE = eINSTANCE.getEnumUnitPropertyInstance();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_UNIT_PROPERTY_INSTANCE__VALUE = eINSTANCE.getEnumUnitPropertyInstance_Value();

	}

} //PropertyinstancesPackage
