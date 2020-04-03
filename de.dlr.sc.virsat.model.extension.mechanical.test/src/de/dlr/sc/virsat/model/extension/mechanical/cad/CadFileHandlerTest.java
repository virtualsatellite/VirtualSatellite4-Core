/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.cad;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * Test class for CadFileWriter
 */

public class CadFileHandlerTest extends AConceptProjectTestCase {

	private Concept conceptPS;
	private Concept conceptVis;

	@Before
	public void setUp() throws CoreException {
		super.setUp();

		conceptPS = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId());
		conceptVis = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId());
	}

	@Test
	public void testWriteFiles() throws IOException, CoreException {
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		ct.setName("ConfigurationTree");

		ElementConfiguration ec = new ElementConfiguration(conceptPS);
		ec.setName("ElementConfiguration");

		ct.add(ec);

		Visualisation visualisation = new Visualisation(conceptVis);
		visualisation.setShape(Visualisation.SHAPE_GEOMETRY_NAME);
		ec.add(visualisation);

		final String STL_FILE_NAME = "dummy.stl";

		String root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		URI stlUri = URI.createPlatformResourceURI("/" + getProjectName() + "/" + STL_FILE_NAME, false);

		Path stlFile = Paths.get(root, stlUri.toPlatformString(true));
		List<String> stlContent = Arrays.asList("solid test", "endsolid test");
		Files.write(stlFile, stlContent);
		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

		visualisation.setGeometryFile(stlUri);

		Path outputPath = VirSatFileUtils.createAutoDeleteTempDirectory("cadTest");
		String jsonFilePath = outputPath.toString() + File.separator + "exported.json";
		File expectedJson = new File(jsonFilePath);
		Path expectedCopiedStl = Paths.get(outputPath.toString(), STL_FILE_NAME);

		assertFalse("JSON file is not there initially", expectedJson.exists());
		assertFalse("STL file is not there initially", expectedCopiedStl.toFile().exists());

		CadFileHandler cadFileWriter = new CadFileHandler();
		cadFileWriter.writeFiles(jsonFilePath, ct, new NullProgressMonitor());

		assertTrue("JSON file is created", expectedJson.exists());
		assertArrayEquals("STL file is copied correctly", Files.readAllBytes(stlFile),
				Files.readAllBytes(expectedCopiedStl));
	}

	@Test
	public void testReadJsonFile() throws IOException, JsonException {
		final String JSON_FILE_NAME = "dummy.json";
		Path outputPath = VirSatFileUtils.createAutoDeleteTempDirectory("cadTest");
		final String TEST_PRODUCT_NAME = "TestProduct";
		Path jsonFilePath = Paths.get(outputPath.toString() + File.separator + JSON_FILE_NAME);

		JsonObject jsonObject = new JsonObject();
		jsonObject.put(CadProperties.NAME.getKey(), TEST_PRODUCT_NAME);

		Files.write(jsonFilePath, Collections.singleton(jsonObject.toJson()));

		CadFileHandler fileHandler = new CadFileHandler();
		JsonObject resultingObject = fileHandler.readJsonFile(jsonFilePath.toString());

		assertNotNull("Parsed object should not be null", resultingObject);
		assertEquals("Name should be as set before", resultingObject.getString(CadProperties.NAME),
				TEST_PRODUCT_NAME);
	}
	
}
