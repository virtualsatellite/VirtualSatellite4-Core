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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.graphiti.ui.platform.GraphitiShapeEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.statespace.StateSpaceExplorer;
import de.dlr.sc.virsat.model.extension.statemachines.statespace.TraceState;
import de.dlr.sc.virsat.model.extension.statemachines.ui.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.util.StateMachineHelper;

/**
 * A view for simulating state machines.
 * @author chrs_ph
 */
public class SimulatorView extends ViewPart {
	
	public static final String ID = "de.dlr.sc.virsat.model.extension.statemachines.ui.views.simulator";
	
	private static final int TIME_COLUMN_WIDTH = 30;
	private static final int STATE_VIEWER_COLUMN_WIDTH = 200;
	private static final int TRANSITION_VIEWER_COLUMN_WIDTH = 200;
	
	private TableViewer traceViewer;
	private TableViewer transitionViewer;
	
	private Action actionClearTrace;
	private Action actionStepForward;
	private Action actionStepBackward;
	
	private int nextTimepoint;
	private List<TraceState> trace;
	private List<StateSpaceExplorer.SystemTransition> transitions;
	
	private List<StateMachine> input;
	private StateSpaceExplorer explorer;
	
	private SelectionIntermediate selectionIntermediate;
	private ISelectionListener stateMachineSelectionListener;
	private ISelectionListener transitionSelectionListener;
	private boolean isDisposed;
	
	
	/**
	 * The SelectionIntermediate combines the selections of the simulator's viewers. It acts as the SelectionProvider for this view.
	 * @author chrs_ph
	 */
	private class SelectionIntermediate implements ISelectionProvider, ISelectionChangedListener {
		private final List<ISelectionChangedListener> listeners;
		private ISelection selection;
		
		private TraceState selectedTraceState;
		private StateSpaceExplorer.SystemTransition selectedTransition;
		
		SelectionIntermediate() {
			listeners = new ArrayList<>();
			selection = StructuredSelection.EMPTY;
		}
		
		@Override
		public void addSelectionChangedListener(ISelectionChangedListener listener) {
			listeners.add(listener);
		}

		@Override
		public ISelection getSelection() {
			return selection;
		}

		@Override
		public void removeSelectionChangedListener(ISelectionChangedListener listener) {
			listeners.remove(listener);
		}

		@Override
		public void setSelection(ISelection selection) {
			this.selection = selection;
		}

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			if (event.getSelectionProvider() == traceViewer) {
				selectedTraceState = (TraceState) event.getStructuredSelection().getFirstElement();
			} else if (event.getSelectionProvider() == transitionViewer) {
				selectedTransition = (StateSpaceExplorer.SystemTransition) event.getStructuredSelection().getFirstElement();
			}
			
			updateSelection();
			notifyListeners();
		}
		
		private void notifyListeners() {
			var event = new SelectionChangedEvent(this, selection);
			listeners.stream().forEach(listener -> listener.selectionChanged(event));
		}
		
