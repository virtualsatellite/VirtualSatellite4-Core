/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.views;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.statespace.StateSpaceExplorer;
import de.dlr.sc.virsat.model.extension.statemachines.statespace.TraceState;
import de.dlr.sc.virsat.model.extension.statemachines.ui.Activator;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * A view for simulating state machines.
 * @author chrs_ph
 */
public class SimulatorView extends ViewPart {
	
	private static final int TIME_COLUMN_WIDTH = 30;
	private static final int STATE_VIEWER_COLUMN_WIDTH = 200;
	private static final int TRANSITION_VIEWER_COLUMN_WIDTH = 200;
	
	private TableViewer traceViewer;
	private TableViewer transitionViewer;
	
	private Action actionNewTrace;
	private Action actionStepForward;
	private Action actionStepBackward;
	
	private int nextTimepoint;
	private List<TraceState> trace;
	private List<StateSpaceExplorer.SystemTransition> transitions;
	
	private List<StateMachine> stateMachines;
	private StateSpaceExplorer explorer;
	
	
	private class SystemStateColumnLabelProvider extends ColumnLabelProvider {
		private StateMachine stateMachine;
		
		SystemStateColumnLabelProvider(StateMachine stateMachine) {
			this.stateMachine = stateMachine;
		}
		
		@Override
		public String getText(Object element) {
			var traceState = (TraceState) element;
			return traceState.getSystemState().getLocalStateOf(stateMachine).getName();
		}
	}
	
	
	public SimulatorView() {
		this.trace = new ArrayList<>();
		this.transitions = new ArrayList<>();
	}

	@Override
	public void createPartControl(final Composite parent) {
		createViewers(parent);
		createActions();
		createActionBar();
		addListeners();
	}
	
	@Override
	public void setFocus() {
		traceViewer.getControl().setFocus();
	}
	
	/**
	 * Creates the trace viewer and the transition viewer.
	 */
	private Composite createViewers(Composite parent) {
		var sashForm = new SashForm(parent, SWT.HORIZONTAL);
		
		traceViewer = createTraceViewer(sashForm);
		transitionViewer = createTransitionViewer(sashForm);
		
		return sashForm;
	}
	
