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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage
 * @generated
 */
public interface TestsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestsFactory eINSTANCE = de.dlr.sc.virsat.model.extension.tests.tests.impl.TestsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Test Category All Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category All Property</em>'.
	 * @generated
	 */
	TestCategoryAllProperty createTestCategoryAllProperty();

	/**
	 * Returns a new object of class '<em>Test Category Composition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Composition</em>'.
	 * @generated
	 */
	TestCategoryComposition createTestCategoryComposition();

	/**
	 * Returns a new object of class '<em>Test Category Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Reference</em>'.
	 * @generated
	 */
	TestCategoryReference createTestCategoryReference();

	/**
	 * Returns a new object of class '<em>Test Category Intrinsic Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Intrinsic Array</em>'.
	 * @generated
	 */
	TestCategoryIntrinsicArray createTestCategoryIntrinsicArray();

	/**
	 * Returns a new object of class '<em>Test Category Composition Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Composition Array</em>'.
	 * @generated
	 */
	TestCategoryCompositionArray createTestCategoryCompositionArray();

	/**
	 * Returns a new object of class '<em>Test Category Reference Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Reference Array</em>'.
	 * @generated
	 */
	TestCategoryReferenceArray createTestCategoryReferenceArray();

	/**
	 * Returns a new object of class '<em>Test Category Bean A</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Bean A</em>'.
	 * @generated
	 */
	TestCategoryBeanA createTestCategoryBeanA();

	/**
	 * Returns a new object of class '<em>Test Category Bean B</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Bean B</em>'.
	 * @generated
	 */
	TestCategoryBeanB createTestCategoryBeanB();

	/**
	 * Returns a new object of class '<em>Test Category Bean Concrete</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Bean Concrete</em>'.
	 * @generated
	 */
	TestCategoryBeanConcrete createTestCategoryBeanConcrete();

	/**
	 * Returns a new object of class '<em>Test Category Base</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Base</em>'.
	 * @generated
	 */
	TestCategoryBase createTestCategoryBase();

	/**
	 * Returns a new object of class '<em>Test Category Extends</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Category Extends</em>'.
	 * @generated
	 */
	TestCategoryExtends createTestCategoryExtends();

	/**
	 * Returns a new object of class '<em>Test Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Parameter</em>'.
	 * @generated
	 */
	TestParameter createTestParameter();

	/**
	 * Returns a new object of class '<em>Test Mass Parameters</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Mass Parameters</em>'.
	 * @generated
	 */
	TestMassParameters createTestMassParameters();

	/**
	 * Returns a new object of class '<em>Test Cross Linked Parameters With Calculation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Cross Linked Parameters With Calculation</em>'.
	 * @generated
	 */
	TestCrossLinkedParametersWithCalculation createTestCrossLinkedParametersWithCalculation();

	/**
	 * Returns a new object of class '<em>EReference Test</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EReference Test</em>'.
	 * @generated
	 */
	EReferenceTest createEReferenceTest();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TestsPackage getTestsPackage();

} //TestsFactory
