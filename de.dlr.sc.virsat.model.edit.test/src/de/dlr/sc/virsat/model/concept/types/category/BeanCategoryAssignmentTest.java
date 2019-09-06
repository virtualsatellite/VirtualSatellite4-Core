/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * Concrete Test case for the Category Assignment Bean
 * 
 * @author bell_er
 *
 */
public class BeanCategoryAssignmentTest extends ABeanCategoryAssignmentTest {

	private Concept concept;
	private CategoryAssignment ca1;
	private CategoryAssignment ca2;
	private ABeanCategoryAssignment aBeanCa1;
	private ABeanCategoryAssignment aBeanCa2;
	private ABeanCategoryAssignment aBeanCa3;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		ca1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca2 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		
		concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getCategories().add(cat);
		ca1.setType(cat);
		
		aBeanCa1 = new BeanCategoryAssignment();
		aBeanCa2 = new BeanCategoryAssignment();
		aBeanCa3 = new BeanCategoryAssignment();
		
		aBeanCa1.setTypeInstance(ca1);
		aBeanCa2.setTypeInstance(ca2);
		aBeanCa3.setTypeInstance(null);
		UserRegistry.getInstance().setSuperUser(true);
	}
	

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testGetName() {
		final String TEST_NAME_GET = "ExampleName";
		ca1.setName(TEST_NAME_GET);

		String beanGetName = aBeanCa1.getName();
		
		assertEquals("Got correct name", TEST_NAME_GET, beanGetName);
	}

	@Test
	public void testSetNameString() {
		final String TEST_NAME_SET = "ExampleName";
		aBeanCa1.setName(TEST_NAME_SET);
		String caGetName = ca1.getName();
		
		assertEquals("Got correct name", TEST_NAME_SET, caGetName);
	}

	@Test
	public void testSetNameEditingDomainString() {
		final String TEST_NAME_SET = "ExampleName";
		Command setCommand = aBeanCa1.setName(ed, TEST_NAME_SET);

		// The command is not yet executed, the name of the CA should not have changed
		String caGetNameNull = ca1.getName();
		assertNull("Command was not yet executed", caGetNameNull);
		
		// Now we execute the command and the name should be correctly set
		setCommand.execute();
		String caGetName = ca1.getName();
		assertEquals("Name is correctly set", TEST_NAME_SET, caGetName);
	}
	
	@Test
	public void testEquals() {
		assertFalse("CAB1 and CAB2 are not equal, they have different CAs", aBeanCa1.equals(aBeanCa2));
		
		// This was previously causing a crash on the Bean
		assertNull("Make sure CAB3 TI is still null", aBeanCa3.getATypeInstance());
		assertFalse("CAB3 and CAB1 are not equal, CAB3 still has a null TI", aBeanCa3.equals(aBeanCa1));
		
		// Now set the CAB3 to the same TI as CAB1. Now they should be equal
		aBeanCa3.setTypeInstance(ca1);
		assertTrue("CAB3 and CAB1 are not equal, CAB3 still has a null TI", aBeanCa3.equals(aBeanCa1));
	}
	
	@Test
	public void testGetUuid() {
		String beanUuid = aBeanCa1.getUuid();
		String caUuid = ca1.getUuid().toString();
		assertEquals("Got correct UUID of bean", caUuid, beanUuid);
	}
	
	@Test
	public void testGetConcept() {
		assertEquals("Retrieved concept is correct", concept, aBeanCa1.getConcept());
	}
}
