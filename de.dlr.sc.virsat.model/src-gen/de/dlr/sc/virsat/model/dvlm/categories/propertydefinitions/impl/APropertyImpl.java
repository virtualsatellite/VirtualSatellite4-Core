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
	 * @generated
	 */
	public IArrayModifier basicGetArrayModifier() {
		return arrayModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER:
				return basicSetArrayModifier(null, msgs);
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
		}
		return super.eIsSet(featureID);
	}

} //APropertyImpl
