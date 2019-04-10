/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * 
 * @author fisc_ph
 *
 */
public class TestStructuralElementInstanceTest extends AConceptTestCase {

	private TestCategoryBeanA tcA1;
	private TestCategoryBeanA tcA2;
	private TestCategoryBeanA tcA3;
	private TestCategoryBeanB tcB1;
	private Concept concept;
	
	@Before
	public void setup() {
		prepareEditingDomain();
		// Load the concept to create the test object
		
		concept = loadConceptFromPlugin();
		tcA1 = new TestCategoryBeanA(concept);
		tcA2 = new TestCategoryBeanA(concept);
		tcA3 = new TestCategoryBeanA(concept);
		tcB1 = new TestCategoryBeanB(concept);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}
	@Test
	public void testRemoveAll() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		tse.add(tcA1);
		tse.add(tcA2);
		tse.add(tcA3);
		
		tse.add(tcB1);
				
		assertTrue("StructualElementInstace constains the CA of Category A1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
		assertTrue("StructualElementInstace constains the CA of Category A2", sei.getCategoryAssignments().contains(tcA2.getTypeInstance()));
		assertTrue("StructualElementInstace constains the CA of Category A3", sei.getCategoryAssignments().contains(tcA3.getTypeInstance()));
		assertTrue("StructualElementInstace constains the CA of Category B1", sei.getCategoryAssignments().contains(tcB1.getTypeInstance()));

		
		tse.removeAll(tcA1.getClass());
		
		assertFalse("Does not contain A1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
		assertFalse("Does not contain A2", sei.getCategoryAssignments().contains(tcA2.getTypeInstance()));
		assertFalse("Does not contain A3", sei.getCategoryAssignments().contains(tcA3.getTypeInstance()));
		assertTrue("Does contain B1", sei.getCategoryAssignments().contains(tcB1.getATypeInstance()));
	}
	
	@Test
	public void testAddCategoryAssignment() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		tse.add(tcA1);
		tse.add(tcA2);
		
		assertThat("StructuralElementInstance contains the categoryassignemnt of Categoy A1", sei.getCategoryAssignments(), hasItem(tcA1.getTypeInstance()));
		assertThat("StructuralElementInstance contains the categoryassignemnt of Categoy A1", sei.getCategoryAssignments(), hasItem(tcA2.getTypeInstance()));

		assertFalse("Does not contain b1", sei.getCategoryAssignments().contains(tcB1.getTypeInstance()));
	}
	
