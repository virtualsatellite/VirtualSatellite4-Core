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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
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
	protected Concept reqConcept;
	
	protected static final String REQ_TYPE_NAME = "CCVImportedRequirementType";

	/**
	 * The function to load requirement contents from a given SCV file to an exiting
	 * requirement specification
	 * 
	 * @param editingDomain
	 *            the editing domain in which the import should be performed
	 * @param csvContentMatrix
	 *            the CSV content matrix
	 * @param targetSpecificationList
	 *            the specification list where the requirements should be imported
	 *            into
	 * @param importType
	 *            the requirement type of the imported requirements
	 * @return the compound command that can be performed to import the requirements
	 */
	public CompoundCommand loadRequirements(EditingDomain editingDomain, List<List<String>> csvContentMatrix,
			IBeanList<RequirementObject> targetSpecificationList, RequirementType importType) {

		this.editingDomain = editingDomain;
		if (reqConcept == null) {
			this.reqConcept = getReqConcept(importType.getTypeInstance());
		}

		CompoundCommand importCommand = new CompoundCommand();

		// Remove the header... we don't need it anymore
		csvContentMatrix.remove(0);

		// Read the actual requirements
		for (List<String> req : csvContentMatrix) {
			Requirement newReqElement = createRequirement(importCommand, targetSpecificationList, importType);

			for (RequirementAttribute attDef : importType.getAttributes()) {
				int currentIndex = importType.getAttributes().indexOf(attDef);
				String value = "";
				if (currentIndex < req.size()) {
					value = req.get(currentIndex);
				}
				createAttributeValue(importCommand, newReqElement, value,
						attDef);
			}
		}

		return importCommand;

	}

	/**
	 * The function to load requirement contents from a given SCV file to an exiting
	 * requirement specification
	 * 
	 * @param editingDomain
	 *            the editing domain in which the import should be performed
	 * @param csvContentMatrix
	 *            the CSV content matrix
	 * @param targetSpecificationList
	 *            the specification list where the requirements should be imported
	 *            into
	 * @param newImportTypeContainer
	 *            the requirement configuration in which the new type element should
	 *            be added into
	 * @return the compound command that can be performed to import the requirements
	 */
	public CompoundCommand loadRequirements(EditingDomain editingDomain, List<List<String>> csvContentMatrix,
			IBeanList<RequirementObject> targetSpecificationList, RequirementsConfiguration newImportTypeContainer) {

		this.editingDomain = editingDomain;
		this.reqConcept = getReqConcept(newImportTypeContainer.getTypeInstance());

		// Create a requirement type for the import
		CompoundCommand importCommand = new CompoundCommand();
		RequirementType reqType = createReqType(importCommand, newImportTypeContainer, csvContentMatrix.get(0));

		importCommand.append(loadRequirements(editingDomain, csvContentMatrix, targetSpecificationList, reqType));

		return importCommand;

	}

	/**
	 * Create a requirement and set its type
	 * 
	 * @param importCommand
	 *            the command in which this operation should be contained in
	 * @param containerSpecificationList
	 *            the containing list of requirements
	 * @param reqType
	 *            the requirement type
	 * @return the new requirement
	 */
	protected Requirement createRequirement(CompoundCommand importCommand,
			IBeanList<RequirementObject> containerSpecificationList, RequirementType reqType) {
		Requirement requirement = new Requirement(reqConcept);
		requirement.setReqType(reqType);
		importCommand.append(containerSpecificationList.add(editingDomain, requirement));
		return requirement;
	}

	/**
	 * Create a attribute value within a requirement
	 * 
	 * @param importCommand
	 *            the command in which this operation should be contained in
	 * @param container
	 *            the containing requirement
	 * @param value
	 *            the value to be added to the attribute
	 * @param attDef
	 *            the type of the attribute
	 * @return the attribute value
	 */
	protected AttributeValue createAttributeValue(CompoundCommand importCommand, Requirement container, String value,
			RequirementAttribute attDef) {
		AttributeValue attValue = new AttributeValue(reqConcept);
		attValue.setValue(value);
		attValue.setAttType(attDef);
		importCommand.append(container.getElements().add(editingDomain, attValue));
		return attValue;
	}

	/**
	 * create a requirement type with a given number of attributes
	 * 
	 * @param importCommand
	 *            the import command which should contain this creation operation
	 * @param container
	 *            the container element in which the new type should be added into
	 * @param attributeNames
	 * 			  a list of the names of all requirement attributes
	 * @return the new requirement type
	 */
	protected RequirementType createReqType(CompoundCommand importCommand, RequirementsConfiguration container,
			List<String> attributeNames) {
		RequirementType newReqType = new RequirementType(reqConcept);
		newReqType.setName(REQ_TYPE_NAME);
		for (String attName : attributeNames) {
			RequirementAttribute attDef = new RequirementAttribute(reqConcept);
			attDef.setName(attName);
			attDef.setType(RequirementAttribute.TYPE_String_NAME);
			newReqType.getAttributes().add(attDef);
		}
		importCommand.append(container.getTypeDefinitions().add(editingDomain, newReqType));
		return newReqType;
	}


	/**
	 * Get the requirement concept from any model element within the same resource
	 * set
	 * 
	 * @param element
	 *            any model element in the same resource set
	 * @return the concept
	 */
	private Concept getReqConcept(EObject element) {
		Repository repository = VirSatResourceSet.getVirSatResourceSet(element).getRepository();
		ActiveConceptHelper activeConceptHelper = new ActiveConceptHelper(repository);
		return activeConceptHelper.getConcept(Activator.getPluginId());
	}

}
