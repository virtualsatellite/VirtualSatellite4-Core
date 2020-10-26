/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class RepositoryUtilityTest extends AProjectTestCase {

	private StructuralElementInstance testSei;
	private CategoryAssignment testCa;
	private StructuralElement testSe;
	private Category testCategory;
	private Concept testConcept;
	private StringProperty testProperty;
	private ValuePropertyInstance testPropertyInstance;
	private Discipline testDiscipline;

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addResourceSetAndRepository();
		
		testConcept = ConceptsFactory.eINSTANCE.createConcept();
		testSe = StructuralFactory.eINSTANCE.createStructuralElement();
		testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		testCategory = CategoriesFactory.eINSTANCE.createCategory();
		testCa = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		testProperty = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		testPropertyInstance = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		
		testPropertyInstance.setType(testProperty);
		
		testCategory.setIsApplicableForAll(true);
		testCategory.getProperties().add(testProperty);
		
		testCa.setType(testCategory);
		testCa.getPropertyInstances().add(testPropertyInstance);
		
		testSe.setIsRootStructuralElement(true);
		
		rs.getAndAddStructuralElementInstanceResource(testSei);
		testSei.setType(testSe);
		testSei.getCategoryAssignments().add(testCa);
		testSei.getCategoryAssignments();
		
		testConcept.getStructuralElements().add(testSe);
		
		testDiscipline = RolesFactory.eINSTANCE.createDiscipline();
		
		repository.getActiveConcepts().add(testConcept);
		repository.getRootEntities().add(testSei);
		repository.getRoleManagement().getDisciplines().add(testDiscipline);
	}

	@Test
	public void testFindSei() throws CoreException {
		StructuralElementInstance sei = RepositoryUtility.findSei("", repository);
		assertNull(sei);
		
		sei = RepositoryUtility.findSei(testSei.getUuid().toString(), repository);
		assertEquals(testSei, sei);
	}

	@Test
	public void testFindCa() throws CoreException {
		CategoryAssignment ca = RepositoryUtility.findCa("", repository);
		assertNull(ca);
		
		ca = RepositoryUtility.findCa(testCa.getUuid().toString(), repository);
		assertEquals(testCa, ca);
	}

	@Test
	public void testFindProperty() {
		APropertyInstance property = RepositoryUtility.findProperty("", repository);
		assertNull(property);
		
		property = RepositoryUtility.findProperty(testPropertyInstance.getUuid().toString(), repository);
		assertEquals(testPropertyInstance, property);
	}

	@Test
	public void testFindDiscipline() {
		Discipline discipline = RepositoryUtility.findDiscipline("", repository);
		assertNull(discipline);
		
		discipline = RepositoryUtility.findDiscipline(testDiscipline.getUuid().toString(), repository);
		assertEquals(testDiscipline, discipline);
	}

	@Test
	public void testFindSe() {
		StructuralElement se = RepositoryUtility.findSe("", repository);
		assertNull(se);
		
		se = RepositoryUtility.findSe(testSe.getFullQualifiedName(), repository);
		assertEquals(testSe, se);
	}
	
	@Test
	public void testFindObjectById() {
		APropertyInstance property = (APropertyInstance) RepositoryUtility.findObjectById("", repository);
		assertNull(property);
		
		property = (APropertyInstance) RepositoryUtility.findObjectById(testPropertyInstance.getUuid().toString(), repository);
		assertEquals(testPropertyInstance, property);
	}

}