	/**
	 * Creates the trace viewer showing the list of explored states.
	 */
	private TableViewer createTraceViewer(Composite parent) {
		var tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(trace);
		
		var timeColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		timeColumn.getColumn().setText("#");
		timeColumn.getColumn().setWidth(TIME_COLUMN_WIDTH);
		timeColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				var traceState = (TraceState) element;
				return Integer.toString(traceState.getTimepoint());
			}
		});

		return tableViewer;
	}
	
	/**
	 * Creates the transition viewer that shows the currently enabled transitions.
	 */
	private TableViewer createTransitionViewer(Composite parent) {
		var tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(transitions);
		
		var columnName = new TableViewerColumn(tableViewer, SWT.NONE);
		columnName.getColumn().setText("Transition");
		columnName.getColumn().setWidth(TRANSITION_VIEWER_COLUMN_WIDTH);
		columnName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				var st = (StateSpaceExplorer.SystemTransition) element;
				return st.getLocalTransitions().stream().map(Transition::getName).collect(joining(", "));
			}
		});
		
		var columnUpdate = new TableViewerColumn(tableViewer, TRANSITION_VIEWER_COLUMN_WIDTH);
		columnUpdate.getColumn().setText("Update");
		columnUpdate.getColumn().setWidth(TRANSITION_VIEWER_COLUMN_WIDTH);
		columnUpdate.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				var st = (StateSpaceExplorer.SystemTransition) element;
				return st.getLocalTransitions().stream().map(t -> {
					var sm = t.getParentCaBeanOfClass(StateMachine.class).getName();
					return sm + ": " + t.getStateTo().getName();
				}).collect(joining(", "));
			}
		});
		
		return tableViewer;
	}
	
	/**
	 * Creates the actions for this view.
	 */
	private void createActions() {
		actionNewTrace = new Action() {
			@Override
			public void run() {
				handleNewTrace();
			}
		};
		actionNewTrace.setText("New trace");
		actionNewTrace.setToolTipText("Start a new trace");
		actionNewTrace.setImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/icons/NewTrace.png"));
		
		actionStepForward = new Action() {
			@Override
			public void run() {
				handleStepForward();
			}
		};
		actionStepForward.setText("Step forward");
		actionStepForward.setToolTipText("Execute the selected transition");
		actionStepForward.setImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/icons/Forward.png"));
		actionStepForward.setEnabled(false);
		
		actionStepBackward = new Action() {
			@Override
			public void run() {
				handleStepBackward();
			}
		};
		actionStepBackward.setText("Step backwards");
		actionStepBackward.setToolTipText("Undo the last transition");
		actionStepBackward.setImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/icons/Backward.png"));
		actionStepBackward.setEnabled(false);
	}
	
	/**
	 * Adds the actions to the view's action bar.
	 */
	private void createActionBar() {
		var toolBarManager = getViewSite().getActionBars().getToolBarManager();
		toolBarManager.add(actionNewTrace);
		toolBarManager.add(actionStepBackward);
		toolBarManager.add(actionStepForward);
	}
	
	/**
	 * Creates and adds all event listeners.
	 */
	private void addListeners() {
		traceViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				var traceState = (TraceState) traceViewer.getStructuredSelection().getFirstElement();
				
				if (traceState == null) {
					transitionViewer.setInput(Collections.emptyList());
				} else {
					transitions = explorer.getSuccessors(traceState.getSystemState());
					transitionViewer.setInput(transitions);
				}
			}
		});
		
		transitionViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				handleStepForward();
			}
		});
		
		transitionViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				actionStepForward.setEnabled(transitionViewer.getStructuredSelection().getFirstElement() != null);
			}
		});
	}
	
	/**
	 * Handles creating a new trace.
	 */
	private void handleNewTrace() {
		stateMachines = getStateMachines();
		explorer = new StateSpaceExplorer(stateMachines);
		
		resetTrace();
		
		var initialStates = explorer.getInitialStates();
		if (initialStates.isEmpty()) {
			MessageDialog.openInformation(traceViewer.getControl().getShell(), "State Machine Simulator", "The system has no valid initial states.");
		} else {		
			var initialState = addToTrace(initialStates.get(0));
			
			createTraceViewerColumns(stateMachines);
			traceViewer.refresh();
			traceViewer.setSelection(new StructuredSelection(initialState));
		}

		actionStepBackward.setEnabled(false);
	}
	
	/**
	 * Handles execution of the currently selected system transition. 
	 */
	private void handleStepForward() {
		var systemTransition = (StateSpaceExplorer.SystemTransition) transitionViewer.getStructuredSelection().getFirstElement();
		
		if (systemTransition == null) {
			return;
		}
		
		var selectedTraceState = (TraceState) traceViewer.getStructuredSelection().getFirstElement();
		var selectedTimepoint = selectedTraceState.getTimepoint();
		
		// if anything but the last state in the trace is selected, partially reset the trace to the currently selected state
		if (selectedTimepoint + 1 < nextTimepoint) {
			// make sure we don't delete anything that the user wants to keep
			if (!MessageDialog.openConfirm(traceViewer.getControl().getShell(), "State Machine Simulator",
					"There are later states in the trace. Proceeding from the currently selected system state removes the subsequent states from the trace.")) {
				return;
			}

			trace.subList(selectedTimepoint + 1, trace.size()).clear();
			nextTimepoint = selectedTimepoint + 1;
		}
		
		var successor = addToTrace(systemTransition.getTo());
		traceViewer.refresh();
		traceViewer.setSelection(new StructuredSelection(successor));
		
		actionStepBackward.setEnabled(trace.size() > 1);
	}
	
	/**
	 * Undo the last executed transition.
	 */
	private void handleStepBackward() {
		if (trace.size() <= 1) {
			return;
		}
		
		nextTimepoint--;
		trace.remove(nextTimepoint);
		traceViewer.refresh();
		traceViewer.setSelection(new StructuredSelection(trace.get(trace.size() - 1)));
		
		actionStepBackward.setEnabled(trace.size() > 1);
	}
	
	/**
	 * Replaces the trace viewer's columns with new columns, where each column corresponds to a state machine.
	 */
	private void createTraceViewerColumns(List<StateMachine> stateMachines) {
		// remove the old columns, except the first one (the time column)
		Arrays.stream(traceViewer.getTable().getColumns()).skip(1).forEach(TableColumn::dispose);
		
		// create a column for each state machine
		stateMachines.stream().forEachOrdered(sm -> {
			var column = new TableViewerColumn(traceViewer, SWT.NONE);
			column.getColumn().setText(sm.getName());
			column.getColumn().setWidth(STATE_VIEWER_COLUMN_WIDTH);
			column.setLabelProvider(new SystemStateColumnLabelProvider(sm));
		});
	}
	
	/**
	 * Returns all state machines that should be simulated.
	 */
	private List<StateMachine> getStateMachines() {
		var workspace = ResourcesPlugin.getWorkspace();
		var projects = VirSatProjectCommons.getAllVirSatProjects(workspace);
		
		if (projects.isEmpty()) {
			return Collections.emptyList();
		}
		
		var repository = VirSatResourceSet.getResourceSet(projects.get(0)).getRepository();
		var stateMachines = new ArrayList<StateMachine>();
		
		EcoreUtil.getAllContents(repository.getRootEntities()).forEachRemaining(o -> {
			if (o instanceof CategoryAssignment) {
				var ca = (CategoryAssignment) o;
				if (ca.getType().getName().equals(StateMachine.class.getSimpleName())) {
					stateMachines.add(new StateMachine(ca));
				}
			}
		});
		
		return stateMachines;
	}

	/**
	 * Empties the current trace.
	 */
	private void resetTrace() {
		trace.clear();
		nextTimepoint = 0;
	}
	
	/**
	 * Adds the given system state to the current trace.
	 */
	private TraceState addToTrace(StateSpaceExplorer.SystemState s) {
		var traceState = new TraceState(nextTimepoint, s);
		
		trace.add(traceState);
		nextTimepoint++;
		
		return traceState;
	}
}
