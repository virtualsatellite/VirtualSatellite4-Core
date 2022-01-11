/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.simulator.GlobalState;
import de.dlr.sc.virsat.model.extension.statemachines.simulator.SimulationTraceExporter;
import de.dlr.sc.virsat.model.extension.statemachines.simulator.StateMachineSimulator;
import de.dlr.sc.virsat.model.extension.statemachines.simulator.Trans;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

/**
 * the class ui snippet active concepts implements the interface ui snippet for the active concept part
 * @author leps_je
 *
 */
public class StateMachineSimulationView extends ViewPart {

	private static final String VARIABLE_NAME = "State Machine";
	private static final String CHECKBOX_NAME = "Select";
	private static final int DEFAULT_TABLE_WIDTH = 200;
	private static final int DEFAULT_HEIGHT = 156;
	private static final int DEFAULT_WIDTH = 565;
	private static final String BUTTON_EXPLICIT = "Run Simulator";
	private static final String CURRENT_STATE_COLUMN = "Current State";
	private static final String NEXT_STATE_COLUMN = "Next State";
	private static final String BUTTON_RELOAD_STATEMACHINES = "Reaload StateMachines";
	
	private static final String EXPORT_REQUEST = "Do you want to export the simulation history ?";
	
	
	private static final int COL = 3;
	private static final int WIDTH = 300;
	private Button buttonExplicit;
	private Button buttonLoadStateMachines;
	Repository repository;
	private StateMachineSimulator simulator;
	private TableViewer viewer;
	
	HashMap<String, Integer> uniqueTriggerEvent;
	
	private Table table;
	private HashMap<String, CategoryAssignment> statemachines;
	private Composite expliciteButtonComposite;
	private Composite swtAwtComposite;
	
	private IProject currentlySelectedProject = null;
	
	private static StateMachineSimulationView simulationViewer = null;
	
	private PriorityQueue<String> simulationhistory;
	
	
	/**
	 * initialize
	 * 
	 * @throws PartInitException
	 *             Exception
	 * @param site
	 *            site
	 */
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		List<IProject> allProjects = VirSatProjectCommons.getAllVirSatProjects(workspace);
		
		if (!allProjects.isEmpty()) {
			if (currentlySelectedProject == null) {
				currentlySelectedProject = allProjects.get(0);
			}
		}
		
		VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(currentlySelectedProject);
		repository = resourceSet.getRepository();
		simulationViewer = this;
	}
	
	/**
	 * return the current viewer
	 * 
	 * @return current viewer
	 */
	public static StateMachineSimulationView getViewer() {
		return simulationViewer;
	}
	
	@Override
	public void setFocus() {
		if (swtAwtComposite != null) {
			swtAwtComposite.setFocus();
		}
	}
	
	
	@Override
	public void createPartControl(final Composite parent) {

		parent.setLayout(new GridLayout(COL, true));
		expliciteButtonComposite = new Composite(parent, SWT.NONE);
		expliciteButtonComposite.setLayout(new GridLayout(2, false));
		expliciteButtonComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false));
			

		this.swtAwtComposite = parent;
	

		createNorthButtonPanel(expliciteButtonComposite);
		createSimulatorPanel(parent);
	}
	
	
	/**
	 * Create buttons and checkboxes
	 */
	private void createNorthButtonPanel(Composite sectionBody) {

		buttonExplicit  = new Button(expliciteButtonComposite, SWT.PUSH);
		buttonExplicit.setText(BUTTON_EXPLICIT);
		buttonLoadStateMachines  = new Button(expliciteButtonComposite, SWT.PUSH);
		buttonLoadStateMachines.setText(BUTTON_RELOAD_STATEMACHINES);
		
		addButtonSelectionListeners(sectionBody);
		
	}
	
	
	/**
	 * 
	 * @param sectionBody
	 */
	public void createSimulatorPanel(Composite sectionBody) {
		
		uniqueTriggerEvent = new HashMap<String, Integer>();
		
		table = new Table(sectionBody, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		GridData gd = new GridData(SWT.BORDER, SWT.BORDER, true, true, 1, 1);
		
	    gd.heightHint = DEFAULT_HEIGHT;
	    gd.widthHint = DEFAULT_WIDTH;
	    table.setLayoutData(gd);
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);

	    TableColumn tblclmnCheckbox = new TableColumn(table, SWT.NONE);
	    tblclmnCheckbox.setWidth(DEFAULT_TABLE_WIDTH);
	    tblclmnCheckbox.setText(CHECKBOX_NAME);

	    TableColumn firstState = new TableColumn(table, SWT.NONE);
	    firstState.setWidth(DEFAULT_TABLE_WIDTH);
	    firstState.setText(VARIABLE_NAME);
		
	    

	    table.addSelectionListener(new SelectionAdapter() {               
	        @Override
	        public void widgetSelected(SelectionEvent event) {
	        	if (event.detail == SWT.CHECK) {
	        		if (table.indexOf((TableItem) event.item) == table.getSelectionIndex()) {
	            		TableItem ti = (TableItem) event.item;
	                    ti.setChecked(!ti.getChecked());

	                }
	        	} 
	        }
	    });
	    
	    loadAllStateMachines();
		
	}

	


	/**
	 * Loads all statemachines of the repository to a selection
	 */
	private void loadAllStateMachines() {
		statemachines = new HashMap<String, CategoryAssignment>();
	    EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				int sequence = 0;
				if (ca.getType().getName().equals("StateMachine")) {
					if (uniqueTriggerEvent.put(ca.getName(), sequence) == null) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(new String[]{"", ca.getName()});
						sequence++;
						statemachines.put(ca.getName(), ca);
					}
					
				}
			}
	    });
	    
	    for (TableItem item :table.getItems()) {
	    	item.setChecked(true);
	    }
		
	}
	
	/**
	 * Manages the output shell for the model checkers
	 * @param composite 
	 * @param exploreTraces 
	 * @param runtime 
	 * @param deadlocktraces 
	 */
	private void createOutputWindow(String type, GlobalState currentState) {

		viewer = new TableViewer(swtAwtComposite, SWT.BORDER | SWT.FULL_SELECTION);
		final Table outputtable = viewer.getTable();
		GridData gd = new GridData(SWT.BORDER, SWT.BORDER, true, true, 1, 1);
	    gd.heightHint = DEFAULT_HEIGHT;
	    gd.widthHint = DEFAULT_WIDTH;
	    outputtable.setLayoutData(gd);
	    
		outputtable.setHeaderVisible(true);
		outputtable.setLinesVisible(true);
		
		TableViewerColumn currentStateColumn = new TableViewerColumn(viewer, SWT.NONE);
		currentStateColumn.getColumn().setText(CURRENT_STATE_COLUMN);
		currentStateColumn.getColumn().pack();
		
		TableViewerColumn nextStateColumn = new TableViewerColumn(viewer, SWT.NONE);
		nextStateColumn.getColumn().setText(NEXT_STATE_COLUMN);
		nextStateColumn.getColumn().pack();
		nextStateColumn.getColumn().setWidth(WIDTH);
		
		createNewSimulationTrace(outputtable, currentState);

	}
	
	/**
	 * 
	 * @param outputtable Table for the simulation result
	 * @param currentState current State of the simulation
	 * @param secondCol 
	 * @param firstCol 
	 */
	private void createNewSimulationTrace(Table outputtable, GlobalState currentState) {
		String noTrans = null;
		TableItem stateitem = new TableItem(outputtable, SWT.NONE);
		if (currentState.getGlobalEnabledTrans().isEmpty()) {
			
			noTrans = "No Transition is executable";
			stateitem.setText(new String[]{currentState.printState(),  noTrans});
			simulationhistory.add(currentState.printState() + "\\t" + noTrans);
		} else {
			stateitem.setText(new String[]{currentState.printState()});
			
			for (Trans trans : currentState.getGlobalEnabledTrans()) {
				final TableEditor transitionsEditors = new TableEditor(table);
				TableItem nextitem = new TableItem(outputtable, SWT.NONE);
				Button transitionButtons = new Button(outputtable, SWT.PUSH);
				transitionButtons.setText(trans.prinTran());
				transitionButtons.computeSize(SWT.DEFAULT, SWT.DEFAULT);
				
				
				transitionButtons.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						transitionsEditors.dispose();
						outputtable.removeAll();
						for (Control control : outputtable.getChildren()) {
							if (control instanceof Button) {
								control.dispose();
							}
						}
						simulationhistory.add(currentState.printState() + " " + trans.prinTran());
						createNewSimulationTrace(outputtable, simulator.nextTransition(currentState, trans));	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						widgetSelected(e);
					}
				});
				transitionsEditors.grabHorizontal = true;
				transitionsEditors.minimumHeight = transitionButtons.getSize().y;
				transitionsEditors.minimumWidth = transitionButtons.getSize().x;
				transitionsEditors.setEditor(transitionButtons, nextitem, 1);
			}
			
		}
		
	}

	
	/**
	 * This method sets up all the listeners for the buttons
	 */
	private void addButtonSelectionListeners(Composite composite) {
		
		buttonExplicit.addSelectionListener(new SelectionListener() {
			

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (viewer != null) {
					
					viewer.getTable().dispose();
					viewer = null;
					
					if (!simulationhistory.isEmpty()) {
						openSimulationHistoryExportShell();
					}
				}
				
				
				List<StateMachine> sm = new ArrayList<StateMachine>();
				for (TableItem item : table.getItems()) {
					if (item.getChecked()) {
						CategoryAssignment ca = statemachines.get(item.getText(1));
						StateMachine stateMaschine = new StateMachine(ca);
						sm.add(stateMaschine);

					}
				}
				simulator = new StateMachineSimulator();
				
				GlobalState gs = simulator.initialSimulationComputation(sm);
				simulationhistory = new PriorityQueue<String>();
				createOutputWindow("Simulation", gs);
			}

			

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		
		buttonLoadStateMachines.addSelectionListener(new SelectionListener() {	

			@Override
			public void widgetSelected(SelectionEvent e) {
				loadAllStateMachines();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
	}
	
	/**
	 * Shell for exporting the simulation trace
	 */
	private void openSimulationHistoryExportShell() {

		MessageBox messageBox = new MessageBox(swtAwtComposite.getShell(), SWT.YES | SWT.NO | SWT.CANCEL);
		
		messageBox.setMessage(EXPORT_REQUEST);
		
		int buttonID = messageBox.open();
		switch (buttonID) {
			case SWT.YES:
				SimulationTraceExporter exporter = new SimulationTraceExporter();
				exporter.export(simulationhistory);
				simulationhistory.clear();
				break;

			case SWT.NO:
				simulationhistory.clear();
				break;
			case SWT.CANCEL:
				break;
			default:
				break;

		}

	}

	
}
