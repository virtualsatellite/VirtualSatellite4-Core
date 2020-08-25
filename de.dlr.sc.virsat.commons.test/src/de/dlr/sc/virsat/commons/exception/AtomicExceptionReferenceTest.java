/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.exception;


import org.junit.Test;

public class AtomicExceptionReferenceTest {

	@Test
	public void testThrowIfSet() throws Exception {
		AtomicExceptionReference<Exception> ae = new AtomicExceptionReference<>();
		ae.throwIfSet();
		// Test case is successful if it reaches this point
	}
	
	@Test(expected = Exception.class)
	public void testThrowIfSetWithException() throws Exception {
		AtomicExceptionReference<Exception> ae = new AtomicExceptionReference<>();
		ae.set(new Exception());
		ae.throwIfSet();
	}
	
	@Test(expected = RuntimeException.class)
	public void testThrowIfSetWithRuntimeException() {
		AtomicExceptionReference<Exception> ae = new AtomicExceptionReference<>();
		ae.set(new Exception());
		ae.throwAsRuntimeExceptionIfSet();
	}
}
