/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure.nature;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.project.Activator;

/**
 * Nature for Virtual Satellite Projects. The Nature will be used for example by the
 * Navigators to only react on Virtual Satellite Projects and to discard displaying the other ones.
 */
public class VirSatProjectNature implements IProjectNature {

	public static final String NATURE_ID = "de.dlr.sc.virsat.project.nature";
	
	public static final String BUILDER_VALIDATOR_ID = "de.dlr.sc.virsat.build.validator";
	public static final String BUILDER_INHERITANCE_ID = "de.dlr.sc.virsat.build.inheritance";
	public static final String BUILDER_CALCULATION_ID = "de.dlr.sc.virsat.model.calculation.compute.builder";
	
	private IProject project;
	
	@Override
	public void configure() throws CoreException {
		// Add nature-specific information
		// for the project, such as adding a builder
		// to a project's build spec.

		boolean hasValidatorBuilder = false;
		boolean hasInheritanceBuilder = false;
		boolean hasCalculationBuilder = false;
		
		// Check which builders are attached to the project
		IProjectDescription desc = project.getDescription();
 		// get the description of the project basically .project file information
		ICommand[] commands = desc.getBuildSpec();
 		// get the build commands already associated with project.
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(BUILDER_VALIDATOR_ID)) {
				hasValidatorBuilder = true;
			} else if (commands[i].getBuilderName().equals(BUILDER_INHERITANCE_ID)) {
				hasInheritanceBuilder = true;
			} else if (commands[i].getBuilderName().equals(BUILDER_CALCULATION_ID)) {
				hasCalculationBuilder = true;
			}
		}

		// The BuildManager calls the build jobs in the order in which they are stored in the commands array
		
		// Add the Inheritance Builder if needed
		// Should be the first one to be called because this one really changes and adds resources 
		if (!hasInheritanceBuilder) {
			addBuilder(desc, BUILDER_INHERITANCE_ID);
		}
		
		// Add the calculation builder if needed
		// after the inheritance has set all the right stuff, the equations can be calculated on that base 
		if (!hasCalculationBuilder) {
			addBuilder(desc, BUILDER_CALCULATION_ID);
		}
		
		// Add the Validation Builder if needed
		// At the end the validator can be called 
		if (!hasValidatorBuilder) {
			addBuilder(desc, BUILDER_VALIDATOR_ID);
		}

		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatProjectNature: Successfully configured project nature", null));
	}

	/**
	 * Method to add the Builder with the builderId to the Project Description 
	 * @param desc The Project Description of current Project
	 * @param builderId The Id of the Builder which should be added 
	 * @throws CoreException If the description of the project cannot be set
	 */
	protected void addBuilder(IProjectDescription desc, String builderId) throws CoreException {
		ICommand[] commands = desc.getBuildSpec();
		ICommand[] newCommands = new ICommand[commands.length + 1];
		// create a new build command
		System.arraycopy(commands, 0, newCommands, 0, commands.length);
		ICommand command = desc.newCommand();
		command.setBuilderName(builderId); // attach it to sample builder
		newCommands[newCommands.length - 1] = command;
		desc.setBuildSpec(newCommands);
		project.setDescription(desc, null); // write to .project file
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatProjectNature: Added Builder " + builderId, null));
	}

	@Override
	public void deconfigure() throws CoreException {
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}
}
