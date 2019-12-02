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

import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralElementInstanceHelper;

/**
 * This rule is used to check for the cardinalities being set in a project.
 * E.G. it checks how many times a certain category can be assigned to a SEI
 * @author fisc_ph
 *
 */
public class DVLMApplicableForCardinalityRule implements IDVLMExtendedModelCapabilityRule {

	@Override
	public boolean isValid(Object owner, Object object) {
		IApplicableFor applicableFor = null;
		
		if (object instanceof StructuralElementInstance) {
			StructuralElementInstance sei = (StructuralElementInstance) object;
			applicableFor = sei.getType();
		} else if (object instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) object;
			applicableFor = (IApplicableFor) ca.getType();
		}
		
		if (applicableFor != null) {
			int cardinality = applicableFor.getCardinality();
			if (cardinality == 0) {
				return true;
			} else {
				return validateExistingAmount(owner, applicableFor, cardinality);
			}
		}
		
		return false;
	}

	@Override
	public boolean canExecute(Object owner, Object object, boolean containment) {
		if (object instanceof StructuralElementInstance && owner instanceof StructuralElementInstance) {
			return true;
		} else if (object instanceof CategoryAssignment && owner instanceof ICategoryAssignmentContainer) {
			return true;
		}
		return false;
	}

	/**
	 * This method counts the appearances of a Category or SE of a specified Type,
	 * and will return true in case further can be added. False if no further can be added
	 * @param owner The Container to which to add the new item
	 * @param applicableFor Either A Category Assignment or a SEI that will be added
	 * @param cardinality the cardinality
	 * @return true in case the cardinality allows to add it or false if not.
	 */
	private boolean validateExistingAmount(Object owner, IApplicableFor applicableFor, int cardinality) {
		if (applicableFor instanceof StructuralElement && owner instanceof StructuralElementInstance) {
			StructuralElementInstance parentSei = (StructuralElementInstance) owner;
			StructuralElement childSe = (StructuralElement) applicableFor;
			boolean isValidAmount = StructuralElementInstanceHelper.getStructuralElementInstances(parentSei, childSe.getFullQualifiedName()).size() < cardinality;
			return isValidAmount;
		} else if (applicableFor instanceof Category && owner instanceof ICategoryAssignmentContainer) {
			ICategoryAssignmentContainer caContainer = (ICategoryAssignmentContainer) owner;
			Category c = (Category) applicableFor;
			boolean isValidAmount = CategoryAssignmentHelper.getCategoryAssignmentsOfType(caContainer, c.getFullQualifiedName()).size() < cardinality;
			return isValidAmount;
		} else if (owner instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) owner;
			Category c = (Category) applicableFor;
			
			boolean isValidAmount = CategoryAssignmentHelper.getNestedCategoryAssignments(ca, c.getFullQualifiedName()).size() < cardinality;
			return isValidAmount;
		}
		
		return false;
	}
}
