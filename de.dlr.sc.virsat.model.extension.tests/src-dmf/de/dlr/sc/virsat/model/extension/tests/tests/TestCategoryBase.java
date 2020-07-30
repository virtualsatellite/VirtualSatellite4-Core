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
 * A representation of the model object '<em><b>Test Category Base</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestArray <em>Test Array</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestBaseProperty <em>Test Base Property</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestReference <em>Test Reference</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryBase()
 * @model
 * @generated
 */
public interface TestCategoryBase extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>Test Array</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Array</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryBase_TestArray()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestCategoryBase> getTestArray();

	/**
	 * Returns the value of the '<em><b>Test Base Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Base Property</em>' attribute.
	 * @see #setTestBaseProperty(int)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryBase_TestBaseProperty()
	 * @model
	 * @generated
	 */
	int getTestBaseProperty();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestBaseProperty <em>Test Base Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Base Property</em>' attribute.
	 * @see #getTestBaseProperty()
	 * @generated
	 */
	void setTestBaseProperty(int value);

	/**
	 * Returns the value of the '<em><b>Test Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Reference</em>' reference.
	 * @see #setTestReference(TestCategoryBase)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryBase_TestReference()
	 * @model
	 * @generated
	 */
	TestCategoryBase getTestReference();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryBase#getTestReference <em>Test Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Reference</em>' reference.
	 * @see #getTestReference()
	 * @generated
	 */
	void setTestReference(TestCategoryBase value);

} // TestCategoryBase
