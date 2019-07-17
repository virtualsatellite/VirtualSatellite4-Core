/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;

/**
 * Test cases for the float bean
 * @author fisc_ph
 *
 */
public class BeanPropertyFloatTest extends ABeanPropertyTest {

	private BeanPropertyFloat beanProperty;
	private UnitValuePropertyInstance uvpi;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		uvpi = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		beanProperty = new BeanPropertyFloat();
		beanProperty.setTypeInstance(uvpi);
		UserRegistry.getInstance().setSuperUser(true);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testSetValueEditingDomainLong() {
		final double TEST_FLOAT = 22.56;
		final String TEST_STRING = "22.56";
		
		Command cmd = beanProperty.setValue(ed, TEST_FLOAT);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_STRING, uvpi.getValue());
	}

	@Test
	public void testSetValueLong() {
		final double TEST_FLOAT = 20.56;
		final String TEST_STRING = "20.56";
		
		beanProperty.setValue(TEST_FLOAT);
		
		assertEquals("Value correctly set", TEST_STRING, uvpi.getValue());
	}

	@Test
	public void testGetValue() {
		final double TEST_FLOAT = 21.56;
		final String TEST_STRING = "21.56";
		final double EPSILON = 0.001;
		
		uvpi.setValue(TEST_STRING);
		assertEquals("Value correctly set", TEST_FLOAT, beanProperty.getValue(), EPSILON);

		uvpi.setValue(null);
		assertEquals("Value correctly set", Double.NaN, beanProperty.getValue(), EPSILON);

		uvpi.setValue("");
		assertEquals("Value correctly set", Double.NaN, beanProperty.getValue(), EPSILON);
	}

	@Test
	public void testGetValueToBaseUnit() {
		final double TEST_FLOAT_METER_TO_BASE = 21.56;
		final double TEST_FLOAT_KILOMETER_TO_BASE = 21560;
		final String TEST_STRING = "21.56";
		final double EPSILON = 0.001;

		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		AUnit unitKm = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		AUnit unitM = QudvUnitHelper.getInstance().getUnitByName(sou, "Meter");

		uvpi.setValue(TEST_STRING);
		uvpi.setUnit(unitM);
		
		assertEquals("Value correctly set", TEST_FLOAT_METER_TO_BASE, beanProperty.getValueToBaseUnit(), EPSILON);
		
		uvpi.setUnit(unitKm);
		assertEquals("Value correctly set", TEST_FLOAT_KILOMETER_TO_BASE, beanProperty.getValueToBaseUnit(), EPSILON);
	}

	@Test
	public void testSetValueAsBaseUnit() {
		final double INPUT_VALUE = 12.45;
		final String INPUT_VALUE_AS_M = "12.45";
		final String INPUT_VALUE_AS_KM = "0.01245";
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		AUnit unitKm = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		AUnit unitM = QudvUnitHelper.getInstance().getUnitByName(sou, "Meter");

		uvpi.setUnit(unitM);
		beanProperty.setValueAsBaseUnit(INPUT_VALUE);
		assertEquals("Value correctly set", INPUT_VALUE_AS_M, uvpi.getValue());
		
		uvpi.setUnit(unitKm);
		beanProperty.setValueAsBaseUnit(INPUT_VALUE);
		assertEquals("Value correctly set", INPUT_VALUE_AS_KM, uvpi.getValue());
	}
	
	@Test
	public void testSetValueAsBaseUnitEditingDomain() {
		final double INPUT_VALUE = 12.45;
		final String INPUT_VALUE_AS_KM = "0.01245";
		final String INPUT_VALUE_AS_M = "12.45";
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		AUnit unitKm = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");

		// with no unit we should not get an executable command
		uvpi.setUnit(null);
		Command cmdOk1 = beanProperty.setValueAsBaseUnit(ed, INPUT_VALUE);
		cmdOk1.execute();
		assertEquals("Value correctly set", INPUT_VALUE_AS_M, uvpi.getValue());
		
		uvpi.setUnit(unitKm);
		Command cmdOk2 = beanProperty.setValueAsBaseUnit(ed, INPUT_VALUE);
		cmdOk2.execute();
		assertEquals("Value correctly set", INPUT_VALUE_AS_KM, uvpi.getValue());
	}

	private Repository repo;
	private Concept concept;
	private UnitManagement unitManagement;
	private SystemOfUnits sou;
	
	private Category testCategory;
	
	private FloatProperty propertyOne;
	
	/**
	 * Additional SetUp for some test cases where the full apth from type definitions
	 * to the concept and repository is needed.
	 */
	private void setUpRepo() {
		testCategory = CategoriesFactory.eINSTANCE.createCategory();

		propertyOne = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		testCategory.getProperties().add(propertyOne);
		testCategory.setIsApplicableForAll(true);

		repo = DVLMFactory.eINSTANCE.createRepository();
		concept = ConceptsFactory.eINSTANCE.createConcept();
		unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		
		sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		unitManagement.setSystemOfUnit(sou);
		repo.setUnitManagement(unitManagement);
		concept.getCategories().add(testCategory);
		concept.setName("testConcept");
		repo.getActiveConcepts().add(concept);
		
		uvpi.setType(propertyOne);
	}
	
	@Test
	public void testSetUnitEditingDomain() {
		setUpRepo();
		
		beanProperty.setUnit(ed, "Kilogram").execute();
		assertEquals("Unit has been set correctly", "Kilogram", uvpi.getUnit().getName());
		
		beanProperty.setUnit(ed, "Gram").execute();
		assertEquals("Unit has been changed correctly", "Gram", uvpi.getUnit().getName());

		Command cmd = beanProperty.setUnit(ed, "Gargl");
		assertFalse("The unit has not been changed", cmd.canExecute());
	}

	@Test
	public void testSetUnit() {
		setUpRepo();
		
		boolean changed;
		
		changed = beanProperty.setUnit("Kilogram");
		assertTrue("The unit has been changed", changed);
		assertEquals("Unit has been set correctly", "Kilogram", uvpi.getUnit().getName());
		
		changed = beanProperty.setUnit("Gram");
		assertTrue("The unit has been changed", changed);
		assertEquals("Unit has been changed correctly", "Gram", uvpi.getUnit().getName());

		changed = beanProperty.setUnit("Gargl");
		assertFalse("The unit has not been changed", changed);
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", uvpi.getUnit().getName());
	}
	
	@Test
	public void testGetUnit() {
		setUpRepo();
		beanProperty.setUnit("Kilogram");
		assertEquals("Unit has been set correctly", "Kilogram", uvpi.getUnit().getName());
		assertEquals("Unit has been set correctly", "Kilogram", beanProperty.getUnit());
		
		beanProperty.setUnit("Gram");
		assertEquals("Unit has been changed correctly", "Gram", uvpi.getUnit().getName());
		assertEquals("Unit has been changed correctly", "Gram", beanProperty.getUnit());

		beanProperty.setUnit("Gargl");
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", uvpi.getUnit().getName());
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", beanProperty.getUnit());
	}
}
