/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.actionProvider;

import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

import de.dlr.sc.virsat.project.editingDomain.IResourceEventListener;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.editingDomain.action.VirSatRedoAction;
import de.dlr.sc.virsat.project.ui.editingDomain.action.VirSatUndoAction;

/**
 * The Action provider from the navigator to correctly set the action handlers
 * for the UNDO and REDO operations / commands. Setting them via the command framework
 * does not yet correctly work. This action provider takes care of updating the actions
 * in case of changes being detected from one of the VirSat command stacks or from
 * the selection service
 * @author fisc_ph
 *
 */
public class VirSatNavigatorActionProvider extends CommonActionProvider implements ISelectionListener, IResourceEventListener {
	
	/**
	 * Constructor of the Action Provider that asks for the current selectionService and
	 * hands it over to the commands. It also places two listeners to detect changes on command
	 * stacks and EMF resources as well as selections
	 */
	public VirSatNavigatorActionProvider() {
		super();
		selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		selectionService.addPostSelectionListener(this);
		VirSatTransactionalEditingDomain.addResourceEventListener(this);
	}

	private ISelectionService selectionService;
	private VirSatUndoAction undoAction;
	private VirSatRedoAction redoAction;
	
	@Override
	public void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
	    actionBars.updateActionBars();
	}
	
	/**
	 * This method triggers the update method of the actions
	 * to correctly set their enabled state and texts once a change
	 * to a command stack or a selection is detected
	 */
	protected void updateActions() {
		undoAction.update();
		redoAction.update();
	}
	
	
	@Override
	public void init(ICommonActionExtensionSite aSite) {
		super.init(aSite);
		// Set the ReDo and UnDo action
		undoAction = new VirSatUndoAction();
		redoAction = new VirSatRedoAction();
	}
	
	@Override
	public void dispose() {
		VirSatTransactionalEditingDomain.removeResourceEventListener(this);
		super.dispose();
	}

	@Override
	public void resourceEvent(Set<Resource> resources, int event) {
		Display.getDefault().asyncExec(() -> updateActions());
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		updateActions();		
	}
}
