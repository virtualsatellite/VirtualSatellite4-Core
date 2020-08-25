/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.command;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;

/**
 * Test Cases for the DeleteCommand in particular on testing to delete PIs
 * @author fisc_ph
 *
 */
public class DeleteStructuralElementInstanceCommandAcceptanceTest extends AConceptTestCase {

	private Concept concept;
	
	@Before
	public void setup() {
		prepareEditingDomain();

		concept = loadConceptFromPlugin();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeletePropertyInstance() {
		TestCategoryAllProperty tcAllProperty = new TestCategoryAllProperty(concept);
		Command command = DeleteStructuralElementInstanceCommand.create(editingDomain, tcAllProperty.getTestBoolBean().getTypeInstance());
		
		assertFalse("The Command for deleting a PI from a CI should fail", command.canExecute());
	}

	/**
	 * Method to create a test property
	 * @param ai Array Instance to be used for crating the property
	 * @return a new BeanPropertyString
	 */
	private BeanPropertyString createNewStringProperty(ArrayInstance ai) {
		CategoryInstantiator ci = new CategoryInstantiator();
		APropertyInstance pi = ci.generateInstance(ai);
		BeanPropertyString newBeanProperty = new BeanPropertyString();
		newBeanProperty.setTypeInstance((ValuePropertyInstance) pi);
		return newBeanProperty;
	}
	
	@Test
	public void testDeletePropertyInstanceIntrinsicArray() {
		TestCategoryIntrinsicArray tcIntrinsicArray = new TestCategoryIntrinsicArray(concept);
		
		ArrayInstance ai = tcIntrinsicArray.getTestStringArrayDynamicBean().getArrayInstance();
		BeanPropertyString prop1 = createNewStringProperty(ai);
		tcIntrinsicArray.getTestStringArrayDynamicBean().add(prop1);

		Command command = DeleteStructuralElementInstanceCommand.create(editingDomain, prop1.getATypeInstance());
		assertTrue("The Command for deleting a PI from a dynamic Intrinsic array should work", command.canExecute());
		
		command = DeleteStructuralElementInstanceCommand.create(editingDomain, tcIntrinsicArray.getTestStringArrayStaticBean().get(1).getTypeInstance());
		assertFalse("You cannot delete from a static array", command.canExecute());
	}
	
}
