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
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
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
public abstract class ATestCategoryReference extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestCategoryReference";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTREFCATEGORY = "testRefCategory";
	public static final String PROPERTY_TESTREFPROPERTY = "testRefProperty";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryReference() {
	}
	
	public ATestCategoryReference(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryReference");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryReference");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryReference(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: testRefCategory
	// *****************************************************************
	private TestCategoryAllProperty testRefCategory;
	
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
			testRefCategory = (TestCategoryAllProperty) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public TestCategoryAllProperty getTestRefCategory() {
		safeAccessTestRefCategory();
		return testRefCategory;
	}
	
	public Command setTestRefCategory(EditingDomain ed, TestCategoryAllProperty value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefCategory");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setTestRefCategory(TestCategoryAllProperty value) {
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
