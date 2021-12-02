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
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.model.extension.requirements.verification.build.IVerificationStep;

/**
 * Class that execute the verification step on each requirement and its verification methods
 *
 */
public abstract class AbstractRequirementVerificationStep implements IVerificationStep {
	
	
	protected EditingDomain editingDomain;

	@Override
	public void execute(RequirementsSpecification specification, EditingDomain editingDomain, IProgressMonitor monitor) {
		this.editingDomain = editingDomain;
		int verificationTaskNumber = specification.getRequirements().size();
		SubMonitor subMonitor = SubMonitor.convert(monitor, verificationTaskNumber);
		subMonitor.beginTask("Running Verification Step " + this.getClass().getName() + " on Requirments in Specification " + specification.getName(), verificationTaskNumber);
		for (RequirementObject requirementObject : specification.getRequirements()) {
			handleRequirement(requirementObject, subMonitor);
			subMonitor.worked(1);
		}
	}
	
	/**
	 * Do the verification step on each requirement object in the specification
	 * @param requirementObject the requirement object to do the step on
	 * @param monitor the progress monitor
	 */
	protected void handleRequirement(RequirementObject requirementObject, IProgressMonitor monitor) {
		
		if (requirementObject instanceof RequirementGroup) {
			// Recursively go through groups
			RequirementGroup group = (RequirementGroup) requirementObject;
			int verificationTaskNumber = group.getChildren().size();
			SubMonitor subMonitor = SubMonitor.convert(monitor, verificationTaskNumber);
			subMonitor.beginTask("Running Verification Step " + this.getClass().getName() + " on Requirments in Group " + group.getName(), verificationTaskNumber);
			for (RequirementObject child : group.getChildren()) {
				handleRequirement(child, subMonitor);
			}
		} else if (requirementObject instanceof Requirement) {
			Requirement requirement = (Requirement) requirementObject;
			
			preVerificationStep(requirement, monitor);
			
			for (IVerification verification : requirement.getVerification()) {
				doVerificationStep(verification, requirement, monitor);
			}
			
			postVerificationStep(requirement, monitor);
		}
	}
	
	public void setEditingDomain(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}
	
	/**
	 * Method before doing the verification
	 * @param requirementObject the requirement object to perform the step on 
	 * @param monitor progress monitor
	 */
	protected void preVerificationStep(Requirement requirementObject, IProgressMonitor monitor) { }
	
	/**
	 * Do the actual verification step for each verification spec
	 * @param verification the verification element
	 * @param requirement the requirement to be verified
	 * @param monitor the progress monitor
	 */
	protected abstract void doVerificationStep(IVerification verification, Requirement requirement, IProgressMonitor monitor);
	
	/**
	 * Method after doing the verification
	 * @param requirementObject the requirement object to perform the step on 
	 * @param monitor progress monitor
	 */
	protected void postVerificationStep(Requirement requirementObject, IProgressMonitor monitor) { }
	

}
