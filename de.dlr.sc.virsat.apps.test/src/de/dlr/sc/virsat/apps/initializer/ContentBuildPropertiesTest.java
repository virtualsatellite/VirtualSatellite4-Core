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
 * Sample test Case to make jenkins maven tycho execute
 * @author fisc_ph
 *
 */
public class ContentBuildPropertiesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetContents() throws IOException {
		CharSequence content = new ContentBuildProperties().getContents();
		GeneratorJunitAssert.assertEqualContent(content, "/resources/expectedGeneratorContent/AppBuildProperties.properties");
	}

}
