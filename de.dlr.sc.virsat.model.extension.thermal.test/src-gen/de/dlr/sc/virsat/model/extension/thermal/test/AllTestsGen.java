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

// *****************************************************************
// * Import Statements
// *****************************************************************

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;

import de.dlr.sc.virsat.model.extension.thermal.migrator.Migrator1v0Test;
import de.dlr.sc.virsat.model.extension.thermal.model.ReportsTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ComponentResultTest;
import de.dlr.sc.virsat.model.extension.thermal.validator.ThermalValidatorTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysisTest;
import de.dlr.sc.virsat.model.extension.thermal.model.MaterialTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ComponentMeshSizeTest;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisTypeTest;
import de.dlr.sc.virsat.model.extension.thermal.model.HeatFlowToFaceTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalInterfaceTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalElementParametersTest;
import de.dlr.sc.virsat.model.extension.thermal.model.FaceRadiationTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalContactsTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalPortListTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalInterfaceListTest;
import de.dlr.sc.virsat.model.extension.thermal.model.SingleFaceRadiationListTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysisResultsTest;
import de.dlr.sc.virsat.model.extension.thermal.model.AnalysisResultTest;
import de.dlr.sc.virsat.model.extension.thermal.model.TemperatureBoundaryTest;
import de.dlr.sc.virsat.model.extension.thermal.model.MeshSizesTest;
import de.dlr.sc.virsat.model.extension.thermal.model.BoundaryConditionsTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalPortTest;
import de.dlr.sc.virsat.model.extension.thermal.model.MaterialCollectionTest;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalDataTest;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	ComponentMeshSizeTest.class,
	MeshSizesTest.class,
	ThermalAnalysisTest.class,
	ThermalDataTest.class,
	SingleFaceRadiationListTest.class,
	ThermalContactsTest.class,
	ThermalPortListTest.class,
	ThermalInterfaceListTest.class,
	BoundaryConditionsTest.class,
	ThermalAnalysisResultsTest.class,
	AnalysisResultTest.class,
	TemperatureBoundaryTest.class,
	HeatFlowToFaceTest.class,
	AnalysisTypeTest.class,
	ReportsTest.class,
	ThermalElementParametersTest.class,
	FaceRadiationTest.class,
	ThermalPortTest.class,
	ThermalInterfaceTest.class,
	MaterialTest.class,
	ComponentResultTest.class,
	MaterialCollectionTest.class,
	Migrator1v0Test.class,
	ThermalValidatorTest.class,
				})

/**
 * 
 * Test Collection
 *
 */
public class AllTestsGen {

	/**
	 * Constructor
	 */
	private AllTestsGen() {
	}
	
	/**
	 * Test Adapter
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}
