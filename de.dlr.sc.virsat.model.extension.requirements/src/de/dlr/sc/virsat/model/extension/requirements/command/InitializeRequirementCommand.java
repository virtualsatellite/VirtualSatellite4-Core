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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;

/**
 * @author fran_tb
 *
 */
public class InitializeRequirementCommand extends RecordingCommand {

	protected final Requirement requirement;
	protected final RequirementType requirementType;
	protected final TransactionalEditingDomain editingDomain;

	/**
	 * Restricted constructor
	 * 
	 * @param requirement
	 *            the bean of the requirement
	 * @param requirementType
	 *            the bean of the requirement type
	 * @param domain
	 *            the editing domain
	 */
	protected InitializeRequirementCommand(TransactionalEditingDomain domain, Requirement requirement,
			RequirementType requirementType) {
		super(domain);
		this.requirement = requirement;
		this.requirementType = requirementType;
		this.editingDomain = domain;
	}

	/**
	 * Create method for this command
	 * 
	 * @param requirement
	 *            the {@link CategoryAssignment} of the new requirement
	 * @param requirementType
	 *            the {@link CategoryAssignment} of the requirement type
	 * @param domain
	 *            the editing domain
	 * @return the command to initialize the requirement
	 */
	public static Command create(TransactionalEditingDomain domain, CategoryAssignment requirement,
			CategoryAssignment requirementType) {
		return new InitializeRequirementCommand(domain, new Requirement(requirement),
				new RequirementType(requirementType));
	}

	/**
	 * Create method for this command
	 * 
	 * @param requirement
	 *            the new requirement
	 * @param requirementType
	 *            the requirement type
	 * @param domain
	 *            the editing domain
	 * @return the command to initialize the requirement
	 */
	public static Command create(TransactionalEditingDomain domain, Requirement requirement,
			RequirementType requirementType) {
		return new InitializeRequirementCommand(domain, requirement, requirementType);
	}

	@Override
	protected void doExecute() {
		requirement.setReqType(requirementType);
		requirement.setName(Requirement.REQUIREMENT_NAME_PREFIX + requirementType.getName());
		requirement.setStatus(Requirement.STATUS_Open_NAME);

		// Also prepare attribute instances to simplify setting values later
		for (RequirementAttribute attDef : requirementType.getAttributes()) {
			AttributeValue attInstance = new AttributeValue(requirement.getConcept());
			InitializeRequirementAttributeCommand composedCommand = new InitializeRequirementAttributeCommand(
					editingDomain, attDef, requirement, attInstance);
			composedCommand.execute();
		}
	}

}
