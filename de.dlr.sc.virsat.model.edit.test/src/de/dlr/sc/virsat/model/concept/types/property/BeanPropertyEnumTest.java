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

import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.ABeanUnit;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;

/**
 * Tests for the Enum bean
 * @author fisc_ph
 *
 */
public class BeanPropertyEnumTest  extends ABeanPropertyTest {

	private BeanPropertyEnum beanProperty;
	private EnumUnitPropertyInstance eupi;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
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
			
		eupi = PropertyinstancesFactory.eINSTANCE.createEnumUnitPropertyInstance();
		eupi.setType(enumProperty);
		beanProperty = new BeanPropertyEnum();
		beanProperty.setTypeInstance(eupi);
		UserRegistry.getInstance().setSuperUser(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetValueEditingDomainString() {
		Command cmd = beanProperty.setValue(ed, "High");
		cmd.execute();

		assertEquals("Enum has been set", "HIGH", eupi.getValue().getName());
	}

	@Test
	public void testSetValueString() {
		beanProperty.setValue("HIGH");
		assertEquals("Enum has been set", "HIGH", eupi.getValue().getName());
		
		beanProperty.setValue("LOW");
		assertEquals("Enum has been set", "LOW", eupi.getValue().getName());
	}

	@Test
	public void testGetValue() {
		assertNull("There is no value set yet", beanProperty.getValue());

		beanProperty.setValue("HIGH");
		assertEquals("Got expected Value", "HIGH", beanProperty.getValue());
	}
	
	//CHECKSTYLE:OFF
	@Test
	public void testGetEnumValue() {
		beanProperty.setValue("HIGH");
		assertEquals("Got expected Value", 12, beanProperty.getEnumValue(), 0.00001);
	}

	@Test
	public void testGetEnumValueToBaseUnit() {
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		AUnit unitKm = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		AUnit unitM = QudvUnitHelper.getInstance().getUnitByName(sou, "meter");

		eupi.setUnit(unitM);
		beanProperty.setValue("HIGH");
		assertEquals("Got expected Value", 12, beanProperty.getEnumValueToBaseUnit(), 0.00001);

		eupi.setUnit(unitKm);
		assertEquals("Got expected Value", 12000, beanProperty.getEnumValueToBaseUnit(), 0.00001);
	}
	//CHECKSTYLE:ON
	
	private Repository repo;
	private Concept concept;
	private UnitManagement unitManagement;
	private SystemOfUnits sou;
	
	private Category testCategory;
	
	private EnumProperty propertyOne;
	
	/**
	 * Additional SetUp for some test cases where the full apth from type definitions
	 * to the concept and repository is needed.
	 */
	private void setUpRepo() {
		testCategory = CategoriesFactory.eINSTANCE.createCategory();

		propertyOne = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
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
		
		eupi.setType(propertyOne);
	}
	
	@Test
	public void testSetUnitEditingDomain() {
		setUpRepo();
		
		beanProperty.setUnit(ed, "Kilogram").execute();
		assertEquals("Unit has been set correctly", "Kilogram", eupi.getUnit().getName());
		
		beanProperty.setUnit(ed, "Gram").execute();
		assertEquals("Unit has been changed correctly", "Gram", eupi.getUnit().getName());

		Command cmd = beanProperty.setUnit(ed, "Gargl");
		assertFalse("The unit has not been changed", cmd.canExecute());
	}

	@Test
	public void testSetUnit() {
		setUpRepo();
		
		boolean changed;
		
		changed = beanProperty.setUnit("Kilogram");
		assertTrue("The unit has been changed", changed);
		assertEquals("Unit has been set correctly", "Kilogram", eupi.getUnit().getName());
		
		changed = beanProperty.setUnit("Gram");
		assertTrue("The unit has been changed", changed);
		assertEquals("Unit has been changed correctly", "Gram", eupi.getUnit().getName());

		changed = beanProperty.setUnit("Gargl");
		assertFalse("The unit has not been changed", changed);
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", eupi.getUnit().getName());
	}
	
	@Test
	public void testGetUnit() {
		setUpRepo();
		beanProperty.setUnit("Kilogram");
		assertEquals("Unit has been set correctly", "Kilogram", eupi.getUnit().getName());
		assertEquals("Unit has been set correctly", "Kilogram", beanProperty.getUnit());
		
		beanProperty.setUnit("Gram");
		assertEquals("Unit has been changed correctly", "Gram", eupi.getUnit().getName());
		assertEquals("Unit has been changed correctly", "Gram", beanProperty.getUnit());

		beanProperty.setUnit("Gargl");
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", eupi.getUnit().getName());
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", beanProperty.getUnit());
	}
	
	@Test
	public void testSetUnitBeanEditingDomain() {
		setUpRepo();
		
		AUnit kmUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		ABeanUnit<? extends AUnit> kmBean = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(kmUnit);
		beanProperty.setUnitBean(ed, kmBean).execute();
		assertEquals("Unit has been set correctly", kmUnit, eupi.getUnit());
		
		AUnit gUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Gram");
		ABeanUnit<? extends AUnit> gBean = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(gUnit);
		beanProperty.setUnitBean(ed, gBean).execute();
		assertEquals("Unit has been changed correctly", gUnit, eupi.getUnit());

		Command cmd = beanProperty.setUnitBean(ed, null);
		assertFalse("The unit can not been changed", cmd.canExecute());
	}

	@Test
	public void testSetUnitBean() {
		setUpRepo();
		
		AUnit kmUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		ABeanUnit<? extends AUnit> kmBean = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(kmUnit);
		beanProperty.setUnitBean(kmBean);
		assertEquals("Unit has been set correctly", kmUnit, eupi.getUnit());
		
		AUnit gUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Gram");
		ABeanUnit<? extends AUnit> gBean = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(gUnit);
		beanProperty.setUnitBean(gBean);
		assertEquals("Unit has been changed correctly", gUnit, eupi.getUnit());

		beanProperty.setUnitBean(null);
		assertEquals("The unit has not been changed", gUnit, eupi.getUnit());
	}
	
	@Test
	public void testGetUnitBean() {
		setUpRepo();
		
		AUnit kmUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		ABeanUnit<? extends AUnit> kmBean = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(kmUnit);
		beanProperty.setUnitBean(kmBean);
		assertEquals("Got correct unit", kmBean.getUnit(), beanProperty.getUnitBean().getUnit());
		
		AUnit gUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Gram");
		ABeanUnit<? extends AUnit> gBean = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(gUnit);
		beanProperty.setUnitBean(gBean);
		assertEquals("Got correct unit", gBean.getUnit(), beanProperty.getUnitBean().getUnit());
	}
}
