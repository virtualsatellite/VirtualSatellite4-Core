/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.team.VersionControlBackendProviderTest;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackendTest;
import de.dlr.sc.virsat.team.svn.VirSatSvnVersionControlBackendTest;
import junit.framework.JUnit4TestAdapter;

@RunWith(Suite.class)

@SuiteClasses({
	VirSatGitVersionControlBackendTest.class,
	VirSatSvnVersionControlBackendTest.class,
	VersionControlBackendProviderTest.class
})

public class AllTests {

	private AllTests() {
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}