/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.action.svn;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.mapping.SimpleResourceMapping;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.team.svn.core.IStateFilter;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.ui.action.local.UpdateAction;
import org.eclipse.team.svn.ui.utility.UnacceptableOperationNotificator;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * a class to define the update actions
 * @author scha_vo
 *
 */
@SuppressWarnings("restriction")
public class SVNUpdateAction extends UpdateAction {

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, newStructuredSelection);
	}

	IStructuredSelection newStructuredSelection;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection eventSelection = HandlerUtil.getCurrentSelection(event);
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(eventSelection);
		IProject selectedProject = selectionHelper.getProjectResource();
		VirSatTransactionalEditingDomain ed = selectionHelper.getEditingDomain();

		try {
			ResourcesPlugin.getWorkspace().run((monitor) -> {
				// clean our command stack for further operations
				if (ed != null) {
					ed.getCommandStack().flush();
				}
				
				// Call the proper SVN commandHandler/action from Subversive
				if (selectedProject != null) {
					newStructuredSelection = new StructuredSelection(new SimpleResourceMapping(selectedProject));
					selectionChanged(null, newStructuredSelection);
					checkSelection(newStructuredSelection);
				}
				
				try {
					super.execute(event);
				} catch (Exception e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to execute call to SVN in Custom Update! " + e.getMessage()));
				}
			}, ResourcesPlugin.getWorkspace().getRoot(), IWorkspace.AVOID_UPDATE, null);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to execute Custom Update! " + e.getMessage()));
		}
		
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void runImpl(IAction action) {
		IResource []resources = UnacceptableOperationNotificator.shrinkResourcesWithNotOnRespositoryParents(this.getShell(), this.getSelectedResources(IStateFilter.SF_ONREPOSITORY));
		if (resources == null || resources.length == 0) {
			return;
		}
		
		if (this.checkForResourcesPresenceRecursive(IStateFilter.SF_REVERTABLE)) {
			IResource []missing = this.getSelectedResourcesRecursive(UpdateAction.SF_MISSING_RESOURCES);
			if (missing.length > 0 && !UpdateAction.updateMissing(this.getShell(), missing)) {
				return;
			}
		}
		
		this.runScheduled(UpdateAction.getUpdateOperation(resources, SVNRevision.HEAD));
	}
}

