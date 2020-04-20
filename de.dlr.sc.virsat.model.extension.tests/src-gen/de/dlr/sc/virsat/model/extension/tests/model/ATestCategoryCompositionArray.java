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
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
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
public abstract class ATestCategoryCompositionArray extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestCategoryCompositionArray";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTCOMPOSITIONARRAYDYNAMIC = "testCompositionArrayDynamic";
	public static final String PROPERTY_TESTCOMPOSITIONARRAYSTATIC = "testCompositionArrayStatic";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryCompositionArray() {
	}
	
	public ATestCategoryCompositionArray(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryCompositionArray");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryCompositionArray");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryCompositionArray(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: testCompositionArrayDynamic
	// *****************************************************************
	private IBeanList<TestCategoryAllProperty> testCompositionArrayDynamic = new TypeSafeComposedPropertyInstanceList<>(TestCategoryAllProperty.class);
	
	private void safeAccessTestCompositionArrayDynamic() {
		if (testCompositionArrayDynamic.getArrayInstance() == null) {
			testCompositionArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testCompositionArrayDynamic"));
		}
	}
	
	public IBeanList<TestCategoryAllProperty> getTestCompositionArrayDynamic() {
		safeAccessTestCompositionArrayDynamic();
		return testCompositionArrayDynamic;
	}
	
	// *****************************************************************
	// * Array Attribute: testCompositionArrayStatic
	// *****************************************************************
	private IBeanList<TestCategoryAllProperty> testCompositionArrayStatic = new TypeSafeComposedPropertyInstanceList<>(TestCategoryAllProperty.class);
	
	private void safeAccessTestCompositionArrayStatic() {
		if (testCompositionArrayStatic.getArrayInstance() == null) {
			testCompositionArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testCompositionArrayStatic"));
		}
	}
	
	public IBeanList<TestCategoryAllProperty> getTestCompositionArrayStatic() {
		safeAccessTestCompositionArrayStatic();
		return testCompositionArrayStatic;
	}
	
	
}
