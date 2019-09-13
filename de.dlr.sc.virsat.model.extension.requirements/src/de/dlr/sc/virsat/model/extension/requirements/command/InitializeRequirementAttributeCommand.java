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
package de.dlr.sc.virsat.model.extension.requirements.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

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
	
	
	/**
	 * 
	 * @param domain the editing domain
	 * @param attributeType the attribute type
	 * @param requirement the requirement instance, which should contains the attribute instance
	 * @param attributeValue the attribute instance
	 */
	public InitializeRequirementAttributeCommand(TransactionalEditingDomain domain, RequirementAttribute attributeType, Requirement requirement, AttributeValue attributeValue) {
		super(domain);
		this.attributeType = attributeType;
		this.requirement = requirement;
		this.attribute = attributeValue;
	}
	
	
	@Override
	protected void doExecute() {
		attribute.setAttType(attributeType);
		attribute.setName(AttributeValue.ATTRIBUTE_NAME_PREFIX + attributeType.getName());
		requirement.getElements().add(attribute);
	}

}
