/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.build;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;

/**
 * Interface for requirement specification verification steps
 *
 */
public interface IVerificationStep {

	/**
	 * Method to execute a verification step on a given requirements specification
	 * @param specification the specification on which the verification needs to be performed
	 * @param editingDomain the editing domain of this verification process
	 * @param monitor a progress monitor to show the verification progress
	 */
	void execute(RequirementsSpecification specification, EditingDomain editingDomain, IProgressMonitor monitor);
	
}
