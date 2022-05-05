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


import de.dlr.sc.virsat.model.dvlm.qudv.Dimension;
import de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;

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
 * An implementation of the model object '<em><b>Dimension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionImpl#getSymbolicExpression <em>Symbolic Expression</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionImpl#getFactor <em>Factor</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DimensionImpl extends MinimalEObjectImpl.Container implements Dimension {
	/**
	 * The default value of the '{@link #getSymbolicExpression() <em>Symbolic Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbolicExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String SYMBOLIC_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSymbolicExpression() <em>Symbolic Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbolicExpression()
	 * @generated
	 * @ordered
	 */
	protected String symbolicExpression = SYMBOLIC_EXPRESSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFactor() <em>Factor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactor()
	 * @generated
	 * @ordered
	 */
	protected EList<DimensionFactor> factor;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DimensionImpl() {
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
		return QudvPackage.Literals.DIMENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getSymbolicExpression() {
		return symbolicExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSymbolicExpression(String newSymbolicExpression) {
		String oldSymbolicExpression = symbolicExpression;
		symbolicExpression = newSymbolicExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.DIMENSION__SYMBOLIC_EXPRESSION, oldSymbolicExpression, symbolicExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<DimensionFactor>}'.
	 * @generated
	 */
	public EList<DimensionFactor> getFactor() {
		if (factor == null) {
			factor = new EObjectResolvingEList<DimensionFactor>(DimensionFactor.class, this, QudvPackage.DIMENSION__FACTOR);
		 
		
		}
		return factor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QudvPackage.DIMENSION__SYMBOLIC_EXPRESSION:
				return getSymbolicExpression();
			case QudvPackage.DIMENSION__FACTOR:
				return getFactor();
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
			case QudvPackage.DIMENSION__SYMBOLIC_EXPRESSION:
				setSymbolicExpression((String)newValue);
				return;
			case QudvPackage.DIMENSION__FACTOR:
				getFactor().clear();
				getFactor().addAll((Collection<? extends DimensionFactor>)newValue);
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
			case QudvPackage.DIMENSION__SYMBOLIC_EXPRESSION:
				setSymbolicExpression(SYMBOLIC_EXPRESSION_EDEFAULT);
				return;
			case QudvPackage.DIMENSION__FACTOR:
				getFactor().clear();
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
			case QudvPackage.DIMENSION__SYMBOLIC_EXPRESSION:
				return SYMBOLIC_EXPRESSION_EDEFAULT == null ? symbolicExpression != null : !SYMBOLIC_EXPRESSION_EDEFAULT.equals(symbolicExpression);
			case QudvPackage.DIMENSION__FACTOR:
				return factor != null && !factor.isEmpty();
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
		result.append(" (symbolicExpression: ");
		result.append(symbolicExpression);
		result.append(')');
		return result.toString();
	}

} //DimensionImpl
