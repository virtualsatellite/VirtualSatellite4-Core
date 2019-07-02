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

import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getSourceTraceElement <em>Source Trace Element</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getTargetTraceElement <em>Target Trace Element</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getValidationEngineName <em>Validation Engine Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage#getTraceElement()
 * @model
 * @generated
 */
public interface TraceElement extends IUuid, IName {
	/**
	 * Returns the value of the '<em><b>Source Trace Element</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Trace Element</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Trace Element</em>' reference list.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage#getTraceElement_SourceTraceElement()
	 * @model upper="10"
	 * @generated
	 */
	EList<EObject> getSourceTraceElement();

	/**
	 * Returns the value of the '<em><b>Target Trace Element</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Trace Element</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Trace Element</em>' reference list.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage#getTraceElement_TargetTraceElement()
	 * @model upper="10"
	 * @generated
	 */
	EList<EObject> getTargetTraceElement();

	/**
	 * Returns the value of the '<em><b>Validation Engine Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validation Engine Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Validation Engine Name</em>' attribute.
	 * @see #setValidationEngineName(String)
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage#getTraceElement_ValidationEngineName()
	 * @model
	 * @generated
	 */
	String getValidationEngineName();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getValidationEngineName <em>Validation Engine Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validation Engine Name</em>' attribute.
	 * @see #getValidationEngineName()
	 * @generated
	 */
	void setValidationEngineName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage#getTraceElement_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // TraceElement
