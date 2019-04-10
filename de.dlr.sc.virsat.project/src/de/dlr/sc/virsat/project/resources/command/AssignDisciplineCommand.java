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
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Command to assign a discipline to an DVLM EObject and directly
 * saving it by its resourceSet
 * @author fisc_ph
 *
 */
public class AssignDisciplineCommand extends AbstractCommand {

	private ResourceSet rs;
	private EObject disciplineContainer;
	private Discipline discipline;
	
	/**
	 * Constructor to create the command for setting the discipline of an eObject in the DVLM model
	 * @param rs The ResourceSet to be used should be a VirSatResourceSet
	 * @param disciplineContainer The eObject implementing the interface IAssignedDiscipline
	 * @param discipline The discipline to be set by the command
	 */
	public AssignDisciplineCommand(ResourceSet rs, EObject disciplineContainer, Discipline discipline) {
		this.rs = rs;
		this.disciplineContainer = disciplineContainer;
		this.discipline = discipline;
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
	public void execute() {
		if ((rs instanceof VirSatResourceSet) && (disciplineContainer instanceof IAssignedDiscipline)) {
			VirSatResourceSet virSatRs = (VirSatResourceSet) rs;
			virSatRs.assignDiscipline((IAssignedDiscipline) disciplineContainer, discipline);
		}
	}

	@Override
	public void redo() {
	}

}
