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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Here the results of the analyses are displayed.
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AComponentResult extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.ComponentResult";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_COMPONENT = "component";
	public static final String PROPERTY_MAXTEMPERATURE = "maxTemperature";
	public static final String PROPERTY_TIMESTEPMAXTEMPERATURE = "timeStepMaxTemperature";
	public static final String PROPERTY_MINTEMPERATURE = "minTemperature";
	public static final String PROPERTY_TIMESTEPMINTEMPERATURE = "timeStepMinTemperature";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AComponentResult() {
	}
	
	public AComponentResult(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ComponentResult");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ComponentResult");
		setTypeInstance(categoryAssignement);
	}
	
	public AComponentResult(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: component
	// *****************************************************************
	private BeanPropertyReference<ThermalElementParameters> component = new BeanPropertyReference<>();
	
	private void safeAccessComponent() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("component");
		component.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public ThermalElementParameters getComponent() {
		safeAccessComponent();
		return component.getValue();
	}
	
	public Command setComponent(EditingDomain ed, ThermalElementParameters value) {
		safeAccessComponent();
		return component.setValue(ed, value);
	}
	
	public void setComponent(ThermalElementParameters value) {
		safeAccessComponent();
		component.setValue(value);
	}
	
	public BeanPropertyReference<ThermalElementParameters> getComponentBean() {
		safeAccessComponent();
		return component;
	}
	
	// *****************************************************************
	// * Attribute: maxTemperature
	// *****************************************************************
	private BeanPropertyFloat maxTemperature = new BeanPropertyFloat();
	
	private void safeAccessMaxTemperature() {
		if (maxTemperature.getTypeInstance() == null) {
			maxTemperature.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("maxTemperature"));
		}
	}
	
	public Command setMaxTemperature(EditingDomain ed, double value) {
		safeAccessMaxTemperature();
		return this.maxTemperature.setValue(ed, value);
	}
	
	public void setMaxTemperature(double value) {
		safeAccessMaxTemperature();
		this.maxTemperature.setValue(value);
	}
	
	public double getMaxTemperature() {
		safeAccessMaxTemperature();
		return maxTemperature.getValue();
	}
	
	public boolean isSetMaxTemperature() {
		safeAccessMaxTemperature();
		return maxTemperature.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMaxTemperatureBean() {
		safeAccessMaxTemperature();
		return maxTemperature;
	}
	
	// *****************************************************************
	// * Attribute: timeStepMaxTemperature
	// *****************************************************************
	private BeanPropertyInt timeStepMaxTemperature = new BeanPropertyInt();
	
	private void safeAccessTimeStepMaxTemperature() {
		if (timeStepMaxTemperature.getTypeInstance() == null) {
			timeStepMaxTemperature.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("timeStepMaxTemperature"));
		}
	}
	
	public Command setTimeStepMaxTemperature(EditingDomain ed, long value) {
		safeAccessTimeStepMaxTemperature();
		return this.timeStepMaxTemperature.setValue(ed, value);
	}
	
	public void setTimeStepMaxTemperature(long value) {
		safeAccessTimeStepMaxTemperature();
		this.timeStepMaxTemperature.setValue(value);
	}
	
	public long getTimeStepMaxTemperature() {
		safeAccessTimeStepMaxTemperature();
		return timeStepMaxTemperature.getValue();
	}
	
	public boolean isSetTimeStepMaxTemperature() {
		safeAccessTimeStepMaxTemperature();
		return timeStepMaxTemperature.isSet();
	}
	
	@XmlElement
	public BeanPropertyInt getTimeStepMaxTemperatureBean() {
		safeAccessTimeStepMaxTemperature();
		return timeStepMaxTemperature;
	}
	
	// *****************************************************************
	// * Attribute: minTemperature
	// *****************************************************************
	private BeanPropertyFloat minTemperature = new BeanPropertyFloat();
	
	private void safeAccessMinTemperature() {
		if (minTemperature.getTypeInstance() == null) {
			minTemperature.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("minTemperature"));
		}
	}
	
	public Command setMinTemperature(EditingDomain ed, double value) {
		safeAccessMinTemperature();
		return this.minTemperature.setValue(ed, value);
	}
	
	public void setMinTemperature(double value) {
		safeAccessMinTemperature();
		this.minTemperature.setValue(value);
	}
	
	public double getMinTemperature() {
		safeAccessMinTemperature();
		return minTemperature.getValue();
	}
	
	public boolean isSetMinTemperature() {
		safeAccessMinTemperature();
		return minTemperature.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMinTemperatureBean() {
		safeAccessMinTemperature();
		return minTemperature;
	}
	
	// *****************************************************************
	// * Attribute: timeStepMinTemperature
	// *****************************************************************
	private BeanPropertyInt timeStepMinTemperature = new BeanPropertyInt();
	
	private void safeAccessTimeStepMinTemperature() {
		if (timeStepMinTemperature.getTypeInstance() == null) {
			timeStepMinTemperature.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("timeStepMinTemperature"));
		}
	}
	
	public Command setTimeStepMinTemperature(EditingDomain ed, long value) {
		safeAccessTimeStepMinTemperature();
		return this.timeStepMinTemperature.setValue(ed, value);
	}
	
	public void setTimeStepMinTemperature(long value) {
		safeAccessTimeStepMinTemperature();
		this.timeStepMinTemperature.setValue(value);
	}
	
	public long getTimeStepMinTemperature() {
		safeAccessTimeStepMinTemperature();
		return timeStepMinTemperature.getValue();
	}
	
	public boolean isSetTimeStepMinTemperature() {
		safeAccessTimeStepMinTemperature();
		return timeStepMinTemperature.isSet();
	}
	
	@XmlElement
	public BeanPropertyInt getTimeStepMinTemperatureBean() {
		safeAccessTimeStepMinTemperature();
		return timeStepMinTemperature;
	}
	
	
}
