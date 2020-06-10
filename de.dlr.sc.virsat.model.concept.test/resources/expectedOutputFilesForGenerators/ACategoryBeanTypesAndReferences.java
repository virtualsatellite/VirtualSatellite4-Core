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
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import testConcept.model.TestCategoryA;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.core.runtime.CoreException;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;


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
	public static final String PROPERTY_TESTSUBCATEGORY = "testSubCategory";
	public static final String PROPERTY_TESTREFCATEGORY = "testRefCategory";
	public static final String PROPERTY_TESTREFPROPERTY = "testRefProperty";
	
	
	
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
	// * Attribute: testSubCategory
	// *****************************************************************
	private BeanPropertyComposed<TestCategoryA> testSubCategory = new BeanPropertyComposed<>();
	
	private void safeAccessTestSubCategory() {
		if (testSubCategory.getTypeInstance() == null) {
			ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("testSubCategory");
			testSubCategory.setTypeInstance(propertyInstance);
		}
	}
	
	public TestCategoryA getTestSubCategory() {
		safeAccessTestSubCategory();
		return testSubCategory.getValue();
	}
	
	public BeanPropertyComposed<TestCategoryA> getTestSubCategoryBean() {
		return testSubCategory;
	}
	
	// *****************************************************************
	// * Attribute: testRefCategory
	// *****************************************************************
	private TestCategoryA testRefCategory;
	
	private void safeAccessTestRefCategory() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefCategory");
		CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
		
		if (ca != null) {
			if (testRefCategory == null) {
				createTestRefCategory(ca);
			}
			testRefCategory.setTypeInstance(ca);
		} else {
			testRefCategory = null;
		}
	}
	
	private void createTestRefCategory(CategoryAssignment ca) {
		try {
			BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
			testRefCategory = (TestCategoryA) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public TestCategoryA getTestRefCategory() {
		safeAccessTestRefCategory();
		return testRefCategory;
	}
	
	public Command setTestRefCategory(EditingDomain ed, TestCategoryA value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefCategory");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setTestRefCategory(TestCategoryA value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefCategory");
		if (value != null) {
			propertyInstance.setReference(value.getTypeInstance());
		} else {
			propertyInstance.setReference(null);
		}
	}
	
	// *****************************************************************
	// * Attribute: testRefProperty
	// *****************************************************************
	private BeanPropertyString testRefProperty = new BeanPropertyString();
	
	private void safeAccessTestRefProperty() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefProperty");
		testRefProperty.setATypeInstance(propertyInstance.getReference());
	}
	
	public de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString getTestRefPropertyBean() {
		safeAccessTestRefProperty();
		return testRefProperty;
	}
	
	public Command setTestRefPropertyBean(EditingDomain ed, BeanPropertyString value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefProperty");
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, value.getTypeInstance());
	}
	
	public void setTestRefPropertyBean(BeanPropertyString value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefProperty");
		propertyInstance.setReference(value.getTypeInstance());
	}
	
	
}
