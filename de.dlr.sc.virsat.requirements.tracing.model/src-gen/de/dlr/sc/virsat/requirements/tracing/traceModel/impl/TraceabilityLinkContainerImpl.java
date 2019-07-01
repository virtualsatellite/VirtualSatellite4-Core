/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
/**
 */
package de.dlr.sc.virsat.requirements.tracing.traceModel.impl;

import de.dlr.sc.virsat.requirements.tracing.traceModel.TMPackage;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceabilityLinkContainer;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Traceability Link Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.traceModel.impl.TraceabilityLinkContainerImpl#getTraceElements <em>Trace Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TraceabilityLinkContainerImpl extends MinimalEObjectImpl.Container implements TraceabilityLinkContainer {
	/**
	 * The cached value of the '{@link #getTraceElements() <em>Trace Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraceElements()
	 * @generated
	 * @ordered
	 */
	protected EList<TraceElement> traceElements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceabilityLinkContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TMPackage.Literals.TRACEABILITY_LINK_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TraceElement> getTraceElements() {
		if (traceElements == null) {
			traceElements = new EObjectContainmentEList<TraceElement>(TraceElement.class, this, TMPackage.TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS);
		}
		return traceElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TMPackage.TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS:
				return ((InternalEList<?>)getTraceElements()).basicRemove(otherEnd, msgs);
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
			case TMPackage.TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS:
				return getTraceElements();
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
			case TMPackage.TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS:
				getTraceElements().clear();
				getTraceElements().addAll((Collection<? extends TraceElement>)newValue);
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
			case TMPackage.TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS:
				getTraceElements().clear();
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
			case TMPackage.TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS:
				return traceElements != null && !traceElements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TraceabilityLinkContainerImpl
