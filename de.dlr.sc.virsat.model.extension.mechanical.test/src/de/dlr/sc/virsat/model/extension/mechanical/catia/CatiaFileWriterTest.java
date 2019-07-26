/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.catia;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * Test class for CatiaFileWriter
 */

public class CatiaFileWriterTest extends AConceptProjectTestCase {

	private Concept conceptPS;
	private Concept conceptVis;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		conceptPS = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId());
		conceptVis = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId());
	}
	
	@Test
	public void testWriteFiles() throws IOException {
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		ct.setName("ConfigurationTree");

		ElementConfiguration ec = new ElementConfiguration(conceptPS);
		ec.setName("ElementConfiguration");
		
		ct.add(ec);
		
		Visualisation visualisation = new Visualisation(conceptVis);
		visualisation.setShape(Visualisation.SHAPE_GEOMETRY_NAME);

		final String STL_FILE_NAME = "dummy.stl";

		String root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		URI stlUri = URI.createPlatformResourceURI("/" + getProjectName() + "/" + STL_FILE_NAME, false);

		Path stlFile = Paths.get(root, stlUri.toPlatformString(true));
		List<String> stlContent = Arrays.asList("solid test", "endsolid test");
		Files.write(stlFile, stlContent);
		
		visualisation.setGeometryFile(stlUri);

		java.nio.file.Path outputPath = Files.createTempDirectory("catiaTest");
		String jsonFilePath = outputPath.toString() + File.separator + "exported.json";
		File expectedJson = new File(jsonFilePath);
		Path expectedCopiedStl = Paths.get(outputPath.toString(), STL_FILE_NAME);

		assertFalse("JSON file is not there initially", expectedJson.exists());
		assertFalse("STL file is not there initially", expectedCopiedStl.toFile().exists());
		
		CatiaFileWriter catiaFileWriter = new CatiaFileWriter();
		catiaFileWriter.writeFiles(jsonFilePath, ct);
		
		assertTrue("JSON file is created", expectedJson.exists());
		assertArrayEquals("STL file is copied correctly", Files.readAllBytes(stlFile), Files.readAllBytes(expectedCopiedStl));
	}
}
