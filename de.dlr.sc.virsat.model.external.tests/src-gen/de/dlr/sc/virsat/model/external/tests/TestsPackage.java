/**
 */
package de.dlr.sc.virsat.model.external.tests;

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
 * @see de.dlr.sc.virsat.model.external.tests.TestsFactory
 * @model kind="package"
 * @generated
 */
public interface TestsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tests";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/external/tests";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tests";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestsPackage eINSTANCE = de.dlr.sc.virsat.model.external.tests.impl.TestsPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.external.tests.impl.SuperTypeImpl <em>Super Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.external.tests.impl.SuperTypeImpl
	 * @see de.dlr.sc.virsat.model.external.tests.impl.TestsPackageImpl#getSuperType()
	 * @generated
	 */
	int SUPER_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Super Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_TYPE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Super Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.external.tests.impl.ExternalTestTypeImpl <em>External Test Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.external.tests.impl.ExternalTestTypeImpl
	 * @see de.dlr.sc.virsat.model.external.tests.impl.TestsPackageImpl#getExternalTestType()
	 * @generated
	 */
	int EXTERNAL_TEST_TYPE = 0;

	/**
	 * The number of structural features of the '<em>External Test Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_TEST_TYPE_FEATURE_COUNT = SUPER_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>External Test Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_TEST_TYPE_OPERATION_COUNT = SUPER_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.external.tests.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.external.tests.impl.ContainerImpl
	 * @see de.dlr.sc.virsat.model.external.tests.impl.TestsPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 2;

	/**
	 * The feature id for the '<em><b>Objects</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__OBJECTS = 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.external.tests.ExternalTestType <em>External Test Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Test Type</em>'.
	 * @see de.dlr.sc.virsat.model.external.tests.ExternalTestType
	 * @generated
	 */
	EClass getExternalTestType();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.external.tests.SuperType <em>Super Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Super Type</em>'.
	 * @see de.dlr.sc.virsat.model.external.tests.SuperType
	 * @generated
	 */
	EClass getSuperType();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.external.tests.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see de.dlr.sc.virsat.model.external.tests.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference '{@link de.dlr.sc.virsat.model.external.tests.Container#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Objects</em>'.
	 * @see de.dlr.sc.virsat.model.external.tests.Container#getObjects()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Objects();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestsFactory getTestsFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.external.tests.impl.ExternalTestTypeImpl <em>External Test Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.external.tests.impl.ExternalTestTypeImpl
		 * @see de.dlr.sc.virsat.model.external.tests.impl.TestsPackageImpl#getExternalTestType()
		 * @generated
		 */
		EClass EXTERNAL_TEST_TYPE = eINSTANCE.getExternalTestType();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.external.tests.impl.SuperTypeImpl <em>Super Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.external.tests.impl.SuperTypeImpl
		 * @see de.dlr.sc.virsat.model.external.tests.impl.TestsPackageImpl#getSuperType()
		 * @generated
		 */
		EClass SUPER_TYPE = eINSTANCE.getSuperType();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.external.tests.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.external.tests.impl.ContainerImpl
		 * @see de.dlr.sc.virsat.model.external.tests.impl.TestsPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Objects</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__OBJECTS = eINSTANCE.getContainer_Objects();

	}

} //TestsPackage
