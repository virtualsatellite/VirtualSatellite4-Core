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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;

/**
 * This class handles the functionality for creating a CellEditingSUpport for
 * IntPropertyInstances String PropertyInstances and FloatPropertyInstances
 * 
 * @author fisc_ph
 *
 */
public class ValuePropertyCellEditingSupport extends APropertyCellEditingSupport {
	
	/**
	 * constructor of the value property cell editing support instantiate the editor
	 * @param editingDomain the editing domain
	 * @param viewer the table viewer
	 * @param property an aproperty
	 */
	public ValuePropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, AProperty property) {
		super(editingDomain, viewer, property);
		setEditor();
	}

	/**
	 * Sets the cell editor. This method needs to be overwritten in visualisation ui.
	 */
	protected void setEditor() {
		editor = new TextCellEditor((Composite) viewer.getControl());	
	}
}