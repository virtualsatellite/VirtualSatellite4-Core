/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.requirements.tracing.traceModel.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.requirements.tracing.traceModel.TMFactory;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TMPackage;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceabilityLinkContainer;

/**
 * @author fran_tb
 *
 */
public class TestTracebilityLinkContainer {
	
	TraceElement testElement1;
	TraceElement testElement2;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAllSimpleProperties() {
		testElement1 = TMFactory.eINSTANCE.createTraceElement();
		testElement2 = TMFactory.eINSTANCE.createTraceElement();
		
		TraceabilityLinkContainer container = TMFactory.eINSTANCE.createTraceabilityLinkContainer();
		container.getTraceElements().add(testElement1);
		container.getTraceElements().add(testElement2);
		
		assertEquals("First element correct", container.getTraceElements().get(0), testElement1);
		assertEquals("Second element correct", container.getTraceElements().get(1), testElement2);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGenericMethods() {
		testElement1 = TMFactory.eINSTANCE.createTraceElement();
		testElement2 = TMFactory.eINSTANCE.createTraceElement();
		
		
		TraceabilityLinkContainer container = TMFactory.eINSTANCE.createTraceabilityLinkContainer();
		container.eUnset(TMPackage.eINSTANCE.getTraceabilityLinkContainer_TraceElements());
		if (!container.eIsSet(TMFactory.eINSTANCE.getTMPackage().getTraceabilityLinkContainer_TraceElements())) {
			List<TraceElement> traceList = new ArrayList<TraceElement>();
			traceList.add(testElement1);
			container.eSet(TMPackage.eINSTANCE.getTraceabilityLinkContainer_TraceElements(), traceList);
		}
		
		assertEquals("First element correct", ((List<TraceElement>) container.eGet(TMPackage.eINSTANCE.getTraceabilityLinkContainer_TraceElements())).get(0), testElement1);
		
	}
	
	

}
