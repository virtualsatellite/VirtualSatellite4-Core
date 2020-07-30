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
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Category Composition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionImpl#getTestSubCategory <em>Test Sub Category</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCategoryCompositionImpl extends GenericCategoryImpl implements TestCategoryComposition {
	/**
	 * The cached value of the '{@link #getTestSubCategory() <em>Test Sub Category</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestSubCategory()
	 * @generated
	 * @ordered
	 */
	protected TestCategoryAllProperty testSubCategory;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCategoryCompositionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CATEGORY_COMPOSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryAllProperty getTestSubCategory() {
		return testSubCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTestSubCategory(TestCategoryAllProperty newTestSubCategory, NotificationChain msgs) {
		TestCategoryAllProperty oldTestSubCategory = testSubCategory;
		testSubCategory = newTestSubCategory;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY, oldTestSubCategory, newTestSubCategory);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestSubCategory(TestCategoryAllProperty newTestSubCategory) {
		if (newTestSubCategory != testSubCategory) {
			NotificationChain msgs = null;
			if (testSubCategory != null)
				msgs = ((InternalEObject)testSubCategory).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY, null, msgs);
			if (newTestSubCategory != null)
				msgs = ((InternalEObject)newTestSubCategory).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY, null, msgs);
			msgs = basicSetTestSubCategory(newTestSubCategory, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY, newTestSubCategory, newTestSubCategory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY:
				return basicSetTestSubCategory(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY:
				return getTestSubCategory();
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
			case TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY:
				setTestSubCategory((TestCategoryAllProperty)newValue);
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
			case TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY:
				setTestSubCategory((TestCategoryAllProperty)null);
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
			case TestsPackage.TEST_CATEGORY_COMPOSITION__TEST_SUB_CATEGORY:
				return testSubCategory != null;
		}
		return super.eIsSet(featureID);
	}

} //TestCategoryCompositionImpl
