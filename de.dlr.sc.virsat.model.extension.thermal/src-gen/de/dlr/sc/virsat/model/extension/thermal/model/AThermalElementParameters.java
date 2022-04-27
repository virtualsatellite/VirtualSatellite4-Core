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


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Thermal conductivity of the element
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AThermalElementParameters extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.ThermalElementParameters";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_PREDEFINEDMATERIAL = "predefinedMaterial";
	public static final String PROPERTY_INITIALTEMPERATURE = "initialTemperature";
	public static final String PROPERTY_POWERBALANCE = "powerBalance";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AThermalElementParameters() {
	}
	
	public AThermalElementParameters(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ThermalElementParameters");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ThermalElementParameters");
		setTypeInstance(categoryAssignement);
	}
	
	public AThermalElementParameters(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: predefinedMaterial
	// *****************************************************************
	private BeanPropertyReference<Material> predefinedMaterial = new BeanPropertyReference<>();
	
	private void safeAccessPredefinedMaterial() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("predefinedMaterial");
		predefinedMaterial.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public Material getPredefinedMaterial() {
		safeAccessPredefinedMaterial();
		return predefinedMaterial.getValue();
	}
	
	public Command setPredefinedMaterial(EditingDomain ed, Material value) {
		safeAccessPredefinedMaterial();
		return predefinedMaterial.setValue(ed, value);
	}
	
	public void setPredefinedMaterial(Material value) {
		safeAccessPredefinedMaterial();
		predefinedMaterial.setValue(value);
	}
	
	public BeanPropertyReference<Material> getPredefinedMaterialBean() {
		safeAccessPredefinedMaterial();
		return predefinedMaterial;
	}
	
	// *****************************************************************
	// * Attribute: initialTemperature
	// *****************************************************************
	private BeanPropertyFloat initialTemperature = new BeanPropertyFloat();
	
	private void safeAccessInitialTemperature() {
		if (initialTemperature.getTypeInstance() == null) {
			initialTemperature.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("initialTemperature"));
		}
	}
	
	public Command setInitialTemperature(EditingDomain ed, double value) {
		safeAccessInitialTemperature();
		return this.initialTemperature.setValue(ed, value);
	}
	
	public void setInitialTemperature(double value) {
		safeAccessInitialTemperature();
		this.initialTemperature.setValue(value);
	}
	
	public double getInitialTemperature() {
		safeAccessInitialTemperature();
		return initialTemperature.getValue();
	}
	
	public boolean isSetInitialTemperature() {
		safeAccessInitialTemperature();
		return initialTemperature.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getInitialTemperatureBean() {
		safeAccessInitialTemperature();
		return initialTemperature;
	}
	
	// *****************************************************************
	// * Attribute: powerBalance
	// *****************************************************************
	private BeanPropertyFloat powerBalance = new BeanPropertyFloat();
	
	private void safeAccessPowerBalance() {
		if (powerBalance.getTypeInstance() == null) {
			powerBalance.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("powerBalance"));
		}
	}
	
	public Command setPowerBalance(EditingDomain ed, double value) {
		safeAccessPowerBalance();
		return this.powerBalance.setValue(ed, value);
	}
	
	public void setPowerBalance(double value) {
		safeAccessPowerBalance();
		this.powerBalance.setValue(value);
	}
	
	public double getPowerBalance() {
		safeAccessPowerBalance();
		return powerBalance.getValue();
	}
	
	public boolean isSetPowerBalance() {
		safeAccessPowerBalance();
		return powerBalance.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getPowerBalanceBean() {
		safeAccessPowerBalance();
		return powerBalance;
	}
	
	
}
