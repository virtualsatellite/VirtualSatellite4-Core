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
import java.time.LocalDateTime;
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
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.ForbidsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;

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
	protected LocalDateTime localDateTime;
	protected DotExportHelper helper;
	private CategoryAssignment exportCa;
	private File file;
	private LinkedHashSet<StateMachine> sm;
	private HashMap<String, String> modules;
	List<AConstraint> constraints;

	/**
	 * Represent state machine as asynchronous system in SMV
	 */
	public DotConverter() {
		this(LocalDateTime.now());
		constraints = new ArrayList<AConstraint>();
	}

	public DotConverter(LocalDateTime now) {
		constraints = new ArrayList<AConstraint>();
	}

	/**
	 * Export to asynchronous
	 */
	@Override
	public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) {
		sm = new LinkedHashSet<StateMachine>();
		helper = new DotExportHelper();
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
		List<AConstraint> smConstraints = stateMaschine.getConstraints();
		constraints.addAll(smConstraints);
		// create the state sheet
		for (AConstraint constrain : smConstraints) {

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
	public String exportStateMachineList(LinkedHashSet<StateMachine> sm2) throws FileNotFoundException, IOException, InterruptedException {
		

		HashSet<String> addedConstraints = new HashSet<String>();
		for (StateMachine stateMachine : sm2) {
			IBeanList<AConstraint> smConstraints = stateMachine.getConstraints();
			for (AConstraint constrain : smConstraints) {

				StateMachine influenced = constrain.getStateInfluenced().getParentCaBeanOfClass((StateMachine.class));

				String input = null;
				input = modules.get(influenced.getName());
				String existing = null;
				if (input != null) {
					existing = input.split("\\)")[0];
				}
				String extraContent = null;
				extraContent = stateMachine.getName();
				String init = null;
				init = new StringBuilder().append("(").append(extraContent).toString();
				String next = null;
				next = new StringBuilder().append(", ").append(extraContent).toString();
				if (addedConstraints.add(influenced.getName() + extraContent)) {
					modules.put(influenced.getName(), existing == null ? init : existing + next);

				}
				String inputstatemachine = null;
				inputstatemachine = modules.get(stateMachine.getName());
				String existingstatemachine = null;
				if (inputstatemachine != null) {
					existingstatemachine = inputstatemachine.split("\\)")[0];
				}
				String initstatemachine = null;
				initstatemachine = new StringBuilder().append("(").append(influenced.getName()).toString();
				String nextstatemachine = null;
				nextstatemachine = new StringBuilder().append(", ").append(influenced.getName()).toString();

				if (addedConstraints.add(stateMachine.getName() + influenced.getName())) {
					modules.put(stateMachine.getName(),
							existingstatemachine == null ? initstatemachine : existingstatemachine + nextstatemachine);
				}

			}
		}
		int counter = 0;
		for (StateMachine currentstateMachine : sm2) {

			String name = currentstateMachine.getName();
			bw.write("digraph G {\r\n" + "	compound=true splines=true\r\n" + "subgraph cluster_" + counter + "{\r\n"
					+ "node [color=red style=filled]\r\n" + "		color=lightblue style=filled");
			bw.newLine();
			createTransition(currentstateMachine);
			bw.newLine();
			bw.write("label=" + name + "\r\n");
			
			
			List<InterfaceEnd> interfaceEnd = null;
			createInterfaces(interfaceEnd);
			bw.newLine();
			bw.write("}\r\n");
			bw.write("}\r\n");
		}

		createConstraints();
		

		bw.flush();
		bw.close();

		ProcessInteraction pi = new ProcessInteraction();
		String runtime = null;
		try {
			java.net.URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
			File f = new File(u.toURI());
			pi.startCommandRunner("cmd", "/c", "start", "/wait", f.getAbsolutePath() + "\\resources\\Graphviz\\bin\\dot.exe", "-Tpng", file.getAbsolutePath(), "-o", "graph.png");
			pi.openCommandResult("\\graph.png");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return runtime;

	}
	
/**
 * Writes constraints to the dot file
 */
	private void createConstraints() {
		try {
			for (AConstraint constraint : constraints) {

				bw.write(constraint.getStateConstraining().getName() + " -> "
						+ constraint.getStateInfluenced().getName());

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

	public void createInterfaces(List<InterfaceEnd> interfaceEnd) {
		
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
			IBeanList<Transition> transitions = stateMaschine.getTransitions();
			
			for (Transition transition : transitions) {
				String to = transition.getStateTo().getName();
				String from = transition.getStateFrom().getName();
				bw.write(to + " -> " + from + "\r\n");
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
