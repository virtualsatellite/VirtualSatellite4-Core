/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.servlet;

import java.util.Map.Entry;

import javax.servlet.Servlet;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.resources.AccessTestResource;
import de.dlr.sc.virsat.server.resources.ModelAccessResource;
import de.dlr.sc.virsat.server.resources.WorkspaceAccessResource;
import de.dlr.virsat.external.lib.jersey.servlet.ApplicationServletContainer;

/**
 * This servlet registers the classes that should be provided as REST resources.
 *
 */
public class VirSatModelAccessServlet extends ApplicationServletContainer implements Servlet {
	
	@Override
	protected Servlet onCreateServlet() {
		ModelAccessRestApplication resourceConfig = new ModelAccessRestApplication();
		return new ServletContainer(resourceConfig);
	}

	private static class ModelAccessRestApplication extends ResourceConfig {
		private ModelAccessRestApplication() {
			register(AccessTestResource.class);
			register(WorkspaceAccessResource.class);
//			register(ModelAccessResource.class);
			
			// Iterate over the ServerRepositories and programmatically create a ModelAccessResource
			// for each that has the name of the project as path
			for (Entry<String, ServerRepository> repo : RepoRegistry.getInstance().getRepositories().entrySet()) {
				String repoName = repo.getKey();
				ModelAccessResource mar = new ModelAccessResource(repoName, repo.getValue());
				register(mar);
//				Resource.Builder resourceBuilder = Resource.builder(ModelAccessResource.class);
//				resourceBuilder.path(ModelAccessResource.PATH + "/" + repoName);
//				Resource resource = resourceBuilder.build();
//				register(resource);
			}
		}
	}
}
