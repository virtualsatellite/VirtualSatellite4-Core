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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisResult;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisType;
import de.dlr.sc.virsat.model.extension.thermal.model.ComponentResult;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysis;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalData;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * 
 * Imports thermal simulation results from CAD programs
 *
 */
public class CadImporterThermal {

	public static final String DEFAULT_SIMULATION_OUTPUT_FILE_NAME = "main.frd";

	private ThermalAnalysis thermalAnalysis;
	private List<IBeanStructuralElementInstance> ecs;

	/**
	 * Initializes the importer for a thermal anaylsis
	 * 
	 * @param thermalAnalysis the thermal analysis
	 */
	public CadImporterThermal(ThermalAnalysis thermalAnalysis) {
		this.thermalAnalysis = thermalAnalysis;

		// Get the root element and all elements in the tree
		StructuralElementInstance rootSei = (StructuralElementInstance) EcoreUtil
				.getRootContainer(thermalAnalysis.getTypeInstance());
		IBeanStructuralElementInstance rootBean = new BeanStructuralElementInstance(rootSei);
		ecs = rootBean.getDeepChildren(IBeanStructuralElementInstance.class);
	}

	/**
	 * Imports the results of a thermal simulation
	 * 
	 * @param path the path to the folder containing the simulation results and the
	 *             previous thermal export data
	 * @throws IOException
	 */
	public AnalysisResult importCadSimulationOutput(String path, String simulationOutputFileName) throws IOException {
		int totalNumberOfNodes = 0;
		int totalNumberOfElements = 0;
		int startingLine = 0;
		int numberOfTimeSteps = 1;
		int[] numberOfNodes = new int[ecs.size()];

		Concept thermalConcept = thermalAnalysis.getConcept();
		List<String> simulationOutput = Files.readAllLines(Paths.get(path, simulationOutputFileName));

		AnalysisResult analysisResult = new AnalysisResult(thermalConcept);
		analysisResult.setName("ThermalAnalyisResults");

		AnalysisType analysisType = thermalAnalysis.getAnalysisType();
		if (analysisType.getAnalysisType().equals(AnalysisType.ANALYSISTYPE_Transient_NAME)) {
			numberOfTimeSteps = (int) (analysisType.getTotalTimeBean().getValueToBaseUnit()
					/ analysisType.getTimeStepBean().getValueToBaseUnit());
		} else {
			numberOfTimeSteps = 1;
		}

		for (int j = 0; j < simulationOutput.size(); j++) {
			String[] lineContent = simulationOutput.get(j).trim().split("\\s+");
			if (lineContent[0].equals("-1")) {
				startingLine = j;
				break;
			}
		}

		int j = 0;
		for (IBeanStructuralElementInstance ec : ecs) {
			ThermalData td = ec.getFirst(ThermalData.class);
			Visualisation visShape = ec.getFirst(Visualisation.class);
			if ((td != null) && (visShape != null)) {
				String name = ec.getName();
				String uuid = ec.getUuid().replace("-", "_");

				Path componentDataPath = Paths.get(path, name + "_" + uuid + ".inp");
				List<String> componentData = Files.readAllLines(componentDataPath);
				final int LINE_NODE_COUNT = 4;
				String[] line = componentData.get(LINE_NODE_COUNT).split("\\s+");

				ComponentResult cr = new ComponentResult(thermalConcept);
				cr.setName(name);
				cr.setMaxTemperature(0.0);
				cr.setMinTemperature(0.0);
				analysisResult.getComponentresult().add(cr);

				String[] entry = line[0].replace("**", "").split(",");
				numberOfNodes[j] = Integer.valueOf(entry[0]);
				totalNumberOfNodes += numberOfNodes[j];
				totalNumberOfElements += Integer.valueOf(entry[1]);
				j++;
			}
		}

		double[] nodeTemperatures = new double[totalNumberOfNodes * numberOfTimeSteps];

		for (int i = 0; i < numberOfTimeSteps; i++) {
			final int OFFSET = 7;
			final int DATA_PER_TIMESTEP = 13;
			// Two lines per component
			// Per node 2 lines, one for heat flux and one for temperature
			int timeStepStartLine = startingLine + OFFSET + totalNumberOfNodes + 2 * totalNumberOfElements
					+ i * (totalNumberOfNodes + totalNumberOfElements) + DATA_PER_TIMESTEP * i;

			j = 0;
			int k = 0;
			for (IBeanStructuralElementInstance ec2 : ecs) {
				ThermalData td2 = ec2.getFirst(ThermalData.class);
				Visualisation visShape2 = ec2.getFirst(Visualisation.class);
				if ((td2 != null) && (visShape2 != null)) {
					for (int l = 0; l < numberOfNodes[j]; l++) {
						String nodeSimulationOutput = simulationOutput.get(timeStepStartLine + k + l);
						double nodeTemperature = Double.valueOf(nodeSimulationOutput.trim().split("\\s+")[2]);
						nodeTemperatures[i * totalNumberOfNodes + l + k] = nodeTemperature;
					}

					double[] nodeTemperaturesCut = Arrays.copyOfRange(nodeTemperatures, k + i * totalNumberOfNodes,
							k + numberOfNodes[j] + i * totalNumberOfNodes);

					DoubleSummaryStatistics stat = Arrays.stream(nodeTemperaturesCut).summaryStatistics();

					List<ComponentResult> crs = analysisResult.getComponentresult();
					for (ComponentResult compRes : crs) {
						if ((compRes.getName().equals(ec2.getName()) && compRes.getMaxTemperature() < stat.getMax())
								|| i == 0) {
							compRes.setMaxTemperature(stat.getMax());
						} else if ((compRes.getName().equals(ec2.getName())
								&& compRes.getMaxTemperature() > stat.getMin()) || i == 0) {
							compRes.setMaxTemperature(stat.getMin());
						}
					}
					k += numberOfNodes[j];
					j++;
				}
			}
		}

		return analysisResult;
	}
}
