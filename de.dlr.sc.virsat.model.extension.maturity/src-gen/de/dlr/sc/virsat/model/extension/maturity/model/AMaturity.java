/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.maturity.model;

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
 * Category to describe the maturity of a part or product
 * 
 */	
public abstract class AMaturity extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.maturity.Maturity";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_LEVEL = "level";
	public static final String PROPERTY_TRL = "trl";
	
	// Level enumeration value names
	public static final String LEVEL_READY_TO_BE_USED_NAME = "READY_TO_BE_USED";
	public static final String LEVEL_HAS_TO_BE_MODIFIED_NAME = "HAS_TO_BE_MODIFIED";
	public static final String LEVEL_HAS_TO_BE_DEVELOPED_NAME = "HAS_TO_BE_DEVELOPED";
	// Level enumeration values
	public static final String LEVEL_READY_TO_BE_USED_VALUE = "1";
	public static final String LEVEL_HAS_TO_BE_MODIFIED_VALUE = "2";
	public static final String LEVEL_HAS_TO_BE_DEVELOPED_VALUE = "3";
	// Trl enumeration value names
	public static final String TRL_TRL_1_NAME = "TRL_1";
	public static final String TRL_TRL_2_NAME = "TRL_2";
	public static final String TRL_TRL_3_NAME = "TRL_3";
	public static final String TRL_TRL_4_NAME = "TRL_4";
	public static final String TRL_TRL_5_NAME = "TRL_5";
	public static final String TRL_TRL_6_NAME = "TRL_6";
	public static final String TRL_TRL_7_NAME = "TRL_7";
	public static final String TRL_TRL_8_NAME = "TRL_8";
	public static final String TRL_TRL_9_NAME = "TRL_9";
	// Trl enumeration values
	public static final String TRL_TRL_1_VALUE = "1";
	public static final String TRL_TRL_2_VALUE = "2";
	public static final String TRL_TRL_3_VALUE = "3";
	public static final String TRL_TRL_4_VALUE = "4";
	public static final String TRL_TRL_5_VALUE = "5";
	public static final String TRL_TRL_6_VALUE = "6";
	public static final String TRL_TRL_7_VALUE = "7";
	public static final String TRL_TRL_8_VALUE = "8";
	public static final String TRL_TRL_9_VALUE = "9";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AMaturity() {
	}
	
	public AMaturity(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "Maturity");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "Maturity");
		setTypeInstance(categoryAssignement);
	}
	
	public AMaturity(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: level
	// *****************************************************************
	private BeanPropertyEnum level = new BeanPropertyEnum();
	
	private void safeAccessLevel() {
		if (level.getTypeInstance() == null) {
			level.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("level"));
		}
	}
	
	public Command setLevel(EditingDomain ed, String value) {
		safeAccessLevel();
		return this.level.setValue(ed, value);
	}
	
	public void setLevel(String value) {
		safeAccessLevel();
		this.level.setValue(value);
	}
	
	public String getLevel() {
		safeAccessLevel();
		return level.getValue();
	}
	
	public double getLevelEnum() {
		safeAccessLevel();
		return level.getEnumValue();
	}
	
	public BeanPropertyEnum getLevelBean() {
		safeAccessLevel();
		return level;
	}
	
	// *****************************************************************
	// * Attribute: trl
	// *****************************************************************
	private BeanPropertyEnum trl = new BeanPropertyEnum();
	
	private void safeAccessTrl() {
		if (trl.getTypeInstance() == null) {
			trl.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("trl"));
		}
	}
	
	public Command setTrl(EditingDomain ed, String value) {
		safeAccessTrl();
		return this.trl.setValue(ed, value);
	}
	
	public void setTrl(String value) {
		safeAccessTrl();
		this.trl.setValue(value);
	}
	
	public String getTrl() {
		safeAccessTrl();
		return trl.getValue();
	}
	
	public double getTrlEnum() {
		safeAccessTrl();
		return trl.getEnumValue();
	}
	
	public BeanPropertyEnum getTrlBean() {
		safeAccessTrl();
		return trl;
	}
	
	
}
