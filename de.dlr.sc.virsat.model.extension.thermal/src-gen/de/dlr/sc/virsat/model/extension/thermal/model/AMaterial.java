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
 * Model of a material
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AMaterial extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.Material";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_THERMALCONDUCTIVITY = "thermalConductivity";
	public static final String PROPERTY_ABSORPTIONCOEFFICIENT = "absorptionCoefficient";
	public static final String PROPERTY_ELEMENTEMISSIVITY = "elementEmissivity";
	public static final String PROPERTY_HEATCAPACITY = "heatCapacity";
	public static final String PROPERTY_DENSITY = "density";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AMaterial() {
	}
	
	public AMaterial(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "Material");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "Material");
		setTypeInstance(categoryAssignement);
	}
	
	public AMaterial(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: thermalConductivity
	// *****************************************************************
	private BeanPropertyFloat thermalConductivity = new BeanPropertyFloat();
	
	private void safeAccessThermalConductivity() {
		if (thermalConductivity.getTypeInstance() == null) {
			thermalConductivity.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("thermalConductivity"));
		}
	}
	
	public Command setThermalConductivity(EditingDomain ed, double value) {
		safeAccessThermalConductivity();
		return this.thermalConductivity.setValue(ed, value);
	}
	
	public void setThermalConductivity(double value) {
		safeAccessThermalConductivity();
		this.thermalConductivity.setValue(value);
	}
	
	public double getThermalConductivity() {
		safeAccessThermalConductivity();
		return thermalConductivity.getValue();
	}
	
	public boolean isSetThermalConductivity() {
		safeAccessThermalConductivity();
		return thermalConductivity.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getThermalConductivityBean() {
		safeAccessThermalConductivity();
		return thermalConductivity;
	}
	
	// *****************************************************************
	// * Attribute: absorptionCoefficient
	// *****************************************************************
	private BeanPropertyFloat absorptionCoefficient = new BeanPropertyFloat();
	
	private void safeAccessAbsorptionCoefficient() {
		if (absorptionCoefficient.getTypeInstance() == null) {
			absorptionCoefficient.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("absorptionCoefficient"));
		}
	}
	
	public Command setAbsorptionCoefficient(EditingDomain ed, double value) {
		safeAccessAbsorptionCoefficient();
		return this.absorptionCoefficient.setValue(ed, value);
	}
	
	public void setAbsorptionCoefficient(double value) {
		safeAccessAbsorptionCoefficient();
		this.absorptionCoefficient.setValue(value);
	}
	
	public double getAbsorptionCoefficient() {
		safeAccessAbsorptionCoefficient();
		return absorptionCoefficient.getValue();
	}
	
	public boolean isSetAbsorptionCoefficient() {
		safeAccessAbsorptionCoefficient();
		return absorptionCoefficient.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getAbsorptionCoefficientBean() {
		safeAccessAbsorptionCoefficient();
		return absorptionCoefficient;
	}
	
	// *****************************************************************
	// * Attribute: elementEmissivity
	// *****************************************************************
	private BeanPropertyFloat elementEmissivity = new BeanPropertyFloat();
	
	private void safeAccessElementEmissivity() {
		if (elementEmissivity.getTypeInstance() == null) {
			elementEmissivity.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("elementEmissivity"));
		}
	}
	
	public Command setElementEmissivity(EditingDomain ed, double value) {
		safeAccessElementEmissivity();
		return this.elementEmissivity.setValue(ed, value);
	}
	
	public void setElementEmissivity(double value) {
		safeAccessElementEmissivity();
		this.elementEmissivity.setValue(value);
	}
	
	public double getElementEmissivity() {
		safeAccessElementEmissivity();
		return elementEmissivity.getValue();
	}
	
	public boolean isSetElementEmissivity() {
		safeAccessElementEmissivity();
		return elementEmissivity.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getElementEmissivityBean() {
		safeAccessElementEmissivity();
		return elementEmissivity;
	}
	
	// *****************************************************************
	// * Attribute: heatCapacity
	// *****************************************************************
	private BeanPropertyFloat heatCapacity = new BeanPropertyFloat();
	
	private void safeAccessHeatCapacity() {
		if (heatCapacity.getTypeInstance() == null) {
			heatCapacity.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("heatCapacity"));
		}
	}
	
	public Command setHeatCapacity(EditingDomain ed, double value) {
		safeAccessHeatCapacity();
		return this.heatCapacity.setValue(ed, value);
	}
	
	public void setHeatCapacity(double value) {
		safeAccessHeatCapacity();
		this.heatCapacity.setValue(value);
	}
	
	public double getHeatCapacity() {
		safeAccessHeatCapacity();
		return heatCapacity.getValue();
	}
	
	public boolean isSetHeatCapacity() {
		safeAccessHeatCapacity();
		return heatCapacity.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getHeatCapacityBean() {
		safeAccessHeatCapacity();
		return heatCapacity;
	}
	
	// *****************************************************************
	// * Attribute: density
	// *****************************************************************
	private BeanPropertyFloat density = new BeanPropertyFloat();
	
	private void safeAccessDensity() {
		if (density.getTypeInstance() == null) {
			density.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("density"));
		}
	}
	
	public Command setDensity(EditingDomain ed, double value) {
		safeAccessDensity();
		return this.density.setValue(ed, value);
	}
	
	public void setDensity(double value) {
		safeAccessDensity();
		this.density.setValue(value);
	}
	
	public double getDensity() {
		safeAccessDensity();
		return density.getValue();
	}
	
	public boolean isSetDensity() {
		safeAccessDensity();
		return density.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getDensityBean() {
		safeAccessDensity();
		return density;
	}
	
	
}
