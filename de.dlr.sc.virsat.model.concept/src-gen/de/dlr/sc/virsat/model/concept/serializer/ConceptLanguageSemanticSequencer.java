/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.serializer;

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.concept.services.ConceptLanguageGrammarAccess;
import de.dlr.sc.virsat.model.dvlm.calculation.AdditionAndSubtraction;
import de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.Function;
import de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.Parenthesis;
import de.dlr.sc.virsat.model.dvlm.calculation.PowerFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.ValueE;
import de.dlr.sc.virsat.model.dvlm.calculation.ValuePi;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.VerificationTypeSpecification;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport;
import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
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
public class ConceptLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private ConceptLanguageGrammarAccess grammarAccess;
	
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
			case CalculationPackage.EQUATION_DEFINITION:
				sequence_EquationDefinition(context, (EquationDefinition) semanticObject); 
				return; 
			case CalculationPackage.EQUATION_INTERMEDIATE_RESULT:
				sequence_EquationIntermediateResult(context, (EquationIntermediateResult) semanticObject); 
				return; 
			case CalculationPackage.FUNCTION:
				sequence_Function(context, (Function) semanticObject); 
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
			case CalculationPackage.REFERENCED_DEFINITION_INPUT:
				sequence_ReferencedDefinitionInput(context, (ReferencedDefinitionInput) semanticObject); 
				return; 
			case CalculationPackage.SET_FUNCTION:
				sequence_SetFunction(context, (SetFunction) semanticObject); 
				return; 
			case CalculationPackage.TYPE_DEFINITION_RESULT:
				sequence_TypeDefinitionResult(context, (TypeDefinitionResult) semanticObject); 
				return; 
			case CalculationPackage.VALUE_E:
				sequence_ValueE(context, (ValueE) semanticObject); 
				return; 
			case CalculationPackage.VALUE_PI:
				sequence_ValuePi(context, (ValuePi) semanticObject); 
				return; 
			}
		else if (epackage == CategoriesPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case CategoriesPackage.CATEGORY:
				sequence_Category(context, (Category) semanticObject); 
				return; 
			}
		else if (epackage == ConceptsPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case ConceptsPackage.CONCEPT:
				sequence_Concept(context, (Concept) semanticObject); 
				return; 
			case ConceptsPackage.CONCEPT_IMPORT:
				sequence_ConceptImport(context, (ConceptImport) semanticObject); 
				return; 
			case ConceptsPackage.ECORE_IMPORT:
				sequence_EcoreImport(context, (EcoreImport) semanticObject); 
				return; 
			}
		else if (epackage == PropertydefinitionsPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case PropertydefinitionsPackage.BOOLEAN_PROPERTY:
				sequence_BooleanProperty(context, (BooleanProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.COMPOSED_PROPERTY:
				sequence_ComposedProperty(context, (ComposedProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.DYNAMIC_ARRAY_MODIFIER:
				sequence_DynmaicArrayModifier(context, (DynamicArrayModifier) semanticObject); 
				return; 
			case PropertydefinitionsPackage.EREFERENCE_PROPERTY:
				sequence_EReferenceProperty(context, (EReferenceProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.ENUM_PROPERTY:
				sequence_EnumProperty(context, (EnumProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.ENUM_VALUE_DEFINITION:
				sequence_EnumValueDefinition(context, (EnumValueDefinition) semanticObject); 
				return; 
			case PropertydefinitionsPackage.FLOAT_PROPERTY:
				sequence_FloatProperty(context, (FloatProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.INT_PROPERTY:
				sequence_IntProperty(context, (IntProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.REFERENCE_PROPERTY:
				sequence_ReferenceProperty(context, (ReferenceProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.RESOURCE_PROPERTY:
				sequence_ResourceProperty(context, (ResourceProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.STATIC_ARRAY_MODIFIER:
				sequence_StaticArrayModifier(context, (StaticArrayModifier) semanticObject); 
				return; 
			case PropertydefinitionsPackage.STRING_PROPERTY:
				sequence_StringProperty(context, (StringProperty) semanticObject); 
				return; 
			case PropertydefinitionsPackage.VERIFICATION_TYPE_SPECIFICATION:
				sequence_VerificationTypeSpecification(context, (VerificationTypeSpecification) semanticObject); 
				return; 
			}
		else if (epackage == StructuralPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case StructuralPackage.GENERAL_RELATION:
				sequence_GeneralRelation(context, (GeneralRelation) semanticObject); 
				return; 
			case StructuralPackage.STRUCTURAL_ELEMENT:
				sequence_StructuralElement(context, (StructuralElement) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     AdditionAndSubtraction returns AdditionAndSubtraction
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns AdditionAndSubtraction
	 *
	 * Constraint:
	 *     (left=AdditionAndSubtraction_AdditionAndSubtraction_1_0 (operator=OperatorPlus | operator=OperatorMinus) right=MultiplicationAndDivision)
	 * </pre>
	 */
	protected void sequence_AdditionAndSubtraction(ISerializationContext context, AdditionAndSubtraction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
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
	 * </pre>
	 */
	protected void sequence_AdvancedFunction(ISerializationContext context, AdvancedFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AProperty returns BooleanProperty
	 *     BooleanProperty returns BooleanProperty
	 *
	 * Constraint:
	 *     (name=ID arrayModifier=ArrayModifier? (description=EString | verification=VerificationTypeSpecification | defaultValue=BooleanLiteralString)*)
	 * </pre>
	 */
	protected void sequence_BooleanProperty(ISerializationContext context, BooleanProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Category returns Category
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         (extendsCategory=[Category|QualifiedName] | shortName=ID | description=EString)* 
	 *         isAbstract?='IsAbstract'? 
	 *         (
	 *             (
	 *                 isVerification?='IsVerification' | 
	 *                 (applicableFor+=[StructuralElement|QualifiedName] applicableFor+=[StructuralElement|QualifiedName]*) | 
	 *                 isApplicableForAll?='All' | 
	 *                 cardinality=INT
	 *             )? 
	 *             isAbstract?='IsAbstract'?
	 *         )* 
	 *         properties+=AProperty* 
	 *         equationDefinitions+=EquationDefinition*
	 *     )
	 * </pre>
	 */
	protected void sequence_Category(ISerializationContext context, Category semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AProperty returns ComposedProperty
	 *     ComposedProperty returns ComposedProperty
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         arrayModifier=ArrayModifier? 
	 *         type=[Category|QualifiedName] 
	 *         (description=EString | quantityKindName=EString | verification=VerificationTypeSpecification | unitName=EString)*
	 *     )
	 * </pre>
	 */
	protected void sequence_ComposedProperty(ISerializationContext context, ComposedProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ConceptImport returns ConceptImport
	 *
	 * Constraint:
	 *     importedNamespace=QualifiedNameWithWildcard
	 * </pre>
	 */
	protected void sequence_ConceptImport(ISerializationContext context, ConceptImport semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ConceptsPackage.Literals.CONCEPT_IMPORT__IMPORTED_NAMESPACE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConceptsPackage.Literals.CONCEPT_IMPORT__IMPORTED_NAMESPACE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getConceptImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_2_0(), semanticObject.getImportedNamespace());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Concept returns Concept
	 *
	 * Constraint:
	 *     (
	 *         name=QualifiedName 
	 *         (displayName=EString | version=Version | beta?='beta' | description=EString | DMF?='hasDMF')* 
	 *         imports+=ConceptImport? 
	 *         (ecoreImports+=EcoreImport? imports+=ConceptImport?)* 
	 *         structuralElements+=StructuralElement* 
	 *         relations+=ARelation* 
	 *         categories+=Category*
	 *     )
	 * </pre>
	 */
	protected void sequence_Concept(ISerializationContext context, Concept semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ArrayModifier returns DynamicArrayModifier
	 *     DynmaicArrayModifier returns DynamicArrayModifier
	 *
	 * Constraint:
	 *     {DynamicArrayModifier}
	 * </pre>
	 */
	protected void sequence_DynmaicArrayModifier(ISerializationContext context, DynamicArrayModifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AProperty returns EReferenceProperty
	 *     EReferenceProperty returns EReferenceProperty
	 *
	 * Constraint:
	 *     (name=ID arrayModifier=ArrayModifier? referenceType=[EClass|QualifiedName] (description=EString | verification=VerificationTypeSpecification)*)
	 * </pre>
	 */
	protected void sequence_EReferenceProperty(ISerializationContext context, EReferenceProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EcoreImport returns EcoreImport
	 *
	 * Constraint:
	 *     (importedNsURI=STRING importedGenModel=STRING?)
	 * </pre>
	 */
	protected void sequence_EcoreImport(ISerializationContext context, EcoreImport semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AProperty returns EnumProperty
	 *     EnumProperty returns EnumProperty
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         arrayModifier=ArrayModifier? 
	 *         (
	 *             (description=EString | quantityKindName=EString | unitName=EString | verification=VerificationTypeSpecification | defaultValue=[EnumValueDefinition|ID])? 
	 *             (values+=EnumValueDefinition values+=EnumValueDefinition*)?
	 *         )+
	 *     )
	 * </pre>
	 */
	protected void sequence_EnumProperty(ISerializationContext context, EnumProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EnumValueDefinition returns EnumValueDefinition
	 *
	 * Constraint:
	 *     (name=ID (value=EString | value=FloatLiteralString))
	 * </pre>
	 */
	protected void sequence_EnumValueDefinition(ISerializationContext context, EnumValueDefinition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EquationDefinition returns EquationDefinition
	 *
	 * Constraint:
	 *     (result=EquationDefinitionResult expression=AdditionAndSubtraction)
	 * </pre>
	 */
	protected void sequence_EquationDefinition(ISerializationContext context, EquationDefinition semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.EQUATION_DEFINITION__RESULT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.EQUATION_DEFINITION__RESULT));
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.EQUATION_DEFINITION__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.EQUATION_DEFINITION__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getEquationDefinitionAccess().getResultEquationDefinitionResultParserRuleCall_0_0(), semanticObject.eGet(CalculationPackage.Literals.EQUATION_DEFINITION__RESULT, false));
		feeder.accept(grammarAccess.getEquationDefinitionAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0(), semanticObject.eGet(CalculationPackage.Literals.EQUATION_DEFINITION__EXPRESSION, false));
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EquationDefinitionResult returns EquationIntermediateResult
	 *     EquationIntermediateResult returns EquationIntermediateResult
	 *
	 * Constraint:
	 *     name=ID
	 * </pre>
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
	 * <pre>
	 * Contexts:
	 *     AProperty returns FloatProperty
	 *     FloatProperty returns FloatProperty
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         arrayModifier=ArrayModifier? 
	 *         (description=EString | defaultValue=FloatLiteralString | quantityKindName=EString | verification=VerificationTypeSpecification | unitName=EString)*
	 *     )
	 * </pre>
	 */
	protected void sequence_FloatProperty(ISerializationContext context, FloatProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
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
	 * </pre>
	 */
	protected void sequence_Function(ISerializationContext context, Function semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     GeneralRelation returns GeneralRelation
	 *     ARelation returns GeneralRelation
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         description=EString? 
	 *         referencedType=[StructuralElement|QualifiedName] 
	 *         ((applicableFor+=[StructuralElement|QualifiedName] applicableFor+=[StructuralElement|QualifiedName]*) | isApplicableForAll?='All')? 
	 *         cardinality=INT?
	 *     )
	 * </pre>
	 */
	protected void sequence_GeneralRelation(ISerializationContext context, GeneralRelation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AProperty returns IntProperty
	 *     IntProperty returns IntProperty
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         arrayModifier=ArrayModifier? 
	 *         (description=EString | defaultValue=IntLiteralString | quantityKindName=EString | verification=VerificationTypeSpecification | unitName=EString)*
	 *     )
	 * </pre>
	 */
	protected void sequence_IntProperty(ISerializationContext context, IntProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AdditionAndSubtraction returns MultiplicationAndDivision
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns MultiplicationAndDivision
	 *     MultiplicationAndDivision returns MultiplicationAndDivision
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns MultiplicationAndDivision
	 *
	 * Constraint:
	 *     (left=MultiplicationAndDivision_MultiplicationAndDivision_1_0 (operator=OperatorMultiply | operator=OperatorDivide) right=PowerFunction)
	 * </pre>
	 */
	protected void sequence_MultiplicationAndDivision(ISerializationContext context, MultiplicationAndDivision semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
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
	 *     value=FloatLiteralString
	 * </pre>
	 */
	protected void sequence_NumberLiteral(ISerializationContext context, NumberLiteral semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.NUMBER_LITERAL__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.NUMBER_LITERAL__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNumberLiteralAccess().getValueFloatLiteralStringParserRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
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
	 * </pre>
	 */
	protected void sequence_Parenthesis(ISerializationContext context, Parenthesis semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
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
	 * </pre>
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
	 * <pre>
	 * Contexts:
	 *     AProperty returns ReferenceProperty
	 *     ReferenceProperty returns ReferenceProperty
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         arrayModifier=ArrayModifier? 
	 *         referenceType=[ATypeDefinition|QualifiedName] 
	 *         (description=EString | verification=VerificationTypeSpecification)*
	 *     )
	 * </pre>
	 */
	protected void sequence_ReferenceProperty(ISerializationContext context, ReferenceProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AdditionAndSubtraction returns ReferencedDefinitionInput
	 *     AdditionAndSubtraction.AdditionAndSubtraction_1_0 returns ReferencedDefinitionInput
	 *     MultiplicationAndDivision returns ReferencedDefinitionInput
	 *     MultiplicationAndDivision.MultiplicationAndDivision_1_0 returns ReferencedDefinitionInput
	 *     PowerFunction returns ReferencedDefinitionInput
	 *     PowerFunction.PowerFunction_1_0 returns ReferencedDefinitionInput
	 *     AExpression returns ReferencedDefinitionInput
	 *     ReferencedDefinitionInput returns ReferencedDefinitionInput
	 *
	 * Constraint:
	 *     reference=[IEquationDefinitionInput|QualifiedName]
	 * </pre>
	 */
	protected void sequence_ReferencedDefinitionInput(ISerializationContext context, ReferencedDefinitionInput semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.REFERENCED_DEFINITION_INPUT__REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.REFERENCED_DEFINITION_INPUT__REFERENCE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getReferencedDefinitionInputAccess().getReferenceIEquationDefinitionInputQualifiedNameParserRuleCall_0_1(), semanticObject.eGet(CalculationPackage.Literals.REFERENCED_DEFINITION_INPUT__REFERENCE, false));
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AProperty returns ResourceProperty
	 *     ResourceProperty returns ResourceProperty
	 *
	 * Constraint:
	 *     (name=ID arrayModifier=ArrayModifier? (description=EString | verification=VerificationTypeSpecification)*)
	 * </pre>
	 */
	protected void sequence_ResourceProperty(ISerializationContext context, ResourceProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
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
	 * </pre>
	 */
	protected void sequence_SetFunction(ISerializationContext context, SetFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ArrayModifier returns StaticArrayModifier
	 *     StaticArrayModifier returns StaticArrayModifier
	 *
	 * Constraint:
	 *     arraySize=INT
	 * </pre>
	 */
	protected void sequence_StaticArrayModifier(ISerializationContext context, StaticArrayModifier semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PropertydefinitionsPackage.Literals.STATIC_ARRAY_MODIFIER__ARRAY_SIZE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PropertydefinitionsPackage.Literals.STATIC_ARRAY_MODIFIER__ARRAY_SIZE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStaticArrayModifierAccess().getArraySizeINTTerminalRuleCall_1_0(), semanticObject.getArraySize());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AProperty returns StringProperty
	 *     StringProperty returns StringProperty
	 *
	 * Constraint:
	 *     (name=ID arrayModifier=ArrayModifier? (description=EString | verification=VerificationTypeSpecification | defaultValue=EString)*)
	 * </pre>
	 */
	protected void sequence_StringProperty(ISerializationContext context, StringProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     StructuralElement returns StructuralElement
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         (shortName=ID | description=EString)* 
	 *         isRootStructuralElement?='IsRootStructuralElement'? 
	 *         (
	 *             (
	 *                 (canInheritFrom+=[StructuralElement|QualifiedName] canInheritFrom+=[StructuralElement|QualifiedName]*) | 
	 *                 isCanInheritFromAll?='All' | 
	 *                 (applicableFor+=[StructuralElement|QualifiedName] applicableFor+=[StructuralElement|QualifiedName]*) | 
	 *                 isApplicableForAll?='All' | 
	 *                 cardinality=INT
	 *             )? 
	 *             isRootStructuralElement?='IsRootStructuralElement'?
	 *         )*
	 *     )
	 * </pre>
	 */
	protected void sequence_StructuralElement(ISerializationContext context, StructuralElement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EquationDefinitionResult returns TypeDefinitionResult
	 *     TypeDefinitionResult returns TypeDefinitionResult
	 *
	 * Constraint:
	 *     reference=[ATypeDefinition|QualifiedName]
	 * </pre>
	 */
	protected void sequence_TypeDefinitionResult(ISerializationContext context, TypeDefinitionResult semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CalculationPackage.Literals.TYPE_DEFINITION_RESULT__REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalculationPackage.Literals.TYPE_DEFINITION_RESULT__REFERENCE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getTypeDefinitionResultAccess().getReferenceATypeDefinitionQualifiedNameParserRuleCall_2_0_1(), semanticObject.eGet(CalculationPackage.Literals.TYPE_DEFINITION_RESULT__REFERENCE, false));
		feeder.finish();
	}
	
	
	/**
	 * <pre>
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
	 * </pre>
	 */
	protected void sequence_ValueE(ISerializationContext context, ValueE semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
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
	 * </pre>
	 */
	protected void sequence_ValuePi(ISerializationContext context, ValuePi semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     VerificationSpecification returns VerificationTypeSpecification
	 *     VerificationTypeSpecification returns VerificationTypeSpecification
	 *
	 * Constraint:
	 *     verificationType=[ATypeDefinition|QualifiedName]
	 * </pre>
	 */
	protected void sequence_VerificationTypeSpecification(ISerializationContext context, VerificationTypeSpecification semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PropertydefinitionsPackage.Literals.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PropertydefinitionsPackage.Literals.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getVerificationTypeSpecificationAccess().getVerificationTypeATypeDefinitionQualifiedNameParserRuleCall_0_1(), semanticObject.eGet(PropertydefinitionsPackage.Literals.VERIFICATION_TYPE_SPECIFICATION__VERIFICATION_TYPE, false));
		feeder.finish();
	}
	
	
}
