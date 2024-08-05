/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

import jakarta.xml.bind.annotation.XmlType;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.util.RequirementHelper;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
@XmlType(name = ARequirement.FULL_QUALIFIED_CATEGORY_NAME)
public  class Requirement extends ARequirement {
	
	public static final String REQUIREMENT_NAME_PREFIX = "Req";
	
	/**
	 * Constructor of Concept Class
	 */
	public Requirement() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public Requirement(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public Requirement(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}
	
	/**
	 * Update the requirement name to the name of its identifying attributes
	 * @param ed the editing domain
	 * @return the command
	 */
	public Command updateNameFromAttributes(EditingDomain ed) {
		return setName(ed, getNameFromAttributes());
	}
	
	/**
	 * Update the requirement name to the name of its identifying attributes
	 * @param ed the editing domain
	 * @param addition the addition
	 * @return the command
	 */
	public Command updateNameFromAttributes(EditingDomain ed, String addition) {
		return setName(ed, getNameFromAttributes() + new RequirementHelper().cleanEntityName(addition));
	}
	
	/**
	 * Update the requirement name to the name of its identifying attributes
	 */
	public void updateNameFromAttributes() {
		setName(getNameFromAttributes());
	}
	
	/**
	 * Update the requirement name to the name of its identifying attributes and give a customization 
	 * @param addition customization / description
	 */
	public void updateNameFromAttributes(String addition) {
		setName(getNameFromAttributes() + new RequirementHelper().cleanEntityName(addition));
	}
	
	/**
	 * Create a name from all identifying attributes
	 * @return the name derived from the attributes
	 */
	protected String getNameFromAttributes() {
		StringBuilder newReqName = new StringBuilder();
		newReqName.append(REQUIREMENT_NAME_PREFIX);
		newReqName.append(getIdentifier());
		return new RequirementHelper().cleanEntityName(newReqName.toString());
	}
	
	@Override
	public String getIdentifier() {
		StringBuilder newReqName = new StringBuilder();
		for (AttributeValue child : getElements()) {
			if (child.getAttType().getType().equals(RequirementAttribute.TYPE_Identifier_NAME)) {
				newReqName.append(child.getValue());
			}
		}
		return newReqName.toString();
	}
}
