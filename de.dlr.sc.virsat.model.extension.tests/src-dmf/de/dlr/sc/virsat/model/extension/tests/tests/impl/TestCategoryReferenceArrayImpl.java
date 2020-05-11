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

import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Category Reference Array</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceArrayImpl#getTestCategoryReferenceArrayDynamic <em>Test Category Reference Array Dynamic</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceArrayImpl#getTestCategoryReferenceArrayStatic <em>Test Category Reference Array Static</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCategoryReferenceArrayImpl extends GenericCategoryImpl implements TestCategoryReferenceArray {
	/**
	 * The cached value of the '{@link #getTestCategoryReferenceArrayDynamic() <em>Test Category Reference Array Dynamic</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestCategoryReferenceArrayDynamic()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCategoryAllProperty> testCategoryReferenceArrayDynamic;

	/**
	 * The cached value of the '{@link #getTestCategoryReferenceArrayStatic() <em>Test Category Reference Array Static</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestCategoryReferenceArrayStatic()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCategoryAllProperty> testCategoryReferenceArrayStatic;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCategoryReferenceArrayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CATEGORY_REFERENCE_ARRAY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TestCategoryAllProperty> getTestCategoryReferenceArrayDynamic() {
		if (testCategoryReferenceArrayDynamic == null) {
			testCategoryReferenceArrayDynamic = new EObjectResolvingEList<TestCategoryAllProperty>(TestCategoryAllProperty.class, this, TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_DYNAMIC);
		}
		return testCategoryReferenceArrayDynamic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TestCategoryAllProperty> getTestCategoryReferenceArrayStatic() {
		if (testCategoryReferenceArrayStatic == null) {
			testCategoryReferenceArrayStatic = new EObjectResolvingEList<TestCategoryAllProperty>(TestCategoryAllProperty.class, this, TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_STATIC);
		}
		return testCategoryReferenceArrayStatic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_DYNAMIC:
				return getTestCategoryReferenceArrayDynamic();
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_STATIC:
				return getTestCategoryReferenceArrayStatic();
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
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_DYNAMIC:
				getTestCategoryReferenceArrayDynamic().clear();
				getTestCategoryReferenceArrayDynamic().addAll((Collection<? extends TestCategoryAllProperty>)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_STATIC:
				getTestCategoryReferenceArrayStatic().clear();
				getTestCategoryReferenceArrayStatic().addAll((Collection<? extends TestCategoryAllProperty>)newValue);
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
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_DYNAMIC:
				getTestCategoryReferenceArrayDynamic().clear();
				return;
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_STATIC:
				getTestCategoryReferenceArrayStatic().clear();
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
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_DYNAMIC:
				return testCategoryReferenceArrayDynamic != null && !testCategoryReferenceArrayDynamic.isEmpty();
			case TestsPackage.TEST_CATEGORY_REFERENCE_ARRAY__TEST_CATEGORY_REFERENCE_ARRAY_STATIC:
				return testCategoryReferenceArrayStatic != null && !testCategoryReferenceArrayStatic.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestCategoryReferenceArrayImpl
