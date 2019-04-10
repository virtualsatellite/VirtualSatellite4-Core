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
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;

/**
 * this class extends the editing support for cells from type string
 * @author leps_je
 *
 */
public class EStringCellEditingSupport extends EditingSupport {

	private CellEditor editor;
	private EditingDomain editingDomain;
	private EAttribute emfAttribute;

	/**
	 * constructor for the string cell editing support instantiate the editor, editing domain and emf attribute
	 * @param editingDomain the editing domain
	 * @param viewer the table viewer
	 * @param emfAttribute the attribute
	 */
	public EStringCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, EAttribute emfAttribute) {
		super(viewer);
		this.editor = new TextCellEditor((Composite) viewer.getControl());
		this.editingDomain = editingDomain;
		this.emfAttribute = emfAttribute;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	private static final String CHANGE_VALUE = "Change Probe Value";

	/**
	 * this method avoid composed properties
	 * @param element 
	 * @return the object 
	 */
	public Object avoidComposedProperty(Object element) {
		if (element instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
			return cpi.getTypeInstance();
		}
		return element;
	}
	
	@Override
	protected boolean canEdit(Object element) {
		Command cmd = SetCommand.create(editingDomain, avoidComposedProperty(element), emfAttribute, CHANGE_VALUE);
		return cmd.canExecute();
	}

	@Override
	protected Object getValue(Object element) {
		EObject eObject = (EObject) avoidComposedProperty(element);
		return eObject.eGet(emfAttribute);
	}

	@Override
	protected void setValue(Object element, Object userInputValue) {
		Command cmd = SetCommand.create(editingDomain, avoidComposedProperty(element), emfAttribute, (String) userInputValue);
		editingDomain.getCommandStack().execute(cmd);
		getViewer().update(element, null);
	}
}