/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.mass.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Definition of the equipment mass
 * 
 */	
public abstract class AAMassParameters extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.budget.mass.AMassParameters";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_MASS = "mass";
	public static final String PROPERTY_MASSWITHMARGIN = "massWithMargin";
	public static final String PROPERTY_MASSMARGIN = "massMargin";
	public static final String PROPERTY_MARGIN = "margin";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AAMassParameters() {
	}
	
	public AAMassParameters(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "AMassParameters");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "AMassParameters");
		setTypeInstance(categoryAssignement);
	}
	
	public AAMassParameters(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: mass
	// *****************************************************************
	private BeanPropertyFloat mass = new BeanPropertyFloat();
	
	private void safeAccessMass() {
		if (mass.getTypeInstance() == null) {
			mass.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("mass"));
		}
	}
	
	public Command setMass(EditingDomain ed, double value) {
		safeAccessMass();
		return this.mass.setValue(ed, value);
	}
	
	public void setMass(double value) {
		safeAccessMass();
		this.mass.setValue(value);
	}
	
	public double getMass() {
		safeAccessMass();
		return mass.getValue();
	}
	
	public boolean isSetMass() {
		safeAccessMass();
		return mass.isSet();
	}
	
	public BeanPropertyFloat getMassBean() {
		safeAccessMass();
		return mass;
	}
	
	// *****************************************************************
	// * Attribute: massWithMargin
	// *****************************************************************
	private BeanPropertyFloat massWithMargin = new BeanPropertyFloat();
	
	private void safeAccessMassWithMargin() {
		if (massWithMargin.getTypeInstance() == null) {
			massWithMargin.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("massWithMargin"));
		}
	}
	
	public Command setMassWithMargin(EditingDomain ed, double value) {
		safeAccessMassWithMargin();
		return this.massWithMargin.setValue(ed, value);
	}
	
	public void setMassWithMargin(double value) {
		safeAccessMassWithMargin();
		this.massWithMargin.setValue(value);
	}
	
	public double getMassWithMargin() {
		safeAccessMassWithMargin();
		return massWithMargin.getValue();
	}
	
	public boolean isSetMassWithMargin() {
		safeAccessMassWithMargin();
		return massWithMargin.isSet();
	}
	
	public BeanPropertyFloat getMassWithMarginBean() {
		safeAccessMassWithMargin();
		return massWithMargin;
	}
	
	// *****************************************************************
	// * Attribute: massMargin
	// *****************************************************************
	private BeanPropertyFloat massMargin = new BeanPropertyFloat();
	
	private void safeAccessMassMargin() {
		if (massMargin.getTypeInstance() == null) {
			massMargin.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("massMargin"));
		}
	}
	
	public Command setMassMargin(EditingDomain ed, double value) {
		safeAccessMassMargin();
		return this.massMargin.setValue(ed, value);
	}
	
	public void setMassMargin(double value) {
		safeAccessMassMargin();
		this.massMargin.setValue(value);
	}
	
	public double getMassMargin() {
		safeAccessMassMargin();
		return massMargin.getValue();
	}
	
	public boolean isSetMassMargin() {
		safeAccessMassMargin();
		return massMargin.isSet();
	}
	
	public BeanPropertyFloat getMassMarginBean() {
		safeAccessMassMargin();
		return massMargin;
	}
	
	// *****************************************************************
	// * Attribute: margin
	// *****************************************************************
	private BeanPropertyFloat margin = new BeanPropertyFloat();
	
	private void safeAccessMargin() {
		if (margin.getTypeInstance() == null) {
			margin.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("margin"));
		}
	}
	
	public Command setMargin(EditingDomain ed, double value) {
		safeAccessMargin();
		return this.margin.setValue(ed, value);
	}
	
	public void setMargin(double value) {
		safeAccessMargin();
		this.margin.setValue(value);
	}
	
	public double getMargin() {
		safeAccessMargin();
		return margin.getValue();
	}
	
	public boolean isSetMargin() {
		safeAccessMargin();
		return margin.isSet();
	}
	
	public BeanPropertyFloat getMarginBean() {
		safeAccessMargin();
		return margin;
	}
	
	
}
