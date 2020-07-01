/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.extension.visualisation.VisualisationTreeInstantiatorTest;
import de.dlr.sc.virsat.model.extension.visualisation.VisualisationTreeManagerTest;
import de.dlr.sc.virsat.model.extension.visualisation.comparison.CompareModelGeometryTest;
import de.dlr.sc.virsat.model.extension.visualisation.comparison.CompareModelPropertyTest;
import de.dlr.sc.virsat.model.extension.visualisation.comparison.ModelPropertyColorMapTest;
import de.dlr.sc.virsat.model.extension.visualisation.delta.CloneShapeDeltaTest;
import de.dlr.sc.virsat.model.extension.visualisation.delta.ColorDeltaTest;
import de.dlr.sc.virsat.model.extension.visualisation.delta.GhostShapeDeltaTest;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModelIoTest;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.SceneGraphClientTest;
import junit.framework.JUnit4TestAdapter;


/**
 * @author desh_me
 */
@RunWith(Suite.class)

@SuiteClasses({
	VisualisationTreeInstantiatorTest.class,
    VisualisationTreeManagerTest.class,
    VisualisationDeltaModelIoTest.class,
    CloneShapeDeltaTest.class,
    ColorDeltaTest.class,
    GhostShapeDeltaTest.class,
    CompareModelGeometryTest.class,
    CompareModelPropertyTest.class,
    ModelPropertyColorMapTest.class,
    SceneGraphClientTest.class})

/**
 * 
 * Test Collection
 *
 */
public class AllTests {

	/**
	 * Constructor
	 */
	private AllTests() {
	}
	
	/**
	 * Test Adapter
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}
