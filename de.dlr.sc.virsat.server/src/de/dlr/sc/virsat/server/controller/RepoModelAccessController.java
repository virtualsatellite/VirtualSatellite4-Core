/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.controller;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.apps.api.external.ModelAPI;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

public class RepoModelAccessController {
	
	private ModelAPI modelApi;
	private VirSatTransactionalEditingDomain editingDomain;
	
	/**
	 * Create a new instance of the modelApi and connect it to the editingDomain
	 * @param editingDomain
	 */
	public RepoModelAccessController(VirSatTransactionalEditingDomain editingDomain) { 
		this.editingDomain = editingDomain;
		
		// TODO: Maybe instantiate the api with the ed and let it handle user management
//		modelApi = new ModelAPI(project.getLocation().toString()) 
		modelApi = new ModelAPI() {
			@Override
			protected void initialize() {
				ModelAPI.resourceSet = editingDomain.getResourceSet();
				ModelAPI.resource = editingDomain.getResourceSet().getResources().get(0);
			}
			
			@Override
			public Repository getRepository() {
				return editingDomain.getResourceSet().getRepository();
			}
		};

	}

	public List<IBeanStructuralElementInstance> getRootSeis() {
		return modelApi.getRootSeis(IBeanStructuralElementInstance.class);
	}
	
	public IBeanStructuralElementInstance getSei(String uuid) throws CoreException {
		return modelApi.findBeanSeiByUuid(uuid);
	}
	
	/**
	 * Update or create a sei identified by the uuid
	 * @param newSei the sei to put
	 * @throws CoreException
	 * @throws IOException
	 */
	public void putSei(IBeanStructuralElementInstance newSei) throws CoreException, IOException {
		// Update the sei with the same uuid or create it
		IBeanStructuralElementInstance beanSei = modelApi.findBeanSeiByUuid(newSei.getUuid());
		if (beanSei != null) {
			modelApi.deleteSeiAndStorage(beanSei);
		}
		modelApi.addRootSei(newSei);
		modelApi.performInheritance();
	}
	
	/**
	 * Creates a copy of the sei with a new uuid in the model
	 * @param beanSei the sei to copy the parameters from
	 * @return String containing the new uuid
	 * @throws CoreException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public String postSei(IBeanStructuralElementInstance beanSei) throws CoreException, InstantiationException, IllegalAccessException {
		// TODO: fails if the sei is already in the repo, so create a new sei and copy the values / clone it ?
		
		// Create new sei instance		
		IBeanStructuralElementInstance newBeanSei = beanSei.getClass().newInstance();
		
		// Copy the sei and change its uuid
		VirSatUuid uuid = new VirSatUuid();
		newBeanSei.setStructuralElementInstance(beanSei.getStructuralElementInstance());
		newBeanSei.getStructuralElementInstance().setUuid(uuid);
		
		// Add the sei to the model
		modelApi.addRootSei(beanSei);
		modelApi.performInheritance();
		
		return uuid.toString();
	}
	
	public void deleteSei(String uuid) throws CoreException, IOException {
		IBeanStructuralElementInstance beanSei = modelApi.findBeanSeiByUuid(uuid);
		if (beanSei != null) {
			modelApi.deleteSeiAndStorage(beanSei);
		}
	}
	
	public IBeanCategoryAssignment getCa(String uuid) throws CoreException {
		return modelApi.findBeanCaByUuid(uuid);
	}
}
