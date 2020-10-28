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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
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
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

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
		
		RecordingCommand recordingCommand = new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resourceSet.getRepository().getActiveConcepts().add(conceptTest);
				resourceSet.getRepository().getRootEntities().add(sei);
				resourceSet.getAndAddStructuralElementInstanceResource(sei);
			}
		};
		ed.getCommandStack().execute(recordingCommand);
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
	
	/*
	 * Test GET various elements
	 */
	@Test
	public void testRootSeisGet() {
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.ROOT_SEIS)
				.request()
				.get();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		// This would return a List<ABeanStructuralElementInstance>
		// but because of problems with unmarshalling the list of abstract objects,
		// we just use a String here
		String entity = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.ROOT_SEIS)
				.request()
				.get(String.class);
		
		assertTrue("Right Sei found", entity.contains(tSei.getUuid()));
	}
	
	/**
	 * Get a testSubject at a path from the server
	 * Then marshall it manually via the JAXBUtility using the classes
	 * Assert that server json and manual marshalled json are equal
	 * @param testSubject the subject to be tested
	 * @param path the path in the ModelAccessResource
	 * @param classes the classes required for marshalling
	 * @throws JAXBException
	 */
	@SuppressWarnings("rawtypes")
	private void testGet(IBeanUuid testSubject, String path, Class[] classes) throws JAXBException {
		String uuid = testSubject.getUuid();
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(path)
				.path(uuid)
				.request()
				.get();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		String entity = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(path)
				.path(uuid)
				.request()
				.get(String.class);
		
		// Compare with the expected
		JAXBUtility jaxbUtility = new JAXBUtility(classes);
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(testSubject, sw);
		String expected = sw.toString();
		assertEquals("Marshalled object as expected", expected, entity);
	}
	
	private void testGetSei(IBeanUuid testSubject) throws JAXBException {
		testGet(testSubject, ModelAccessResource.SEI, new Class[] {testSubject.getClass()});
	}
	
	@Test
	public void testSeiGet() throws JAXBException {
		testGetSei(tSei);
	}
	
	@SuppressWarnings("rawtypes")
	private void testGetProperty(IBeanObject testSubject) throws JAXBException {
		testGet(testSubject, ModelAccessResource.PROPERTY, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	private void testGetProperty(IBeanObject testSubject, Class[] classes) throws JAXBException {
		testGet(testSubject, ModelAccessResource.PROPERTY, classes);
	}
	
	@Test
	public void testPropertyStringGet() throws JAXBException {
		testGetProperty(beanString);
	}
	
	@Test
	public void testPropertyBoolGet() throws JAXBException {
		testGetProperty(beanBool);
	}
	
	@Test
	public void testPropertyEnumGet() throws JAXBException {
		testGetProperty(beanEnum);
	}
	
	@Test
	public void testPropertyFloatGet() throws JAXBException {
		testGetProperty(beanFloat);
	}
	
	@Test
	public void testPropertyIntGet() throws JAXBException {
		testGetProperty(beanInt);
	}
	
	@Test
	public void testPropertyResourceGet() throws JAXBException {
		testGetProperty(beanResource);
	}
	
	@Test
	public void testPropertyReferenceGet() throws JAXBException {
		testGetProperty(beanReferenceProp, new Class[] {beanReferenceProp.getClass(), beanString.getClass()});
		testGetProperty(beanReferenceCa, new Class[] {beanReferenceProp.getClass(), tcAllProperty.getClass()});
	}
	
	@Test
	public void testPropertyComposedGet() throws JAXBException {
		testGetProperty(beanComposed, new Class[] {beanComposed.getClass(), tcAllProperty.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	private void testGetCa(IBeanObject testSubject) throws JAXBException {
		testGet(testSubject, ModelAccessResource.CA, new Class[] {testSubject.getClass()});
	}
	
	@SuppressWarnings("rawtypes")
	private void testGetCa(IBeanObject testSubject, Class[] classes) throws JAXBException {
		testGet(testSubject, ModelAccessResource.CA, classes);
	}
	
	@Test
	public void testCaAllPropertyGet() throws JAXBException {
		testGetCa(tcAllProperty);
	}
	
	@Test
	public void testCaBeanAGet() throws JAXBException {
		testGetCa(tcBeanA);
	}
	
	@Test
	public void testCaCompositionGet() throws JAXBException {
		testGetCa(tcComposition);
	}
	
	@Test
	public void testCaRefernceGet() throws JAXBException {
		testGetCa(tcReference);
	}
	
	@Test
	public void testCaIntrinsicArrayGet() throws JAXBException {
		testGetCa(tcIntrinsicArray);
	}
	
	@Test
	public void testCaCompositionArrayGet() throws JAXBException {
		testGetCa(tcCompositionArray, new Class[] {tcCompositionArray.getClass(), tcAllProperty.getClass()});
	}
	
	@Test
	public void testCaReferenceArrayGet() throws JAXBException {
		testGetCa(tcReferenceArray);
	}
	
	/*
	 * Test PUT various elements
	 */
	
	/**
	 * PUT a sei and assert that the server returns OK
	 * @param sei bean to PUT
	 */
	private void testPutSei(IBeanStructuralElementInstance sei) {
		
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.SEI)
				.request()
				.put(Entity.entity(sei, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testSeiPut() throws JAXBException {
		testPutSei(tSei);
	}
	
	@Test
	public void testPropertyStringPutChangesModel() throws JAXBException {
		
		// Manually marshall the Class to edit the json
		JAXBUtility jaxbUtility = new JAXBUtility(new Class[] {BeanPropertyString.class});
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(beanString, sw);
		String jsonIn = sw.toString();
		jsonIn = jsonIn.replace("null", "\"" + TEST_STRING + "\"");
	
		assertNull(beanString.getValue());
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.path(ModelAccessResource.STRING)
				.request()
				.put(Entity.json(jsonIn));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("Model changed as expected", TEST_STRING, beanString.getValue());
	}
	
	/**
	 * PUT a property of a specified type and assert that the server returns OK
	 * @param property bean property to PUT
	 * @param type the type of the property
	 */
	@SuppressWarnings("rawtypes")
	private void testPutProperty(IBeanObject property, String type) {
		
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.path(type)
				.request()
				.put(Entity.entity(property, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testPropertyBoolPut() throws JAXBException {
		testPutProperty(beanBool, ModelAccessResource.BOOLEAN);
	}
	
	@Test
	public void testPropertyStringPut() throws JAXBException {
		testPutProperty(beanString, ModelAccessResource.STRING);
	}
	
	@Test
	public void testPropertyEnumPut() throws JAXBException {
		testPutProperty(beanEnum, ModelAccessResource.ENUM);
	}
	
	@Test
	public void testPropertyFloatPut() throws JAXBException {
		testPutProperty(beanFloat, ModelAccessResource.FLOAT);
	}
	
	@Test
	public void testPropertyIntPut() throws JAXBException {
		testPutProperty(beanInt, ModelAccessResource.INT);
	}
	
	@Test
	public void testPropertyResourcePut() throws JAXBException {
		testPutProperty(beanResource, ModelAccessResource.RESOURCE);
	}
	
	@Test
	public void testPropertyReferencePut() throws JAXBException {
		testPutProperty(beanReferenceProp, ModelAccessResource.REFERENCE);
		testPutProperty(beanReferenceCa, ModelAccessResource.REFERENCE);
	}
	
	@Test
	public void testPropertyComposedPut() throws JAXBException {
		
		// Manually marshall the Class because Entity.entity doesn't marshall
		// the TestCategoryAllProperty right because of generics
		JAXBUtility jaxbUtility = new JAXBUtility(new Class[] {BeanPropertyComposed.class, TestCategoryAllProperty.class});
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(beanComposed, sw);
		String jsonIn = sw.toString();
		
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.path(ModelAccessResource.COMPOSED)
				.request()
				.put(Entity.json(jsonIn));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	/**
	 * PUT a ca and assert that the server returns OK
	 * @param ca bean category assignment to PUT
	 */
	private void testPutCa(IBeanCategoryAssignment ca) {
		
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.CA)
				.request()
				.put(Entity.entity(ca, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testCaAllPropertyPut() {
		testPutCa(tcAllProperty);
	}
	
	@Test
	public void testCaBeanAPut() {
		testPutCa(tcBeanA);
	}
	
	@Test
	public void testCaCompositionPut() {
		testPutCa(tcComposition);
	}
	
	@Test
	public void testCaReferncePut() {
		testPutCa(tcReference);
	}
	
	@Test
	public void testCaIntrinsicArrayPut() {
		testPutCa(tcIntrinsicArray);
	}
	
	@Test
	public void testCaCompositionArrayPut() throws JAXBException {
		// Manually marshall the Class because Entity.entity doesn't marshall
		// the TestCategoryAllProperty right because of generics
		JAXBUtility jaxbUtility = new JAXBUtility(new Class[] {TestCategoryCompositionArray.class, TestCategoryAllProperty.class});
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(tcCompositionArray, sw);
		String jsonIn = sw.toString();
		
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.CA)
				.request()
				.put(Entity.json(jsonIn));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testCaReferenceArrayPut() {
		testPutCa(tcReferenceArray);
	}
}
