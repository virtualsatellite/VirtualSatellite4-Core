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
import javax.xml.bind.annotation.XmlElement;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Fixed voltage defining a nominal value
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AFixedVoltageDefinition extends VoltageDefinition implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.funcelectrical.FixedVoltageDefinition";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_NOMINALVALUE = "nominalValue";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AFixedVoltageDefinition() {
	}
	
	public AFixedVoltageDefinition(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "FixedVoltageDefinition");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "FixedVoltageDefinition");
		setTypeInstance(categoryAssignement);
	}
	
	public AFixedVoltageDefinition(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: nominalValue
	// *****************************************************************
	private BeanPropertyFloat nominalValue = new BeanPropertyFloat();
	
	private void safeAccessNominalValue() {
		if (nominalValue.getTypeInstance() == null) {
			nominalValue.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("nominalValue"));
		}
	}
	
	public Command setNominalValue(EditingDomain ed, double value) {
		safeAccessNominalValue();
		return this.nominalValue.setValue(ed, value);
	}
	
	public void setNominalValue(double value) {
		safeAccessNominalValue();
		this.nominalValue.setValue(value);
	}
	
	public double getNominalValue() {
		safeAccessNominalValue();
		return nominalValue.getValue();
	}
	
	public boolean isSetNominalValue() {
		safeAccessNominalValue();
		return nominalValue.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getNominalValueBean() {
		safeAccessNominalValue();
		return nominalValue;
	}
	
	
}
