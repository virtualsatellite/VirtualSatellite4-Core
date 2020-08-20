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
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import javax.xml.bind.annotation.XmlElement;


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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
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
	private BeanPropertyReference<State> mode = new BeanPropertyReference<>();
	
	private void safeAccessMode() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("mode");
		mode.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public State getMode() {
		safeAccessMode();
		return mode.getValue();
	}
	
	public Command setMode(EditingDomain ed, State value) {
		safeAccessMode();
		return mode.setValue(ed, value);
	}
	
	public void setMode(State value) {
		safeAccessMode();
		mode.setValue(value);
	}
	
	public BeanPropertyReference<State> getModeBean() {
		safeAccessMode();
		return mode;
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
	
	@XmlElement
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
	
	@XmlElement
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
	
	@XmlElement
	public BeanPropertyFloat getDutyCycleBean() {
		safeAccessDutyCycle();
		return dutyCycle;
	}
	
	
}
