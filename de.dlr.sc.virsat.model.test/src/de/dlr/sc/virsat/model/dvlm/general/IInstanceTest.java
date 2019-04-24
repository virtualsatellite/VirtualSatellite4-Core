/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.general;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;

/**
 * tests that the FQN functionality of this interface is well implemented
 * 
 * @author fisc_ph
 *
 */
public class IInstanceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFullQualifiedInstanceName() {
		// Create a common root category
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName("DataPort");
		
		// Add two simple properties that will convert into ValueInstanceProeprties
		IntProperty propSerialNo = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propSerialNo.setName("serialNo");
		IntProperty propConnections = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propConnections.setName("connections");
		cat.getProperties().add(propSerialNo);
		cat.getProperties().add(propConnections);
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, "dataPort1");
		
		String fullQualifedNameForSerialNo = ((IInstance) ca.getPropertyInstances().get(0)).getFullQualifiedInstanceName();
		String fullQualifedNameForConnections = ((IInstance) ca.getPropertyInstances().get(1)).getFullQualifiedInstanceName();
		
		assertEquals("Got correct FQN of Instance", "dataPort1.serialNo", fullQualifedNameForSerialNo);
		assertEquals("Got correct FQN of Instance", "dataPort1.connections", fullQualifedNameForConnections);

		// Alld etailed tests are executed in the ActiveConceptHelper which gets actually calle by the interface
	}
}
