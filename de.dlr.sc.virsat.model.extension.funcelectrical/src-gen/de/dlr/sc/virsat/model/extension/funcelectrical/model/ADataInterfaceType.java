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
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Describes a Data communication interface type
 * 
 */	
public abstract class ADataInterfaceType extends InterfaceType implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.funcelectrical.DataInterfaceType";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_ELECTRICALSTANDARD = "electricalStandard";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ADataInterfaceType() {
	}
	
	public ADataInterfaceType(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "DataInterfaceType");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "DataInterfaceType");
		setTypeInstance(categoryAssignement);
	}
	
	public ADataInterfaceType(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: electricalStandard
	// *****************************************************************
	private BeanPropertyString electricalStandard = new BeanPropertyString();
	
	private void safeAccessElectricalStandard() {
		if (electricalStandard.getTypeInstance() == null) {
			electricalStandard.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("electricalStandard"));
		}
	}
	
	public Command setElectricalStandard(EditingDomain ed, String value) {
		safeAccessElectricalStandard();
		return this.electricalStandard.setValue(ed, value);
	}
	
	public void setElectricalStandard(String value) {
		safeAccessElectricalStandard();
		this.electricalStandard.setValue(value);
	}
	
	public String getElectricalStandard() {
		safeAccessElectricalStandard();
		return electricalStandard.getValue();
	}
	
	public BeanPropertyString getElectricalStandardBean() {
		safeAccessElectricalStandard();
		return electricalStandard;
	}
	
	
}
