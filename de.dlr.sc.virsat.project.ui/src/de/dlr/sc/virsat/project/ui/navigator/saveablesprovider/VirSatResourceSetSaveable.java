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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.Saveable;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.Activator;

/**
 * This class implements a Saveable for the Virtual Satellite Project
 * The Saveable usually gets created by the Saveables Provider.
 * It encapsulates the EMF ResourceSet of of a Project
 * to determine its state and to provide save functionality from within the Navigator.
 * @author fisc_ph
 *
 */
public class VirSatResourceSetSaveable extends Saveable {

	private VirSatResourceSet resourceSet;
	private VirSatTransactionalEditingDomain ed;
	private VirSatProjectSaveablesProvider saveablesProvider;
	
	/**
	 * Method to access the EMF VirSat resourceSet that is managed by this Saveable
	 * @return the EMF Resource managed by this Saveable
	 */
	public VirSatResourceSet getResourceSet() {
		return resourceSet;
	}
	
	/**
	 * Constructor for the Saveable
	 * @param resourceSet the EMF VirSatResourceSet which should be handled by this Saveable
	 * @param saveablesProvider The SaveablesProvider which is used by the Saveable to report back dirty state changes when saved etc.
	 */
	public VirSatResourceSetSaveable(VirSatResourceSet resourceSet, VirSatProjectSaveablesProvider saveablesProvider) {
		this.resourceSet = resourceSet;
		this.saveablesProvider = saveablesProvider;
		ed = VirSatEditingDomainRegistry.INSTANCE.getEd(resourceSet);
	}
	
	@Override
	public String getName() {
		IProject iProject = resourceSet.getProject();
		return (iProject != null) ? iProject.getName() : "";
	}

	@Override
	public String getToolTipText() {
		return getName() + " Location: " + resourceSet.getProject().getLocation().toOSString();
	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
		ImageDescriptor imageDescriptor = null; 
		IProject iProject = resourceSet.getProject();
		if (iProject != null) {
			imageDescriptor = Activator.getImageDescriptor("resources/icons/VirSat_Project_DataBase.gif");
		}
		return imageDescriptor;
	}

	@Override
	public void doSave(IProgressMonitor monitor) throws CoreException {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatResourceSetSaveable: Starting to save resourceSet through saveable: " + resourceSet.getProject().getName()));

		ed.saveAll();
			
		// After saving we assume the dirty state has changed. The resource should not be dirty anymore
		// The SaveablesProvider knows how to notify the framework  about this dirty change.
		// Accordingly we have to inform the SaveablesProvider from here
		saveablesProvider.saveableDirtyChanged(this);
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatResourceSetSaveable: Finished to save resourceSet through saveable: " + resourceSet.getProject().getName()));
	}

	@Override
	public boolean isDirty() {
		// The editing domain tracks if changes happened to the resource in memory
		return ed.isDirty();
	}

	@Override
	public boolean equals(Object object) {
		// See documentation of SuperClass
		if (object instanceof VirSatResourceSetSaveable) {
			VirSatResourceSetSaveable saveableRHS = (VirSatResourceSetSaveable) object;
			return resourceSet.equals(saveableRHS.resourceSet); 
		}
		return false;
	}

	@Override
	public int hashCode() {
		// See documentation of SuperClass
		final int HASH_SEED = 56;
		int resourceHash = resourceSet.hashCode();
		return HASH_SEED * resourceHash + Activator.getPluginId().hashCode();
	}
}
