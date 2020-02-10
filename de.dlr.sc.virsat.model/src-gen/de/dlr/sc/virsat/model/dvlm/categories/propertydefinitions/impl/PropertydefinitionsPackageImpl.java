/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;

import de.dlr.sc.virsat.model.dvlm.calculation.impl.CalculationPackageImpl;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;

import de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;

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
public class PropertydefinitionsPackageImpl extends EPackageImpl implements PropertydefinitionsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass composedPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referencePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eReferencePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iIntrinsicTypePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aQudvTypePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumValueDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourcePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticArrayModifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iArrayModifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicArrayModifierEClass = null;

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
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PropertydefinitionsPackageImpl() {
		super(eNS_URI, PropertydefinitionsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link PropertydefinitionsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PropertydefinitionsPackage init() {
		if (isInited) return (PropertydefinitionsPackage)EPackage.Registry.INSTANCE.getEPackage(PropertydefinitionsPackage.eNS_URI);

		// Obtain or create and register package
		PropertydefinitionsPackageImpl thePropertydefinitionsPackage = (PropertydefinitionsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PropertydefinitionsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PropertydefinitionsPackageImpl());

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
		PropertyinstancesPackageImpl thePropertyinstancesPackage = (PropertyinstancesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PropertyinstancesPackage.eNS_URI) instanceof PropertyinstancesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PropertyinstancesPackage.eNS_URI) : PropertyinstancesPackage.eINSTANCE);
		QudvPackageImpl theQudvPackage = (QudvPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QudvPackage.eNS_URI) instanceof QudvPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QudvPackage.eNS_URI) : QudvPackage.eINSTANCE);
		CalculationPackageImpl theCalculationPackage = (CalculationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CalculationPackage.eNS_URI) instanceof CalculationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CalculationPackage.eNS_URI) : CalculationPackage.eINSTANCE);
		InheritancePackageImpl theInheritancePackage = (InheritancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI) instanceof InheritancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI) : InheritancePackage.eINSTANCE);
		DmfPackageImpl theDmfPackage = (DmfPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DmfPackage.eNS_URI) instanceof DmfPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DmfPackage.eNS_URI) : DmfPackage.eINSTANCE);

		// Create package meta-data objects
		thePropertydefinitionsPackage.createPackageContents();
		theDVLMPackage.createPackageContents();
		theStructuralPackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theConceptsPackage.createPackageContents();
		theRolesPackage.createPackageContents();
		theUnitsPackage.createPackageContents();
		theTypesPackage.createPackageContents();
		theCategoriesPackage.createPackageContents();
		thePropertyinstancesPackage.createPackageContents();
		theQudvPackage.createPackageContents();
		theCalculationPackage.createPackageContents();
		theInheritancePackage.createPackageContents();
		theDmfPackage.createPackageContents();

		// Initialize created meta-data
		thePropertydefinitionsPackage.initializePackageContents();
		theDVLMPackage.initializePackageContents();
		theStructuralPackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theConceptsPackage.initializePackageContents();
		theRolesPackage.initializePackageContents();
		theUnitsPackage.initializePackageContents();
		theTypesPackage.initializePackageContents();
		theCategoriesPackage.initializePackageContents();
		thePropertyinstancesPackage.initializePackageContents();
		theQudvPackage.initializePackageContents();
		theCalculationPackage.initializePackageContents();
		theInheritancePackage.initializePackageContents();
		theDmfPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePropertydefinitionsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PropertydefinitionsPackage.eNS_URI, thePropertydefinitionsPackage);
		return thePropertydefinitionsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAProperty() {
		return aPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAProperty_ArrayModifier() {
		return (EReference)aPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComposedProperty() {
		return composedPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComposedProperty_Type() {
		return (EReference)composedPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComposedProperty_QuantityKindName() {
		return (EAttribute)composedPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComposedProperty_UnitName() {
		return (EAttribute)composedPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceProperty() {
		return referencePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceProperty_ReferenceType() {
		return (EReference)referencePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEReferenceProperty() {
		return eReferencePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEReferenceProperty_ReferenceType() {
		return (EReference)eReferencePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIIntrinsicTypeProperty() {
		return iIntrinsicTypePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIIntrinsicTypeProperty_DefaultValue() {
		return (EAttribute)iIntrinsicTypePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAQudvTypeProperty() {
		return aQudvTypePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAQudvTypeProperty_QuantityKindName() {
		return (EAttribute)aQudvTypePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAQudvTypeProperty_UnitName() {
		return (EAttribute)aQudvTypePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntProperty() {
		return intPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFloatProperty() {
		return floatPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringProperty() {
		return stringPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanProperty() {
		return booleanPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumProperty() {
		return enumPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumProperty_Values() {
		return (EReference)enumPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumProperty_DefaultValue() {
		return (EReference)enumPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumValueDefinition() {
		return enumValueDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumValueDefinition_Value() {
		return (EAttribute)enumValueDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceProperty() {
		return resourcePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticArrayModifier() {
		return staticArrayModifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticArrayModifier_ArraySize() {
		return (EAttribute)staticArrayModifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIArrayModifier() {
		return iArrayModifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicArrayModifier() {
		return dynamicArrayModifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertydefinitionsFactory getPropertydefinitionsFactory() {
		return (PropertydefinitionsFactory)getEFactoryInstance();
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
		aPropertyEClass = createEClass(APROPERTY);
		createEReference(aPropertyEClass, APROPERTY__ARRAY_MODIFIER);

		composedPropertyEClass = createEClass(COMPOSED_PROPERTY);
		createEReference(composedPropertyEClass, COMPOSED_PROPERTY__TYPE);
		createEAttribute(composedPropertyEClass, COMPOSED_PROPERTY__QUANTITY_KIND_NAME);
		createEAttribute(composedPropertyEClass, COMPOSED_PROPERTY__UNIT_NAME);

		referencePropertyEClass = createEClass(REFERENCE_PROPERTY);
		createEReference(referencePropertyEClass, REFERENCE_PROPERTY__REFERENCE_TYPE);

		eReferencePropertyEClass = createEClass(EREFERENCE_PROPERTY);
		createEReference(eReferencePropertyEClass, EREFERENCE_PROPERTY__REFERENCE_TYPE);

		iIntrinsicTypePropertyEClass = createEClass(IINTRINSIC_TYPE_PROPERTY);
		createEAttribute(iIntrinsicTypePropertyEClass, IINTRINSIC_TYPE_PROPERTY__DEFAULT_VALUE);

		aQudvTypePropertyEClass = createEClass(AQUDV_TYPE_PROPERTY);
		createEAttribute(aQudvTypePropertyEClass, AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME);
		createEAttribute(aQudvTypePropertyEClass, AQUDV_TYPE_PROPERTY__UNIT_NAME);

		intPropertyEClass = createEClass(INT_PROPERTY);

		floatPropertyEClass = createEClass(FLOAT_PROPERTY);

		stringPropertyEClass = createEClass(STRING_PROPERTY);

		booleanPropertyEClass = createEClass(BOOLEAN_PROPERTY);

		enumPropertyEClass = createEClass(ENUM_PROPERTY);
		createEReference(enumPropertyEClass, ENUM_PROPERTY__VALUES);
		createEReference(enumPropertyEClass, ENUM_PROPERTY__DEFAULT_VALUE);

		enumValueDefinitionEClass = createEClass(ENUM_VALUE_DEFINITION);
		createEAttribute(enumValueDefinitionEClass, ENUM_VALUE_DEFINITION__VALUE);

		resourcePropertyEClass = createEClass(RESOURCE_PROPERTY);

		staticArrayModifierEClass = createEClass(STATIC_ARRAY_MODIFIER);
		createEAttribute(staticArrayModifierEClass, STATIC_ARRAY_MODIFIER__ARRAY_SIZE);

		iArrayModifierEClass = createEClass(IARRAY_MODIFIER);

		dynamicArrayModifierEClass = createEClass(DYNAMIC_ARRAY_MODIFIER);
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
		CategoriesPackage theCategoriesPackage = (CategoriesPackage)EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI);
		CalculationPackage theCalculationPackage = (CalculationPackage)EPackage.Registry.INSTANCE.getEPackage(CalculationPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		aPropertyEClass.getESuperTypes().add(theCategoriesPackage.getATypeDefinition());
		composedPropertyEClass.getESuperTypes().add(this.getAProperty());
		composedPropertyEClass.getESuperTypes().add(theCalculationPackage.getIEquationDefinitionInput());
		referencePropertyEClass.getESuperTypes().add(this.getAProperty());
		eReferencePropertyEClass.getESuperTypes().add(this.getAProperty());
		aQudvTypePropertyEClass.getESuperTypes().add(theCalculationPackage.getIEquationDefinitionInput());
		aQudvTypePropertyEClass.getESuperTypes().add(this.getAProperty());
		intPropertyEClass.getESuperTypes().add(this.getAQudvTypeProperty());
		intPropertyEClass.getESuperTypes().add(this.getIIntrinsicTypeProperty());
		floatPropertyEClass.getESuperTypes().add(this.getAQudvTypeProperty());
		floatPropertyEClass.getESuperTypes().add(this.getIIntrinsicTypeProperty());
		stringPropertyEClass.getESuperTypes().add(this.getAProperty());
		stringPropertyEClass.getESuperTypes().add(this.getIIntrinsicTypeProperty());
		booleanPropertyEClass.getESuperTypes().add(this.getAProperty());
		booleanPropertyEClass.getESuperTypes().add(this.getIIntrinsicTypeProperty());
		enumPropertyEClass.getESuperTypes().add(this.getAQudvTypeProperty());
		enumValueDefinitionEClass.getESuperTypes().add(theGeneralPackage.getIName());
		resourcePropertyEClass.getESuperTypes().add(this.getAProperty());
		staticArrayModifierEClass.getESuperTypes().add(this.getIArrayModifier());
		dynamicArrayModifierEClass.getESuperTypes().add(this.getIArrayModifier());

		// Initialize classes, features, and operations; add parameters
		initEClass(aPropertyEClass, AProperty.class, "AProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAProperty_ArrayModifier(), this.getIArrayModifier(), null, "arrayModifier", null, 0, 1, AProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(composedPropertyEClass, ComposedProperty.class, "ComposedProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComposedProperty_Type(), theCategoriesPackage.getCategory(), null, "type", null, 1, 1, ComposedProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComposedProperty_QuantityKindName(), ecorePackage.getEString(), "quantityKindName", null, 0, 1, ComposedProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComposedProperty_UnitName(), ecorePackage.getEString(), "unitName", null, 0, 1, ComposedProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referencePropertyEClass, ReferenceProperty.class, "ReferenceProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceProperty_ReferenceType(), theCategoriesPackage.getATypeDefinition(), null, "referenceType", null, 1, 1, ReferenceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eReferencePropertyEClass, EReferenceProperty.class, "EReferenceProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEReferenceProperty_ReferenceType(), theEcorePackage.getEClass(), null, "referenceType", null, 0, 1, EReferenceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iIntrinsicTypePropertyEClass, IIntrinsicTypeProperty.class, "IIntrinsicTypeProperty", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIIntrinsicTypeProperty_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, IIntrinsicTypeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aQudvTypePropertyEClass, AQudvTypeProperty.class, "AQudvTypeProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAQudvTypeProperty_QuantityKindName(), ecorePackage.getEString(), "quantityKindName", null, 0, 1, AQudvTypeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAQudvTypeProperty_UnitName(), ecorePackage.getEString(), "unitName", null, 0, 1, AQudvTypeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intPropertyEClass, IntProperty.class, "IntProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(floatPropertyEClass, FloatProperty.class, "FloatProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringPropertyEClass, StringProperty.class, "StringProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanPropertyEClass, BooleanProperty.class, "BooleanProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(enumPropertyEClass, EnumProperty.class, "EnumProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnumProperty_Values(), this.getEnumValueDefinition(), null, "values", null, 1, -1, EnumProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnumProperty_DefaultValue(), this.getEnumValueDefinition(), null, "defaultValue", null, 0, 1, EnumProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumValueDefinitionEClass, EnumValueDefinition.class, "EnumValueDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumValueDefinition_Value(), ecorePackage.getEString(), "value", null, 0, 1, EnumValueDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourcePropertyEClass, ResourceProperty.class, "ResourceProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(staticArrayModifierEClass, StaticArrayModifier.class, "StaticArrayModifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStaticArrayModifier_ArraySize(), ecorePackage.getEInt(), "arraySize", null, 0, 1, StaticArrayModifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iArrayModifierEClass, IArrayModifier.class, "IArrayModifier", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dynamicArrayModifierEClass, DynamicArrayModifier.class, "DynamicArrayModifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} //PropertydefinitionsPackageImpl
