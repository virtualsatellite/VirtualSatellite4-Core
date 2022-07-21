/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.calculation.impl;


import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.Import;

import de.dlr.sc.virsat.model.dvlm.general.IInstance;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ImportImpl#getSuperTis <em>Super Tis</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ImportImpl#isIsInherited <em>Is Inherited</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ImportImpl#getImportedNamespace <em>Imported Namespace</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImportImpl extends MinimalEObjectImpl.Container implements Import {
	/**
	 * The cached value of the '{@link #getSuperTis() <em>Super Tis</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperTis()
	 * @generated
	 * @ordered
	 */
	protected EList<IInheritanceLink> superTis;
	/**
	 * The default value of the '{@link #isIsInherited() <em>Is Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInherited()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INHERITED_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isIsInherited() <em>Is Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInherited()
	 * @generated
	 * @ordered
	 */
	protected boolean isInherited = IS_INHERITED_EDEFAULT;
	/**
	 * The cached value of the '{@link #getImportedNamespace() <em>Imported Namespace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedNamespace()
	 * @generated
	 * @ordered
	 */
	protected IInstance importedNamespace;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportImpl() {
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
		return CalculationPackage.Literals.IMPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<IInheritanceLink>}'.
	 * @generated
	 */
	public EList<IInheritanceLink> getSuperTis() {
		if (superTis == null) {
			superTis = new EObjectResolvingEList<IInheritanceLink>(IInheritanceLink.class, this, CalculationPackage.IMPORT__SUPER_TIS);
		 
		
		}
		return superTis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isIsInherited() {
		return isInherited;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInherited(boolean newIsInherited) {
		boolean oldIsInherited = isInherited;
		isInherited = newIsInherited;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.IMPORT__IS_INHERITED, oldIsInherited, isInherited));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code IInstance}'.
	 * @generated
	 */
	public IInstance getImportedNamespace() {
		if (importedNamespace != null && importedNamespace.eIsProxy()) {
			InternalEObject oldImportedNamespace = (InternalEObject)importedNamespace;
			importedNamespace = (IInstance)eResolveProxy(oldImportedNamespace);
			if (importedNamespace != oldImportedNamespace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalculationPackage.IMPORT__IMPORTED_NAMESPACE, oldImportedNamespace, importedNamespace));
			}
		}
		return importedNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code IInstance}'.
	 * @generated
	 */
	public IInstance basicGetImportedNamespace() {
		return importedNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportedNamespace(IInstance newImportedNamespace) {
		IInstance oldImportedNamespace = importedNamespace;
		importedNamespace = newImportedNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.IMPORT__IMPORTED_NAMESPACE, oldImportedNamespace, importedNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CalculationPackage.IMPORT__SUPER_TIS:
				return getSuperTis();
			case CalculationPackage.IMPORT__IS_INHERITED:
				return isIsInherited();
			case CalculationPackage.IMPORT__IMPORTED_NAMESPACE:
				if (resolve) return getImportedNamespace();
				return basicGetImportedNamespace();
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
			case CalculationPackage.IMPORT__SUPER_TIS:
				getSuperTis().clear();
				getSuperTis().addAll((Collection<? extends IInheritanceLink>)newValue);
				return;
			case CalculationPackage.IMPORT__IS_INHERITED:
				setIsInherited((Boolean)newValue);
				return;
			case CalculationPackage.IMPORT__IMPORTED_NAMESPACE:
				setImportedNamespace((IInstance)newValue);
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
			case CalculationPackage.IMPORT__SUPER_TIS:
				getSuperTis().clear();
				return;
			case CalculationPackage.IMPORT__IS_INHERITED:
				setIsInherited(IS_INHERITED_EDEFAULT);
				return;
			case CalculationPackage.IMPORT__IMPORTED_NAMESPACE:
				setImportedNamespace((IInstance)null);
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
			case CalculationPackage.IMPORT__SUPER_TIS:
				return superTis != null && !superTis.isEmpty();
			case CalculationPackage.IMPORT__IS_INHERITED:
				return isInherited != IS_INHERITED_EDEFAULT;
			case CalculationPackage.IMPORT__IMPORTED_NAMESPACE:
				return importedNamespace != null;
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
		result.append(" (isInherited: ");
		result.append(isInherited);
		result.append(')');
		return result.toString();
	}

} //ImportImpl
