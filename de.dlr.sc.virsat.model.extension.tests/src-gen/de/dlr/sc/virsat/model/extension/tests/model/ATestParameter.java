/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import javax.xml.bind.annotation.XmlElement;


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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ATestParameter extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestParameter";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_DEFAULTVALUE = "defaultValue";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestParameter() {
	}
	
	public ATestParameter(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestParameter");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestParameter");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestParameter(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: defaultValue
	// *****************************************************************
	private BeanPropertyFloat defaultValue = new BeanPropertyFloat();
	
	private void safeAccessDefaultValue() {
		if (defaultValue.getTypeInstance() == null) {
			defaultValue.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("defaultValue"));
		}
	}
	
	public Command setDefaultValue(EditingDomain ed, double value) {
		safeAccessDefaultValue();
		return this.defaultValue.setValue(ed, value);
	}
	
	public void setDefaultValue(double value) {
		safeAccessDefaultValue();
		this.defaultValue.setValue(value);
	}
	
	public double getDefaultValue() {
		safeAccessDefaultValue();
		return defaultValue.getValue();
	}
	
	public boolean isSetDefaultValue() {
		safeAccessDefaultValue();
		return defaultValue.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getDefaultValueBean() {
		safeAccessDefaultValue();
		return defaultValue;
	}
	
	
}
