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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.ReqIF10Factory;
import org.eclipse.rmf.reqif10.ReqIF10Package;
import org.eclipse.rmf.reqif10.Specification;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementLink;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommand;

/**
 * Test class for the reqif importer
 *
 */
public class ReqIfImporterTest extends AConceptProjectTestCase {
	
	private static final String REQ_IF_RESOURCE_NAME = "ImportModel";
	private static final String REQ_IF_RESOURCE_PATH = "test/" + REQ_IF_RESOURCE_NAME + ".reqif";
	private static final String PLATFORM_REQ_IF_MODEL_PATH = "/de.dlr.sc.virsat.model.extension.requirements.test/resources/ReqIF/TestRequirements.reqif";
	private static final String PLATFORM_REQ_IF_MODEL_PATH_REIMPORT = "/de.dlr.sc.virsat.model.extension.requirements.test/resources/ReqIF/TestRequirements_reimport.reqif";
	private static final String PLATFORM_REQ_IF_MODEL_WITH_LINK_PATH = "/de.dlr.sc.virsat.model.extension.requirements.test/resources/ReqIF/TestRequirements_link.reqif";
	
	private static final String TREE_NAME = "ConfigurationTree";
	private static final String TREE_CHILD_NAME = "ElementConfiguration";
	private static final String REQUIREMENT_NAME = "TestRequirement";
	
	private static final String SPEC_1_NAME = "FirstSpec";
	private static final String SPEC_2_NAME = "SecondSpec";
	private static final String SPEC_3_NAME = "ThirdSpec";
	
	private static final String TYPE_1_NAME = "System Requirement";
	private static final String TYPE_2_NAME = "Heading";
	private static final String TYPE_3_NAME = "Hardware Requirement";
	
	private Resource reqIfModel;
	private IFile reqIfModelFile;
	private ReqIF reqIFContent;
	
	private Concept psConcept;
	private Concept reqConcept;
	
	private RequirementsConfigurationCollection rcc;
	StructuralElementInstance treeSei;
	StructuralElementInstance childSei;
	
	// ReqIF File Content
	private static final String TEXT_ATTRIBUTE_NAME = "Text";
	private static final String ID_ATTRIBUTE_NAME = "ID";
	private static final String PRIORITY_ATTRIBUTE_NAME = "Priority";
	
	private static final String SYSTEM_SPEC_NAME = "TestSpecification";
	private static final String HARDWARE_SPEC_NAME = "TestHardwareSpecification";
	
	private static final String SYSTEM_REQUIREMENT_TEXT = "TestRequirement";
	private static final String SYSTEM_REQUIREMENT_ID = "99095";
	private static final String SYSTEM_REQUIREMENT_PRIORITY = "High";
	
	private static final String SYSTEM_REQUIREMENT_GROUP_NAME = "MissionRequirements";
	private static final String SYSTEM_REQUIREMENT_GROUP_NAME_WITH_ID = "Req99096MissionRequirements";
	private static final String SYSTEM_REQUIREMENT_GROUP_CHILD_TEXT = "Test Child Requirement";
	private static final String SYSTEM_REQUIREMENT_LINK_NAME = "Req99097SatisfactionReq99095";
	
	private static final String HARDWARE_REQUIREMENT_GROUP_NAME = "MissionObjectives";
	private static final String HARDWARE_REQUIREMENT_GROUP_NAME_WITH_ID = "Req99099MissionObjectives";
	private static final String HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT = "Test Hardware Requirement";
	private static final String HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT_UPDATED = "Test Hardware Requirement Updated";
	
	public static final int THREAD_TEST_SLEEP_TIME = 50;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		
		executeAsCommand(() -> loadConceptAndInstallToRepository(CONCEPT_ID_CORE));
		executeAsCommand(() -> psConcept = loadConceptAndInstallToRepository(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId()));
		executeAsCommand(() -> reqConcept = loadConceptAndInstallToRepository(Activator.getPluginId()));
		 
