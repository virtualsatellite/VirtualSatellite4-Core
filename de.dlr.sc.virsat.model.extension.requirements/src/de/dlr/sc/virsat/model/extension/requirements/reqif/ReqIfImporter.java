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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.rmf.reqif10.AttributeDefinition;
import org.eclipse.rmf.reqif10.AttributeDefinitionBoolean;
import org.eclipse.rmf.reqif10.AttributeDefinitionDate;
import org.eclipse.rmf.reqif10.AttributeDefinitionEnumeration;
import org.eclipse.rmf.reqif10.AttributeDefinitionInteger;
import org.eclipse.rmf.reqif10.AttributeDefinitionReal;
import org.eclipse.rmf.reqif10.AttributeDefinitionString;
import org.eclipse.rmf.reqif10.AttributeDefinitionXHTML;
import org.eclipse.rmf.reqif10.AttributeValue;
import org.eclipse.rmf.reqif10.AttributeValueBoolean;
import org.eclipse.rmf.reqif10.AttributeValueDate;
import org.eclipse.rmf.reqif10.AttributeValueEnumeration;
import org.eclipse.rmf.reqif10.AttributeValueInteger;
import org.eclipse.rmf.reqif10.AttributeValueReal;
import org.eclipse.rmf.reqif10.AttributeValueString;
import org.eclipse.rmf.reqif10.AttributeValueXHTML;
import org.eclipse.rmf.reqif10.DatatypeDefinitionEnumeration;
import org.eclipse.rmf.reqif10.EnumValue;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.ReqIFContent;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;
import org.eclipse.rmf.reqif10.SpecObjectType;
import org.eclipse.rmf.reqif10.SpecRelation;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.rmf.reqif10.Specification;
import org.eclipse.rmf.reqif10.common.util.ReqIF10XhtmlUtil;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationLiteral;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementLink;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementLinkType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.model.extension.requirements.model.SpecificationMapping;
import de.dlr.sc.virsat.model.extension.requirements.util.RequirementHelper;

/**
 * Class for importing requirements from reqIF to our requirements concept
 *
 */
public class ReqIfImporter {
	
	public static final String IMPORT_CONFIC_PREFIX = "ReqIFImport";
	public static final String TYPE_CONTAINER_PREFIX = "TypeContainer";
	
	private RequirementHelper reqHelper = new RequirementHelper();
	private ReqIfUtils reqIfUtils;
	protected ImportConfiguration importConfiguration = null;
	protected RequirementsConfigurationCollection configurationContainer = null;
	protected Concept concept = null;
	protected ReqIF reqIfContent;
	protected List<INativeRequirementAttributeMapping> mappingImpls = new ArrayList<INativeRequirementAttributeMapping>();
	

	/**
	 * Init the importer by handing over the ReqIF content to be handled and the import configuration location
	 * 
	 * @param reqIFContent the ReqIF content to be handled
	 * @param configurationContainer the import configuration location when created
	 */
	public void init(ReqIF reqIFContent, RequirementsConfigurationCollection configurationContainer) {
		this.concept = ActiveConceptHelper.getConcept(configurationContainer.getStructuralElementInstance().getType());
		this.reqIfContent = reqIFContent;
		this.configurationContainer = configurationContainer;
		registerAttributeMappingImpls();
		reqIfUtils = new ReqIfUtils(mappingImpls);
	}
	
	/**
	 * Init the importer by handing over the ReqIF content to be handled and the import configuration
	 * 
	 * @param reqIFContent the ReqIF content to be handled
	 * @param configuration the import configuration
	 */
	public void init(ReqIF reqIFContent, ImportConfiguration configuration) {
		this.concept = ActiveConceptHelper.getConcept(configuration.getTypeInstance().getType());
		this.reqIfContent = reqIFContent;
		this.importConfiguration = configuration;
		this.configurationContainer = (RequirementsConfigurationCollection) configuration.getParent();
		registerAttributeMappingImpls();
		reqIfUtils = new ReqIfUtils(mappingImpls);
	}
	
