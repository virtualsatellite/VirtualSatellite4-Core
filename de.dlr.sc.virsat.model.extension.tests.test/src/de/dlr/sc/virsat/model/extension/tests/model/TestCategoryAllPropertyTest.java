/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * 
 * @author fisc_ph
 *
 */
public class TestCategoryAllPropertyTest extends AConceptTestCase {

	private TestCategoryAllProperty tcAllProperty;
	
	@Before
	public void setup() {
		prepareEditingDomain();

		Concept concept = loadConceptFromPlugin();
		tcAllProperty = new TestCategoryAllProperty(concept);
	}
	
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testStringProperty() {
		final String TEST_VALUE_ONE = "ValOne";
		tcAllProperty.setTestString(TEST_VALUE_ONE);
		
		String vpiValue = tcAllProperty.getTestStringBean().getTypeInstance().getValue();
		String beanValue = tcAllProperty.getTestString();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue);
	}
	
	@Test
	public void testStringPropertyEditingDomain() {
		final String TEST_VALUE_ONE = "ValTwo";
		Command cmd = tcAllProperty.setTestString(editingDomain, TEST_VALUE_ONE);
		editingDomain.getCommandStack().execute(cmd);
		
		String vpiValue = tcAllProperty.getTestStringBean().getTypeInstance().getValue();
		String beanValue = tcAllProperty.getTestString();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue);
	}
	
	@Test
	public void testIntProperty() {
		final long TEST_VALUE_ONE = 123;
		tcAllProperty.setTestInt(TEST_VALUE_ONE);
		
		long vpiValue = Long.parseLong(tcAllProperty.getTestIntBean().getTypeInstance().getValue());
		long beanValue = tcAllProperty.getTestInt();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue);
	}
	
	@Test
	public void testIntPropertyEditingDomain() {
		final long TEST_VALUE_ONE = 234;
		Command cmd = tcAllProperty.setTestInt(editingDomain, TEST_VALUE_ONE);
		editingDomain.getCommandStack().execute(cmd);
		
		long vpiValue = Long.parseLong(tcAllProperty.getTestIntBean().getTypeInstance().getValue());
		long beanValue = tcAllProperty.getTestInt();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue);
	}
	
	@Test
	public void testBooleanProperty() {
		final boolean TEST_VALUE_ONE = true;
		tcAllProperty.setTestBool(TEST_VALUE_ONE);
		
		boolean vpiValue = Boolean.parseBoolean(tcAllProperty.getTestBoolBean().getTypeInstance().getValue());
		boolean beanValue = tcAllProperty.getTestBool();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue);
	}
	
	@Test
	public void testBooleanPropertyEditingDomain() {
		final boolean TEST_VALUE_ONE = true;
		Command cmd = tcAllProperty.setTestBool(editingDomain, TEST_VALUE_ONE);
		editingDomain.getCommandStack().execute(cmd);
		
		boolean vpiValue = Boolean.parseBoolean(tcAllProperty.getTestBoolBean().getTypeInstance().getValue());
		boolean beanValue = tcAllProperty.getTestBool();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue);
	}
	
	private static final double TEST_EPSILON =  0.0001;
	
	@Test
	public void testFloatProperty() {
		final double TEST_VALUE_ONE = 123.123;
		tcAllProperty.setTestFloat(TEST_VALUE_ONE);
		
		double vpiValue = Float.parseFloat(tcAllProperty.getTestFloatBean().getTypeInstance().getValue());
		double beanValue = tcAllProperty.getTestFloat();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue, TEST_EPSILON);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue, TEST_EPSILON);
	}
	
	@Test
	public void testFloatPropertyGetBaseUnitValue() {
		final double TEST_VALUE_ONE = 123.123;
		tcAllProperty.setTestFloat(TEST_VALUE_ONE);
		
		double vpiValue = Float.parseFloat(tcAllProperty.getTestFloatBean().getTypeInstance().getValue());
		double beanValue = tcAllProperty.getTestFloatBean().getValueToBaseUnit();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue, TEST_EPSILON);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue, TEST_EPSILON);
	}
	
	@Test
	public void testEnumProperty() {
		final String TEST_VALUE = "HIGH";
		final double TEST_VALUE_ENUM = 25;
		tcAllProperty.setTestEnum(TEST_VALUE);
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE, tcAllProperty.getTestEnum());
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ENUM, tcAllProperty.getTestEnumEnum(), TEST_EPSILON);
	}
	
	@Test
	public void testFloatPropertyGetValueWithUnit() {
		final double TEST_VALUE_ONE = 123.123;
		tcAllProperty.setTestFloat(TEST_VALUE_ONE);
		
		double vpiValue = Float.parseFloat(tcAllProperty.getTestFloatBean().getTypeInstance().getValue());
		String beanValue = tcAllProperty.getTestFloatBean().getValueWithUnit();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue, TEST_EPSILON);
		assertEquals("GetValue is receiving correct Value", "123.123 ", beanValue);
	}
	
	@Test
	public void testFloatPropertyEditingDomain() {
		final double TEST_VALUE_ONE = 234.123;
		Command cmd = tcAllProperty.setTestFloat(editingDomain, TEST_VALUE_ONE);
		editingDomain.getCommandStack().execute(cmd);
		
		double vpiValue = Float.parseFloat(tcAllProperty.getTestFloatBean().getTypeInstance().getValue());
		double beanValue = tcAllProperty.getTestFloat();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue, TEST_EPSILON);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue, TEST_EPSILON);
	}
}
