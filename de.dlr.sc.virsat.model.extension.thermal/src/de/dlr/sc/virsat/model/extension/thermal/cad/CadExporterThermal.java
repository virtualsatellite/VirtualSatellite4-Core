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
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysis;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalData;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalElementParameters;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * 
 * Exports thermal data for CAD programs
 *
 */
public class CadExporterThermal {
	
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
	 * Creates the main input file for the CAD program
	 * @param thermalAnalysis the thermal analysis
	 * @param path the path where to create the main file
	 * @throws IOException 
	 */
	public void writeCadMainInput(ThermalAnalysis thermalAnalysis, String path) throws IOException {
		// Get the root element and all elements in the tree
		StructuralElementInstance rootSei = (StructuralElementInstance) EcoreUtil.getRootContainer(thermalAnalysis.getTypeInstance());
		IBeanStructuralElementInstance rootBean = new BeanStructuralElementInstance(rootSei);
		List<IBeanStructuralElementInstance> ecs = rootBean.getDeepChildren(IBeanStructuralElementInstance.class);
		
		AnalysisType analysisType = thermalAnalysis.getAnalysisType();
		String analysisTypeCad = getAnalysisTypeCad(analysisType);

		// Create the main input file
		File mainFile = new File(path + File.separatorChar + "main.inp");
		mainFile.createNewFile();
		
		try (Writer output = new FileWriter(mainFile)) {
	
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
		
		}
	}
	
	/**
	 * Creates the material input file for the CAD program
	 * @param thermalAnalysis the thermal analysis
	 * @param path the path where to create the main file
	 * @throws IOException 
	 */
	public void writeCadMaterialsInput(ThermalAnalysis thermalAnalysis, String path) throws IOException {
		// Get the root element and all elements in the tree
		StructuralElementInstance rootSei = (StructuralElementInstance) EcoreUtil.getRootContainer(thermalAnalysis.getTypeInstance());
		IBeanStructuralElementInstance rootBean = new BeanStructuralElementInstance(rootSei);
		List<IBeanStructuralElementInstance> ecs = rootBean.getDeepChildren(IBeanStructuralElementInstance.class);
		
		File materialFile = new File(path + File.separatorChar + "Materials.inp");
		materialFile.createNewFile();
		
		try (Writer output = new FileWriter(materialFile)) {
		
			for (IBeanStructuralElementInstance ec : ecs) {
				String name = ec.getName();
				ThermalData td = ec.getFirst(ThermalData.class);
				Visualisation visShape = ec.getFirst(Visualisation.class);
				
				if ((td != null) && (visShape != null)) {	
					ThermalElementParameters tep = td.getThermalelementparameters();
					//CHECKSTYLE:OFF
					double thermalConductivity = tep.getPredefinedMaterial().getThermalConductivityBean().getValueToBaseUnit() * 1000;
					double heatCapacity = tep.getPredefinedMaterial().getHeatCapacityBean().getValueToBaseUnit() * 1000000;
					double densityBean = tep.getPredefinedMaterial().getDensityBean().getValueToBaseUnit() / 1000000000;
					//CHECKSTYLE:ON
					
					output.write("\n*MATERIAL, NAME=" + name + "_material\n");
					output.write("*CONDUCTIVITY\n");
					output.write(thermalConductivity + "\n");
					output.write("*SPECIFIC HEAT\n");
					output.write(heatCapacity + "\n");
					output.write("*DENSITY\n");
					output.write(densityBean + "\n");
					output.write("*SOLID SECTION,MATERIAL=" + name + "_material,ELSET=" + name + "\n\n");
				} else if (td != null && visShape == null) {
					throw new RuntimeException("No visualisation element found for " + name + "!");
				}
			}
		}
	}

}
