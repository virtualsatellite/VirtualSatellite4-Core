/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import de.dlr.sc.virsat.server.test.AJerseyTest;

public class DocumentationResourceTest extends AJerseyTest {

	@Override
	protected Application configure() {
		ResourceConfig mockConfig = new ResourceConfig();
		
		final DocumentationResource docProvider = new DocumentationResource("resources");
		final AbstractBinder docBinder = new AbstractBinder() {
			@Override
			public void configure() {
				bind(docProvider).to(DocumentationResource.class);
			}
		};
		
		mockConfig.register(docBinder);
		mockConfig.register(DocumentationResource.class);
		
		return mockConfig;
	}
	
	@Test
	public void testDocumentation() {
	    String docFileContent = target(DocumentationResource.SWAGGER_JSON).request().get(String.class);
	    assertEquals("Right file content", "testContent", docFileContent);
	    
	    docFileContent = target(DocumentationResource.SWAGGER_YAML).request().get(String.class);
	    assertEquals("File not in resources", DocumentationResource.FILE_NOT_FOUND, docFileContent);
	}
	
	@Test
	public void testAuthentication() {
		Response response = target(DocumentationResource.SWAGGER_JSON).request().get();
		assertEquals("The resources are not secured and can be accessed even without an auth header", HttpStatus.OK_200, response.getStatus());
		
		response = target(DocumentationResource.SWAGGER_YAML).request().get();
		assertEquals("The resources are not secured and can be accessed even without an auth header", HttpStatus.OK_200, response.getStatus());
	}
	
}
