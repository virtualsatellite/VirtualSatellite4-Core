/**
 */
package de.dlr.sc.virsat.model.external.tests;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.external.tests.TestsPackage
 * @generated
 */
public interface TestsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestsFactory eINSTANCE = de.dlr.sc.virsat.model.external.tests.impl.TestsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>External Test Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Test Type</em>'.
	 * @generated
	 */
	ExternalTestType createExternalTestType();

	/**
	 * Returns a new object of class '<em>Super Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Super Type</em>'.
	 * @generated
	 */
	SuperType createSuperType();

	/**
	 * Returns a new object of class '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container</em>'.
	 * @generated
	 */
	Container createContainer();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TestsPackage getTestsPackage();

} //TestsFactory
