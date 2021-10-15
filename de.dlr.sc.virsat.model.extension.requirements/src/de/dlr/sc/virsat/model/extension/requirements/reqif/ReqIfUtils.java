/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.reqif;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.rmf.reqif10.AttributeDefinition;
import org.eclipse.rmf.reqif10.AttributeValue;
import org.eclipse.rmf.reqif10.AttributeValueString;
import org.eclipse.rmf.reqif10.AttributeValueXHTML;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;
import org.eclipse.rmf.reqif10.common.util.ReqIF10XhtmlUtil;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.util.RequirementHelper;

/**
 * Utility methods for ReqIF requirement handling
 *
 */
public class ReqIfUtils {
	
	private RequirementHelper reqHelper = new RequirementHelper();
	
	public static final String XHTML_PARSER_ERROR = "Could not parse XHTML!";
	public static final String DUPLICATE_NATIVE_ATTRIBUTE_MAPPING = "Duplicate implementation for native attribute mapping defined! For attribute: ";
	public static final String REQIF_NAME_ATTRIBUTE_NAME = "ReqIF.Name";
	protected static final int MAX_LENGTH_NAME_DESCRIPTION = 50;
	
	protected List<INativeRequirementAttributeMapping> mappingImpls;
	
	public ReqIfUtils(List<INativeRequirementAttributeMapping> nativeMappings) {
		this.mappingImpls = nativeMappings;
	}
	
