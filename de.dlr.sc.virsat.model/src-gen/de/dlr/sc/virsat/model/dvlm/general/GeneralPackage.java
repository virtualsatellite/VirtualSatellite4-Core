/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.general;

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
 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralFactory
 * @model kind="package"
 * @generated
 */
public interface GeneralPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "general";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/g";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_g";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GeneralPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.general.IName <em>IName</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.general.IName
	 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIName()
	 * @generated
	 */
	int INAME = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAME__NAME = 0;

	/**
	 * The number of structural features of the '<em>IName</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAME_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IName</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAME_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.general.IDescription <em>IDescription</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.general.IDescription
	 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIDescription()
	 * @generated
	 */
	int IDESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDESCRIPTION__DESCRIPTION = 0;

	/**
	 * The number of structural features of the '<em>IDescription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDESCRIPTION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IDescription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDESCRIPTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.general.IUuid <em>IUuid</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.general.IUuid
	 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIUuid()
	 * @generated
	 */
	int IUUID = 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUUID__UUID = 0;

	/**
	 * The number of structural features of the '<em>IUuid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUUID_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IUuid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUUID_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName <em>IQualified Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.general.IQualifiedName
	 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIQualifiedName()
	 * @generated
	 */
	int IQUALIFIED_NAME = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IQUALIFIED_NAME__NAME = 0;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IQUALIFIED_NAME__FULL_QUALIFIED_NAME = 1;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IQUALIFIED_NAME__SHORT_NAME = 2;

	/**
	 * The number of structural features of the '<em>IQualified Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IQUALIFIED_NAME_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>IQualified Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IQUALIFIED_NAME_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.general.IComment <em>IComment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.general.IComment
	 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIComment()
	 * @generated
	 */
	int ICOMMENT = 4;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMMENT__COMMENT = 0;

	/**
	 * The number of structural features of the '<em>IComment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IComment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline <em>IAssigned Discipline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline
	 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIAssignedDiscipline()
	 * @generated
	 */
	int IASSIGNED_DISCIPLINE = 5;

	/**
	 * The feature id for the '<em><b>Assigned Discipline</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE = 0;

	/**
	 * The number of structural features of the '<em>IAssigned Discipline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IASSIGNED_DISCIPLINE_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Get Contained IAssigned Disciplines</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IASSIGNED_DISCIPLINE___GET_CONTAINED_IASSIGNED_DISCIPLINES = 0;

	/**
	 * The number of operations of the '<em>IAssigned Discipline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IASSIGNED_DISCIPLINE_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.general.IInstance <em>IInstance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.general.IInstance
	 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIInstance()
	 * @generated
	 */
	int IINSTANCE = 6;

	/**
	 * The number of structural features of the '<em>IInstance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINSTANCE_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = 0;

	/**
	 * The number of operations of the '<em>IInstance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINSTANCE_OPERATION_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.general.IName <em>IName</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IName</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IName
	 * @generated
	 */
	EClass getIName();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.general.IName#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IName#getName()
	 * @see #getIName()
	 * @generated
	 */
	EAttribute getIName_Name();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.general.IDescription <em>IDescription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IDescription</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IDescription
	 * @generated
	 */
	EClass getIDescription();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.general.IDescription#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IDescription#getDescription()
	 * @see #getIDescription()
	 * @generated
	 */
	EAttribute getIDescription_Description();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.general.IUuid <em>IUuid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IUuid</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IUuid
	 * @generated
	 */
	EClass getIUuid();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.general.IUuid#getUuid <em>Uuid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uuid</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IUuid#getUuid()
	 * @see #getIUuid()
	 * @generated
	 */
	EAttribute getIUuid_Uuid();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName <em>IQualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IQualified Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IQualifiedName
	 * @generated
	 */
	EClass getIQualifiedName();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getName()
	 * @see #getIQualifiedName()
	 * @generated
	 */
	EAttribute getIQualifiedName_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getFullQualifiedName <em>Full Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full Qualified Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getFullQualifiedName()
	 * @see #getIQualifiedName()
	 * @generated
	 */
	EAttribute getIQualifiedName_FullQualifiedName();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getShortName <em>Short Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getShortName()
	 * @see #getIQualifiedName()
	 * @generated
	 */
	EAttribute getIQualifiedName_ShortName();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.general.IComment <em>IComment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IComment</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IComment
	 * @generated
	 */
	EClass getIComment();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.general.IComment#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IComment#getComment()
	 * @see #getIComment()
	 * @generated
	 */
	EAttribute getIComment_Comment();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline <em>IAssigned Discipline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IAssigned Discipline</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline
	 * @generated
	 */
	EClass getIAssignedDiscipline();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline#getAssignedDiscipline <em>Assigned Discipline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Assigned Discipline</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline#getAssignedDiscipline()
	 * @see #getIAssignedDiscipline()
	 * @generated
	 */
	EReference getIAssignedDiscipline_AssignedDiscipline();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline#getContainedIAssignedDisciplines() <em>Get Contained IAssigned Disciplines</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Contained IAssigned Disciplines</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline#getContainedIAssignedDisciplines()
	 * @generated
	 */
	EOperation getIAssignedDiscipline__GetContainedIAssignedDisciplines();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.general.IInstance <em>IInstance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IInstance</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IInstance
	 * @generated
	 */
	EClass getIInstance();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.general.IInstance#getFullQualifiedInstanceName() <em>Get Full Qualified Instance Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Full Qualified Instance Name</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IInstance#getFullQualifiedInstanceName()
	 * @generated
	 */
	EOperation getIInstance__GetFullQualifiedInstanceName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GeneralFactory getGeneralFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.general.IName <em>IName</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.general.IName
		 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIName()
		 * @generated
		 */
		EClass INAME = eINSTANCE.getIName();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INAME__NAME = eINSTANCE.getIName_Name();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.general.IDescription <em>IDescription</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.general.IDescription
		 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIDescription()
		 * @generated
		 */
		EClass IDESCRIPTION = eINSTANCE.getIDescription();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDESCRIPTION__DESCRIPTION = eINSTANCE.getIDescription_Description();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.general.IUuid <em>IUuid</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.general.IUuid
		 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIUuid()
		 * @generated
		 */
		EClass IUUID = eINSTANCE.getIUuid();

		/**
		 * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IUUID__UUID = eINSTANCE.getIUuid_Uuid();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName <em>IQualified Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.general.IQualifiedName
		 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIQualifiedName()
		 * @generated
		 */
		EClass IQUALIFIED_NAME = eINSTANCE.getIQualifiedName();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IQUALIFIED_NAME__NAME = eINSTANCE.getIQualifiedName_Name();

		/**
		 * The meta object literal for the '<em><b>Full Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IQUALIFIED_NAME__FULL_QUALIFIED_NAME = eINSTANCE.getIQualifiedName_FullQualifiedName();

		/**
		 * The meta object literal for the '<em><b>Short Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IQUALIFIED_NAME__SHORT_NAME = eINSTANCE.getIQualifiedName_ShortName();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.general.IComment <em>IComment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.general.IComment
		 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIComment()
		 * @generated
		 */
		EClass ICOMMENT = eINSTANCE.getIComment();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ICOMMENT__COMMENT = eINSTANCE.getIComment_Comment();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline <em>IAssigned Discipline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline
		 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIAssignedDiscipline()
		 * @generated
		 */
		EClass IASSIGNED_DISCIPLINE = eINSTANCE.getIAssignedDiscipline();

		/**
		 * The meta object literal for the '<em><b>Assigned Discipline</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE = eINSTANCE.getIAssignedDiscipline_AssignedDiscipline();

		/**
		 * The meta object literal for the '<em><b>Get Contained IAssigned Disciplines</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IASSIGNED_DISCIPLINE___GET_CONTAINED_IASSIGNED_DISCIPLINES = eINSTANCE.getIAssignedDiscipline__GetContainedIAssignedDisciplines();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.general.IInstance <em>IInstance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.general.IInstance
		 * @see de.dlr.sc.virsat.model.dvlm.general.impl.GeneralPackageImpl#getIInstance()
		 * @generated
		 */
		EClass IINSTANCE = eINSTANCE.getIInstance();

		/**
		 * The meta object literal for the '<em><b>Get Full Qualified Instance Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IINSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME = eINSTANCE.getIInstance__GetFullQualifiedInstanceName();

	}

} //GeneralPackage
