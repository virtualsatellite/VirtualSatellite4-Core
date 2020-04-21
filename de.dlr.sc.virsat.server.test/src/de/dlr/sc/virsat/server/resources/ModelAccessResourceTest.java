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
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.server.dataaccess.FlattenedConcept;
import de.dlr.sc.virsat.server.dataaccess.FlattenedDiscipline;
import de.dlr.sc.virsat.server.dataaccess.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

public class ModelAccessResourceTest extends AServerRepositoryTest {	
	
	private static VirSatTransactionalEditingDomain ed;
	private static FlattenedStructuralElementInstance flatSei;
	
	@BeforeClass
	public static void setUpModel() throws Exception {

		ed = testServerRepository.getEd();
		
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("TestRootComponent");
		se.setIsRootStructuralElement(true);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getStructuralElements().add(se);
		concept.setName("testConcept");
		
		Command addConceptToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), concept);
		ed.getCommandStack().execute(addConceptToRepo);
		
		flatSei = new FlattenedStructuralElementInstance();
		flatSei.setName("TestSei");
		flatSei.setSe(se.getFullQualifiedName());
	}

	@Test
	public void testGetRootSeisEmptyInitally() {
		List<FlattenedStructuralElementInstance> rootSeis = getRootSeisRequest();
		assertTrue("Initially empty list", rootSeis.isEmpty());
	}

	@Ignore
	@Test
	public void postAndGetSei() {
		synchronized (this) {
			String uuid = postSeiRequest(flatSei);
		
			// Doesn't work atm because post fails because the unflattened sei does not have parents and children
			FlattenedStructuralElementInstance returnedSei = getSeiRequest(uuid);
			assertThat("Seis have the same properties", flatSei, samePropertyValuesAs(returnedSei));
		}
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
	
	private String postSeiRequest(FlattenedStructuralElementInstance flatSei) {
		return webTarget
			.path(ModelAccessResource.PATH).path(projectName).path(ModelAccessResource.SEI)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.entity(flatSei, MediaType.APPLICATION_JSON))
			.readEntity(String.class);
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
