/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.requirements.tracing.builder.TraceNature;
import de.dlr.sc.virsat.requirements.tracing.ui.Activator;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public class AddTraceNatureHandler extends AbstractHandler implements IHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);		 
		if (selection instanceof TreeSelection) {
			TreeSelection select = (TreeSelection) selection;
			if (select.getFirstElement() instanceof IProject) {
				IProject project = (IProject) select.getFirstElement();
				try {
					addProjectNature(project, TraceNature.NATURE_ID);
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not add trace nature to project", e));
				}
			}
		}
		return null;
	}

	/**
	 * @param project the project
	 * @param nature nature to add
	 * @throws CoreException the exception
	 */
	public static void addProjectNature(IProject project, String nature) throws CoreException {
		if (project != null && nature != null) {
			if (!project.hasNature(nature)) {
				IProjectDescription desc = project.getDescription();

				String[] oldNatures = desc.getNatureIds();
				String[] newNatures = new String[oldNatures.length + 1];
				newNatures[0] = nature;
				if (oldNatures.length > 0) {
					System.arraycopy(oldNatures, 0, newNatures, 1, oldNatures.length);
				}
				desc.setNatureIds(newNatures);
				project.setDescription(desc, null);
			}
		}
	}
}