		// Create basic PS structure
		ConfigurationTree ct = new ConfigurationTree(psConcept);
		ct.setName(TREE_NAME);
		ElementConfiguration ec = new ElementConfiguration(psConcept);
		ec.setName(TREE_CHILD_NAME);
		ct.add(ec);
		treeSei = ct.getStructuralElementInstance();
		childSei = ec.getStructuralElementInstance();
		executeAsCommand(() -> repository.getRootEntities().add(treeSei));
		editingDomain.getCommandStack().execute(new CreateSeiResourceAndFileCommand(rs, treeSei));
		
		// Create basic req concept structure
		rcc = new RequirementsConfigurationCollection(reqConcept);
		executeAsCommand(() -> repository.getRootEntities().add(rcc.getStructuralElementInstance()));
		
		// Create ReqIF model resource
		reqIfModelFile = testProject.getFile(REQ_IF_RESOURCE_PATH);
		URI fileUri = URI.createPlatformResourceURI(reqIfModelFile.getFullPath().toString(), true);
		reqIfModel = rs.createResource(fileUri);
		
		// Create external model content
		reqIFContent = ReqIF10Factory.eINSTANCE.createReqIF();
		executeAsCommand(() -> reqIfModel.getContents().add(reqIFContent));
		try {
			reqIfModel.save(Collections.emptyMap());
			editingDomain.saveAll();
		} catch (IOException e) {
		}
		
	}
	
	private ReqIfImporter importerUnderTest = new ReqIfImporter();
	
	@Test
	public void testPersistSpecificationMapping() {
		importerUnderTest.init(reqIFContent, rcc);
		Command importCmd = importerUnderTest.persistSpecificationMapping(editingDomain, getBasicSpecMapping(), reqIFContent, new ArrayList<String>(), true, rcc);
		editingDomain.getCommandStack().execute(importCmd);
		
		// Check that new specifications have been created
		assertEquals("Tree SIE has been added to tree", treeSei.getCategoryAssignments().size(), 1);  // Spec1 
		assertEquals("Tree SIE has been added to tree", childSei.getCategoryAssignments().size(), 2);  // Spec2 + Spec3
		assertEquals("Spec named correctly", treeSei.getCategoryAssignments().get(0).getName(), SPEC_1_NAME);
		List<String> childSpecNames = childSei.getCategoryAssignments().stream().
				map(n -> n.getName()).
				collect(Collectors.toList());
		assertTrue("Spec2 to added to child", childSpecNames.contains(SPEC_2_NAME));
		assertTrue("Spec3 to added to child", childSpecNames.contains(SPEC_3_NAME));
		
		// Check mapping has been created
		ImportConfiguration importConfig = rcc.getFirst(ImportConfiguration.class);
		assertEquals(importConfig.getName(), ReqIfImporter.IMPORT_CONFIC_PREFIX + REQ_IF_RESOURCE_NAME);
		assertNotNull("It exists a mapping of external Spec1 to a new internal spec element", importConfig.getSpecification(SPEC_1_NAME));
		assertNotNull("It exists a mapping of external Spec2 to a new internal spec element", importConfig.getSpecification(SPEC_2_NAME));
		assertNotNull("It exists a mapping of external Spec3 to a new internal spec element", importConfig.getSpecification(SPEC_3_NAME));
		
		// Check if mapping is correct
		assertEquals(treeSei.getCategoryAssignments().get(0), importConfig.getSpecification(SPEC_1_NAME).getATypeInstance());
		assertThat(childSei.getCategoryAssignments(), hasItem(importConfig.getSpecification(SPEC_2_NAME).getTypeInstance()));
		assertThat(childSei.getCategoryAssignments(), hasItem(importConfig.getSpecification(SPEC_3_NAME).getTypeInstance()));
	}
	
	@Test
	public void testPersistRequirementTypeContainer() {
		ImportConfiguration importConfiguration = new ImportConfiguration(reqConcept);
		editingDomain.getCommandStack().execute(rcc.add(editingDomain, importConfiguration));
		importerUnderTest.init(reqIFContent, importConfiguration);
		
		assertNull("The should be no type container for now", rcc.getFirst(RequirementsConfiguration.class));
		
		Command createNewCommand = importerUnderTest.persistRequirementTypeContainer(editingDomain, null);
		editingDomain.getCommandStack().execute(createNewCommand);
		
		RequirementsConfiguration typeContainer = rcc.getFirst(RequirementsConfiguration.class);
		assertNotNull("Now there should be one", typeContainer);
		assertEquals(typeContainer, importConfiguration.getTypeDefinitionsContainer());
		
	}
	
	@Test
	public void testImportRequirements() {
		registerEPackageReqIF();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_PATH, true);
		Resource modelResource = rs.getResource(modelURI, true);
		ReqIF reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		importerUnderTest.init(reqIfFileContent, rcc);
		
		// Simply map all specifications into childSei
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		for (Specification spec : reqIfFileContent.getCoreContent().getSpecifications()) {
			map.put(spec, childSei);
		}

		// Prepare import
		editingDomain.getCommandStack().execute(importerUnderTest.persistSpecificationMapping(editingDomain, map, reqIfFileContent, getRequirementTypeList(), true, rcc));
		editingDomain.getCommandStack().execute(importerUnderTest.persistRequirementTypeContainer(editingDomain, null));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementTypes(editingDomain, reqIfFileContent));
		
		// Do the actual import
		Command importCommand = importerUnderTest.importRequirements(editingDomain, reqIfFileContent);
		editingDomain.getCommandStack().execute(importCommand);
		
		RequirementsSpecification systemSpec = null;
		RequirementsSpecification hardwareSpec = null;
		for (CategoryAssignment cA : childSei.getCategoryAssignments()) {
			if (cA.getName().equals(SYSTEM_SPEC_NAME)) {
				systemSpec = new RequirementsSpecification(cA);
			} else if (cA.getName().equals(HARDWARE_SPEC_NAME)) {
				hardwareSpec = new RequirementsSpecification(cA);
			}
		}

		Requirement firstRequirement = (Requirement) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof Requirement)
				.collect(Collectors.toList())
				.get(0);
		assertEquals("Should be Requirement Prefix + its ID + name", Requirement.REQUIREMENT_NAME_PREFIX + SYSTEM_REQUIREMENT_ID + REQUIREMENT_NAME, firstRequirement.getName());
		assertEquals(SYSTEM_REQUIREMENT_TEXT, getRequirementValue(firstRequirement, TEXT_ATTRIBUTE_NAME));
		assertEquals(SYSTEM_REQUIREMENT_ID, getRequirementValue(firstRequirement, ID_ATTRIBUTE_NAME));
		assertEquals(SYSTEM_REQUIREMENT_PRIORITY, getRequirementValue(firstRequirement, PRIORITY_ATTRIBUTE_NAME));
		
		RequirementGroup firstGroup = (RequirementGroup) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof RequirementGroup)
				.collect(Collectors.toList())
				.get(0);
		
		assertEquals(SYSTEM_REQUIREMENT_GROUP_NAME, firstGroup.getName());
		
		Requirement firstChildRequirement = (Requirement) firstGroup.getChildren().get(0);
		assertEquals(SYSTEM_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstChildRequirement, TEXT_ATTRIBUTE_NAME));
		
		RequirementGroup hardwareMissionObjectives = (RequirementGroup) hardwareSpec.getRequirements().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_NAME, hardwareMissionObjectives.getName());
		Requirement firstHardwareRequirement = (Requirement) hardwareMissionObjectives.getChildren().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstHardwareRequirement, TEXT_ATTRIBUTE_NAME));
	}
	
	@Test
	public void testImportRequirementsNoGroups() {
		registerEPackageReqIF();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_PATH, true);
		Resource modelResource = rs.getResource(modelURI, true);
		ReqIF reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		importerUnderTest.init(reqIfFileContent, rcc);
		
		// Simply map all specifications into childSei
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		for (Specification spec : reqIfFileContent.getCoreContent().getSpecifications()) {
			map.put(spec, childSei);
		}

		// Prepare import
		editingDomain.getCommandStack().execute(importerUnderTest.persistSpecificationMapping(editingDomain, map, reqIfFileContent, getRequirementTypeList(), false, rcc));
		editingDomain.getCommandStack().execute(importerUnderTest.persistRequirementTypeContainer(editingDomain, null));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementTypes(editingDomain, reqIfFileContent));
		
		// Do the actual import
		Command importCommand = importerUnderTest.importRequirements(editingDomain, reqIfFileContent);
		editingDomain.getCommandStack().execute(importCommand);
		
		RequirementsSpecification systemSpec = null;
		RequirementsSpecification hardwareSpec = null;
		for (CategoryAssignment cA : childSei.getCategoryAssignments()) {
			if (cA.getName().equals(SYSTEM_SPEC_NAME)) {
				systemSpec = new RequirementsSpecification(cA);
			} else if (cA.getName().equals(HARDWARE_SPEC_NAME)) {
				hardwareSpec = new RequirementsSpecification(cA);
			}
		}

		Requirement firstRequirement = (Requirement) systemSpec.getRequirements().stream()
				.filter((child) -> ((Requirement) child).getChildren().isEmpty())
				.collect(Collectors.toList())
				.get(0);
		assertEquals("Should be Requirement Prefix + its ID + name", Requirement.REQUIREMENT_NAME_PREFIX + SYSTEM_REQUIREMENT_ID + REQUIREMENT_NAME, firstRequirement.getName());
		assertEquals(SYSTEM_REQUIREMENT_TEXT, getRequirementValue(firstRequirement, TEXT_ATTRIBUTE_NAME));
		assertEquals(SYSTEM_REQUIREMENT_ID, getRequirementValue(firstRequirement, ID_ATTRIBUTE_NAME));
		assertEquals(SYSTEM_REQUIREMENT_PRIORITY, getRequirementValue(firstRequirement, PRIORITY_ATTRIBUTE_NAME));
		
		Requirement firstGroup = (Requirement) systemSpec.getRequirements().stream()
				.filter((child) -> !((Requirement) child).getChildren().isEmpty())
				.collect(Collectors.toList())
				.get(0);
		
		assertEquals(SYSTEM_REQUIREMENT_GROUP_NAME_WITH_ID, firstGroup.getName());
		
		Requirement firstChildRequirement = (Requirement) firstGroup.getChildren().get(0);
		assertEquals(SYSTEM_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstChildRequirement, TEXT_ATTRIBUTE_NAME));
		
		Requirement hardwareMissionObjectives = (Requirement) hardwareSpec.getRequirements().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_NAME_WITH_ID, hardwareMissionObjectives.getName());
		Requirement firstHardwareRequirement = (Requirement) hardwareMissionObjectives.getChildren().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstHardwareRequirement, TEXT_ATTRIBUTE_NAME));
	}
	
	@Test
	public void testImportRequirementsFilteredTypes() {
		registerEPackageReqIF();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_PATH, true);
		Resource modelResource = rs.getResource(modelURI, true);
		ReqIF reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		importerUnderTest.init(reqIfFileContent, rcc);
		
		// Simply map all specifications into childSei
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		for (Specification spec : reqIfFileContent.getCoreContent().getSpecifications()) {
			map.put(spec, childSei);
		}

		// Prepare import
		editingDomain.getCommandStack().execute(importerUnderTest.persistSpecificationMapping(editingDomain, map, reqIfFileContent, getRequirementTypeListNoSystemRequirements(), true, rcc));
		editingDomain.getCommandStack().execute(importerUnderTest.persistRequirementTypeContainer(editingDomain, null));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementTypes(editingDomain, reqIfFileContent));
		
		// Do the actual import
		Command importCommand = importerUnderTest.importRequirements(editingDomain, reqIfFileContent);
		editingDomain.getCommandStack().execute(importCommand);
		
		RequirementsSpecification systemSpec = null;
		RequirementsSpecification hardwareSpec = null;
		for (CategoryAssignment cA : childSei.getCategoryAssignments()) {
			if (cA.getName().equals(SYSTEM_SPEC_NAME)) {
				systemSpec = new RequirementsSpecification(cA);
			} else if (cA.getName().equals(HARDWARE_SPEC_NAME)) {
				hardwareSpec = new RequirementsSpecification(cA);
			}
		}

		RequirementGroup firstGroup = (RequirementGroup) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof RequirementGroup)
				.collect(Collectors.toList())
				.get(0);
		
		assertEquals(SYSTEM_REQUIREMENT_GROUP_NAME, firstGroup.getName());
		
		assertTrue("System requirements are not imported anymore", firstGroup.getChildren().isEmpty());
		
		// Hardware requirements are imported
		RequirementGroup hardwareMissionObjectives = (RequirementGroup) hardwareSpec.getRequirements().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_NAME, hardwareMissionObjectives.getName());
		Requirement firstHardwareRequirement = (Requirement) hardwareMissionObjectives.getChildren().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstHardwareRequirement, TEXT_ATTRIBUTE_NAME));
	}
	
	@Test
	public void testReCreateRequirementElements() {
		registerEPackageReqIF();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_PATH, true);
		Resource modelResource = rs.getResource(modelURI, true);
		ReqIF reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		importerUnderTest.init(reqIfFileContent, rcc);
		
		// Simply map all specifications into childSei
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		for (Specification spec : reqIfFileContent.getCoreContent().getSpecifications()) {
			map.put(spec, childSei);
		}

		// Prepare import
		editingDomain.getCommandStack().execute(importerUnderTest.persistSpecificationMapping(editingDomain, map, reqIfFileContent, getRequirementTypeList(), true, rcc));
		editingDomain.getCommandStack().execute(importerUnderTest.persistRequirementTypeContainer(editingDomain, null));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementTypes(editingDomain, reqIfFileContent));
		
		// Do the actual import
		Command importCommand = importerUnderTest.importRequirements(editingDomain, reqIfFileContent);
		editingDomain.getCommandStack().execute(importCommand);
		
		RequirementsSpecification systemSpec = null;
		RequirementsSpecification hardwareSpec = null;
		for (CategoryAssignment cA : childSei.getCategoryAssignments()) {
			if (cA.getName().equals(SYSTEM_SPEC_NAME)) {
				systemSpec = new RequirementsSpecification(cA);
			} else if (cA.getName().equals(HARDWARE_SPEC_NAME)) {
				hardwareSpec = new RequirementsSpecification(cA);
			}
		}

		Requirement firstRequirement = (Requirement) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof Requirement)
				.collect(Collectors.toList())
				.get(0);
		
		AttributeValue firstAttValue = firstRequirement.getElements().get(0);
		editingDomain.getCommandStack().execute(firstRequirement.getElements().remove(editingDomain, firstAttValue));
		
		RequirementGroup firstGroup = (RequirementGroup) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof RequirementGroup)
				.collect(Collectors.toList())
				.get(0);
		
		// Remove a group to check if it is re-imported
		editingDomain.getCommandStack().execute(systemSpec.getRequirements().remove(editingDomain, firstGroup));
		
		// Do the re-import
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirements(editingDomain, reqIfFileContent));
		
		// Group should be there again
		firstGroup = (RequirementGroup) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof RequirementGroup)
				.collect(Collectors.toList())
				.get(0);
		
		assertEquals(SYSTEM_REQUIREMENT_GROUP_NAME, firstGroup.getName());
		
		Requirement firstChildRequirement = (Requirement) firstGroup.getChildren().get(0);
		assertEquals(SYSTEM_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstChildRequirement, TEXT_ATTRIBUTE_NAME));
		
		RequirementGroup hardwareMissionObjectives = (RequirementGroup) hardwareSpec.getRequirements().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_NAME, hardwareMissionObjectives.getName());
		Requirement firstHardwareRequirement = (Requirement) hardwareMissionObjectives.getChildren().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstHardwareRequirement, TEXT_ATTRIBUTE_NAME));
	}
	
	@Test
	public void testReImportRequirements() {
		registerEPackageReqIF();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_PATH, true);
		Resource modelResource = rs.getResource(modelURI, true);
		ReqIF reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		importerUnderTest.init(reqIfFileContent, rcc);
		
		// Simply map all specifications into childSei
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		for (Specification spec : reqIfFileContent.getCoreContent().getSpecifications()) {
			map.put(spec, childSei);
		}

		// Do first import
		editingDomain.getCommandStack().execute(importerUnderTest.persistSpecificationMapping(editingDomain, map, reqIfFileContent, getRequirementTypeList(), true, rcc));
		editingDomain.getCommandStack().execute(importerUnderTest.persistRequirementTypeContainer(editingDomain, null));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementTypes(editingDomain, reqIfFileContent));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirements(editingDomain, reqIfFileContent));
		
		// Get some nested requirement to check on if values are updated
		RequirementsSpecification hardwareSpec = null;
		for (CategoryAssignment cA : childSei.getCategoryAssignments()) {
			if (cA.getName().equals(HARDWARE_SPEC_NAME)) {
				hardwareSpec = new RequirementsSpecification(cA);
			}
		}
		RequirementGroup hardwareMissionObjectives = (RequirementGroup) hardwareSpec.getRequirements().get(0);
		Requirement firstHardwareRequirement = (Requirement) hardwareMissionObjectives.getChildren().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstHardwareRequirement, TEXT_ATTRIBUTE_NAME));
		
		
		// Now do re-import and check if value changed
		URI reImportModelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_PATH_REIMPORT, true);
		modelResource = rs.getResource(reImportModelURI, true);
		reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirements(editingDomain, reqIfFileContent));

		assertEquals(HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT_UPDATED, getRequirementValue(firstHardwareRequirement, TEXT_ATTRIBUTE_NAME));
	}
	
	@Test
	public void testReImportRequirementsNoGroups() {
		registerEPackageReqIF();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_PATH, true);
		Resource modelResource = rs.getResource(modelURI, true);
		ReqIF reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		importerUnderTest.init(reqIfFileContent, rcc);
		
		// Simply map all specifications into childSei
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		for (Specification spec : reqIfFileContent.getCoreContent().getSpecifications()) {
			map.put(spec, childSei);
		}

		// Do first import
		editingDomain.getCommandStack().execute(importerUnderTest.persistSpecificationMapping(editingDomain, map, reqIfFileContent, getRequirementTypeList(), false, rcc));
		editingDomain.getCommandStack().execute(importerUnderTest.persistRequirementTypeContainer(editingDomain, null));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementTypes(editingDomain, reqIfFileContent));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirements(editingDomain, reqIfFileContent));
		
		// Get some nested requirement to check on if values are updated
		RequirementsSpecification hardwareSpec = null;
		for (CategoryAssignment cA : childSei.getCategoryAssignments()) {
			if (cA.getName().equals(HARDWARE_SPEC_NAME)) {
				hardwareSpec = new RequirementsSpecification(cA);
			}
		}
		Requirement hardwareMissionObjectives = (Requirement) hardwareSpec.getRequirements().get(0);
		Requirement firstHardwareRequirement = (Requirement) hardwareMissionObjectives.getChildren().get(0);
		assertEquals(HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT, getRequirementValue(firstHardwareRequirement, TEXT_ATTRIBUTE_NAME));
		
		
		// Now do re-import and check if value changed
		URI reImportModelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_PATH_REIMPORT, true);
		modelResource = rs.getResource(reImportModelURI, true);
		reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirements(editingDomain, reqIfFileContent));

		assertEquals(HARDWARE_REQUIREMENT_GROUP_CHILD_TEXT_UPDATED, getRequirementValue(firstHardwareRequirement, TEXT_ATTRIBUTE_NAME));
	}
	
	@Test
	public void testImportRequirementLinks() throws InterruptedException, CoreException {
		registerEPackageReqIF();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_WITH_LINK_PATH, true);
		Resource modelResource = rs.getResource(modelURI, true);
		ReqIF reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		importerUnderTest.init(reqIfFileContent, rcc);
		
		// Simply map all specifications into childSei
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		for (Specification spec : reqIfFileContent.getCoreContent().getSpecifications()) {
			map.put(spec, childSei);
		}

		// Do import
		editingDomain.getCommandStack().execute(importerUnderTest.persistSpecificationMapping(editingDomain, map, reqIfFileContent, getRequirementTypeList(), true, rcc));
		editingDomain.getCommandStack().execute(importerUnderTest.persistRequirementTypeContainer(editingDomain, null));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementTypes(editingDomain, reqIfFileContent));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirements(editingDomain, reqIfFileContent));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementLinks(editingDomain, reqIfFileContent));
		editingDomain.saveAll();
		editingDomain.reloadAll();
		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		
		RequirementsSpecification systemSpec = null;
		for (CategoryAssignment cA : childSei.getCategoryAssignments()) {
			if (cA.getName().equals(SYSTEM_SPEC_NAME)) {
				systemSpec = new RequirementsSpecification(cA);
			} 
		}
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		// Check that target requirement was imported properly
		Requirement firstRequirement = (Requirement) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof Requirement)
				.collect(Collectors.toList())
				.get(0);
		assertEquals("Should be Requirement Prefix + its ID + name", Requirement.REQUIREMENT_NAME_PREFIX + SYSTEM_REQUIREMENT_ID + REQUIREMENT_NAME, firstRequirement.getName());
		assertEquals(SYSTEM_REQUIREMENT_TEXT, getRequirementValue(firstRequirement, TEXT_ATTRIBUTE_NAME));
		assertEquals(SYSTEM_REQUIREMENT_ID, getRequirementValue(firstRequirement, ID_ATTRIBUTE_NAME));
		assertEquals(SYSTEM_REQUIREMENT_PRIORITY, getRequirementValue(firstRequirement, PRIORITY_ATTRIBUTE_NAME));
		
		RequirementGroup firstGroup = (RequirementGroup) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof RequirementGroup)
				.collect(Collectors.toList())
				.get(0);
		final Requirement EXPECTED_SOURCE = (Requirement) firstGroup.getChildren().get(0); 
		final Requirement EXPECTED_TARGET = (Requirement) systemSpec.getRequirements().get(0);
		
		assertEquals("There should be a link now", 1, systemSpec.getLinks().size());
		RequirementLink importedLink = systemSpec.getLinks().get(0);
		assertEquals("Name is set", SYSTEM_REQUIREMENT_LINK_NAME, importedLink.getName());
		
		assertEquals(EXPECTED_SOURCE, importedLink.getSubject());
		assertEquals(EXPECTED_TARGET, importedLink.getTargets().get(0));
		
		assertEquals("ReqIF relations only support one target", 1, importedLink.getTargets().size());
	}
	
	@Test
	public void testReImportRequirementLinks() throws InterruptedException, CoreException {
		registerEPackageReqIF();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_REQ_IF_MODEL_WITH_LINK_PATH, true);
		Resource modelResource = rs.getResource(modelURI, true);
		ReqIF reqIfFileContent = (ReqIF) modelResource.getContents().get(0);
		importerUnderTest.init(reqIfFileContent, rcc);
		
		// Simply map all specifications into childSei
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		for (Specification spec : reqIfFileContent.getCoreContent().getSpecifications()) {
			map.put(spec, childSei);
		}

		// Do import
		editingDomain.getCommandStack().execute(importerUnderTest.persistSpecificationMapping(editingDomain, map, reqIfFileContent, getRequirementTypeList(), true, rcc));
		editingDomain.getCommandStack().execute(importerUnderTest.persistRequirementTypeContainer(editingDomain, null));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementTypes(editingDomain, reqIfFileContent));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirements(editingDomain, reqIfFileContent));
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementLinks(editingDomain, reqIfFileContent));
		
		// Re import links
		editingDomain.getCommandStack().execute(importerUnderTest.importRequirementLinks(editingDomain, reqIfFileContent));
		
		RequirementsSpecification systemSpec = null;
		for (CategoryAssignment cA : childSei.getCategoryAssignments()) {
			if (cA.getName().equals(SYSTEM_SPEC_NAME)) {
				systemSpec = new RequirementsSpecification(cA);
			} 
		}

		// Check that target requirement was imported properly
		Requirement firstRequirement = (Requirement) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof Requirement)
				.collect(Collectors.toList())
				.get(0);
		assertEquals("Should be Requirement Prefix + its ID + name", Requirement.REQUIREMENT_NAME_PREFIX + SYSTEM_REQUIREMENT_ID + REQUIREMENT_NAME, firstRequirement.getName());
		assertEquals(SYSTEM_REQUIREMENT_TEXT, getRequirementValue(firstRequirement, TEXT_ATTRIBUTE_NAME));
		assertEquals(SYSTEM_REQUIREMENT_ID, getRequirementValue(firstRequirement, ID_ATTRIBUTE_NAME));
		assertEquals(SYSTEM_REQUIREMENT_PRIORITY, getRequirementValue(firstRequirement, PRIORITY_ATTRIBUTE_NAME));
		
		RequirementGroup firstGroup = (RequirementGroup) systemSpec.getRequirements().stream()
				.filter((child) -> child instanceof RequirementGroup)
				.collect(Collectors.toList())
				.get(0);
		final Requirement EXPECTED_SOURCE = (Requirement) firstGroup.getChildren().get(0); 
		final Requirement EXPECTED_TARGET = (Requirement) systemSpec.getRequirements().get(0);
				
		assertEquals("There should still be only one link now", 1, systemSpec.getLinks().size());
		RequirementLink importedLink = systemSpec.getLinks().get(0);
		assertEquals("Re-import should not add target again", 1, importedLink.getTargets().size());
		
		assertEquals(EXPECTED_SOURCE, importedLink.getSubject());
		assertEquals(EXPECTED_TARGET, importedLink.getTargets().get(0));
	}
	
	/**
	 * Create a basic mapping of reqif specifications to SEIs
	 * @return a hashmap
	 */
	protected Map<Specification, StructuralElementInstance> getBasicSpecMapping() {
		Map<Specification, StructuralElementInstance> map = new HashMap<Specification, StructuralElementInstance>();
		
		//Create some basic specification elements
		Specification spec1 = ReqIF10Factory.eINSTANCE.createSpecification();
		spec1.setLongName(SPEC_1_NAME);
		Specification spec2 = ReqIF10Factory.eINSTANCE.createSpecification();
		spec2.setLongName(SPEC_2_NAME);
		Specification spec3 = ReqIF10Factory.eINSTANCE.createSpecification();
		spec3.setLongName(SPEC_3_NAME);
		
		// Put one spec directly into tree
		map.put(spec1, treeSei);
		
		// Put two specs into on SEI
		map.put(spec2, childSei);
		map.put(spec3, childSei);
		return map;
	}
	
	/**
	 * Get the requirement value of a specified attribute
	 * @param requirement the requirement
	 * @param attName the attribute to get
	 * @return the value
	 */
	protected String getRequirementValue(Requirement requirement, String attName) {
		for (AttributeValue att : requirement.getElements()) {
			if (att.getAttType().getName().equals(attName)) {
				return att.getValue();
			}
		}
		return null;
	}
	
	protected List<String> getRequirementTypeList() {
		List<String> typeList = new ArrayList<String>();
		typeList.add(TYPE_1_NAME);
		typeList.add(TYPE_2_NAME);
		typeList.add(TYPE_3_NAME);
		return typeList;
	}
	
	protected List<String> getRequirementTypeListNoSystemRequirements() {
		List<String> typeList = new ArrayList<String>();
		typeList.add(TYPE_2_NAME);
		typeList.add(TYPE_3_NAME);
		return typeList;
	}
	
	/**
	 * Register all required packages
	 */
	private static void registerEPackageReqIF() {
		EPackage.Registry.INSTANCE.put(ReqIF10Package.eNS_URI,
				ReqIF10Package.eINSTANCE);
	}
	
	@Override
	public void initializeTimeOutRule() {
		// Disable timeout rule as its shutdown-hook conflicts with the ReqIF serialization
	}

}
