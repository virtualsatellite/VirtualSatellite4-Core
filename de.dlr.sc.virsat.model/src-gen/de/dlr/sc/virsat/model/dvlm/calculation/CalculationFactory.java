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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage
 * @generated
 */
public interface CalculationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CalculationFactory eINSTANCE = de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Equation Section</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Equation Section</em>'.
	 * @generated
	 */
	EquationSection createEquationSection();

	/**
	 * Returns a new object of class '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import</em>'.
	 * @generated
	 */
	Import createImport();

	/**
	 * Returns a new object of class '<em>Equation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Equation</em>'.
	 * @generated
	 */
	Equation createEquation();

	/**
	 * Returns a new object of class '<em>Equation Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Equation Definition</em>'.
	 * @generated
	 */
	EquationDefinition createEquationDefinition();

	/**
	 * Returns a new object of class '<em>Equation Intermediate Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Equation Intermediate Result</em>'.
	 * @generated
	 */
	EquationIntermediateResult createEquationIntermediateResult();

	/**
	 * Returns a new object of class '<em>Type Instance Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Instance Result</em>'.
	 * @generated
	 */
	TypeInstanceResult createTypeInstanceResult();

	/**
	 * Returns a new object of class '<em>Type Definition Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Definition Result</em>'.
	 * @generated
	 */
	TypeDefinitionResult createTypeDefinitionResult();

	/**
	 * Returns a new object of class '<em>Addition And Subtraction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Addition And Subtraction</em>'.
	 * @generated
	 */
	AdditionAndSubtraction createAdditionAndSubtraction();

	/**
	 * Returns a new object of class '<em>Multiplication And Division</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multiplication And Division</em>'.
	 * @generated
	 */
	MultiplicationAndDivision createMultiplicationAndDivision();

	/**
	 * Returns a new object of class '<em>Power Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Power Function</em>'.
	 * @generated
	 */
	PowerFunction createPowerFunction();

	/**
	 * Returns a new object of class '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function</em>'.
	 * @generated
	 */
	Function createFunction();

	/**
	 * Returns a new object of class '<em>Advanced Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Advanced Function</em>'.
	 * @generated
	 */
	AdvancedFunction createAdvancedFunction();

	/**
	 * Returns a new object of class '<em>Set Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Function</em>'.
	 * @generated
	 */
	SetFunction createSetFunction();

	/**
	 * Returns a new object of class '<em>Parenthesis</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parenthesis</em>'.
	 * @generated
	 */
	Parenthesis createParenthesis();

	/**
	 * Returns a new object of class '<em>Value Pi</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Pi</em>'.
	 * @generated
	 */
	ValuePi createValuePi();

	/**
	 * Returns a new object of class '<em>Value E</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value E</em>'.
	 * @generated
	 */
	ValueE createValueE();

	/**
	 * Returns a new object of class '<em>Number Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Literal</em>'.
	 * @generated
	 */
	NumberLiteral createNumberLiteral();

	/**
	 * Returns a new object of class '<em>Referenced Input</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Referenced Input</em>'.
	 * @generated
	 */
	ReferencedInput createReferencedInput();

	/**
	 * Returns a new object of class '<em>Referenced Definition Input</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Referenced Definition Input</em>'.
	 * @generated
	 */
	ReferencedDefinitionInput createReferencedDefinitionInput();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CalculationPackage getCalculationPackage();

} //CalculationFactory
