/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.action.git;

import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.egit.ui.internal.pull.PullOperationUI;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * This class performs a git pull.
 */
@SuppressWarnings("restriction")
public class GitUpdateAction extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection eventSelection = HandlerUtil.getCurrentSelection(event);
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(eventSelection);
		IProject selectedProject = selectionHelper.getProjectResource();
		
		VirSatTransactionalEditingDomain ed = selectionHelper.getEditingDomain();
		// clean our command stack for further operations
		if (ed != null) {
			ed.getCommandStack().flush();
		}
		
		Repository gitRepository = RepositoryMapping.getMapping(selectedProject).getRepository();
    	
		PullOperationUI pull = new PullOperationUI(Collections.singleton(gitRepository)); 
		pull.start();
		
		return null;
	}
}
