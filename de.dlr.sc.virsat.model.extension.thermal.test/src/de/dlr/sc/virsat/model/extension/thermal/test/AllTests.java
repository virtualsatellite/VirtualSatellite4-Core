/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.extension.thermal.cad.CadExporterThermalTest;
import de.dlr.sc.virsat.model.extension.thermal.cad.CadImporterThermalTest;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisResultTest;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisTypeTest;
import de.dlr.sc.virsat.model.extension.thermal.model.BoundaryConditionTest;
import de.dlr.sc.virsat.model.extension.thermal.model.BoundaryConditionsTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ComponentMeshSizeTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ComponentResultTest;
import de.dlr.sc.virsat.model.extension.thermal.model.FaceRadiationTest;
import de.dlr.sc.virsat.model.extension.thermal.model.HeatFlowToFaceTest;
import de.dlr.sc.virsat.model.extension.thermal.model.MaterialCollectionTest;
import de.dlr.sc.virsat.model.extension.thermal.model.MaterialTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ReportsTest;
import de.dlr.sc.virsat.model.extension.thermal.model.SingleFaceEmissivitiesTest;
import de.dlr.sc.virsat.model.extension.thermal.model.SingleFaceRadiationTest;
import de.dlr.sc.virsat.model.extension.thermal.model.TemperatureBoundaryTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysisTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalContactsTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalDataTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalElementParametersTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalInterfaceTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalPortTest;
import de.dlr.sc.virsat.model.extension.thermal.validator.ValidatorTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({ 
	ValidatorTest.class,
	CadExporterThermalTest.class,
	CadImporterThermalTest.class,
	AnalysisResultTest.class,
	AnalysisTypeTest.class,
	BoundaryConditionsTest.class,
	BoundaryConditionTest.class,
	ComponentMeshSizeTest.class,
	ComponentResultTest.class,
	FaceRadiationTest.class,
	HeatFlowToFaceTest.class,
	MaterialCollectionTest.class,
	MaterialTest.class,
	ReportsTest.class,
	SingleFaceEmissivitiesTest.class,
	SingleFaceRadiationTest.class,
	TemperatureBoundaryTest.class,
	ThermalAnalysisTest.class,
	ThermalContactsTest.class,
	ThermalDataTest.class,
	ThermalElementParametersTest.class,
	ThermalInterfaceTest.class,
	ThermalPortTest.class
	})

public class AllTests {

	/**
	 * Constructor for Test Suite
	 */
	private AllTests() {
	}

	/**
	 * entry point for test suite
	 * 
	 * @return the test suite
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}
}