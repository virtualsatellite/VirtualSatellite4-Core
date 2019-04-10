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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IEquation Definition Section Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer#getEquationDefinitions <em>Equation Definitions</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getIEquationDefinitionSectionContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IEquationDefinitionSectionContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Equation Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equation Definitions</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getIEquationDefinitionSectionContainer_EquationDefinitions()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<EquationDefinition> getEquationDefinitions();

} // IEquationDefinitionSectionContainer
