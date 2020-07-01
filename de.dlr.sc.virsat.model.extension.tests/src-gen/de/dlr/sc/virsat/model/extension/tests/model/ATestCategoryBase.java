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
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import org.eclipse.emf.edit.command.SetCommand;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBase;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;


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
public abstract class ATestCategoryBase extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestCategoryBase";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTARRAY = "testArray";
	public static final String PROPERTY_TESTBASEPROPERTY = "testBaseProperty";
	public static final String PROPERTY_TESTREFERENCE = "testReference";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryBase() {
	}
	
	public ATestCategoryBase(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryBase");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryBase");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryBase(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: testArray
	// *****************************************************************
	private IBeanList<TestCategoryBase> testArray = new TypeSafeComposedPropertyInstanceList<>(TestCategoryBase.class);
	
	private void safeAccessTestArray() {
		if (testArray.getArrayInstance() == null) {
			testArray.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testArray"));
		}
	}
	
	public IBeanList<TestCategoryBase> getTestArray() {
		safeAccessTestArray();
		return testArray;
	}
	
	// *****************************************************************
	// * Attribute: testBaseProperty
	// *****************************************************************
	private BeanPropertyInt testBaseProperty = new BeanPropertyInt();
	
	private void safeAccessTestBaseProperty() {
		if (testBaseProperty.getTypeInstance() == null) {
			testBaseProperty.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("testBaseProperty"));
		}
	}
	
	public Command setTestBaseProperty(EditingDomain ed, long value) {
		safeAccessTestBaseProperty();
		return this.testBaseProperty.setValue(ed, value);
	}
	
	public void setTestBaseProperty(long value) {
		safeAccessTestBaseProperty();
		this.testBaseProperty.setValue(value);
	}
	
	public long getTestBaseProperty() {
		safeAccessTestBaseProperty();
		return testBaseProperty.getValue();
	}
	
	public boolean isSetTestBaseProperty() {
		safeAccessTestBaseProperty();
		return testBaseProperty.isSet();
	}
	
	public BeanPropertyInt getTestBasePropertyBean() {
		safeAccessTestBaseProperty();
		return testBaseProperty;
	}
	
	// *****************************************************************
	// * Attribute: testReference
	// *****************************************************************
	private TestCategoryBase testReference;
	
	private void safeAccessTestReference() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testReference");
		CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
		
		if (ca != null) {
			if (testReference == null) {
				createTestReference(ca);
			}
			testReference.setTypeInstance(ca);
		} else {
			testReference = null;
		}
	}
	
	private void createTestReference(CategoryAssignment ca) {
		try {
			BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
			testReference = (TestCategoryBase) beanFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			
		}
	}
					
	public TestCategoryBase getTestReference() {
		safeAccessTestReference();
		return testReference;
	}
	
	public Command setTestReference(EditingDomain ed, TestCategoryBase value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testReference");
		CategoryAssignment ca = value.getTypeInstance();
		return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
	}
	
	public void setTestReference(TestCategoryBase value) {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testReference");
		if (value != null) {
			propertyInstance.setReference(value.getTypeInstance());
		} else {
			propertyInstance.setReference(null);
		}
	}
	
	
}
