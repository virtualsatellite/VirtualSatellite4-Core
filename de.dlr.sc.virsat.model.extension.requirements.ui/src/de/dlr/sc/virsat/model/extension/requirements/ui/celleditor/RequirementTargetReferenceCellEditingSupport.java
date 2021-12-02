/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.celleditor;

import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.ReferencePropertyCellEditingSupport;

public class RequirementTargetReferenceCellEditingSupport extends ReferencePropertyCellEditingSupport {

	public RequirementTargetReferenceCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer,
			ReferenceProperty property) {
		super(editingDomain, viewer, property);
	}
	
	@Override
	protected List<Function<Object, Boolean>> getResultFilters() {
		// Don't show requirement elements as possible trace targets
		List<Function<Object, Boolean>> superFilters = super.getResultFilters();
		superFilters.add((element) -> {
			Concept concept = ActiveConceptHelper.getConcept(((CategoryAssignment) element).getType());
			return !concept.getFullQualifiedName().equals(Activator.getPluginId());
		});
		return superFilters;
	}

}
