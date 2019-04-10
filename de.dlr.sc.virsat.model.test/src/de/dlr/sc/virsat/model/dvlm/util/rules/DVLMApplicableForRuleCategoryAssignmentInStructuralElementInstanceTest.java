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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This class checks the ApplicableForRule for Adding CategoryAssignments to a StructuralELementInstance
 * @author fisc_ph
 *
 */
public class DVLMApplicableForRuleCategoryAssignmentInStructuralElementInstanceTest {

	private StructuralElement seAccept;
	private StructuralElement seFail;
	
	private StructuralElementInstance seiRoot;
	
	@Before
	public void setUp() throws Exception {
		seAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		seFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seAccept.getApplicableFor().add(seAccept);
		seFail.getApplicableFor().add(seFail);
		
		seiRoot = new StructuralInstantiator().generateInstance(seAccept, "Se_Root");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testisValid() {
		Category categoryForAccept = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForFail = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForAll = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForNone = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForInheritsForAccept = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForInheritsForFail = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForInheritsForAll = CategoriesFactory.eINSTANCE.createCategory();
		
		categoryForAccept.getApplicableFor().add(seAccept);
		categoryForFail.getApplicableFor().add(seFail);
		categoryForAll.setIsApplicableForAll(true);
		categoryForInheritsForAccept.setExtendsCategory(categoryForAccept);
		categoryForInheritsForFail.setExtendsCategory(categoryForFail);
		categoryForInheritsForAll.setExtendsCategory(categoryForAll);
		
		CategoryAssignment caForAccept = new CategoryInstantiator().generateInstance(categoryForAccept, "CaAccept");
		CategoryAssignment caForFail = new CategoryInstantiator().generateInstance(categoryForFail, "CaFail");
		CategoryAssignment caForAll = new CategoryInstantiator().generateInstance(categoryForAll, "CaAcceptAll");
		CategoryAssignment caForNone = new CategoryInstantiator().generateInstance(categoryForNone, "CaAcceptNone");
		CategoryAssignment caForForInheritsForAccept = new CategoryInstantiator().generateInstance(categoryForInheritsForAccept, "CaForForInheritsForAccept");
		CategoryAssignment caForForInheritsForFail = new CategoryInstantiator().generateInstance(categoryForInheritsForFail, "CaForForInheritsForFail");
		CategoryAssignment caForInheritsForAll = new CategoryInstantiator().generateInstance(categoryForInheritsForAll, "CaForInheritsForAll");
		
		DVLMApplicableForRuleCategoryAssignmentInStructuralElementInstance rule = new DVLMApplicableForRuleCategoryAssignmentInStructuralElementInstance();
		
		assertTrue("Object can be assigned", rule.isValid(seiRoot, caForAccept));
		assertTrue("Object can be assigned", rule.isValid(seiRoot, caForAll));
		assertFalse("Object can not be assigned", rule.isValid(seiRoot, caForFail));
		assertFalse("Object can not be assigned", rule.isValid(seiRoot, caForNone));
		assertTrue("Object can be assigned", rule.isValid(seiRoot, caForForInheritsForAccept));
		assertFalse("Object can not be assigned", rule.isValid(seiRoot, caForForInheritsForFail));
		assertTrue("Object can be assigned", rule.isValid(seiRoot, caForInheritsForAll));
	}

	@Test
	public void testCanExecute() {
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		Object wrongObject = new Object();

		DVLMApplicableForRuleCategoryAssignmentInStructuralElementInstance rule = new DVLMApplicableForRuleCategoryAssignmentInStructuralElementInstance();
		
		assertTrue("This rule is well called and works on containments", rule.canExecute(seiRoot, ca, true));
		
		assertFalse("This rule is is not needed for non containments", rule.canExecute(seiRoot, ca, false));
		assertFalse("This rule is is for category assignemnts only ", rule.canExecute(seiRoot, wrongObject, true));
		assertFalse("This rule is is for StructuralElementInstances only ", rule.canExecute(wrongObject, ca, true));
	}
}
