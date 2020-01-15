/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;

/**
 * 
 * @author fisc_ph
 *
 */
public class PropertyInstanceValueSwitchTest {

	private PropertyInstanceValueSwitch pivSwitch;
	private static final String ELEMENT_NAME = "Name";
	
	@Before
	public void setUp() throws Exception {
		pivSwitch = new PropertyInstanceValueSwitch();
	}

	@Test
	public void testCaseReferencePropertyInstanceToPropertyInstance() {
		CategoryAssignment referenceTarget = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		referenceTarget.setName(ELEMENT_NAME);
		ReferencePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		rpi.setReference(referenceTarget);
		
		ATypeInstance getDisplayedType = pivSwitch.doSwitch(rpi);
		
		assertEquals("Got the referenced property", referenceTarget, getDisplayedType);
		
		String labelDefault = pivSwitch.getValueString(rpi);
		final String expected = ELEMENT_NAME + " - " + ActiveConceptHelper.getContainerQualifedNameForInstance(referenceTarget);
		assertEquals(labelDefault, expected);
		
		pivSwitch.setShowLocationForReferenceValues(false);
		String labelWithoutLocation = pivSwitch.getValueString(rpi);
		final String expectedWithoutLocation = ELEMENT_NAME;
		assertEquals(labelWithoutLocation, expectedWithoutLocation);
	}
	
	@Test
	public void testCaseReferencePropertyInstanceToCategoryAssignemnt() {
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ReferencePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		rpi.setReference(ca);
		
		ATypeInstance getDisplayedType = pivSwitch.doSwitch(rpi);
		
		assertEquals("Got the referenced CategoryAssignment", ca, getDisplayedType);
	}

	@Test
	public void testGetValueString() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();

		String noValue = new PropertyInstanceValueSwitch().getValueString(vpi);
		assertEquals("Safe access returned correct value", PropertyInstanceValueSwitch.EMPTY_VALUE, noValue);

		final String TEST_VALUE = "123hallo";
		
		vpi.setValue(TEST_VALUE);
		String specifiedValue = new PropertyInstanceValueSwitch().getValueString(vpi);
		assertEquals("Safe access returned correct value", TEST_VALUE, specifiedValue);
	}
	
	@Test
	public void testGetValueStringOfFloat() {
		FloatProperty fp = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpi.setType(fp);

		String noValue = new PropertyInstanceValueSwitch().getValueString(vpi);
		assertEquals("Safe access returned correct value", PropertyInstanceValueSwitch.EMPTY_VALUE, noValue);

		final String TEST_VALUE = "123.45678";
		final String EXPECTED_VALUE = "123.457";
		final String EXPECTED_VALUE_2 = "123.46";
		
		vpi.setValue(TEST_VALUE);
		String specifiedValue = new PropertyInstanceValueSwitch().getValueString(vpi);
		assertEquals("Safe access returned correct value", EXPECTED_VALUE, specifiedValue);

		String specifiedValue2 = new PropertyInstanceValueSwitch(2).getValueString(vpi);
		assertEquals("Safe access returned correct value", EXPECTED_VALUE_2, specifiedValue2);
	}

	@Test
	public void testGetValueStringWithUnit() {
		UnitValuePropertyInstance uvpi = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();

		final String EXPECTED_NOVALUE_WITH_UNIT = PropertyInstanceValueSwitch.EMPTY_VALUE + " ";
		String noValue = new PropertyInstanceValueSwitch().getValueString(uvpi);
		assertEquals("Safe access returned correct value", EXPECTED_NOVALUE_WITH_UNIT, noValue);

		final String TEST_VALUE = "123hallo";
		final String TEST_UNIT_NAME = "meter";
		final String TEST_UNIT_SYMBOL = "m";
		final String EXPECTED_RESULT = "123hallo [m]";
		AUnit unit = QudvFactory.eINSTANCE.createSimpleUnit();
		unit.setName(TEST_UNIT_NAME);
		unit.setSymbol(TEST_UNIT_SYMBOL);
		uvpi.setValue(TEST_VALUE);
		
		uvpi.setUnit(unit);
		String specifiedValue = new PropertyInstanceValueSwitch().getValueString(uvpi);
		assertEquals("Safe access returned correct value", EXPECTED_RESULT, specifiedValue);
	}
	
	@Test
	public void testEnumValueStringWithUnit() {
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		EnumValueDefinition evd2 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();

		evd1.setName("HIGH");
		evd2.setName("LOW");
		evd1.setValue("12");
		evd2.setValue("3");
		
		EnumProperty enumProperty = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		enumProperty.getValues().add(evd1);
		enumProperty.getValues().add(evd2);
		enumProperty.setDefaultValue(evd2);
	
		final String EXPECTED_NOVALUE_WITH_UNIT = PropertyInstanceValueSwitch.EMPTY_VALUE + " ";
		EnumUnitPropertyInstance eupi = PropertyinstancesFactory.eINSTANCE.createEnumUnitPropertyInstance();
		eupi.setType(enumProperty);
		
		String noValue = new PropertyInstanceValueSwitch().getValueString(eupi);
		assertEquals("Safe access returned correct value", EXPECTED_NOVALUE_WITH_UNIT, noValue);

		final String TEST_UNIT_NAME = "meter";
		final String TEST_UNIT_SYMBOL = "m";
		final String EXPECTED_RESULT = "LOW=3 [m]";
		AUnit unit = QudvFactory.eINSTANCE.createSimpleUnit();
		unit.setName(TEST_UNIT_NAME);
		unit.setSymbol(TEST_UNIT_SYMBOL);
		eupi.setValue(evd2);
		
		eupi.setUnit(unit);
		String specifiedValue = new PropertyInstanceValueSwitch().getValueString(eupi);
		assertEquals("Safe access returned correct value", EXPECTED_RESULT, specifiedValue);
	}
	
	@Test
	public void testGetValueStringOfCategoryAssignment() {
	
		String validName = "caName";
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca.setName(validName);
		
		String returndValueString = new PropertyInstanceValueSwitch().getValueString(ca);
		assertEquals("it returnd the category assignment name as value string", validName, returndValueString);
	}
	
	@Test
	public void testDefaultCaseEObject() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		
		ATypeInstance getDisplayedType = pivSwitch.doSwitch(vpi);
		
		assertEquals("Got the referenced property", vpi, getDisplayedType);
	}
}
