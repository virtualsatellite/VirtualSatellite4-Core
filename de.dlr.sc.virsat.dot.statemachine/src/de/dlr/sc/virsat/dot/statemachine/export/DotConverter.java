/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.dot.statemachine.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import de.dlr.sc.virsat.commons.external.ProcessInteraction;
import de.dlr.sc.virsat.dot.exporter.DotExportHelper;
import de.dlr.sc.virsat.excel.exporter.IExport;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.ForbidsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;

public class DotConverter implements IExport {
	/**
	 * This file is part of the VirSat project.
	 *
	 * Copyright (c) 2008-2015 German Aerospace Center (DLR), Simulation and
	 * Software Technology, Germany All rights reserved
	 *
	 */

	private static final String DEFAULT_TEMPLATE_PATH = "//resources//DotStateMachineExportTemplate.dot";
	private BufferedWriter bw;
	protected DotExportHelper helper;
	private CategoryAssignment exportCa;
	private File file;
	private LinkedHashSet<StateMachine> sm;
	List<AConstraint> constraints;
	HashMap<String, String> interfaceEndPortMap;
	HashSet<String> uniquenodes;
	HashSet<String> constrainthelper;

	/**
	 * Represent state machine as asynchronous system in SMV
	 */
	public DotConverter() {
		constraints = new ArrayList<AConstraint>();
		sm = new LinkedHashSet<StateMachine>();
		helper = new DotExportHelper();
	}

