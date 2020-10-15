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
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
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
public abstract class AVerificationType extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.VerificationType";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_VERDESCRIPTION = "verDescription";
	public static final String PROPERTY_VERTYPE = "verType";
	
	// VerType enumeration value names
	public static final String VERTYPE_NotApplicable_NAME = "NotApplicable";
	public static final String VERTYPE_Analysis_NAME = "Analysis";
	public static final String VERTYPE_Inspection_NAME = "Inspection";
	public static final String VERTYPE_Demonstration_NAME = "Demonstration";
	public static final String VERTYPE_Test_NAME = "Test";
	// VerType enumeration values
	public static final String VERTYPE_NotApplicable_VALUE = "0";
	public static final String VERTYPE_Analysis_VALUE = "1";
	public static final String VERTYPE_Inspection_VALUE = "2";
	public static final String VERTYPE_Demonstration_VALUE = "3";
	public static final String VERTYPE_Test_VALUE = "4";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AVerificationType() {
	}
	
	public AVerificationType(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "VerificationType");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "VerificationType");
		setTypeInstance(categoryAssignement);
	}
	
	public AVerificationType(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: verDescription
	// *****************************************************************
	private BeanPropertyString verDescription = new BeanPropertyString();
	
	private void safeAccessVerDescription() {
		if (verDescription.getTypeInstance() == null) {
			verDescription.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("verDescription"));
		}
	}
	
	public Command setVerDescription(EditingDomain ed, String value) {
		safeAccessVerDescription();
		return this.verDescription.setValue(ed, value);
	}
	
	public void setVerDescription(String value) {
		safeAccessVerDescription();
		this.verDescription.setValue(value);
	}
	
	public String getVerDescription() {
		safeAccessVerDescription();
		return verDescription.getValue();
	}
	
	public BeanPropertyString getVerDescriptionBean() {
		safeAccessVerDescription();
		return verDescription;
	}
	
	// *****************************************************************
	// * Attribute: verType
	// *****************************************************************
	private BeanPropertyEnum verType = new BeanPropertyEnum();
	
	private void safeAccessVerType() {
		if (verType.getTypeInstance() == null) {
			verType.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("verType"));
		}
	}
	
	public Command setVerType(EditingDomain ed, String value) {
		safeAccessVerType();
		return this.verType.setValue(ed, value);
	}
	
	public void setVerType(String value) {
		safeAccessVerType();
		this.verType.setValue(value);
	}
	
	public String getVerType() {
		safeAccessVerType();
		return verType.getValue();
	}
	
	public double getVerTypeEnum() {
		safeAccessVerType();
		return verType.getEnumValue();
	}
	
	public BeanPropertyEnum getVerTypeBean() {
		safeAccessVerType();
		return verType;
	}
	
	
}
