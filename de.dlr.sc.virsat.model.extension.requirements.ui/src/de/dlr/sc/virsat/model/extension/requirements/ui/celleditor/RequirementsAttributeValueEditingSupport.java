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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;

/**
 * Editing support  for a table with a column for all attribute values
 *
 */
public class RequirementsAttributeValueEditingSupport extends AbstractAttributeValueEditingSupport {


	protected static final String REQUIREMENTS_CONCEPT_NAME = Activator.getPluginId();
	protected static final String ATTRIBUTE_CATEGORY_NAME = "AttributeValue";

	/**
	 * @param editingDomain the editing domain
	 * @param viewer the column viewer
	 */
	public RequirementsAttributeValueEditingSupport(EditingDomain editingDomain, ColumnViewer viewer) {
		super(editingDomain, viewer);
	}

	@Override
	protected APropertyInstance getPropertyInstance(Object element) {
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
		AttributeValue attribute = new AttributeValue(cpi.getTypeInstance());

		APropertyInstance instance = getPropertyInstanceFromBean(attribute);
		if (instance == null) {
			// Requirement attribute instance does not exist yet, create one...
			// But don't add it to the model yet, otherwise we will trigger a notification
			// that disturbs UI (Focus loss)
			AttributeValue newAttributeInstance = new AttributeValue(getConcept());
			instance = getPropertyInstance(newAttributeInstance);
		}

		return instance;
	}
	
	/**
	 * get the property instance from the bean object
	 * 
	 * @param attributeInstance the bean object
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
	 * @param element the editor element
	 * @return the attribute definition
	 */
	@Override
	protected RequirementAttribute getAttributeDefinition(Object element) {
		AttributeValue att = null;
		
		if (element instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
			att = new AttributeValue(cpi.getTypeInstance());
		} else if (element instanceof AttributeValue) {
			att = (AttributeValue) element;
		} else if (element instanceof CategoryAssignment) {
			att = new AttributeValue((CategoryAssignment) element);
		} else {
			return null;
		}
		
		return att.getAttType();
	}
	
}
