/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Materials can be created here or imported from .csv .
 * 
 */
public class MaterialCollection extends AMaterialCollection {
	
	/**
	 * Constructor of Concept Class
	 */
	public MaterialCollection() {
		super();
	}

	/**
 	 * Constructor of Concept Class
 	 * @param concept The concept from where to initialize
 	 */
	public MaterialCollection(Concept concept) {
		super(concept);
	}	

	/**
 	 * Constructor of Concept Class that can be initialized manually by a given StructuralElementInstance
	 * @param sei The StructuralElementInstance to be used for background initialization of the StructuralElementInstance bean
	 */
	public MaterialCollection(StructuralElementInstance sei) {
		super(sei);
	}
}
