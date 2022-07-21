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
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Modeling an external heat flow
 * 
 */	
public abstract class AHeatFlow extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.HeatFlow";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_PORTCOMPONENT = "portComponent";
	public static final String PROPERTY_FREECADFACENUMBERHEATFLOW = "freeCADFaceNumberHeatFlow";
	public static final String PROPERTY_HEATFLOW = "heatFlow";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AHeatFlow() {
	}
	
	public AHeatFlow(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "HeatFlow");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "HeatFlow");
		setTypeInstance(categoryAssignement);
	}
	
	public AHeatFlow(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: portComponent
	// *****************************************************************
	private BeanPropertyReference<ThermalElementParameters> portComponent = new BeanPropertyReference<>();
	
	private void safeAccessPortComponent() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("portComponent");
		portComponent.setTypeInstance(propertyInstance);
	}
	
	public ThermalElementParameters getPortComponent() {
		safeAccessPortComponent();
		return portComponent.getValue();
	}
	
	public Command setPortComponent(EditingDomain ed, ThermalElementParameters value) {
		safeAccessPortComponent();
		return portComponent.setValue(ed, value);
	}
	
	public void setPortComponent(ThermalElementParameters value) {
		safeAccessPortComponent();
		portComponent.setValue(value);
	}
	
	public BeanPropertyReference<ThermalElementParameters> getPortComponentBean() {
		safeAccessPortComponent();
		return portComponent;
	}
	
	// *****************************************************************
	// * Attribute: freeCADFaceNumberHeatFlow
	// *****************************************************************
	private BeanPropertyInt freeCADFaceNumberHeatFlow = new BeanPropertyInt();
	
	private void safeAccessFreeCADFaceNumberHeatFlow() {
		if (freeCADFaceNumberHeatFlow.getTypeInstance() == null) {
			freeCADFaceNumberHeatFlow.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("freeCADFaceNumberHeatFlow"));
		}
	}
	
	public Command setFreeCADFaceNumberHeatFlow(EditingDomain ed, long value) {
		safeAccessFreeCADFaceNumberHeatFlow();
		return this.freeCADFaceNumberHeatFlow.setValue(ed, value);
	}
	
	public void setFreeCADFaceNumberHeatFlow(long value) {
		safeAccessFreeCADFaceNumberHeatFlow();
		this.freeCADFaceNumberHeatFlow.setValue(value);
	}
	
	public long getFreeCADFaceNumberHeatFlow() {
		safeAccessFreeCADFaceNumberHeatFlow();
		return freeCADFaceNumberHeatFlow.getValue();
	}
	
	public boolean isSetFreeCADFaceNumberHeatFlow() {
		safeAccessFreeCADFaceNumberHeatFlow();
		return freeCADFaceNumberHeatFlow.isSet();
	}
	
	public BeanPropertyInt getFreeCADFaceNumberHeatFlowBean() {
		safeAccessFreeCADFaceNumberHeatFlow();
		return freeCADFaceNumberHeatFlow;
	}
	
	// *****************************************************************
	// * Attribute: heatFlow
	// *****************************************************************
	private BeanPropertyFloat heatFlow = new BeanPropertyFloat();
	
	private void safeAccessHeatFlow() {
		if (heatFlow.getTypeInstance() == null) {
			heatFlow.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("heatFlow"));
		}
	}
	
	public Command setHeatFlow(EditingDomain ed, double value) {
		safeAccessHeatFlow();
		return this.heatFlow.setValue(ed, value);
	}
	
	public void setHeatFlow(double value) {
		safeAccessHeatFlow();
		this.heatFlow.setValue(value);
	}
	
	public double getHeatFlow() {
		safeAccessHeatFlow();
		return heatFlow.getValue();
	}
	
	public boolean isSetHeatFlow() {
		safeAccessHeatFlow();
		return heatFlow.isSet();
	}
	
	public BeanPropertyFloat getHeatFlowBean() {
		safeAccessHeatFlow();
		return heatFlow;
	}
	
	
}
