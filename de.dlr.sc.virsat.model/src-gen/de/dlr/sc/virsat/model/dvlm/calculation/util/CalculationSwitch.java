/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.calculation.util;

import de.dlr.sc.virsat.model.dvlm.calculation.*;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage
 * @generated
 */
public class CalculationSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CalculationPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CalculationSwitch() {
		if (modelPackage == null) {
			modelPackage = CalculationPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CalculationPackage.IEQUATION_DEFINITION_SECTION_CONTAINER: {
				IEquationDefinitionSectionContainer iEquationDefinitionSectionContainer = (IEquationDefinitionSectionContainer)theEObject;
				T result = caseIEquationDefinitionSectionContainer(iEquationDefinitionSectionContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.IEQUATION_SECTION_CONTAINER: {
				IEquationSectionContainer iEquationSectionContainer = (IEquationSectionContainer)theEObject;
				T result = caseIEquationSectionContainer(iEquationSectionContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.EQUATION_SECTION: {
				EquationSection equationSection = (EquationSection)theEObject;
				T result = caseEquationSection(equationSection);
				if (result == null) result = caseIEquationSectionContainer(equationSection);
				if (result == null) result = caseIInheritanceLink(equationSection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.IMPORT: {
				Import import_ = (Import)theEObject;
				T result = caseImport(import_);
				if (result == null) result = caseIInheritanceLink(import_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.EQUATION: {
				Equation equation = (Equation)theEObject;
				T result = caseEquation(equation);
				if (result == null) result = caseIOverridableInheritanceLink(equation);
				if (result == null) result = caseIInheritanceLink(equation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.EQUATION_DEFINITION: {
				EquationDefinition equationDefinition = (EquationDefinition)theEObject;
				T result = caseEquationDefinition(equationDefinition);
				if (result == null) result = caseIQualifiedEquationObject(equationDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.IEQUATION_RESULT: {
				IEquationResult iEquationResult = (IEquationResult)theEObject;
				T result = caseIEquationResult(iEquationResult);
				if (result == null) result = caseIUuid(iEquationResult);
				if (result == null) result = caseIInheritanceLink(iEquationResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.IEQUATION_DEFINITION_RESULT: {
				IEquationDefinitionResult iEquationDefinitionResult = (IEquationDefinitionResult)theEObject;
				T result = caseIEquationDefinitionResult(iEquationDefinitionResult);
				if (result == null) result = caseIQualifiedEquationObject(iEquationDefinitionResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.IEQUATION_INPUT: {
				IEquationInput iEquationInput = (IEquationInput)theEObject;
				T result = caseIEquationInput(iEquationInput);
				if (result == null) result = caseIInheritanceLink(iEquationInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.IEQUATION_DEFINITION_INPUT: {
				IEquationDefinitionInput iEquationDefinitionInput = (IEquationDefinitionInput)theEObject;
				T result = caseIEquationDefinitionInput(iEquationDefinitionInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.EQUATION_INTERMEDIATE_RESULT: {
				EquationIntermediateResult equationIntermediateResult = (EquationIntermediateResult)theEObject;
				T result = caseEquationIntermediateResult(equationIntermediateResult);
				if (result == null) result = caseIEquationResult(equationIntermediateResult);
				if (result == null) result = caseIEquationInput(equationIntermediateResult);
				if (result == null) result = caseIEquationDefinitionInput(equationIntermediateResult);
				if (result == null) result = caseIEquationDefinitionResult(equationIntermediateResult);
				if (result == null) result = caseIUuid(equationIntermediateResult);
				if (result == null) result = caseIInheritanceLink(equationIntermediateResult);
				if (result == null) result = caseIQualifiedEquationObject(equationIntermediateResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.TYPE_INSTANCE_RESULT: {
				TypeInstanceResult typeInstanceResult = (TypeInstanceResult)theEObject;
				T result = caseTypeInstanceResult(typeInstanceResult);
				if (result == null) result = caseIEquationResult(typeInstanceResult);
				if (result == null) result = caseIUuid(typeInstanceResult);
				if (result == null) result = caseIInheritanceLink(typeInstanceResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.TYPE_DEFINITION_RESULT: {
				TypeDefinitionResult typeDefinitionResult = (TypeDefinitionResult)theEObject;
				T result = caseTypeDefinitionResult(typeDefinitionResult);
				if (result == null) result = caseIEquationDefinitionResult(typeDefinitionResult);
				if (result == null) result = caseIQualifiedEquationObject(typeDefinitionResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.AEXPRESSION: {
				AExpression aExpression = (AExpression)theEObject;
				T result = caseAExpression(aExpression);
				if (result == null) result = caseIInheritanceLink(aExpression);
				if (result == null) result = caseIQualifiedEquationObject(aExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION: {
				ALeftOpRightExpression aLeftOpRightExpression = (ALeftOpRightExpression)theEObject;
				T result = caseALeftOpRightExpression(aLeftOpRightExpression);
				if (result == null) result = caseAExpression(aLeftOpRightExpression);
				if (result == null) result = caseIInheritanceLink(aLeftOpRightExpression);
				if (result == null) result = caseIQualifiedEquationObject(aLeftOpRightExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.AOP_RIGHT_EXPRESSION: {
				AOpRightExpression aOpRightExpression = (AOpRightExpression)theEObject;
				T result = caseAOpRightExpression(aOpRightExpression);
				if (result == null) result = caseAExpression(aOpRightExpression);
				if (result == null) result = caseIInheritanceLink(aOpRightExpression);
				if (result == null) result = caseIQualifiedEquationObject(aOpRightExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.ADDITION_AND_SUBTRACTION: {
				AdditionAndSubtraction additionAndSubtraction = (AdditionAndSubtraction)theEObject;
				T result = caseAdditionAndSubtraction(additionAndSubtraction);
				if (result == null) result = caseALeftOpRightExpression(additionAndSubtraction);
				if (result == null) result = caseAExpression(additionAndSubtraction);
				if (result == null) result = caseIInheritanceLink(additionAndSubtraction);
				if (result == null) result = caseIQualifiedEquationObject(additionAndSubtraction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.MULTIPLICATION_AND_DIVISION: {
				MultiplicationAndDivision multiplicationAndDivision = (MultiplicationAndDivision)theEObject;
				T result = caseMultiplicationAndDivision(multiplicationAndDivision);
				if (result == null) result = caseALeftOpRightExpression(multiplicationAndDivision);
				if (result == null) result = caseAExpression(multiplicationAndDivision);
				if (result == null) result = caseIInheritanceLink(multiplicationAndDivision);
				if (result == null) result = caseIQualifiedEquationObject(multiplicationAndDivision);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.POWER_FUNCTION: {
				PowerFunction powerFunction = (PowerFunction)theEObject;
				T result = casePowerFunction(powerFunction);
				if (result == null) result = caseALeftOpRightExpression(powerFunction);
				if (result == null) result = caseAExpression(powerFunction);
				if (result == null) result = caseIInheritanceLink(powerFunction);
				if (result == null) result = caseIQualifiedEquationObject(powerFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.FUNCTION: {
				Function function = (Function)theEObject;
				T result = caseFunction(function);
				if (result == null) result = caseAOpRightExpression(function);
				if (result == null) result = caseAExpression(function);
				if (result == null) result = caseIInheritanceLink(function);
				if (result == null) result = caseIQualifiedEquationObject(function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.AADVANCED_FUNCTION: {
				AAdvancedFunction aAdvancedFunction = (AAdvancedFunction)theEObject;
				T result = caseAAdvancedFunction(aAdvancedFunction);
				if (result == null) result = caseAExpression(aAdvancedFunction);
				if (result == null) result = caseIInheritanceLink(aAdvancedFunction);
				if (result == null) result = caseIQualifiedEquationObject(aAdvancedFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.ADVANCED_FUNCTION: {
				AdvancedFunction advancedFunction = (AdvancedFunction)theEObject;
				T result = caseAdvancedFunction(advancedFunction);
				if (result == null) result = caseAAdvancedFunction(advancedFunction);
				if (result == null) result = caseAExpression(advancedFunction);
				if (result == null) result = caseIInheritanceLink(advancedFunction);
				if (result == null) result = caseIQualifiedEquationObject(advancedFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.SET_FUNCTION: {
				SetFunction setFunction = (SetFunction)theEObject;
				T result = caseSetFunction(setFunction);
				if (result == null) result = caseAAdvancedFunction(setFunction);
				if (result == null) result = caseAExpression(setFunction);
				if (result == null) result = caseIInheritanceLink(setFunction);
				if (result == null) result = caseIQualifiedEquationObject(setFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.PARENTHESIS: {
				Parenthesis parenthesis = (Parenthesis)theEObject;
				T result = caseParenthesis(parenthesis);
				if (result == null) result = caseAOpRightExpression(parenthesis);
				if (result == null) result = caseAExpression(parenthesis);
				if (result == null) result = caseIInheritanceLink(parenthesis);
				if (result == null) result = caseIQualifiedEquationObject(parenthesis);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.ALITERAL: {
				ALiteral aLiteral = (ALiteral)theEObject;
				T result = caseALiteral(aLiteral);
				if (result == null) result = caseAExpression(aLiteral);
				if (result == null) result = caseIInheritanceLink(aLiteral);
				if (result == null) result = caseIQualifiedEquationObject(aLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.VALUE_PI: {
				ValuePi valuePi = (ValuePi)theEObject;
				T result = caseValuePi(valuePi);
				if (result == null) result = caseALiteral(valuePi);
				if (result == null) result = caseAExpression(valuePi);
				if (result == null) result = caseIInheritanceLink(valuePi);
				if (result == null) result = caseIQualifiedEquationObject(valuePi);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.VALUE_E: {
				ValueE valueE = (ValueE)theEObject;
				T result = caseValueE(valueE);
				if (result == null) result = caseALiteral(valueE);
				if (result == null) result = caseAExpression(valueE);
				if (result == null) result = caseIInheritanceLink(valueE);
				if (result == null) result = caseIQualifiedEquationObject(valueE);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.NUMBER_LITERAL: {
				NumberLiteral numberLiteral = (NumberLiteral)theEObject;
				T result = caseNumberLiteral(numberLiteral);
				if (result == null) result = caseALiteral(numberLiteral);
				if (result == null) result = caseAExpression(numberLiteral);
				if (result == null) result = caseIInheritanceLink(numberLiteral);
				if (result == null) result = caseIQualifiedEquationObject(numberLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.REFERENCED_INPUT: {
				ReferencedInput referencedInput = (ReferencedInput)theEObject;
				T result = caseReferencedInput(referencedInput);
				if (result == null) result = caseALiteral(referencedInput);
				if (result == null) result = caseAExpression(referencedInput);
				if (result == null) result = caseIInheritanceLink(referencedInput);
				if (result == null) result = caseIQualifiedEquationObject(referencedInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.REFERENCED_DEFINITION_INPUT: {
				ReferencedDefinitionInput referencedDefinitionInput = (ReferencedDefinitionInput)theEObject;
				T result = caseReferencedDefinitionInput(referencedDefinitionInput);
				if (result == null) result = caseALiteral(referencedDefinitionInput);
				if (result == null) result = caseAExpression(referencedDefinitionInput);
				if (result == null) result = caseIInheritanceLink(referencedDefinitionInput);
				if (result == null) result = caseIQualifiedEquationObject(referencedDefinitionInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CalculationPackage.IQUALIFIED_EQUATION_OBJECT: {
				IQualifiedEquationObject iQualifiedEquationObject = (IQualifiedEquationObject)theEObject;
				T result = caseIQualifiedEquationObject(iQualifiedEquationObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEquation Definition Section Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEquation Definition Section Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEquationDefinitionSectionContainer(IEquationDefinitionSectionContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEquation Section Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEquation Section Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEquationSectionContainer(IEquationSectionContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Equation Section</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Equation Section</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEquationSection(EquationSection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImport(Import object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Equation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Equation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEquation(Equation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Equation Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Equation Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEquationDefinition(EquationDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEquation Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEquation Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEquationResult(IEquationResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEquation Definition Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEquation Definition Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEquationDefinitionResult(IEquationDefinitionResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEquation Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEquation Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEquationInput(IEquationInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEquation Definition Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEquation Definition Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEquationDefinitionInput(IEquationDefinitionInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Equation Intermediate Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Equation Intermediate Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEquationIntermediateResult(EquationIntermediateResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Instance Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Instance Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeInstanceResult(TypeInstanceResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Definition Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Definition Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeDefinitionResult(TypeDefinitionResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AExpression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AExpression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAExpression(AExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ALeft Op Right Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ALeft Op Right Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseALeftOpRightExpression(ALeftOpRightExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AOp Right Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AOp Right Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAOpRightExpression(AOpRightExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Addition And Subtraction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Addition And Subtraction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdditionAndSubtraction(AdditionAndSubtraction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multiplication And Division</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multiplication And Division</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiplicationAndDivision(MultiplicationAndDivision object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Power Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Power Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePowerFunction(PowerFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AAdvanced Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AAdvanced Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAAdvancedFunction(AAdvancedFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Advanced Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Advanced Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdvancedFunction(AdvancedFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Set Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Set Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSetFunction(SetFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parenthesis</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parenthesis</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParenthesis(Parenthesis object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ALiteral</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ALiteral</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseALiteral(ALiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Pi</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Pi</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValuePi(ValuePi object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value E</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value E</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueE(ValueE object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Number Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Number Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumberLiteral(NumberLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Referenced Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Referenced Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferencedInput(ReferencedInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Referenced Definition Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Referenced Definition Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferencedDefinitionInput(ReferencedDefinitionInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IQualified Equation Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IQualified Equation Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIQualifiedEquationObject(IQualifiedEquationObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IInheritance Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IInheritance Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIInheritanceLink(IInheritanceLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IOverridable Inheritance Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IOverridable Inheritance Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIOverridableInheritanceLink(IOverridableInheritanceLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IUuid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IUuid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIUuid(IUuid object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //CalculationSwitch
