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
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.server.resources.AModelAccessResourceTest;
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
				.path(RepositoryAccessResource.CA)
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
	public void testCreateCa() throws Exception {
		String wantedTypeFqn = tcBeanA.getFullQualifiedCategoryName();
		
		// Bundle[] bundles = FrameworkUtil.getBundle(getClass()).getBundleContext().getBundles();
		// Arrays.asList(bundles).stream().map(bundle -> bundle.getSymbolicName()).sorted().forEach(bundle -> System.out.println(bundle));
				
		Response response = getTestRequestBuilderWithQueryParam(RepositoryAccessResource.CA + "/" + tSei.getUuid(), 
				RepositoryAccessResource.QP_FULL_QUALIFIED_NAME, wantedTypeFqn).post(Entity.json(null));
		
		assertIUuidGotCreated(response, wantedTypeFqn, ed, tSei.getStructuralElementInstance().getCategoryAssignments());
	}
	
	@Test
	public void testErrorResponses() {
		assertNotFoundResponse(getTestRequestBuilder(RepositoryAccessResource.CA + "/unknown").get());
		
		assertNotFoundResponse(getTestRequestBuilder(RepositoryAccessResource.CA + "/unknown").delete());
		
		assertNotFoundResponse(getTestRequestBuilder(RepositoryAccessResource.CA + "/unknown").post(Entity.json(null)));
		
		// Assert check ca discipline via sei
		setDiscipline(tSei.getStructuralElementInstance(), anotherDiscipline);
		assertForbiddenResponse(getTestRequestBuilder(RepositoryAccessResource.CA + "/" + tcAllProperty.getUuid()).delete());
		
		setDiscipline(tSei.getStructuralElementInstance(), anotherDiscipline);
		String wantedTypeFqn = tcBeanA.getFullQualifiedCategoryName();
		
		Response response = getTestRequestBuilderWithQueryParam(RepositoryAccessResource.CA + "/" + tSei.getUuid(), 
				RepositoryAccessResource.QP_FULL_QUALIFIED_NAME, wantedTypeFqn).post(Entity.json(null));
		assertForbiddenResponse(response);
	}
}