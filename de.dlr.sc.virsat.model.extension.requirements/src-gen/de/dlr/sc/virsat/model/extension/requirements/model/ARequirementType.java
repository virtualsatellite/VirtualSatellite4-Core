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
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Definition of a requirement type
 * 
 */	
public abstract class ARequirementType extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.RequirementType";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_ATTRIBUTES = "attributes";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARequirementType() {
	}
	
	public ARequirementType(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "RequirementType");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "RequirementType");
		setTypeInstance(categoryAssignement);
	}
	
	public ARequirementType(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: attributes
	// *****************************************************************
	private IBeanList<RequirementAttribute> attributes = new TypeSafeComposedPropertyInstanceList<>(RequirementAttribute.class);
	
	private void safeAccessAttributes() {
		if (attributes.getArrayInstance() == null) {
			attributes.setArrayInstance((ArrayInstance) helper.getPropertyInstance("attributes"));
		}
	}
	
	public IBeanList<RequirementAttribute> getAttributes() {
		safeAccessAttributes();
		return attributes;
	}
	
	private IBeanList<BeanPropertyComposed<RequirementAttribute>> attributesBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessAttributesBean() {
		if (attributesBean.getArrayInstance() == null) {
			attributesBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("attributes"));
		}
	}
	
	public IBeanList<BeanPropertyComposed<RequirementAttribute>> getAttributesBean() {
		safeAccessAttributesBean();
		return attributesBean;
	}
	
	
}
