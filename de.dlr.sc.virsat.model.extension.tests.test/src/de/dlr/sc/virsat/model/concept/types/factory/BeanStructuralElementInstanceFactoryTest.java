/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.factory;


import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.model.extension.tests.model.AExtensionConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;

/**
 * This class tests the CategoryAssignmentBeanFactory
 * @author muel_s8
 *
 */

public class BeanStructuralElementInstanceFactoryTest extends AExtensionConceptTestCase {

	private BeanStructuralElementInstanceFactory seBeanFactory;
	private Concept concept;
	
	@Before
	public void setup() {
		seBeanFactory = new BeanStructuralElementInstanceFactory();
		// Load the concept to create the test object
		concept = loadConceptFromPlugin();
	}
	
	@Test
	public void testGetInstanceForFullQualifiedName() throws CoreException {
		IBeanStructuralElementInstance seiBean = seBeanFactory.getInstanceFor("de.dlr.sc.virsat.model.extension.tests.TestStructuralElement");
		assertThat("Created bean is of correct type", seiBean, instanceOf(TestStructuralElement.class));
	}

	@Test
	public void testGetInstanceForCategory() throws CoreException {
		StructuralElement se = ActiveConceptHelper.getStructuralElement(concept, "TestStructuralElement");
		
		IBeanStructuralElementInstance seiBean = seBeanFactory.getInstanceFor(se);
		assertThat("Created bean is of correct type", seiBean, instanceOf(TestStructuralElement.class));
	}

	@Test
	public void testGetInstanceForCategoryAssignment() throws CoreException {
		StructuralElement se = ActiveConceptHelper.getStructuralElement(concept, "TestStructuralElement");
		StructuralElementInstance sei = new StructuralInstantiator().generateInstance(se, "TestStructuralElement");
		
		IBeanStructuralElementInstance seiBean = seBeanFactory.getInstanceFor(sei);
		assertThat("Created bean is of correct type", seiBean, instanceOf(TestStructuralElement.class));
		assertEquals("Bean has a type instance set", sei, seiBean.getStructuralElementInstance());
		assertEquals("Type instance of bean is set correctly", "TestStructuralElement", seiBean.getStructuralElementInstance().getType().getName());
	}

}
