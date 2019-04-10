/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.exception;

/**
 * A sub class of Exceptions that are specific to the DVLM Data Model
 * @author fisc_ph
 *
 */
public abstract class ADVLMModelException extends RuntimeException {

	private static final long serialVersionUID = -1827057403330952906L;

	/**
	 * Constructor to place a message with the exception
	 * @param message the message to be attached to the exception
	 */
	public ADVLMModelException(String message) {
		super(message);
	}
}
