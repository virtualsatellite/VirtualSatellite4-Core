/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.model.extension.requirements.ui.snippet.dialog;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.uiengine.ui.databinding.VirSatDataBindingContext;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.general.UiSnippetIName;

/**
 * @author fran_tb
 * 
 * Snippet that works outside of editor workbenches and thus sets the data binding itself
 *
 */
public class UiSnippetNameAutoDataBinding extends UiSnippetIName {


	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {

		super.createSwt(toolkit, editingDomain, composite, initModel);
		setDataBinding(new VirSatDataBindingContext(), editingDomain, initModel);
		updateState(true);
	}

}
