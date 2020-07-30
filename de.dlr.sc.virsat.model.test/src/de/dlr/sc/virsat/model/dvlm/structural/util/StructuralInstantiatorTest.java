/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Class for unit testing StructuralInstantiator
 * check that it instantiates StructuralElementInstances and RelationInstances
 * @author kova_an
 *
 */
public class StructuralInstantiatorTest {

	private StructuralInstantiator si;
	private static final String INSTANCE_NAME = "TEST_INSTANCE_NAME";
	
	@Before
	public void setUp() throws Exception {
		si = new StructuralInstantiator();
	}

	@Test
	public void testGenerateInstanceStructuralElementString() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = si.generateInstance(se, INSTANCE_NAME);
		
		assertEquals(sei.getType(), se);
		assertEquals(sei.getName(), INSTANCE_NAME);
	}

	@Test
	public void testGenerateInstanceGeneralRelationString() {
		GeneralRelation gr = StructuralFactory.eINSTANCE.createGeneralRelation();
		RelationInstance ri = si.generateInstance(gr, INSTANCE_NAME);
		
		assertEquals(ri.getType(), gr);
		assertEquals(ri.getName(), INSTANCE_NAME);
	}
}
