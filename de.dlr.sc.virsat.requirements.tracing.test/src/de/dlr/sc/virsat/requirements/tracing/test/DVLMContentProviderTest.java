/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.requirements.tracing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.ILabelProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.requirements.tracing.ui.ISystemModelContentProvider;
import de.dlr.sc.virsat.requirements.tracing.ui.impl.DVLMSystemModelContentProvider;

/**
 * @author fran_tb
 *
 */
public class DVLMContentProviderTest extends AProjectTestCase {

	protected static final String TEST_CONCEPT_NAME = "TestConcept";
	protected static final String TEST_CATEGORY_NAME = "TestCategroy";
	protected static final String TEST_STRUCTURAL_ELEMENT_NAME = "TestElement";
	protected Concept testConcept;
	protected Category testCategory1;
	protected Category testCategory2;
	protected StructuralElement se1;
	protected StructuralElement se2;

	ISystemModelContentProvider contentProvider;

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		contentProvider = new DVLMSystemModelContentProvider();
		addEditingDomainAndRepository();
		testConcept = getTestConcept();

	}

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
	}

	/**
	 * Creates a simple instance of a test concept
	 * 
	 * @return a example test concept
	 */
	protected Concept getTestConcept() {

		Concept testConcept = ConceptsFactory.eINSTANCE.createConcept();
		testConcept.setName(TEST_CONCEPT_NAME);
		testCategory1 = CategoriesFactory.eINSTANCE.createCategory();
		testCategory1.setName(TEST_CATEGORY_NAME);
		
		testCategory2 = CategoriesFactory.eINSTANCE.createCategory();
		se1 = StructuralFactory.eINSTANCE.createStructuralElement();
		se1.setName(TEST_STRUCTURAL_ELEMENT_NAME);
		se2 = StructuralFactory.eINSTANCE.createStructuralElement();

		testConcept.getCategories().add(testCategory1);
		testConcept.getCategories().add(testCategory2);
		testConcept.getStructuralElements().add(se1);
		testConcept.getStructuralElements().add(se2);
		
		return testConcept;

	}

	@Test
	public void testGetInput() {
		Object inputObject = contentProvider.getInput(testProject);

		assertTrue("With DVLM input is the repository", inputObject instanceof Repository);
		assertEquals("Repository is from the test project", repository, inputObject);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetTypeListInput() {

		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				repository.getActiveConcepts().add(testConcept);
			}
		});

		List<Object> typeList = (List<Object>) contentProvider.getTypeListInput(testProject);

		assertTrue(typeList.contains(testCategory1));
		assertTrue(typeList.contains(testCategory2));
		assertTrue(typeList.contains(se1));
		assertTrue(typeList.contains(se2));
	}

	@Test
	public void testGetTypeListLabelProvider() {

		ILabelProvider labelContentProviderObject = contentProvider.getTypeListLabelProvider();

		assertEquals("Content provider does not return correct category name", TEST_CATEGORY_NAME,
				labelContentProviderObject.getText(testCategory1));
		assertEquals("Content provider does not return correct structural element name", TEST_STRUCTURAL_ELEMENT_NAME,
				labelContentProviderObject.getText(se1));
		
		//Add test for actual images...
		assertEquals("Test element don't have images", labelContentProviderObject.getImage(testCategory1), null);
		assertEquals("Test element don't have images", labelContentProviderObject.getImage(se1), null);
	}
}
