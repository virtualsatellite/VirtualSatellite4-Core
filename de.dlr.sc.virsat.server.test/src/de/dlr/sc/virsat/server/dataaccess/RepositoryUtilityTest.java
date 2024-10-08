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
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
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
		
		testProperty.setName("SomeProperty");
		testPropertyInstance.setType(testProperty);
		
		testCategory.setIsApplicableForAll(true);
		testCategory.getProperties().add(testProperty);
		
		testCa.setType(testCategory);
		testCa.getPropertyInstances().add(testPropertyInstance);
		testCa.setName("SomeCategory");
		
		testSe.setIsRootStructuralElement(true);
		testSe.setName("SomeSe");
		
		rs.getAndAddStructuralElementInstanceResource(testSei);
		testSei.setType(testSe);
		testSei.getCategoryAssignments().add(testCa);
		testSei.getCategoryAssignments();
		
		testConcept.getStructuralElements().add(testSe);
		testConcept.setName("de.dlr.sc.virsat.server.dataaccess.test.concept");
		
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
	
	@Test
	public void testFindUnit() {
		AUnit unit = RepositoryUtility.findUnit("", repository);
		assertNull(unit);
		
		SimpleUnit testUnit = QudvFactory.eINSTANCE.createSimpleUnit();
		repository.getUnitManagement().getSystemOfUnit().getUnit().add(testUnit);
		unit = RepositoryUtility.findUnit(testUnit.getUuid().toString(), repository);
		assertEquals(testUnit, unit);
	}
	
	@Test
	public void testFindQuantityKind() {
		AQuantityKind quantityKind = RepositoryUtility.findQuantityKind("", repository);
		assertNull(quantityKind);
		
		SimpleQuantityKind testQuantityKind = QudvFactory.eINSTANCE.createSimpleQuantityKind();
		repository.getUnitManagement().getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().add(testQuantityKind);
		quantityKind = RepositoryUtility.findQuantityKind(testQuantityKind.getUuid().toString(), repository);
		assertEquals(testQuantityKind, quantityKind);
	}
	
	@Test
	public void testFindPrefix() {
		Prefix prefix = RepositoryUtility.findPrefix("", repository);
		assertNull(prefix);
		
		Prefix testPrefix = QudvFactory.eINSTANCE.createPrefix();
		repository.getUnitManagement().getSystemOfUnit().getPrefix().add(testPrefix);
		prefix = RepositoryUtility.findPrefix(testPrefix.getUuid().toString(), repository);
		assertEquals(testPrefix, prefix);
	}
	
	@Test
	public void testFindUnitFactor() {
		UnitFactor unitFactor = RepositoryUtility.findUnitFactor("", repository);
		assertNull(unitFactor);
		
		UnitFactor testUnitFactor = QudvFactory.eINSTANCE.createUnitFactor();
		DerivedUnit testUnit = QudvFactory.eINSTANCE.createDerivedUnit();
		testUnit.getFactor().add(testUnitFactor);
		repository.getUnitManagement().getSystemOfUnit().getUnit().add(testUnit);
		
		unitFactor = RepositoryUtility.findUnitFactor(testUnitFactor.getUuid().toString(), repository);
		assertEquals(testUnitFactor, unitFactor);
	}
	
	@Test
	public void testFindQuantityKindFactor() {
		QuantityKindFactor quantityKindFactor = RepositoryUtility.findQuantityKindFactor("", repository);
		assertNull(quantityKindFactor);
		
		QuantityKindFactor testQuantityKindFactor = QudvFactory.eINSTANCE.createQuantityKindFactor();
		DerivedQuantityKind testQuantityKind = QudvFactory.eINSTANCE.createDerivedQuantityKind();
		testQuantityKind.getFactor().add(testQuantityKindFactor);
		repository.getUnitManagement().getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().add(testQuantityKind);
		
		quantityKindFactor = RepositoryUtility.findQuantityKindFactor(testQuantityKindFactor.getUuid().toString(), repository);
		assertEquals(testQuantityKindFactor, quantityKindFactor);
	}

}
