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
public abstract class AUpperLimitVerification extends IValueVerification implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.UpperLimitVerification";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_UPPERBOUND = "upperBound";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AUpperLimitVerification() {
	}
	
	public AUpperLimitVerification(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "UpperLimitVerification");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "UpperLimitVerification");
		setTypeInstance(categoryAssignement);
	}
	
	public AUpperLimitVerification(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: upperBound
	// *****************************************************************
	private BeanPropertyFloat upperBound = new BeanPropertyFloat();
	
	private void safeAccessUpperBound() {
		if (upperBound.getTypeInstance() == null) {
			upperBound.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("upperBound"));
		}
	}
	
	public Command setUpperBound(EditingDomain ed, double value) {
		safeAccessUpperBound();
		return this.upperBound.setValue(ed, value);
	}
	
	public void setUpperBound(double value) {
		safeAccessUpperBound();
		this.upperBound.setValue(value);
	}
	
	public double getUpperBound() {
		safeAccessUpperBound();
		return upperBound.getValue();
	}
	
	public boolean isSetUpperBound() {
		safeAccessUpperBound();
		return upperBound.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getUpperBoundBean() {
		safeAccessUpperBound();
		return upperBound;
	}
	
	
}
