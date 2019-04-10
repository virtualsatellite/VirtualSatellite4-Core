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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * This class tests the manual additions to the generated code of 
 * A TypeInstance such as the method to retrieve its container.
 * @author fisc_ph
 *
 */
public class ATypeInstanceTest {

	private Category testCategory;
	private CategoryAssignment ca;
	
	private IntProperty propertyOne;

	private static final String TEST_PROPERTY_NAME_ONE = "p1";
	private static final String TEST_PROPERTY_NAME_TWO = "p2";

	@Before
	public void setUp() throws Exception {
		testCategory = CategoriesFactory.eINSTANCE.createCategory();

		propertyOne = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propertyOne.setName(TEST_PROPERTY_NAME_ONE);
		testCategory.getProperties().add(propertyOne);
		testCategory.setIsApplicableForAll(true);

		StringProperty propertyTwo = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		propertyTwo.setName(TEST_PROPERTY_NAME_TWO);
		testCategory.getProperties().add(propertyTwo);

		ca = new CategoryInstantiator().generateInstance(testCategory, "TestInstanceName");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCategoryAssignmentContainer() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		APropertyInstance pi1 = ca.getPropertyInstances().get(0);
		APropertyInstance pi2 = ca.getPropertyInstances().get(1);
		
		assertNull("CA is not yet contained", ca.getCategoryAssignmentContainer());
		assertNull("CA is not yet contained", pi1.getCategoryAssignmentContainer());
		assertNull("CA is not yet contained", pi2.getCategoryAssignmentContainer());
		
		sei.getCategoryAssignments().add(ca);
		
		assertEquals("Ca is now contained", sei, ca.getCategoryAssignmentContainer());
		assertEquals("Ca is now contained", sei, pi1.getCategoryAssignmentContainer());
		assertEquals("Ca is now contained", sei, pi2.getCategoryAssignmentContainer());
	}
}
