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

import de.dlr.sc.virsat.model.extension.tests.tests.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestsFactoryImpl extends EFactoryImpl implements TestsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TestsFactory init() {
		try {
			TestsFactory theTestsFactory = (TestsFactory)EPackage.Registry.INSTANCE.getEFactory(TestsPackage.eNS_URI);
			if (theTestsFactory != null) {
				return theTestsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TestsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY: return createTestCategoryAllProperty();
			case TestsPackage.TEST_CATEGORY_COMPOSITION: return createTestCategoryComposition();
			case TestsPackage.TEST_CATEGORY_REFERENCE: return createTestCategoryReference();
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY: return createTestCategoryIntrinsicArray();
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY: return createTestCategoryCompositionArray();
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY: return createTestCategoryReferenceArray();
			case TestsPackage.TEST_CATEGORY_BEAN_A: return createTestCategoryBeanA();
			case TestsPackage.TEST_CATEGORY_BEAN_B: return createTestCategoryBeanB();
			case TestsPackage.TEST_CATEGORY_BEAN_CONCRETE: return createTestCategoryBeanConcrete();
			case TestsPackage.TEST_CATEGORY_BASE: return createTestCategoryBase();
			case TestsPackage.TEST_CATEGORY_EXTENDS: return createTestCategoryExtends();
			case TestsPackage.TEST_PARAMETER: return createTestParameter();
			case TestsPackage.TEST_MASS_PARAMETERS: return createTestMassParameters();
			case TestsPackage.TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION: return createTestCrossLinkedParametersWithCalculation();
			case TestsPackage.EREFERENCE_TEST: return createEReferenceTest();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TestsPackage.ENUM_TEST_ENUM:
				return createEnumTestEnumFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TestsPackage.ENUM_TEST_ENUM:
				return convertEnumTestEnumToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryAllProperty createTestCategoryAllProperty() {
		TestCategoryAllPropertyImpl testCategoryAllProperty = new TestCategoryAllPropertyImpl();
		return testCategoryAllProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryComposition createTestCategoryComposition() {
		TestCategoryCompositionImpl testCategoryComposition = new TestCategoryCompositionImpl();
		return testCategoryComposition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryReference createTestCategoryReference() {
		TestCategoryReferenceImpl testCategoryReference = new TestCategoryReferenceImpl();
		return testCategoryReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryIntrinsicArray createTestCategoryIntrinsicArray() {
		TestCategoryIntrinsicArrayImpl testCategoryIntrinsicArray = new TestCategoryIntrinsicArrayImpl();
		return testCategoryIntrinsicArray;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryCompositionArray createTestCategoryCompositionArray() {
		TestCategoryCompositionArrayImpl testCategoryCompositionArray = new TestCategoryCompositionArrayImpl();
		return testCategoryCompositionArray;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryReferenceArray createTestCategoryReferenceArray() {
		TestCategoryReferenceArrayImpl testCategoryReferenceArray = new TestCategoryReferenceArrayImpl();
		return testCategoryReferenceArray;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryBeanA createTestCategoryBeanA() {
		TestCategoryBeanAImpl testCategoryBeanA = new TestCategoryBeanAImpl();
		return testCategoryBeanA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryBeanB createTestCategoryBeanB() {
		TestCategoryBeanBImpl testCategoryBeanB = new TestCategoryBeanBImpl();
		return testCategoryBeanB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryBeanConcrete createTestCategoryBeanConcrete() {
		TestCategoryBeanConcreteImpl testCategoryBeanConcrete = new TestCategoryBeanConcreteImpl();
		return testCategoryBeanConcrete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryBase createTestCategoryBase() {
		TestCategoryBaseImpl testCategoryBase = new TestCategoryBaseImpl();
		return testCategoryBase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryExtends createTestCategoryExtends() {
		TestCategoryExtendsImpl testCategoryExtends = new TestCategoryExtendsImpl();
		return testCategoryExtends;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestParameter createTestParameter() {
		TestParameterImpl testParameter = new TestParameterImpl();
		return testParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestMassParameters createTestMassParameters() {
		TestMassParametersImpl testMassParameters = new TestMassParametersImpl();
		return testMassParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCrossLinkedParametersWithCalculation createTestCrossLinkedParametersWithCalculation() {
		TestCrossLinkedParametersWithCalculationImpl testCrossLinkedParametersWithCalculation = new TestCrossLinkedParametersWithCalculationImpl();
		return testCrossLinkedParametersWithCalculation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReferenceTest createEReferenceTest() {
		EReferenceTestImpl eReferenceTest = new EReferenceTestImpl();
		return eReferenceTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumTestEnum createEnumTestEnumFromString(EDataType eDataType, String initialValue) {
		EnumTestEnum result = EnumTestEnum.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnumTestEnumToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestsPackage getTestsPackage() {
		return (TestsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TestsPackage getPackage() {
		return TestsPackage.eINSTANCE;
	}

} //TestsFactoryImpl
