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

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory
 * @model kind="package"
 * @generated
 */
public interface CalculationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "calculation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/calc";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_calc";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CalculationPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer <em>IEquation Definition Section Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationDefinitionSectionContainer()
	 * @generated
	 */
	int IEQUATION_DEFINITION_SECTION_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Equation Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_DEFINITION_SECTION_CONTAINER__EQUATION_DEFINITIONS = 0;

	/**
	 * The number of structural features of the '<em>IEquation Definition Section Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_DEFINITION_SECTION_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IEquation Definition Section Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_DEFINITION_SECTION_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer <em>IEquation Section Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationSectionContainer()
	 * @generated
	 */
	int IEQUATION_SECTION_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Equation Section</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_SECTION_CONTAINER__EQUATION_SECTION = 0;

	/**
	 * The number of structural features of the '<em>IEquation Section Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_SECTION_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IEquation Section Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_SECTION_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl <em>Equation Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getEquationSection()
	 * @generated
	 */
	int EQUATION_SECTION = 2;

	/**
	 * The feature id for the '<em><b>Equation Section</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_SECTION__EQUATION_SECTION = IEQUATION_SECTION_CONTAINER__EQUATION_SECTION;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_SECTION__SUPER_TIS = IEQUATION_SECTION_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_SECTION__IS_INHERITED = IEQUATION_SECTION_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_SECTION__IMPORTS = IEQUATION_SECTION_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Equations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_SECTION__EQUATIONS = IEQUATION_SECTION_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Serialized Statements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_SECTION__SERIALIZED_STATEMENTS = IEQUATION_SECTION_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Equation Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_SECTION_FEATURE_COUNT = IEQUATION_SECTION_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Equation Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_SECTION_OPERATION_COUNT = IEQUATION_SECTION_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ImportImpl <em>Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ImportImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getImport()
	 * @generated
	 */
	int IMPORT = 3;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__SUPER_TIS = InheritancePackage.IINHERITANCE_LINK__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__IS_INHERITED = InheritancePackage.IINHERITANCE_LINK__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Imported Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__IMPORTED_NAMESPACE = InheritancePackage.IINHERITANCE_LINK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_FEATURE_COUNT = InheritancePackage.IINHERITANCE_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_OPERATION_COUNT = InheritancePackage.IINHERITANCE_LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl <em>Equation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getEquation()
	 * @generated
	 */
	int EQUATION = 4;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION__SUPER_TIS = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION__IS_INHERITED = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION__OVERRIDE = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION__EXPRESSION = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION__RESULT = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Result Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION__RESULT_TEXT = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION__DEFINITION = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Equation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_FEATURE_COUNT = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Equation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_OPERATION_COUNT = InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject <em>IQualified Equation Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIQualifiedEquationObject()
	 * @generated
	 */
	int IQUALIFIED_EQUATION_OBJECT = 30;

	/**
	 * The number of structural features of the '<em>IQualified Equation Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IQUALIFIED_EQUATION_OBJECT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IQualified Equation Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IQUALIFIED_EQUATION_OBJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationDefinitionImpl <em>Equation Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationDefinitionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getEquationDefinition()
	 * @generated
	 */
	int EQUATION_DEFINITION = 5;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_DEFINITION__EXPRESSION = IQUALIFIED_EQUATION_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_DEFINITION__RESULT = IQUALIFIED_EQUATION_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Equation Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_DEFINITION_FEATURE_COUNT = IQUALIFIED_EQUATION_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Equation Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_DEFINITION_OPERATION_COUNT = IQUALIFIED_EQUATION_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationResultImpl <em>IEquation Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationResultImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationResult()
	 * @generated
	 */
	int IEQUATION_RESULT = 6;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_RESULT__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_RESULT__SUPER_TIS = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_RESULT__IS_INHERITED = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>IEquation Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_RESULT_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>IEquation Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_RESULT_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionResultImpl <em>IEquation Definition Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionResultImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationDefinitionResult()
	 * @generated
	 */
	int IEQUATION_DEFINITION_RESULT = 7;

	/**
	 * The number of structural features of the '<em>IEquation Definition Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_DEFINITION_RESULT_FEATURE_COUNT = IQUALIFIED_EQUATION_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>IEquation Definition Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_DEFINITION_RESULT_OPERATION_COUNT = IQUALIFIED_EQUATION_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationInputImpl <em>IEquation Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationInputImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationInput()
	 * @generated
	 */
	int IEQUATION_INPUT = 8;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_INPUT__SUPER_TIS = InheritancePackage.IINHERITANCE_LINK__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_INPUT__IS_INHERITED = InheritancePackage.IINHERITANCE_LINK__IS_INHERITED;

	/**
	 * The number of structural features of the '<em>IEquation Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_INPUT_FEATURE_COUNT = InheritancePackage.IINHERITANCE_LINK_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>IEquation Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_INPUT_OPERATION_COUNT = InheritancePackage.IINHERITANCE_LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionInputImpl <em>IEquation Definition Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionInputImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationDefinitionInput()
	 * @generated
	 */
	int IEQUATION_DEFINITION_INPUT = 9;

	/**
	 * The number of structural features of the '<em>IEquation Definition Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_DEFINITION_INPUT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IEquation Definition Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEQUATION_DEFINITION_INPUT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationIntermediateResultImpl <em>Equation Intermediate Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationIntermediateResultImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getEquationIntermediateResult()
	 * @generated
	 */
	int EQUATION_INTERMEDIATE_RESULT = 10;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_INTERMEDIATE_RESULT__UUID = IEQUATION_RESULT__UUID;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_INTERMEDIATE_RESULT__SUPER_TIS = IEQUATION_RESULT__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_INTERMEDIATE_RESULT__IS_INHERITED = IEQUATION_RESULT__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_INTERMEDIATE_RESULT__NAME = IEQUATION_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Equation Intermediate Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_INTERMEDIATE_RESULT_FEATURE_COUNT = IEQUATION_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Equation Intermediate Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUATION_INTERMEDIATE_RESULT_OPERATION_COUNT = IEQUATION_RESULT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.TypeInstanceResultImpl <em>Type Instance Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.TypeInstanceResultImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getTypeInstanceResult()
	 * @generated
	 */
	int TYPE_INSTANCE_RESULT = 11;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_INSTANCE_RESULT__UUID = IEQUATION_RESULT__UUID;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_INSTANCE_RESULT__SUPER_TIS = IEQUATION_RESULT__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_INSTANCE_RESULT__IS_INHERITED = IEQUATION_RESULT__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_INSTANCE_RESULT__REFERENCE = IEQUATION_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Instance Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_INSTANCE_RESULT_FEATURE_COUNT = IEQUATION_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Type Instance Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_INSTANCE_RESULT_OPERATION_COUNT = IEQUATION_RESULT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.TypeDefinitionResultImpl <em>Type Definition Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.TypeDefinitionResultImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getTypeDefinitionResult()
	 * @generated
	 */
	int TYPE_DEFINITION_RESULT = 12;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFINITION_RESULT__REFERENCE = IEQUATION_DEFINITION_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Definition Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFINITION_RESULT_FEATURE_COUNT = IEQUATION_DEFINITION_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Type Definition Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFINITION_RESULT_OPERATION_COUNT = IEQUATION_DEFINITION_RESULT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AExpressionImpl <em>AExpression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AExpressionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAExpression()
	 * @generated
	 */
	int AEXPRESSION = 13;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEXPRESSION__SUPER_TIS = InheritancePackage.IINHERITANCE_LINK__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEXPRESSION__IS_INHERITED = InheritancePackage.IINHERITANCE_LINK__IS_INHERITED;

	/**
	 * The number of structural features of the '<em>AExpression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEXPRESSION_FEATURE_COUNT = InheritancePackage.IINHERITANCE_LINK_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>AExpression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEXPRESSION_OPERATION_COUNT = InheritancePackage.IINHERITANCE_LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ALeftOpRightExpressionImpl <em>ALeft Op Right Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ALeftOpRightExpressionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getALeftOpRightExpression()
	 * @generated
	 */
	int ALEFT_OP_RIGHT_EXPRESSION = 14;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALEFT_OP_RIGHT_EXPRESSION__SUPER_TIS = AEXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALEFT_OP_RIGHT_EXPRESSION__IS_INHERITED = AEXPRESSION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALEFT_OP_RIGHT_EXPRESSION__LEFT = AEXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALEFT_OP_RIGHT_EXPRESSION__OPERATOR = AEXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALEFT_OP_RIGHT_EXPRESSION__RIGHT = AEXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>ALeft Op Right Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALEFT_OP_RIGHT_EXPRESSION_FEATURE_COUNT = AEXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>ALeft Op Right Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALEFT_OP_RIGHT_EXPRESSION_OPERATION_COUNT = AEXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AOpRightExpressionImpl <em>AOp Right Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AOpRightExpressionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAOpRightExpression()
	 * @generated
	 */
	int AOP_RIGHT_EXPRESSION = 15;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOP_RIGHT_EXPRESSION__SUPER_TIS = AEXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOP_RIGHT_EXPRESSION__IS_INHERITED = AEXPRESSION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOP_RIGHT_EXPRESSION__OPERATOR = AEXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOP_RIGHT_EXPRESSION__RIGHT = AEXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>AOp Right Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOP_RIGHT_EXPRESSION_FEATURE_COUNT = AEXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>AOp Right Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AOP_RIGHT_EXPRESSION_OPERATION_COUNT = AEXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AdditionAndSubtractionImpl <em>Addition And Subtraction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AdditionAndSubtractionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAdditionAndSubtraction()
	 * @generated
	 */
	int ADDITION_AND_SUBTRACTION = 16;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION_AND_SUBTRACTION__SUPER_TIS = ALEFT_OP_RIGHT_EXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION_AND_SUBTRACTION__IS_INHERITED = ALEFT_OP_RIGHT_EXPRESSION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION_AND_SUBTRACTION__LEFT = ALEFT_OP_RIGHT_EXPRESSION__LEFT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION_AND_SUBTRACTION__OPERATOR = ALEFT_OP_RIGHT_EXPRESSION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION_AND_SUBTRACTION__RIGHT = ALEFT_OP_RIGHT_EXPRESSION__RIGHT;

	/**
	 * The number of structural features of the '<em>Addition And Subtraction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION_AND_SUBTRACTION_FEATURE_COUNT = ALEFT_OP_RIGHT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Addition And Subtraction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION_AND_SUBTRACTION_OPERATION_COUNT = ALEFT_OP_RIGHT_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.MultiplicationAndDivisionImpl <em>Multiplication And Division</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.MultiplicationAndDivisionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getMultiplicationAndDivision()
	 * @generated
	 */
	int MULTIPLICATION_AND_DIVISION = 17;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION_AND_DIVISION__SUPER_TIS = ALEFT_OP_RIGHT_EXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION_AND_DIVISION__IS_INHERITED = ALEFT_OP_RIGHT_EXPRESSION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION_AND_DIVISION__LEFT = ALEFT_OP_RIGHT_EXPRESSION__LEFT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION_AND_DIVISION__OPERATOR = ALEFT_OP_RIGHT_EXPRESSION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION_AND_DIVISION__RIGHT = ALEFT_OP_RIGHT_EXPRESSION__RIGHT;

	/**
	 * The number of structural features of the '<em>Multiplication And Division</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION_AND_DIVISION_FEATURE_COUNT = ALEFT_OP_RIGHT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Multiplication And Division</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION_AND_DIVISION_OPERATION_COUNT = ALEFT_OP_RIGHT_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.PowerFunctionImpl <em>Power Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.PowerFunctionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getPowerFunction()
	 * @generated
	 */
	int POWER_FUNCTION = 18;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FUNCTION__SUPER_TIS = ALEFT_OP_RIGHT_EXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FUNCTION__IS_INHERITED = ALEFT_OP_RIGHT_EXPRESSION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FUNCTION__LEFT = ALEFT_OP_RIGHT_EXPRESSION__LEFT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FUNCTION__OPERATOR = ALEFT_OP_RIGHT_EXPRESSION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FUNCTION__RIGHT = ALEFT_OP_RIGHT_EXPRESSION__RIGHT;

	/**
	 * The number of structural features of the '<em>Power Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FUNCTION_FEATURE_COUNT = ALEFT_OP_RIGHT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Power Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_FUNCTION_OPERATION_COUNT = ALEFT_OP_RIGHT_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.FunctionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 19;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SUPER_TIS = AOP_RIGHT_EXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__IS_INHERITED = AOP_RIGHT_EXPRESSION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__OPERATOR = AOP_RIGHT_EXPRESSION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__RIGHT = AOP_RIGHT_EXPRESSION__RIGHT;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = AOP_RIGHT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_OPERATION_COUNT = AOP_RIGHT_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AAdvancedFunctionImpl <em>AAdvanced Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AAdvancedFunctionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAAdvancedFunction()
	 * @generated
	 */
	int AADVANCED_FUNCTION = 20;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AADVANCED_FUNCTION__SUPER_TIS = AEXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AADVANCED_FUNCTION__IS_INHERITED = AEXPRESSION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AADVANCED_FUNCTION__OPERATOR = AEXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>AAdvanced Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AADVANCED_FUNCTION_FEATURE_COUNT = AEXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>AAdvanced Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AADVANCED_FUNCTION_OPERATION_COUNT = AEXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AdvancedFunctionImpl <em>Advanced Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AdvancedFunctionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAdvancedFunction()
	 * @generated
	 */
	int ADVANCED_FUNCTION = 21;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVANCED_FUNCTION__SUPER_TIS = AADVANCED_FUNCTION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVANCED_FUNCTION__IS_INHERITED = AADVANCED_FUNCTION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVANCED_FUNCTION__OPERATOR = AADVANCED_FUNCTION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVANCED_FUNCTION__INPUTS = AADVANCED_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Advanced Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVANCED_FUNCTION_FEATURE_COUNT = AADVANCED_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Advanced Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVANCED_FUNCTION_OPERATION_COUNT = AADVANCED_FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.SetFunctionImpl <em>Set Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.SetFunctionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getSetFunction()
	 * @generated
	 */
	int SET_FUNCTION = 22;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_FUNCTION__SUPER_TIS = AADVANCED_FUNCTION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_FUNCTION__IS_INHERITED = AADVANCED_FUNCTION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_FUNCTION__OPERATOR = AADVANCED_FUNCTION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Type Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_FUNCTION__TYPE_DEFINITION = AADVANCED_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Filter Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_FUNCTION__FILTER_NAME = AADVANCED_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_FUNCTION__DEPTH = AADVANCED_FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Set Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_FUNCTION_FEATURE_COUNT = AADVANCED_FUNCTION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Set Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_FUNCTION_OPERATION_COUNT = AADVANCED_FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ParenthesisImpl <em>Parenthesis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ParenthesisImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getParenthesis()
	 * @generated
	 */
	int PARENTHESIS = 23;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIS__SUPER_TIS = AOP_RIGHT_EXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIS__IS_INHERITED = AOP_RIGHT_EXPRESSION__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIS__OPERATOR = AOP_RIGHT_EXPRESSION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIS__RIGHT = AOP_RIGHT_EXPRESSION__RIGHT;

	/**
	 * The number of structural features of the '<em>Parenthesis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIS_FEATURE_COUNT = AOP_RIGHT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Parenthesis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIS_OPERATION_COUNT = AOP_RIGHT_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ALiteralImpl <em>ALiteral</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ALiteralImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getALiteral()
	 * @generated
	 */
	int ALITERAL = 24;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALITERAL__SUPER_TIS = AEXPRESSION__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALITERAL__IS_INHERITED = AEXPRESSION__IS_INHERITED;

	/**
	 * The number of structural features of the '<em>ALiteral</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALITERAL_FEATURE_COUNT = AEXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>ALiteral</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALITERAL_OPERATION_COUNT = AEXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ValuePiImpl <em>Value Pi</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ValuePiImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getValuePi()
	 * @generated
	 */
	int VALUE_PI = 25;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PI__SUPER_TIS = ALITERAL__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PI__IS_INHERITED = ALITERAL__IS_INHERITED;

	/**
	 * The number of structural features of the '<em>Value Pi</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PI_FEATURE_COUNT = ALITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Value Pi</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_PI_OPERATION_COUNT = ALITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ValueEImpl <em>Value E</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ValueEImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getValueE()
	 * @generated
	 */
	int VALUE_E = 26;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_E__SUPER_TIS = ALITERAL__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_E__IS_INHERITED = ALITERAL__IS_INHERITED;

	/**
	 * The number of structural features of the '<em>Value E</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_E_FEATURE_COUNT = ALITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Value E</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_E_OPERATION_COUNT = ALITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.NumberLiteralImpl <em>Number Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.NumberLiteralImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getNumberLiteral()
	 * @generated
	 */
	int NUMBER_LITERAL = 27;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__SUPER_TIS = ALITERAL__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__IS_INHERITED = ALITERAL__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__VALUE = ALITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL_FEATURE_COUNT = ALITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL_OPERATION_COUNT = ALITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ReferencedInputImpl <em>Referenced Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ReferencedInputImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getReferencedInput()
	 * @generated
	 */
	int REFERENCED_INPUT = 28;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INPUT__SUPER_TIS = ALITERAL__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INPUT__IS_INHERITED = ALITERAL__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INPUT__REFERENCE = ALITERAL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INPUT__DEFINITION = ALITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Referenced Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INPUT_FEATURE_COUNT = ALITERAL_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Referenced Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INPUT_OPERATION_COUNT = ALITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ReferencedDefinitionInputImpl <em>Referenced Definition Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ReferencedDefinitionInputImpl
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getReferencedDefinitionInput()
	 * @generated
	 */
	int REFERENCED_DEFINITION_INPUT = 29;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_DEFINITION_INPUT__SUPER_TIS = ALITERAL__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_DEFINITION_INPUT__IS_INHERITED = ALITERAL__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_DEFINITION_INPUT__REFERENCE = ALITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Referenced Definition Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_DEFINITION_INPUT_FEATURE_COUNT = ALITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Referenced Definition Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_DEFINITION_INPUT_OPERATION_COUNT = ALITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.MathOperator <em>Math Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.MathOperator
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getMathOperator()
	 * @generated
	 */
	int MATH_OPERATOR = 31;

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer <em>IEquation Definition Section Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IEquation Definition Section Container</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer
	 * @generated
	 */
	EClass getIEquationDefinitionSectionContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer#getEquationDefinitions <em>Equation Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Equation Definitions</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer#getEquationDefinitions()
	 * @see #getIEquationDefinitionSectionContainer()
	 * @generated
	 */
	EReference getIEquationDefinitionSectionContainer_EquationDefinitions();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer <em>IEquation Section Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IEquation Section Container</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer
	 * @generated
	 */
	EClass getIEquationSectionContainer();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer#getEquationSection <em>Equation Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Equation Section</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer#getEquationSection()
	 * @see #getIEquationSectionContainer()
	 * @generated
	 */
	EReference getIEquationSectionContainer_EquationSection();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationSection <em>Equation Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equation Section</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationSection
	 * @generated
	 */
	EClass getEquationSection();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imports</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getImports()
	 * @see #getEquationSection()
	 * @generated
	 */
	EReference getEquationSection_Imports();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getEquations <em>Equations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Equations</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getEquations()
	 * @see #getEquationSection()
	 * @generated
	 */
	EReference getEquationSection_Equations();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getSerializedStatements <em>Serialized Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Serialized Statements</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationSection#getSerializedStatements()
	 * @see #getEquationSection()
	 * @generated
	 */
	EAttribute getEquationSection_SerializedStatements();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Import
	 * @generated
	 */
	EClass getImport();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.Import#getImportedNamespace <em>Imported Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Imported Namespace</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Import#getImportedNamespace()
	 * @see #getImport()
	 * @generated
	 */
	EReference getImport_ImportedNamespace();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation <em>Equation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equation</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Equation
	 * @generated
	 */
	EClass getEquation();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Equation#getExpression()
	 * @see #getEquation()
	 * @generated
	 */
	EReference getEquation_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Equation#getResult()
	 * @see #getEquation()
	 * @generated
	 */
	EReference getEquation_Result();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getResultText <em>Result Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result Text</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Equation#getResultText()
	 * @see #getEquation()
	 * @generated
	 */
	EAttribute getEquation_ResultText();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.Equation#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Definition</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Equation#getDefinition()
	 * @see #getEquation()
	 * @generated
	 */
	EReference getEquation_Definition();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition <em>Equation Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equation Definition</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition
	 * @generated
	 */
	EClass getEquationDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition#getExpression()
	 * @see #getEquationDefinition()
	 * @generated
	 */
	EReference getEquationDefinition_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition#getResult()
	 * @see #getEquationDefinition()
	 * @generated
	 */
	EReference getEquationDefinition_Result();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult <em>IEquation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IEquation Result</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult
	 * @generated
	 */
	EClass getIEquationResult();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionResult <em>IEquation Definition Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IEquation Definition Result</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionResult
	 * @generated
	 */
	EClass getIEquationDefinitionResult();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput <em>IEquation Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IEquation Input</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput
	 * @generated
	 */
	EClass getIEquationInput();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionInput <em>IEquation Definition Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IEquation Definition Input</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionInput
	 * @generated
	 */
	EClass getIEquationDefinitionInput();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult <em>Equation Intermediate Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equation Intermediate Result</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult
	 * @generated
	 */
	EClass getEquationIntermediateResult();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult#getName()
	 * @see #getEquationIntermediateResult()
	 * @generated
	 */
	EAttribute getEquationIntermediateResult_Name();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult <em>Type Instance Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Instance Result</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult
	 * @generated
	 */
	EClass getTypeInstanceResult();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult#getReference()
	 * @see #getTypeInstanceResult()
	 * @generated
	 */
	EReference getTypeInstanceResult_Reference();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult <em>Type Definition Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Definition Result</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult
	 * @generated
	 */
	EClass getTypeDefinitionResult();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult#getReference()
	 * @see #getTypeDefinitionResult()
	 * @generated
	 */
	EReference getTypeDefinitionResult_Reference();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.AExpression <em>AExpression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AExpression</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AExpression
	 * @generated
	 */
	EClass getAExpression();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression <em>ALeft Op Right Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ALeft Op Right Expression</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression
	 * @generated
	 */
	EClass getALeftOpRightExpression();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression#getLeft()
	 * @see #getALeftOpRightExpression()
	 * @generated
	 */
	EReference getALeftOpRightExpression_Left();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression#getOperator()
	 * @see #getALeftOpRightExpression()
	 * @generated
	 */
	EAttribute getALeftOpRightExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression#getRight()
	 * @see #getALeftOpRightExpression()
	 * @generated
	 */
	EReference getALeftOpRightExpression_Right();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression <em>AOp Right Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AOp Right Expression</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression
	 * @generated
	 */
	EClass getAOpRightExpression();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression#getOperator()
	 * @see #getAOpRightExpression()
	 * @generated
	 */
	EAttribute getAOpRightExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression#getRight()
	 * @see #getAOpRightExpression()
	 * @generated
	 */
	EReference getAOpRightExpression_Right();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.AdditionAndSubtraction <em>Addition And Subtraction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Addition And Subtraction</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AdditionAndSubtraction
	 * @generated
	 */
	EClass getAdditionAndSubtraction();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision <em>Multiplication And Division</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multiplication And Division</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision
	 * @generated
	 */
	EClass getMultiplicationAndDivision();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.PowerFunction <em>Power Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Power Function</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.PowerFunction
	 * @generated
	 */
	EClass getPowerFunction();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction <em>AAdvanced Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AAdvanced Function</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction
	 * @generated
	 */
	EClass getAAdvancedFunction();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction#getOperator()
	 * @see #getAAdvancedFunction()
	 * @generated
	 */
	EAttribute getAAdvancedFunction_Operator();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction <em>Advanced Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Advanced Function</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction
	 * @generated
	 */
	EClass getAdvancedFunction();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inputs</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction#getInputs()
	 * @see #getAdvancedFunction()
	 * @generated
	 */
	EReference getAdvancedFunction_Inputs();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction <em>Set Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set Function</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.SetFunction
	 * @generated
	 */
	EClass getSetFunction();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getTypeDefinition <em>Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Definition</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getTypeDefinition()
	 * @see #getSetFunction()
	 * @generated
	 */
	EReference getSetFunction_TypeDefinition();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getFilterName <em>Filter Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getFilterName()
	 * @see #getSetFunction()
	 * @generated
	 */
	EAttribute getSetFunction_FilterName();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getDepth <em>Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Depth</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.SetFunction#getDepth()
	 * @see #getSetFunction()
	 * @generated
	 */
	EAttribute getSetFunction_Depth();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.Parenthesis <em>Parenthesis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parenthesis</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.Parenthesis
	 * @generated
	 */
	EClass getParenthesis();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.ALiteral <em>ALiteral</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ALiteral</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ALiteral
	 * @generated
	 */
	EClass getALiteral();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.ValuePi <em>Value Pi</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Pi</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ValuePi
	 * @generated
	 */
	EClass getValuePi();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.ValueE <em>Value E</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value E</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ValueE
	 * @generated
	 */
	EClass getValueE();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral <em>Number Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Literal</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral
	 * @generated
	 */
	EClass getNumberLiteral();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral#getValue()
	 * @see #getNumberLiteral()
	 * @generated
	 */
	EAttribute getNumberLiteral_Value();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput <em>Referenced Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referenced Input</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput
	 * @generated
	 */
	EClass getReferencedInput();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput#getReference()
	 * @see #getReferencedInput()
	 * @generated
	 */
	EReference getReferencedInput_Reference();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Definition</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput#getDefinition()
	 * @see #getReferencedInput()
	 * @generated
	 */
	EReference getReferencedInput_Definition();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput <em>Referenced Definition Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referenced Definition Input</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput
	 * @generated
	 */
	EClass getReferencedDefinitionInput();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput#getReference()
	 * @see #getReferencedDefinitionInput()
	 * @generated
	 */
	EReference getReferencedDefinitionInput_Reference();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject <em>IQualified Equation Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IQualified Equation Object</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject
	 * @generated
	 */
	EClass getIQualifiedEquationObject();

	/**
	 * Returns the meta object for enum '{@link de.dlr.sc.virsat.model.dvlm.calculation.MathOperator <em>Math Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Math Operator</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.MathOperator
	 * @generated
	 */
	EEnum getMathOperator();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CalculationFactory getCalculationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer <em>IEquation Definition Section Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationDefinitionSectionContainer()
		 * @generated
		 */
		EClass IEQUATION_DEFINITION_SECTION_CONTAINER = eINSTANCE.getIEquationDefinitionSectionContainer();

		/**
		 * The meta object literal for the '<em><b>Equation Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IEQUATION_DEFINITION_SECTION_CONTAINER__EQUATION_DEFINITIONS = eINSTANCE.getIEquationDefinitionSectionContainer_EquationDefinitions();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer <em>IEquation Section Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationSectionContainer()
		 * @generated
		 */
		EClass IEQUATION_SECTION_CONTAINER = eINSTANCE.getIEquationSectionContainer();

		/**
		 * The meta object literal for the '<em><b>Equation Section</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IEQUATION_SECTION_CONTAINER__EQUATION_SECTION = eINSTANCE.getIEquationSectionContainer_EquationSection();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl <em>Equation Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getEquationSection()
		 * @generated
		 */
		EClass EQUATION_SECTION = eINSTANCE.getEquationSection();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUATION_SECTION__IMPORTS = eINSTANCE.getEquationSection_Imports();

		/**
		 * The meta object literal for the '<em><b>Equations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUATION_SECTION__EQUATIONS = eINSTANCE.getEquationSection_Equations();

		/**
		 * The meta object literal for the '<em><b>Serialized Statements</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUATION_SECTION__SERIALIZED_STATEMENTS = eINSTANCE.getEquationSection_SerializedStatements();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ImportImpl <em>Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ImportImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getImport()
		 * @generated
		 */
		EClass IMPORT = eINSTANCE.getImport();

		/**
		 * The meta object literal for the '<em><b>Imported Namespace</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT__IMPORTED_NAMESPACE = eINSTANCE.getImport_ImportedNamespace();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl <em>Equation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getEquation()
		 * @generated
		 */
		EClass EQUATION = eINSTANCE.getEquation();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUATION__EXPRESSION = eINSTANCE.getEquation_Expression();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUATION__RESULT = eINSTANCE.getEquation_Result();

		/**
		 * The meta object literal for the '<em><b>Result Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUATION__RESULT_TEXT = eINSTANCE.getEquation_ResultText();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUATION__DEFINITION = eINSTANCE.getEquation_Definition();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationDefinitionImpl <em>Equation Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationDefinitionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getEquationDefinition()
		 * @generated
		 */
		EClass EQUATION_DEFINITION = eINSTANCE.getEquationDefinition();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUATION_DEFINITION__EXPRESSION = eINSTANCE.getEquationDefinition_Expression();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EQUATION_DEFINITION__RESULT = eINSTANCE.getEquationDefinition_Result();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationResultImpl <em>IEquation Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationResultImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationResult()
		 * @generated
		 */
		EClass IEQUATION_RESULT = eINSTANCE.getIEquationResult();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionResultImpl <em>IEquation Definition Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionResultImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationDefinitionResult()
		 * @generated
		 */
		EClass IEQUATION_DEFINITION_RESULT = eINSTANCE.getIEquationDefinitionResult();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationInputImpl <em>IEquation Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationInputImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationInput()
		 * @generated
		 */
		EClass IEQUATION_INPUT = eINSTANCE.getIEquationInput();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionInputImpl <em>IEquation Definition Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionInputImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIEquationDefinitionInput()
		 * @generated
		 */
		EClass IEQUATION_DEFINITION_INPUT = eINSTANCE.getIEquationDefinitionInput();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationIntermediateResultImpl <em>Equation Intermediate Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationIntermediateResultImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getEquationIntermediateResult()
		 * @generated
		 */
		EClass EQUATION_INTERMEDIATE_RESULT = eINSTANCE.getEquationIntermediateResult();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUATION_INTERMEDIATE_RESULT__NAME = eINSTANCE.getEquationIntermediateResult_Name();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.TypeInstanceResultImpl <em>Type Instance Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.TypeInstanceResultImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getTypeInstanceResult()
		 * @generated
		 */
		EClass TYPE_INSTANCE_RESULT = eINSTANCE.getTypeInstanceResult();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_INSTANCE_RESULT__REFERENCE = eINSTANCE.getTypeInstanceResult_Reference();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.TypeDefinitionResultImpl <em>Type Definition Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.TypeDefinitionResultImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getTypeDefinitionResult()
		 * @generated
		 */
		EClass TYPE_DEFINITION_RESULT = eINSTANCE.getTypeDefinitionResult();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DEFINITION_RESULT__REFERENCE = eINSTANCE.getTypeDefinitionResult_Reference();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AExpressionImpl <em>AExpression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AExpressionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAExpression()
		 * @generated
		 */
		EClass AEXPRESSION = eINSTANCE.getAExpression();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ALeftOpRightExpressionImpl <em>ALeft Op Right Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ALeftOpRightExpressionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getALeftOpRightExpression()
		 * @generated
		 */
		EClass ALEFT_OP_RIGHT_EXPRESSION = eINSTANCE.getALeftOpRightExpression();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALEFT_OP_RIGHT_EXPRESSION__LEFT = eINSTANCE.getALeftOpRightExpression_Left();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALEFT_OP_RIGHT_EXPRESSION__OPERATOR = eINSTANCE.getALeftOpRightExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALEFT_OP_RIGHT_EXPRESSION__RIGHT = eINSTANCE.getALeftOpRightExpression_Right();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AOpRightExpressionImpl <em>AOp Right Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AOpRightExpressionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAOpRightExpression()
		 * @generated
		 */
		EClass AOP_RIGHT_EXPRESSION = eINSTANCE.getAOpRightExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AOP_RIGHT_EXPRESSION__OPERATOR = eINSTANCE.getAOpRightExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AOP_RIGHT_EXPRESSION__RIGHT = eINSTANCE.getAOpRightExpression_Right();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AdditionAndSubtractionImpl <em>Addition And Subtraction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AdditionAndSubtractionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAdditionAndSubtraction()
		 * @generated
		 */
		EClass ADDITION_AND_SUBTRACTION = eINSTANCE.getAdditionAndSubtraction();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.MultiplicationAndDivisionImpl <em>Multiplication And Division</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.MultiplicationAndDivisionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getMultiplicationAndDivision()
		 * @generated
		 */
		EClass MULTIPLICATION_AND_DIVISION = eINSTANCE.getMultiplicationAndDivision();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.PowerFunctionImpl <em>Power Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.PowerFunctionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getPowerFunction()
		 * @generated
		 */
		EClass POWER_FUNCTION = eINSTANCE.getPowerFunction();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.FunctionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AAdvancedFunctionImpl <em>AAdvanced Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AAdvancedFunctionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAAdvancedFunction()
		 * @generated
		 */
		EClass AADVANCED_FUNCTION = eINSTANCE.getAAdvancedFunction();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AADVANCED_FUNCTION__OPERATOR = eINSTANCE.getAAdvancedFunction_Operator();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.AdvancedFunctionImpl <em>Advanced Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.AdvancedFunctionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getAdvancedFunction()
		 * @generated
		 */
		EClass ADVANCED_FUNCTION = eINSTANCE.getAdvancedFunction();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVANCED_FUNCTION__INPUTS = eINSTANCE.getAdvancedFunction_Inputs();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.SetFunctionImpl <em>Set Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.SetFunctionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getSetFunction()
		 * @generated
		 */
		EClass SET_FUNCTION = eINSTANCE.getSetFunction();

		/**
		 * The meta object literal for the '<em><b>Type Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SET_FUNCTION__TYPE_DEFINITION = eINSTANCE.getSetFunction_TypeDefinition();

		/**
		 * The meta object literal for the '<em><b>Filter Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SET_FUNCTION__FILTER_NAME = eINSTANCE.getSetFunction_FilterName();

		/**
		 * The meta object literal for the '<em><b>Depth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SET_FUNCTION__DEPTH = eINSTANCE.getSetFunction_Depth();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ParenthesisImpl <em>Parenthesis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ParenthesisImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getParenthesis()
		 * @generated
		 */
		EClass PARENTHESIS = eINSTANCE.getParenthesis();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ALiteralImpl <em>ALiteral</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ALiteralImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getALiteral()
		 * @generated
		 */
		EClass ALITERAL = eINSTANCE.getALiteral();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ValuePiImpl <em>Value Pi</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ValuePiImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getValuePi()
		 * @generated
		 */
		EClass VALUE_PI = eINSTANCE.getValuePi();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ValueEImpl <em>Value E</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ValueEImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getValueE()
		 * @generated
		 */
		EClass VALUE_E = eINSTANCE.getValueE();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.NumberLiteralImpl <em>Number Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.NumberLiteralImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getNumberLiteral()
		 * @generated
		 */
		EClass NUMBER_LITERAL = eINSTANCE.getNumberLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_LITERAL__VALUE = eINSTANCE.getNumberLiteral_Value();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ReferencedInputImpl <em>Referenced Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ReferencedInputImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getReferencedInput()
		 * @generated
		 */
		EClass REFERENCED_INPUT = eINSTANCE.getReferencedInput();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCED_INPUT__REFERENCE = eINSTANCE.getReferencedInput_Reference();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCED_INPUT__DEFINITION = eINSTANCE.getReferencedInput_Definition();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ReferencedDefinitionInputImpl <em>Referenced Definition Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.ReferencedDefinitionInputImpl
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getReferencedDefinitionInput()
		 * @generated
		 */
		EClass REFERENCED_DEFINITION_INPUT = eINSTANCE.getReferencedDefinitionInput();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCED_DEFINITION_INPUT__REFERENCE = eINSTANCE.getReferencedDefinitionInput_Reference();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject <em>IQualified Equation Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getIQualifiedEquationObject()
		 * @generated
		 */
		EClass IQUALIFIED_EQUATION_OBJECT = eINSTANCE.getIQualifiedEquationObject();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.calculation.MathOperator <em>Math Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.MathOperator
		 * @see de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl#getMathOperator()
		 * @generated
		 */
		EEnum MATH_OPERATOR = eINSTANCE.getMathOperator();

	}

} //CalculationPackage
