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
package de.dlr.sc.virsat.model.extension.requirements.ui.command;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;

/**
 * @author fran_tb
 *
 */
public class InitializeRequirementAttributeCommand extends RecordingCommand {
	
	protected final RequirementAttribute attributeType;
	protected final Requirement requirement;
	protected final AttributeValue attribute;
	
	protected static final String PREFIX_ATTRIBUTE = "att";
	
	/**
	 * 
	 * @param domain the editing domain
	 * @param attributeType the attribute type
	 * @param requirement the requirement instance, which should contains the attribute instance
	 * @param attributeValue the attribute instance
	 */
	protected InitializeRequirementAttributeCommand(TransactionalEditingDomain domain, RequirementAttribute attributeType, Requirement requirement, AttributeValue attributeValue) {
		super(domain);
		this.attributeType = attributeType;
		this.requirement = requirement;
		this.attribute = attributeValue;
	}
	
	/**
	 * Create a command that initializes a new attribute of a given type
	 * @param domain the editing domain
	 * @param attributeType the attribute type
	 * @param requirement the requirement instance, which should contains the attribute instance
	 * @param attributeValue the attribute instance
	 * @return the initialize command
	 */
	public static Command create(TransactionalEditingDomain domain, RequirementAttribute attributeType, Requirement requirement, AttributeValue attributeValue) {
		return new InitializeRequirementAttributeCommand(domain, attributeType, requirement, attributeValue);
	}
	
	/**
	 * Create a command that initializes a new attribute of a given type
	 * @param domain the editing domain
	 * @param attributeType the attribute type
	 * @param requirement the requirement instance, which should contains the attribute instance
	 * @param attributeValue the attribute instance
	 * @return the initialize command
	 */
	public static Command create(TransactionalEditingDomain domain, CategoryAssignment attributeType, CategoryAssignment requirement, CategoryAssignment attributeValue) {
		return new InitializeRequirementAttributeCommand(domain, new RequirementAttribute(attributeType), new Requirement(requirement), new AttributeValue(attributeValue));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 */
	@Override
	protected void doExecute() {
		attribute.setAttType(attributeType);
		attribute.setName(PREFIX_ATTRIBUTE + attributeType.getName());
		requirement.getElements().add(attribute);
	}
	
	

}
