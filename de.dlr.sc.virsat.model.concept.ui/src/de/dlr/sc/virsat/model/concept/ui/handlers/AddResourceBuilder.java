/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.ui.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.model.concept.Activator;
import de.dlr.sc.virsat.model.concept.builder.resources.ResourceAccessBuilder;
import de.dlr.sc.virsat.model.concept.ui.internal.ConceptActivator;

/**
 * This handler enables resource generator
 * @author bell_er
 */
public class AddResourceBuilder extends AbstractHandler implements IHandler {

	@Override
	public Object execute(final ExecutionEvent event) {
		final IProject project = getProject(event);

		if (project != null) {
			try {
				// verify already registered builders
				if (hasBuilder(project)) {
					// already enabled
					return null;
				}

				// add builder to project properties
				IProjectDescription description = project.getDescription();
				final ICommand buildCommand = description.newCommand();
				buildCommand.setBuilderName(ResourceAccessBuilder.BUILDER_ID);

				final List<ICommand> commands = new ArrayList<ICommand>();
				commands.addAll(Arrays.asList(description.getBuildSpec()));
				commands.add(buildCommand);

				description.setBuildSpec(commands.toArray(new ICommand[commands.size()]));
				project.setDescription(description, null);

			} catch (final CoreException e) {
				ConceptActivator.getInstance().getLog().log(
						new Status(Status.ERROR, Activator.getPluginId(), Status.OK, "ResourceAccessBuilder: Cannot add the builder", null));

			}
		}

		return null;
	}
	/**
	 * Gets the project from the executionEvent
	 * @param event the execution event
	 * @return the project
	 * @author bell_er
	 */
	public static IProject getProject(final ExecutionEvent event) {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			final Object element = ((IStructuredSelection) selection).getFirstElement();

			return (IProject) Platform.getAdapterManager().getAdapter(element, IProject.class);
		}

		return null;
	}
	/**
	 * Checks if the project has the builder
	 * @param project the project
	 * @return if it has true  else false
	 * @author bell_er
	 */
	public static final boolean hasBuilder(final IProject project) {
		try {
			for (final ICommand buildSpec : project.getDescription().getBuildSpec()) {
				if (ResourceAccessBuilder.BUILDER_ID.equals(buildSpec.getBuilderName())) {
					return true;
				}
			}
		} catch (final CoreException e) {
		}

		return false;
	}

}
