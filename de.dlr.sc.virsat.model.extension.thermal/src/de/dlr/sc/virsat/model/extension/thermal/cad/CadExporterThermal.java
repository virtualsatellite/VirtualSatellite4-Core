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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisType;
import de.dlr.sc.virsat.model.extension.thermal.model.BoundaryConditions;
import de.dlr.sc.virsat.model.extension.thermal.model.FaceRadiation;
import de.dlr.sc.virsat.model.extension.thermal.model.HeatFlowToFace;
import de.dlr.sc.virsat.model.extension.thermal.model.TemperatureBoundary;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysis;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalContacts;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalData;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalElementParameters;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalInterface;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalPort;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * 
 * Exports thermal data for CAD programs
 *
 */
public class CadExporterThermal {

	private ThermalAnalysis thermalAnalysis;
	private List<IBeanStructuralElementInstance> ecs;

	/**
	 * Initializes the exporter for a thermal anaylsis
	 * 
	 * @param thermalAnalysis the thermal analysis
	 */
	public CadExporterThermal(ThermalAnalysis thermalAnalysis) {
		this.thermalAnalysis = thermalAnalysis;

		// Get the root element and all elements in the tree
		StructuralElementInstance rootSei = (StructuralElementInstance) EcoreUtil
				.getRootContainer(thermalAnalysis.getTypeInstance());
		IBeanStructuralElementInstance rootBean = new BeanStructuralElementInstance(rootSei);
		ecs = rootBean.getDeepChildren(IBeanStructuralElementInstance.class);
	}

	/**
	 * Converts the virsat analysis type to the analysis type for the CAD program
	 * 
	 * @param analysisType the virsat analysis type
	 * @return a string analysis type for the CAD program
	 */
	private String getAnalysisTypeCad(AnalysisType analysisType) {
		String analysisTypeString = analysisType.getAnalysisType();
		if (analysisTypeString == null) {
			throw new RuntimeException("Analysis type is not specified! Please specify analysis type!");
		}

		if (analysisTypeString.equals(AnalysisType.ANALYSISTYPE_Static_NAME)) {
			return "STEADY STATE";
		} else {
			return "";
		}
	}

	/**
	 * Writes all Cad input files for a thermal analysis
	 * @param path a path to a folder into which all export files will be written
	 * @throws IOException
	 */
	public void writeCadInput(String path) throws IOException {
		// Validate the correctness of the model
		for (IBeanStructuralElementInstance ec : ecs) {
			ThermalData td = ec.getFirst(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);
			if (td != null && visShape == null) {
				// We have a problem if we have thermal data but no geometry
				throw new RuntimeException("No visualisation element found for " + ec.getName() + "!");
			}
		}

		writeCadMainInput(path);
		writeCadMaterialsInput(path);
		writeCadRaduationInput(path);
		writeCadVolumeHeatFluxInput(path);
		writeCadBoundaryConditionsInput(path);
		writeCadContacsInput(path);
	}

	/**
	 * Creates the main input file for the CAD program
	 * 
	 * @param path the path where to create the main file
	 * @throws IOException
	 */
	public void writeCadMainInput(String path) throws IOException {
		AnalysisType analysisType = thermalAnalysis.getAnalysisType();
		String analysisTypeCad = getAnalysisTypeCad(analysisType);

		// Create the main input file
		File mainFile = new File(path + File.separatorChar + "main.inp");
		mainFile.createNewFile();

		Writer output = new FileWriter(mainFile);

		// Write the geometry files
		for (IBeanStructuralElementInstance ec : ecs) {
			ThermalData td = ec.getFirst(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);

			// Attention! The main file must be created without including a
			// cflux file when this conditions is true!
			if ((td != null) && (visShape != null)) {
				String uuid = ec.getUuid().replace("-", "_");
				String name = ec.getName();

				output.write("*INCLUDE, INPUT=" + name + "_" + uuid + ".inp\n");
			}
		}

		// Perform setup
		output.write("\n*PHYSICAL CONSTANTS, ABSOLUTE ZERO = 0, STEFAN BOLTZMANN = 5.669E-8\n");
		output.write("\n*INITIAL CONDITIONS,TYPE=TEMPERATURE\n");
		output.write("Nall,293\n");
		if (analysisTypeCad.equals("")) {
			for (IBeanStructuralElementInstance ec : ecs) {
				ThermalData td = ec.getFirst(ThermalData.class);
				Visualisation visShape = ec.getFirst(Visualisation.class);
				if ((td != null) && (visShape != null)) {
					double initTemp = td.getThermalelementparameters().getInitialTemperature();
					// node set with component name necessary!
					output.write(ec.getName() + "," + initTemp + "\n");
				}
			}
		}

		// Write references to the material and the contacts file
		output.write("\n*INCLUDE, INPUT=Materials.inp\n");
		output.write("\n*INCLUDE, INPUT=add_contact.inp\n");
		output.write("*INCLUDE, INPUT=contact_surfaces.inp\n");

		// Write the simulation type
		if (analysisTypeCad.equals("")) {
			output.write("*TIME POINTS,NAME=T1,GENERATE\n");
			output.write("0," + analysisType.getTotalTimeBean().getValueToBaseUnit() + ","
					+ analysisType.getTimeStepBean().getValueToBaseUnit() + "\n");
			output.write("\n*STEP\n");
			output.write("\n*HEAT TRANSFER, DIRECT," + analysisTypeCad + "\n");
			output.write(analysisType.getTimeStep() + "," + analysisType.getTotalTime() + "\n");
		} else {
			output.write("\n*STEP\n");
			output.write("\n*HEAT TRANSFER, " + analysisTypeCad + "\n");
		}

		// Write face and body Heat Flux
		for (IBeanStructuralElementInstance ec : ecs) {
			List<ThermalData> td = ec.getAll(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);
			if ((td.size() > 0) && (visShape != null)) {
				String uuid = ec.getUuid().replace("-", "_");
				String name = ec.getName();

				output.write("\n*DFLUX\n");
				output.write("**INCLUDE, INPUT=" + name + "_" + uuid + ".dfl\n");
				output.write("*INCLUDE, INPUT=" + name + "_" + uuid + ".bfl\n");
				output.write("*INCLUDE, INPUT=" + name + "_" + uuid + ".hf\n");
				output.write("*INCLUDE, INPUT=" + name + "_" + uuid + ".bc\n\n");
			}
		}

		// Write radiation
		output.write("\n*RADIATE\n");
		for (IBeanStructuralElementInstance ec : ecs) {
			List<ThermalData> td = ec.getAll(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);
			if ((td.size() > 0) && (visShape != null)) {
				String uuid = ec.getUuid().replace("-", "_");
				String name = ec.getName();

				// write RADIATE here
				output.write("*INCLUDE, INPUT=" + name + "_" + uuid + ".rad\n");
			}
		}

		// Perform final post processing by writing necessary end statements
		output.write("\n*NODE FILE\n");
		output.write("NT\n");
		output.write("*EL File\n");
		output.write("HFL\n");
		output.write("*END STEP");
		output.close();
	}

