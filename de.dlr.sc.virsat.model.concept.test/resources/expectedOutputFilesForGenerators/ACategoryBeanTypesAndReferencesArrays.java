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
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import javax.xml.bind.annotation.XmlElement;


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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
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
	
	private IBeanList<BeanPropertyComposed<TestCategoryA>> testSubCategoryArrayDynamicBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessTestSubCategoryArrayDynamicBean() {
		if (testSubCategoryArrayDynamicBean.getArrayInstance() == null) {
			testSubCategoryArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testSubCategoryArrayDynamic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<TestCategoryA>> getTestSubCategoryArrayDynamicBean() {
		safeAccessTestSubCategoryArrayDynamicBean();
		return testSubCategoryArrayDynamicBean;
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
	
	private IBeanList<BeanPropertyComposed<TestCategoryA>> testSubCategoryArrayStaticBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessTestSubCategoryArrayStaticBean() {
		if (testSubCategoryArrayStaticBean.getArrayInstance() == null) {
			testSubCategoryArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testSubCategoryArrayStatic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<TestCategoryA>> getTestSubCategoryArrayStaticBean() {
		safeAccessTestSubCategoryArrayStaticBean();
		return testSubCategoryArrayStaticBean;
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
		
		private IBeanList<BeanPropertyReference<TestCategoryA>> testRefCategoryArrayDynamicBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTestRefCategoryArrayDynamicBean() {
			if (testRefCategoryArrayDynamicBean.getArrayInstance() == null) {
				testRefCategoryArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testRefCategoryArrayDynamic"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<TestCategoryA>> getTestRefCategoryArrayDynamicBean() {
			safeAccessTestRefCategoryArrayDynamicBean();
			return testRefCategoryArrayDynamicBean;
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
		
		private IBeanList<BeanPropertyReference<TestCategoryA>> testRefCategoryArrayStaticBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTestRefCategoryArrayStaticBean() {
			if (testRefCategoryArrayStaticBean.getArrayInstance() == null) {
				testRefCategoryArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testRefCategoryArrayStatic"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<TestCategoryA>> getTestRefCategoryArrayStaticBean() {
			safeAccessTestRefCategoryArrayStaticBean();
			return testRefCategoryArrayStaticBean;
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
		
		private IBeanList<BeanPropertyReference<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString>> testRefPropertyArrayDynamicBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTestRefPropertyArrayDynamicBean() {
			if (testRefPropertyArrayDynamicBean.getArrayInstance() == null) {
				testRefPropertyArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testRefPropertyArrayDynamic"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString>> getTestRefPropertyArrayDynamicBean() {
			safeAccessTestRefPropertyArrayDynamicBean();
			return testRefPropertyArrayDynamicBean;
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
		
		private IBeanList<BeanPropertyReference<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString>> testRefPropertyArrayStaticBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTestRefPropertyArrayStaticBean() {
			if (testRefPropertyArrayStaticBean.getArrayInstance() == null) {
				testRefPropertyArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testRefPropertyArrayStatic"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString>> getTestRefPropertyArrayStaticBean() {
			safeAccessTestRefPropertyArrayStaticBean();
			return testRefPropertyArrayStaticBean;
		}
	
	
}
