/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources.command;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.resource.Resource;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * This command takes care of creating a resource for a SEI. It also makes sure
 * that the underlying file is well created. It also supports UNDO and REDO and wraps
 * all workspace operations into runnables to synchronize with builders etc.
 * @author fisc_ph
 *
 */
public class CreateSeiResourceAndFileCommand extends AbstractCommand {

	private VirSatResourceSet rs;
	private StructuralElementInstance sei;
	
	/**
	 * Constructor for the command that will create the needed file structure for the SEI
	 * using its UUID and will create a resource in the resourceSet as well
	 * @param rs The resourceSet in which the created resource should be place
	 * @param sei The SEI for which the resource and file structure should be created
	 */
	public CreateSeiResourceAndFileCommand(VirSatResourceSet rs, StructuralElementInstance sei) {
		super("Create Resource and File for SEI");
		this.rs = rs;
		this.sei = sei;
	}
	
	@Override
	public void execute() {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				Resource createdResource = rs.getStructuralElementInstanceResource(sei);
				createdResource.getContents().add(sei);
				VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(sei);
				editingDomain.getVirSatCommandStack().triggerSaveAll();
			}
		};
		
		try {
			ResourcesPlugin.getWorkspace().run(runnable, null, IWorkspace.AVOID_UPDATE, null);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Cannot create File Structure for SEI and add it to ResourceSet", e));
		}
	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public boolean canExecute() {
		return true;
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(sei);
				editingDomain.getVirSatCommandStack().triggerSaveAll();
				rs.removeStructuralElementInstanceResource(sei);
			}
		};
		
		try {
			ResourcesPlugin.getWorkspace().run(runnable, null, IWorkspace.AVOID_UPDATE, null);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Cannot remove File Structure for SEI and remove it from ResourceSet", e));
		}
	}
}
