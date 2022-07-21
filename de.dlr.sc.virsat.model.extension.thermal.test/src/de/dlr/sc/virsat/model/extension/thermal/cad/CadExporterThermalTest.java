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
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisType;
import de.dlr.sc.virsat.model.extension.thermal.model.FaceRadiation;
import de.dlr.sc.virsat.model.extension.thermal.model.HeatFlowToFace;
import de.dlr.sc.virsat.model.extension.thermal.model.Material;
import de.dlr.sc.virsat.model.extension.thermal.model.TemperatureBoundary;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysis;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalData;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalInterface;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalPort;
import de.dlr.sc.virsat.model.extension.thermal.test.TestActivator;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

public class CadExporterThermalTest extends AConceptProjectTestCase {
	
	private Concept conceptPS;
	private Concept conceptVis;
	private Concept conceptThermal;
	private ElementConfiguration ecThermal;
	private ElementConfiguration ecComponent;
	private ThermalData thermalData;
	private ThermalAnalysis thermalAnalysis;
	private Path outputPath;
	
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
		outputPath = VirSatFileUtils.createAutoDeleteTempDirectory("cadThermalTest");
	}
	
	@Test(expected = RuntimeException.class)
	public void testWriteCadMainInputNoAnalysisType() throws IOException {
		String filePath = outputPath.toString() + File.separator + "main.inp";
		
		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadMainInput(filePath);
	}
	
	@Test
	public void testWriteCadMainInputStatic() throws IOException, CoreException {
		thermalAnalysis.getAnalysisType().setAnalysisType(AnalysisType.ANALYSISTYPE_Static_NAME);
		
		String filePath = outputPath.toString() + File.separator + "main.inp";
		File expectedFile = new File(filePath);

		assertFalse("Main input file is not there initially", expectedFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadMainInput(outputPath.toString());

		assertTrue("Main input file is created", expectedFile.exists());
		assertEquals("Main input file is correct", Files.readAllLines(Paths.get(filePath)),
				TestActivator.getResourceContentAsString("/resources/static_main.inp"));
	}
	
	@Test
	public void testWriteCadMainInputTransient() throws IOException, CoreException {
		thermalAnalysis.getAnalysisType().setAnalysisType(AnalysisType.ANALYSISTYPE_Transient_NAME);
		thermalAnalysis.getAnalysisType().setTotalTime(1);
		thermalAnalysis.getAnalysisType().setTimeStep(1);
		
		String filePath = outputPath.toString() + File.separator + "main.inp";
		File expectedFile = new File(filePath);

		assertFalse("Main input file is not there initially", expectedFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadMainInput(outputPath.toString());

		assertTrue("Main input file is created", expectedFile.exists());
		assertEquals("Main input file is correct", Files.readAllLines(Paths.get(filePath)),
				TestActivator.getResourceContentAsString("/resources/transient_main.inp"));
	}

	@Test
	public void testWriteCadMaterialsInput() throws IOException, CoreException {
		Material material = new Material(conceptThermal);
		material.setThermalConductivity(1);
		material.setHeatCapacity(1);
		material.setDensity(1);
		thermalData.getThermalelementparameters().setPredefinedMaterial(material);
		
		String filePath = outputPath.toString() + File.separator + "Materials.inp";
		File expectedFile = new File(filePath);

		assertFalse("Materials input file is not there initially", expectedFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadMaterialsInput(outputPath.toString());

		assertTrue("Materials input file is created", expectedFile.exists());
		assertEquals("Materials input file is correct", Files.readAllLines(Paths.get(filePath)),
				TestActivator.getResourceContentAsString("/resources/Materials.inp"));
	}
	
	@Test
	public void testWriteCadRadiationInput() throws IOException, CoreException {	
		Material material = new Material(conceptThermal);
		material.setElementEmissivity(1);
		thermalData.getThermalelementparameters().setPredefinedMaterial(material);
		
		FaceRadiation faceRadiation = new FaceRadiation(conceptThermal);
		faceRadiation.setFreeCADFaceNumber(1);
		faceRadiation.setFaceEmissivity(1);
		thermalData.getSinglefaceradiationaList().add(faceRadiation);
		
		String expectedFileName = ecComponent.getName() + "_" + ecComponent.getUuid().replace("-", "_") + ".rad";
		String filePath = outputPath.toString() + File.separator + expectedFileName;
		File expectedFile = new File(filePath);

		assertFalse("Radiation input file is not there initially", expectedFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadRaduationInput(outputPath.toString());

		assertTrue("Radiation input file is created", expectedFile.exists());
		assertEquals("Radiation input file is correct", Files.readAllLines(Paths.get(filePath)),
				TestActivator.getResourceContentAsString("/resources/" + expectedFileName));
	}
	
	@Test
	public void testWriteCadHeatFluxInput() throws IOException, CoreException {	
		thermalData.getThermalelementparameters().setPowerBalance(1);
		
		String expectedFileName = ecComponent.getName() + "_" + ecComponent.getUuid().replace("-", "_") + ".bfl";
		String filePath = outputPath.toString() + File.separator + expectedFileName;
		File expectedFile = new File(filePath);

		assertFalse("Heat Flux input file is not there initially", expectedFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadVolumeHeatFluxInput(outputPath.toString());

		assertTrue("Heat Flux input file is created", expectedFile.exists());
		assertEquals("Heat Flux input file is correct", Files.readAllLines(Paths.get(filePath)),
				TestActivator.getResourceContentAsString("/resources/" + expectedFileName));
	}
	
	@Test
	public void testWriteBoundaryConditionsInput() throws IOException, CoreException {
		TemperatureBoundary faceBoundary = new TemperatureBoundary(conceptThermal);
		faceBoundary.setBoundaryTemperature(1);
		faceBoundary.setFreeCADFaceNumberBC(1);
		faceBoundary.setBoundaryType(TemperatureBoundary.BOUNDARYTYPE_Face_NAME);
		faceBoundary.setComponent(thermalData.getThermalelementparameters());
		thermalAnalysis.getBoundaryConditions().getBoundaries().add(faceBoundary);
		
		TemperatureBoundary volumeBoundary = new TemperatureBoundary(conceptThermal);
		volumeBoundary.setBoundaryTemperature(1);
		volumeBoundary.setBoundaryType(TemperatureBoundary.BOUNDARYTYPE_Volume_NAME);
		volumeBoundary.setComponent(thermalData.getThermalelementparameters());
		thermalAnalysis.getBoundaryConditions().getBoundaries().add(volumeBoundary);
		
		HeatFlowToFace heatFlow = new HeatFlowToFace(conceptThermal);
		heatFlow.setFreeCADFaceNumberHF(1);
		heatFlow.setHeatFlow(1);
		heatFlow.setComponent(thermalData.getThermalelementparameters());
		thermalAnalysis.getBoundaryConditions().getHeatflowface().add(heatFlow);
		
		String expectedBoundaryConditionsFileName = ecComponent.getName() + "_" + ecComponent.getUuid().replace("-", "_") + ".bcf";
		String boundaryConditionsFilePath = outputPath.toString() + File.separator + expectedBoundaryConditionsFileName;
		File expectedBoundaryConditionsFile = new File(boundaryConditionsFilePath);
		
		String expectedHeatFlowFileName = ecComponent.getName() + "_" + ecComponent.getUuid().replace("-", "_") + ".ehf";
		String heatFlowFilePath = outputPath.toString() + File.separator + expectedHeatFlowFileName;
		File expectedHeatFlowFile = new File(heatFlowFilePath);
		
		assertFalse("Boundary conditions input file is not there initially", expectedBoundaryConditionsFile.exists());
		assertFalse("Heat flow input file is not there initially", expectedHeatFlowFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadBoundaryConditionsInput(outputPath.toString());

		assertTrue("Boundary condition input file is created", expectedBoundaryConditionsFile.exists());
		assertTrue("Heat flow input file is created", expectedHeatFlowFile.exists());
		
		assertEquals("Boundary condition input file is correct", Files.readAllLines(Paths.get(boundaryConditionsFilePath)),
				TestActivator.getResourceContentAsString("/resources/" + expectedBoundaryConditionsFileName));
		assertEquals("Heat flow input file is correct", Files.readAllLines(Paths.get(heatFlowFilePath)),
				TestActivator.getResourceContentAsString("/resources/" + expectedHeatFlowFileName));
	}
	
	@Test
	public void testWriteContactsInput() throws IOException, CoreException {
		// Create a dummy thermal interface from a component to itself
		ThermalPort thermalPort1 = new ThermalPort(conceptThermal);
		thermalPort1.setPortComponent(thermalData.getThermalelementparameters());
		thermalAnalysis.getThermalcontacts().getThermalportlist().add(thermalPort1);
		ThermalPort thermalPort2 = new ThermalPort(conceptThermal);
		thermalPort2.setPortComponent(thermalData.getThermalelementparameters());
		thermalAnalysis.getThermalcontacts().getThermalportlist().add(thermalPort2);
		
		ThermalInterface thermalInterface = new ThermalInterface(conceptThermal);
		thermalInterface.setThermalContactConductivity(1);
		List<APropertyInstance> arrayInstances = thermalInterface.getContacts().getArrayInstance().getArrayInstances();
		ReferencePropertyInstance rpi1 = (ReferencePropertyInstance) arrayInstances.get(0);
		ReferencePropertyInstance rpi2 = (ReferencePropertyInstance) arrayInstances.get(1);
		rpi1.setReference(thermalPort1.getTypeInstance());
		rpi2.setReference(thermalPort2.getTypeInstance());
		thermalInterface.setContactMaxMeshElementSize0(1);
		thermalInterface.setContactMaxMeshElementSize1(1);
		thermalAnalysis.getThermalcontacts().getThermalinterfacelist().add(thermalInterface);
		
		String expectedAddContactFileName = "add_contact.inp";
		String addContactFilePath = outputPath.toString() + File.separator + expectedAddContactFileName;
		File addContactConditionsFile = new File(addContactFilePath);
		
		String expectedValidateContactsMasterFileName = "validateContactsMaster.txt";
		String validateContactsMasterFilePath = outputPath.toString() + File.separator + expectedValidateContactsMasterFileName;
		File validateContactsMasterConditionsFile = new File(validateContactsMasterFilePath);
		
		String expectedValidateContactsSlaveFileName = "validateContactsSlave.txt";
		String validateContactsSlaveFilePath = outputPath.toString() + File.separator + expectedValidateContactsSlaveFileName;
		File validateContactsSlaveConditionsFile = new File(validateContactsSlaveFilePath);
		
		assertFalse("Add Contacts input file is not there initially", addContactConditionsFile.exists());
		assertFalse("Validate Contacts Master input file is not there initially", validateContactsMasterConditionsFile.exists());
		assertFalse("Validate Contacts Slave input file is not there initially", validateContactsSlaveConditionsFile.exists());
		
		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadContacsInput(outputPath.toString());

		assertTrue("Add Contacts input file is created", addContactConditionsFile.exists());
		assertTrue("Validate Contacts Master input file is created", validateContactsMasterConditionsFile.exists());
		assertTrue("Validate Contacts Slave input file is created", validateContactsMasterConditionsFile.exists());
		
		assertEquals("Add Contacts input file is correct", Files.readAllLines(Paths.get(addContactFilePath)),
				TestActivator.getResourceContentAsString("/resources/" + expectedAddContactFileName));
		assertEquals("Validate Contacts Master  input file is correct", Files.readAllLines(Paths.get(validateContactsMasterFilePath)),
				TestActivator.getResourceContentAsString("/resources/" + expectedValidateContactsMasterFileName));
		assertEquals("Validate Contacts Slave  input file is correct", Files.readAllLines(Paths.get(validateContactsSlaveFilePath)),
				TestActivator.getResourceContentAsString("/resources/" + expectedValidateContactsSlaveFileName));
	}
	
	@Test
	public void testWriteCadMeshSizesInput() throws IOException, CoreException {	
		thermalData.getThermalelementparameters().setPowerBalance(1);
		
		String expectedFileName = "meshSizes.txt";
		String filePath = outputPath.toString() + File.separator + expectedFileName;
		File expectedFile = new File(filePath);

		assertFalse("Mesh Sizes input file is not there initially", expectedFile.exists());

		CadExporterThermal cadExporter = new CadExporterThermal(thermalAnalysis);
		cadExporter.writeCadMeshSizesInput(outputPath.toString());

		assertTrue("Mesh Sizes input file is created", expectedFile.exists());
		assertEquals("Mesh Sizes input file is correct", Files.readAllLines(Paths.get(filePath)),
				TestActivator.getResourceContentAsString("/resources/" + expectedFileName));
	}
}