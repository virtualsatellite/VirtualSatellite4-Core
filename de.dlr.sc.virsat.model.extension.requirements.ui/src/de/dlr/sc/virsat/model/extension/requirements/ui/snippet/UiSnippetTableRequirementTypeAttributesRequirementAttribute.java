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

import java.util.List;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementTypeAttributesRequirementAttribute extends AUiSnippetTableRequirementTypeAttributesRequirementAttribute implements IUiSnippet {

	
	@Override
	protected void setUpTableViewer(EditingDomain editingDomain, FormToolkit toolkit) {
		super.setUpTableViewer(editingDomain, toolkit);
		
		List<?> tableObjects = getTableObjects();
		
		// Sort the array entries by their index
		columnViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				int lhsIndex = tableObjects.indexOf(e1);
				int rhsIndex = tableObjects.indexOf(e2);
				return Integer.compare(lhsIndex, rhsIndex);
			}
		});
	}
	
	@Override
	protected List<APropertyInstance> getTableObjects() {
		return getArrayInstance(model).getArrayInstances();
	}
	
}
