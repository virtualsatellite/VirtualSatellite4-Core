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

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementVerificationsExistenceVerification extends AUISnippetTableRequirementsVerification implements IUiSnippet {
	
	public UiSnippetTableRequirementVerificationsExistenceVerification() {
		super("de.dlr.sc.virsat.model.extension.requirements",
				"de.dlr.sc.virsat.model.extension.requirements",
				"ExistenceVerification",
				"de.dlr.sc.virsat.model.extension.requirements.ExistenceVerification",
				STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
	}

	private static final int TABLE_HIGHT = 80;
	private static final String SECTION_HEADING = "System Model Existence Verification";
	private static final String SECTION_DESCRIPTION = "Verification method to ensure that a particular system model element is created.";


	@Override
	protected Table createDefaultTable(FormToolkit toolkit, Composite sectionBody) {
		Table table = super.createDefaultTable(toolkit, sectionBody);
		GridData gridDataTable = (GridData) table.getLayoutData();
		gridDataTable.heightHint = TABLE_HIGHT;
		return table;
	}

	@Override
	protected String getSnippetTitle() {
		return SECTION_HEADING;
	}
	
	@Override
	protected String getSnippetDescription() {
		return SECTION_DESCRIPTION;
	}
}
