/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.power.model;

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
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
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
 * Definition of the equipment power
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class APowerEquipment extends APowerParameters implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.budget.power.PowerEquipment";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_POWERVALUES = "powerValues";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public APowerEquipment() {
	}
	
	public APowerEquipment(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "PowerEquipment");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "PowerEquipment");
		setTypeInstance(categoryAssignement);
	}
	
	public APowerEquipment(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: powerValues
	// *****************************************************************
	private IBeanList<PowerState> powerValues = new TypeSafeComposedPropertyInstanceList<>(PowerState.class);
	
	private void safeAccessPowerValues() {
		if (powerValues.getArrayInstance() == null) {
			powerValues.setArrayInstance((ArrayInstance) helper.getPropertyInstance("powerValues"));
		}
	}
	
	public IBeanList<PowerState> getPowerValues() {
		safeAccessPowerValues();
		return powerValues;
	}
	
	private IBeanList<BeanPropertyComposed<PowerState>> powerValuesBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessPowerValuesBean() {
		if (powerValuesBean.getArrayInstance() == null) {
			powerValuesBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("powerValues"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<PowerState>> getPowerValuesBean() {
		safeAccessPowerValuesBean();
		return powerValuesBean;
	}
	
	
}
