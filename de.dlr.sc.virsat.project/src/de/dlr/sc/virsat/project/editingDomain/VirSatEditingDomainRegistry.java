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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * This is the VirSat Editing Domain Factory. When creating or getting an Editing Domain
 * It has to be done using this class. In Virtual Satellite one Project has exactly one Editing Domain
 */
public class VirSatEditingDomainRegistry {
	
	public static final VirSatEditingDomainRegistry INSTANCE = new VirSatEditingDomainRegistry();
	
	private Map<IProject, VirSatTransactionalEditingDomain> mapProjectToEditingDomain;
	private Map<ResourceSet, VirSatTransactionalEditingDomain> mapResourceSetToEditingDomain;

	private VirSatTransactionalEditingDomainFactory edFactory;
	
	/**
	 * Call this method to clear and reset the registry.
	 * this method is intended for test case use only.
	 */
	public void clear() {
		VirSatTransactionalEditingDomain.stopResourceChangeEventThread();
		for (VirSatTransactionalEditingDomain ed : mapProjectToEditingDomain.values()) {
			ed.dispose();
		}
		mapProjectToEditingDomain.clear();
		mapResourceSetToEditingDomain.clear();
	}
	
	/**
	 * Constructor of the Editing Domain Factory
	 */
	private VirSatEditingDomainRegistry() {
		mapProjectToEditingDomain = new ConcurrentHashMap<>();
		mapResourceSetToEditingDomain = new ConcurrentHashMap<>();
		edFactory = new VirSatTransactionalEditingDomainFactory();
	}
	
	/**
	 * This Method creates an Editing Domain for a Project
	 * @param project The project for which to create the Editing Domain
	 * @param resourceSet A ResourceSet is needed when creating the Editing Domain. The ED will be assigned to the ResourceSet and the ResourceSet to the ED
	 * @return Correctly created VirSat Editing Domain
	 */
	public VirSatTransactionalEditingDomain createEd(IProject project, ResourceSet resourceSet) {
		VirSatTransactionalEditingDomain editingDomain = createEd(resourceSet);
		mapProjectToEditingDomain.put(project, editingDomain);
		mapResourceSetToEditingDomain.put(resourceSet, editingDomain);
		VirSatEditingDomainClipBoard.INSTANCE.registerEd(editingDomain);
		return editingDomain;
	}	
	
	/**
	 * This Method hands back an Editing Domain which is already created
	 * @param resourceSet The ResourceSet to which the Editing Domain is associated
	 * @return the Editing Domain which is already registered or null in case it does not exist
	 */
	public VirSatTransactionalEditingDomain getEd(ResourceSet resourceSet) {
		VirSatTransactionalEditingDomain editingDomain = mapResourceSetToEditingDomain.get(resourceSet);
		return editingDomain;
	}

	/**
	 * This Method hands back an Editing Domain which is already created
	 * @param eObject The eObject of which this method tries to get the ResourceSet for and finally the EditingDomain
	 * @return the Editing Domain which is already registered or null in case it does not exist
	 */
	public VirSatTransactionalEditingDomain getEd(EObject eObject) {
		if (eObject != null) {
			Resource resource = eObject.eResource();
			if (resource != null) {
				ResourceSet resourceSet = resource.getResourceSet();
				if (resourceSet != null) {
					return getEd(resourceSet);
				}
			}
		}
		return null;
	}
	
	/**
	 * This Method hands back an Editing Domain which is already created
	 * @param project The project to which the Editing Domain is associated
	 * @return the Editing Domain which is already registered or null in case it does not exist
	 */
	public VirSatTransactionalEditingDomain getEd(IProject project) {
		VirSatTransactionalEditingDomain editingDomain = mapProjectToEditingDomain.get(project);
		return editingDomain;
	}
	
	/**
	 * Call this method to get the EditingDomain by a URI pointing into the project
	 * @param projectUri The URI pointing to a resource in the project
	 * @return the ED for the project or null if it does not exit.
	 */
	public VirSatTransactionalEditingDomain getEd(URI projectUri) {
		IProject iProject = VirSatProjectCommons.getProjectByUri(projectUri);
		return getEd(iProject);
	}
	
	/**
	 * Internal method to actually create the Editing Domain and bind it with the ResourceSet
	 * @param resourceSet The ResourceSet which is associated with the Editing Domain
	 * @return a new Editing Domain
	 */
	private VirSatTransactionalEditingDomain createEd(ResourceSet resourceSet) {
		VirSatTransactionalEditingDomain editingDomain = (VirSatTransactionalEditingDomain) edFactory.createEditingDomain(resourceSet);
		VirSatEditingDomainClipBoard.INSTANCE.registerEd(editingDomain);
		return editingDomain;
	}

	/**
	 * Call this method to remove an Editing from the EditingDomain Registry
	 * @param project the project for which the editing domain has been registered
	 */
	public void removeEd(IProject project) {
		if (mapProjectToEditingDomain.containsKey(project)) {
			VirSatTransactionalEditingDomain virSatEditingDomain = mapProjectToEditingDomain.get(project);
			VirSatEditingDomainClipBoard.INSTANCE.deregisterEd(virSatEditingDomain);
			virSatEditingDomain.dispose();
			VirSatResourceSet virSatResourceSet = virSatEditingDomain.getResourceSet();
			mapProjectToEditingDomain.remove(project);
			mapResourceSetToEditingDomain.remove(virSatResourceSet);
		}
	}
}
