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
 * A representation of the model object '<em><b>Test Category Reference Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray#getTestCategoryReferenceArrayDynamic <em>Test Category Reference Array Dynamic</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray#getTestCategoryReferenceArrayStatic <em>Test Category Reference Array Static</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryReferenceArray()
 * @model
 * @generated
 */
public interface TestCategoryReferenceArray extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>Test Category Reference Array Dynamic</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Category Reference Array Dynamic</em>' reference list.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryReferenceArray_TestCategoryReferenceArrayDynamic()
	 * @model
	 * @generated
	 */
	EList<TestCategoryAllProperty> getTestCategoryReferenceArrayDynamic();

	/**
	 * Returns the value of the '<em><b>Test Category Reference Array Static</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Category Reference Array Static</em>' reference list.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryReferenceArray_TestCategoryReferenceArrayStatic()
	 * @model upper="4"
	 * @generated
	 */
	EList<TestCategoryAllProperty> getTestCategoryReferenceArrayStatic();

} // TestCategoryReferenceArray
