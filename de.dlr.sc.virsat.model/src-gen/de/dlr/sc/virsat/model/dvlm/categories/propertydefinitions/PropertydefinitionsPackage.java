/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;

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
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory
 * @model kind="package"
 * @generated
 */
public interface PropertydefinitionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "propertydefinitions";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/cp/cppd";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_cppd";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PropertydefinitionsPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.APropertyImpl <em>AProperty</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.APropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getAProperty()
	 * @generated
	 */
	int APROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY__NAME = CategoriesPackage.ATYPE_DEFINITION__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY__FULL_QUALIFIED_NAME = CategoriesPackage.ATYPE_DEFINITION__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY__SHORT_NAME = CategoriesPackage.ATYPE_DEFINITION__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY__DESCRIPTION = CategoriesPackage.ATYPE_DEFINITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY__ARRAY_MODIFIER = CategoriesPackage.ATYPE_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>AProperty</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_FEATURE_COUNT = CategoriesPackage.ATYPE_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>AProperty</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APROPERTY_OPERATION_COUNT = CategoriesPackage.ATYPE_DEFINITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ComposedPropertyImpl <em>Composed Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ComposedPropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getComposedProperty()
	 * @generated
	 */
	int COMPOSED_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY__NAME = APROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY__FULL_QUALIFIED_NAME = APROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY__SHORT_NAME = APROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY__DESCRIPTION = APROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY__ARRAY_MODIFIER = APROPERTY__ARRAY_MODIFIER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY__TYPE = APROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Quantity Kind Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY__QUANTITY_KIND_NAME = APROPERTY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY__UNIT_NAME = APROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Composed Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_FEATURE_COUNT = APROPERTY_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Composed Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_PROPERTY_OPERATION_COUNT = APROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ReferencePropertyImpl <em>Reference Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ReferencePropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getReferenceProperty()
	 * @generated
	 */
	int REFERENCE_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY__NAME = APROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY__FULL_QUALIFIED_NAME = APROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY__SHORT_NAME = APROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY__DESCRIPTION = APROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY__ARRAY_MODIFIER = APROPERTY__ARRAY_MODIFIER;

	/**
	 * The feature id for the '<em><b>Reference Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY__REFERENCE_TYPE = APROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_FEATURE_COUNT = APROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Reference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTY_OPERATION_COUNT = APROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EReferencePropertyImpl <em>EReference Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EReferencePropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getEReferenceProperty()
	 * @generated
	 */
	int EREFERENCE_PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY__NAME = APROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY__FULL_QUALIFIED_NAME = APROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY__SHORT_NAME = APROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY__DESCRIPTION = APROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY__ARRAY_MODIFIER = APROPERTY__ARRAY_MODIFIER;

	/**
	 * The feature id for the '<em><b>Reference Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY__REFERENCE_TYPE = APROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EReference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_FEATURE_COUNT = APROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>EReference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_PROPERTY_OPERATION_COUNT = APROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty <em>IIntrinsic Type Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getIIntrinsicTypeProperty()
	 * @generated
	 */
	int IINTRINSIC_TYPE_PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINTRINSIC_TYPE_PROPERTY__DEFAULT_VALUE = 0;

	/**
	 * The number of structural features of the '<em>IIntrinsic Type Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINTRINSIC_TYPE_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IIntrinsic Type Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINTRINSIC_TYPE_PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl <em>AQudv Type Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getAQudvTypeProperty()
	 * @generated
	 */
	int AQUDV_TYPE_PROPERTY = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY__NAME = CalculationPackage.IEQUATION_DEFINITION_INPUT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY__FULL_QUALIFIED_NAME = CalculationPackage.IEQUATION_DEFINITION_INPUT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY__SHORT_NAME = CalculationPackage.IEQUATION_DEFINITION_INPUT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY__DESCRIPTION = CalculationPackage.IEQUATION_DEFINITION_INPUT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER = CalculationPackage.IEQUATION_DEFINITION_INPUT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Quantity Kind Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME = CalculationPackage.IEQUATION_DEFINITION_INPUT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY__UNIT_NAME = CalculationPackage.IEQUATION_DEFINITION_INPUT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>AQudv Type Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY_FEATURE_COUNT = CalculationPackage.IEQUATION_DEFINITION_INPUT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>AQudv Type Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUDV_TYPE_PROPERTY_OPERATION_COUNT = CalculationPackage.IEQUATION_DEFINITION_INPUT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.IntPropertyImpl <em>Int Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.IntPropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getIntProperty()
	 * @generated
	 */
	int INT_PROPERTY = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY__NAME = AQUDV_TYPE_PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY__FULL_QUALIFIED_NAME = AQUDV_TYPE_PROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY__SHORT_NAME = AQUDV_TYPE_PROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY__DESCRIPTION = AQUDV_TYPE_PROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY__ARRAY_MODIFIER = AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER;

	/**
	 * The feature id for the '<em><b>Quantity Kind Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY__QUANTITY_KIND_NAME = AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME;

	/**
	 * The feature id for the '<em><b>Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY__UNIT_NAME = AQUDV_TYPE_PROPERTY__UNIT_NAME;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY__DEFAULT_VALUE = AQUDV_TYPE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY_FEATURE_COUNT = AQUDV_TYPE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Int Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_PROPERTY_OPERATION_COUNT = AQUDV_TYPE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.FloatPropertyImpl <em>Float Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.FloatPropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getFloatProperty()
	 * @generated
	 */
	int FLOAT_PROPERTY = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY__NAME = AQUDV_TYPE_PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY__FULL_QUALIFIED_NAME = AQUDV_TYPE_PROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY__SHORT_NAME = AQUDV_TYPE_PROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY__DESCRIPTION = AQUDV_TYPE_PROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY__ARRAY_MODIFIER = AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER;

	/**
	 * The feature id for the '<em><b>Quantity Kind Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY__QUANTITY_KIND_NAME = AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME;

	/**
	 * The feature id for the '<em><b>Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY__UNIT_NAME = AQUDV_TYPE_PROPERTY__UNIT_NAME;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY__DEFAULT_VALUE = AQUDV_TYPE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY_FEATURE_COUNT = AQUDV_TYPE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Float Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_PROPERTY_OPERATION_COUNT = AQUDV_TYPE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.StringPropertyImpl <em>String Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.StringPropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getStringProperty()
	 * @generated
	 */
	int STRING_PROPERTY = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__NAME = APROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__FULL_QUALIFIED_NAME = APROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__SHORT_NAME = APROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__DESCRIPTION = APROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__ARRAY_MODIFIER = APROPERTY__ARRAY_MODIFIER;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__DEFAULT_VALUE = APROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY_FEATURE_COUNT = APROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY_OPERATION_COUNT = APROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.BooleanPropertyImpl <em>Boolean Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.BooleanPropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getBooleanProperty()
	 * @generated
	 */
	int BOOLEAN_PROPERTY = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__NAME = APROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__FULL_QUALIFIED_NAME = APROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__SHORT_NAME = APROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__DESCRIPTION = APROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__ARRAY_MODIFIER = APROPERTY__ARRAY_MODIFIER;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__DEFAULT_VALUE = APROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY_FEATURE_COUNT = APROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY_OPERATION_COUNT = APROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumPropertyImpl <em>Enum Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumPropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getEnumProperty()
	 * @generated
	 */
	int ENUM_PROPERTY = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__NAME = AQUDV_TYPE_PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__FULL_QUALIFIED_NAME = AQUDV_TYPE_PROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__SHORT_NAME = AQUDV_TYPE_PROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__DESCRIPTION = AQUDV_TYPE_PROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__ARRAY_MODIFIER = AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER;

	/**
	 * The feature id for the '<em><b>Quantity Kind Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__QUANTITY_KIND_NAME = AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME;

	/**
	 * The feature id for the '<em><b>Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__UNIT_NAME = AQUDV_TYPE_PROPERTY__UNIT_NAME;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__VALUES = AQUDV_TYPE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY__DEFAULT_VALUE = AQUDV_TYPE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Enum Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY_FEATURE_COUNT = AQUDV_TYPE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Enum Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_PROPERTY_OPERATION_COUNT = AQUDV_TYPE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumValueDefinitionImpl <em>Enum Value Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumValueDefinitionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getEnumValueDefinition()
	 * @generated
	 */
	int ENUM_VALUE_DEFINITION = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_VALUE_DEFINITION__NAME = GeneralPackage.INAME__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_VALUE_DEFINITION__VALUE = GeneralPackage.INAME_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Enum Value Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_VALUE_DEFINITION_FEATURE_COUNT = GeneralPackage.INAME_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Enum Value Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_VALUE_DEFINITION_OPERATION_COUNT = GeneralPackage.INAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ResourcePropertyImpl <em>Resource Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ResourcePropertyImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getResourceProperty()
	 * @generated
	 */
	int RESOURCE_PROPERTY = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY__NAME = APROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY__FULL_QUALIFIED_NAME = APROPERTY__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY__SHORT_NAME = APROPERTY__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY__DESCRIPTION = APROPERTY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY__ARRAY_MODIFIER = APROPERTY__ARRAY_MODIFIER;

	/**
	 * The number of structural features of the '<em>Resource Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_FEATURE_COUNT = APROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Resource Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_PROPERTY_OPERATION_COUNT = APROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier <em>IArray Modifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getIArrayModifier()
	 * @generated
	 */
	int IARRAY_MODIFIER = 14;

	/**
	 * The number of structural features of the '<em>IArray Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IARRAY_MODIFIER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IArray Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IARRAY_MODIFIER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.StaticArrayModifierImpl <em>Static Array Modifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.StaticArrayModifierImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getStaticArrayModifier()
	 * @generated
	 */
	int STATIC_ARRAY_MODIFIER = 13;

	/**
	 * The feature id for the '<em><b>Array Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_ARRAY_MODIFIER__ARRAY_SIZE = IARRAY_MODIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Static Array Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_ARRAY_MODIFIER_FEATURE_COUNT = IARRAY_MODIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Static Array Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_ARRAY_MODIFIER_OPERATION_COUNT = IARRAY_MODIFIER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.DynamicArrayModifierImpl <em>Dynamic Array Modifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.DynamicArrayModifierImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getDynamicArrayModifier()
	 * @generated
	 */
	int DYNAMIC_ARRAY_MODIFIER = 15;

	/**
	 * The number of structural features of the '<em>Dynamic Array Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_ARRAY_MODIFIER_FEATURE_COUNT = IARRAY_MODIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Dynamic Array Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_ARRAY_MODIFIER_OPERATION_COUNT = IARRAY_MODIFIER_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty <em>AProperty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AProperty</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty
	 * @generated
	 */
	EClass getAProperty();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty#getArrayModifier <em>Array Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array Modifier</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty#getArrayModifier()
	 * @see #getAProperty()
	 * @generated
	 */
	EReference getAProperty_ArrayModifier();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty <em>Composed Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composed Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty
	 * @generated
	 */
	EClass getComposedProperty();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty#getType()
	 * @see #getComposedProperty()
	 * @generated
	 */
	EReference getComposedProperty_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty#getQuantityKindName <em>Quantity Kind Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantity Kind Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty#getQuantityKindName()
	 * @see #getComposedProperty()
	 * @generated
	 */
	EAttribute getComposedProperty_QuantityKindName();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty#getUnitName <em>Unit Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty#getUnitName()
	 * @see #getComposedProperty()
	 * @generated
	 */
	EAttribute getComposedProperty_UnitName();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty <em>Reference Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty
	 * @generated
	 */
	EClass getReferenceProperty();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty#getReferenceType <em>Reference Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference Type</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty#getReferenceType()
	 * @see #getReferenceProperty()
	 * @generated
	 */
	EReference getReferenceProperty_ReferenceType();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty <em>EReference Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EReference Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty
	 * @generated
	 */
	EClass getEReferenceProperty();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty#getReferenceType <em>Reference Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference Type</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty#getReferenceType()
	 * @see #getEReferenceProperty()
	 * @generated
	 */
	EReference getEReferenceProperty_ReferenceType();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty <em>IIntrinsic Type Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IIntrinsic Type Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty
	 * @generated
	 */
	EClass getIIntrinsicTypeProperty();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty#getDefaultValue()
	 * @see #getIIntrinsicTypeProperty()
	 * @generated
	 */
	EAttribute getIIntrinsicTypeProperty_DefaultValue();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty <em>AQudv Type Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AQudv Type Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty
	 * @generated
	 */
	EClass getAQudvTypeProperty();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty#getQuantityKindName <em>Quantity Kind Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantity Kind Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty#getQuantityKindName()
	 * @see #getAQudvTypeProperty()
	 * @generated
	 */
	EAttribute getAQudvTypeProperty_QuantityKindName();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty#getUnitName <em>Unit Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty#getUnitName()
	 * @see #getAQudvTypeProperty()
	 * @generated
	 */
	EAttribute getAQudvTypeProperty_UnitName();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty <em>Int Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty
	 * @generated
	 */
	EClass getIntProperty();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty <em>Float Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty
	 * @generated
	 */
	EClass getFloatProperty();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty <em>String Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty
	 * @generated
	 */
	EClass getStringProperty();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty <em>Boolean Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty
	 * @generated
	 */
	EClass getBooleanProperty();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty <em>Enum Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty
	 * @generated
	 */
	EClass getEnumProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty#getValues()
	 * @see #getEnumProperty()
	 * @generated
	 */
	EReference getEnumProperty_Values();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Value</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty#getDefaultValue()
	 * @see #getEnumProperty()
	 * @generated
	 */
	EReference getEnumProperty_DefaultValue();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition <em>Enum Value Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Value Definition</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition
	 * @generated
	 */
	EClass getEnumValueDefinition();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition#getValue()
	 * @see #getEnumValueDefinition()
	 * @generated
	 */
	EAttribute getEnumValueDefinition_Value();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty <em>Resource Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Property</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty
	 * @generated
	 */
	EClass getResourceProperty();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier <em>Static Array Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Array Modifier</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier
	 * @generated
	 */
	EClass getStaticArrayModifier();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier#getArraySize <em>Array Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Array Size</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier#getArraySize()
	 * @see #getStaticArrayModifier()
	 * @generated
	 */
	EAttribute getStaticArrayModifier_ArraySize();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier <em>IArray Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IArray Modifier</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier
	 * @generated
	 */
	EClass getIArrayModifier();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier <em>Dynamic Array Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Array Modifier</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier
	 * @generated
	 */
	EClass getDynamicArrayModifier();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PropertydefinitionsFactory getPropertydefinitionsFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.APropertyImpl <em>AProperty</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.APropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getAProperty()
		 * @generated
		 */
		EClass APROPERTY = eINSTANCE.getAProperty();

		/**
		 * The meta object literal for the '<em><b>Array Modifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APROPERTY__ARRAY_MODIFIER = eINSTANCE.getAProperty_ArrayModifier();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ComposedPropertyImpl <em>Composed Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ComposedPropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getComposedProperty()
		 * @generated
		 */
		EClass COMPOSED_PROPERTY = eINSTANCE.getComposedProperty();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSED_PROPERTY__TYPE = eINSTANCE.getComposedProperty_Type();

		/**
		 * The meta object literal for the '<em><b>Quantity Kind Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSED_PROPERTY__QUANTITY_KIND_NAME = eINSTANCE.getComposedProperty_QuantityKindName();

		/**
		 * The meta object literal for the '<em><b>Unit Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSED_PROPERTY__UNIT_NAME = eINSTANCE.getComposedProperty_UnitName();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ReferencePropertyImpl <em>Reference Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ReferencePropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getReferenceProperty()
		 * @generated
		 */
		EClass REFERENCE_PROPERTY = eINSTANCE.getReferenceProperty();

		/**
		 * The meta object literal for the '<em><b>Reference Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_PROPERTY__REFERENCE_TYPE = eINSTANCE.getReferenceProperty_ReferenceType();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EReferencePropertyImpl <em>EReference Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EReferencePropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getEReferenceProperty()
		 * @generated
		 */
		EClass EREFERENCE_PROPERTY = eINSTANCE.getEReferenceProperty();

		/**
		 * The meta object literal for the '<em><b>Reference Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EREFERENCE_PROPERTY__REFERENCE_TYPE = eINSTANCE.getEReferenceProperty_ReferenceType();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty <em>IIntrinsic Type Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getIIntrinsicTypeProperty()
		 * @generated
		 */
		EClass IINTRINSIC_TYPE_PROPERTY = eINSTANCE.getIIntrinsicTypeProperty();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IINTRINSIC_TYPE_PROPERTY__DEFAULT_VALUE = eINSTANCE.getIIntrinsicTypeProperty_DefaultValue();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl <em>AQudv Type Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getAQudvTypeProperty()
		 * @generated
		 */
		EClass AQUDV_TYPE_PROPERTY = eINSTANCE.getAQudvTypeProperty();

		/**
		 * The meta object literal for the '<em><b>Quantity Kind Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME = eINSTANCE.getAQudvTypeProperty_QuantityKindName();

		/**
		 * The meta object literal for the '<em><b>Unit Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AQUDV_TYPE_PROPERTY__UNIT_NAME = eINSTANCE.getAQudvTypeProperty_UnitName();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.IntPropertyImpl <em>Int Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.IntPropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getIntProperty()
		 * @generated
		 */
		EClass INT_PROPERTY = eINSTANCE.getIntProperty();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.FloatPropertyImpl <em>Float Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.FloatPropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getFloatProperty()
		 * @generated
		 */
		EClass FLOAT_PROPERTY = eINSTANCE.getFloatProperty();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.StringPropertyImpl <em>String Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.StringPropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getStringProperty()
		 * @generated
		 */
		EClass STRING_PROPERTY = eINSTANCE.getStringProperty();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.BooleanPropertyImpl <em>Boolean Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.BooleanPropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getBooleanProperty()
		 * @generated
		 */
		EClass BOOLEAN_PROPERTY = eINSTANCE.getBooleanProperty();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumPropertyImpl <em>Enum Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumPropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getEnumProperty()
		 * @generated
		 */
		EClass ENUM_PROPERTY = eINSTANCE.getEnumProperty();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_PROPERTY__VALUES = eINSTANCE.getEnumProperty_Values();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_PROPERTY__DEFAULT_VALUE = eINSTANCE.getEnumProperty_DefaultValue();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumValueDefinitionImpl <em>Enum Value Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumValueDefinitionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getEnumValueDefinition()
		 * @generated
		 */
		EClass ENUM_VALUE_DEFINITION = eINSTANCE.getEnumValueDefinition();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUM_VALUE_DEFINITION__VALUE = eINSTANCE.getEnumValueDefinition_Value();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ResourcePropertyImpl <em>Resource Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ResourcePropertyImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getResourceProperty()
		 * @generated
		 */
		EClass RESOURCE_PROPERTY = eINSTANCE.getResourceProperty();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.StaticArrayModifierImpl <em>Static Array Modifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.StaticArrayModifierImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getStaticArrayModifier()
		 * @generated
		 */
		EClass STATIC_ARRAY_MODIFIER = eINSTANCE.getStaticArrayModifier();

		/**
		 * The meta object literal for the '<em><b>Array Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATIC_ARRAY_MODIFIER__ARRAY_SIZE = eINSTANCE.getStaticArrayModifier_ArraySize();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier <em>IArray Modifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getIArrayModifier()
		 * @generated
		 */
		EClass IARRAY_MODIFIER = eINSTANCE.getIArrayModifier();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.DynamicArrayModifierImpl <em>Dynamic Array Modifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.DynamicArrayModifierImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl#getDynamicArrayModifier()
		 * @generated
		 */
		EClass DYNAMIC_ARRAY_MODIFIER = eINSTANCE.getDynamicArrayModifier();

	}

} //PropertydefinitionsPackage
