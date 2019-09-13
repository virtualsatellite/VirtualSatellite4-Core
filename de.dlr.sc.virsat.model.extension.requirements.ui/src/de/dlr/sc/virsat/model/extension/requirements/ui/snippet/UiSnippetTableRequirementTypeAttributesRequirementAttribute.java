/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.snippet;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.extension.requirements.ui.celleditor.RequirementsAttributeTypeEnumerationEditingSupport;
import de.dlr.sc.virsat.model.extension.requirements.ui.provider.RequirementsAttributeDefinitionTableLabelProvider;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes.EStringCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementTypeAttributesRequirementAttribute extends AUiSnippetTableRequirementTypeAttributesRequirementAttribute implements IUiSnippet, SelectionListener {

	
	private static final String COLUMN_TEXT_NAME = "Name";
	private static final String COLUMN_TEXT_TYPE_NAME = "Type";
	private static final String NAME_TYPE_PROPERTY = "type";
	
	private static final String BUTTON_MOVEUP_TEXT = "Move Up";
	private static final String BUTTON_MOVEDOWN_TEXT = "Move Down";
	
	protected Button moveUpButton;
	protected Button moveDownButton;
	
	private FormToolkit toolKit;
	
	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		TableViewerColumn colName = (TableViewerColumn) createDefaultColumn(COLUMN_TEXT_NAME);
		
		//we know that createDefaultColumn returns tableViewerColumn so we cast it to tableViewerColumn
		colName.setEditingSupport(new EStringCellEditingSupport(editingDomain, (TableViewer) columnViewer, GeneralPackage.Literals.INAME__NAME));
		
		//Type property table
		EnumProperty typeProp = (EnumProperty) ActiveConceptHelper.getProperty(categoryModel, NAME_TYPE_PROPERTY);
		TableViewerColumn colType = (TableViewerColumn) createDefaultColumn(COLUMN_TEXT_TYPE_NAME);
		colType.setEditingSupport(new RequirementsAttributeTypeEnumerationEditingSupport(toolKit, editingDomain, columnViewer, typeProp));
		colType.getColumn().setToolTipText(typeProp.getDescription());
		
	}

	
	// Show attributes in the order of their creation
	@Override
	protected void setUpTableViewer(EditingDomain editingDomain, FormToolkit toolkit) {
		super.setUpTableViewer(editingDomain, toolkit);
		
		List<?> tableObjects = getTableObjects();
		
		// Sort the array entries by their index
		columnViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				int lhsIndex = tableObjects.indexOf(e1);
				int rhsIndex = tableObjects.indexOf(e2);
				return Integer.compare(lhsIndex, rhsIndex);
			}
		});
	}
	
	@Override
	protected void createButtons(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody) {
		Composite compositeButtons = toolkit.createComposite(sectionBody);
		compositeButtons.setLayoutData(new GridData());
		compositeButtons.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		createAddButton(toolkit, editingDomain, compositeButtons);
		createRemoveButton(toolkit, editingDomain, compositeButtons);
		
		moveUpButton = toolkit.createButton(compositeButtons, BUTTON_MOVEUP_TEXT, SWT.PUSH);
		moveDownButton = toolkit.createButton(compositeButtons, BUTTON_MOVEDOWN_TEXT, SWT.PUSH);
		
		moveUpButton.addSelectionListener(this);
		moveDownButton.addSelectionListener(this);
		
		createEditorButton(toolkit, compositeButtons);
		createExcelButton(toolkit, compositeButtons, columnViewer);
	}
	
	

	@Override
	protected ITableLabelProvider getTableLabelProvider() {
		return new RequirementsAttributeDefinitionTableLabelProvider(adapterFactory);
	}


	@Override
	protected List<APropertyInstance> getTableObjects() {
		return getArrayInstance(model).getArrayInstances();
	}
	


	@Override
	public Composite createSectionBody(FormToolkit toolkit, String sectionHeading, String sectionDescription,
			int numberColumns) {
		this.toolKit = toolkit;
		return super.createSectionBody(toolkit, sectionHeading, sectionDescription, numberColumns);
	}


	@Override
	public void widgetSelected(SelectionEvent e) {
		List<?> selection = columnViewer.getStructuredSelection().toList();
		TransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(model);
		if (!selection.isEmpty() && selection.get(0) instanceof ComposedPropertyInstance) {
	
			int moveDirection = 0;
			if (e.getSource().equals(moveUpButton)) {
				moveDirection = -1;
			} else if (e.getSource().equals(moveDownButton)) {
				moveDirection = +1;
			}
			
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) selection.get(0);
			ArrayInstance array = (ArrayInstance) cpi.eContainer();
			int newIndex = array.getArrayInstances().indexOf(cpi) + moveDirection;
			if (newIndex >= 0 && newIndex < array.getArrayInstances().size()) {
				Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Move Attribute") {
					@Override
					protected void doExecute() {
						array.getArrayInstances().move(newIndex, cpi);
					}
				};
				editingDomain.getCommandStack().execute(cmd);
			}
		}
	}


	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}
	
}
