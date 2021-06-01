/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.sequencing.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.extension.statemachines.model.TransitionTriggerEvent;
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
 * 
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AEventToInterfaceEndMapping extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.sequencing.EventToInterfaceEndMapping";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_EVENT = "event";
	public static final String PROPERTY_INTERFACEEND = "interfaceEnd";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AEventToInterfaceEndMapping() {
	}
	
	public AEventToInterfaceEndMapping(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "EventToInterfaceEndMapping");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "EventToInterfaceEndMapping");
		setTypeInstance(categoryAssignement);
	}
	
	public AEventToInterfaceEndMapping(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: event
	// *****************************************************************
	private BeanPropertyReference<TransitionTriggerEvent> event = new BeanPropertyReference<>();
	
	private void safeAccessEvent() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("event");
		event.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public TransitionTriggerEvent getEvent() {
		safeAccessEvent();
		return event.getValue();
	}
	
	public Command setEvent(EditingDomain ed, TransitionTriggerEvent value) {
		safeAccessEvent();
		return event.setValue(ed, value);
	}
	
	public void setEvent(TransitionTriggerEvent value) {
		safeAccessEvent();
		event.setValue(value);
	}
	
	public BeanPropertyReference<TransitionTriggerEvent> getEventBean() {
		safeAccessEvent();
		return event;
	}
	
	// *****************************************************************
	// * Attribute: interfaceEnd
	// *****************************************************************
	private BeanPropertyReference<InterfaceEnd> interfaceEnd = new BeanPropertyReference<>();
	
	private void safeAccessInterfaceEnd() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("interfaceEnd");
		interfaceEnd.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public InterfaceEnd getInterfaceEnd() {
		safeAccessInterfaceEnd();
		return interfaceEnd.getValue();
	}
	
	public Command setInterfaceEnd(EditingDomain ed, InterfaceEnd value) {
		safeAccessInterfaceEnd();
		return interfaceEnd.setValue(ed, value);
	}
	
	public void setInterfaceEnd(InterfaceEnd value) {
		safeAccessInterfaceEnd();
		interfaceEnd.setValue(value);
	}
	
	public BeanPropertyReference<InterfaceEnd> getInterfaceEndBean() {
		safeAccessInterfaceEnd();
		return interfaceEnd;
	}
	
	
}
