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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;

/**
 * This class implements the core functionality for PropertyInstance Cell Editors
 * Such core functionality contains looking for the Property Instances by having the 
 * Properties as well as preparing the set commands
 * @author fisc_ph
 *
 */
public abstract class APropertyCellEditingSupport extends EditingSupport {

	protected ColumnViewer viewer;
	protected EditingDomain editingDomain;
	protected CategoryAssignmentHelper caHelper;
	protected CellEditor editor;
	protected String propertyFqn;
	
	// Changing the probe value to 0 gives the advantage that it is valid as float, as int and as a string
	// having a string as it has been set before, the probe value is directly rejected by the command when 
	// setting an integer or a float, since it cannot parse the value
	protected static final String CHANGE_VALUE = "0";
	protected static final String EMPTY_VALUE = "";

	/**
	 * Constructor of the abstract class property cell editing support instantiate the editing domain, the column viewer, property and category assignment
	 * @param editingDomain the editing domain
	 * @param viewer the column viewer
	 * @param property an a property
	 */
	public APropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, AProperty property) {
		super(viewer);
	    this.viewer = viewer;
	    this.editingDomain = editingDomain;
	    this.propertyFqn = property.getFullQualifiedName();
	    
	    caHelper = new CategoryAssignmentHelper(null);
	}

	@Override
	protected boolean canEdit(Object element) {
		boolean canEdit = createSetCommand(element, CHANGE_VALUE).canExecute();
		return canEdit;
	}

	/**
	 * use this method to access the Property Instance given by an Object
	 * The Object is usually expected to be of type Property
	 * @param element An object usually a Property that is referenced by the PropertyInstance we are looking for 
	 * @return the Property Instance that references the given element, or null in case nothing was found
	 */
	protected APropertyInstance getPropertyInstance(Object element) {
		if (element instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
			CategoryAssignment ca = cpi.getTypeInstance();
			return caHelper.setCategoryAssignment(ca).tryGetPropertyInstance(propertyFqn);
		}
		if (element instanceof CategoryAssignment) {
			return caHelper.setCategoryAssignment((CategoryAssignment) element).tryGetPropertyInstance(propertyFqn);
		} else if (element instanceof APropertyInstance) {
			return (APropertyInstance) element;
		}
		return null;
	}

	/**
	 * This method creates the command to set the value
	 * @param element the element to be changed
	 * @param userInputValue the input to be set
	 * @return the command in case it could be created or null in case no command could be created
	 */
	protected Command createSetCommand(Object element, Object userInputValue) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		if (propertyInstance != null) {
			Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, userInputValue); 
			return cmd;
		}
		return UnexecutableCommand.INSTANCE;
	}

	@Override
	protected Object getValue(Object element) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		String value = ((ValuePropertyInstance) propertyInstance).getValue();
		if (value == null) {
			value = EMPTY_VALUE;
		}
		return value;
	}

	@Override
	protected void setValue(Object element, Object userInputValue) {
		if (userInputValue == null) {
			userInputValue = EMPTY_VALUE;
		}
		
		// Only execute the command in case there is actually a change in the value
		Object currentValue = getValue(element);
		if (!userInputValue.equals(currentValue)) {
			Command cmd = createSetCommand(element, userInputValue);  
			editingDomain.getCommandStack().execute(cmd);
		}
		viewer.update(element, null);
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}
}