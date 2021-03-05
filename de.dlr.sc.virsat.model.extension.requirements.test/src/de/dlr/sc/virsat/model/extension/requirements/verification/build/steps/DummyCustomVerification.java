/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.build.steps;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;

/**
 *
 */
public class DummyCustomVerification implements IAutomaticVerification {
	
	private static boolean didRun = false;

	@Override
	public void runCustomVerification(EditingDomain editingDomain, IVerification verification, Requirement requirement,
			IProgressMonitor monitor) {
		didRun = true;
	}
	
	public static boolean didRun() {
		return didRun;
	}
	
	public static void reset() {
		didRun = false;
	}

}
