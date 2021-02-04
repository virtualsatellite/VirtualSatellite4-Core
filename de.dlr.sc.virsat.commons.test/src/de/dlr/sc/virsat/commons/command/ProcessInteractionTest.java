/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.external.ProcessInteraction;


/**
 * Test cases for the command runner.
 * @author maur_pa
 *
 */
public class ProcessInteractionTest {
	
	private ProcessInteraction cr;
	
	@Before
	public void setUp() throws Exception {
		cr = new ProcessInteraction();
	}

	@Test
	public void testStandardCommandRunner() {
		assertEquals(cr.startCommandRunner("ping", ""), true);
	}
	
}
