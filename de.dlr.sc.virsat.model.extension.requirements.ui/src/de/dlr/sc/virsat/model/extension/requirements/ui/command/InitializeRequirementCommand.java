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
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;

/**
 * @author fran_tb
 *
 */
public class InitializeRequirementCommand extends RecordingCommand {

	protected final Requirement requirement;
	protected final RequirementType requirementType;
	protected static final String REQ_NAME_PREFIX = "req";
	
	/**
	 * Restricted constructor
	 * @param requirement the bean of the requirement 
	 * @param requirementType the bean of the requirement type
	 * @param domain the editing domain
	 */
	protected InitializeRequirementCommand(TransactionalEditingDomain domain, Requirement requirement, RequirementType requirementType) {
		super(domain);
		this.requirement = requirement;
		this.requirementType = requirementType;
	}
	
	
	/**
	 * Create method for this command
	 * @param requirement the {@link CategoryAssignment} of the new requirement
	 * @param requirementType the {@link CategoryAssignment} of the requirement type
	 * @param domain the editing domain
	 * @return the command to initialize the requirement
	 */
	public static Command create(TransactionalEditingDomain domain, CategoryAssignment requirement, CategoryAssignment requirementType) {
		return new InitializeRequirementCommand(domain, new Requirement(requirement), new RequirementType(requirementType));
	}
	
	/**
	 * Create method for this command
	 * @param requirement the new requirement
	 * @param requirementType the requirement type
	 * @param domain the editing domain
	 * @return the command to initialize the requirement
	 */
	public static Command create(TransactionalEditingDomain domain, Requirement requirement, RequirementType requirementType) {
		return new InitializeRequirementCommand(domain, requirement, requirementType);
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 */
	@Override
	protected void doExecute() {
		requirement.setReqType(requirementType);
		requirement.setName(REQ_NAME_PREFIX + requirementType.getName());
		requirement.setStatus(Requirement.STATUS_Open_NAME);
	}

}
