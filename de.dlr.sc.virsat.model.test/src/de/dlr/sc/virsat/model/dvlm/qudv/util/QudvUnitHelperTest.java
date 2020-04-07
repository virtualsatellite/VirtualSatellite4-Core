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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper.QudvCalcMethod;

/**
 * Qudv Unit Helper Test class  
 * @author scha_vo
 *
 */
//CHECKSTYLE:OFF Turning checkstyle off due to many magic numbers in tests
public class QudvUnitHelperTest {

	private QudvUnitHelper qudvHelper;
	
	private static final Double TEST_EPSILON = 0.00001; 
	
	@Before
	public void setUp() throws Exception {
		qudvHelper = QudvUnitHelper.getInstance();
	}
	
	@Test
	public void createSimpleUnitTest() {
		
		String name = "UnitName";
		String symbol = "un";
		String desc = "UnitDescription";
		String definitionURI = "theDefinitionURI";
	
		AUnit simpleUnit = qudvHelper.createSimpleUnit(name, symbol, desc, definitionURI, null);
		
		assertEquals(name, simpleUnit.getName());
		assertEquals(symbol, simpleUnit.getSymbol());
		assertEquals(desc, simpleUnit.getDescription());
		assertEquals(definitionURI, simpleUnit.getDefinitionURI());
		
		// Create a second unit and check the IDs are not the same
		AUnit simpleUnit2 = qudvHelper.createSimpleUnit(name, symbol, desc, definitionURI, null);
		
		String uuid1 = simpleUnit.getUuid().toString();
		String uuid2 = simpleUnit2.getUuid().toString();
		
		assertNotSame(uuid1, uuid2);
	
	}
	@Test
	public void createPrefixedUnitTest() {
		
		String name = "UnitName";
		String symbol = "un";
		String desc = "UnitDescription";
		String definitionURI = "theDefinitionURI";
		Prefix prefix = QudvFactory.eINSTANCE.createPrefix();
		
		AUnit baseUnit = qudvHelper.createSimpleUnit("simpleUnit", "su", "this is the description of su", "http://su.virsat.dlr.de", null);
		
		PrefixedUnit prefixedUnit = qudvHelper.createPrefixedUnit(name, symbol, desc, definitionURI, null, prefix, baseUnit);
		
		assertEquals(name, prefixedUnit.getName());
		assertEquals(symbol, prefixedUnit.getSymbol());
		assertEquals(desc, prefixedUnit.getDescription());
		assertEquals(definitionURI, prefixedUnit.getDefinitionURI());
		assertEquals(prefix, prefixedUnit.getPrefix());
		assertEquals(baseUnit, prefixedUnit.getReferenceUnit());
		assertTrue(prefixedUnit.isIsInvertible());
		assertNull(prefixedUnit.getQuantityKind());
		
		// Create a second unit and check the IDs are not the same
		PrefixedUnit prefixedUnit2 = qudvHelper.createPrefixedUnit(name, symbol, desc, definitionURI, null, prefix, baseUnit);
		
		String uuid1 = prefixedUnit.getUuid().toString();
		String uuid2 = prefixedUnit2.getUuid().toString();
		
		assertNotSame(uuid1, uuid2);
	
	}
	
	@Test
	public void createAffineConversionUnitTest() {
		
		String name = "UnitName";
		String symbol = "un";
		String desc = "UnitDescription";
		String definitionURI = "theDefinitionURI";
		Double factor = 10.45E-3;
		Double offset = 89.67;
		
		AQuantityKind length = qudvHelper.createSimpleQuantityKind("length", "l", "long", "http://length.virsat.dlr.de"); 
		SimpleUnit refUnit = qudvHelper.createSimpleUnit("simpleUnit", "su", "this is the description of su", "http://su.virsat.dlr.de", null);
		
		AffineConversionUnit affineConversionUnit = qudvHelper.createAffineConversionUnit(name, symbol, desc, definitionURI, length, refUnit, factor, offset);
		
		assertEquals(name, affineConversionUnit.getName());
		assertEquals(symbol, affineConversionUnit.getSymbol());
		assertEquals(desc, affineConversionUnit.getDescription());
		assertEquals(definitionURI, affineConversionUnit.getDefinitionURI());
		assertEquals(factor, affineConversionUnit.getFactor(), 1E-5f);
		assertEquals(offset, affineConversionUnit.getOffset(), 1E-5f);
		assertTrue(affineConversionUnit.isIsInvertible());
		assertEquals(length, affineConversionUnit.getQuantityKind());
		
		// Create a second unit and check the IDs are not the same
		AffineConversionUnit affineConversionUnit2 = qudvHelper.createAffineConversionUnit(name, symbol, desc, definitionURI, length, refUnit, factor, offset);
		
		String uuid1 = affineConversionUnit.getUuid().toString();
		String uuid2 = affineConversionUnit2.getUuid().toString();
		
		assertNotSame(uuid1, uuid2);
	
	}
	
	@Test
	public void createLinearConversionUnitTest() {
		
		String name = "UnitName";
		String symbol = "un";
		String desc = "UnitDescription";
		String definitionURI = "theDefinitionURI";
		Double factor = 10.45E-3;
		
		AQuantityKind length = qudvHelper.createSimpleQuantityKind("length", "l", "long", "http://length.virsat.dlr.de");
		AUnit refUnit = qudvHelper.createSimpleUnit("simpleUnit", "su", "this is the description of su", "http://su.virsat.dlr.de", null);
		
		LinearConversionUnit linearConversionUnit = qudvHelper.createLinearConversionUnit(name, symbol, desc, definitionURI, length, refUnit, factor);
		
		assertEquals(name, linearConversionUnit.getName());
		assertEquals(symbol, linearConversionUnit.getSymbol());
		assertEquals(desc, linearConversionUnit.getDescription());
		assertEquals(definitionURI, linearConversionUnit.getDefinitionURI());
		assertEquals(factor, linearConversionUnit.getFactor(), 1E-5f);
		assertTrue(linearConversionUnit.isIsInvertible());
		assertEquals(length, linearConversionUnit.getQuantityKind());
		
		// Create a second unit and check the IDs are not the same
		LinearConversionUnit linearConversionUnit2 = qudvHelper.createLinearConversionUnit(name, symbol, desc, definitionURI, length, refUnit, factor);
		
		String uuid1 = linearConversionUnit.getUuid().toString();
		String uuid2 = linearConversionUnit2.getUuid().toString();
		
		assertNotSame(uuid1, uuid2);
	
	}
	
	@Test
	public void createSimpleQuantityKindTest() {
		
		String name = "QuantityKindName";
		String symbol = "qk";
		String desc = "QuantityKindDescription";
		String definitionURI = "theDefinitionURI";
	
		AQuantityKind simpleQuantityKind = qudvHelper.createSimpleQuantityKind(name, symbol, desc, definitionURI);
		
		assertEquals(name, simpleQuantityKind.getName());
		assertEquals(symbol, simpleQuantityKind.getSymbol());
		assertEquals(desc, simpleQuantityKind.getDescription());
		assertEquals(definitionURI, simpleQuantityKind.getDefinitionURI());
		
		// Create a second unit and check the IDs are not the same
		AQuantityKind simpleQuantityKind2 = qudvHelper.createSimpleQuantityKind(name, symbol, desc, definitionURI);
		
		String uuid1 = simpleQuantityKind.getUuid().toString();
		String uuid2 = simpleQuantityKind2.getUuid().toString();
		
		assertNotSame(uuid1, uuid2);
	
	}
	
	
	@Test
	public void qudvImportExportTest() throws IOException {
		SystemOfUnits sou1 = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "This is the system of units for this study", "N/A");
		