		/**
		 * Builds a new StructuredSelection combining the selections of the viewers.
		 */
		private void updateSelection() {
			var selections = new ArrayList<Object>(2);
			
			if (selectedTraceState != null) {
				selections.add(selectedTraceState);
			}
			
			if (selectedTransition != null) {
				selections.add(selectedTransition);
			}
			
			setSelection(new StructuredSelection(selections));
		}
	}
	
	private static class SystemStateColumnLabelProvider extends ColumnLabelProvider {
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
	
	private static class StateMachineComparator implements Comparator<StateMachine>, Serializable {
		private static final long serialVersionUID = -806482358312370242L;

		@Override
		public int compare(StateMachine o1, StateMachine o2) {
			return o1.getName().compareTo(o2.getName());
		}

	}
	
	private StateMachineComparator comparator = new StateMachineComparator();
	
	public SimulatorView() {
		this.trace = new ArrayList<>();
		this.transitions = new ArrayList<>();
		this.isDisposed = false;
	}

	@Override
	public void createPartControl(final Composite parent) {
		createViewers(parent);
		createActions();
		createActionBar();
		addListeners();
		
		selectionIntermediate = new SelectionIntermediate();
		traceViewer.addSelectionChangedListener(selectionIntermediate);
		transitionViewer.addSelectionChangedListener(selectionIntermediate);
		getSite().setSelectionProvider(selectionIntermediate);
	}
	
	@Override
	public void setFocus() {
		traceViewer.getControl().setFocus();
	}
	
	@Override
	public void dispose() {
		if (isDisposed) {
			return;
		}
		
		isDisposed = true;
		getSite().getPage().removeSelectionListener(stateMachineSelectionListener);
		getSite().getPage().removeSelectionListener(transitionSelectionListener);
		super.dispose();
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
		tableViewer.getTable().setLinesVisible(true);
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
		tableViewer.getTable().setLinesVisible(true);
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
		actionClearTrace = new Action() {
			@Override
			public void run() {
				handleClearTrace();
			}
		};
		actionClearTrace.setText("Clear trace");
		actionClearTrace.setToolTipText("Clear the trace and start a new simulation");
		actionClearTrace.setImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/icons/ClearTrace.png"));
		
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
		toolBarManager.add(actionClearTrace);
		toolBarManager.add(actionStepBackward);
		toolBarManager.add(actionStepForward);
	}
	
	/**
	 * Creates and adds all event listeners.
	 */
	private void addListeners() {
		stateMachineSelectionListener = new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (!(selection instanceof IStructuredSelection)) {
					return;
				}
				
				var structuredSelection = (IStructuredSelection) selection;
				var selectedStateMachines = new HashSet<StateMachine>();
				
				for (var sel : structuredSelection) {
					if (sel instanceof CategoryAssignment) { // selection of a state machine CA
						var ca = (CategoryAssignment) sel;
						if (ca.getType().getFullQualifiedName().equals(StateMachine.FULL_QUALIFIED_CATEGORY_NAME)) {
							selectedStateMachines.add(new StateMachine(ca));
						}
					} else if (sel instanceof StructuralElementInstance) { // selection of an SEI possibly containing nested state machines
						var selectedSei = (StructuralElementInstance) sel;
						var seis = new ArrayList<>(selectedSei.getDeepChildren());
						seis.add(selectedSei);
						
						var helper = new BeanCategoryAssignmentHelper();
						
						seis.stream().forEach(sei -> selectedStateMachines.addAll(helper.getAllBeanCategories(sei, StateMachine.class)));
					} else if (sel instanceof GraphitiShapeEditPart) { // selection of a state machine in a state machine diagram
						var sep = (GraphitiShapeEditPart) sel;
						var bo = sep.getFeatureProvider().getBusinessObjectForPictogramElement(sep.getPictogramElement());
						if (bo instanceof StateMachine && !(bo instanceof State)) {
							selectedStateMachines.add((StateMachine) bo);
						}
					}
				}
				
				if (!selectedStateMachines.isEmpty()) {
					setInput(selectedStateMachines);
				}
			}
		};
		getSite().getPage().addSelectionListener(stateMachineSelectionListener);
		
		transitionSelectionListener = new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (!(selection instanceof IStructuredSelection)) {
					return;
				}
				
				var selectedElement = ((IStructuredSelection) selection).getFirstElement();
				
				if (selectedElement instanceof GraphitiShapeEditPart) {
					var sep = (GraphitiShapeEditPart) selectedElement;
					var bo = sep.getFeatureProvider().getBusinessObjectForPictogramElement(sep.getPictogramElement());
					if (bo instanceof Transition) {
						var localTransition = (Transition) bo;
						transitions.stream().filter(t -> t.getLocalTransitions().contains(localTransition)).findFirst().ifPresent(t -> {
							transitionViewer.setSelection(new StructuredSelection(t));
						});
					}
				}
			}
		};
		getSite().getPage().addSelectionListener(transitionSelectionListener);
		
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
	 * Set the input for the view.
	 * @param stateMachines
	 */
	private void setInput(Set<StateMachine> stateMachines) {
		if (isDisposed) {
			return;
		}
		
		input = new ArrayList<>(StateMachineHelper.transitiveClosureOverConstraints(stateMachines));
		input.sort(comparator); // make sure the order is stable and reasonable, since the list is created from a set
		
		// if there already is a trace in the view, do not remove it unless explicitly requested by the user
		if (trace.size() > 1) {
			return;
		}
		
		handleClearTrace();
	}
	
	/**
	 * Handles creating a new trace.
	 */
	private void handleClearTrace() {
		resetTrace();
		explorer = new StateSpaceExplorer(input);

		createTraceViewerColumns(input);
		traceViewer.refresh();
		
		var initialStates = explorer.getInitialStates();
		if (initialStates.isEmpty()) {
			actionStepForward.setEnabled(false);
		} else {		
			var initialState = addToTrace(initialStates.get(0));

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
