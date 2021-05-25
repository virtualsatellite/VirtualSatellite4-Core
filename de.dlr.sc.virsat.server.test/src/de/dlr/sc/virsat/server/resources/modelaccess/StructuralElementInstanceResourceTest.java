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

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
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
	public void testSeiDeleteRoleManagement() throws Exception {
		// Assert check sei discpline
		setSeiDiscipline(tSei.getStructuralElementInstance(), null);
		assertNoRightsResponse(getTestRequestBuilder(ModelAccessResource.SEI + "/" + tSei.getUuid()).delete());
		
		// Assert check sei children discpline
		setSeiDiscipline(tSei.getStructuralElementInstance(), discipline);
		setSeiDiscipline(tSeiChild.getStructuralElementInstance(), null);
		assertNoRightsResponse(getTestRequestBuilder(ModelAccessResource.SEI + "/" + tSei.getUuid()).delete());
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
		
		Response response = getTestRequestBuilderWithQueryParam(ModelAccessResource.SEI + "/" + tSei.getUuid(), 
				ModelAccessResource.QP_FULL_QUALIFIED_NAME, wantedTypeFqn).post(Entity.json(null));
		
		String entity = assertIUuidGotCreated(response, wantedTypeFqn, ed, tSei.getStructuralElementInstance().getChildren());
		
		StructuralElementInstance sei = RepositoryUtility.findSei(entity, ed.getResourceSet().getRepository());
		assertEquals("Inherited parent discipline", tSei.getStructuralElementInstance().getAssignedDiscipline(), sei.getAssignedDiscipline());
		
		// Assert implicit parent discipline rights check
		setSeiDiscipline(tSei.getStructuralElementInstance(), null);
		
		response = getTestRequestBuilderWithQueryParam(ModelAccessResource.SEI + "/" + tSei.getUuid(), 
				ModelAccessResource.QP_FULL_QUALIFIED_NAME, wantedTypeFqn).post(Entity.json(null));
		assertNoRightsResponse(response);
	}
	
	@Test
	public void testErrorResponses() {
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.SEI + "/unknown").get());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.SEI + "/unknown").delete());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.SEI + "/unknown").post(Entity.json(null)));
	}
}
