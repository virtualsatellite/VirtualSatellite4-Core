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
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.servlet.ServletContainer;

import de.dlr.sc.virsat.server.auth.filter.CorsFilter;
import de.dlr.sc.virsat.server.auth.filter.DynamicRepositoryFilterBinding;
import de.dlr.sc.virsat.server.dataaccess.TransactionalJsonProvider;
import de.dlr.sc.virsat.server.resources.modelaccess.RepositoryAccessResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

/**
 * This servlet registers the classes that should be provided as REST resources.
 *
 */
public class ModelAccessServletContainer extends ServletContainer implements Servlet {
	
	private static final long serialVersionUID = 3446565649712728172L;
	public static final String MODEL_API_ID = "/model";
	public static final String MODEL_API_VERSION = "v0.0.1";
	public static final String MODEL_API = MODEL_API_ID + "/" + MODEL_API_VERSION;
	
	
	public ModelAccessServletContainer() {
		super(new ModelAccessRestApplication());
	}
	
	private static class ModelAccessRestApplication extends ResourceConfig {
		
		
		/**
		 * Registers all relevant Classes: Resources, Filter and Bindings
		 */
		private ModelAccessRestApplication() {
			
			// Resources
			register(RepositoryAccessResource.class);

			OpenApiResource openApiResource = new OpenApiResource();
			Set<String> resourcePackages = RepositoryAccessResource.RESOURCE_CLASSES.stream().map((clazz) -> clazz.getPackage().toString()).collect(Collectors.toSet());
			openApiResource.resourcePackages(resourcePackages);
			register(openApiResource);
			
			// Registering this feature enables jetty to check for java security annotations e.g. roles allowed
			register(RolesAllowedDynamicFeature.class);
			
			// Register our RepositoryFilter via a dynamic binding
			register(DynamicRepositoryFilterBinding.class);
			
			// Register the Cross origin resource sharing filter
			register(CorsFilter.class);
			
			// Register a custom json provider that extends the default moxy provider
			final TransactionalJsonProvider provider = new TransactionalJsonProvider();
			final AbstractBinder binder = new AbstractBinder() {
				@Override
				public void configure() {
					bind(provider).to(TransactionalJsonProvider.class);
				}
			};
			register(binder);
			register(TransactionalJsonProvider.class);
			
			// Disable the default json provider
			this.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE_SERVER, true);
			this.property(ServerProperties.WADL_FEATURE_DISABLE, true);
		}
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		super.service(req, res);
	}
}
