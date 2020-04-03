/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.extension.tests.tests.util;

import de.dlr.sc.virsat.model.dvlm.dmf.DObject;

import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.ext.core.core.GenericCategory;

import de.dlr.sc.virsat.model.extension.tests.tests.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage
 * @generated
 */
public class TestsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TestsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsSwitch() {
		if (modelPackage == null) {
			modelPackage = TestsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY: {
				TestCategoryAllProperty testCategoryAllProperty = (TestCategoryAllProperty)theEObject;
				T result = caseTestCategoryAllProperty(testCategoryAllProperty);
				if (result == null) result = caseGenericCategory(testCategoryAllProperty);
				if (result == null) result = caseDObject(testCategoryAllProperty);
				if (result == null) result = caseIUuid(testCategoryAllProperty);
				if (result == null) result = caseIName(testCategoryAllProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_COMPOSITION: {
				TestCategoryComposition testCategoryComposition = (TestCategoryComposition)theEObject;
				T result = caseTestCategoryComposition(testCategoryComposition);
				if (result == null) result = caseGenericCategory(testCategoryComposition);
				if (result == null) result = caseDObject(testCategoryComposition);
				if (result == null) result = caseIUuid(testCategoryComposition);
				if (result == null) result = caseIName(testCategoryComposition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_REFERENCE: {
				TestCategoryReference testCategoryReference = (TestCategoryReference)theEObject;
				T result = caseTestCategoryReference(testCategoryReference);
				if (result == null) result = caseGenericCategory(testCategoryReference);
				if (result == null) result = caseDObject(testCategoryReference);
				if (result == null) result = caseIUuid(testCategoryReference);
				if (result == null) result = caseIName(testCategoryReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY: {
				TestCategoryIntrinsicArray testCategoryIntrinsicArray = (TestCategoryIntrinsicArray)theEObject;
				T result = caseTestCategoryIntrinsicArray(testCategoryIntrinsicArray);
				if (result == null) result = caseGenericCategory(testCategoryIntrinsicArray);
				if (result == null) result = caseDObject(testCategoryIntrinsicArray);
				if (result == null) result = caseIUuid(testCategoryIntrinsicArray);
				if (result == null) result = caseIName(testCategoryIntrinsicArray);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY: {
				TestCategoryCompositionArray testCategoryCompositionArray = (TestCategoryCompositionArray)theEObject;
				T result = caseTestCategoryCompositionArray(testCategoryCompositionArray);
				if (result == null) result = caseGenericCategory(testCategoryCompositionArray);
				if (result == null) result = caseDObject(testCategoryCompositionArray);
				if (result == null) result = caseIUuid(testCategoryCompositionArray);
				if (result == null) result = caseIName(testCategoryCompositionArray);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY: {
				TestCategoryReferenceArray testCategoryReferenceArray = (TestCategoryReferenceArray)theEObject;
				T result = caseTestCategoryReferenceArray(testCategoryReferenceArray);
				if (result == null) result = caseGenericCategory(testCategoryReferenceArray);
				if (result == null) result = caseDObject(testCategoryReferenceArray);
				if (result == null) result = caseIUuid(testCategoryReferenceArray);
				if (result == null) result = caseIName(testCategoryReferenceArray);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_BEAN_A: {
				TestCategoryBeanA testCategoryBeanA = (TestCategoryBeanA)theEObject;
				T result = caseTestCategoryBeanA(testCategoryBeanA);
				if (result == null) result = caseGenericCategory(testCategoryBeanA);
				if (result == null) result = caseDObject(testCategoryBeanA);
				if (result == null) result = caseIUuid(testCategoryBeanA);
				if (result == null) result = caseIName(testCategoryBeanA);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_BEAN_B: {
				TestCategoryBeanB testCategoryBeanB = (TestCategoryBeanB)theEObject;
				T result = caseTestCategoryBeanB(testCategoryBeanB);
				if (result == null) result = caseGenericCategory(testCategoryBeanB);
				if (result == null) result = caseDObject(testCategoryBeanB);
				if (result == null) result = caseIUuid(testCategoryBeanB);
				if (result == null) result = caseIName(testCategoryBeanB);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_BEAN_ABSTRACT: {
				TestCategoryBeanAbstract testCategoryBeanAbstract = (TestCategoryBeanAbstract)theEObject;
				T result = caseTestCategoryBeanAbstract(testCategoryBeanAbstract);
				if (result == null) result = caseGenericCategory(testCategoryBeanAbstract);
				if (result == null) result = caseDObject(testCategoryBeanAbstract);
				if (result == null) result = caseIUuid(testCategoryBeanAbstract);
				if (result == null) result = caseIName(testCategoryBeanAbstract);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_BEAN_CONCRETE: {
				TestCategoryBeanConcrete testCategoryBeanConcrete = (TestCategoryBeanConcrete)theEObject;
				T result = caseTestCategoryBeanConcrete(testCategoryBeanConcrete);
				if (result == null) result = caseTestCategoryBeanAbstract(testCategoryBeanConcrete);
				if (result == null) result = caseGenericCategory(testCategoryBeanConcrete);
				if (result == null) result = caseDObject(testCategoryBeanConcrete);
				if (result == null) result = caseIUuid(testCategoryBeanConcrete);
				if (result == null) result = caseIName(testCategoryBeanConcrete);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_BASE: {
				TestCategoryBase testCategoryBase = (TestCategoryBase)theEObject;
				T result = caseTestCategoryBase(testCategoryBase);
				if (result == null) result = caseGenericCategory(testCategoryBase);
				if (result == null) result = caseDObject(testCategoryBase);
				if (result == null) result = caseIUuid(testCategoryBase);
				if (result == null) result = caseIName(testCategoryBase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CATEGORY_EXTENDS: {
				TestCategoryExtends testCategoryExtends = (TestCategoryExtends)theEObject;
				T result = caseTestCategoryExtends(testCategoryExtends);
				if (result == null) result = caseTestCategoryBase(testCategoryExtends);
				if (result == null) result = caseGenericCategory(testCategoryExtends);
				if (result == null) result = caseDObject(testCategoryExtends);
				if (result == null) result = caseIUuid(testCategoryExtends);
				if (result == null) result = caseIName(testCategoryExtends);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_PARAMETER: {
				TestParameter testParameter = (TestParameter)theEObject;
				T result = caseTestParameter(testParameter);
				if (result == null) result = caseGenericCategory(testParameter);
				if (result == null) result = caseDObject(testParameter);
				if (result == null) result = caseIUuid(testParameter);
				if (result == null) result = caseIName(testParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_MASS_PARAMETERS: {
				TestMassParameters testMassParameters = (TestMassParameters)theEObject;
				T result = caseTestMassParameters(testMassParameters);
				if (result == null) result = caseGenericCategory(testMassParameters);
				if (result == null) result = caseDObject(testMassParameters);
				if (result == null) result = caseIUuid(testMassParameters);
				if (result == null) result = caseIName(testMassParameters);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION: {
				TestCrossLinkedParametersWithCalculation testCrossLinkedParametersWithCalculation = (TestCrossLinkedParametersWithCalculation)theEObject;
				T result = caseTestCrossLinkedParametersWithCalculation(testCrossLinkedParametersWithCalculation);
				if (result == null) result = caseGenericCategory(testCrossLinkedParametersWithCalculation);
				if (result == null) result = caseDObject(testCrossLinkedParametersWithCalculation);
				if (result == null) result = caseIUuid(testCrossLinkedParametersWithCalculation);
				if (result == null) result = caseIName(testCrossLinkedParametersWithCalculation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestsPackage.EREFERENCE_TEST: {
				EReferenceTest eReferenceTest = (EReferenceTest)theEObject;
				T result = caseEReferenceTest(eReferenceTest);
				if (result == null) result = caseGenericCategory(eReferenceTest);
				if (result == null) result = caseDObject(eReferenceTest);
				if (result == null) result = caseIUuid(eReferenceTest);
				if (result == null) result = caseIName(eReferenceTest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category All Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category All Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryAllProperty(TestCategoryAllProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Composition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Composition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryComposition(TestCategoryComposition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryReference(TestCategoryReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Intrinsic Array</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Intrinsic Array</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryIntrinsicArray(TestCategoryIntrinsicArray object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Composition Array</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Composition Array</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryCompositionArray(TestCategoryCompositionArray object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Reference Array</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Reference Array</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryReferenceArray(TestCategoryReferenceArray object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Bean A</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Bean A</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryBeanA(TestCategoryBeanA object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Bean B</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Bean B</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryBeanB(TestCategoryBeanB object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Bean Abstract</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Bean Abstract</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryBeanAbstract(TestCategoryBeanAbstract object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Bean Concrete</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Bean Concrete</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryBeanConcrete(TestCategoryBeanConcrete object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Base</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Base</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryBase(TestCategoryBase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Category Extends</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Category Extends</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCategoryExtends(TestCategoryExtends object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestParameter(TestParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Mass Parameters</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Mass Parameters</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestMassParameters(TestMassParameters object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Cross Linked Parameters With Calculation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Cross Linked Parameters With Calculation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCrossLinkedParametersWithCalculation(TestCrossLinkedParametersWithCalculation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EReference Test</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EReference Test</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEReferenceTest(EReferenceTest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IUuid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IUuid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIUuid(IUuid object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IName</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IName</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIName(IName object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDObject(DObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericCategory(GenericCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //TestsSwitch
