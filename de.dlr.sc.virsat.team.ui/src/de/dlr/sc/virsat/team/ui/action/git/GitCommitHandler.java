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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.egit.ui.internal.credentials.EGitCredentialsProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;
import de.dlr.sc.virsat.team.ui.dialog.CommitMessageDialog;

public class GitCommitHandler extends AbstractHandler {

	@SuppressWarnings("restriction")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		CommitMessageDialog commitMessageDialog = new CommitMessageDialog(Display.getDefault().getActiveShell(),
				"Commit Message", "Please enter a commit message describing your changes", "");

		if (commitMessageDialog.open() != Window.OK) {
			// Commit canceled
			return null;
		}

		IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event);

		Set<IProject> selectedProjects = new HashSet<>();
		List<IStatus> status = new ArrayList<>();
		
		for (Object object : selection.toList()) {
			if (object instanceof EObject) {
				VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd((EObject) object);
				ed.saveAll();
				IProject project = ed.getResourceSet().getProject();
				selectedProjects.add(project);
			}
		}
		
		IVirSatVersionControlBackend gitBackend = new VirSatGitVersionControlBackend(new EGitCredentialsProvider());
		
		Job job = new Job("Virtual Satellite Git Commit And Push") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				SubMonitor subMonitor = SubMonitor.convert(monitor, selectedProjects.size());
				for (IProject project : selectedProjects) {
					VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
					try {
						ed.writeExclusive(() -> {
							ed.getCommandStack().flush();
							try {
								gitBackend.commit(project, commitMessageDialog.getCommitMessage(), subMonitor.split(1));
							} catch (Exception e) {
								status.add(new Status(Status.ERROR, "id", "Error during Commit", e));
							}
						});
					} catch (InterruptedException e) {
						status.add(new Status(Status.ERROR, "id", "Transaction interruption during commit", e));
					}
				}
				return Status.OK_STATUS;
			}
		};
		
		job.setUser(true);
		job.schedule();

		if (!status.isEmpty()) {
			MultiStatus multiStatus = new MultiStatus("id", Status.ERROR, status.toArray(new Status[] {}), "Errors during commit", status.get(0).getException());
			ErrorDialog.openError(Display.getCurrent().getActiveShell(), "Commit Error", "Error during commit", multiStatus);
		}
		
		return null;
	}

}
