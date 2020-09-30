/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.model;

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
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 * Describes a Functional Electrical Interface connecting two InterfaceEnds
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AInterface extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.funcelectrical.Interface";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_INTERFACEENDFROM = "interfaceEndFrom";
	public static final String PROPERTY_INTERFACEENDTO = "interfaceEndTo";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AInterface() {
	}
	
	public AInterface(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "Interface");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "Interface");
		setTypeInstance(categoryAssignement);
	}
	
	public AInterface(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: interfaceEndFrom
	// *****************************************************************
	private BeanPropertyReference<InterfaceEnd> interfaceEndFrom = new BeanPropertyReference<>();
	
	private void safeAccessInterfaceEndFrom() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("interfaceEndFrom");
		interfaceEndFrom.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public InterfaceEnd getInterfaceEndFrom() {
		safeAccessInterfaceEndFrom();
		return interfaceEndFrom.getValue();
	}
	
	public Command setInterfaceEndFrom(EditingDomain ed, InterfaceEnd value) {
		safeAccessInterfaceEndFrom();
		return interfaceEndFrom.setValue(ed, value);
	}
	
	public void setInterfaceEndFrom(InterfaceEnd value) {
		safeAccessInterfaceEndFrom();
		interfaceEndFrom.setValue(value);
	}
	
	public BeanPropertyReference<InterfaceEnd> getInterfaceEndFromBean() {
		safeAccessInterfaceEndFrom();
		return interfaceEndFrom;
	}
	
	// *****************************************************************
	// * Attribute: interfaceEndTo
	// *****************************************************************
	private BeanPropertyReference<InterfaceEnd> interfaceEndTo = new BeanPropertyReference<>();
	
	private void safeAccessInterfaceEndTo() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("interfaceEndTo");
		interfaceEndTo.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public InterfaceEnd getInterfaceEndTo() {
		safeAccessInterfaceEndTo();
		return interfaceEndTo.getValue();
	}
	
	public Command setInterfaceEndTo(EditingDomain ed, InterfaceEnd value) {
		safeAccessInterfaceEndTo();
		return interfaceEndTo.setValue(ed, value);
	}
	
	public void setInterfaceEndTo(InterfaceEnd value) {
		safeAccessInterfaceEndTo();
		interfaceEndTo.setValue(value);
	}
	
	public BeanPropertyReference<InterfaceEnd> getInterfaceEndToBean() {
		safeAccessInterfaceEndTo();
		return interfaceEndTo;
	}
	
	
}
