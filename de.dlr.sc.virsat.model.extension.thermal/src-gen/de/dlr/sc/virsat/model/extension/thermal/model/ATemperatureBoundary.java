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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
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
 * Modeling a temperature boundary condition
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ATemperatureBoundary extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.TemperatureBoundary";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_COMPONENT = "component";
	public static final String PROPERTY_BOUNDARYTYPE = "boundaryType";
	public static final String PROPERTY_FREECADFACENUMBERBC = "freeCADFaceNumberBC";
	public static final String PROPERTY_BOUNDARYTEMPERATURE = "boundaryTemperature";
	
	// BoundaryType enumeration value names
	public static final String BOUNDARYTYPE_Face_NAME = "Face";
	public static final String BOUNDARYTYPE_Volume_NAME = "Volume";
	// BoundaryType enumeration values
	public static final String BOUNDARYTYPE_Face_VALUE = "0";
	public static final String BOUNDARYTYPE_Volume_VALUE = "1";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATemperatureBoundary() {
	}
	
	public ATemperatureBoundary(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TemperatureBoundary");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TemperatureBoundary");
		setTypeInstance(categoryAssignement);
	}
	
	public ATemperatureBoundary(CategoryAssignment categoryAssignement) {
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
	// * Attribute: boundaryType
	// *****************************************************************
	private BeanPropertyEnum boundaryType = new BeanPropertyEnum();
	
	private void safeAccessBoundaryType() {
		if (boundaryType.getTypeInstance() == null) {
			boundaryType.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("boundaryType"));
		}
	}
	
	public Command setBoundaryType(EditingDomain ed, String value) {
		safeAccessBoundaryType();
		return this.boundaryType.setValue(ed, value);
	}
	
	public void setBoundaryType(String value) {
		safeAccessBoundaryType();
		this.boundaryType.setValue(value);
	}
	
	public String getBoundaryType() {
		safeAccessBoundaryType();
		return boundaryType.getValue();
	}
	
	public double getBoundaryTypeEnum() {
		safeAccessBoundaryType();
		return boundaryType.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getBoundaryTypeBean() {
		safeAccessBoundaryType();
		return boundaryType;
	}
	
	// *****************************************************************
	// * Attribute: freeCADFaceNumberBC
	// *****************************************************************
	private BeanPropertyInt freeCADFaceNumberBC = new BeanPropertyInt();
	
	private void safeAccessFreeCADFaceNumberBC() {
		if (freeCADFaceNumberBC.getTypeInstance() == null) {
			freeCADFaceNumberBC.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("freeCADFaceNumberBC"));
		}
	}
	
	public Command setFreeCADFaceNumberBC(EditingDomain ed, long value) {
		safeAccessFreeCADFaceNumberBC();
		return this.freeCADFaceNumberBC.setValue(ed, value);
	}
	
	public void setFreeCADFaceNumberBC(long value) {
		safeAccessFreeCADFaceNumberBC();
		this.freeCADFaceNumberBC.setValue(value);
	}
	
	public long getFreeCADFaceNumberBC() {
		safeAccessFreeCADFaceNumberBC();
		return freeCADFaceNumberBC.getValue();
	}
	
	public boolean isSetFreeCADFaceNumberBC() {
		safeAccessFreeCADFaceNumberBC();
		return freeCADFaceNumberBC.isSet();
	}
	
	@XmlElement
	public BeanPropertyInt getFreeCADFaceNumberBCBean() {
		safeAccessFreeCADFaceNumberBC();
		return freeCADFaceNumberBC;
	}
	
	// *****************************************************************
	// * Attribute: boundaryTemperature
	// *****************************************************************
	private BeanPropertyFloat boundaryTemperature = new BeanPropertyFloat();
	
	private void safeAccessBoundaryTemperature() {
		if (boundaryTemperature.getTypeInstance() == null) {
			boundaryTemperature.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("boundaryTemperature"));
		}
	}
	
	public Command setBoundaryTemperature(EditingDomain ed, double value) {
		safeAccessBoundaryTemperature();
		return this.boundaryTemperature.setValue(ed, value);
	}
	
	public void setBoundaryTemperature(double value) {
		safeAccessBoundaryTemperature();
		this.boundaryTemperature.setValue(value);
	}
	
	public double getBoundaryTemperature() {
		safeAccessBoundaryTemperature();
		return boundaryTemperature.getValue();
	}
	
	public boolean isSetBoundaryTemperature() {
		safeAccessBoundaryTemperature();
		return boundaryTemperature.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getBoundaryTemperatureBean() {
		safeAccessBoundaryTemperature();
		return boundaryTemperature;
	}
	
	
}
