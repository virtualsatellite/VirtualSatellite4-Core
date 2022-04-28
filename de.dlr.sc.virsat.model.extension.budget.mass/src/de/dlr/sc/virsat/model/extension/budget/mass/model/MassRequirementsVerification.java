/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.mass.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.verification.build.steps.IAutomaticVerification;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class MassRequirementsVerification extends AMassRequirementsVerification implements IAutomaticVerification {

	/**
	 * Constructor of Concept Class
	 */
	public MassRequirementsVerification() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate a CategoryAssignment in
	 * the background from the given concept
	 * 
	 * @param concept the concept where it will find the correct Category to
	 *                instantiate from
	 */
	public MassRequirementsVerification(Concept concept) {
		super(concept);
	}

	/**
	 * Constructor of Concept Class that can be initialized manually by a given
	 * Category Assignment
	 * 
	 * @param categoryAssignment The category Assignment to be used for background
	 *                           initialization of the Category bean
	 */
	public MassRequirementsVerification(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}

	@Override
	public Command runCustomVerification(EditingDomain editingDomain, Requirement requirement,
			IProgressMonitor monitor) {
		CompoundCommand cc = new CompoundCommand();

		double upperLimit = Double.MAX_VALUE;
		double lowerLimit = -Double.MAX_VALUE;
		if (isSetLowerBound()) {
			lowerLimit = getLowerBound();
		}
		if (isSetUpperBound()) {
			upperLimit = getUpperBound();
		}

		if (requirement.getTrace().getTarget().isEmpty()) {
			cc.append(setStatusOpen(editingDomain));
		} else {
			boolean isCompliantForAllTargets = true;
			for (GenericCategory target : requirement.getTrace().getTarget()) {
				if (target instanceof MassEquipment) {
					isCompliantForAllTargets &= isCompliant((MassEquipment) target, lowerLimit, upperLimit);
				} else if (target instanceof MassSummary) {
					isCompliantForAllTargets &= isCompliant((MassSummary) target, lowerLimit, upperLimit);
				}
			}
			updateStatus(editingDomain, cc, isCompliantForAllTargets);
		}

		return cc;
	}

	/**
	 * Check if mass equipment is compliant
	 * 
	 * @param massSummary the mass summary
	 * @param lowerLimit the lower limit
	 * @param upperLimit the upper limit
	 * @return true iff the mass with margin is within the lower and upper limit
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
	 * 
	 * @param massEquipment the mass equipment
	 * @param lowerLimit the lower limit
	 * @param upperLimit the upper limit
	 * @return true iff the mass with margin is within the limits
	 */
	public boolean isCompliant(MassEquipment massEquipment, double lowerLimit, double upperLimit) {
		double value = massEquipment.getMassWithMargin();
		return value >= lowerLimit && value <= upperLimit;
	}

	/**
	 * Update the status of the verification method based on the compliance status
	 * of the values
	 * 
	 * @param isCompliant the values compliance status
	 */
	protected void updateStatus(EditingDomain editingDomain, CompoundCommand cc, boolean isCompliant) {
		if (isCompliant && (getStatus().equals(IVerification.STATUS_Open_NAME)
				|| getStatus().equals(IVerification.STATUS_NonCompliant_NAME))) {
			cc.append(setStatusCompliant(editingDomain));
			// If all values changed to be complaint set status to be complaint
		} else if (!isCompliant) {
			cc.append(setStatusNonCompliant(editingDomain));
			// If status was non or partly compliant and values changed to be non-complaint
			// set status to be non-complaint
		}
	}

}
