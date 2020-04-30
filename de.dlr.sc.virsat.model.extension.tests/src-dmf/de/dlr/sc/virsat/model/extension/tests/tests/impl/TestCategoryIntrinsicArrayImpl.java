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

import de.dlr.sc.virsat.model.ext.core.core.impl.GenericCategoryImpl;

import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Category Intrinsic Array</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryIntrinsicArrayImpl#getTestStringArrayDynamic <em>Test String Array Dynamic</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryIntrinsicArrayImpl#getTestStringArrayStatic <em>Test String Array Static</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCategoryIntrinsicArrayImpl extends GenericCategoryImpl implements TestCategoryIntrinsicArray {
	/**
	 * The cached value of the '{@link #getTestStringArrayDynamic() <em>Test String Array Dynamic</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestStringArrayDynamic()
	 * @generated
	 * @ordered
	 */
	protected EList<String> testStringArrayDynamic;

	/**
	 * The cached value of the '{@link #getTestStringArrayStatic() <em>Test String Array Static</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestStringArrayStatic()
	 * @generated
	 * @ordered
	 */
	protected EList<String> testStringArrayStatic;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCategoryIntrinsicArrayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CATEGORY_INTRINSIC_ARRAY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getTestStringArrayDynamic() {
		if (testStringArrayDynamic == null) {
			testStringArrayDynamic = new EDataTypeUniqueEList<String>(String.class, this, TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_DYNAMIC);
		}
		return testStringArrayDynamic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getTestStringArrayStatic() {
		if (testStringArrayStatic == null) {
			testStringArrayStatic = new EDataTypeUniqueEList<String>(String.class, this, TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_STATIC);
		}
		return testStringArrayStatic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_DYNAMIC:
				return getTestStringArrayDynamic();
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_STATIC:
				return getTestStringArrayStatic();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_DYNAMIC:
				getTestStringArrayDynamic().clear();
				getTestStringArrayDynamic().addAll((Collection<? extends String>)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_STATIC:
				getTestStringArrayStatic().clear();
				getTestStringArrayStatic().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_DYNAMIC:
				getTestStringArrayDynamic().clear();
				return;
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_STATIC:
				getTestStringArrayStatic().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_DYNAMIC:
				return testStringArrayDynamic != null && !testStringArrayDynamic.isEmpty();
			case TestsPackage.TEST_CATEGORY_INTRINSIC_ARRAY__TEST_STRING_ARRAY_STATIC:
				return testStringArrayStatic != null && !testStringArrayStatic.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (testStringArrayDynamic: ");
		result.append(testStringArrayDynamic);
		result.append(", testStringArrayStatic: ");
		result.append(testStringArrayStatic);
		result.append(')');
		return result.toString();
	}

} //TestCategoryIntrinsicArrayImpl
