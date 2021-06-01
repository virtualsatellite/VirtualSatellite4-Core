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

import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;

/**
 * Class to update the requirement status according to their verification status 
 *
 */
public class RequirementsStatusUpdater extends AbstractRequirementVerificationStep {

	@Override
	protected void doVerificationStep(IVerification verification, Requirement requirement, IProgressMonitor monitor) {
		// Nothing to do as this step is only updating the status
	}
	
	@Override
	protected void postVerificationStep(Requirement requirement, IProgressMonitor monitor) { 
		boolean inProgress = false;
		boolean partlyNonCompliant = false;
		boolean partlyCompliant = false;
		boolean fullyCompliant = false;
		
		for (IVerification verification : requirement.getVerification()) {
			String verificationStatus = verification.getStatus();
			if (verificationStatus != null) {
				if (verificationStatus.equals(IVerification.STATUS_FullyCompliant_NAME)) {
					fullyCompliant = true;
				} else if (verificationStatus.equals(IVerification.STATUS_NonCompliant_NAME)) {
					partlyNonCompliant = true;
				} else if (verificationStatus.equals(IVerification.STATUS_Open_NAME)) {
					inProgress = true;
				} else if (verificationStatus.equals(IVerification.STATUS_PartialCompliant_NAME)) {
					partlyCompliant = true;
				}
			}
		}
		Command setCommand = null;
		if (inProgress) {
			setCommand = requirement.setStatus(editingDomain, Requirement.STATUS_Open_NAME);
		} else if (fullyCompliant && partlyNonCompliant || partlyCompliant) {
			setCommand = requirement.setStatus(editingDomain, Requirement.STATUS_PartialCompliant_NAME);
		} else if (fullyCompliant) {
			setCommand = requirement.setStatus(editingDomain, Requirement.STATUS_FullyCompliant_NAME);
		} else if (partlyNonCompliant) {
			setCommand = requirement.setStatus(editingDomain, Requirement.STATUS_NonCompliant_NAME);
		}
		if (setCommand != null) {
			editingDomain.getCommandStack().execute(setCommand);
		}
	}

}