	protected void registerAttributeMappingImpls() {
		mappingImpls.add(new DoorsNGImportMapping());
	}
	
	/**
	 * Import the requirements specified in the ReqIF content
	 * 
	 * @param editingDomain the editing domain for the import
	 * @param reqIFContent the actual ReqIF content
	 * @return the command to be executed
	 */
	public Command importRequirements(EditingDomain editingDomain, ReqIF reqIFContent) {
		CompoundCommand cc = new CompoundCommand();
		for (SpecificationMapping specMapping : importConfiguration.getMappedSpecifications()) {
			Specification reqIfSpec = null;
			for (Specification spec : reqIFContent.getCoreContent().getSpecifications()) {
				if (spec.getLongName().equals(specMapping.getExternalIdentifier())) {
					reqIfSpec = spec;
				}
			}
			if (reqIfSpec != null) {
				importRequirementList(editingDomain, cc, specMapping.getSpecification().getRequirements(), reqIfSpec.getChildren());
			} else {
				Activator.getDefault().getLog().error("One mapped specification not found in ReqIF file!");
			}
			
		}
		return cc;
	}
	
	/**
	 * Import a list of ReqIF requirements by checking if these already exist locally
	 * 
	 * @param editingDomain the editing domain for the import
	 * @param cc The compound command in which commands should be added
	 * @param reqList the list of local requirements on the current level
	 * @param reqIfSpecificationList the list of ReqIF requirements on the current level 
	 * @return the command to be executed for import
	 */
	protected Command importRequirementList(EditingDomain editingDomain, CompoundCommand cc, IBeanList<RequirementObject> reqList, EList<SpecHierarchy> reqIfSpecificationList) {
		for (SpecHierarchy rootChild : reqIfSpecificationList) {
			
			RequirementObject current = reqIfUtils.findExisting(reqList, rootChild);
			
			// Import atomic requirements first
			if (rootChild.getChildren() == null || rootChild.getChildren().isEmpty()) {
				if (current == null) {
					Requirement newReq = createRequirementBase(rootChild.getObject().getType());
					createSpecHierarchyRequirement(newReq, rootChild);
					newReq.updateNameFromAttributes();
					cc.append(reqList.add(editingDomain, newReq));
				} else {
					reImportSpecHierarchyRequirement(editingDomain, cc, (Requirement) current, rootChild);
				}
			} else {
				// Import requirements which have further children
				if (current == null) {
					RequirementGroup newGroup = new RequirementGroup(concept);
					createSpecHierarchyGroup(newGroup, rootChild);
					newGroup.setName(reqIfUtils.getReqIFRequirementName(rootChild));
					cc.append(reqList.add(editingDomain, newGroup));
				} else {
					importRequirementList(editingDomain, cc, ((RequirementGroup) current).getChildren(), rootChild.getChildren());
				}
			}
		}
		return cc;
	}
	
	/**
	 * Import a requirement list into a new local group (->initial import)
	 * 
	 * @param reqGroup the new local requirement group
	 * @param reqIfSpecificationList the ReqIF container of the list of requirements 
	 */
	protected void createSpecHierarchyGroup(RequirementGroup reqGroup, SpecHierarchy reqIfSpecificationList) {
		reqGroup.setName(reqIfSpecificationList.getObject().getType().getLongName());
		
		for (SpecHierarchy spec : reqIfSpecificationList.getChildren()) {
			if (spec.getChildren() == null || spec.getChildren().isEmpty()) {
				Requirement newRequirement = createRequirementBase(spec.getObject().getType());
				createSpecHierarchyRequirement(newRequirement, spec);
				newRequirement.updateNameFromAttributes();
				reqGroup.getChildren().add(newRequirement);
			} else {
				RequirementGroup newGroup = new RequirementGroup(concept);
				createSpecHierarchyGroup(newGroup, spec);
				newGroup.setName(reqIfUtils.getReqIFRequirementName(spec));
				reqGroup.getChildren().add(newGroup);
			}
		}
	}
	
	
	/**
	 * Import a ReqIF requirement by creating a new local requirement (-> initial import)
	 * 
	 * @param conceptRequirement the local requirement (not persisted yet)
	 * @param reqIfRequirement the ReqIF requirement
	 */
	protected void createSpecHierarchyRequirement(Requirement conceptRequirement, SpecHierarchy reqIfRequirement) {
		SpecObject reqObject = reqIfRequirement.getObject();
		// Import values
		for (AttributeValue att : reqObject.getValues()) {
			setAttributeValue(conceptRequirement, att);
		}
		
		// Fill up other, emtpy values
		RequirementType type = conceptRequirement.getReqType();
		for (RequirementAttribute attDef : type.getAttributes()) {
			String attName = de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue.ATTRIBUTE_NAME_PREFIX + attDef.getName();
			if (!reqIfUtils.contains(conceptRequirement.getElements(), attName)) {
				de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue attValue = new de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue(concept);
				attValue.setAttType(attDef);
				attValue.setName(attName);
				conceptRequirement.getElements().add(attValue);
			}
		}
	}
	
