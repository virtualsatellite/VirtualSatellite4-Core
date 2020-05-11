/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Attribute definition for requirements
 * 
 */	
public abstract class ARequirementAttribute extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.RequirementAttribute";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_ENUMERATION = "enumeration";
	
	// Type enumeration value names
	public static final String TYPE_Boolean_NAME = "Boolean";
	public static final String TYPE_Date_NAME = "Date";
	public static final String TYPE_Enumeration_NAME = "Enumeration";
	public static final String TYPE_Integer_NAME = "Integer";
	public static final String TYPE_Real_NAME = "Real";
	public static final String TYPE_String_NAME = "String";
	public static final String TYPE_Identifier_NAME = "Identifier";
	// Type enumeration values
	public static final String TYPE_Boolean_VALUE = "0";
	public static final String TYPE_Date_VALUE = "1";
	public static final String TYPE_Enumeration_VALUE = "2";
	public static final String TYPE_Integer_VALUE = "3";
	public static final String TYPE_Real_VALUE = "4";
	public static final String TYPE_String_VALUE = "5";
	public static final String TYPE_Identifier_VALUE = "6";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARequirementAttribute() {
	}
	
	public ARequirementAttribute(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "RequirementAttribute");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "RequirementAttribute");
		setTypeInstance(categoryAssignement);
	}
	
	public ARequirementAttribute(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: type
	// *****************************************************************
	private BeanPropertyEnum type = new BeanPropertyEnum();
	
	private void safeAccessType() {
		if (type.getTypeInstance() == null) {
			type.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("type"));
		}
	}
	
	public Command setType(EditingDomain ed, String value) {
		safeAccessType();
		return this.type.setValue(ed, value);
	}
	
	public void setType(String value) {
		safeAccessType();
		this.type.setValue(value);
	}
	
	public String getType() {
		safeAccessType();
		return type.getValue();
	}
	
	public double getTypeEnum() {
		safeAccessType();
		return type.getEnumValue();
	}
	
	public BeanPropertyEnum getTypeBean() {
		safeAccessType();
		return type;
	}
	
	// *****************************************************************
	// * Attribute: enumeration
	// *****************************************************************
	private EnumerationDefinition enumeration = new EnumerationDefinition();
	
	private void safeAccessEnumeration() {
		if (enumeration.getTypeInstance() == null) {
			ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("enumeration");
			enumeration.setTypeInstance(propertyInstance.getTypeInstance());
		}
	}
	
	public EnumerationDefinition getEnumeration () {
		safeAccessEnumeration();
		return enumeration;
	}
	
	
}
