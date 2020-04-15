/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources.command;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Command to assign a discipline to an DVLM EObject and directly
 * saving it by its resourceSet
 */
public class AssignDisciplineCommand extends AbstractCommand {

	private EObject disciplineContainer;
	private Discipline discipline;
	private EditingDomain ed;
	private IUserContext userContext;
	
	/**
	 * Constructor to create the command for setting the discipline of an eObject in the DVLM model
	 * @param virSatEd The VirSat Editing Domain for directly saving the assigned discipline
	 * @param disciplineContainer The eObject implementing the interface IAssignedDiscipline
	 * @param discipline The discipline to be set by the command
	 */
	public AssignDisciplineCommand(EditingDomain virSatEd, EObject disciplineContainer, Discipline discipline) {
		this.disciplineContainer = disciplineContainer;
		this.discipline = discipline;
		this.ed = virSatEd;
		this.userContext = UserRegistry.getInstance();
		if (this.ed instanceof IUserContext) {
			userContext = (IUserContext) ed;
		}
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
	
	@Override
	public boolean canUndo() {
		return false;
	}
	
	@Override
	public boolean canExecute() {
		return RightsHelper.hasWritePermission(disciplineContainer, userContext) && super.canExecute();
	}
	
	@Override
	public void execute() {
		if (disciplineContainer instanceof IAssignedDiscipline) {
			((IAssignedDiscipline) disciplineContainer).setAssignedDiscipline(discipline);
			Resource resource = disciplineContainer.eResource();
				
			if (ed instanceof VirSatTransactionalEditingDomain) {
				((VirSatTransactionalEditingDomain) ed).saveResourceIgnorePermissions(resource);
			}
		}
	}

	@Override
	public void redo() {
	}

}
