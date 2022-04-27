/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.model;

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
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 * Definition the equipment cost of the type
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ACostTableEntry extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.budget.cost.CostTableEntry";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_COST = "cost";
	public static final String PROPERTY_COSTWITHMARGIN = "costWithMargin";
	public static final String PROPERTY_COSTMARGIN = "costMargin";
	public static final String PROPERTY_MARGIN = "margin";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ACostTableEntry() {
	}
	
	public ACostTableEntry(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "CostTableEntry");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "CostTableEntry");
		setTypeInstance(categoryAssignement);
	}
	
	public ACostTableEntry(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: type
	// *****************************************************************
	private BeanPropertyReference<CostType> type = new BeanPropertyReference<>();
	
	private void safeAccessType() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("type");
		type.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public CostType getType() {
		safeAccessType();
		return type.getValue();
	}
	
	public Command setType(EditingDomain ed, CostType value) {
		safeAccessType();
		return type.setValue(ed, value);
	}
	
	public void setType(CostType value) {
		safeAccessType();
		type.setValue(value);
	}
	
	public BeanPropertyReference<CostType> getTypeBean() {
		safeAccessType();
		return type;
	}
	
	// *****************************************************************
	// * Attribute: cost
	// *****************************************************************
	private BeanPropertyFloat cost = new BeanPropertyFloat();
	
	private void safeAccessCost() {
		if (cost.getTypeInstance() == null) {
			cost.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("cost"));
		}
	}
	
	public Command setCost(EditingDomain ed, double value) {
		safeAccessCost();
		return this.cost.setValue(ed, value);
	}
	
	public void setCost(double value) {
		safeAccessCost();
		this.cost.setValue(value);
	}
	
	public double getCost() {
		safeAccessCost();
		return cost.getValue();
	}
	
	public boolean isSetCost() {
		safeAccessCost();
		return cost.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getCostBean() {
		safeAccessCost();
		return cost;
	}
	
	// *****************************************************************
	// * Attribute: costWithMargin
	// *****************************************************************
	private BeanPropertyFloat costWithMargin = new BeanPropertyFloat();
	
	private void safeAccessCostWithMargin() {
		if (costWithMargin.getTypeInstance() == null) {
			costWithMargin.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("costWithMargin"));
		}
	}
	
	public Command setCostWithMargin(EditingDomain ed, double value) {
		safeAccessCostWithMargin();
		return this.costWithMargin.setValue(ed, value);
	}
	
	public void setCostWithMargin(double value) {
		safeAccessCostWithMargin();
		this.costWithMargin.setValue(value);
	}
	
	public double getCostWithMargin() {
		safeAccessCostWithMargin();
		return costWithMargin.getValue();
	}
	
	public boolean isSetCostWithMargin() {
		safeAccessCostWithMargin();
		return costWithMargin.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getCostWithMarginBean() {
		safeAccessCostWithMargin();
		return costWithMargin;
	}
	
	// *****************************************************************
	// * Attribute: costMargin
	// *****************************************************************
	private BeanPropertyFloat costMargin = new BeanPropertyFloat();
	
	private void safeAccessCostMargin() {
		if (costMargin.getTypeInstance() == null) {
			costMargin.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("costMargin"));
		}
	}
	
	public Command setCostMargin(EditingDomain ed, double value) {
		safeAccessCostMargin();
		return this.costMargin.setValue(ed, value);
	}
	
	public void setCostMargin(double value) {
		safeAccessCostMargin();
		this.costMargin.setValue(value);
	}
	
	public double getCostMargin() {
		safeAccessCostMargin();
		return costMargin.getValue();
	}
	
	public boolean isSetCostMargin() {
		safeAccessCostMargin();
		return costMargin.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getCostMarginBean() {
		safeAccessCostMargin();
		return costMargin;
	}
	
	// *****************************************************************
	// * Attribute: margin
	// *****************************************************************
	private BeanPropertyFloat margin = new BeanPropertyFloat();
	
	private void safeAccessMargin() {
		if (margin.getTypeInstance() == null) {
			margin.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("margin"));
		}
	}
	
	public Command setMargin(EditingDomain ed, double value) {
		safeAccessMargin();
		return this.margin.setValue(ed, value);
	}
	
	public void setMargin(double value) {
		safeAccessMargin();
		this.margin.setValue(value);
	}
	
	public double getMargin() {
		safeAccessMargin();
		return margin.getValue();
	}
	
	public boolean isSetMargin() {
		safeAccessMargin();
		return margin.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMarginBean() {
		safeAccessMargin();
		return margin;
	}
	
	
}
