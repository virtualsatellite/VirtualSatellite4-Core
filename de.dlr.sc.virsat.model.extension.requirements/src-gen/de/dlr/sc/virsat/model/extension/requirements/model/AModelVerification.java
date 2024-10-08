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
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;


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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AModelVerification extends IVerification implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.ModelVerification";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_ELEMENTTOBEVERIFIED = "elementToBeVerified";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AModelVerification() {
	}
	
	public AModelVerification(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ModelVerification");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ModelVerification");
		setTypeInstance(categoryAssignement);
	}
	
	public AModelVerification(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: elementToBeVerified
	// *****************************************************************
	private BeanPropertyEReference<ATypeDefinition> elementToBeVerified = new BeanPropertyEReference<ATypeDefinition>();
	
	private void safeAccessElementToBeVerified() {
		if (elementToBeVerified.getTypeInstance() == null) {
			elementToBeVerified.setTypeInstance((EReferencePropertyInstance) helper.getPropertyInstance("elementToBeVerified"));
		}
	}
	
	public Command setElementToBeVerified(EditingDomain ed, ATypeDefinition value) {
		safeAccessElementToBeVerified();
		return this.elementToBeVerified.setValue(ed, value);
	}
	
	public void setElementToBeVerified(ATypeDefinition value) {
		safeAccessElementToBeVerified();
		this.elementToBeVerified.setValue(value);
	}
	
	public ATypeDefinition getElementToBeVerified() {
		safeAccessElementToBeVerified();
		return elementToBeVerified.getValue();
	}
	
	
	
}
