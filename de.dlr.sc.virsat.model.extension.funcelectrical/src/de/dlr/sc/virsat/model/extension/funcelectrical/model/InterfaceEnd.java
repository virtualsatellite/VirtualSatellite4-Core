/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

import jakarta.xml.bind.annotation.XmlType;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * 
 * 
 */	
@XmlType(name = AInterfaceEnd.FULL_QUALIFIED_CATEGORY_NAME)
public class InterfaceEnd extends AInterfaceEnd {
	
	/**
	 * Constructor of Concept Class
	 */
	public InterfaceEnd() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public InterfaceEnd(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public InterfaceEnd(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}
	
	private static final String PROPERTYNAME1 = "type";
	
	/**
	 * Method to access the RPI which will be needed by the Validator
	 * to indicate the correct target of where warning come from.
	 * @return the EMF ReferencePropertyInstance
	 */
	public ReferencePropertyInstance getTypeReferenceProperty() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance(PROPERTYNAME1);
		return propertyInstance;
	}
}
