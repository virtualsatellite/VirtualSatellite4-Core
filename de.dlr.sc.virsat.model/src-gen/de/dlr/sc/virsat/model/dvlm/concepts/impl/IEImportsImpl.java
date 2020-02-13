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
import de.dlr.sc.virsat.model.dvlm.concepts.IEImports;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IE Imports</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.IEImportsImpl#getEcoreImports <em>Ecore Imports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IEImportsImpl extends MinimalEObjectImpl.Container implements IEImports {
	/**
	 * The cached value of the '{@link #getEcoreImports() <em>Ecore Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEcoreImports()
	 * @generated
	 * @ordered
	 */
	protected EList<EcoreImport> ecoreImports;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IEImportsImpl() {
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
		return ConceptsPackage.Literals.IE_IMPORTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EcoreImport> getEcoreImports() {
		if (ecoreImports == null) {
			ecoreImports = new EObjectContainmentEList.Resolving<EcoreImport>(EcoreImport.class, this, ConceptsPackage.IE_IMPORTS__ECORE_IMPORTS);
		 
		
		}
		return ecoreImports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConceptsPackage.IE_IMPORTS__ECORE_IMPORTS:
				return ((InternalEList<?>)getEcoreImports()).basicRemove(otherEnd, msgs);
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
			case ConceptsPackage.IE_IMPORTS__ECORE_IMPORTS:
				return getEcoreImports();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConceptsPackage.IE_IMPORTS__ECORE_IMPORTS:
				getEcoreImports().clear();
				getEcoreImports().addAll((Collection<? extends EcoreImport>)newValue);
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
			case ConceptsPackage.IE_IMPORTS__ECORE_IMPORTS:
				getEcoreImports().clear();
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
			case ConceptsPackage.IE_IMPORTS__ECORE_IMPORTS:
				return ecoreImports != null && !ecoreImports.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //IEImportsImpl
