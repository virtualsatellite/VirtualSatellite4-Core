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
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.extension.statemachines.model.TransitionTriggerEvent;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
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
public abstract class AEventToOutputMapping extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.sequencing.EventToOutputMapping";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_EVENT = "event";
	public static final String PROPERTY_INTERFACEENDS = "interfaceEnds";
	public static final String PROPERTY_OUTPUTSTATES = "outputStates";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AEventToOutputMapping() {
	}
	
	public AEventToOutputMapping(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "EventToOutputMapping");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "EventToOutputMapping");
		setTypeInstance(categoryAssignement);
	}
	
	public AEventToOutputMapping(CategoryAssignment categoryAssignement) {
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
	// * Array Attribute: interfaceEnds
	// *****************************************************************
		private IBeanList<InterfaceEnd> interfaceEnds = new TypeSafeReferencePropertyInstanceList<>(InterfaceEnd.class);
	
		private void safeAccessInterfaceEnds() {
			if (interfaceEnds.getArrayInstance() == null) {
				interfaceEnds.setArrayInstance((ArrayInstance) helper.getPropertyInstance("interfaceEnds"));
			}
		}
	
		public IBeanList<InterfaceEnd> getInterfaceEnds() {
			safeAccessInterfaceEnds();
			return interfaceEnds;
		}
		
		private IBeanList<BeanPropertyReference<InterfaceEnd>> interfaceEndsBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessInterfaceEndsBean() {
			if (interfaceEndsBean.getArrayInstance() == null) {
				interfaceEndsBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("interfaceEnds"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<InterfaceEnd>> getInterfaceEndsBean() {
			safeAccessInterfaceEndsBean();
			return interfaceEndsBean;
		}
	
	// *****************************************************************
	// * Array Attribute: outputStates
	// *****************************************************************
		private IBeanList<State> outputStates = new TypeSafeReferencePropertyInstanceList<>(State.class);
	
		private void safeAccessOutputStates() {
			if (outputStates.getArrayInstance() == null) {
				outputStates.setArrayInstance((ArrayInstance) helper.getPropertyInstance("outputStates"));
			}
		}
	
		public IBeanList<State> getOutputStates() {
			safeAccessOutputStates();
			return outputStates;
		}
		
		private IBeanList<BeanPropertyReference<State>> outputStatesBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessOutputStatesBean() {
			if (outputStatesBean.getArrayInstance() == null) {
				outputStatesBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("outputStates"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<State>> getOutputStatesBean() {
			safeAccessOutputStatesBean();
			return outputStatesBean;
		}
	
	
}
