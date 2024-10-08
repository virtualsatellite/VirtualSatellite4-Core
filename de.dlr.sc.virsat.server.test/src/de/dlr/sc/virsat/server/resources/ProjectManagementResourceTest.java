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
import java.nio.file.Path;
import java.util.List;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.resources.project.ProjectManagementResource;
import de.dlr.sc.virsat.server.servlet.RepoManagementServletContainer;
import de.dlr.sc.virsat.server.test.AJettyServerTest;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class ProjectManagementResourceTest extends AJettyServerTest {

	private RepositoryConfiguration testProjectConfiguration;

	@Before
	public void setUpProjectConfiguration() throws IOException {
		String projectName = "testProject";
		String uri = "test.project.uri";
		VersionControlSystem backend = VersionControlSystem.GIT;
		String username = "username";
		String password = "password";
		String localPath = "";

		testProjectConfiguration = new RepositoryConfiguration(projectName, localPath, uri, backend, username, password);
		Path pathConfigurationDir = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteServerConfiguration_");
		ServerConfiguration.setRepositoryConfigurationsDir(pathConfigurationDir.toString());
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
				.path(RepoManagementServletContainer.MANAGEMENT_API)
				.path(ProjectManagementResource.PATH)
				.path("/nonExistingProject")
				.request()
				.header(HttpHeaders.AUTHORIZATION, ADMIN_HEADER)
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
	}
	
	@Test
	public void testAddInvalidProject() {
		Response response = webTarget
				.path(RepoManagementServletContainer.MANAGEMENT_API)
				.path(ProjectManagementResource.PATH)
				.path("/someProject")
				.request()
				.header(HttpHeaders.AUTHORIZATION, ADMIN_HEADER)
				.put(Entity.json("{\"name\":\"raspberry\"}"));
		
		assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
	}

	@Test
	public void testAddProjectWithInvalidUri() {
		testProjectConfiguration.setRemoteUri("[invalid]");
		Response response = putRequest(testProjectConfiguration.getProjectName(), testProjectConfiguration);
		
		assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
	}
	
	@Test
	public void testAuthentication() {
		assertEquals(HttpStatus.FORBIDDEN_403, getAllProjectsRequestResponse(USER_NO_REPO_HEADER).getStatus());
		
		assertEquals(HttpStatus.FORBIDDEN_403, getAllProjectsRequestResponse(USER_WITH_REPO_HEADER).getStatus());
		
		assertEquals(HttpStatus.OK_200, getAllProjectsRequestResponse(ADMIN_HEADER).getStatus());
	}

	/**
	 * Put request to update the configuration of a project identified by its name
	 * @param projectName to identify the project
	 * @param configuration new configuration
	 * @return Response
	 */
	private Response putRequest(String projectName, RepositoryConfiguration configuration) {
		return webTarget.path(RepoManagementServletContainer.MANAGEMENT_API)
				.path(ProjectManagementResource.PATH)
				.path("/" + projectName)
				.request()
				.header(HttpHeaders.AUTHORIZATION, ADMIN_HEADER)
				.put(Entity.entity(configuration, MediaType.APPLICATION_JSON));
	}

	/**
	 * Get request to access the configuration of a project identified by its name
	 * @param projectName to identify the project
	 * @return RepositoryConfiguration
	 */
	private RepositoryConfiguration getRequest(String projectName) {
		return webTarget.path(RepoManagementServletContainer.MANAGEMENT_API)
				.path(ProjectManagementResource.PATH)
				.path("/" + projectName)
				.request()
				.header(HttpHeaders.AUTHORIZATION, ADMIN_HEADER)
				.accept(MediaType.APPLICATION_JSON)
				.get(RepositoryConfiguration.class);
	}

	/**
	 * Delete request to delete the configuration of a project identified by its name
	 * @param projectName to identify the project
	 * @return Response
	 */
	private Response deleteRequest(String projectName) {
		return webTarget.path(RepoManagementServletContainer.MANAGEMENT_API)
				.path(ProjectManagementResource.PATH)
				.path("/" + projectName)
				.request()
				.header(HttpHeaders.AUTHORIZATION, ADMIN_HEADER)
				.delete();
	}

	/**
	 * Get request to get the names of all projects
	 * @return List<String> containing the names
	 */
	private List<String> getAllProjectsRequest() {
		return webTarget.path(RepoManagementServletContainer.MANAGEMENT_API)
				.path(ProjectManagementResource.PATH)
				.request()
				.header(HttpHeaders.AUTHORIZATION, ADMIN_HEADER)
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<String>>() { });
	}
	
	/**
	 * Get request to get the names of all projects with custom header
	 * @param header the authorization header
	 * @return Response
	 */
	private Response getAllProjectsRequestResponse(String header) {
		return webTarget.path(RepoManagementServletContainer.MANAGEMENT_API)
				.path(ProjectManagementResource.PATH)
				.request()
				.header(HttpHeaders.AUTHORIZATION, header)
				.accept(MediaType.APPLICATION_JSON)
				.get();
	}
}
