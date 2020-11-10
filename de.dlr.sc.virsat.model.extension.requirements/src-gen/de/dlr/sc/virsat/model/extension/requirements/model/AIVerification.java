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
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
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
public abstract class AIVerification extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.IVerification";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_STATUS = "status";
	
	// Status enumeration value names
	public static final String STATUS_Open_NAME = "Open";
	public static final String STATUS_FullyCompliant_NAME = "FullyCompliant";
	public static final String STATUS_PartialCompliant_NAME = "PartialCompliant";
	public static final String STATUS_NonCompliant_NAME = "NonCompliant";
	public static final String STATUS_NotApplicable_NAME = "NotApplicable";
	// Status enumeration values
	public static final String STATUS_Open_VALUE = "0";
	public static final String STATUS_FullyCompliant_VALUE = "1";
	public static final String STATUS_PartialCompliant_VALUE = "2";
	public static final String STATUS_NonCompliant_VALUE = "3";
	public static final String STATUS_NotApplicable_VALUE = "4";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AIVerification() {
	}
	
	public AIVerification(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "IVerification");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "IVerification");
		setTypeInstance(categoryAssignement);
	}
	
	public AIVerification(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: status
	// *****************************************************************
	private BeanPropertyEnum status = new BeanPropertyEnum();
	
	private void safeAccessStatus() {
		if (status.getTypeInstance() == null) {
			status.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("status"));
		}
	}
	
	public Command setStatus(EditingDomain ed, String value) {
		safeAccessStatus();
		return this.status.setValue(ed, value);
	}
	
	public void setStatus(String value) {
		safeAccessStatus();
		this.status.setValue(value);
	}
	
	public String getStatus() {
		safeAccessStatus();
		return status.getValue();
	}
	
	public double getStatusEnum() {
		safeAccessStatus();
		return status.getEnumValue();
	}
	
	public BeanPropertyEnum getStatusBean() {
		safeAccessStatus();
		return status;
	}
	
	
}
