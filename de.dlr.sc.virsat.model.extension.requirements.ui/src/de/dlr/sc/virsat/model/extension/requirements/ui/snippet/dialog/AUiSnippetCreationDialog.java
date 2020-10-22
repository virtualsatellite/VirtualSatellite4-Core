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
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog using editor snippets to add a new model element
 *
 */
public abstract class AUiSnippetCreationDialog extends AUiSnippetEditDialog {
	
	/**
	 * @param parent The shell of the parent element
	 * @param editingDomain the editing domain of the underlying model element
	 * @param initModel the underlying model element
	 */
	public AUiSnippetCreationDialog(Shell parent, EditingDomain editingDomain, EObject initModel) {
		super(parent, editingDomain, initModel);
	}

	
	@Override
	protected void cancelPressed() {
		//Clean up
		editingDomain.getCommandStack().execute(getDeleteCommand(initModel));
		super.cancelPressed();
	}

}
