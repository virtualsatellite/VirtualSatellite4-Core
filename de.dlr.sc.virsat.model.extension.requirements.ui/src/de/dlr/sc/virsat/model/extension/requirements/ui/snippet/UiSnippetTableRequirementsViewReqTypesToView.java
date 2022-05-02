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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementsViewReqTypesToView extends AUiSnippetTableRequirementsViewReqTypesToView implements IUiSnippet {
	
	protected Button buttonAdd;
	private static final String SCETION_HEADING = "Filter requirements by slecting which types to show:";
	private static final String ADD_BUTTON_LABEL = "Add RequirementType";
	private static final String REMOVE_BUTTON_LABEL = "Remove RequirementType";
	
	@Override
	public Composite createSectionBody(FormToolkit toolkit, String sectionHeading, String sectionDescription,
			int numberColumns) {
		Composite sectionBody = super.createSectionBody(toolkit, sectionHeading, sectionDescription, numberColumns);
		getSection().setText(SCETION_HEADING);
		return sectionBody;
	}
	
	@Override
	protected void createAddButton(FormToolkit toolkit, EditingDomain editingDomain, Composite compositeButtons) {
		if ((style & STYLE_ADD_BUTTON) != 0) {
			buttonAdd = toolkit.createButton(compositeButtons, ADD_BUTTON_LABEL, SWT.PUSH);
			buttonAdd.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Concept activeConcept = acHelper.getConcept(conceptId);
					Command addCommand = createAddCommand(editingDomain, activeConcept);
					editingDomain.getCommandStack().execute(addCommand);
					Collection<?> objectes = addCommand.getAffectedObjects();
					ReferencePropertyInstance arrayElement = (ReferencePropertyInstance) objectes.iterator().next();
					ReferenceSelectionDialog dialog = ReferenceSelectionDialog.createRefernceSelectionDialog(Display.getCurrent().getActiveShell(), ActiveConceptHelper.getCategory(activeConcept, RequirementType.FULL_QUALIFIED_CATEGORY_NAME), adapterFactory);
					dialog.setAllowMultiple(false);
					dialog.setInput(arrayElement.eResource());
					dialog.setDoubleClickSelects(true);
					if (dialog.open() == Dialog.OK) {
						Object selection = dialog.getFirstResult();
						if (selection instanceof ATypeInstance) {
							Command cmd = SetCommand.create(editingDomain, arrayElement, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, dialog.getFirstResult());
							editingDomain.getCommandStack().execute(cmd);
						}
					} 
				}
	
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			checkWriteAccess(buttonAdd);
		}
	}
	
	@Override
	protected void createRemoveButton(FormToolkit toolkit, EditingDomain editingDomain, Composite compositeButtons) {
		if ((style & STYLE_REMOVE_BUTTON) != 0) {
			Button buttonRemove = toolkit.createButton(compositeButtons, REMOVE_BUTTON_LABEL, SWT.PUSH);
			buttonRemove.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					List<?> selection = columnViewer.getStructuredSelection().toList();
					if (!selection.isEmpty()) {
						Command cmd = createDeleteCommand(editingDomain, selection);
						editingDomain.getCommandStack().execute(cmd);
					}
				}
	
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			checkWriteAccess(buttonRemove);
		}
	}
}
