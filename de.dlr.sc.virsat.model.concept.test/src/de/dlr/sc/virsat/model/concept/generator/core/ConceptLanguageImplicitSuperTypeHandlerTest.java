/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.ext.core.Activator;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;

/**
 * Test class for the implicit super type handler
 */
public class ConceptLanguageImplicitSuperTypeHandlerTest {

	private static final String TEST_CONCEPT_ID = "de.dlr.sc.virsat.model.extension.tests";
	private Category testCategoryOne;
	private Category testCategorySuper;
	private Category testCategoryHasSuper;
	private Concept testConcept;

	private ConceptLanguageImplicitSuperTypeHandler implicitSuperTypeHandler;

	@Before
	public void setUp() throws CoreException {

		testConcept = ConceptsFactory.eINSTANCE.createConcept();
		testConcept.setName(TEST_CONCEPT_ID);

		testCategoryOne = CategoriesFactory.eINSTANCE.createCategory();
		testCategorySuper = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryHasSuper = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryHasSuper.setExtendsCategory(testCategorySuper);

		testConcept.getCategories().add(testCategoryOne);
		testConcept.getCategories().add(testCategorySuper);
		testConcept.getCategories().add(testCategoryHasSuper);

		implicitSuperTypeHandler = new ConceptLanguageImplicitSuperTypeHandler();
	}

	@Test
	public void testAddImplicitSuperType() {

		Concept updatedConcept = implicitSuperTypeHandler.addImplicitSuperType(testConcept);

		testCategoryOne = updatedConcept.getCategories().get(0);
		assertEquals("Category without explict super type should have the generic category as super type",
				GenericCategory.FULL_QUALIFIED_CATEGORY_NAME,
				testCategoryOne.getExtendsCategory().getFullQualifiedName());
		testCategorySuper = updatedConcept.getCategories().get(1);
		assertEquals("Category without explict super type should have the generic category as super type",
				GenericCategory.FULL_QUALIFIED_CATEGORY_NAME,
				testCategorySuper.getExtendsCategory().getFullQualifiedName());
		testCategoryHasSuper = updatedConcept.getCategories().get(2);
		assertFalse("Category with explict super type should not have the generic category as super type",
				GenericCategory.FULL_QUALIFIED_CATEGORY_NAME
						.equals(testCategoryHasSuper.getExtendsCategory().getFullQualifiedName()));

		ConceptImport importedConcept = updatedConcept.getImports().get(0);
		assertEquals("The core concept should be automatically added as implicit import", Activator.getPluginId() + ".*",
				importedConcept.getImportedNamespace());
		
		assertNotEquals("The concept should be edited in a clone of it", testConcept, updatedConcept);

		testConcept.setName(Activator.getPluginId());
		Concept updatedCoreConcept = implicitSuperTypeHandler.addImplicitSuperType(testConcept);
		assertEquals("A concept with the name of the core concept should not be copied + manipulated", testConcept, updatedCoreConcept);

	}

}
