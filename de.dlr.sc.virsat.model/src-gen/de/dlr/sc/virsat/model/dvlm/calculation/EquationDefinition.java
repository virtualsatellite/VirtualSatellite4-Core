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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Equation Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition#getExpression <em>Expression</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition#getResult <em>Result</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquationDefinition()
 * @model
 * @generated
 */
public interface EquationDefinition extends IQualifiedEquationObject {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(AExpression)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquationDefinition_Expression()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	AExpression getExpression();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(AExpression value);

	/**
	 * Returns the value of the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' containment reference.
	 * @see #setResult(IEquationDefinitionResult)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquationDefinition_Result()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	IEquationDefinitionResult getResult();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition#getResult <em>Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' containment reference.
	 * @see #getResult()
	 * @generated
	 */
	void setResult(IEquationDefinitionResult value);

} // EquationDefinition
