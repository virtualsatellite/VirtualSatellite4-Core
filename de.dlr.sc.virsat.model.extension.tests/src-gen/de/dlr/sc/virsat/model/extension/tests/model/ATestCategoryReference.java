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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.json.ReferenceAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;


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
	private BeanPropertyReference<TestCategoryAllProperty> testRefCategory = new BeanPropertyReference<>();
	
	private void safeAccessTestRefCategory() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("testRefCategory");
		testRefCategory.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable=true)
	@XmlJavaTypeAdapter(ReferenceAdapter.class)
	public TestCategoryAllProperty getTestRefCategory() {
		safeAccessTestRefCategory();
		return testRefCategory.getValue();
	}
	
	public Command setTestRefCategory(EditingDomain ed, TestCategoryAllProperty value) {
		safeAccessTestRefCategory();
		return testRefCategory.setValue(ed, value);
	}
	
	public void setTestRefCategory(TestCategoryAllProperty value) {
		safeAccessTestRefCategory();
		testRefCategory.setValue(value);
	}
	
//	@XmlElement(nillable=true)
	public BeanPropertyReference<TestCategoryAllProperty> getTestRefCategoryBean() {
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
	
	@XmlElement(nillable=true)
	@XmlJavaTypeAdapter(ReferenceAdapter.class)
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
	
//	@XmlElement(nillable=true)
	public BeanPropertyReference<BeanPropertyString> getTestRefPropertyBean() {
		safeAccessTestRefProperty();
		return testRefProperty;
	}
	
	
}
