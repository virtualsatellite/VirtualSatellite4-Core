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
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
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
 * Modeling thermal element data
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AThermalData extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.ThermalData";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_RADIATIONALIST = "radiationaList";
	public static final String PROPERTY_THERMALCONTACTS = "thermalcontacts";
	public static final String PROPERTY_THERMALELEMENTPARAMETERS = "thermalelementparameters";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AThermalData() {
	}
	
	public AThermalData(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ThermalData");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ThermalData");
		setTypeInstance(categoryAssignement);
	}
	
	public AThermalData(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: radiationaList
	// *****************************************************************
	private BeanPropertyComposed<SingleFaceRadiationList> radiationaList = new BeanPropertyComposed<>();
	
	private void safeAccessRadiationaList() {
		if (radiationaList.getTypeInstance() == null) {
			ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("radiationaList");
			radiationaList.setTypeInstance(propertyInstance);
		}
	}
	
	@XmlElement(nillable = true)
	public SingleFaceRadiationList getRadiationaList() {
		safeAccessRadiationaList();
		return radiationaList.getValue();
	}
	
	public BeanPropertyComposed<SingleFaceRadiationList> getRadiationaListBean() {
		safeAccessRadiationaList();
		return radiationaList;
	}
	
	// *****************************************************************
	// * Attribute: thermalcontacts
	// *****************************************************************
	private BeanPropertyComposed<ThermalContacts> thermalcontacts = new BeanPropertyComposed<>();
	
	private void safeAccessThermalcontacts() {
		if (thermalcontacts.getTypeInstance() == null) {
			ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("thermalcontacts");
			thermalcontacts.setTypeInstance(propertyInstance);
		}
	}
	
	@XmlElement(nillable = true)
	public ThermalContacts getThermalcontacts() {
		safeAccessThermalcontacts();
		return thermalcontacts.getValue();
	}
	
	public BeanPropertyComposed<ThermalContacts> getThermalcontactsBean() {
		safeAccessThermalcontacts();
		return thermalcontacts;
	}
	
	// *****************************************************************
	// * Attribute: thermalelementparameters
	// *****************************************************************
	private BeanPropertyComposed<ThermalElementParameters> thermalelementparameters = new BeanPropertyComposed<>();
	
	private void safeAccessThermalelementparameters() {
		if (thermalelementparameters.getTypeInstance() == null) {
			ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("thermalelementparameters");
			thermalelementparameters.setTypeInstance(propertyInstance);
		}
	}
	
	@XmlElement(nillable = true)
	public ThermalElementParameters getThermalelementparameters() {
		safeAccessThermalelementparameters();
		return thermalelementparameters.getValue();
	}
	
	public BeanPropertyComposed<ThermalElementParameters> getThermalelementparametersBean() {
		safeAccessThermalelementparameters();
		return thermalelementparameters;
	}
	
	
}
