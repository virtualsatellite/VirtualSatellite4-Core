/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.extension.tests.tests.impl;

import de.dlr.sc.virsat.model.ext.core.core.impl.GenericCategoryImpl;

import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Category Composition Array</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionArrayImpl#getTestCompositionArrayDynamic <em>Test Composition Array Dynamic</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.impl.TestCategoryCompositionArrayImpl#getTestCompositionArrayStatic <em>Test Composition Array Static</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCategoryCompositionArrayImpl extends GenericCategoryImpl implements TestCategoryCompositionArray {
	/**
	 * The cached value of the '{@link #getTestCompositionArrayDynamic() <em>Test Composition Array Dynamic</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestCompositionArrayDynamic()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCategoryAllProperty> testCompositionArrayDynamic;

	/**
	 * The cached value of the '{@link #getTestCompositionArrayStatic() <em>Test Composition Array Static</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestCompositionArrayStatic()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCategoryAllProperty> testCompositionArrayStatic;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCategoryCompositionArrayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsPackage.Literals.TEST_CATEGORY_COMPOSITION_ARRAY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TestCategoryAllProperty> getTestCompositionArrayDynamic() {
		if (testCompositionArrayDynamic == null) {
			testCompositionArrayDynamic = new EObjectContainmentEList<TestCategoryAllProperty>(TestCategoryAllProperty.class, this, TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC);
		}
		return testCompositionArrayDynamic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TestCategoryAllProperty> getTestCompositionArrayStatic() {
		if (testCompositionArrayStatic == null) {
			testCompositionArrayStatic = new EObjectContainmentEList<TestCategoryAllProperty>(TestCategoryAllProperty.class, this, TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC);
		}
		return testCompositionArrayStatic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC:
				return ((InternalEList<?>)getTestCompositionArrayDynamic()).basicRemove(otherEnd, msgs);
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC:
				return ((InternalEList<?>)getTestCompositionArrayStatic()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC:
				return getTestCompositionArrayDynamic();
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC:
				return getTestCompositionArrayStatic();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC:
				getTestCompositionArrayDynamic().clear();
				getTestCompositionArrayDynamic().addAll((Collection<? extends TestCategoryAllProperty>)newValue);
				return;
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC:
				getTestCompositionArrayStatic().clear();
				getTestCompositionArrayStatic().addAll((Collection<? extends TestCategoryAllProperty>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC:
				getTestCompositionArrayDynamic().clear();
				return;
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC:
				getTestCompositionArrayStatic().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_DYNAMIC:
				return testCompositionArrayDynamic != null && !testCompositionArrayDynamic.isEmpty();
			case TestsPackage.TEST_CATEGORY_COMPOSITION_ARRAY__TEST_COMPOSITION_ARRAY_STATIC:
				return testCompositionArrayStatic != null && !testCompositionArrayStatic.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestCategoryCompositionArrayImpl
