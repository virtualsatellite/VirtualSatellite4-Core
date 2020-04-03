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
import de.dlr.sc.virsat.server.repository.RepoRegistry;

public class RepoModelAccessController {
	
	private ModelAPI modelApi;
	
	public RepoModelAccessController(String repoName) { 
		IProject project = RepoRegistry.getInstance().getRepository(repoName).getProject();
		String path = project.getRawLocation().toFile().getAbsolutePath();
		modelApi = new ModelAPI(path);
	}

	public List<IBeanStructuralElementInstance> getRootSeis() {
		return modelApi.getRootSeis(IBeanStructuralElementInstance.class);
	}
	
	public IBeanStructuralElementInstance getSei(String uuid) throws CoreException {
		return modelApi.findBeanSeiByUuid(uuid);
	}
}
