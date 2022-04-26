/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.test;

import java.util.logging.Level;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Test class wrapping the class from the Jersey Test Framework.
 * Before and After have to be overwritten here for JUnit to work correctly.
 * Also enables verbose debug output.
 */
public class AJerseyTest extends JerseyTest {
	private static final String ALLOW_HEADERS = "sun.net.http.allowRestrictedHeaders";
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		System.setProperty(ALLOW_HEADERS, "true");
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		System.setProperty(ALLOW_HEADERS, "false");
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		set(TestProperties.RECORD_LOG_LEVEL, Level.FINE.intValue());
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
}
