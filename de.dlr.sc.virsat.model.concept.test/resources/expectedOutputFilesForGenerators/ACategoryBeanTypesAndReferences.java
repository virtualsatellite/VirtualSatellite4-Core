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
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
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
	
	@XmlElement(nillable = true)
	public TestCategoryA getTestSubCategory() {
		safeAccessTestSubCategory();
		return testSubCategory.getValue();
	}
	
	public BeanPropertyComposed<TestCategoryA> getTestSubCategoryBean() {
		safeAccessTestSubCategory();
		return testSubCategory;
	}
	
	// *****************************************************************
	// * Attribute: testRefCategory
	// *****************************************************************
	private BeanPropertyReference<TestCategoryA> testRefCategory = new BeanPropertyReference<>();
	
	private void safeAccessTestRefCategory() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefCategory");
		testRefCategory.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public TestCategoryA getTestRefCategory() {
		safeAccessTestRefCategory();
		return testRefCategory.getValue();
	}
	
	public Command setTestRefCategory(EditingDomain ed, TestCategoryA value) {
		safeAccessTestRefCategory();
		return testRefCategory.setValue(ed, value);
	}
	
	public void setTestRefCategory(TestCategoryA value) {
		safeAccessTestRefCategory();
		testRefCategory.setValue(value);
	}
	
	public BeanPropertyReference<TestCategoryA> getTestRefCategoryBean() {
		safeAccessTestRefCategory();
		return testRefCategory;
	}
	
	// *****************************************************************
	// * Attribute: testRefProperty
	// *****************************************************************
	private BeanPropertyReference<BeanPropertyString> testRefProperty = new BeanPropertyReference<>();
	
	private void safeAccessTestRefProperty() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefProperty");
		testRefProperty.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public BeanPropertyString getTestRefProperty() {
		safeAccessTestRefProperty();
		return testRefProperty.getValue();
	}
	
	public Command setTestRefProperty(EditingDomain ed, BeanPropertyString value) {
		safeAccessTestRefProperty();
		return testRefProperty.setValue(ed, value);
	}
	
	public void setTestRefProperty(BeanPropertyString value) {
		safeAccessTestRefProperty();
		testRefProperty.setValue(value);
	}
	
	public BeanPropertyReference<BeanPropertyString> getTestRefPropertyBean() {
		safeAccessTestRefProperty();
		return testRefProperty;
	}
	
	
}
