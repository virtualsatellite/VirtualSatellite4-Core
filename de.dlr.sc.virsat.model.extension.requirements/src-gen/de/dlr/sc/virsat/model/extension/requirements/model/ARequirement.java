/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
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
public abstract class ARequirement extends RequirementObject implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.Requirement";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_REQTYPE = "reqType";
	public static final String PROPERTY_ELEMENTS = "elements";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_VERIFICATION = "verification";
	public static final String PROPERTY_TRACE = "trace";
	public static final String PROPERTY_DESCRIPTIONTEXT = "descriptionText";
	public static final String PROPERTY_CHILDREN = "children";
	
	// Status enumeration value names
	public static final String STATUS_Open_NAME = "Open";
	public static final String STATUS_FullyCompliant_NAME = "FullyCompliant";
	public static final String STATUS_PartialCompliant_NAME = "PartialCompliant";
	public static final String STATUS_NonCompliant_NAME = "NonCompliant";
	public static final String STATUS_NotApplicable_NAME = "NotApplicable";
	// Status enumeration values
	public static final String STATUS_Open_VALUE = "0";
	public static final String STATUS_FullyCompliant_VALUE = "1";
	public static final String STATUS_PartialCompliant_VALUE = "2";
	public static final String STATUS_NonCompliant_VALUE = "3";
	public static final String STATUS_NotApplicable_VALUE = "4";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARequirement() {
	}
	
	public ARequirement(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "Requirement");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "Requirement");
		setTypeInstance(categoryAssignement);
	}
	
	public ARequirement(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: reqType
	// *****************************************************************
	private BeanPropertyReference<RequirementType> reqType = new BeanPropertyReference<>();
	
	private void safeAccessReqType() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("reqType");
		reqType.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public RequirementType getReqType() {
		safeAccessReqType();
		return reqType.getValue();
	}
	
	public Command setReqType(EditingDomain ed, RequirementType value) {
		safeAccessReqType();
		return reqType.setValue(ed, value);
	}
	
	public void setReqType(RequirementType value) {
		safeAccessReqType();
		reqType.setValue(value);
	}
	
	public BeanPropertyReference<RequirementType> getReqTypeBean() {
		safeAccessReqType();
		return reqType;
	}
	
	// *****************************************************************
	// * Array Attribute: elements
	// *****************************************************************
	private IBeanList<AttributeValue> elements = new TypeSafeComposedPropertyInstanceList<>(AttributeValue.class);
	
	private void safeAccessElements() {
		if (elements.getArrayInstance() == null) {
			elements.setArrayInstance((ArrayInstance) helper.getPropertyInstance("elements"));
		}
	}
	
	public IBeanList<AttributeValue> getElements() {
		safeAccessElements();
		return elements;
	}
	
	private IBeanList<BeanPropertyComposed<AttributeValue>> elementsBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessElementsBean() {
		if (elementsBean.getArrayInstance() == null) {
			elementsBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("elements"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<AttributeValue>> getElementsBean() {
		safeAccessElementsBean();
		return elementsBean;
	}
	
	// *****************************************************************
	// * Attribute: status
	// *****************************************************************
	private BeanPropertyEnum status = new BeanPropertyEnum();
	
	private void safeAccessStatus() {
		if (status.getTypeInstance() == null) {
			status.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("status"));
		}
	}
	
	public Command setStatus(EditingDomain ed, String value) {
		safeAccessStatus();
		return this.status.setValue(ed, value);
	}
	
	public void setStatus(String value) {
		safeAccessStatus();
		this.status.setValue(value);
	}
	
	public String getStatus() {
		safeAccessStatus();
		return status.getValue();
	}
	
	public double getStatusEnum() {
		safeAccessStatus();
		return status.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getStatusBean() {
		safeAccessStatus();
		return status;
	}
	
	// *****************************************************************
	// * Array Attribute: verification
	// *****************************************************************
	private IBeanList<IVerification> verification = new TypeSafeComposedPropertyInstanceList<>(IVerification.class);
	
	private void safeAccessVerification() {
		if (verification.getArrayInstance() == null) {
			verification.setArrayInstance((ArrayInstance) helper.getPropertyInstance("verification"));
		}
	}
	
	public IBeanList<IVerification> getVerification() {
		safeAccessVerification();
		return verification;
	}
	
	private IBeanList<BeanPropertyComposed<IVerification>> verificationBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessVerificationBean() {
		if (verificationBean.getArrayInstance() == null) {
			verificationBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("verification"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<IVerification>> getVerificationBean() {
		safeAccessVerificationBean();
		return verificationBean;
	}
	
	// *****************************************************************
	// * Attribute: trace
	// *****************************************************************
	private BeanPropertyComposed<RequirementTrace> trace = new BeanPropertyComposed<>();
	
	private void safeAccessTrace() {
		if (trace.getTypeInstance() == null) {
			ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("trace");
			trace.setTypeInstance(propertyInstance);
		}
	}
	
	@XmlElement(nillable = true)
	public RequirementTrace getTrace() {
		safeAccessTrace();
		return trace.getValue();
	}
	
	public BeanPropertyComposed<RequirementTrace> getTraceBean() {
		safeAccessTrace();
		return trace;
	}
	
	// *****************************************************************
	// * Attribute: descriptionText
	// *****************************************************************
	private BeanPropertyString descriptionText = new BeanPropertyString();
	
	private void safeAccessDescriptionText() {
		if (descriptionText.getTypeInstance() == null) {
			descriptionText.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("descriptionText"));
		}
	}
	
	public Command setDescriptionText(EditingDomain ed, String value) {
		safeAccessDescriptionText();
		return this.descriptionText.setValue(ed, value);
	}
	
	public void setDescriptionText(String value) {
		safeAccessDescriptionText();
		this.descriptionText.setValue(value);
	}
	
	public String getDescriptionText() {
		safeAccessDescriptionText();
		return descriptionText.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getDescriptionTextBean() {
		safeAccessDescriptionText();
		return descriptionText;
	}
	
	// *****************************************************************
	// * Array Attribute: children
	// *****************************************************************
	private IBeanList<Requirement> children = new TypeSafeComposedPropertyInstanceList<>(Requirement.class);
	
	private void safeAccessChildren() {
		if (children.getArrayInstance() == null) {
			children.setArrayInstance((ArrayInstance) helper.getPropertyInstance("children"));
		}
	}
	
	public IBeanList<Requirement> getChildren() {
		safeAccessChildren();
		return children;
	}
	
	private IBeanList<BeanPropertyComposed<Requirement>> childrenBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessChildrenBean() {
		if (childrenBean.getArrayInstance() == null) {
			childrenBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("children"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<Requirement>> getChildrenBean() {
		safeAccessChildrenBean();
		return childrenBean;
	}
	
	
}
