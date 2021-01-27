/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl;


import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composed Property Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ComposedPropertyInstanceImpl#getTypeInstance <em>Type Instance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComposedPropertyInstanceImpl extends APropertyInstanceImpl implements ComposedPropertyInstance {
	/**
	 * The cached value of the '{@link #getTypeInstance() <em>Type Instance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeInstance()
	 * @generated
	 * @ordered
	 */
	protected CategoryAssignment typeInstance;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedPropertyInstanceImpl() {
		super();
		this.uuid = new de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid(); 
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
		return PropertyinstancesPackage.Literals.COMPOSED_PROPERTY_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CategoryAssignment getTypeInstance() {
		if (typeInstance != null && typeInstance.eIsProxy()) {
			InternalEObject oldTypeInstance = (InternalEObject)typeInstance;
			typeInstance = (CategoryAssignment)eResolveProxy(oldTypeInstance);
			if (typeInstance != oldTypeInstance) {
				InternalEObject newTypeInstance = (InternalEObject)typeInstance;
				NotificationChain msgs = oldTypeInstance.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE, null, null);
				if (newTypeInstance.eInternalContainer() == null) {
					msgs = newTypeInstance.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE, oldTypeInstance, typeInstance));
			}
		}
		return typeInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code CategoryAssignment}'.
	 * @generated
	 */
	public CategoryAssignment basicGetTypeInstance() {
		return typeInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newTypeInstance new value to be of type '{@code CategoryAssignment}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetTypeInstance(CategoryAssignment newTypeInstance, NotificationChain msgs) {
		CategoryAssignment oldTypeInstance = typeInstance;
		typeInstance = newTypeInstance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE, oldTypeInstance, newTypeInstance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeInstance(CategoryAssignment newTypeInstance) {
		if (newTypeInstance != typeInstance) {
			NotificationChain msgs = null;
			if (typeInstance != null)
				msgs = ((InternalEObject)typeInstance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE, null, msgs);
			if (newTypeInstance != null)
				msgs = ((InternalEObject)newTypeInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE, null, msgs);
			msgs = basicSetTypeInstance(newTypeInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE, newTypeInstance, newTypeInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE:
				return basicSetTypeInstance(null, msgs);
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
			case PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE:
				if (resolve) return getTypeInstance();
				return basicGetTypeInstance();
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
			case PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE:
				setTypeInstance((CategoryAssignment)newValue);
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
			case PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE:
				setTypeInstance((CategoryAssignment)null);
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
			case PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE:
				return typeInstance != null;
		}
		return super.eIsSet(featureID);
	}

} //ComposedPropertyInstanceImpl
