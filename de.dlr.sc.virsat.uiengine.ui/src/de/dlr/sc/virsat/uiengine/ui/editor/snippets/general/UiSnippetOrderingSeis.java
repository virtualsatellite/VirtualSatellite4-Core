/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets.general;

import java.util.List;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * This class provides the UI Snippet to manipulate the Inheritance of Structural Elements
 * @author fisc_ph
 *
 */
public class UiSnippetOrderingSeis extends AUiSnippetEStructuralFeatureTable implements IUiSnippet {

	private static final String SECTION_NAME = "Structural Element Instance and Category Assignment Ordering";
	private static final String SECTION_DESCRIPTION = "Use these Tables to change the order of Structural Element Instances on the next level and Category Assignemnts of this Structural Element Instance.";

	private Button buttonMoveUpSei;
	private Button buttonMoveDownSei;
	private Button buttonMoveUpCa;
	private Button buttonMoveDownCa;
	private TableViewer tableViewerCa;
	private Table tableCa;
	private EStructuralFeature eStructuralfeatureCa;

	private static final String BUTTON_MOVEUP_TEXT = "Move Up";
	private static final String BUTTON_MOVEDOWN_TEXT = "Move Down";
	private static final String COLUMN_TEXT_SEI_NAME = "Structural Element Instance Name";
	private static final String COLUMN_TEXT_SE_NAME = "Structural Element Name";
	private static final String COLUMN_TEXT_CA_NAME = "Category Assignment Name";
	private static final String COLUMN_TEXT_CATEGORY_NAME = "Category Name";
	
	/**
	 * Constructor for this class to instantiate a UI Snippet
	 */
	public UiSnippetOrderingSeis() {
		super(StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN);
		eStructuralfeatureCa = CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS;
	}

	
	
	@Override
	public Composite createSectionBody(FormToolkit toolkit, String sectionHeading, String sectionDescription, int numberColumns) {
		return super.createSectionBody(toolkit, sectionHeading, sectionDescription, numberColumns + 1);
	}
	
	@Override
	protected Table createDefaultTable(FormToolkit toolkit, Composite sectionBody) {
		Table tableSei = super.createDefaultTable(toolkit, sectionBody);
		
		GridData gridDataTable = createDefaultGridData();
		gridDataTable.horizontalSpan = 1;
		gridDataTable.minimumHeight = DEFAULT_TABLE_SIZE;
		gridDataTable.heightHint = DEFAULT_TABLE_SIZE;

		tableCa = toolkit.createTable(sectionBody, SWT.MULTI | SWT.FULL_SELECTION);
		tableCa.setLayoutData(gridDataTable);
		tableCa.setHeaderVisible(true);
		
		return tableSei;
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);

