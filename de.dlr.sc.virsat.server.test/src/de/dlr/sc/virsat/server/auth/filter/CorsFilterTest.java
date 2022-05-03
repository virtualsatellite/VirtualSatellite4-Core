/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import de.dlr.sc.virsat.server.test.AJerseyTest;

public class CorsFilterTest extends AJerseyTest {

	@Path("/")
    public static class MockResource {
        @GET
        public Response getTest() { 
        	return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
	
	@Override
	protected Application configure() {
		ResourceConfig mockConfig = new ResourceConfig();
		
		mockConfig.register(MockResource.class);
		mockConfig.register(CorsFilter.class);
		
		return mockConfig;
	}
	
	/**
	 * Returns the builder for a request with CORS headers
	 * @param headers the CORS headers
	 * @return request builder
	 */
	private Builder getBuilderWithHeaders(Map<String, String> headers) {
		return target()
				.request()
				.headers(new MultivaluedHashMap<>(headers));
	}
	
	@Test
	public void testCorsHeaders() {
		Map<String, String> headers = new HashMap<String, String>();

		// Not a CORS request has no additional headers
		// and status code of the underlying resource
		Response response = getBuilderWithHeaders(headers).get();
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_ORIGIN));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_CREDENTIALS));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_METHODS));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_HEADERS));
		assertEquals(HttpStatus.FORBIDDEN_403, response.getStatus());

		// CORS simple request only has allow origin header
		headers.put(CorsFilter.ORIGIN, "test");

		response = getBuilderWithHeaders(headers).get();
		// If the following assert fails when run via the test suite, without any obvious reason, the follow problem could have occurred:
		// Jetty requires the sun.net.http.allowRestrictedHeaders system property to be set to send CORS requests.
		// Jetty seems to only read this system property when the first server instance is started (probably because of reusing something).
		// This requires the that the first test case that starts a server via the test suite sets the system property
		// So in short: the system property is probably missing in another test case
		// This should be the case if: 
		//  a) this test case works when run alone and 
		//  b) the header set above is not propagated into the CORS filter
		assertEquals(CorsFilter.AC_ALLOWED_ORIGINS, response.getHeaderString(CorsFilter.AC_ALLOW_ORIGIN));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_CREDENTIALS));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_METHODS));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_HEADERS));
		assertEquals(HttpStatus.FORBIDDEN_403, response.getStatus());

		// A preflight request has all headers
		response = getBuilderWithHeaders(headers).options();
		assertEquals(CorsFilter.AC_ALLOWED_ORIGINS, response.getHeaderString(CorsFilter.AC_ALLOW_ORIGIN));
		assertEquals(CorsFilter.AC_ALLOWED_CREDENTIALS, response.getHeaderString(CorsFilter.AC_ALLOW_CREDENTIALS));
		assertEquals(CorsFilter.AC_ALLOWED_METHODS, response.getHeaderString(CorsFilter.AC_ALLOW_METHODS));
		assertEquals(CorsFilter.AC_ALLOWED_HEADERS, response.getHeaderString(CorsFilter.AC_ALLOW_HEADERS));
		// The preflight request will have the status code ok instead of forbidden
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
}