		//also flush the system of units before we do anything
		sou1.getUnit().clear();
		
		
		AQuantityKind length = qudvHelper.getQuantityKindByName(sou1.getSystemOfQuantities().get(0), "Length");
		AUnit simpleUnit = qudvHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		AUnit simpleUnit2 = qudvHelper.createSimpleUnit("metre", "m", "meters are used to measure lengths", "http://meters.virsat.dlr.de", length);
	
		sou1.getUnit().add(simpleUnit);
		sou1.getUnit().add(simpleUnit2);
		
		Prefix prefix = sou1.getPrefix().get(7);
		PrefixedUnit prefixedUnit = qudvHelper.createPrefixedUnit("kilogram", "kg", "", "", length, prefix, simpleUnit);
		
		sou1.getUnit().add(prefixedUnit);
		
		String destination = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "/exportQudv.qudv"; 

		qudvHelper.exportModeltoFile(sou1, destination);
		
		assertTrue(sou1.getUnit().contains(simpleUnit));		
		
		SystemOfUnits importedSoU = qudvHelper.importModelFromFile(destination);

		assertNotNull("System of Units got correctly loaded", importedSoU);
		
		assertEquals(sou1.getUuid(), importedSoU.getUuid());
		assertEquals(sou1.getName(), importedSoU.getName());
		
		assertEquals(3, importedSoU.getUnit().size());
		assertEquals(44, importedSoU.getSystemOfQuantities().get(0).getQuantityKind().size());
		
		//without the copySystemOfUnits method the Ids of the imported Qudv model are the same
		assertEquals(simpleUnit.getUuid(), importedSoU.getUnit().get(0).getUuid());
		assertEquals(simpleUnit.getName(), importedSoU.getUnit().get(0).getName());
		
		assertEquals(simpleUnit2.getUuid(), importedSoU.getUnit().get(1).getUuid());
		assertEquals(simpleUnit2.getName(), importedSoU.getUnit().get(1).getName());
		
		assertEquals(prefixedUnit.getUuid(), importedSoU.getUnit().get(2).getUuid());
		assertEquals(prefixedUnit.getName(), importedSoU.getUnit().get(2).getName());
		
		//check that the references are loaded correctly
		assertEquals(simpleUnit2.getQuantityKind().getName(), importedSoU.getUnit().get(1).getQuantityKind().getName());
		
