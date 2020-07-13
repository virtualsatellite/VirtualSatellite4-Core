/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;

/**
 * Test Cases for the EnumProeprtyhelper
 * @author fisc_ph
 *
 */
public class EnumPropertyHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEnumValues() {
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		EnumValueDefinition evd2 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		
		evd1.setName("VAL1");
		evd2.setName("VAL2");
		
		evd1.setValue("123");
		evd2.setValue("345");
		
		EnumProperty ep = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		ep.getValues().add(evd1);
		ep.getValues().add(evd2);
		
		List<String> values = new EnumPropertyHelper().getEnumValues(ep, true);
		
		final int EXPECTED_VALUES = 3;
		assertEquals("Returned correct amount of entries", EXPECTED_VALUES, values.size());
		
		assertThat("Correct content", values, hasItems("123", "345"));
	}
	
	@Test
	public void testGetEnumNames() {
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		EnumValueDefinition evd2 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		
		evd1.setName("VAL1");
		evd2.setName("VAL2");
		
		evd1.setValue("123");
		evd2.setValue("345");
		
		EnumProperty ep = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		ep.getValues().add(evd1);
		ep.getValues().add(evd2);
		
		List<String> values = new EnumPropertyHelper().getEnumNames(ep, true);
		
		final int EXPECTED_VALUES = 3;
		assertEquals("Returned correct amount of entries", EXPECTED_VALUES, values.size());
		
		assertThat("Correct content", values, hasItems("VAL1", "VAL2"));
	}
	
	@Test
	public void testGetEnumValueDefinitionString() {
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		EnumValueDefinition evd2 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		
		evd1.setName("VAL1");
		evd2.setName("VAL2");
		
		evd1.setValue("123");
		evd2.setValue("345");
		
		EnumProperty ep = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		ep.getValues().add(evd1);
		ep.getValues().add(evd2);
		
		List<String> values = new EnumPropertyHelper().getEnumValueDefinitionStrings(ep, true);
		
		final int EXPECTED_VALUES = 3;
		assertEquals("Returned correct amount of entries", EXPECTED_VALUES, values.size());
		
		assertThat("Correct content", values, hasItems("VAL1=123", "VAL2=345"));
	}

	@Test
	public void testGetEvdForName() {
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		EnumValueDefinition evd2 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		
		evd1.setName("VAL1");
		evd2.setName("VAL2");
		
		evd1.setValue("123");
		evd1.setValue("345");
		
		EnumProperty ep = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		ep.getValues().add(evd1);
		ep.getValues().add(evd2);
		
		EnumValueDefinition getEvd1 =  new EnumPropertyHelper().getEvdForName(ep, "VAL1");
		EnumValueDefinition getEvd2 =  new EnumPropertyHelper().getEvdForName(ep, "VAL2");
		EnumValueDefinition getEvdN =  new EnumPropertyHelper().getEvdForName(ep, "VAL3");
		
		assertEquals("Enum correct", evd1, getEvd1);
		assertEquals("Enum correct", evd2, getEvd2);
		assertNull("Got null as expected", getEvdN);
	}
	
	@Test
	public void testGetDmfEEnumName() {
		EnumProperty ep = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		ep.setName("smallFirstLetter");
		
		assertEquals("Created Correct Enum Name", "EnumSmallFirstLetter", new EnumPropertyHelper().getDmfEEnumName(ep));
	}
}
