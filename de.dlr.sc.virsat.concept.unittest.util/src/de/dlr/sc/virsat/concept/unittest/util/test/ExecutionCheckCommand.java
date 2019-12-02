/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.concept.unittest.util.test;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * Wrapped RecordingCommand to observe if a command got executed correctly
 *
 */
public class ExecutionCheckCommand extends RecordingCommand {

	protected boolean isExecuted = false;
	
	/**
	 * Just a constructor
	 * @param domain the editing domain where this command will act on
	 */
	public ExecutionCheckCommand(TransactionalEditingDomain domain) {
		super(domain);
	}

	@Override
	protected void doExecute() {
		isExecuted = true;
	}
	
	/**
	 * Checks if the isExecuted flag got set
	 * @return tre iff the command got executed
	 */
	public boolean isExecuted() {
		return isExecuted;
	}
}
