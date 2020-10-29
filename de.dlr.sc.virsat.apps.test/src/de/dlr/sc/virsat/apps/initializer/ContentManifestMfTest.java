/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.initializer;

import java.io.IOException;

import org.junit.Test;

import de.dlr.sc.virsat.apps.test.util.GeneratorJunitAssert;

/**
 * tests the content of the manifest generator
 * @author fisc_ph
 *
 */
public class ContentManifestMfTest {

	public static final String TEST_PROJECT_NAME = "de.dlr.virsat.app.analysis";
	
	@Test
	public void testGetContents() throws IOException {
		CharSequence content = new ContentManifestMf().getContents(TEST_PROJECT_NAME);
		GeneratorJunitAssert.assertEqualContent(content, "/resources/expectedGeneratorContent/AppManifest.MF");
	}
}
