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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisResult;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisType;
import de.dlr.sc.virsat.model.extension.thermal.model.ComponentResult;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysis;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalData;
import de.dlr.sc.virsat.model.extension.thermal.test.TestActivator;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

public class CadImporterThermalTest extends AConceptProjectTestCase {
	
	public static final double EPSILON = 0.00001;

	private Concept conceptPS;
	private Concept conceptVis;
	private Concept conceptThermal;
	private ElementConfiguration ecThermal;
	private ElementConfiguration ecComponent;
	private ThermalData thermalData;
	private ThermalAnalysis thermalAnalysis;
	private Path inputPath;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();

		conceptPS = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId());
		conceptVis = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId());
		conceptThermal = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.thermal.Activator.getPluginId());
	
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		ct.setName("ConfigurationTree");

		ecComponent = new ElementConfiguration(conceptPS);
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
	
	@Before
	public void setupTmpFolder() throws IOException {
		inputPath = VirSatFileUtils.createAutoDeleteTempDirectory("cadThermalTest");
	}
	
	@Test
	public void importCadSimulationOutputStatic() throws IOException {
		thermalAnalysis.getAnalysisType().setAnalysisType(AnalysisType.ANALYSISTYPE_Static_NAME);
		
		// Copy the simulation results from inside the plugin into the input folder
		String simulationOutputPath = inputPath.toString() + File.separator + "main.frd";
		File simulationOutputFile = new File(simulationOutputPath);
		simulationOutputFile.createNewFile();
		try (Writer writer = new FileWriter(simulationOutputFile)) {
			List<String> simulationOutput = TestActivator.getResourceContentAsString("/resources/static_main.frd");
			for (String line : simulationOutput) {
				writer.write(line + "\n");
			}
		}

		// Copy the component .inp from inside the plugin into the input folder
		String componentInpPath = inputPath.toString() + File.separator + "Component_c7430cd8_5bd2_49b6_bf7d_529cc8627b70.inp";
		File componentInpFile = new File(simulationOutputPath);
		componentInpFile.createNewFile();
		try (Writer writer = new FileWriter(componentInpPath)) {
			List<String> componentInput = TestActivator.getResourceContentAsString("/resources/static_Component_c7430cd8_5bd2_49b6_bf7d_529cc8627b70.inp");
			for (String line : componentInput) {
				writer.write(line + "\n");
			}
		}
		
		CadImporterThermal cadImporter = new CadImporterThermal(thermalAnalysis);
		AnalysisResult analysisResult = cadImporter.importCadSimulationOutput(inputPath.toString(), CadImporterThermal.DEFAULT_SIMULATION_OUTPUT_FILE_NAME);

		assertEquals("Number of component analysis results is correct", 1, analysisResult.getComponentresult().size());
		ComponentResult componentResult = analysisResult.getComponentresult().get(0);
		assertEquals("Minimum temperature is correct", 0.0, componentResult.getMinTemperature(), EPSILON);
		final double EXPECTED_MAX_TEMPERATURE = 258.301;
		assertEquals("Maximum temperature is correct", EXPECTED_MAX_TEMPERATURE, componentResult.getMaxTemperature(), EPSILON);
	}

	@Test
	public void importCadSimulationOutputTransient() throws IOException {
		thermalAnalysis.getAnalysisType().setAnalysisType(AnalysisType.ANALYSISTYPE_Transient_NAME);
		thermalAnalysis.getAnalysisType().setTotalTime(2);
		thermalAnalysis.getAnalysisType().setTimeStep(1);
		
		// Copy the simulation results from inside the plugin into the input folder
		String simulationOutputPath = inputPath.toString() + File.separator + "main.frd";
		File simulationOutputFile = new File(simulationOutputPath);
		simulationOutputFile.createNewFile();
		try (Writer writer = new FileWriter(simulationOutputFile)) {
			List<String> simulationOutput = TestActivator.getResourceContentAsString("/resources/transient_main.frd");
			for (String line : simulationOutput) {
				writer.write(line + "\n");
			}
		}

		// Copy the component .inp from inside the plugin into the input folder
		String componentInpPath = inputPath.toString() + File.separator + "Component_c7430cd8_5bd2_49b6_bf7d_529cc8627b70.inp";
		File componentInpFile = new File(simulationOutputPath);
		componentInpFile.createNewFile();
		try (Writer writer = new FileWriter(componentInpPath)) {
			List<String> componentInput = TestActivator.getResourceContentAsString("/resources/transient_Component_c7430cd8_5bd2_49b6_bf7d_529cc8627b70.inp");
			for (String line : componentInput) {
				writer.write(line + "\n");
			}
		}
		
		CadImporterThermal cadImporter = new CadImporterThermal(thermalAnalysis);
		AnalysisResult analysisResult = cadImporter.importCadSimulationOutput(inputPath.toString(), CadImporterThermal.DEFAULT_SIMULATION_OUTPUT_FILE_NAME);

		assertEquals("Number of component analysis results is correct", 1, analysisResult.getComponentresult().size());
		ComponentResult componentResult = analysisResult.getComponentresult().get(0);
		assertEquals("Minimum temperature is correct", 0.0, componentResult.getMinTemperature(), EPSILON);
		final double EXPECTED_MAX_TEMPERATURE = 289.436;
		assertEquals("Maximum temperature is correct", EXPECTED_MAX_TEMPERATURE, componentResult.getMaxTemperature(), EPSILON);
	}
	
}
