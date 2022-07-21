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
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList;
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
 * Modeling of thermal interfaces
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AThermalInterface extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.ThermalInterface";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_CONTACTS = "Contacts";
	public static final String PROPERTY_THERMALCONTACTCONDUCTIVITY = "thermalContactConductivity";
	public static final String PROPERTY_CONTACTMAXMESHELEMENTSIZE0 = "contactMaxMeshElementSize0";
	public static final String PROPERTY_CONTACTMAXMESHELEMENTSIZE1 = "contactMaxMeshElementSize1";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AThermalInterface() {
	}
	
	public AThermalInterface(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ThermalInterface");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ThermalInterface");
		setTypeInstance(categoryAssignement);
	}
	
	public AThermalInterface(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: Contacts
	// *****************************************************************
		private IBeanList<ThermalPort> Contacts = new TypeSafeReferencePropertyInstanceList<>(ThermalPort.class);
	
		private void safeAccessContacts() {
			if (Contacts.getArrayInstance() == null) {
				Contacts.setArrayInstance((ArrayInstance) helper.getPropertyInstance("Contacts"));
			}
		}
	
		public IBeanList<ThermalPort> getContacts() {
			safeAccessContacts();
			return Contacts;
		}
		
		private IBeanList<BeanPropertyReference<ThermalPort>> ContactsBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessContactsBean() {
			if (ContactsBean.getArrayInstance() == null) {
				ContactsBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("Contacts"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<ThermalPort>> getContactsBean() {
			safeAccessContactsBean();
			return ContactsBean;
		}
	
	// *****************************************************************
	// * Attribute: thermalContactConductivity
	// *****************************************************************
	private BeanPropertyFloat thermalContactConductivity = new BeanPropertyFloat();
	
	private void safeAccessThermalContactConductivity() {
		if (thermalContactConductivity.getTypeInstance() == null) {
			thermalContactConductivity.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("thermalContactConductivity"));
		}
	}
	
	public Command setThermalContactConductivity(EditingDomain ed, double value) {
		safeAccessThermalContactConductivity();
		return this.thermalContactConductivity.setValue(ed, value);
	}
	
	public void setThermalContactConductivity(double value) {
		safeAccessThermalContactConductivity();
		this.thermalContactConductivity.setValue(value);
	}
	
	public double getThermalContactConductivity() {
		safeAccessThermalContactConductivity();
		return thermalContactConductivity.getValue();
	}
	
	public boolean isSetThermalContactConductivity() {
		safeAccessThermalContactConductivity();
		return thermalContactConductivity.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getThermalContactConductivityBean() {
		safeAccessThermalContactConductivity();
		return thermalContactConductivity;
	}
	
	// *****************************************************************
	// * Attribute: contactMaxMeshElementSize0
	// *****************************************************************
	private BeanPropertyFloat contactMaxMeshElementSize0 = new BeanPropertyFloat();
	
	private void safeAccessContactMaxMeshElementSize0() {
		if (contactMaxMeshElementSize0.getTypeInstance() == null) {
			contactMaxMeshElementSize0.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("contactMaxMeshElementSize0"));
		}
	}
	
	public Command setContactMaxMeshElementSize0(EditingDomain ed, double value) {
		safeAccessContactMaxMeshElementSize0();
		return this.contactMaxMeshElementSize0.setValue(ed, value);
	}
	
	public void setContactMaxMeshElementSize0(double value) {
		safeAccessContactMaxMeshElementSize0();
		this.contactMaxMeshElementSize0.setValue(value);
	}
	
	public double getContactMaxMeshElementSize0() {
		safeAccessContactMaxMeshElementSize0();
		return contactMaxMeshElementSize0.getValue();
	}
	
	public boolean isSetContactMaxMeshElementSize0() {
		safeAccessContactMaxMeshElementSize0();
		return contactMaxMeshElementSize0.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getContactMaxMeshElementSize0Bean() {
		safeAccessContactMaxMeshElementSize0();
		return contactMaxMeshElementSize0;
	}
	
	// *****************************************************************
	// * Attribute: contactMaxMeshElementSize1
	// *****************************************************************
	private BeanPropertyFloat contactMaxMeshElementSize1 = new BeanPropertyFloat();
	
	private void safeAccessContactMaxMeshElementSize1() {
		if (contactMaxMeshElementSize1.getTypeInstance() == null) {
			contactMaxMeshElementSize1.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("contactMaxMeshElementSize1"));
		}
	}
	
	public Command setContactMaxMeshElementSize1(EditingDomain ed, double value) {
		safeAccessContactMaxMeshElementSize1();
		return this.contactMaxMeshElementSize1.setValue(ed, value);
	}
	
	public void setContactMaxMeshElementSize1(double value) {
		safeAccessContactMaxMeshElementSize1();
		this.contactMaxMeshElementSize1.setValue(value);
	}
	
	public double getContactMaxMeshElementSize1() {
		safeAccessContactMaxMeshElementSize1();
		return contactMaxMeshElementSize1.getValue();
	}
	
	public boolean isSetContactMaxMeshElementSize1() {
		safeAccessContactMaxMeshElementSize1();
		return contactMaxMeshElementSize1.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getContactMaxMeshElementSize1Bean() {
		safeAccessContactMaxMeshElementSize1();
		return contactMaxMeshElementSize1;
	}
	
	
}
