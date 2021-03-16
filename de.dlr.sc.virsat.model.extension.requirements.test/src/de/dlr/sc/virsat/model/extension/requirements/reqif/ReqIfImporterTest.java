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
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.ReqIF10Factory;
import org.eclipse.rmf.reqif10.Specification;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommand;

/**
 * Test class for the reqif importer
 *
 */
public class ReqIfImporterTest extends AConceptProjectTestCase {
	
	private static final String REQ_IF_RESOURCE_NAME = "ImportModel";
	private static final String REQ_IF_RESOURCE_PATH = "test/" + REQ_IF_RESOURCE_NAME + ".reqif";
	
	private static final String TREE_NAME = "ConfigurationTree";
	private static final String TREE_CHILD_NAME = "ElementConfiguration";
	
	private static final String SPEC_1_NAME = "FirstSpec";
	private static final String SPEC_2_NAME = "SecondSpec";
	private static final String SPEC_3_NAME = "ThirdSpec";
	
	private Resource reqIfModel;
	private IFile reqIfModelFile;
	private ReqIF reqIFContent;
	
	private Concept psConcept;
	private Concept reqConcept;
	
	private RequirementsConfigurationCollection rcc;
	StructuralElementInstance treeSei;
	StructuralElementInstance childSei;
	
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
		} catch (IOException e) {
		}
		
	}
	
	private ReqIfImporter importerUnderTest = new ReqIfImporter();
	
	@Test
	public void testPersistSpecificationMapping() {
		importerUnderTest.init(reqIFContent, rcc);
		Command importCmd = importerUnderTest.persistSpecificationMapping(editingDomain, getBasicSpecMapping(), reqIFContent, rcc);
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

}
