/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.graphiti.diagram.BeanIndependenceSolverTest;
import de.dlr.sc.virsat.graphiti.diagram.ToolBehaviorProviderTest;
import de.dlr.sc.virsat.graphiti.util.DiagramHelperTest;
import junit.framework.JUnit4TestAdapter;


@RunWith(Suite.class)

@SuiteClasses({
	BeanIndependenceSolverTest.class,
	DiagramHelperTest.class,
	ToolBehaviorProviderTest.class
})

public class AllTests {

	private AllTests() {
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}