	/**
	 * Import a ReqIF requirement into an existing local requirement
	 * 
	 * @param editingDomain the editing domain for the import
	 * @param conceptRequirement the local requirement
	 * @param hierarchyLevel the ReqIF requirement
	 * @return the command to be executed for import
	 */
	protected Command reImportSpecHierarchyRequirement(EditingDomain editingDomain, CompoundCommand cc, Requirement conceptRequirement, SpecHierarchy hierarchyLevel) {
		SpecObject reqObject = hierarchyLevel.getObject();
		for (AttributeValue att : reqObject.getValues()) {
			setAttributeValue(editingDomain, cc, conceptRequirement, att);
		}
		return cc;
	}
	
	/**
	 * Set the attribute value from the corresponding ReqIF attribute value definition
	 * 
	 * @param editingDomain the editing domain for the import
	 * @param cc The compound command in which commands should be added
	 * @param conceptRequirement the local requirement element
	 * @param attvalue the ReqIF attribute value
	 * @return the command to be executed for import
	 */
	protected Command setAttributeValue(EditingDomain editingDomain, CompoundCommand cc, Requirement conceptRequirement, AttributeValue attvalue) {
		StringBuilder newValue = new StringBuilder();
		StringBuilder newFormattedValue = new StringBuilder();
		AttributeDefinition attDef = switchAttributeValue(newValue, newFormattedValue, attvalue);
		
		de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue conceptAttributeValue = reqIfUtils.findExisting(conceptRequirement, attDef);
		RequirementAttribute conceptAttDef = reqIfUtils.findAttributeDefinition(conceptRequirement, attDef);
		
		// Check if local requirement type has the corresponding attribute; if not is has been deleted form the type and thus should not be imported
		if (conceptAttDef != null) {
			if (conceptAttributeValue == null) {
				conceptAttributeValue = new de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue(concept);
				conceptAttributeValue.setAttType(conceptAttDef);
				conceptAttributeValue.setName(de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue.ATTRIBUTE_NAME_PREFIX + conceptAttDef.getName());
				cc.append(conceptRequirement.getElements().add(editingDomain, conceptAttributeValue));
			} 
			if (reqIfUtils.hasNativeAttributeImpl(attDef)) { 
				cc.append(reqIfUtils.setNativeRequirementAttributeValue(editingDomain, conceptRequirement, attvalue, attDef));
			} else {
				cc.append(conceptAttributeValue.setValue(editingDomain, newValue.toString()));
				cc.append(conceptAttributeValue.setFormattedValue(editingDomain, newFormattedValue.toString()));
			}
		}
		return cc;
	}
	
