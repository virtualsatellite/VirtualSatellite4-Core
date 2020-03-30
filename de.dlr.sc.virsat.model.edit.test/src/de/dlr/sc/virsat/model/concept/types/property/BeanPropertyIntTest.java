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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;

/**
 * test cases for the int bean
 * @author fisc_ph
 *
 */
public class BeanPropertyIntTest extends ABeanPropertyTest {

	private BeanPropertyInt beanProperty;
	private UnitValuePropertyInstance uvpi;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		uvpi = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		beanProperty = new BeanPropertyInt();
		beanProperty.setTypeInstance(uvpi);
		UserRegistry.getInstance().setSuperUser(true);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testSetValueEditingDomainLong() {
		final Long TEST_INT = 22L;
		final String TEST_STRING = "22";
		
		Command cmd = beanProperty.setValue(ed, TEST_INT);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_STRING, uvpi.getValue());
	}

	@Test
	public void testSetValueLong() {
		final Long TEST_INT = 20L;
		final String TEST_STRING = "20";
		
		beanProperty.setValue(TEST_INT);
		
		assertEquals("Value correctly set", TEST_STRING, uvpi.getValue());
	}

	@Test
	public void testGetValue() {
		final Long TEST_INT = 21L;
		final String TEST_STRING = "21";

		uvpi.setValue(TEST_STRING);
		assertEquals("Value correctly set", TEST_INT, beanProperty.getValue());
	}
	
	public void testGetValuenNull() {
		uvpi.setValue(null);
		assertNull(beanProperty.getValue());
	}

	public void testGetValueEmpty() {
		uvpi.setValue("");
		assertNull(beanProperty.getValue());
	}

	@Test(expected = NumberFormatException.class)
	public void testGetInvalidValue() {
		uvpi.setValue("invalid");
		beanProperty.getValue();
	}
	
	private Repository repo;
	private Concept concept;
	private UnitManagement unitManagement;
	private SystemOfUnits sou;
	
	private Category testCategory;
	
	private IntProperty propertyOne;
	
	/**
	 * Additional SetUp for some test cases where the full apth from type definitions
	 * to the concept and repository is needed.
	 */
	private void setUpRepo() {
		testCategory = CategoriesFactory.eINSTANCE.createCategory();

		propertyOne = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
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
	
	@Test
	public void testSetValueWithUnit() {
		setUpRepo();
		
		UnitValuePropertyInstance uvpiOther = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		uvpiOther.setType(propertyOne);
		BeanPropertyInt other = new BeanPropertyInt(uvpiOther);
		
		beanProperty.setUnit("Gram");
		beanProperty.setValue(0L);
		
		other.setUnit("Kilogram");
		other.setValue(1L);
		
		beanProperty.setValueWithUnit(other);
		
		final double EPSILON = 0.001;
		assertEquals("Unit has been set correctly", other.getUnit(), beanProperty.getUnit());
		assertEquals("Value has been set correctly", other.getValue(), beanProperty.getValue(), EPSILON);
	}
	
	@Test
	public void testSetValueWithUnitEditingDomain() {
		setUpRepo();
		
		UnitValuePropertyInstance uvpiOther = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		uvpiOther.setType(propertyOne);
		BeanPropertyInt other = new BeanPropertyInt(uvpiOther);
		
		beanProperty.setUnit("Gram");
		beanProperty.setValue(0L);
		
		other.setUnit("Kilogram");
		other.setValue(1L);
		
		Command cmd = beanProperty.setValueWithUnit(ed, other);
		cmd.execute();
		
		final double EPSILON = 0.001;
		assertEquals("Unit has been set correctly", other.getUnit(), beanProperty.getUnit());
		assertEquals("Value has been set correctly", other.getValue(), beanProperty.getValue(), EPSILON);
	}
}
