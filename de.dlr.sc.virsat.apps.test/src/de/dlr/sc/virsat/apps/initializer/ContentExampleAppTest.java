/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.initializer;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.apps.test.util.GeneratorJunitAssert;

/**
 * tests the content of the manifest generator
 * @author fisc_ph
 *
 */
public class ContentExampleAppTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static final String TEST_SCRIPT_NAME = "de.dlr.virsat.app.app";
	
	@Test
	public void testGetContents() throws IOException {
		CharSequence content = new ContentExampleApp().getContents(TEST_SCRIPT_NAME);
		GeneratorJunitAssert.assertEqualContent(content, "/resources/expectedGeneratorContent/AppExample.java");
	}
}
