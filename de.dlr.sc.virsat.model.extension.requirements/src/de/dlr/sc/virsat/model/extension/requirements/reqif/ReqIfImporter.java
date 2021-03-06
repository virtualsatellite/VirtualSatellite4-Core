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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.rmf.reqif10.AttributeDefinition;
import org.eclipse.rmf.reqif10.AttributeDefinitionBoolean;
import org.eclipse.rmf.reqif10.AttributeDefinitionDate;
import org.eclipse.rmf.reqif10.AttributeDefinitionEnumeration;
import org.eclipse.rmf.reqif10.AttributeDefinitionInteger;
import org.eclipse.rmf.reqif10.AttributeDefinitionReal;
import org.eclipse.rmf.reqif10.AttributeDefinitionString;
import org.eclipse.rmf.reqif10.AttributeDefinitionXHTML;
import org.eclipse.rmf.reqif10.DatatypeDefinitionEnumeration;
import org.eclipse.rmf.reqif10.EnumValue;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.ReqIFContent;
import org.eclipse.rmf.reqif10.SpecObjectType;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.rmf.reqif10.Specification;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationLiteral;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
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
	protected ImportConfiguration importConfiguration = null;
	protected RequirementsConfigurationCollection configurationContainer = null;
	protected Concept concept = null;
	protected ReqIF reqIfContent;
	protected List<INativeRequirementAttributeMapping> mappingImpls = new ArrayList<INativeRequirementAttributeMapping>();
	

	public void init(ReqIF reqIFContent, RequirementsConfigurationCollection configurationContainer) {
		this.concept = ActiveConceptHelper.getConcept(configurationContainer.getStructuralElementInstance().getType());
		this.reqIfContent = reqIFContent;
		this.configurationContainer = configurationContainer;
		registerAttributeMappingImpls();
	}
	
	public void init(ReqIF reqIFContent, ImportConfiguration configuration) {
		this.concept = ActiveConceptHelper.getConcept(configuration.getTypeInstance().getType());
		this.reqIfContent = reqIFContent;
		this.importConfiguration = configuration;
		this.configurationContainer = (RequirementsConfigurationCollection) configuration.getParent();
		registerAttributeMappingImpls();
	}
	
	protected void registerAttributeMappingImpls() {
		mappingImpls.add(new DoorsNGImportMapping());
	}
	
	/**
	 * Import the requirements specified in the ReqIF content
	 * @param editingDomain the editing domain for the import
	 * @param reqIFContent the actual ReqIF content
	 * @return the command to be executed
	 */
	public void importRequirements(EditingDomain editingDomain, ReqIF reqIFContent) {
		
	}
	
	/**
	 * Import the requirement types specified in the ReqIF content
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
			String reqTypeName = reqHelper.cleanEntityName(reqIfRequirementType.getLongName());
			
			// We're not overwriting the requirement types to ensure these can be customized
			if (!contains(typeContainer.getTypeDefinitions(), reqTypeName)) {
				RequirementType conceptRequirementType = new RequirementType(concept);
				conceptRequirementType.setName(reqHelper.cleanEntityName(reqIfRequirementType.getLongName()));
				
				for (AttributeDefinition reqIfAttDef : reqIfRequirementType.getSpecAttributes()) {
					
					if (!hasNativeAttributeImpl(reqIfAttDef)) {
						RequirementAttribute conceptAttType = new RequirementAttribute(concept);
						String attDefName = reqHelper.cleanEntityName(reqIfAttDef.getLongName());
						conceptAttType.setName(attDefName);
						configureAttributeType(conceptAttType, reqIfAttDef);
						conceptRequirementType.getAttributes().add(conceptAttType);
					}
					
				}
				cc.append(typeContainer.getTypeDefinitions().add(editingDomain, conceptRequirementType));
			}
		}
		return cc;
	}
	
	/**
	 * Create a command that persists the mapping created in the UI to the model so that it can be reused
	 * @param editingDomain the editing domain
	 * @param mapping the mapping from UI
	 * @param reqIFContent the ReqIF content
	 * @param configurationContainer the container for the new configuration element
	 * @return the command to be executed
	 */
	public Command persistSpecificationMapping(EditingDomain editingDomain, Map<Specification, StructuralElementInstance> mapping, ReqIF reqIFContent, RequirementsConfigurationCollection configurationContainer) {
		importConfiguration = new ImportConfiguration(concept);
		importConfiguration.setName(IMPORT_CONFIC_PREFIX + getImportTitle());
		
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
	
	/**
	 * Utility method to check if a list of named elements contains an element with a given name
	 * @param list the list of named beans
	 * @param name the name to search for
	 * @return weather the list contains an element with the given name
	 */
	protected boolean contains(IBeanList<? extends IBeanName> list, String name) {
		for (IBeanName namedElement : list) {
			if (namedElement.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Configure the type of the requirement attribute
	 * @param conceptAttType the concept requirement attribute type to be configured
	 * @param reqIfAttDef the ReqIF attribute definition
	 */
	protected void configureAttributeType(RequirementAttribute conceptAttType, AttributeDefinition reqIfAttDef) {
		if (reqIfAttDef instanceof AttributeDefinitionString) {
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
			conceptAttType.getEnumeration().setName(reqHelper.cleanEntityName(enumerationType.getLongName()));
			for (EnumValue value : enumerationType.getSpecifiedValues()) {
				EnumerationLiteral literal = new EnumerationLiteral(concept);
				literal.setName(reqHelper.cleanEntityName(value.getLongName()));
				conceptAttType.getEnumeration().getLiterals().add(literal);
			}
		} else {
			conceptAttType.setType(RequirementAttribute.TYPE_String_NAME);
		}
	}
	
	/**
	 * Check it a requirement attribute to be imported has a specified mapping to a native attribute implementation
	 * 
	 * @param reqIfAtt the attribute mapping to be checked
	 * @return true if native mapping is defined, false otherwise
	 */
	protected boolean hasNativeAttributeImpl(AttributeDefinition reqIfAtt) {
		for (INativeRequirementAttributeMapping mappingImpl : mappingImpls) {
			if (mappingImpl.isNativeAttribute(reqIfAtt)) {
				return true;
			}
		}
		return false;
	}

}
