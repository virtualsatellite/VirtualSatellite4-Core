/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.dvlm.tests;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.tests.PropertydefinitionsTests;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.tests.PropertyinstancesTests;

import de.dlr.sc.virsat.model.dvlm.categories.tests.CategoriesTests;

import de.dlr.sc.virsat.model.dvlm.concepts.tests.ConceptsTests;

import de.dlr.sc.virsat.model.dvlm.general.tests.GeneralTests;

import de.dlr.sc.virsat.model.dvlm.roles.tests.RolesTests;

import de.dlr.sc.virsat.model.dvlm.structural.tests.StructuralTests;

import de.dlr.sc.virsat.model.dvlm.units.tests.UnitsTests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>DVLM</b></em>' model.
 * <!-- end-user-doc -->
 * @generated
 */
public class DVLMAllTests extends TestSuite {

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
		TestSuite suite = new DVLMAllTests("DVLM Tests");
		suite.addTest(DVLMTests.suite());
		suite.addTest(StructuralTests.suite());
		suite.addTest(GeneralTests.suite());
		suite.addTest(ConceptsTests.suite());
		suite.addTest(RolesTests.suite());
		suite.addTest(UnitsTests.suite());
		suite.addTest(CategoriesTests.suite());
		suite.addTest(PropertydefinitionsTests.suite());
		suite.addTest(PropertyinstancesTests.suite());
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DVLMAllTests(String name) {
		super(name);
	}

} //DVLMAllTests
