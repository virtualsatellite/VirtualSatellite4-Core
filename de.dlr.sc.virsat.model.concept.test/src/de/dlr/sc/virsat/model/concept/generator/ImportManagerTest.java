/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;

/**
 * Test class for the import manager as it is used in the Concept Generator
 * @author fisc_ph
 *
 */
public class ImportManagerTest {

	private ImportManager im;

	private Concept concept;
	private Category cat;
	private AProperty prop;
	

	private static final String TEST_STRING = "de.dlr.virsat.BeanProperty";
	
	@Before
	public void setUp() throws Exception {
		im = new ImportManager();
		
		// Create a very simple test concept
		concept = ConceptsFactory.eINSTANCE.createConcept();
		cat = CategoriesFactory.eINSTANCE.createCategory();
		prop = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		concept.setName("de.dlr.test.concept");
		cat.setName("TestCategory");
		prop.setName("TestProperty");
		cat.getProperties().add(prop);
		concept.getCategories().add(cat);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterATypeDefinition() {
		assertTrue("Registration is still empty", im.getImportedClasses().isEmpty());
		
		im.register(cat);
		im.register(prop);
				
		assertThat("String is now contained", im.getImportedClasses(), hasItems("de.dlr.test.concept.model.TestCategory"));
		assertThat("String is now contained", im.getImportedClasses(), hasItems("de.dlr.test.concept.model.TestCategory.TestProperty"));
	}
	
	@Test
	public void testRegisterATypeDefinitionCheckPackage() {
		im.setPackage("de.dlr.test.concept.model");
		assertTrue("Registration is still empty", im.getImportedClasses().isEmpty());
		
		im.register(cat);
		assertTrue("Didn't get registered because in same package", im.getImportedClasses().isEmpty());
		
		im.register(prop);
		assertFalse("Did get registered", im.getImportedClasses().isEmpty());
	}

	@Test
	public void testSerializeATypeDefinition() {
		assertTrue("Registration is still empty", im.getImportedClasses().isEmpty());
		
		String serialize1 = im.serialize(cat);
		String serialize2 = im.serialize(prop);
	
		assertEquals("String is valid", serialize1, "TestCategory");
		assertEquals("String is valid", serialize2, "TestProperty");
		
		assertThat("String is now contained", im.getImportedClasses(), hasItems("de.dlr.test.concept.model.TestCategory"));
		assertThat("String is now contained", im.getImportedClasses(), hasItems("de.dlr.test.concept.model.TestCategory.TestProperty"));
	}
	
	@Test
	public void testSerializeClass() {
		assertTrue("Registration is still empty", im.getImportedClasses().isEmpty());
		
		String serialize1 = im.serialize(BooleanProperty.class);
		String serialize2 = im.serialize(Category.class);
				
		assertEquals("String is valid", serialize1, BooleanProperty.class.getSimpleName());
		assertEquals("String is valid", serialize2, Category.class.getSimpleName());
		
		assertThat("String is now contained", im.getImportedClasses(), hasItems(BooleanProperty.class.getName(), Category.class.getName()));
	}

	@Test
	public void testRegisterClass() {
		assertTrue("Registration is still empty", im.getImportedClasses().isEmpty());
				
		im.register(BooleanProperty.class);
		im.register(Category.class);
				
		assertThat("String is now contained", im.getImportedClasses(), hasItems(BooleanProperty.class.getName(), Category.class.getName()));
	}
	
	@Test
	public void testRegisterClassCheckPackage() {
		im.setPackage("de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions");
		assertTrue("Registration is still empty", im.getImportedClasses().isEmpty());
		
		im.register(BooleanProperty.class);
		assertTrue("Didn't get registered because in same package", im.getImportedClasses().isEmpty());
		
		im.register(Category.class);
		assertFalse("Did get registered", im.getImportedClasses().isEmpty());
	}

	@Test
	public void testRegisterString() {		
		assertThat("String is not yet contained", im.getImportedClasses(), not(hasItems(TEST_STRING)));
		
		im.register(TEST_STRING);
		
		assertThat("String is now contained", im.getImportedClasses(), hasItems(TEST_STRING));
	}
	
	@Test
	public void testRegisterStringCheckPackage() {		
		im.setPackage("de.dlr.virsat");
		assertTrue("Registration is still empty", im.getImportedClasses().isEmpty());
		
		im.register(TEST_STRING);
		assertTrue("Didn't get registered because in same package", im.getImportedClasses().isEmpty());
	}
}
