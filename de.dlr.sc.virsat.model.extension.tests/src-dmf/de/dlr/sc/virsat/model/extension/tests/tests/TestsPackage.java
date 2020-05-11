/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.extension.tests.tests;

import de.dlr.sc.virsat.model.ext.core.core.CorePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsFactory
 * @model kind="package"
 * @generated
 */
public interface TestsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tests";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dmf/v1.1/tests";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dmf_tests";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestsPackage eINSTANCE = de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl <em>Test Category All Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryAllProperty()
	 * @generated
	 */
	int TEST_CATEGORY_ALL_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Test String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY__TEST_STRING = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Test Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY__TEST_INT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Test Float</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY__TEST_FLOAT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Test Bool</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY__TEST_BOOL = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Test Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY__TEST_RESOURCE = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Test Enum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY__TEST_ENUM = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Test Category All Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Test Category All Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_ALL_PROPERTY_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionImpl <em>Test Category Composition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryComposition()
	 * @generated
	 */
	int TEST_CATEGORY_COMPOSITION = 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Test Sub Category</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Category Composition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Test Category Composition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceImpl <em>Test Category Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryReference()
	 * @generated
	 */
	int TEST_CATEGORY_REFERENCE = 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Test Ref Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Category Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Test Category Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryIntrinsicArrayImpl <em>Test Category Intrinsic Array</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryIntrinsicArrayImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryIntrinsicArray()
	 * @generated
	 */
	int TEST_CATEGORY_INTRINSIC_ARRAY = 3;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_INTRINSIC_ARRAY__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_INTRINSIC_ARRAY__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Test String Array Dynamic</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_DYNAMIC = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Test String Array Static</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_STATIC = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Category Intrinsic Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_INTRINSIC_ARRAY_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Test Category Intrinsic Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_INTRINSIC_ARRAY_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionArrayImpl <em>Test Category Composition Array</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionArrayImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryCompositionArray()
	 * @generated
	 */
	int TEST_CATEGORY_COMPOSITION_ARRAY = 4;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION_ARRAY__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION_ARRAY__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Test Composition Array Dynamic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Test Composition Array Static</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Category Composition Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION_ARRAY_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Test Category Composition Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_COMPOSITION_ARRAY_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceArrayImpl <em>Test Category Reference Array</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceArrayImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryReferenceArray()
	 * @generated
	 */
	int TEST_CATEGORY_REFERENCE_ARRAY = 5;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE_ARRAY__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE_ARRAY__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Test Category Reference Array Dynamic</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_DYNAMIC = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Test Category Reference Array Static</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_STATIC = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Category Reference Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE_ARRAY_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Test Category Reference Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_REFERENCE_ARRAY_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanAImpl <em>Test Category Bean A</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanAImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBeanA()
	 * @generated
	 */
	int TEST_CATEGORY_BEAN_A = 6;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_A__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_A__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The number of structural features of the '<em>Test Category Bean A</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_A_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Test Category Bean A</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_A_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanBImpl <em>Test Category Bean B</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanBImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBeanB()
	 * @generated
	 */
	int TEST_CATEGORY_BEAN_B = 7;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_B__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_B__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The number of structural features of the '<em>Test Category Bean B</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_B_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Test Category Bean B</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_B_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanAbstractImpl <em>Test Category Bean Abstract</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanAbstractImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBeanAbstract()
	 * @generated
	 */
	int TEST_CATEGORY_BEAN_ABSTRACT = 8;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_ABSTRACT__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_ABSTRACT__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The number of structural features of the '<em>Test Category Bean Abstract</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_ABSTRACT_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Test Category Bean Abstract</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_ABSTRACT_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanConcreteImpl <em>Test Category Bean Concrete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanConcreteImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBeanConcrete()
	 * @generated
	 */
	int TEST_CATEGORY_BEAN_CONCRETE = 9;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_CONCRETE__UUID = TEST_CATEGORY_BEAN_ABSTRACT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_CONCRETE__NAME = TEST_CATEGORY_BEAN_ABSTRACT__NAME;

	/**
	 * The number of structural features of the '<em>Test Category Bean Concrete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_CONCRETE_FEATURE_COUNT = TEST_CATEGORY_BEAN_ABSTRACT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Test Category Bean Concrete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BEAN_CONCRETE_OPERATION_COUNT = TEST_CATEGORY_BEAN_ABSTRACT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBaseImpl <em>Test Category Base</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBaseImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBase()
	 * @generated
	 */
	int TEST_CATEGORY_BASE = 10;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BASE__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BASE__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Test Array</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BASE__TEST_ARRAY = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Test Base Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BASE__TEST_BASE_PROPERTY = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Test Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BASE__TEST_REFERENCE = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Test Category Base</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BASE_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Test Category Base</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_BASE_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryExtendsImpl <em>Test Category Extends</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryExtendsImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryExtends()
	 * @generated
	 */
	int TEST_CATEGORY_EXTENDS = 11;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_EXTENDS__UUID = TEST_CATEGORY_BASE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_EXTENDS__NAME = TEST_CATEGORY_BASE__NAME;

	/**
	 * The feature id for the '<em><b>Test Array</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_EXTENDS__TEST_ARRAY = TEST_CATEGORY_BASE__TEST_ARRAY;

	/**
	 * The feature id for the '<em><b>Test Base Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_EXTENDS__TEST_BASE_PROPERTY = TEST_CATEGORY_BASE__TEST_BASE_PROPERTY;

	/**
	 * The feature id for the '<em><b>Test Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_EXTENDS__TEST_REFERENCE = TEST_CATEGORY_BASE__TEST_REFERENCE;

	/**
	 * The feature id for the '<em><b>Test Extends Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_EXTENDS__TEST_EXTENDS_PROPERTY = TEST_CATEGORY_BASE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Category Extends</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_EXTENDS_FEATURE_COUNT = TEST_CATEGORY_BASE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Test Category Extends</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CATEGORY_EXTENDS_OPERATION_COUNT = TEST_CATEGORY_BASE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestParameterImpl <em>Test Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestParameterImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestParameter()
	 * @generated
	 */
	int TEST_PARAMETER = 12;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER__DEFAULT_VALUE = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Test Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestMassParametersImpl <em>Test Mass Parameters</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestMassParametersImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestMassParameters()
	 * @generated
	 */
	int TEST_MASS_PARAMETERS = 13;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_MASS_PARAMETERS__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_MASS_PARAMETERS__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Mass</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_MASS_PARAMETERS__MASS = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Mass Parameters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_MASS_PARAMETERS_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Test Mass Parameters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_MASS_PARAMETERS_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCrossLinkedParametersWithCalculationImpl <em>Test Cross Linked Parameters With Calculation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCrossLinkedParametersWithCalculationImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCrossLinkedParametersWithCalculation()
	 * @generated
	 */
	int TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION = 14;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Calced Trl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__CALCED_TRL = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Cross Linked Parameters With Calculation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Test Cross Linked Parameters With Calculation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.EReferenceTestImpl <em>EReference Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.EReferenceTestImpl
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getEReferenceTest()
	 * @generated
	 */
	int EREFERENCE_TEST = 15;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_TEST__UUID = CorePackage.GENERIC_CATEGORY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_TEST__NAME = CorePackage.GENERIC_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>EReference Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_TEST__EREFERENCE_TEST = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EReference Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_TEST_FEATURE_COUNT = CorePackage.GENERIC_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>EReference Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EREFERENCE_TEST_OPERATION_COUNT = CorePackage.GENERIC_CATEGORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum <em>Enum Test Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getEnumTestEnum()
	 * @generated
	 */
	int ENUM_TEST_ENUM = 16;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty <em>Test Category All Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category All Property</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty
	 * @generated
	 */
	EClass getTestCategoryAllProperty();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestString <em>Test String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test String</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestString()
	 * @see #getTestCategoryAllProperty()
	 * @generated
	 */
	EAttribute getTestCategoryAllProperty_TestString();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestInt <em>Test Int</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Int</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestInt()
	 * @see #getTestCategoryAllProperty()
	 * @generated
	 */
	EAttribute getTestCategoryAllProperty_TestInt();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestFloat <em>Test Float</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Float</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestFloat()
	 * @see #getTestCategoryAllProperty()
	 * @generated
	 */
	EAttribute getTestCategoryAllProperty_TestFloat();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#isTestBool <em>Test Bool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Bool</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#isTestBool()
	 * @see #getTestCategoryAllProperty()
	 * @generated
	 */
	EAttribute getTestCategoryAllProperty_TestBool();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestResource <em>Test Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Resource</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestResource()
	 * @see #getTestCategoryAllProperty()
	 * @generated
	 */
	EAttribute getTestCategoryAllProperty_TestResource();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestEnum <em>Test Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Enum</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestEnum()
	 * @see #getTestCategoryAllProperty()
	 * @generated
	 */
	EAttribute getTestCategoryAllProperty_TestEnum();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition <em>Test Category Composition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Composition</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition
	 * @generated
	 */
	EClass getTestCategoryComposition();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition#getTestSubCategory <em>Test Sub Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Test Sub Category</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition#getTestSubCategory()
	 * @see #getTestCategoryComposition()
	 * @generated
	 */
	EReference getTestCategoryComposition_TestSubCategory();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference <em>Test Category Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Reference</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference
	 * @generated
	 */
	EClass getTestCategoryReference();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference#getTestRefCategory <em>Test Ref Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Test Ref Category</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference#getTestRefCategory()
	 * @see #getTestCategoryReference()
	 * @generated
	 */
	EReference getTestCategoryReference_TestRefCategory();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray <em>Test Category Intrinsic Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Intrinsic Array</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray
	 * @generated
	 */
	EClass getTestCategoryIntrinsicArray();

	/**
	 * Returns the meta object for the attribute list '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray#getTestStringArrayDynamic <em>Test String Array Dynamic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Test String Array Dynamic</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray#getTestStringArrayDynamic()
	 * @see #getTestCategoryIntrinsicArray()
	 * @generated
	 */
	EAttribute getTestCategoryIntrinsicArray_TestStringArrayDynamic();

	/**
	 * Returns the meta object for the attribute list '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray#getTestStringArrayStatic <em>Test String Array Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Test String Array Static</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray#getTestStringArrayStatic()
	 * @see #getTestCategoryIntrinsicArray()
	 * @generated
	 */
	EAttribute getTestCategoryIntrinsicArray_TestStringArrayStatic();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray <em>Test Category Composition Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Composition Array</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray
	 * @generated
	 */
	EClass getTestCategoryCompositionArray();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray#getTestCompositionArrayDynamic <em>Test Composition Array Dynamic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Composition Array Dynamic</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray#getTestCompositionArrayDynamic()
	 * @see #getTestCategoryCompositionArray()
	 * @generated
	 */
	EReference getTestCategoryCompositionArray_TestCompositionArrayDynamic();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray#getTestCompositionArrayStatic <em>Test Composition Array Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Composition Array Static</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray#getTestCompositionArrayStatic()
	 * @see #getTestCategoryCompositionArray()
	 * @generated
	 */
	EReference getTestCategoryCompositionArray_TestCompositionArrayStatic();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray <em>Test Category Reference Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Reference Array</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray
	 * @generated
	 */
	EClass getTestCategoryReferenceArray();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray#getTestCategoryReferenceArrayDynamic <em>Test Category Reference Array Dynamic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Test Category Reference Array Dynamic</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray#getTestCategoryReferenceArrayDynamic()
	 * @see #getTestCategoryReferenceArray()
	 * @generated
	 */
	EReference getTestCategoryReferenceArray_TestCategoryReferenceArrayDynamic();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray#getTestCategoryReferenceArrayStatic <em>Test Category Reference Array Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Test Category Reference Array Static</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray#getTestCategoryReferenceArrayStatic()
	 * @see #getTestCategoryReferenceArray()
	 * @generated
	 */
	EReference getTestCategoryReferenceArray_TestCategoryReferenceArrayStatic();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanA <em>Test Category Bean A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Bean A</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanA
	 * @generated
	 */
	EClass getTestCategoryBeanA();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanB <em>Test Category Bean B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Bean B</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanB
	 * @generated
	 */
	EClass getTestCategoryBeanB();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanAbstract <em>Test Category Bean Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Bean Abstract</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanAbstract
	 * @generated
	 */
	EClass getTestCategoryBeanAbstract();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanConcrete <em>Test Category Bean Concrete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Bean Concrete</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanConcrete
	 * @generated
	 */
	EClass getTestCategoryBeanConcrete();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase <em>Test Category Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Base</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase
	 * @generated
	 */
	EClass getTestCategoryBase();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestArray <em>Test Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Array</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestArray()
	 * @see #getTestCategoryBase()
	 * @generated
	 */
	EReference getTestCategoryBase_TestArray();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestBaseProperty <em>Test Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Base Property</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestBaseProperty()
	 * @see #getTestCategoryBase()
	 * @generated
	 */
	EAttribute getTestCategoryBase_TestBaseProperty();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestReference <em>Test Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Test Reference</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestReference()
	 * @see #getTestCategoryBase()
	 * @generated
	 */
	EReference getTestCategoryBase_TestReference();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryExtends <em>Test Category Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Category Extends</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryExtends
	 * @generated
	 */
	EClass getTestCategoryExtends();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryExtends#getTestExtendsProperty <em>Test Extends Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Extends Property</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryExtends#getTestExtendsProperty()
	 * @see #getTestCategoryExtends()
	 * @generated
	 */
	EAttribute getTestCategoryExtends_TestExtendsProperty();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestParameter <em>Test Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Parameter</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestParameter
	 * @generated
	 */
	EClass getTestParameter();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestParameter#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestParameter#getDefaultValue()
	 * @see #getTestParameter()
	 * @generated
	 */
	EAttribute getTestParameter_DefaultValue();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters <em>Test Mass Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Mass Parameters</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters
	 * @generated
	 */
	EClass getTestMassParameters();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters#getMass <em>Mass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mass</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters#getMass()
	 * @see #getTestMassParameters()
	 * @generated
	 */
	EReference getTestMassParameters_Mass();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCrossLinkedParametersWithCalculation <em>Test Cross Linked Parameters With Calculation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Cross Linked Parameters With Calculation</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCrossLinkedParametersWithCalculation
	 * @generated
	 */
	EClass getTestCrossLinkedParametersWithCalculation();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCrossLinkedParametersWithCalculation#getCalcedTrl <em>Calced Trl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Calced Trl</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCrossLinkedParametersWithCalculation#getCalcedTrl()
	 * @see #getTestCrossLinkedParametersWithCalculation()
	 * @generated
	 */
	EAttribute getTestCrossLinkedParametersWithCalculation_CalcedTrl();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest <em>EReference Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EReference Test</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest
	 * @generated
	 */
	EClass getEReferenceTest();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest#getEReferenceTest <em>EReference Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference Test</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest#getEReferenceTest()
	 * @see #getEReferenceTest()
	 * @generated
	 */
	EReference getEReferenceTest_EReferenceTest();

	/**
	 * Returns the meta object for enum '{@link de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum <em>Enum Test Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Enum Test Enum</em>'.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum
	 * @generated
	 */
	EEnum getEnumTestEnum();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestsFactory getTestsFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl <em>Test Category All Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryAllProperty()
		 * @generated
		 */
		EClass TEST_CATEGORY_ALL_PROPERTY = eINSTANCE.getTestCategoryAllProperty();

		/**
		 * The meta object literal for the '<em><b>Test String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_ALL_PROPERTY__TEST_STRING = eINSTANCE.getTestCategoryAllProperty_TestString();

		/**
		 * The meta object literal for the '<em><b>Test Int</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_ALL_PROPERTY__TEST_INT = eINSTANCE.getTestCategoryAllProperty_TestInt();

		/**
		 * The meta object literal for the '<em><b>Test Float</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_ALL_PROPERTY__TEST_FLOAT = eINSTANCE.getTestCategoryAllProperty_TestFloat();

		/**
		 * The meta object literal for the '<em><b>Test Bool</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_ALL_PROPERTY__TEST_BOOL = eINSTANCE.getTestCategoryAllProperty_TestBool();

		/**
		 * The meta object literal for the '<em><b>Test Resource</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_ALL_PROPERTY__TEST_RESOURCE = eINSTANCE.getTestCategoryAllProperty_TestResource();

		/**
		 * The meta object literal for the '<em><b>Test Enum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_ALL_PROPERTY__TEST_ENUM = eINSTANCE.getTestCategoryAllProperty_TestEnum();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionImpl <em>Test Category Composition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryComposition()
		 * @generated
		 */
		EClass TEST_CATEGORY_COMPOSITION = eINSTANCE.getTestCategoryComposition();

		/**
		 * The meta object literal for the '<em><b>Test Sub Category</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY = eINSTANCE.getTestCategoryComposition_TestSubCategory();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceImpl <em>Test Category Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryReference()
		 * @generated
		 */
		EClass TEST_CATEGORY_REFERENCE = eINSTANCE.getTestCategoryReference();

		/**
		 * The meta object literal for the '<em><b>Test Ref Category</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY = eINSTANCE.getTestCategoryReference_TestRefCategory();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryIntrinsicArrayImpl <em>Test Category Intrinsic Array</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryIntrinsicArrayImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryIntrinsicArray()
		 * @generated
		 */
		EClass TEST_CATEGORY_INTRINSIC_ARRAY = eINSTANCE.getTestCategoryIntrinsicArray();

		/**
		 * The meta object literal for the '<em><b>Test String Array Dynamic</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_DYNAMIC = eINSTANCE.getTestCategoryIntrinsicArray_TestStringArrayDynamic();

		/**
		 * The meta object literal for the '<em><b>Test String Array Static</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_STATIC = eINSTANCE.getTestCategoryIntrinsicArray_TestStringArrayStatic();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionArrayImpl <em>Test Category Composition Array</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionArrayImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryCompositionArray()
		 * @generated
		 */
		EClass TEST_CATEGORY_COMPOSITION_ARRAY = eINSTANCE.getTestCategoryCompositionArray();

		/**
		 * The meta object literal for the '<em><b>Test Composition Array Dynamic</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC = eINSTANCE.getTestCategoryCompositionArray_TestCompositionArrayDynamic();

		/**
		 * The meta object literal for the '<em><b>Test Composition Array Static</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC = eINSTANCE.getTestCategoryCompositionArray_TestCompositionArrayStatic();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceArrayImpl <em>Test Category Reference Array</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceArrayImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryReferenceArray()
		 * @generated
		 */
		EClass TEST_CATEGORY_REFERENCE_ARRAY = eINSTANCE.getTestCategoryReferenceArray();

		/**
		 * The meta object literal for the '<em><b>Test Category Reference Array Dynamic</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_DYNAMIC = eINSTANCE.getTestCategoryReferenceArray_TestCategoryReferenceArrayDynamic();

		/**
		 * The meta object literal for the '<em><b>Test Category Reference Array Static</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_STATIC = eINSTANCE.getTestCategoryReferenceArray_TestCategoryReferenceArrayStatic();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanAImpl <em>Test Category Bean A</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanAImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBeanA()
		 * @generated
		 */
		EClass TEST_CATEGORY_BEAN_A = eINSTANCE.getTestCategoryBeanA();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanBImpl <em>Test Category Bean B</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanBImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBeanB()
		 * @generated
		 */
		EClass TEST_CATEGORY_BEAN_B = eINSTANCE.getTestCategoryBeanB();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanAbstractImpl <em>Test Category Bean Abstract</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanAbstractImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBeanAbstract()
		 * @generated
		 */
		EClass TEST_CATEGORY_BEAN_ABSTRACT = eINSTANCE.getTestCategoryBeanAbstract();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanConcreteImpl <em>Test Category Bean Concrete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBeanConcreteImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBeanConcrete()
		 * @generated
		 */
		EClass TEST_CATEGORY_BEAN_CONCRETE = eINSTANCE.getTestCategoryBeanConcrete();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBaseImpl <em>Test Category Base</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBaseImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryBase()
		 * @generated
		 */
		EClass TEST_CATEGORY_BASE = eINSTANCE.getTestCategoryBase();

		/**
		 * The meta object literal for the '<em><b>Test Array</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CATEGORY_BASE__TEST_ARRAY = eINSTANCE.getTestCategoryBase_TestArray();

		/**
		 * The meta object literal for the '<em><b>Test Base Property</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_BASE__TEST_BASE_PROPERTY = eINSTANCE.getTestCategoryBase_TestBaseProperty();

		/**
		 * The meta object literal for the '<em><b>Test Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CATEGORY_BASE__TEST_REFERENCE = eINSTANCE.getTestCategoryBase_TestReference();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryExtendsImpl <em>Test Category Extends</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryExtendsImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCategoryExtends()
		 * @generated
		 */
		EClass TEST_CATEGORY_EXTENDS = eINSTANCE.getTestCategoryExtends();

		/**
		 * The meta object literal for the '<em><b>Test Extends Property</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CATEGORY_EXTENDS__TEST_EXTENDS_PROPERTY = eINSTANCE.getTestCategoryExtends_TestExtendsProperty();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestParameterImpl <em>Test Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestParameterImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestParameter()
		 * @generated
		 */
		EClass TEST_PARAMETER = eINSTANCE.getTestParameter();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_PARAMETER__DEFAULT_VALUE = eINSTANCE.getTestParameter_DefaultValue();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestMassParametersImpl <em>Test Mass Parameters</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestMassParametersImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestMassParameters()
		 * @generated
		 */
		EClass TEST_MASS_PARAMETERS = eINSTANCE.getTestMassParameters();

		/**
		 * The meta object literal for the '<em><b>Mass</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_MASS_PARAMETERS__MASS = eINSTANCE.getTestMassParameters_Mass();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCrossLinkedParametersWithCalculationImpl <em>Test Cross Linked Parameters With Calculation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCrossLinkedParametersWithCalculationImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getTestCrossLinkedParametersWithCalculation()
		 * @generated
		 */
		EClass TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION = eINSTANCE.getTestCrossLinkedParametersWithCalculation();

		/**
		 * The meta object literal for the '<em><b>Calced Trl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__CALCED_TRL = eINSTANCE.getTestCrossLinkedParametersWithCalculation_CalcedTrl();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.EReferenceTestImpl <em>EReference Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.EReferenceTestImpl
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getEReferenceTest()
		 * @generated
		 */
		EClass EREFERENCE_TEST = eINSTANCE.getEReferenceTest();

		/**
		 * The meta object literal for the '<em><b>EReference Test</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EREFERENCE_TEST__EREFERENCE_TEST = eINSTANCE.getEReferenceTest_EReferenceTest();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum <em>Enum Test Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum
		 * @see de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsPackageImpl#getEnumTestEnum()
		 * @generated
		 */
		EEnum ENUM_TEST_ENUM = eINSTANCE.getEnumTestEnum();

	}

} //TestsPackage
