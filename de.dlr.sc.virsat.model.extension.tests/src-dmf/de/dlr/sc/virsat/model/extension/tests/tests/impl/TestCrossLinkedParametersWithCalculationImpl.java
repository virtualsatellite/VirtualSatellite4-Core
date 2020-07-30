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

import de.dlr.sc.virsat.model.extension.tests.tests.TestCrossLinkedParametersWithCalculation;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Cross Linked Parameters With Calculation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCrossLinkedParametersWithCalculationImpl#getCalcedTrl <em>Calced Trl</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCrossLinkedParametersWithCalculationImpl extends GenericCategoryImpl implements TestCrossLinkedParametersWithCalculation {
	/**
	 * The default value of the '{@link #getCalcedTrl() <em>Calced Trl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalcedTrl()
	 * @generated
	 * @ordered
	 */
	protected static final double CALCED_TRL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCalcedTrl() <em>Calced Trl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalcedTrl()
	 * @generated
	 * @ordered
	 */
	protected double calcedTrl = CALCED_TRL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCrossLinkedParametersWithCalculationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getCalcedTrl() {
		return calcedTrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCalcedTrl(double newCalcedTrl) {
		double oldCalcedTrl = calcedTrl;
		calcedTrl = newCalcedTrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__CALCED_TRL, oldCalcedTrl, calcedTrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__CALCED_TRL:
				return getCalcedTrl();
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
			case TestsPackage.TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__CALCED_TRL:
				setCalcedTrl((Double)newValue);
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
			case TestsPackage.TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__CALCED_TRL:
				setCalcedTrl(CALCED_TRL_EDEFAULT);
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
			case TestsPackage.TEST_CROSS_LINKED_PARAMETERS_WITH_CALCULATION__CALCED_TRL:
				return calcedTrl != CALCED_TRL_EDEFAULT;
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
		result.append(" (calcedTrl: ");
		result.append(calcedTrl);
		result.append(')');
		return result.toString();
	}

} //TestCrossLinkedParametersWithCalculationImpl
