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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.extension.requirements.model.ExistenceVerification;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetSectionExistenceVerification extends AUiSnippetSectionExistenceVerification implements IUiSnippet {
	
	@Override
	protected void createCommonPropertyWidgets(FormToolkit toolkit, Composite sectionBody, AProperty property) {
		if (!property.getName().equals(ExistenceVerification.PROPERTY_ELEMENTTOBEVERIFIED)) {
			super.createCommonPropertyWidgets(toolkit, sectionBody, property);
		}
	}
	
	@Override
	protected void createCustomPropertyWidgets(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody,
			AProperty property) {
		if (!property.getName().equals(ExistenceVerification.PROPERTY_ELEMENTTOBEVERIFIED)) {
			super.createCustomPropertyWidgets(toolkit, editingDomain, sectionBody, property);
		}
	}
	
}
