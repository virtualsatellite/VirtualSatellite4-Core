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
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Storage for configuration elements of requirements
 * 
 */	
public abstract class ARequirementsConfigurationCollection extends ABeanStructuralElementInstance implements IBeanStructuralElementInstance {

	public static final String FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME = "de.dlr.sc.virsat.model.extension.requirements.RequirementsConfigurationCollection";
	
	/**
 	* Call this method to get the full qualified name of the underlying Structural Element
 	* @return The FQN of the StructuralElement as String
 	*/
	public String getFullQualifiedSturcturalElementName() {
		return FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME;
	}
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	/**
	 * Constructor of Concept Class
	 */
	public ARequirementsConfigurationCollection() {
	}
	
	/**
	 * Constructor of Concept Class
	 * @param concept The concept from where to initialize
	 */
	public ARequirementsConfigurationCollection(Concept concept) {
		StructuralElement seFromActiveConcept = ActiveConceptHelper.getStructuralElement(concept, "RequirementsConfigurationCollection");
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(seFromActiveConcept);
		setStructuralElementInstance(sei);
	}
	
	/**
	 * Constructor of Concept Class that can be initialized manually by a given StructuralElementInstance
	 * @param sei The StructuralElementInstance to be used for background initialization of the StructuralElementInstance bean
	 */
	public ARequirementsConfigurationCollection(StructuralElementInstance sei) {
		setStructuralElementInstance(sei);
	}
	
	
}
