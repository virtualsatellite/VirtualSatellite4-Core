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

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.server.dataaccess.FlattenedCategoryAssignment;
import de.dlr.sc.virsat.server.dataaccess.FlattenedConcept;
import de.dlr.sc.virsat.server.dataaccess.FlattenedDiscipline;
import de.dlr.sc.virsat.server.dataaccess.FlattenedPropertyInstance;
import de.dlr.sc.virsat.server.dataaccess.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

public class ModelAccessResourceTest extends AServerRepositoryTest {	
	
	private static VirSatTransactionalEditingDomain ed;
	private static FlattenedStructuralElementInstance flatRootSei;
	private static FlattenedCategoryAssignment flatCa;
	private static FlattenedPropertyInstance flatProperty;
	
	@BeforeClass
	public static void setUpModel() throws Exception {

		ed = testServerRepository.getEd();

		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("TestRootComponent");
		se.setIsRootStructuralElement(true);

		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getStructuralElements().add(se);
		concept.setName("testConcept");

		Category category = CategoriesFactory.eINSTANCE.createCategory();
		category.setName("SomeCategory");
		category.getApplicableFor().add(se);

		IntProperty ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		ip.setName("SomeProperty");
		category.getProperties().add(ip);

		concept.getCategories().add(category);

		Command addConceptToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), concept);
		ed.getCommandStack().execute(addConceptToRepo);

		StructuralElementInstance seiContaining = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiContaining.setName("seiWithSei");
		seiContaining.setType(se);
		Command addSeiToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), DVLMPackage.eINSTANCE.getRepository_RootEntities(), seiContaining);
		ed.getCommandStack().execute(addSeiToRepo);

		CategoryAssignment caOfContainingSei = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caOfContainingSei.setType(category);
		caOfContainingSei.setName("caOfSei");
		
		ValuePropertyInstance intPropertyInstance = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		intPropertyInstance.setType(ip);
		caOfContainingSei.getPropertyInstances().add(intPropertyInstance);
		
		seiContaining.getCategoryAssignments().add(caOfContainingSei);
		
		flatCa = new FlattenedCategoryAssignment(caOfContainingSei);
		flatRootSei = new FlattenedStructuralElementInstance(seiContaining);
		flatProperty = new FlattenedPropertyInstance(intPropertyInstance);
	}

	@Test
	public void testGetRootSeis() {
		List<FlattenedStructuralElementInstance> rootSeis = getRootSeisRequest();
		assertEquals("One root element found", 1, rootSeis.size());
	}

	@Test
	public void testGetSei() {
		FlattenedStructuralElementInstance returnedSei = getSeiRequest(flatRootSei.getUuid().toString());
		assertThat("Correct sei returned", flatRootSei, samePropertyValuesAs(returnedSei)); 
	}
	
	@Test
	public void testPutSei() {
		Response response = putSeiRequest(flatRootSei, flatRootSei.getUuid());
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
	}
	
	@Test
	public void testGetDisciplines() {
		List<FlattenedDiscipline> disciplines = getDisciplinesRequest();
		assertEquals("One initial discipline found", 1, disciplines.size());
		assertEquals("It's the system discipline", "System", disciplines.get(0).getName());
	}
	
	@Test
	public void testGetConcepts() {
		List<FlattenedConcept> concepts = getConceptsRequest();
		assertEquals("One initial concept found", 1, concepts.size());
		assertEquals("It's the testConcept", "testConcept", concepts.get(0).getName());
	}
	
	@Test
	public void testGetCaWithProperty() {
		FlattenedCategoryAssignment returnedCa = getCaRequest(flatCa.getUuid().toString());
		// We can't use samePropertyValuesAs here because of the List<FlattenedPropertyInstance>
		assertEquals("Correct ca returned", flatCa.getName(), returnedCa.getName()); 
		
		List<FlattenedPropertyInstance> properties = returnedCa.getProperties();
		assertEquals("One property found", 1, properties.size());
		assertThat("It's the right property", flatProperty, samePropertyValuesAs(properties.get(0)));
	}
	
	@Test
	public void testPutCa() {
		Response response = putCaRequest(flatCa, flatCa.getUuid());
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
	}
	
	private List<FlattenedStructuralElementInstance> getRootSeisRequest() {
		return webTarget
			.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.ROOT_SEIS)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get(new GenericType<List<FlattenedStructuralElementInstance>>() { });
	}
	
	private FlattenedStructuralElementInstance getSeiRequest(String uuid) {
		return webTarget
			.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.SEI).path(uuid)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get(FlattenedStructuralElementInstance.class);
	}
	
	private FlattenedCategoryAssignment getCaRequest(String uuid) {
		return webTarget
			.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.CA).path(uuid)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get(FlattenedCategoryAssignment.class);
	}
	
	private Response putSeiRequest(FlattenedStructuralElementInstance flatSei, String uuid) {
		return webTarget
			.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.SEI).path(uuid)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.put(Entity.entity(flatSei, MediaType.APPLICATION_JSON));
	}
	
	private Response putCaRequest(FlattenedCategoryAssignment flatCa, String uuid) {
		return webTarget
			.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.CA).path(uuid)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.put(Entity.entity(flatCa, MediaType.APPLICATION_JSON));
	}
	
	private List<FlattenedDiscipline> getDisciplinesRequest() {
		return webTarget
			.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.DISCIPLINES)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get(new GenericType<List<FlattenedDiscipline>>() { });
	}
	
	private List<FlattenedConcept> getConceptsRequest() {
		return webTarget
			.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.CONCEPTS)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get(new GenericType<List<FlattenedConcept>>() { });
	}
}
