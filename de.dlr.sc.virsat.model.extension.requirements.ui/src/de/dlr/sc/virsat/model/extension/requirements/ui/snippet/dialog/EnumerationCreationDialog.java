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

import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.UiSnippetTableEnumerationDefinitionLiteralsEnumerationLiteral;

/**
 * @author fran_tb
 *
 */
public class EnumerationCreationDialog extends AUiSnippetCreationDialog {
	
	protected FormToolkit toolkit;
	
	
	/**
	 * 
	 * @param parentShell The shell of the parent element
	 * @param toolkit The swt toolkit
	 * @param editingDomain the editing domain of the underlying model element
	 * @param initModel the underlying model element
	 */
	public EnumerationCreationDialog(Shell parentShell, FormToolkit toolkit, EditingDomain editingDomain,
			EObject initModel) {
		super(parentShell, editingDomain, initModel);
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

		new UiSnippetNameAutoDataBinding().createSwt(toolkit, editingDomain, composite, initModel);
		UiSnippetTableEnumerationDefinitionLiteralsEnumerationLiteral snippet = new UiSnippetTableEnumerationDefinitionLiteralsEnumerationLiteral();
		snippet.createSwt(toolkit, editingDomain, composite,
				initModel);
		snippet.setExpanded(true);
		buttonBar = createButtonBar(composite);

		return composite;
	}
	
}
