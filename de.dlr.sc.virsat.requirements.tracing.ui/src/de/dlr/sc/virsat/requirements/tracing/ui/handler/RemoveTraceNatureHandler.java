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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class RemoveTraceNatureHandler extends AbstractHandler implements IHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);		 
		if (selection instanceof TreeSelection) {
			TreeSelection select = (TreeSelection) selection;
			if (select.getFirstElement() instanceof IProject) {
				IProject project = (IProject) select.getFirstElement();
				try {
					removeProjectNature(project, TraceNature.NATURE_ID);
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not remove the trace nature form the project", e));
				}
			}
		}
		return null;
	}

	/**
	 * @param project the project
	 * @param nature removes the traceability nature
	 * @throws CoreException the exception
	 */
	private void removeProjectNature(IProject project, String nature) throws CoreException {
		if (project != null && nature != null) {
			if (project.hasNature(nature)) {
				IProjectDescription desc = project.getDescription();
				String[] oldNatures = desc.getNatureIds();	
				List<String> list = new ArrayList<String>(Arrays.asList(oldNatures));
				list.remove(nature);
				String[] newNatures = list.toArray(new String[0]);
				desc.setNatureIds(newNatures);
				project.setDescription(desc, null);
			}
		}
	}
}