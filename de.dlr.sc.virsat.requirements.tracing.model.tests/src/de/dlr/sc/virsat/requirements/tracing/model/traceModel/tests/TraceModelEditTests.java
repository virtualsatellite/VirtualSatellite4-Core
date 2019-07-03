/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.requirements.tracing.model.traceModel.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.dlr.sc.virsat.requirements.tracing.model.traceModel.provider.TraceModelEditPlugin;

/**
 * @author fran_tb
 *
 */
public class TraceModelEditTests {
	
	@Test
	public void testEditPlugin() {
		
		assertNotNull(TraceModelEditPlugin.INSTANCE.getPluginResourceLocator());
		assertNotNull(TraceModelEditPlugin.getPlugin());
		
	}

}
