/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.dmf;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

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
 * @see de.dlr.sc.virsat.model.dvlm.dmf.DmfFactory
 * @model kind="package"
 * @generated
 */
public interface DmfPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dmf";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/dmf";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_dmf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DmfPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectImpl <em>DObject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectImpl
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfPackageImpl#getDObject()
	 * @generated
	 */
	int DOBJECT = 0;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOBJECT__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOBJECT__NAME = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DObject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOBJECT_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>DObject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOBJECT_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectContainerImpl <em>DObject Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectContainerImpl
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfPackageImpl#getDObjectContainer()
	 * @generated
	 */
	int DOBJECT_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOBJECT_CONTAINER__OBJECTS = 0;

	/**
	 * The number of structural features of the '<em>DObject Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOBJECT_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>DObject Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOBJECT_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.dmf.impl.UnresolveableDObjectImpl <em>Unresolveable DObject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.UnresolveableDObjectImpl
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfPackageImpl#getUnresolveableDObject()
	 * @generated
	 */
	int UNRESOLVEABLE_DOBJECT = 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVEABLE_DOBJECT__UUID = DOBJECT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVEABLE_DOBJECT__NAME = DOBJECT__NAME;

	/**
	 * The number of structural features of the '<em>Unresolveable DObject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVEABLE_DOBJECT_FEATURE_COUNT = DOBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Unresolveable DObject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVEABLE_DOBJECT_OPERATION_COUNT = DOBJECT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.dmf.DObject <em>DObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DObject</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.DObject
	 * @generated
	 */
	EClass getDObject();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.dmf.DObjectContainer <em>DObject Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DObject Container</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.DObjectContainer
	 * @generated
	 */
	EClass getDObjectContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.dmf.DObjectContainer#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objects</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.DObjectContainer#getObjects()
	 * @see #getDObjectContainer()
	 * @generated
	 */
	EReference getDObjectContainer_Objects();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.dmf.UnresolveableDObject <em>Unresolveable DObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unresolveable DObject</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.UnresolveableDObject
	 * @generated
	 */
	EClass getUnresolveableDObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DmfFactory getDmfFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectImpl <em>DObject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectImpl
		 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfPackageImpl#getDObject()
		 * @generated
		 */
		EClass DOBJECT = eINSTANCE.getDObject();
		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectContainerImpl <em>DObject Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectContainerImpl
		 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfPackageImpl#getDObjectContainer()
		 * @generated
		 */
		EClass DOBJECT_CONTAINER = eINSTANCE.getDObjectContainer();
		/**
		 * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOBJECT_CONTAINER__OBJECTS = eINSTANCE.getDObjectContainer_Objects();
		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.dmf.impl.UnresolveableDObjectImpl <em>Unresolveable DObject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.UnresolveableDObjectImpl
		 * @see de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfPackageImpl#getUnresolveableDObject()
		 * @generated
		 */
		EClass UNRESOLVEABLE_DOBJECT = eINSTANCE.getUnresolveableDObject();

	}

} //DmfPackage
