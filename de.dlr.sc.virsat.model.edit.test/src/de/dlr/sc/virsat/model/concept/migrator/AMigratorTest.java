/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.migrator;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * Tests the abstract class AMigrator
 * @author muel_s8
 *
 */

public class AMigratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test implementation of AMigrator
	 * @author muel_s8
	 *
	 */
	class TestMigrator extends AMigrator {
		
		boolean calledAddStructuralElement = false;
		boolean calledChangeStructuralElement = false;
		boolean calledDeleteStructuralElement = false;
		boolean calledAddCategory = false;
		boolean calledChangeCatgeory = false;
		boolean calledDeleteCategory = false;
		boolean calledAddProperty = false;
		boolean calledChangeProperty = false;
		boolean calledDeleteProperty = false;
		
		@Override
		protected void addStructuralElement(Diff diff, Match match, StructuralElement structuralElement) {
			calledAddStructuralElement = true;
			super.addStructuralElement(diff, match, structuralElement);
		}
		
		@Override
		protected void changeStructuralElement(Diff diff, Match match, StructuralElement structuralElement) {
			calledChangeStructuralElement = true;
			super.changeStructuralElement(diff, match, structuralElement);
		}
		
		@Override
		protected void deleteStructuralElement(Diff diff, Match match, StructuralElement structuralElement) {
			calledDeleteStructuralElement = true;
			super.deleteStructuralElement(diff, match, structuralElement);
		}
		
		@Override
		protected void addCategory(Diff diff, Match match, Category category) {
			calledAddCategory = true;
			super.addCategory(diff, match, category);
		}
		
		@Override
		protected void changeCategory(Diff diff, Match match, Category category) {
			calledChangeCatgeory = true;
			super.changeCategory(diff, match, category);
		}
		
		@Override
		protected void deleteCategory(Diff diff, Match match, Category category) {
			calledDeleteCategory = true;
			super.deleteCategory(diff, match, category);
		}
		
		@Override
		protected void addProperty(Diff diff, Match match, AProperty property) {
			calledAddProperty = true;
			super.addProperty(diff, match, property);
		}
		
		@Override
		protected void changeProperty(Diff diff, Match match, AProperty property) {
			calledChangeProperty = true;
			super.changeProperty(diff, match, property);
		}
		
		@Override
		protected void deleteProperty(Diff diff, Match match, AProperty property) {
			calledDeleteProperty = true;
			super.deleteProperty(diff, match, property);
		}
		
	};
	
	@Test
	public void testMigrateAddStructuralElement() {
		Concept conceptPrevious = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptPrevious.setName("de.dlr.test.concept.migrate.se");
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement se1Previous = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement se1Current = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement se1Next = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement se2Next = StructuralFactory.eINSTANCE.createStructuralElement();
		
		se1Previous.setName("Type1");
		se1Current.setName("Type1");
		se1Next.setName("Type1");
		se2Next.setName("Type2");
		
		conceptPrevious.getStructuralElements().add(se1Previous);
		conceptCurrent.getStructuralElements().add(se1Current);
		conceptNext.getStructuralElements().add(se1Next);
		conceptNext.getStructuralElements().add(se2Next);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Adding Structural Element", migrator.calledAddStructuralElement);
		assertEquals("Structural Element has been merged into concept", conceptCurrent.getStructuralElements().size(), conceptNext.getStructuralElements().size());
		
		StructuralElement seMergedIn1 = conceptCurrent.getStructuralElements().get(0);
		StructuralElement seMergedIn2 = conceptCurrent.getStructuralElements().get(1);
	
		assertEquals("Merged in Structural Element has correct ID", se1Next.getName(), seMergedIn1.getName());
		assertEquals("Merged in Structural Element has correct ID", se2Next.getName(), seMergedIn2.getName());
	}
	
	@Test
	public void testMigrateChangeStructuralElement() {
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement seCurrent = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seNext = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seCurrent.setName("Type1");
		seNext.setName("Type1");
		
		conceptCurrent.getStructuralElements().add(seCurrent);
		conceptNext.getStructuralElements().add(seNext);
		
		seCurrent.setIsRootStructuralElement(false);
		seCurrent.getCanInheritFrom().add(seCurrent);
		seNext.setIsRootStructuralElement(true);
		seNext.getApplicableFor().add(seNext);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Changing StructuralElement", migrator.calledChangeStructuralElement);
		assertFalse("Didnt Call Method for Deleting StructuralElement", migrator.calledDeleteStructuralElement);
		assertFalse("Didnt Call Method for Adding StructuralElement", migrator.calledAddStructuralElement);
		assertEquals("Structural Element has been updated correctly", seNext.isIsRootStructuralElement(), seCurrent.isIsRootStructuralElement());
		assertTrue("Structural Element has been updated correctly", seCurrent.getCanInheritFrom().isEmpty());
		assertThat("Structural Element has been updated correctly", seCurrent.getApplicableFor(), hasItem(seCurrent));
	}

	@Test
	public void testMigrateChangeIdStructuralElement() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement se2Current = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement se1Next = StructuralFactory.eINSTANCE.createStructuralElement();
		
		se2Current.setName("Type2");
		se1Next.setName("Type1");
		
		se2Current.setIsRootStructuralElement(true);
		se1Next.setIsRootStructuralElement(true);
		
		conceptCurrent.getStructuralElements().add(se2Current);
		conceptNext.getStructuralElements().add(se1Next);
		
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance sei2Current = si.generateInstance(se2Current, "SeiType2");
		
		repository.getRootEntities().add(sei2Current);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator() {
			@Override
			protected void registerOldToNewIds() {
				oldToNewObjectIds.put("de.dlr.test.concept.migrate.se.Type2", "de.dlr.test.concept.migrate.se.Type1");
			}
		};
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Changing Structural Element", migrator.calledChangeStructuralElement);
		assertThat("StructuralElementInstances have been left in peace", repository.getRootEntities(), hasItem(sei2Current));
		assertEquals("The Type of the Sei has not changed in respect of its instance", se2Current, sei2Current.getType());
		assertEquals("The name of the structural eleemnt has been changed correctly", se1Next.getName(), se2Current.getName());
	}
	
	@Test
	public void testMigrateDeleteStructuralElement() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement se1Current = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement se2Current = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement se1Next = StructuralFactory.eINSTANCE.createStructuralElement();
		
		se1Current.setName("Type1");
		se2Current.setName("Type2");
		se1Next.setName("Type1");
		
		se1Current.setIsRootStructuralElement(true);
		se2Current.setIsRootStructuralElement(true);
		se1Next.setIsRootStructuralElement(true);
		
		conceptCurrent.getStructuralElements().add(se1Current);
		conceptCurrent.getStructuralElements().add(se2Current);
		conceptNext.getStructuralElements().add(se1Next);
		
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance sei1Current = si.generateInstance(se1Current, "SeiType1");
		StructuralElementInstance sei2Current = si.generateInstance(se2Current, "SeiType2");
		
		repository.getRootEntities().add(sei1Current);
		repository.getRootEntities().add(sei2Current);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Deleting Structural Element", migrator.calledDeleteStructuralElement);
		assertEquals("Structural Element has been removed from concept", conceptCurrent.getStructuralElements().size(), conceptNext.getStructuralElements().size());
	
		assertThat("StructuralElementInstances have been cleaned up properly", repository.getRootEntities(), hasItem(sei1Current));
		assertThat("StructuralElementInstances have been cleaned up properly", repository.getRootEntities(), not(hasItem(sei2Current)));
	}
	
	@Test
	public void testMigrateAddCategory() {
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		Category cat1Current = CategoriesFactory.eINSTANCE.createCategory();
		Category cat1Next = CategoriesFactory.eINSTANCE.createCategory();
		Category cat2Next = CategoriesFactory.eINSTANCE.createCategory();
		
		cat1Current.setName("Type1");
		cat1Next.setName("Type1");
		cat2Next.setName("Type2");
		
		conceptCurrent.getCategories().add(cat1Current);
		conceptNext.getCategories().add(cat1Next);
		conceptNext.getCategories().add(cat2Next);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Adding Category", migrator.calledAddCategory);
		assertEquals("Category has been merged into concept", conceptCurrent.getStructuralElements().size(), conceptNext.getStructuralElements().size());
		
		Category cat1MergedIn = conceptCurrent.getCategories().get(0);
		Category cat2MergedIn = conceptCurrent.getCategories().get(1);
	
		assertEquals("Merged in Category has correct ID", cat1Next.getName(), cat1MergedIn.getName());
		assertEquals("Merged in Category has correct ID", cat2Next.getName(), cat2MergedIn.getName());
	}

	@Test
	public void testMigrateChangeCategory() {
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement seCurrent = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seNext = StructuralFactory.eINSTANCE.createStructuralElement();
		Category catCurrent = CategoriesFactory.eINSTANCE.createCategory();
		Category catNext = CategoriesFactory.eINSTANCE.createCategory();
		
		seCurrent.setName("StructuralElement");
		seNext.setName("StructuralElement");
		catCurrent.setName("Type1");
		catNext.setName("Type1");
		
		conceptCurrent.getStructuralElements().add(seCurrent);
		conceptNext.getStructuralElements().add(seNext);
		conceptCurrent.getCategories().add(catCurrent);
		conceptNext.getCategories().add(catNext);
		
		catCurrent.setIsApplicableForAll(true);
		catNext.setIsApplicableForAll(false);
		catNext.getApplicableFor().add(seNext);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Changing Category", migrator.calledChangeCatgeory);
		assertFalse("Didnt Call Method for Deleting Category", migrator.calledDeleteCategory);
		assertFalse("Didnt Call Method for Adding Category", migrator.calledAddCategory);
		assertEquals("Category has been updated correctly", catNext.isIsApplicableForAll(), catCurrent.isIsApplicableForAll());
		assertThat("Category has been updated correctly", catCurrent.getApplicableFor(), hasItem(seCurrent));
	}
	
	@Test
	public void testMigrateChangeIdCategory() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement seCurrent = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seNext = StructuralFactory.eINSTANCE.createStructuralElement();
		Category cat1Current = CategoriesFactory.eINSTANCE.createCategory();
		Category cat1Next = CategoriesFactory.eINSTANCE.createCategory();
		Category cat2Current = CategoriesFactory.eINSTANCE.createCategory();
		Category cat2Next = CategoriesFactory.eINSTANCE.createCategory();
		
		seCurrent.setName("StructuralElement");
		seNext.setName("StructuralElement");
		cat1Current.setName("Type1");
		cat1Next.setName("Type2");
		cat2Current.setName("ReferencingCategory");
		cat2Next.setName("ReferencingCategory");
		
		cat1Current.setIsApplicableForAll(true);
		cat1Next.setIsApplicableForAll(true);
		cat2Current.setIsApplicableForAll(true);
		cat2Next.setIsApplicableForAll(true);
		
		conceptCurrent.getStructuralElements().add(seCurrent);
		conceptNext.getStructuralElements().add(seNext);
		conceptCurrent.getCategories().add(cat1Current);
		conceptNext.getCategories().add(cat1Next);
		conceptCurrent.getCategories().add(cat2Current);
		conceptNext.getCategories().add(cat2Next);
		
		ReferenceProperty rpCurrent = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		ReferenceProperty rpNext = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		
		rpCurrent.setName("ReferenceProperty");
		rpNext.setName("ReferenceProperty");
		rpCurrent.setReferenceType(cat1Current);
		rpNext.setReferenceType(cat1Next);
		
		cat2Current.getProperties().add(rpCurrent);
		cat2Next.getProperties().add(rpNext);
		
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance seiCurrent = si.generateInstance(seCurrent, "SeiType1");
		
		CategoryInstantiator ci = new CategoryInstantiator();
		CategoryAssignment ca1Current = ci.generateInstance(cat1Current, "CaReferenced");
		CategoryAssignment ca2Current = ci.generateInstance(cat2Current, "CaReferencing");
		
		seiCurrent.getCategoryAssignments().add(ca1Current);
		seiCurrent.getCategoryAssignments().add(ca2Current);
		
		ReferencePropertyInstance rpi = (ReferencePropertyInstance) ca2Current.getPropertyInstances().get(0);
		rpi.setReference(ca1Current);
		
		repository.getRootEntities().add(seiCurrent);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator() {
			@Override
			protected void registerOldToNewIds() {
				oldToNewObjectIds.put("de.dlr.test.concept.migrate.se.Type2", "de.dlr.test.concept.migrate.se.Type1");
			}
		};
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Changing Category", migrator.calledChangeCatgeory);
		assertFalse("Didnt Call Method for Deleting Category", migrator.calledDeleteCategory);
		assertFalse("Didnt Call Method for Adding Category", migrator.calledAddCategory);
		assertEquals("Category has been updated correctly", cat1Next.getName(), cat1Current.getName());
		assertEquals("CategoryAssignment has been updated correctly", cat1Current, ca1Current.getType());
		assertEquals("Reference is still intact", ca1Current, rpi.getReference());
	}
	
	@Test
	public void testMigrateDeleteCategory() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement seCurrent = StructuralFactory.eINSTANCE.createStructuralElement();
		Category cat1Current = CategoriesFactory.eINSTANCE.createCategory();
		Category cat2Current = CategoriesFactory.eINSTANCE.createCategory();
		StructuralElement seNext = StructuralFactory.eINSTANCE.createStructuralElement();
		Category cat1Next = CategoriesFactory.eINSTANCE.createCategory();
		
		seCurrent.setName("StructuralElement");
		seNext.setName("StructuralElement");
		cat1Current.setName("Type1");
		cat2Current.setName("Type2");
		cat1Next.setName("Type1");
		
		seCurrent.setIsRootStructuralElement(true);
		seNext.setIsRootStructuralElement(true);
		
		cat1Current.setIsApplicableForAll(true);
		cat2Current.setIsApplicableForAll(true);
		cat1Next.setIsApplicableForAll(true);
		
		conceptCurrent.getStructuralElements().add(seCurrent);
		conceptCurrent.getCategories().add(cat1Current);
		conceptCurrent.getCategories().add(cat2Current);
		conceptNext.getStructuralElements().add(seNext);
		conceptNext.getCategories().add(cat1Next);
		
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance seiCurrent = si.generateInstance(seCurrent, "SeiType1");
		
		CategoryInstantiator ci = new CategoryInstantiator();
		CategoryAssignment ca1Current = ci.generateInstance(cat1Current, "CaType1");
		CategoryAssignment ca2Current = ci.generateInstance(cat2Current, "CaType2");
		
		seiCurrent.getCategoryAssignments().add(ca1Current);
		seiCurrent.getCategoryAssignments().add(ca2Current);
		
		repository.getRootEntities().add(seiCurrent);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Deleting Category", migrator.calledDeleteCategory);
		assertEquals("Category has been merged removed from concept", conceptCurrent.getCategories().size(), conceptNext.getCategories().size());
	
		assertThat("CategoryAssignments have been cleaned up properly", seiCurrent.getCategoryAssignments(), hasItem(ca1Current));
		assertThat("CategoryAssignments have been cleaned up properly", seiCurrent.getCategoryAssignments(), not(hasItem(ca2Current)));
	}
	
	@Test
	public void testMigrateAddProperty() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement seCurrent = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seNext = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seCurrent.setName("StructuralElement");
		seNext.setName("StructuralElement");
		seCurrent.setIsRootStructuralElement(true);
		seNext.setIsRootStructuralElement(true);
		
		Category catCurrent = CategoriesFactory.eINSTANCE.createCategory();
		Category catNext = CategoriesFactory.eINSTANCE.createCategory();
		
		catCurrent.setName("Category");
		catNext.setName("Category");
		catCurrent.setIsApplicableForAll(true);
		catNext.setIsApplicableForAll(true);
		
		BooleanProperty prop1Current = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		BooleanProperty prop1Next = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		BooleanProperty prop2Next = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		
		prop1Current.setName("Type1");
		prop1Next.setName("Type1");
		prop2Next.setName("Type2");
		
		catCurrent.getProperties().add(prop1Current);
		catNext.getProperties().add(prop1Next);
		catNext.getProperties().add(prop2Next);
		
		conceptCurrent.getCategories().add(catCurrent);
		conceptNext.getCategories().add(catNext);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance seiCurrent = si.generateInstance(seCurrent, "StructuralElementInstance");
		
		CategoryInstantiator ci = new CategoryInstantiator();
		CategoryAssignment caCurrent = ci.generateInstance(catCurrent, "CategoryAssignment");
		
		seiCurrent.getCategoryAssignments().add(caCurrent);
		
		repository.getRootEntities().add(seiCurrent);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Adding Category", migrator.calledAddProperty);
		assertEquals("Property has been merged into category", catCurrent.getProperties().size(), catNext.getProperties().size());
		
		AProperty prop1MergedIn = catCurrent.getProperties().get(0);
		AProperty prop2MergedIn = catCurrent.getProperties().get(1);
	
		assertEquals("Merged in Category has correct ID", prop1Next.getName(), prop1MergedIn.getName());
		assertEquals("Merged in Category has correct ID", prop2Next.getName(), prop2MergedIn.getName());
		
		assertEquals("CategoryAssignment has been updated correctly", catCurrent.getProperties().size(), caCurrent.getPropertyInstances().size());
		
		APropertyInstance pi2 = caCurrent.getPropertyInstances().get(1);
		
		assertEquals("Newly created property instance has correct type", prop2MergedIn, pi2.getType());
	}
	
	@Test
	public void testMigrateChangeProperty() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement seCurrent = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seNext = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seCurrent.setName("StructuralElement");
		seNext.setName("StructuralElement");
		seCurrent.setIsRootStructuralElement(true);
		seNext.setIsRootStructuralElement(true);
		
		Category catCurrent = CategoriesFactory.eINSTANCE.createCategory();
		Category catNext = CategoriesFactory.eINSTANCE.createCategory();
		
		catCurrent.setName("Category");
		catNext.setName("Category");
		catCurrent.setIsApplicableForAll(true);
		catNext.setIsApplicableForAll(true);
		
		BooleanProperty propCurrent = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		BooleanProperty propNext = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		
		propCurrent.setName("Type1");
		propNext.setName("Type1");
		
		propCurrent.setDefaultValue("False");
		propNext.setDefaultValue("True");
		
		catCurrent.getProperties().add(propCurrent);
		catNext.getProperties().add(propNext);
		
		conceptCurrent.getCategories().add(catCurrent);
		conceptNext.getCategories().add(catNext);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance seiCurrent = si.generateInstance(seCurrent, "StructuralElementInstance");
		
		CategoryInstantiator ci = new CategoryInstantiator();
		CategoryAssignment caCurrent = ci.generateInstance(catCurrent, "CategoryAssignment");
		
		seiCurrent.getCategoryAssignments().add(caCurrent);
		
		repository.getRootEntities().add(seiCurrent);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Adding Category", migrator.calledChangeProperty);
		assertFalse("Didnt Call Method for Adding Category", migrator.calledAddProperty);
		assertFalse("Didnt Call Method for Adding Category", migrator.calledDeleteProperty);
		assertEquals("Property has been updated correctly", propNext.getDefaultValue(), propCurrent.getDefaultValue());
		
	}
	
	@Test
	public void testMigrateDeleteProperty() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		// Concept has to be placed into a repository to simulate it being active
		// Migration assumes concepts to be migrated are active 
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement seCurrent = StructuralFactory.eINSTANCE.createStructuralElement();
		Category catCurrent = CategoriesFactory.eINSTANCE.createCategory();
		StructuralElement seNext = StructuralFactory.eINSTANCE.createStructuralElement();
		Category catNext = CategoriesFactory.eINSTANCE.createCategory();
		BooleanProperty prop1Current = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		BooleanProperty prop2Current = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		BooleanProperty prop1Next = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		
		seCurrent.setName("StructuralElement");
		seNext.setName("StructuralElement");
		catCurrent.setName("Category");
		catNext.setName("Category");
		prop1Current.setName("Type1");
		prop2Current.setName("Type2");
		prop1Next.setName("Type1");
		
		seCurrent.setIsRootStructuralElement(true);
		seNext.setIsRootStructuralElement(true);
		
		catCurrent.setIsApplicableForAll(true);
		catNext.setIsApplicableForAll(true);
		
		conceptCurrent.getStructuralElements().add(seCurrent);
		conceptCurrent.getCategories().add(catCurrent);
		catCurrent.getProperties().add(prop1Current);
		catCurrent.getProperties().add(prop2Current);
		conceptNext.getStructuralElements().add(seNext);
		conceptNext.getCategories().add(catNext);
		catNext.getProperties().add(prop1Next);
		
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance seiCurrent = si.generateInstance(seCurrent, "SeiType1");
		
		CategoryInstantiator ci = new CategoryInstantiator();
		CategoryAssignment ca1Current = ci.generateInstance(catCurrent, "CaType1");
		
		seiCurrent.getCategoryAssignments().add(ca1Current);
		
		repository.getRootEntities().add(seiCurrent);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		assertTrue("Called Method for Deleting Property", migrator.calledDeleteProperty);
		assertEquals("Category has been merged removed from concept", catNext.getProperties().size(), catCurrent.getProperties().size());
		assertThat("Category has been cleaned up properly", catCurrent.getProperties(), hasItem(prop1Current));
		assertThat("Category has been cleaned up properly", catCurrent.getProperties(), not(hasItem(prop2Current)));
		assertEquals("CategoryAssignment has been updated correctly", ca1Current.getPropertyInstances().size(), 1);
	}
	
	@Test
	public void testSetResource() {
		TestMigrator migrator = new TestMigrator();
		final String RESOURCE_PATH = "TEST/concept.xmi";
		migrator.setResource(RESOURCE_PATH);
		assertEquals("Migrator path to concept resource has been set correctly", RESOURCE_PATH, migrator.getResource());
	}
	
	@Test
	public void testGetNewDependencies() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		final String NEW_CONCEPT_DEPENDENCY = "de.dlr.test.concept.bla";
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		ConceptImport conceptImport = ConceptsFactory.eINSTANCE.createConceptImport();
		conceptImport.setImportedNamespace(NEW_CONCEPT_DEPENDENCY + ".*");
		conceptNext.getImports().add(conceptImport);
		
		repository.getActiveConcepts().add(conceptCurrent);
		
		TestMigrator migrator = new TestMigrator();
		Set<String> newDendencies = migrator.getNewDependencies(conceptCurrent, conceptNext);
		
		assertTrue("New dependency should be returned", newDendencies.contains(NEW_CONCEPT_DEPENDENCY));
	}

}
