/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.CutToClipboardCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;

import de.dlr.sc.virsat.commons.exception.AtomicExceptionReference;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.dvlm.util.command.DVLMCopierCommand;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatCopyToClipboardCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatCutToClipboardCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatPasteFromClipboardCommand;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.resources.VirSatResourceSetUtil;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.structure.AVirSatProjectResourceChangeListener;

/**
 * The Transactional Editing Domain with attached WorkspaceSynchronizer
 */
public class VirSatTransactionalEditingDomain extends TransactionalEditingDomainImpl implements IUserContext {

	private boolean isDisposed;
	
	/**
	 * Standard constructor that creates a VirSatTransActionalEditingDomain
	 * @param composedAdapterFactory AdapterFactory or EMF Edit Provider
	 * @param commandStack the commandStack to use for this editing domain
	 */
	public VirSatTransactionalEditingDomain(ComposedAdapterFactory composedAdapterFactory, VirSatWorkspaceCommandStack commandStack) {
		super(composedAdapterFactory, commandStack);
		initialize();
	}

	/**
	 * Standard constructor that creates a VirSatTransActionalEditingDomain
	 * @param composedAdapterFactory AdapterFactory or EMF Edit Provider
	 * @param commandStack the commandStack to use for this editing domain
	 * @param rset The ResourceSet to be used with the given TransactionalEditingDomain
	 */
	public VirSatTransactionalEditingDomain(ComposedAdapterFactory composedAdapterFactory, VirSatWorkspaceCommandStack commandStack, ResourceSet rset) {
		super(composedAdapterFactory, commandStack, rset);
		if (rset instanceof VirSatResourceSet) {
			this.virSatResourceSet = (VirSatResourceSet) rset;
		}
		initialize();
	}
	
	/**
	 * Method to place common initialization for constructors
	 */
	private void initialize() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Intializing"));
		// Make sure we are correctly handling resource changes from the workspace
		//ResourcesPlugin.getWorkspace().addResourceChangeListener(new VirSatDebugResourceChangeListener());
		ResourcesPlugin.getWorkspace().addResourceChangeListener(initWorkSpaceChangeListener());
		
		// Attach the listener so that we see which resources are affected by commands
		// The information is needed to set the dirty flags for the resources
		((IWorkspaceCommandStack) commandStack).getOperationHistory().addOperationHistoryListener(historyListener);
		getVirSatCommandStack().setEditingDomain(this);
		
