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
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
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
 * Here all thermal contacts are stored
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AThermalContacts extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.ThermalContacts";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_THERMALPORTLIST = "thermalportlist";
	public static final String PROPERTY_THERMALINTERFACELIST = "thermalinterfacelist";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AThermalContacts() {
	}
	
	public AThermalContacts(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ThermalContacts");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ThermalContacts");
		setTypeInstance(categoryAssignement);
	}
	
	public AThermalContacts(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: thermalportlist
	// *****************************************************************
	private IBeanList<ThermalPort> thermalportlist = new TypeSafeComposedPropertyInstanceList<>(ThermalPort.class);
	
	private void safeAccessThermalportlist() {
		if (thermalportlist.getArrayInstance() == null) {
			thermalportlist.setArrayInstance((ArrayInstance) helper.getPropertyInstance("thermalportlist"));
		}
	}
	
	public IBeanList<ThermalPort> getThermalportlist() {
		safeAccessThermalportlist();
		return thermalportlist;
	}
	
	private IBeanList<BeanPropertyComposed<ThermalPort>> thermalportlistBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessThermalportlistBean() {
		if (thermalportlistBean.getArrayInstance() == null) {
			thermalportlistBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("thermalportlist"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<ThermalPort>> getThermalportlistBean() {
		safeAccessThermalportlistBean();
		return thermalportlistBean;
	}
	
	// *****************************************************************
	// * Array Attribute: thermalinterfacelist
	// *****************************************************************
	private IBeanList<ThermalInterface> thermalinterfacelist = new TypeSafeComposedPropertyInstanceList<>(ThermalInterface.class);
	
	private void safeAccessThermalinterfacelist() {
		if (thermalinterfacelist.getArrayInstance() == null) {
			thermalinterfacelist.setArrayInstance((ArrayInstance) helper.getPropertyInstance("thermalinterfacelist"));
		}
	}
	
	public IBeanList<ThermalInterface> getThermalinterfacelist() {
		safeAccessThermalinterfacelist();
		return thermalinterfacelist;
	}
	
	private IBeanList<BeanPropertyComposed<ThermalInterface>> thermalinterfacelistBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessThermalinterfacelistBean() {
		if (thermalinterfacelistBean.getArrayInstance() == null) {
			thermalinterfacelistBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("thermalinterfacelist"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<ThermalInterface>> getThermalinterfacelistBean() {
		safeAccessThermalinterfacelistBean();
		return thermalinterfacelistBean;
	}
	
	
}
