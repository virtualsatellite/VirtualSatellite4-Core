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
 * A representation of the model object '<em><b>AOp Right Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getAOpRightExpression()
 * @model abstract="true"
 * @generated
 */
public interface AOpRightExpression extends AExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dlr.sc.virsat.model.dvlm.calculation.MathOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.MathOperator
	 * @see #setOperator(MathOperator)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getAOpRightExpression_Operator()
	 * @model
	 * @generated
	 */
	MathOperator getOperator();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.MathOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(MathOperator value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(AExpression)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getAOpRightExpression_Right()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	AExpression getRight();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(AExpression value);

} // AOpRightExpression
