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
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
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
 * Transitions among states
 * 
 */	
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
	private State stateFrom;
	
	private void safeAccessStateFrom() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateFrom");
		CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
		
		if (ca != null) {
			if (stateFrom == null) {
				createStateFrom(ca);
			}
			stateFrom.setTypeInstance(ca);
		} else {
			stateFrom = null;
		}
	}
	
	private void createStateFrom(CategoryAssignment ca) {
		try {
			BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
			stateFrom = (State) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public State getStateFrom() {
		safeAccessStateFrom();
		return stateFrom;
	}
	
	public Command setStateFrom(EditingDomain ed, State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateFrom");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setStateFrom(State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateFrom");
		if (value != null) {
			propertyInstance.setReference(value.getTypeInstance());
		} else {
			propertyInstance.setReference(null);
		}
	}
	
	// *****************************************************************
	// * Attribute: stateTo
	// *****************************************************************
	private State stateTo;
	
	private void safeAccessStateTo() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateTo");
		CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
		
		if (ca != null) {
			if (stateTo == null) {
				createStateTo(ca);
			}
			stateTo.setTypeInstance(ca);
		} else {
			stateTo = null;
		}
	}
	
	private void createStateTo(CategoryAssignment ca) {
		try {
			BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
			stateTo = (State) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public State getStateTo() {
		safeAccessStateTo();
		return stateTo;
	}
	
	public Command setStateTo(EditingDomain ed, State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateTo");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setStateTo(State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateTo");
		if (value != null) {
			propertyInstance.setReference(value.getTypeInstance());
		} else {
			propertyInstance.setReference(null);
		}
	}
	
	
}
