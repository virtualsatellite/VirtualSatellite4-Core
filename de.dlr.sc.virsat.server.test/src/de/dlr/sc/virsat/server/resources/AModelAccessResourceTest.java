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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.core.resources.IFolder;
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
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.server.servlet.VirSatModelAccessServlet;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;
import de.dlr.sc.virsat.server.test.VersionControlTestHelper;

public abstract class AModelAccessResourceTest extends AServerRepositoryTest {
	protected Concept conceptTest;
	protected VirSatResourceSet resourceSet;
	protected VirSatTransactionalEditingDomain ed;
	
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

	protected static final String TEST_STRING = "testString";
	
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
		
		RecordingCommand recordingCommand = new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resourceSet.getRepository().getActiveConcepts().add(conceptTest);
				resourceSet.getRepository().getRootEntities().add(sei);
				resourceSet.getAndAddStructuralElementInstanceResource(sei);
				resourceSet.getAndAddStructuralElementInstanceResource(tSeiChild.getStructuralElementInstance());
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
	protected void testGet(IBeanUuid testSubject, String path, Class[] classes) throws Exception {
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
	
	/**
	 * Gets a sei with a specified header
	 * @param header to send
	 * @param beanSei to get
	 * @return server response
	 */
	protected Response getSeiRequest(String header, IBeanStructuralElementInstance beanSei) {
		return webTarget
				.path(ModelAccessResource.SEI)
				.path(beanSei.getUuid())
				.request()
				.header(HttpHeaders.AUTHORIZATION, header)
				.get();
	}
	
	protected void testGetSei(IBeanStructuralElementInstance testSubject) throws Exception {
		testGet(testSubject, ModelAccessResource.SEI, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	protected void testGetProperty(IBeanObject testSubject) throws Exception {
		testGet(testSubject, ModelAccessResource.PROPERTY, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	protected void testGetProperty(IBeanObject testSubject, Class[] classes) throws Exception {
		testGet(testSubject, ModelAccessResource.PROPERTY, classes);
	}
	
	@SuppressWarnings("rawtypes")
	protected void testGetCa(IBeanObject testSubject) throws Exception {
		testGet(testSubject, ModelAccessResource.CA, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	protected void testGetCa(IBeanObject testSubject, Class[] classes) throws Exception {
		testGet(testSubject, ModelAccessResource.CA, classes);
	}
	
	/**
	 * PUT a sei and assert that the server returns OK
	 * @param sei bean to PUT
	 * @throws Exception
	 */
	protected void testPutSei(IBeanStructuralElementInstance sei) throws Exception {
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
	
	/**
	 * PUT a property and assert that the server returns OK
	 * @param property bean property to PUT
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	protected void testPutProperty(IBeanObject property) throws Exception {
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
	
	/**
	 * PUT a ca and assert that the server returns OK
	 * @param ca bean category assignment to PUT
	 * @throws Exception
	 */
	protected void testPutCa(IBeanCategoryAssignment ca) throws Exception {
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
		
		testDelete(beanSei, ModelAccessResource.SEI);
		
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
		
		testDelete(beanCa, ModelAccessResource.CA);

		assertEquals("Ca removed", initialCas - 1, tSei.getCategoryAssignments().size());
	}
}
