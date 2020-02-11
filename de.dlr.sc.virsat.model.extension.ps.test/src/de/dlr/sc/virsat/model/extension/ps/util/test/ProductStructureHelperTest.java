/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.util.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;
import de.dlr.sc.virsat.model.extension.ps.Activator;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.util.ProductStructureHelper;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;

/**
 * Test Case for Generating Tree
 *
 */
public class ProductStructureHelperTest extends AConceptProjectTestCase {
	private static final String CONCEPT_ID_PS = Activator.getPluginId();
	
	private static final int AMOUNT_CT_SUBSYTEMS = 3;

	private ConfigurationTree ct;

	private StructuralElementInstance seiCT;
	private StructuralElementInstance seiEC1;
	private StructuralElementInstance seiEC2;
	
	private StructuralElementInstance copyAT;
	private StructuralElementInstance seiAT;
	private StructuralElementInstance seiEO1;
	private StructuralElementInstance seiEO2;
	private StructuralElementInstance seiEO3;
	
	private StructuralElementInstance seiPT;
	private StructuralElementInstance seiPTD;
	private StructuralElementInstance seiED1;
	private StructuralElementInstance seiED2;
	
	private Concept conceptPs;

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		activateCoreConcept();
		conceptPs = executeAsCommand(() -> loadConceptAndInstallToRepository(CONCEPT_ID_PS));
			
		// get Structural elements
		StructuralElement productTree = ActiveConceptHelper.getStructuralElement(conceptPs, "ProductTree");
		StructuralElement productTreeDomain = ActiveConceptHelper.getStructuralElement(conceptPs, "ProductTreeDomain");
		StructuralElement elementDefinition = ActiveConceptHelper.getStructuralElement(conceptPs, "ElementDefinition");
		
		StructuralElement configurationTree = ActiveConceptHelper.getStructuralElement(conceptPs, "ConfigurationTree");	
		StructuralElement elementConfiguration = ActiveConceptHelper.getStructuralElement(conceptPs, "ElementConfiguration");
		
		StructuralElement assemblyTree = ActiveConceptHelper.getStructuralElement(conceptPs, "AssemblyTree");
		StructuralElement elementOccurence = ActiveConceptHelper.getStructuralElement(conceptPs, "ElementOccurence");
		
		// Create A configuration Tree And its Children
		seiCT = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiCT.setType(configurationTree);
		
		ct = new ConfigurationTree(seiCT);
		ct.setName("ConfigurationTree1");
		
		seiEC1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEC1.setType(elementConfiguration);
		
		seiEC2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEC2.setType(elementConfiguration);
		
		seiCT.getChildren().add(seiEC1);
		seiCT.getChildren().add(seiEC2);
		
		// create assembly tree
		seiAT = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiAT.setType(assemblyTree);
	
		seiEO1  = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEO1.setType(elementOccurence);
		seiEO1.setName("ElementOccurence1");

		seiEO2  = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEO2.setType(elementOccurence);
		seiEO2.setName("ElementOccurence2");

		seiEO3  = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEO3.setType(elementOccurence);
		seiEO3.setName("ElementOccurence3");

		seiAT.getChildren().add(seiEO1);
		seiEO1.getChildren().add(seiEO2);
		seiEO1.getChildren().add(seiEO3);

		// Create a Product tree And its Children
		seiPT = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiPT.setType(productTree);
		
		seiPTD = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiPTD.setType(productTreeDomain);
		
		seiED1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiED1.setType(elementDefinition);
		
		seiED2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiED2.setType(elementDefinition);

		seiPT.getChildren().add(seiPTD);
		seiPTD.getChildren().add(seiED1);
		seiPTD.getChildren().add(seiED2);
		
		copyAT = (StructuralElementInstance) DVLMCopier.makeCopyKeepUuids(seiAT);
	}

	@Test
	public void testConfigTreeToAssemblyTree()  { 
		Command commandAddStructuralElementInstance = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, seiCT);
		editingDomain.getCommandStack().execute(commandAddStructuralElementInstance);
		AssemblyTree at = (AssemblyTree) ProductStructureHelper.createTreeModel(seiCT);
		assertEquals(2, at.getStructuralElementInstance().getChildren().size());
	}
	
	@Test
	public void testDuplicate()  { 
		ProductStructureHelper.duplicate(seiEC2);
		assertEquals(AMOUNT_CT_SUBSYTEMS, seiCT.getChildren().size());
	}	

	@Test
	public void testProductTreeToConfigurationTree()  { 
		Command commandAddStructuralElementInstance = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, seiPT);
		editingDomain.getCommandStack().execute(commandAddStructuralElementInstance);
		ConfigurationTree ct = (ConfigurationTree) ProductStructureHelper.createTreeModel(seiPT);
		assertEquals(1, ct.getStructuralElementInstance().getChildren().size());
		assertEquals(2, ct.getStructuralElementInstance().getChildren().get(0).getChildren().size());
	}

	@Test
	public void testConfigure()  { 
		StructuralElementInstance newChildEo  = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		newChildEo.setType(ActiveConceptHelper.getStructuralElement(conceptPs, "ElementOccurence"));
		newChildEo.setName("ElementOccurence35");
		copyAT.getChildren().add(newChildEo);

		for (StructuralElementInstance sei : copyAT.getDeepChildren()) {
			if (sei.getUuid().equals(seiEO1.getUuid())) {
				sei.setName("New Name");
			} else if (sei.getUuid().equals(seiEO2.getUuid())) {
				sei.getSuperSeis().add(seiEC2);
			}

		}

		ProductStructureHelper.createAndExecuteCompaundCommandForMerge(editingDomain, seiAT, copyAT);

		assertEquals("New Name", seiEO1.getName());
		assertEquals(1, seiEO2.getSuperSeis().size());
		assertEquals(seiEC2, seiEO2.getSuperSeis().get(0));
		assertEquals(seiAT.getChildren().size(), 2);

		copyAT = (StructuralElementInstance) DVLMCopier.makeCopyKeepUuids(seiAT);
	
		StructuralElementInstance toRemove = null;
		for (StructuralElementInstance sei : copyAT.getChildren()) {
			if (sei.getUuid().equals(seiEO1.getUuid())) {
				toRemove = sei;
			}
		}
		copyAT.getChildren().remove(toRemove);
		assertEquals(seiAT.getChildren().size(), 2);
	}
}
