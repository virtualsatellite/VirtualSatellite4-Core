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
 * A representation of the model object '<em><b>Set Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getTypeDefinition <em>Type Definition</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getFilterName <em>Filter Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getDepth <em>Depth</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getSetFunction()
 * @model
 * @generated
 */
public interface SetFunction extends AAdvancedFunction {
	/**
	 * Returns the value of the '<em><b>Type Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Definition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Definition</em>' reference.
	 * @see #setTypeDefinition(ATypeDefinition)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getSetFunction_TypeDefinition()
	 * @model
	 * @generated
	 */
	ATypeDefinition getTypeDefinition();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getTypeDefinition <em>Type Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Definition</em>' reference.
	 * @see #getTypeDefinition()
	 * @generated
	 */
	void setTypeDefinition(ATypeDefinition value);

	/**
	 * Returns the value of the '<em><b>Filter Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Name</em>' attribute.
	 * @see #setFilterName(String)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getSetFunction_FilterName()
	 * @model
	 * @generated
	 */
	String getFilterName();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getFilterName <em>Filter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Name</em>' attribute.
	 * @see #getFilterName()
	 * @generated
	 */
	void setFilterName(String value);

	/**
	 * Returns the value of the '<em><b>Depth</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depth</em>' attribute.
	 * @see #setDepth(int)
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#getSetFunction_Depth()
	 * @model default="-1"
	 * @generated
	 */
	int getDepth();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getDepth <em>Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depth</em>' attribute.
	 * @see #getDepth()
	 * @generated
	 */
	void setDepth(int value);

} // SetFunction
