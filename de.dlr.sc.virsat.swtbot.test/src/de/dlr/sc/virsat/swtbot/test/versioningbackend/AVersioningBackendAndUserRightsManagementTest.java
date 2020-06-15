/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.test.versioningbackend;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.swtbot.test.ASwtBotTestCase;

public abstract class AVersioningBackendAndUserRightsManagementTest extends ASwtBotTestCase {

	@Before
	public void before() throws Exception {
		super.before();
		
		// Now prepare the versioning-backends
		setUpVersioningBackend();
		
		// Switch back to virtual Satellite perspective
		openCorePerspective();

		// Share the test project with the backend
		shareTestProjectWithVersioningBackend();
	}
	
	protected abstract void setUpVersioningBackend() throws IOException;
	
	protected abstract void shareTestProjectWithVersioningBackend();
	
	@Test
	public void testCommitProject() {
		fail("Not yet implemented");
	}	
	
	@Test
	public void testUpdateProject() {
		fail("Not yet implemented");
	}
}
