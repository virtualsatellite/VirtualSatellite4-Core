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
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Mapping specification of imported requirements
 * 
 */
public  class ImportConfiguration extends AImportConfiguration {
	
	/**
	 * Constructor of Concept Class
	 */
	public ImportConfiguration() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public ImportConfiguration(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public ImportConfiguration(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}
	
	/**
	 * Utility method to get a concept specification by its external identifier 
	 * @param externalIdentifier the identifier
	 * @return the specification as defined in the req concept
	 */
	public RequirementsSpecification getSpecification(String externalIdentifier) {
		for (SpecificationMapping mapping : this.getMappedSpecifications()) {
			if (mapping.getExternalIdentifier().equals(externalIdentifier)) {
				return mapping.getSpecification();
			}
		}
		return null;
	}
}