		//check that the prefix reference was loaded correctly
		assertEquals(prefixedUnit.getPrefix().getName(), ((PrefixedUnit) importedSoU.getUnit().get(2)).getPrefix().getName());
	}
	
	@Test
	public void getQuantityKindByNameTest() {
		SystemOfUnits sou1 = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "This is the system of units for this study", "N/A");
		
		SystemOfQuantities soq = sou1.getSystemOfQuantities().get(0);
		String name = "Length";
		//check that the parameter was returned correctly
		AQuantityKind qk = qudvHelper.getQuantityKindByName(soq, name);
		assertEquals(name, qk.getName());
		//check if null is returned if a parameter is not found 
		qk = qudvHelper.getQuantityKindByName(soq, "Idontexist");
		assertNull(qk);
		//check if null is returned when the systemOfQuantities is null
		qk = qudvHelper.getQuantityKindByName(null, "Idontexist");
		assertNull(qk);
	}
	
	@Test
	public void getQuantityKindByBaseQuantityKindsTest() {
		SystemOfUnits sou1 = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "This is the system of units for this study", "N/A");
		
		SystemOfQuantities soq = sou1.getSystemOfQuantities().get(0);
		AQuantityKind qk = QudvUnitHelper.getInstance().getQuantityKindByName(soq, "Electric potential difference");
		Map<AQuantityKind, Double> baseQuantityKinds = QudvUnitHelper.getInstance().getBaseQuantityKinds(qk);
		
		List<AQuantityKind> retrivedQuantityKinds = QudvUnitHelper.getInstance().getQuantityKindByBaseKinds(soq, baseQuantityKinds);
		assertThat("Retrieved the correct quantityKinds", retrivedQuantityKinds, hasItem(qk));
		assertEquals("Retrieved quantitiy kinds has 1 item", retrivedQuantityKinds.size(), 1);
	}


	@Test
	public void convertToTargetUnitTest() {
	
		//we simply use the the library to set up the systemOfUnits in the background 
		SystemOfUnits sou1 = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "This is the system of units for this study", "N/A");
		
		//we need two units, let's consider we want to convert percent into Unit
		// 20% --> 0.2
		AUnit percentUnit = QudvUnitHelper.getInstance().getUnitByName(sou1, "Percent");
		AUnit noUnit = QudvUnitHelper.getInstance().getUnitByName(sou1, "No Unit");
		final double TWENTY = 20.0;
		final double POINT_TWO = 0.2;
		double convertedValue = QudvUnitHelper.getInstance().convertFromSourceToTargetUnit(percentUnit, TWENTY, noUnit);
		assertEquals(POINT_TWO, convertedValue, TEST_EPSILON);
		
		//more complex example
		// 10m/s --> 36 km/h
		AUnit meterPerSecond = QudvUnitHelper.getInstance().getUnitByName(sou1, "Meter Per Second");
		AUnit kilometerPerHour = QudvUnitHelper.getInstance().getUnitByName(sou1, "Kilometer Per Hour");
		final double TEN = 10.0;
		final double THIRTY_SIX = 36.0;
		convertedValue = QudvUnitHelper.getInstance().convertFromSourceToTargetUnit(meterPerSecond, TEN, kilometerPerHour);
		assertEquals(THIRTY_SIX, convertedValue, TEST_EPSILON);
		
	}
	
	@Test
	public void initializeSystemOfUnitsTest() {
		SystemOfUnits sou1 = qudvHelper.initializeSystemOfUnits("SystemOfUnits", "SoU", "This is the system of units for this study", "N/A");
		SystemOfQuantities soq = sou1.getSystemOfQuantities().get(0);
		assertTrue(sou1.getSystemOfQuantities().get(0).getQuantityKind().contains(qudvHelper.getQuantityKindByName(soq, "Length")));
	}
	
	@Test
	public void copySystemOfUnitsTest() {
		SystemOfUnits sou1 = qudvHelper.initializeSystemOfUnits("SystemOfUnits", "SoU", "This is the system of units for this study", "N/A");
		//and flush the units so we make a decent test!
		sou1.getUnit().clear();
		
		SystemOfQuantities soq = sou1.getSystemOfQuantities().get(0);
		
		//we need to create some units and quantityKinds
		AQuantityKind length = qudvHelper.getQuantityKindByName(soq, "length");
		AQuantityKind mass = qudvHelper.getQuantityKindByName(soq, "mass");
		
		AUnit g = qudvHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		AUnit m = qudvHelper.createSimpleUnit("metre", "m", "meters are used to measure lengths", "http://meters.virsat.dlr.de", length);
		
		Prefix kilo = sou1.getPrefix().get(7);
		PrefixedUnit kg = qudvHelper.createPrefixedUnit("kilogram", "kg", "kilogramms as in 1000 grams", "http://kilogram.virsat.dlr.de", mass, kilo, g);
		
		//now we add the units
		sou1.getUnit().add(g);
		sou1.getUnit().add(m);
		sou1.getUnit().add(kg);
		
		//we directly copy the systemOfUnits
		SystemOfUnits sou2 = sou1;
		
		//the systemOfUnits were directly copied, everything should be the same, also the IDs 
		assertEquals(sou1.getUuid(), sou2.getUuid());
		assertEquals(sou1.getName(), sou2.getName());
		
		//check that three units are there, one systemOfQuantities and eight quantityKinds are there 
		assertEquals(3, sou1.getUnit().size());
		assertEquals(1, sou1.getSystemOfQuantities().size());
		assertEquals(44, sou1.getSystemOfQuantities().get(0).getQuantityKind().size());
		assertEquals(3, sou2.getUnit().size());
		assertEquals(1, sou2.getSystemOfQuantities().size());
		assertEquals(44, sou2.getSystemOfQuantities().get(0).getQuantityKind().size());
		
		//without the copySystemOfUnits method the Ids of the three units are the same
		assertEquals(sou1.getUnit().get(0).getUuid(), sou2.getUnit().get(0).getUuid());
		assertEquals(sou1.getUnit().get(0).getName(), sou2.getUnit().get(0).getName());
		assertEquals(sou1.getUnit().get(1).getUuid(), sou2.getUnit().get(1).getUuid());
		assertEquals(sou1.getUnit().get(1).getName(), sou2.getUnit().get(1).getName());
		assertEquals(sou1.getUnit().get(2).getUuid(), sou2.getUnit().get(2).getUuid());
		assertEquals(sou1.getUnit().get(2).getName(), sou2.getUnit().get(2).getName());
	
		//same with the systemOfQuantities
		assertEquals(sou1.getSystemOfQuantities().get(0).getUuid(), sou2.getSystemOfQuantities().get(0).getUuid());
		assertEquals(sou1.getSystemOfQuantities().get(0).getName(), sou2.getSystemOfQuantities().get(0).getName());
		
		//same with the eight quantityKinds
		assertEquals(sou1.getSystemOfQuantities().get(0).getQuantityKind().get(0).getUuid(), sou2.getSystemOfQuantities().get(0).getQuantityKind().get(0).getUuid());
		assertEquals(sou1.getSystemOfQuantities().get(0).getQuantityKind().get(0).getName(), sou2.getSystemOfQuantities().get(0).getQuantityKind().get(0).getName());
		assertEquals(sou1.getSystemOfQuantities().get(0).getQuantityKind().get(1).getUuid(), sou2.getSystemOfQuantities().get(0).getQuantityKind().get(1).getUuid());
		assertEquals(sou1.getSystemOfQuantities().get(0).getQuantityKind().get(1).getName(), sou2.getSystemOfQuantities().get(0).getQuantityKind().get(1).getName());
		
		//now use the ecore.uitl copier to do the copy
		SystemOfUnits sou3 = qudvHelper.copySystemOfUnits(sou1);
		
		//ids should not be the same
		assertNotSame(sou1.getUuid(), sou3.getUuid());
		assertEquals(sou1.getName(), sou3.getName());
		
		//check that three units are there, one systemOfQunatities and eight quantityKinds are there 
		assertEquals(3, sou3.getUnit().size());
		assertEquals(1, sou3.getSystemOfQuantities().size());
		assertEquals(44, sou3.getSystemOfQuantities().get(0).getQuantityKind().size());
		
		//after using the copyUnitManagement method the Ids of the three units not the same anymore
		assertNotSame(sou1.getUnit().get(0).getUuid(), sou3.getUnit().get(0).getUuid());
		assertEquals(sou1.getUnit().get(0).getName(), sou3.getUnit().get(0).getName());
		assertNotSame(sou1.getUnit().get(1).getUuid(), sou3.getUnit().get(1).getUuid());
		assertEquals(sou1.getUnit().get(1).getName(), sou3.getUnit().get(1).getName());
		assertNotSame(sou1.getUnit().get(2).getUuid(), sou3.getUnit().get(2).getUuid());
		assertEquals(sou1.getUnit().get(2).getName(), sou3.getUnit().get(2).getName());
		
		//same with the systemOfQuantities
		assertNotSame(sou1.getSystemOfQuantities().get(0).getUuid(), sou3.getSystemOfQuantities().get(0).getUuid());
		assertEquals(sou1.getSystemOfQuantities().get(0).getName(), sou3.getSystemOfQuantities().get(0).getName());
		
		//same with the two quantityKinds
		assertNotSame(sou1.getSystemOfQuantities().get(0).getQuantityKind().get(0).getUuid(), sou3.getSystemOfQuantities().get(0).getQuantityKind().get(0).getUuid());
		assertEquals(sou1.getSystemOfQuantities().get(0).getQuantityKind().get(0).getName(), sou3.getSystemOfQuantities().get(0).getQuantityKind().get(0).getName());
		assertNotSame(sou1.getSystemOfQuantities().get(0).getQuantityKind().get(1).getUuid(), sou3.getSystemOfQuantities().get(0).getQuantityKind().get(1).getUuid());
		assertEquals(sou1.getSystemOfQuantities().get(0).getQuantityKind().get(1).getName(), sou3.getSystemOfQuantities().get(0).getQuantityKind().get(1).getName());
	
		//Last but not least we check for the references
		//in the test data we had kg referencing to g (prefixed unit)
	
		//retrieve the third unit, which was kg and cast it to PrefixedUnit, so we can actually do what we want to do
		PrefixedUnit prefixedUnit = (PrefixedUnit) sou3.getUnit().get(2);
		//and check that the reference is not null
		assertNotNull(prefixedUnit.getReferenceUnit());
		//then we check if the reference is pointing to the correct unit, by checking the id and the name
		assertEquals(prefixedUnit.getReferenceUnit().getUuid(), sou3.getUnit().get(0).getUuid());
		assertEquals(prefixedUnit.getReferenceUnit().getName(), sou3.getUnit().get(0).getName());
		
	}

	@Test
	public void haveSameQuantityKindWithUnitTest() {
		//we need to create some units and quantityKinds
		AQuantityKind length = qudvHelper.createSimpleQuantityKind("length", "L", "long", "http://length.virsat.dlr.de");
		AQuantityKind mass = qudvHelper.createSimpleQuantityKind("mass", "M", "heavy Mass", "http://mass.virsat.dlr.de");

		AUnit g = qudvHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", mass);
		AUnit m = qudvHelper.createSimpleUnit("metre", "m", "meters are used to measure lengths", "http://meters.virsat.dlr.de", length);
		AUnit pound = qudvHelper.createSimpleUnit("pound", "pd", "pound of coffee", "http://pound.virsat.dlr.de", mass);

		assertTrue(qudvHelper.haveSameQuantityKind(g, pound));
		assertFalse(qudvHelper.haveSameQuantityKind(m, pound));

		//check what happens if a QuantityKind reference is not set.
		g = qudvHelper.createSimpleUnit("gramms", "g", "gramms are a base unit for mass", "http://gramms.virsat.dlr.de", null);
		assertFalse(qudvHelper.haveSameQuantityKind(g, pound));

		//if both are not set, it should return false as well
		pound = qudvHelper.createSimpleUnit("pound", "pd", "pound of coffee", "http://pound.virsat.dlr.de", null);
		assertFalse(qudvHelper.haveSameQuantityKind(g, pound));

	}
	
	@Test
	public void haveSameQuantityKindWithMapTest() {
		//make some simple quantityKinds
		AQuantityKind dimensionlessQK = qudvHelper.createSimpleQuantityKind(QudvUnitHelper.DIMENSIONLESS_QK_NAME, "U", "dimensionlessQK", "");
		AQuantityKind length = qudvHelper.createSimpleQuantityKind("length", "L", "long", "http://length.virsat.dlr.de");
		AQuantityKind mass = qudvHelper.createSimpleQuantityKind("mass", "M", "heavy Mass", "http://mass.virsat.dlr.de");
		AQuantityKind time = qudvHelper.createSimpleQuantityKind("Time", "T", "timeQK", "");

		final Double M2 = -2.0;
		
		HashMap<AQuantityKind, Double> map1 = new HashMap<AQuantityKind, Double>();
		map1.put(length, 1.0);
		map1.put(time, M2);
		map1.put(mass, 2.0);
		
		HashMap<AQuantityKind, Double> map2 = new HashMap<AQuantityKind, Double>();
		map2.put(length, 1.0);
		map2.put(time, M2);
		map2.put(mass, 2.0);
		
		assertTrue(qudvHelper.haveSameQuantityKind(map1, map2));
		
		map1.clear();		
		map1.put(length, 1.0001);
		map1.put(time, M2);
		map1.put(mass, 2.0);
		
		assertFalse(qudvHelper.haveSameQuantityKind(map1, map2));
	
		//check for one map that contains dimensionless and another empty map
		map1.clear();
		map1.put(dimensionlessQK, 1.0);
		map2.clear();
		
		assertTrue(qudvHelper.haveSameQuantityKind(map1, map2));
		assertTrue(qudvHelper.haveSameQuantityKind(map2, map1));
		
		//check for cancellation case
		map1.clear();
		map2.clear();
		map2.put(length, 2.0);
		assertFalse(qudvHelper.haveSameQuantityKind(map1, map2));
		assertFalse(qudvHelper.haveSameQuantityKind(map2, map1));
		
	}
	
	@Test
	public void getBaseQuantityKindsTest() {
		//make some simple quantityKinds
		AQuantityKind length = qudvHelper.createSimpleQuantityKind("length", "L", "long", "http://length.virsat.dlr.de");
		AQuantityKind mass = qudvHelper.createSimpleQuantityKind("mass", "M", "heavy Mass", "http://mass.virsat.dlr.de");
		AQuantityKind time = qudvHelper.createSimpleQuantityKind("Time", "T", "timeQK", "");
		
		// some coefficient 
		final Double M2 = -2.0;
		
		//force as a derived quantity kind
		Map<AQuantityKind, Double> factorMap = new HashMap<AQuantityKind, Double>();
		factorMap.clear();
		factorMap.put(length, 1.0);
		factorMap.put(time, M2);
		DerivedQuantityKind acceleration = qudvHelper.createAndAddDerivedQuantityKind("acceleration", "L¹ T⁻²", "accelerationQK", "",  factorMap);
		
		Map<AQuantityKind, Double> accelerationBaseMap = qudvHelper.getBaseQuantityKinds(acceleration);
		
		//check for the right number of elements in the forceBaseMap
		assertEquals(2, accelerationBaseMap.size());
		
		//check the contents of the keys
		assertTrue(accelerationBaseMap.containsKey(length));
		assertTrue(accelerationBaseMap.containsKey(time));
		
		assertEquals(1.0, accelerationBaseMap.get(length), TEST_EPSILON);
		assertEquals(-2.0, accelerationBaseMap.get(time), TEST_EPSILON);
		
		// second example testing the nested derivedQK problem
		// we define force differently now
		// force [N] = mass[kg] * acceleration [m/s²] 
		
		//force as a derived quantity kind
		factorMap.clear();
		factorMap.put(acceleration, 1.0);
		factorMap.put(mass, 1.0);

		DerivedQuantityKind force = qudvHelper.createAndAddDerivedQuantityKind("Force", "L¹ M¹ T⁻²", "forceQK", "",  factorMap);
		
		Map<AQuantityKind, Double> forceBaseMap = qudvHelper.getBaseQuantityKinds(force);
		
		//check for the right number of elements in the forceBaseMap
		assertEquals(3, forceBaseMap.size());
		
		//check the contents of the keys
		assertTrue(forceBaseMap.containsKey(mass));
		assertTrue(forceBaseMap.containsKey(length));
		assertTrue(forceBaseMap.containsKey(time));
		
		assertEquals(1.0, forceBaseMap.get(length), TEST_EPSILON);
		assertEquals(1.0, forceBaseMap.get(mass), TEST_EPSILON);
		assertEquals(-2.0, forceBaseMap.get(time), TEST_EPSILON);
	}
	
	@Test
	public void isDimensionlessTest() {
		//make some simple quantityKinds
		AQuantityKind dimensionlessQK = qudvHelper.createSimpleQuantityKind(QudvUnitHelper.DIMENSIONLESS_QK_NAME, "U", "dimensionlessQK", "");
		AQuantityKind length = qudvHelper.createSimpleQuantityKind("length", "L", "long", "http://length.virsat.dlr.de");
		AUnit m = qudvHelper.createSimpleUnit("metre", "m", "meters are used to measure lengths", "http://meters.virsat.dlr.de", length);
		AUnit unitWithoutQK = qudvHelper.createSimpleUnit("noUnit", "nU", "", "", null);
		AUnit dimensionlessUnit = qudvHelper.createSimpleUnit("dimensionlessUnit", "nU", "", "", dimensionlessQK);
		assertFalse(qudvHelper.isDimensionless(m));
		assertFalse(qudvHelper.isDimensionless(unitWithoutQK));
		assertTrue(qudvHelper.isDimensionless(dimensionlessUnit));
	}
	
	@Test
	public void mergeMapsTest() {
		//make some simple quantityKinds
		AQuantityKind length = qudvHelper.createSimpleQuantityKind("length", "L", "long", "http://length.virsat.dlr.de");
		AQuantityKind mass = qudvHelper.createSimpleQuantityKind("mass", "M", "heavy Mass", "http://mass.virsat.dlr.de");
		AQuantityKind temperature = qudvHelper.createSimpleQuantityKind("Temperature", "Θ", "temperatureQK", "");
		AQuantityKind electricCurrent = qudvHelper.createSimpleQuantityKind("Electric current", "I", "electricCurrentQK", "");
		
		//now build up the input maps for the test
		HashMap<AQuantityKind, Double> map1 = new HashMap<AQuantityKind, Double>();
		map1.put(length, 1.0);
		map1.put(mass, -1.2);
		map1.put(temperature, 2.0);
		
		HashMap<AQuantityKind, Double> map2 = new HashMap<AQuantityKind, Double>();
		map2.put(length, 1.0);
		map2.put(mass, 1.2);
		map2.put(electricCurrent, 4.2);
		
		//now merge the maps and make some checks
		Map<AQuantityKind, Double> mergedMap = qudvHelper.mergeMaps(map1, map2, QudvUnitHelper.QudvCalcMethod.ADD);
		
		//check if all four keys are present
		assertEquals(3, mergedMap.size());
		assertTrue(mergedMap.containsKey(electricCurrent));
		assertTrue(mergedMap.containsKey(temperature));
		assertTrue(mergedMap.containsKey(length));
		
		assertEquals(2.0, mergedMap.get(length), TEST_EPSILON);
		assertEquals(4.2f, mergedMap.get(electricCurrent), TEST_EPSILON);
		assertEquals(2.0, mergedMap.get(temperature), TEST_EPSILON);
		
		// merging a map with the undefined qk with any other map yields again the map with the undefined qk
		Map<AQuantityKind, Double> undefinedQKMap = qudvHelper.createUndefinedQKMap();
		assertEquals(undefinedQKMap, qudvHelper.mergeMaps(undefinedQKMap, map1, QudvCalcMethod.ADD));
		assertEquals(undefinedQKMap, qudvHelper.mergeMaps(map2, undefinedQKMap, QudvCalcMethod.SUBTRACT));
	}
	
	@Test
	public void convertToBaseUnitsTest() {
		SystemOfUnits sou1 = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits" , "SoU", "This is the system of units for this study" , "N/A");
		SystemOfQuantities soq = sou1.getSystemOfQuantities().get(0);
		
		AUnit gram = qudvHelper.getUnitByName(sou1, "Gram");
		AUnit meter = qudvHelper.getUnitByName(sou1, "Meter");
		
		AUnit tonne = qudvHelper.createLinearConversionUnit("Tonne", "t", "", "", gram.getQuantityKind(), gram, 1000000.0);
		AUnit minute = qudvHelper.getUnitByName(sou1, "Minute");
		AUnit kilometer = qudvHelper.getUnitByName(sou1, "Kilometer");
		
		AUnit squareMeter = qudvHelper.getUnitByName(sou1, "Square Meter");
		AUnit hertz = qudvHelper.getUnitByName(sou1, "Hertz");

		HashMap<AUnit, Double> unitFactorMap = new HashMap<AUnit, Double>();
		unitFactorMap.clear();
		unitFactorMap.put(minute, 2.0);
		DerivedUnit squareMinute = qudvHelper.createAndAddDerivedUnit("Square Minute", "min²", "", "", qudvHelper.getQuantityKindByName(soq, "Time Squared"), unitFactorMap);
		
		HashMap<AQuantityKind, Double> factorMap = new HashMap<AQuantityKind, Double>();
		factorMap.clear();
		factorMap.put(gram.getQuantityKind(), 3.0);
		DerivedQuantityKind cubicMass = qudvHelper.createAndAddDerivedQuantityKind("Cubic Mass", "M³", "", "",  factorMap);
		soq.getQuantityKind().add(cubicMass);
		unitFactorMap.clear();
		unitFactorMap.put(tonne, 3.0);
		DerivedUnit cubeTonne = qudvHelper.createAndAddDerivedUnit("Cube Tonne", "t³", "Nonsense", "", cubicMass, unitFactorMap);
		
		AUnit squareKilometer = qudvHelper.getUnitByName(sou1, "Square Kilometer");

		AUnit fahrenheit = qudvHelper.getUnitByName(sou1, "Degree Fahrenheit");
		AUnit hour= qudvHelper.getUnitByName(sou1, "Hour");
		
		AUnit pfund = qudvHelper.createLinearConversionUnit("Pfund", "Pf", "", "", gram.getQuantityKind(), gram, 500.0);
		AUnit zentner = qudvHelper.createLinearConversionUnit("Zentner", "Ztr", "", "", gram.getQuantityKind(), pfund, 100.0);
		
		Prefix centi = null;
		for (Prefix pr : sou1.getPrefix()) 
		{
			if (pr.getName().equals("centi")) 
			{
				centi = pr;
			}
		};
		PrefixedUnit centiKilometer = qudvHelper.createPrefixedUnit("CentiKilometer", "ckm", "", "", meter.getQuantityKind(), centi, kilometer);

		unitFactorMap.clear();
		unitFactorMap.put(tonne, 1.0);
		unitFactorMap.put(pfund, 2.0);
		DerivedUnit strangeCubeMass = qudvHelper.createAndAddDerivedUnit("Strange Cube Mass", "x³", "Nonsense", "", cubicMass, unitFactorMap);
		
		AUnit meterPerSecond = qudvHelper.getUnitByName(sou1, "Meter Per Second");
		
		unitFactorMap.clear();
		unitFactorMap.put(meter, 1.0);
		unitFactorMap.put(minute, -1.0);
		DerivedUnit meterPerMinute = qudvHelper.createAndAddDerivedUnit("Meter Per Minute", "m¹ min⁻¹", "", "", meterPerSecond.getQuantityKind(), unitFactorMap);
		unitFactorMap.clear();
		unitFactorMap.put(minute, -1.0);
		unitFactorMap.put(meter, 1.0);
		DerivedUnit meterPerMinute2 = qudvHelper.createAndAddDerivedUnit("Meter Per Minute2", "min⁻¹ m¹", "", "", meterPerSecond.getQuantityKind(), unitFactorMap);
		
		unitFactorMap.clear();
		AUnit second = qudvHelper.getUnitByName(sou1, "Second");
		unitFactorMap.put(second, -1.0);
		unitFactorMap.put(kilometer, 1.0);
		DerivedUnit kilometerPerSecond = qudvHelper.createAndAddDerivedUnit("Kilometer Per Second", "km¹ s⁻¹", "", "", meterPerSecond.getQuantityKind(), unitFactorMap);
		unitFactorMap.clear();
		unitFactorMap.put(kilometer, 1.0);
		unitFactorMap.put(second, -1.0);
		DerivedUnit kilometerPerSecond2 = qudvHelper.createAndAddDerivedUnit("Kilometer Per Second2", "s⁻¹ km¹", "", "", meterPerSecond.getQuantityKind(), unitFactorMap);
		
		AUnit kilometerPerHour = qudvHelper.getUnitByName(sou1, "Kilometer Per Hour");
		
		AUnit megaByte = qudvHelper.getUnitByName(sou1, "Megabyte");
		AUnit megaBytePerSecond = qudvHelper.getUnitByName(sou1, "Megabyte Per Second");
		
		AUnit kilogram = qudvHelper.getUnitByName(sou1, "Kilogram");
		AUnit tonne2 = qudvHelper.createLinearConversionUnit("Tonne2", "t2", "", "", gram.getQuantityKind(), kilogram, 1000.0);
		
		AUnit newton = qudvHelper.getUnitByName(sou1, "Newton");
		unitFactorMap.clear();
		unitFactorMap.put(newton, 1.0);
		unitFactorMap.put(kilometerPerHour, 1.0);
		DerivedUnit strangePower = qudvHelper.createAndAddDerivedUnit("strangePower", "kg¹ m¹ s⁻² km¹ h⁻¹", "", "", qudvHelper.getQuantityKindByName(soq, "Power"), unitFactorMap);
		
		
		//Simple units should have no conversion: g, m
		assertEquals(1.0, qudvHelper.convertFromSourceUnitToBaseUnit(gram, 1.0), TEST_EPSILON);
		assertEquals(2.0, qudvHelper.convertFromSourceUnitToBaseUnit(meter, 2.0), TEST_EPSILON);
		
		//Single linear conversion: t to g 
		assertEquals(3000000.0, qudvHelper.convertFromSourceUnitToBaseUnit(tonne, 3.0), TEST_EPSILON);
		
		//Single affine conversion: min to s
		assertEquals(240.0, qudvHelper.convertFromSourceUnitToBaseUnit(minute, 4.0), TEST_EPSILON);
		
		//Single prefix conversion: km to m
		assertEquals(5000, qudvHelper.convertFromSourceUnitToBaseUnit(kilometer, 5.0), TEST_EPSILON);
		
		//Potentiated simple units have no conversion: m², s⁻¹
		assertEquals(6.0, qudvHelper.convertFromSourceUnitToBaseUnit(squareMeter, 6.0), TEST_EPSILON);
		assertEquals(7.0, qudvHelper.convertFromSourceUnitToBaseUnit(hertz, 7.0), TEST_EPSILON);		
		
		//Potentiated linear converted unit: t³ to g³
		assertEquals(8E18, qudvHelper.convertFromSourceUnitToBaseUnit(cubeTonne, 8.0), TEST_EPSILON);
		
		//Potentiated affine converted unit: min² to s²
		assertEquals(32400.0, qudvHelper.convertFromSourceUnitToBaseUnit(squareMinute, 9.0), TEST_EPSILON);
		
		//Potentiated prefix converted unit: km² to m²
		assertEquals(1E7, qudvHelper.convertFromSourceUnitToBaseUnit(squareKilometer, 10.0), TEST_EPSILON);
		
		//Affine Concatenated units: °F over °C to K, h over min to s 
			//first we convert 1°F into kelvins; this goes through °C, so it's going through 2 affine conversion units to return the final value 
			//1°F = -17.222 °C = 255.917778 K
		assertEquals(255.92778, qudvHelper.convertFromSourceUnitToBaseUnit(fahrenheit, 1.0), TEST_EPSILON);
		assertEquals(39600.0, qudvHelper.convertFromSourceUnitToBaseUnit(hour, 11.0), TEST_EPSILON);
		
		//Linear Concatenated units: Ztr over Pf to g
		assertEquals(600000, qudvHelper.convertFromSourceUnitToBaseUnit(zentner, 12.0), TEST_EPSILON);
		
		//Double Pefixed unit: centi(kilometer) to m
		assertEquals(130, qudvHelper.convertFromSourceUnitToBaseUnit(centiKilometer, 13.0), TEST_EPSILON);
	
		//Simple derivation (product of simple units) should have no conversion
		assertEquals(7.0, qudvHelper.convertFromSourceUnitToBaseUnit(meterPerSecond, 7.0), TEST_EPSILON);		

		//Product of linear converted units: t¹ Pf² to g³
		assertEquals(25E10, qudvHelper.convertFromSourceUnitToBaseUnit(strangeCubeMass, 1.0), TEST_EPSILON);
		
		//Product of affine converted and prefixed units: m¹ min⁻¹, min⁻¹ m¹, km¹ s⁻¹, s⁻¹ km¹, km¹ h⁻¹ to m¹ s⁻¹
		assertEquals(0.13333333, qudvHelper.convertFromSourceUnitToBaseUnit(meterPerMinute, 8.0), TEST_EPSILON);
		assertEquals(9000.0, qudvHelper.convertFromSourceUnitToBaseUnit(kilometerPerSecond, 9.0), TEST_EPSILON);
		assertEquals(0.13333333, qudvHelper.convertFromSourceUnitToBaseUnit(meterPerMinute2, 8.0), TEST_EPSILON);
		assertEquals(9000.0, qudvHelper.convertFromSourceUnitToBaseUnit(kilometerPerSecond2, 9.0), TEST_EPSILON);
			//okay now, let's convert km/h into m/s
			//10 km/h =  2,777778 m/s
		assertEquals(2.777778, qudvHelper.convertFromSourceUnitToBaseUnit(kilometerPerHour, 10.0), TEST_EPSILON);
	
		//Other strange combinations
		
		// MegaByte is a prefixed unit derived from Byte, a unit linear converted from Bit, which is derived from NoUnit  
		// 10 MegaByte will by converted back to Bits and should create  10.000.000 * 8 Bits
		assertEquals(8E7, qudvHelper.convertFromSourceUnitToBaseUnit(megaByte, 10.0), TEST_EPSILON);

		// Further Derivation: Quotient of MegaByte and Second
		// 10 MegaByte per Second will by converted back to Bits and should create  10.000.000 * 8 Bits per Second
		assertEquals(88E6, qudvHelper.convertFromSourceUnitToBaseUnit(megaBytePerSecond, 11.0), TEST_EPSILON);
		
		// Tonne2 is a linear conversion of a prefixed unit
		assertEquals(12000000.0, qudvHelper.convertFromSourceUnitToBaseUnit(tonne2, 12.0), TEST_EPSILON);
		
		// StrangePower gives factors 1000*1000/3600
		// Product of derived Units with prefixes and affine conversion
		assertEquals(10000.0, qudvHelper.convertFromSourceUnitToBaseUnit(strangePower, 36.0), TEST_EPSILON);
		
	}
	
	@Test
	public void testConvertAngleUnits() {
		SystemOfUnits sou1 = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits" , "SoU", "This is the system of units for this study" , "N/A");
		
		AUnit angleDeg = qudvHelper.getUnitByName(sou1, "Degree");
		AUnit angleRad = qudvHelper.getUnitByName(sou1, "Radian");
		
		// assuming radian is the base unit;
		// 180 Degree should return PI
		double result180DegInBase = qudvHelper.convertFromSourceUnitToBaseUnit(angleDeg, 180.0);
		assertEquals("180Degree got correctly converted to base unit", Math.PI, result180DegInBase, TEST_EPSILON);

		double resultPiRadianInBase = qudvHelper.convertFromSourceUnitToBaseUnit(angleRad, Math.PI);
		assertEquals("PI Radian got correctly converted to base unit", Math.PI, resultPiRadianInBase, TEST_EPSILON);
		
		double result360DegInRadians = qudvHelper.convertFromSourceToTargetUnit(angleDeg, 360, angleRad);
		assertEquals("360Degree got correctly converted to radians", Math.PI * 2, result360DegInRadians, TEST_EPSILON);

		double resultPiRadianFromBaseUnit = qudvHelper.convertFromBaseUnitToTargetUnit(angleDeg, Math.PI);
		assertEquals("PI Radian got correctly converted from base unit to degree", 180.0, resultPiRadianFromBaseUnit, TEST_EPSILON);
	}
	
	@Test
	public void inverseConvertTest() {
		SystemOfUnits sou1 = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits" , "SoU", "This is the system of units for this study" , "N/A");
		SystemOfQuantities soq = sou1.getSystemOfQuantities().get(0);
		
		AUnit gram = qudvHelper.getUnitByName(sou1, "Gram");
		AUnit meter = qudvHelper.getUnitByName(sou1, "Meter");
		
		AUnit tonne = qudvHelper.createLinearConversionUnit("Tonne", "t", "", "", gram.getQuantityKind(), gram, 1000000.0);
		AUnit minute = qudvHelper.getUnitByName(sou1, "Minute");
		AUnit celsius = qudvHelper.getUnitByName(sou1, "Degree Celsius");
		AUnit kilometer = qudvHelper.getUnitByName(sou1, "Kilometer");
		
		AUnit squareMeter = qudvHelper.getUnitByName(sou1, "Square Meter");
		AUnit hertz = qudvHelper.getUnitByName(sou1, "Hertz");

		HashMap<AUnit, Double> unitFactorMap = new HashMap<AUnit, Double>();
		unitFactorMap.clear();
		unitFactorMap.put(minute, 2.0);
		DerivedUnit squareMinute = qudvHelper.createAndAddDerivedUnit("Square Minute", "min²", "", "", qudvHelper.getQuantityKindByName(soq, "Time Squared"), unitFactorMap);
		
		HashMap<AQuantityKind, Double> factorMap = new HashMap<AQuantityKind, Double>();
		factorMap.clear();
		factorMap.put(gram.getQuantityKind(), 3.0);
		DerivedQuantityKind cubicMass = qudvHelper.createAndAddDerivedQuantityKind("Cubic Mass", "M³", "", "",  factorMap);
		soq.getQuantityKind().add(cubicMass);
		unitFactorMap.clear();
		unitFactorMap.put(tonne, 3.0);
		DerivedUnit cubeTonne = qudvHelper.createAndAddDerivedUnit("Cube Tonne", "t³", "Nonsense", "", cubicMass, unitFactorMap);
		
		AUnit squareKilometer = qudvHelper.getUnitByName(sou1, "Square Kilometer");

		AUnit fahrenheit = qudvHelper.getUnitByName(sou1, "Degree Fahrenheit");
		AUnit hour= qudvHelper.getUnitByName(sou1, "Hour");
		
		AUnit pfund = qudvHelper.createLinearConversionUnit("Pfund", "Pf", "", "", gram.getQuantityKind(), gram, 500.0);
		AUnit zentner = qudvHelper.createLinearConversionUnit("Zentner", "Ztr", "", "", gram.getQuantityKind(), pfund, 100.0);
		
		Prefix centi = null;
		for (Prefix pr : sou1.getPrefix()) 
		{
			if (pr.getName().equals("centi")) 
			{
				centi = pr;
			}
		};
		PrefixedUnit centiKilometer = qudvHelper.createPrefixedUnit("CentiKilometer", "ckm", "", "", meter.getQuantityKind(), centi, kilometer);

		unitFactorMap.clear();
		unitFactorMap.put(tonne, 1.0);
		unitFactorMap.put(pfund, 2.0);
		DerivedUnit strangeCubeMass = qudvHelper.createAndAddDerivedUnit("Strange Cube Mass", "x³", "Nonsense", "", cubicMass, unitFactorMap);
		
		AUnit meterPerSecond = qudvHelper.getUnitByName(sou1, "Meter Per Second");
		
		unitFactorMap.clear();
		unitFactorMap.put(meter, 1.0);
		unitFactorMap.put(minute, -1.0);
		DerivedUnit meterPerMinute = qudvHelper.createAndAddDerivedUnit("Meter Per Minute", "m¹ min⁻¹", "", "", meterPerSecond.getQuantityKind(), unitFactorMap);
		unitFactorMap.clear();
		unitFactorMap.put(minute, -1.0);
		unitFactorMap.put(meter, 1.0);
		DerivedUnit meterPerMinute2 = qudvHelper.createAndAddDerivedUnit("Meter Per Minute2", "min⁻¹ m¹", "", "", meterPerSecond.getQuantityKind(), unitFactorMap);
		
		unitFactorMap.clear();
		AUnit second = qudvHelper.getUnitByName(sou1, "Second");
		unitFactorMap.put(second, -1.0);
		unitFactorMap.put(kilometer, 1.0);
		DerivedUnit kilometerPerSecond = qudvHelper.createAndAddDerivedUnit("Kilometer Per Second", "km¹ s⁻¹", "", "", meterPerSecond.getQuantityKind(), unitFactorMap);
		unitFactorMap.clear();
		unitFactorMap.put(kilometer, 1.0);
		unitFactorMap.put(second, -1.0);
		DerivedUnit kilometerPerSecond2 = qudvHelper.createAndAddDerivedUnit("Kilometer Per Second2", "s⁻¹ km¹", "", "", meterPerSecond.getQuantityKind(), unitFactorMap);
		
		AUnit kilometerPerHour = qudvHelper.getUnitByName(sou1, "Kilometer Per Hour");
		
		AUnit megaByte = qudvHelper.getUnitByName(sou1, "Megabyte");
		AUnit megaBytePerSecond = qudvHelper.getUnitByName(sou1, "Megabyte Per Second");
		
		AUnit kilogram = qudvHelper.getUnitByName(sou1, "Kilogram");
		AUnit tonne2 = qudvHelper.createLinearConversionUnit("Tonne2", "t2", "", "", gram.getQuantityKind(), kilogram, 1000.0);
		
		AUnit newton = qudvHelper.getUnitByName(sou1, "Newton");
		unitFactorMap.clear();
		unitFactorMap.put(newton, 1.0);
		unitFactorMap.put(kilometerPerHour, 1.0);
		DerivedUnit strangePower = qudvHelper.createAndAddDerivedUnit("strangePower", "kg¹ m¹ s⁻² km¹ h⁻¹", "", "", qudvHelper.getQuantityKindByName(soq, "Power"), unitFactorMap);
		
		//Simple units should have no conversion: g, m
		assertEquals(1.0, qudvHelper.convertFromBaseUnitToTargetUnit(gram, 1.0), TEST_EPSILON);
		assertEquals(2.0, qudvHelper.convertFromBaseUnitToTargetUnit(meter, 2.0), TEST_EPSILON);
		
		//Single linear conversion: t to g 
		assertEquals(3.0, qudvHelper.convertFromBaseUnitToTargetUnit(tonne, 3000000.0), TEST_EPSILON);
		
		//Single affine conversion: min to s
		assertEquals(4.0, qudvHelper.convertFromBaseUnitToTargetUnit(minute, 240.0), TEST_EPSILON);
		assertEquals(0.0, qudvHelper.convertFromBaseUnitToTargetUnit(celsius, 273.15), TEST_EPSILON);
		
		//Single prefix conversion: km to m
		assertEquals(5.0, qudvHelper.convertFromBaseUnitToTargetUnit(kilometer, 5000.0), TEST_EPSILON);
		
		//Potentiated simple units have no conversion: m², s⁻¹
		assertEquals(6.0, qudvHelper.convertFromBaseUnitToTargetUnit(squareMeter, 6.0), TEST_EPSILON);
		assertEquals(7.0, qudvHelper.convertFromBaseUnitToTargetUnit(hertz, 7.0), TEST_EPSILON);		
		
		//Potentiated linear converted unit: t³ to g³
		assertEquals(8.0, qudvHelper.convertFromBaseUnitToTargetUnit(cubeTonne, 8E18), TEST_EPSILON);
		
		//Potentiated affine converted unit: min² to s²
		assertEquals(9.0, qudvHelper.convertFromBaseUnitToTargetUnit(squareMinute, 32400.0), TEST_EPSILON);
		
		//Potentiated prefix converted unit: km² to m²
		assertEquals(10.0, qudvHelper.convertFromBaseUnitToTargetUnit(squareKilometer, 1E7), TEST_EPSILON);
		
		//Affine Concatenated units: °F over °C to K, h over min to s 
			//first we convert 273.15K into degree fahrenheit; this goes through °C, so it's going through 2 affine conversion units to return the final value 
			//273.15K = 32.000 °F
		assertEquals(32.0, qudvHelper.convertFromBaseUnitToTargetUnit(fahrenheit, 273.15), TEST_EPSILON);
		assertEquals(11.0, qudvHelper.convertFromBaseUnitToTargetUnit(hour, 39600.0), TEST_EPSILON);
		
		//Linear Concatenated units: Ztr over Pf to g
		assertEquals(12.0, qudvHelper.convertFromBaseUnitToTargetUnit(zentner, 600000.0), TEST_EPSILON);
		
		//Double Pefixed unit: centi(kilometer) to m
		assertEquals(13.0, qudvHelper.convertFromBaseUnitToTargetUnit(centiKilometer, 130), TEST_EPSILON);
	
		//Simple derivation (product of simple units) should have no conversion
		assertEquals(7.0, qudvHelper.convertFromBaseUnitToTargetUnit(meterPerSecond, 7.0), TEST_EPSILON);		

		//Product of linear converted units: t¹ Pf² to g³
		assertEquals(1.0, qudvHelper.convertFromBaseUnitToTargetUnit(strangeCubeMass, 25E10), TEST_EPSILON);
		
		//Product of affine converted and prefixed units: m¹ min⁻¹, min⁻¹ m¹, km¹ s⁻¹, s⁻¹ km¹, km¹ h⁻¹ to m¹ s⁻¹
		assertEquals(8.0, qudvHelper.convertFromBaseUnitToTargetUnit(meterPerMinute, 0.13333333), TEST_EPSILON);
		assertEquals(9.0, qudvHelper.convertFromBaseUnitToTargetUnit(kilometerPerSecond, 9000.0), TEST_EPSILON);
		assertEquals(8.0, qudvHelper.convertFromBaseUnitToTargetUnit(meterPerMinute2, 0.13333333), TEST_EPSILON);
		assertEquals(9.0, qudvHelper.convertFromBaseUnitToTargetUnit(kilometerPerSecond2, 9000.0), TEST_EPSILON);
			//okay now, let's convert km/h into m/s
			//10 km/h =  2,777778 m/s
		assertEquals(10.0, qudvHelper.convertFromBaseUnitToTargetUnit(kilometerPerHour, 2.777778), TEST_EPSILON);
	
		//Other strange combinations
		
		// MegaByte is a prefixed unit derived from Byte, a unit linear converted from Bit, which is derived from NoUnit  
		// 10 MegaByte will by converted back to Bits and should create  10.000.000 * 8 Bits
		assertEquals(10.0, qudvHelper.convertFromBaseUnitToTargetUnit(megaByte, 8E7), TEST_EPSILON);

		// Further Derivation: Quotient of MegaByte and Second
		// 10 MegaByte per Second will by converted back to Bits and should create  10.000.000 * 8 Bits per Second
		assertEquals(11.0, qudvHelper.convertFromBaseUnitToTargetUnit(megaBytePerSecond, 88E6), TEST_EPSILON);
		
		// Tonne2 is a linear conversion of a prefixed unit
		assertEquals(12.0, qudvHelper.convertFromBaseUnitToTargetUnit(tonne2, 12000000.0), TEST_EPSILON);
		
		// StrangePower gives factors 1000*1000/3600
		// Product of derived Units with prefixes and affine conversion
		assertEquals(36.0, qudvHelper.convertFromBaseUnitToTargetUnit(strangePower, 10000.0), TEST_EPSILON);
	}
	
	@Test
	public void testConvertToString() {
		SystemOfUnits sou1 = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits" , "SoU", "This is the system of units for this study" , "N/A");
		SystemOfQuantities soq = sou1.getSystemOfQuantities().get(0);
		
		AQuantityKind qkLength = qudvHelper.getQuantityKindByName(soq, "Length");
		Map<AQuantityKind, Double> qkMap = new HashMap<>();
		qkMap.put(qkLength, 1d);
		
		String stringRepresentationNeutral = qudvHelper.convertToString(qkMap);
		assertEquals("Correct representation", "L ", stringRepresentationNeutral);
		
		qkMap = new HashMap<>();
		qkMap.put(qkLength, -1234567890d);
		
		String stringRepresentationInt = qudvHelper.convertToString(qkMap);
		String expectedStringRepresentation = "L" 
				+ QudvUnitHelper.SUPERSCRIPT_MINUS 
				+ QudvUnitHelper.SUPERSCRIPT_ONE
				+ QudvUnitHelper.SUPERSCRIPT_TWO
				+ QudvUnitHelper.SUPERSCRIPT_THREE
				+ QudvUnitHelper.SUPERSCRIPT_FOUR
				+ QudvUnitHelper.SUPERSCRIPT_FIVE
				+ QudvUnitHelper.SUPERSCRIPT_SIX
				+ QudvUnitHelper.SUPERSCRIPT_SEVEN
				+ QudvUnitHelper.SUPERSCRIPT_EIGHT
				+ QudvUnitHelper.SUPERSCRIPT_NINE
				+ QudvUnitHelper.SUPERSCRIPT_ZERO
				+ " ";
		assertEquals("Correct representation", expectedStringRepresentation, stringRepresentationInt);
		
		AQuantityKind qkTime = qudvHelper.getQuantityKindByName(soq, "Time");
		qkMap = new HashMap<>();
		qkMap.put(qkTime, -3.5d);
		
		String stringRepresentationDouble = qudvHelper.convertToString(qkMap);
		assertEquals("Correct representation", "T^-3.5 ", stringRepresentationDouble);
	}
	
	@Test
	public void testGetUndefinedQK() {
		assertEquals(QudvUnitHelper.UNDEFINED_QK_NAME, qudvHelper.getUndefinedQK().getName());
	}
	
	@Test
	public void testCreateUndefinedQKMap() {
		Map<AQuantityKind, Double> undefinedQKMap = qudvHelper.createUndefinedQKMap();
		assertEquals(1, undefinedQKMap.size());
		assertEquals(1, undefinedQKMap.get(qudvHelper.getUndefinedQK()), TEST_EPSILON);
	}
}
