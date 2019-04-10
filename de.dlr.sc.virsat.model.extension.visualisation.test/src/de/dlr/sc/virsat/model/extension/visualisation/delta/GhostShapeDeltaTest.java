/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation.delta;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;

/**
 * This test checks the java bean for a Clone Shape
 * @author fisc_ph
 *
 */
public class GhostShapeDeltaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsObject() {
		String parentId = "1234";
		String parentId2 = "1235";
		
		Shape shape1 = new Shape();
		Shape shape2 = new Shape();
		
		shape1.shape = VisualisationShape.BOX;
		shape1.id = new VirSatUuid().toString();
		
		shape2.shape = VisualisationShape.BOX;
		shape2.id = new VirSatUuid().toString();
		
		GhostShapeDelta delta1 = new GhostShapeDelta(parentId, shape1);
		GhostShapeDelta delta2 = new GhostShapeDelta(parentId, shape2);
		GhostShapeDelta delta3 = new GhostShapeDelta(parentId, shape1);
		GhostShapeDelta delta4 = new GhostShapeDelta(parentId2, shape1);
		
		assertFalse("They should not match", delta1.equals(delta2));
		assertFalse("They should not match", delta1.equals(delta4));
		assertTrue("They should not match", delta1.equals(delta3));
		
		assertEquals("Same HashCode", delta1.hashCode(), delta3.hashCode());
	}

}
