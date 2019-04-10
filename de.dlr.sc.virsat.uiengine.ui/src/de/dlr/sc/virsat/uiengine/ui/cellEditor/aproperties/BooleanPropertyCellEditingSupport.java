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
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;

/**
 * This class implements a cell editor for a combobox
 * showing False and True options to be set
 * 
 * @author fisc_ph
 *
 */
public class BooleanPropertyCellEditingSupport extends APropertyCellEditingSupport {

	/**
	 * constructor for the boolean property cell editing support instantiate the editor
	 * @param editingDomain the editing domain
	 * @param viewer the column viewer
	 * @param property an aproperty
	 */
	public BooleanPropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, AProperty property) {
		super(editingDomain, viewer, property);
		editor = new ComboBoxCellEditor((Composite) viewer.getControl(), BOOL_LITERALS);
	}

	public static final String[] BOOL_LITERALS = {Boolean.FALSE.toString(), Boolean.TRUE.toString() };
	private static final int NOT_SET = -1;
	
	@Override
	protected Object getValue(Object element) {
		String comboValue = (String) super.getValue(element);
		for (int i = 0; i <= 1; i++) {
			if (comboValue.equals(BOOL_LITERALS[i])) {
				return i;
			}
		}
		return NOT_SET;
	}
	
	@Override
	protected void setValue(Object element, Object userInputValue) {
		if (userInputValue instanceof Integer) {
			int comboBoxIndex = (Integer) userInputValue;
			if ((comboBoxIndex >= 0) && (comboBoxIndex <= 1)) {
				String booleanLiteral = BOOL_LITERALS[comboBoxIndex];
				super.setValue(element, booleanLiteral);
			}
		}
	}
}