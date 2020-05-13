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
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Category Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryReferenceImpl#getTestRefCategory <em>Test Ref Category</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCategoryReferenceImpl extends GenericCategoryImpl implements TestCategoryReference {
	/**
	 * The cached value of the '{@link #getTestRefCategory() <em>Test Ref Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestRefCategory()
	 * @generated
	 * @ordered
	 */
	protected TestCategoryAllProperty testRefCategory;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCategoryReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CATEGORY_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryAllProperty getTestRefCategory() {
		if (testRefCategory != null && testRefCategory.eIsProxy()) {
			InternalEObject oldTestRefCategory = (InternalEObject)testRefCategory;
			testRefCategory = (TestCategoryAllProperty)eResolveProxy(oldTestRefCategory);
			if (testRefCategory != oldTestRefCategory) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestsPackage.TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY, oldTestRefCategory, testRefCategory));
			}
		}
		return testRefCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCategoryAllProperty basicGetTestRefCategory() {
		return testRefCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestRefCategory(TestCategoryAllProperty newTestRefCategory) {
		TestCategoryAllProperty oldTestRefCategory = testRefCategory;
		testRefCategory = newTestRefCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY, oldTestRefCategory, testRefCategory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY:
				if (resolve) return getTestRefCategory();
				return basicGetTestRefCategory();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY:
				setTestRefCategory((TestCategoryAllProperty)newValue);
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
			case TestsPackage.TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY:
				setTestRefCategory((TestCategoryAllProperty)null);
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
			case TestsPackage.TEST_CATEGORY_REFERENCE__TEST_REF_CATEGORY:
				return testRefCategory != null;
		}
		return super.eIsSet(featureID);
	}

} //TestCategoryReferenceImpl
