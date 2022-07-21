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
 * The maximum specific size of the mesh is specified here.
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AComponentMeshSize extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.ComponentMeshSize";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_MESHCOMPONENT = "meshComponent";
	public static final String PROPERTY_MAXIMUMCHARACTERISTICMESHLENGTH = "maximumCharacteristicMeshLength";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AComponentMeshSize() {
	}
	
	public AComponentMeshSize(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ComponentMeshSize");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ComponentMeshSize");
		setTypeInstance(categoryAssignement);
	}
	
	public AComponentMeshSize(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: meshComponent
	// *****************************************************************
	private BeanPropertyReference<ThermalElementParameters> meshComponent = new BeanPropertyReference<>();
	
	private void safeAccessMeshComponent() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("meshComponent");
		meshComponent.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public ThermalElementParameters getMeshComponent() {
		safeAccessMeshComponent();
		return meshComponent.getValue();
	}
	
	public Command setMeshComponent(EditingDomain ed, ThermalElementParameters value) {
		safeAccessMeshComponent();
		return meshComponent.setValue(ed, value);
	}
	
	public void setMeshComponent(ThermalElementParameters value) {
		safeAccessMeshComponent();
		meshComponent.setValue(value);
	}
	
	public BeanPropertyReference<ThermalElementParameters> getMeshComponentBean() {
		safeAccessMeshComponent();
		return meshComponent;
	}
	
	// *****************************************************************
	// * Attribute: maximumCharacteristicMeshLength
	// *****************************************************************
	private BeanPropertyFloat maximumCharacteristicMeshLength = new BeanPropertyFloat();
	
	private void safeAccessMaximumCharacteristicMeshLength() {
		if (maximumCharacteristicMeshLength.getTypeInstance() == null) {
			maximumCharacteristicMeshLength.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("maximumCharacteristicMeshLength"));
		}
	}
	
	public Command setMaximumCharacteristicMeshLength(EditingDomain ed, double value) {
		safeAccessMaximumCharacteristicMeshLength();
		return this.maximumCharacteristicMeshLength.setValue(ed, value);
	}
	
	public void setMaximumCharacteristicMeshLength(double value) {
		safeAccessMaximumCharacteristicMeshLength();
		this.maximumCharacteristicMeshLength.setValue(value);
	}
	
	public double getMaximumCharacteristicMeshLength() {
		safeAccessMaximumCharacteristicMeshLength();
		return maximumCharacteristicMeshLength.getValue();
	}
	
	public boolean isSetMaximumCharacteristicMeshLength() {
		safeAccessMaximumCharacteristicMeshLength();
		return maximumCharacteristicMeshLength.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMaximumCharacteristicMeshLengthBean() {
		safeAccessMaximumCharacteristicMeshLength();
		return maximumCharacteristicMeshLength;
	}
	
	
}
