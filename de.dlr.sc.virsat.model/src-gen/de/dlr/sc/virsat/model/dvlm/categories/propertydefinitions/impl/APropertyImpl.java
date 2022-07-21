/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl;


import de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeDefinitionImpl;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IVerificationSpecification;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AProperty</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.APropertyImpl#getArrayModifier <em>Array Modifier</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.APropertyImpl#getVerification <em>Verification</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class APropertyImpl extends ATypeDefinitionImpl implements AProperty {
	/**
	 * The cached value of the '{@link #getArrayModifier() <em>Array Modifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayModifier()
	 * @generated
	 * @ordered
	 */
	protected IArrayModifier arrayModifier;

	/**
	 * The cached value of the '{@link #getVerification() <em>Verification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerification()
	 * @generated
	 * @ordered
	 */
	protected IVerificationSpecification verification;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected APropertyImpl() {
		super();
		
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PropertydefinitionsPackage.Literals.APROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code IArrayModifier}'.
	 * @generated
	 */
	public IArrayModifier getArrayModifier() {
		if (arrayModifier != null && arrayModifier.eIsProxy()) {
			InternalEObject oldArrayModifier = (InternalEObject)arrayModifier;
			arrayModifier = (IArrayModifier)eResolveProxy(oldArrayModifier);
			if (arrayModifier != oldArrayModifier) {
				InternalEObject newArrayModifier = (InternalEObject)arrayModifier;
				NotificationChain msgs = oldArrayModifier.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER, null, null);
				if (newArrayModifier.eInternalContainer() == null) {
					msgs = newArrayModifier.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER, oldArrayModifier, arrayModifier));
			}
		}
		return arrayModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code IArrayModifier}'.
	 * @generated
	 */
	public IArrayModifier basicGetArrayModifier() {
		return arrayModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newArrayModifier new value to be of type '{@code IArrayModifier}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetArrayModifier(IArrayModifier newArrayModifier, NotificationChain msgs) {
		IArrayModifier oldArrayModifier = arrayModifier;
		arrayModifier = newArrayModifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER, oldArrayModifier, newArrayModifier);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayModifier(IArrayModifier newArrayModifier) {
		if (newArrayModifier != arrayModifier) {
			NotificationChain msgs = null;
			if (arrayModifier != null)
				msgs = ((InternalEObject)arrayModifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER, null, msgs);
			if (newArrayModifier != null)
				msgs = ((InternalEObject)newArrayModifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER, null, msgs);
			msgs = basicSetArrayModifier(newArrayModifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER, newArrayModifier, newArrayModifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code IVerificationSpecification}'.
	 * @generated
	 */
	public IVerificationSpecification getVerification() {
		if (verification != null && verification.eIsProxy()) {
			InternalEObject oldVerification = (InternalEObject)verification;
			verification = (IVerificationSpecification)eResolveProxy(oldVerification);
			if (verification != oldVerification) {
				InternalEObject newVerification = (InternalEObject)verification;
				NotificationChain msgs = oldVerification.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.APROPERTY__VERIFICATION, null, null);
				if (newVerification.eInternalContainer() == null) {
					msgs = newVerification.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.APROPERTY__VERIFICATION, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertydefinitionsPackage.APROPERTY__VERIFICATION, oldVerification, verification));
			}
		}
		return verification;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code IVerificationSpecification}'.
	 * @generated
	 */
	public IVerificationSpecification basicGetVerification() {
		return verification;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newVerification new value to be of type '{@code IVerificationSpecification}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetVerification(IVerificationSpecification newVerification, NotificationChain msgs) {
		IVerificationSpecification oldVerification = verification;
		verification = newVerification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.APROPERTY__VERIFICATION, oldVerification, newVerification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerification(IVerificationSpecification newVerification) {
		if (newVerification != verification) {
			NotificationChain msgs = null;
			if (verification != null)
				msgs = ((InternalEObject)verification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.APROPERTY__VERIFICATION, null, msgs);
			if (newVerification != null)
				msgs = ((InternalEObject)newVerification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.APROPERTY__VERIFICATION, null, msgs);
			msgs = basicSetVerification(newVerification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.APROPERTY__VERIFICATION, newVerification, newVerification));
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER:
				return basicSetArrayModifier(null, msgs);
			case PropertydefinitionsPackage.APROPERTY__VERIFICATION:
				return basicSetVerification(null, msgs);
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
			case PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER:
				if (resolve) return getArrayModifier();
				return basicGetArrayModifier();
			case PropertydefinitionsPackage.APROPERTY__VERIFICATION:
				if (resolve) return getVerification();
				return basicGetVerification();
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
			case PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER:
				setArrayModifier((IArrayModifier)newValue);
				return;
			case PropertydefinitionsPackage.APROPERTY__VERIFICATION:
				setVerification((IVerificationSpecification)newValue);
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
			case PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER:
				setArrayModifier((IArrayModifier)null);
				return;
			case PropertydefinitionsPackage.APROPERTY__VERIFICATION:
				setVerification((IVerificationSpecification)null);
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
			case PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER:
				return arrayModifier != null;
			case PropertydefinitionsPackage.APROPERTY__VERIFICATION:
				return verification != null;
		}
		return super.eIsSet(featureID);
	}

} //APropertyImpl
