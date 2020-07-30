/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.extension.tests.tests.impl;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;

import de.dlr.sc.virsat.model.ext.core.core.CorePackage;

import de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanAbstract;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanB;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanConcrete;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryExtends;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCrossLinkedParametersWithCalculation;
import de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters;
import de.dlr.sc.virsat.model.extension.tests.tests.TestParameter;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsFactory;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestsPackageImpl extends EPackageImpl implements TestsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryAllPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryCompositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryIntrinsicArrayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryCompositionArrayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryReferenceArrayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryBeanAEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryBeanBEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryBeanAbstractEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryBeanConcreteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryBaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCategoryExtendsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testMassParametersEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCrossLinkedParametersWithCalculationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eReferenceTestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum enumTestEnumEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TestsPackageImpl() {
		super(eNS_URI, TestsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link TestsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TestsPackage init() {
		if (isInited) return (TestsPackage)EPackage.Registry.INSTANCE.getEPackage(TestsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredTestsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		TestsPackageImpl theTestsPackage = registeredTestsPackage instanceof TestsPackageImpl ? (TestsPackageImpl)registeredTestsPackage : new TestsPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		DVLMPackage.eINSTANCE.eClass();
		CorePackage.eINSTANCE.eClass();
		de.dlr.sc.virsat.model.external.tests.TestsPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theTestsPackage.createPackageContents();

		// Initialize created meta-data
		theTestsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTestsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TestsPackage.eNS_URI, theTestsPackage);
		return theTestsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryAllProperty() {
		return testCategoryAllPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryAllProperty_TestString() {
		return (EAttribute)testCategoryAllPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryAllProperty_TestInt() {
		return (EAttribute)testCategoryAllPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryAllProperty_TestFloat() {
		return (EAttribute)testCategoryAllPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryAllProperty_TestBool() {
		return (EAttribute)testCategoryAllPropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryAllProperty_TestResource() {
		return (EAttribute)testCategoryAllPropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryAllProperty_TestEnum() {
		return (EAttribute)testCategoryAllPropertyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryComposition() {
		return testCategoryCompositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestCategoryComposition_TestSubCategory() {
		return (EReference)testCategoryCompositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryReference() {
		return testCategoryReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestCategoryReference_TestRefCategory() {
		return (EReference)testCategoryReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryIntrinsicArray() {
		return testCategoryIntrinsicArrayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryIntrinsicArray_TestStringArrayDynamic() {
		return (EAttribute)testCategoryIntrinsicArrayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryIntrinsicArray_TestStringArrayStatic() {
		return (EAttribute)testCategoryIntrinsicArrayEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryCompositionArray() {
		return testCategoryCompositionArrayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestCategoryCompositionArray_TestCompositionArrayDynamic() {
		return (EReference)testCategoryCompositionArrayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestCategoryCompositionArray_TestCompositionArrayStatic() {
		return (EReference)testCategoryCompositionArrayEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryReferenceArray() {
		return testCategoryReferenceArrayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestCategoryReferenceArray_TestCategoryReferenceArrayDynamic() {
		return (EReference)testCategoryReferenceArrayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestCategoryReferenceArray_TestCategoryReferenceArrayStatic() {
		return (EReference)testCategoryReferenceArrayEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryBeanA() {
		return testCategoryBeanAEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryBeanB() {
		return testCategoryBeanBEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryBeanAbstract() {
		return testCategoryBeanAbstractEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryBeanConcrete() {
		return testCategoryBeanConcreteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryBase() {
		return testCategoryBaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestCategoryBase_TestArray() {
		return (EReference)testCategoryBaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryBase_TestBaseProperty() {
		return (EAttribute)testCategoryBaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestCategoryBase_TestReference() {
		return (EReference)testCategoryBaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCategoryExtends() {
		return testCategoryExtendsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCategoryExtends_TestExtendsProperty() {
		return (EAttribute)testCategoryExtendsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestParameter() {
		return testParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestParameter_DefaultValue() {
		return (EAttribute)testParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestMassParameters() {
		return testMassParametersEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestMassParameters_Mass() {
		return (EReference)testMassParametersEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCrossLinkedParametersWithCalculation() {
		return testCrossLinkedParametersWithCalculationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCrossLinkedParametersWithCalculation_CalcedTrl() {
		return (EAttribute)testCrossLinkedParametersWithCalculationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEReferenceTest() {
		return eReferenceTestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEReferenceTest_EReferenceTest() {
		return (EReference)eReferenceTestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getEnumTestEnum() {
		return enumTestEnumEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestsFactory getTestsFactory() {
		return (TestsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		testCategoryAllPropertyEClass = createEClass(TEST_CATEGORY_ALL_PROPERTY);
		createEAttribute(testCategoryAllPropertyEClass, TEST_CATEGORY_ALL_PROPERTY__TEST_STRING);
		createEAttribute(testCategoryAllPropertyEClass, TEST_CATEGORY_ALL_PROPERTY__TEST_INT);
		createEAttribute(testCategoryAllPropertyEClass, TEST_CATEGORY_ALL_PROPERTY__TEST_FLOAT);
		createEAttribute(testCategoryAllPropertyEClass, TEST_CATEGORY_ALL_PROPERTY__TEST_BOOL);
		createEAttribute(testCategoryAllPropertyEClass, TEST_CATEGORY_ALL_PROPERTY__TEST_RESOURCE);
		createEAttribute(testCategoryAllPropertyEClass, TEST_CATEGORY_ALL_PROPERTY__TEST_ENUM);

		testCategoryCompositionEClass = createEClass(TEST_CATEGORY_COMPOSITION);
		createEReference(testCategoryCompositionEClass, TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY);

		testCategoryReferenceEClass = createEClass(TEST_CATEGORY_REFERENCE);
		createEReference(testCategoryReferenceEClass, TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY);

		testCategoryIntrinsicArrayEClass = createEClass(TEST_CATEGORY_INTRINSIC_ARRAY);
		createEAttribute(testCategoryIntrinsicArrayEClass, TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_DYNAMIC);
		createEAttribute(testCategoryIntrinsicArrayEClass, TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_STATIC);

		testCategoryCompositionArrayEClass = createEClass(TEST_CATEGORY_COMPOSITION_ARRAY);
		createEReference(testCategoryCompositionArrayEClass, TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC);
		createEReference(testCategoryCompositionArrayEClass, TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC);

		testCategoryReferenceArrayEClass = createEClass(TEST_CATEGORY_REFERENCE_ARRAY);
		createEReference(testCategoryReferenceArrayEClass, TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_DYNAMIC);
		createEReference(testCategoryReferenceArrayEClass, TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_STATIC);

		testCategoryBeanAEClass = createEClass(TEST_CATEGORY_BEAN_A);

		testCategoryBeanBEClass = createEClass(TEST_CATEGORY_BEAN_B);

		testCategoryBeanAbstractEClass = createEClass(TEST_CATEGORY_BEAN_ABSTRACT);

		testCategoryBeanConcreteEClass = createEClass(TEST_CATEGORY_BEAN_CONCRETE);

		testCategoryBaseEClass = createEClass(TEST_CATEGORY_BASE);
		createEReference(testCategoryBaseEClass, TEST_CATEGORY_BASE__TEST_ARRAY);
		createEAttribute(testCategoryBaseEClass, TEST_CATEGORY_BASE__TEST_BASE_PROPERTY);
		createEReference(testCategoryBaseEClass, TEST_CATEGORY_BASE__TEST_REFERENCE);

		testCategoryExtendsEClass = createEClass(TEST_CATEGORY_EXTENDS);
		createEAttribute(testCategoryExtendsEClass, TEST_CATEGORY_EXTENDS__TEST_EXTENDS_PROPERTY);

		testParameterEClass = createEClass(TEST_PARAMETER);
		createEAttribute(testParameterEClass, TEST_PARAMETER__DEFAULT_VALUE);

		testMassParametersEClass = createEClass(TEST_MASS_PARAMETERS);
		createEReference(testMassParametersEClass, TEST_MASS_PARAMETERS__MASS);

		testCrossLinkedParametersWithCalculationEClass = createEClass(TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION);
		createEAttribute(testCrossLinkedParametersWithCalculationEClass, TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__CALCED_TRL);

		eReferenceTestEClass = createEClass(EREFERENCE_TEST);
		createEReference(eReferenceTestEClass, EREFERENCE_TEST__EREFERENCE_TEST);

		// Create enums
		enumTestEnumEEnum = createEEnum(ENUM_TEST_ENUM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		de.dlr.sc.virsat.model.external.tests.TestsPackage theTestsPackage_1 = (de.dlr.sc.virsat.model.external.tests.TestsPackage)EPackage.Registry.INSTANCE.getEPackage(de.dlr.sc.virsat.model.external.tests.TestsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		testCategoryAllPropertyEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryCompositionEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryReferenceEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryIntrinsicArrayEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryCompositionArrayEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryReferenceArrayEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryBeanAEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryBeanBEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryBeanAbstractEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryBeanConcreteEClass.getESuperTypes().add(this.getTestCategoryBeanAbstract());
		testCategoryBaseEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCategoryExtendsEClass.getESuperTypes().add(this.getTestCategoryBase());
		testParameterEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testMassParametersEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		testCrossLinkedParametersWithCalculationEClass.getESuperTypes().add(theCorePackage.getGenericCategory());
		eReferenceTestEClass.getESuperTypes().add(theCorePackage.getGenericCategory());

		// Initialize classes, features, and operations; add parameters
		initEClass(testCategoryAllPropertyEClass, TestCategoryAllProperty.class, "TestCategoryAllProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCategoryAllProperty_TestString(), ecorePackage.getEString(), "testString", null, 0, 1, TestCategoryAllProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCategoryAllProperty_TestInt(), ecorePackage.getEInt(), "testInt", null, 0, 1, TestCategoryAllProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCategoryAllProperty_TestFloat(), ecorePackage.getEDouble(), "testFloat", null, 0, 1, TestCategoryAllProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCategoryAllProperty_TestBool(), ecorePackage.getEBoolean(), "testBool", null, 0, 1, TestCategoryAllProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCategoryAllProperty_TestResource(), ecorePackage.getEString(), "testResource", null, 0, 1, TestCategoryAllProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCategoryAllProperty_TestEnum(), this.getEnumTestEnum(), "testEnum", null, 0, 1, TestCategoryAllProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCategoryCompositionEClass, TestCategoryComposition.class, "TestCategoryComposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCategoryComposition_TestSubCategory(), this.getTestCategoryAllProperty(), null, "testSubCategory", null, 0, 1, TestCategoryComposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCategoryReferenceEClass, TestCategoryReference.class, "TestCategoryReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCategoryReference_TestRefCategory(), this.getTestCategoryAllProperty(), null, "testRefCategory", null, 0, 1, TestCategoryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCategoryIntrinsicArrayEClass, TestCategoryIntrinsicArray.class, "TestCategoryIntrinsicArray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCategoryIntrinsicArray_TestStringArrayDynamic(), ecorePackage.getEString(), "testStringArrayDynamic", null, 0, -1, TestCategoryIntrinsicArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCategoryIntrinsicArray_TestStringArrayStatic(), ecorePackage.getEString(), "testStringArrayStatic", null, 0, 4, TestCategoryIntrinsicArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCategoryCompositionArrayEClass, TestCategoryCompositionArray.class, "TestCategoryCompositionArray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCategoryCompositionArray_TestCompositionArrayDynamic(), this.getTestCategoryAllProperty(), null, "testCompositionArrayDynamic", null, 0, -1, TestCategoryCompositionArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCategoryCompositionArray_TestCompositionArrayStatic(), this.getTestCategoryAllProperty(), null, "testCompositionArrayStatic", null, 0, 4, TestCategoryCompositionArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCategoryReferenceArrayEClass, TestCategoryReferenceArray.class, "TestCategoryReferenceArray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCategoryReferenceArray_TestCategoryReferenceArrayDynamic(), this.getTestCategoryAllProperty(), null, "testCategoryReferenceArrayDynamic", null, 0, -1, TestCategoryReferenceArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCategoryReferenceArray_TestCategoryReferenceArrayStatic(), this.getTestCategoryAllProperty(), null, "testCategoryReferenceArrayStatic", null, 0, 4, TestCategoryReferenceArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCategoryBeanAEClass, TestCategoryBeanA.class, "TestCategoryBeanA", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testCategoryBeanBEClass, TestCategoryBeanB.class, "TestCategoryBeanB", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testCategoryBeanAbstractEClass, TestCategoryBeanAbstract.class, "TestCategoryBeanAbstract", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testCategoryBeanConcreteEClass, TestCategoryBeanConcrete.class, "TestCategoryBeanConcrete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testCategoryBaseEClass, TestCategoryBase.class, "TestCategoryBase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCategoryBase_TestArray(), this.getTestCategoryBase(), null, "testArray", null, 0, -1, TestCategoryBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCategoryBase_TestBaseProperty(), ecorePackage.getEInt(), "testBaseProperty", null, 0, 1, TestCategoryBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCategoryBase_TestReference(), this.getTestCategoryBase(), null, "testReference", null, 0, 1, TestCategoryBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCategoryExtendsEClass, TestCategoryExtends.class, "TestCategoryExtends", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCategoryExtends_TestExtendsProperty(), ecorePackage.getEInt(), "testExtendsProperty", null, 0, 1, TestCategoryExtends.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testParameterEClass, TestParameter.class, "TestParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestParameter_DefaultValue(), ecorePackage.getEDouble(), "defaultValue", null, 0, 1, TestParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testMassParametersEClass, TestMassParameters.class, "TestMassParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestMassParameters_Mass(), this.getTestParameter(), null, "mass", null, 0, 1, TestMassParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCrossLinkedParametersWithCalculationEClass, TestCrossLinkedParametersWithCalculation.class, "TestCrossLinkedParametersWithCalculation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCrossLinkedParametersWithCalculation_CalcedTrl(), ecorePackage.getEDouble(), "calcedTrl", null, 0, 1, TestCrossLinkedParametersWithCalculation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eReferenceTestEClass, EReferenceTest.class, "EReferenceTest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEReferenceTest_EReferenceTest(), theTestsPackage_1.getExternalTestType(), null, "eReferenceTest", null, 0, 1, EReferenceTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(enumTestEnumEEnum, EnumTestEnum.class, "EnumTestEnum");
		addEEnumLiteral(enumTestEnumEEnum, EnumTestEnum.LOW);
		addEEnumLiteral(enumTestEnumEEnum, EnumTestEnum.MEDIUM);
		addEEnumLiteral(enumTestEnumEEnum, EnumTestEnum.HIGH);
		addEEnumLiteral(enumTestEnumEEnum, EnumTestEnum.INCREDIBLE);

		// Create resource
		createResource(eNS_URI);
	}

} //TestsPackageImpl
