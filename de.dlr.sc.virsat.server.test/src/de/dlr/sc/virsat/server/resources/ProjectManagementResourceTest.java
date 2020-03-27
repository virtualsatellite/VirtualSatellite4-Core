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

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import de.dlr.sc.virsat.server.test.AGitAndJettyServerTest;

public class ProjectManagementResourceTest extends AGitAndJettyServerTest {

	@Test
	public void testGetAllProjectsEmptyInitially() throws IOException {

		String jsonResult = webTarget.
				path("/management").
				path(ProjectManagementResource.PATH).
				request().
				accept(MediaType.APPLICATION_JSON).
				get(String.class);
		
		String expectedJsonResult = "[]";
		assertEquals(expectedJsonResult, jsonResult);
	}
}
