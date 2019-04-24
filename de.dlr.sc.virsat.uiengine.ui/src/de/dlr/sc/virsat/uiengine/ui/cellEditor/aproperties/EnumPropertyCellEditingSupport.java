/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties;

import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;

/**
 * This class handles the functionality for creating a CellEditingSUpport for
 * IntPropertyInstances String PropertyInstances and FloatPropertyInstances
 * 
 * @author fisc_ph
 *
 */
public class EnumPropertyCellEditingSupport extends APropertyCellEditingSupport {
	
	private List<String> comboItems;
	private boolean showEmptyField;
	
	/**
	 * constructor of the value property cell editing support instantiate the editor
	 * @param editingDomain the editing domain
	 * @param viewer the table viewer
	 * @param property an a property
	 */
	public EnumPropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, EnumProperty property) {
		super(editingDomain, viewer, property);
		this.showEmptyField = true;
		comboItems = new EnumPropertyHelper().getEnumValueDefinitionStrings(property, showEmptyField);
		setEditor();
	}
	
	/**
	 * constructor of the value property cell editing support instantiate the editor
	 * @param editingDomain the editing domain
	 * @param viewer the table viewer
	 * @param property an aproperty
	 * @param showEmptyField 
	 * @param valueSwitch 
	 */
	public EnumPropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, EnumProperty property, PropertyInstanceValueSwitch valueSwitch, boolean showEmptyField) {
		super(editingDomain, viewer, property);
		this.showEmptyField = showEmptyField;
		comboItems = valueSwitch.getShowEnumValueDefinitionValues() 
				? new EnumPropertyHelper().getEnumValues(property, showEmptyField) : new EnumPropertyHelper().getEnumNames(property, showEmptyField);
		setEditor();
	}
	
	/**
	 * Sets the cell editor which is a combo for the Enums 
	 */
	protected void setEditor() {
		editor = new ComboBoxCellEditor((Composite) viewer.getControl(), comboItems.toArray(new String[0]));	
	}
	
	/**
	 * This method creates the command to set the value
	 * @param element the element to be changed
	 * @param userInputValue the input to be set
	 * @return the command in case it could be created or null in case no command could be created
	 */
	@Override
	protected Command createSetCommand(Object element, Object userInputValue) {
		// Now build the command to set the value
		APropertyInstance propertyInstance = getPropertyInstance(element);
		if (propertyInstance != null) {
			Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.ENUM_UNIT_PROPERTY_INSTANCE__VALUE, userInputValue); 
			return cmd;
		}
		return UnexecutableCommand.INSTANCE;
	}
	
	@Override
	protected boolean canEdit(Object element) {
		EnumUnitPropertyInstance propertyInstance = (EnumUnitPropertyInstance) getPropertyInstance(element);
		EnumValueDefinition changeEvd = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		changeEvd.setName(CHANGE_VALUE);
		Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.ENUM_UNIT_PROPERTY_INSTANCE__VALUE, changeEvd);
		boolean canEdit = cmd.canExecute();
		return canEdit;
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
		int index = (Integer) userInputValue;
		if (index >= 0) {
			EnumUnitPropertyInstance propertyInstance = (EnumUnitPropertyInstance) getPropertyInstance(element);
			EnumProperty ep = (EnumProperty) propertyInstance.getType();
			
			// Remember that index 0 is the empty field, or in other words the reference to null
			EnumValueDefinition newEvd = null;
			if (index > 0) {
				newEvd = showEmptyField ? ep.getValues().get(index - 1) : ep.getValues().get(index);
			} 
			EnumValueDefinition oldEvd = propertyInstance.getValue();

			// Only execute the command in case there is actually a change in the value
			if (!Objects.equals(newEvd, oldEvd)) {
				Command cmd = createSetCommand(element, newEvd);  
				editingDomain.getCommandStack().execute(cmd);
			}
		}
		viewer.update(element, null);
	}

}