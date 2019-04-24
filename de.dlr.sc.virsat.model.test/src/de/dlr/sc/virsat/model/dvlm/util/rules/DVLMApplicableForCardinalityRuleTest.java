/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util.rules;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Implementation of the testCases for the cardinality checks
 * @author fisc_ph
 *
 */
public class DVLMApplicableForCardinalityRuleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValid() {
		StructuralElement seA = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seB = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seC = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seA.setName("seA");
		seB.setName("seB");
		seC.setName("seC");
		
		seB.setIsApplicableForAll(true);
		seC.setIsApplicableForAll(true);

		StructuralElementInstance seiA = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiB1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiB2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiC1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiC2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiA.setType(seA);
		seiB1.setType(seB);
		seiB2.setType(seB);
		seiC1.setType(seC);
		seiC2.setType(seC);
		
		// Check SEIs for Cardinality 1
		seiA.getChildren().clear();
		seB.setCardinality(1);
		assertTrue("SeiA has no children yet", seiA.getChildren().isEmpty());
		
		seiA.getChildren().add(seiB1);
		seiA.getChildren().add(seiB2);
		seiA.getChildren().add(seiC1);
		seiA.getChildren().add(seiC2);
		assertThat("Only B1 is in the List", seiA.getChildren(), allOf(hasItems(seiB1, seiC1, seiC2), not(hasItems(seiB2))));

		// Check SEIs for Cardinality None
		seiA.getChildren().clear();
		seB.setCardinality(0);
		assertTrue("SeiA has no children yet", seiA.getChildren().isEmpty());
		
		seiA.getChildren().add(seiB1);
		seiA.getChildren().add(seiB2);
		seiA.getChildren().add(seiC1);
		seiA.getChildren().add(seiC2);
		assertThat("All seis are present", seiA.getChildren(), hasItems(seiB1, seiB2, seiC1, seiC2));

		// Now test for the categories
				
		Category cX = CategoriesFactory.eINSTANCE.createCategory();
		Category cY = CategoriesFactory.eINSTANCE.createCategory();
		CategoryAssignment caX1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		CategoryAssignment caX2 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		CategoryAssignment caY1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		CategoryAssignment caY2 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		cX.setName("cX");
		cY.setName("cY");
		caX1.setName("caX1");
		caX2.setName("caX2");
		caY1.setName("caY1");
		caY2.setName("caY2");
		
		caX1.setType(cX);
		caX2.setType(cX);
		caY1.setType(cY);
		caY2.setType(cY);
		
		cX.setIsApplicableForAll(true);
		cY.setIsApplicableForAll(true);
		
		// Check SEIs for Cardinality 1
		seiA.getCategoryAssignments().clear();
		cX.setCardinality(1);
		assertTrue("SeiA has no CAs yet", seiA.getCategoryAssignments().isEmpty());
		
		seiA.getCategoryAssignments().add(caX1);
		seiA.getCategoryAssignments().add(caX2);
		seiA.getCategoryAssignments().add(caY1);
		seiA.getCategoryAssignments().add(caY2);
		assertThat("Only X1 is in the List", seiA.getCategoryAssignments(), allOf(hasItems(caX1, caY1, caY2), not(hasItems(caX2))));

		// Check SEIs for Cardinality 0
		seiA.getCategoryAssignments().clear();
		cX.setCardinality(0);
		assertTrue("SeiA has no CAs yet", seiA.getCategoryAssignments().isEmpty());
		
		seiA.getCategoryAssignments().add(caX1);
		seiA.getCategoryAssignments().add(caX2);
		seiA.getCategoryAssignments().add(caY1);
		seiA.getCategoryAssignments().add(caY2);
		assertThat("All are in the list", seiA.getCategoryAssignments(), hasItems(caX1, caX2, caY1, caY2));
	
		// Check with inheritance
		cY.setExtendsCategory(cX);
		seiA.getCategoryAssignments().clear();
		cX.setCardinality(1);
		cY.setCardinality(0);
		assertTrue("SeiA has no CAs yet", seiA.getCategoryAssignments().isEmpty());
		
		seiA.getCategoryAssignments().add(caX1);
		seiA.getCategoryAssignments().add(caX2);
		seiA.getCategoryAssignments().add(caY1);
		seiA.getCategoryAssignments().add(caY2);
		assertThat("X1 and Y1 are in the list", seiA.getCategoryAssignments(), allOf(hasItems(caX1, caY1), not(hasItems(caX2, caY2))));
		
		cY.setExtendsCategory(cX);
		seiA.getCategoryAssignments().clear();
		cX.setCardinality(2);
		cY.setCardinality(1);
		assertTrue("SeiA has no CAs yet", seiA.getCategoryAssignments().isEmpty());
		
		seiA.getCategoryAssignments().add(caX1);
		seiA.getCategoryAssignments().add(caX2);
		seiA.getCategoryAssignments().add(caY1);
		seiA.getCategoryAssignments().add(caY2);
		assertThat("Only Y2 ia not in the list", seiA.getCategoryAssignments(), allOf(hasItems(caX1, caX2, caY1), not(hasItems(caY2))));
	}

	@Test
	public void testCanExecute() {
		StructuralElement seA = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seB = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seA.setName("seA");
		seB.setName("seB");
		
		seB.setIsApplicableForAll(true);

		StructuralElementInstance seiA = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiB = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiA.setType(seA);
		seiB.setType(seB);

		Category c = CategoriesFactory.eINSTANCE.createCategory();
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		c.setName("c");
		ca.setName("ca");
		ca.setType(c);
		c.setIsApplicableForAll(true);
		
		// First check if the rule is applicable for adding a SEI into another SEI
		assertTrue("Rule can be executed", new DVLMApplicableForCardinalityRule().canExecute(seiA, seiB, true));
		assertTrue("Rule can be executed", new DVLMApplicableForCardinalityRule().canExecute(seiA, seiB, false));

		// Now check for CAs
		assertTrue("Rule can be executed", new DVLMApplicableForCardinalityRule().canExecute(seiA, ca, true));
		assertTrue("Rule can be executed", new DVLMApplicableForCardinalityRule().canExecute(seiA, ca, false));
		
		//now check for something that does not make sense
		assertFalse("Rule can not be executed", new DVLMApplicableForCardinalityRule().canExecute(ca, seiA, true));
		assertFalse("Rule can not be executed", new DVLMApplicableForCardinalityRule().canExecute(ca, ca, false));		
	}
}
