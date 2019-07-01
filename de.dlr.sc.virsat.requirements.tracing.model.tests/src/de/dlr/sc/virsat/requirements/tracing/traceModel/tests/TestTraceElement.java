/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.traceModel.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.rmf.reqif10.ReqIF10Factory;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TMFactory;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;

/**
 * Test class for the trace element
 * @author fran_tb
 *
 */
public class TestTraceElement {
	
	TraceElement testTrace;
	VirSatUuid uuid;
	SpecHierarchy specHierarchy1;
	SpecHierarchy specHierarchy2;
	private static final String TEST_NAME = "TestTrace";
	private static final String TEST_DESCRIPTION = "TestDescription";
	
	@Before
	public void setUp() throws Exception {
		uuid = new VirSatUuid();
		specHierarchy1 = ReqIF10Factory.eINSTANCE.createSpecHierarchy();
		specHierarchy2 = ReqIF10Factory.eINSTANCE.createSpecHierarchy();
		testTrace = TMFactory.eINSTANCE.createTraceElement();
	}

	@Test
	public void testAllSimpleProperties() {
		testTrace.setDescription(TEST_DESCRIPTION);
		testTrace.setName(TEST_NAME);
		testTrace.setUuid(uuid);
		
		assertEquals("Name correctly added", testTrace.getName(), TEST_NAME);
		assertEquals("Description correctly added", testTrace.getDescription(), TEST_DESCRIPTION);
		assertEquals("UUID correctly added", testTrace.getUuid(), uuid);
	}
	
	@Test
	public void testSourceRelationToOthers() {
		
		testTrace.getSourceTraceElement().add(specHierarchy1);
		testTrace.getSourceTraceElement().add(specHierarchy2);
		
		assertEquals("First element correct", testTrace.getSourceTraceElement().get(0), specHierarchy1);
		assertEquals("Second element correct", testTrace.getSourceTraceElement().get(1), specHierarchy2);
	}
	
	@Test
	public void testTargetRelationToOthers() {
		
		testTrace.getTargetTraceElement().add(specHierarchy1);
		testTrace.getTargetTraceElement().add(specHierarchy2);
		
		assertEquals("First element correct", testTrace.getTargetTraceElement().get(0), specHierarchy1);
		assertEquals("Second element correct", testTrace.getTargetTraceElement().get(1), specHierarchy2);
	}
	

}
