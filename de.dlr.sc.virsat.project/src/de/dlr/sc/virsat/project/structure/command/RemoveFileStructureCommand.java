/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure.command;

import java.util.function.Function;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.core.commands.operations.AbstractOperation;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.util.command.IVirSatRecordableCommand;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * This class wraps the IDE Workspace Operation of Deleting the folder of a SEI
 * By this the deletion is undoable restoring the full content including files
 * in the SEIs document folder
 *
 */
public class RemoveFileStructureCommand extends AbstractCommand implements IVirSatRecordableCommand {

	private VirSatProjectCommons projectCommons;
	
	private AbstractOperation deleteResourceOperation;
	
	private VirSatTransactionalEditingDomain editingDomain;
	
	/**
	 * the public constructor
	 * @param selectedProject the selected project
	 * @param iste {@link IStructuralTreeElement}
	 */
	public RemoveFileStructureCommand(IProject selectedProject, StructuralElementInstance iste, Function<IFolder,  ? extends AbstractOperation> deleteResourcesOperationFunction) {
		projectCommons = new VirSatProjectCommons(selectedProject);
		IFolder seiFolder = projectCommons.getStructuralElemntInstanceFolder(iste);
		this.deleteResourceOperation = deleteResourcesOperationFunction.apply(seiFolder);
//		dro = new DeleteResourcesOperation(new IResource[] {seiFolder}, "Deleting SEI folder", true) {
//			@Override
//			protected ISchedulingRule getExecuteSchedulingRule() {
//				return selectedProject;
//			}
//			
//			@Override
//			protected ISchedulingRule getUndoSchedulingRule() {
//				return selectedProject;
//			}
//			
//			@Override
//			protected ISchedulingRule getRedoSchedulingRule() {
//				return selectedProject;
//			}
//		};
		editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(iste);
	}
	
	@Override
	public void execute() {
		try {
			editingDomain.runExclusive(() -> {
				try {
					deleteResourceOperation.execute(null, null);
					editingDomain.getVirSatCommandStack().triggerSaveAll();
				} catch (ExecutionException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to Delete SEI folder structure!", e));
				}
			});
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to Delete SEI folder structure!", e));
		}
	}

	@Override
	public void redo() {
		try {
			editingDomain.runExclusive(() -> {
				try {
					deleteResourceOperation.redo(null, null);
					editingDomain.getVirSatCommandStack().triggerSaveAll();
				} catch (ExecutionException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to Redo Delete of SEI folder structure!", e));
				}
			});
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to Redo Delete of SEI folder structure!", e));
		}
	}
	
	@Override
	public void undo() {
		try {
			editingDomain.runExclusive(() -> {
				try {
					deleteResourceOperation.undo(null,  null);
					editingDomain.getVirSatCommandStack().triggerSaveAll();
				} catch (ExecutionException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to Undo Delete of SEI folder structure!", e));
				}
			});
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to Undo Delete of SEI folder structure!", e));
		}
	}
	
	@Override
	public boolean canUndo() {
		return deleteResourceOperation.canUndo();
	}
	
	@Override
	public boolean canExecute() {
		return deleteResourceOperation.canExecute();
	}
}
