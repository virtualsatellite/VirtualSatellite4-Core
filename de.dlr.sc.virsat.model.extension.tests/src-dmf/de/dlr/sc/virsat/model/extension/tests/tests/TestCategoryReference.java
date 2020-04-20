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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Category Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference#getTestRefCategory <em>Test Ref Category</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryReference()
 * @model
 * @generated
 */
public interface TestCategoryReference extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>Test Ref Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Ref Category</em>' reference.
	 * @see #setTestRefCategory(TestCategoryAllProperty)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryReference_TestRefCategory()
	 * @model
	 * @generated
	 */
	TestCategoryAllProperty getTestRefCategory();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference#getTestRefCategory <em>Test Ref Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Ref Category</em>' reference.
	 * @see #getTestRefCategory()
	 * @generated
	 */
	void setTestRefCategory(TestCategoryAllProperty value);

} // TestCategoryReference