	/**
	 * Creates the material input file for the CAD program
	 * 
	 * @param path the path where to create the material file
	 * @throws IOException
	 */
	public void writeCadMaterialsInput(String path) throws IOException {
		File materialFile = new File(path + File.separatorChar + "Materials.inp");
		materialFile.createNewFile();

		Writer output = new FileWriter(materialFile);

		for (IBeanStructuralElementInstance ec : ecs) {
			ThermalData td = ec.getFirst(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);

			if ((td != null) && (visShape != null)) {
				String name = ec.getName();
				
				ThermalElementParameters tep = td.getThermalelementparameters();
				// CHECKSTYLE:OFF
				double thermalConductivity = tep.getPredefinedMaterial().getThermalConductivityBean()
						.getValueToBaseUnit() * 1000;
				double heatCapacity = tep.getPredefinedMaterial().getHeatCapacityBean().getValueToBaseUnit() * 1000000;
				double densityBean = tep.getPredefinedMaterial().getDensityBean().getValueToBaseUnit() / 1000000000;
				// CHECKSTYLE:ON

				output.write("\n*MATERIAL, NAME=" + name + "_material\n");
				output.write("*CONDUCTIVITY\n");
				output.write(thermalConductivity + "\n");
				output.write("*SPECIFIC HEAT\n");
				output.write(heatCapacity + "\n");
				output.write("*DENSITY\n");
				output.write(densityBean + "\n");
				output.write("*SOLID SECTION,MATERIAL=" + name + "_material,ELSET=" + name + "\n\n");
			}
		}

		output.close();
	}

	/**
	 * Creates the radiation input file for the CAD program
	 * 
	 * @param path the path where to create the files
	 * @throws IOException
	 */
	public void writeCadRaduationInput(String path) throws IOException {
		for (IBeanStructuralElementInstance ec : ecs) {
			ThermalData td = ec.getFirst(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);
			if ((td != null) && (visShape != null)) {
				String name = ec.getName();
				String uuid = ec.getUuid().replace("-", "_");
				
				File mainFile = new File(path + File.separatorChar + name + "_" + uuid + ".rad");
				mainFile.createNewFile();

				Writer output = new FileWriter(mainFile);
				double emissivity = td.getThermalelementparameters().getPredefinedMaterial().getElementEmissivity();
				output.write("0, " + emissivity + "\n");

				List<FaceRadiation> radiationList = td.getSinglefaceradiationaList();
				for (FaceRadiation faceRadiation : radiationList) {
					long freeCADFaceNumber = faceRadiation.getFreeCADFaceNumber();
					double faceEmissivity = faceRadiation.getFaceEmissivity();
					output.write(freeCADFaceNumber + ", " + faceEmissivity + "\n");
				}
				output.close();
			}
		}
	}

