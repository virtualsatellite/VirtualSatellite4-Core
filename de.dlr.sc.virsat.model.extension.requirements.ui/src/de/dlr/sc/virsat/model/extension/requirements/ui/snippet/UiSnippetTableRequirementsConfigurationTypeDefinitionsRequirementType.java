/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.snippet;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.dialog.RequirementTypeCreationDialog;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementsConfigurationTypeDefinitionsRequirementType
		extends AUiSnippetTableRequirementsConfigurationTypeDefinitionsRequirementType implements IUiSnippet {

	protected Button buttonAdd;

	/**
	 * this method creates the add button and his functionality
	 * 
	 * @param toolkit the toolkit to be used to create the button
	 * @param editingDomain the editing domain an which the button might act on
	 * @param compositeButtons the composite in which the button should be placed
	 */
	protected void createAddButton(FormToolkit toolkit, EditingDomain editingDomain, Composite compositeButtons) {
		if ((style & STYLE_ADD_BUTTON) != 0) {
			buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT + getTypeInformation(), SWT.PUSH);
			buttonAdd.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					Concept activeConcept = acHelper.getConcept(conceptId);
					Command addCommand = createAddCommand(editingDomain, activeConcept);
					editingDomain.getCommandStack().execute(addCommand);

					RequirementsConfiguration requirementsConfiguration = new RequirementsConfiguration(
							(CategoryAssignment) model);
					RequirementType newElement = requirementsConfiguration.getTypeDefinitions()
							.get(requirementsConfiguration.getTypeDefinitions().size() - 1);

					Dialog dialog = new RequirementTypeCreationDialog(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), toolkit, editingDomain,
							newElement.getTypeInstance());
					dialog.open();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			checkWriteAccess(buttonAdd);
		}
	}

}
