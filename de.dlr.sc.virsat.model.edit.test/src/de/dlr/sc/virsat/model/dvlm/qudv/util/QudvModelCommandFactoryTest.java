/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.QudvItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;

/**
 * Test Class to test the commands to modify the unit library QUDV
 * @author scha_vo
 *
 */
//CHECKSTYLE:OFF because of the many magic numbers in the test
public class QudvModelCommandFactoryTest {

	private QudvUnitHelper qudvUnitHelper;
	private QudvModelCommandFactory qudvController;
	private UnitManagement um;
	
	private EditingDomain ed;
	
	@Before
	public void setUp() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new QudvItemProviderAdapterFactory());
		
		BasicCommandStack commandStack = new BasicCommandStack();

		ed = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
		
		qudvUnitHelper = QudvUnitHelper.getInstance();
		qudvController = new QudvModelCommandFactory(ed);
		
		um = UnitsFactory.eINSTANCE.createUnitManagement();
		SystemOfUnits sou = qudvUnitHelper.initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		um.setSystemOfUnit(sou);
		
		// Only the superUser is allowed to make changes to the unitManagement  
		UserRegistry.getInstance().setSuperUser(true);
		
	}
	
	@After
	public void tearDown() throws CoreException { 
		UserRegistry.getInstance().setSuperUser(false);
	}	

	
	@Test
	public void addSimpleUnitTest() {
		AUnit simpleUnit = qudvUnitHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		Command cmd = qudvController.addSimpleUnit(um, simpleUnit);
		ed.getCommandStack().execute(cmd);
		assertTrue(um.getSystemOfUnit().getUnit().contains(simpleUnit));
	}
	
	@Test
	public void setSimpleUnitTest() {
		AUnit g = qudvUnitHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		Command cmd = qudvController.addSimpleUnit(um, g);
		ed.getCommandStack().execute(cmd);
		
		String name = "linearUnit";
		String symbol = "linearSymbol";
		String description = "linearDescription";
		String definitionURI = "linearDefinitioURI";
		
		Command cmd2 = qudvController.setSimpleUnit(g, name, symbol, description, definitionURI, null);
		ed.getCommandStack().execute(cmd2);
		
		//check that all the units are there
		assertTrue(um.getSystemOfUnit().getUnit().contains(g));
		
		//check the references
		List<AUnit> listOfUnits = um.getSystemOfUnit().getUnit();
		int indexOfUnit = listOfUnits.indexOf(g);
		AUnit simpleUnit = listOfUnits.get(indexOfUnit);
				
		assertEquals(name, simpleUnit.getName());
		assertEquals(symbol, simpleUnit.getSymbol());
		assertEquals(description, simpleUnit.getDescription());
		assertEquals(definitionURI, simpleUnit.getDefinitionURI());
		assertNull(simpleUnit.getQuantityKind());
	}
	
	@Test
	public void createRemUnitTest() {
		
		//since we are filling the library with default values first flush them
		um.getSystemOfUnit().getUnit().clear();
		
		// Make sure there are no units stored with the Proxy
		assertTrue(um.getSystemOfUnit().getUnit().isEmpty());
		
		// Add two units to the UnitManagement
		AUnit unit1 = QudvFactory.eINSTANCE.createSimpleUnit();
		AUnit unit2 = QudvFactory.eINSTANCE.createSimpleUnit();
		
		um.getSystemOfUnit().getUnit().add(unit1);
		um.getSystemOfUnit().getUnit().add(unit2);
		
		// Now execute a command to remove unit1 
		Command cmd = qudvController.createRemUnit(unit1, um); 
		cmd.execute();
		
		// Make sure Unit1 got removed and Unit2 stayed
		assertFalse(um.getSystemOfUnit().getUnit().contains(unit1));
		assertTrue(um.getSystemOfUnit().getUnit().contains(unit2));
	}
	
	@Test
	public void removeUnitTest() {
		AUnit unit1 = QudvFactory.eINSTANCE.createSimpleUnit();
		AUnit unit2 = QudvFactory.eINSTANCE.createSimpleUnit();
		AUnit unit3 = QudvFactory.eINSTANCE.createSimpleUnit();
		
		um.getSystemOfUnit().getUnit().add(unit1);
		um.getSystemOfUnit().getUnit().add(unit2);
		um.getSystemOfUnit().getUnit().add(unit3);
		
		List<AUnit> units = new LinkedList<AUnit>();
		
		units.add(unit1);
		units.add(unit2);
		
		Command cmd = qudvController.removeUnit(um, units);
		ed.getCommandStack().execute(cmd);
		
		assertFalse(um.getSystemOfUnit().getUnit().contains(unit1));
		assertFalse(um.getSystemOfUnit().getUnit().contains(unit2));
		assertTrue(um.getSystemOfUnit().getUnit().contains(unit3));
		
	}
	
	@Test
	public void addPrefixedUnitTest() {
		AQuantityKind mass = qudvUnitHelper.createSimpleQuantityKind("mass", "M", "heavy", "http://mass.virsat.dlr.de");
		Command cmd = qudvController.addSimpleQuantityKind(um, mass);
		ed.getCommandStack().execute(cmd);
		
		AUnit baseUnit = qudvUnitHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		Command cmd2 = qudvController.addSimpleUnit(um, baseUnit);
		ed.getCommandStack().execute(cmd2);
		
		Prefix prefix = um.getSystemOfUnit().getPrefix().get(0);
		PrefixedUnit prefixedUnit = qudvUnitHelper.createPrefixedUnit("kilogramms", "kg", "kilogramms are prefixed unit from gramms", "http://kilogramms.virsat.dlr.de", mass, prefix, baseUnit);
		Command cmd3  = qudvController.addPrefixedUnit(um, prefixedUnit, prefix, baseUnit);
		ed.getCommandStack().execute(cmd3);
		
		//check that both units are there
		assertTrue(um.getSystemOfUnit().getUnit().contains(baseUnit));
		assertTrue(um.getSystemOfUnit().getUnit().contains(prefixedUnit));
		
		//check that the quantityKind is there
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(mass));
		
		//add another prefixedUnit and check if all three units are there
		Prefix prefix2 = um.getSystemOfUnit().getPrefix().get(4);
		PrefixedUnit prefixedUnit2 = qudvUnitHelper.createPrefixedUnit("kilogramms", "kg", "kilogramms are prefixed unit from gramms", "http://kilogramms.virsat.dlr.de", mass, prefix2, baseUnit);
		Command cmd4 = qudvController.addPrefixedUnit(um, prefixedUnit2, prefix2, baseUnit);
		ed.getCommandStack().execute(cmd4);
		
		assertTrue(um.getSystemOfUnit().getUnit().contains(baseUnit));
		assertTrue(um.getSystemOfUnit().getUnit().contains(prefixedUnit));
		assertTrue(um.getSystemOfUnit().getUnit().contains(prefixedUnit2));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(mass));
		
		//check the references
		List<AUnit> listOfUnits = um.getSystemOfUnit().getUnit();
		int indexOfPrefixedUnit = listOfUnits.indexOf(prefixedUnit);
		int indexOfPrefixedUnit2 = listOfUnits.indexOf(prefixedUnit2);
		assertEquals(prefix, ((PrefixedUnit) listOfUnits.get(indexOfPrefixedUnit)).getPrefix());
		assertEquals(prefix2, ((PrefixedUnit) listOfUnits.get(indexOfPrefixedUnit2)).getPrefix());
		assertEquals(baseUnit, ((PrefixedUnit) listOfUnits.get(indexOfPrefixedUnit)).getReferenceUnit());
		assertEquals(baseUnit, ((PrefixedUnit) listOfUnits.get(indexOfPrefixedUnit2)).getReferenceUnit());
		
	}	
	
	@Test
	public void addConversionBasedUnitTest() {
		AQuantityKind mass = qudvUnitHelper.createSimpleQuantityKind("mass", "M", "heavy", "http://mass.virsat.dlr.de");
		Command cmd = qudvController.addSimpleQuantityKind(um, mass);
		ed.getCommandStack().execute(cmd);
		
		AUnit refUnit = qudvUnitHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		Command cmd2 = qudvController.addSimpleUnit(um, refUnit);
		ed.getCommandStack().execute(cmd2);
		
		double factor = 10.45E-3;
		double offset = 89.67;
		
		AConversionBasedUnit linearConversionUnit = qudvUnitHelper.createLinearConversionUnit("linearConversionUnit", "lcu", "I am the black peter", "http://lcu.virsat.dlr.de", mass, refUnit, factor);
		Command cmd3 = qudvController.addConversionBasedUnit(um, linearConversionUnit, refUnit);
		ed.getCommandStack().execute(cmd3);
		
		//check that both units and the quantity kind are there
		assertTrue(um.getSystemOfUnit().getUnit().contains(refUnit));
		assertTrue(um.getSystemOfUnit().getUnit().contains(linearConversionUnit));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(mass));
		
		AConversionBasedUnit affineConversionUnit = qudvUnitHelper.createAffineConversionUnit("affineConversionUnit", "acu", "the black shep", "http://acu.virsat.dlr.de", mass, refUnit, factor, offset);
		Command cmd4 = qudvController.addConversionBasedUnit(um, affineConversionUnit, refUnit);
		ed.getCommandStack().execute(cmd4);
		
		//check that all three units are there
		assertTrue(um.getSystemOfUnit().getUnit().contains(refUnit));
		assertTrue(um.getSystemOfUnit().getUnit().contains(linearConversionUnit));
		assertTrue(um.getSystemOfUnit().getUnit().contains(affineConversionUnit));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(mass));
		
		//check the references
		List<AUnit> listOfUnits = um.getSystemOfUnit().getUnit();
		int indexOfLinearConversionUnit = listOfUnits.indexOf(linearConversionUnit);
		int indexOfAffineConversionUnit = listOfUnits.indexOf(affineConversionUnit);
		assertEquals(refUnit, ((LinearConversionUnit) listOfUnits.get(indexOfLinearConversionUnit)).getReferenceUnit());
		assertEquals(refUnit, ((AffineConversionUnit) listOfUnits.get(indexOfAffineConversionUnit)).getReferenceUnit());		
		
		AConversionBasedUnit affineConversionUnit2 = qudvUnitHelper.createAffineConversionUnit("affineConversionUnit2", "acu2", "the black pepper", "http://acu2.virsat.dlr.de", mass, linearConversionUnit, factor, offset);
		Command cmd5 = qudvController.addConversionBasedUnit(um, affineConversionUnit2, linearConversionUnit);
		ed.getCommandStack().execute(cmd5);
		
		listOfUnits = um.getSystemOfUnit().getUnit();
		int indexOfAffineConversionUnit2 = listOfUnits.indexOf(affineConversionUnit2);
		assertEquals(linearConversionUnit, ((AffineConversionUnit) listOfUnits.get(indexOfAffineConversionUnit2)).getReferenceUnit());
	}
	
	@Test
	public void setAffineConversionUnitTest() {
		AQuantityKind mass = qudvUnitHelper.createSimpleQuantityKind("mass", "M", "heavy", "http://mass.virsat.dlr.de");
		Command cmd = qudvController.addSimpleQuantityKind(um, mass);
		ed.getCommandStack().execute(cmd);
		
		AUnit refUnit = qudvUnitHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", mass);
		Command cmd2 = qudvController.addSimpleUnit(um, refUnit);
		ed.getCommandStack().execute(cmd2);
		
		double factor = 10.45E-3f;
		double offset = 89.67f;
		
		AffineConversionUnit affineConversionUnit = qudvUnitHelper.createAffineConversionUnit("affineConversionUnit", "acu", "the black shep", "http://acu.virsat.dlr.de", mass, refUnit, factor, offset);
		Command cmd3 = qudvController.addConversionBasedUnit(um, affineConversionUnit, refUnit);
		ed.getCommandStack().execute(cmd3);
		
		String name = "affineUnit";
		String symbol = "affineSymbol";
		String description = "affineDescription";
		String definitionURI = "affineDefinitioURI";
		factor = 10.98E-2f;
		offset = 89.54f;

		Command cmd4 = qudvController.setAffineConversionUnit(affineConversionUnit, name, symbol, description, definitionURI, mass, factor, offset, refUnit);
		ed.getCommandStack().execute(cmd4);
		
		//check that both units and the quantity kind are there
		assertTrue(um.getSystemOfUnit().getUnit().contains(refUnit));
		assertTrue(um.getSystemOfUnit().getUnit().contains(affineConversionUnit));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(mass));
		
		//retrieve the affineConvUnit from the um and perform checks
		List<AUnit>listOfUnits = um.getSystemOfUnit().getUnit();
		int indexOfAffineConversionUnit = listOfUnits.indexOf(affineConversionUnit);
		affineConversionUnit = (AffineConversionUnit) um.getSystemOfUnit().getUnit().get(indexOfAffineConversionUnit);
		
		assertEquals(name, affineConversionUnit.getName());
		assertEquals(symbol, affineConversionUnit.getSymbol());
		assertEquals(description, affineConversionUnit.getDescription());
		assertEquals(definitionURI, affineConversionUnit.getDefinitionURI());
		assertEquals(factor, affineConversionUnit.getFactor(), 10E-5f);
		assertEquals(offset, affineConversionUnit.getOffset(), 10E-5f);
		assertEquals(refUnit, affineConversionUnit.getReferenceUnit());
		assertTrue(affineConversionUnit.isIsInvertible());
		assertEquals(mass, affineConversionUnit.getQuantityKind());
		
	}
	
	@Test
	public void setLinearConversionUnitTest() {
		AQuantityKind mass = qudvUnitHelper.createSimpleQuantityKind("mass", "M", "heavy", "http://mass.virsat.dlr.de");
		Command cmd = qudvController.addSimpleQuantityKind(um, mass);
		ed.getCommandStack().execute(cmd);
		
		AUnit refUnit = qudvUnitHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		Command cmd2 = qudvController.addSimpleUnit(um, refUnit);
		ed.getCommandStack().execute(cmd2);
		double factor = 10.45E-3f;
		
		LinearConversionUnit linearConversionUnit = qudvUnitHelper.createLinearConversionUnit("linearConversionUnit", "lcu", "the black shep", "http://acu.virsat.dlr.de", mass, refUnit, factor);
		Command cmd3 = qudvController.addConversionBasedUnit(um, linearConversionUnit, refUnit);
		ed.getCommandStack().execute(cmd3);
		
		String name = "linearUnit";
		String symbol = "linearSymbol";
		String description = "linearDescription";
		String definitionURI = "linearDefinitioURI";
		factor = 10.98E-2f;

		Command cmd4 = qudvController.setLinearConversionUnit(linearConversionUnit, name, symbol, description, definitionURI, mass, factor, refUnit);
		ed.getCommandStack().execute(cmd4);
		
		//check that both units and the quantity kind are there
		assertTrue(um.getSystemOfUnit().getUnit().contains(refUnit));
		assertTrue(um.getSystemOfUnit().getUnit().contains(linearConversionUnit));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(mass));
		
		//retrieve the affineConvUnit from the um and perform checks
		List<AUnit>listOfUnits = um.getSystemOfUnit().getUnit();
		int indexOfLinearConversionUnit = listOfUnits.indexOf(linearConversionUnit);
		linearConversionUnit = (LinearConversionUnit) um.getSystemOfUnit().getUnit().get(indexOfLinearConversionUnit);
		
		assertEquals(name, linearConversionUnit.getName());
		assertEquals(symbol, linearConversionUnit.getSymbol());
		assertEquals(description, linearConversionUnit.getDescription());
		assertEquals(definitionURI, linearConversionUnit.getDefinitionURI());
		assertEquals(factor, linearConversionUnit.getFactor(), 10E-5f);
		assertEquals(refUnit, linearConversionUnit.getReferenceUnit());
		assertTrue(linearConversionUnit.isIsInvertible());
		assertEquals(mass, linearConversionUnit.getQuantityKind());
		
	}
	
	@Test
	public void addDerivedUnitTest() {
		AQuantityKind mass = qudvUnitHelper.createSimpleQuantityKind("mass", "M", "heavy", "http://mass.virsat.dlr.de");
		Command cmd = qudvController.addSimpleQuantityKind(um, mass);
		ed.getCommandStack().execute(cmd);
		
		AUnit g = qudvUnitHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", mass);
		Command cmd2 = qudvController.addSimpleUnit(um, g);
		ed.getCommandStack().execute(cmd2);
		
		Prefix prefix = um.getSystemOfUnit().getPrefix().get(0);
		PrefixedUnit kg = qudvUnitHelper.createPrefixedUnit("kilogramms", "kg", "kilogramms are prefixed unit from gramms", "http://kilogramms.virsat.dlr.de", mass, prefix, g);
		Command cmd3 = qudvController.addPrefixedUnit(um, kg, prefix, g);
		ed.getCommandStack().execute(cmd3);
		
		AUnit m = qudvUnitHelper.createSimpleUnit("meter", "m", "meters", "http://meters.virsat.dlr.de", null);
		Command cmd4 = qudvController.addSimpleUnit(um, m);
		ed.getCommandStack().execute(cmd4);
		
		AUnit s = qudvUnitHelper.createSimpleUnit("second", "s", "second", "http://meters.virsat.dlr.de", null);
		Command cmd5 = qudvController.addSimpleUnit(um, s);
		ed.getCommandStack().execute(cmd5);
	
		String name = "Newton";
		String symbol = "N";
		String description = "NewtonDescription";
		String definitionURI = "NewtonDefinitioURI";
		HashMap<AUnit, Double> unitFactors = new HashMap<AUnit, Double>();
		
		unitFactors.put(kg, 1.0);
		unitFactors.put(m, 1.0);
		unitFactors.put(s, -2.0);
		
		AQuantityKind force = qudvUnitHelper.createSimpleQuantityKind("force", "F", "actio = reactio", "http://force.virsat.dlr.de");
		Command cmd6 = qudvController.addSimpleQuantityKind(um, force);
		ed.getCommandStack().execute(cmd6);
		
		DerivedUnit newton = qudvUnitHelper.createDerivedUnit(name, symbol, description, definitionURI, force);
		Command cmd7 = qudvController.addDerivedUnit(um, newton, unitFactors);
		ed.getCommandStack().execute(cmd7);
		
		//check that all the units are there
		assertTrue(um.getSystemOfUnit().getUnit().contains(g));
		assertTrue(um.getSystemOfUnit().getUnit().contains(kg));
		assertTrue(um.getSystemOfUnit().getUnit().contains(m));
		assertTrue(um.getSystemOfUnit().getUnit().contains(s));
		assertTrue(um.getSystemOfUnit().getUnit().contains(newton));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(mass));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(force));
		
		//retrieve the derivedUnit from the um and perform checks
		List<AUnit>listOfUnits = um.getSystemOfUnit().getUnit();
		int indexOfDerivedUnit = listOfUnits.indexOf(newton);
		DerivedUnit derivedUnit = (DerivedUnit) um.getSystemOfUnit().getUnit().get(indexOfDerivedUnit);
		
		assertEquals(name, derivedUnit.getName());
		assertEquals(symbol, derivedUnit.getSymbol());
		assertEquals(description, derivedUnit.getDescription());
		assertEquals(definitionURI, derivedUnit.getDefinitionURI());
		assertEquals(3, derivedUnit.getFactor().size());
		
	}
	@Test
	public void setDerivedUnitTest() {
		AUnit g = qudvUnitHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		Command cmd = qudvController.addSimpleUnit(um, g);
		ed.getCommandStack().execute(cmd);
		
		Prefix prefix = um.getSystemOfUnit().getPrefix().get(0);
		PrefixedUnit kg = qudvUnitHelper.createPrefixedUnit("kilogramms", "kg", "kilogramms are prefixed unit from gramms", "http://kilogramms.virsat.dlr.de", null, prefix, g);
		Command cmd2 = qudvController.addPrefixedUnit(um, kg, prefix, g);
		ed.getCommandStack().execute(cmd2);
		
		AUnit m = qudvUnitHelper.createSimpleUnit("meter", "m", "meters", "http://meters.virsat.dlr.de", null);
		Command cmd3 = qudvController.addSimpleUnit(um, m);
		ed.getCommandStack().execute(cmd3);
		
		AUnit s = qudvUnitHelper.createSimpleUnit("second", "s", "second", "http://meters.virsat.dlr.de", null);
		Command cmd4 = qudvController.addSimpleUnit(um, s);
		ed.getCommandStack().execute(cmd4);
		HashMap<AUnit, Double> unitFactors = new HashMap<AUnit, Double>();
		
		unitFactors.put(kg, 1.0);
		unitFactors.put(m, 1.0);
		unitFactors.put(s, -2.0);
		
		DerivedUnit newton = qudvUnitHelper.createDerivedUnit("Newton", "N", "desc", "http:4567", null);
		Command cmd5 = qudvController.addDerivedUnit(um, newton, unitFactors);
		ed.getCommandStack().execute(cmd5);
		
		String name = "modifiedNewton";
		String symbol = "modifiedN";
		String description = "newNewtonDescription";
		String definitionURI = "NewtonDefinitioURI";
		
		//clear the list and put only 2 unitfactors now
		unitFactors.clear();
		unitFactors.put(kg, 1.0);
		unitFactors.put(m, 1.0);
		
		Command cmd6 = qudvController.setDerivedUnit(newton, name, symbol, description, definitionURI, null, unitFactors);
		ed.getCommandStack().execute(cmd6);
		
		//retrieve the derivedUnit from the um and perform checks
		List<AUnit>listOfUnits = um.getSystemOfUnit().getUnit();
		int indexOfDerivedUnit = listOfUnits.indexOf(newton);
		DerivedUnit derivedUnit = (DerivedUnit) um.getSystemOfUnit().getUnit().get(indexOfDerivedUnit);

		assertEquals(name, derivedUnit.getName());
		assertEquals(symbol, derivedUnit.getSymbol());
		assertEquals(description, derivedUnit.getDescription());
		assertNull(derivedUnit.getQuantityKind());
		assertEquals(definitionURI, derivedUnit.getDefinitionURI());
		assertEquals(2, derivedUnit.getFactor().size());

	}
	
	@Test
	public void getListOfNonPrefixedUnitsTest() {
		//first flush the system of Units!
		um.getSystemOfUnit().getUnit().clear();
		
		AUnit unit1 = QudvFactory.eINSTANCE.createSimpleUnit();
		AUnit unit2 = QudvFactory.eINSTANCE.createSimpleUnit();
		AUnit unit3 = QudvFactory.eINSTANCE.createPrefixedUnit();
		AUnit unit4 = QudvFactory.eINSTANCE.createLinearConversionUnit();
		AUnit unit5 = QudvFactory.eINSTANCE.createAffineConversionUnit();
		AUnit unit6 = QudvFactory.eINSTANCE.createDerivedUnit();
		AUnit unit7 = QudvFactory.eINSTANCE.createPrefixedUnit();
		
		um.getSystemOfUnit().getUnit().add(unit1);
		um.getSystemOfUnit().getUnit().add(unit2);
		um.getSystemOfUnit().getUnit().add(unit3);
		um.getSystemOfUnit().getUnit().add(unit4);
		um.getSystemOfUnit().getUnit().add(unit5);
		um.getSystemOfUnit().getUnit().add(unit6);
		um.getSystemOfUnit().getUnit().add(unit7);
		
		List<AUnit> list = QudvModelCommandFactory.getListOfNonPrefixedUnits(um.getSystemOfUnit());
		assertEquals(5, list.size());
		
		//check to make sure I haven't modified the original list
		assertEquals(7, um.getSystemOfUnit().getUnit().size());
		assertTrue(um.getSystemOfUnit().getUnit().contains(unit7));
	}

	@Test
	public void addSimpleQuantityTest() {
		AQuantityKind length = qudvUnitHelper.createSimpleQuantityKind("length", "L", "length is the quantityKind for meters, inches, ...", "http://length.virsat.dlr.de");
		
		Command cmd = qudvController.addSimpleQuantityKind(um, length);
		ed.getCommandStack().execute(cmd);
		
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(length));
	}
	
	@Test
	public void setSimpleQuantityKindTest() {
		AQuantityKind length = qudvUnitHelper.createSimpleQuantityKind("length", "L", "length is the quantityKind for meters, inches, ...", "http://length.virsat.dlr.de");
		Command cmd = qudvController.addSimpleQuantityKind(um, length);
		ed.getCommandStack().execute(cmd);
		
		String name = "QuantityKind";
		String symbol = "QK";
		String description = "quantityKindDescription";
		String definitionURI = "quantityKindDefinitionURI";
		
		Command cmd2 = qudvController.setSimpleQuantityKind(length, name, symbol, description, definitionURI);
		ed.getCommandStack().execute(cmd2);
		
		//check that the quantityKind is there
		List<AQuantityKind> listOfQuantityKinds = um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind();
		assertTrue(listOfQuantityKinds.contains(length));
		
		//check the references
		int indexOfQuantity = listOfQuantityKinds.indexOf(length);
		AQuantityKind simpleQuantityKind = listOfQuantityKinds.get(indexOfQuantity);
				
		assertEquals(name, simpleQuantityKind.getName());
		assertEquals(symbol, simpleQuantityKind.getSymbol());
		assertEquals(description, simpleQuantityKind.getDescription());
		assertEquals(definitionURI, simpleQuantityKind.getDefinitionURI());
		
	}
	
	@Test
	public void addDerivedQuantityTest() {
			
		AQuantityKind length = qudvUnitHelper.createSimpleQuantityKind("length", "L", "length is the quantityKind for meters, inches, ...", "http://length.virsat.dlr.de");
		Command cmd = qudvController.addSimpleQuantityKind(um, length);
		ed.getCommandStack().execute(cmd);
		
		AQuantityKind mass = qudvUnitHelper.createSimpleQuantityKind("mass", "M", "heavy", "http://mass.virsat.dlr.de");
		Command cmd2 = qudvController.addSimpleQuantityKind(um, mass);
		ed.getCommandStack().execute(cmd2);
		
		AQuantityKind time = qudvUnitHelper.createSimpleQuantityKind("time", "T", "goes by..., so slowly...", "http://time.virsat.dlr.de");
		Command cmd3 = qudvController.addSimpleQuantityKind(um, time);
		ed.getCommandStack().execute(cmd3);
	
		String name = "force";
		String symbol = "F";
		String description = "actio = reactio";
		String definitionURI = "http://force.virsat.dlr.de";
		HashMap<AQuantityKind, Double> quantityKindFactors = new HashMap<AQuantityKind, Double>();
		
		quantityKindFactors.put(mass, 1.0);
		quantityKindFactors.put(length, 1.0);
		quantityKindFactors.put(time, -2.0);
		
		DerivedQuantityKind force = qudvUnitHelper.createDerivedQuantityKind(name, symbol, description, definitionURI);
		Command cmd4 = qudvController.addDerivedQuantityKind(um, force, quantityKindFactors);
		ed.getCommandStack().execute(cmd4);
		
		//check that all the quantityKinds are there
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(mass));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(time));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(length));
		assertTrue(um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().contains(force));
		
		//retrieve the derivedQuantityKinds from the unit management and perform checks
		List<AQuantityKind>listOfQuantityKinds = um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind();
		int indexOfDerivedQuantityKind = listOfQuantityKinds.indexOf(force);
		DerivedQuantityKind derivedQuantityKind = (DerivedQuantityKind) um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().get(indexOfDerivedQuantityKind);
		
		assertEquals(name, derivedQuantityKind.getName());
		assertEquals(symbol, derivedQuantityKind.getSymbol());
		assertEquals(description, derivedQuantityKind.getDescription());
		assertEquals(definitionURI, derivedQuantityKind.getDefinitionURI());
		assertEquals(3, derivedQuantityKind.getFactor().size());
		
	}
	
	@Test
	public void setDerivedQuantityTest() {
		AQuantityKind length = qudvUnitHelper.createSimpleQuantityKind("length", "L", "length is the quantityKind for meters, inches, ...", "http://length.virsat.dlr.de");
		Command cmd = qudvController.addSimpleQuantityKind(um, length);
		ed.getCommandStack().execute(cmd);
		
		AQuantityKind mass = qudvUnitHelper.createSimpleQuantityKind("mass", "M", "heavy", "http://mass.virsat.dlr.de");
		Command cmd2 = qudvController.addSimpleQuantityKind(um, mass);
		ed.getCommandStack().execute(cmd2);
		
		AQuantityKind time = qudvUnitHelper.createSimpleQuantityKind("time", "T", "goes by..., so slowly...", "http://time.virsat.dlr.de");
		Command cmd3 = qudvController.addSimpleQuantityKind(um, time);
		ed.getCommandStack().execute(cmd3);
		
		String name = "force";
		String symbol = "F";
		String description = "actio = reactio";
		String definitionURI = "http://force.virsat.dlr.de";
		
		HashMap<AQuantityKind, Double> quantityKindFactors = new HashMap<AQuantityKind, Double>();
		
		quantityKindFactors.put(mass, 1.0);
		quantityKindFactors.put(length, 10.0);
		quantityKindFactors.put(time, -2.0);
		
		HashMap<AQuantityKind, Double> initialQuantityKindFactors = new HashMap<AQuantityKind, Double>();
		
		initialQuantityKindFactors.put(mass, 10.0);
		initialQuantityKindFactors.put(length, 3.0);
		initialQuantityKindFactors.put(time, -5.0);
		
		DerivedQuantityKind force = qudvUnitHelper.createDerivedQuantityKind("initialName", "initialSymbol", "initialDescription", "initialDefinitionURI");
		Command cmd4 = qudvController.addDerivedQuantityKind(um, force, initialQuantityKindFactors);
		ed.getCommandStack().execute(cmd4);
		
		//now us the set method to modify the data 
		Command cmd5 = qudvController.setDerivedQuantityKind(um, force, name, symbol, description, definitionURI, quantityKindFactors);
		ed.getCommandStack().execute(cmd5);
		
		//retrieve the derivedQuantityKinds from the unit management and perform checks
		List<AQuantityKind>listOfQuantityKinds = um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind();
		int indexOfDerivedQuantityKind = listOfQuantityKinds.indexOf(force);
		DerivedQuantityKind derivedQuantityKind = (DerivedQuantityKind) um.getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().get(indexOfDerivedQuantityKind);
		
		assertEquals(name, derivedQuantityKind.getName());
		assertEquals(symbol, derivedQuantityKind.getSymbol());
		assertEquals(description, derivedQuantityKind.getDescription());
		assertEquals(definitionURI, derivedQuantityKind.getDefinitionURI());
		assertEquals(3, derivedQuantityKind.getFactor().size());
		
		List<AQuantityKind> retrievedQKList = new LinkedList<AQuantityKind>();
		for (int iRun = 0; iRun < derivedQuantityKind.getFactor().size(); iRun++) {
			retrievedQKList.add(derivedQuantityKind.getFactor().get(iRun).getQuantityKind());
			retrievedQKList.add(derivedQuantityKind.getFactor().get(iRun).getQuantityKind());
			retrievedQKList.add(derivedQuantityKind.getFactor().get(iRun).getQuantityKind());
		}
		assertTrue(retrievedQKList.contains(mass));
		assertTrue(retrievedQKList.contains(length));
		assertTrue(retrievedQKList.contains(time));
		
		List<Double> retrievedExpList = new LinkedList<Double>();
		for (int iRun = 0; iRun < derivedQuantityKind.getFactor().size(); iRun++) {
			retrievedExpList.add(derivedQuantityKind.getFactor().get(iRun).getExponent());
			retrievedExpList.add(derivedQuantityKind.getFactor().get(iRun).getExponent());
			retrievedExpList.add(derivedQuantityKind.getFactor().get(iRun).getExponent());
		}
		assertTrue(retrievedExpList.contains(1.0));
		assertTrue(retrievedExpList.contains(10.0));
		assertTrue(retrievedExpList.contains(-2.0));
	
	}
	
}
