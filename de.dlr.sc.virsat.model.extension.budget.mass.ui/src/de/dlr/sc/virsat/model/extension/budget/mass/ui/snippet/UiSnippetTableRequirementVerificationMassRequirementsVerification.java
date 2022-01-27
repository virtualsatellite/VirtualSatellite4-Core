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

import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.AUISnippetTableRequirementsVerification;

public class UiSnippetTableRequirementVerificationMassRequirementsVerification extends AUISnippetTableRequirementsVerification {

	/**
	 * Constructor
	 */
	public UiSnippetTableRequirementVerificationMassRequirementsVerification() {
		super("de.dlr.sc.virsat.model.extension.budget.mass",
			"de.dlr.sc.virsat.model.extension.budget.mass",
			"MassRequirementsVerification",
			"de.dlr.sc.virsat.model.extension.budget.mass.MassRequirementsVerification",
			STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
	}
	
}
