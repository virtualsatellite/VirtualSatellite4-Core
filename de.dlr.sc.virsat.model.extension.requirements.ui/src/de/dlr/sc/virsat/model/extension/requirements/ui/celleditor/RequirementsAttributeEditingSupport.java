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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.ui.command.InitializeRequirementAttributeCommand;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.APropertyCellEditingSupport;

/**
 * @author fran_tb
 *
 */
public class RequirementsAttributeEditingSupport extends APropertyCellEditingSupport {

	protected final int attributeIndex;

	protected static final String REQUIREMENTS_CONCEPT_NAME = "de.dlr.sc.virsat.model.extension.requirements";
	protected static final String ATTRIBUTE_CATEGORY_NAME = "AttributeValue";

	protected final VirSatTransactionalEditingDomain domain;

	private CellEditor editor;

	/**
	 * @param editingDomain
	 *            the editing domain
	 * @param viewer
	 *            the column viewer
	 * @param attIndex
	 *            the index of the attribute definition this class should support
	 */
	public RequirementsAttributeEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, int attIndex) {
		super(editingDomain, viewer, getAttributeValueProperty((VirSatTransactionalEditingDomain) editingDomain));
		this.attributeIndex = attIndex;
		this.domain = (VirSatTransactionalEditingDomain) editingDomain;
		editor = new TextCellEditor((Composite) viewer.getControl());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected APropertyInstance getPropertyInstance(Object element) {
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
		Requirement requirement = new Requirement(cpi.getTypeInstance());

		RequirementAttribute attributeDef = requirement.getReqType().getAttributes().get(attributeIndex);

		APropertyInstance instance = null;
		for (AttributeValue value : requirement.getElements()) {
			if (value.getAttType().equals(attributeDef)) {
				instance = getPropertyInstance(value);
			}
		}
		if (instance == null) {
			// Requirement attribute instance does not exist yet, create one
			AttributeValue newAttributeInstance = new AttributeValue(getConcept());
			Command command = InitializeRequirementAttributeCommand.create(domain, attributeDef, requirement,
					newAttributeInstance);
			domain.getCommandStack().execute(command);
			instance = getPropertyInstance(newAttributeInstance);
		}

		return instance;

	}

	/**
	 * get the property instance from the bean object
	 * 
	 * @param attributeInstance
	 *            the bean object
	 * @return the property instance
	 */
	protected APropertyInstance getPropertyInstance(AttributeValue attributeInstance) {
		CategoryAssignmentHelper attributeInstanceHelper = new CategoryAssignmentHelper(
				attributeInstance.getTypeInstance());
		return attributeInstanceHelper.getPropertyInstance(AttributeValue.PROPERTY_VALUE);
	}

	/**
	 * Get the requirements concept
	 * 
	 * @return the concept
	 */
	protected Concept getConcept() {
		ActiveConceptHelper acHelper = new ActiveConceptHelper(domain.getResourceSet().getRepository());
		return acHelper.getConcept(REQUIREMENTS_CONCEPT_NAME);
	}

	/**
	 * Get the property definition of the attribute's value
	 * 
	 * @param editingDomain
	 *            the editing domain
	 * @return the property definition
	 */
	protected static AProperty getAttributeValueProperty(VirSatTransactionalEditingDomain editingDomain) {
		ActiveConceptHelper acHelper = new ActiveConceptHelper(editingDomain.getResourceSet().getRepository());
		return acHelper.getProperty(REQUIREMENTS_CONCEPT_NAME, ATTRIBUTE_CATEGORY_NAME, AttributeValue.PROPERTY_VALUE);
	}

}
