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
	
	
}
