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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.ExistenceVerification;
import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.dialog.ExistenceVerificationTargetEditingDialog;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.APropertyCellEditingSupport;

/**
 * Implements a customized editing support for selecting the target of an existence editing support
 * 
 */
public class ExisitenceVerificationTargetEditingSupport extends APropertyCellEditingSupport {

	protected FormToolkit toolkit;
	
	/**
	 * constructor of the value property cell editing support instantiate the editor
	 * @param editingDomain the editing domain
	 * @param viewer the table viewer
	 * @param property an aproperty
	 */
	public ExisitenceVerificationTargetEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, AProperty property, FormToolkit toolkit) {
		super(editingDomain, viewer, property);
		this.toolkit = toolkit;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		
		if (element instanceof ComposedPropertyInstance) {
			ExistenceVerification verification = new ExistenceVerification(((ComposedPropertyInstance) element).getTypeInstance());
			final CategoryAssignment referencedTypeInstance = verification.getTypeInstance();
			editor = new DialogCellEditor((Composite) viewer.getControl()) {
				
				@Override
				protected Object openDialogBox(Control cellEditorWindow) {
					EditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(referencedTypeInstance);
					if (referencedTypeInstance != null) {
						Dialog dialog = new ExistenceVerificationTargetEditingDialog(
								PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), toolkit, editingDomain,
								referencedTypeInstance);
						if (dialog.open() == Dialog.OK) {
							return referencedTypeInstance;
						} 
					}
					return null;
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
		}
		
		return editor;
	}
	
	@Override
	protected Object getValue(Object element) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		return propertyInstance;
	}
	
	@Override
	protected boolean canEdit(Object element) {
		// Overwriting super implementation to prevent check if set command can be executed.
		// Check is not necessary because this editing support is not actually doing any change on 
		// the model but rather forwarding the editing capabilities to a snippet which is executed in
		// a dialog and doing its own checks if the model can be edited. Thus, this editing support
		// does not even have a set command that can be executed
		return true;	
	}
	
	@Override
	protected void setValue(Object element, Object userInputValue) {
		// Overwriting super method because this method is not actually changing any value, it's just 
		// forwarding the editing capabilities and thus does not need to execute any set command
		viewer.update(element, null);
	}
	
}
