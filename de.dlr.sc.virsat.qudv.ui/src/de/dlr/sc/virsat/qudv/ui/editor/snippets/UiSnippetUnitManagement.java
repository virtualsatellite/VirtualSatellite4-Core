/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.qudv.ui.editor.snippets;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsPackage;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.commonSorter.LabelColumnSorter;
import de.dlr.sc.virsat.qudv.ui.wizards.ConversionBasedUnitWizard;
import de.dlr.sc.virsat.qudv.ui.wizards.DerivedUnitWizard;
import de.dlr.sc.virsat.qudv.ui.wizards.PrefixedUnitWizard;
import de.dlr.sc.virsat.qudv.ui.wizards.QudvUnitSetupWizard;
import de.dlr.sc.virsat.qudv.ui.wizards.SimpleUnitWizard;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * class ui snippet unit management implements the interface ui snippet for the unit management
 * @author scha_vo
 *
 */
public class UiSnippetUnitManagement extends AUiSnippetEStructuralFeatureTable implements IUiSnippet {

	public static final String SECTION_NAME = "Unit Management";
	
	public static final String BUTTON_ADD_TEXT = "Add Unit";
	public static final String BUTTON_REMOVE_TEXT = "Remove Unit";
	public static final String BUTTON_EDIT_TEXT = "Edit Unit";
	
	public static final String COLUMN_TEXT_UNIT_NAME = "Unit Name";
	public static final String COLUMN_TEXT_SYMBOL = "Symbol";
	public static final String COLUMN_TEXT_QK = "Quantity Kind";

	private Button buttonAdd;
	private Button buttonRemove;
	private Button buttonEdit;

	private ITableLabelProvider labelProvider;
	private LabelColumnSorter labelColumnSorter;
	
	/**
	 * Constructor for this class to instantiate a UI Snippet
	 */
	public UiSnippetUnitManagement() {
		super(UnitsPackage.Literals.UNIT_MANAGEMENT__SYSTEM_OF_UNIT);
	}

	@Override
	protected String getSectionHeading() {
		return super.getSectionHeading() + SECTION_NAME;
	}

	@Override
	protected IContentProvider getTableContentProvider() {
		//Get filtered content showing all AUnits 
		VirSatFilteredWrappedTreeContentProvider filteredContentProvider = new VirSatFilteredWrappedTreeContentProvider(adapterFactory);
		filteredContentProvider.addClassFilterToGetChildren(AUnit.class);
		filteredContentProvider.addClassFilterToGetElement(AUnit.class);
		
		return filteredContentProvider;
	}
	
	@Override
	protected ITableLabelProvider getTableLabelProvider() {
		labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			@Override
			public String getColumnText(Object object, int columnIndex) {
				super.getColumnText(object, columnIndex);
				if (object instanceof AUnit) {
					AUnit unit = (AUnit) object;
					
					switch (columnIndex) {
						case 0:
							return unit.getName();
						case 1:
							return unit.getSymbol();
						case 2: 
							return unit.getQuantityKind().getName();
						default:
							break;
					}
				}
				return super.getText(object);
			}
		};
		
		labelColumnSorter.setLabelProvider(labelProvider);
		return labelProvider;		
	}
	
	@Override
	protected void createTableViewer(Table table) {
		super.createTableViewer(table);
		
		labelColumnSorter = new LabelColumnSorter(tableViewer);
		tableViewer.setComparator(labelColumnSorter);
	}
	
	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		// Column Unit Name
		TableViewerColumn nameColumn = createDefaultColumn(tableViewer, COLUMN_TEXT_UNIT_NAME);
		labelColumnSorter.onSelectColumn(nameColumn.getColumn(), 0);
		
		// Column Symbol
		createDefaultColumn(tableViewer, COLUMN_TEXT_SYMBOL);
		
		//Column QuantityKind
		createDefaultColumn(tableViewer, COLUMN_TEXT_QK);
	}
	
	@Override
	protected TableViewerColumn createDefaultColumn(TableViewer tableViewer, String columnText) {
		TableViewerColumn column = super.createDefaultColumn(tableViewer, columnText);
		// Columns dont have a get index method so we need to grab the information from the table
		int columnIndex = tableViewer.getTable().getColumnCount() - 1;
		column.getColumn().addSelectionListener(SelectionListener.widgetSelectedAdapter(
			event -> labelColumnSorter.onSelectColumn(column.getColumn(), columnIndex)
		));
		
		return column;
	}
	
	@Override
	protected void setTableViewerInput(EditingDomain editingDomain) {
		VirSatResourceSet virSatResourceSet = VirSatResourceSet.getVirSatResourceSet(model);
		SystemOfUnits systemOfUnits = virSatResourceSet.getUnitManagement().getSystemOfUnit();
		tableViewer.setInput(systemOfUnits);
	}
	
	@Override
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtons = super.createButtons(toolkit, sectionBody);
	
		buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT, SWT.PUSH);
		buttonRemove = toolkit.createButton(compositeButtons, BUTTON_REMOVE_TEXT, SWT.PUSH);
		buttonEdit = toolkit.createButton(compositeButtons, BUTTON_EDIT_TEXT, SWT.PUSH);
		
		// Mark the Controls which should be checked for write access
		checkWriteAccess(buttonAdd, buttonRemove, buttonEdit);
		
		return compositeButtons;
	}

	@Override
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {
		
		buttonAdd.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// some code to create a unit via our QudvWizard 
				
				new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), new QudvUnitSetupWizard((UnitManagement) model)).open();
				// the wizard guides the user through the possible steps to add a unit
				// at the end, on the performFinish() method it executes a cmd over the commandStack which ends the new unit in the proper way.
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		buttonRemove.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					Command cmd = DeleteCommand.create(editingDomain, selection);
					editingDomain.getCommandStack().execute(cmd);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonEdit.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AUnit unit = getFirstSelectedUnit();
				if (unit instanceof PrefixedUnit) {
					new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), new PrefixedUnitWizard((UnitManagement) model, unit)).open();
				} else if (unit instanceof AConversionBasedUnit) {
					new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), new ConversionBasedUnitWizard((UnitManagement) model, unit)).open();
				} else if (unit instanceof DerivedUnit) {
					new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), new DerivedUnitWizard((UnitManagement) model, unit)).open();
				} else {
					new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), new SimpleUnitWizard((UnitManagement) model, unit)).open();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
	
	/**
	 * Returns the first selected Mode in the table
	 * @return null if selection is not a Mode
	 */
	private AUnit getFirstSelectedUnit() {
		IStructuredSelection selection = (IStructuredSelection) getSelection();
		
		// Don't work on an empty selection
		if (selection.isEmpty()) {
			return null;
		}
		
		Object obj = selection.toList().get(0);
		if (obj instanceof AUnit) {
			return (AUnit) obj;
		}
		return null;
	}
}
