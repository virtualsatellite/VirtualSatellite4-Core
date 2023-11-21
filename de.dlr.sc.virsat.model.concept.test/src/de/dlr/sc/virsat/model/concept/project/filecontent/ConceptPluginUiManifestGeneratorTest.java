/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.project.filecontent;

import org.junit.After;
import org.junit.Before;

/**
 * Test to check the generator for the build properties of the concept feature
 * @author fisc_ph
 *
 */
public class ConceptPluginUiManifestGeneratorTest extends AFileContentGeneratorTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		fileContentGenerator = new ConceptPluginUiManifestGenerator();
		expectedContentFilePath = "/resources/expectedOutputFilesForGenerators/ConceptPluginUiManifest.MF";
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Override
	String getTestProjectName() {
		return TEST_PROJECT_NAME + ".ui";
	}

}
