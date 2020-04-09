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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.apps.api.external.ModelAPI;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
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
			/**
			 * Problem with reading in the Repository.dvlm if left standard
			 */
			public Repository getRepository() {
				return editingDomain.getResourceSet().getRepository();
			}
			
			@Override
			/**
			 * For our projects it is not always JAVA_SYSTEM_PROPERTY_WORKING_DIR
			 */
			public String getCurrentProjectAbsolutePath() {
				return editingDomain.getResourceSet().getProject().getLocation().toString();
			}
		};

	}

	/**
	 * Get the roots seis and flatten them
	 * @return List<FlattenedStructuralElementInstance> flattened seis
	 */
	public List<FlattenedStructuralElementInstance> getRootSeis() {
		List<IBeanStructuralElementInstance> beans = modelApi.getRootSeis(IBeanStructuralElementInstance.class);
		List<FlattenedStructuralElementInstance> flattenedSeis = new ArrayList<FlattenedStructuralElementInstance>();
		for (IBeanStructuralElementInstance bean : beans) {
			flattenedSeis.add(bean.flatten());
		}
		return flattenedSeis;
	}
	
	public FlattenedStructuralElementInstance getSei(String uuid) throws CoreException {
		return modelApi.findBeanSeiByUuid(uuid).flatten();
	}
	
	/**
	 * Update or create a sei identified by the uuid
	 * @param newSei the sei to put
	 * @throws CoreException
	 * @throws IOException
	 */
	public void putSei(FlattenedStructuralElementInstance flatSei) throws CoreException, IOException {
		// Update the sei with the same uuid or create it
		StructuralElementInstance sei = flatSei.unflatten();
		IBeanStructuralElementInstance beanSei = modelApi.findBeanSeiByUuid(sei.getUuid().toString());
		if (beanSei != null) {
			modelApi.deleteSeiAndStorage(beanSei);
		} else {
			beanSei = createBeanInstance(sei);
		}
		beanSei.setStructuralElementInstance(sei);
		modelApi.addRootSei(beanSei);
		modelApi.performInheritance();
		
		modelApi.saveAll();
	}
	
	/**
	 * Creates a copy of the sei with a new uuid in the model
	 * @param beanSei the sei to copy the parameters from
	 * @return String containing the new uuid
	 * @throws CoreException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException 
	 */
	public String postSei(FlattenedStructuralElementInstance flatSei) throws CoreException, InstantiationException, IllegalAccessException, IOException {
		// TODO: fails if the sei is already in the repo, so create a new sei and copy the values / clone it ?
		StructuralElementInstance sei = flatSei.unflatten();
		
		// Change the uuid of the sei and create a new bean for it
		VirSatUuid uuid = new VirSatUuid();
		IBeanStructuralElementInstance beanSei = createBeanInstance(sei);
		
		// Add the bean to the model
		modelApi.addRootSei(beanSei);
		modelApi.performInheritance();
		
		modelApi.saveAll();
		
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
	
	private IBeanStructuralElementInstance createBeanInstance(StructuralElementInstance  sei) throws CoreException {
		// TODO: what is the right way to create a bean?
		IBeanStructuralElementInstance bean = (new BeanStructuralElementInstanceFactory()).getInstanceFor(sei);
		return bean;
	}
}
