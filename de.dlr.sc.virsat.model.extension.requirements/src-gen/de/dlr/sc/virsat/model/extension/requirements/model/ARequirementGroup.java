/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * 
 * 
 */	
public abstract class ARequirementGroup extends RequirementObject implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.RequirementGroup";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_CHILDREN = "children";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARequirementGroup() {
	}
	
	public ARequirementGroup(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "RequirementGroup");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "RequirementGroup");
		setTypeInstance(categoryAssignement);
	}
	
	public ARequirementGroup(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: children
	// *****************************************************************
	private IBeanList<RequirementObject> children = new TypeSafeComposedPropertyInstanceList<>(RequirementObject.class);
	
	private void safeAccessChildren() {
		if (children.getArrayInstance() == null) {
			children.setArrayInstance((ArrayInstance) helper.getPropertyInstance("children"));
		}
	}
	
	public IBeanList<RequirementObject> getChildren() {
		safeAccessChildren();
		return children;
	}
	
	private IBeanList<BeanPropertyComposed<RequirementObject>> childrenBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessChildrenBean() {
		if (childrenBean.getArrayInstance() == null) {
			childrenBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("children"));
		}
	}
	
	public IBeanList<BeanPropertyComposed<RequirementObject>> getChildrenBean() {
		safeAccessChildrenBean();
		return childrenBean;
	}
	
	
}
