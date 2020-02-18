/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.external.tests.Container;
import de.dlr.sc.virsat.model.external.tests.ExternalModelTestHelper;
import de.dlr.sc.virsat.model.external.tests.ExternalTestType;
import de.dlr.sc.virsat.model.external.tests.TestsFactory;

/**
 * 
 * Test validation of EClass of EObject value added to the reference
 */
public class EReferencePropertyInstanceImplCustomTest {

	private EReferencePropertyInstance propertyInstance;
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
		propertyDefinition = PropertydefinitionsFactory.eINSTANCE.createEReferenceProperty();
		propertyInstance = PropertyinstancesFactory.eINSTANCE.createEReferencePropertyInstance();
		propertyInstance.setType(propertyDefinition);
		propertyDefinition.setName(PROPERTY_TEST_NAME);
		propertyDefinition.setReferenceType((EClass) testExternalPackage.getEClassifier(TEST_CLASS_NAME));
	}

	@Test
	public void testSetReference() {
		ExternalTestType eClassValidInstance = TestsFactory.eINSTANCE.createExternalTestType();
		propertyInstance.setReference(eClassValidInstance);
		
		assertNotNull("Reference should be set", propertyInstance.getReference());
		assertEquals("Reference value setting should have worked", eClassValidInstance, propertyInstance.getReference());
		
		//Reset property
		propertyInstance.setReference(null);
		
		Container eClassInvalidInstance = TestsFactory.eINSTANCE.createContainer();
		propertyInstance.setReference(eClassInvalidInstance);
		assertNull("Invalid reference value should not be settable", propertyInstance.getReference());
	}
	
}
