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
 * A representation of the model object '<em><b>Test Category Intrinsic Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray#getTestStringArrayDynamic <em>Test String Array Dynamic</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray#getTestStringArrayStatic <em>Test String Array Static</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryIntrinsicArray()
 * @model
 * @generated
 */
public interface TestCategoryIntrinsicArray extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>Test String Array Dynamic</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test String Array Dynamic</em>' attribute list.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryIntrinsicArray_TestStringArrayDynamic()
	 * @model
	 * @generated
	 */
	EList<String> getTestStringArrayDynamic();

	/**
	 * Returns the value of the '<em><b>Test String Array Static</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test String Array Static</em>' attribute list.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryIntrinsicArray_TestStringArrayStatic()
	 * @model upper="4"
	 * @generated
	 */
	EList<String> getTestStringArrayStatic();

} // TestCategoryIntrinsicArray
