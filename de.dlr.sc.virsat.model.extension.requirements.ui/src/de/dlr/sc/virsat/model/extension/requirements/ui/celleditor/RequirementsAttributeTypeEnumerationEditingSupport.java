/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.celleditor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.dialog.EnumerationCreationDialog;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.EnumPropertyCellEditingSupport;

/**
 * @author fran_tb
 *
 */
public class RequirementsAttributeTypeEnumerationEditingSupport extends EnumPropertyCellEditingSupport {

	private List<String> comboItems;
	protected final FormToolkit toolKit;
	private EnumProperty property;
	
	/**
	 * constructor of the value property cell editing support instantiate the editor
	 * @param toolKit the swt toolkit
	 * @param editingDomain the editing domain
	 * @param viewer the table viewer
	 * @param property an a property
	 */
	public RequirementsAttributeTypeEnumerationEditingSupport(FormToolkit toolKit, EditingDomain editingDomain, ColumnViewer viewer, EnumProperty property) {
		super(editingDomain, viewer, property);
		this.toolKit = toolKit;
		this.property = property;
		setupValues(property);
	}
	
	/**
	 * Constructs the list of values available as type
	 * @param ep the enmueration property
	 */
	protected void setupValues(EnumProperty ep) {
		comboItems = new ArrayList<>();
		comboItems.add("");
		ep.getValues().forEach((evd) -> {
			comboItems.add(evd.getName());
		});
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		setupValues(property);
		return new ComboBoxCellEditor((Composite) viewer.getControl(), comboItems.toArray(new String[0]));
	}
	
	@Override
	protected Object getValue(Object element) {
		EnumUnitPropertyInstance propertyInstance = (EnumUnitPropertyInstance) getPropertyInstance(element);
		String selectedValue = (propertyInstance.getValue() != null) ? PropertyInstanceValueSwitch.getEnumValueDefinitionString(propertyInstance.getValue()) : EMPTY_VALUE;
		Integer value = this.comboItems.indexOf(selectedValue); 
		return value;
	}
	
	@Override
	protected void setValue(Object element, Object userInputValue) {
		super.setValue(element, userInputValue);
		int index = (Integer) userInputValue;
		if (index >= 0) {
			EnumUnitPropertyInstance propertyInstance = (EnumUnitPropertyInstance) getPropertyInstance(element);
			RequirementAttribute type = new RequirementAttribute((CategoryAssignment) propertyInstance.eContainer());
			if (propertyInstance.getValue().getName().equals(RequirementAttribute.TYPE_Enumeration_NAME)) {
				Dialog dialog = new EnumerationCreationDialog(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), this.toolKit, editingDomain,
						type.getEnumeration().getATypeInstance());
				dialog.open();
			} else if (!type.getEnumeration().getLiterals().isEmpty()) {
				editingDomain.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain) editingDomain) {
					@Override
					protected void doExecute() {
						type.getEnumeration().getLiterals().clear();
					}
				});
			}
		}
	}
}
