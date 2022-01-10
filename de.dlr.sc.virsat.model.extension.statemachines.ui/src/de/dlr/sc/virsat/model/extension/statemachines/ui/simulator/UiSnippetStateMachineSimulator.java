/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.simulator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.QualifiedName;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.simulator.StateMachineSimulator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.forms.widgets.FormToolkit;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSectionSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * the class ui snippet active concepts implements the interface ui snippet for the active concept part
 * @author leps_je
 *
 */
public class UiSnippetStateMachineSimulator extends AUiSectionSnippet implements IUiSnippet {

	private static final String SECTION_NAME = "StateMachine Simulator";
	private static final String VARIABLE_NAME = "State Machine";
	private static final String SECTION_EXPANSION_STATE_KEY_EXTENSION = ".apps";
	private static final String CHECKBOX_NAME = "Select";
	private TreeViewer treeViewer;
	private static final int DEFAULT_TABLE_WIDTH = 200;
	private static final int DEFAULT_HEIGHT = 156;
	private static final int DEFAULT_WIDTH = 565;
	private static final String BUTTON_EXPLICIT = "Run Simulator";
	private static final String RUNTIME_COLUMN = "Runtime";
	private static final String TRACE_COLUMN = "Explored Traces";
	private Button buttonExplicit;
	Repository repository; 
	
	HashMap<String, Integer> uniqueTriggerEvent;
	
	private Table table;
	private HashMap<String, CategoryAssignment> statemachines;
	
	
	
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		treeViewer.addSelectionChangedListener(listener);
	}

	@Override
	public ISelection getSelection() {
		return treeViewer.getSelection();
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		treeViewer.removeSelectionChangedListener(listener);
		
	}

	@Override
	public void setSelection(ISelection selection) {
		treeViewer.setSelection(selection);
	}

	@Override
	public boolean isActive(EObject model) {
		if (model instanceof Repository) {
			this.repository = (Repository) model;
			return true;
		}
		
		return false;
	}

	@Override
	protected QualifiedName getSectionExpansionStateKey() {
		return new QualifiedName(UI_SECTION_SNIPPET_ID + SECTION_EXPANSION_STATE_KEY_EXTENSION, SECTION_NAME);
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);

		Composite sectionBody = createSectionBody(toolkit, SECTION_NAME, null, 1);
		
		createModelCheckerButtons(toolkit, sectionBody);
		setUpSelection(toolkit, sectionBody);
	}

	/**
	 * Method to set up the the Selection Table
	 * @param toolkit The Toolkit which creates the Composites
	 * @param sectionBody The Composite in which the buttons are created
	 */
	private void setUpSelection(FormToolkit toolkit, Composite sectionBody) {
		uniqueTriggerEvent = new HashMap<String, Integer>();
		
		table = new Table(sectionBody, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
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
	    treeViewer = new TreeViewer(table);
		
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
	 * @param exploreTraces 
	 * @param runtime 
	 * @param deadlocktraces 
	 */
	private void createOutputWindow(String type, float runtime, int exploreTraces, String deadlocktraces) {
		Shell shell = new Shell();
		shell.setText(type);
		shell.setLayout(new GridLayout(2, true));
		final Table outputtable = new Table(shell, SWT.BORDER);
		outputtable.setHeaderVisible(true);
		TableColumn runtimecol = new TableColumn(outputtable, SWT.NONE);
		runtimecol.setText(RUNTIME_COLUMN);
		
		TableColumn tracesColumn = new TableColumn(outputtable, SWT.NONE);
		tracesColumn.setText(TRACE_COLUMN);

		TableItem runtimeitem = new TableItem(outputtable, SWT.NONE);
		runtimeitem.setText(new String[]{ Float.toString(runtime), Integer.toString(exploreTraces)});
		for (int col = 0; col < outputtable.getColumnCount(); col++) {
			outputtable.getColumn(col).pack();
		}
		
		if (deadlocktraces.isEmpty()) {
			Label label = new Label(shell, SWT.BORDER);
	        label.setText("No deadlock state exists");
		} else {
			Label label = new Label(shell, SWT.BORDER);
	        label.setText(deadlocktraces);
		}
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd.heightHint = DEFAULT_HEIGHT;
	    gd.widthHint = DEFAULT_WIDTH;
	    table.setLayoutData(gd);
		shell.pack();
		shell.open();
	}
	/**
	 * Method to set up the buttons below the TreeViewer
	 * @param toolkit The Toolkit which creates the Composites
	 * @param sectionBody The Composite in which the buttons are created
	 */
	private Composite createModelCheckerButtons(FormToolkit toolkit, Composite sectionBody) {
		// Put another composite under the table to add the buttons
		Composite compositeButtons = toolkit.createComposite(sectionBody);
		compositeButtons.setLayoutData(new GridData());

		// Now start adding the buttons
		FillLayout compositeButtonsLayout = new FillLayout(SWT.HORIZONTAL);
		compositeButtons.setLayout(compositeButtonsLayout);

		buttonExplicit = toolkit.createButton(compositeButtons, BUTTON_EXPLICIT, SWT.PUSH);

		addButtonSelectionListeners(sectionBody);
		return compositeButtons;
	}
	
	/**
	 * This method sets up all the listeners for the buttons
	 */
	private void addButtonSelectionListeners(Composite composite) {
		List<StateMachine> sm = new ArrayList<StateMachine>();
		buttonExplicit.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (TableItem item : table.getItems()) {
					if (item.getChecked()) {
						CategoryAssignment ca = statemachines.get(item.getText(1));
						StateMachine stateMaschine = new StateMachine(ca);
						sm.add(stateMaschine);

					}
				}
				StateMachineSimulator simulator = new StateMachineSimulator();
				simulator.exhautiveExploration(sm);

				sm.clear();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
	
}
