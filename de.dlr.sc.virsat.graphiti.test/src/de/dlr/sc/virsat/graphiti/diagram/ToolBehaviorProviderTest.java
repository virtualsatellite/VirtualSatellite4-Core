/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.diagram;


import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ToolBehaviorProviderTest {

	@Test
	public void testIsDefaultBendPointRenderingActive() {
		ToolBehaviorProvider behaviorProivder = new ToolBehaviorProvider(null);
		
		assertFalse("Make sure bezierlines are not provided", behaviorProivder.isDefaultBendPointRenderingActive());
	}
}
