/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
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
 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory
 * @model kind="package"
 * @generated
 */
public interface CategoriesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "categories";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/cp";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_cp";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CategoriesPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeDefinitionImpl <em>AType Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeDefinitionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getATypeDefinition()
	 * @generated
	 */
	int ATYPE_DEFINITION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_DEFINITION__NAME = GeneralPackage.IQUALIFIED_NAME__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_DEFINITION__FULL_QUALIFIED_NAME = GeneralPackage.IQUALIFIED_NAME__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_DEFINITION__SHORT_NAME = GeneralPackage.IQUALIFIED_NAME__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_DEFINITION__DESCRIPTION = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>AType Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_DEFINITION_FEATURE_COUNT = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>AType Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_DEFINITION_OPERATION_COUNT = GeneralPackage.IQUALIFIED_NAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl <em>AType Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getATypeInstance()
	 * @generated
	 */
	int ATYPE_INSTANCE = 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE__COMMENT = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE__SUPER_TIS = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE__IS_INHERITED = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE__TYPE = GeneralPackage.IUUID_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>AType Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = GeneralPackage.IUUID_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = GeneralPackage.IUUID_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>AType Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATYPE_INSTANCE_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__NAME = ATYPE_DEFINITION__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__FULL_QUALIFIED_NAME = ATYPE_DEFINITION__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__SHORT_NAME = ATYPE_DEFINITION__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__DESCRIPTION = ATYPE_DEFINITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Applicable For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__APPLICABLE_FOR = ATYPE_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Applicable For All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__IS_APPLICABLE_FOR_ALL = ATYPE_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__CARDINALITY = ATYPE_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Equation Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__EQUATION_DEFINITIONS = ATYPE_DEFINITION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__PROPERTIES = ATYPE_DEFINITION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__IS_ABSTRACT = ATYPE_DEFINITION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Extends Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__EXTENDS_CATEGORY = ATYPE_DEFINITION_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = ATYPE_DEFINITION_FEATURE_COUNT + 7;

	/**
	 * The operation id for the '<em>Get All Properties</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY___GET_ALL_PROPERTIES = ATYPE_DEFINITION_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Is Extension Of</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY___IS_EXTENSION_OF__ATYPEDEFINITION = ATYPE_DEFINITION_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Get All Equation Definitions</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY___GET_ALL_EQUATION_DEFINITIONS = ATYPE_DEFINITION_OPERATION_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Cardinality</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY___GET_CARDINALITY = ATYPE_DEFINITION_OPERATION_COUNT + 3;

	/**
	 * The number of operations of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_OPERATION_COUNT = ATYPE_DEFINITION_OPERATION_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryAssignmentImpl <em>Category Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryAssignmentImpl
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getCategoryAssignment()
	 * @generated
	 */
	int CATEGORY_ASSIGNMENT = 3;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT__UUID = ATYPE_INSTANCE__UUID;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT__COMMENT = ATYPE_INSTANCE__COMMENT;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT__SUPER_TIS = ATYPE_INSTANCE__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT__IS_INHERITED = ATYPE_INSTANCE__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT__TYPE = ATYPE_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT__NAME = ATYPE_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Equation Section</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT__EQUATION_SECTION = ATYPE_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Property Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT__PROPERTY_INSTANCES = ATYPE_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Category Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT_FEATURE_COUNT = ATYPE_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT___GET_FULL_QUALIFIED_INSTANCE_NAME = ATYPE_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;

	/**
	 * The operation id for the '<em>Get Category Assignment Container</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT___GET_CATEGORY_ASSIGNMENT_CONTAINER = ATYPE_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER;

	/**
	 * The number of operations of the '<em>Category Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_ASSIGNMENT_OPERATION_COUNT = ATYPE_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer <em>ICategory Assignment Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer
	 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getICategoryAssignmentContainer()
	 * @generated
	 */
	int ICATEGORY_ASSIGNMENT_CONTAINER = 4;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICATEGORY_ASSIGNMENT_CONTAINER__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Category Assignments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>ICategory Assignment Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICATEGORY_ASSIGNMENT_CONTAINER_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>ICategory Assignment Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICATEGORY_ASSIGNMENT_CONTAINER_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition <em>AType Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AType Definition</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
	 * @generated
	 */
	EClass getATypeDefinition();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance <em>AType Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AType Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance
	 * @generated
	 */
	EClass getATypeInstance();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance#getType()
	 * @see #getATypeInstance()
	 * @generated
	 */
	EReference getATypeInstance_Type();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance#getCategoryAssignmentContainer() <em>Get Category Assignment Container</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Category Assignment Container</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance#getCategoryAssignmentContainer()
	 * @generated
	 */
	EOperation getATypeInstance__GetCategoryAssignmentContainer();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.Category
	 * @generated
	 */
	EClass getCategory();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.Category#getProperties()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_Properties();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#isIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.Category#isIsAbstract()
	 * @see #getCategory()
	 * @generated
	 */
	EAttribute getCategory_IsAbstract();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#getExtendsCategory <em>Extends Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extends Category</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.Category#getExtendsCategory()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_ExtendsCategory();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#getAllProperties() <em>Get All Properties</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get All Properties</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.Category#getAllProperties()
	 * @generated
	 */
	EOperation getCategory__GetAllProperties();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#isExtensionOf(de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition) <em>Is Extension Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Extension Of</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.Category#isExtensionOf(de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition)
	 * @generated
	 */
	EOperation getCategory__IsExtensionOf__ATypeDefinition();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#getAllEquationDefinitions() <em>Get All Equation Definitions</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get All Equation Definitions</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.Category#getAllEquationDefinitions()
	 * @generated
	 */
	EOperation getCategory__GetAllEquationDefinitions();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#getCardinality() <em>Get Cardinality</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Cardinality</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.Category#getCardinality()
	 * @generated
	 */
	EOperation getCategory__GetCardinality();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment <em>Category Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category Assignment</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment
	 * @generated
	 */
	EClass getCategoryAssignment();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment#getPropertyInstances <em>Property Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property Instances</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment#getPropertyInstances()
	 * @see #getCategoryAssignment()
	 * @generated
	 */
	EReference getCategoryAssignment_PropertyInstances();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer <em>ICategory Assignment Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ICategory Assignment Container</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer
	 * @generated
	 */
	EClass getICategoryAssignmentContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer#getCategoryAssignments <em>Category Assignments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Category Assignments</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer#getCategoryAssignments()
	 * @see #getICategoryAssignmentContainer()
	 * @generated
	 */
	EReference getICategoryAssignmentContainer_CategoryAssignments();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CategoriesFactory getCategoriesFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeDefinitionImpl <em>AType Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeDefinitionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getATypeDefinition()
		 * @generated
		 */
		EClass ATYPE_DEFINITION = eINSTANCE.getATypeDefinition();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl <em>AType Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getATypeInstance()
		 * @generated
		 */
		EClass ATYPE_INSTANCE = eINSTANCE.getATypeInstance();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATYPE_INSTANCE__TYPE = eINSTANCE.getATypeInstance_Type();

		/**
		 * The meta object literal for the '<em><b>Get Category Assignment Container</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ATYPE_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER = eINSTANCE.getATypeInstance__GetCategoryAssignmentContainer();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY__PROPERTIES = eINSTANCE.getCategory_Properties();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY__IS_ABSTRACT = eINSTANCE.getCategory_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Extends Category</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY__EXTENDS_CATEGORY = eINSTANCE.getCategory_ExtendsCategory();

		/**
		 * The meta object literal for the '<em><b>Get All Properties</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CATEGORY___GET_ALL_PROPERTIES = eINSTANCE.getCategory__GetAllProperties();

		/**
		 * The meta object literal for the '<em><b>Is Extension Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CATEGORY___IS_EXTENSION_OF__ATYPEDEFINITION = eINSTANCE.getCategory__IsExtensionOf__ATypeDefinition();

		/**
		 * The meta object literal for the '<em><b>Get All Equation Definitions</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CATEGORY___GET_ALL_EQUATION_DEFINITIONS = eINSTANCE.getCategory__GetAllEquationDefinitions();

		/**
		 * The meta object literal for the '<em><b>Get Cardinality</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CATEGORY___GET_CARDINALITY = eINSTANCE.getCategory__GetCardinality();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryAssignmentImpl <em>Category Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryAssignmentImpl
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getCategoryAssignment()
		 * @generated
		 */
		EClass CATEGORY_ASSIGNMENT = eINSTANCE.getCategoryAssignment();

		/**
		 * The meta object literal for the '<em><b>Property Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY_ASSIGNMENT__PROPERTY_INSTANCES = eINSTANCE.getCategoryAssignment_PropertyInstances();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer <em>ICategory Assignment Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer
		 * @see de.dlr.sc.virsat.model.dvlm.categories.impl.CategoriesPackageImpl#getICategoryAssignmentContainer()
		 * @generated
		 */
		EClass ICATEGORY_ASSIGNMENT_CONTAINER = eINSTANCE.getICategoryAssignmentContainer();

		/**
		 * The meta object literal for the '<em><b>Category Assignments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS = eINSTANCE.getICategoryAssignmentContainer_CategoryAssignments();

	}

} //CategoriesPackage
