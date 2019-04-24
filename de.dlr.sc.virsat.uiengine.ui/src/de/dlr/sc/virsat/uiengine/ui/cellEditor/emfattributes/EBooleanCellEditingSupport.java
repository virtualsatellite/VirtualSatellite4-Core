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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

/**
 * this class extends the editing support for the boolean cell editing support 
 * @author leps_je
 *
 */
public class EBooleanCellEditingSupport extends EditingSupport {

	private CellEditor editor;
	private EditingDomain editingDomain;
	private EAttribute emfAttribute;

	/**
	 * constructor for the boolean cell editing support instantiate the editior, editing domain and emf attribute
	 * @param editingDomain the editing domain
	 * @param viewer the table viewer
	 * @param emfAttribute the emf Attribute
	 */
	public EBooleanCellEditingSupport(EditingDomain editingDomain, TableViewer viewer, EAttribute emfAttribute) {
		super(viewer);
		this.editor = new ComboBoxCellEditor((Composite) viewer.getControl(), BOOL_LITERALS);
		this.editingDomain = editingDomain;
		this.emfAttribute = emfAttribute;
	}

	private static final String[] BOOL_LITERALS = { Boolean.FALSE.toString(), Boolean.TRUE.toString() };
	private static final int NOT_SET = -1;

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		Command cmd = SetCommand.create(editingDomain, element, emfAttribute, false);
		return cmd.canExecute();
	}

	@Override
	protected Object getValue(Object element) {
		EObject eObject = (EObject) element;
		Boolean attributeValue = (Boolean) eObject.eGet(emfAttribute);
		for (int i = 0; i < BOOL_LITERALS.length; i++) {
			if (BOOL_LITERALS[i].equals(attributeValue.toString())) {
				return i;
			}
		}
		return NOT_SET;
	}

	@Override
	protected void setValue(Object element, Object userInputValue) {
		int comboBoxIndex = (Integer) userInputValue;
		if ((comboBoxIndex >= 0) && (comboBoxIndex < BOOL_LITERALS.length)) {
			String booleanLiteral = BOOL_LITERALS[comboBoxIndex];
			Command cmd = SetCommand.create(editingDomain, element, emfAttribute, Boolean.parseBoolean(booleanLiteral));
			editingDomain.getCommandStack().execute(cmd);
			getViewer().update(element, null);
		}
	}
}