		isDisposed = false;
	}
	
	private VirSatResourceSet virSatResourceSet;
	protected Set<URI> recentlyChangedResource = Collections.synchronizedSet(new HashSet<URI>());
	private Map<Resource, Boolean> isResourceDirty = new ConcurrentHashMap<Resource, Boolean>();

	/**
	 * a little helper method to print the recently saved resource to the console 
	 * 
	 */
	private void printRecentlyChangedResources() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
				
		int counter = 1;
		ps.println();
		ps.println("Currently loaded resources: ----------");
		for (Resource loadedResource : getResourceSet().getResources()) {
			ps.println("Currently loaded resource <" + counter++ + "> : " + loadedResource.getURI() + " - " + loadedResource);
		}
		counter = 1;
		ps.println("Recently Changed resources: ----------");
		for (URI uri : recentlyChangedResource) {
			ps.println("Recently Changed Resource <" + counter++ + "> : " + uri + " - " + uri);
		}
		
		try {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Recently saved Resources: " + os.toString("UTF8")));
		} catch (UnsupportedEncodingException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Recently saved Resources Failed: ", e));
		}
	}
	
	protected VirSatEditingDomainResourceChangeListsner workspaceChangeListener; 

	/**
	 * Stops the workspace change listener.
	 * Useful for unit testing operations that modify resources without 
	 * saving / deleting directly over the transactional editing domain. 
	 */
	public void stopWorkspaceChangeListener() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceChangeListener);
	}

	/**
	 * Specialization of the VirSatProjectResurceCjhangeListener to manage loading and reloading of
	 * VirtualSatellite related Model data and files. This ChangeListsner tries to identify in particular,
	 * if the workspace change is due to a file which has been saved actively from virtual satellite
	 * or if it is due to a change of the file from some VirSat domain external editor. 
	 */
	protected class VirSatEditingDomainResourceChangeListsner extends AVirSatProjectResourceChangeListener {
		
		public VirSatEditingDomainResourceChangeListsner(IProject virSatProject) {
			super(virSatProject);
		}

		protected boolean triggerFullReload;
		
		@Override
		public void handlePreCondition() {
			triggerFullReload = false;
		}
		
		@Override
		public void handlePostCondition() {
			if (triggerFullReload) {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Execute Full Reload"));
				VirSatTransactionalEditingDomain.this.reloadAll();
			}
		}
		
		@Override
		public void handleChangedDvlmResources(List<IResource> changedDvlmResources) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Synchronizer handling changed resources"));
			synchronized (recentlyChangedResource) {
				printRecentlyChangedResources();
				changedDvlmResources.forEach((wsDvlmResource) -> {
					// A resource which has been marked ad changed is processed now and the mark
					// should be removed again to detect external changes if they happen on a DVLM file.
					updateTriggerFullReload(wsDvlmResource, true);
				});
			}
		}

		@Override
		public void handleRemovedDvlmResources(List<IResource> removedDvlmResources) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Synchronizer handling removed resources"));
			Set<Resource> removedEmfResources =  new HashSet<>();
			synchronized (recentlyChangedResource) {
				printRecentlyChangedResources();
				removedDvlmResources.forEach((wsDvlmResource) -> {
					updateTriggerFullReload(wsDvlmResource, true);
					
					URI changedResourceUri = URI.createPlatformResourceURI(wsDvlmResource.getFullPath().toString(), true);
					Resource emfResource = virSatResourceSet.getResource(changedResourceUri, false);
					removedEmfResources.add(emfResource);
				});
			}
			
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Handle removed Resources (" + removedEmfResources + ")"));
			removedEmfResources.forEach((emfResource) -> {
				VirSatTransactionalEditingDomain.this.removeResource(emfResource);
			});
		}
		
		@Override
		public void handleAddedDvlmResources(List<IResource> addedDvlmResources) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Synchronizer handling added resources"));
			synchronized (recentlyChangedResource) {
				printRecentlyChangedResources();
				addedDvlmResources.forEach((wsDvlmResource) -> {
					// If a DVLM file is added it should not be taken from the list of recently saved resources.
					// There are two reasons for it: First logically, how can a DVLM file be added if it is marked as changed. 
					// Which means if it is marked as changed it cannot be added thus it should not be removed from the list of
					// recently saved resources. Second and more importantly: When VirSat creates a new SEI for example, the
					// workspace recognizes two changes: An ADD and a CHANGE on the same file. This usually triggers this listener
					// two times resulting in two Workspace Synchronize operations. One for the add and one for the change. Together
					// with the save  and create commands/operations it usually creates the following order of changes to the currently
					// changed resources: a marking of changed resource when creating the SEI, the ADD change removing it, the save adding
					// it to the list of changes again and finally the CHANGE removing it again. In rare cases this order is changed: first
					// marking the resource as changed, then processing the ADD and directly processing the CHANGE. This resulted in two times
					// trying to take the resource from the list of recently changed files and thus creating a full reload which is not needed.
					updateTriggerFullReload(wsDvlmResource, false);
				});
			}
		}
		
		/**
		 * Updates the flag for triggering a full reload if there has been an external change
		 * @param wsResource the resource that has been modified
		 */
		protected void updateTriggerFullReload(IResource wsResource, boolean removeFromRecentlySavedResources) {
			URI changedResourceUri = URI.createPlatformResourceURI(wsResource.getFullPath().toString(), true);
			boolean isInResourceSet = getResourceSet().getResource(wsResource, false) != null;
			if (isInResourceSet) {
				// First check if the resource which is changed is not on the list of
				// recently resources than trigger a full reload for all resources.
				if (!recentlyChangedResource.contains(changedResourceUri)) {
					Activator.getDefault().getLog().
						log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: (" + changedResourceUri.toPlatformString(true) + ") not in recently saved resources. Triggering for a full relaod."));
					triggerFullReload = true;
				} 
				
				// Now remove the file from the recently saved resources if requested
				if (removeFromRecentlySavedResources) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: (" + changedResourceUri.toPlatformString(true) + ") removed from list of rcently saved resources."));
					recentlyChangedResource.remove(changedResourceUri);
				}
			}
		}
	};
	
	/**
	 * This method implements the resource change listener to detect external vs. internal and expected changes of resources
	 * @return a WorkspaceListener, method should always hand back the same instance
	 */
	private AVirSatProjectResourceChangeListener initWorkSpaceChangeListener() {
		if (workspaceChangeListener == null) {
			workspaceChangeListener = new VirSatEditingDomainResourceChangeListsner(getResourceSet().getProject());
		}
		return workspaceChangeListener;
	}
	
	/**
	 * this method saves all the resources in the {@link VirSatResourceSet}
	 */
	public void saveAll() {
		saveAll(false, false);
	}
	
	/**
	 * This method is the counter part to the run exclusive method from the transactional
	 * editing domain. This call grants exclusive write access to the editing domain. 
	 * 
	 * @param readWriteRunner a runnable provided with read and write privileges
	 * 
	 * @return the result of the read operation if it is a
	 *    {@link RunnableWithResult} and the transaction did not roll back;
	 *    <code>null</code>, otherwise
	 *    
	 * @throws InterruptedException if the current thread is interrupted while
	 *    waiting for access to the resource set
	 */
	public Object writeExclusive(Runnable readWriteRunnable) throws InterruptedException {
		return runExclusiveInWorkspace(readWriteRunnable, true);
	}
	
	/**
	 * This method is the counter is the actual implementation for locking
	 * a write transaction in the editing domain
	 * 
	 * @param readWriteRunner a runnable provided with read and write privileges
	 * 
	 * @return the result of the read operation if it is a
	 *    {@link RunnableWithResult} and the transaction did not roll back;
	 *    <code>null</code>, otherwise
	 *    
	 * @throws InterruptedException if the current thread is interrupted while
	 *    waiting for access to the resource set
	 */
	protected Object doWriteExclusive(Runnable readWriteRunnable) throws InterruptedException {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Starting an exclusive write transaction"));

		// get the active transaction and check if it is reusable for us
		Transaction activeTransaction = getActiveTransaction();
		Transaction writeTransaction = null;
		
		// If there is no transaction -> create one
		// If there is a transaction, and it is of a different thread -> create a new one
		// If there is a transaction, and it is readOnly -> create a new one
		// If there is a transaction, but it is not active -> create a new one
		// Simply speaking: Create a new transaction if there is none, or if it is not reusable.
		if ((activeTransaction == null)
			|| !(activeTransaction.isActive() 
				&& !activeTransaction.isReadOnly()
				&& (activeTransaction.getOwner() == Thread.currentThread()))) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Creating a new write transaction"));
			writeTransaction = startTransaction(false, null);
		}
		
		// Create a cast in case it is a runnable with result
		final RunnableWithResult<?> rwr = (readWriteRunnable instanceof RunnableWithResult) ? (RunnableWithResult<?>) readWriteRunnable : null;
		
		try {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Executing the runnable"));
			readWriteRunnable.run();
		} finally {
			if ((writeTransaction != null) && (writeTransaction.isActive())) {
				try {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Commiting the write Transaction"));
					writeTransaction.commit();
					
					if (rwr != null) {
						rwr.setStatus(Status.OK_STATUS);
					}
				} catch (RollbackException e) {
					Activator.getDefault().getLog().log(new Status(
						Status.WARNING,
						Activator.getPluginId(),
						"VirSatTransactionalEditingDomain: Detected Rollback on exclusive Write transaction: " + e.getMessage())
					);
					if (rwr != null) {
						rwr.setStatus(e.getStatus());
					}
				}
			}
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Finished an exclusive write transaction"));
		// Hand back the result of the runnable with result in case it exists, otherwise null
		return (rwr != null) ? rwr.getResult() : null;
	}
	
	/**
	 * this method saves all the resources in the {@link VirSatResourceSet}
	 * @param supressRemoveDanglingReferences set to true to make the virsat editing domain not remove dangling references during the save.
	 * This is needed for the builders which should not incur any additional changes during the save or they will trigger themselves.
	 * @param dvlmResourcesOnly if true only saves DVLM resources; if you want to save ALL resources make sure external changes are loaded as well
	 */
	public void saveAll(boolean supressRemoveDanglingReferences, boolean dvlmResourcesOnly) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Try saving all resources"));
		
		// First lock the workspace then lock the transaction
		try {
			this.writeExclusive(() -> {
				List<Resource> resources = new ArrayList<Resource>(virSatResourceSet.getResources());
				for (Resource resource : resources) {
					boolean fileIsDVLMResource = VirSatProjectCommons.FILENAME_EXTENSION.equalsIgnoreCase(resource.getURI().fileExtension());
					if (fileIsDVLMResource || !dvlmResourcesOnly) {
						saveResource(resource, supressRemoveDanglingReferences);
						virSatResourceSet.updateDiagnostic(resource);
						virSatResourceSet.notifyDiagnosticListeners(resource);
					}
				}
				
				maintainDirtyResources();
			});
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(
				Status.WARNING,
				Activator.getPluginId(),
				"VirSatTransactionalEditingDomain: failed samving all resources: " + e.getMessage())
			);
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Finished try saving all resources"));
	}
	
	/**
	 * Method to remove a resource through this editing domain
	 * it takes care of setting the dirty state correctly and removing the resource from the resourceset
	 * @param emfResource the resource to be removed
	 */
	public void removeResource(Resource emfResource) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: About to unload a resource"));
		try {
			runExclusive(() -> {
				if (emfResource != null) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: ActuallyUnloaded Resource URI (" + emfResource.getURI().toPlatformString(true) + ")"));
					virSatResourceSet.removeResource(emfResource);
					// If the resource has been removed we don't need to monitor its dirty state anymore
					isResourceDirty.remove(emfResource);
					fireNotifyResourceEvent(Collections.singleton(emfResource), VirSatTransactionalEditingDomain.EVENT_UNLOAD);
				}
			});
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Thread got interrupted"));
		}
	}
	
	/**
	 * Call this method to reload all resources and notify all listeners
	 */
	public void reloadAll() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Started reloading all resources"));

		try {
			runExclusive(() -> {
				// Make sure that no Change Events are fired while a resource is reloaded
				synchronized (accumulatedResourceChangeEvents) {
					// Clear all Accumulated Resource Change Events because at the end of this method
					// we will notify about all resources being reloaded.
					clearAccumulatedRecourceChangeEvents();
					
					// take the lock on recently changed resources. So that all resources will be reloaded
					// in one go. No one should interfere at this point.
					synchronized (recentlyChangedResource) {
						// In case that a resource is properly unloaded 
						// The command stack should be flushed and the Clipboard
						// should be brought back into a clean state
						VirSatEditingDomainClipBoard.INSTANCE.flushClipboard(this);
						VirSatTransactionalEditingDomain.this.getCommandStack().flush();

						// Now reload all resources and make sure that all of them are marked as unchanged.
						virSatResourceSet.realoadAll();
						recentlyChangedResource.clear();
						
						// After performing a reload all there are no more dirty resources
						isResourceDirty.clear();
					
						// Now start notifying everyone about the change of resources
						List<Resource> reloadedResources = virSatResourceSet.getResources();
						fireNotifyResourceEvent(new HashSet<>(reloadedResources), VirSatTransactionalEditingDomain.EVENT_RELOAD);
					}
				}
			});
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Execution got interrupted"));
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Finished reloading all resources"));
	}

	/**
	 * Use this method to make the editing domain aware of that the resource has been saved
	 * The Editing Domain remembers it and prevents a direct update coming from the workspace resources 
	 * @param resource The resource to be saved
	 */
	protected void saveResource(Resource resource) {
		saveResource(resource, false);
	}
	
	/**
	 * Use this method to make the editing domain aware of that the resource has been saved
	 * The Editing Domain remembers it and prevents a direct update coming from the workspace resources 
	 * @param resource The resource to be saved
	 * @param supressRemoveDanglingReferences set to true in case the resource is should not be cleared of dangling references before the save
	 */
	protected void saveResource(Resource resource, boolean supressRemoveDanglingReferences) {
		if (resource != null) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Try saving resource (" + resource.getURI().toPlatformString(true) + ")"));
			
			if (!resource.isLoaded() || resource.getContents().isEmpty()) {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: aborted saving (" + resource.getURI().toPlatformString(true) + ") because it is already unloaded "));
				return;
			}
			
			// Remove dangling references only if this the user has write access to this resource
			boolean writeRemovedDanglingReferences = !supressRemoveDanglingReferences && virSatResourceSet.hasWritePermission(resource, this) && VirSatProjectCommons.isDvlmFile(resource); 
			
			// for dangling references call the Utils to remove them before actually saving them
			if (writeRemovedDanglingReferences) {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Saving resource (" + resource.getURI().toPlatformString(true) + ") removing dangling references"));
				
				RecordingCommand recCmd = new RecordingCommand(this) {
					@Override
					protected void doExecute() {
						VirSatResourceSetUtil.removeDanglingReferences(resource);						
					}
					
					@Override
					public boolean canUndo() {
						return false;
					}
					
					@Override
					public boolean canRedo() {
						return false;
					}
				};
				this.getVirSatCommandStack().executeNoUndo(recCmd);
			}
			
			// Check the state of the resource an only save it, if it actually has been modified. 
			// If it hasen't it should not be stored otherwise we may confuse our lists, and maps
			// tracking the states of the currently open resources. In particular, if store the resource
			// in recentlySavedResource BUT the resource has not changed then, the WokrspaceSynchronizer will NOT trigger
			// and the resource will stay listed in recentlySavedResource indefinitely.
			if (virSatResourceSet.hasWritePermission(resource, this) && virSatResourceSet.isChanged(resource)) {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Has write permission and found changes thus saving (" + resource.getURI().toPlatformString(true) + ")"));
				internallySaveResource(resource, false);
				fireNotifyResourceEvent(Collections.singleton(resource), VirSatTransactionalEditingDomain.EVENT_CHANGED);
			} else {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Has no write permission or no changes thus not saving (" + resource.getURI().toPlatformString(true) + ")"));
			}
			
			// Mark the resource as not dirty in either case
			isResourceDirty.put(resource, false);
			
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Finished try saving resource (" + resource.getURI().toPlatformString(true) + ")"));
		}
	}
	
	/**
	 * Special method to save a resource without obeying write permissions
	 * @param resource the resource to be saved
	 */
	public void saveResourceIgnorePermissions(Resource resource) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Saving resource ignoring write permissions (" + resource.getURI().toPlatformString(true) + ")"));
		try {
			writeExclusive(() -> internallySaveResource(resource, true));
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Save resource without permission got interrupted)"));
		}
	}
	
	/**
	 * Performs the actual save of an resource, only visible to the outside for the special case of
	 * needing to ignore checks such as rights management. Needed for example when changing the discipline
	 * of a resource since this means giving the rights away.
	 * @param resource the resource to save
	 * @param overrideWritePermissions the flag to give permission to ignore rights management
	 */
	private void internallySaveResource(Resource resource, boolean overrideWritePermissions) {
		// Put it to the list of recently saved resources in case it is not suppressed. This helps the workspaceSynchronizer
		// to decide if reload of the resource is needed or not (means handling external resource changes)
		synchronized (recentlyChangedResource) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Adding to recently changed resource (" + resource.getURI().toPlatformString(true) + ")"));
			recentlyChangedResource.add(resource.getURI());
		}
		
		// Call the VirSatResourceSet so we are sure it uses our correct Save Settings
		virSatResourceSet.saveResource(resource, this, overrideWritePermissions);
	}
	
	/**
	 * Use this method to determine if a resource is dirty or not
	 * @param resource the resource to be checked
	 * @return true in case it is dirty
	 */
	public boolean isDirty(Resource resource) {
		if (resource == null) {
			return false;
		}
		
		Boolean isDirty = isResourceDirty.get(resource); 
		return (isDirty == null) ? false : isDirty; 
	}
	
	/**
	 * Use this method to check if the editing domain has been disposed
	 * @return true iff the editing domain has been disposed
	 */
	public boolean isDisposed() {
		return isDisposed;
	}
	
	/**
	 * Use this method to determine if this transactional editing domain has a resource that is dirty
	 * @return true iff in the case that there is a dirty resource
	 */
	public boolean isDirty() {
		boolean isDirty = isResourceDirty.values().stream().filter(dirty -> dirty).count() > 0;
		return isDirty;
	}
	
	/**
	 * The Operation History Listener which checks for each executed command what happened
	 * It records which resources have been touched and marks them as being dirty
	 */
	private final IOperationHistoryListener historyListener = new IOperationHistoryListener() {
		public void historyNotification(final OperationHistoryEvent event) {
			try {
				Set<Resource> affectedResources = ResourceUndoContext.getAffectedResources(event.getOperation());
				switch (event.getEventType()) {
					case OperationHistoryEvent.UNDONE:
					case OperationHistoryEvent.REDONE:
					case OperationHistoryEvent.DONE:
						synchronized (recentlyChangedResource) {
							// Make sure to synchronize over recentlyChangedResources so when we look at the content in another thread
							// we have all the changed resources inside
							for (Resource changedResource : affectedResources) {
								recentlyChangedResource.add(changedResource.getURI());
							}
						}

						for (Resource changedResource : affectedResources) {
							// We require the resource to be in the resource set since we only consider the EVENT_CHANGED case
							// in this method
							// We may have a performance issue here. Maybe we should identify changes via the command stack.
							boolean isChanged = virSatResourceSet.isChanged(changedResource);
							isResourceDirty.put(changedResource, isChanged);
						}
						
						fireNotifyResourceEvent(affectedResources, VirSatTransactionalEditingDomain.EVENT_CHANGED);
						
						printRecentlyChangedResources();
						
						// Always run all diagnostics, create a copy on the list fo resources to avoid
						// concurrent modification exceptions.
						for (Resource resource : new ArrayList<>(virSatResourceSet.getResources())) {
							if (virSatResourceSet.updateDiagnostic(resource)) {
								virSatResourceSet.notifyDiagnosticListeners(resource);
							}
						}
						
						// Rework the dirty states of the resources
						maintainDirtyResources();
						break;
					default:
				}
			} catch (Exception e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to listen to post command event on the command stack! ", e));
			}
		}
	};
	
	protected IUserContext userContextOverride;
	
	/**
	 * This method filters the map of dirty states of the resources
	 * the method takes care of taking out irrelevant or stale resources from the map
	 * as well as setting unloaded resources to be not dirty.
	 */
	private void maintainDirtyResources() {
		// Keep only the resources in the dirty state map which are actual resources of the ResourceSet
		List<Resource> currentResourcesInResourceSet = new ArrayList<>(resourceSet.getResources());
		Set<Resource> currentResourcesInDirtyMap = isResourceDirty.keySet();
		currentResourcesInDirtyMap.retainAll(currentResourcesInResourceSet);
		
		// And now do a second stage filtering with all unloaded resources which have to be set to not dirty
		// probably we don't need it anymore by our implementation, but this functionality should not create harm
		currentResourcesInDirtyMap.forEach((resource) -> {
			if (!resource.isLoaded()) {
				isResourceDirty.put(resource, false);
			}
		});
	}
	
	public static final int EVENT_LOAD = 0;
	public static final int EVENT_CHANGED = 1;
	public static final int EVENT_UNLOAD = 2;
	public static final int EVENT_RELOAD = 3;
	
	/**
	 * interface Definition for resource listeners
	 * @author scha_vo
	 *
	 */
	public interface IResourceEventListener {
		/**
		 * a method to create a resource event
		 * @param resources the resources on which the event should be generated
		 * @param event the code of the event to be generated
		 */
		void resourceEvent(Set<Resource> resources, int event);
	};
	
	// Central registry for listeners to the Editing domain
	private static List<IResourceEventListener> resourceEventlisteners = Collections.synchronizedList(new LinkedList<>());
	
	private static Set<Resource> accumulatedResourceChangeEvents = new HashSet<>();
	
	/**
	 * Internal method to notify other Software Parts about changes on the Resources.
	 * For example the SaveablesProvider is listening to these notifications
	 * to update the Saveables from the Navigator correctly. Since the Navigator may contain
	 * and display objects from several different resources as well as different projects.
	 * It explains why we have to manage the events from the ED in one central place
	 * @param resources The resources that were change by the firing ED
	 * @param event the actual EVent telling what happened with the Resource
	 */
	private static void fireNotifyResourceEvent(Set<Resource> resources, int event) {
		initResourceChangeEventThread();
		
		if (event == EVENT_CHANGED) {
			synchronized (accumulatedResourceChangeEvents) {
				accumulatedResourceChangeEvents.addAll(resources);
			}
			
			resourceChangeEventThread.resetTimer();
		} else {
			doFireNotifyResourceEvent(resources, event);
		}
	}

	/**
	 * Method to set up the resource Change event thread.
	 * Starts one if it exists, and creates a new instance if the current
	 * one got terminated. This seemed to be a reason for stalling jUnit test cases
	 */
	private static synchronized void initResourceChangeEventThread() {
		if (resourceChangeEventThread == null || resourceChangeEventThread.getState() == Thread.State.TERMINATED) {
			resourceChangeEventThread = new ResourceChangeEventThread();
		}
		if (resourceChangeEventThread.getState() == Thread.State.NEW) {
			resourceChangeEventThread.start();
		}
	}
	
	private static ResourceChangeEventThread resourceChangeEventThread = null; 
	
	/**
	 * Use this method to stop the notification thread for resource event changes.
	 * Should be called only by the Activator.
	 */
	public static void stopResourceChangeEventThread() {
		if (resourceChangeEventThread != null) {
			resourceChangeEventThread.finish();
		}
	}
	
	/**
	 * Use this method to wait until accumulated resource change events have
	 * been fired off by the resourceChangeEventThread.
	 * Intended to be used by test cases.
	 */
	public static void waitForFiringOfAccumulatedResourceChangeEvents() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ResourceChangeEventThread: Waiting for Events to be fired"));
		
		/**
		 *  Define a new WorkspaceJob which can be fired here and waits for all accumulated
		 *  notifications to be fired. The basic idea is, that we cannot be sure about the Workspace
		 *  Job of the WorkspaceChangelsietenr, if it executes, is about to execute etc. Thus it may still
		 *  trigger notifications. This JOb basically ensures, that all other Jobs have been run before
		 *  and will make the calling thread of this method, e.g. a test, wait until all notifications
		 *  and workspace jobs have been scheduled and executed.
		 */
		class WorkspaceAccumulatedNotificationJob extends WorkspaceJob {

			WorkspaceAccumulatedNotificationJob() {
				super("VirSatTransactionalEditingDomain: Waiting for accumulated Notifications");
			}
		
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				synchronized (this) {
					synchronized (accumulatedResourceChangeEvents) {
						while (!accumulatedResourceChangeEvents.isEmpty()) {
							try {
								accumulatedResourceChangeEvents.wait(ResourceChangeEventThread.ACCUMULATION_TIME);
							} catch (InterruptedException e) {
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
									"VirSatTransactionalEditingDomian: Got interuppted while waiting for accumulation of notifications", e));
							}
						}
					}
				}
				return Status.OK_STATUS;
			}
		}
		
		try {
			WorkspaceAccumulatedNotificationJob wsAccumulatedNotificationJob = new WorkspaceAccumulatedNotificationJob();
			wsAccumulatedNotificationJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
			wsAccumulatedNotificationJob.schedule();
			wsAccumulatedNotificationJob.join();
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"VirSatTransactionalEditingDomian: Got interuppted while waiting for accumulation of notifications", e));
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ResourceChangeEventThread: All events fired Queue is empty"));
	}
	
	/**
	 * This class accumulates resource change events and if it does
	 * not receive a timer reset before its timer runs out,
	 * the accumulated notifications will be fired.
	 * @author muel_s8
	 *
	 */
	private static class ResourceChangeEventThread extends Thread {
		
		private static final int SLEEP_TIME = 50;
		private static final int ACCUMULATION_TIME = 250;

		private boolean triggerFinished = false;
		
		private int timer;
		
		/**
		 * Default constructor. Initializes the timer.
		 */
		ResourceChangeEventThread() {
			resetTimer();
		}
		
		/**
		 * Resets the timer
		 */
		public void resetTimer() {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ResourceChangeEventThread: Timer got reset "));
			timer = ACCUMULATION_TIME;
		}
		
		@Override
		public void run() {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ResourceChangeEventThread: Thread started "));
			while (!triggerFinished) {
				try {
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ResourceChangeEventThread: Thread failed to sleep ", e));
				}
				
				if (timer < 0) {
					synchronized (accumulatedResourceChangeEvents) {
						if (!accumulatedResourceChangeEvents.isEmpty()) {
							Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ResourceChangeEventThread: Firing accumulated event changes."));
							doFireNotifyResourceEvent(accumulatedResourceChangeEvents, EVENT_CHANGED);
							clearAccumulatedRecourceChangeEvents();
						}
					}
				} else {
					timer -= SLEEP_TIME;
				}
			}
		}
		
		/**
		 * This will stop the thread gracefully
		 */
		public void finish() {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ResourceChangeEventThread: Thread triggered for stop.... "));
			triggerFinished = true;

			try {
				join();
			} catch (InterruptedException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "ResourceChangeEventThread: Failed waiting until Threads are joined.... "));
			}
		}
	}
	
	/**
	 * This method actually fires the given resource events
	 * @param resources the resources related to the event
	 * @param event the event
	 */
	private static void doFireNotifyResourceEvent(Set<Resource> resources, int event) {
		printNotifyResourceEvents(resources, event);
		
		resourceEventlisteners.forEach((resourceEventListener) -> {
			try {
				resourceEventListener.resourceEvent(resources, event);
			} catch (Exception e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to trigger listener attached to Editing Domain! ", e));
			}
		});
	}
	
	/**
	 * Clears the list of accumulated change events and notifies all waiting objects
	 */
	public static void clearAccumulatedRecourceChangeEvents() {
		synchronized (accumulatedResourceChangeEvents) {
			accumulatedResourceChangeEvents.clear();
			accumulatedResourceChangeEvents.notifyAll();			
		}
	}
	
	/**
	 * Use this method to print debug information about a given set of resource events
	 * @param resources the resources related to the event
	 * @param event the event
	 */
	private static void printNotifyResourceEvents(Set<Resource> resources, int event) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
				
		int counter = 1;
		ps.println();
		ps.println("Currently notified resources for event " + event + ": ----------");
		for (Resource notifiedResource : resources) {
			ps.println("Currently notified resource <" + counter++ + "> : " + notifiedResource.getURI() + " - " + notifiedResource);
		}
		
		try {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Notified Resources: " + os.toString("UTF8")));
		} catch (UnsupportedEncodingException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Print notified resources failed: ", e));
		}
	}

	/**
	 * Method to clear list of all listeners
	 */
	public static void clearResourceEventListener() {
		resourceEventlisteners.clear();
	}
	
	/**
	 * Method that allows to add a new Listener to the registry
	 * @param resourceEventListener the listener which will be triggered by resource changes
	 */
	public static void addResourceEventListener(IResourceEventListener resourceEventListener) {
		if (resourceEventListener != null) {
			resourceEventlisteners.add(resourceEventListener);
		}
	}

	/**
	 * Method to remove a listener from the registry
	 * @param resourceEventListener the resourceEventListener to be removed
	 */
	public static void removeResourceEventListener(IResourceEventListener resourceEventListener) {
		if (resourceEventListener != null) {
			resourceEventlisteners.remove(resourceEventListener);
		}
	}
	
	/**
	 * Use this method to hand back the VirSatResourceSet
	 * @return {@link VirSatResourceSet}
	 */
	public VirSatResourceSet getResourceSet() {
		ResourceSet resSet = super.getResourceSet();
		if (resSet instanceof VirSatResourceSet) {
			return (VirSatResourceSet) super.getResourceSet();
		} 
		return null;
	}
	
	@Override
	public void dispose() {
		if (!isDisposed) {
			
			synchronized (accumulatedResourceChangeEvents) {
				doFireNotifyResourceEvent(accumulatedResourceChangeEvents, EVENT_CHANGED);
				clearAccumulatedRecourceChangeEvents();
			}
			
			((IWorkspaceCommandStack) commandStack).getOperationHistory().removeOperationHistoryListener(historyListener);
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(initWorkSpaceChangeListener());
			
			// Remove all resources from the dirty state
			getResourceSet().getResources().forEach((res) -> {
				isResourceDirty.remove(res);
			});
			
			isDisposed = true;
		}
		
		// Call the dispose method of the ResourceSet
		super.dispose();
	}
	
	/**
	 * Method to access the special VirSatCommandStack which allows to execute a command without undo.
	 * @return the VirSatwWorkspaceCommand
	 */
	public VirSatWorkspaceCommandStack getVirSatCommandStack() {
		return (VirSatWorkspaceCommandStack) getCommandStack();
	}
	
	@Override
	public Command createCommand(Class<? extends Command> commandClass, CommandParameter commandParameter) {

		Collection<?> collection = commandParameter.getCollection();
		EObject owner = commandParameter.getEOwner();
		// For the COPY CUT PASTE we will only allow actions on certain objects
		if (commandClass == CopyCommand.class) {
			return new DVLMCopierCommand(collection);
		} else if (commandClass == CopyToClipboardCommand.class) {
			return VirSatCopyToClipboardCommand.create(this, collection);
		} else if (commandClass == PasteFromClipboardCommand.class) {
			return VirSatPasteFromClipboardCommand.create(this, owner);
		} else if (commandClass == CutToClipboardCommand.class) {
			return VirSatCutToClipboardCommand.create(this, collection);
		} else if (commandClass == DeleteCommand.class) {
			return new DeleteStructuralElementInstanceCommand(this, commandParameter.getCollection());
		} else {
			return super.createCommand(commandClass, commandParameter);
		}
	}
	
	/**
	 * Call this method from the calls for executing a command. This method maintains the order
	 * of executing the command as a wrapped workspace operation as well as saving the files in the
	 * right point of time.
	 * @param runnable the runnable that implements the call to the execution of the command
	 */
	protected void executeInWorkspace(Runnable runnable) {
		// Do not execute with a special UserContext
		executeInWorkspace(runnable, null);
	}
	
	/**
	 * Call this method from the calls for executing a command. This method maintains the order
	 * of executing the command as a wrapped workspace operation as well as saving the files in the
	 * right point of time. This method provides extra capabilities to set the UserContext as well.
	 * @param runnable the runnable that implements the call to the execution of the command
	 * @param userContextOverride the UserContext to be set in the editing domain before executing the runnable
	 * @throws RuntimeException in case something goes wrong during the execution of the runnable
	 */
	protected void executeInWorkspace(Runnable runnable, IUserContext userContextOverride) throws RuntimeException {
		IProject project = getResourceSet().getProject();
		
		/**
		 * This workspace is used as lock to synchronize the workspace operations and
		 * the transactions on the editing domain. E.g. The builder starts and locks the
		 * workspace, then it tries to place a non undoable command into the stack. in the meantime
		 * a user operation created a new SEI, this has been placed as a command into the stack in between.
		 * This command will try to write to the workspace which is locked. The builder will get stuck
		 * because it tries to execute a new command on the stack but cannot acquire the transaction.
		 * 
		 * As a way out, this lock will be used to first synchronize all operations executing a command.
		 * This ensures that a command is always executed after another one. Second every command will first try
		 * to lock the workspace. Then it will try to get the transaction. Commands which are already in a locked 
		 * workspace will reenter the lock.
		 */
		IWorkspace workspace = project.getWorkspace();
		try {
			workspace.run((monitor -> {
				AtomicExceptionReference<Exception> atomicException = new AtomicExceptionReference<>();
				
				// Now set the user context for the execution within the ws of the editing domain
				IUserContext previousUserContext = this.userContextOverride;
				this.setUserContextOverride(userContextOverride);
				
				// Now execute the actual runnable which was planned for execution
				try {
					runnable.run();
				} catch (Exception e) {
					atomicException.set(e);
				} finally {
					// Make sure the user context override is set back to null after execution
					this.setUserContextOverride(previousUserContext);
				}
				
				// and try to hand over errors
				atomicException.throwAsRuntimeExceptionIfSet();
			}), null);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Object runExclusive(Runnable read) throws InterruptedException {
		return runExclusiveInWorkspace(read, false);
	}
	
	/**
	 * Method to wrap calls to run or writeExclsuive into a workspace operation.
	 * This ensures that the workspace is locked before the transaction and avoids
	 * race conditions with interlocking threads.
	 * @param read the Code to be executed in the transaction
	 * @param writeExclusive true in case if it is a write locking transaction
	 * @return the result of the code executed in the transaction
	 * @throws InterruptedException
	 */
	protected Object runExclusiveInWorkspace(Runnable read, boolean writeExclusive) throws InterruptedException {
		// Prepare variables to remember the results of the execution of the runnable
		AtomicReference<Object> atomicResult = new AtomicReference<>();
		AtomicExceptionReference<InterruptedException> atomicException = new AtomicExceptionReference<>();
		
		// Now start executing the runnable within a runnable executed in the workspace
		// This ensures workspace locking before transaction locking
		executeInWorkspace(() -> {
			try {
				if (writeExclusive) {
					Object result = doWriteExclusive(read);
					atomicResult.set(result);
				} else {
					Object result = super.runExclusive(read);
					atomicResult.set(result);
				}
			} catch (InterruptedException e) {
				// In case there has been an exception store it.
				atomicException.set(e);
			} 
		});
	
		// Retrieve the exception if it exists and throw it again
		atomicException.throwIfSet();
		
		// Or if there is no exception hand back the result of the inner runnable
		return atomicResult.get();
	}

	protected void setUserContextOverride(IUserContext userContext) {
		if (userContext != this) {
			this.userContextOverride = userContext;
		}
	}
	
	@Override
	public boolean isSuperUser() {
		if (userContextOverride != null) {
			return userContextOverride.isSuperUser();
		} else {
			return UserRegistry.getInstance().isSuperUser();
		}
	}

	@Override
	public String getUserName() {
		if (userContextOverride != null) {
			return userContextOverride.getUserName();
		} else {
			return UserRegistry.getInstance().getUserName();
		}
	}
}
