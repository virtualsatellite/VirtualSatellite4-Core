/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.builder.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
/**
 * test class for resource access builder
 * @author bell_Er
 *
 */
public class ResourceAccessBuilderTest extends AConceptProjectTestCase {
	private static final String FRAGMENT_ID = "de.dlr.sc.virsat.model.concept.test";
	private static final String PATH_TO_TEST_FILES = "/resources/expectedOutputFilesForResourceAccessBuilder/";
	
	private static final String PARAMETER_MANIFEST_FILE_PATH = "MANIFEST.MF";
	private static final String TEST_MANIFEST_FILE_PATH = "ManifestMF.java";
	
	private static final String PARAMETER_PLUGIN_XML_FILE_PATH = "plugin.xml";
	private static final String PARAMETER_PLUGIN_UI_XML_FILE_PATH = "pluginUi.xml";
	private static final String TEST_PLUGIN_XML_FILE_PATH = "PluginXml.java";
	private static final String TEST_PLUGIN_UI_XML_FILE_PATH = "PluginUiXml.java";
	
	@Test
	public void testManifestGeneration() throws IOException {
		test(PARAMETER_MANIFEST_FILE_PATH, TEST_MANIFEST_FILE_PATH, true);
	}
	
	@Test
	public void testPluginGeneration() throws IOException, URISyntaxException {
		test(PARAMETER_PLUGIN_XML_FILE_PATH, TEST_PLUGIN_XML_FILE_PATH, false);
	}
	
	@Test
	public void testPluginUiGeneration() throws IOException, URISyntaxException {
		test(PARAMETER_PLUGIN_UI_XML_FILE_PATH, TEST_PLUGIN_UI_XML_FILE_PATH, false);
	}
	
	/**
	 * General test method to be used for both manifest generation and Plugin generation
	 * @param inputFileLocation location of the input file
	 * @param testFileLocation location of the test file
	 * @param isManifest true for generating manifest false for generating plugin.xml
	 * @throws IOException throws it
	 */
	public void test(String inputFileLocation, String testFileLocation, boolean isManifest) throws IOException {
		ResourceAccessBuilder rab = new ResourceAccessBuilder() {
			@Override
			public IProject getTheProject() {
				return testProject;
			}
		};
		
		// grab the plugin.xml or manifest file from resources and generate the java file from it
		URL parameterFileUrl = new URL("platform:/plugin/" + FRAGMENT_ID + PATH_TO_TEST_FILES + inputFileLocation);
		InputStream parameterFileInputStream = parameterFileUrl.openConnection().getInputStream();
		StringInputStream sip;
		if (isManifest) {
			sip = rab.buildManifestAccessClass(parameterFileInputStream);
		} else {
			sip = rab.buildPluginXmlAccessClass(parameterFileInputStream);
		}
		
		String createdFileContent = CharStreams.toString(new InputStreamReader(sip, Charsets.UTF_8));
		GeneratorJunitAssert.assertEqualContent(createdFileContent, PATH_TO_TEST_FILES + testFileLocation);
	}
}
