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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.servlet.RepoManagementServlet;
import de.dlr.sc.virsat.server.servlet.VirSatModelAccessServlet;
import de.dlr.sc.virsat.server.test.AJettyServerTest;

public class DocumentationResourceTest extends AJettyServerTest {

	private static final String MODEL_DIR = "model";
	private static final String MANAGEMENT_DIR = "management";

	/**
	 * Gets the file at apiPath from the server and assert that the right content got returned
	 */
	private void testDocumentation(String apiPath, String directory, String filename) throws IOException {
		String docFileContent = webTarget
				.path(apiPath)
				.path(filename)
				.request()
				.get(String.class);

		String realmResourceName = "doc-gen" + File.separator + directory + File.separator + filename;
		String expectedFileContent =  IOUtils.toString(FileLocator.openStream(Activator.getDefault().getBundle(), 
				new Path(realmResourceName), false), StandardCharsets.UTF_8);
		assertEquals("Right file content", expectedFileContent, docFileContent);
	}
	
	@Test
	public void testDocumentation() throws IOException {
		testDocumentation(VirSatModelAccessServlet.MODEL_API, MODEL_DIR, DocumentationResource.SWAGGER_JSON);
		testDocumentation(VirSatModelAccessServlet.MODEL_API, MODEL_DIR, DocumentationResource.SWAGGER_YAML);
		
		testDocumentation(RepoManagementServlet.MANAGEMENT_API, MANAGEMENT_DIR, DocumentationResource.SWAGGER_JSON);
		testDocumentation(RepoManagementServlet.MANAGEMENT_API, MANAGEMENT_DIR, DocumentationResource.SWAGGER_YAML);
	}
	
	/**
	 * Gets the file at apiPath from the server and assert that the resource could be accessed
	 */
	private void testAuthentication(String apiPath, String filename) {		
		Response response = webTarget
			.path(apiPath)
			.path(filename)
			.request()
			.get();
		assertEquals("The resources are not secured and can be accessed even without an auth header", HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testAuthentication() {
		testAuthentication(VirSatModelAccessServlet.MODEL_API, DocumentationResource.SWAGGER_JSON);
		testAuthentication(VirSatModelAccessServlet.MODEL_API, DocumentationResource.SWAGGER_YAML);
		
		testAuthentication(RepoManagementServlet.MANAGEMENT_API, DocumentationResource.SWAGGER_JSON);
		testAuthentication(RepoManagementServlet.MANAGEMENT_API, DocumentationResource.SWAGGER_YAML);
	}
}
