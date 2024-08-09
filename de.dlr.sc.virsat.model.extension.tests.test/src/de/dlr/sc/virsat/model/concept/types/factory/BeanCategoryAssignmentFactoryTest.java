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


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.tests.model.AExtensionConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;

/**
 * This class tests the CategoryAssignmentBeanFactory
 * @author muel_s8
 *
 */

public class BeanCategoryAssignmentFactoryTest extends AExtensionConceptTestCase {

	private BeanCategoryAssignmentFactory caBeanFactory;
	private Concept concept;
	
	@Before
	public void setup() {
		caBeanFactory = new BeanCategoryAssignmentFactory();
		// Load the concept to create the test object
		concept = loadConceptFromPlugin();
	}
	
	@Test
	public void testGetInstanceForFullQualifiedName() throws CoreException {
		IBeanCategoryAssignment caBean = caBeanFactory.getInstanceFor("de.dlr.sc.virsat.model.extension.tests.TestCategoryBeanA");
		assertThat("Created bean is of correct type", caBean, instanceOf(TestCategoryBeanA.class));
	}

	@Test
	public void testGetInstanceForCategory() throws CoreException {
		Category category = ActiveConceptHelper.getCategory(concept, "TestCategoryBeanA");
		
		IBeanCategoryAssignment caBean = caBeanFactory.getInstanceFor(category);
		assertThat("Created bean is of correct type", caBean, instanceOf(TestCategoryBeanA.class));
	}

	@Test
	public void testGetInstanceForCategoryAssignment() throws CoreException {
		Category category = ActiveConceptHelper.getCategory(concept, "TestCategoryBeanA");
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(category, "TestCategoryBeanA");
		
		IBeanCategoryAssignment caBean = caBeanFactory.getInstanceFor(ca);
		assertThat("Created bean is of correct type", caBean, instanceOf(TestCategoryBeanA.class));
		assertEquals("Bean has a type instance set", ca, caBean.getTypeInstance());
		assertEquals("Type instance of bean is set correctly", "TestCategoryBeanA", caBean.getTypeInstance().getType().getName());
	}
	
	@Test(expected = CoreException.class)
	public void testGetInstanceForUnregisteredCategoryAssignment() throws CoreException {
		Category category = CategoriesFactory.eINSTANCE.createCategory();
		category.setName("Unregistered_Category");
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(category, "TestUnregisteredCategoryAssignment");
		
		caBeanFactory.getInstanceFor(ca);
	}

}
