/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.extension.tests.tests.util;

import de.dlr.sc.virsat.model.dvlm.dmf.DObject;

import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.ext.core.core.GenericCategory;

import de.dlr.sc.virsat.model.extension.tests.tests.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage
 * @generated
 */
public class TestsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TestsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TestsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestsSwitch<Adapter> modelSwitch =
		new TestsSwitch<Adapter>() {
			@Override
			public Adapter caseTestCategoryAllProperty(TestCategoryAllProperty object) {
				return createTestCategoryAllPropertyAdapter();
			}
			@Override
			public Adapter caseTestCategoryComposition(TestCategoryComposition object) {
				return createTestCategoryCompositionAdapter();
			}
			@Override
			public Adapter caseTestCategoryReference(TestCategoryReference object) {
				return createTestCategoryReferenceAdapter();
			}
			@Override
			public Adapter caseTestCategoryIntrinsicArray(TestCategoryIntrinsicArray object) {
				return createTestCategoryIntrinsicArrayAdapter();
			}
			@Override
			public Adapter caseTestCategoryCompositionArray(TestCategoryCompositionArray object) {
				return createTestCategoryCompositionArrayAdapter();
			}
			@Override
			public Adapter caseTestCategoryReferenceArray(TestCategoryReferenceArray object) {
				return createTestCategoryReferenceArrayAdapter();
			}
			@Override
			public Adapter caseTestCategoryBeanA(TestCategoryBeanA object) {
				return createTestCategoryBeanAAdapter();
			}
			@Override
			public Adapter caseTestCategoryBeanB(TestCategoryBeanB object) {
				return createTestCategoryBeanBAdapter();
			}
			@Override
			public Adapter caseTestCategoryBeanAbstract(TestCategoryBeanAbstract object) {
				return createTestCategoryBeanAbstractAdapter();
			}
			@Override
			public Adapter caseTestCategoryBeanConcrete(TestCategoryBeanConcrete object) {
				return createTestCategoryBeanConcreteAdapter();
			}
			@Override
			public Adapter caseTestCategoryBase(TestCategoryBase object) {
				return createTestCategoryBaseAdapter();
			}
			@Override
			public Adapter caseTestCategoryExtends(TestCategoryExtends object) {
				return createTestCategoryExtendsAdapter();
			}
			@Override
			public Adapter caseTestParameter(TestParameter object) {
				return createTestParameterAdapter();
			}
			@Override
			public Adapter caseTestMassParameters(TestMassParameters object) {
				return createTestMassParametersAdapter();
			}
			@Override
			public Adapter caseTestCrossLinkedParametersWithCalculation(TestCrossLinkedParametersWithCalculation object) {
				return createTestCrossLinkedParametersWithCalculationAdapter();
			}
			@Override
			public Adapter caseEReferenceTest(EReferenceTest object) {
				return createEReferenceTestAdapter();
			}
			@Override
			public Adapter caseIUuid(IUuid object) {
				return createIUuidAdapter();
			}
			@Override
			public Adapter caseIName(IName object) {
				return createINameAdapter();
			}
			@Override
			public Adapter caseDObject(DObject object) {
				return createDObjectAdapter();
			}
			@Override
			public Adapter caseGenericCategory(GenericCategory object) {
				return createGenericCategoryAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty <em>Test Category All Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty
	 * @generated
	 */
	public Adapter createTestCategoryAllPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition <em>Test Category Composition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition
	 * @generated
	 */
	public Adapter createTestCategoryCompositionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference <em>Test Category Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference
	 * @generated
	 */
	public Adapter createTestCategoryReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray <em>Test Category Intrinsic Array</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray
	 * @generated
	 */
	public Adapter createTestCategoryIntrinsicArrayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray <em>Test Category Composition Array</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray
	 * @generated
	 */
	public Adapter createTestCategoryCompositionArrayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray <em>Test Category Reference Array</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray
	 * @generated
	 */
	public Adapter createTestCategoryReferenceArrayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanA <em>Test Category Bean A</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanA
	 * @generated
	 */
	public Adapter createTestCategoryBeanAAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanB <em>Test Category Bean B</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanB
	 * @generated
	 */
	public Adapter createTestCategoryBeanBAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanAbstract <em>Test Category Bean Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanAbstract
	 * @generated
	 */
	public Adapter createTestCategoryBeanAbstractAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanConcrete <em>Test Category Bean Concrete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBeanConcrete
	 * @generated
	 */
	public Adapter createTestCategoryBeanConcreteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase <em>Test Category Base</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase
	 * @generated
	 */
	public Adapter createTestCategoryBaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryExtends <em>Test Category Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryExtends
	 * @generated
	 */
	public Adapter createTestCategoryExtendsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestParameter <em>Test Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestParameter
	 * @generated
	 */
	public Adapter createTestParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters <em>Test Mass Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters
	 * @generated
	 */
	public Adapter createTestMassParametersAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCrossLinkedParametersWithCalculation <em>Test Cross Linked Parameters With Calculation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestCrossLinkedParametersWithCalculation
	 * @generated
	 */
	public Adapter createTestCrossLinkedParametersWithCalculationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest <em>EReference Test</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest
	 * @generated
	 */
	public Adapter createEReferenceTestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IUuid <em>IUuid</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IUuid
	 * @generated
	 */
	public Adapter createIUuidAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IName <em>IName</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IName
	 * @generated
	 */
	public Adapter createINameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.dmf.DObject <em>DObject</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.DObject
	 * @generated
	 */
	public Adapter createDObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.ext.core.core.GenericCategory <em>Generic Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.ext.core.core.GenericCategory
	 * @generated
	 */
	public Adapter createGenericCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TestsAdapterFactory
