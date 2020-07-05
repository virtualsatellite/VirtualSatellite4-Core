/*******************************************************************************
getWizard() * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationLiteral;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;

/**
 * The test class for the requirements importer
 *
 */
public class RequirementsImporterTest extends AConceptProjectTestCase {

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
	private Concept conceptPS;

	private RequirementAttribute attributeName;
	private RequirementAttribute attributeID;
	private RequirementAttribute attributeDescription;
	private RequirementAttribute attributeCategory;
	private RequirementAttribute attributeValue;
	
	private RequirementsSpecification targetSpec;
	private RequirementGroup targetSpecGroup;
	private RequirementType reqImportType;
	private RequirementsConfiguration configuration;
	private StructuralElementInstance reqContainerSEI;
	private StructuralElementInstance rccSEI;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		requirementsConcept = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.requirements");
		conceptPS = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.ps");
		addEditingDomainAndRepository();
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				repository.getActiveConcepts().add(requirementsConcept);
				ConfigurationTree configurationTree = new ConfigurationTree(conceptPS);
				ElementConfiguration reqContainer = new ElementConfiguration(conceptPS);
				RequirementsConfigurationCollection rcc = new RequirementsConfigurationCollection(requirementsConcept);
				configurationTree.add(reqContainer);
				reqContainerSEI = reqContainer.getStructuralElementInstance();
				rccSEI = rcc.getStructuralElementInstance();
				repository.getRootEntities().add(reqContainerSEI);
				repository.getRootEntities().add(rccSEI);
				editingDomain.getResourceSet().getAndAddStructuralElementInstanceResource(reqContainerSEI);
				editingDomain.getResourceSet().getAndAddStructuralElementInstanceResource(rccSEI);
			}
		});
		editingDomain.saveAll();
	}

	@Test
	public void testLoadRequirementsToRequirementSpecification() {
		
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				targetSpec = new RequirementsSpecification(requirementsConcept);
				reqContainerSEI.getCategoryAssignments().clear();
				reqContainerSEI.getCategoryAssignments().add(targetSpec.getTypeInstance());
				reqImportType = createReqTypeWith3Attributes();
			}
		});
		List<List<String>> csvContentMatrix = createCSVContentMatrix();
		Map<Integer, RequirementAttribute> mapping = new HashMap<>();
		for (RequirementAttribute att : reqImportType.getAttributes()) {
			int index = reqImportType.getAttributes().indexOf(att);
			mapping.put(index, att);
		}

		RequirementsImporter importer = new RequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				mapping, reqImportType);
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

		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				targetSpecGroup = new RequirementGroup(requirementsConcept);
				reqContainerSEI.getCategoryAssignments().clear();
				reqContainerSEI.getCategoryAssignments().add(targetSpecGroup.getTypeInstance());
				reqImportType = createReqTypeWith3Attributes();
			}
		});
		List<List<String>> csvContentMatrix = createCSVContentMatrix();
		Map<Integer, RequirementAttribute> mapping = new HashMap<>();
		for (RequirementAttribute att : reqImportType.getAttributes()) {
			int index = reqImportType.getAttributes().indexOf(att);
			mapping.put(index, att);
		}

		RequirementsImporter importer = new RequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpecGroup.getChildren(),
				mapping, reqImportType);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberSimpleAtt = 3;
		Requirement importedReq1 = (Requirement) targetSpecGroup.getChildren().get(0);
		Requirement importedReq2 = (Requirement) targetSpecGroup.getChildren().get(1);
		Requirement importedReq3 = (Requirement) targetSpecGroup.getChildren().get(2);
		
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

		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				targetSpec = new RequirementsSpecification(requirementsConcept);
				reqContainerSEI.getCategoryAssignments().clear();
				reqContainerSEI.getCategoryAssignments().add(targetSpec.getTypeInstance());
				reqImportType = createReqTypeWithCategory();
			}
		});
		List<List<String>> csvContentMatrix = createCSVContentMatrix();
		Map<Integer, RequirementAttribute> mapping = new HashMap<>();
		for (RequirementAttribute att : reqImportType.getAttributes()) {
			int index = reqImportType.getAttributes().indexOf(att);
			mapping.put(index, att);
		}

		RequirementsImporter importer = new RequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				mapping, reqImportType);
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

		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				targetSpec = new RequirementsSpecification(requirementsConcept);
				reqContainerSEI.getCategoryAssignments().clear();
				reqContainerSEI.getCategoryAssignments().add(targetSpec.getTypeInstance());
				reqImportType = createReqTypeWithDoubleValue();
			}
		});
		List<List<String>> csvContentMatrix = createCSVContentMatrix();
		Map<Integer, RequirementAttribute> mapping = new HashMap<>();
		for (RequirementAttribute att : reqImportType.getAttributes()) {
			int index = reqImportType.getAttributes().indexOf(att);
			mapping.put(index, att);
		}

		RequirementsImporter importer = new RequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				mapping, reqImportType);
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

		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				targetSpec = new RequirementsSpecification(requirementsConcept);
				reqContainerSEI.getCategoryAssignments().clear();
				reqContainerSEI.getCategoryAssignments().add(targetSpec.getTypeInstance());
				configuration = new RequirementsConfiguration(requirementsConcept);
				rccSEI.getCategoryAssignments().add(configuration.getTypeInstance());
			}
		});
		
		List<List<String>> csvContentMatrix = createCSVContentMatrix(true);
		Map<Integer, RequirementAttribute> mapping = new HashMap<>();

		RequirementsImporter importer = new RequirementsImporter();
		RequirementType newType = importer.prepareRequirementType(requirementsConcept, csvContentMatrix.get(0));
		for (RequirementAttribute att : newType.getAttributes()) {
			int index = newType.getAttributes().indexOf(att);
			mapping.put(index, att);
		}
		
		csvContentMatrix.remove(0); //remove header line
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				mapping, configuration, newType);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberExpectedAtt = 4;
		final int indexValueAtt = 3;
		Requirement importedReq = (Requirement) targetSpec.getRequirements().get(0);
		

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
	
	@Test
	public void testTypeCreationWithEnumeration() {

		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				targetSpec = new RequirementsSpecification(requirementsConcept);
				reqContainerSEI.getCategoryAssignments().clear();
				reqContainerSEI.getCategoryAssignments().add(targetSpec.getTypeInstance());
				configuration = new RequirementsConfiguration(requirementsConcept);
				rccSEI.getCategoryAssignments().add(configuration.getTypeInstance());
			}
		});
		
		List<List<String>> csvContentMatrix = createCSVContentMatrix(true);
		Map<Integer, RequirementAttribute> mapping = new HashMap<>();

		RequirementsImporter importer = new RequirementsImporter();
		
		// Get basic type suggestion from importer
		RequirementType newType = importer.prepareRequirementType(requirementsConcept, csvContentMatrix.get(0));
		for (RequirementAttribute att : newType.getAttributes()) {
			int index = newType.getAttributes().indexOf(att);
			mapping.put(index, att);
		}
		// Customize to our wishes, change one attribute to enumeration type
		RequirementAttribute attType = newType.getAttributes().get(0);
		attType.setType(RequirementAttribute.TYPE_Enumeration_NAME);
		// Get enumeration literals from the data to import
		csvContentMatrix.remove(0); //remove header line
		importer.fillEnumAttributes(newType, csvContentMatrix, mapping);
		
		final int EXPECTED_LITERAL_NUMBER = 3; // Three data lines, each name is added as enumeration literal
		assertEquals(EXPECTED_LITERAL_NUMBER, attType.getEnumeration().getLiterals().size());
		
		List<String> literals =  attType.getEnumeration().getLiterals().stream().
			map(EnumerationLiteral::getName).
			collect(Collectors.toList());
		
		assertTrue("Should contain all elements from first column", literals.contains(ATT_1_NAME));
		assertTrue("Should contain all elements from first column", literals.contains(ATT_2_NAME));
		assertTrue("Should contain all elements from first column", literals.contains(ATT_3_NAME));

	}
	
	@Test
	public void testLoadRequirementsWithCustomMapping() {

		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				targetSpec = new RequirementsSpecification(requirementsConcept);
				reqContainerSEI.getCategoryAssignments().clear();
				reqContainerSEI.getCategoryAssignments().add(targetSpec.getTypeInstance());
				reqImportType = createReqTypeWithDoubleValue();
			}
		});
		List<List<String>> csvContentMatrix = createCSVContentMatrix();
		Map<Integer, RequirementAttribute> mapping = new HashMap<>();
		for (RequirementAttribute att : reqImportType.getAttributes()) {
			
			int index = reqImportType.getAttributes().indexOf(att);
			if (index != 1) {
				mapping.put(index, att);		//Customize mapping to don't import ID column
			}
		}

		RequirementsImporter importer = new RequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				mapping, reqImportType);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberExpectedAtt = 4;
		final int indexCategory = 3;
		Requirement importedReq2 = (Requirement) targetSpec.getRequirements().get(1);
		
		assertEquals("Requirement type not set", importedReq2.getReqType(), reqImportType);
		
		assertEquals("Number attributes is not correct", 
				importedReq2.getElements().size(), numberExpectedAtt);
		assertEquals("Attribute not contained", importedReq2.getElements().get(0).getValue(), ATT_2_NAME);
		assertEquals("Attribute not contained", importedReq2.getElements().get(1).getValue(), null); //ID column should not be imported
		assertEquals("Attribute not contained", importedReq2.getElements().get(2).getValue(), ATT_2_DESCRIPTION);
		assertEquals("Attribute not contained", importedReq2.getElements().get(indexCategory).getValue(), ATT_2_VALUE + "");

	}
	
	@Test
	public void testLoadRequirementsWithCustomMappingInverseOrder() {

		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				targetSpec = new RequirementsSpecification(requirementsConcept);
				reqContainerSEI.getCategoryAssignments().clear();
				reqContainerSEI.getCategoryAssignments().add(targetSpec.getTypeInstance());
				reqImportType = createReqTypeWithDoubleValue();
			}
		});
		List<List<String>> csvContentMatrix = createCSVContentMatrix();
		Map<Integer, RequirementAttribute> mapping = new HashMap<>();
		final int maxIndex = 3;
		for (RequirementAttribute att : reqImportType.getAttributes()) {
			
			int index = reqImportType.getAttributes().indexOf(att);
			mapping.put(maxIndex - index, att);		//Inverse order
		
		}

		RequirementsImporter importer = new RequirementsImporter();
		Command importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetSpec.getRequirements(),
				mapping, reqImportType);
		editingDomain.getVirSatCommandStack().execute(importCommand);
		
		final int numberExpectedAtt = 4;
		final int indexCategory = 3;
		Requirement importedReq2 = (Requirement) targetSpec.getRequirements().get(1);
		
		assertEquals("Requirement type not set", importedReq2.getReqType(), reqImportType);
		
		assertEquals("Number attributes is not correct", 
				importedReq2.getElements().size(), numberExpectedAtt);
		
		assertEquals("Imported order should be inverse now", importedReq2.getElements().get(0).getValue(), ATT_2_VALUE + "");
		assertEquals("Imported order should be inverse now", importedReq2.getElements().get(1).getValue(), ATT_2_DESCRIPTION); 
		assertEquals("Imported order should be inverse now", importedReq2.getElements().get(2).getValue(), ATT_2_ID);
		assertEquals("Imported order should be inverse now", importedReq2.getElements().get(indexCategory).getValue(), ATT_2_NAME);

	}

	/**
	 * Create a matrix with test requirement values
	 *
	 * @return a matrix as list of list of string values
	 */
	private List<List<String>> createCSVContentMatrix() {
		return createCSVContentMatrix(false);
	}
	
	/**
	 * Create a matrix with test requirement values
	 * @param withHeader add header or not
	 * @return a matrix as list of list of string values
	 */
	private List<List<String>> createCSVContentMatrix(boolean withHeader) {

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

		if (withHeader) {
			csvContentMatrix.add(header);
		}
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
		RequirementsConfiguration configurationRoot = new RequirementsConfiguration(requirementsConcept);
		RequirementType requirementType = new RequirementType(requirementsConcept);
		configurationRoot.getTypeDefinitions().add(requirementType);
		rccSEI.getCategoryAssignments().add(configurationRoot.getTypeInstance());
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