	/**
	 * Set the attribute value from the corresponding ReqIF attribute value definition into a new local requirement
	 * 
	 * @param conceptRequirement the local requirement element
	 * @param attvalue the ReqIF attribute value
	 */
	protected void setAttributeValue(Requirement conceptRequirement, AttributeValue attvalue) {
		StringBuilder newValue = new StringBuilder();
		StringBuilder newFormattedValue = new StringBuilder();
		AttributeDefinition attDef = switchAttributeValue(newValue, newFormattedValue, attvalue);
		RequirementAttribute conceptAttDef = reqIfUtils.findAttributeDefinition(conceptRequirement, attDef);
		
		// Check if attribute definition exist locally, otherwise it was deleted and thus should not be imported
		if (conceptAttDef != null) {
			// Create and add a new attribute value into requirement
			de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue conceptAttributeValue = new de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue(concept);
			conceptAttributeValue.setName(de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue.ATTRIBUTE_NAME_PREFIX + conceptAttDef.getName());
			conceptAttributeValue.setAttType(conceptAttDef);
			
			// Set the value
			if (reqIfUtils.hasNativeAttributeImpl(attDef)) { 
				reqIfUtils.setNativeRequirementAttributeValue(conceptRequirement, attvalue, attDef);
			} else {
				conceptAttributeValue.setValue(newValue.toString());
				conceptAttributeValue.setFormattedValue(newFormattedValue.toString());
			}
			conceptRequirement.getElements().add(conceptAttributeValue);
		}
	}

	/**
	 * Import the requirement types specified in the ReqIF content
	 * 
	 * @param editingDomain the editing domain for the import
	 * @param reqIFContent the actual ReqIF content
	 * @return the command to be executed
	 */
	public Command importRequirementTypes(EditingDomain editingDomain, ReqIF reqIFContent) {
		this.reqIfContent = reqIFContent;
		CompoundCommand cc = new CompoundCommand();
		RequirementsConfiguration typeContainer = importConfiguration.getTypeDefinitionsContainer();
		ReqIFContent importContent = reqIFContent.getCoreContent();

		for (SpecType type : importContent.getSpecTypes().stream().
					filter(type -> type instanceof SpecObjectType).collect(Collectors.toList())) {
			SpecObjectType reqIfRequirementType = (SpecObjectType) type; // List is filtered to only contain spec object types
			String reqTypeName = reqIfUtils.cleanAttName(reqIfRequirementType.getLongName());
			
			// We're not overwriting the requirement types to ensure these can be customized
			if (!reqIfUtils.contains(typeContainer.getTypeDefinitions(), reqTypeName)) {
				RequirementType conceptRequirementType = new RequirementType(concept);
				conceptRequirementType.setName(reqHelper.cleanEntityName(reqIfRequirementType.getLongName()));
				
				for (AttributeDefinition reqIfAttDef : reqIfRequirementType.getSpecAttributes()) {
					
					if (!reqIfUtils.hasNativeAttributeImpl(reqIfAttDef)) {
						RequirementAttribute conceptAttType = new RequirementAttribute(concept);
						String attDefName = reqIfUtils.cleanAttName(reqIfAttDef.getLongName());
						conceptAttType.setName(attDefName);
						configureAttributeType(conceptAttType, reqIfAttDef);
						Integer index = reqIfUtils.nativeIndex(reqIfAttDef);
						if (index == null) {
							conceptRequirementType.getAttributes().add(conceptAttType);
						} else {
							conceptRequirementType.getAttributes().add(index, conceptAttType);
						}
						
					}
					
				}
				cc.append(typeContainer.getTypeDefinitions().add(editingDomain, conceptRequirementType));
			}
		}
		return cc;
	}
	
	/**
	 * Import the requirement links specified in the ReqIF content
	 * 
	 * @param editingDomain the editing domain for the import
	 * @param reqIFContent the actual ReqIF content
	 * @return the command to be executed
	 */
	public Command importRequirementLinks(EditingDomain editingDomain, ReqIF reqIFContent) {
		CompoundCommand cc = new CompoundCommand();
		for (SpecRelation relation : reqIFContent.getCoreContent().getSpecRelations()) {
			importRequirementLink(editingDomain, cc, relation);
		}
		return cc;
	}
	
