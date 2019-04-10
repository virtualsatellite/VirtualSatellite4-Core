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


import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Equation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getExpression <em>Expression</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getResult <em>Result</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getResultText <em>Result Text</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getDefinition <em>Definition</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquation()
 * @model
 * @generated
 */
public interface Equation extends IOverridableInheritanceLink {
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
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquation_Expression()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	AExpression getExpression();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getExpression <em>Expression</em>}' containment reference.
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
	 * @see #setResult(IEquationResult)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquation_Result()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	IEquationResult getResult();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getResult <em>Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' containment reference.
	 * @see #getResult()
	 * @generated
	 */
	void setResult(IEquationResult value);

	/**
	 * Returns the value of the '<em><b>Result Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Text</em>' attribute.
	 * @see #setResultText(String)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquation_ResultText()
	 * @model id="true"
	 * @generated
	 */
	String getResultText();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getResultText <em>Result Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Text</em>' attribute.
	 * @see #getResultText()
	 * @generated
	 */
	void setResultText(String value);

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' reference.
	 * @see #setDefinition(EquationDefinition)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getEquation_Definition()
	 * @model
	 * @generated
	 */
	EquationDefinition getDefinition();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getDefinition <em>Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(EquationDefinition value);

} // Equation
