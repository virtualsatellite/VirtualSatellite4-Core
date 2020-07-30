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

import de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import de.dlr.sc.virsat.model.external.tests.ExternalTestType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EReference Test</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.EReferenceTestImpl#getEReferenceTest <em>EReference Test</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EReferenceTestImpl extends GenericCategoryImpl implements EReferenceTest {
	/**
	 * The cached value of the '{@link #getEReferenceTest() <em>EReference Test</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReferenceTest()
	 * @generated
	 * @ordered
	 */
	protected ExternalTestType eReferenceTest;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EReferenceTestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.EREFERENCE_TEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExternalTestType getEReferenceTest() {
		if (eReferenceTest != null && eReferenceTest.eIsProxy()) {
			InternalEObject oldEReferenceTest = (InternalEObject)eReferenceTest;
			eReferenceTest = (ExternalTestType)eResolveProxy(oldEReferenceTest);
			if (eReferenceTest != oldEReferenceTest) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestsPackage.EREFERENCE_TEST__EREFERENCE_TEST, oldEReferenceTest, eReferenceTest));
			}
		}
		return eReferenceTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalTestType basicGetEReferenceTest() {
		return eReferenceTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEReferenceTest(ExternalTestType newEReferenceTest) {
		ExternalTestType oldEReferenceTest = eReferenceTest;
		eReferenceTest = newEReferenceTest;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.EREFERENCE_TEST__EREFERENCE_TEST, oldEReferenceTest, eReferenceTest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.EREFERENCE_TEST__EREFERENCE_TEST:
				if (resolve) return getEReferenceTest();
				return basicGetEReferenceTest();
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
			case TestsPackage.EREFERENCE_TEST__EREFERENCE_TEST:
				setEReferenceTest((ExternalTestType)newValue);
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
			case TestsPackage.EREFERENCE_TEST__EREFERENCE_TEST:
				setEReferenceTest((ExternalTestType)null);
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
			case TestsPackage.EREFERENCE_TEST__EREFERENCE_TEST:
				return eReferenceTest != null;
		}
		return super.eIsSet(featureID);
	}

} //EReferenceTestImpl
