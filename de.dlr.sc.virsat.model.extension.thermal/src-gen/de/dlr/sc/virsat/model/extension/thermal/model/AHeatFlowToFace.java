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
 * Modeling an external heat flow to a face
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AHeatFlowToFace extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.HeatFlowToFace";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_COMPONENT = "component";
	public static final String PROPERTY_FREECADFACENUMBERHF = "freeCADFaceNumberHF";
	public static final String PROPERTY_HEATFLOW = "heatFlow";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AHeatFlowToFace() {
	}
	
	public AHeatFlowToFace(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "HeatFlowToFace");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "HeatFlowToFace");
		setTypeInstance(categoryAssignement);
	}
	
	public AHeatFlowToFace(CategoryAssignment categoryAssignement) {
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
	// * Attribute: freeCADFaceNumberHF
	// *****************************************************************
	private BeanPropertyInt freeCADFaceNumberHF = new BeanPropertyInt();
	
	private void safeAccessFreeCADFaceNumberHF() {
		if (freeCADFaceNumberHF.getTypeInstance() == null) {
			freeCADFaceNumberHF.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("freeCADFaceNumberHF"));
		}
	}
	
	public Command setFreeCADFaceNumberHF(EditingDomain ed, long value) {
		safeAccessFreeCADFaceNumberHF();
		return this.freeCADFaceNumberHF.setValue(ed, value);
	}
	
	public void setFreeCADFaceNumberHF(long value) {
		safeAccessFreeCADFaceNumberHF();
		this.freeCADFaceNumberHF.setValue(value);
	}
	
	public long getFreeCADFaceNumberHF() {
		safeAccessFreeCADFaceNumberHF();
		return freeCADFaceNumberHF.getValue();
	}
	
	public boolean isSetFreeCADFaceNumberHF() {
		safeAccessFreeCADFaceNumberHF();
		return freeCADFaceNumberHF.isSet();
	}
	
	@XmlElement
	public BeanPropertyInt getFreeCADFaceNumberHFBean() {
		safeAccessFreeCADFaceNumberHF();
		return freeCADFaceNumberHF;
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
	
	@XmlElement
	public BeanPropertyFloat getHeatFlowBean() {
		safeAccessHeatFlow();
		return heatFlow;
	}
	
	
}
