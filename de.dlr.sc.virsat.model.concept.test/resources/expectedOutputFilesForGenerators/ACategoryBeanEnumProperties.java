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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
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
public abstract class ATestCategoryA extends ABeanCategoryAssignment implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "testConcept.TestCategoryA";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_ENUMPROPERTY = "enumProperty";
	public static final String PROPERTY_EMPTYENUMPROPERTY = "emptyEnumProperty";
	
	// EnumProperty enumeration value names
	public static final String ENUMPROPERTY_LITERAL1_NAME = "LITERAL1";
	public static final String ENUMPROPERTY_LITERAL2_NAME = "LITERAL2";
	public static final String ENUMPROPERTY_LITERAL3_NAME = "LITERAL3";
	// EnumProperty enumeration values
	public static final String ENUMPROPERTY_LITERAL1_VALUE = "1";
	public static final String ENUMPROPERTY_LITERAL2_VALUE = "2";
	public static final String ENUMPROPERTY_LITERAL3_VALUE = "3";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryA() {
	}
	
	public ATestCategoryA(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryA");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryA");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryA(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: enumProperty
	// *****************************************************************
	private BeanPropertyEnum enumProperty = new BeanPropertyEnum();
	
	private void safeAccessEnumProperty() {
		if (enumProperty.getTypeInstance() == null) {
			enumProperty.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("enumProperty"));
		}
	}
	
	public Command setEnumProperty(EditingDomain ed, String value) {
		safeAccessEnumProperty();
		return this.enumProperty.setValue(ed, value);
	}
	
	public void setEnumProperty(String value) {
		safeAccessEnumProperty();
		this.enumProperty.setValue(value);
	}
	
	public String getEnumProperty() {
		safeAccessEnumProperty();
		return enumProperty.getValue();
	}
	
	public double getEnumPropertyEnum() {
		safeAccessEnumProperty();
		return enumProperty.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getEnumPropertyBean() {
		safeAccessEnumProperty();
		return enumProperty;
	}
	
	// *****************************************************************
	// * Attribute: emptyEnumProperty
	// *****************************************************************
	private BeanPropertyEnum emptyEnumProperty = new BeanPropertyEnum();
	
	private void safeAccessEmptyEnumProperty() {
		if (emptyEnumProperty.getTypeInstance() == null) {
			emptyEnumProperty.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("emptyEnumProperty"));
		}
	}
	
	public Command setEmptyEnumProperty(EditingDomain ed, String value) {
		safeAccessEmptyEnumProperty();
		return this.emptyEnumProperty.setValue(ed, value);
	}
	
	public void setEmptyEnumProperty(String value) {
		safeAccessEmptyEnumProperty();
		this.emptyEnumProperty.setValue(value);
	}
	
	public String getEmptyEnumProperty() {
		safeAccessEmptyEnumProperty();
		return emptyEnumProperty.getValue();
	}
	
	public double getEmptyEnumPropertyEnum() {
		safeAccessEmptyEnumProperty();
		return emptyEnumProperty.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getEmptyEnumPropertyBean() {
		safeAccessEmptyEnumProperty();
		return emptyEnumProperty;
	}
	
	
}
