/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.dvlm.concepts.impl;


import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ecore Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.EcoreImportImpl#getImportedNsURI <em>Imported Ns URI</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.EcoreImportImpl#getImportedGenModel <em>Imported Gen Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EcoreImportImpl extends MinimalEObjectImpl.Container implements EcoreImport {
	/**
	 * The default value of the '{@link #getImportedNsURI() <em>Imported Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedNsURI()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPORTED_NS_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImportedNsURI() <em>Imported Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedNsURI()
	 * @generated
	 * @ordered
	 */
	protected String importedNsURI = IMPORTED_NS_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getImportedGenModel() <em>Imported Gen Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedGenModel()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPORTED_GEN_MODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImportedGenModel() <em>Imported Gen Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedGenModel()
	 * @generated
	 * @ordered
	 */
	protected String importedGenModel = IMPORTED_GEN_MODEL_EDEFAULT;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EcoreImportImpl() {
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
		return ConceptsPackage.Literals.ECORE_IMPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImportedNsURI() {
		return importedNsURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportedNsURI(String newImportedNsURI) {
		String oldImportedNsURI = importedNsURI;
		importedNsURI = newImportedNsURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.ECORE_IMPORT__IMPORTED_NS_URI, oldImportedNsURI, importedNsURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImportedGenModel() {
		return importedGenModel;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportedGenModel(String newImportedGenModel) {
		String oldImportedGenModel = importedGenModel;
		importedGenModel = newImportedGenModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.ECORE_IMPORT__IMPORTED_GEN_MODEL, oldImportedGenModel, importedGenModel));
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConceptsPackage.ECORE_IMPORT__IMPORTED_NS_URI:
				return getImportedNsURI();
			case ConceptsPackage.ECORE_IMPORT__IMPORTED_GEN_MODEL:
				return getImportedGenModel();
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
			case ConceptsPackage.ECORE_IMPORT__IMPORTED_NS_URI:
				setImportedNsURI((String)newValue);
				return;
			case ConceptsPackage.ECORE_IMPORT__IMPORTED_GEN_MODEL:
				setImportedGenModel((String)newValue);
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
			case ConceptsPackage.ECORE_IMPORT__IMPORTED_NS_URI:
				setImportedNsURI(IMPORTED_NS_URI_EDEFAULT);
				return;
			case ConceptsPackage.ECORE_IMPORT__IMPORTED_GEN_MODEL:
				setImportedGenModel(IMPORTED_GEN_MODEL_EDEFAULT);
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
			case ConceptsPackage.ECORE_IMPORT__IMPORTED_NS_URI:
				return IMPORTED_NS_URI_EDEFAULT == null ? importedNsURI != null : !IMPORTED_NS_URI_EDEFAULT.equals(importedNsURI);
			case ConceptsPackage.ECORE_IMPORT__IMPORTED_GEN_MODEL:
				return IMPORTED_GEN_MODEL_EDEFAULT == null ? importedGenModel != null : !IMPORTED_GEN_MODEL_EDEFAULT.equals(importedGenModel);
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
		result.append(" (importedNsURI: ");
		result.append(importedNsURI);
		result.append(", importedGenModel: ");
		result.append(importedGenModel);
		result.append(')');
		return result.toString();
	}

} //EcoreImportImpl