	/**
	 * Import a ReqIF relation into local requirements
	 * 
	 * @param editingDomain the editing domain for the import
	 * @param command the compound command
	 * @param relation the relation to be imported
	 */
	public void importRequirementLink(EditingDomain editingDomain, CompoundCommand command, SpecRelation relation) {
		if (relation.getSource() != null || relation.getTarget() != null || relation.getType() != null) {
			SpecObject target;
			SpecObject source;
			try {
				target = relation.getTarget();
				source = relation.getSource();
			} catch (ClassCastException e) {
				Activator.getDefault().getLog().info("Could not resolve relateion target / source reference");
				return;
			}
			String sourceReqName = Requirement.REQUIREMENT_NAME_PREFIX + reqIfUtils.getReqIFRequirementIdentifier(source);
			String targetReqName = Requirement.REQUIREMENT_NAME_PREFIX + reqIfUtils.getReqIFRequirementIdentifier(target);
			String linkName = Requirement.REQUIREMENT_NAME_PREFIX + reqIfUtils.getReqIFRequirementIdentifier(relation.getSource()) 
					+ relation.getType().getLongName() 
					+ Requirement.REQUIREMENT_NAME_PREFIX + reqIfUtils.getReqIFRequirementIdentifier(relation.getTarget());
			String linkTypeName = relation.getType().getLongName();
			RequirementsSpecification containerSpec = null;
			Requirement localSourceRequirement = null;
			Requirement localTargetRequirement = null;
			for (SpecificationMapping mappedSpec : importConfiguration.getMappedSpecifications()) {
				RequirementsSpecification spec = mappedSpec.getSpecification();
				Requirement localSourceRequirementTmp = reqHelper.findRequirement(spec.getRequirements(), sourceReqName, true);
				Requirement localTargetRequirementTmp = reqHelper.findRequirement(spec.getRequirements(), targetReqName, true);
				if (localSourceRequirementTmp != null) {
					localSourceRequirement = localSourceRequirementTmp;
					containerSpec = spec;
				}
				if (localTargetRequirementTmp != null) {
					localTargetRequirement = localTargetRequirementTmp;
				}
			}
			// Check if relation is relevant for imported specifications, if not ignore it
			if (containerSpec != null && localSourceRequirement != null && localTargetRequirement != null) {
				RequirementLink localLink = (RequirementLink) reqIfUtils.findExisting(containerSpec.getLinks(), linkName);
				RequirementsConfiguration typeContainer = importConfiguration.getTypeDefinitionsContainer();
				RequirementLinkType localLinkType = (RequirementLinkType) reqIfUtils.findExisting(typeContainer.getLinkTypeDefinitions(), linkTypeName);
				if (localLinkType == null) {
					localLinkType = new RequirementLinkType(concept);
					localLinkType.setName(linkTypeName);
					localLinkType.setLinkDescription(relation.getType().getDesc());
					command.append(typeContainer.getLinkTypeDefinitions().add(editingDomain, localLinkType));
				}
				if (localLink == null) {
					localLink = new RequirementLink(concept);
					localLink.setName(linkName);
					localLink.setSubject(localSourceRequirement);
					localLink.getTargets().add(localTargetRequirement);
					localLink.setType(localLinkType);
					command.append(containerSpec.getLinks().add(editingDomain, localLink));
				} else {
					if (!localLink.getTargets().contains(localTargetRequirement)) {
						command.append(localLink.setType(editingDomain, localLinkType));
						command.append(localLink.getTargets().add(editingDomain, localTargetRequirement));
					}
				}
			}
		}
	}
	
