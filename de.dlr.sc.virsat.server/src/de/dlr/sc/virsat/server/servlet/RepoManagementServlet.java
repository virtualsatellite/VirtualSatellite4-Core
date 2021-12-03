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

import java.io.File;

import javax.servlet.Servlet;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.servlet.ServletContainer;

import de.dlr.sc.virsat.server.auth.filter.CorsFilter;
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
			
			// Registering this feature enables jetty to check for java security annotations e.g. roles allowed
			register(RolesAllowedDynamicFeature.class);
			
			// Register the Cross origin resource sharing filter
			register(CorsFilter.class);

			// Register documentation resource via binder
			final DocumentationResource docProvider = new DocumentationResource("doc-gen" + File.separator + "management");
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
