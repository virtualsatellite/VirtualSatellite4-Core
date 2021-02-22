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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jgit.api.Git;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.server.auth.filter.CorsFilter;
import de.dlr.sc.virsat.server.servlet.VirSatModelAccessServlet;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;
import de.dlr.sc.virsat.server.test.VersionControlTestHelper;

public class ModelAccessResourceTest extends AServerRepositoryTest {

	private Concept conceptTest;
	private VirSatResourceSet resourceSet;
	private VirSatTransactionalEditingDomain ed;
	
	private TestStructuralElement tSei;
	private StructuralElementInstance sei;
	
	private TestCategoryAllProperty tcAllProperty;
	private TestCategoryBeanA tcBeanA;
	private TestCategoryComposition tcComposition;
	private TestCategoryReference tcReference;
	private TestCategoryIntrinsicArray tcIntrinsicArray;
	private TestCategoryCompositionArray tcCompositionArray;
	private TestCategoryReferenceArray tcReferenceArray;
	
	private BeanPropertyString beanString;
	private BeanPropertyBoolean beanBool;
	private BeanPropertyEnum beanEnum;
	private BeanPropertyFloat beanFloat;
	private BeanPropertyInt beanInt;
	private BeanPropertyResource beanResource;
	private BeanPropertyReference<BeanPropertyString> beanReferenceProp;
	private BeanPropertyComposed<TestCategoryAllProperty> beanComposed;
	private BeanPropertyReference<TestCategoryAllProperty> beanReferenceCa;

	private static final String TEST_STRING = "testString";
	
	@BeforeClass
	public static void setUpTargetAndUser() {
		UserRegistry.getInstance().setSuperUser(true);
		webTarget = webTarget
			.path(VirSatModelAccessServlet.MODEL_API)
			.path(ModelAccessResource.PATH)
			.path(projectName);
	}
	
	@AfterClass
	public static void tearDownUser() {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Before
	public void setUpModel() throws Exception {
		ed = testServerRepository.getEd();
		resourceSet = ed.getResourceSet();

		conceptTest = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.tests");
		
		// Create various test categories
		tcAllProperty = new TestCategoryAllProperty(conceptTest);
		tcBeanA = new TestCategoryBeanA(conceptTest);
		tcComposition = new TestCategoryComposition(conceptTest);
		tcReference = new TestCategoryReference(conceptTest);
		tcIntrinsicArray = new TestCategoryIntrinsicArray(conceptTest);
		tcCompositionArray = new TestCategoryCompositionArray(conceptTest);
		tcReferenceArray = new TestCategoryReferenceArray(conceptTest);

		// Add them to a sei
		tSei = new TestStructuralElement(conceptTest);
		tSei.add(tcAllProperty);
		tSei.add(tcBeanA);
		tSei.add(tcComposition);
		tSei.add(tcReference);
		tSei.add(tcIntrinsicArray);
		tSei.add(tcCompositionArray);
		tSei.add(tcReferenceArray);

		sei = tSei.getStructuralElementInstance();
		
		beanString = tcAllProperty.getTestStringBean();
		beanBool = tcAllProperty.getTestBoolBean();
		beanEnum = tcAllProperty.getTestEnumBean();
		beanFloat = tcAllProperty.getTestFloatBean();
		beanInt = tcAllProperty.getTestIntBean();
		beanResource = tcAllProperty.getTestResourceBean();
		beanReferenceProp = tcReference.getTestRefPropertyBean();
		beanReferenceCa = tcReference.getTestRefCategoryBean();
		beanComposed = tcComposition.getTestSubCategoryBean();
		
		tcReference.setTestRefProperty(beanString);
		tcReference.setTestRefCategory(tcAllProperty);
		IBeanList<BeanPropertyReference<TestCategoryAllProperty>> catArray = tcReferenceArray.getTestCategoryReferenceArrayStaticBean();
		for (BeanPropertyReference<TestCategoryAllProperty> element : catArray) {
			element.setValue(tcAllProperty);
		}
		IBeanList<BeanPropertyReference<BeanPropertyString>> propArray = tcReferenceArray.getTestPropertyReferenceArrayStaticBean();
		for (BeanPropertyReference<BeanPropertyString> element : propArray) {
			element.setValue(beanString);
		}
		
		// Set booleans because it will default from null to false
		// Which would result in an additional commit
		beanBool.setValue(false);
		beanComposed.getValue().setTestBool(false);
		
		RecordingCommand recordingCommand = new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resourceSet.getRepository().getActiveConcepts().add(conceptTest);
				resourceSet.getRepository().getRootEntities().add(sei);
				resourceSet.getAndAddStructuralElementInstanceResource(sei);
			}
		};
		ed.getCommandStack().execute(recordingCommand);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		ed.saveAll();
		
