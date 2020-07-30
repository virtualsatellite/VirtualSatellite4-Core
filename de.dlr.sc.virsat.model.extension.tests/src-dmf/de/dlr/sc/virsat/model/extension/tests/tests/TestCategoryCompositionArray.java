/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.extension.tests.tests;

import de.dlr.sc.virsat.model.ext.core.core.GenericCategory;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Category Composition Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray#getTestCompositionArrayDynamic <em>Test Composition Array Dynamic</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryCompositionArray#getTestCompositionArrayStatic <em>Test Composition Array Static</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryCompositionArray()
 * @model
 * @generated
 */
public interface TestCategoryCompositionArray extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>Test Composition Array Dynamic</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Composition Array Dynamic</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryCompositionArray_TestCompositionArrayDynamic()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestCategoryAllProperty> getTestCompositionArrayDynamic();

	/**
	 * Returns the value of the '<em><b>Test Composition Array Static</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Composition Array Static</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryCompositionArray_TestCompositionArrayStatic()
	 * @model containment="true" upper="4"
	 * @generated
	 */
	EList<TestCategoryAllProperty> getTestCompositionArrayStatic();

} // TestCategoryCompositionArray
