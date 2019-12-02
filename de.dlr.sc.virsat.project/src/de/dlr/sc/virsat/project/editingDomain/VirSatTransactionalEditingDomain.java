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

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
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
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;

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
import de.dlr.sc.virsat.project.structure.VirSatProjectResourceChangeListener;

/**
 * The Transactional Editing DOmain with attached WorkspaceSynchronizer
 * @author fisc_ph
 *
 */
public class VirSatTransactionalEditingDomain extends TransactionalEditingDomainImpl {

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
		StringBuilder sb = new StringBuilder();
				
		int counter = 1;
		sb.append("\nCurrently loaded resources: ----------\n");
		for (Resource loadedResource : getResourceSet().getResources()) {
			sb.append("Currently loaded resource <" + counter++ + "> : " + loadedResource.getURI() + " - " + loadedResource + "\n");
		}
		counter = 1;
		sb.append("Recently Changed resources: ----------\n");
		synchronized (recentlyChangedResource) {
			for (URI uri : recentlyChangedResource) {
				sb.append("Recently Changed Resource <" + counter++ + "> : " + uri + " - " + uri + "\n");
			}
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Recently saved Resources: " + sb.toString()));
	}
	
	private VirSatProjectResourceChangeListener workSpaceChangeListener; 

	/**
	 * Stops the workspace change listener.
	 * Useful for unit testing operations that modify resources without 
	 * saving / deleting directly over the transactional editing domain. 
	 */
	public void stopWorkspaceChangeListener() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(workSpaceChangeListener);
	}

	/**
	 * This method implements the resource change listener to detect external vs internal and expected chanegs of resources
	 * @return a Workpsacelistener, method should always hand abck the same instance
	 */
	private VirSatProjectResourceChangeListener initWorkSpaceChangeListener() {
		if (workSpaceChangeListener == null) {
			workSpaceChangeListener = new VirSatProjectResourceChangeListener(this, getResourceSet().getProject()) {
				
				private boolean triggerFullReload;
				
				@Override
				public void handlePreCondition() {
					triggerFullReload = false;
				}
				
				@Override
				public void handlePostCondition() {
					if (triggerFullReload) {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Execute Full Reload"));
						try {
							// We need the editing domain to do the reload
							runExclusive(() -> {
								VirSatTransactionalEditingDomain.this.reloadAll();
							});
						} catch (InterruptedException e) {
							Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Failed to perform Full Reload"));
						}
					}
					
					recentlyChangedResource.clear();
				}
				
				@Override
				public void handleChangedDvlmResources(List<IResource> changedDvlmResources) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Synchronizer handling changed resources"));
					printRecentlyChangedResources();
					changedDvlmResources.forEach((wsDvlmResource) -> {
						updateTriggerFullReload(wsDvlmResource);
					});
				}

				@Override
				public void handleRemovedDvlmResources(List<IResource> removedDvlmResources) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Synchronizer handling removed resources"));
					Set<Resource> removedEmfResources = new HashSet<>();
					printRecentlyChangedResources();
					removedDvlmResources.forEach((wsDvlmResource) -> {
						updateTriggerFullReload(wsDvlmResource);
						
						URI changedResourceUri = URI.createPlatformResourceURI(wsDvlmResource.getFullPath().toString(), true);
						Resource emfResource = virSatResourceSet.getResource(changedResourceUri, false);
						removedEmfResources.add(emfResource);
					});
					
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Handle removed Resources (" + removedEmfResources + ")"));
					removedEmfResources.forEach((emfResource) -> {
						VirSatTransactionalEditingDomain.this.removeResource(emfResource);
					});
				}
				
				@Override
				public void handleAddedDvlmResources(List<IResource> addedDvlmResources) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Synchronizer handling added resources"));
					printRecentlyChangedResources();
					addedDvlmResources.forEach((wsDvlmResource) -> {
						updateTriggerFullReload(wsDvlmResource);
					});
				}
				
				/**
				 * Updates the flag for triggering a full reload if there has been an external change
				 * @param wsDvlmResource the resource that has been modified
				 */
				private void updateTriggerFullReload(IResource wsDvlmResource) {
					String fileExtension = wsDvlmResource.getFileExtension();
					if (VirSatProjectCommons.FILENAME_EXTENSION.equals(fileExtension)) {
						URI changedResourceUri = URI.createPlatformResourceURI(wsDvlmResource.getFullPath().toString(), true);
						if (!recentlyChangedResource.contains(changedResourceUri)) {
							triggerFullReload = true;
						} 
					}
				}
			};
		}
		
		return workSpaceChangeListener;
	};
	
	/**
	 * this method saves all the resources in the {@link VirSatResourceSet}
	 */
	public void saveAll() {
		saveAll(true, true);
	}
	
	/**
	 * this method saves all the resources in the {@link VirSatResourceSet}
	 * @param updateRecentlySavedResource set to true to make resource appear in list of recently saved resources. this will always create a reload of the resource by the WorkspaceSynchronizer
	 * this was needed for the wizard that creates files and changes them so quickly that all resources are marked as added but not as changed
	 * accordingly the WorkspaceSychronizer is not capable to react as it should. The WorkspaceSynchronizer does not react to ADD events
	 * @param removeDanglingReferences set to true to make the virsat editing domain remove dangling references during the save.
	 * This is needed for the builders which should not incur any additional changes during the save or they will trigger themselves.
	 */
	public void saveAll(boolean updateRecentlySavedResource, boolean removeDanglingReferences) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Started saving all resources"));
	
		List<Resource> resources = new ArrayList<Resource>(virSatResourceSet.getResources());
		for (Resource resource : resources) {
			saveResource(resource, updateRecentlySavedResource, removeDanglingReferences);
			virSatResourceSet.updateDiagnostic(resource);
			virSatResourceSet.notifyDiagnosticListeners(resource);
		}
		
		maintainDirtyResources();
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Finished saving all resources"));
	}
	
	/**
	 * Method to remove a resource through this editing domain
	 * it takes care of setting the dirty state correctly and removing the resource from the resourceset
	 * @param emfResource the resource to be removed
	 */
	public void removeResource(Resource emfResource) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: About to unload a resource"));
		if (emfResource != null) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: ActuallyUnloaded Resource URI (" + emfResource.getURI().toPlatformString(true) + ")"));
			virSatResourceSet.removeResource(emfResource);
			// If the resource has been removed we don't need to monitor its dirty state anymore
			isResourceDirty.remove(emfResource);
			fireNotifyResourceEvent(Collections.singleton(emfResource), IResourceEventListener.EVENT_UNLOAD);
		}
	}
	
	/**
	 * Call this method to reload all resources and notify all listeners
	 */
	public void reloadAll() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Started reloading all resources"));
		
		// In case that a resource is properly unloaded 
		// The command stack should be flushed and the Clipboard
		// should be brought back into a clean state
		VirSatEditingDomainClipBoard.INSTANCE.flushClipboard(this);
		VirSatTransactionalEditingDomain.this.getCommandStack().flush();

		virSatResourceSet.realoadAll();
		recentlyChangedResource.clear();
		
		// After performing a reload all there are no more dirty resources
		isResourceDirty.clear();

		List<Resource> reloadedResources = virSatResourceSet.getResources();
		fireNotifyResourceEvent(new HashSet<>(reloadedResources), IResourceEventListener.EVENT_RELOAD);
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Finished reloading all resources"));
	}

	/**
	 * Use this method to make the editing domain aware of that the resource has been saved
	 * The Editing Domain remembers it and prevents a direct update coming from the workspace resources 
	 * @param resource The resource to be saved
	 */
	protected void saveResource(Resource resource) {
		saveResource(resource, true, true);
	}
	
	/**
	 * Use this method to make the editing domain aware of that the resource has been saved
	 * The Editing Domain remembers it and prevents a direct update coming from the workspace resources 
	 * @param resource The resource to be saved
	 * @param updateRecentlySavedResource set to true in case the resource should be placed in the list of recently saved resources 
	 * @param removeDanglingReferences set to true in case the resource is should be cleared of dangling references before the save
	 */
	public void saveResource(Resource resource, boolean updateRecentlySavedResource, boolean removeDanglingReferences) {
		if (resource == null) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Attempted to save a resource that was null."));
			return;
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Started saving resource (" + resource.getURI().toPlatformString(true) + ")"));
		
		if (!resource.isLoaded() || resource.getContents().isEmpty()) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain aborted saving (" + resource.getURI().toPlatformString(true) + ") because it is already unloaded."));
			return;
		}
		
		if (!virSatResourceSet.hasWritePermission(resource)) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain aborted saving (" + resource.getURI().toPlatformString(true) + ") because we do not have write permission."));
			return;
		}
		
		if (removeDanglingReferences) {
			removeDanglingReferences(resource);
		}
		
		if (isDirty(resource)) {
			internallySaveResource(resource, updateRecentlySavedResource, false);
			fireNotifyResourceEvent(Collections.singleton(resource), IResourceEventListener.EVENT_CHANGED);
		}
		
		// Mark the resource as not dirty in either case
		isResourceDirty.put(resource, false);
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Finished saving resource (" + resource.getURI().toPlatformString(true) + ")"));
	}
	
	/**
	 * For dangling references call the Utils to remove them before actually saving them
	 * @param resource the resource from which dangling references shall be removed
	 */
	private void removeDanglingReferences(Resource resource) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Saving resource (" + resource.getURI().toPlatformString(true) + ") removing dangling references"));
		
		RecordingCommand recCmd = new RecordingCommand(this) {
			@Override
			protected void doExecute() {
				boolean removedDanglingReference = VirSatResourceSetUtil.removeDanglingReferences(resource);						
				if (removedDanglingReference) {
					isResourceDirty.put(resource, true);
				}
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
	
	/**
	 * Performs the actual save of an resource, only visible to the outside for the special case of
	 * needing to ignore checks such as rights management. Needed for example when changing the discipline
	 * of a resource since this means giving the rights away.
	 * @param resource the resource to save
	 * @param updateRecentlySavedResource the flag to memorize this resource for the recently saved resources list
	 * @param overrideWritePermissions the flag to give permission to ignore rights management
	 */
	public void internallySaveResource(Resource resource, boolean updateRecentlySavedResource, boolean overrideWritePermissions) {
		// Put it to the list of recently saved resources in case it is not suppressed. This helps the workspaceSynchronizer
		// to decide if reload of the resource is needed or not (means handling external resource changes)
		if (updateRecentlySavedResource) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalEditingDomain: Changed resource (" + resource.getURI().toPlatformString(true) + ")"));
			recentlyChangedResource.add(resource.getURI());
		}
		
		// Call the VirSatResourceSet so we are sure it uses our correct Save Settings
		virSatResourceSet.saveResource(resource, overrideWritePermissions);
	}
	
	/**
	 * Use this method to determine if a resource is dirty or not
	 * @param resource the resource to be checked
	 * @return true in case it is dirty
	 */
	public boolean isDirty(Resource resource) {
		Boolean isDirty = isResourceDirty.getOrDefault(resource, false); 
		return isDirty; 
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
		boolean isDirty = isResourceDirty.values().stream().anyMatch(dirty -> dirty);
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
						for (Resource changedResource : affectedResources) {
							// We require the resource to be in the resource set since we only consider the EVENT_CHANGED case
							// in this method
							// We may have a performance issue here. Maybe we should identify changes via the command stack.
							boolean isChanged = virSatResourceSet.isChanged(changedResource);
							isResourceDirty.put(changedResource, isChanged);
						}
						
						fireNotifyResourceEvent(affectedResources, IResourceEventListener.EVENT_CHANGED);
						
						// Always run all diagnostics
						for (Resource resource : virSatResourceSet.getResources()) {
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
		
		if (event == IResourceEventListener.EVENT_CHANGED) {
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
		while (true) {
			synchronized (accumulatedResourceChangeEvents) {
				try {
					accumulatedResourceChangeEvents.wait(ResourceChangeEventThread.SLEEP_TIME);
				} catch (InterruptedException e) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ResourceChangeEventThread: Thread failed to wait ", e));
				}
				if (accumulatedResourceChangeEvents.isEmpty()) {
					break;
				}
			}
		}
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
							doFireNotifyResourceEvent(accumulatedResourceChangeEvents, IResourceEventListener.EVENT_CHANGED);
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
		resourceEventlisteners.add(resourceEventListener);
	}

	/**
	 * Method to remove a listener from the registry
	 * @param resourceEventListener the resourceEventListener to be removed
	 */
	public static void removeResourceEventListener(IResourceEventListener resourceEventListener) {
		resourceEventlisteners.remove(resourceEventListener);
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
				doFireNotifyResourceEvent(accumulatedResourceChangeEvents, IResourceEventListener.EVENT_CHANGED);
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
}
