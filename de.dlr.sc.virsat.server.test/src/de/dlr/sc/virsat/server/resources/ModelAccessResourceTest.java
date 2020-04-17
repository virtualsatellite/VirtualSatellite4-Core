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
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.server.dataaccess.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

public class ModelAccessResourceTest extends AServerRepositoryTest {	
	
	private VirSatTransactionalEditingDomain ed;
	private FlattenedStructuralElementInstance flatSei;
	
	@Before
	public void setUpModel() throws Exception {

		ed = testServerRepository.getEd();
		
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("TestRootComponent");
		se.setIsRootStructuralElement(true);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getStructuralElements().add(se);
		
		Command addConceptToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), concept);
		ed.getCommandStack().execute(addConceptToRepo);
		
		flatSei = new FlattenedStructuralElementInstance();
		flatSei.setName("TestSei");
		flatSei.setSe(se);
	}
	
	@Test
	public void testGetRootSeisEmptyInitally() {
		List<FlattenedStructuralElementInstance> rootSeis = getRootSeisRequest();
		assertTrue(rootSeis.isEmpty());
	}
	
	@Test
	public void postAndGetSei() {
		String uuid = postSeiRequest(flatSei);
		
		FlattenedStructuralElementInstance returnedSei = getSeiRequest(uuid);
		
		assertEquals(flatSei, returnedSei);
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
}
