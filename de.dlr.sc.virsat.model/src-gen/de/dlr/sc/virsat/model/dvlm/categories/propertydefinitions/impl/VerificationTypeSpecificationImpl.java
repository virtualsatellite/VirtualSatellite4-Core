/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl;


import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.VerificationTypeSpecification;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Verification Type Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.VerificationTypeSpecificationImpl#getVerificationType <em>Verification Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VerificationTypeSpecificationImpl extends IVerificationSpecificationImpl implements VerificationTypeSpecification {
	/**
	 * The cached value of the '{@link #getVerificationType() <em>Verification Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerificationType()
	 * @generated
	 * @ordered
	 */
	protected ATypeDefinition verificationType;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VerificationTypeSpecificationImpl() {
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
		return PropertydefinitionsPackage.Literals.VERIFICATION_TYPE_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATypeDefinition getVerificationType() {
		if (verificationType != null && verificationType.eIsProxy()) {
			InternalEObject oldVerificationType = (InternalEObject)verificationType;
			verificationType = (ATypeDefinition)eResolveProxy(oldVerificationType);
			if (verificationType != oldVerificationType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertydefinitionsPackage.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE, oldVerificationType, verificationType));
			}
		}
		return verificationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATypeDefinition basicGetVerificationType() {
		return verificationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerificationType(ATypeDefinition newVerificationType) {
		ATypeDefinition oldVerificationType = verificationType;
		verificationType = newVerificationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE, oldVerificationType, verificationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PropertydefinitionsPackage.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE:
				if (resolve) return getVerificationType();
				return basicGetVerificationType();
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
			case PropertydefinitionsPackage.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE:
				setVerificationType((ATypeDefinition)newValue);
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
			case PropertydefinitionsPackage.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE:
				setVerificationType((ATypeDefinition)null);
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
			case PropertydefinitionsPackage.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE:
				return verificationType != null;
		}
		return super.eIsSet(featureID);
	}

} //VerificationTypeSpecificationImpl
