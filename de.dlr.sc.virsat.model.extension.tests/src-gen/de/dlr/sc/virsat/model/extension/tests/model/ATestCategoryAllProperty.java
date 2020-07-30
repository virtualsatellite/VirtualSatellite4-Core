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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.util.URI;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
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
 * TestCategory for testing all property Types once
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ATestCategoryAllProperty extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestCategoryAllProperty";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTSTRING = "testString";
	public static final String PROPERTY_TESTINT = "testInt";
	public static final String PROPERTY_TESTFLOAT = "testFloat";
	public static final String PROPERTY_TESTBOOL = "testBool";
	public static final String PROPERTY_TESTRESOURCE = "testResource";
	public static final String PROPERTY_TESTENUM = "testEnum";
	
	// TestEnum enumeration value names
	public static final String TESTENUM_LOW_NAME = "LOW";
	public static final String TESTENUM_MEDIUM_NAME = "MEDIUM";
	public static final String TESTENUM_HIGH_NAME = "HIGH";
	public static final String TESTENUM_INCREDIBLE_NAME = "INCREDIBLE";
	// TestEnum enumeration values
	public static final String TESTENUM_LOW_VALUE = "15";
	public static final String TESTENUM_MEDIUM_VALUE = "20";
	public static final String TESTENUM_HIGH_VALUE = "25";
	public static final String TESTENUM_INCREDIBLE_VALUE = "30";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryAllProperty() {
	}
	
	public ATestCategoryAllProperty(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryAllProperty");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryAllProperty");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryAllProperty(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: testString
	// *****************************************************************
	private BeanPropertyString testString = new BeanPropertyString();
	
	private void safeAccessTestString() {
		if (testString.getTypeInstance() == null) {
			testString.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("testString"));
		}
	}
	
	public Command setTestString(EditingDomain ed, String value) {
		safeAccessTestString();
		return this.testString.setValue(ed, value);
	}
	
	public void setTestString(String value) {
		safeAccessTestString();
		this.testString.setValue(value);
	}
	
	public String getTestString() {
		safeAccessTestString();
		return testString.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getTestStringBean() {
		safeAccessTestString();
		return testString;
	}
	
	// *****************************************************************
	// * Attribute: testInt
	// *****************************************************************
	private BeanPropertyInt testInt = new BeanPropertyInt();
	
	private void safeAccessTestInt() {
		if (testInt.getTypeInstance() == null) {
			testInt.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("testInt"));
		}
	}
	
	public Command setTestInt(EditingDomain ed, long value) {
		safeAccessTestInt();
		return this.testInt.setValue(ed, value);
	}
	
	public void setTestInt(long value) {
		safeAccessTestInt();
		this.testInt.setValue(value);
	}
	
	public long getTestInt() {
		safeAccessTestInt();
		return testInt.getValue();
	}
	
	public boolean isSetTestInt() {
		safeAccessTestInt();
		return testInt.isSet();
	}
	
	@XmlElement
	public BeanPropertyInt getTestIntBean() {
		safeAccessTestInt();
		return testInt;
	}
	
	// *****************************************************************
	// * Attribute: testFloat
	// *****************************************************************
	private BeanPropertyFloat testFloat = new BeanPropertyFloat();
	
	private void safeAccessTestFloat() {
		if (testFloat.getTypeInstance() == null) {
			testFloat.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("testFloat"));
		}
	}
	
	public Command setTestFloat(EditingDomain ed, double value) {
		safeAccessTestFloat();
		return this.testFloat.setValue(ed, value);
	}
	
	public void setTestFloat(double value) {
		safeAccessTestFloat();
		this.testFloat.setValue(value);
	}
	
	public double getTestFloat() {
		safeAccessTestFloat();
		return testFloat.getValue();
	}
	
	public boolean isSetTestFloat() {
		safeAccessTestFloat();
		return testFloat.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getTestFloatBean() {
		safeAccessTestFloat();
		return testFloat;
	}
	
	// *****************************************************************
	// * Attribute: testBool
	// *****************************************************************
	private BeanPropertyBoolean testBool = new BeanPropertyBoolean();
	
	private void safeAccessTestBool() {
		if (testBool.getTypeInstance() == null) {
			testBool.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("testBool"));
		}
	}
	
	public Command setTestBool(EditingDomain ed, boolean value) {
		safeAccessTestBool();
		return this.testBool.setValue(ed, value);
	}
	
	public void setTestBool(boolean value) {
		safeAccessTestBool();
		this.testBool.setValue(value);
	}
	
	public boolean getTestBool() {
		safeAccessTestBool();
		return testBool.getValue();
	}
	
	@XmlElement
	public BeanPropertyBoolean getTestBoolBean() {
		safeAccessTestBool();
		return testBool;
	}
	
	// *****************************************************************
	// * Attribute: testResource
	// *****************************************************************
	private BeanPropertyResource testResource = new BeanPropertyResource();
	
	private void safeAccessTestResource() {
		if (testResource.getTypeInstance() == null) {
			testResource.setTypeInstance((ResourcePropertyInstance) helper.getPropertyInstance("testResource"));
		}
	}
	
	public Command setTestResource(EditingDomain ed, URI value) {
		safeAccessTestResource();
		return this.testResource.setValue(ed, value);
	}
	
	public void setTestResource(URI value) {
		safeAccessTestResource();
		this.testResource.setValue(value);
	}
	
	public URI getTestResource() {
		safeAccessTestResource();
		return testResource.getValue();
	}
	
	@XmlElement
	public BeanPropertyResource getTestResourceBean() {
		safeAccessTestResource();
		return testResource;
	}
	
	// *****************************************************************
	// * Attribute: testEnum
	// *****************************************************************
	private BeanPropertyEnum testEnum = new BeanPropertyEnum();
	
	private void safeAccessTestEnum() {
		if (testEnum.getTypeInstance() == null) {
			testEnum.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("testEnum"));
		}
	}
	
	public Command setTestEnum(EditingDomain ed, String value) {
		safeAccessTestEnum();
		return this.testEnum.setValue(ed, value);
	}
	
	public void setTestEnum(String value) {
		safeAccessTestEnum();
		this.testEnum.setValue(value);
	}
	
	public String getTestEnum() {
		safeAccessTestEnum();
		return testEnum.getValue();
	}
	
	public double getTestEnumEnum() {
		safeAccessTestEnum();
		return testEnum.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getTestEnumBean() {
		safeAccessTestEnum();
		return testEnum;
	}
	
	
}
