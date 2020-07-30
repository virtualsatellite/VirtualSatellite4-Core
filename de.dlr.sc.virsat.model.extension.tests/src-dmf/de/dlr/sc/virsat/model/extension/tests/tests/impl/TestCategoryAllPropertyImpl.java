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

import de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Category All Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl#getTestString <em>Test String</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl#getTestInt <em>Test Int</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl#getTestFloat <em>Test Float</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl#isTestBool <em>Test Bool</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl#getTestResource <em>Test Resource</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryAllPropertyImpl#getTestEnum <em>Test Enum</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCategoryAllPropertyImpl extends GenericCategoryImpl implements TestCategoryAllProperty {
	/**
	 * The default value of the '{@link #getTestString() <em>Test String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestString()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestString() <em>Test String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestString()
	 * @generated
	 * @ordered
	 */
	protected String testString = TEST_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestInt() <em>Test Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestInt()
	 * @generated
	 * @ordered
	 */
	protected static final int TEST_INT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTestInt() <em>Test Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestInt()
	 * @generated
	 * @ordered
	 */
	protected int testInt = TEST_INT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestFloat() <em>Test Float</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestFloat()
	 * @generated
	 * @ordered
	 */
	protected static final double TEST_FLOAT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTestFloat() <em>Test Float</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestFloat()
	 * @generated
	 * @ordered
	 */
	protected double testFloat = TEST_FLOAT_EDEFAULT;

	/**
	 * The default value of the '{@link #isTestBool() <em>Test Bool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTestBool()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TEST_BOOL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTestBool() <em>Test Bool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTestBool()
	 * @generated
	 * @ordered
	 */
	protected boolean testBool = TEST_BOOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestResource() <em>Test Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestResource()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_RESOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestResource() <em>Test Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestResource()
	 * @generated
	 * @ordered
	 */
	protected String testResource = TEST_RESOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestEnum() <em>Test Enum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestEnum()
	 * @generated
	 * @ordered
	 */
	protected static final EnumTestEnum TEST_ENUM_EDEFAULT = EnumTestEnum.LOW;

	/**
	 * The cached value of the '{@link #getTestEnum() <em>Test Enum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestEnum()
	 * @generated
	 * @ordered
	 */
	protected EnumTestEnum testEnum = TEST_ENUM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCategoryAllPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CATEGORY_ALL_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTestString() {
		return testString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestString(String newTestString) {
		String oldTestString = testString;
		testString = newTestString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_STRING, oldTestString, testString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getTestInt() {
		return testInt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestInt(int newTestInt) {
		int oldTestInt = testInt;
		testInt = newTestInt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_INT, oldTestInt, testInt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getTestFloat() {
		return testFloat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestFloat(double newTestFloat) {
		double oldTestFloat = testFloat;
		testFloat = newTestFloat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_FLOAT, oldTestFloat, testFloat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTestBool() {
		return testBool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestBool(boolean newTestBool) {
		boolean oldTestBool = testBool;
		testBool = newTestBool;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_BOOL, oldTestBool, testBool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTestResource() {
		return testResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestResource(String newTestResource) {
		String oldTestResource = testResource;
		testResource = newTestResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_RESOURCE, oldTestResource, testResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnumTestEnum getTestEnum() {
		return testEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestEnum(EnumTestEnum newTestEnum) {
		EnumTestEnum oldTestEnum = testEnum;
		testEnum = newTestEnum == null ? TEST_ENUM_EDEFAULT : newTestEnum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_ENUM, oldTestEnum, testEnum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_STRING:
				return getTestString();
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_INT:
				return getTestInt();
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_FLOAT:
				return getTestFloat();
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_BOOL:
				return isTestBool();
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_RESOURCE:
				return getTestResource();
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_ENUM:
				return getTestEnum();
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
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_STRING:
				setTestString((String)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_INT:
				setTestInt((Integer)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_FLOAT:
				setTestFloat((Double)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_BOOL:
				setTestBool((Boolean)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_RESOURCE:
				setTestResource((String)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_ENUM:
				setTestEnum((EnumTestEnum)newValue);
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
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_STRING:
				setTestString(TEST_STRING_EDEFAULT);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_INT:
				setTestInt(TEST_INT_EDEFAULT);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_FLOAT:
				setTestFloat(TEST_FLOAT_EDEFAULT);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_BOOL:
				setTestBool(TEST_BOOL_EDEFAULT);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_RESOURCE:
				setTestResource(TEST_RESOURCE_EDEFAULT);
				return;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_ENUM:
				setTestEnum(TEST_ENUM_EDEFAULT);
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
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_STRING:
				return TEST_STRING_EDEFAULT == null ? testString != null : !TEST_STRING_EDEFAULT.equals(testString);
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_INT:
				return testInt != TEST_INT_EDEFAULT;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_FLOAT:
				return testFloat != TEST_FLOAT_EDEFAULT;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_BOOL:
				return testBool != TEST_BOOL_EDEFAULT;
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_RESOURCE:
				return TEST_RESOURCE_EDEFAULT == null ? testResource != null : !TEST_RESOURCE_EDEFAULT.equals(testResource);
			case TestsPackage.TEST_CATEGORY_ALL_PROPERTY__TEST_ENUM:
				return testEnum != TEST_ENUM_EDEFAULT;
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
		result.append(" (testString: ");
		result.append(testString);
		result.append(", testInt: ");
		result.append(testInt);
		result.append(", testFloat: ");
		result.append(testFloat);
		result.append(", testBool: ");
		result.append(testBool);
		result.append(", testResource: ");
		result.append(testResource);
		result.append(", testEnum: ");
		result.append(testEnum);
		result.append(')');
		return result.toString();
	}

} //TestCategoryAllPropertyImpl
