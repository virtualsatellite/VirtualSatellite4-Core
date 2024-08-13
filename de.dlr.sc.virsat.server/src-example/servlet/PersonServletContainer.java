/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.virsat.server.example.servlet;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.servlet.ServletContainer;

import de.dlr.virsat.server.example.resources.PersonsResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.servlet.Servlet;

public class PersonServletContainer extends ServletContainer implements Servlet {
	
	private static final long serialVersionUID = 7464874609253668718L;

	public PersonServletContainer() {
		super(new PersonsResourceConfiguration());
	}
	
	private static class PersonsResourceConfiguration extends ResourceConfig {
		private PersonsResourceConfiguration() {
			register(PersonsResource.class);
			register(OpenApiResource.class);
			
			// Registering this feature enables jetty to check for java security annotations e.g. roles allowed
			register(RolesAllowedDynamicFeature.class);
		}
	}
}
