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

import java.io.StringWriter;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.server.resources.AModelAccessResourceTest;
import de.dlr.sc.virsat.server.resources.ModelAccessResource;
import de.dlr.sc.virsat.server.test.VersionControlTestHelper;

public class CategoryAssignmentResourceTest extends AModelAccessResourceTest {
	
	@Test
	public void testCaAllPropertyGet() throws Exception {
		testGetCa(tcAllProperty);
	}
	
	@Test
	public void testCaBeanAGet() throws Exception {
		testGetCa(tcBeanA);
	}
	
	@Test
	public void testCaCompositionGet() throws Exception {
		testGetCa(tcComposition);
	}
	
	@Test
	public void testCaRefernceGet() throws Exception {
		testGetCa(tcReference);
	}
	
	@Test
	public void testCaIntrinsicArrayGet() throws Exception {
		testGetCa(tcIntrinsicArray);
	}
	
	@Test
	public void testCaCompositionArrayGet() throws Exception {
		testGetCa(tcCompositionArray, new Class[] {tcCompositionArray.getClass(), tcAllProperty.getClass()});
	}
	
	@Test
	public void testCaReferenceArrayGet() throws Exception {
		testGetCa(tcReferenceArray);
	}
	
	@Test
	public void testCaAllPropertyPut() throws Exception {
		testPutCa(tcAllProperty);
	}
	
	@Test
	public void testCaBeanAPut() throws Exception {
		testPutCa(tcBeanA);
	}
	
	@Test
	public void testCaCompositionPut() throws Exception {
		testPutCa(tcComposition);
	}
	
	@Test
	public void testCaReferncePut() throws Exception {
		testPutCa(tcReference);
	}
	
	@Test
	public void testCaIntrinsicArrayPut() throws Exception {
		testPutCa(tcIntrinsicArray);
	}
	
	@Test
	public void testCaCompositionArrayPut() throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		// Manually marshall the Class because Entity.entity doesn't marshall
		// the TestCategoryAllProperty right because of generics
		JAXBUtility jaxbUtility = new JAXBUtility(new Class[] {TestCategoryCompositionArray.class, TestCategoryAllProperty.class});
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(tcCompositionArray, sw);
		String jsonIn = sw.toString();
		
		Response response = webTarget
				.path(ModelAccessResource.CA)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.json(jsonIn));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("One new commit because boolean was changed from null to false", commits + 1,
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	@Test
	public void testCaReferenceArrayPut() throws Exception {
		testPutCa(tcReferenceArray);
	}
	
	@Test
	public void testCaDelete() throws Exception {
		testDeleteCa(tcAllProperty);
	}
	
	@Test
	public void testErrorResponses() {
		assertBadRequestResponse(
				getTestRequestBuilder(ModelAccessResource.CA + "/unknown").get(), 
				CategoryAssignmentResource.COULD_NOT_FIND_REQUESTED_CA);
		
		assertBadRequestResponse(
				getTestRequestBuilder(ModelAccessResource.CA + "/unknown").delete(), 
				CategoryAssignmentResource.COULD_NOT_FIND_REQUESTED_CA);
	}
	
	@Test
	public void testGetNoSync() {
		assertGetNoSync(ModelAccessResource.CA, tcBeanA.getUuid());
	}
}
