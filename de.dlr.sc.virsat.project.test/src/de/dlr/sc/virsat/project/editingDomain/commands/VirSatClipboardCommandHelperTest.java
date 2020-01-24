/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test Cases for the ClipBoard Command Helper
 */

public class VirSatClipboardCommandHelperTest {

	@Test
	public void testIsValidCollection() {
		List<EObject> objects = new ArrayList<>();
		objects.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		objects.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		objects.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
				
		assertTrue("Is valid collection", VirSatClipboardCommandHelper.isValidCollection(objects));
		
		objects.add(RolesFactory.eINSTANCE.createDiscipline());
		
		assertFalse("Is not a valid collection", VirSatClipboardCommandHelper.isValidCollection(objects));
		
		objects.clear();
		objects.add(RolesFactory.eINSTANCE.createDiscipline());
		objects.add(RolesFactory.eINSTANCE.createDiscipline());
		objects.add(RolesFactory.eINSTANCE.createDiscipline());
		
		assertTrue("Is valid collection", VirSatClipboardCommandHelper.isValidCollection(objects));
	}

	@Test
	public void testIsValidSource() {
		assertTrue("Is a Valid Source Object", VirSatClipboardCommandHelper.isValidSource(CategoriesFactory.eINSTANCE.createCategoryAssignment()));
		assertTrue("Is a Valid Source Object", VirSatClipboardCommandHelper.isValidSource(RolesFactory.eINSTANCE.createDiscipline()));
		assertTrue("Is a Valid Source Object", VirSatClipboardCommandHelper.isValidSource(StructuralFactory.eINSTANCE.createStructuralElementInstance()));

		assertFalse("Is not a Valid Source Object", VirSatClipboardCommandHelper.isValidSource(DVLMFactory.eINSTANCE.createRepository()));
		assertFalse("Is not a Valid Source Object", VirSatClipboardCommandHelper.isValidSource(CategoriesFactory.eINSTANCE.createCategory()));
		assertFalse("Is not a Valid Source Object", VirSatClipboardCommandHelper.isValidSource(StructuralFactory.eINSTANCE.createStructuralElement()));
	}

	@Test
	public void testIsValidTarget() {
		assertTrue("Is a Valid Source Object", VirSatClipboardCommandHelper.isValidTarget(RolesFactory.eINSTANCE.createRoleManagement()));
		assertTrue("Is a Valid Source Object", VirSatClipboardCommandHelper.isValidTarget(StructuralFactory.eINSTANCE.createStructuralElementInstance()));
		assertTrue("Is a Valid Source Object", VirSatClipboardCommandHelper.isValidTarget(DVLMFactory.eINSTANCE.createRepository()));
		
		assertFalse("Is not a Valid Source Object", VirSatClipboardCommandHelper.isValidTarget(CategoriesFactory.eINSTANCE.createCategory()));
		assertFalse("Is not a Valid Source Object", VirSatClipboardCommandHelper.isValidTarget(StructuralFactory.eINSTANCE.createStructuralElement()));
		assertTrue("Is a Valid Source Object", VirSatClipboardCommandHelper.isValidTarget(CategoriesFactory.eINSTANCE.createCategoryAssignment()));
	}

	@Test
	public void testIsCollectionSei() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		List<EObject> disciplines = new ArrayList<>();
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());

		List<EObject> cas = new ArrayList<>();
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		
		assertTrue("Is valid collection", VirSatClipboardCommandHelper.isCollectionSei(seis));
		assertFalse("Is valid collection", VirSatClipboardCommandHelper.isCollectionSei(disciplines));
		assertFalse("Is valid collection", VirSatClipboardCommandHelper.isCollectionSei(cas));
	}
	
	@Test
	public void testIsCollectionPi() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance());

		List<EObject> pis = new ArrayList<>();
		pis.add(PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance());
		pis.add(PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance());
		pis.add(PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance());
		
		assertFalse("Is not valid collection", VirSatClipboardCommandHelper.isCollectionPi(seis));
		assertTrue("Is valid collection", VirSatClipboardCommandHelper.isCollectionPi(pis));
	}

	@Test
	public void testIsCollectionDiscipline() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		List<EObject> disciplines = new ArrayList<>();
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());

		List<EObject> cas = new ArrayList<>();
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		
		assertFalse("Is valid collection", VirSatClipboardCommandHelper.isCollectionDiscipline(seis));
		assertTrue("Is valid collection", VirSatClipboardCommandHelper.isCollectionDiscipline(disciplines));
		assertFalse("Is valid collection", VirSatClipboardCommandHelper.isCollectionDiscipline(cas));
	}

	@Test
	public void testIsCollectionCategoryAssignment() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		List<EObject> disciplines = new ArrayList<>();
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());

		List<EObject> cas = new ArrayList<>();
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		
		assertFalse("Is valid collection", VirSatClipboardCommandHelper.isCollectionCategoryAssignment(seis));
		assertFalse("Is valid collection", VirSatClipboardCommandHelper.isCollectionCategoryAssignment(disciplines));
		assertTrue("Is valid collection", VirSatClipboardCommandHelper.isCollectionCategoryAssignment(cas));
	}

	@Test
	public void testIsCollectionOfType() {
		List<EObject> objects = new ArrayList<>();
		objects.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		objects.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
		objects.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());
				
		assertTrue("Is valid collection", VirSatClipboardCommandHelper.isCollectionOfType(objects, CategoryAssignment.class));
		assertFalse("Is not a valid collection", VirSatClipboardCommandHelper.isCollectionOfType(objects, Category.class));
		
		objects.add(CategoriesFactory.eINSTANCE.createCategory());

		assertFalse("Is not a valid collection", VirSatClipboardCommandHelper.isCollectionOfType(objects, CategoryAssignment.class));
	}
}
