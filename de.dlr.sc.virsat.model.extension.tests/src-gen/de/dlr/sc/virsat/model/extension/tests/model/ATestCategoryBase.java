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
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import javax.xml.bind.annotation.XmlElement;
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
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
	
	private IBeanList<BeanPropertyComposed<TestCategoryBase>> testArrayBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessTestArrayBean() {
		if (testArrayBean.getArrayInstance() == null) {
			testArrayBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testArray"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<TestCategoryBase>> getTestArrayBean() {
		safeAccessTestArrayBean();
		return testArrayBean;
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
	
	@XmlElement
	public BeanPropertyInt getTestBasePropertyBean() {
		safeAccessTestBaseProperty();
		return testBaseProperty;
	}
	
	// *****************************************************************
	// * Attribute: testReference
	// *****************************************************************
	private BeanPropertyReference<TestCategoryBase> testReference = new BeanPropertyReference<>();
	
	private void safeAccessTestReference() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testReference");
		testReference.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public TestCategoryBase getTestReference() {
		safeAccessTestReference();
		return testReference.getValue();
	}
	
	public Command setTestReference(EditingDomain ed, TestCategoryBase value) {
		safeAccessTestReference();
		return testReference.setValue(ed, value);
	}
	
	public void setTestReference(TestCategoryBase value) {
		safeAccessTestReference();
		testReference.setValue(value);
	}
	
	public BeanPropertyReference<TestCategoryBase> getTestReferenceBean() {
		safeAccessTestReference();
		return testReference;
	}
	
	
}
