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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationLiteral;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;
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

	public static final String[] BOOL_LITERALS = { Boolean.FALSE.toString(), Boolean.TRUE.toString() };
	private static final int NOT_SET = 0;

	private ColumnViewer viewer;
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
		this.viewer = viewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(Object element) {
		RequirementAttribute attDef = getAttributeDefinition(element);
		switch (attDef.getType()) {
			case RequirementAttribute.TYPE_String_NAME:
				editor = new TextCellEditor((Composite) viewer.getControl());
				return editor;
	
			case RequirementAttribute.TYPE_Boolean_NAME:
				editor = new ComboBoxCellEditor((Composite) viewer.getControl(), BOOL_LITERALS);
				return editor;
				
			case RequirementAttribute.TYPE_Enumeration_NAME:
				List<String> comboItems = new ArrayList<String>();
				attDef.getEnumeration().getLiterals().forEach(literal -> comboItems.add(literal.getName()));
				editor = new ComboBoxCellEditor((Composite) viewer.getControl(), comboItems.toArray(new String[0]));
				return editor;
	
			default:
				editor = new TextCellEditor((Composite) viewer.getControl());
				return editor;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.
	 * APropertyCellEditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(Object element) {
		RequirementAttribute attDef = getAttributeDefinition(element);
		switch (attDef.getType()) {
			case RequirementAttribute.TYPE_String_NAME:
				return super.getValue(element);
	
			case RequirementAttribute.TYPE_Boolean_NAME:
				return getBooleanValue((String) super.getValue(element));
				
			case RequirementAttribute.TYPE_Enumeration_NAME:
				return getEnumerationValue((String) super.getValue(element), attDef);
	
			default:
				return super.getValue(element);
		}
	}

	/**
	 * Transform the boolean string to an integer
	 * 
	 * @param stringValue
	 *            the boolean string
	 * @return the integer value
	 */
	protected Integer getBooleanValue(String stringValue) {
		for (int i = 0; i <= 1; i++) {
			if (stringValue.equals(BOOL_LITERALS[i])) {
				return i;
			}
		}
		return NOT_SET;
	}
	 
	/**
	 * Get the integer value of the enumeration
	 * @param stringValue the enumeration's string
	 * @param attDef the enumeration
	 * @return the index
	 */
	protected Integer getEnumerationValue(String stringValue, RequirementAttribute attDef) {
		for (EnumerationLiteral literal : attDef.getEnumeration().getLiterals()) {
			if (literal.getName().equals(stringValue)) {
				return attDef.getEnumeration().getLiterals().indexOf(literal);
			}
		}
		return NOT_SET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.
	 * APropertyCellEditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object userInputValue) {
		RequirementAttribute attDef = getAttributeDefinition(element);
	
		APropertyInstance attributeInstance = getPropertyInstance(element);

		if (attributeInstance.eResource() == null) {
			// Requirement attribute instance is not saved in file yet
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
			persistValueContainerAttribute(attributeInstance, cpi.getTypeInstance());
		}

		switch (attDef.getType()) {
			case RequirementAttribute.TYPE_String_NAME:
				super.setValue(element, userInputValue);
				break;
	
			case RequirementAttribute.TYPE_Boolean_NAME:
				setBooleanValue(element, userInputValue);
				break;
	
			case RequirementAttribute.TYPE_Integer_NAME:
				setIntegerValue(element, userInputValue);
				break;
	
			case RequirementAttribute.TYPE_Real_NAME:
				setRealValue(element, userInputValue);
				break;
	
			case RequirementAttribute.TYPE_Enumeration_NAME:
				setEnumerationValue(element, userInputValue, attDef);
				
			default:
				super.setValue(element, userInputValue);
				break;
		}
	}

	/**
	 * Transform the user input to a boolean-string value
	 * 
	 * @param element
	 *            the editor subject
	 * @param userInputValue
	 *            the user input
	 */
	protected void setBooleanValue(Object element, Object userInputValue) {
		if (userInputValue instanceof Integer) {
			int comboBoxIndex = (Integer) userInputValue;
			if ((comboBoxIndex >= 0) && (comboBoxIndex <= 1)) {
				String booleanLiteral = BOOL_LITERALS[comboBoxIndex];
				super.setValue(element, booleanLiteral);
			}
		}
	}

	/**
	 * Transform the user input to a integer-string value
	 * 
	 * @param element
	 *            the editor subject
	 * @param userInputValue
	 *            the user input
	 */
	protected void setIntegerValue(Object element, Object userInputValue) {
		Integer newValue = null;
		if (userInputValue instanceof Integer) {
			newValue = (Integer) userInputValue;

		} else if (userInputValue instanceof String) {
			try {
				newValue = Integer.parseInt((String) userInputValue);
			} catch (NumberFormatException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
						"Requirements UI: The user input value is not of type Integer!"));
			}
		}

		if (newValue != null) {
			super.setValue(element, newValue + "");
		}
	}

	/**
	 * Transform the user input to a integer-string value
	 * 
	 * @param element
	 *            the editor subject
	 * @param userInputValue
	 *            the user input
	 */
	protected void setRealValue(Object element, Object userInputValue) {
		Double newValue = null;
		if (userInputValue instanceof Double) {
			newValue = (Double) userInputValue;

		} else if (userInputValue instanceof String) {
			try {
				userInputValue = ((String) userInputValue).replaceAll(",", ".");
				newValue = Double.parseDouble((String) userInputValue);
			} catch (NumberFormatException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
						"Requirements UI: The user input value is not of type Real/Double!"));
			}
		}

		if (newValue != null) {
			super.setValue(element, newValue + "");
		}
	}
	
	/**
	 * Set the value of the selected enumeration literal's index
	 * @param element the element
	 * @param userInputValue the user input
	 * @param attDef the attrbute definition
	 */
	protected void setEnumerationValue(Object element, Object userInputValue, RequirementAttribute attDef) {
		super.setValue(element, attDef.getEnumeration().getLiterals().get((int) userInputValue).getName());
	}

	@Override
	protected APropertyInstance getPropertyInstance(Object element) {
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
		Requirement requirement = new Requirement(cpi.getTypeInstance());

		if (attributeIndex >= requirement.getReqType().getAttributes().size()) {
			return null;
		}

		RequirementAttribute attributeDef = requirement.getReqType().getAttributes().get(attributeIndex);

		APropertyInstance instance = null;
		for (AttributeValue value : requirement.getElements()) {
			if (value.getAttType().equals(attributeDef)) {
				instance = getPropertyInstance(value);
			}
		}
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
	 * Persist a the value's containing attribute instance
	 * @param attributeInstance the attribute instance
	 * @param caRequirement the requirement in which the attribute should be added
	 */
	protected void persistValueContainerAttribute(APropertyInstance attributeInstance,
			CategoryAssignment caRequirement) {
		Requirement requirement = new Requirement(caRequirement);
		AttributeValue newAttributeInstance = new AttributeValue((CategoryAssignment) attributeInstance.eContainer());
		Command command = InitializeRequirementAttributeCommand.create(domain,
				requirement.getReqType().getAttributes().get(attributeIndex), requirement, newAttributeInstance);
		domain.getCommandStack().execute(command);
	}

	/**
	 * Get the attribute definition of the current editor element
	 * 
	 * @param element
	 *            the editor element
	 * @return the attribute definition
	 */
	protected RequirementAttribute getAttributeDefinition(Object element) {
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
		Requirement requirement = new Requirement(cpi.getTypeInstance());

		return requirement.getReqType().getAttributes().get(attributeIndex);
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
