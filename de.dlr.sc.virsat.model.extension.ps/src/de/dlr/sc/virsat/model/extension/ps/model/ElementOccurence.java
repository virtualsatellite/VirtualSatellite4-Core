/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

import javax.xml.bind.annotation.XmlType;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * A component representing the integrated state within an assembly tree
 * 
 */	
@XmlType(name = AElementOccurence.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME)
public class ElementOccurence extends AElementOccurence {
	
	/**
	 * Constructor of Concept Class
	 */
	public ElementOccurence() {
		super();
	}

	/**
 	 * Constructor of Concept Class
 	 * @param concept The concept from where to initialize
 	 */
	public ElementOccurence(Concept concept) {
		super(concept);
	}	

	/**
 	 * Constructor of Concept Class that can be initialized manually by a given StructuralElementInstance
	 * @param sei The StructuralElementInstance to be used for background initialization of the StructuralElementInstance bean
	 */
	public ElementOccurence(StructuralElementInstance sei) {
		super(sei);
	}
}
