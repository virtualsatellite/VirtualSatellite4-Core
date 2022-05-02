/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.ui.snippet;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetGenericCategoryAssignmentTable;
import de.dlr.sc.virsat.model.extension.thermal.ui.command.CreateAddComponentNameCatCommand;

;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Temporary category serving as an anchor for ThermalPort component reference
 * 
 */	
public abstract class AUiSnippetTableComponentNameCat extends AUiSnippetGenericCategoryAssignmentTable implements IUiSnippet {
	public AUiSnippetTableComponentNameCat() {
		super("de.dlr.sc.virsat.model.extension.thermal",
			"ComponentNameCat",
			"de.dlr.sc.virsat.model.extension.thermal.ComponentNameCat",
			STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
	}

	@Override
	protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
		return new CreateAddComponentNameCatCommand().create(editingDomain, model, activeConcept);
	}
}
