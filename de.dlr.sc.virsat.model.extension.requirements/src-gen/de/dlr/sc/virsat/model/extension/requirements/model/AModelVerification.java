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
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.ecore.EObject;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;


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
	private BeanPropertyEReference<EObject> elementToBeVerified = new BeanPropertyEReference<EObject>();
	
	private void safeAccessElementToBeVerified() {
		if (elementToBeVerified.getTypeInstance() == null) {
			elementToBeVerified.setTypeInstance((EReferencePropertyInstance) helper.getPropertyInstance("elementToBeVerified"));
		}
	}
	
	public Command setElementToBeVerified(EditingDomain ed, EObject value) {
		safeAccessElementToBeVerified();
		return this.elementToBeVerified.setValue(ed, value);
	}
	
	public void setElementToBeVerified(EObject value) {
		safeAccessElementToBeVerified();
		this.elementToBeVerified.setValue(value);
	}
	
	public EObject getElementToBeVerified() {
		safeAccessElementToBeVerified();
		return elementToBeVerified.getValue();
	}
	
	
	
}
