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
 * A representation of the model object '<em><b>Referenced Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput#getReference <em>Reference</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput#getDefinition <em>Definition</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getReferencedInput()
 * @model
 * @generated
 */
public interface ReferencedInput extends ALiteral {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(IEquationInput)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getReferencedInput_Reference()
	 * @model
	 * @generated
	 */
	IEquationInput getReference();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(IEquationInput value);

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' reference.
	 * @see #setDefinition(ReferencedDefinitionInput)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getReferencedInput_Definition()
	 * @model
	 * @generated
	 */
	ReferencedDefinitionInput getDefinition();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput#getDefinition <em>Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(ReferencedDefinitionInput value);

} // ReferencedInput
