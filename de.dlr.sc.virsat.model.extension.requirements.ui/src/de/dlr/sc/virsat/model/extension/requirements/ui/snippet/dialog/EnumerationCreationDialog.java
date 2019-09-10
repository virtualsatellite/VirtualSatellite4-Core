/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.snippet.dialog;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.UiSnippetTableEnumerationDefinitionLiteralsEnumerationLiteral;

/**
 * @author fran_tb
 *
 */
public class EnumerationCreationDialog  extends Dialog {
	
	protected FormToolkit toolkit;
	protected EditingDomain editingDomain;
	protected EObject initModel;
	
	
	/**
	 * 
	 * @param parentShell
	 *            The shell of the parent element
	 * @param toolkit
	 *            The swt toolkit
	 * @param editingDomain
	 *            the editing domain of the underlying model element
	 * @param initModel
	 *            the underlying model element
	 */
	public EnumerationCreationDialog(Shell parentShell, FormToolkit toolkit, EditingDomain editingDomain,
			EObject initModel) {
		super(parentShell);
		this.toolkit = toolkit;
		this.editingDomain = editingDomain;
		this.initModel = initModel;

	}
	
	
	@Override
	protected Control createContents(Composite parent) {
		
		// create the top level composite for the dialog
		Composite composite = new Composite(parent, 0);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		applyDialogFont(composite);

		// initialize the dialog units
		initializeDialogUnits(composite);
		// create the dialog area and button bar
		dialogArea = createDialogArea(composite);

		new UiSnippetNameAutoDataBinding().createSwt(toolkit, editingDomain, composite, initModel);
		UiSnippetTableEnumerationDefinitionLiteralsEnumerationLiteral snippet = new UiSnippetTableEnumerationDefinitionLiteralsEnumerationLiteral();
		snippet.createSwt(toolkit, editingDomain, composite,
				initModel);
		snippet.setExpanded(true);
		buttonBar = createButtonBar(composite);

		return composite;
	}
	
	
	@Override
	protected void cancelPressed() {
		//Clean up
		editingDomain.getCommandStack().execute(getDeleteCommand(initModel));
		super.cancelPressed();
	}
	
	
	/**
	 * Create delete command
	 * @param object the object to be deleted
	 * @return the command
	 */
	protected Command getDeleteCommand(EObject object) {
		Command deleteCommand;
		
		BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
		if (object instanceof CategoryAssignment || object instanceof ComposedPropertyInstance) {
			
			CategoryAssignment ca = object instanceof ComposedPropertyInstance ? ((ComposedPropertyInstance) object).getTypeInstance() : (CategoryAssignment) object;
			try {
				IBeanCategoryAssignment beanCa = beanCaFactory.getInstanceFor(ca);
				deleteCommand = beanCa.delete(editingDomain);
			} catch (CoreException e) {

				deleteCommand = DeleteCommand.create(editingDomain, ca);
			}
		} else {

			deleteCommand = DeleteCommand.create(editingDomain, object);
		}
		return deleteCommand;
	}

}
