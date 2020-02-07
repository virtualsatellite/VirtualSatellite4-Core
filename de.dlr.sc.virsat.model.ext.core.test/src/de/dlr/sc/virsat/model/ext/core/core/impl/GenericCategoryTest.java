/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ext.core.core.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.dlr.sc.virsat.model.ext.core.core.CorePackage;

/**
 * Simple test if an instance of the generic category can be instantiated
 */
public class GenericCategoryTest {
	
	@Test
	public void testGenericCategory() {
		GenericCategoryMockupImpl impl = new GenericCategoryMockupImpl();
		assertEquals("Eclass is not correct", CorePackage.Literals.GENERIC_CATEGORY, impl.eStaticClass());
	}

}
