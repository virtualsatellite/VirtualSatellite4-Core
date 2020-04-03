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

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.apps.api.external.ModelAPI;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

public class RepoModelAccessController {
	
	private ModelAPI modelApi;
	private VirSatTransactionalEditingDomain editingDomain;
	
	public RepoModelAccessController(VirSatTransactionalEditingDomain editingDomain) { 
		this.editingDomain = editingDomain;
		
		// maybe instantiate the api with the ed and let it handle user management
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
}
