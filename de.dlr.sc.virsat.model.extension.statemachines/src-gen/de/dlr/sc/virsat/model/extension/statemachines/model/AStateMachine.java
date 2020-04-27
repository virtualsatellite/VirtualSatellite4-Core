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
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.core.runtime.CoreException;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * A state machine with states and transitions
 * 
 */	
public abstract class AStateMachine extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.statemachines.StateMachine";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_INITIALSTATE = "initialState";
	public static final String PROPERTY_STATES = "states";
	public static final String PROPERTY_TRANSITIONS = "transitions";
	public static final String PROPERTY_CONSTRAINTS = "constraints";
	
	// Type enumeration value names
	public static final String TYPE_SoftwareStateMachine_NAME = "SoftwareStateMachine";
	public static final String TYPE_HardwareStateMachine_NAME = "HardwareStateMachine";
	// Type enumeration values
	public static final String TYPE_SoftwareStateMachine_VALUE = "1";
	public static final String TYPE_HardwareStateMachine_VALUE = "2";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AStateMachine() {
	}
	
	public AStateMachine(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "StateMachine");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "StateMachine");
		setTypeInstance(categoryAssignement);
	}
	
	public AStateMachine(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: type
	// *****************************************************************
	private BeanPropertyEnum type = new BeanPropertyEnum();
	
	private void safeAccessType() {
		if (type.getTypeInstance() == null) {
			type.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("type"));
		}
	}
	
	public Command setType(EditingDomain ed, String value) {
		safeAccessType();
		return this.type.setValue(ed, value);
	}
	
	public void setType(String value) {
		safeAccessType();
		this.type.setValue(value);
	}
	
	public String getType() {
		safeAccessType();
		return type.getValue();
	}
	
	public double getTypeEnum() {
		safeAccessType();
		return type.getEnumValue();
	}
	
	public BeanPropertyEnum getTypeBean() {
		safeAccessType();
		return type;
	}
	
	// *****************************************************************
	// * Attribute: initialState
	// *****************************************************************
	private State initialState;
	
	private void safeAccessInitialState() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("initialState");
		CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
		
		if (ca != null) {
			if (initialState == null) {
				createInitialState(ca);
			}
			initialState.setTypeInstance(ca);
		} else {
			initialState = null;
		}
	}
	
	private void createInitialState(CategoryAssignment ca) {
		try {
			BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
			initialState = (State) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public State getInitialState() {
		safeAccessInitialState();
		return initialState;
	}
	
	public Command setInitialState(EditingDomain ed, State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("initialState");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setInitialState(State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("initialState");
		if (value != null) {
			propertyInstance.setReference(value.getTypeInstance());
		} else {
			propertyInstance.setReference(null);
		}
	}
	
	// *****************************************************************
	// * Array Attribute: states
	// *****************************************************************
	private IBeanList<State> states = new TypeSafeComposedPropertyInstanceList<>(State.class);
	
	private void safeAccessStates() {
		if (states.getArrayInstance() == null) {
			states.setArrayInstance((ArrayInstance) helper.getPropertyInstance("states"));
		}
	}
	
	public IBeanList<State> getStates() {
		safeAccessStates();
		return states;
	}
	
	// *****************************************************************
	// * Array Attribute: transitions
	// *****************************************************************
	private IBeanList<Transition> transitions = new TypeSafeComposedPropertyInstanceList<>(Transition.class);
	
	private void safeAccessTransitions() {
		if (transitions.getArrayInstance() == null) {
			transitions.setArrayInstance((ArrayInstance) helper.getPropertyInstance("transitions"));
		}
	}
	
	public IBeanList<Transition> getTransitions() {
		safeAccessTransitions();
		return transitions;
	}
	
	// *****************************************************************
	// * Array Attribute: constraints
	// *****************************************************************
	private IBeanList<AConstraint> constraints = new TypeSafeComposedPropertyInstanceList<>(AConstraint.class);
	
	private void safeAccessConstraints() {
		if (constraints.getArrayInstance() == null) {
			constraints.setArrayInstance((ArrayInstance) helper.getPropertyInstance("constraints"));
		}
	}
	
	public IBeanList<AConstraint> getConstraints() {
		safeAccessConstraints();
		return constraints;
	}
	
	
}
