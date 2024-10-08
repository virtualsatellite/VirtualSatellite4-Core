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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.List;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.resources.modelaccess.RepositoryAccessResource;
import de.dlr.sc.virsat.server.test.VersionControlTestHelper;

public class ModelAccessResourceTest extends AModelAccessResourceTest {
	
	@Test
	public void testAuthentication() {
		// Test for resource and subresource
		assertEquals(HttpStatus.OK_200, getRootSeisRequest(ADMIN_HEADER).getStatus());
		assertEquals(HttpStatus.OK_200, getSeiRequest(ADMIN_HEADER, tSei).getStatus());
		
		assertEquals(HttpStatus.OK_200, getRootSeisRequest(USER_WITH_REPO_HEADER).getStatus());
		assertEquals(HttpStatus.OK_200, getSeiRequest(USER_WITH_REPO_HEADER, tSei).getStatus());
		
		assertEquals(HttpStatus.FORBIDDEN_403, getRootSeisRequest(USER_NO_REPO_HEADER).getStatus());
		assertEquals(HttpStatus.FORBIDDEN_403, getSeiRequest(USER_NO_REPO_HEADER, tSei).getStatus());

		// The RepositoryFilter won't be called if the request got denied by jersey before
		// so this won't produce an Exception in RepositoryFilter"
		String encoded = getAuthHeader("unknown:password");
		assertEquals(HttpStatus.FORBIDDEN_403, getRootSeisRequest(encoded).getStatus());
		assertEquals(HttpStatus.FORBIDDEN_403, getSeiRequest(encoded, tSei).getStatus());
	}

	@Test
	public void testRootSeisGet() throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = getRootSeisRequest(USER_WITH_REPO_HEADER);
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		// This would return a List<ABeanStructuralElementInstance>
		// but because of problems with unmarshalling the list of abstract objects,
		// we just use a String here
		String entity = response.readEntity(String.class);
		
		assertTrue("Right Sei found", entity.contains(tSei.getUuid()));
		
		assertEquals("No new commit on get without remote changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	@Test
	public void testPropertyStringPutChangesModel() throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		// Manually marshall the Class to edit the json
		JAXBUtility jaxbUtility = new JAXBUtility(new Class[] {BeanPropertyString.class});
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(beanString, sw);
		String jsonIn = sw.toString();
		jsonIn = jsonIn.replace("null", "\"" + TEST_STRING + "\"");
	
		assertNull(beanString.getValue());
		Response response = webTarget
				.path(RepositoryAccessResource.PROPERTY)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.json(jsonIn));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("Model changed as expected", TEST_STRING, beanString.getValue());
		
