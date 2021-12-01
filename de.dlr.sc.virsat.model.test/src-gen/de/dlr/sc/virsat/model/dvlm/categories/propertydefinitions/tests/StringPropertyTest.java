/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.tests;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>String Property</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StringPropertyTest extends APropertyTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StringPropertyTest.class);
	}

	/**
	 * Constructs a new String Property test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringPropertyTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this String Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected StringProperty getFixture() {
		return (StringProperty)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(PropertydefinitionsFactory.eINSTANCE.createStringProperty());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //StringPropertyTest