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
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 * General constraint in statemachines 
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
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
	private BeanPropertyReference<State> stateConstraining = new BeanPropertyReference<>();
	
	private void safeAccessStateConstraining() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateConstraining");
		stateConstraining.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public State getStateConstraining() {
		safeAccessStateConstraining();
		return stateConstraining.getValue();
	}
	
	public Command setStateConstraining(EditingDomain ed, State value) {
		safeAccessStateConstraining();
		return stateConstraining.setValue(ed, value);
	}
	
	public void setStateConstraining(State value) {
		safeAccessStateConstraining();
		stateConstraining.setValue(value);
	}
	
	public BeanPropertyReference<State> getStateConstrainingBean() {
		safeAccessStateConstraining();
		return stateConstraining;
	}
	
	// *****************************************************************
	// * Attribute: stateInfluenced
	// *****************************************************************
	private BeanPropertyReference<State> stateInfluenced = new BeanPropertyReference<>();
	
	private void safeAccessStateInfluenced() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("stateInfluenced");
		stateInfluenced.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public State getStateInfluenced() {
		safeAccessStateInfluenced();
		return stateInfluenced.getValue();
	}
	
	public Command setStateInfluenced(EditingDomain ed, State value) {
		safeAccessStateInfluenced();
		return stateInfluenced.setValue(ed, value);
	}
	
	public void setStateInfluenced(State value) {
		safeAccessStateInfluenced();
		stateInfluenced.setValue(value);
	}
	
	public BeanPropertyReference<State> getStateInfluencedBean() {
		safeAccessStateInfluenced();
		return stateInfluenced;
	}
	
	
}
