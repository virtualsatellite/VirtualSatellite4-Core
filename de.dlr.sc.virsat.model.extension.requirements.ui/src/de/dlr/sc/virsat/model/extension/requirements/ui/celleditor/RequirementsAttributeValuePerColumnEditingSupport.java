/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.model.extension.requirements.ui.celleditor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;

/**
 * Editing support for table with columns for the different attributes
 *
 */
public class RequirementsAttributeValuePerColumnEditingSupport extends AbstractAttributeValueEditingSupport {

	protected final int attributeIndex;


	/**
	 * @param editingDomain the editing domain
	 * @param viewer the column viewer
	 * @param attributeIndex the index of the attribute definition this class should support
	 */
	public RequirementsAttributeValuePerColumnEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, int attributeIndex) {
		super(editingDomain, viewer);
		this.attributeIndex = attributeIndex;
	}


	@Override
	protected APropertyInstance getPropertyInstance(Object element) {
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
		Requirement requirement = new Requirement(cpi.getTypeInstance());

		if (requirement.getReqType() == null 
				|| attributeIndex >= requirement.getReqType().getAttributes().size()) {
			return null;
		}

		RequirementAttribute attributeDef = requirement.getReqType().getAttributes().get(attributeIndex);

		for (AttributeValue value : requirement.getElements()) {
			if (value.getAttType().equals(attributeDef)) {
				return getPropertyInstanceFromBean(value);
			}
		}
		
		// Requirement attribute instance does not exist yet, create one...
		// But don't add it to the model yet, otherwise we will trigger a notification
		// that disturbs UI (Focus loss)
		AttributeValue newAttributeInstance = new AttributeValue(requirement.getConcept());
		return getPropertyInstance(newAttributeInstance);

	}

	/**
	 * get the property instance from the bean object
	 * 
	 * @param attributeInstance
	 *            the bean object
	 * @return the property instance
	 */
	protected APropertyInstance getPropertyInstanceFromBean(AttributeValue attributeInstance) {
		CategoryAssignmentHelper attributeInstanceHelper = new CategoryAssignmentHelper(
				attributeInstance.getTypeInstance());
		return attributeInstanceHelper.getPropertyInstance(AttributeValue.PROPERTY_VALUE);
	}

	/**
	 * Get the attribute definition of the current editor element
	 * 
	 * @param element
	 *            the editor element
	 * @return the attribute definition
	 */
	@Override
	protected RequirementAttribute getAttributeDefinition(Object element) {
		Requirement requirement = null;
		
		if (element instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
			requirement = new Requirement(cpi.getTypeInstance());
		} else if (element instanceof Requirement) {
			requirement = (Requirement) element;
		} else if (element instanceof CategoryAssignment) {
			requirement = new Requirement((CategoryAssignment) element);
		} else {
			return null;
		}
		
		return requirement.getReqType().getAttributes().get(attributeIndex);
	}

}
