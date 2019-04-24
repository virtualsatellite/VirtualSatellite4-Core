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


import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Advanced Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getAdvancedFunction()
 * @model
 * @generated
 */
public interface AdvancedFunction extends AAdvancedFunction {
	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.calculation.AExpression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inputs</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getAdvancedFunction_Inputs()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<AExpression> getInputs();

} // AdvancedFunction
