/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util.rules;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.exception.DVLMMissingTypeException;

/**
 * This rule checks of a category assignment is applicable for the Type of the StructuralElementInstance
 * @author fisc_ph
 *
 */
public class DVLMApplicableForRuleCategoryAssignmentInStructuralElementInstance extends ADVLMExtendedModelCapabilityRuleXInStructuralElementInstance implements IDVLMExtendedModelCapabilityRule {

	@Override
	public boolean isValid(Object owner, Object object) {
		StructuralElement owningStructuralElement = getStructuralElementFromOwner(owner);
		
		CategoryAssignment categoryAssignment = (CategoryAssignment) object;
		Category category = (Category) categoryAssignment.getType();
		
		DVLMMissingTypeException.checkAndThrowMissingCategory(categoryAssignment);

		// if there is no applicableFor information in this category
		// but this category is extending some other category, then check it for
		// applicableFor information
		while (category.getExtendsCategory() != null && !category.isIsApplicableForAll() && category.getApplicableFor().isEmpty()) {
			category = category.getExtendsCategory();
		}
		
		if (category.isIsApplicableForAll()) {
			return true;
		} else {
			return category.getApplicableFor().contains(owningStructuralElement);
		}
	}

	@Override
	public boolean canExecute(Object owner, Object object, boolean containment) {
		return super.canExecute(owner, object, containment) && object instanceof CategoryAssignment && containment;
	}
}
