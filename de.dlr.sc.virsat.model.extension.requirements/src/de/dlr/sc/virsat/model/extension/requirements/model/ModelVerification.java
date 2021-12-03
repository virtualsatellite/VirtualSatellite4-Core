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

import javax.xml.bind.annotation.XmlType;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

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
@XmlType(name = AModelVerification.FULL_QUALIFIED_CATEGORY_NAME)
public abstract class ModelVerification extends AModelVerification {
	
	/**
	 * Constructor of Concept Class
	 */
	public ModelVerification() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public ModelVerification(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public ModelVerification(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}
	
	
	protected Command setStatusCompliant(EditingDomain editingDomain) {
		return setStatus(editingDomain, IVerification.STATUS_FullyCompliant_NAME);
	}
	
	protected void setStatusCompliant() {
		setStatus(IVerification.STATUS_FullyCompliant_NAME);
	}
	
	protected Command setStatusNonCompliant(EditingDomain editingDomain) {
		return setStatus(editingDomain, IVerification.STATUS_NonCompliant_NAME);
	}
	
	protected void setStatusNonCompliant() {
		setStatus(IVerification.STATUS_NonCompliant_NAME);
	}
	
	protected Command setStatusPartlyCompliant(EditingDomain editingDomain) {
		return setStatus(editingDomain, IVerification.STATUS_PartialCompliant_NAME);
	}
	
	protected void setStatusPartlyCompliant() {
		setStatus(IVerification.STATUS_PartialCompliant_NAME);
	}
	
	protected Command setStatusOpen(EditingDomain editingDomain) {
		return setStatus(editingDomain, IVerification.STATUS_Open_NAME);
	}
	
	protected void setStatusOpen() {
		setStatus(IVerification.STATUS_Open_NAME);
	}
	
}
