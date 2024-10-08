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

import jakarta.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;

import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

public class ApiErrorHelper {

	public static final String SUCCESSFUL_OPERATION = "Successful operation";
	public static final String SUCCESSFUL_OPERATION_CODE = "200";
	public static final String INTERNAL_SERVER_ERROR = "Internal server error";
	public static final String INTERNAL_SERVER_ERROR_CODE = "500";
	public static final String COULD_NOT_FIND_REQUESTED_ELEMENT = "Could not find requested element";
	public static final String COULD_NOT_FIND_REQUESTED_ELEMENT_CODE = "400";
	public static final String INVALID_TYPE_ERROR = "Is not a valid type";
	public static final String NOT_EXECUTEABLE = "Command was not executeable";
	public static final String NOT_EXECUTEABLE_CODE = "500";
	public static final String INVALID_TYPE_ERROR_CODE = "500";
	
	private ApiErrorHelper() { };
	
	public static Response createBadRequestResponse(String msg) {
		return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
	}

	public static Response createNotFoundErrorResponse() {
		return createBadRequestResponse(COULD_NOT_FIND_REQUESTED_ELEMENT);
	}

	public static Response createInvalidTypeErrorResponse(String type) {
		return createBadRequestResponse(INVALID_TYPE_ERROR + ": " + type);
	}

	public static Response createInternalErrorResponse(String msg) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ApiErrorHelper.INTERNAL_SERVER_ERROR + ": " + msg).build();
	}
	
	public static void executeCommandIffCanExecute(Command command, VirSatTransactionalEditingDomain ed, IUserContext iUserContext) {
		if (ed.getVirSatCommandStack().canExecute(command, iUserContext)) {
			ed.getVirSatCommandStack().executeNoUndo(command, iUserContext, false);
		} else {
			throw new IllegalArgumentException(NOT_EXECUTEABLE);
		}
	}

	public static Response createNotExecuteableErrorResponse() {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(NOT_EXECUTEABLE).build();
	}
}
