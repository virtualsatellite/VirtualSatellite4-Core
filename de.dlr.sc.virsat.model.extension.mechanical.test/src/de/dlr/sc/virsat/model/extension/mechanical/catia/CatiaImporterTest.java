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
import java.util.Map;
import java.util.UUID;

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
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
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

	// Tree structure elements
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

	// Visualisation elements
	private Visualisation reactionWheelVisDefinition;

	// Create JSON object

	@Before
	public void setUp() {
		conceptPS = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.ps");
		conceptVis = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.visualisation");

		createTestTreeScenario();
	}

	@Test
	public void testTransformProductTree() {

		ProductTree productTree = new ProductTree(conceptPS);

		CatiaImporter importer = new CatiaImporter();

		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(jsonObject, productTree);

		importer.transform(jsonObject, mapping);

	}

	@Test
	public void testMapJSONtoSEIWithNoUnmappedJSONObject() {

		JsonObject rootObject = createMappedJsonObjectWithProductandConfiguration();

		CatiaImporter importer = new CatiaImporter();
		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(rootObject, configurationTree);
		List<JsonObject> unmappedElements = importer.getUnmappedJSONObjects(rootObject, mapping);

		//Check map
		assertEquals("Map does not contain element definition", elementReactionWheelDefinition.getStructuralElementInstance(),
				mapping.get(elementReactionWheelDefinition.getUuid()));
		assertEquals("Map does not contain first element configuration",
				elementConfigurationReactionWheel1.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel1.getUuid()));
		assertEquals("Map does not contain second element configuration",
				elementConfigurationReactionWheel2.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel2.getUuid()));
		assertEquals("Map does not contain root product", subSystemAOCS.getStructuralElementInstance(),
				mapping.get(subSystemAOCS.getUuid()));

		//Check unmapped elements
		assertEquals("Check that there are no umappable elements in the imported JSON", 0, unmappedElements.size());

	}

	@Test
	public void testMapJSONtoSEIWithUnmappedJSONPart() {

		JsonObject rootObject = createMappedJsonObjectWithProductandConfiguration();

		// Create new unmapped element
		JsonObject unmappedJsonObject = new JsonObject();
		unmappedJsonObject.put(CatiaProperties.UUID.getKey(), UUID.randomUUID().toString());

		JsonArray partArray = rootObject.getCollection(CatiaProperties.PARTS);
		partArray.add(unmappedJsonObject);

		CatiaImporter importer = new CatiaImporter();
		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(rootObject, configurationTree);
		List<JsonObject> unmappedElements = importer.getUnmappedJSONObjects(rootObject, mapping);

		//Check map
		assertEquals("Map does not contain element definition", elementReactionWheelDefinition.getStructuralElementInstance(),
				mapping.get(elementReactionWheelDefinition.getUuid()));
		assertEquals("Map does not contain first element configuration",
				elementConfigurationReactionWheel1.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel1.getUuid()));
		assertEquals("Map does not contain second element configuration",
				elementConfigurationReactionWheel2.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel2.getUuid()));
		assertEquals("Map does not contain root product", subSystemAOCS.getStructuralElementInstance(),
				mapping.get(subSystemAOCS.getUuid()));

		//Check unmapped elements
		assertEquals("Check that there is one umappable element in the imported JSON", 1, unmappedElements.size());
		assertEquals("Expected unmapped part not found", unmappedJsonObject, unmappedElements.get(0));

	}

	@Test
	public void testMapJSONtoSEIWithUnmappedJSONProduct() {

		JsonObject rootObject = createMappedJsonObjectWithProductandConfiguration();

		// Create new unmapped element
		JsonObject unmappedJsonObject = new JsonObject();
		unmappedJsonObject.put(CatiaProperties.UUID.getKey(), UUID.randomUUID().toString());

		JsonObject rootProduct = rootObject.getMap(CatiaProperties.PRODUCTS);
		JsonArray productArray = rootProduct.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		productArray.add(unmappedJsonObject);

		CatiaImporter importer = new CatiaImporter();
		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(rootObject, configurationTree);
		List<JsonObject> unmappedElements = importer.getUnmappedJSONObjects(rootObject, mapping);
		
		//Check map
		assertEquals("Map does not contain element definition", elementReactionWheelDefinition.getStructuralElementInstance(),
				mapping.get(elementReactionWheelDefinition.getUuid()));
		assertEquals("Map does not contain first element configuration",
				elementConfigurationReactionWheel1.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel1.getUuid()));
		assertEquals("Map does not contain second element configuration",
				elementConfigurationReactionWheel2.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel2.getUuid()));
		assertEquals("Map does not contain root product", subSystemAOCS.getStructuralElementInstance(),
				mapping.get(subSystemAOCS.getUuid()));

		//Check unmapped elements
		assertEquals("Check that there is one umappable elements in the imported JSON", 1, unmappedElements.size());
		assertEquals("Expected unmapped product not found", unmappedJsonObject, unmappedElements.get(0));

	}

	/**
	 * Create test scenario with inheritance
	 */
	public void createTestTreeScenario() {

		// Create objects
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

		// Visualisation elements
		reactionWheelVisDefinition = new Visualisation(conceptVis);

		// Create tree structure with inheritance
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

		// Add visualisation categories
		elementReactionWheelDefinition.add(reactionWheelVisDefinition);

		new InheritanceCopier().updateAllInOrder(repository, new NullProgressMonitor());

		assertNotNull("Sanitycheck that the inheritance copier worked as expected",
				reactionWheelOccurence1.getFirst(Visualisation.class));

	}

	/**
	 * Create a simple mapped JSON object with parts and products that are mapped to
	 * elements in the test trees
	 * 
	 * @return the JSON root object
	 */
	protected JsonObject createMappedJsonObjectWithProductandConfiguration() {

		JsonObject jsonObjectReactionWheelDefinition = new JsonObject();
		jsonObjectReactionWheelDefinition.put(CatiaProperties.UUID.getKey(), elementReactionWheelDefinition.getUuid());
		JsonArray partArray = new JsonArray();
		partArray.add(jsonObjectReactionWheelDefinition);

		JsonObject jsonObjectReactionWheel1Configuration = new JsonObject();
		jsonObjectReactionWheel1Configuration.put(CatiaProperties.UUID.getKey(),
				elementConfigurationReactionWheel1.getUuid());
		JsonObject jsonObjectReactionWheel2Configuration = new JsonObject();
		jsonObjectReactionWheel2Configuration.put(CatiaProperties.UUID.getKey(),
				elementConfigurationReactionWheel2.getUuid());
		JsonArray productArray = new JsonArray();
		productArray.add(jsonObjectReactionWheel1Configuration);
		productArray.add(jsonObjectReactionWheel2Configuration);

		JsonObject rootProduct = new JsonObject();
		rootProduct.put(CatiaProperties.UUID.getKey(), subSystemAOCS.getUuid());
		rootProduct.put(CatiaProperties.PRODUCT_CHILDREN.getKey(), productArray);

		JsonObject rootObject = new JsonObject();
		rootObject.put(CatiaProperties.PARTS.getKey(), partArray);
		rootObject.put(CatiaProperties.PRODUCTS.getKey(), rootProduct);

		return rootObject;
	}

}
