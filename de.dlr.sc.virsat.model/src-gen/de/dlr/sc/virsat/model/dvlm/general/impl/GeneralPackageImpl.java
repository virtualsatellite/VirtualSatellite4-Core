/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.general.impl;

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
import de.dlr.sc.virsat.model.dvlm.general.GeneralFactory;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.general.IComment;
import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

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
public class GeneralPackageImpl extends EPackageImpl implements GeneralPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iUuidEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iQualifiedNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iCommentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iAssignedDisciplineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iInstanceEClass = null;

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
	 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GeneralPackageImpl() {
		super(eNS_URI, GeneralFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link GeneralPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GeneralPackage init() {
		if (isInited) return (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);

		// Obtain or create and register package
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GeneralPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		DVLMPackageImpl theDVLMPackage = (DVLMPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DVLMPackage.eNS_URI) instanceof DVLMPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DVLMPackage.eNS_URI) : DVLMPackage.eINSTANCE);
		StructuralPackageImpl theStructuralPackage = (StructuralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StructuralPackage.eNS_URI) instanceof StructuralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StructuralPackage.eNS_URI) : StructuralPackage.eINSTANCE);
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
		theGeneralPackage.createPackageContents();
		theDVLMPackage.createPackageContents();
		theStructuralPackage.createPackageContents();
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
		theGeneralPackage.initializePackageContents();
		theDVLMPackage.initializePackageContents();
		theStructuralPackage.initializePackageContents();
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
		theGeneralPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GeneralPackage.eNS_URI, theGeneralPackage);
		return theGeneralPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIName() {
		return iNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIName_Name() {
		return (EAttribute)iNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIDescription() {
		return iDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIDescription_Description() {
		return (EAttribute)iDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIUuid() {
		return iUuidEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIUuid_Uuid() {
		return (EAttribute)iUuidEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIQualifiedName() {
		return iQualifiedNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIQualifiedName_Name() {
		return (EAttribute)iQualifiedNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIQualifiedName_FullQualifiedName() {
		return (EAttribute)iQualifiedNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIQualifiedName_ShortName() {
		return (EAttribute)iQualifiedNameEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIComment() {
		return iCommentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIComment_Comment() {
		return (EAttribute)iCommentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIAssignedDiscipline() {
		return iAssignedDisciplineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIAssignedDiscipline_AssignedDiscipline() {
		return (EReference)iAssignedDisciplineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIAssignedDiscipline__GetContainedIAssignedDisciplines() {
		return iAssignedDisciplineEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIInstance() {
		return iInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIInstance__GetFullQualifiedInstanceName() {
		return iInstanceEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralFactory getGeneralFactory() {
		return (GeneralFactory)getEFactoryInstance();
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
		iNameEClass = createEClass(INAME);
		createEAttribute(iNameEClass, INAME__NAME);

		iDescriptionEClass = createEClass(IDESCRIPTION);
		createEAttribute(iDescriptionEClass, IDESCRIPTION__DESCRIPTION);

		iUuidEClass = createEClass(IUUID);
		createEAttribute(iUuidEClass, IUUID__UUID);

		iQualifiedNameEClass = createEClass(IQUALIFIED_NAME);
		createEAttribute(iQualifiedNameEClass, IQUALIFIED_NAME__NAME);
		createEAttribute(iQualifiedNameEClass, IQUALIFIED_NAME__FULL_QUALIFIED_NAME);
		createEAttribute(iQualifiedNameEClass, IQUALIFIED_NAME__SHORT_NAME);

		iCommentEClass = createEClass(ICOMMENT);
		createEAttribute(iCommentEClass, ICOMMENT__COMMENT);

		iAssignedDisciplineEClass = createEClass(IASSIGNED_DISCIPLINE);
		createEReference(iAssignedDisciplineEClass, IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE);
		createEOperation(iAssignedDisciplineEClass, IASSIGNED_DISCIPLINE___GET_CONTAINED_IASSIGNED_DISCIPLINES);

		iInstanceEClass = createEClass(IINSTANCE);
		createEOperation(iInstanceEClass, IINSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME);
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
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);
		RolesPackage theRolesPackage = (RolesPackage)EPackage.Registry.INSTANCE.getEPackage(RolesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(iNameEClass, IName.class, "IName", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIName_Name(), ecorePackage.getEString(), "name", null, 0, 1, IName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iDescriptionEClass, IDescription.class, "IDescription", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIDescription_Description(), ecorePackage.getEString(), "description", null, 0, 1, IDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iUuidEClass, IUuid.class, "IUuid", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIUuid_Uuid(), theTypesPackage.getUuid(), "uuid", "", 0, 1, IUuid.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iQualifiedNameEClass, IQualifiedName.class, "IQualifiedName", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIQualifiedName_Name(), ecorePackage.getEString(), "name", "de.dlr.sc.model.dvlm.noid", 1, 1, IQualifiedName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIQualifiedName_FullQualifiedName(), ecorePackage.getEString(), "fullQualifiedName", "de.dlr.sc.model.dvlm.noid", 1, 1, IQualifiedName.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getIQualifiedName_ShortName(), ecorePackage.getEString(), "shortName", "", 0, 1, IQualifiedName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iCommentEClass, IComment.class, "IComment", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIComment_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, IComment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iAssignedDisciplineEClass, IAssignedDiscipline.class, "IAssignedDiscipline", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIAssignedDiscipline_AssignedDiscipline(), theRolesPackage.getDiscipline(), null, "assignedDiscipline", null, 1, 1, IAssignedDiscipline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getIAssignedDiscipline__GetContainedIAssignedDisciplines(), this.getIAssignedDiscipline(), "getContainedIAssignedDisciplines", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(iInstanceEClass, IInstance.class, "IInstance", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getIInstance__GetFullQualifiedInstanceName(), ecorePackage.getEString(), "getFullQualifiedInstanceName", 0, 1, IS_UNIQUE, IS_ORDERED);
	}

} //GeneralPackageImpl
