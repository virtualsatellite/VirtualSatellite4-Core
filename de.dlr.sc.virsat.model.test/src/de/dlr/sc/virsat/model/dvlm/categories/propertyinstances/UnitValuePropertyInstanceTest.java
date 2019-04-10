/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * Test cases for the intrinsic unit conversion methods.
 * @author fisc_ph
 *
 */
public class UnitValuePropertyInstanceTest {

	@Test
	public void testGetValueToBaseUnit() {
		final double TEST_FLOAT_METER_TO_BASE = 21.56;
		final double TEST_FLOAT_KILOMETER_TO_BASE = 21560;
		final String TEST_STRING = "21.56";
		final double EPSILON = 0.001;

		UnitValuePropertyInstance uvpi = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		AUnit unitKm = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		AUnit unitM = QudvUnitHelper.getInstance().getUnitByName(sou, "Meter");

		// in case no unit is set, the value should be returned as is.
		uvpi.setValue(TEST_STRING);
		uvpi.setUnit(null);
		assertEquals("Value correctly set", TEST_FLOAT_METER_TO_BASE, uvpi.getValueToBaseUnit(), EPSILON);

		// now test to receive a value defined in meters as base unit
		uvpi.setUnit(unitM);
		assertEquals("Value correctly set", TEST_FLOAT_METER_TO_BASE, uvpi.getValueToBaseUnit(), EPSILON);
		
		// and now kilometers as base unit
		uvpi.setUnit(unitKm);
		assertEquals("Value correctly set", TEST_FLOAT_KILOMETER_TO_BASE, uvpi.getValueToBaseUnit(), EPSILON);
	}

	@Test
	public void testSetValueAsBaseUnit() {
		final double INPUT_VALUE = 12.45;
		final String INPUT_VALUE_AS_M = "12.45";
		final String INPUT_VALUE_AS_KM = "0.01245";
		
		UnitValuePropertyInstance uvpi = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		AUnit unitKm = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		AUnit unitM = QudvUnitHelper.getInstance().getUnitByName(sou, "Meter");

		// In case no value is set, we are on base unit
		uvpi.setUnit(null);
		uvpi.setValueAsBaseUnit(INPUT_VALUE);
		assertEquals("Value correctly set", INPUT_VALUE_AS_M, uvpi.getValue());
		
		// now set meters via base unit
		uvpi.setUnit(unitM);
		uvpi.setValueAsBaseUnit(INPUT_VALUE);
		assertEquals("Value correctly set", INPUT_VALUE_AS_M, uvpi.getValue());
		
		// now set kilometers via base unit
		uvpi.setUnit(unitKm);
		uvpi.setValueAsBaseUnit(INPUT_VALUE);
		assertEquals("Value correctly set", INPUT_VALUE_AS_KM, uvpi.getValue());
	}
}
