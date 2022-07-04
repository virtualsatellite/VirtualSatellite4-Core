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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
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
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		initializeHelperForModel(initModel);

		Composite sectionBody = createSectionBody(toolkit, SECTION_HEADING, null, 1);
		sectionBody.setLayout(new GridLayout(UI_LAYOUT_NR_COLUMNS, false));

		Category viewerCategory = acHelper.getCategory(conceptId, categoryId);

		// Don't show property elementsToBeVerified as it is not relvant for the user (only for verification)
		for (AProperty property : ActiveConceptHelper.getNonArrayProperties(viewerCategory)) {
			if (!property.getName().equals(ExistenceVerification.PROPERTY_ELEMENTTOBEVERIFIED)) {
				createCommonPropertyWidgets(toolkit, sectionBody, property);
				createCustomPropertyWidgets(toolkit, editingDomain, sectionBody, property);
			}
		}
	}
	
}
