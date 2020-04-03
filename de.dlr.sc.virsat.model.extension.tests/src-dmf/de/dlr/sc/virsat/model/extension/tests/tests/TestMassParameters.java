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
 * A representation of the model object '<em><b>Test Mass Parameters</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters#getMass <em>Mass</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestMassParameters()
 * @model
 * @generated
 */
public interface TestMassParameters extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>Mass</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mass</em>' containment reference.
	 * @see #setMass(TestParameter)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestMassParameters_Mass()
	 * @model containment="true"
	 * @generated
	 */
	TestParameter getMass();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestMassParameters#getMass <em>Mass</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mass</em>' containment reference.
	 * @see #getMass()
	 * @generated
	 */
	void setMass(TestParameter value);

} // TestMassParameters
