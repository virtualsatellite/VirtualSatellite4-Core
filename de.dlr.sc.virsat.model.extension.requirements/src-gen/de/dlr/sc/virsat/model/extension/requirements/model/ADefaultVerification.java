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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
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
public abstract class ADefaultVerification extends IVerification implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.DefaultVerification";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_VERIFICATIONTYPE = "verificationType";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ADefaultVerification() {
	}
	
	public ADefaultVerification(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "DefaultVerification");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "DefaultVerification");
		setTypeInstance(categoryAssignement);
	}
	
	public ADefaultVerification(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: verificationType
	// *****************************************************************
	private BeanPropertyReference<VerificationType> verificationType = new BeanPropertyReference<>();
	
	private void safeAccessVerificationType() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("verificationType");
		verificationType.setTypeInstance(propertyInstance);
	}
	
	public VerificationType getVerificationType() {
		safeAccessVerificationType();
		return verificationType.getValue();
	}
	
	public Command setVerificationType(EditingDomain ed, VerificationType value) {
		safeAccessVerificationType();
		return verificationType.setValue(ed, value);
	}
	
	public void setVerificationType(VerificationType value) {
		safeAccessVerificationType();
		verificationType.setValue(value);
	}
	
	public BeanPropertyReference<VerificationType> getVerificationTypeBean() {
		safeAccessVerificationType();
		return verificationType;
	}
	
	
}
