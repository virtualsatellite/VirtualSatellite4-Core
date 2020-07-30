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
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
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
	
	private IBeanList<BeanPropertyComposed<TestCategoryAllProperty>> testCompositionArrayDynamicBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessTestCompositionArrayDynamicBean() {
		if (testCompositionArrayDynamicBean.getArrayInstance() == null) {
			testCompositionArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testCompositionArrayDynamic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<TestCategoryAllProperty>> getTestCompositionArrayDynamicBean() {
		safeAccessTestCompositionArrayDynamicBean();
		return testCompositionArrayDynamicBean;
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
	
	private IBeanList<BeanPropertyComposed<TestCategoryAllProperty>> testCompositionArrayStaticBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessTestCompositionArrayStaticBean() {
		if (testCompositionArrayStaticBean.getArrayInstance() == null) {
			testCompositionArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testCompositionArrayStatic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<TestCategoryAllProperty>> getTestCompositionArrayStaticBean() {
		safeAccessTestCompositionArrayStaticBean();
		return testCompositionArrayStaticBean;
	}
	
	
}
