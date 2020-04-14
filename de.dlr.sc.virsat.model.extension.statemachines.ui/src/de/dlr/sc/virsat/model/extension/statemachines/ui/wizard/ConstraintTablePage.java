/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.wizard;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralElementInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.ui.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.util.ConstraintTableHelper;


/**
 * Page to show the constraint table
 * @author bell_er
 *
 */

public class ConstraintTablePage extends WizardPage {
	private Composite content;
	private StructuralElementInstance sc;
	private static final int SELECTION_COLUMN_WIDTH = 100;
	private static final int STATE_NAME_COLUMN_WIDTH = 200;

	private static final Color WHITE = new Color(null, 255, 255, 255);
	
	private BeanCategoryAssignmentHelper bcah;
	private StructuralElementInstanceHelper seih;

	private List <StateMachine> tempStateMachines;

	/**
	 * Create a new ConstraintTablePage
	 * @param preSelect the initial selection to be used as a model
	 */
	protected ConstraintTablePage(ISelection preSelect) {
		super("");
		sc = (StructuralElementInstance) ((IStructuredSelection) preSelect).getFirstElement();
		if (sc.getType().getName().equals(ElementConfiguration.class.getSimpleName())) {
			setTitle("All the stateMachines (Beta)");
		}
		setDescription("Change the constraints between the states");
		tempStateMachines = new ArrayList<StateMachine>();
	}

	@Override
	public void createControl(Composite parent) {

		content = new Composite(parent, SWT.FILL);
		FillLayout flContent = new FillLayout();
		content.setLayout(flContent);
		content.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

		setControl(content);
		BeanStructuralElementInstanceFactory bsf = new BeanStructuralElementInstanceFactory();
		ABeanStructuralElementInstance addedElement = null;
		List<StateMachine> stateMachines;
		bcah = new BeanCategoryAssignmentHelper();
		seih = new StructuralElementInstanceHelper(sc);
		try {
			addedElement = (ABeanStructuralElementInstance) bsf.getInstanceFor(sc);
			stateMachines = addedElement.getAll(StateMachine.class);
			for (StateMachine sm : stateMachines) {
				CategoryAssignment tempStateMachineCategoryAssignment = DVLMCopier.makeCopyKeepUuids(sm.getTypeInstance());
				StateMachine tempStateMachine = new StateMachine(tempStateMachineCategoryAssignment);
				tempStateMachines.add(tempStateMachine);
			}
			
			final TabFolder tabFolder = new TabFolder(content, SWT.BORDER | SWT.FILL);
			for (int loopIndex = 0; loopIndex < stateMachines.size(); loopIndex++) {
				TabItem tabItem = new TabItem(tabFolder, SWT.NULL);
				tabItem.setText(stateMachines.get(loopIndex).getName());

				Text text = new Text(tabFolder, SWT.BORDER);
				text.setText("This is page " + loopIndex);
				tabItem.setControl(createTable(stateMachines.get(loopIndex), tabFolder));
			}
		} catch (CoreException e) {
			Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
		}
	}

	/**
	 * @param stateConstraining
	 *            stateConstraining
	 * @param combos
	 *            combos
	 * @param stateInfluenced
	 *            stateInfluenced
	 */

