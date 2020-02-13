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

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;

import de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.ALiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.AdditionAndSubtraction;
import de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.Function;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;
import de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject;
import de.dlr.sc.virsat.model.dvlm.calculation.Import;
import de.dlr.sc.virsat.model.dvlm.calculation.MathOperator;
import de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.Parenthesis;
import de.dlr.sc.virsat.model.dvlm.calculation.PowerFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.calculation.ValueE;
import de.dlr.sc.virsat.model.dvlm.calculation.ValuePi;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;

import de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl;

import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;

import de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl;

import de.dlr.sc.virsat.model.dvlm.dmf.DmfPackage;
import de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfPackageImpl;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

import de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl;

import de.dlr.sc.virsat.model.dvlm.impl.DVLMPackageImpl;

import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;

import de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl;

import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;

import de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl;

import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;

import de.dlr.sc.virsat.model.dvlm.roles.impl.RolesPackageImpl;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

import de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl;

import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.TypesPackageImpl;

import de.dlr.sc.virsat.model.dvlm.units.UnitsPackage;

import de.dlr.sc.virsat.model.dvlm.units.impl.UnitsPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CalculationPackageImpl extends EPackageImpl implements CalculationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iEquationDefinitionSectionContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iEquationSectionContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equationSectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equationDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iEquationResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iEquationDefinitionResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iEquationInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iEquationDefinitionInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equationIntermediateResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeInstanceResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDefinitionResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aLeftOpRightExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aOpRightExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass additionAndSubtractionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiplicationAndDivisionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass powerFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aAdvancedFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass advancedFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parenthesisEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valuePiEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referencedInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referencedDefinitionInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iQualifiedEquationObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum mathOperatorEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CalculationPackageImpl() {
		super(eNS_URI, CalculationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CalculationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CalculationPackage init() {
		if (isInited) return (CalculationPackage)EPackage.Registry.INSTANCE.getEPackage(CalculationPackage.eNS_URI);

		// Obtain or create and register package
		CalculationPackageImpl theCalculationPackage = (CalculationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CalculationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CalculationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		DVLMPackageImpl theDVLMPackage = (DVLMPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DVLMPackage.eNS_URI) instanceof DVLMPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DVLMPackage.eNS_URI) : DVLMPackage.eINSTANCE);
		StructuralPackageImpl theStructuralPackage = (StructuralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StructuralPackage.eNS_URI) instanceof StructuralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StructuralPackage.eNS_URI) : StructuralPackage.eINSTANCE);
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
		ConceptsPackageImpl theConceptsPackage = (ConceptsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConceptsPackage.eNS_URI) instanceof ConceptsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConceptsPackage.eNS_URI) : ConceptsPackage.eINSTANCE);
		RolesPackageImpl theRolesPackage = (RolesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RolesPackage.eNS_URI) instanceof RolesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RolesPackage.eNS_URI) : RolesPackage.eINSTANCE);
		UnitsPackageImpl theUnitsPackage = (UnitsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UnitsPackage.eNS_URI) instanceof UnitsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UnitsPackage.eNS_URI) : UnitsPackage.eINSTANCE);
		TypesPackageImpl theTypesPackage = (TypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) : TypesPackage.eINSTANCE);
		CategoriesPackageImpl theCategoriesPackage = (CategoriesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI) instanceof CategoriesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI) : CategoriesPackage.eINSTANCE);
		PropertydefinitionsPackageImpl thePropertydefinitionsPackage = (PropertydefinitionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PropertydefinitionsPackage.eNS_URI) instanceof PropertydefinitionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PropertydefinitionsPackage.eNS_URI) : PropertydefinitionsPackage.eINSTANCE);
		PropertyinstancesPackageImpl thePropertyinstancesPackage = (PropertyinstancesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PropertyinstancesPackage.eNS_URI) instanceof PropertyinstancesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PropertyinstancesPackage.eNS_URI) : PropertyinstancesPackage.eINSTANCE);
		QudvPackageImpl theQudvPackage = (QudvPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QudvPackage.eNS_URI) instanceof QudvPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QudvPackage.eNS_URI) : QudvPackage.eINSTANCE);
		InheritancePackageImpl theInheritancePackage = (InheritancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI) instanceof InheritancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI) : InheritancePackage.eINSTANCE);
		DmfPackageImpl theDmfPackage = (DmfPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DmfPackage.eNS_URI) instanceof DmfPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DmfPackage.eNS_URI) : DmfPackage.eINSTANCE);

		// Create package meta-data objects
		theCalculationPackage.createPackageContents();
		theDVLMPackage.createPackageContents();
		theStructuralPackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theConceptsPackage.createPackageContents();
		theRolesPackage.createPackageContents();
		theUnitsPackage.createPackageContents();
		theTypesPackage.createPackageContents();
		theCategoriesPackage.createPackageContents();
		thePropertydefinitionsPackage.createPackageContents();
		thePropertyinstancesPackage.createPackageContents();
		theQudvPackage.createPackageContents();
		theInheritancePackage.createPackageContents();
		theDmfPackage.createPackageContents();

		// Initialize created meta-data
		theCalculationPackage.initializePackageContents();
		theDVLMPackage.initializePackageContents();
		theStructuralPackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theConceptsPackage.initializePackageContents();
		theRolesPackage.initializePackageContents();
		theUnitsPackage.initializePackageContents();
		theTypesPackage.initializePackageContents();
		theCategoriesPackage.initializePackageContents();
		thePropertydefinitionsPackage.initializePackageContents();
		thePropertyinstancesPackage.initializePackageContents();
		theQudvPackage.initializePackageContents();
		theInheritancePackage.initializePackageContents();
		theDmfPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCalculationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CalculationPackage.eNS_URI, theCalculationPackage);
		return theCalculationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIEquationDefinitionSectionContainer() {
		return iEquationDefinitionSectionContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIEquationDefinitionSectionContainer_EquationDefinitions() {
		return (EReference)iEquationDefinitionSectionContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIEquationSectionContainer() {
		return iEquationSectionContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIEquationSectionContainer_EquationSection() {
		return (EReference)iEquationSectionContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEquationSection() {
		return equationSectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquationSection_Imports() {
		return (EReference)equationSectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquationSection_Equations() {
		return (EReference)equationSectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEquationSection_SerializedStatements() {
		return (EAttribute)equationSectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImport() {
		return importEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImport_ImportedNamespace() {
		return (EReference)importEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEquation() {
		return equationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquation_Expression() {
		return (EReference)equationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquation_Result() {
		return (EReference)equationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEquation_ResultText() {
		return (EAttribute)equationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquation_Definition() {
		return (EReference)equationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEquationDefinition() {
		return equationDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquationDefinition_Expression() {
		return (EReference)equationDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEquationDefinition_Result() {
		return (EReference)equationDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIEquationResult() {
		return iEquationResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIEquationDefinitionResult() {
		return iEquationDefinitionResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIEquationInput() {
		return iEquationInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIEquationDefinitionInput() {
		return iEquationDefinitionInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEquationIntermediateResult() {
		return equationIntermediateResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEquationIntermediateResult_Name() {
		return (EAttribute)equationIntermediateResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeInstanceResult() {
		return typeInstanceResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeInstanceResult_Reference() {
		return (EReference)typeInstanceResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDefinitionResult() {
		return typeDefinitionResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDefinitionResult_Reference() {
		return (EReference)typeDefinitionResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAExpression() {
		return aExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getALeftOpRightExpression() {
		return aLeftOpRightExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getALeftOpRightExpression_Left() {
		return (EReference)aLeftOpRightExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getALeftOpRightExpression_Operator() {
		return (EAttribute)aLeftOpRightExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getALeftOpRightExpression_Right() {
		return (EReference)aLeftOpRightExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAOpRightExpression() {
		return aOpRightExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAOpRightExpression_Operator() {
		return (EAttribute)aOpRightExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAOpRightExpression_Right() {
		return (EReference)aOpRightExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdditionAndSubtraction() {
		return additionAndSubtractionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiplicationAndDivision() {
		return multiplicationAndDivisionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPowerFunction() {
		return powerFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAAdvancedFunction() {
		return aAdvancedFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAAdvancedFunction_Operator() {
		return (EAttribute)aAdvancedFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdvancedFunction() {
		return advancedFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdvancedFunction_Inputs() {
		return (EReference)advancedFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSetFunction() {
		return setFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSetFunction_TypeDefinition() {
		return (EReference)setFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSetFunction_FilterName() {
		return (EAttribute)setFunctionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSetFunction_Depth() {
		return (EAttribute)setFunctionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParenthesis() {
		return parenthesisEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getALiteral() {
		return aLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValuePi() {
		return valuePiEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueE() {
		return valueEEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumberLiteral() {
		return numberLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumberLiteral_Value() {
		return (EAttribute)numberLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferencedInput() {
		return referencedInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferencedInput_Reference() {
		return (EReference)referencedInputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferencedInput_Definition() {
		return (EReference)referencedInputEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferencedDefinitionInput() {
		return referencedDefinitionInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferencedDefinitionInput_Reference() {
		return (EReference)referencedDefinitionInputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIQualifiedEquationObject() {
		return iQualifiedEquationObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMathOperator() {
		return mathOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CalculationFactory getCalculationFactory() {
		return (CalculationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		iEquationDefinitionSectionContainerEClass = createEClass(IEQUATION_DEFINITION_SECTION_CONTAINER);
		createEReference(iEquationDefinitionSectionContainerEClass, IEQUATION_DEFINITION_SECTION_CONTAINER__EQUATION_DEFINITIONS);

		iEquationSectionContainerEClass = createEClass(IEQUATION_SECTION_CONTAINER);
		createEReference(iEquationSectionContainerEClass, IEQUATION_SECTION_CONTAINER__EQUATION_SECTION);

		equationSectionEClass = createEClass(EQUATION_SECTION);
		createEReference(equationSectionEClass, EQUATION_SECTION__IMPORTS);
		createEReference(equationSectionEClass, EQUATION_SECTION__EQUATIONS);
		createEAttribute(equationSectionEClass, EQUATION_SECTION__SERIALIZED_STATEMENTS);

		importEClass = createEClass(IMPORT);
		createEReference(importEClass, IMPORT__IMPORTED_NAMESPACE);

		equationEClass = createEClass(EQUATION);
		createEReference(equationEClass, EQUATION__EXPRESSION);
		createEReference(equationEClass, EQUATION__RESULT);
		createEAttribute(equationEClass, EQUATION__RESULT_TEXT);
		createEReference(equationEClass, EQUATION__DEFINITION);

		equationDefinitionEClass = createEClass(EQUATION_DEFINITION);
		createEReference(equationDefinitionEClass, EQUATION_DEFINITION__EXPRESSION);
		createEReference(equationDefinitionEClass, EQUATION_DEFINITION__RESULT);

		iEquationResultEClass = createEClass(IEQUATION_RESULT);

		iEquationDefinitionResultEClass = createEClass(IEQUATION_DEFINITION_RESULT);

		iEquationInputEClass = createEClass(IEQUATION_INPUT);

		iEquationDefinitionInputEClass = createEClass(IEQUATION_DEFINITION_INPUT);

		equationIntermediateResultEClass = createEClass(EQUATION_INTERMEDIATE_RESULT);
		createEAttribute(equationIntermediateResultEClass, EQUATION_INTERMEDIATE_RESULT__NAME);

		typeInstanceResultEClass = createEClass(TYPE_INSTANCE_RESULT);
		createEReference(typeInstanceResultEClass, TYPE_INSTANCE_RESULT__REFERENCE);

		typeDefinitionResultEClass = createEClass(TYPE_DEFINITION_RESULT);
		createEReference(typeDefinitionResultEClass, TYPE_DEFINITION_RESULT__REFERENCE);

		aExpressionEClass = createEClass(AEXPRESSION);

		aLeftOpRightExpressionEClass = createEClass(ALEFT_OP_RIGHT_EXPRESSION);
		createEReference(aLeftOpRightExpressionEClass, ALEFT_OP_RIGHT_EXPRESSION__LEFT);
		createEAttribute(aLeftOpRightExpressionEClass, ALEFT_OP_RIGHT_EXPRESSION__OPERATOR);
		createEReference(aLeftOpRightExpressionEClass, ALEFT_OP_RIGHT_EXPRESSION__RIGHT);

		aOpRightExpressionEClass = createEClass(AOP_RIGHT_EXPRESSION);
		createEAttribute(aOpRightExpressionEClass, AOP_RIGHT_EXPRESSION__OPERATOR);
		createEReference(aOpRightExpressionEClass, AOP_RIGHT_EXPRESSION__RIGHT);

		additionAndSubtractionEClass = createEClass(ADDITION_AND_SUBTRACTION);

		multiplicationAndDivisionEClass = createEClass(MULTIPLICATION_AND_DIVISION);

		powerFunctionEClass = createEClass(POWER_FUNCTION);

		functionEClass = createEClass(FUNCTION);

		aAdvancedFunctionEClass = createEClass(AADVANCED_FUNCTION);
		createEAttribute(aAdvancedFunctionEClass, AADVANCED_FUNCTION__OPERATOR);

		advancedFunctionEClass = createEClass(ADVANCED_FUNCTION);
		createEReference(advancedFunctionEClass, ADVANCED_FUNCTION__INPUTS);

		setFunctionEClass = createEClass(SET_FUNCTION);
		createEReference(setFunctionEClass, SET_FUNCTION__TYPE_DEFINITION);
		createEAttribute(setFunctionEClass, SET_FUNCTION__FILTER_NAME);
		createEAttribute(setFunctionEClass, SET_FUNCTION__DEPTH);

		parenthesisEClass = createEClass(PARENTHESIS);

		aLiteralEClass = createEClass(ALITERAL);

		valuePiEClass = createEClass(VALUE_PI);

		valueEEClass = createEClass(VALUE_E);

		numberLiteralEClass = createEClass(NUMBER_LITERAL);
		createEAttribute(numberLiteralEClass, NUMBER_LITERAL__VALUE);

		referencedInputEClass = createEClass(REFERENCED_INPUT);
		createEReference(referencedInputEClass, REFERENCED_INPUT__REFERENCE);
		createEReference(referencedInputEClass, REFERENCED_INPUT__DEFINITION);

		referencedDefinitionInputEClass = createEClass(REFERENCED_DEFINITION_INPUT);
		createEReference(referencedDefinitionInputEClass, REFERENCED_DEFINITION_INPUT__REFERENCE);

		iQualifiedEquationObjectEClass = createEClass(IQUALIFIED_EQUATION_OBJECT);

		// Create enums
		mathOperatorEEnum = createEEnum(MATH_OPERATOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		InheritancePackage theInheritancePackage = (InheritancePackage)EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI);
		GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);
		CategoriesPackage theCategoriesPackage = (CategoriesPackage)EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		equationSectionEClass.getESuperTypes().add(this.getIEquationSectionContainer());
		equationSectionEClass.getESuperTypes().add(theInheritancePackage.getIInheritanceLink());
		importEClass.getESuperTypes().add(theInheritancePackage.getIInheritanceLink());
		equationEClass.getESuperTypes().add(theInheritancePackage.getIOverridableInheritanceLink());
		equationDefinitionEClass.getESuperTypes().add(this.getIQualifiedEquationObject());
		iEquationResultEClass.getESuperTypes().add(theGeneralPackage.getIUuid());
		iEquationResultEClass.getESuperTypes().add(theInheritancePackage.getIInheritanceLink());
		iEquationDefinitionResultEClass.getESuperTypes().add(this.getIQualifiedEquationObject());
		iEquationInputEClass.getESuperTypes().add(theInheritancePackage.getIInheritanceLink());
		equationIntermediateResultEClass.getESuperTypes().add(this.getIEquationResult());
		equationIntermediateResultEClass.getESuperTypes().add(this.getIEquationInput());
		equationIntermediateResultEClass.getESuperTypes().add(this.getIEquationDefinitionInput());
		equationIntermediateResultEClass.getESuperTypes().add(this.getIEquationDefinitionResult());
		typeInstanceResultEClass.getESuperTypes().add(this.getIEquationResult());
		typeDefinitionResultEClass.getESuperTypes().add(this.getIEquationDefinitionResult());
		aExpressionEClass.getESuperTypes().add(theInheritancePackage.getIInheritanceLink());
		aExpressionEClass.getESuperTypes().add(this.getIQualifiedEquationObject());
		aLeftOpRightExpressionEClass.getESuperTypes().add(this.getAExpression());
		aOpRightExpressionEClass.getESuperTypes().add(this.getAExpression());
		additionAndSubtractionEClass.getESuperTypes().add(this.getALeftOpRightExpression());
		multiplicationAndDivisionEClass.getESuperTypes().add(this.getALeftOpRightExpression());
		powerFunctionEClass.getESuperTypes().add(this.getALeftOpRightExpression());
		functionEClass.getESuperTypes().add(this.getAOpRightExpression());
		aAdvancedFunctionEClass.getESuperTypes().add(this.getAExpression());
		advancedFunctionEClass.getESuperTypes().add(this.getAAdvancedFunction());
		setFunctionEClass.getESuperTypes().add(this.getAAdvancedFunction());
		parenthesisEClass.getESuperTypes().add(this.getAOpRightExpression());
		aLiteralEClass.getESuperTypes().add(this.getAExpression());
		valuePiEClass.getESuperTypes().add(this.getALiteral());
		valueEEClass.getESuperTypes().add(this.getALiteral());
		numberLiteralEClass.getESuperTypes().add(this.getALiteral());
		referencedInputEClass.getESuperTypes().add(this.getALiteral());
		referencedDefinitionInputEClass.getESuperTypes().add(this.getALiteral());

		// Initialize classes, features, and operations; add parameters
		initEClass(iEquationDefinitionSectionContainerEClass, IEquationDefinitionSectionContainer.class, "IEquationDefinitionSectionContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIEquationDefinitionSectionContainer_EquationDefinitions(), this.getEquationDefinition(), null, "equationDefinitions", null, 0, -1, IEquationDefinitionSectionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iEquationSectionContainerEClass, IEquationSectionContainer.class, "IEquationSectionContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIEquationSectionContainer_EquationSection(), this.getEquationSection(), null, "equationSection", null, 0, 1, IEquationSectionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(equationSectionEClass, EquationSection.class, "EquationSection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEquationSection_Imports(), this.getImport(), null, "imports", null, 0, -1, EquationSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEquationSection_Equations(), this.getEquation(), null, "equations", null, 0, -1, EquationSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEquationSection_SerializedStatements(), ecorePackage.getEString(), "serializedStatements", null, 0, 1, EquationSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImport_ImportedNamespace(), theGeneralPackage.getIInstance(), null, "importedNamespace", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(equationEClass, Equation.class, "Equation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEquation_Expression(), this.getAExpression(), null, "expression", null, 0, 1, Equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEquation_Result(), this.getIEquationResult(), null, "result", null, 0, 1, Equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEquation_ResultText(), ecorePackage.getEString(), "resultText", null, 0, 1, Equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEquation_Definition(), this.getEquationDefinition(), null, "definition", null, 0, 1, Equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(equationDefinitionEClass, EquationDefinition.class, "EquationDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEquationDefinition_Expression(), this.getAExpression(), null, "expression", null, 0, 1, EquationDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEquationDefinition_Result(), this.getIEquationDefinitionResult(), null, "result", null, 0, 1, EquationDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iEquationResultEClass, IEquationResult.class, "IEquationResult", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iEquationDefinitionResultEClass, IEquationDefinitionResult.class, "IEquationDefinitionResult", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iEquationInputEClass, IEquationInput.class, "IEquationInput", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iEquationDefinitionInputEClass, IEquationDefinitionInput.class, "IEquationDefinitionInput", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(equationIntermediateResultEClass, EquationIntermediateResult.class, "EquationIntermediateResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEquationIntermediateResult_Name(), ecorePackage.getEString(), "name", null, 0, 1, EquationIntermediateResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeInstanceResultEClass, TypeInstanceResult.class, "TypeInstanceResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeInstanceResult_Reference(), theCategoriesPackage.getATypeInstance(), null, "reference", null, 0, 1, TypeInstanceResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeDefinitionResultEClass, TypeDefinitionResult.class, "TypeDefinitionResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeDefinitionResult_Reference(), theCategoriesPackage.getATypeDefinition(), null, "reference", null, 0, 1, TypeDefinitionResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aExpressionEClass, AExpression.class, "AExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(aLeftOpRightExpressionEClass, ALeftOpRightExpression.class, "ALeftOpRightExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getALeftOpRightExpression_Left(), this.getAExpression(), null, "left", null, 0, 1, ALeftOpRightExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getALeftOpRightExpression_Operator(), this.getMathOperator(), "operator", null, 0, 1, ALeftOpRightExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getALeftOpRightExpression_Right(), this.getAExpression(), null, "right", null, 0, 1, ALeftOpRightExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aOpRightExpressionEClass, AOpRightExpression.class, "AOpRightExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAOpRightExpression_Operator(), this.getMathOperator(), "operator", null, 0, 1, AOpRightExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAOpRightExpression_Right(), this.getAExpression(), null, "right", null, 0, 1, AOpRightExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(additionAndSubtractionEClass, AdditionAndSubtraction.class, "AdditionAndSubtraction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(multiplicationAndDivisionEClass, MultiplicationAndDivision.class, "MultiplicationAndDivision", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(powerFunctionEClass, PowerFunction.class, "PowerFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionEClass, Function.class, "Function", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(aAdvancedFunctionEClass, AAdvancedFunction.class, "AAdvancedFunction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAAdvancedFunction_Operator(), ecorePackage.getEString(), "operator", null, 0, 1, AAdvancedFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(advancedFunctionEClass, AdvancedFunction.class, "AdvancedFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdvancedFunction_Inputs(), this.getAExpression(), null, "inputs", null, 0, -1, AdvancedFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(setFunctionEClass, SetFunction.class, "SetFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSetFunction_TypeDefinition(), theCategoriesPackage.getATypeDefinition(), null, "typeDefinition", null, 0, 1, SetFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSetFunction_FilterName(), ecorePackage.getEString(), "filterName", null, 0, 1, SetFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSetFunction_Depth(), ecorePackage.getEInt(), "depth", "-1", 0, 1, SetFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parenthesisEClass, Parenthesis.class, "Parenthesis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(aLiteralEClass, ALiteral.class, "ALiteral", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valuePiEClass, ValuePi.class, "ValuePi", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valueEEClass, ValueE.class, "ValueE", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(numberLiteralEClass, NumberLiteral.class, "NumberLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumberLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, NumberLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referencedInputEClass, ReferencedInput.class, "ReferencedInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferencedInput_Reference(), this.getIEquationInput(), null, "reference", null, 0, 1, ReferencedInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferencedInput_Definition(), this.getReferencedDefinitionInput(), null, "definition", null, 0, 1, ReferencedInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referencedDefinitionInputEClass, ReferencedDefinitionInput.class, "ReferencedDefinitionInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferencedDefinitionInput_Reference(), this.getIEquationDefinitionInput(), null, "reference", null, 0, 1, ReferencedDefinitionInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iQualifiedEquationObjectEClass, IQualifiedEquationObject.class, "IQualifiedEquationObject", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(mathOperatorEEnum, MathOperator.class, "MathOperator");
		addEEnumLiteral(mathOperatorEEnum, MathOperator.PLUS);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.MINUS);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.MULTIPLY);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.DIVIDE);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.POWER);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.COS);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.SIN);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.TAN);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.ATAN);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.ACOS);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.ASIN);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.SQRT);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.LOG);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.LN);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.EXP);
		addEEnumLiteral(mathOperatorEEnum, MathOperator.LD);
	}

} //CalculationPackageImpl
