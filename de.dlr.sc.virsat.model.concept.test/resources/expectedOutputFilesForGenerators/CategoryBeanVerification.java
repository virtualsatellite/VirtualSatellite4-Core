/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package testConcept.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.verification.build.steps.IAutomaticVerification;
import org.eclipse.emf.edit.domain.EditingDomain;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import org.eclipse.core.runtime.IProgressMonitor;

// *****************************************************************
// * Class Declaration
// *****************************************************************

@XmlType(name = ATestCategory.FULL_QUALIFIED_CATEGORY_NAME)
/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public  class TestCategory extends ATestCategory implements IAutomaticVerification {
	
	/**
	 * Constructor of Concept Class
	 */
	public TestCategory() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public TestCategory(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public TestCategory(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}
	
	@Override
	public Command runCustomVerification(EditingDomain editingDomain, Requirement requirement,
			IProgressMonitor monitor) {
		// TODO implement custom verification		
		return null;
	}
}
