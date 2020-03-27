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
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.test.AGitAndJettyServerTest;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class ProjectManagementResourceTest extends AGitAndJettyServerTest {

	@Test
	public void testGetAllProjectsEmptyInitially() throws IOException {

		List<String> projects = getAllProjectsRequest();
		
		assertTrue(projects.isEmpty());
	}
	
	@Test
	public void testAddAndGetProject() throws IOException {

		// Creating a new project
		String projectName = "testProject";
		String uri = "test.project.uri";
		VersionControlSystem backend = VersionControlSystem.GIT;
		String username = "username";
		String password = "password";

		RepositoryConfiguration projectConfiguration = new RepositoryConfiguration(uri, backend, username, password, projectName);
		
		Response addResponse = putRequest(projectName, projectConfiguration);
		
		assertEquals(Response.Status.OK, addResponse.getStatusInfo().toEnum());
		assertTrue(RepoRegistry.getInstance().getRepositories().containsKey(projectName));
		
		// getting
		RepositoryConfiguration receivedConfiguration = getRequest(projectName);
		assertEquals(projectConfiguration, receivedConfiguration);
		
		String newPassword = "new password";
		projectConfiguration.setFunctionalAccountPassword(newPassword);
	}
	
	private Response putRequest(String projectName, RepositoryConfiguration configuration) {
		Response response = webTarget
				.path("/management")
				.path(ProjectManagementResource.PATH)
				.path("/" + projectName)
				.request()
				.put(Entity.entity(configuration, MediaType.APPLICATION_JSON));
		
		return response;
	}

	private RepositoryConfiguration getRequest(String projectName) {
		RepositoryConfiguration receivedConfiguration = webTarget
				.path("/management")
				.path(ProjectManagementResource.PATH)
				.path("/" + projectName)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(RepositoryConfiguration.class);
		
		return receivedConfiguration;
	}
	
	private List<String> getAllProjectsRequest() {
		List<String> projectNames = webTarget
				.path("/management")
				.path(ProjectManagementResource.PATH)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<String>>() { });
				
		return projectNames;
	}
}
