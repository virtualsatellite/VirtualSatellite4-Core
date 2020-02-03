/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.saveablesprovider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.navigator.SaveablesProvider;

import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * This is the SaveablesProvider which is primarily used to provide Save capabilities
 * for the Navigator. It listens to changes on the resources bundles them into Saveables
 * and provides them to the Eclipse framework through the Interfaces ISaveablesSource or
 * ISaveablePart   
 * @author fisc_ph
 *
 */
public class VirSatProjectSaveablesProvider extends SaveablesProvider {

	/**
	 * Instantiation of a Listener to the Editing Domain. The ED is the central point of knowledge about resource
	 * States (e.g. Dirty State) and changes to them (e.g. Removed Workspace Resource etc). The listener tracks these changes
	 * and calls the appropriate methods on this provider. 
	 */
	private VirSatTransactionalEditingDomain.IResourceEventListener dirtyChangeListener = new VirSatTransactionalEditingDomain.IResourceEventListener() {
		@Override
		public void resourceEvent(Set<Resource> resources, int event) {
			if (resources.isEmpty()) {
				return;
			}
			Resource firstResource = resources.iterator().next();
			ResourceSet resourceSet = firstResource.getResourceSet();
			if (resourceSet instanceof VirSatResourceSet) {
				VirSatResourceSet virSatResourceSet = (VirSatResourceSet) resourceSet;
				Display.getDefault().asyncExec(() -> {
					switch (event) {
						case VirSatTransactionalEditingDomain.EVENT_LOAD:
						case VirSatTransactionalEditingDomain.EVENT_RELOAD:
						case VirSatTransactionalEditingDomain.EVENT_CHANGED:
							createNewDirtySaveable(virSatResourceSet);
							break;
						case VirSatTransactionalEditingDomain.EVENT_UNLOAD:
							if (!virSatResourceSet.getProject().isOpen()) {
								removeSaveable(virSatResourceSet);
							}
							break;
						default:
							break;
					}
				});
			}
		}
	};

	// A nap to keep track of the currently provided saveables
	// The map does not necessarily represent all currently loaded resources
	// Only if a resource is changed by the ED this SaveablesProvider
	// will be notified and the resource being tracked as a further Saveable
	private Map<VirSatResourceSet, VirSatResourceSetSaveable> saveables = new HashMap<>();
	private boolean isDisposed;

	@Override
	protected void doInit() {
		super.doInit();
		VirSatTransactionalEditingDomain.addResourceEventListener(dirtyChangeListener);
		isDisposed = false;
	}	
	
	@Override
	public void dispose() {
		isDisposed = true;
		VirSatTransactionalEditingDomain.removeResourceEventListener(dirtyChangeListener);
		super.dispose();
	}
	
	@Override
	public Saveable[] getSaveables() {
		return saveables.values().toArray(new Saveable[0]);
	}
	
	@Override
	public Object[] getElements(Saveable saveable) {
		// Now the elements to be handed back by the provider are the projects
		if (saveable instanceof VirSatResourceSetSaveable) {
			VirSatResourceSetSaveable resourceSaveable = (VirSatResourceSetSaveable) saveable; 
			VirSatResourceSet virSatResourceSet = resourceSaveable.getResourceSet();
			return new Object[] { virSatResourceSet };
		}
		return null;
	}

	@Override
	public Saveable getSaveable(Object element) {
		return saveables.get(element);
	}

	/**
	 * Method to remove a Saveable from the currently tracked Saveables.
	 * This method gets invoked if a resource is unloaded or relaoded by the
	 * Editing Domain.
	 * @param virSatResourceSet The resource to which the corresponding saveable should be removed
	 */
	private void removeSaveable(VirSatResourceSet virSatResourceSet) {
		VirSatResourceSetSaveable saveable = saveables.remove(virSatResourceSet);
		if (saveable != null) {
			fireSaveablesClosed(new Saveable[] {saveable});
		}
	}

	/**
	 * This method is invoked by the Editing Domain if a resource is either
	 * loaded or changed. The method creates a new Saveable for the given 
	 * Resource if needed.
	 * @param virSatResourceSet The resource for which to create the saveable
	 */
	private void createNewDirtySaveable(VirSatResourceSet virSatResourceSet) {
		if (isDisposed) {
			return;
		}
		
		Saveable saveable = saveables.get(virSatResourceSet);
		if (saveable == null) {
			saveable = new VirSatResourceSetSaveable(virSatResourceSet, this);
			saveables.put(virSatResourceSet, (VirSatResourceSetSaveable) saveable);
			fireSaveablesOpened(new Saveable[] {saveable});
		}
		fireSaveablesDirtyChanged(new Saveable[] {saveable});
	}
	
	/**
	 * Method to fire an Update to the registered Eclipse Workbench
	 * from an external position. This method should be invoked by the
	 * managed VirSatSaveables of this Provider. They will call this method
	 * if their managed resource is saved and the dirty State will change
	 * accordingly.
	 * @param saveable The saveable for which to mention that the stae has changed
	 */
	public void saveableDirtyChanged(Saveable saveable) {
		fireSaveablesDirtyChanged(new Saveable[] {saveable});
	}
}
