/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;

/**
 * This Test Class checks if the validation check for allowing to set references is working.
 * @author muel_s8
 *
 */
public class DVLMReferenceCheckTest {

	private ReferencePropertyInstance referencingPropertyInstance;
	private CategoryAssignment referencedInstanceAcceptOne;
	private CategoryAssignment referencedInstanceAcceptTwo;
	private CategoryAssignment referencedInstanceFail;
	
	@Before
	public void setUp() throws Exception {
		Category cAcceptOne = CategoriesFactory.eINSTANCE.createCategory();
		Category cAcceptTwo = CategoriesFactory.eINSTANCE.createCategory();
		Category cFail = CategoriesFactory.eINSTANCE.createCategory();
		
		cAcceptTwo.setExtendsCategory(cAcceptOne);
		
		ReferenceProperty referenceProperty = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		referenceProperty.setReferenceType(cAcceptOne);
		referencingPropertyInstance = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		referencingPropertyInstance.setType(referenceProperty);
		
		CategoryInstantiator caInstantiator = new CategoryInstantiator();
		
		referencedInstanceAcceptOne = caInstantiator.generateInstance(cAcceptOne, "acceptOne");
		referencedInstanceAcceptTwo = caInstantiator.generateInstance(cAcceptTwo, "acceptTwo");
		referencedInstanceFail = caInstantiator.generateInstance(cFail, "fail");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValid() {
		assertTrue("Object can be referenced", DVLMReferenceCheck.isValid(referencingPropertyInstance, referencedInstanceAcceptOne));
		assertTrue("Object can be referenced", DVLMReferenceCheck.isValid(referencingPropertyInstance, referencedInstanceAcceptTwo));
		assertFalse("Object cannot be referenced", DVLMReferenceCheck.isValid(referencingPropertyInstance, referencedInstanceFail));
	
		
	}
}