	/**
	 * Configure the type of the requirement attribute
	 * 
	 * @param conceptAttType the concept requirement attribute type to be configured
	 * @param reqIfAttDef the ReqIF attribute definition
	 */
	protected void configureAttributeType(RequirementAttribute conceptAttType, AttributeDefinition reqIfAttDef) {
		if (reqIfUtils.isIdentifier(reqIfAttDef)) {
			conceptAttType.setType(RequirementAttribute.TYPE_Identifier_NAME);
		} else if (reqIfAttDef instanceof AttributeDefinitionString) {
			conceptAttType.setType(RequirementAttribute.TYPE_String_NAME);
		} else if (reqIfAttDef instanceof AttributeDefinitionBoolean) {
			conceptAttType.setType(RequirementAttribute.TYPE_Boolean_NAME);
		} else if (reqIfAttDef instanceof AttributeDefinitionDate) {
			conceptAttType.setType(RequirementAttribute.TYPE_Date_NAME);
		} else if (reqIfAttDef instanceof AttributeDefinitionReal) {
			conceptAttType.setType(RequirementAttribute.TYPE_Real_NAME);
		} else if (reqIfAttDef instanceof AttributeDefinitionInteger) {
			conceptAttType.setType(RequirementAttribute.TYPE_Integer_NAME);
		} else if (reqIfAttDef instanceof AttributeDefinitionXHTML) {
			conceptAttType.setType(RequirementAttribute.TYPE_String_NAME);
		} else if (reqIfAttDef instanceof AttributeDefinitionEnumeration) {
			conceptAttType.setType(RequirementAttribute.TYPE_Enumeration_NAME);
			AttributeDefinitionEnumeration attributeDefinitionEnumeration = (AttributeDefinitionEnumeration) reqIfAttDef;
			DatatypeDefinitionEnumeration enumerationType = attributeDefinitionEnumeration.getType();
			conceptAttType.getEnumeration().setName(reqIfUtils.cleanAttName(enumerationType.getLongName()));
			for (EnumValue value : enumerationType.getSpecifiedValues()) {
				EnumerationLiteral literal = new EnumerationLiteral(concept);
				literal.setName(reqIfUtils.cleanAttName(value.getLongName()));
				conceptAttType.getEnumeration().getLiterals().add(literal);
			}
		} else {
			conceptAttType.setType(RequirementAttribute.TYPE_String_NAME);
		}
	}
	
