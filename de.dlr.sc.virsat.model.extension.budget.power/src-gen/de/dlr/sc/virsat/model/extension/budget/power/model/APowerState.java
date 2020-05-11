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
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.core.runtime.CoreException;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Power data for a single equipment mode
 * 
 */	
public abstract class APowerState extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.budget.power.PowerState";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_MODE = "mode";
	public static final String PROPERTY_POWER = "power";
	public static final String PROPERTY_AVGPOWER = "avgPower";
	public static final String PROPERTY_DUTYCYCLE = "dutyCycle";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public APowerState() {
	}
	
	public APowerState(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "PowerState");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "PowerState");
		setTypeInstance(categoryAssignement);
	}
	
	public APowerState(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: mode
	// *****************************************************************
	private State mode;
	
	private void safeAccessMode() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("mode");
		CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
		
		if (ca != null) {
			if (mode == null) {
				createMode(ca);
			}
			mode.setTypeInstance(ca);
		} else {
			mode = null;
		}
	}
	
	private void createMode(CategoryAssignment ca) {
		try {
			BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
			mode = (State) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public State getMode() {
		safeAccessMode();
		return mode;
	}
	
	public Command setMode(EditingDomain ed, State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("mode");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setMode(State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("mode");
		if (value != null) {
			propertyInstance.setReference(value.getTypeInstance());
		} else {
			propertyInstance.setReference(null);
		}
	}
	
	// *****************************************************************
	// * Attribute: power
	// *****************************************************************
	private BeanPropertyFloat power = new BeanPropertyFloat();
	
	private void safeAccessPower() {
		if (power.getTypeInstance() == null) {
			power.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("power"));
		}
	}
	
	public Command setPower(EditingDomain ed, double value) {
		safeAccessPower();
		return this.power.setValue(ed, value);
	}
	
	public void setPower(double value) {
		safeAccessPower();
		this.power.setValue(value);
	}
	
	public double getPower() {
		safeAccessPower();
		return power.getValue();
	}
	
	public boolean isSetPower() {
		safeAccessPower();
		return power.isSet();
	}
	
	public BeanPropertyFloat getPowerBean() {
		safeAccessPower();
		return power;
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
	// * Attribute: dutyCycle
	// *****************************************************************
	private BeanPropertyFloat dutyCycle = new BeanPropertyFloat();
	
	private void safeAccessDutyCycle() {
		if (dutyCycle.getTypeInstance() == null) {
			dutyCycle.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("dutyCycle"));
		}
	}
	
	public Command setDutyCycle(EditingDomain ed, double value) {
		safeAccessDutyCycle();
		return this.dutyCycle.setValue(ed, value);
	}
	
	public void setDutyCycle(double value) {
		safeAccessDutyCycle();
		this.dutyCycle.setValue(value);
	}
	
	public double getDutyCycle() {
		safeAccessDutyCycle();
		return dutyCycle.getValue();
	}
	
	public boolean isSetDutyCycle() {
		safeAccessDutyCycle();
		return dutyCycle.isSet();
	}
	
	public BeanPropertyFloat getDutyCycleBean() {
		safeAccessDutyCycle();
		return dutyCycle;
	}
	
	
}
