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
 * General constraint in statemachines 
 * 
 */	
public abstract class AAConstraint extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.statemachines.AConstraint";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_STATECONSTRAINING = "stateConstraining";
	public static final String PROPERTY_STATEINFLUENCED = "stateInfluenced";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AAConstraint() {
	}
	
	public AAConstraint(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "AConstraint");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "AConstraint");
		setTypeInstance(categoryAssignement);
	}
	
	public AAConstraint(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: stateConstraining
	// *****************************************************************
	private State stateConstraining;
	
	private void safeAccessStateConstraining() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateConstraining");
		CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
		
		if (ca != null) {
			if (stateConstraining == null) {
				createStateConstraining(ca);
			}
			stateConstraining.setTypeInstance(ca);
		} else {
			stateConstraining = null;
		}
	}
	
	private void createStateConstraining(CategoryAssignment ca) {
		try {
			BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
			stateConstraining = (State) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public State getStateConstraining() {
		safeAccessStateConstraining();
		return stateConstraining;
	}
	
	public Command setStateConstraining(EditingDomain ed, State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateConstraining");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setStateConstraining(State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateConstraining");
		if (value != null) {
			propertyInstance.setReference(value.getTypeInstance());
		} else {
			propertyInstance.setReference(null);
		}
	}
	
	// *****************************************************************
	// * Attribute: stateInfluenced
	// *****************************************************************
	private State stateInfluenced;
	
	private void safeAccessStateInfluenced() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateInfluenced");
		CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
		
		if (ca != null) {
			if (stateInfluenced == null) {
				createStateInfluenced(ca);
			}
			stateInfluenced.setTypeInstance(ca);
		} else {
			stateInfluenced = null;
		}
	}
	
	private void createStateInfluenced(CategoryAssignment ca) {
		try {
			BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
			stateInfluenced = (State) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public State getStateInfluenced() {
		safeAccessStateInfluenced();
		return stateInfluenced;
	}
	
	public Command setStateInfluenced(EditingDomain ed, State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateInfluenced");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setStateInfluenced(State value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateInfluenced");
		if (value != null) {
			propertyInstance.setReference(value.getTypeInstance());
		} else {
			propertyInstance.setReference(null);
		}
	}
	
	
}
