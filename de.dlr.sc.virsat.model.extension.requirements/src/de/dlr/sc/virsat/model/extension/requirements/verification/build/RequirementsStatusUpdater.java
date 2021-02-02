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
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;

/**
 * Class to update the requirement status according to their verification status 
 *
 */
public class RequirementsStatusUpdater implements IVerificationStep {

	protected EditingDomain editingDomain;
	
	@Override
	public void execute(RequirementsSpecification specification, EditingDomain editingDomain, IProgressMonitor monitor) {
		this.editingDomain = editingDomain;
		int verificationTaskNumber = specification.getRequirements().size();
		SubMonitor subMonitor = SubMonitor.convert(monitor, verificationTaskNumber);
		subMonitor.beginTask("Updating Status on Requirments in Specification " + specification.getName(), verificationTaskNumber);
		for (RequirementObject requirementObject : specification.getRequirements()) {
			updateRequirementStatus(requirementObject, subMonitor);
			subMonitor.worked(1);
		}
	}
	
	/**
	 * Update the status of the different requirement objects in a specification 
	 * @param requirementObject the requirement object to verify
	 * @param monitor the progress monitor
	 */
	protected void updateRequirementStatus(RequirementObject requirementObject, IProgressMonitor monitor) {
		if (requirementObject instanceof RequirementGroup) {
			// Recursively go threw groups
			RequirementGroup group = (RequirementGroup) requirementObject;
			int verificationTaskNumber = group.getChildren().size();
			SubMonitor subMonitor = SubMonitor.convert(monitor, verificationTaskNumber);
			subMonitor.beginTask("Updating Status on Requirments in Group " + group.getName(), verificationTaskNumber);
			for (RequirementObject child : group.getChildren()) {
				updateRequirementStatus(child, subMonitor);
			}
		} else if (requirementObject instanceof Requirement) {
			Requirement requirement = (Requirement) requirementObject;
			computeNewStatus(requirement);
		}
	}
	
	/**
	 * Compute the new requirement status based on the verification status
	 * @param requirement the requirement to be updated
	 */
	protected void computeNewStatus(Requirement requirement) {
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
