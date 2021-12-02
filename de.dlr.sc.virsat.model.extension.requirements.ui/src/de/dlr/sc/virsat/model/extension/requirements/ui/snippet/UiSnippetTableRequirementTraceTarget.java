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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementTrace;
import de.dlr.sc.virsat.model.extension.requirements.ui.celleditor.RequirementTargetReferenceCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementTraceTarget extends AUiSnippetTableRequirementTraceTarget implements IUiSnippet {
	
	private static final String ADD_BUTTON_LABEL = "Add Target";
	
	@Override
	protected EditingSupport createEditingSupport(EditingDomain editingDomain, AProperty property) {
		if (property.getName().equals(RequirementTrace.PROPERTY_TARGET)) {
			return new RequirementTargetReferenceCellEditingSupport(editingDomain, columnViewer, (ReferenceProperty) property);
		}
		return super.createEditingSupport(editingDomain, property);
	}
	
	@Override
	protected void createAddButton(FormToolkit toolkit, EditingDomain editingDomain, Composite compositeButtons) {
		if ((style & STYLE_ADD_BUTTON) != 0) {
			Button buttonAdd = toolkit.createButton(compositeButtons, ADD_BUTTON_LABEL, SWT.PUSH);
			buttonAdd.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Concept activeConcept = acHelper.getConcept(conceptId);
					Command addCommand = createAddCommand(editingDomain, activeConcept);
					
					editingDomain.getCommandStack().execute(addCommand);
					Collection<?> objectes = addCommand.getAffectedObjects();
					ReferencePropertyInstance arrayElement = (ReferencePropertyInstance) objectes.iterator().next();
					ReferenceProperty typeDef = (ReferenceProperty) arrayElement.getType();
					
					Object selection = new RequirementTargetReferenceCellEditingSupport(editingDomain, columnViewer, typeDef).startCellEditor(arrayElement, typeDef.getReferenceType(), adapterFactory);
					if (selection instanceof ATypeInstance) {
						Command cmd = SetCommand.create(editingDomain, arrayElement, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, selection);
						editingDomain.getCommandStack().execute(cmd);
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
}
