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
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
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
public abstract class AAttributeValue extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.AttributeValue";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_ATTTYPE = "attType";
	public static final String PROPERTY_VALUE = "value";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AAttributeValue() {
	}
	
	public AAttributeValue(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "AttributeValue");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "AttributeValue");
		setTypeInstance(categoryAssignement);
	}
	
	public AAttributeValue(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: attType
	// *****************************************************************
	private BeanPropertyReference<RequirementAttribute> attType = new BeanPropertyReference<>();
	
	private void safeAccessAttType() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("attType");
		attType.setTypeInstance(propertyInstance);
	}
	
	public RequirementAttribute getAttType() {
		safeAccessAttType();
		return attType.getValue();
	}
	
	public Command setAttType(EditingDomain ed, RequirementAttribute value) {
		safeAccessAttType();
		return attType.setValue(ed, value);
	}
	
	public void setAttType(RequirementAttribute value) {
		safeAccessAttType();
		attType.setValue(value);
	}
	
	public BeanPropertyReference<RequirementAttribute> getAttTypeBean() {
		safeAccessAttType();
		return attType;
	}
	
	// *****************************************************************
	// * Attribute: value
	// *****************************************************************
	private BeanPropertyString value = new BeanPropertyString();
	
	private void safeAccessValue() {
		if (value.getTypeInstance() == null) {
			value.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("value"));
		}
	}
	
	public Command setValue(EditingDomain ed, String value) {
		safeAccessValue();
		return this.value.setValue(ed, value);
	}
	
	public void setValue(String value) {
		safeAccessValue();
		this.value.setValue(value);
	}
	
	public String getValue() {
		safeAccessValue();
		return value.getValue();
	}
	
	public BeanPropertyString getValueBean() {
		safeAccessValue();
		return value;
	}
	
	
}
