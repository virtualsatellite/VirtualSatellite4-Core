/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts.impl;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;

import de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;

import de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsPackageImpl;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesPackageImpl;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport;
import de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept;
import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.IEImports;
import de.dlr.sc.virsat.model.dvlm.concepts.IImports;

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
import org.eclipse.emf.ecore.EOperation;
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
public class ConceptsPackageImpl extends EPackageImpl implements ConceptsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conceptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conceptImportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iActiveConceptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iImportsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iConceptTypeDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ecoreImportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ieImportsEClass = null;

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
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ConceptsPackageImpl() {
		super(eNS_URI, ConceptsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ConceptsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ConceptsPackage init() {
		if (isInited) return (ConceptsPackage)EPackage.Registry.INSTANCE.getEPackage(ConceptsPackage.eNS_URI);

		// Obtain or create and register package
		ConceptsPackageImpl theConceptsPackage = (ConceptsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConceptsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ConceptsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		DVLMPackageImpl theDVLMPackage = (DVLMPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DVLMPackage.eNS_URI) instanceof DVLMPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DVLMPackage.eNS_URI) : DVLMPackage.eINSTANCE);
		StructuralPackageImpl theStructuralPackage = (StructuralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StructuralPackage.eNS_URI) instanceof StructuralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StructuralPackage.eNS_URI) : StructuralPackage.eINSTANCE);
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
		RolesPackageImpl theRolesPackage = (RolesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RolesPackage.eNS_URI) instanceof RolesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RolesPackage.eNS_URI) : RolesPackage.eINSTANCE);
		UnitsPackageImpl theUnitsPackage = (UnitsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UnitsPackage.eNS_URI) instanceof UnitsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UnitsPackage.eNS_URI) : UnitsPackage.eINSTANCE);
		TypesPackageImpl theTypesPackage = (TypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) : TypesPackage.eINSTANCE);
		CategoriesPackageImpl theCategoriesPackage = (CategoriesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI) instanceof CategoriesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI) : CategoriesPackage.eINSTANCE);
		PropertydefinitionsPackageImpl thePropertydefinitionsPackage = (PropertydefinitionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PropertydefinitionsPackage.eNS_URI) instanceof PropertydefinitionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PropertydefinitionsPackage.eNS_URI) : PropertydefinitionsPackage.eINSTANCE);
		PropertyinstancesPackageImpl thePropertyinstancesPackage = (PropertyinstancesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PropertyinstancesPackage.eNS_URI) instanceof PropertyinstancesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PropertyinstancesPackage.eNS_URI) : PropertyinstancesPackage.eINSTANCE);
		QudvPackageImpl theQudvPackage = (QudvPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QudvPackage.eNS_URI) instanceof QudvPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QudvPackage.eNS_URI) : QudvPackage.eINSTANCE);
		CalculationPackageImpl theCalculationPackage = (CalculationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CalculationPackage.eNS_URI) instanceof CalculationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CalculationPackage.eNS_URI) : CalculationPackage.eINSTANCE);
		InheritancePackageImpl theInheritancePackage = (InheritancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI) instanceof InheritancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI) : InheritancePackage.eINSTANCE);
		DmfPackageImpl theDmfPackage = (DmfPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DmfPackage.eNS_URI) instanceof DmfPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DmfPackage.eNS_URI) : DmfPackage.eINSTANCE);

		// Create package meta-data objects
		theConceptsPackage.createPackageContents();
		theDVLMPackage.createPackageContents();
		theStructuralPackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theRolesPackage.createPackageContents();
		theUnitsPackage.createPackageContents();
		theTypesPackage.createPackageContents();
		theCategoriesPackage.createPackageContents();
		thePropertydefinitionsPackage.createPackageContents();
		thePropertyinstancesPackage.createPackageContents();
		theQudvPackage.createPackageContents();
		theCalculationPackage.createPackageContents();
		theInheritancePackage.createPackageContents();
		theDmfPackage.createPackageContents();

		// Initialize created meta-data
		theConceptsPackage.initializePackageContents();
		theDVLMPackage.initializePackageContents();
		theStructuralPackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theRolesPackage.initializePackageContents();
		theUnitsPackage.initializePackageContents();
		theTypesPackage.initializePackageContents();
		theCategoriesPackage.initializePackageContents();
		thePropertydefinitionsPackage.initializePackageContents();
		thePropertyinstancesPackage.initializePackageContents();
		theQudvPackage.initializePackageContents();
		theCalculationPackage.initializePackageContents();
		theInheritancePackage.initializePackageContents();
		theDmfPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theConceptsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ConceptsPackage.eNS_URI, theConceptsPackage);
		return theConceptsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConcept() {
		return conceptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcept_Categories() {
		return (EReference)conceptEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcept_StructuralElements() {
		return (EReference)conceptEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcept_Relations() {
		return (EReference)conceptEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConcept_Version() {
		return (EAttribute)conceptEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConcept_DMF() {
		return (EAttribute)conceptEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConcept_DisplayName() {
		return (EAttribute)conceptEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConcept_Beta() {
		return (EAttribute)conceptEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getConcept__GetNonAbstractCategories() {
		return conceptEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConceptImport() {
		return conceptImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConceptImport_ImportedNamespace() {
		return (EAttribute)conceptImportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIActiveConcept() {
		return iActiveConceptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIActiveConcept_Active() {
		return (EAttribute)iActiveConceptEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIImports() {
		return iImportsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIImports_Imports() {
		return (EReference)iImportsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIConceptTypeDefinition() {
		return iConceptTypeDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEcoreImport() {
		return ecoreImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEcoreImport_ImportedNsURI() {
		return (EAttribute)ecoreImportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEcoreImport_ImportedGenModel() {
		return (EAttribute)ecoreImportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIEImports() {
		return ieImportsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIEImports_EcoreImports() {
		return (EReference)ieImportsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConceptsFactory getConceptsFactory() {
		return (ConceptsFactory)getEFactoryInstance();
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
		conceptEClass = createEClass(CONCEPT);
		createEReference(conceptEClass, CONCEPT__CATEGORIES);
		createEReference(conceptEClass, CONCEPT__STRUCTURAL_ELEMENTS);
		createEReference(conceptEClass, CONCEPT__RELATIONS);
		createEAttribute(conceptEClass, CONCEPT__VERSION);
		createEAttribute(conceptEClass, CONCEPT__DMF);
		createEAttribute(conceptEClass, CONCEPT__DISPLAY_NAME);
		createEAttribute(conceptEClass, CONCEPT__BETA);
		createEOperation(conceptEClass, CONCEPT___GET_NON_ABSTRACT_CATEGORIES);

		conceptImportEClass = createEClass(CONCEPT_IMPORT);
		createEAttribute(conceptImportEClass, CONCEPT_IMPORT__IMPORTED_NAMESPACE);

		iActiveConceptEClass = createEClass(IACTIVE_CONCEPT);
		createEAttribute(iActiveConceptEClass, IACTIVE_CONCEPT__ACTIVE);

		iImportsEClass = createEClass(IIMPORTS);
		createEReference(iImportsEClass, IIMPORTS__IMPORTS);

		iConceptTypeDefinitionEClass = createEClass(ICONCEPT_TYPE_DEFINITION);

		ecoreImportEClass = createEClass(ECORE_IMPORT);
		createEAttribute(ecoreImportEClass, ECORE_IMPORT__IMPORTED_NS_URI);
		createEAttribute(ecoreImportEClass, ECORE_IMPORT__IMPORTED_GEN_MODEL);

		ieImportsEClass = createEClass(IE_IMPORTS);
		createEReference(ieImportsEClass, IE_IMPORTS__ECORE_IMPORTS);
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
		GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);
		CategoriesPackage theCategoriesPackage = (CategoriesPackage)EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI);
		StructuralPackage theStructuralPackage = (StructuralPackage)EPackage.Registry.INSTANCE.getEPackage(StructuralPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		conceptEClass.getESuperTypes().add(theGeneralPackage.getIQualifiedName());
		conceptEClass.getESuperTypes().add(theGeneralPackage.getIDescription());
		conceptEClass.getESuperTypes().add(this.getIActiveConcept());
		conceptEClass.getESuperTypes().add(this.getIImports());
		conceptEClass.getESuperTypes().add(this.getIEImports());
		iConceptTypeDefinitionEClass.getESuperTypes().add(theGeneralPackage.getIQualifiedName());

		// Initialize classes, features, and operations; add parameters
		initEClass(conceptEClass, Concept.class, "Concept", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConcept_Categories(), theCategoriesPackage.getCategory(), null, "categories", null, 0, -1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcept_StructuralElements(), theStructuralPackage.getStructuralElement(), null, "structuralElements", null, 0, -1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcept_Relations(), theStructuralPackage.getGeneralRelation(), null, "relations", null, 0, -1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcept_Version(), ecorePackage.getEString(), "version", "1.0", 0, 1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcept_DMF(), ecorePackage.getEBoolean(), "DMF", "FALSE", 0, 1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcept_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcept_Beta(), ecorePackage.getEBoolean(), "beta", null, 0, 1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getConcept__GetNonAbstractCategories(), theCategoriesPackage.getCategory(), "getNonAbstractCategories", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(conceptImportEClass, ConceptImport.class, "ConceptImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConceptImport_ImportedNamespace(), ecorePackage.getEString(), "importedNamespace", null, 0, 1, ConceptImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iActiveConceptEClass, IActiveConcept.class, "IActiveConcept", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIActiveConcept_Active(), ecorePackage.getEBoolean(), "active", "false", 1, 1, IActiveConcept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iImportsEClass, IImports.class, "IImports", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIImports_Imports(), this.getConceptImport(), null, "imports", null, 0, -1, IImports.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iConceptTypeDefinitionEClass, IConceptTypeDefinition.class, "IConceptTypeDefinition", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(ecoreImportEClass, EcoreImport.class, "EcoreImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEcoreImport_ImportedNsURI(), theEcorePackage.getEString(), "importedNsURI", null, 0, 1, EcoreImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEcoreImport_ImportedGenModel(), theEcorePackage.getEString(), "importedGenModel", null, 0, 1, EcoreImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ieImportsEClass, IEImports.class, "IEImports", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIEImports_EcoreImports(), this.getEcoreImport(), null, "ecoreImports", null, 0, -1, IEImports.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //ConceptsPackageImpl
