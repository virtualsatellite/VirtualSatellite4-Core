/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.model;

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
import de.dlr.sc.virsat.model.extension.funcelectrical.model.VoltageDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Interval range of nominal voltages
 * 
 */	
public abstract class ARangedVoltageDefinition extends VoltageDefinition implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.funcelectrical.RangedVoltageDefinition";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_MINVOLTAGE = "minVoltage";
	public static final String PROPERTY_MAXVOLTAGE = "maxVoltage";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARangedVoltageDefinition() {
	}
	
	public ARangedVoltageDefinition(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "RangedVoltageDefinition");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "RangedVoltageDefinition");
		setTypeInstance(categoryAssignement);
	}
	
	public ARangedVoltageDefinition(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: minVoltage
	// *****************************************************************
	private BeanPropertyFloat minVoltage = new BeanPropertyFloat();
	
	private void safeAccessMinVoltage() {
		if (minVoltage.getTypeInstance() == null) {
			minVoltage.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("minVoltage"));
		}
	}
	
	public Command setMinVoltage(EditingDomain ed, double value) {
		safeAccessMinVoltage();
		return this.minVoltage.setValue(ed, value);
	}
	
	public void setMinVoltage(double value) {
		safeAccessMinVoltage();
		this.minVoltage.setValue(value);
	}
	
	public double getMinVoltage() {
		safeAccessMinVoltage();
		return minVoltage.getValue();
	}
	
	public boolean isSetMinVoltage() {
		safeAccessMinVoltage();
		return minVoltage.isSet();
	}
	
	public BeanPropertyFloat getMinVoltageBean() {
		safeAccessMinVoltage();
		return minVoltage;
	}
	
	// *****************************************************************
	// * Attribute: maxVoltage
	// *****************************************************************
	private BeanPropertyFloat maxVoltage = new BeanPropertyFloat();
	
	private void safeAccessMaxVoltage() {
		if (maxVoltage.getTypeInstance() == null) {
			maxVoltage.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("maxVoltage"));
		}
	}
	
	public Command setMaxVoltage(EditingDomain ed, double value) {
		safeAccessMaxVoltage();
		return this.maxVoltage.setValue(ed, value);
	}
	
	public void setMaxVoltage(double value) {
		safeAccessMaxVoltage();
		this.maxVoltage.setValue(value);
	}
	
	public double getMaxVoltage() {
		safeAccessMaxVoltage();
		return maxVoltage.getValue();
	}
	
	public boolean isSetMaxVoltage() {
		safeAccessMaxVoltage();
		return maxVoltage.isSet();
	}
	
	public BeanPropertyFloat getMaxVoltageBean() {
		safeAccessMaxVoltage();
		return maxVoltage;
	}
	
	
}
