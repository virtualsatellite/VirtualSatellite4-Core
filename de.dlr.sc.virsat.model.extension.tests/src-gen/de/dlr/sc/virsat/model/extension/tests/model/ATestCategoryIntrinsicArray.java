/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.list.TypeSafeArrayInstanceList;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
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
public abstract class ATestCategoryIntrinsicArray extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestCategoryIntrinsicArray";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTSTRINGARRAYDYNAMIC = "testStringArrayDynamic";
	public static final String PROPERTY_TESTSTRINGARRAYSTATIC = "testStringArrayStatic";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryIntrinsicArray() {
	}
	
	public ATestCategoryIntrinsicArray(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryIntrinsicArray");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryIntrinsicArray");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryIntrinsicArray(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: testStringArrayDynamic
	// *****************************************************************
	private IBeanList<BeanPropertyString> testStringArrayDynamic = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);
	
	private void safeAccessTestStringArrayDynamic() {
		if (testStringArrayDynamic.getArrayInstance() == null) {
			testStringArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testStringArrayDynamic"));
		}
	}
	
	public IBeanList<BeanPropertyString> getTestStringArrayDynamic() {
		safeAccessTestStringArrayDynamic();
		return testStringArrayDynamic;
	}
	
	// *****************************************************************
	// * Array Attribute: testStringArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyString> testStringArrayStatic = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);
	
	private void safeAccessTestStringArrayStatic() {
		if (testStringArrayStatic.getArrayInstance() == null) {
			testStringArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testStringArrayStatic"));
		}
	}
	
	public IBeanList<BeanPropertyString> getTestStringArrayStatic() {
		safeAccessTestStringArrayStatic();
		return testStringArrayStatic;
	}
	
	
}
