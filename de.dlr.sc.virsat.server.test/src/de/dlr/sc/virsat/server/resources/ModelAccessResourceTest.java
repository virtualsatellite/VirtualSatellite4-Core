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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
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
	
	private TestStructuralElement tSei;
	
	private TestCategoryAllProperty tcAllProperty;
	private TestCategoryBeanA tcBeanA;
	private TestCategoryComposition tcComposition;
	private TestCategoryReference tcReference;
	private TestCategoryIntrinsicArray tcIntrinsicArray;
	private TestCategoryCompositionArray tcCompositionArray;
	private TestCategoryReferenceArray tcReferenceArray;
	
	private BeanPropertyString tString;

	private static final String TEST_STRING = "testString";
	
	@Before
	public void setUpModel() throws Exception {

		VirSatTransactionalEditingDomain ed = testServerRepository.getEd();
		resourceSet = ed.getResourceSet();

		conceptTest = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.tests");

		// TODO: convention (move in recording cmd)
		Command addConceptToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), conceptTest);
		ed.getCommandStack().execute(addConceptToRepo);
		
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

		StructuralElementInstance sei = tSei.getStructuralElementInstance();
		Command addSeiToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), DVLMPackage.eINSTANCE.getRepository_RootEntities(), sei);
		ed.getCommandStack().execute(addSeiToRepo);
		
		// Setup string test property
		tString = tcAllProperty.getTestStringBean();
		tString.getATypeInstance().setUuid(new VirSatUuid("bc418734-29e7-403b-9e36-22cfb0d9ae4b"));
		
		RecordingCommand recordingCommand = new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resourceSet.getAndAddStructuralElementInstanceResource(sei);
			}
		};
		ed.getCommandStack().execute(recordingCommand);
		
		// TODO: Test every adapter and generics (composed)
	}
	
	/*
	 * Test GET various elements
	 */
	// TODO: test more properties
	@Test
	public void testPropertyGet() {
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.path(tString.getUuid())
				.request()
				.get();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());

		// TODO: assert right entity?
		String entity = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.path(tString.getUuid())
				.request()
				.get(String.class);
		System.out.println(entity);
	}
	
	private void testGetCa(String uuid) {
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.CA)
				.path(uuid)
				.request()
				.get();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		// TODO: assert right entity?
		String entity = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.CA)
				.path(uuid)
				.request()
				.get(String.class);
		System.out.println(entity);
		System.out.println("---");
	}
	
	@Test
	public void testCaAllPropertyGet() {
		testGetCa(tcAllProperty.getUuid());
	}
	
	@Test
	public void testCaBeanAGet() {
		// TODO: nillable
		testGetCa(tcBeanA.getUuid());
	}
	
	@Test
	public void testCaCompositionGet() {
		testGetCa(tcComposition.getUuid());
	}
	
	@Test
	public void testCaRefernecGet() {
		testGetCa(tcReference.getUuid());
	}
	
	@Test
	public void testCaIntrinsicArrayGet() {
		testGetCa(tcIntrinsicArray.getUuid());
	}
	
	@Test
	public void testCaCompositionArrayGet() {
		// TODO: generics
		testGetCa(tcCompositionArray.getUuid());
	}
	
	@Test
	public void testCaReferenceArrayGet() {
		testGetCa(tcReferenceArray.getUuid());
	}
	
	/*
	 * Test PUT various elements
	 */
	@Test
	public void testPropertyPut() {
		
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.request()
				.put(Entity.entity(tString, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	
		// TODO: improve test setup
		assertNull(tString.getValue());
		response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.request()
				.put(Entity.json(
				"{\"type\":\"beanPropertyString\",\"uuid\":\"bc418734-29e7-403b-9e36-22cfb0d9ae4b\",\"value\":\"testString\"}"));
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("Model changed as expected", TEST_STRING, tString.getValue());
	}
	
	private void testPutCa(IBeanCategoryAssignment ca) {
		
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.CA)
				.request()
				.put(Entity.entity(ca, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	
		// TODO: improve test setup
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
	public void testCaRefernecPut() {
		testPutCa(tcReference);
	}
	
	@Test
	public void testCaIntrinsicArrayPut() {
		testPutCa(tcIntrinsicArray);
	}
	
	@Test
	public void testCaCompositionArrayPut() {
		testPutCa(tcCompositionArray);
	}
	
	@Test
	// TODO transactional editing domain is a problem here
	public void testCaReferenceArrayPut() {
		testPutCa(tcReferenceArray);
	}
}
