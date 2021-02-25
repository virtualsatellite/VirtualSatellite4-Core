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
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.model.extension.requirements.model.SpecificationMapping;

/**
 * @author fran_tb
 *
 */
public class ReqIfImporter {
	
	public Command persistSpecificationMapping(EditingDomain editingDomain, Map<Specification, StructuralElementInstance> mapping, ReqIF reqIFContent, RequirementsConfigurationCollection configurationContainer) {
		Concept concept = ActiveConceptHelper.getConcept(configurationContainer.getStructuralElementInstance().getType());
		CompoundCommand cc = new CompoundCommand();
		ImportConfiguration configuration = new ImportConfiguration(concept);
		
		for (Specification spec : mapping.keySet()) {
			
			// Create specification
			BeanStructuralElementInstance seiBean = new BeanStructuralElementInstance(mapping.get(spec));
			RequirementsSpecification conceptSpec = new RequirementsSpecification(concept);
			String externalIdentifier = spec.getLongName();
			conceptSpec.setName(spec.getLongName());
			cc.append(seiBean.add(editingDomain, conceptSpec));
			
			//Add to mapping
			SpecificationMapping specMapping = new SpecificationMapping(concept);
			specMapping.setExternalIdentifier(externalIdentifier);
			specMapping.setSpecification(conceptSpec);
			configuration.getMappedSpecifications().add(specMapping);
		}
		
		cc.append(configurationContainer.add(editingDomain, configuration));
		return cc;
	}
	

}
