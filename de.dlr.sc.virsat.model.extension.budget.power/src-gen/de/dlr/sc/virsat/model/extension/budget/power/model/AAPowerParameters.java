/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.power.model;

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
 * Abstract definition of a collection of power parameters
 * 
 */	
public abstract class AAPowerParameters extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.budget.power.APowerParameters";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_AVGPOWER = "avgPower";
	public static final String PROPERTY_MINPOWER = "minPower";
	public static final String PROPERTY_MAXPOWER = "maxPower";
	public static final String PROPERTY_AVGPOWERWITHMARGIN = "avgPowerWithMargin";
	public static final String PROPERTY_AVGPOWERMARGIN = "avgPowerMargin";
	public static final String PROPERTY_MARGIN = "margin";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AAPowerParameters() {
	}
	
	public AAPowerParameters(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "APowerParameters");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "APowerParameters");
		setTypeInstance(categoryAssignement);
	}
	
	public AAPowerParameters(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: avgPower
	// *****************************************************************
	private BeanPropertyFloat avgPower = new BeanPropertyFloat();
	
	private void safeAccessAvgPower() {
		if (avgPower.getTypeInstance() == null) {
			avgPower.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("avgPower"));
		}
	}
	
	public Command setAvgPower(EditingDomain ed, double value) {
		safeAccessAvgPower();
		return this.avgPower.setValue(ed, value);
	}
	
	public void setAvgPower(double value) {
		safeAccessAvgPower();
		this.avgPower.setValue(value);
	}
	
	public double getAvgPower() {
		safeAccessAvgPower();
		return avgPower.getValue();
	}
	
	public boolean isSetAvgPower() {
		safeAccessAvgPower();
		return avgPower.isSet();
	}
	
	public BeanPropertyFloat getAvgPowerBean() {
		safeAccessAvgPower();
		return avgPower;
	}
	
	// *****************************************************************
	// * Attribute: minPower
	// *****************************************************************
	private BeanPropertyFloat minPower = new BeanPropertyFloat();
	
	private void safeAccessMinPower() {
		if (minPower.getTypeInstance() == null) {
			minPower.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("minPower"));
		}
	}
	
	public Command setMinPower(EditingDomain ed, double value) {
		safeAccessMinPower();
		return this.minPower.setValue(ed, value);
	}
	
	public void setMinPower(double value) {
		safeAccessMinPower();
		this.minPower.setValue(value);
	}
	
	public double getMinPower() {
		safeAccessMinPower();
		return minPower.getValue();
	}
	
	public boolean isSetMinPower() {
		safeAccessMinPower();
		return minPower.isSet();
	}
	
	public BeanPropertyFloat getMinPowerBean() {
		safeAccessMinPower();
		return minPower;
	}
	
	// *****************************************************************
	// * Attribute: maxPower
	// *****************************************************************
	private BeanPropertyFloat maxPower = new BeanPropertyFloat();
	
	private void safeAccessMaxPower() {
		if (maxPower.getTypeInstance() == null) {
			maxPower.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("maxPower"));
		}
	}
	
	public Command setMaxPower(EditingDomain ed, double value) {
		safeAccessMaxPower();
		return this.maxPower.setValue(ed, value);
	}
	
	public void setMaxPower(double value) {
		safeAccessMaxPower();
		this.maxPower.setValue(value);
	}
	
	public double getMaxPower() {
		safeAccessMaxPower();
		return maxPower.getValue();
	}
	
	public boolean isSetMaxPower() {
		safeAccessMaxPower();
		return maxPower.isSet();
	}
	
	public BeanPropertyFloat getMaxPowerBean() {
		safeAccessMaxPower();
		return maxPower;
	}
	
	// *****************************************************************
	// * Attribute: avgPowerWithMargin
	// *****************************************************************
	private BeanPropertyFloat avgPowerWithMargin = new BeanPropertyFloat();
	
	private void safeAccessAvgPowerWithMargin() {
		if (avgPowerWithMargin.getTypeInstance() == null) {
			avgPowerWithMargin.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("avgPowerWithMargin"));
		}
	}
	
	public Command setAvgPowerWithMargin(EditingDomain ed, double value) {
		safeAccessAvgPowerWithMargin();
		return this.avgPowerWithMargin.setValue(ed, value);
	}
	
	public void setAvgPowerWithMargin(double value) {
		safeAccessAvgPowerWithMargin();
		this.avgPowerWithMargin.setValue(value);
	}
	
	public double getAvgPowerWithMargin() {
		safeAccessAvgPowerWithMargin();
		return avgPowerWithMargin.getValue();
	}
	
	public boolean isSetAvgPowerWithMargin() {
		safeAccessAvgPowerWithMargin();
		return avgPowerWithMargin.isSet();
	}
	
	public BeanPropertyFloat getAvgPowerWithMarginBean() {
		safeAccessAvgPowerWithMargin();
		return avgPowerWithMargin;
	}
	
	// *****************************************************************
	// * Attribute: avgPowerMargin
	// *****************************************************************
	private BeanPropertyFloat avgPowerMargin = new BeanPropertyFloat();
	
	private void safeAccessAvgPowerMargin() {
		if (avgPowerMargin.getTypeInstance() == null) {
			avgPowerMargin.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("avgPowerMargin"));
		}
	}
	
	public Command setAvgPowerMargin(EditingDomain ed, double value) {
		safeAccessAvgPowerMargin();
		return this.avgPowerMargin.setValue(ed, value);
	}
	
	public void setAvgPowerMargin(double value) {
		safeAccessAvgPowerMargin();
		this.avgPowerMargin.setValue(value);
	}
	
	public double getAvgPowerMargin() {
		safeAccessAvgPowerMargin();
		return avgPowerMargin.getValue();
	}
	
	public boolean isSetAvgPowerMargin() {
		safeAccessAvgPowerMargin();
		return avgPowerMargin.isSet();
	}
	
	public BeanPropertyFloat getAvgPowerMarginBean() {
		safeAccessAvgPowerMargin();
		return avgPowerMargin;
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
