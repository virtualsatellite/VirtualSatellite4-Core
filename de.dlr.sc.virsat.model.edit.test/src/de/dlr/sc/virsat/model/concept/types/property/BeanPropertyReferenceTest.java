/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

public class BeanPropertyReferenceTest extends ABeanPropertyTest {

	private BeanPropertyReference<CategoryAssignment> beanProperty;
	private CategoryAssignment testCa;
	private ReferencePropertyInstance rpi;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		rpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		beanProperty = new BeanPropertyReference<CategoryAssignment>();
		beanProperty.setTypeInstance(rpi);
		
		testCa = CategoriesFactory.eINSTANCE.createCategoryAssignment();
	}
	
	@Test
	public void testSetValueEditingDomain() {
		Command cmd = beanProperty.setValue(ed, testCa);
		cmd.execute();
		
		assertEquals("Value correctly set", testCa, rpi.getReference());
	}
	
	@Test
	public void testSetValue() {
		beanProperty.setValue(testCa);
		
		assertEquals("Value correctly set", testCa, rpi.getReference());
	}
	
	@Test
	public void testGetValue() {
		rpi.setReference(testCa);
		
		assertEquals("Git correct value", testCa, beanProperty.getValue());
	}
	
	@Test
	public void testConstructor() {
		BeanPropertyReference<CategoryAssignment> beanProperty2 = 
				new BeanPropertyReference<CategoryAssignment>(rpi);
		
		assertEquals("Beans are identical", beanProperty2, beanProperty);
	}
}
