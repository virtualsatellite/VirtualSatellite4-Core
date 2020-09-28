/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.category.BeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

public class NotAbstractBeanCategoryAssignmentTest {

	private CategoryAssignment ca;
	private BeanCategoryAssignment bean;
	private NotAbstractBeanCategoryAssignment wrappedBean;
	private static final String NAME = "name";
	
	@Before
	public void setUp() throws Exception {
		ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca.setName(NAME);
		bean = new BeanCategoryAssignment();
		bean.setTypeInstance(ca);
	}
	
	@Test
	public void testWrapping() {
		wrappedBean = new NotAbstractBeanCategoryAssignment();
		assertNull(wrappedBean.getName());
		assertNull(wrappedBean.getBeanCa());
		
		wrappedBean = new NotAbstractBeanCategoryAssignment(bean);
		assertEquals(NAME, wrappedBean.getName());
		assertEquals(bean, wrappedBean.getBeanCa());
	}
}
