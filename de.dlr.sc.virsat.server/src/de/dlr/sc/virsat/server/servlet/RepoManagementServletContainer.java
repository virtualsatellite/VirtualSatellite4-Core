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

import jakarta.servlet.Servlet;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.servlet.ServletContainer;

import de.dlr.sc.virsat.server.auth.filter.CorsFilter;
import de.dlr.sc.virsat.server.resources.project.ProjectManagementResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class RepoManagementServletContainer extends ServletContainer implements Servlet {
	
	private static final long serialVersionUID = -2692482360010807873L;
	public static final String MANAGEMENT_API_ID = "/management";
	public static final String MANAGEMENT_API_VERSION = "v0.0.1";
	public static final String MANAGEMENT_API = MANAGEMENT_API_ID + "/" + MANAGEMENT_API_VERSION;
	
	public RepoManagementServletContainer() {
		super(new RepoManagementRestApplication());
	}
	
	private static class RepoManagementRestApplication extends ResourceConfig {
		/**
		 * Registers all relevant Classes: Resources, Filter and Bindings
		 */
		private RepoManagementRestApplication() {
			register(ProjectManagementResource.class);
			register(OpenApiResource.class);
			
			// Registering this feature enables jetty to check for java security annotations e.g. roles allowed
			register(RolesAllowedDynamicFeature.class);
			
			// Register the Cross origin resource sharing filter
			register(CorsFilter.class);
		}
	}

}
