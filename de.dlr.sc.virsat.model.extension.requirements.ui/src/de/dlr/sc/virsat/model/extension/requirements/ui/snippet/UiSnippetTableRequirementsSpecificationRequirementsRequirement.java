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

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.ui.command.CreateAddArrayElementRequirementsCommand;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementsSpecificationRequirementsRequirement
		extends UiSnippetCustomRequirementsAttributeTable implements IUiSnippet {

	/**
	 * 
	 */
	public UiSnippetTableRequirementsSpecificationRequirementsRequirement() {
		super("de.dlr.sc.virsat.model.extension.requirements",
			"Requirement",
			"requirements",
			"RequirementsSpecification",
			"de.dlr.sc.virsat.model.extension.requirements.Requirement",
			STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
	}
	
	@Override
	protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
		return new CreateAddArrayElementRequirementsCommand().create(editingDomain, getArrayInstance(model),  ActiveConceptHelper.getCategory(activeConcept, "Requirement"));
	}
	
}
