/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.concept.generator.core.ConceptLanguageImplicitSuperTypeHandler;
import de.dlr.sc.virsat.model.concept.resources.ConceptResourceLoader;
import de.dlr.sc.virsat.model.concept.test.MockupConceptResourceLoader;
import de.dlr.sc.virsat.model.concept.test.MockupFileSystemAccess;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;

/**
 * Test class for the concept preprocessor - checks that model adaption of the concept
 * done before generating resource from it are correctly applied
 */
public class ConceptPreprocessorTest extends AConceptProjectTestCase {
	
	
	private Concept conceptSource;
	private Category testExtendsCategory;
	private Category testCategory;
	private Resource resource;
	
	private Concept mockupConceptAfterReloading;
	private Resource mockupResource;
	
	private MockupFileSystemAccess fsa = new MockupFileSystemAccess();
	private ConceptPreprocessor processor;
	private ConceptLanguageImplicitSuperTypeHandler conceptLanguageHandler = new ConceptLanguageImplicitSuperTypeHandler();
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		processor = new ConceptPreprocessor(fsa);
		
		//Create concept to be processed
		conceptSource = ConceptsFactory.eINSTANCE.createConcept();
		testExtendsCategory = CategoriesFactory.eINSTANCE.createCategory();
		testCategory = CategoriesFactory.eINSTANCE.createCategory();
		testExtendsCategory.setExtendsCategory(testCategory);
		resource = editingDomain.getResourceSet().createResource(URI.createFileURI("test.concept"));
		
		//Create concept that is (re-)loaded after processing (mocks the not persisted updated concept)
		mockupConceptAfterReloading = ConceptsFactory.eINSTANCE.createConcept();
		mockupResource = editingDomain.getResourceSet().createResource(URI.createFileURI("test.xmi"));
		
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				resource.getContents().add(conceptSource);
				mockupResource.getContents().add(mockupConceptAfterReloading);
			}
		});
		
		ConceptResourceLoader.injectInstance(new MockupConceptResourceLoader());
	}
	
	
	@Test
	public void testProcess() throws IOException {
		Concept processedConcept = processor.process(resource);
		
		//Check that content is correctly processed
		Concept expectedConcept = conceptLanguageHandler.addImplicitSuperType(conceptSource);
		Resource mockupResource = new XMIResourceImpl();
		mockupResource.getContents().add(expectedConcept);
		OutputStream outputStream = new ByteArrayOutputStream();
		mockupResource.save(outputStream, null);
		assertEquals("The concept should be handled by the implicit super type handler", 
				fsa.getContents().get(0), outputStream.toString());
		
		//Check that output filenames were processed correctly
		final String EXPECTED_CONCEPT_XMI_NAME = "concept.xmi";
		final String EXPECTED_CONCEPT_XMI_VERSION_NAME = "concept_v1_0.xmi";
		assertTrue("One generated file should be name as the concept but with XMI", 
				fsa.getGeneratedFilenames().contains(EXPECTED_CONCEPT_XMI_NAME));
		assertTrue("One generated file should be name as the concept but with XMI and version number", 
				fsa.getGeneratedFilenames().contains(EXPECTED_CONCEPT_XMI_VERSION_NAME));
		
		//Check that concept is properly reloaded after processing
		assertEquals("The precompiler should load the xmi concept (mockup) after processing",  
				mockupConceptAfterReloading, processedConcept);
	}
	
	
}
