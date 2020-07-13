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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationLiteral;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.util.RequirementHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * A class that imports requirements from a CSV file
 *
 */
public class RequirementsImporter {

	protected EditingDomain editingDomain;
	protected Concept reqConcept;

	private static final String REQ_TYPE_NAME = "CSVImportedRequirementType";
	private static final String REQ_EXTENSION_IDENTIFIER_PREFIX = "-extension";

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
	 * @param attributeMapping
	 *            the mapping of column index to attribute
	 * @param importType
	 *            the requirement type of the imported requirements
	 * @return the compound command that can be performed to import the requirements
	 */
	public CompoundCommand loadRequirements(EditingDomain editingDomain, List<List<String>> csvContentMatrix,
			IBeanList<RequirementObject> targetSpecificationList, Map<Integer, RequirementAttribute> attributeMapping,
			RequirementType importType) {

		this.editingDomain = editingDomain;
		if (reqConcept == null) {
			this.reqConcept = getReqConcept(importType.getTypeInstance());
		}

		CompoundCommand importCommand = new CompoundCommand();

		// Read the actual requirements
		for (int lineNumber = 0; lineNumber < csvContentMatrix.size(); lineNumber++) {
			List<String> req = csvContentMatrix.get(lineNumber);
			Requirement newReqElement = createRequirement(importType, lineNumber);

			addRepeatedValuesFromPreviousLine(csvContentMatrix, attributeMapping, lineNumber, req);
	
			for (int currentIndex = 0; currentIndex < req.size(); currentIndex++) {
				String attValue = req.get(currentIndex);
				RequirementAttribute mappedAttribute = attributeMapping.get(currentIndex);

				if (mappedAttribute != null) {
					setAttributeValue(newReqElement, attValue, mappedAttribute);
				}
			}
			newReqElement.updateNameFromAttributes();
			importCommand.append(targetSpecificationList.add(editingDomain, newReqElement));
		}

		return importCommand;

	}

	/**
	 * The function to load requirement contents from a given CSV file to an exiting
	 * requirement specification
	 * 
	 * @param editingDomain
	 *            the editing domain in which the import should be performed
	 * @param csvContentMatrix
	 *            the CSV content matrix
	 * @param targetSpecificationList
	 *            the specification list where the requirements should be imported
	 *            into
	 * @param attributeMapping
	 *            the mapping of column index to attribute
	 * @param newImportTypeContainer
	 *            the requirement configuration in which the new type element should
	 *            be added into
	 * @param nonPersistedType
	 *            A new requirement type that is not yet persisted
	 * @return the compound command that can be performed to import the requirements
	 */
	public CompoundCommand loadRequirements(EditingDomain editingDomain, List<List<String>> csvContentMatrix,
			IBeanList<RequirementObject> targetSpecificationList, Map<Integer, RequirementAttribute> attributeMapping,
			RequirementsConfiguration newImportTypeContainer, RequirementType nonPersistedType) {

		this.editingDomain = editingDomain;
		this.reqConcept = getReqConcept(newImportTypeContainer.getTypeInstance());

		// Create a requirement type for the import
		CompoundCommand importCommand = new CompoundCommand();
		fillEnumAttributes(nonPersistedType, csvContentMatrix, attributeMapping);
		importCommand.append(newImportTypeContainer.getTypeDefinitions().add(editingDomain, nonPersistedType));
		importCommand.append(loadRequirements(editingDomain, csvContentMatrix, targetSpecificationList, attributeMapping, nonPersistedType));

		return importCommand;
	}