		assertEquals("One new commit", commits + 1, VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	
	@Test
	public void testActiveConceptsGet() throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		List<String> entity = webTarget
				.path(RepositoryAccessResource.CONCEPTS)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(new GenericType<List<String>>() { });
		
		List<Concept> activeConcepts = resourceSet.getRepository().getActiveConcepts();
		assertEquals(activeConcepts.size(), entity.size());
		
		String entityString = webTarget
				.path(RepositoryAccessResource.CONCEPTS)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(String.class);
		assertTrue(entityString.contains(activeConcepts.get(0).getFullQualifiedName()));
		
		assertEquals("No new commit on get without remote changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	@Test
	public void testAllConceptsGet() throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		List<String> entity = webTarget
				.path(RepositoryAccessResource.CONCEPTS)
				.queryParam(RepositoryAccessResource.QP_ONLY_ACTIVE_CONCEPTS, "false")
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(new GenericType<List<String>>() { });
		
		List<Concept> activeConcepts = resourceSet.getRepository().getActiveConcepts();
		assertThat(entity.size(), is(greaterThan(activeConcepts.size())));
		
		String entityString = webTarget
				.path(RepositoryAccessResource.CONCEPTS)
				.queryParam(RepositoryAccessResource.QP_ONLY_ACTIVE_CONCEPTS, "false")
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(String.class);
		assertTrue(entityString.contains(activeConcepts.get(0).getFullQualifiedName()));
		
		assertEquals("No new commit on get without remote changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	@Test
	public void testQueryParams() {
		// Request without query params that should create a certain amount of log messages
		CountingLogListener listener = new CountingLogListener();
		Activator.getDefault().getLog().addLogListener(listener);
		
		assertEquals(0, listener.getCount());
		
		getTestRequestBuilder(RepositoryAccessResource.ROOT_SEIS).get();
		
		int countWithSync = listener.getCount();
		assertThat("At least one log message because of synchronization", countWithSync, greaterThan(0));
		
		// Request one with the query param build = false that should create one less log message
		listener.setCount(0);
		getTestRequestBuilderWithQueryParam(RepositoryAccessResource.ROOT_SEIS, RepositoryAccessResource.QP_BUILD, "false").get();
		
		assertEquals("Build message missing", countWithSync - 1, listener.getCount());
		
		// Request with the query param sync = false that should create no messages
		listener.setCount(0);
		getTestRequestBuilderWithQueryParam(RepositoryAccessResource.ROOT_SEIS, RepositoryAccessResource.QP_SYNC, "false").get();
		
		assertEquals("No new messages", 0, listener.getCount());
		
		Activator.getDefault().getLog().removeLogListener(listener);
	}
	
	@Test
	public void testForceSyncGet() {
		CountingLogListener listener = new CountingLogListener();
		Activator.getDefault().getLog().addLogListener(listener);
		
		// Request with the query param sync = false should still create log no messages
		listener.setCount(0);
		getTestRequestBuilderWithQueryParam(RepositoryAccessResource.FORCE_SYNC, RepositoryAccessResource.QP_SYNC, "false").get();
		
		int countWithSync = listener.getCount();
		assertThat("At least one log message because of synchronization", countWithSync, greaterThan(0));
		
		Activator.getDefault().getLog().removeLogListener(listener);
	}
	
	@Test
	public void testCreateRootSei() throws Exception {
		String wantedTypeFqn = tSei.getFullQualifiedSturcturalElementName();
		
		Response response = getTestRequestBuilderWithQueryParam(RepositoryAccessResource.ROOT_SEIS, 
				RepositoryAccessResource.QP_FULL_QUALIFIED_NAME, wantedTypeFqn).post(Entity.json(null));
		
		IUuid entity = assertIUuidGotCreated(response, wantedTypeFqn, ed, ed.getResourceSet().getRepository().getRootEntities());
		
		StructuralElementInstance sei = RepositoryUtility.findSei(entity.getUuid().toString(), ed.getResourceSet().getRepository());
		assertEquals("Inherited parent discipline", ed.getResourceSet().getRepository().getAssignedDiscipline(), sei.getAssignedDiscipline());
		
		// Assert implicit parent discipline rights check
		setDiscipline(ed.getResourceSet().getRepository(), anotherDiscipline);
		
		response = getTestRequestBuilderWithQueryParam(RepositoryAccessResource.ROOT_SEIS, 
				RepositoryAccessResource.QP_FULL_QUALIFIED_NAME, wantedTypeFqn).post(Entity.json(null));
		assertCommandNotExecuteableErrorResponse(response);
	}
	
	@Test
	public void testDisciplinesGet() throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		// Test all disciplines
		String entityString = getTestRequestBuilder(RepositoryAccessResource.DISCIPLINES).get(String.class);
		assertTrue("Test discipline should be returned", entityString.contains(DISCIPLINE_NAME));
		
		// Test role management discipline
		entityString = getTestRequestBuilder(RepositoryAccessResource.ROLEMANAGEMENT).get(String.class);
		assertTrue("Test discipline should be returned", entityString.contains(DISCIPLINE_NAME));
		
		// Test repository discipline
		entityString = getTestRequestBuilder(RepositoryAccessResource.REPOSITORY).get(String.class);
		assertTrue("Test discipline should be returned", entityString.contains(DISCIPLINE_NAME));
		
		assertEquals("No new commit on get without remote changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
}
