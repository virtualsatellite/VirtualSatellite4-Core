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


import java.io.IOException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.project.IProjectBuilderInfo;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;

/**
 * Common testCase for all FileContent generators that are used by the new project wizard
 * 
 * @author fisc_ph
 *
 */
public abstract class AFileContentGeneratorTest {

	private IProjectBuilderInfo projectBuilderInfo;
	
	@Before
	public void setUp() throws Exception {
		projectBuilderInfo = new IProjectBuilderInfo() {
			@Override
			public String getProjectName() {
				return TEST_PROJECT_NAME;
			}

			@Override
			public Set<String> getNatureIds() {
				return null;
			}

			@Override
			public Set<String> getBuilderIds() {
				return null;
			}

			@Override
			public Set<String> getSourceFolders() {
				return null;
			}

			@Override
			public Set<String> getFolders() {
				return null;
			}

			@Override
			public String getOutputFolder() {
				return null;
			}

			@Override
			public Set<String> getContainers() {
				return null;
			}

			@Override
			public Set<IFileDescription> getFileDesciptors() {
				return null;
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	public static final String TEST_PROJECT_NAME = "de.dlr.sc.virsat.concept.example";
	protected IFileContentGenerator fileContentGenerator;
	protected String expectedContentFilePath;
	
	@Test
	public void testGenerateContent() throws IOException {
		String result = fileContentGenerator.generateContent(projectBuilderInfo);
		GeneratorJunitAssert.assertEqualContent(result, expectedContentFilePath);
	}
}
