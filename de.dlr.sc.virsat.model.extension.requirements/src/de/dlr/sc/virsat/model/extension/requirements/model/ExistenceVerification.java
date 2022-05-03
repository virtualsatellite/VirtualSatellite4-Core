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
import de.dlr.sc.virsat.model.extension.requirements.verification.build.steps.IAutomaticVerification;

import javax.xml.bind.annotation.XmlType;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
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
@XmlType(name = AExistenceVerification.FULL_QUALIFIED_CATEGORY_NAME)
public  class ExistenceVerification extends AExistenceVerification implements IAutomaticVerification {

	@Override
	public Command runCustomVerification(EditingDomain editingDomain, Requirement requirement, IProgressMonitor monitor) {
		
		if (requirement.getTrace().getTarget().isEmpty()) {
			return setStatusOpen(editingDomain);
		} else if (getStatus().equals(IVerification.STATUS_Open_NAME)) {
			return setStatusPartlyCompliant(editingDomain);
		}
		return null;
	}
	
	/**
	 * Constructor of Concept Class
	 */
	public ExistenceVerification() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public ExistenceVerification(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public ExistenceVerification(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}
}
