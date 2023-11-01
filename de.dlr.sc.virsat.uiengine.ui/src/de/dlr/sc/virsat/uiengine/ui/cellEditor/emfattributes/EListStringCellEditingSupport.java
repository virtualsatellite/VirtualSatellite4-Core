/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;

/**
 * Custom cell editing support for handling EList of strings in a table viewer.
 * Extends EStringCellEditingSupport.
 */
public class EListStringCellEditingSupport extends EStringCellEditingSupport {

	// Delimiters: comma, semicolon, period, or spaces
	private static final String DELIMITERS_REGEX = "[,;.\\s]+"; 
	
	public EListStringCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, EAttribute emfAttribute) {
		super(editingDomain, viewer, emfAttribute);
	}
	
	@Override
	protected boolean canEdit(Object element) {
		Command cmd = SetCommand.create(editingDomain, avoidComposedProperty(element), emfAttribute, Collections.EMPTY_LIST);
		return cmd.canExecute();
	}

	@Override
	protected Object getValue(Object element) {
	    // Retrieve the current value from the model
	    Object value = super.getValue(element);

	    // If the value is a List, join its elements into a comma-separated string
	    if (value instanceof List<?>) {
	        return String.join(", ", ((List<?>) value).toArray(new String[0]));
	    }

	    // Return an empty string if the value is not a List
	    return "";
	}

	@Override
	protected void setValue(Object element, Object userInputValue) {
	    // Check if the element is of type Discipline and the input value is a String
	    if (element instanceof Discipline && userInputValue instanceof String) {
	        // Split the input string using the specified delimiters
	        String[] userArray = ((String) userInputValue).split(DELIMITERS_REGEX);

	        // Create a SetCommand to update the model with the new value
	        Command cmd = SetCommand.create(editingDomain, avoidComposedProperty(element), emfAttribute, Arrays.asList(userArray));

	        // Execute the command to update the model
	        editingDomain.getCommandStack().execute(cmd);

	        // Update the viewer to reflect the changes
	        getViewer().update(element, null);
	    }
	}

}
