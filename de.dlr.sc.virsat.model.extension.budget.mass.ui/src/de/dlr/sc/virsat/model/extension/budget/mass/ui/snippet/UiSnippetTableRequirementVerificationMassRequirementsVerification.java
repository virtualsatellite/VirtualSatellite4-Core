/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.mass.ui.snippet;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.budget.mass.Activator;
import de.dlr.sc.virsat.model.extension.requirements.ui.command.CreateAddArrayElementVerificationCommand;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstanceCategoryTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

public class UiSnippetTableRequirementVerificationMassRequirementsVerification extends AUiSnippetArrayInstanceCategoryTable implements IUiSnippet {

	/**
	 * Constructor
	 */
	public UiSnippetTableRequirementVerificationMassRequirementsVerification() {
		super("de.dlr.sc.virsat.model.extension.budget.mass",
			"MassRequirementsVerification",
			"verification",
			"Requirement",
			"de.dlr.sc.virsat.model.extension.budget.mass.MassRequirementsVerification",
			STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
	}
	
	@Override
	protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
		return new CreateAddArrayElementVerificationCommand().create(editingDomain, getArrayInstance(model),  ActiveConceptHelper.getCategory(activeConcept, "MassRequirementsVerification"));
	}
	
	@Override
	public boolean isActive(EObject model) {
		initializeHelperForModel(model);
		if (model == null || acHelper.getConcept(Activator.getPluginId()) == null) {
			return false; // Concept not active
		}
		return super.isActive(model);
	}
	
}
