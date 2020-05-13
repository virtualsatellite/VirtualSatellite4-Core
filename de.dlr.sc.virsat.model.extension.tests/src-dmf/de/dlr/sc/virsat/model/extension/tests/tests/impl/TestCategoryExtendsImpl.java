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

import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryExtends;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Category Extends</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryExtendsImpl#getTestExtendsProperty <em>Test Extends Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCategoryExtendsImpl extends TestCategoryBaseImpl implements TestCategoryExtends {
	/**
	 * The default value of the '{@link #getTestExtendsProperty() <em>Test Extends Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestExtendsProperty()
	 * @generated
	 * @ordered
	 */
	protected static final int TEST_EXTENDS_PROPERTY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTestExtendsProperty() <em>Test Extends Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestExtendsProperty()
	 * @generated
	 * @ordered
	 */
	protected int testExtendsProperty = TEST_EXTENDS_PROPERTY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCategoryExtendsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CATEGORY_EXTENDS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getTestExtendsProperty() {
		return testExtendsProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestExtendsProperty(int newTestExtendsProperty) {
		int oldTestExtendsProperty = testExtendsProperty;
		testExtendsProperty = newTestExtendsProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_EXTENDS__TEST_EXTENDS_PROPERTY, oldTestExtendsProperty, testExtendsProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_EXTENDS__TEST_EXTENDS_PROPERTY:
				return getTestExtendsProperty();
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
			case TestsPackage.TEST_CATEGORY_EXTENDS__TEST_EXTENDS_PROPERTY:
				setTestExtendsProperty((Integer)newValue);
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
			case TestsPackage.TEST_CATEGORY_EXTENDS__TEST_EXTENDS_PROPERTY:
				setTestExtendsProperty(TEST_EXTENDS_PROPERTY_EDEFAULT);
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
			case TestsPackage.TEST_CATEGORY_EXTENDS__TEST_EXTENDS_PROPERTY:
				return testExtendsProperty != TEST_EXTENDS_PROPERTY_EDEFAULT;
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
		result.append(" (testExtendsProperty: ");
		result.append(testExtendsProperty);
		result.append(')');
		return result.toString();
	}

} //TestCategoryExtendsImpl
