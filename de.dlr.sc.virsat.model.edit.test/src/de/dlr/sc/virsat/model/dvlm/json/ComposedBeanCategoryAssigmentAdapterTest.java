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

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.category.BeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;

public class ComposedBeanCategoryAssigmentAdapterTest {

	private ComposedBeanCategoryAssigmentAdapter adapter;
	private BeanCategoryAssignment testBeanCa;
	
	@Before
	public void setUp() throws Exception {
		adapter = new ComposedBeanCategoryAssigmentAdapter();
		testBeanCa = new BeanCategoryAssignment();
		testBeanCa.setTypeInstance(CategoriesFactory.eINSTANCE.createCategoryAssignment());
	}

	@Test
	public void testUnmarshalObject() throws Exception {
		Object marshalledObject = adapter.marshal(testBeanCa);
		assertEquals(testBeanCa, marshalledObject);
	}

	@Test
	public void testMarshalObject() throws Exception {
		Object unmarshalledObject = adapter.unmarshal(testBeanCa);
		assertEquals(testBeanCa, unmarshalledObject);
	}

}
