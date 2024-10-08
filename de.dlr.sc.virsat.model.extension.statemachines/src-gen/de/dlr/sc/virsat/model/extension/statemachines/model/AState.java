/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * State of the component at a given time
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AState extends StateMachine implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.statemachines.State";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_DETAIL = "detail";
	public static final String PROPERTY_MAXTIME = "maxTime";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AState() {
	}
	
	public AState(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "State");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "State");
		setTypeInstance(categoryAssignement);
	}
	
	public AState(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: detail
	// *****************************************************************
	private BeanPropertyString detail = new BeanPropertyString();
	
	private void safeAccessDetail() {
		if (detail.getTypeInstance() == null) {
			detail.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("detail"));
		}
	}
	
	public Command setDetail(EditingDomain ed, String value) {
		safeAccessDetail();
		return this.detail.setValue(ed, value);
	}
	
	public void setDetail(String value) {
		safeAccessDetail();
		this.detail.setValue(value);
	}
	
	public String getDetail() {
		safeAccessDetail();
		return detail.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getDetailBean() {
		safeAccessDetail();
		return detail;
	}
	
	// *****************************************************************
	// * Attribute: maxTime
	// *****************************************************************
	private BeanPropertyFloat maxTime = new BeanPropertyFloat();
	
	private void safeAccessMaxTime() {
		if (maxTime.getTypeInstance() == null) {
			maxTime.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("maxTime"));
		}
	}
	
	public Command setMaxTime(EditingDomain ed, double value) {
		safeAccessMaxTime();
		return this.maxTime.setValue(ed, value);
	}
	
	public void setMaxTime(double value) {
		safeAccessMaxTime();
		this.maxTime.setValue(value);
	}
	
	public double getMaxTime() {
		safeAccessMaxTime();
		return maxTime.getValue();
	}
	
	public boolean isSetMaxTime() {
		safeAccessMaxTime();
		return maxTime.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMaxTimeBean() {
		safeAccessMaxTime();
		return maxTime;
	}
	
	
}
