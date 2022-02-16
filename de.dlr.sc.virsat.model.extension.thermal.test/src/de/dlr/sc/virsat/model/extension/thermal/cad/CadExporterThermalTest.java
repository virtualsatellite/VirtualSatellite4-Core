/*******************************************************************************
 * Copyright (c) 2008-2022 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.cad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisType;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysis;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalData;
import de.dlr.sc.virsat.model.extension.thermal.test.TestActivator;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

public class CadExporterThermalTest extends AConceptProjectTestCase {

	private Concept conceptPS;
	private Concept conceptVis;
	private Concept conceptThermal;
	private ElementConfiguration ecThermal;
	private ThermalData thermalData;
	private ThermalAnalysis thermalAnalysis;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();

		conceptPS = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId());
		conceptVis = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId());
		conceptThermal = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.thermal.Activator.getPluginId());
	
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		ct.setName("ConfigurationTree");

		ElementConfiguration ecComponent = new ElementConfiguration(conceptPS);
		ecComponent.setName("Component");
		ecComponent.getStructuralElementInstance().setUuid(new VirSatUuid("c7430cd8-5bd2-49b6-bf7d-529cc8627b70"));
		ct.add(ecComponent);
		
		Visualisation visualisation = new Visualisation(conceptVis);
		ecComponent.add(visualisation);
		
		thermalData = new ThermalData(conceptThermal);
		ecComponent.add(thermalData);
		
		ecThermal = new ElementConfiguration(conceptPS);
		ecThermal.setName("Thermal");
		ct.add(ecThermal);

		thermalAnalysis = new ThermalAnalysis(conceptThermal);
		ecThermal.add(thermalAnalysis);
	}
	
	@Test(expected = RuntimeException.class)
	public void testWriteCadMainInputNoAnalysisType() throws IOException {
		Path outputPath = VirSatFileUtils.createAutoDeleteTempDirectory("cadTest");
		String filePath = outputPath.toString() + File.separator + "main.inp";
		
		CadExporterThermal cadExporter = new CadExporterThermal();
		cadExporter.writeCadMainInput(thermalAnalysis, filePath);
	}
	
	@Test
	public void testWriteCadMainInputStatic() throws IOException, CoreException {
		thermalAnalysis.getAnalysisType().setAnalysisType(AnalysisType.ANALYSISTYPE_Static_NAME);
		
		Path outputPath = VirSatFileUtils.createAutoDeleteTempDirectory("cadTest");
		String filePath = outputPath.toString() + File.separator + "main.inp";
		File expectedMainFile = new File(filePath);

		assertFalse("Main input file is not there initially", expectedMainFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal();
		cadExporter.writeCadMainInput(thermalAnalysis, outputPath.toString());

		assertTrue("Main input file is created", expectedMainFile.exists());
		assertEquals("Main input file is correct", Files.readAllLines(Paths.get(filePath)),
				TestActivator.getResourceContentAsString("/resources/static_main.inp"));
	}
	
	@Test
	public void testWriteCadMainInputTransient() throws IOException, CoreException {
		thermalAnalysis.getAnalysisType().setAnalysisType(AnalysisType.ANALYSISTYPE_Transient_NAME);
		thermalAnalysis.getAnalysisType().setTotalTime(1);
		thermalAnalysis.getAnalysisType().setTimeStep(1);
		
		Path outputPath = VirSatFileUtils.createAutoDeleteTempDirectory("cadTest");
		String filePath = outputPath.toString() + File.separator + "main.inp";
		File expectedMainFile = new File(filePath);

		assertFalse("Main input file is not there initially", expectedMainFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal();
		cadExporter.writeCadMainInput(thermalAnalysis, outputPath.toString());

		assertTrue("Main input file is created", expectedMainFile.exists());
		assertEquals("Main input file is correct", Files.readAllLines(Paths.get(filePath)),
				TestActivator.getResourceContentAsString("/resources/transient_main.inp"));
	}
}