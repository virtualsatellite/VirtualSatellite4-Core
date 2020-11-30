/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpMethod;

/**
 * A filter that handles CORS requests
 */
@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

	public static final String AC_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	public static final String AC_ALLOW_METHODS = "Access-Control-Allow-Methods";
	public static final String AC_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String AC_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	
	public static final String AC_ALLOWED_CREDENTIALS = "true";
	public static final String AC_ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTIONS, HEAD";
	public static final String AC_ALLOWED_HEADERS = ""; //"X-Requested-With, Authorization, Accept-Version, Content-MD5, CSRF-Token, Content-Type";
	public static final String AC_ALLOWED_ORIGINS = "*";
	
	public static final String ORIGIN = "Origin";
	
	@Override
	public void filter(ContainerRequestContext request) throws IOException {

		// If the request is a preflight request we don't want to evaluate it further,
		// instead we return OK and add the headers with the filter below
		if (isPreflightRequest(request)) {
			request.abortWith(Response.ok().build());
			return;
		}
	}

	/**
	 * A preflight request is an OPTIONS request with an Origin header.
	 */
	private static boolean isPreflightRequest(ContainerRequestContext request) {
		return request.getHeaderString(ORIGIN) != null && request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString());
	}

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {

		// Not a CORS request so return
		if (request.getHeaderString(ORIGIN) == null) {
			return;
		}

		// Add all headers for a preflight request
		if (isPreflightRequest(request)) {
			response.getHeaders().add(AC_ALLOW_CREDENTIALS, AC_ALLOWED_CREDENTIALS);
			response.getHeaders().add(AC_ALLOW_METHODS, AC_ALLOWED_METHODS);
			// Additional non-standard / safe headers can be added here
			response.getHeaders().add(AC_ALLOW_HEADERS, AC_ALLOWED_HEADERS);
		}

		// All CORS requests need this response
		response.getHeaders().add(AC_ALLOW_ORIGIN, AC_ALLOWED_ORIGINS);
	}
}