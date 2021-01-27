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


import de.dlr.sc.virsat.model.dvlm.categories.Category;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composed Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ComposedPropertyImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ComposedPropertyImpl#getQuantityKindName <em>Quantity Kind Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.ComposedPropertyImpl#getUnitName <em>Unit Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComposedPropertyImpl extends APropertyImpl implements ComposedProperty {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Category type;

	/**
	 * The default value of the '{@link #getQuantityKindName() <em>Quantity Kind Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantityKindName()
	 * @generated
	 * @ordered
	 */
	protected static final String QUANTITY_KIND_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQuantityKindName() <em>Quantity Kind Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantityKindName()
	 * @generated
	 * @ordered
	 */
	protected String quantityKindName = QUANTITY_KIND_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnitName() <em>Unit Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnitName() <em>Unit Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitName()
	 * @generated
	 * @ordered
	 */
	protected String unitName = UNIT_NAME_EDEFAULT;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedPropertyImpl() {
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
		return PropertydefinitionsPackage.Literals.COMPOSED_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Category getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Category)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertydefinitionsPackage.COMPOSED_PROPERTY__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code Category}'.
	 * @generated
	 */
	public Category basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Category newType) {
		Category oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.COMPOSED_PROPERTY__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQuantityKindName() {
		return quantityKindName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantityKindName(String newQuantityKindName) {
		String oldQuantityKindName = quantityKindName;
		quantityKindName = newQuantityKindName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.COMPOSED_PROPERTY__QUANTITY_KIND_NAME, oldQuantityKindName, quantityKindName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnitName(String newUnitName) {
		String oldUnitName = unitName;
		unitName = newUnitName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.COMPOSED_PROPERTY__UNIT_NAME, oldUnitName, unitName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__QUANTITY_KIND_NAME:
				return getQuantityKindName();
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__UNIT_NAME:
				return getUnitName();
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
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__TYPE:
				setType((Category)newValue);
				return;
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__QUANTITY_KIND_NAME:
				setQuantityKindName((String)newValue);
				return;
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__UNIT_NAME:
				setUnitName((String)newValue);
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
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__TYPE:
				setType((Category)null);
				return;
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__QUANTITY_KIND_NAME:
				setQuantityKindName(QUANTITY_KIND_NAME_EDEFAULT);
				return;
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__UNIT_NAME:
				setUnitName(UNIT_NAME_EDEFAULT);
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
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__TYPE:
				return type != null;
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__QUANTITY_KIND_NAME:
				return QUANTITY_KIND_NAME_EDEFAULT == null ? quantityKindName != null : !QUANTITY_KIND_NAME_EDEFAULT.equals(quantityKindName);
			case PropertydefinitionsPackage.COMPOSED_PROPERTY__UNIT_NAME:
				return UNIT_NAME_EDEFAULT == null ? unitName != null : !UNIT_NAME_EDEFAULT.equals(unitName);
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
		result.append(" (quantityKindName: ");
		result.append(quantityKindName);
		result.append(", unitName: ");
		result.append(unitName);
		result.append(')');
		return result.toString();
	}

} //ComposedPropertyImpl
