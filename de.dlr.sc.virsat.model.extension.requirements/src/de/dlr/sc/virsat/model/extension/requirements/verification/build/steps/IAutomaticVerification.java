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
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;

/**
 * Verification interface intended to be implemented by all kind of concepts that want to implement automatic requirements verification
 *
 */
public interface IAutomaticVerification {
	
	/**
	 * Run a customized implementation of a verification method
	 * @param editingDomain the editing domain 
	 * @param requirement the requirement to be verified
	 * @param monitor progress monitor
	 * @return the EMF command to be executed 
	 */
	Command runCustomVerification(EditingDomain editingDomain, Requirement requirement, IProgressMonitor monitor);

}