		// Placing the table
		tableCa.setLinesVisible(true);
		setUpTableViewerCas(editingDomain, tableCa);
	}

	@Override
	protected void createDropTarget(EditingDomain editingDomain, Table table) {
		// Don't create a drop target for this UiWidget Snippet, ehnce don'T call the super
	}
	/**
	 * Standard method to set up the tableViewer
	 * @param editingDomain The EditingDomain to get the Features from
	 * @param table The Table which should be set in the TableViewer
	 */
	@SuppressWarnings("rawtypes")
	protected void setUpTableViewerCas(EditingDomain editingDomain, Table table) {
		tableViewerCa = new TableViewer(table);
		ObservableListContentProvider contentprovider = new ObservableListContentProvider();
		tableViewerCa.setContentProvider(contentprovider);
		
		// Column StructuralElementInstance Name
		TableViewerColumn columnSEI = createDefaultColumn(tableViewerCa, COLUMN_TEXT_CA_NAME);
		columnSEI.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, contentprovider, true, GeneralPackage.Literals.INAME__NAME));
				
		// Column StructuralElement Name
		TableViewerColumn columnSE = createDefaultColumn(tableViewerCa, COLUMN_TEXT_CATEGORY_NAME);
		columnSE.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, contentprovider, false, CategoriesPackage.Literals.ATYPE_INSTANCE__TYPE, GeneralPackage.Literals.IQUALIFIED_NAME__NAME));

		if (getTableLabelProvider() != null) {
			tableViewerCa.setLabelProvider(getTableLabelProvider()); 
		}
	
		setTableViewerInput(editingDomain);
	}
	
	@Override
	protected String getSectionHeading() {
		return super.getSectionHeading() + SECTION_NAME;
	}
	
	@Override
	protected String getSectionDescription() {
		return SECTION_DESCRIPTION;
	}

	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		// Column StructuralElementInstance Name
		TableViewerColumn columnSEI = createDefaultColumn(tableViewer, COLUMN_TEXT_SEI_NAME);
		columnSEI.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, true, GeneralPackage.Literals.INAME__NAME));
				
		// Column StructuralElement Name
		TableViewerColumn columnSE = createDefaultColumn(tableViewer, COLUMN_TEXT_SE_NAME);
		columnSE.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, false, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__TYPE, GeneralPackage.Literals.IQUALIFIED_NAME__NAME));
	}

	@Override
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtonsSei = super.createButtons(toolkit, sectionBody);
	
		buttonMoveUpSei = toolkit.createButton(compositeButtonsSei, BUTTON_MOVEUP_TEXT, SWT.PUSH);
		buttonMoveDownSei = toolkit.createButton(compositeButtonsSei, BUTTON_MOVEDOWN_TEXT, SWT.PUSH);

		Composite compositeButtonsCa = super.createButtons(toolkit, sectionBody);
		
		buttonMoveUpCa = toolkit.createButton(compositeButtonsCa, BUTTON_MOVEUP_TEXT, SWT.PUSH);
		buttonMoveDownCa = toolkit.createButton(compositeButtonsCa, BUTTON_MOVEDOWN_TEXT, SWT.PUSH);

		// Mark the Controls which should be checked for write access
		checkWriteAccess(buttonMoveDownCa, buttonMoveUpCa, buttonMoveDownSei, buttonMoveUpSei);
		
		return compositeButtonsSei;
	}

	@Override
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {
		buttonMoveUpSei.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean seiSelected = selection.get(0) instanceof StructuralElementInstance;
					boolean seiCalled = model instanceof StructuralElementInstance;
					if (seiSelected && seiCalled) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Move Sei Up In Order") {
							@Override
							protected void doExecute() {
								StructuralElementInstance selectedSei = (StructuralElementInstance) selection.get(0);
								StructuralElementInstance openedSei = (StructuralElementInstance) model;
								int oldPositionOfSelectedSei = openedSei.getChildren().indexOf(selectedSei);
								if (oldPositionOfSelectedSei > 0) {
									openedSei.getChildren().move(oldPositionOfSelectedSei - 1, oldPositionOfSelectedSei);
								}
							}
						};
						editingDomain.getCommandStack().execute(cmd);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonMoveDownSei.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean seiSelected = selection.get(0) instanceof StructuralElementInstance;
					boolean seiCalled = model instanceof StructuralElementInstance;
					if (seiSelected && seiCalled) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Move Sei Down In order") {
							@Override
							protected void doExecute() {
								StructuralElementInstance selectedSei = (StructuralElementInstance) selection.get(0);
								StructuralElementInstance openedSei = (StructuralElementInstance) model;
								int oldPositionOfSelectedSei = openedSei.getChildren().indexOf(selectedSei);
								if (oldPositionOfSelectedSei < openedSei.getChildren().size() - 1) {
									openedSei.getChildren().move(oldPositionOfSelectedSei + 1, oldPositionOfSelectedSei);
								}
							}
						};
						editingDomain.getCommandStack().execute(cmd);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonMoveUpCa.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewerCa.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean caSelected = selection.get(0) instanceof CategoryAssignment;
					boolean seiCalled = model instanceof StructuralElementInstance;
					if (caSelected && seiCalled) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Move Ca Up In Order") {
							@Override
							protected void doExecute() {
								CategoryAssignment selectedCA = (CategoryAssignment) selection.get(0);
								StructuralElementInstance openedSei = (StructuralElementInstance) model;
								int oldPositionOfSelectedSei = openedSei.getCategoryAssignments().indexOf(selectedCA);
								if (oldPositionOfSelectedSei > 0) {
									openedSei.getCategoryAssignments().move(oldPositionOfSelectedSei - 1, oldPositionOfSelectedSei);
								}
							}
						};
						editingDomain.getCommandStack().execute(cmd);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonMoveDownCa.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewerCa.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean caSelected = selection.get(0) instanceof CategoryAssignment;
					boolean seiCalled = model instanceof StructuralElementInstance;
					if (caSelected && seiCalled) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Move Ca Down In order") {
							@Override
							protected void doExecute() {
								CategoryAssignment selectedCA = (CategoryAssignment) selection.get(0);
								StructuralElementInstance openedSei = (StructuralElementInstance) model;
								int oldPositionOfSelectedSei = openedSei.getCategoryAssignments().indexOf(selectedCA);
								if (oldPositionOfSelectedSei < openedSei.getCategoryAssignments().size() - 1) {
									openedSei.getCategoryAssignments().move(oldPositionOfSelectedSei + 1, oldPositionOfSelectedSei);
								}
							}
						};
						editingDomain.getCommandStack().execute(cmd);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	@Override
	protected void setTableViewerInput(EditingDomain editingDomain) {
		super.setTableViewerInput(editingDomain);
		if (tableViewerCa != null) {
			@SuppressWarnings("unchecked")
			IListProperty<EObject, ?> property = EMFEditProperties.list(editingDomain, eStructuralfeatureCa);
			tableViewerCa.setInput(property.observe(model));
		}
	}
	
	@Override
	public boolean isActive(EObject model) {
		return model instanceof StructuralElementInstance;
	}
}
