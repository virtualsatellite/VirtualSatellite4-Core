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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport;
import de.dlr.sc.virsat.model.external.tests.ExternalModelTestHelper;

public class EReferencePropertyHelperTest {
	
	private EReferenceProperty propertyDefinition;
	private ExternalModelTestHelper helper = new ExternalModelTestHelper();
	private EPackage testExternalPackage;
	private static final String PROPERTY_TEST_NAME = "testEReference";
	private static final String TEST_CLASS_NAME = "ExternalTestType";
	
	@Before
	public void setUp() throws Exception {
		//Load external metamodel
		testExternalPackage = helper.loadExternalPackage();
		
		//Prepare EReference property
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		Category testCategory = CategoriesFactory.eINSTANCE.createCategory();
		propertyDefinition = PropertydefinitionsFactory.eINSTANCE.createEReferenceProperty();
		EcoreImport eImport = ConceptsFactory.eINSTANCE.createEcoreImport();
		
		eImport.setImportedNsURI("http://www.virsat.sc.dlr.de/external/tests");
		concept.getEcoreImports().add(eImport);
		
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
		assertNotEquals("Resolved package from registry should not be the locally loaded one", testExternalPackage, registeredPackage);
	}
	
	@Test
	public void testActivateEClassType() {
		EReferencePropertyHelper helper = new EReferencePropertyHelper();
		
		//Create proxy by unloading an element
		EClass eClassProxy = (EClass) testExternalPackage.getEClassifiers().get(0);
		eClassProxy.eResource().unload();
		
		//Test URI
		final String TEST_PROJECT = "de.dlr.sc.virsat.model.test";
		final String TEST_PATH = "model/test.ecore";
		final String TEST_FRAGMENT = "ExternalTestType";
		URI testURI = URI.createPlatformResourceURI(TEST_PROJECT + "/" + TEST_PATH + TEST_FRAGMENT, true);
		testURI = testURI.appendFragment(TEST_FRAGMENT);
		((InternalEObject) eClassProxy).eSetProxyURI(testURI);
		assertTrue("Test proxy should now have a ResourceURI NOT a PluginURI", ((InternalEObject) eClassProxy).eProxyURI().isPlatformResource());
		
		//Test activation
		EClass activeEClass = helper.activateEClassType(eClassProxy);
		
		URI activeURI = ((InternalEObject) activeEClass).eProxyURI();
		assertTrue("Type's URI should be Plugin URI now", activeURI.isPlatformPlugin());
		assertNotNull("Fragment should have been copied", activeURI.fragment());
		assertEquals("Fragement should be kept", TEST_FRAGMENT, activeURI.fragment());
		assertTrue("Should still contain project", activeURI.toString().contains(TEST_PROJECT));
		assertTrue("Should still contain project path", activeURI.toString().contains(TEST_PATH));
	}
	
}
