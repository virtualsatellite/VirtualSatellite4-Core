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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

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
 * test class for checking the implementation Of the Copy Cut Paste Commands.
 * This class focuses on copy and paste of PIs in AIs
 * @author fisc_ph
 *
 */
public class VirSatClipboardCommandAcceptanceTest  extends AConceptTestCase  {

	private Concept concept;
	
	@Before
	public void setup() {
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Method to create a test property
	 * @param ai the AI in which to create a new property
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
	public void testCopyCutPasteIntrinsicProperties() {
		// Cut Copy and Paste of Intrinsic properties in a CA should not work
		// removing or adding a PI does not make sense
		TestCategoryAllProperty tcAllProperty = new TestCategoryAllProperty(concept);
	
		APropertyInstance pi = tcAllProperty.getTestIntBean().getTypeInstance();
	
		// Copying may make sense in case the property is copied into a dynamic array
		Command command = VirSatCopyToClipboardCommand.create(editingDomain, Collections.singleton(pi));
		assertTrue("Command should not execute", command.canExecute());
		command.execute();
		
		// Accordingly the paste should fail here
		command = VirSatPasteFromClipboardCommand.create(editingDomain, pi);
		assertFalse("Command should not execute", command.canExecute());

		command = VirSatCutToClipboardCommand.create(editingDomain, Collections.singleton(pi));
		assertFalse("Command should not execute", command.canExecute());

		command = VirSatPasteFromClipboardCommand.create(editingDomain, pi);
		assertFalse("Command should not execute", command.canExecute());

		command = VirSatPasteFromClipboardCommand.create(editingDomain, tcAllProperty.getTypeInstance());
		assertFalse("Command should not execute", command.canExecute());
	}
	
	@Test
	public void testCopyPasteSameArrayInstance() {
		TestCategoryIntrinsicArray tcIntrinsicArray = new TestCategoryIntrinsicArray(concept);
		
		ArrayInstance ai = tcIntrinsicArray.getTestStringArrayDynamicBean().getArrayInstance();
		BeanPropertyString prop1 = createNewStringProperty(ai);
		tcIntrinsicArray.getTestStringArrayDynamicBean().add(prop1);

		Command command = VirSatCopyToClipboardCommand.create(editingDomain, Collections.singleton(prop1.getTypeInstance()));
		assertTrue("The Copy Command can be executed on a PI within an Array", command.canExecute());
		command.execute();
		
		command = VirSatPasteFromClipboardCommand.create(editingDomain, prop1.getTypeInstance());
		assertTrue("The Paste Command can be executed on a PI within an Array", command.canExecute());
		command.execute();
		
		
		command = VirSatPasteFromClipboardCommand.create(editingDomain, ai);
		assertTrue("The Paste Command can be executed on the Array Instance", command.canExecute());
		command.execute();
		
		// CHECKSTYLE:OFF
		assertEquals("Array now contains three elements", 3, ai.getArrayInstances().size());
		// CHECKSTYLE:ON
	}
	
	@Test
	public void testCopyCutPasteAcrossArrayInstances() {
		TestCategoryIntrinsicArray tcIntrinsicArray = new TestCategoryIntrinsicArray(concept);

		ValuePropertyInstance vpi = tcIntrinsicArray.getTestStringArrayStaticBean().get(0).getTypeInstance();
		
		ArrayInstance aiDynamic = tcIntrinsicArray.getTestStringArrayDynamicBean().getArrayInstance();

		// Copy paste from Static to dynamic should work
		Command command = VirSatCopyToClipboardCommand.create(editingDomain, Collections.singleton(vpi));
		assertTrue("The Copy Command can be executed on a PI within an Array", command.canExecute());
		command.execute();
		
		command = VirSatPasteFromClipboardCommand.create(editingDomain, aiDynamic);
		assertTrue("The Paste Command can be executed on a PI within an Array", command.canExecute());
		command.execute();
		
		// CHECKSTYLE:OFF
		assertEquals("Array now contains three elements", 1, aiDynamic.getArrayInstances().size());
		// CHECKSTYLE:ON
		
		// Cut Paste from Static to dynamic should not work
		command = VirSatCutToClipboardCommand.create(editingDomain, Collections.singleton(vpi));
		assertFalse("Cannot Cut from Static Array", command.canExecute());
		command.execute();
		
		// Now Copy And Paste from a Second dynamic array
		TestCategoryIntrinsicArray tcIntrinsicArray2 = new TestCategoryIntrinsicArray(concept);
		ArrayInstance ai2 = tcIntrinsicArray2.getTestStringArrayDynamicBean().getArrayInstance();
		BeanPropertyString prop1 = createNewStringProperty(ai2);
		tcIntrinsicArray2.getTestStringArrayDynamicBean().add(prop1);

		command = VirSatCopyToClipboardCommand.create(editingDomain, Collections.singleton(prop1.getTypeInstance()));
		assertTrue("The Copy Command can be executed on a PI within an Array", command.canExecute());
		command.execute();
		
		command = VirSatPasteFromClipboardCommand.create(editingDomain, aiDynamic);
		assertTrue("The Paste Command can be executed on a PI within an Array", command.canExecute());
		command.execute();
		
		// CHECKSTYLE:OFF
		assertEquals("Array now contains three elements", 2, aiDynamic.getArrayInstances().size());
		// CHECKSTYLE:ON

		// Now Cut And Paste from a Second dynamic array
		command = VirSatCutToClipboardCommand.create(editingDomain, Collections.singleton(prop1.getTypeInstance()));
		assertTrue("The Copy Command can be executed on a PI within an Array", command.canExecute());
		command.execute();
		
		command = VirSatPasteFromClipboardCommand.create(editingDomain, aiDynamic);
		assertTrue("The Paste Command can be executed on a PI within an Array", command.canExecute());
		command.execute();
		
		// CHECKSTYLE:OFF
		assertEquals("Array now contains three elements", 0, ai2.getArrayInstances().size());
		assertEquals("Array now contains three elements", 3, aiDynamic.getArrayInstances().size());
		// CHECKSTYLE:ON
	}
}