	/**
	 * Creates the volume heat flux input file for the CAD program
	 * 
	 * @param path the path where to create the files
	 * @throws IOException
	 */
	public void writeCadVolumeHeatFluxInput(String path) throws IOException {
		for (IBeanStructuralElementInstance ec : ecs) {
			ThermalData td = ec.getFirst(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);
			if ((td != null) && (visShape != null)) {
				String name = ec.getName();
				String uuid = ec.getUuid().replace("-", "_");
				
				// Attention! The main file must be created without including a cflux file when
				// this conditions is true!
				File loadFile = new File(path + File.separatorChar + name + "_" + uuid + ".bfl");
				loadFile.createNewFile();

				Writer output = new FileWriter(loadFile);
				ThermalElementParameters tep = td.getThermalelementparameters();
				// CHECKSTYLE:OFF
				String powerBalance = Double.toString(tep.getPowerBalance() * 1000000);
				// CHECKSTYLE:ON
				output.write(powerBalance);
				output.close();
			}
		}
	}

	/**
	 * Creates the boundary condition input files for the CAD program
	 * 
	 * @param path the path where to create the files
	 * @throws IOException
	 */
	public void writeCadBoundaryConditionsInput(String path) throws IOException {
		for (IBeanStructuralElementInstance ec : ecs) {
			ThermalData td = ec.getFirst(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);

			if ((td != null) && (visShape != null)) {
				String name = ec.getName();
				String uuid = ec.getUuid().replace("-", "_");

				File ehfFile = new File(path + File.separatorChar + name + "_" + uuid + ".ehf");
				ehfFile.createNewFile();
				Writer ehfOutput = new FileWriter(ehfFile);

				File bcFile = new File(path + File.separatorChar + name + "_" + uuid + ".bcf");
				bcFile.createNewFile();
				Writer bcOutput = new FileWriter(bcFile);

				BoundaryConditions boundaryConditions = thermalAnalysis.getBoundaryConditions();
				for (HeatFlowToFace ehf : boundaryConditions.getHeatflowface()) {
					String ehfParentComponentUuid = ehf.getComponent().getParent().getUuid();
					if (ehfParentComponentUuid.equals(ec.getUuid())) {
						ehfOutput.write(ehf.getFreeCADFaceNumberHF() + ", " + ehf.getHeatFlow() + "\n");
					}
				}

				for (TemperatureBoundary tb : boundaryConditions.getBoundaries()) {
					String tbParentComponentUuid = tb.getComponent().getParent().getUuid();
					if (tbParentComponentUuid.equals(ec.getUuid())) {
						if (tb.getBoundaryType().equals(TemperatureBoundary.BOUNDARYTYPE_Face_NAME)) {
							bcOutput.write(tb.getFreeCADFaceNumberBC() + ", " + tb.getBoundaryTemperature() + "\n");
						} else if (tb.getBoundaryType().equals(TemperatureBoundary.BOUNDARYTYPE_Volume_NAME)) {
							bcOutput.write(tb.getBoundaryTemperature() + "\n");
						}
					}
				}

				ehfOutput.close();
				bcOutput.close();
			}
		}
	}
	
	/**
	 * Creates the contact input files for the CAD program
	 * 
	 * @param path the path where to create the files
	 * @throws IOException
	 */
	public void writeCadContacsInput(String path) throws IOException {
		ThermalContacts tcs = thermalAnalysis.getThermalcontacts();
		
		File addContactFile = new File(path + File.separatorChar + "add_contact.inp");
		addContactFile.createNewFile();
		Writer output = new FileWriter(addContactFile);
		
		File contactValidationMaster = new File(path + File.separatorChar + "validateContactsMaster.txt");
		contactValidationMaster.createNewFile();
		Writer outputMaster = new FileWriter(contactValidationMaster);
		
		File contactValidationSlave = new File(path + File.separatorChar + "validateContactsSlave.txt");
		contactValidationSlave.createNewFile();
		Writer outputSlave = new FileWriter(contactValidationSlave);
	
		List<ThermalInterface> tis = tcs.getThermalinterfacelist();
	
		for (int i = 0; i < tis.size(); ++i) {
			ThermalInterface ti = tis.get(i);
			List<ThermalPort> tp = ti.getContacts();
			
			double tcc = ti.getThermalContactConductivity();
			output.write("\n*SURFACE INTERACTION, NAME=SI" + i + "\n");
			output.write("*SURFACE BEHAVIOR, PRESSURE-OVERCLOSURE=LINEAR\n");
			output.write("1e10\n");
			output.write("\n*GAP CONDUCTANCE\n");
			output.write(tcc + ",,300\n");
			
			IBeanStructuralElementInstance component1 = tp.get(0).getPortComponent().getParent();
			IBeanStructuralElementInstance component2 = tp.get(1).getPortComponent().getParent();
			
			String component1UUID = component1.getUuid().replace('-', '_');
			String component2UUID = component2.getUuid().replace('-', '_');
			
			double maxMeshElementSize0 = ti.getContactMaxMeshElementSize0Bean().getValueToBaseUnit();
			double maxMeshElementSize1 = ti.getContactMaxMeshElementSize1Bean().getValueToBaseUnit();
			
			outputMaster.write(component1.getName() + "_" + component1UUID + "," + maxMeshElementSize0 + "\n");
			outputSlave.write(component2.getName() + "_" + component2UUID + "," + maxMeshElementSize1 + "\n");
		}
		
		output.close();
		outputMaster.close();
		outputSlave.close();

	}
}
