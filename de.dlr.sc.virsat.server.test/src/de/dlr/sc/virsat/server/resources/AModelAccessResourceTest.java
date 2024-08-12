/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.xml.bind.Marshaller;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jgit.api.Git;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

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
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
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
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.resources.modelaccess.RepositoryAccessResource;
import de.dlr.sc.virsat.server.servlet.ModelAccessServletContainer;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;
import de.dlr.sc.virsat.server.test.VersionControlTestHelper;

public abstract class AModelAccessResourceTest extends AServerRepositoryTest {
	protected Concept conceptTest;
	protected VirSatResourceSet resourceSet;
	protected VirSatTransactionalEditingDomain ed;
	protected UnitManagement unitManagement;
	protected SystemOfQuantities systemOfQuantities;
	
	protected TestStructuralElement tSei;
	protected TestStructuralElement tSeiChild;
	protected StructuralElementInstance sei;
	
	protected TestCategoryAllProperty tcAllProperty;
	protected TestCategoryBeanA tcBeanA;
	protected TestCategoryComposition tcComposition;
	protected TestCategoryReference tcReference;
	protected TestCategoryIntrinsicArray tcIntrinsicArray;
	protected TestCategoryCompositionArray tcCompositionArray;
	protected TestCategoryReferenceArray tcReferenceArray;
	
	protected BeanPropertyString beanString;
	protected BeanPropertyBoolean beanBool;
	protected BeanPropertyEnum beanEnum;
	protected BeanPropertyFloat beanFloat;
	protected BeanPropertyInt beanInt;
	protected BeanPropertyResource beanResource;
	protected BeanPropertyReference<BeanPropertyString> beanReferenceProp;
	protected BeanPropertyComposed<TestCategoryAllProperty> beanComposed;
	protected BeanPropertyReference<TestCategoryAllProperty> beanReferenceCa;
	protected VirSatProjectCommons projectCommons;
	protected Discipline discipline;
	protected Discipline anotherDiscipline;
	protected SimpleQuantityKind qkNoReference;

	protected static final String TEST_STRING = "testString";
	protected static final String DISCIPLINE_NAME = "testDiscipline";
	
