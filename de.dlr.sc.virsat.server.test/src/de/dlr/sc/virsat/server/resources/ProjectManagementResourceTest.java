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

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.test.AGitAndJettyServerTest;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class ProjectManagementResourceTest extends AGitAndJettyServerTest {

	private RepositoryConfiguration testProjectConfiguration;

	@Before
	public void setUpProjectConfiguration() {
		String projectName = "testProject";
		String uri = "test.project.uri";
		VersionControlSystem backend = VersionControlSystem.GIT;
		String username = "username";
		String password = "password";
		String localPath = "";

		testProjectConfiguration = new RepositoryConfiguration(projectName, localPath, uri, backend, username, password);
	}
	
	@Test
	public void testGetAllProjectsEmptyInitially() throws IOException {
		List<String> projects = getAllProjectsRequest();
		assertTrue(projects.isEmpty());
	}
	
	@Test
	public void testAddAndGetProject() throws IOException {

		String projectName = testProjectConfiguration.getProjectName();
		Response addResponse = putRequest(projectName, testProjectConfiguration);
		
		assertEquals(Response.Status.OK, addResponse.getStatusInfo().toEnum());
		assertTrue(RepoRegistry.getInstance().getRepositories().containsKey(projectName));
		
		RepositoryConfiguration receivedConfiguration = getRequest(projectName);
		assertEquals(testProjectConfiguration, receivedConfiguration);
		
		List<String> projects = getAllProjectsRequest();
		assertEquals(1, projects.size());
		assertTrue(projects.contains(projectName));
	}
	
	@Test
	public void testUpdateProject() {
		String projectName = testProjectConfiguration.getProjectName();
		putRequest(projectName, testProjectConfiguration);
		
		testProjectConfiguration.setFunctionalAccountPassword("new password");
		Response updateResponse = putRequest(projectName, testProjectConfiguration);
		assertEquals(Response.Status.OK, updateResponse.getStatusInfo().toEnum());
		
		RepositoryConfiguration receivedConfiguration = getRequest(projectName);
		assertEquals(testProjectConfiguration, receivedConfiguration);
	}
	
	@Test
	public void testDeleteProject() {
		String projectName = testProjectConfiguration.getProjectName();
		putRequest(projectName, testProjectConfiguration);
		Response response = deleteRequest(projectName);
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
		assertTrue(getAllProjectsRequest().isEmpty());
	}
	
	@Test
	public void testAddConfigurationForDifferentProject() {
		String newProjectName = "newProject";
		putRequest(newProjectName, testProjectConfiguration);
		
		RepositoryConfiguration receivedConfiguration = getRequest(newProjectName);
		testProjectConfiguration.setProjectName(newProjectName);
		assertEquals(testProjectConfiguration, receivedConfiguration);
	}

	@Test
	public void testGetNonExistingProject() {
		Response response = webTarget
				.path("/management").path(ProjectManagementResource.PATH).path("/nonExistingProject")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
	}
	
	@Test
	public void testAddInvalidProject() {
		Response response = webTarget
				.path("/management").path(ProjectManagementResource.PATH).path("/someProject")
				.request()
				.put(Entity.json("{\"name\":\"raspberry\"}"));
		
		assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
	}

	@Test
	public void testAddProjectWithInvalidUri() {
		testProjectConfiguration.setRemoteUri("[invalid]");
		Response response = putRequest(testProjectConfiguration.getProjectName(), testProjectConfiguration);
		
		assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
	}

	private Response putRequest(String projectName, RepositoryConfiguration configuration) {
		return webTarget
				.path("/management").path(ProjectManagementResource.PATH).path("/" + projectName)
				.request()
				.put(Entity.entity(configuration, MediaType.APPLICATION_JSON));
	}

	private RepositoryConfiguration getRequest(String projectName) {
		return webTarget
				.path("/management").path(ProjectManagementResource.PATH).path("/" + projectName)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(RepositoryConfiguration.class);
	}

	private Response deleteRequest(String projectName) {
		return webTarget
				.path("/management").path(ProjectManagementResource.PATH).path("/" + projectName)
				.request()
				.delete();
	}

	private List<String> getAllProjectsRequest() {
		return webTarget
				.path("/management").path(ProjectManagementResource.PATH)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<String>>() { });
	}
}
