/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.csv;

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * A class that imports requirements from a CSV file
 *
 */
public class CsvRequirementsImporter {

	protected EditingDomain editingDomain;
	
	/**
	 * The function to load requirement contents from a given SCV file to an exiting
	 * requirement specification
	 * 
	 * @param editingDomain the editing domain in which the import should be performed
	 * @param csvContentMatrix the CSV content matrix
	 * @param targetSpecificationList the specification list where the requirements should be imported into
	 * @param importType the requirement type of the imported requirements
	 * @return the compound command that can be performed to import the requirements
	 */
	public CompoundCommand loadRequirements(EditingDomain editingDomain, List<List<String>> csvContentMatrix,
			IBeanList<RequirementObject> targetSpecificationList, RequirementType importType) {
		
		this.editingDomain = editingDomain;
		CompoundCommand importCommand = new CompoundCommand();
		

		return importCommand;

	}
	
	/**
	 * The function to load requirement contents from a given SCV file to an exiting
	 * requirement specification
	 * 
	 * @param editingDomain the editing domain in which the import should be performed
	 * @param csvContentMatrix the CSV content matrix
	 * @param targetSpecificationList the specification list where the requirements should be imported into
	 * @param newImportTypeContainer the requirement configuration in which the new type element should be added into
	 * @return the compound command that can be performed to import the requirements
	 */
	public CompoundCommand loadRequirements(EditingDomain editingDomain, List<List<String>> csvContentMatrix,
			IBeanList<RequirementObject> targetSpecificationList, RequirementsConfiguration newImportTypeContainer) {

		this.editingDomain = editingDomain;
		CompoundCommand importCommand = new CompoundCommand();
		
		int maxAttributeLength = 0;
		for (List<String> req : csvContentMatrix) {
			if (req.size() > maxAttributeLength) {
				maxAttributeLength = req.size();
			}
		}
		
		RequirementType reqType = createReqType(importCommand, newImportTypeContainer, maxAttributeLength);
		importCommand.append(
				loadRequirements(editingDomain, csvContentMatrix, targetSpecificationList, reqType));

		return importCommand;

	}
	
	
	/**
	 * create a requirement type with a given number of attributes
	 * 
	 * @param importCommand the import command which should contain this creation operation
	 * @param container the container element in which the new type should be added into
	 * @param numberAttributes the number of attributes
	 * @return the new requirement type
	 */
	protected RequirementType createReqType(CompoundCommand importCommand, RequirementsConfiguration container, int numberAttributes) {
		
		Repository repository = VirSatResourceSet.getVirSatResourceSet(container.getTypeInstance())
				.getRepository();
		ActiveConceptHelper activeConceptHelper = new ActiveConceptHelper(repository);
		Concept reqConcept = activeConceptHelper.getConcept(Activator.getPluginId());
		RequirementType newReqType = new RequirementType(reqConcept);
		importCommand.append(container.getTypeDefinitions().add(editingDomain, newReqType));
		return newReqType;
		
	}

}