	@BeforeClass
	public static void setUpTargetAndUser() {
		UserRegistry.getInstance().setSuperUser(true);
		webTarget = webTarget
			.path(ModelAccessServletContainer.MODEL_API)
			.path(RepositoryAccessResource.PATH)
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
		unitManagement = resourceSet.getRepository().getUnitManagement();
		systemOfQuantities = unitManagement.getSystemOfUnit().getSystemOfQuantities().get(0);

		projectCommons = new VirSatProjectCommons(resourceSet.getProject());

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

		tSeiChild = new TestStructuralElement(conceptTest);

		sei = tSei.getStructuralElementInstance();
		// Add a child to also test the beanSei adapter
		tSeiChild.getStructuralElementInstance().setParent(sei);

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
		
		discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setName(DISCIPLINE_NAME);
		discipline.getUsers().add(USER_WITH_REPO_NAME);
		qkNoReference = QudvFactory.eINSTANCE.createSimpleQuantityKind();
		
		anotherDiscipline = RolesFactory.eINSTANCE.createDiscipline();
		anotherDiscipline.getUsers().add("another user");
		
		RecordingCommand recordingCommand = new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resourceSet.getRepository().getActiveConcepts().add(conceptTest);
				resourceSet.getRepository().getRootEntities().add(sei);
				resourceSet.getAndAddStructuralElementInstanceResource(sei);
				resourceSet.getAndAddStructuralElementInstanceResource(tSeiChild.getStructuralElementInstance());
				
				// role management
				ed.getResourceSet().getRoleManagement().getDisciplines().add(discipline);
				sei.setAssignedDiscipline(discipline);
				resourceSet.getRepository().setAssignedDiscipline(discipline);
				resourceSet.getRoleManagement().setAssignedDiscipline(discipline);
				tSeiChild.getStructuralElementInstance().setAssignedDiscipline(discipline);
				systemOfQuantities.getQuantityKind().add(qkNoReference);
				unitManagement.setAssignedDiscipline(discipline);
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
	
	protected Response getRootSeisRequest(String header) {
		return webTarget
				.path(RepositoryAccessResource.ROOT_SEIS)
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
	protected void testGet(IBeanUuid testSubject, String path, Class[] classes) throws Exception {
		JAXBUtility jaxbUtility = new JAXBUtility(classes);
		Marshaller marshaller = jaxbUtility.getJsonMarshaller();
		// Compare with the expected
		StringWriter sw = new StringWriter();
		marshaller.marshal(testSubject, sw);
		String expected = sw.toString();
				
		
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
		
		String entity = response.readEntity(String.class);
		
		assertEquals("Marshalled object as expected", expected, entity);
	}
	
	/**
	 * Gets a sei with a specified header
	 * @param header to send
	 * @param beanSei to get
	 * @return server response
	 */
	protected Response getSeiRequest(String header, IBeanStructuralElementInstance beanSei) {
		return webTarget
				.path(RepositoryAccessResource.SEI)
				.path(beanSei.getUuid())
				.request()
				.header(HttpHeaders.AUTHORIZATION, header)
				.get();
	}
	
	protected void testGetSei(IBeanStructuralElementInstance testSubject) throws Exception {
		testGet(testSubject, RepositoryAccessResource.SEI, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	protected void testGetProperty(IBeanObject testSubject) throws Exception {
		testGet(testSubject, RepositoryAccessResource.PROPERTY, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	protected void testGetProperty(IBeanObject testSubject, Class[] classes) throws Exception {
		testGet(testSubject, RepositoryAccessResource.PROPERTY, classes);
	}
	
	@SuppressWarnings("rawtypes")
	protected void testGetCa(IBeanObject testSubject) throws Exception {
		testGet(testSubject, RepositoryAccessResource.CA, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	protected void testGetCa(IBeanObject testSubject, Class[] classes) throws Exception {
		testGet(testSubject, RepositoryAccessResource.CA, classes);
	}
	
	/**
	 * PUT a sei and assert that the server returns OK
	 * @param sei bean to PUT
	 * @throws Exception
	 */
	protected void testPutSei(IBeanStructuralElementInstance sei) throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = webTarget
				.path(RepositoryAccessResource.SEI)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.entity(sei, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("No new commit on put without changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	/**
	 * PUT a property and assert that the server returns OK
	 * @param property bean property to PUT
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	protected void testPutProperty(IBeanObject property) throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = webTarget
				.path(RepositoryAccessResource.PROPERTY)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.entity(property, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("No new commit on put without changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	/**
	 * PUT a ca and assert that the server returns OK
	 * @param ca bean category assignment to PUT
	 * @throws Exception
	 */
	protected void testPutCa(IBeanCategoryAssignment ca) throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		Response response = webTarget
				.path(RepositoryAccessResource.CA)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.entity(ca, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("No new commit on put without changes", commits, 
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	/**
	 * Delete a testSubject via delete request to a specified path
	 * @param testSubject to be deleted
	 * @param path to the delete resource
	 * @throws Exception
	 */
	protected void testDelete(IBeanUuid testSubject, String path) throws Exception {
		int commits = VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath());
		
		String uuid = testSubject.getUuid();
		Response response = webTarget
				.path(path)
				.path(uuid)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.delete();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		assertEquals("One new commit", commits + 1,
				VersionControlTestHelper.countCommits(testServerRepository.getLocalRepositoryPath()));
	}
	
	/**
	 * Delete a beanSei via delete request
	 * @param beanSei to delete
	 * @throws Exception
	 */
	protected void testDeleteSei(IBeanStructuralElementInstance beanSei) throws Exception {
		IFolder seiFolder = projectCommons.getStructuralElemntInstanceFolder(beanSei.getStructuralElementInstance());
		
		assertTrue("Sei folder exists", seiFolder.exists());
		
		testDelete(beanSei, RepositoryAccessResource.SEI);
		
		assertNull("Sei has been deleted and is no longer in a resource", beanSei.getStructuralElementInstance().eResource());
		assertFalse("Sei folder deleted", seiFolder.exists());
	}
	
	/**
	 * Delete a beanCa via delete request
	 * @param beanCa to delete
	 * @throws Exception
	 */
	protected void testDeleteCa(IBeanCategoryAssignment beanCa) throws Exception {
		int initialCas = tSei.getCategoryAssignments().size();
		
		testDelete(beanCa, RepositoryAccessResource.CA);

		assertEquals("Ca removed", initialCas - 1, tSei.getCategoryAssignments().size());
	}
	
	protected Builder getTestRequestBuilder(String path) {
		return webTarget
			.path(path)
			.request()
			.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER);
	}
	
	protected Builder getTestRequestBuilderWithQueryParam(String path, String queryParam, String qpValue) {
		return webTarget
			.path(path)
			.queryParam(queryParam, qpValue)
			.request()
			.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER);
	}
	
	protected void assertErrorResponse(Response response, Status expectedStatus, String expectedMessage) {
		assertEquals(expectedStatus.getStatusCode(), response.getStatus());
		assertEquals(expectedMessage, response.readEntity(String.class));
	}
	
	protected void assertBadRequestResponse(Response response, String expectedMessage) {
		assertErrorResponse(response, Status.BAD_REQUEST, expectedMessage);
	}
	
	protected void assertNotFoundResponse(Response response) {
		assertBadRequestResponse(response, ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT);
	}
	
	protected void assertForbiddenResponse(Response response) {
		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());
	}
	
	protected void assertInternalErrorResponse(Response response, String expectedMessage) {
		assertErrorResponse(response, Status.INTERNAL_SERVER_ERROR, ApiErrorHelper.INTERNAL_SERVER_ERROR + ": " + expectedMessage);
	}
	
	protected void assertCommandNotExecuteableErrorResponse(Response response) {
		assertInternalErrorResponse(response, ApiErrorHelper.NOT_EXECUTEABLE);
	}
	
	protected void assertNotExecuteableErrorResponse(Response response) {
		assertErrorResponse(response, Status.INTERNAL_SERVER_ERROR, ApiErrorHelper.NOT_EXECUTEABLE);
	}
	
	/**
	 * Asserts if an sei or ca got correctly created from the server
	 * @param response of the server containing the created elements uuid
	 * @param wantedTypeFqn type string of the element
	 * @param ed
	 * @param ownerChildren list of the current children of the owner
	 * @return uuid
	 * @throws CoreException
	 */
	@SuppressWarnings("unchecked")
	protected IUuid assertIUuidGotCreated(Response response, String wantedTypeFqn, VirSatTransactionalEditingDomain ed, List<? extends IUuid> ownerChildren) throws CoreException {
		Repository repository = ed.getResourceSet().getRepository();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		String entityString = response.readEntity(String.class);
		JsonReader reader = Json.createReader(new StringReader(entityString));
		JsonObject repsonseObject = reader.readObject();

		IUuid obj = RepositoryUtility.findObjectById(repsonseObject.getString("uuid"), repository);
		assertNotNull(obj);
		
		// Assert correctly typed
		if (obj instanceof StructuralElementInstance) {
			assertEquals(wantedTypeFqn, ((StructuralElementInstance) obj).getType().getFullQualifiedName());
		} else {
			assertEquals(wantedTypeFqn, ((ATypeInstance) obj).getType().getFullQualifiedName());
		}
		
		assertThat("Structural element instance is correctly added to repository", (List<IUuid>) ownerChildren, hasItem(obj));
		return obj;
	}

	protected static class CountingLogListener implements ILogListener {
		private int logCount = 0;
		
		@Override
		public void logging(IStatus status, String plugin) {
			logCount++;
		}
		
		public int getCount() {
			return logCount;
		}
		
		public void setCount(int logCount) {
			this.logCount = logCount;
		}
	}

	/**
	 * Change assigned discipline of element via command
	 * @param element
	 * @param discipline
	 */
	protected void setDiscipline(IAssignedDiscipline element, Discipline discipline) {
		RecordingCommand recordingCommand = new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				element.setAssignedDiscipline(discipline);
			}
		};
		ed.getCommandStack().execute(recordingCommand);
	}
}
