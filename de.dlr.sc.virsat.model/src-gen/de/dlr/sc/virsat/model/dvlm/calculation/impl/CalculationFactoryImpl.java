/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.calculation.impl;

import de.dlr.sc.virsat.model.dvlm.calculation.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CalculationFactoryImpl extends EFactoryImpl implements CalculationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return an initialized instance of 'CalculationFactory'.
	 * @generated
	 */
	public static CalculationFactory init() {
		try {
			CalculationFactory theCalculationFactory = (CalculationFactory)EPackage.Registry.INSTANCE.getEFactory(CalculationPackage.eNS_URI);
			if (theCalculationFactory != null) {
				return theCalculationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CalculationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CalculationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CalculationPackage.EQUATION_SECTION: return createEquationSection();
			case CalculationPackage.IMPORT: return createImport();
			case CalculationPackage.EQUATION: return createEquation();
			case CalculationPackage.EQUATION_DEFINITION: return createEquationDefinition();
			case CalculationPackage.EQUATION_INTERMEDIATE_RESULT: return createEquationIntermediateResult();
			case CalculationPackage.TYPE_INSTANCE_RESULT: return createTypeInstanceResult();
			case CalculationPackage.TYPE_DEFINITION_RESULT: return createTypeDefinitionResult();
			case CalculationPackage.ADDITION_AND_SUBTRACTION: return createAdditionAndSubtraction();
			case CalculationPackage.MULTIPLICATION_AND_DIVISION: return createMultiplicationAndDivision();
			case CalculationPackage.POWER_FUNCTION: return createPowerFunction();
			case CalculationPackage.FUNCTION: return createFunction();
			case CalculationPackage.ADVANCED_FUNCTION: return createAdvancedFunction();
			case CalculationPackage.SET_FUNCTION: return createSetFunction();
			case CalculationPackage.PARENTHESIS: return createParenthesis();
			case CalculationPackage.VALUE_PI: return createValuePi();
			case CalculationPackage.VALUE_E: return createValueE();
			case CalculationPackage.NUMBER_LITERAL: return createNumberLiteral();
			case CalculationPackage.REFERENCED_INPUT: return createReferencedInput();
			case CalculationPackage.REFERENCED_DEFINITION_INPUT: return createReferencedDefinitionInput();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CalculationPackage.MATH_OPERATOR:
				return createMathOperatorFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CalculationPackage.MATH_OPERATOR:
				return convertMathOperatorToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EquationSection createEquationSection() {
		EquationSectionImpl equationSection = new EquationSectionImpl();
		return equationSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Import createImport() {
		ImportImpl import_ = new ImportImpl();
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Equation createEquation() {
		EquationImpl equation = new EquationImpl();
		return equation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EquationDefinition createEquationDefinition() {
		EquationDefinitionImpl equationDefinition = new EquationDefinitionImpl();
		return equationDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EquationIntermediateResult createEquationIntermediateResult() {
		EquationIntermediateResultImpl equationIntermediateResult = new EquationIntermediateResultImpl();
		return equationIntermediateResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeInstanceResult createTypeInstanceResult() {
		TypeInstanceResultImpl typeInstanceResult = new TypeInstanceResultImpl();
		return typeInstanceResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDefinitionResult createTypeDefinitionResult() {
		TypeDefinitionResultImpl typeDefinitionResult = new TypeDefinitionResultImpl();
		return typeDefinitionResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdditionAndSubtraction createAdditionAndSubtraction() {
		AdditionAndSubtractionImpl additionAndSubtraction = new AdditionAndSubtractionImpl();
		return additionAndSubtraction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiplicationAndDivision createMultiplicationAndDivision() {
		MultiplicationAndDivisionImpl multiplicationAndDivision = new MultiplicationAndDivisionImpl();
		return multiplicationAndDivision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PowerFunction createPowerFunction() {
		PowerFunctionImpl powerFunction = new PowerFunctionImpl();
		return powerFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function createFunction() {
		FunctionImpl function = new FunctionImpl();
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdvancedFunction createAdvancedFunction() {
		AdvancedFunctionImpl advancedFunction = new AdvancedFunctionImpl();
		return advancedFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetFunction createSetFunction() {
		SetFunctionImpl setFunction = new SetFunctionImpl();
		return setFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parenthesis createParenthesis() {
		ParenthesisImpl parenthesis = new ParenthesisImpl();
		return parenthesis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValuePi createValuePi() {
		ValuePiImpl valuePi = new ValuePiImpl();
		return valuePi;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueE createValueE() {
		ValueEImpl valueE = new ValueEImpl();
		return valueE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumberLiteral createNumberLiteral() {
		NumberLiteralImpl numberLiteral = new NumberLiteralImpl();
		return numberLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencedInput createReferencedInput() {
		ReferencedInputImpl referencedInput = new ReferencedInputImpl();
		return referencedInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencedDefinitionInput createReferencedDefinitionInput() {
		ReferencedDefinitionInputImpl referencedDefinitionInput = new ReferencedDefinitionInputImpl();
		return referencedDefinitionInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eDataType describing the type of object.
	 * @param initialValue for the creation of the object.
	 * @return a newly created object of type '{@code MathOperator}.'
	 * @generated
	 */
	public MathOperator createMathOperatorFromString(EDataType eDataType, String initialValue) {
		MathOperator result = MathOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eDataType describing the type of object.
	 * @param instanceValue of the object.
	 * @return a string representing the object.
	 * @generated
	 */
	public String convertMathOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CalculationPackage getCalculationPackage() {
		return (CalculationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @return an instance of CalculationPackage.
	 * @generated
	 */
	@Deprecated
	public static CalculationPackage getPackage() {
		return CalculationPackage.eINSTANCE;
	}

} //CalculationFactoryImpl
