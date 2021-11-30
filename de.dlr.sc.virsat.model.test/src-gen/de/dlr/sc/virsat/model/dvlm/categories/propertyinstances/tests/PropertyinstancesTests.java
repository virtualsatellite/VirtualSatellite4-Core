/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>propertyinstances</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertyinstancesTests extends TestSuite {

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
		TestSuite suite = new PropertyinstancesTests("propertyinstances Tests");
		suite.addTestSuite(ValuePropertyInstanceTest.class);
		suite.addTestSuite(UnitValuePropertyInstanceTest.class);
		suite.addTestSuite(ReferencePropertyInstanceTest.class);
		suite.addTestSuite(EReferencePropertyInstanceTest.class);
		suite.addTestSuite(ComposedPropertyInstanceTest.class);
		suite.addTestSuite(ArrayInstanceTest.class);
		suite.addTestSuite(ResourcePropertyInstanceTest.class);
		suite.addTestSuite(EnumUnitPropertyInstanceTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyinstancesTests(String name) {
		super(name);
	}

} //PropertyinstancesTests
