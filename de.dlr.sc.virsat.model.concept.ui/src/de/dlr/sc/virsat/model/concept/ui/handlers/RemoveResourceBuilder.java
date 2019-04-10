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
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.model.concept.Activator;
import de.dlr.sc.virsat.model.concept.builder.resources.ResourceAccessBuilder;
import de.dlr.sc.virsat.model.concept.ui.internal.ConceptActivator;
/**
 * This handler disables resource generator
 * @author bell_er
 */
public class RemoveResourceBuilder extends AbstractHandler implements IHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final IProject project = AddResourceBuilder.getProject(event);

		if (project != null) {
			try {
				final IProjectDescription description = project.getDescription();
				final List<ICommand> commands = new ArrayList<ICommand>();
				commands.addAll(Arrays.asList(description.getBuildSpec()));

				for (final ICommand buildSpec : description.getBuildSpec()) {
					if (ResourceAccessBuilder.BUILDER_ID.equals(buildSpec.getBuilderName())) {
						// remove builder
						commands.remove(buildSpec);
					}
				}

				description.setBuildSpec(commands.toArray(new ICommand[commands.size()]));
				project.setDescription(description, null);
			} catch (final CoreException e) {
				ConceptActivator.getInstance().getLog().log(
						new Status(Status.ERROR, Activator.getPluginId(), Status.OK, "ResourceAccessBuilder: Cannot remove the builder", null));

			}
		}

		return null;
	}
}