	/**
	 * Export to asynchronous
	 */
	@Override
	public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) {
		
		if (eObject instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) eObject;

			// find the export template
			InputStream iStream = null;
			try {
				if (useDefaultTemplate) {
					iStream = Activator.getResourceContentAsString(DEFAULT_TEMPLATE_PATH);
				} else {
					iStream = new FileInputStream(templatePath);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String newPath = path + "/" + ca.getFullQualifiedInstanceName() + ".dot";
			// and write the results
			file = new File(newPath);
			helper.setFile(file);
			try (FileOutputStream out = new FileOutputStream(file)) {
				bw = new BufferedWriter(new OutputStreamWriter(out));
				exportData(ca);

			} catch (IOException e) {
				Status status = new Status(Status.ERROR, Activator.getPluginId(),
						"Failed to perform an export operation!" + System.lineSeparator() + e.getMessage(), e);
				StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
			}

			try {
				if (iStream != null) {
					iStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Exports the state machine
	 * 
	 * @param ca object to be exported
	 */
	protected void exportData(CategoryAssignment ca) {

		exportCa = ca;
		StateMachine stateMaschine = new StateMachine(exportCa);
		exportStateMachines(stateMaschine);

	}

	/**
	 * @param stateMaschine
	 */
	public void exportStateMachines(StateMachine stateMaschine) {

		sm.add(stateMaschine);
		// create the state sheet
		for (AConstraint constrain : stateMaschine.getConstraints()) {
			constraints.add(constrain);
			StateMachine smi = constrain.getStateInfluenced().getParentCaBeanOfClass((StateMachine.class));
			StateMachine smcons = constrain.getStateConstraining().getParentCaBeanOfClass(StateMachine.class);
		
			if (smcons != null) {
				sm.add(smcons);
			}
			if (smi != null) {
				sm.add(smi);
			}

		}

		try {
			if (sm.size() > 1)  {
				uniquenodes = new HashSet<String>();
				constrainthelper = new HashSet<String>();
			}
			exportStateMachineList(sm);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param sm2
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public String exportStateMachineList(LinkedHashSet<StateMachine> sm2)
			throws FileNotFoundException, IOException, InterruptedException {

		interfaceEndPortMap = new HashMap<String, String>();
		List<Interface> interfaces = new ArrayList<Interface>();
		bw.write("digraph G {\r\n" + "	compound=true splines=true\r\n");
		int counter = 0;
		for (StateMachine currentstateMachine : sm2) {

			bw.write("subgraph cluster_" + counter + "{\r\n"
					+ "node [color=red style=filled]\r\n" + "		color=lightblue style=filled \r\n");
			counter++;
			
			createTransition(currentstateMachine);
			bw.newLine();
			bw.write("label=" + currentstateMachine.getName() + "\r\n");

			ElementConfiguration parent = (ElementConfiguration) currentstateMachine.getParent();
			List<InterfaceEnd> interfaceends = new ArrayList<InterfaceEnd>();
			interfaceends.addAll(parent.getAll(InterfaceEnd.class));
			interfaces.addAll(parent.getAll(Interface.class));
			
			if (!interfaceends.isEmpty()) {
				createInterfaces(interfaceends, currentstateMachine.getName(), counter);
			}
			bw.newLine();
			bw.write("}\r\n");
			
		}

		createConstraints();
		if (!interfaces.isEmpty()) {
			createInterfaceConnections(interfaces);
		}
		bw.write("}\r\n");
		bw.flush();
		bw.close();

		ProcessInteraction pi = new ProcessInteraction();
		String runtime = null;
		try {
			java.net.URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
			File f = new File(u.toURI());
			pi.startCommandRunner("cmd", "/c", "start", "/wait",
					f.getAbsolutePath() + "\\resources\\Graphviz\\bin\\dot.exe", "-Tpng", file.getAbsolutePath(), "-o",
					"graph.png");
			pi.openCommandResult("\\graph.png");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return runtime;

	}

	/**
	 * draws the connections between two s
	 * 
	 * @param interfaces
	 */
	private void createInterfaceConnections(List<Interface> interfaces) {

		for (Interface i : interfaces) {
			String from = i.getInterfaceEndFrom().getName();
			String to = i.getInterfaceEndTo().getName();
			try {
				bw.write(interfaceEndPortMap.get(from) + " -> " + interfaceEndPortMap.get(to) + " [dir=both side =l arrowtail=box arrowhead=box]\r\n");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Writes constraints to the dot file
	 */
	private void createConstraints() {
		try {
			for (AConstraint constraint : constraints) {
				
				String constraining = constraint.getStateConstraining().getName();
				String influencing = constraint.getStateInfluenced().getName();
				String inf = constraint.getStateInfluenced().getParentCaBeanOfClass(StateMachine.class).getName() + influencing;
				String cons = constraint.getStateConstraining().getParentCaBeanOfClass(StateMachine.class).getName() + constraining;
			

				

				if (constrainthelper.contains(cons)) {
					bw.write(cons);
				} else {
					bw.write(constraining);
				}

				bw.write(" -> ");
				if (constrainthelper.contains(inf)) {
					bw.write(inf);
				} else {
					bw.write(influencing);
				}

				if (constraint instanceof ForbidsConstraint) {
					bw.write("[color = red]");
				} else {
					bw.write("[color = green]");
				}
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Includes the interfaceEnds in each subgraph of the statemachines
	 * 
	 * @param interfaceends List of all Interface Ends
	 * @param portName      Name of the port in the dot file
	 * @throws IOException bufferedwriter fails
	 */
	public void createInterfaces(List<InterfaceEnd> interfaceends, String portName, int counter) throws IOException {
		bw.write("InterfaceEnd" + counter + "[label =<\r\n");
		bw.write("<TABLE BORDER=\"1\" CELLSPACING=\"0\" color=\"black\">\r\n");
		bw.write("<TR>\r\n");
		int count = 1;
		for (InterfaceEnd interfaceend : interfaceends) {
			String interfaceName = interfaceend.getName();
			bw.write("<TD PORT=\"" + portName + count + "\">" + interfaceend.getName() + "</TD>\r\n");
			interfaceEndPortMap.put(interfaceName, "InterfaceEnd" + counter + ":" + portName + count);
			count++;
		}
		bw.write("</TR>\r\n </TABLE>> color = white shape =component]");
	}

	@Override
	public boolean canExport(Object selection) {
		if (selection instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) selection;
			if (ca.getType().getName().equals(StateMachine.class.getSimpleName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Creates the data sheet for States and populates it with the data
	 * 
	 * @param inputList
	 * @param statemachine
	 */
	private void createTransition(StateMachine stateMaschine) {
		try {
			for (Transition transition : stateMaschine.getTransitions()) {
				
				String from = transition.getStateFrom().getName();
				String to = transition.getStateTo().getName();
				
				bw.write(from);
				if (uniquenodes.contains(from)) {
					bw.write(stateMaschine.getName());
					constrainthelper.add(from + stateMaschine.getName());
				}
				
				bw.write(" -> "); 
				bw.write(to);
				if (uniquenodes.contains(to)) {
					bw.write(stateMaschine.getName());
					constrainthelper.add(to + stateMaschine.getName());
				}
				
				bw.write("\r\n");
			}
			
			for (State state : stateMaschine.getStates()) {
				uniquenodes.add(state.getName());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
