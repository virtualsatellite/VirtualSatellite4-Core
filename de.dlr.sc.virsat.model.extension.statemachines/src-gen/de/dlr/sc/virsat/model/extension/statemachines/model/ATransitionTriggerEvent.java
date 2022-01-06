/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
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
 * Some kind of event or action that triggers a transition
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ATransitionTriggerEvent extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.statemachines.TransitionTriggerEvent";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SENDING = "sending";
	public static final String PROPERTY_DETAIL = "detail";
	public static final String PROPERTY_MINTIME = "mintime";
	public static final String PROPERTY_MAXTIME = "maxtime";
	
	// Type enumeration value names
	public static final String TYPE_auto_NAME = "auto";
	public static final String TYPE_telecommand_NAME = "telecommand";
	public static final String TYPE_reconfiguration_NAME = "reconfiguration";
	// Type enumeration values
	public static final String TYPE_auto_VALUE = "0";
	public static final String TYPE_telecommand_VALUE = "1";
	public static final String TYPE_reconfiguration_VALUE = "2";
	// Sending enumeration value names
	public static final String SENDING_send_NAME = "send";
	public static final String SENDING_receive_NAME = "receive";
	// Sending enumeration values
	public static final String SENDING_send_VALUE = "0";
	public static final String SENDING_receive_VALUE = "1";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATransitionTriggerEvent() {
	}
	
	public ATransitionTriggerEvent(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TransitionTriggerEvent");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TransitionTriggerEvent");
		setTypeInstance(categoryAssignement);
	}
	
	public ATransitionTriggerEvent(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: type
	// *****************************************************************
	private BeanPropertyEnum type = new BeanPropertyEnum();
	
	private void safeAccessType() {
		if (type.getTypeInstance() == null) {
			type.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("type"));
		}
	}
	
	public Command setType(EditingDomain ed, String value) {
		safeAccessType();
		return this.type.setValue(ed, value);
	}
	
	public void setType(String value) {
		safeAccessType();
		this.type.setValue(value);
	}
	
	public String getType() {
		safeAccessType();
		return type.getValue();
	}
	
	public double getTypeEnum() {
		safeAccessType();
		return type.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getTypeBean() {
		safeAccessType();
		return type;
	}
	
	// *****************************************************************
	// * Attribute: sending
	// *****************************************************************
	private BeanPropertyEnum sending = new BeanPropertyEnum();
	
	private void safeAccessSending() {
		if (sending.getTypeInstance() == null) {
			sending.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("sending"));
		}
	}
	
	public Command setSending(EditingDomain ed, String value) {
		safeAccessSending();
		return this.sending.setValue(ed, value);
	}
	
	public void setSending(String value) {
		safeAccessSending();
		this.sending.setValue(value);
	}
	
	public String getSending() {
		safeAccessSending();
		return sending.getValue();
	}
	
	public double getSendingEnum() {
		safeAccessSending();
		return sending.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getSendingBean() {
		safeAccessSending();
		return sending;
	}
	
	// *****************************************************************
	// * Attribute: detail
	// *****************************************************************
	private BeanPropertyString detail = new BeanPropertyString();
	
	private void safeAccessDetail() {
		if (detail.getTypeInstance() == null) {
			detail.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("detail"));
		}
	}
	
	public Command setDetail(EditingDomain ed, String value) {
		safeAccessDetail();
		return this.detail.setValue(ed, value);
	}
	
	public void setDetail(String value) {
		safeAccessDetail();
		this.detail.setValue(value);
	}
	
	public String getDetail() {
		safeAccessDetail();
		return detail.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getDetailBean() {
		safeAccessDetail();
		return detail;
	}
	
	// *****************************************************************
	// * Attribute: mintime
	// *****************************************************************
	private BeanPropertyFloat mintime = new BeanPropertyFloat();
	
	private void safeAccessMintime() {
		if (mintime.getTypeInstance() == null) {
			mintime.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("mintime"));
		}
	}
	
	public Command setMintime(EditingDomain ed, double value) {
		safeAccessMintime();
		return this.mintime.setValue(ed, value);
	}
	
	public void setMintime(double value) {
		safeAccessMintime();
		this.mintime.setValue(value);
	}
	
	public double getMintime() {
		safeAccessMintime();
		return mintime.getValue();
	}
	
	public boolean isSetMintime() {
		safeAccessMintime();
		return mintime.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMintimeBean() {
		safeAccessMintime();
		return mintime;
	}
	
	// *****************************************************************
	// * Attribute: maxtime
	// *****************************************************************
	private BeanPropertyFloat maxtime = new BeanPropertyFloat();
	
	private void safeAccessMaxtime() {
		if (maxtime.getTypeInstance() == null) {
			maxtime.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("maxtime"));
		}
	}
	
	public Command setMaxtime(EditingDomain ed, double value) {
		safeAccessMaxtime();
		return this.maxtime.setValue(ed, value);
	}
	
	public void setMaxtime(double value) {
		safeAccessMaxtime();
		this.maxtime.setValue(value);
	}
	
	public double getMaxtime() {
		safeAccessMaxtime();
		return maxtime.getValue();
	}
	
	public boolean isSetMaxtime() {
		safeAccessMaxtime();
		return maxtime.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getMaxtimeBean() {
		safeAccessMaxtime();
		return maxtime;
	}
	
	
}
