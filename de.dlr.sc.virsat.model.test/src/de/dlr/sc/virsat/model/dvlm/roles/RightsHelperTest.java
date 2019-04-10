/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.roles;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * test class to test the rights managment in VirSat
 * @author scha_vo
 *
 */
public class RightsHelperTest {

	@Before
	public void setUp() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testGetDiscipline() {
		Category c = CategoriesFactory.eINSTANCE.createCategory();
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		sei.setType(se);
		ca.setType(c);
		
		c.getApplicableFor().add(se);
		
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		sei.getCategoryAssignments().add(ca);
		ca.getPropertyInstances().add(vpi);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		sei.setAssignedDiscipline(discipline);
		
		assertEquals("Got correct Discipline from sc", discipline, RightsHelper.getDiscipline(sei));
		assertEquals("Got correct Discipline from ca", discipline, RightsHelper.getDiscipline(ca));
		assertEquals("Got correct Discipline from vpi", discipline, RightsHelper.getDiscipline(vpi));

		// Create a filing case which should return null
		sei.getCategoryAssignments().clear();
		assertNull("Failure case operated as expected", RightsHelper.getDiscipline(ca));
	}

	@Test
	public void testHasWritePermission() {
		final int TWO_HUNDRED = 200;
		
		UserRegistry.getInstance().setUser("TestUser", TWO_HUNDRED);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setUser("TestUser");
		
		// Now for this test the StructuralElementInstance and the CatgeoryAssignment need to have a sort of Typeing
		// Otherwise the new lists will refuse to allow placing objects which are not "ApplicableFor"
		Category c = CategoriesFactory.eINSTANCE.createCategory();
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		sei.setType(se);
		ca.setType(c);
		
		c.getApplicableFor().add(se);
		
		// Now continue adding properties to the ca
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		sei.getCategoryAssignments().add(ca);
		ca.getPropertyInstances().add(vpi);
		
		sei.setAssignedDiscipline(discipline);
		
		assertTrue("Yes we can write to the object", RightsHelper.hasWritePermission(sei));
		assertTrue("Yes we can write to the object", RightsHelper.hasWritePermission(ca));
		assertTrue("Yes we can write to the object", RightsHelper.hasWritePermission(vpi));

		discipline.setUser("TheBadGuy");

		assertFalse("No we cannot write to the object", RightsHelper.hasWritePermission(sei));
		
		assertFalse("No we cannot write to the object", RightsHelper.hasWritePermission(ca));
		
		assertFalse("No we cannot write to the object", RightsHelper.hasWritePermission(vpi));
		
		sei.getCategoryAssignments().remove(ca);
		// We should have write permission on ca itself since it has no longer a link to the sei to which
		// we have no write permission
		assertTrue("Yes we can write to the object", RightsHelper.hasWritePermission(ca));
	}
}
