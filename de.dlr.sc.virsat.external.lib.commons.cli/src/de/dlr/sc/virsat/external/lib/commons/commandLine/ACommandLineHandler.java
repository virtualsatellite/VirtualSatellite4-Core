/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.external.lib.commons.commandLine;

/**
 * A Handler Class representing an attribute being set by a command line option
 * @author fisc_ph
 *
 */
public abstract class ACommandLineHandler implements ICommandLineHandler {

	protected String attribute;

	@Override
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

}
