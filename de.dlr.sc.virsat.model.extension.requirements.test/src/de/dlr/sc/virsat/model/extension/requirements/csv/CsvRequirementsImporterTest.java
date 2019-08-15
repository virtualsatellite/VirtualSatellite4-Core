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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationLiteral;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;

/**
 * The test class for the requirements importer
 *
 */
public class CsvRequirementsImporterTest extends AConceptProjectTestCase {

	private static final String NAME_HEADER_NAME = "Name";
	private static final String NAME_HEADER_ID = "ID";
	private static final String NAME_HEADER_DES = "Description";
	private static final String NAME_HEADER_VALUE = "Value";
	
	private static final String ATT_1_NAME = "att1Name";
	private static final String ATT_2_NAME = "att2Name";
	private static final String ATT_3_NAME = "att3Name";

	private static final String ATT_1_ID = "att1ID";
	private static final String ATT_2_ID = "att2ID";
	private static final String ATT_3_ID = "att3ID";

	private static final String ATT_1_DESCRIPTION = "att1des";
	private static final String ATT_2_DESCRIPTION = "att2des";
	private static final String ATT_3_DESCRIPTION = "att3des";

	private static final String ATT_1_CATEGORY = "AOCS";
	private static final double ATT_2_VALUE = 322.23;

	private Concept requirementsConcept;

	private RequirementAttribute attributeName;
	private RequirementAttribute attributeID;
	private RequirementAttribute attributeDescription;
	private RequirementAttribute attributeCategory;
	private RequirementAttribute attributeValue;
	
	@Before
	public void setUp() throws CoreException {
		requirementsConcept = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.requirements");
		
		addEditingDomainAndRepository();
	}

	@Test
	public void testLoadRequirementsToRequirementSpecification() {

		RequirementsSpecification targetSpec = new RequirementsSpecification(requirementsConcept);
		RequirementType reqImportType = createReqTypeWith3Attributes();
		List<List<String>> csvContentMatrix = createCSVContentMatrix();

		CsvRequirementsImporter importer = new CsvRequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				reqImportType);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberSimpleAtt = 3;
		Requirement importedReq1 = (Requirement) targetSpec.getRequirements().get(0);
		Requirement importedReq2 = (Requirement) targetSpec.getRequirements().get(1);
		Requirement importedReq3 = (Requirement) targetSpec.getRequirements().get(2);
		
		assertEquals("Requirement type not set", importedReq1.getReqType(), reqImportType);
		assertEquals("Requirement type not set", importedReq2.getReqType(), reqImportType);
		assertEquals("Requirement type not set", importedReq3.getReqType(), reqImportType);
		
		assertEquals("Should only import first relvant elements for specified types", 
				importedReq1.getElements().size(), numberSimpleAtt);
		assertEquals("Attribute not contained", importedReq1.getElements().get(0).getValue(), ATT_1_NAME);
		assertEquals("Attribute not contained", importedReq1.getElements().get(1).getValue(), ATT_1_ID);
		assertEquals("Attribute not contained", importedReq1.getElements().get(2).getValue(), ATT_1_DESCRIPTION);
		
		assertEquals("Should only import first relvant elements for specified types", 
				importedReq2.getElements().size(), numberSimpleAtt);
		assertEquals("Attribute not contained", importedReq2.getElements().get(0).getValue(), ATT_2_NAME);
		assertEquals("Attribute not contained", importedReq2.getElements().get(1).getValue(), ATT_2_ID);
		assertEquals("Attribute not contained", importedReq2.getElements().get(2).getValue(), ATT_2_DESCRIPTION);
		
