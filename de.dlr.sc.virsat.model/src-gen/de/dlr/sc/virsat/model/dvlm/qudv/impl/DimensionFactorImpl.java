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


import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dimension Factor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionFactorImpl#getExponent <em>Exponent</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionFactorImpl#getBaseDimension <em>Base Dimension</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DimensionFactorImpl extends MinimalEObjectImpl.Container implements DimensionFactor {
	/**
	 * The default value of the '{@link #getExponent() <em>Exponent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExponent()
	 * @generated
	 * @ordered
	 */
	protected static final double EXPONENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getExponent() <em>Exponent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExponent()
	 * @generated
	 * @ordered
	 */
	protected double exponent = EXPONENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBaseDimension() <em>Base Dimension</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseDimension()
	 * @generated
	 * @ordered
	 */
	protected AQuantityKind baseDimension;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DimensionFactorImpl() {
		super();
		
	}
	
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * This method handles unresolved references into otehr resources.
	 * When ever a proxy object in the DVLM model is resolved
	 * This method will check the object and place an error to the resource
	 * if it cannot be resolved 
	 * @generated
	 */
	@Override
	public EObject eResolveProxy(InternalEObject proxy) {
		EObject resolvedProxy = super.eResolveProxy(proxy);
		return DVLMUnresolvedReferenceException.checkProxyObject(resolvedProxy, proxy, this.eResource());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QudvPackage.Literals.DIMENSION_FACTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getExponent() {
		return exponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExponent(double newExponent) {
		double oldExponent = exponent;
		exponent = newExponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.DIMENSION_FACTOR__EXPONENT, oldExponent, exponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AQuantityKind getBaseDimension() {
		if (baseDimension != null && baseDimension.eIsProxy()) {
			InternalEObject oldBaseDimension = (InternalEObject)baseDimension;
			baseDimension = (AQuantityKind)eResolveProxy(oldBaseDimension);
			if (baseDimension != oldBaseDimension) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QudvPackage.DIMENSION_FACTOR__BASE_DIMENSION, oldBaseDimension, baseDimension));
			}
		}
		return baseDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AQuantityKind basicGetBaseDimension() {
		return baseDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseDimension(AQuantityKind newBaseDimension) {
		AQuantityKind oldBaseDimension = baseDimension;
		baseDimension = newBaseDimension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.DIMENSION_FACTOR__BASE_DIMENSION, oldBaseDimension, baseDimension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QudvPackage.DIMENSION_FACTOR__EXPONENT:
				return getExponent();
			case QudvPackage.DIMENSION_FACTOR__BASE_DIMENSION:
				if (resolve) return getBaseDimension();
				return basicGetBaseDimension();
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
			case QudvPackage.DIMENSION_FACTOR__EXPONENT:
				setExponent((Double)newValue);
				return;
			case QudvPackage.DIMENSION_FACTOR__BASE_DIMENSION:
				setBaseDimension((AQuantityKind)newValue);
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
			case QudvPackage.DIMENSION_FACTOR__EXPONENT:
				setExponent(EXPONENT_EDEFAULT);
				return;
			case QudvPackage.DIMENSION_FACTOR__BASE_DIMENSION:
				setBaseDimension((AQuantityKind)null);
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
			case QudvPackage.DIMENSION_FACTOR__EXPONENT:
				return exponent != EXPONENT_EDEFAULT;
			case QudvPackage.DIMENSION_FACTOR__BASE_DIMENSION:
				return baseDimension != null;
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
		result.append(" (exponent: ");
		result.append(exponent);
		result.append(')');
		return result.toString();
	}

} //DimensionFactorImpl
