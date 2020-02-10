/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.impl;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;

import de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl;

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

import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor;
import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

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
public class StructuralPackageImpl extends EPackageImpl implements StructuralPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iApplicableForEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuralElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuralElementInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generalRelationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationInstanceEClass = null;

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
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StructuralPackageImpl() {
		super(eNS_URI, StructuralFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link StructuralPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StructuralPackage init() {
		if (isInited) return (StructuralPackage)EPackage.Registry.INSTANCE.getEPackage(StructuralPackage.eNS_URI);

		// Obtain or create and register package
		StructuralPackageImpl theStructuralPackage = (StructuralPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StructuralPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StructuralPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		DVLMPackageImpl theDVLMPackage = (DVLMPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DVLMPackage.eNS_URI) instanceof DVLMPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DVLMPackage.eNS_URI) : DVLMPackage.eINSTANCE);
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
		ConceptsPackageImpl theConceptsPackage = (ConceptsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConceptsPackage.eNS_URI) instanceof ConceptsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConceptsPackage.eNS_URI) : ConceptsPackage.eINSTANCE);
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
		theStructuralPackage.createPackageContents();
		theDVLMPackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theConceptsPackage.createPackageContents();
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
		theStructuralPackage.initializePackageContents();
		theDVLMPackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theConceptsPackage.initializePackageContents();
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
		theStructuralPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StructuralPackage.eNS_URI, theStructuralPackage);
		return theStructuralPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIApplicableFor() {
		return iApplicableForEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIApplicableFor_ApplicableFor() {
		return (EReference)iApplicableForEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIApplicableFor_IsApplicableForAll() {
		return (EAttribute)iApplicableForEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIApplicableFor_Cardinality() {
		return (EAttribute)iApplicableForEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructuralElement() {
		return structuralElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStructuralElement_IsRootStructuralElement() {
		return (EAttribute)structuralElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructuralElementInstance() {
		return structuralElementInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralElementInstance_Type() {
		return (EReference)structuralElementInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralElementInstance_RelationInstances() {
		return (EReference)structuralElementInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralElementInstance_Children() {
		return (EReference)structuralElementInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralElementInstance_Parent() {
		return (EReference)structuralElementInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getStructuralElementInstance__GetDeepChildren() {
		return structuralElementInstanceEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneralRelation() {
		return generalRelationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralRelation_ReferencedType() {
		return (EReference)generalRelationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationInstance() {
		return relationInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationInstance_Type() {
		return (EReference)relationInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationInstance_References() {
		return (EReference)relationInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFactory getStructuralFactory() {
		return (StructuralFactory)getEFactoryInstance();
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
		iApplicableForEClass = createEClass(IAPPLICABLE_FOR);
		createEReference(iApplicableForEClass, IAPPLICABLE_FOR__APPLICABLE_FOR);
		createEAttribute(iApplicableForEClass, IAPPLICABLE_FOR__IS_APPLICABLE_FOR_ALL);
		createEAttribute(iApplicableForEClass, IAPPLICABLE_FOR__CARDINALITY);

		structuralElementEClass = createEClass(STRUCTURAL_ELEMENT);
		createEAttribute(structuralElementEClass, STRUCTURAL_ELEMENT__IS_ROOT_STRUCTURAL_ELEMENT);

		structuralElementInstanceEClass = createEClass(STRUCTURAL_ELEMENT_INSTANCE);
		createEReference(structuralElementInstanceEClass, STRUCTURAL_ELEMENT_INSTANCE__TYPE);
		createEReference(structuralElementInstanceEClass, STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES);
		createEReference(structuralElementInstanceEClass, STRUCTURAL_ELEMENT_INSTANCE__CHILDREN);
		createEReference(structuralElementInstanceEClass, STRUCTURAL_ELEMENT_INSTANCE__PARENT);
		createEOperation(structuralElementInstanceEClass, STRUCTURAL_ELEMENT_INSTANCE___GET_DEEP_CHILDREN);

		generalRelationEClass = createEClass(GENERAL_RELATION);
		createEReference(generalRelationEClass, GENERAL_RELATION__REFERENCED_TYPE);

		relationInstanceEClass = createEClass(RELATION_INSTANCE);
		createEReference(relationInstanceEClass, RELATION_INSTANCE__TYPE);
		createEReference(relationInstanceEClass, RELATION_INSTANCE__REFERENCES);
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
		ConceptsPackage theConceptsPackage = (ConceptsPackage)EPackage.Registry.INSTANCE.getEPackage(ConceptsPackage.eNS_URI);
		InheritancePackage theInheritancePackage = (InheritancePackage)EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI);
		CategoriesPackage theCategoriesPackage = (CategoriesPackage)EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		structuralElementEClass.getESuperTypes().add(theGeneralPackage.getIQualifiedName());
		structuralElementEClass.getESuperTypes().add(theGeneralPackage.getIDescription());
		structuralElementEClass.getESuperTypes().add(this.getIApplicableFor());
		structuralElementEClass.getESuperTypes().add(theConceptsPackage.getIConceptTypeDefinition());
		structuralElementEClass.getESuperTypes().add(theInheritancePackage.getICanInheritFrom());
		structuralElementInstanceEClass.getESuperTypes().add(theGeneralPackage.getIUuid());
		structuralElementInstanceEClass.getESuperTypes().add(theGeneralPackage.getIDescription());
		structuralElementInstanceEClass.getESuperTypes().add(theGeneralPackage.getIName());
		structuralElementInstanceEClass.getESuperTypes().add(theGeneralPackage.getIAssignedDiscipline());
		structuralElementInstanceEClass.getESuperTypes().add(theCategoriesPackage.getICategoryAssignmentContainer());
		structuralElementInstanceEClass.getESuperTypes().add(theGeneralPackage.getIInstance());
		structuralElementInstanceEClass.getESuperTypes().add(theInheritancePackage.getIInheritsFrom());
		generalRelationEClass.getESuperTypes().add(theGeneralPackage.getIQualifiedName());
		generalRelationEClass.getESuperTypes().add(this.getIApplicableFor());
		generalRelationEClass.getESuperTypes().add(theGeneralPackage.getIDescription());
		generalRelationEClass.getESuperTypes().add(theConceptsPackage.getIConceptTypeDefinition());
		relationInstanceEClass.getESuperTypes().add(theGeneralPackage.getIUuid());
		relationInstanceEClass.getESuperTypes().add(theGeneralPackage.getIDescription());
		relationInstanceEClass.getESuperTypes().add(theGeneralPackage.getIName());

		// Initialize classes, features, and operations; add parameters
		initEClass(iApplicableForEClass, IApplicableFor.class, "IApplicableFor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIApplicableFor_ApplicableFor(), this.getStructuralElement(), null, "applicableFor", null, 0, -1, IApplicableFor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIApplicableFor_IsApplicableForAll(), ecorePackage.getEBoolean(), "isApplicableForAll", "false", 0, 1, IApplicableFor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIApplicableFor_Cardinality(), ecorePackage.getEInt(), "cardinality", "0", 0, 1, IApplicableFor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(structuralElementEClass, StructuralElement.class, "StructuralElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStructuralElement_IsRootStructuralElement(), ecorePackage.getEBoolean(), "isRootStructuralElement", "false", 0, 1, StructuralElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(structuralElementInstanceEClass, StructuralElementInstance.class, "StructuralElementInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStructuralElementInstance_Type(), this.getStructuralElement(), null, "type", null, 0, 1, StructuralElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructuralElementInstance_RelationInstances(), this.getRelationInstance(), null, "relationInstances", null, 0, -1, StructuralElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructuralElementInstance_Children(), this.getStructuralElementInstance(), this.getStructuralElementInstance_Parent(), "children", null, 0, -1, StructuralElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructuralElementInstance_Parent(), this.getStructuralElementInstance(), this.getStructuralElementInstance_Children(), "parent", null, 0, 1, StructuralElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getStructuralElementInstance__GetDeepChildren(), this.getStructuralElementInstance(), "getDeepChildren", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(generalRelationEClass, GeneralRelation.class, "GeneralRelation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeneralRelation_ReferencedType(), this.getStructuralElement(), null, "referencedType", null, 1, 1, GeneralRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relationInstanceEClass, RelationInstance.class, "RelationInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelationInstance_Type(), this.getGeneralRelation(), null, "type", null, 0, 1, RelationInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelationInstance_References(), this.getStructuralElementInstance(), null, "references", null, 0, 1, RelationInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //StructuralPackageImpl
