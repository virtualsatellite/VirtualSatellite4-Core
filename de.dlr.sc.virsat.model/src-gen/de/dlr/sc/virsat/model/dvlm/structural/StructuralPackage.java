/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural;

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
 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory
 * @model kind="package"
 * @generated
 */
public interface StructuralPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "structural";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/s";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_s";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StructuralPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor <em>IApplicable For</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getIApplicableFor()
	 * @generated
	 */
	int IAPPLICABLE_FOR = 0;

	/**
	 * The feature id for the '<em><b>Applicable For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IAPPLICABLE_FOR__APPLICABLE_FOR = 0;

	/**
	 * The feature id for the '<em><b>Is Applicable For All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IAPPLICABLE_FOR__IS_APPLICABLE_FOR_ALL = 1;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IAPPLICABLE_FOR__CARDINALITY = 2;

	/**
	 * The number of structural features of the '<em>IApplicable For</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IAPPLICABLE_FOR_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>IApplicable For</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IAPPLICABLE_FOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getStructuralElement()
	 * @generated
	 */
	int STRUCTURAL_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__NAME = GeneralPackage.IQUALIFIED_NAME__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__FULL_QUALIFIED_NAME = GeneralPackage.IQUALIFIED_NAME__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__SHORT_NAME = GeneralPackage.IQUALIFIED_NAME__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__DESCRIPTION = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Applicable For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__APPLICABLE_FOR = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Applicable For All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__IS_APPLICABLE_FOR_ALL = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__CARDINALITY = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Can Inherit From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__CAN_INHERIT_FROM = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Can Inherit From All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__IS_CAN_INHERIT_FROM_ALL = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Is Root Structural Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT__IS_ROOT_STRUCTURAL_ELEMENT = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_FEATURE_COUNT = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_OPERATION_COUNT = GeneralPackage.IQUALIFIED_NAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl <em>Element Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getStructuralElementInstance()
	 * @generated
	 */
	int STRUCTURAL_ELEMENT_INSTANCE = 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__DESCRIPTION = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__NAME = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Assigned Discipline</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Category Assignments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS = GeneralPackage.IUUID_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Super Seis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__SUPER_SEIS = GeneralPackage.IUUID_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__TYPE = GeneralPackage.IUUID_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Relation Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES = GeneralPackage.IUUID_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__CHILDREN = GeneralPackage.IUUID_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE__PARENT = GeneralPackage.IUUID_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Element Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 9;

	/**
	 * The operation id for the '<em>Get Contained IAssigned Disciplines</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE___GET_CONTAINED_IASSIGNED_DISCIPLINES = GeneralPackage.IUUID_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = GeneralPackage.IUUID_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Deep Children</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE___GET_DEEP_CHILDREN = GeneralPackage.IUUID_OPERATION_COUNT + 2;

	/**
	 * The number of operations of the '<em>Element Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_ELEMENT_INSTANCE_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.structural.impl.GeneralRelationImpl <em>General Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.GeneralRelationImpl
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getGeneralRelation()
	 * @generated
	 */
	int GENERAL_RELATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION__NAME = GeneralPackage.IQUALIFIED_NAME__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION__FULL_QUALIFIED_NAME = GeneralPackage.IQUALIFIED_NAME__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION__SHORT_NAME = GeneralPackage.IQUALIFIED_NAME__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Applicable For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION__APPLICABLE_FOR = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Applicable For All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION__IS_APPLICABLE_FOR_ALL = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION__CARDINALITY = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION__DESCRIPTION = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION__REFERENCED_TYPE = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>General Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION_FEATURE_COUNT = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>General Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_RELATION_OPERATION_COUNT = GeneralPackage.IQUALIFIED_NAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl <em>Relation Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl
	 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getRelationInstance()
	 * @generated
	 */
	int RELATION_INSTANCE = 4;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INSTANCE__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INSTANCE__DESCRIPTION = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INSTANCE__NAME = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INSTANCE__TYPE = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INSTANCE__REFERENCES = GeneralPackage.IUUID_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Relation Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INSTANCE_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Relation Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_INSTANCE_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor <em>IApplicable For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IApplicable For</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor
	 * @generated
	 */
	EClass getIApplicableFor();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#getApplicableFor <em>Applicable For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Applicable For</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#getApplicableFor()
	 * @see #getIApplicableFor()
	 * @generated
	 */
	EReference getIApplicableFor_ApplicableFor();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#isIsApplicableForAll <em>Is Applicable For All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Applicable For All</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#isIsApplicableForAll()
	 * @see #getIApplicableFor()
	 * @generated
	 */
	EAttribute getIApplicableFor_IsApplicableForAll();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#getCardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cardinality</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#getCardinality()
	 * @see #getIApplicableFor()
	 * @generated
	 */
	EAttribute getIApplicableFor_Cardinality();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElement
	 * @generated
	 */
	EClass getStructuralElement();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElement#isIsRootStructuralElement <em>Is Root Structural Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Root Structural Element</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElement#isIsRootStructuralElement()
	 * @see #getStructuralElement()
	 * @generated
	 */
	EAttribute getStructuralElement_IsRootStructuralElement();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance <em>Element Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance
	 * @generated
	 */
	EClass getStructuralElementInstance();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getType()
	 * @see #getStructuralElementInstance()
	 * @generated
	 */
	EReference getStructuralElementInstance_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getRelationInstances <em>Relation Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relation Instances</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getRelationInstances()
	 * @see #getStructuralElementInstance()
	 * @generated
	 */
	EReference getStructuralElementInstance_RelationInstances();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getChildren()
	 * @see #getStructuralElementInstance()
	 * @generated
	 */
	EReference getStructuralElementInstance_Children();

	/**
	 * Returns the meta object for the container reference '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getParent()
	 * @see #getStructuralElementInstance()
	 * @generated
	 */
	EReference getStructuralElementInstance_Parent();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getDeepChildren() <em>Get Deep Children</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Deep Children</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getDeepChildren()
	 * @generated
	 */
	EOperation getStructuralElementInstance__GetDeepChildren();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation <em>General Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>General Relation</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation
	 * @generated
	 */
	EClass getGeneralRelation();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation#getReferencedType <em>Referenced Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Type</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation#getReferencedType()
	 * @see #getGeneralRelation()
	 * @generated
	 */
	EReference getGeneralRelation_ReferencedType();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.structural.RelationInstance <em>Relation Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation Instance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.RelationInstance
	 * @generated
	 */
	EClass getRelationInstance();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.structural.RelationInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.RelationInstance#getType()
	 * @see #getRelationInstance()
	 * @generated
	 */
	EReference getRelationInstance_Type();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.structural.RelationInstance#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>References</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.RelationInstance#getReferences()
	 * @see #getRelationInstance()
	 * @generated
	 */
	EReference getRelationInstance_References();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StructuralFactory getStructuralFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor <em>IApplicable For</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getIApplicableFor()
		 * @generated
		 */
		EClass IAPPLICABLE_FOR = eINSTANCE.getIApplicableFor();

		/**
		 * The meta object literal for the '<em><b>Applicable For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IAPPLICABLE_FOR__APPLICABLE_FOR = eINSTANCE.getIApplicableFor_ApplicableFor();

		/**
		 * The meta object literal for the '<em><b>Is Applicable For All</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IAPPLICABLE_FOR__IS_APPLICABLE_FOR_ALL = eINSTANCE.getIApplicableFor_IsApplicableForAll();

		/**
		 * The meta object literal for the '<em><b>Cardinality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IAPPLICABLE_FOR__CARDINALITY = eINSTANCE.getIApplicableFor_Cardinality();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getStructuralElement()
		 * @generated
		 */
		EClass STRUCTURAL_ELEMENT = eINSTANCE.getStructuralElement();

		/**
		 * The meta object literal for the '<em><b>Is Root Structural Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURAL_ELEMENT__IS_ROOT_STRUCTURAL_ELEMENT = eINSTANCE.getStructuralElement_IsRootStructuralElement();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl <em>Element Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getStructuralElementInstance()
		 * @generated
		 */
		EClass STRUCTURAL_ELEMENT_INSTANCE = eINSTANCE.getStructuralElementInstance();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURAL_ELEMENT_INSTANCE__TYPE = eINSTANCE.getStructuralElementInstance_Type();

		/**
		 * The meta object literal for the '<em><b>Relation Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES = eINSTANCE.getStructuralElementInstance_RelationInstances();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURAL_ELEMENT_INSTANCE__CHILDREN = eINSTANCE.getStructuralElementInstance_Children();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURAL_ELEMENT_INSTANCE__PARENT = eINSTANCE.getStructuralElementInstance_Parent();

		/**
		 * The meta object literal for the '<em><b>Get Deep Children</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation STRUCTURAL_ELEMENT_INSTANCE___GET_DEEP_CHILDREN = eINSTANCE.getStructuralElementInstance__GetDeepChildren();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.structural.impl.GeneralRelationImpl <em>General Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.GeneralRelationImpl
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getGeneralRelation()
		 * @generated
		 */
		EClass GENERAL_RELATION = eINSTANCE.getGeneralRelation();

		/**
		 * The meta object literal for the '<em><b>Referenced Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERAL_RELATION__REFERENCED_TYPE = eINSTANCE.getGeneralRelation_ReferencedType();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl <em>Relation Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl
		 * @see de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralPackageImpl#getRelationInstance()
		 * @generated
		 */
		EClass RELATION_INSTANCE = eINSTANCE.getRelationInstance();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION_INSTANCE__TYPE = eINSTANCE.getRelationInstance_Type();

		/**
		 * The meta object literal for the '<em><b>References</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION_INSTANCE__REFERENCES = eINSTANCE.getRelationInstance_References();

	}

} //StructuralPackage
