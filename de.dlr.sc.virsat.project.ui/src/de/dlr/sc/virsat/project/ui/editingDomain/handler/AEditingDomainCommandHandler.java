/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.editingDomain.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * A generic class for handlers dealing with the TransactionalEditing Domain
 * It will ask the selection to lead to the correct Editing domain.
 * You should override the code that handles the execution on the ED.
 * @author fisc_ph
 *
 */
public abstract class AEditingDomainCommandHandler extends AbstractHandler {

	protected VirSatTransactionalEditingDomain ed;
	protected VirSatSelectionHelper selectionHelper;
	protected EObject firstSelectedEObject;
	protected IProject project;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		initializeFieldsFromSelection(HandlerUtil.getCurrentSelection(event));
		execute();
		
		return null;
	}

	/**
	 * Initialize selection helper, project, editing domain, first selected object from given selection
	 * @param selection 
	 */
	protected void initializeFieldsFromSelection(ISelection selection) {
		selectionHelper = new VirSatSelectionHelper(selection);
		project = selectionHelper.getProjectResource();
		firstSelectedEObject = selectionHelper.getFirstEObject();

		if (firstSelectedEObject != null) {
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd(firstSelectedEObject);
		} else if (project != null) {
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		}
	}
	
	/**
	 * Abstract execute command that will be called by the handler after setting up the correct Editing domain
	 */
	protected abstract void execute();
}
