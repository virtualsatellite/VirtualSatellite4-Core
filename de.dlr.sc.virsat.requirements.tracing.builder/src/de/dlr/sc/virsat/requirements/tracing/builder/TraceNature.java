/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
	
	The nature for the tracebility builder
 *
 */
public class TraceNature implements IProjectNature {
	
	public static final String NATURE_ID = "de.dlr.sc.virsat.requirements.tracing.builder.TraceNature";
	public static final String BUILDER_TRACE_ID = "de.dlr.sc.virsat.requirements.tracing.builder.TraceBuilder";
	
	private IProject project;
	
	/**
	 * Method to check if there is tracebuilder
	 * @return true if there is
	 * @throws CoreException the exception
	 */
	private boolean hasTraceBuilder() throws CoreException {
		boolean hasTraceBuilder = false;
		IProjectDescription desc = project.getDescription();
		ICommand[] commands = desc.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(BUILDER_TRACE_ID)) {
				hasTraceBuilder = true;
			} 
		}
		return hasTraceBuilder;
	}
	
	/**
	 * @throws CoreException the exception
	 */
	private void addBuilder() throws CoreException {
		IProjectDescription desc = project.getDescription();
		ICommand[] commands = desc.getBuildSpec();
		ICommand[] newCommands = new ICommand[commands.length + 1];
		// create a new build command
		System.arraycopy(commands, 0, newCommands, 0, commands.length);
		ICommand command = desc.newCommand();
		command.setBuilderName(BUILDER_TRACE_ID); // attach it to builder
		newCommands[newCommands.length - 1] = command;
		desc.setBuildSpec(newCommands);
		
		int oldNatureNumber = desc.getNatureIds().length;
		String[] natures = new String[oldNatureNumber + 1];
		System.arraycopy(desc.getNatureIds(), 0, natures, 0, oldNatureNumber);
		natures[natures.length - 1] = NATURE_ID;
		desc.setNatureIds(natures);
		project.setDescription(desc, null); // write to .project file
	}
	/**
	 * @throws CoreException the exception
	 */
	private void removeBuilder() throws CoreException {
		if (project != null) {
			try {
				final IProjectDescription description = project.getDescription();
				
				final List<ICommand> commands = new ArrayList<ICommand>();
				commands.addAll(Arrays.asList(description.getBuildSpec()));
				for (final ICommand buildSpec : description.getBuildSpec()) {
					if (BUILDER_TRACE_ID.equals(buildSpec.getBuilderName())) {
						commands.remove(buildSpec);
					}
				}
				description.setBuildSpec(commands.toArray(new ICommand[commands.size()]));
				
				final List<String> natures = new ArrayList<String>();
				natures.addAll(Arrays.asList(description.getNatureIds()));
				natures.remove(NATURE_ID);
				description.setNatureIds(natures.toArray(new String[natures.size()]));
				
				project.setDescription(description, null);
			} catch (final CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "failed to do the job", e));
			}
		}		
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature#getProject()
	 */
	@Override
	public IProject getProject() {
		return project;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
	 */
	@Override
	public void setProject(IProject project) {
		this.project = project;
	}
	
	@Override
	public void configure() throws CoreException {
		if (!hasTraceBuilder()) {
			addBuilder();
		}
	}

	@Override
	public void deconfigure() throws CoreException {
		if (hasTraceBuilder()) {
			removeBuilder();
		}
	}

}
