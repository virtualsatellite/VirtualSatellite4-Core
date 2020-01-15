/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.VoltageDefinition;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Describes a Power interface type with nominal voltages
 * 
 */	
public abstract class APowerInterfaceType extends InterfaceType implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.funcelectrical.PowerInterfaceType";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_VOLTAGES = "voltages";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public APowerInterfaceType() {
	}
	
	public APowerInterfaceType(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "PowerInterfaceType");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "PowerInterfaceType");
		setTypeInstance(categoryAssignement);
	}
	
	public APowerInterfaceType(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: voltages
	// *****************************************************************
	private IBeanList<VoltageDefinition> voltages = new TypeSafeComposedPropertyInstanceList<>(VoltageDefinition.class);
	
	private void safeAccessVoltages() {
		if (voltages.getArrayInstance() == null) {
			voltages.setArrayInstance((ArrayInstance) helper.getPropertyInstance("voltages"));
		}
	}
	
	public IBeanList<VoltageDefinition> getVoltages() {
		safeAccessVoltages();
		return voltages;
	}
	
	
}
