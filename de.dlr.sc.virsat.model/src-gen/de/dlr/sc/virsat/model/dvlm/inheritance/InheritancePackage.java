/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.inheritance;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceFactory
 * @model kind="package"
 * @generated
 */
public interface InheritancePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "inheritance";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/i";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_i";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InheritancePackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom <em>ICan Inherit From</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl#getICanInheritFrom()
	 * @generated
	 */
	int ICAN_INHERIT_FROM = 0;

	/**
	 * The feature id for the '<em><b>Can Inherit From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICAN_INHERIT_FROM__CAN_INHERIT_FROM = 0;

	/**
	 * The feature id for the '<em><b>Is Can Inherit From All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICAN_INHERIT_FROM__IS_CAN_INHERIT_FROM_ALL = 1;

	/**
	 * The number of structural features of the '<em>ICan Inherit From</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICAN_INHERIT_FROM_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>ICan Inherit From</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICAN_INHERIT_FROM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom <em>IInherits From</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl#getIInheritsFrom()
	 * @generated
	 */
	int IINHERITS_FROM = 1;

	/**
	 * The feature id for the '<em><b>Super Seis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINHERITS_FROM__SUPER_SEIS = 0;

	/**
	 * The number of structural features of the '<em>IInherits From</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINHERITS_FROM_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IInherits From</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINHERITS_FROM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink <em>IInheritance Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl#getIInheritanceLink()
	 * @generated
	 */
	int IINHERITANCE_LINK = 2;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINHERITANCE_LINK__SUPER_TIS = 0;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINHERITANCE_LINK__IS_INHERITED = 1;

	/**
	 * The number of structural features of the '<em>IInheritance Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINHERITANCE_LINK_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>IInheritance Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINHERITANCE_LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink <em>IOverridable Inheritance Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl#getIOverridableInheritanceLink()
	 * @generated
	 */
	int IOVERRIDABLE_INHERITANCE_LINK = 3;

	/**
	 * The feature id for the '<em><b>Super Tis</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IOVERRIDABLE_INHERITANCE_LINK__SUPER_TIS = IINHERITANCE_LINK__SUPER_TIS;

	/**
	 * The feature id for the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IOVERRIDABLE_INHERITANCE_LINK__IS_INHERITED = IINHERITANCE_LINK__IS_INHERITED;

	/**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE = IINHERITANCE_LINK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>IOverridable Inheritance Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IOVERRIDABLE_INHERITANCE_LINK_FEATURE_COUNT = IINHERITANCE_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>IOverridable Inheritance Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IOVERRIDABLE_INHERITANCE_LINK_OPERATION_COUNT = IINHERITANCE_LINK_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom <em>ICan Inherit From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ICan Inherit From</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom
	 * @generated
	 */
	EClass getICanInheritFrom();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom#getCanInheritFrom <em>Can Inherit From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Can Inherit From</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom#getCanInheritFrom()
	 * @see #getICanInheritFrom()
	 * @generated
	 */
	EReference getICanInheritFrom_CanInheritFrom();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom#isIsCanInheritFromAll <em>Is Can Inherit From All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Can Inherit From All</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom#isIsCanInheritFromAll()
	 * @see #getICanInheritFrom()
	 * @generated
	 */
	EAttribute getICanInheritFrom_IsCanInheritFromAll();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom <em>IInherits From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IInherits From</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom
	 * @generated
	 */
	EClass getIInheritsFrom();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom#getSuperSeis <em>Super Seis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Super Seis</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom#getSuperSeis()
	 * @see #getIInheritsFrom()
	 * @generated
	 */
	EReference getIInheritsFrom_SuperSeis();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink <em>IInheritance Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IInheritance Link</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink
	 * @generated
	 */
	EClass getIInheritanceLink();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink#getSuperTis <em>Super Tis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Super Tis</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink#getSuperTis()
	 * @see #getIInheritanceLink()
	 * @generated
	 */
	EReference getIInheritanceLink_SuperTis();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink#isIsInherited <em>Is Inherited</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Inherited</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink#isIsInherited()
	 * @see #getIInheritanceLink()
	 * @generated
	 */
	EAttribute getIInheritanceLink_IsInherited();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink <em>IOverridable Inheritance Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IOverridable Inheritance Link</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink
	 * @generated
	 */
	EClass getIOverridableInheritanceLink();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink#isOverride <em>Override</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Override</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink#isOverride()
	 * @see #getIOverridableInheritanceLink()
	 * @generated
	 */
	EAttribute getIOverridableInheritanceLink_Override();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	InheritanceFactory getInheritanceFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom <em>ICan Inherit From</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom
		 * @see de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl#getICanInheritFrom()
		 * @generated
		 */
		EClass ICAN_INHERIT_FROM = eINSTANCE.getICanInheritFrom();

		/**
		 * The meta object literal for the '<em><b>Can Inherit From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICAN_INHERIT_FROM__CAN_INHERIT_FROM = eINSTANCE.getICanInheritFrom_CanInheritFrom();

		/**
		 * The meta object literal for the '<em><b>Is Can Inherit From All</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ICAN_INHERIT_FROM__IS_CAN_INHERIT_FROM_ALL = eINSTANCE.getICanInheritFrom_IsCanInheritFromAll();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom <em>IInherits From</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom
		 * @see de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl#getIInheritsFrom()
		 * @generated
		 */
		EClass IINHERITS_FROM = eINSTANCE.getIInheritsFrom();

		/**
		 * The meta object literal for the '<em><b>Super Seis</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IINHERITS_FROM__SUPER_SEIS = eINSTANCE.getIInheritsFrom_SuperSeis();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink <em>IInheritance Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink
		 * @see de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl#getIInheritanceLink()
		 * @generated
		 */
		EClass IINHERITANCE_LINK = eINSTANCE.getIInheritanceLink();

		/**
		 * The meta object literal for the '<em><b>Super Tis</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IINHERITANCE_LINK__SUPER_TIS = eINSTANCE.getIInheritanceLink_SuperTis();

		/**
		 * The meta object literal for the '<em><b>Is Inherited</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IINHERITANCE_LINK__IS_INHERITED = eINSTANCE.getIInheritanceLink_IsInherited();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink <em>IOverridable Inheritance Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink
		 * @see de.dlr.sc.virsat.model.dvlm.inheritance.impl.InheritancePackageImpl#getIOverridableInheritanceLink()
		 * @generated
		 */
		EClass IOVERRIDABLE_INHERITANCE_LINK = eINSTANCE.getIOverridableInheritanceLink();

		/**
		 * The meta object literal for the '<em><b>Override</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE = eINSTANCE.getIOverridableInheritanceLink_Override();

	}

} //InheritancePackage
