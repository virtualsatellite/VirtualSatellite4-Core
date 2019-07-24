/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.catia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * The CATIA importer test class
 *
 */
public class CatiaImporterTest extends AConceptTestCase {
	
	private Concept conceptPS;
	private Concept conceptVis;
	
	private JsonObject jsonObject;
	
	//Tree structure elements
	Repository repository = DVLMFactory.eINSTANCE.createRepository();
	
	private ProductTree productTree;
	private ProductTreeDomain domainAOCS;
	private ElementDefinition elementReactionWheelDefinition;
	
	private ConfigurationTree configurationTree;
	private ElementConfiguration subSystemAOCS;
	private ElementConfiguration elementConfigurationReactionWheel1;
	private ElementConfiguration elementConfigurationReactionWheel2;
	
	private AssemblyTree assemblyTree;
	private ElementOccurence aocsSubSystemOccurence;
	private ElementOccurence reactionWheelOccurence1;
	private ElementOccurence reactionWheelOccurence2;
	
	//Visualisation elements
	private Visualisation reactionWheelVisDefinition;

	
	
	//Create JSON object
	
	
	
	@Before
	public void setUp() {
		conceptPS = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.ps");
		conceptVis = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.visualisation");

		createTestScenario();
	}
	
	@Test
	public void testTransformProductTree() {
		
		ProductTree productTree = new ProductTree(conceptPS);
		
		CatiaImporter importer = new CatiaImporter();
		importer.transform(jsonObject, productTree);
		
	}
	
	/**
	 * Create test scenario with inheritance 
	 */
	public void createTestScenario() {
		
		//Create objects
		productTree = new ProductTree(conceptPS);
		domainAOCS = new ProductTreeDomain(conceptPS);
		elementReactionWheelDefinition = new ElementDefinition(conceptPS);
		
		configurationTree = new ConfigurationTree(conceptPS);
		subSystemAOCS = new ElementConfiguration(conceptPS);
		elementConfigurationReactionWheel1 = new ElementConfiguration(conceptPS);
		elementConfigurationReactionWheel2 = new ElementConfiguration(conceptPS);
		
		assemblyTree = new AssemblyTree(conceptPS);
		aocsSubSystemOccurence = new ElementOccurence(conceptPS);
		reactionWheelOccurence1 = new ElementOccurence(conceptPS);
		reactionWheelOccurence2 = new ElementOccurence(conceptPS);
		
		//Visualisation elements
		reactionWheelVisDefinition = new Visualisation(conceptVis);
		
		
		//Create tree structure with inheritance
		repository.getRootEntities().add(productTree.getStructuralElementInstance());
		repository.getRootEntities().add(configurationTree.getStructuralElementInstance());
		repository.getRootEntities().add(assemblyTree.getStructuralElementInstance());
		repository.getActiveConcepts().add(conceptPS);
		repository.getActiveConcepts().add(conceptVis);
		
		productTree.add(domainAOCS);
		domainAOCS.add(elementReactionWheelDefinition);
	
		configurationTree.add(subSystemAOCS);
		subSystemAOCS.add(elementConfigurationReactionWheel1);
		subSystemAOCS.add(elementConfigurationReactionWheel2);
		elementConfigurationReactionWheel1.addSuperSei(elementReactionWheelDefinition);
		elementConfigurationReactionWheel2.addSuperSei(elementReactionWheelDefinition);
		
		assemblyTree.add(aocsSubSystemOccurence);
		aocsSubSystemOccurence.add(reactionWheelOccurence1);
		aocsSubSystemOccurence.add(reactionWheelOccurence2);
		reactionWheelOccurence1.addSuperSei(elementConfigurationReactionWheel1);
		reactionWheelOccurence2.addSuperSei(elementConfigurationReactionWheel1);
		
		//Add visualisation categories
		elementReactionWheelDefinition.add(reactionWheelVisDefinition);
		
		new InheritanceCopier().updateAllInOrder(repository, new NullProgressMonitor());
		
		assertNotNull("Sanitycheck that the inheritance copier worked as expected", reactionWheelOccurence1.getFirst(Visualisation.class));

	}
	
	@Test
	public void testMapJSONtoSEIWithNoUnmappedJSONObject() {
		
		JsonObject jsonObjectReactionWheelDefinition = new JsonObject();
		jsonObjectReactionWheelDefinition.put(CatiaProperties.UUID, elementReactionWheelDefinition.getUuid());
		
		JsonObject rootObject = new JsonObject();
		JsonArray partArray = new JsonArray();
		partArray.add(jsonObjectReactionWheelDefinition);
		rootObject.put(CatiaProperties.PARTS, partArray);
		
		CatiaImporter importer = new CatiaImporter();
		List<JsonObject> unmappedElements = importer.mapJSONtoSEI(rootObject, configurationTree);
		
		assertEquals("Check that there are no umappable elements in the imported JSON", 0, unmappedElements.size());
		
	}

}
