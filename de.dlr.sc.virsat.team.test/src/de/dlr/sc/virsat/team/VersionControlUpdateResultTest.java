/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VersionControlUpdateResultTest {
	
	@Test
	public void testHasChanges() {
		VersionControlUpdateResult result = new VersionControlUpdateResult();
		
		assertFalse(result.hasChanges());
		
		result.addChange(new VersionControlChange(null, null, null));
		
		assertEquals(1, result.getChanges().size());
		assertTrue(result.hasChanges());
	}
}
