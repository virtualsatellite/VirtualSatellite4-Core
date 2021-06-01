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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.junit.Test;

import de.dlr.sc.virsat.server.resources.AModelAccessResourceTest;
import de.dlr.sc.virsat.server.resources.ModelAccessResource;

public class StructuralElementInstanceResourceTest extends AModelAccessResourceTest {

	@Test
	public void testSeiGet() throws Exception {
		testGetSei(tSei);
	}

	@Test
	public void testSeiPut() throws Exception {
		testPutSei(tSei);
	}
	
	@Test
	public void testSeiDelete() throws Exception {
		testDeleteSei(tSei);
	}
	
	@Test
	public void testChildSeiGet() throws Exception {
		testGetSei(tSeiChild);
	}
	
	@Test
	public void testChildSeiPut() throws Exception {
		testPutSei(tSeiChild);
	}
	
	@Test
	public void testCreateSei() throws Exception {
		String wantedTypeFqn = tSei.getFullQualifiedSturcturalElementName();
		
		Response response = webTarget
				.path(ModelAccessResource.SEI)
				.path(tSei.getUuid())
				.queryParam(ModelAccessResource.QP_FULL_QUALIFIED_NAME, wantedTypeFqn)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.post(Entity.json(null));
		
		assertIUuidGotCreated(response, wantedTypeFqn, ed, tSei.getStructuralElementInstance().getChildren());
	}
	
	@Test
	public void testErrorResponses() {
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.SEI + "/unknown").get());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.SEI + "/unknown").delete());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.CA + "/unknown").post(Entity.json(null)));
	}
}
