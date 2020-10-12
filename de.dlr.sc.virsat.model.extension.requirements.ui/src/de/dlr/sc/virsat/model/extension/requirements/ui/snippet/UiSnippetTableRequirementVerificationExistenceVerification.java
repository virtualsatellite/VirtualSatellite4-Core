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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.EditingSupport;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.extension.requirements.ui.celleditor.ExisitenceVerificationTargetEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.PropertyInstanceEditingSupportFactory;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementVerificationExistenceVerification extends AUiSnippetTableRequirementVerificationExistenceVerification implements IUiSnippet {
	
	@Override
	protected EditingSupport createEditingSupport(EditingDomain editingDomain, AProperty property) {
		
		if (property instanceof ReferenceProperty) {
			return new ExisitenceVerificationTargetEditingSupport(editingDomain, columnViewer, (ReferenceProperty) property, toolkit);
		}
		
		return PropertyInstanceEditingSupportFactory.INSTANCE.createEditingSupportFor(editingDomain, columnViewer, property);
	}
	
}
