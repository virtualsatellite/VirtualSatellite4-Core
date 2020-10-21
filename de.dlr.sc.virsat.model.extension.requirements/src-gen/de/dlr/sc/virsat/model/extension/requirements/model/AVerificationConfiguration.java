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
 * 
 * 
 */	
public abstract class AVerificationConfiguration extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.VerificationConfiguration";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_VERIFICATIONTYPES = "verificationTypes";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AVerificationConfiguration() {
	}
	
	public AVerificationConfiguration(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "VerificationConfiguration");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "VerificationConfiguration");
		setTypeInstance(categoryAssignement);
	}
	
	public AVerificationConfiguration(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: verificationTypes
	// *****************************************************************
	private IBeanList<VerificationType> verificationTypes = new TypeSafeComposedPropertyInstanceList<>(VerificationType.class);
	
	private void safeAccessVerificationTypes() {
		if (verificationTypes.getArrayInstance() == null) {
			verificationTypes.setArrayInstance((ArrayInstance) helper.getPropertyInstance("verificationTypes"));
		}
	}
	
	public IBeanList<VerificationType> getVerificationTypes() {
		safeAccessVerificationTypes();
		return verificationTypes;
	}
	
	private IBeanList<BeanPropertyComposed<VerificationType>> verificationTypesBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessVerificationTypesBean() {
		if (verificationTypesBean.getArrayInstance() == null) {
			verificationTypesBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("verificationTypes"));
		}
	}
	
	public IBeanList<BeanPropertyComposed<VerificationType>> getVerificationTypesBean() {
		safeAccessVerificationTypesBean();
		return verificationTypesBean;
	}
	
	
}
