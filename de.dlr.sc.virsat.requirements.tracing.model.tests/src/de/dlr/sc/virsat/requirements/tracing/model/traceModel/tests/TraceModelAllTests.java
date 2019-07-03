/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.model.traceModel.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	TestTraceElement.class,
	TestTracebilityLinkContainer.class,
	TraceModelEditTests.class
				})

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>TraceModel</b></em>' model.
 * <!-- end-user-doc -->
 * @generated
 */
public class TraceModelAllTests extends TestSuite {
	
	/**
	 * main method
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * Method to get the test suite
	 * @return the test suite
	 */
	public static Test suite() {
		TestSuite suite = new TraceModelAllTests("TraceModel Tests");
		return suite;
	}

	/**
	 * Constructor
	 * @param name the name of the test cases
	 */
	public TraceModelAllTests(String name) {
		super(name);
	}

} //TraceModelAllTests
