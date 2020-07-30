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

import java.util.concurrent.atomic.AtomicReference;

/**
 * An atomic exception that can be used to hand over exceptions
 * in cases where an exception has to be explicitly final. This
 * is needed e.g. in Lambda expressions
 * @param <E> the type of Exception to be handled
 *
 */
public class AtomicExceptionReference<E extends Exception> extends AtomicReference<E> {

	private static final long serialVersionUID = -6329653725507243226L;

	/**
	 * This method throws the exception again in case it has been set
	 * @throws E the type of exception that maybe thrown
	 */
	public void throwIfSet() throws E {
		E exception = get();
		if (exception != null) {
			throw exception;
		}
	}
	
	/**
	 * This method throws the exception again in case it has been set but this time as RuntimeException
	 * @throws RuntimeException the type of exception that maybe thrown
	 */
	public void throwAsRuntimeExceptionIfSet() throws RuntimeException {
		E exception = get();
		if (exception != null) {
			throw new RuntimeException(exception);
		}
	}
}
