/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.model;

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
 * Transitions among states
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ATransition extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.statemachines.Transition";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_STATEFROM = "stateFrom";
	public static final String PROPERTY_STATETO = "stateTo";
	public static final String PROPERTY_TRIGGER = "trigger";
	public static final String PROPERTY_MINEXEC = "minExec";
	public static final String PROPERTY_MAXEXEC = "maxExec";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATransition() {
	}
	
	public ATransition(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "Transition");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "Transition");
		setTypeInstance(categoryAssignement);
	}
	
	public ATransition(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: stateFrom
	// *****************************************************************
	private BeanPropertyReference<State> stateFrom = new BeanPropertyReference<>();
	
	private void safeAccessStateFrom() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateFrom");
		stateFrom.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public State getStateFrom() {
		safeAccessStateFrom();
		return stateFrom.getValue();
	}
	
	public Command setStateFrom(EditingDomain ed, State value) {
		safeAccessStateFrom();
		return stateFrom.setValue(ed, value);
	}
	
	public void setStateFrom(State value) {
		safeAccessStateFrom();
		stateFrom.setValue(value);
	}
	
	public BeanPropertyReference<State> getStateFromBean() {
		safeAccessStateFrom();
		return stateFrom;
	}
	
	// *****************************************************************
	// * Attribute: stateTo
	// *****************************************************************
	private BeanPropertyReference<State> stateTo = new BeanPropertyReference<>();
	
	private void safeAccessStateTo() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateTo");
		stateTo.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public State getStateTo() {
		safeAccessStateTo();
		return stateTo.getValue();
	}
	
	public Command setStateTo(EditingDomain ed, State value) {
		safeAccessStateTo();
		return stateTo.setValue(ed, value);
	}
	
	public void setStateTo(State value) {
		safeAccessStateTo();
		stateTo.setValue(value);
	}
	
	public BeanPropertyReference<State> getStateToBean() {
		safeAccessStateTo();
		return stateTo;
	}
	
	// *****************************************************************
	// * Attribute: trigger
	// *****************************************************************
	private BeanPropertyReference<TransitionTriggerEvent> trigger = new BeanPropertyReference<>();
	
	private void safeAccessTrigger() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("trigger");
		trigger.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public TransitionTriggerEvent getTrigger() {
		safeAccessTrigger();
		return trigger.getValue();
	}
	
	public Command setTrigger(EditingDomain ed, TransitionTriggerEvent value) {
		safeAccessTrigger();
		return trigger.setValue(ed, value);
	}
	
	public void setTrigger(TransitionTriggerEvent value) {
		safeAccessTrigger();
		trigger.setValue(value);
	}
	
	public BeanPropertyReference<TransitionTriggerEvent> getTriggerBean() {
		safeAccessTrigger();
		return trigger;
	}
	
	// *****************************************************************
	// * Attribute: minExec
	// *****************************************************************
	private BeanPropertyFloat minExec = new BeanPropertyFloat();
	
	private void safeAccessMinExec() {
		if (minExec.getTypeInstance() == null) {
			minExec.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("minExec"));
		}
	}
	
	public Command setMinExec(EditingDomain ed, double value) {
		safeAccessMinExec();
		return this.minExec.setValue(ed, value);
	}
	
	public void setMinExec(double value) {
		safeAccessMinExec();
		this.minExec.setValue(value);
	}
	
	public double getMinExec() {
		safeAccessMinExec();
		return minExec.getValue();
	}
	
	public boolean isSetMinExec() {
		safeAccessMinExec();
		return minExec.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMinExecBean() {
		safeAccessMinExec();
		return minExec;
	}
	
	// *****************************************************************
	// * Attribute: maxExec
	// *****************************************************************
	private BeanPropertyFloat maxExec = new BeanPropertyFloat();
	
	private void safeAccessMaxExec() {
		if (maxExec.getTypeInstance() == null) {
			maxExec.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("maxExec"));
		}
	}
	
	public Command setMaxExec(EditingDomain ed, double value) {
		safeAccessMaxExec();
		return this.maxExec.setValue(ed, value);
	}
	
	public void setMaxExec(double value) {
		safeAccessMaxExec();
		this.maxExec.setValue(value);
	}
	
	public double getMaxExec() {
		safeAccessMaxExec();
		return maxExec.getValue();
	}
	
	public boolean isSetMaxExec() {
		safeAccessMaxExec();
		return maxExec.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMaxExecBean() {
		safeAccessMaxExec();
		return maxExec;
	}
	
	
}
