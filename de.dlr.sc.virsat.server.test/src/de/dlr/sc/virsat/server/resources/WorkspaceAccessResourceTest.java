/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.server.test.AJettyServerTest;

public class WorkspaceAccessResourceTest extends AJettyServerTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testCloneString() {
		ClientConfig config = new ClientConfig();
		
		Client client = ClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri("http://localhost:8000/").build();
		
		WebTarget target = client.target(uri);
		
		String serverResponse = target.
			path("rest").
			path("mar").
			path("data").
			request().
			accept(MediaType.APPLICATION_JSON).
			get(Response.class)
			.toString();
		
		String serverJson = target.
				path("rest").
				path("mar").
				path("data").
				request().
				accept(MediaType.APPLICATION_JSON).
				get(String.class);
		
		String expectedResponse = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/mar/data, status=200, reason=OK}}";
		String expectedJson = "{\"UUID\":\"data\"}";
		
		assertEquals("Server response is correct", expectedResponse, serverResponse);
		assertEquals("Server json paylaod is correct", expectedJson, serverJson);
	}

	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8000/").build();
	}

//	@Test
//	public void testCommit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}

}
