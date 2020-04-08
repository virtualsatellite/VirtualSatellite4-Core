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

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

public class ModelAccessResourceTest extends AServerRepositoryTest {
	
	@Before
	public void setUp() {
	}
	
	
	@Test
	public void testGetRootSeisEmptyInitally() {
		List<FlattenedStructuralElementInstance> rootSeis = getRootSeisRequest();
		assertTrue(rootSeis.isEmpty());
	}
	
//	@Test
//	public void testPutAndGetProject() throws IOException {
//		Response putResponse = putRequest(projectName, testProjectConfiguration);
//		
//		assertEquals(Response.Status.OK, addResponse.getStatusInfo().toEnum());
//		assertTrue(RepoRegistry.getInstance().getRepositories().containsKey(projectName));
//		
//		RepositoryConfiguration receivedConfiguration = getRequest(projectName);
//		assertEquals(testProjectConfiguration, receivedConfiguration);
//		
//		List<String> projects = getAllProjectsRequest();
//		assertEquals(1, projects.size());
//		assertTrue(projects.contains(projectName));
//	}
	
	private List<FlattenedStructuralElementInstance> getRootSeisRequest() {
		return webTarget
				.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.ROOT_SEIS)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<FlattenedStructuralElementInstance>>() { });
	}
	
}
