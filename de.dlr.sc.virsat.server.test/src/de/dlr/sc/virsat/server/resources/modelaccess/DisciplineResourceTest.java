/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources.modelaccess;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.roles.BeanDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.resources.AModelAccessResourceTest;
import de.dlr.sc.virsat.server.resources.ModelAccessResource;
import de.dlr.sc.virsat.server.test.VersionControlTestHelper;

public class DisciplineResourceTest extends AModelAccessResourceTest {
	
	@Test
	public void testDisciplineGet() throws Exception {
		testGet(new BeanDiscipline(discipline),
				ModelAccessResource.DISCIPLINE,
				new Class[] {BeanDiscipline.class});
	}

	@Test
	public void testDisciplinePut() throws Exception {
		
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = getTestRequestBuilder(
				ModelAccessResource.DISCIPLINE)
				.put(Entity.entity(new BeanDiscipline(discipline), MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("No new commit on put without changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	@Test
	public void testDisciplineCreate() throws Exception {
		String uuid = getTestRequestBuilder(
				ModelAccessResource.DISCIPLINE)
				.post(Entity.json(null))
				.readEntity(String.class);
		
		Discipline createdDiscipline = RepositoryUtility.findDiscipline(uuid, ed.getResourceSet().getRepository());
		
		assertThat(ed.getResourceSet().getRoleManagement().getDisciplines(), hasItem(createdDiscipline));
	}
	
	@Test
	public void testDisciplineDelete() throws Exception {
		String uuid = discipline.getUuid().toString();
		getTestRequestBuilder(
				ModelAccessResource.DISCIPLINE + "/" + uuid)
				.delete();
		
		assertThat(ed.getResourceSet().getRoleManagement().getDisciplines(), not(hasItem(discipline)));
	}
	
	@Test
	public void testErrorResponses() {
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.DISCIPLINE + "/unknown").get());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.DISCIPLINE + "/unknown").delete());
		
		// Assert check discipline
		setDiscipline(ed.getResourceSet().getRoleManagement(), anotherDiscipline);
		assertCommandNotExecuteableErrorResponse(getTestRequestBuilder(ModelAccessResource.DISCIPLINE + "/" + discipline.getUuid()).delete());
		
		assertCommandNotExecuteableErrorResponse(getTestRequestBuilder(ModelAccessResource.DISCIPLINE).post(Entity.json(null)));
	}
}
