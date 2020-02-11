/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.types.impl;

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

import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

import de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.units.UnitsPackage;

import de.dlr.sc.virsat.model.dvlm.units.impl.UnitsPackageImpl;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesPackageImpl extends EPackageImpl implements TypesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uuidEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uriEDataType = null;

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
	 * @see de.dlr.sc.virsat.model.dvlm.types.TypesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TypesPackageImpl() {
		super(eNS_URI, TypesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link TypesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TypesPackage init() {
		if (isInited) return (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Obtain or create and register package
		TypesPackageImpl theTypesPackage = (TypesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TypesPackageImpl());

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
		CategoriesPackageImpl theCategoriesPackage = (CategoriesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI) instanceof CategoriesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CategoriesPackage.eNS_URI) : CategoriesPackage.eINSTANCE);
		PropertydefinitionsPackageImpl thePropertydefinitionsPackage = (PropertydefinitionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PropertydefinitionsPackage.eNS_URI) instanceof PropertydefinitionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PropertydefinitionsPackage.eNS_URI) : PropertydefinitionsPackage.eINSTANCE);
		PropertyinstancesPackageImpl thePropertyinstancesPackage = (PropertyinstancesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PropertyinstancesPackage.eNS_URI) instanceof PropertyinstancesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PropertyinstancesPackage.eNS_URI) : PropertyinstancesPackage.eINSTANCE);
		QudvPackageImpl theQudvPackage = (QudvPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QudvPackage.eNS_URI) instanceof QudvPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QudvPackage.eNS_URI) : QudvPackage.eINSTANCE);
		CalculationPackageImpl theCalculationPackage = (CalculationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CalculationPackage.eNS_URI) instanceof CalculationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CalculationPackage.eNS_URI) : CalculationPackage.eINSTANCE);
		InheritancePackageImpl theInheritancePackage = (InheritancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI) instanceof InheritancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InheritancePackage.eNS_URI) : InheritancePackage.eINSTANCE);
		DmfPackageImpl theDmfPackage = (DmfPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DmfPackage.eNS_URI) instanceof DmfPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DmfPackage.eNS_URI) : DmfPackage.eINSTANCE);

		// Create package meta-data objects
		theTypesPackage.createPackageContents();
		theDVLMPackage.createPackageContents();
		theStructuralPackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theConceptsPackage.createPackageContents();
		theRolesPackage.createPackageContents();
		theUnitsPackage.createPackageContents();
		theCategoriesPackage.createPackageContents();
		thePropertydefinitionsPackage.createPackageContents();
		thePropertyinstancesPackage.createPackageContents();
		theQudvPackage.createPackageContents();
		theCalculationPackage.createPackageContents();
		theInheritancePackage.createPackageContents();
		theDmfPackage.createPackageContents();

		// Initialize created meta-data
		theTypesPackage.initializePackageContents();
		theDVLMPackage.initializePackageContents();
		theStructuralPackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theConceptsPackage.initializePackageContents();
		theRolesPackage.initializePackageContents();
		theUnitsPackage.initializePackageContents();
		theCategoriesPackage.initializePackageContents();
		thePropertydefinitionsPackage.initializePackageContents();
		thePropertyinstancesPackage.initializePackageContents();
		theQudvPackage.initializePackageContents();
		theCalculationPackage.initializePackageContents();
		theInheritancePackage.initializePackageContents();
		theDmfPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTypesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TypesPackage.eNS_URI, theTypesPackage);
		return theTypesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUuid() {
		return uuidEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getURI() {
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesFactory getTypesFactory() {
		return (TypesFactory)getEFactoryInstance();
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

		// Create data types
		uuidEDataType = createEDataType(UUID);
		uriEDataType = createEDataType(URI);
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

		// Initialize data types
		initEDataType(uuidEDataType, VirSatUuid.class, "Uuid", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
	}

} //TypesPackageImpl
