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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

/**
 * A class that provides editing support for multiple properties in one column.
 * For example, a TreeWiewer where parent and child elements are category assignments of different categories.
 * In this case it is possible to register multiple EditingSupports.
 */
public class MultiPropertyEditingSupport extends EditingSupport {

	List<APropertyCellEditingSupport> registeredEditingSupports = new ArrayList<>();
	
	/**
	 * @param viewer ColumnViewer to create EditingSupport for
	 */
	public MultiPropertyEditingSupport(ColumnViewer viewer) {
		super(viewer);
	}


	/**
	 * Register EditingSupport into MultiEditingSupport
	 * @param editingSupport EditingSupport for specific object
	 * for a given row Object
	 */
	public void registerEditingSupport(APropertyCellEditingSupport editingSupport) {
		registeredEditingSupports.add(editingSupport);
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return activeEditingSupport(element).getCellEditor(element);
	}

	@Override
	protected boolean canEdit(Object element) {
		return activeEditingSupport(element).canEdit(element);
	}

	@Override
	protected Object getValue(Object element) {
		return activeEditingSupport(element).getValue(element);
	}

	@Override
	protected void setValue(Object element, Object value) {
		activeEditingSupport(element).setValue(element, value);
	}

	/**
	 * Picks a registered editing support that is active for the given object
	 * @param element row Object to test against
	 * @return active EditingSupport
	 */
	private APropertyCellEditingSupport activeEditingSupport(Object element) {
		for (APropertyCellEditingSupport editingSupport : registeredEditingSupports) {
			if (editingSupport.getPropertyInstance(element) != null) {
				return editingSupport;
			}
		}
		throw new IllegalArgumentException("No Editing Support registered for Object " + element.toString());
	}
}