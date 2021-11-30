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

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>propertydefinitions</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertydefinitionsTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new PropertydefinitionsTests("propertydefinitions Tests");
		suite.addTestSuite(ComposedPropertyTest.class);
		suite.addTestSuite(ReferencePropertyTest.class);
		suite.addTestSuite(EReferencePropertyTest.class);
		suite.addTestSuite(IntPropertyTest.class);
		suite.addTestSuite(FloatPropertyTest.class);
		suite.addTestSuite(StringPropertyTest.class);
		suite.addTestSuite(BooleanPropertyTest.class);
		suite.addTestSuite(EnumPropertyTest.class);
		suite.addTestSuite(ResourcePropertyTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertydefinitionsTests(String name) {
		super(name);
	}

} //PropertydefinitionsTests
