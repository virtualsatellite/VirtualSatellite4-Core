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
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
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
public abstract class ARequirementsConfiguration extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.RequirementsConfiguration";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_FILENAME = "fileName";
	public static final String PROPERTY_TYPEDEFINITIONS = "typeDefinitions";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARequirementsConfiguration() {
	}
	
	public ARequirementsConfiguration(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "RequirementsConfiguration");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "RequirementsConfiguration");
		setTypeInstance(categoryAssignement);
	}
	
	public ARequirementsConfiguration(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: fileName
	// *****************************************************************
	private BeanPropertyString fileName = new BeanPropertyString();
	
	private void safeAccessFileName() {
		if (fileName.getTypeInstance() == null) {
			fileName.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("fileName"));
		}
	}
	
	public Command setFileName(EditingDomain ed, String value) {
		safeAccessFileName();
		return this.fileName.setValue(ed, value);
	}
	
	public void setFileName(String value) {
		safeAccessFileName();
		this.fileName.setValue(value);
	}
	
	public String getFileName() {
		safeAccessFileName();
		return fileName.getValue();
	}
	
	public BeanPropertyString getFileNameBean() {
		safeAccessFileName();
		return fileName;
	}
	
	// *****************************************************************
	// * Array Attribute: typeDefinitions
	// *****************************************************************
	private IBeanList<RequirementType> typeDefinitions = new TypeSafeComposedPropertyInstanceList<>(RequirementType.class);
	
	private void safeAccessTypeDefinitions() {
		if (typeDefinitions.getArrayInstance() == null) {
			typeDefinitions.setArrayInstance((ArrayInstance) helper.getPropertyInstance("typeDefinitions"));
		}
	}
	
	public IBeanList<RequirementType> getTypeDefinitions() {
		safeAccessTypeDefinitions();
		return typeDefinitions;
	}
	
	
}
