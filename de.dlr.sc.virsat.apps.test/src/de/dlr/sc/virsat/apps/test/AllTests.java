/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.apps.initializer.ContentBuildPropertiesTest;
import de.dlr.sc.virsat.apps.initializer.ContentExampleAppTest;
import de.dlr.sc.virsat.apps.initializer.ContentManifestMfTest;
import de.dlr.sc.virsat.apps.initializer.ManifestRequiredBundlesConceptUpdaterTest;
import de.dlr.sc.virsat.apps.initializer.VirsatAppsInititalizerTest;
import junit.framework.JUnit4TestAdapter;
/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({	ContentManifestMfTest.class,
				ContentBuildPropertiesTest.class,
				ContentExampleAppTest.class,
				VirsatAppsInititalizerTest.class,
				ManifestRequiredBundlesConceptUpdaterTest.class
				})

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