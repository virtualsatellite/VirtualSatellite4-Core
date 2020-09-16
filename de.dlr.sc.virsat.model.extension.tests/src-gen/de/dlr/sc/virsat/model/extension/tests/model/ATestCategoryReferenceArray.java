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
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
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
 * 
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ATestCategoryReferenceArray extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestCategoryReferenceArray";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTCATEGORYREFERENCEARRAYDYNAMIC = "testCategoryReferenceArrayDynamic";
	public static final String PROPERTY_TESTCATEGORYREFERENCEARRAYSTATIC = "testCategoryReferenceArrayStatic";
	public static final String PROPERTY_TESTPROPERTYREFERENCEARRAYDYNAMIC = "testPropertyReferenceArrayDynamic";
	public static final String PROPERTY_TESTPROPERTYREFERENCEARRAYSTATIC = "testPropertyReferenceArrayStatic";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryReferenceArray() {
	}
	
	public ATestCategoryReferenceArray(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryReferenceArray");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryReferenceArray");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryReferenceArray(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: testCategoryReferenceArrayDynamic
	// *****************************************************************
		private IBeanList<TestCategoryAllProperty> testCategoryReferenceArrayDynamic = new TypeSafeReferencePropertyInstanceList<>(TestCategoryAllProperty.class);
	
		private void safeAccessTestCategoryReferenceArrayDynamic() {
			if (testCategoryReferenceArrayDynamic.getArrayInstance() == null) {
				testCategoryReferenceArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testCategoryReferenceArrayDynamic"));
			}
		}
	
		public IBeanList<TestCategoryAllProperty> getTestCategoryReferenceArrayDynamic() {
			safeAccessTestCategoryReferenceArrayDynamic();
			return testCategoryReferenceArrayDynamic;
		}
		
		private IBeanList<BeanPropertyReference<TestCategoryAllProperty>> testCategoryReferenceArrayDynamicBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTestCategoryReferenceArrayDynamicBean() {
			if (testCategoryReferenceArrayDynamicBean.getArrayInstance() == null) {
				testCategoryReferenceArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testCategoryReferenceArrayDynamic"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<TestCategoryAllProperty>> getTestCategoryReferenceArrayDynamicBean() {
			safeAccessTestCategoryReferenceArrayDynamicBean();
			return testCategoryReferenceArrayDynamicBean;
		}
	
	// *****************************************************************
	// * Array Attribute: testCategoryReferenceArrayStatic
	// *****************************************************************
		private IBeanList<TestCategoryAllProperty> testCategoryReferenceArrayStatic = new TypeSafeReferencePropertyInstanceList<>(TestCategoryAllProperty.class);
	
		private void safeAccessTestCategoryReferenceArrayStatic() {
			if (testCategoryReferenceArrayStatic.getArrayInstance() == null) {
				testCategoryReferenceArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testCategoryReferenceArrayStatic"));
			}
		}
	
		public IBeanList<TestCategoryAllProperty> getTestCategoryReferenceArrayStatic() {
			safeAccessTestCategoryReferenceArrayStatic();
			return testCategoryReferenceArrayStatic;
		}
		
		private IBeanList<BeanPropertyReference<TestCategoryAllProperty>> testCategoryReferenceArrayStaticBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTestCategoryReferenceArrayStaticBean() {
			if (testCategoryReferenceArrayStaticBean.getArrayInstance() == null) {
				testCategoryReferenceArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testCategoryReferenceArrayStatic"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<TestCategoryAllProperty>> getTestCategoryReferenceArrayStaticBean() {
			safeAccessTestCategoryReferenceArrayStaticBean();
			return testCategoryReferenceArrayStaticBean;
		}
	
	// *****************************************************************
	// * Array Attribute: testPropertyReferenceArrayDynamic
	// *****************************************************************
		private IBeanList<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString> testPropertyReferenceArrayDynamic = new TypeSafeReferencePropertyInstanceList<>(de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString.class);
	
		private void safeAccessTestPropertyReferenceArrayDynamic() {
			if (testPropertyReferenceArrayDynamic.getArrayInstance() == null) {
				testPropertyReferenceArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testPropertyReferenceArrayDynamic"));
			}
		}
	
		public IBeanList<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString> getTestPropertyReferenceArrayDynamic() {
			safeAccessTestPropertyReferenceArrayDynamic();
			return testPropertyReferenceArrayDynamic;
		}
		
		private IBeanList<BeanPropertyReference<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString>> testPropertyReferenceArrayDynamicBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTestPropertyReferenceArrayDynamicBean() {
			if (testPropertyReferenceArrayDynamicBean.getArrayInstance() == null) {
				testPropertyReferenceArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testPropertyReferenceArrayDynamic"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString>> getTestPropertyReferenceArrayDynamicBean() {
			safeAccessTestPropertyReferenceArrayDynamicBean();
			return testPropertyReferenceArrayDynamicBean;
		}
	
	// *****************************************************************
	// * Array Attribute: testPropertyReferenceArrayStatic
	// *****************************************************************
		private IBeanList<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString> testPropertyReferenceArrayStatic = new TypeSafeReferencePropertyInstanceList<>(de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString.class);
	
		private void safeAccessTestPropertyReferenceArrayStatic() {
			if (testPropertyReferenceArrayStatic.getArrayInstance() == null) {
				testPropertyReferenceArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testPropertyReferenceArrayStatic"));
			}
		}
	
		public IBeanList<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString> getTestPropertyReferenceArrayStatic() {
			safeAccessTestPropertyReferenceArrayStatic();
			return testPropertyReferenceArrayStatic;
		}
		
		private IBeanList<BeanPropertyReference<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString>> testPropertyReferenceArrayStaticBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTestPropertyReferenceArrayStaticBean() {
			if (testPropertyReferenceArrayStaticBean.getArrayInstance() == null) {
				testPropertyReferenceArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testPropertyReferenceArrayStatic"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString>> getTestPropertyReferenceArrayStaticBean() {
			safeAccessTestPropertyReferenceArrayStaticBean();
			return testPropertyReferenceArrayStaticBean;
		}
	
	
}
