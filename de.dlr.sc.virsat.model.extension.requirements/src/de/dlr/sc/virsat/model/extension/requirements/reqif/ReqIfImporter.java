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

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.Specification;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
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
	

	public void init(ReqIF reqIFContent, RequirementsConfigurationCollection configurationContainer) {
		this.concept = ActiveConceptHelper.getConcept(configurationContainer.getStructuralElementInstance().getType());
		this.reqIfContent = reqIFContent;
		this.configurationContainer = configurationContainer;
	}
	
	public void init(ReqIF reqIFContent, ImportConfiguration configuration) {
		this.concept = ActiveConceptHelper.getConcept(configuration.getTypeInstance().getType());
		this.reqIfContent = reqIFContent;
		this.importConfiguration = configuration;
		this.configurationContainer = (RequirementsConfigurationCollection) configuration.getParent();
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
	
	public Command importRequirementTypes(EditingDomain editingDomain, ReqIF reqIFContent) {
		CompoundCommand cc = new CompoundCommand();
		return cc;
	}
	
	protected String getImportTitle() {
		return this.reqIfContent.eResource().getURI().trimFileExtension().lastSegment();
	}

}
