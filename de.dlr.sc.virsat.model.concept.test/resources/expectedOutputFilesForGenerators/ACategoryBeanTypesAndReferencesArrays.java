/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package testConcept.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import testConcept.model.TestCategoryA;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
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
public abstract class ATestCategoryB extends ABeanCategoryAssignment implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "testConcept.TestCategoryB";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTSUBCATEGORYARRAYDYNAMIC = "testSubCategoryArrayDynamic";
	public static final String PROPERTY_TESTSUBCATEGORYARRAYSTATIC = "testSubCategoryArrayStatic";
	public static final String PROPERTY_TESTREFCATEGORYARRAYDYNAMIC = "testRefCategoryArrayDynamic";
	public static final String PROPERTY_TESTREFCATEGORYARRAYSTATIC = "testRefCategoryArrayStatic";
	public static final String PROPERTY_TESTREFPROPERTYARRAYDYNAMIC = "testRefPropertyArrayDynamic";
	public static final String PROPERTY_TESTREFPROPERTYARRAYSTATIC = "testRefPropertyArrayStatic";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryB() {
	}
	
	public ATestCategoryB(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryB");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryB");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryB(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: testSubCategoryArrayDynamic
	// *****************************************************************
	private IBeanList<TestCategoryA> testSubCategoryArrayDynamic = new TypeSafeComposedPropertyInstanceList<>(TestCategoryA.class);
	
	private void safeAccessTestSubCategoryArrayDynamic() {
		if (testSubCategoryArrayDynamic.getArrayInstance() == null) {
			testSubCategoryArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testSubCategoryArrayDynamic"));
		}
	}
	
	public IBeanList<TestCategoryA> getTestSubCategoryArrayDynamic() {
		safeAccessTestSubCategoryArrayDynamic();
		return testSubCategoryArrayDynamic;
	}
	
	// *****************************************************************
	// * Array Attribute: testSubCategoryArrayStatic
	// *****************************************************************
	private IBeanList<TestCategoryA> testSubCategoryArrayStatic = new TypeSafeComposedPropertyInstanceList<>(TestCategoryA.class);
	
	private void safeAccessTestSubCategoryArrayStatic() {
		if (testSubCategoryArrayStatic.getArrayInstance() == null) {
			testSubCategoryArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testSubCategoryArrayStatic"));
		}
	}
	
	public IBeanList<TestCategoryA> getTestSubCategoryArrayStatic() {
		safeAccessTestSubCategoryArrayStatic();
		return testSubCategoryArrayStatic;
	}
	
	// *****************************************************************
	// * Array Attribute: testRefCategoryArrayDynamic
	// *****************************************************************
		private IBeanList<TestCategoryA> testRefCategoryArrayDynamic = new TypeSafeReferencePropertyInstanceList<>(TestCategoryA.class);
	
		private void safeAccessTestRefCategoryArrayDynamic() {
			if (testRefCategoryArrayDynamic.getArrayInstance() == null) {
				testRefCategoryArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testRefCategoryArrayDynamic"));
			}
		}
	
		public IBeanList<TestCategoryA> getTestRefCategoryArrayDynamic() {
			safeAccessTestRefCategoryArrayDynamic();
			return testRefCategoryArrayDynamic;
		}
	
	// *****************************************************************
	// * Array Attribute: testRefCategoryArrayStatic
	// *****************************************************************
		private IBeanList<TestCategoryA> testRefCategoryArrayStatic = new TypeSafeReferencePropertyInstanceList<>(TestCategoryA.class);
	
		private void safeAccessTestRefCategoryArrayStatic() {
			if (testRefCategoryArrayStatic.getArrayInstance() == null) {
				testRefCategoryArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testRefCategoryArrayStatic"));
			}
		}
	
		public IBeanList<TestCategoryA> getTestRefCategoryArrayStatic() {
			safeAccessTestRefCategoryArrayStatic();
			return testRefCategoryArrayStatic;
		}
	
	// *****************************************************************
	// * Array Attribute: testRefPropertyArrayDynamic
	// *****************************************************************
		private IBeanList<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString> testRefPropertyArrayDynamic = new TypeSafeReferencePropertyInstanceList<>(de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString.class);
	
		private void safeAccessTestRefPropertyArrayDynamic() {
			if (testRefPropertyArrayDynamic.getArrayInstance() == null) {
				testRefPropertyArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testRefPropertyArrayDynamic"));
			}
		}
	
		public IBeanList<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString> getTestRefPropertyArrayDynamic() {
			safeAccessTestRefPropertyArrayDynamic();
			return testRefPropertyArrayDynamic;
		}
	
	// *****************************************************************
	// * Array Attribute: testRefPropertyArrayStatic
	// *****************************************************************
		private IBeanList<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString> testRefPropertyArrayStatic = new TypeSafeReferencePropertyInstanceList<>(de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString.class);
	
		private void safeAccessTestRefPropertyArrayStatic() {
			if (testRefPropertyArrayStatic.getArrayInstance() == null) {
				testRefPropertyArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testRefPropertyArrayStatic"));
			}
		}
	
		public IBeanList<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString> getTestRefPropertyArrayStatic() {
			safeAccessTestRefPropertyArrayStatic();
			return testRefPropertyArrayStatic;
		}
	
	
}

