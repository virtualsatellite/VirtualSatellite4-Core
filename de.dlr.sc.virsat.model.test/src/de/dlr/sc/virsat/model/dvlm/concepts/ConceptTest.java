/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;


/**
 * This class tests the manual additions to the generated code of 
 * Concept such as getting the non abstract categories
 * @author muel_s8
 *
 */
public class ConceptTest {

	private Category testCategoryOne;
	private Category testCategoryTwo;
	private Concept testConcept;

	@Before
	public void setUp() throws Exception {
		testCategoryOne = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryTwo = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryTwo.setIsAbstract(true);
		
		testConcept = ConceptsFactory.eINSTANCE.createConcept();
		testConcept.getCategories().add(testCategoryOne);
		testConcept.getCategories().add(testCategoryTwo);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllProperties() {
		assertThat("Concept has 2 categories", testConcept.getCategories(), hasItems(testCategoryOne, testCategoryTwo));
		assertThat("Concept only has 1 non abstract category", testConcept.getNonAbstractCategories(), allOf(hasItem(testCategoryOne), not(hasItem(testCategoryTwo))));
	}
}