	/**
	 * Switch over the attribute value implementation and set the value into the value and formatted value Strings
	 * 
	 * @param value the value String element in which the ReqIF attribute value should be inserted
	 * @param formattedValue the formatted String element in which the ReqIF attribute HTML value should be inserted
	 * @param attValue the ReqIF attribute value
	 * @return the attribute definition
	 */
	protected AttributeDefinition switchAttributeValue(StringBuilder value, StringBuilder formattedValue, AttributeValue attValue) {
		AttributeDefinition attDef = null;
		if (attValue instanceof AttributeValueString) {
			AttributeValueString attvalueString = (AttributeValueString) attValue;
			attDef = attvalueString.getDefinition();
			value.append(attvalueString.getTheValue());
		} else if (attValue instanceof AttributeValueBoolean) {
			AttributeValueBoolean attValueBoolean = (AttributeValueBoolean) attValue;
			attDef = attValueBoolean.getDefinition();
			value.append(attValueBoolean.isTheValue());
		} else if (attValue instanceof AttributeValueDate) {
			AttributeValueDate attValueDate = (AttributeValueDate) attValue;
			attDef = attValueDate.getDefinition();
			value.append(attValueDate.getTheValue().toZonedDateTime().toLocalDateTime().toString());
		} else if (attValue instanceof AttributeValueEnumeration) {
			AttributeValueEnumeration attValueEnumeration = (AttributeValueEnumeration) attValue;
			attDef = attValueEnumeration.getDefinition();
			value.append(reqHelper.cleanEntityName(attValueEnumeration.getValues().get(0).getLongName()));
		} else if (attValue instanceof AttributeValueReal) {
			AttributeValueReal attDefinitionReal = (AttributeValueReal) attValue;
			attDef = attDefinitionReal.getDefinition();
			value.append(String.valueOf(attDefinitionReal.getTheValue()));
		} else if (attValue instanceof AttributeValueInteger) {
			AttributeValueInteger attValueInteger = (AttributeValueInteger) attValue;
			attDef = attValueInteger.getDefinition();
			value.append(attValueInteger.getTheValue());
		} else if (attValue instanceof AttributeValueXHTML) {
			AttributeValueXHTML attValueXHTML = (AttributeValueXHTML) attValue;
			attDef = attValueXHTML.getDefinition();
			String xhtmlString;
			try {
				xhtmlString = ReqIF10XhtmlUtil.getXhtmlString(attValueXHTML.getTheValue());
				value.append(xhtmlString.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "").replaceAll("\\r|\\n", "")); // Remove HTML mark-up
				formattedValue.append(xhtmlString);
			} catch (IOException e) {
				Activator.getDefault().getLog().error(ReqIfUtils.XHTML_PARSER_ERROR, e);
			}
		}
		return attDef;
	}
	
	/**
	 * Instantiate a new requirement from a ReqIF requirement type
	 * 
	 * @param reqIfType the ReqIF requirement type
	 * @return the new requirement
	 */
	protected Requirement createRequirementBase(SpecObjectType reqIfType) {
		Requirement newRequirement = new Requirement(concept);
		String reqTypeName = reqHelper.cleanEntityName(reqIfType.getLongName());
		for (RequirementType reqType : importConfiguration.getTypeDefinitionsContainer().getTypeDefinitions()) {
			if (reqType.getName().equals(reqTypeName)) {
				newRequirement.setReqType(reqType);
			}
		}
		return newRequirement;
	}
	
	/**
	 * Create a command that persists the mapping created in the UI to the model so that it can be reused
	 * 
	 * @param editingDomain the editing domain
	 * @param mapping the mapping from UI
	 * @param reqIFContent the ReqIF content
	 * @param configurationContainer the container for the new configuration element
	 * @return the command to be executed
	 */
	public Command persistSpecificationMapping(EditingDomain editingDomain, Map<Specification, StructuralElementInstance> mapping, ReqIF reqIFContent, List<String> requirementTypeList, RequirementsConfigurationCollection configurationContainer) {
		importConfiguration = new ImportConfiguration(concept);
		importConfiguration.setName(IMPORT_CONFIC_PREFIX + getImportTitle());

		// Specify which types are supposed to be imported
		for (String typeKey : requirementTypeList) {
			ArrayInstance ai = importConfiguration.getSelectedTypeKeysBean().getArrayInstance();
			ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
			vpi.setValue(typeKey);
			vpi.setType(ai.getType());
			ai.getArrayInstances().add(vpi);
		}
		
		CompoundCommand cc = new CompoundCommand();
		for (Entry<Specification, StructuralElementInstance> entry : mapping.entrySet()) {
			Specification spec = entry.getKey();  // According to spotbugs this way of iterating is faster than by using keySet()
			
			// Create specification
			BeanStructuralElementInstance seiBean = new BeanStructuralElementInstance(mapping.get(spec));
			RequirementsSpecification conceptSpec = new RequirementsSpecification(concept);
			String externalIdentifier = spec.getLongName();
			conceptSpec.setName(reqHelper.cleanEntityName(spec.getLongName()));
			cc.append(seiBean.add(editingDomain, conceptSpec));
			
			//Add to mapping
			SpecificationMapping specMapping = new SpecificationMapping(concept);
			specMapping.setExternalIdentifier(externalIdentifier);
			specMapping.setSpecification(conceptSpec);
			importConfiguration.getMappedSpecifications().add(specMapping);
		}
		
		cc.append(configurationContainer.add(editingDomain, importConfiguration));
		return cc;
	}
	
	/**
	 * Add the configuration element for new requirement types to the import configuration. If non is specified, a new type container is created
	 * 
	 * @param editingDomain the editing domain
	 * @param typeContainer an existing type container or null if a new one should be created
	 * @return the command to be executed
	 */
	public Command persistRequirementTypeContainer(EditingDomain editingDomain, RequirementsConfiguration typeContainer) {
		if (typeContainer == null) {
			CompoundCommand cc = new CompoundCommand();
			RequirementsConfiguration newTypeContainer = new RequirementsConfiguration(concept);
			newTypeContainer.setName(TYPE_CONTAINER_PREFIX + getImportTitle());
			cc.append(configurationContainer.add(editingDomain, newTypeContainer));
			cc.append(importConfiguration.setTypeDefinitionsContainer(editingDomain, newTypeContainer));
			return cc;
		}
		return importConfiguration.setTypeDefinitionsContainer(editingDomain, typeContainer);
	}
	
	protected String getImportTitle() {
		return this.reqIfContent.eResource().getURI().trimFileExtension().lastSegment();
	}

}
