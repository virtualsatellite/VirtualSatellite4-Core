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
package de.dlr.sc.virsat.requirements.tracing.model.traceModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Traceability Link Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer#getTraceElements <em>Trace Elements</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage#getTraceabilityLinkContainer()
 * @model
 * @generated
 */
public interface TraceabilityLinkContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Trace Elements</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trace Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace Elements</em>' containment reference list.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage#getTraceabilityLinkContainer_TraceElements()
	 * @model containment="true"
	 * @generated
	 */
	EList<TraceElement> getTraceElements();

} // TraceabilityLinkContainer