		assertEquals("Should only import first relvant elements for specified types", 
				importedReq3.getElements().size(), numberSimpleAtt);
		assertEquals("Attribute not contained", importedReq3.getElements().get(0).getValue(), ATT_3_NAME);
		assertEquals("Attribute not contained", importedReq3.getElements().get(1).getValue(), ATT_3_ID);
		assertEquals("Attribute not contained", importedReq3.getElements().get(2).getValue(), ATT_3_DESCRIPTION);

	}
	
	@Test
	public void testLoadRequirementsToRequirementGroup() {

		RequirementGroup targetSpec = new RequirementGroup(requirementsConcept);
		RequirementType reqImportType = createReqTypeWith3Attributes();
		List<List<String>> csvContentMatrix = createCSVContentMatrix();

		CsvRequirementsImporter importer = new CsvRequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getChildren(),
				reqImportType);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberSimpleAtt = 3;
		Requirement importedReq1 = (Requirement) targetSpec.getChildren().get(0);
		Requirement importedReq2 = (Requirement) targetSpec.getChildren().get(1);
		Requirement importedReq3 = (Requirement) targetSpec.getChildren().get(2);
		
		assertEquals("Requirement type not set", importedReq1.getReqType(), reqImportType);
		assertEquals("Requirement type not set", importedReq2.getReqType(), reqImportType);
		assertEquals("Requirement type not set", importedReq3.getReqType(), reqImportType);
		
		assertEquals("Should only import first relvant elements for specified types", 
				importedReq1.getElements().size(), numberSimpleAtt);
		assertEquals("Attribute not contained", importedReq1.getElements().get(0).getValue(), ATT_1_NAME);
		assertEquals("Attribute not contained", importedReq1.getElements().get(1).getValue(), ATT_1_ID);
		assertEquals("Attribute not contained", importedReq1.getElements().get(2).getValue(), ATT_1_DESCRIPTION);
		
		assertEquals("Should only import first relvant elements for specified types", 
				importedReq2.getElements().size(), numberSimpleAtt);
		assertEquals("Attribute not contained", importedReq2.getElements().get(0).getValue(), ATT_2_NAME);
		assertEquals("Attribute not contained", importedReq2.getElements().get(1).getValue(), ATT_2_ID);
		assertEquals("Attribute not contained", importedReq2.getElements().get(2).getValue(), ATT_2_DESCRIPTION);
		
		assertEquals("Should only import first relvant elements for specified types", 
				importedReq3.getElements().size(), numberSimpleAtt);
		assertEquals("Attribute not contained", importedReq3.getElements().get(0).getValue(), ATT_3_NAME);
		assertEquals("Attribute not contained", importedReq3.getElements().get(1).getValue(), ATT_3_ID);
		assertEquals("Attribute not contained", importedReq3.getElements().get(2).getValue(), ATT_3_DESCRIPTION);

	}
	
	@Test
	public void testLoadRequirementsWithEnumeration() {

		RequirementsSpecification targetSpec = new RequirementsSpecification(requirementsConcept);
		RequirementType reqImportType = createReqTypeWithCategory();
		List<List<String>> csvContentMatrix = createCSVContentMatrix();

		CsvRequirementsImporter importer = new CsvRequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				reqImportType);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberExpectedAtt = 4;
		final int indexCategory = 3;
		Requirement importedReq1 = (Requirement) targetSpec.getRequirements().get(0);
		
		assertEquals("Requirement type not set", importedReq1.getReqType(), reqImportType);
		
		assertEquals("Number attributes is not correct", 
				importedReq1.getElements().size(), numberExpectedAtt);
		assertEquals("Attribute not contained", importedReq1.getElements().get(0).getValue(), ATT_1_NAME);
		assertEquals("Attribute not contained", importedReq1.getElements().get(1).getValue(), ATT_1_ID);
		assertEquals("Attribute not contained", importedReq1.getElements().get(2).getValue(), ATT_1_DESCRIPTION);
		assertEquals("Attribute not contained", importedReq1.getElements().get(indexCategory).getValue(), ATT_1_CATEGORY);

	}
	
	@Test
	public void testLoadRequirementsWithDoubleValue() {

		RequirementsSpecification targetSpec = new RequirementsSpecification(requirementsConcept);
		RequirementType reqImportType = createReqTypeWithDoubleValue();
		List<List<String>> csvContentMatrix = createCSVContentMatrix();

		CsvRequirementsImporter importer = new CsvRequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				reqImportType);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberExpectedAtt = 4;
		final int indexCategory = 3;
		Requirement importedReq2 = (Requirement) targetSpec.getRequirements().get(1);
		
		assertEquals("Requirement type not set", importedReq2.getReqType(), reqImportType);
		
		assertEquals("Number attributes is not correct", 
				importedReq2.getElements().size(), numberExpectedAtt);
		assertEquals("Attribute not contained", importedReq2.getElements().get(0).getValue(), ATT_2_NAME);
		assertEquals("Attribute not contained", importedReq2.getElements().get(1).getValue(), ATT_2_ID);
		assertEquals("Attribute not contained", importedReq2.getElements().get(2).getValue(), ATT_2_DESCRIPTION);
		assertEquals("Attribute not contained", importedReq2.getElements().get(indexCategory).getValue(), ATT_2_VALUE + "");

	}
	
	@Test
	public void testLoadRequirementsWithoutType() {

		RequirementsSpecification targetSpec = new RequirementsSpecification(requirementsConcept);
		RequirementsConfiguration configuration = new RequirementsConfiguration(requirementsConcept);
		List<List<String>> csvContentMatrix = createCSVContentMatrix();

		CsvRequirementsImporter importer = new CsvRequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				configuration);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberExpectedAtt = 4;
		final int indexValueAtt = 4;
		Requirement importedReq = (Requirement) targetSpec.getRequirements().get(0);
		
		//Check if requirement type was created properly
		assertEquals("Requirement type not set", importedReq.getReqType(), null);

		RequirementType importedReqType = importedReq.getReqType();
		assertEquals("Type created", importedReqType, configuration.getTypeDefinitions().get(0));
		assertEquals("Number of attributes added correctly", importedReqType.getAttributes().size(), numberExpectedAtt);
		assertEquals("Attributes should be called as CSV header", importedReqType.getAttributes().get(0).getName(), NAME_HEADER_NAME);
		assertEquals("Attributes should be called as CSV header", importedReqType.getAttributes().get(1).getName(), NAME_HEADER_ID);
		assertEquals("Attributes should be called as CSV header", importedReqType.getAttributes().get(2).getName(), NAME_HEADER_DES);
		assertEquals("Attributes should be called as CSV header", importedReqType.getAttributes().get(indexValueAtt).getName(), NAME_HEADER_VALUE);

		//Check imported values
		Requirement importedReq1 = (Requirement) targetSpec.getRequirements().get(0);
		Requirement importedReq2 = (Requirement) targetSpec.getRequirements().get(1);
		Requirement importedReq3 = (Requirement) targetSpec.getRequirements().get(2);
		
		assertEquals("Should match number of max elements", 
				importedReq1.getElements().size(), numberExpectedAtt);
		assertEquals("Attribute not contained", importedReq1.getElements().get(0).getValue(), ATT_1_NAME);
		assertEquals("Attribute not contained", importedReq1.getElements().get(1).getValue(), ATT_1_ID);
		assertEquals("Attribute not contained", importedReq1.getElements().get(2).getValue(), ATT_1_DESCRIPTION);
		
		assertEquals("Should match number of max elements", 
				importedReq2.getElements().size(), numberExpectedAtt);
		assertEquals("Attribute not contained", importedReq2.getElements().get(0).getValue(), ATT_2_NAME);
		assertEquals("Attribute not contained", importedReq2.getElements().get(1).getValue(), ATT_2_ID);
		assertEquals("Attribute not contained", importedReq2.getElements().get(2).getValue(), ATT_2_DESCRIPTION);
		
		assertEquals("Should match number of max elements", 
				importedReq3.getElements().size(), numberExpectedAtt);
		assertEquals("Attribute not contained", importedReq3.getElements().get(0).getValue(), ATT_3_NAME);
		assertEquals("Attribute not contained", importedReq3.getElements().get(1).getValue(), ATT_3_ID);
		assertEquals("Attribute not contained", importedReq3.getElements().get(2).getValue(), ATT_3_DESCRIPTION);
	}

	/**
	 * Create a matrix with test requirement values
	 * 
	 * @return a matrix as list of list of string values
	 */
	private List<List<String>> createCSVContentMatrix() {

		List<List<String>> csvContentMatrix = new ArrayList<List<String>>();

		// Create heading
		List<String> header = new ArrayList<String>();
		header.add(NAME_HEADER_NAME);
		header.add(NAME_HEADER_ID);
		header.add(NAME_HEADER_DES);
		header.add(NAME_HEADER_VALUE);
		
		// Create first requirement
		List<String> req1Att = new ArrayList<String>();
		req1Att.add(ATT_1_NAME);
		req1Att.add(ATT_1_ID);
		req1Att.add(ATT_1_DESCRIPTION);
		req1Att.add(ATT_1_CATEGORY);

		// Create second requirement
		List<String> req2Att = new ArrayList<String>();
		req2Att.add(ATT_2_NAME);
		req2Att.add(ATT_2_ID);
		req2Att.add(ATT_2_DESCRIPTION);
		req2Att.add(ATT_2_VALUE + "");

		// Create third requirement
		List<String> req3Att = new ArrayList<String>();
		req3Att.add(ATT_3_NAME);
		req3Att.add(ATT_3_ID);
		req3Att.add(ATT_3_DESCRIPTION);

		csvContentMatrix.add(req1Att);
		csvContentMatrix.add(req2Att);
		csvContentMatrix.add(req3Att);

		return csvContentMatrix;
	}
	

	/**
	 * Create a simple requirement type with basic attributes
	 * 
	 * @return the requriement type
	 */
	private RequirementType createReqTypeWith3Attributes() {
		
		RequirementType requirementType = new RequirementType(requirementsConcept);
		attributeName = new RequirementAttribute(requirementsConcept);
		attributeName.setType(RequirementAttribute.TYPE_String_NAME);
		attributeID = new RequirementAttribute(requirementsConcept);
		attributeID.setType(RequirementAttribute.TYPE_Identifier_NAME);
		attributeDescription = new RequirementAttribute(requirementsConcept);
		attributeDescription.setType(RequirementAttribute.TYPE_String_NAME);
		requirementType.getAttributes().add(attributeName);
		requirementType.getAttributes().add(attributeID);
		requirementType.getAttributes().add(attributeDescription);
		
		return requirementType;
		
	}
	
	/**
	 * Create a requirement type with enumeration
	 * 
	 * @return the requriement type
	 */
	private RequirementType createReqTypeWithCategory() {
		
		RequirementType requirementType = createReqTypeWith3Attributes();
		attributeCategory = new RequirementAttribute(requirementsConcept);
		EnumerationLiteral aocsLiteral = new EnumerationLiteral(requirementsConcept);
		aocsLiteral.setName("AOCS");
		attributeCategory.getEnumeration().getLiterals().add(aocsLiteral);
		requirementType.getAttributes().add(attributeCategory);
		
		return requirementType;
		
	}
	
	/**
	 * Create a simple requirement type with double value
	 * 
	 * @return the requriement type
	 */
	private RequirementType createReqTypeWithDoubleValue() {
		
		RequirementType requirementType = createReqTypeWith3Attributes();
		attributeValue = new RequirementAttribute(requirementsConcept);
		attributeValue.setType(RequirementAttribute.TYPE_Real_NAME);
		requirementType.getAttributes().add(attributeValue);
		
		return requirementType;
		
	}
}