		// Initial commit to have a valid HEAD
		Git git = Git.open(testServerRepository.getLocalRepositoryPath());
		git.add().addFilepattern(".").call();
		git.commit().setAll(true).setMessage("Initial commit").call();
		git.push().call();
	}
	
	@After
	public void tearDownModel() throws Exception {		
		RecordingCommand recordingCommand = new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resourceSet.getRepository().getActiveConcepts().remove(conceptTest);
				resourceSet.getRepository().getRootEntities().remove(sei);
				resourceSet.removeStructuralElementInstanceResource(sei);
			}
		};
		ed.getCommandStack().execute(recordingCommand);
	}
	
	@Test
	public void testAuthentication() {
		assertEquals(HttpStatus.OK_200, getRootSeisRequest(ADMIN_HEADER).getStatus());
		
		assertEquals(HttpStatus.OK_200, getRootSeisRequest(USER_WITH_REPO_HEADER).getStatus());
		
		assertEquals(HttpStatus.FORBIDDEN_403, getRootSeisRequest(USER_NO_REPO_HEADER).getStatus());

		// The RepositoryFilter won't be called if the request got denied by jersey before
		// so this won't produce an Exception in RepositoryFilter"
		String encoded = getAuthHeader("unknown:password");
		assertEquals(HttpStatus.FORBIDDEN_403, getRootSeisRequest(encoded).getStatus());
	}

	/*
	 * Test GET various elements
	 */
	@Test
	public void testRootSeisGet() throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = getRootSeisRequest(USER_WITH_REPO_HEADER);
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		// This would return a List<ABeanStructuralElementInstance>
		// but because of problems with unmarshalling the list of abstract objects,
		// we just use a String here
		String entity = webTarget
				.path(ModelAccessResource.ROOT_SEIS)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(String.class);
		
		assertTrue("Right Sei found", entity.contains(tSei.getUuid()));
		
		assertEquals("No new commit on get without remote changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	private Response getRootSeisRequest(String header) {
		return webTarget
				.path(ModelAccessResource.ROOT_SEIS)
				.request()
				.header(HttpHeaders.AUTHORIZATION, header)
				.get();
	}
	
	/**
	 * Get a testSubject at a path from the server
	 * Then marshall it manually via the JAXBUtility using the classes
	 * Assert that server json and manual marshalled json are equal
	 * @param testSubject the subject to be tested
	 * @param path the path in the ModelAccessResource
	 * @param classes the classes required for marshalling
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private void testGet(IBeanUuid testSubject, String path, Class[] classes) throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		String uuid = testSubject.getUuid();
		Response response = webTarget
				.path(path)
				.path(uuid)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get();
		
		assertEquals("No new commit on get without remote changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		String entity = webTarget
				.path(path)
				.path(uuid)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(String.class);
		
		// Compare with the expected
		JAXBUtility jaxbUtility = new JAXBUtility(classes);
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(testSubject, sw);
		String expected = sw.toString();
		assertEquals("Marshalled object as expected", expected, entity);
	}
	
	private void testGetSei(IBeanUuid testSubject) throws Exception {
		testGet(testSubject, ModelAccessResource.SEI, new Class[] {testSubject.getClass()});
	}
	
	@Test
	public void testSeiGet() throws Exception {
		testGetSei(tSei);
	}
	
	@SuppressWarnings("rawtypes")
	private void testGetProperty(IBeanObject testSubject) throws Exception {
		testGet(testSubject, ModelAccessResource.PROPERTY, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	private void testGetProperty(IBeanObject testSubject, Class[] classes) throws Exception {
		testGet(testSubject, ModelAccessResource.PROPERTY, classes);
	}
	
	@Test
	public void testPropertyStringGet() throws Exception {
		testGetProperty(beanString);
	}
	
	@Test
	public void testPropertyBoolGet() throws Exception {
		testGetProperty(beanBool);
	}
	
	@Test
	public void testPropertyEnumGet() throws Exception {
		testGetProperty(beanEnum);
	}
	
	@Test
	public void testPropertyFloatGet() throws Exception {
		testGetProperty(beanFloat);
	}
	
	@Test
	public void testPropertyIntGet() throws Exception {
		testGetProperty(beanInt);
	}
	
	@Test
	public void testPropertyResourceGet() throws Exception {
		testGetProperty(beanResource);
	}
	
	@Test
	public void testPropertyReferenceGet() throws Exception {
		testGetProperty(beanReferenceProp, new Class[] {beanReferenceProp.getClass(), beanString.getClass()});
		testGetProperty(beanReferenceCa, new Class[] {beanReferenceProp.getClass(), tcAllProperty.getClass()});
	}
	
	@Test
	public void testPropertyComposedGet() throws Exception {
		testGetProperty(beanComposed, new Class[] {beanComposed.getClass(), tcAllProperty.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	private void testGetCa(IBeanObject testSubject) throws Exception {
		testGet(testSubject, ModelAccessResource.CA, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	private void testGetCa(IBeanObject testSubject, Class[] classes) throws Exception {
		testGet(testSubject, ModelAccessResource.CA, classes);
	}
	
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
	
	/*
	 * Test PUT various elements
	 */
	
	/**
	 * PUT a sei and assert that the server returns OK
	 * @param sei bean to PUT
	 * @throws Exception
	 */
	private void testPutSei(IBeanStructuralElementInstance sei) throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = webTarget
				.path(ModelAccessResource.SEI)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.entity(sei, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("No new commit on put without changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	@Test
	public void testSeiPut() throws Exception {
		testPutSei(tSei);
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
				.path(ModelAccessResource.PROPERTY)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.json(jsonIn));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("Model changed as expected", TEST_STRING, beanString.getValue());
		
		assertEquals("One new commit", commits + 1, VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	/**
	 * PUT a property and assert that the server returns OK
	 * @param property bean property to PUT
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	private void testPutProperty(IBeanObject property) throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = webTarget
				.path(ModelAccessResource.PROPERTY)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.entity(property, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("No new commit on put without changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	@Test
	public void testPropertyBoolPut() throws Exception {
		testPutProperty(beanBool);
	}
	
	@Test
	public void testPropertyStringPut() throws Exception {
		testPutProperty(beanString);
	}
	
	@Test
	public void testPropertyEnumPut() throws Exception {
		testPutProperty(beanEnum);
	}
	
	@Test
	public void testPropertyFloatPut() throws Exception {
		testPutProperty(beanFloat);
	}
	
	@Test
	public void testPropertyIntPut() throws Exception {
		testPutProperty(beanInt);
	}
	
	@Test
	public void testPropertyResourcePut() throws Exception {
		testPutProperty(beanResource);
	}
	
	@Test
	public void testPropertyReferencePut() throws Exception {
		testPutProperty(beanReferenceProp);
		testPutProperty(beanReferenceCa);
	}
	
	@Test
	public void testPropertyComposedPut() throws JAXBException {
		
		// Manually marshall the Class because Entity.entity doesn't marshall
		// the TestCategoryAllProperty right because of generics
		JAXBUtility jaxbUtility = new JAXBUtility(new Class[] {BeanPropertyComposed.class, TestCategoryAllProperty.class});
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(beanComposed, sw);
		String jsonIn = sw.toString();
		
		Response response = webTarget
				.path(ModelAccessResource.PROPERTY)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.json(jsonIn));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	/**
	 * PUT a ca and assert that the server returns OK
	 * @param ca bean category assignment to PUT
	 * @throws Exception
	 */
	private void testPutCa(IBeanCategoryAssignment ca) throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = webTarget
				.path(ModelAccessResource.CA)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.entity(ca, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("No new commit on put without changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
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
	public void testCorsHeaders() {
		Map<String, String> headers = new HashMap<String, String>();

		// Not a CORS request has no additional headers
		// and status code of the underlying resource
		Response response = getBuilderWithHeaders(headers).get();
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_ORIGIN));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_CREDENTIALS));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_METHODS));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_HEADERS));
		assertEquals(HttpStatus.FORBIDDEN_403, response.getStatus());

		// CORS simple request only has allow origin header
		headers.put(CorsFilter.ORIGIN, "test");

		response = getBuilderWithHeaders(headers).get();
		assertEquals(CorsFilter.AC_ALLOWED_ORIGINS, response.getHeaderString(CorsFilter.AC_ALLOW_ORIGIN));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_CREDENTIALS));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_METHODS));
		assertNull(response.getHeaderString(CorsFilter.AC_ALLOW_HEADERS));
		assertEquals(HttpStatus.FORBIDDEN_403, response.getStatus());

		// A preflight request has all headers
		response = getBuilderWithHeaders(headers).options();
		assertEquals(CorsFilter.AC_ALLOWED_ORIGINS, response.getHeaderString(CorsFilter.AC_ALLOW_ORIGIN));
		assertEquals(CorsFilter.AC_ALLOWED_CREDENTIALS, response.getHeaderString(CorsFilter.AC_ALLOW_CREDENTIALS));
		assertEquals(CorsFilter.AC_ALLOWED_METHODS, response.getHeaderString(CorsFilter.AC_ALLOW_METHODS));
		assertEquals(CorsFilter.AC_ALLOWED_HEADERS, response.getHeaderString(CorsFilter.AC_ALLOW_HEADERS));
		// The preflight request will have the status code ok instead of forbidden
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}

	/**
	 * Returns the builder for a request with CORS headers
	 * @param headers the CORS headers
	 * @return request builder
	 */
	private Builder getBuilderWithHeaders(Map<String, String> headers) {
		return webTarget
				.path(ModelAccessResource.ROOT_SEIS)
				.request()
				.headers(new MultivaluedHashMap<>(headers));
	}
}
