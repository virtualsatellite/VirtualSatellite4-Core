/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

public class ServerConceptTest {
	private Concept testConcept;

	private ServerConcept serverConcept;
	
	private static final String CONCEPT_DESCRIPTION = "description";
	private static final String CONCEPT_VERSION = "version";
	private static final String CONCEPT_NAME = "concept";
	private static final String SE_NAME = "se";
	private static final String CATEGORY_NAME = "category";
	private static final String ABSTRACT_CATEGORY_NAME = "abstract";
	
	@Before
	public void setup() throws JAXBException {
		Category testCategoryOne = CategoriesFactory.eINSTANCE.createCategory();
		Category testCategoryTwo = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryOne.setName(CATEGORY_NAME);
		testCategoryTwo.setName(ABSTRACT_CATEGORY_NAME);
		testCategoryTwo.setIsAbstract(true);
		
		StructuralElement testSe = StructuralFactory.eINSTANCE.createStructuralElement();
		testSe.setName(SE_NAME);
		
		testConcept = ConceptsFactory.eINSTANCE.createConcept();
		testConcept.getCategories().add(testCategoryOne);
		testConcept.getCategories().add(testCategoryTwo);
		testConcept.getStructuralElements().add(testSe);
		testConcept.setDescription(CONCEPT_DESCRIPTION);
		testConcept.setVersion(CONCEPT_VERSION);
		testConcept.setName(CONCEPT_NAME);
		testConcept.setBeta(true);
		
		serverConcept = new ServerConcept(testConcept);
	}
	
	private String getFullName(String name) {
		return CONCEPT_NAME + "." + name;
	}
	
	@Test
	public void testGetters() throws JAXBException, IOException {
		assertEquals(CONCEPT_NAME, serverConcept.getFullQualifiedName());
		assertEquals(CONCEPT_DESCRIPTION, serverConcept.getDescription());
		assertEquals(CONCEPT_VERSION, serverConcept.getVersion());
		assertEquals(true, serverConcept.getIsBeta());
		
		assertEquals(1, serverConcept.getStructuralElements().size());
		assertEquals(2, serverConcept.getCategories().size());
		assertEquals(1, serverConcept.getNonAbstractCategories().size());
		
		assertThat(serverConcept.getStructuralElements(), contains(getFullName(SE_NAME)));
		assertThat(serverConcept.getCategories(), contains(getFullName(CATEGORY_NAME), getFullName(ABSTRACT_CATEGORY_NAME)));
		assertThat(serverConcept.getNonAbstractCategories(), contains(getFullName(CATEGORY_NAME)));
	}

}
