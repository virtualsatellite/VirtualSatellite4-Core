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

import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Category Base</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBaseImpl#getTestArray <em>Test Array</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBaseImpl#getTestBaseProperty <em>Test Base Property</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryBaseImpl#getTestReference <em>Test Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCategoryBaseImpl extends GenericCategoryImpl implements TestCategoryBase {
	/**
	 * The cached value of the '{@link #getTestArray() <em>Test Array</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestArray()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCategoryBase> testArray;

	/**
	 * The default value of the '{@link #getTestBaseProperty() <em>Test Base Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestBaseProperty()
	 * @generated
	 * @ordered
	 */
	protected static final int TEST_BASE_PROPERTY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTestBaseProperty() <em>Test Base Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestBaseProperty()
	 * @generated
	 * @ordered
	 */
	protected int testBaseProperty = TEST_BASE_PROPERTY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTestReference() <em>Test Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestReference()
	 * @generated
	 * @ordered
	 */
	protected TestCategoryBase testReference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCategoryBaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CATEGORY_BASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TestCategoryBase> getTestArray() {
		if (testArray == null) {
			testArray = new EObjectContainmentEList<TestCategoryBase>(TestCategoryBase.class, this, TestsPackage.TEST_CATEGORY_BASE__TEST_ARRAY);
		}
		return testArray;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getTestBaseProperty() {
		return testBaseProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestBaseProperty(int newTestBaseProperty) {
		int oldTestBaseProperty = testBaseProperty;
		testBaseProperty = newTestBaseProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_BASE__TEST_BASE_PROPERTY, oldTestBaseProperty, testBaseProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCategoryBase getTestReference() {
		if (testReference != null && testReference.eIsProxy()) {
			InternalEObject oldTestReference = (InternalEObject)testReference;
			testReference = (TestCategoryBase)eResolveProxy(oldTestReference);
			if (testReference != oldTestReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestsPackage.TEST_CATEGORY_BASE__TEST_REFERENCE, oldTestReference, testReference));
			}
		}
		return testReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCategoryBase basicGetTestReference() {
		return testReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestReference(TestCategoryBase newTestReference) {
		TestCategoryBase oldTestReference = testReference;
		testReference = newTestReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CATEGORY_BASE__TEST_REFERENCE, oldTestReference, testReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_BASE__TEST_ARRAY:
				return ((InternalEList<?>)getTestArray()).basicRemove(otherEnd, msgs);
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
			case TestsPackage.TEST_CATEGORY_BASE__TEST_ARRAY:
				return getTestArray();
			case TestsPackage.TEST_CATEGORY_BASE__TEST_BASE_PROPERTY:
				return getTestBaseProperty();
			case TestsPackage.TEST_CATEGORY_BASE__TEST_REFERENCE:
				if (resolve) return getTestReference();
				return basicGetTestReference();
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
			case TestsPackage.TEST_CATEGORY_BASE__TEST_ARRAY:
				getTestArray().clear();
				getTestArray().addAll((Collection<? extends TestCategoryBase>)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_BASE__TEST_BASE_PROPERTY:
				setTestBaseProperty((Integer)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_BASE__TEST_REFERENCE:
				setTestReference((TestCategoryBase)newValue);
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
			case TestsPackage.TEST_CATEGORY_BASE__TEST_ARRAY:
				getTestArray().clear();
				return;
			case TestsPackage.TEST_CATEGORY_BASE__TEST_BASE_PROPERTY:
				setTestBaseProperty(TEST_BASE_PROPERTY_EDEFAULT);
				return;
			case TestsPackage.TEST_CATEGORY_BASE__TEST_REFERENCE:
				setTestReference((TestCategoryBase)null);
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
			case TestsPackage.TEST_CATEGORY_BASE__TEST_ARRAY:
				return testArray != null && !testArray.isEmpty();
			case TestsPackage.TEST_CATEGORY_BASE__TEST_BASE_PROPERTY:
				return testBaseProperty != TEST_BASE_PROPERTY_EDEFAULT;
			case TestsPackage.TEST_CATEGORY_BASE__TEST_REFERENCE:
				return testReference != null;
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
		result.append(" (testBaseProperty: ");
		result.append(testBaseProperty);
		result.append(')');
		return result.toString();
	}

} //TestCategoryBaseImpl
