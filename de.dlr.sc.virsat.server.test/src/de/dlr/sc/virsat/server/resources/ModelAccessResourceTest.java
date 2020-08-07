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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
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
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

public class ModelAccessResourceTest extends AServerRepositoryTest {

	private static TestCategoryAllProperty tcAllProperty;
	private TestStructuralElement tSei;
	private BeanPropertyString tString;

	@Before
	public void setUpModel() throws Exception {

		VirSatTransactionalEditingDomain ed = testServerRepository.getEd();
		VirSatResourceSet resourceSet = ed.getResourceSet();

		Concept conceptTest = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.tests");

		// TODO: convention (moive in recording cmd)
		Command addConceptToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), conceptTest);
		ed.getCommandStack().execute(addConceptToRepo);
		
		tcAllProperty = new TestCategoryAllProperty(conceptTest);
		
		// TODO: do we actually need the repository or can we just search in the resource set
		// without traversing the tree?

		tSei = new TestStructuralElement(conceptTest);
		tSei.add(tcAllProperty);

		StructuralElementInstance sei = tSei.getStructuralElementInstance();
		Command addSeiToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), DVLMPackage.eINSTANCE.getRepository_RootEntities(), sei);
		ed.getCommandStack().execute(addSeiToRepo);
		
		RecordingCommand recordingCommand = new RecordingCommand(ed) {

			@Override
			protected void doExecute() {
				Resource resourceSei1 = resourceSet.getStructuralElementInstanceResource(sei);
				resourceSei1.getContents().add(sei);
			}

		};
		ed.getCommandStack().execute(recordingCommand);
		
		// Test every adapter and generics (composed)
		
		tString = tcAllProperty.getTestStringBean();
	}
	
	@Test
	public void testPropertyGet() {
		Response serverResponse = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.path(tString.getUuid())
				.request()
				.get();
		
		System.out.println(serverResponse.toString());
		assertEquals(serverResponse.getStatus(), HttpStatus.OK_200);
		
		String entity = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.path(tString.getUuid())
				.request()
				.get(String.class);
		System.out.println(entity);
		
		entity = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.path(tString.getUuid())
				.request()
				.get(String.class);
		System.out.println(entity);
	}
	
//	@Test
//	public void testPropertyPut() {
//		BeanPropertyString testBean = new BeanPropertyString(vpi);
//		Response response = webTarget.path(ModelAccessResource.PATH)
//				.path(projectName)
//				.path(ModelAccessResource.PROPERTY)
//				.request()
//				.put(Entity.entity(testBean, MediaType.APPLICATION_JSON_TYPE));
//		System.out.println(response.toString());
//		assertEquals(response.getStatus(), HttpStatus.OK_200);
//	}
	
	@Test
	public void testCaGet() {
		Response serverResponse = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.CA)
				.path(tcAllProperty.getUuid())
				.request()
				.get();
		
		System.out.println(serverResponse.toString());
		assertEquals(serverResponse.getStatus(), HttpStatus.OK_200);
		
		String entity = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.CA)
				.path(tcAllProperty.getUuid())
				.request()
				.get(String.class);
		System.out.println(entity);
	}
}
