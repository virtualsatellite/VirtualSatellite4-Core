/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * Test the concept resource loader class, to mock-up the URI handling in the IDE,
 * this tests overwrites URI creation to use plugin URIs instead  
 */
public class ConceptResourceLoaderTest {
	
	static class TestResourceLoader extends ConceptResourceLoader {
		@Override
		protected URI getUriFromPath(String path) {
			return URI.createPlatformPluginURI(path, true);
		}
	}
	
	@Test
	public void testCreateInstance() {
		assertNotNull(ConceptResourceLoader.getInstance());
	}
	
	@Test
	public void testLoadConcept() {
		// Use customized loader with modified URI creation to be able to test 
		ConceptResourceLoader testInstance = new TestResourceLoader();
		
		Concept testConcept = testInstance.loadConceptByName("de.dlr.sc.virsat.model.extension.tests");
		Concept expectedConcept = ConceptXmiLoader.loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.tests/concept/concept.xmi");
		
		assertNotNull("Concept should be loaded", testConcept);
		assertEquals("Should be same as with loaded from test util class", expectedConcept.getFullQualifiedName(), testConcept.getFullQualifiedName());
	}
	
	@Test
	public void testUriCreation() {
		final String TEST_PROJECT = "test_project";
		final String TEST_FILE_PATH = "test.test";
		final String TEST_PATH = TEST_PROJECT + "/" + TEST_FILE_PATH;
		URI testURI = ConceptResourceLoader.getInstance().getUriFromPath(TEST_PATH);
		
		assertTrue("Platform URI", testURI.isPlatform());
		assertTrue("Platform Resource URI", testURI.isPlatformResource());
		assertTrue("Should contain test path", testURI.toString().contains(TEST_FILE_PATH));
		assertTrue("Should contain test path", testURI.toString().contains(TEST_PROJECT));
	}
	
	@Test
	public void testGetConceptDMFResourceUri() {
		// Use customized loader with modified URI creation to be able to test 
		ConceptResourceLoader testInstance = new TestResourceLoader();
				
		URI dmfURI = testInstance.getConceptDMFResourceUri("de.dlr.sc.virsat.model.extension.tests");
		assertNotNull("Uri should be created", dmfURI);
		assertTrue("DMF model uri should be ecore file", dmfURI.fileExtension().equals("ecore"));
		assertNotNull("Resource should exist", new ResourceSetImpl().getResource(dmfURI, true));
	}

}