	/**
	 * Check it a requirement attribute to be imported is specified as identifier
	 * 
	 * @param reqIfAtt the attribute mapping to be checked
	 * @return true if is an identifier, false otherwise
	 */
	protected boolean isIdentifier(AttributeDefinition reqIfAtt) {
		for (INativeRequirementAttributeMapping mappingImpl : mappingImpls) {
			if (mappingImpl.isIdentifierAttribute(reqIfAtt)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if a native index exists for the given attribute
	 * @param reqIfAtt the attribute
	 * @return a fixed index or null
	 */
	protected Integer nativeIndex(AttributeDefinition reqIfAtt) {
		for (INativeRequirementAttributeMapping mappingImpl : mappingImpls) {
			Integer index = mappingImpl.getNativeIndex(reqIfAtt);
			if (index != null) {
				return index;
			}
		}
		return null;
	}
	
	/**
	 * Check it a requirement attribute to be imported has a specified mapping to a native attribute implementation
	 * 
	 * @param reqIfAtt the attribute mapping to be checked
	 * @return true if native mapping is defined, false otherwise
	 */
	protected boolean hasNativeAttributeImpl(AttributeDefinition reqIfAtt) {
		boolean isNativeAttribute = false;
		for (INativeRequirementAttributeMapping mappingImpl : mappingImpls) {
			if (mappingImpl.isNativeAttribute(reqIfAtt)) {
				if (isNativeAttribute) {
					Activator.getDefault().getLog().error(DUPLICATE_NATIVE_ATTRIBUTE_MAPPING + reqIfAtt.getLongName());
				}
				isNativeAttribute = true;
			}
		}
		return isNativeAttribute;
	}
	
	/**
	 * Delegate the setting of the attribute value to the implementation of native attribute mapping
	 * 
	 * @param editingDomain the editing domain of the import
	 * @param requirement the requirement in which values are supposed to be imported
	 * @param attValue the ReqIF attribute value
	 * @param attDef the ReqIF attribute definition
	 * @return the command to be executed for 
	 */
	protected Command setNativeRequirementAttributeValue(EditingDomain editingDomain, Requirement requirement, AttributeValue attValue, AttributeDefinition attDef) {
		for (INativeRequirementAttributeMapping mappingImpl : mappingImpls) {
			if (mappingImpl.isNativeAttribute(attDef)) {
				return mappingImpl.setNativeValues(editingDomain, requirement, attValue);
			}
		}
		return UnexecutableCommand.INSTANCE;
	}
	
	/**
	 * Delegate the setting of the attribute value to the implementation of native attribute mapping
	 * 
	 * @param requirement the requirement in which values are supposed to be imported
	 * @param attValue the ReqIF attribute value
	 * @param attDef the ReqIF attribute definition
	 */
	protected void setNativeRequirementAttributeValue(Requirement requirement, AttributeValue attValue, AttributeDefinition attDef) {
		for (INativeRequirementAttributeMapping mappingImpl : mappingImpls) {
			if (mappingImpl.isNativeAttribute(attDef)) {
				mappingImpl.setNativeValues(requirement, attValue);
			}
		}
	}
	
	/**
	 * Utility method to check if a list of named elements contains an element with a given name
	 * 
	 * @param list the list of named beans
	 * @param name the name to search for
	 * @return weather the list contains an element with the given name
	 */
	protected boolean contains(IBeanList<? extends IBeanName> list, String name) {
		return findExisting(list, name) != null;
	}
	
	/**
	 * Check if we have an existing object locally for the given ReqIF requirement
	 * @param reqList the list to search for existing requirement objects
	 * @param hierarchyObject the ReqIF requirement hierarchy element
	 * @return the existing requirement object or null
	 */
	protected RequirementObject findExisting(IBeanList<RequirementObject> reqList, SpecHierarchy hierarchyObject) {
		String requirementIdentifier = getReqIFRequirementIdentifier(hierarchyObject);
		String groupName = getReqIFRequirementName(hierarchyObject);
		for (RequirementObject requirementObject : reqList) {
			if (requirementObject.getIdentifier().equals(requirementIdentifier) 
					|| requirementObject.getIdentifier().equals(groupName)) {
				return requirementObject;
			}
		}
		return null;
	}
	
	/**
	 * Check if we have an existing object locally for the given element
	 * @param beanList the list to search for existing  objects
	 * @param hierarchyObject the element name
	 * @return the existing element
	 */
	protected IBeanName findExisting(IBeanList<? extends IBeanName> beanList, String name) {
		for (IBeanName namedElement : beanList) {
			if (namedElement.getName().equals(name)) {
				return namedElement;
			}
		}
		return null;
	}
	
	/**
	 * Return an existing attribute value object of a given requirement if existing
	 * 
	 * @param requirement the requirement in which the attribute value should be found
	 * @param attDef the ReqIF attribute definition corresponding to the attribute value 
	 * @return the existing attribute value or null if not existing
	 */
	protected de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue findExisting(Requirement requirement, AttributeDefinition attDef) {
		for (de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue attValue : requirement.getElements()) {
			if (attValue.getAttType().getName().equals(cleanAttName(attDef.getLongName()))) {
				return attValue;
			}
		}
		return null;
	}
	
	/**
	 * Return the attribute type of a ReqIF attribute definition
	 * 
	 * @param requirement the requirement in which the attribute value should be found
	 * @param attDefReqIf the ReqIF attribute definition corresponding to the attribute value 
	 * @return the existing attribute value or null if not existing
	 */
	protected RequirementAttribute findAttributeDefinition(Requirement requirement, AttributeDefinition attDefReqIf) {
		RequirementType reqType = requirement.getReqType();
		return (RequirementAttribute) findExisting(reqType.getAttributes(), cleanAttName(attDefReqIf.getLongName()));
	}
	
	/**
	 * Get the identifying attribute value for a ReqIF requirement
	 * 
	 * @param hierarchyObject the ReqIF requirement
	 * @return the identifying string
	 */
	protected String getReqIFRequirementIdentifier(SpecHierarchy hierarchyObject) {
		return getReqIFRequirementIdentifier(hierarchyObject.getObject());
	}
	
	/**
	 * Get the identifying attribute value for a ReqIF requirement
	 * 
	 * @param reqObject the ReqIF requirement
	 * @return the identifying string
	 */
	protected String getReqIFRequirementIdentifier(SpecObject reqObject) {
		for (AttributeValue attValue : reqObject.getValues()) {
			if (attValue instanceof AttributeValueString) {
				AttributeValueString attValueString = (AttributeValueString) attValue;
				if (isIdentifier(attValueString.getDefinition())) {
					return attValueString.getTheValue();
				}
				
			}
		}
		return null;
	}
	
	/**
	 * Get the name of a ReqIF requirement object
	 * 
	 * @param hierarchyObject the ReqIF object
	 * @return the name as string
	 */
	protected String getReqIFRequirementName(SpecHierarchy hierarchyObject) {
		return getReqIFRequirementName(hierarchyObject.getObject());
	}
	
	/**
	 * Update name of requirement element (must not be persisted yet)
	 * @param requirement the requirement to be updated
	 * @param spec the ReqIF spec of the requirement
	 */
	protected void updateRequirementNameFromReqIF(Requirement requirement, SpecHierarchy spec) {
		String description = getReqIFRequirementName(spec);
		if (description.length() < MAX_LENGTH_NAME_DESCRIPTION) {
			requirement.updateNameFromAttributes(description);
		} else {
			requirement.updateNameFromAttributes();
		}
	}
	
	/**
	 * Get the name of a ReqIF requirement object
	 * 
	 * @param reqObject the ReqIF object
	 * @return the name as string
	 */
	protected String getReqIFRequirementName(SpecObject reqObject) {
		for (AttributeValue attValue : reqObject.getValues()) {
			if (attValue instanceof AttributeValueXHTML) {
				AttributeValueXHTML attValueHTML = (AttributeValueXHTML) attValue;
				if (attValueHTML.getDefinition().getLongName().equals(REQIF_NAME_ATTRIBUTE_NAME)) {
					String name;
					try {
						name = ReqIF10XhtmlUtil.getXhtmlString(attValueHTML.getTheValue());
						return reqHelper.cleanEntityName(name.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", ""));
					} catch (IOException e) {
						Activator.getDefault().getLog().error(XHTML_PARSER_ERROR, e);
					}
				}
			}
		}
		return null;
	}
	
	public String cleanAttName(String rawName) {
		return reqHelper.cleanEntityName(rawName).replaceAll("ReqIF", "").replaceAll("Foreign", "");
	}

}
