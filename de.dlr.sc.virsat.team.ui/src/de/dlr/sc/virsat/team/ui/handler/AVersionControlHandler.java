/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.handler;

import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;

/**
 * Abstract class to handle preparatory actions for perfoming
 * a version control action.
 *
 */
public abstract class AVersionControlHandler extends AbstractHandler {

	protected Set<IProject> selectedProjects;
	protected IVirSatVersionControlBackend backend;
	
	/**
	 * Creates the version control backend
	 * @return
	 */
	protected abstract IVirSatVersionControlBackend createVersionControlBackend();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event);		
		
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
		selectedProjects = selectionHelper.getAllProjectResouces();
		
		for (IProject project : selectedProjects) {
			VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
			ed.saveAll();
		}
		
		backend = createVersionControlBackend();
		return null;
	}

}
