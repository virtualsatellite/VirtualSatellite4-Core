/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Specification of desired element for postprocessing
 * 
 */	
public abstract class APostProcessing extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.PostProcessing";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_ELEMENT = "Element";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public APostProcessing() {
	}
	
	public APostProcessing(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "PostProcessing");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "PostProcessing");
		setTypeInstance(categoryAssignement);
	}
	
	public APostProcessing(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: Element
	// *****************************************************************
	private BeanPropertyReference<ThermalElementParameters> Element = new BeanPropertyReference<>();
	
	private void safeAccessElement() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("Element");
		Element.setTypeInstance(propertyInstance);
	}
	
	public ThermalElementParameters getElement() {
		safeAccessElement();
		return Element.getValue();
	}
	
	public Command setElement(EditingDomain ed, ThermalElementParameters value) {
		safeAccessElement();
		return Element.setValue(ed, value);
	}
	
	public void setElement(ThermalElementParameters value) {
		safeAccessElement();
		Element.setValue(value);
	}
	
	public BeanPropertyReference<ThermalElementParameters> getElementBean() {
		safeAccessElement();
		return Element;
	}
	
	
}
