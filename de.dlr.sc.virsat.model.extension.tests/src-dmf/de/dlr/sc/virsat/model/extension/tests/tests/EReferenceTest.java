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

import de.dlr.sc.virsat.model.external.tests.ExternalTestType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EReference Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest#getEReferenceTest <em>EReference Test</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getEReferenceTest()
 * @model
 * @generated
 */
public interface EReferenceTest extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>EReference Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference Test</em>' reference.
	 * @see #setEReferenceTest(ExternalTestType)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getEReferenceTest_EReferenceTest()
	 * @model
	 * @generated
	 */
	ExternalTestType getEReferenceTest();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest#getEReferenceTest <em>EReference Test</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference Test</em>' reference.
	 * @see #getEReferenceTest()
	 * @generated
	 */
	void setEReferenceTest(ExternalTestType value);

} // EReferenceTest