	private void addTableItem(State stateConstraining, TableCombo combos, State stateInfluenced) {
		String stateName = stateInfluenced.getName();
		Table table = combos.getTable();
		TableItem item = new TableItem(table, SWT.FILL);
		Button checkbox = new Button(table, SWT.CHECK);
		if (getConstraint(stateConstraining, stateInfluenced) != null) {
			checkbox.setSelection(true);
		}
		checkbox.setText("allows");

		checkbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button btn = (Button) e.getSource();
				StateMachine sm = stateConstraining.getParentCaBeanOfClass(StateMachine.class);
				int index = 0;
				for (StateMachine temps : tempStateMachines) {
					if (temps.getUuid().equals(sm.getUuid())) {
						index = tempStateMachines.indexOf(temps);
					}
				}
				Concept concept = stateInfluenced.getConcept();
				AllowsConstraint aConstraint = new AllowsConstraint(concept);
				aConstraint.setStateConstraining(stateConstraining);
				aConstraint.setStateInfluenced(stateInfluenced);
				StateMachine stateMachine = tempStateMachines.get(index);
				if (btn.getSelection()) {
					stateMachine.getConstraints().add(aConstraint);
					combos.setText(addString(combos.getText(), stateInfluenced.getName()));
				} else {	
					AConstraint toBeRemoved = null;
					for (AConstraint ac : stateMachine.getConstraints()) {
						if (ConstraintTableHelper.isMatching(aConstraint, ac)) {
							toBeRemoved = ac;
						}
					}
					if (toBeRemoved != null) {
						stateMachine.getConstraints().remove(toBeRemoved);
					}
					combos.setText(removeString(combos.getText(), stateInfluenced.getName()));
				}
			}
		});
		Text text = new Text(table, SWT.FILL);
		text.setText(stateName);
		text.setEditable(false);
		text.setBackground(WHITE);
		TableEditor editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.setEditor(text, item, 1);
		editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.setEditor(checkbox, item, 0);
	}

	/**
	 * @param constratiningState
	 *            constratiningState
	 * @param influencedStateMachine
	 *            influencedStateMachine
	 * @return states
	 */
	private List<State> getAllConstrainedStates(State constratiningState, StateMachine influencedStateMachine) {
		StateMachine sm = constratiningState.getParentCaBeanOfClass(StateMachine.class);
		List<State> states = new ArrayList<State>();
		for (AConstraint c : sm.getConstraints()) {
			State stateConstraining = c.getStateConstraining();
			if (stateConstraining != null && stateConstraining.equals(constratiningState)) {
				StateMachine hsm = c.getStateInfluenced().getParentCaBeanOfClass(StateMachine.class);
				if (hsm.equals(influencedStateMachine)) {
					states.add(c.getStateInfluenced());
				}
			}
		}
		return states;
	}

	/**
	 * display name
	 * 
	 * @param states
	 *            states
	 * @return string
	 */
	private String getDisplay(List<State> states) {
		StringBuilder stringBuilder = new StringBuilder();

		for (State s : states) {
			stringBuilder.append(s.getName());
			stringBuilder.append(", ");
		}
		return stringBuilder.toString();
	}
	
	
	/**
	 * @param initial the string
	 * @param toBeRemoved part to be removed
	 * @return newString
	 */
	private String removeString(String initial, String toBeRemoved) {
		toBeRemoved = toBeRemoved + ",";
		String result = initial.replaceFirst(Pattern.quote(toBeRemoved), "");
		return result;
	}

	/**
	 * @param initial  the string
	 * @param toBeAdded part to be added
	 * @return newString
	 */
	private String addString(String initial, String toBeAdded) {
		toBeAdded = toBeAdded + ",";
		initial = initial + toBeAdded;
		return initial;
	}
	
	/**
	 * @param from
	 *            the constraining state
	 * @param to
	 *            the influenced state
	 * @return AConstraint
	 */
	private AConstraint getConstraint(State from, State to) {
		StateMachine sm = from.getParentCaBeanOfClass(StateMachine.class);
		List<AConstraint> constraints = sm.getConstraints();
		for (AConstraint aConstraint : constraints) {
			State stateConstraining = aConstraint.getStateConstraining();
			State stateInfluenced = aConstraint.getStateInfluenced();
			if (stateConstraining != null && stateInfluenced != null) {
				if (stateConstraining.equals(from) && stateInfluenced.equals(to)) {
					return aConstraint;
				}
			}

		}
		return null;
	}

	/** creates and returns tables for each tab
	 * @param sm the state machine
	 * @param tabFolder the tabfolder for all of the state machines
	 * @return the table
	 */
	private Table createTable(StateMachine sm, TabFolder tabFolder) {
		Table table = new Table(tabFolder, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.FILL);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn column = new TableColumn(table, SWT.CENTER);
		column.setResizable(true);
		column.setText("");
		List<StateMachine> sms = bcah.getAllBeanCategoriesFromRoot(seih.getRoot(),
				StateMachine.class);
		sms.remove(sm);
		for (StateMachine stateMachine : sms) {
			column = new TableColumn(table, SWT.FILL);
			column.setText(stateMachine.getName());
		}
		for (State s : sm.getStates()) {
			TableItem item = new TableItem(table, SWT.FILL);

			TableEditor editor = new TableEditor(table);
			
			Text text = new Text(table, SWT.CENTER);
		
			text.setText(s.getName());
			text.setEditable(false);
			text.setBackground(WHITE);
			editor.grabHorizontal = true;
			editor.setEditor(text, item, 0);
			editor = new TableEditor(table);
			int q = 1;
			TableCombo combos = null;
			for (StateMachine hsm : sms) {
				combos = new TableCombo(table, SWT.CHECK);
				String[] columnNames = new String[] { "Select", "State" };
				int[] columnWidths = new int[] { SELECTION_COLUMN_WIDTH, STATE_NAME_COLUMN_WIDTH };
				combos.defineColumns(columnNames, columnWidths);
				combos.setShowTableHeader(true);
				combos.setShowTableLines(true);
				editor.grabHorizontal = true;
				editor.setEditor(combos, item, q);
				editor = new TableEditor(table);
				for (State state : hsm.getStates()) {
					addTableItem(s, combos, state);
				}
				q++;
				combos.setText(getDisplay(getAllConstrainedStates(s, hsm)));
			}
		}
		for (int i = 0; i < sms.size() + 1; i++) {
			TableColumn tableColumn = table.getColumn(i);
			tableColumn.pack();
			if (tableColumn.getWidth() < SELECTION_COLUMN_WIDTH) {
				tableColumn.setWidth(SELECTION_COLUMN_WIDTH);
			}
		}
		return table;
	}	
	/**
	 * @return the modifiedStateMAchines
	 */
	public List<StateMachine> getStateMachines() {
		return tempStateMachines;
	}
}
