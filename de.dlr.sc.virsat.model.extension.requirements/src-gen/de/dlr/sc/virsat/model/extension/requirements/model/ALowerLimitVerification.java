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
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import javax.xml.bind.annotation.XmlElement;


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
public abstract class ALowerLimitVerification extends IValueVerification implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.LowerLimitVerification";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_LOWERBOUND = "lowerBound";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ALowerLimitVerification() {
	}
	
	public ALowerLimitVerification(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "LowerLimitVerification");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "LowerLimitVerification");
		setTypeInstance(categoryAssignement);
	}
	
	public ALowerLimitVerification(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: lowerBound
	// *****************************************************************
	private BeanPropertyFloat lowerBound = new BeanPropertyFloat();
	
	private void safeAccessLowerBound() {
		if (lowerBound.getTypeInstance() == null) {
			lowerBound.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("lowerBound"));
		}
	}
	
	public Command setLowerBound(EditingDomain ed, double value) {
		safeAccessLowerBound();
		return this.lowerBound.setValue(ed, value);
	}
	
	public void setLowerBound(double value) {
		safeAccessLowerBound();
		this.lowerBound.setValue(value);
	}
	
	public double getLowerBound() {
		safeAccessLowerBound();
		return lowerBound.getValue();
	}
	
	public boolean isSetLowerBound() {
		safeAccessLowerBound();
		return lowerBound.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getLowerBoundBean() {
		safeAccessLowerBound();
		return lowerBound;
	}
	
	
}
