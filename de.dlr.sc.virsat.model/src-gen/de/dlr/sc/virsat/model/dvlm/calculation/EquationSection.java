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


import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Equation Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getImports <em>Imports</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getEquations <em>Equations</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getSerializedStatements <em>Serialized Statements</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquationSection()
 * @model
 * @generated
 */
public interface EquationSection extends IEquationSectionContainer, IInheritanceLink {
	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.calculation.Import}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquationSection_Imports()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Import> getImports();

	/**
	 * Returns the value of the '<em><b>Equations</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.calculation.Equation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Equations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equations</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquationSection_Equations()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Equation> getEquations();

	/**
	 * Returns the value of the '<em><b>Serialized Statements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The serialized formulas are used as fall back strategy in case that the serialization of the formulas fail
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Serialized Statements</em>' attribute.
	 * @see #setSerializedStatements(String)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquationSection_SerializedStatements()
	 * @model
	 * @generated
	 */
	String getSerializedStatements();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getSerializedStatements <em>Serialized Statements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Serialized Statements</em>' attribute.
	 * @see #getSerializedStatements()
	 * @generated
	 */
	void setSerializedStatements(String value);

} // EquationSection
