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
import jakarta.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import jakarta.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import jakarta.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import jakarta.xml.bind.annotation.XmlElement;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Here all thermal interfaces between ports are stored
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AThermalInterfaceList extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.ThermalInterfaceList";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_THERMALINTERFACES = "thermalInterfaces";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AThermalInterfaceList() {
	}
	
	public AThermalInterfaceList(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ThermalInterfaceList");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ThermalInterfaceList");
		setTypeInstance(categoryAssignement);
	}
	
	public AThermalInterfaceList(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: thermalInterfaces
	// *****************************************************************
	private IBeanList<ThermalInterface> thermalInterfaces = new TypeSafeComposedPropertyInstanceList<>(ThermalInterface.class);
	
	private void safeAccessThermalInterfaces() {
		if (thermalInterfaces.getArrayInstance() == null) {
			thermalInterfaces.setArrayInstance((ArrayInstance) helper.getPropertyInstance("thermalInterfaces"));
		}
	}
	
	public IBeanList<ThermalInterface> getThermalInterfaces() {
		safeAccessThermalInterfaces();
		return thermalInterfaces;
	}
	
	private IBeanList<BeanPropertyComposed<ThermalInterface>> thermalInterfacesBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessThermalInterfacesBean() {
		if (thermalInterfacesBean.getArrayInstance() == null) {
			thermalInterfacesBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("thermalInterfaces"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<ThermalInterface>> getThermalInterfacesBean() {
		safeAccessThermalInterfacesBean();
		return thermalInterfacesBean;
	}
	
	
}