	/**
	 * In case the first columns of data are empty, data from the previous data line have to be repeated. This method
	 * then fills these values into the current line
	 * 
	 * @param contentMatrix the matrix of requirements data
	 * @param attributeMapping the mapping of requirements
	 * @param lineNumber the current line number that is supposed to be filled up
	 * @param requirement the current requirement data
	 */
	private void addRepeatedValuesFromPreviousLine(List<List<String>> contentMatrix,
			Map<Integer, RequirementAttribute> attributeMapping, int lineNumber, List<String> requirement) {
		if (lineNumber > 0) {
			//If the first columns of a requirement are empty then values from previous lines are used
			for (int index = 0; index < requirement.size(); index++) {
				if (!requirement.get(index).equals("")) {
					break;
				}
				//Get value of last line
				String repeatedValueOldLine = contentMatrix.get(lineNumber - 1).get(index);
				//If attribute is an identifier then append extension to make data unique
				RequirementAttribute att = attributeMapping.get(index);
				if (att != null && att.getType().equals(RequirementAttribute.TYPE_Identifier_NAME)) {
					repeatedValueOldLine += REQ_EXTENSION_IDENTIFIER_PREFIX;
				}
				//Set the value
				requirement.set(index, repeatedValueOldLine);
			}
		}
	}
	
	/**
	 * Creates a requirement type from a list of attribute names. Does not create a
	 * command or persist the element yet
	 * 
	 * @param reqConcept
	 *            the active concept
	 * @param attributeNames
	 *            a list of attribute names
	 * @return the new type
	 */
	public RequirementType prepareRequirementType(Concept reqConcept, List<String> attributeNames) {
		this.reqConcept = reqConcept;
		RequirementType newReqType = new RequirementType(reqConcept);
		newReqType.setName(REQ_TYPE_NAME);
		for (String attName : attributeNames) {
			if (!attName.equals("")) {
				RequirementAttribute attDef = new RequirementAttribute(reqConcept);
				attDef.setName(attName.replace(" ", ""));
				attDef.setType(RequirementAttribute.TYPE_String_NAME);
				newReqType.getAttributes().add(attDef);
			}
		}
		return newReqType;
	}

	/**
	 * Create a requirement and set its type
	 * 
	 *            the containing list of requirements
	 * @param reqType
	 *            the requirement type
	 * @param lineNumber 
	 * 			  the line index of the requirement
	 * @return the new requirement
	 */
	protected Requirement createRequirement(RequirementType reqType, int lineNumber) {
		Requirement requirement = new Requirement(reqConcept);
		requirement.setReqType(reqType);
		requirement.setName(reqType.getName() + lineNumber);
		for (RequirementAttribute att : reqType.getAttributes()) {
			AttributeValue attValue = new AttributeValue(reqConcept);
			attValue.setName(att.getName());
			attValue.setAttType(att);
			requirement.getElements().add(attValue);
		}
		return requirement;
	}

	/**
	 * Create a attribute value within a requirement
	 * 
	 * @param requirement
	 *            the requirement to edit
	 * @param value
	 *            the value to be added to the attribute
	 * @param attDef the attribute definition
	 */
	protected void setAttributeValue(Requirement requirement, String value,
			RequirementAttribute attDef) {
		for (AttributeValue att : requirement.getElements()) {
			RequirementAttribute type = att.getAttType();
			if (type.equals(attDef)) {
				att.setValue(value);
			}
		}
	}
	
	/**
	 * Update a enumerations in a new requirement type from a set of CSV data
	 * @param type the not yet persisted requirement type
	 * @param csvContentMatrix the CSV data
	 * @param attributeMapping the mapping of column index to attribute
	 */
	protected void fillEnumAttributes(RequirementType type, List<List<String>> csvContentMatrix, Map<Integer, RequirementAttribute> attributeMapping) {
		
		for (Entry<Integer, RequirementAttribute> entry : attributeMapping.entrySet()) {
			RequirementAttribute att = entry.getValue();
			if (att.getType().equals(RequirementAttribute.TYPE_Enumeration_NAME)) {
				Set<String> enumerationLiteralValues = new HashSet<>();
				int columnIndexOfAttribute = entry.getKey();
				for (List<String> reg : csvContentMatrix) {
					if (reg.size() > columnIndexOfAttribute) {
						enumerationLiteralValues.add(reg.get(columnIndexOfAttribute));
					}
				}
				for (String literal : enumerationLiteralValues) {
					if (!literal.equals("")) {
						EnumerationLiteral literalBean = new EnumerationLiteral(reqConcept);
						literalBean.setName(new RequirementHelper().cleanEntityName(literal));
						att.getEnumeration().getLiterals().add(literalBean);
					}
				}
			}
		}
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
