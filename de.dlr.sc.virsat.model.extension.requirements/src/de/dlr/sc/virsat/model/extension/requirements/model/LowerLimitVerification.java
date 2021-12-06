/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.extension.requirements.verification.build.steps.IAutomaticVerification;
import org.eclipse.emf.edit.domain.EditingDomain;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import org.eclipse.core.runtime.IProgressMonitor;

// *****************************************************************
// * Class Declaration
// *****************************************************************

@XmlType(name = ALowerLimitVerification.FULL_QUALIFIED_CATEGORY_NAME)
/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public  class LowerLimitVerification extends ALowerLimitVerification implements IAutomaticVerification {
	
	/**
	 * Constructor of Concept Class
	 */
	public LowerLimitVerification() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public LowerLimitVerification(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public LowerLimitVerification(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}
	
	@Override
	public Command runCustomVerification(EditingDomain editingDomain, Requirement requirement,
			IProgressMonitor monitor) {
		CompoundCommand cc = new CompoundCommand();

		if (requirement.getTrace().getTarget().isEmpty()) {
			cc.append(setStatusOpen(editingDomain));
		} else {
			boolean isCompliantForAllTargets = true;
			for (GenericCategory target : requirement.getTrace().getTarget()) {
				isCompliantForAllTargets &= isCompliant(target);
			}
			updateStatus(editingDomain, cc, isCompliantForAllTargets);
		}

		return cc;
	}
	
	@Override
	protected boolean isCompliant(double value) {
		return  getLowerBound() <= value;
	}
	
	protected double getLowerLimit() {
		double lowerLimit = Double.MIN_VALUE;
		if (isSetLowerBound()) {
			lowerLimit = getLowerBound();
		}
		return lowerLimit;
	}
}
