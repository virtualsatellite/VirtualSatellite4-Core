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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
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
 * 
 * 
 */	
public abstract class AExistenceVerification extends ModelVerification implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.ExistenceVerification";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TARGET = "target";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AExistenceVerification() {
	}
	
	public AExistenceVerification(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ExistenceVerification");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ExistenceVerification");
		setTypeInstance(categoryAssignement);
	}
	
	public AExistenceVerification(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: target
	// *****************************************************************
		private IBeanList<GenericCategory> target = new TypeSafeReferencePropertyInstanceList<>(GenericCategory.class);
	
		private void safeAccessTarget() {
			if (target.getArrayInstance() == null) {
				target.setArrayInstance((ArrayInstance) helper.getPropertyInstance("target"));
			}
		}
	
		public IBeanList<GenericCategory> getTarget() {
			safeAccessTarget();
			return target;
		}
		
		private IBeanList<BeanPropertyReference<GenericCategory>> targetBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTargetBean() {
			if (targetBean.getArrayInstance() == null) {
				targetBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("target"));
			}
		}
		
		public IBeanList<BeanPropertyReference<GenericCategory>> getTargetBean() {
			safeAccessTargetBean();
			return targetBean;
		}
	
	
}
