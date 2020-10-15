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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.UiSnippetTableExistenceVerificationTarget;

/**
 *
 */
public class ExistenceVerificationTargetEditingDialog extends AUiSnippetEditDialog {

	protected FormToolkit toolkit;
	protected static final String DIALOG_TITEL = "Model Existence Verification Target Selection"; 
	
	/**
	 * @param parent
	 * @param editingDomain
	 * @param initModel
	 */
	public ExistenceVerificationTargetEditingDialog(Shell parent, FormToolkit toolkit,  EditingDomain editingDomain, EObject initModel) {
		super(parent, editingDomain, initModel);
		this.toolkit = toolkit;
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

		new UiSnippetTableExistenceVerificationTarget().createSwt(toolkit, editingDomain, composite,
				initModel);
		
		buttonBar = createButtonBar(composite);

		return composite;
	}
	
	@Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(DIALOG_TITEL);
    }
	
	@Override
    protected boolean isResizable() {
        return true;
    }

}
