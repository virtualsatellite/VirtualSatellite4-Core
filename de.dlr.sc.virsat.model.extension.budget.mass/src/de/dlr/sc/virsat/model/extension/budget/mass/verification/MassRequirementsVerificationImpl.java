/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.mass.verification;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassEquipment;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassRequirementsVerification;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassSummary;
import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.verification.build.steps.IAutomaticVerification;
import de.dlr.sc.virsat.model.extension.requirements.verification.impl.AbstractAutomaticVerification;

public class MassRequirementsVerificationImpl extends AbstractAutomaticVerification implements IAutomaticVerification {
	
	@Override
	public void runCustomVerification(EditingDomain editingDomain, IVerification verification, Requirement requirement, IProgressMonitor monitor) {
		this.editingDomain = editingDomain;
		this.verification = verification;
		
		if (verification instanceof MassRequirementsVerification) {
			
			MassRequirementsVerification massRequirementsVerification = (MassRequirementsVerification) verification;
			double upperLimit = Double.MAX_VALUE;
			double lowerLimit = -Double.MAX_VALUE;
			if (massRequirementsVerification.isSetLowerBound()) {
				lowerLimit = massRequirementsVerification.getLowerBound();
			}
			if (massRequirementsVerification.isSetUpperBound()) {
				upperLimit = massRequirementsVerification.getUpperBound();
			}

			if (requirement.getTrace().getTarget().isEmpty()) {
				setStatusOpen();
			} else {
				for (GenericCategory target : requirement.getTrace().getTarget()) {
					boolean isCompliant = false;
					if (target instanceof MassEquipment) {
						isCompliant = isCompliant((MassEquipment) target, lowerLimit, upperLimit);
					} else if (target instanceof MassSummary) {
						isCompliant = isCompliant((MassSummary) target, lowerLimit, upperLimit);
					}
					updateStatus(isCompliant);
				}
			}
		}
	}
	
	/**
	 * Check if mass equipment is compliant 
	 * @param verificationMethod
	 * @param massEquipment
	 * @return
	 */
	public boolean isCompliant(MassSummary massSummary, double lowerLimit, double upperLimit) {
		double value = massSummary.getMassWithMargin();
		if (value >= lowerLimit && value <= upperLimit) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if mass equipment is compliant 
	 * @param verificationMethod
	 * @param massEquipment
	 * @return
	 */
	public boolean isCompliant(MassEquipment massEquipment, double lowerLimit, double upperLimit) {
		double value = massEquipment.getMassWithMargin();
		if (value >= lowerLimit && value <= upperLimit) {
			return true;
		}
		return false;
	}
	
	/**
	 * Update the status of the verification method based on the compliance status of the values
	 * @param isCompliant the values compliance status
	 */
	protected void updateStatus(boolean isCompliant) {
		if (isCompliant && verification.getStatus().equals(IVerification.STATUS_Open_NAME)
				|| verification.getStatus().equals(IVerification.STATUS_NonCompliant_NAME)) {
			setStatusCompliant();
			// If all values changed to be complaint set status to be complaint
		} else if (!isCompliant
				&& (verification.getStatus().equals(IVerification.STATUS_FullyCompliant_NAME)
						|| verification.getStatus().equals(IVerification.STATUS_PartialCompliant_NAME))) {
			setStatusNonCompliant();
			// If status was non or partly compliant and values changed to be non-complaint set status to be non-complaint
		}
	}

}
