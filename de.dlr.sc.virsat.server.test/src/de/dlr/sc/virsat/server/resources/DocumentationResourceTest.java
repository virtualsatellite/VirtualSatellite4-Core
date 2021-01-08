/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Test;

import de.dlr.sc.virsat.server.servlet.RepoManagementServlet;
import de.dlr.sc.virsat.server.servlet.VirSatModelAccessServlet;
import de.dlr.sc.virsat.server.test.AJettyServerTest;

public class DocumentationResourceTest extends AJettyServerTest {

	/**
	 * Gets the file at apiPath from the server and assert that the right content got returned
	 */
	private void testDocumentation(String apiPath, String filename) throws IOException {
		String docFileContent = webTarget
				.path(apiPath)
				.path(filename)
				.request()
				.get(String.class);

		assertNotEquals("File found", DocumentationResource.FILE_NOT_FOUND, docFileContent);
	}
	
	@Test
	public void testDocumentation() throws IOException {
		testDocumentation(VirSatModelAccessServlet.MODEL_API, DocumentationResource.SWAGGER_JSON);
		testDocumentation(VirSatModelAccessServlet.MODEL_API, DocumentationResource.SWAGGER_YAML);
		
		testDocumentation(RepoManagementServlet.MANAGEMENT_API, DocumentationResource.SWAGGER_JSON);
		testDocumentation(RepoManagementServlet.MANAGEMENT_API, DocumentationResource.SWAGGER_YAML);
	}
}
