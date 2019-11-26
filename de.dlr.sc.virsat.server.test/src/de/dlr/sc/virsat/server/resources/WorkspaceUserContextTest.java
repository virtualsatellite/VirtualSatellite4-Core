/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import java.io.File;

import org.junit.Test;

import de.dlr.sc.virsat.server.resources.WorkspaceUserContext.IWorkspaceUserContextExecution;

public class WorkspaceUserContextTest {

	public static final String TEST_USER_1 = "test_user1";
	public static final String TEST_USER_2 = "test_user2";
	public static final String TEST_REPO_1 = "repo_1";
	public static final String TEST_REPO_2 = "repo_2";
	
	@Test
	public void testGetRepositoryPath() {
		String combinedPath = new WorkspaceUserContext(TEST_USER_1, TEST_REPO_1).getRepoPath();
		String expectedPath = TEST_USER_1 + File.separator + TEST_REPO_1; 
		assertEquals("Got correct combined path", expectedPath, combinedPath);
	}

	@Test
	public void testGetContext() {
		Object context1 = new WorkspaceUserContext(TEST_USER_1, TEST_REPO_1).getContext();
		Object context2 = new WorkspaceUserContext(TEST_USER_1, TEST_REPO_2).getContext();
		Object context3 = new WorkspaceUserContext(TEST_USER_2, TEST_REPO_1).getContext();
		
		assertSame("Got same context for the same user", context1, context2);
		assertNotSame("Got different context for different users", context1, context3);
	}

	@Test
	public void testRunInContext() {
		WorkspaceUserContext wuContext1 = new WorkspaceUserContext(TEST_USER_1, TEST_REPO_1);
		
		final String TEST_RESULT = "Result";
		
		Object context1 = wuContext1.getContext(); 
		String result = wuContext1.runInContext(new IWorkspaceUserContextExecution() {
			@Override
			public String run(Object context, WorkspaceUserContext workspaceUserContext) {
				assertEquals("Got correct Context", context1, context);
				assertSame("Got correct WorkspaceUserContext", wuContext1, workspaceUserContext);
				return TEST_RESULT;
			}
		});
		
		assertEquals("Got correct result", TEST_RESULT, result);
	}
}
