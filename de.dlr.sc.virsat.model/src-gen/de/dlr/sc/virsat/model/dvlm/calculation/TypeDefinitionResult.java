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


import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Definition Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getTypeDefinitionResult()
 * @model
 * @generated
 */
public interface TypeDefinitionResult extends IEquationDefinitionResult {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(ATypeDefinition)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getTypeDefinitionResult_Reference()
	 * @model
	 * @generated
	 */
	ATypeDefinition getReference();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(ATypeDefinition value);

} // TypeDefinitionResult
