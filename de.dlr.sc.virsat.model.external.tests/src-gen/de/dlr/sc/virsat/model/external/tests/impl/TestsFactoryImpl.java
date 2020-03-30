/**
 */
package de.dlr.sc.virsat.model.external.tests.impl;

import de.dlr.sc.virsat.model.external.tests.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestsFactoryImpl extends EFactoryImpl implements TestsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TestsFactory init() {
		try {
			TestsFactory theTestsFactory = (TestsFactory)EPackage.Registry.INSTANCE.getEFactory(TestsPackage.eNS_URI);
			if (theTestsFactory != null) {
				return theTestsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TestsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TestsPackage.EXTERNAL_TEST_TYPE: return createExternalTestType();
			case TestsPackage.SUPER_TYPE: return createSuperType();
			case TestsPackage.CONTAINER: return createContainer();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExternalTestType createExternalTestType() {
		ExternalTestTypeImpl externalTestType = new ExternalTestTypeImpl();
		return externalTestType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SuperType createSuperType() {
		SuperTypeImpl superType = new SuperTypeImpl();
		return superType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public de.dlr.sc.virsat.model.external.tests.Container createContainer() {
		ContainerImpl container = new ContainerImpl();
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestsPackage getTestsPackage() {
		return (TestsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TestsPackage getPackage() {
		return TestsPackage.eINSTANCE;
	}

} //TestsFactoryImpl
