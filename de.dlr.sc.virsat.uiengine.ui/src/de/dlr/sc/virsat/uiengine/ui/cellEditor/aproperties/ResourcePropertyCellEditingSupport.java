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

import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.uiengine.ui.dialog.SelectOrUploadFileDialog;

/**
 * This class handles the functionality for creating a CellEditingSUpport for
 * IntPropertyInstances String PropertyInstances and FloatPropertyInstances
 * 
 * @author fisc_ph
 *
 */
public class ResourcePropertyCellEditingSupport extends APropertyCellEditingSupport {
	
	private SelectOrUploadFileDialog dialog;
	
	/**
	 * constructor for reference property cell editing support instantiate the referenced type
	 * @param editingDomain the editing domain
	 * @param viewer the column viewer
	 * @param property the referenced property
	 */
	public ResourcePropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, ResourceProperty property) {
		super(editingDomain, viewer, property);
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		editor = new DialogCellEditor((Composite) viewer.getControl()) {
			
			@Override
			protected Object openDialogBox(Control cellEditorWindow) {
				Shell currentShell = Display.getCurrent().getActiveShell();
				IFolder propertyDocumentsFolder = VirSatProjectCommons.getDocumentFolder((EObject) element); 
				
				dialog = new SelectOrUploadFileDialog(currentShell, SWT.OPEN, propertyDocumentsFolder);
				return dialog.open();
			}
			
			@Override
			protected Button createButton(final Composite parent) {
				// This override is needed to the following eclipse bug
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=193081
				Button button = super.createButton(parent);

				// This listener hands back traversal control to the cell rather the button. 
				// This is important if TableEditor functionality is used. if the button handles the traverse 
				// signal, it will try to select the next button but not the next cell.
				button.addListener(SWT.Traverse, (event) -> parent.notifyListeners(SWT.Traverse, event));
				return button;
			}
		};
		return editor;
	}
	
	@Override
	protected Object getValue(Object element) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		URI uri = ((ResourcePropertyInstance) propertyInstance).getUri();
		return uri;
	}
	
	@Override
	protected boolean canEdit(Object element) {
		boolean canEdit = createSetCommand(element, null).canExecute();
		return canEdit;
	}
	
	@Override
	protected Command createSetCommand(Object element, Object userInputValue) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		if (propertyInstance != null) {
			Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI, userInputValue); 
			return cmd;
		}
		return UnexecutableCommand.INSTANCE;
	}
	
	@Override
	protected void setValue(Object element, Object userInputValue) {
		Command cmd = createSetCommand(element, userInputValue);  
		editingDomain.getCommandStack().execute(cmd);
		viewer.update(element, null);
	}
}