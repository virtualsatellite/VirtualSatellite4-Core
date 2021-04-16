/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources.modelaccess;

import org.junit.Test;

import de.dlr.sc.virsat.server.resources.ModelAccessResourceTest;

public class StructuralElementInstanceResourceTest extends ModelAccessResourceTest {

	@Test
	public void testSeiGet() throws Exception {
		testGetSei(tSei);
	}

	@Test
	public void testSeiPut() throws Exception {
		testPutSei(tSei);
	}
	
	@Test
	public void testSeiDelete() throws Exception {
		testDeleteSei(tSei);
	}
	
	@Test
	public void testChildSeiGet() throws Exception {
		testGetSei(tSeiChild);
	}
	
	@Test
	public void testChildSeiPut() throws Exception {
		testPutSei(tSeiChild);
	}
}
