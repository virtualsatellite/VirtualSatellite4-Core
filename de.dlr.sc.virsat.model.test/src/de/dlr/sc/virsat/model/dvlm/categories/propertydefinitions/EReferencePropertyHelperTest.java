/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.external.tests.AExternalModelTest;

public class EReferencePropertyHelperTest extends AExternalModelTest {
	
	private EReferenceProperty propertyDefinition;
	private static final String PROPERTY_TEST_NAME = "testEReference";
	private static final String TEST_CLASS_NAME = "ExternalTestType";
	
	@Before
	public void setUp() throws Exception {
		//Load external metamodel
		loadExternalPackage();
		
		//Prepare EReference property
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		Category testCategory = CategoriesFactory.eINSTANCE.createCategory();
		propertyDefinition = PropertydefinitionsFactory.eINSTANCE.createEReferenceProperty();
		
		concept.getCategories().add(testCategory);
		testCategory.getProperties().add(propertyDefinition);
		propertyDefinition.setName(PROPERTY_TEST_NAME);
		propertyDefinition.setReferenceType((EClass) testExternalPackage.getEClassifier(TEST_CLASS_NAME));
	}
	
	@Test
	public void testGetResolvedEClassType() {
		EReferencePropertyHelper helper = new EReferencePropertyHelper();
		assertEquals("EClass should be same as set before", TEST_CLASS_NAME, helper.getResolvedEClassType(propertyDefinition).getName());
		
		//Unload resource to check resolving
		testExternalPackage.eResource().unload();
		assertEquals("EClass should be resolved from the registered package", TEST_CLASS_NAME, helper.getResolvedEClassType(propertyDefinition).getName());
		EPackage registeredPackage = helper.getEPackageOfType(propertyDefinition);
		assertNotEquals("Resolved package from registry should not be the locally loaded one", loadExternalPackage(), registeredPackage);
	}
	
}
