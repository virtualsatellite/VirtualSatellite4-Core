/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.serializer;

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.calculation.services.EquationDSLGrammarAccess;
import de.dlr.sc.virsat.model.dvlm.calculation.AdditionAndSubtraction;
import de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.Function;
import de.dlr.sc.virsat.model.dvlm.calculation.Import;
import de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.Parenthesis;
import de.dlr.sc.virsat.model.dvlm.calculation.PowerFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.calculation.ValueE;
import de.dlr.sc.virsat.model.dvlm.calculation.ValuePi;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class EquationDSLSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private EquationDSLGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == CalculationPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case CalculationPackage.ADDITION_AND_SUBTRACTION:
				sequence_AdditionAndSubtraction(context, (AdditionAndSubtraction) semanticObject); 
				return; 
			case CalculationPackage.ADVANCED_FUNCTION:
				sequence_AdvancedFunction(context, (AdvancedFunction) semanticObject); 
				return; 
			case CalculationPackage.EQUATION:
				sequence_Equation(context, (Equation) semanticObject); 
				return; 
			case CalculationPackage.EQUATION_INTERMEDIATE_RESULT:
				sequence_EquationIntermediateResult(context, (EquationIntermediateResult) semanticObject); 
				return; 
			case CalculationPackage.EQUATION_SECTION:
				sequence_EquationSection(context, (EquationSection) semanticObject); 
				return; 
			case CalculationPackage.FUNCTION:
				sequence_Function(context, (Function) semanticObject); 
				return; 
			case CalculationPackage.IMPORT:
				sequence_Import(context, (Import) semanticObject); 
				return; 
			case CalculationPackage.MULTIPLICATION_AND_DIVISION:
				sequence_MultiplicationAndDivision(context, (MultiplicationAndDivision) semanticObject); 
				return; 
			case CalculationPackage.NUMBER_LITERAL:
				sequence_NumberLiteral(context, (NumberLiteral) semanticObject); 
				return; 
			case CalculationPackage.PARENTHESIS:
				sequence_Parenthesis(context, (Parenthesis) semanticObject); 
				return; 
			case CalculationPackage.POWER_FUNCTION:
				sequence_PowerFunction(context, (PowerFunction) semanticObject); 
				return; 
			case CalculationPackage.REFERENCED_INPUT:
				sequence_ReferencedInput(context, (ReferencedInput) semanticObject); 
				return; 
			case CalculationPackage.SET_FUNCTION:
				sequence_SetFunction(context, (SetFunction) semanticObject); 
				return; 
			case CalculationPackage.TYPE_INSTANCE_RESULT:
				sequence_TypeInstanceResult(context, (TypeInstanceResult) semanticObject); 
				return; 
			case CalculationPackage.VALUE_E:
				sequence_ValueE(context, (ValueE) semanticObject); 
				return; 
			case CalculationPackage.VALUE_PI:
				sequence_ValuePi(context, (ValuePi) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns AdditionAndSubtraction
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns AdditionAndSubtraction
	 *
	 * Constraint:
	 *     (left=AdditionAndSubtraction_AdditionAndSubtraction_1_0 (operator=OperatorPlus | operator=OperatorMinus) right=MultiplicationAndDivision)
	 */
	protected void sequence_AdditionAndSubtraction(ISerializationContext context, AdditionAndSubtraction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns AdvancedFunction
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns AdvancedFunction
	 *     MultiplicationAndDivision returns AdvancedFunction
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns AdvancedFunction
	 *     PowerFunction returns AdvancedFunction
	 *     PowerFunction.PowerFunction_1_0 returns AdvancedFunction
	 *     AExpression returns AdvancedFunction
	 *     AAdvancedFunction returns AdvancedFunction
	 *     AdvancedFunction returns AdvancedFunction
	 *
	 * Constraint:
	 *     (operator=ID inputs+=AdditionAndSubtraction inputs+=AdditionAndSubtraction*)
	 */
	protected void sequence_AdvancedFunction(ISerializationContext context, AdvancedFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     EquationResult returns EquationIntermediateResult
	 *     EquationIntermediateResult returns EquationIntermediateResult
	 *
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_EquationIntermediateResult(ISerializationContext context, EquationIntermediateResult semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.EQUATION_INTERMEDIATE_RESULT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.EQUATION_INTERMEDIATE_RESULT__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getEquationIntermediateResultAccess().getNameIDTerminalRuleCall_2_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     EquationSection returns EquationSection
	 *
	 * Constraint:
	 *     ((imports+=Import+ equations+=Equation+) | equations+=Equation+)?
	 */
	protected void sequence_EquationSection(ISerializationContext context, EquationSection semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Equation returns Equation
	 *
	 * Constraint:
	 *     (result=EquationResult expression=AdditionAndSubtraction)
	 */
	protected void sequence_Equation(ISerializationContext context, Equation semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.EQUATION__RESULT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.EQUATION__RESULT));
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.EQUATION__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.EQUATION__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getEquationAccess().getResultEquationResultParserRuleCall_0_0(), semanticObject.eGet(CalculationPackage.Literals.EQUATION__RESULT, false));
		feeder.accept(grammarAccess.getEquationAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0(), semanticObject.eGet(CalculationPackage.Literals.EQUATION__EXPRESSION, false));
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns Function
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns Function
	 *     MultiplicationAndDivision returns Function
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns Function
	 *     PowerFunction returns Function
	 *     PowerFunction.PowerFunction_1_0 returns Function
	 *     AExpression returns Function
	 *     Function returns Function
	 *
	 * Constraint:
	 *     (
	 *         (
	 *             operator=OperatorCos | 
	 *             operator=OperatorSin | 
	 *             operator=OperatorTan | 
	 *             operator=OperatorAtan | 
	 *             operator=OperatorAcos | 
	 *             operator=OperatorAsin | 
	 *             operator=OperatorSqrt | 
	 *             operator=OperatorLog | 
	 *             operator=OperatorLn | 
	 *             operator=OperatorLd | 
	 *             operator=OperatorExp
	 *         ) 
	 *         right=AdditionAndSubtraction
	 *     )
	 */
	protected void sequence_Function(ISerializationContext context, Function semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Import returns Import
	 *
	 * Constraint:
	 *     importedNamespace=[IInstance|QualifiedName]
	 */
	protected void sequence_Import(ISerializationContext context, Import semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.IMPORT__IMPORTED_NAMESPACE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.IMPORT__IMPORTED_NAMESPACE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getImportAccess().getImportedNamespaceIInstanceQualifiedNameParserRuleCall_1_0_1(), semanticObject.eGet(CalculationPackage.Literals.IMPORT__IMPORTED_NAMESPACE, false));
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns MultiplicationAndDivision
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns MultiplicationAndDivision
	 *     MultiplicationAndDivision returns MultiplicationAndDivision
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns MultiplicationAndDivision
	 *
	 * Constraint:
	 *     (left=MultiplicationAndDivision_MultiplicationAndDivision_1_0 (operator=OperatorMultiply | operator=OperatorDivide) right=PowerFunction)
	 */
	protected void sequence_MultiplicationAndDivision(ISerializationContext context, MultiplicationAndDivision semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns NumberLiteral
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns NumberLiteral
	 *     MultiplicationAndDivision returns NumberLiteral
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns NumberLiteral
	 *     PowerFunction returns NumberLiteral
	 *     PowerFunction.PowerFunction_1_0 returns NumberLiteral
	 *     AExpression returns NumberLiteral
	 *     ALiteral returns NumberLiteral
	 *     NumberLiteral returns NumberLiteral
	 *
	 * Constraint:
	 *     value=NumberLiteralString
	 */
	protected void sequence_NumberLiteral(ISerializationContext context, NumberLiteral semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.NUMBER_LITERAL__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.NUMBER_LITERAL__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNumberLiteralAccess().getValueNumberLiteralStringParserRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns Parenthesis
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns Parenthesis
	 *     MultiplicationAndDivision returns Parenthesis
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns Parenthesis
	 *     PowerFunction returns Parenthesis
	 *     PowerFunction.PowerFunction_1_0 returns Parenthesis
	 *     Parenthesis returns Parenthesis
	 *     AExpression returns Parenthesis
	 *
	 * Constraint:
	 *     (operator=OperatorMinus? right=AdditionAndSubtraction)
	 */
	protected void sequence_Parenthesis(ISerializationContext context, Parenthesis semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns PowerFunction
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns PowerFunction
	 *     MultiplicationAndDivision returns PowerFunction
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns PowerFunction
	 *     PowerFunction returns PowerFunction
	 *     PowerFunction.PowerFunction_1_0 returns PowerFunction
	 *
	 * Constraint:
	 *     (left=PowerFunction_PowerFunction_1_0 operator=OperatorPower right=AExpression)
	 */
	protected void sequence_PowerFunction(ISerializationContext context, PowerFunction semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.ALEFT_OP_RIGHT_EXPRESSION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.ALEFT_OP_RIGHT_EXPRESSION__LEFT));
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.ALEFT_OP_RIGHT_EXPRESSION__OPERATOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.ALEFT_OP_RIGHT_EXPRESSION__OPERATOR));
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.ALEFT_OP_RIGHT_EXPRESSION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.ALEFT_OP_RIGHT_EXPRESSION__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0(), semanticObject.getOperator());
		feeder.accept(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0(), semanticObject.eGet(CalculationPackage.Literals.ALEFT_OP_RIGHT_EXPRESSION__RIGHT, false));
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns ReferencedInput
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns ReferencedInput
	 *     MultiplicationAndDivision returns ReferencedInput
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns ReferencedInput
	 *     PowerFunction returns ReferencedInput
	 *     PowerFunction.PowerFunction_1_0 returns ReferencedInput
	 *     AExpression returns ReferencedInput
	 *     ReferencedInput returns ReferencedInput
	 *
	 * Constraint:
	 *     reference=[IEquationInput|QualifiedName]
	 */
	protected void sequence_ReferencedInput(ISerializationContext context, ReferencedInput semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.REFERENCED_INPUT__REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.REFERENCED_INPUT__REFERENCE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputQualifiedNameParserRuleCall_0_1(), semanticObject.eGet(CalculationPackage.Literals.REFERENCED_INPUT__REFERENCE, false));
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns SetFunction
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns SetFunction
	 *     MultiplicationAndDivision returns SetFunction
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns SetFunction
	 *     PowerFunction returns SetFunction
	 *     PowerFunction.PowerFunction_1_0 returns SetFunction
	 *     AExpression returns SetFunction
	 *     AAdvancedFunction returns SetFunction
	 *     SetFunction returns SetFunction
	 *
	 * Constraint:
	 *     (operator=ID typeDefinition=[ATypeDefinition|QualifiedName] depth=INT? filterName=ID?)
	 */
	protected void sequence_SetFunction(ISerializationContext context, SetFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     EquationResult returns TypeInstanceResult
	 *     TypeInstanceResult returns TypeInstanceResult
	 *
	 * Constraint:
	 *     reference=[ATypeInstance|QualifiedName]
	 */
	protected void sequence_TypeInstanceResult(ISerializationContext context, TypeInstanceResult semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.TYPE_INSTANCE_RESULT__REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.TYPE_INSTANCE_RESULT__REFERENCE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceQualifiedNameParserRuleCall_2_0_1(), semanticObject.eGet(CalculationPackage.Literals.TYPE_INSTANCE_RESULT__REFERENCE, false));
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns ValueE
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns ValueE
	 *     MultiplicationAndDivision returns ValueE
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns ValueE
	 *     PowerFunction returns ValueE
	 *     PowerFunction.PowerFunction_1_0 returns ValueE
	 *     AExpression returns ValueE
	 *     ALiteral returns ValueE
	 *     ValueE returns ValueE
	 *
	 * Constraint:
	 *     {ValueE}
	 */
	protected void sequence_ValueE(ISerializationContext context, ValueE semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     AdditionAndSubtraction returns ValuePi
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns ValuePi
	 *     MultiplicationAndDivision returns ValuePi
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns ValuePi
	 *     PowerFunction returns ValuePi
	 *     PowerFunction.PowerFunction_1_0 returns ValuePi
	 *     AExpression returns ValuePi
	 *     ALiteral returns ValuePi
	 *     ValuePi returns ValuePi
	 *
	 * Constraint:
	 *     {ValuePi}
	 */
	protected void sequence_ValuePi(ISerializationContext context, ValuePi semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
