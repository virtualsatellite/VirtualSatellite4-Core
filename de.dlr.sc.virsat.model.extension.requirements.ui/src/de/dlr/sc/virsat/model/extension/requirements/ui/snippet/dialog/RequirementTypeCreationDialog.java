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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.UiSnippetTableRequirementTypeAttributesRequirementAttribute;

/**
 * @author Tobias Franz tobias.franz@dlr.de
 *
 */
public class RequirementTypeCreationDialog extends AUiSnippetCreationDialog {

	protected static final String INITIAL_TYPE_NAME = "NewRequirementType"; 
	
	protected FormToolkit toolkit;

	/**
	 * 
	 * @param parentShell The shell of the parent element
	 * @param toolkit The swt toolkit
	 * @param editingDomain the editing domain of the underlying model element
	 * @param initModel the underlying model element
	 */
	public RequirementTypeCreationDialog(Shell parentShell, FormToolkit toolkit, EditingDomain editingDomain,
			EObject initModel) {
		super(parentShell, editingDomain, initModel);
		this.toolkit = toolkit;
		if (initModel instanceof CategoryAssignment) {
			prepareNewTypeElement((CategoryAssignment) initModel);
		}
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
		new UiSnippetTableRequirementTypeAttributesRequirementAttribute().createSwt(toolkit, editingDomain, composite,
				initModel);
		buttonBar = createButtonBar(composite);

		return composite;
	}

	
	/**
	 * Prepare the new requirement type 
	 * @param initialElement the initially created element
	 */
	protected void prepareNewTypeElement(CategoryAssignment initialElement) {
		Command setCommand = SetCommand.create(editingDomain, initialElement, GeneralPackage.INAME__NAME, INITIAL_TYPE_NAME);
		editingDomain.getCommandStack().execute(setCommand);
	}

}
