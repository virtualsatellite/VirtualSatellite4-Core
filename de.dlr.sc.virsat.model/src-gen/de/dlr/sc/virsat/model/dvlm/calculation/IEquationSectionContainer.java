/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.calculation;


import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IEquation Section Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer#getEquationSection <em>Equation Section</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getIEquationSectionContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IEquationSectionContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Equation Section</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Equation Section</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equation Section</em>' containment reference.
	 * @see #setEquationSection(EquationSection)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getIEquationSectionContainer_EquationSection()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EquationSection getEquationSection();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer#getEquationSection <em>Equation Section</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Equation Section</em>' containment reference.
	 * @see #getEquationSection()
	 * @generated
	 */
	void setEquationSection(EquationSection value);

} // IEquationSectionContainer
