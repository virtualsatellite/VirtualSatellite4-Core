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

import javax.servlet.Servlet;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import de.dlr.sc.virsat.server.resources.DocumentationResource;
import de.dlr.sc.virsat.server.resources.ProjectManagementResource;
import de.dlr.virsat.external.lib.jersey.servlet.ApplicationServletContainer;

public class RepoManagementServlet extends ApplicationServletContainer implements Servlet {
	
	public static final String MANAGEMENT_API_ID = "/management";
	public static final String MANAGEMENT_API_VERSION = "v0.0.1";
	public static final String MANAGEMENT_API = MANAGEMENT_API_ID + "/" + MANAGEMENT_API_VERSION;
	
	@Override
	protected Servlet onCreateServlet() {
		RepoManagementRestApplication resourceConfig = new RepoManagementRestApplication();
		return new ServletContainer(resourceConfig);
	}

	private static class RepoManagementRestApplication extends ResourceConfig {
		/**
		 * Registers all relevant Classes: Resources, Filter and Bindings
		 */
		private RepoManagementRestApplication() {
			register(ProjectManagementResource.class);

			// Register documentation resource via binder
			final DocumentationResource docProvider = new DocumentationResource("management");
			final AbstractBinder docBinder = new AbstractBinder() {
				@Override
				public void configure() {
					bind(docProvider).to(DocumentationResource.class);
				}
			};
			register(docBinder);
			register(DocumentationResource.class);
		}
	}

}
