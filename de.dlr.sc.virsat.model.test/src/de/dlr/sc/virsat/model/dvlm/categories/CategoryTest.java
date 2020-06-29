/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;


/**
 * This class tests the manual additions to the generated code of 
 * A TypeInstance such as the method to retrieve its container.
 * @author fisc_ph
 *
 */
public class CategoryTest {

	private Category testCategoryOne;
	private Category testCategoryTwo;
	
	private IntProperty propertyOne;
	private IntProperty propertyTwo;

	private static final String TEST_PROPERTY_NAME_ONE = "p1";
	private static final String TEST_PROPERTY_NAME_TWO = "p2";

	@Before
	public void setUp() throws Exception {
		testCategoryOne = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryTwo = CategoriesFactory.eINSTANCE.createCategory();

		propertyOne = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propertyOne.setName(TEST_PROPERTY_NAME_ONE);
		testCategoryOne.getProperties().add(propertyOne);

		propertyTwo = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propertyTwo.setName(TEST_PROPERTY_NAME_TWO);
		testCategoryTwo.getProperties().add(propertyTwo);
		
		testCategoryTwo.setExtendsCategory(testCategoryOne);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllProperties() {
		assertThat("CategoryOne only has one property by itself", testCategoryOne.getProperties(), allOf(hasItem(propertyOne), not(hasItem(propertyTwo))));
		assertThat("CategoryTwo only has one property by itself", testCategoryTwo.getProperties(), allOf(hasItem(propertyTwo), not(hasItem(propertyOne))));
		assertEquals("CategoryTwo has 2 properties including inherited ones", 2, testCategoryTwo.getAllProperties().size());
		assertThat("CategoryTwo is inheriting property one from category one", testCategoryTwo.getAllProperties(), hasItems(propertyOne, propertyTwo));
	}
	
	@Test
	public void testGetCardinality() {
		assertEquals("CategoryTwo has no cardinality defined", 0, testCategoryTwo.getCardinality());
		testCategoryOne.setCardinality(2);
		assertEquals("CategoryTwo has inherited the cardinality of the super category", 2, testCategoryTwo.getCardinality());
		testCategoryTwo.setCardinality(1);
		assertEquals("CategoryTwo has overwritten its inherited cardinality", 1, testCategoryTwo.getCardinality());
	}
	
	@Test
	public void testIsInstanceOf() {
		assertFalse("CategoryOne does not extend CategoryTwo", testCategoryOne.isExtensionOf(testCategoryTwo));
		assertTrue("CategoryTwo extends CategoryOne", testCategoryTwo.isExtensionOf(testCategoryOne));
	}
}