	@Test
	public void testAddCategoryAssignmentEditingDomain() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		Command addCommand = tse.add(editingDomain, tcA1);
		assertFalse("Does not contain a1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
		
		addCommand.execute();
		assertTrue("Does now contain a1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
	}
	@Test
	public void testRemoveAllCategoryAssignment() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		tse.add(tcA1);
		tse.add(tcA2);
		tse.add(tcA3);
				
		assertTrue("StructualElementInstace constains the CA of Category A1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
		assertTrue("StructualElementInstace constains the CA of Category A2", sei.getCategoryAssignments().contains(tcA2.getTypeInstance()));
		assertTrue("StructualElementInstace constains the CA of Category A3", sei.getCategoryAssignments().contains(tcA3.getTypeInstance()));

		List<IBeanCategoryAssignment> list = new ArrayList<>();
		list.add(tcA1);
		list.add(tcA2);
		
		tse.removeAllCategoryAssignment(list);
		
		
		
		assertFalse("Does not contain A1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
		assertFalse("Does not contain A2", sei.getCategoryAssignments().contains(tcA2.getTypeInstance()));
		assertTrue("Does contain A3", sei.getCategoryAssignments().contains(tcA3.getTypeInstance()));
	}
	@Test
	public void testRemoveAllStructuralElementInstance() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		TestStructuralElement childTse1 = new TestStructuralElement(concept);
		TestStructuralElement childTse2 = new TestStructuralElement(concept);
		TestStructuralElement childTse3 = new TestStructuralElement(concept);
		
		tse.add(childTse1);
		tse.add(childTse2);
		tse.add(childTse3);
		
		assertTrue("StructuralElementInstance contains correct Child", sei.getChildren().contains(childTse1.getStructuralElementInstance()));
		assertTrue("StructuralElementInstance contains correct Child", sei.getChildren().contains(childTse2.getStructuralElementInstance()));
		assertTrue("StructuralElementInstance contains correct Child", sei.getChildren().contains(childTse3.getStructuralElementInstance()));
		
		List<IBeanStructuralElementInstance> beanList = new ArrayList<>();
		beanList.add(childTse1);
		beanList.add(childTse2);
		
		tse.removeAllStructuralElementInstance(beanList);

		assertEquals("Has correct amount of children", 1, sei.getChildren().size());
		assertFalse("StructuralElementInstance does not contain the Child", sei.getChildren().contains(childTse1.getStructuralElementInstance()));
		assertFalse("StructuralElementInstance does not contain the Child", sei.getChildren().contains(childTse2.getStructuralElementInstance()));
		assertTrue("StructuralElementInstance does not contain the Child", sei.getChildren().contains(childTse3.getStructuralElementInstance()));
	}
	@Test
	public void testRemoveCategoryAssignment() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		tse.add(tcA1);
		tse.add(tcA2);
		
		assertThat("StructuralElementInstance contains the categoryassignemnt of Categoy A1", sei.getCategoryAssignments(), hasItem(tcA1.getTypeInstance()));
		assertThat("StructuralElementInstance contains the categoryassignemnt of Categoy A1", sei.getCategoryAssignments(), hasItem(tcA2.getTypeInstance()));

		tse.remove(tcA1);
		
		assertFalse("Does not contain a1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
	}
	
	@Test
	public void testRemoveCategoryAssignmentEditingDomain() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		tse.add(tcA1);
		tse.add(tcA2);
		
		assertThat("StructuralElementInstance contains the categoryassignemnt of Categoy A1", sei.getCategoryAssignments(), hasItem(tcA1.getTypeInstance()));
		assertThat("StructuralElementInstance contains the categoryassignemnt of Categoy A1", sei.getCategoryAssignments(), hasItem(tcA2.getTypeInstance()));

		Command removeCommand = tse.remove(editingDomain, tcA1);
		assertTrue("Does still contain a1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
		
		removeCommand.execute();
		assertFalse("Does not contain a1", sei.getCategoryAssignments().contains(tcA1.getTypeInstance()));
	}
	
	@Test
	public void testGetAllCategoryAssignment() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		
		tse.add(tcA1);
		tse.add(tcA2);
		tse.add(tcB1);

		List<TestCategoryBeanA> tcAs = tse.getAll(TestCategoryBeanA.class);
		
		assertEquals("Contains exactly two elements", 2, tcAs.size());
		assertThat("List contains correct obects", tcAs, hasItems(tcA1, tcA2));
		
		List<TestCategoryBeanB> tcBs = tse.getAll(TestCategoryBeanB.class);
		
		assertEquals("Contains exactly two elements", 1, tcBs.size());
		assertThat("List contains correct obects", tcBs, hasItems(tcB1));
	}
	
	@Test
	public void testGetFirstCategoryAssignment() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		
		tse.add(tcB1);

		TestCategoryBeanA tcA = tse.getFirst(TestCategoryBeanA.class);
		
		assertNull("No element found", tcA);
		
		TestCategoryBeanB tcB = tse.getFirst(TestCategoryBeanB.class);
		
