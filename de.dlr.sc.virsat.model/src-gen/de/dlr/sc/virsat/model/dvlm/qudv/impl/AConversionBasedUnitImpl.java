/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.impl;


import de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AConversion Based Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AConversionBasedUnitImpl#isIsInvertible <em>Is Invertible</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AConversionBasedUnitImpl#getReferenceUnit <em>Reference Unit</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AConversionBasedUnitImpl extends AUnitImpl implements AConversionBasedUnit {
	/**
	 * The default value of the '{@link #isIsInvertible() <em>Is Invertible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInvertible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INVERTIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsInvertible() <em>Is Invertible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInvertible()
	 * @generated
	 * @ordered
	 */
	protected boolean isInvertible = IS_INVERTIBLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferenceUnit() <em>Reference Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceUnit()
	 * @generated
	 * @ordered
	 */
	protected AUnit referenceUnit;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AConversionBasedUnitImpl() {
		super();
		this.uuid = new de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid(); 
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QudvPackage.Literals.ACONVERSION_BASED_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsInvertible() {
		return isInvertible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInvertible(boolean newIsInvertible) {
		boolean oldIsInvertible = isInvertible;
		isInvertible = newIsInvertible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.ACONVERSION_BASED_UNIT__IS_INVERTIBLE, oldIsInvertible, isInvertible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AUnit getReferenceUnit() {
		if (referenceUnit != null && referenceUnit.eIsProxy()) {
			InternalEObject oldReferenceUnit = (InternalEObject)referenceUnit;
			referenceUnit = (AUnit)eResolveProxy(oldReferenceUnit);
			if (referenceUnit != oldReferenceUnit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QudvPackage.ACONVERSION_BASED_UNIT__REFERENCE_UNIT, oldReferenceUnit, referenceUnit));
			}
		}
		return referenceUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AUnit basicGetReferenceUnit() {
		return referenceUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceUnit(AUnit newReferenceUnit) {
		AUnit oldReferenceUnit = referenceUnit;
		referenceUnit = newReferenceUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.ACONVERSION_BASED_UNIT__REFERENCE_UNIT, oldReferenceUnit, referenceUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QudvPackage.ACONVERSION_BASED_UNIT__IS_INVERTIBLE:
				return isIsInvertible();
			case QudvPackage.ACONVERSION_BASED_UNIT__REFERENCE_UNIT:
				if (resolve) return getReferenceUnit();
				return basicGetReferenceUnit();
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
			case QudvPackage.ACONVERSION_BASED_UNIT__IS_INVERTIBLE:
				setIsInvertible((Boolean)newValue);
				return;
			case QudvPackage.ACONVERSION_BASED_UNIT__REFERENCE_UNIT:
				setReferenceUnit((AUnit)newValue);
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
			case QudvPackage.ACONVERSION_BASED_UNIT__IS_INVERTIBLE:
				setIsInvertible(IS_INVERTIBLE_EDEFAULT);
				return;
			case QudvPackage.ACONVERSION_BASED_UNIT__REFERENCE_UNIT:
				setReferenceUnit((AUnit)null);
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
			case QudvPackage.ACONVERSION_BASED_UNIT__IS_INVERTIBLE:
				return isInvertible != IS_INVERTIBLE_EDEFAULT;
			case QudvPackage.ACONVERSION_BASED_UNIT__REFERENCE_UNIT:
				return referenceUnit != null;
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isInvertible: ");
		result.append(isInvertible);
		result.append(')');
		return result.toString();
	}

} //AConversionBasedUnitImpl
