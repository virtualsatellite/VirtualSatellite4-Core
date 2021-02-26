/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import javax.ws.rs.core.Response;

public class ApiErrorHelper {

	public static final String SUCCESSFUL_OPERATION = "Successful operation";
	public static final String SYNC_ERROR = "Synchronization error";
	
	private ApiErrorHelper() { };
	
	public static Response createBadRequestResponse(String msg) {
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
	}

	public static Response createSyncErrorResponse(String msg) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).build();
	}
}