		assertEquals("Contains exactly two elements", tcB1, tcB);
	}

	@Test
	public void testHasCategory() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		tse.add(tcA1);

		assertTrue("SEI bean has CA", tse.hasCategory(TestCategoryBeanA.class));
		assertFalse("SEI bean doesn't have CA", tse.hasCategory(TestCategoryBeanB.class));
	}
	
	@Test
	public void testAddStructuralElementInstanceBean() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		TestStructuralElement childTse1 = new TestStructuralElement(concept);
		TestStructuralElement childTse2 = new TestStructuralElement(concept);
		
		tse.add(childTse1);
		tse.add(childTse2);

		assertEquals("Has correct amount of children", 2, sei.getChildren().size());
		assertThat("StructuralElementInstance contains correct Child", sei.getChildren(), hasItem(childTse1.getStructuralElementInstance()));
		assertThat("StructuralElementInstance contains correct Child", sei.getChildren(), hasItem(childTse2.getStructuralElementInstance()));
	}
	
	@Test
	public void testAddStructuralElementInstanceBeanEditingDomain() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();

		TestStructuralElement childTse1 = new TestStructuralElement(concept);

		Command addCommand = tse.add(editingDomain, childTse1);
		assertFalse("Does not yet contain child", sei.getChildren().contains(childTse1.getStructuralElementInstance()));
		
		addCommand.execute();
		assertTrue("Does now contain child", sei.getChildren().contains(childTse1.getStructuralElementInstance()));
	}
	
	@Test
	public void testRemoveStructuralElementInstanceBean() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		TestStructuralElement childTse1 = new TestStructuralElement(concept);
		TestStructuralElement childTse2 = new TestStructuralElement(concept);
		
		tse.add(childTse1);
		tse.add(childTse2);
		
		assertEquals("Has correct amount of children", 2, sei.getChildren().size());
		assertThat("StructuralElementInstance contains correct Child", sei.getChildren(), hasItem(childTse1.getStructuralElementInstance()));
		assertThat("StructuralElementInstance contains correct Child", sei.getChildren(), hasItem(childTse2.getStructuralElementInstance()));
		
		tse.remove(childTse1);

		assertEquals("Has correct amount of children", 1, sei.getChildren().size());
		assertFalse("StructuralElementInstance does not contain the Child", sei.getChildren().contains(childTse1.getStructuralElementInstance()));
		assertThat("StructuralElementInstance contains correct Child", sei.getChildren(), hasItem(childTse2.getStructuralElementInstance()));
		
	}
	
	@Test
	public void testRemoveStructuralElementInstanceBeanEditingDomain() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		TestStructuralElement childTse1 = new TestStructuralElement(concept);
		TestStructuralElement childTse2 = new TestStructuralElement(concept);
		
		tse.add(childTse1);
		tse.add(childTse2);
		
		assertEquals("Has correct amount of children", 2, sei.getChildren().size());
		assertThat("StructuralElementInstance contains correct Child", sei.getChildren(), hasItem(childTse1.getStructuralElementInstance()));
		assertThat("StructuralElementInstance contains correct Child", sei.getChildren(), hasItem(childTse2.getStructuralElementInstance()));
		
		Command removeCommand = tse.remove(editingDomain, childTse1);
		assertTrue("Does still contain child", sei.getChildren().contains(childTse1.getStructuralElementInstance()));
		
		removeCommand.execute();
		
		assertEquals("Has correct amount of children", 1, sei.getChildren().size());
		assertFalse("StructuralElementInstance does not contain the Child", sei.getChildren().contains(childTse1.getStructuralElementInstance()));
		assertThat("StructuralElementInstance contains correct Child", sei.getChildren(), hasItem(childTse2.getStructuralElementInstance()));
	}
	
	@Test
	public void testGetChildren() {
		TestStructuralElement rootElement = new TestStructuralElement(concept);
		TestStructuralElement child1 = new TestStructuralElement(concept);
		TestStructuralElement child2 = new TestStructuralElement(concept);
		TestStructuralElementOther childOther1 = new TestStructuralElementOther(concept);
		TestStructuralElementOther childOther2 = new TestStructuralElementOther(concept);
		
		rootElement.add(child1);
		rootElement.add(child2);
		rootElement.add(childOther1);
		rootElement.add(childOther2);
		
		List<TestStructuralElement> filteredChildren = rootElement.getChildren(TestStructuralElement.class);
		List<TestStructuralElementOther> filteredChildrenOther = rootElement.getChildren(TestStructuralElementOther.class);
		List<ABeanStructuralElementInstance> allChildren = rootElement.getChildren(ABeanStructuralElementInstance.class);
		
		assertEquals("Correct number of elements", 2, filteredChildren.size());
		assertThat("Correct elements", filteredChildren, hasItems(child1, child2));
		assertThat("Correct elements", filteredChildrenOther, hasItems(childOther1, childOther2));
		assertThat("Correct elements", allChildren, hasItems(child1, child2, childOther1, childOther2));
	}
	
	@Test
	public void testGetDeepChildren() {
		TestStructuralElement rootElement = new TestStructuralElement(concept);
		TestStructuralElement child1 = new TestStructuralElement(concept);
		TestStructuralElement child2 = new TestStructuralElement(concept);
		TestStructuralElementOther childOther1 = new TestStructuralElementOther(concept);
		TestStructuralElementOther childOther2 = new TestStructuralElementOther(concept);
		
		rootElement.add(child1);
		child1.add(child2);
		rootElement.add(childOther1);
		childOther1.add(childOther2);
		
		List<TestStructuralElement> filteredChildren = rootElement.getDeepChildren(TestStructuralElement.class);
		List<TestStructuralElementOther> filteredChildrenOther = rootElement.getDeepChildren(TestStructuralElementOther.class);
		
		assertEquals("Correct number of elements", 2, filteredChildren.size());
		assertThat("Correct elements", filteredChildren, hasItems(child1, child2));
		assertThat("Correct elements", filteredChildrenOther, hasItems(childOther1, childOther2));
	}
	
	@Test
	public void testAddInheritanceLink() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();

		assertEquals("Has correct amount of children", 0, sei.getSuperSeis().size());

		TestStructuralElement childTse1 = new TestStructuralElement(concept);
		TestStructuralElement childTse2 = new TestStructuralElement(concept);
		
		tse.addSuperSei(childTse1);
		tse.addSuperSei(childTse2);
		
		assertEquals("Has correct amount of children", 2, sei.getSuperSeis().size());
		assertThat("StructuralElementInstance contains correct Child", sei.getSuperSeis(), hasItem(childTse1.getStructuralElementInstance()));
		assertThat("StructuralElementInstance contains correct Child", sei.getSuperSeis(), hasItem(childTse2.getStructuralElementInstance()));
	}

	@Test
	public void testAddInheritanceLinkCommand() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();

		assertEquals("Has correct amount of children", 0, sei.getSuperSeis().size());

		TestStructuralElement childTse1 = new TestStructuralElement(concept);
		TestStructuralElement childTse2 = new TestStructuralElement(concept);
		
		tse.addSuperSei(editingDomain, childTse1).execute();
		tse.addSuperSei(editingDomain, childTse2).execute();
		
		assertEquals("Has correct amount of children", 2, sei.getSuperSeis().size());
		assertThat("StructuralElementInstance contains correct Child", sei.getSuperSeis(), hasItem(childTse1.getStructuralElementInstance()));
		assertThat("StructuralElementInstance contains correct Child", sei.getSuperSeis(), hasItem(childTse2.getStructuralElementInstance()));
	}

	@Test
	public void testRemoveInheritanceLink() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();

		assertEquals("Has correct amount of children", 0, sei.getSuperSeis().size());

		TestStructuralElement childTse1 = new TestStructuralElement(concept);
		TestStructuralElement childTse2 = new TestStructuralElement(concept);
		
		tse.addSuperSei(childTse1);
		tse.addSuperSei(childTse2);
		
		assertEquals("Has correct amount of children", 2, sei.getSuperSeis().size());
		
		tse.removeSuperSei(childTse1);

		assertEquals("Has correct amount of children", 1, sei.getSuperSeis().size());
		assertThat("StructuralElementInstance contains correct Child", sei.getSuperSeis(), hasItem(childTse2.getStructuralElementInstance()));
	}
	
	@Test
	public void testRemoveInheritanceLinkCommand() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();

		assertEquals("Has correct amount of children", 0, sei.getSuperSeis().size());

		TestStructuralElement childTse1 = new TestStructuralElement(concept);
		TestStructuralElement childTse2 = new TestStructuralElement(concept);
		
		tse.addSuperSei(childTse1);
		tse.addSuperSei(childTse2);
		
		assertEquals("Has correct amount of children", 2, sei.getSuperSeis().size());
		
		tse.removeSuperSei(editingDomain, childTse1).execute();

		assertEquals("Has correct amount of children", 1, sei.getSuperSeis().size());
		assertThat("StructuralElementInstance contains correct Child", sei.getSuperSeis(), hasItem(childTse2.getStructuralElementInstance()));
	}
	
	@Test
	public void testGetSuperSeis() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		StructuralElementInstance sei = tse.getStructuralElementInstance();

		assertEquals("Has correct amount of children", 0, sei.getSuperSeis().size());

		TestStructuralElement superTse1 = new TestStructuralElement(concept);
		TestStructuralElement superTse2 = new TestStructuralElement(concept);
		
		tse.addSuperSei(superTse1);
		tse.addSuperSei(superTse2);
		
		assertEquals("Has correct amount of children", 2, sei.getSuperSeis().size());
		
		List<TestStructuralElement> superSeiBeans = tse.getSuperSeis(TestStructuralElement.class);
		assertEquals("Correct amount of super Sei beans", 2, superSeiBeans.size());
		assertThat("List ahs correct elements of super Sei Benas", superSeiBeans, hasItems(superTse1, superTse2));
	}
	
	@Test
	public void testDelete() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		TestStructuralElement superTse = new TestStructuralElement(concept);
		tse.addSuperSei(superTse);
		
		Resource resource = editingDomain.getResourceSet().createResource(URI.createURI("TSE"));
		resource.getContents().add(tse.getStructuralElementInstance());
		resource.getContents().add(superTse.getStructuralElementInstance());
		
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		assertEquals("SEI has not been deleted", 1, sei.getSuperSeis().size());
		
		superTse.delete();
		
		assertEquals("SEI has been deleted", 0, sei.getSuperSeis().size());
	}
	
	@Test
	public void testDeleteCommand() {
		TestStructuralElement tse = new TestStructuralElement(concept);
		TestStructuralElement superTse = new TestStructuralElement(concept);
		tse.addSuperSei(superTse);
		
		Resource resource = editingDomain.getResourceSet().createResource(URI.createURI("TSE"));
		resource.getContents().add(tse.getStructuralElementInstance());
		resource.getContents().add(superTse.getStructuralElementInstance());
		
		StructuralElementInstance sei = tse.getStructuralElementInstance();
		
		assertEquals("SEI has not been deleted", 1, sei.getSuperSeis().size());
		
		Command command = superTse.delete(editingDomain);
		assertTrue("Command can be executed", command.canExecute());
		
		command.execute();
		
		assertEquals("SEI has been deleted", 0, sei.getSuperSeis().size());
	}
}
