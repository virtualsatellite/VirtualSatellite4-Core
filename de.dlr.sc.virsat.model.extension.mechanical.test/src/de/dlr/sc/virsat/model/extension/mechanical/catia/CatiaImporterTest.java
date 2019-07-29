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
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
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
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * The CATIA importer test class
 *
 */
public class CatiaImporterTest extends AProjectTestCase {

	private Concept conceptPS;
	private Concept conceptVis;

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

	private static final int TEST_POS_X_PRODUCT = 5;
	private static final int TEST_POS_Y_PRODUCT = 5;
	private static final int TEST_POS_Z_PRODUCT = 5;

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		conceptPS = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.ps");
		conceptVis = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.visualisation");

		addEditingDomainAndRepository();
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				repository.getActiveConcepts().add(conceptPS);
				repository.getActiveConcepts().add(conceptVis);
			}
		});
		createTestTreeScenario();
	}

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
	}

	@Test
	public void testTransformProductTree() {

		JsonObject rootObject = createMappedJsonObjectWithProductAndConfiguration();

		// Add some changes to import
		JsonArray partArray = rootObject.getCollection(CatiaProperties.PARTS);
		JsonObject firstPart = partArray.getMap(0);
		firstPart.put(CatiaProperties.PART_SHAPE.getKey(), Visualisation.SHAPE_BOX_NAME);

		JsonObject rootProduct = rootObject.getMap(CatiaProperties.PRODUCTS);
		JsonArray childProducts = rootProduct.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		JsonObject firstChild = childProducts.getMap(0);
		firstChild.put(CatiaProperties.PRODUCT_POS_X.getKey(), TEST_POS_X_PRODUCT);
		firstChild.put(CatiaProperties.PRODUCT_POS_Y.getKey(), TEST_POS_Y_PRODUCT);
		firstChild.put(CatiaProperties.PRODUCT_POS_Z.getKey(), TEST_POS_Z_PRODUCT);
		String testProductID = firstChild.getString(CatiaProperties.UUID);

		// Do the import
		CatiaImporter importer = new CatiaImporter();
		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(rootObject, configurationTree);
		Command importCommand = importer.transform(editingDomain, rootObject, mapping);
		editingDomain.getVirSatCommandStack().execute(importCommand);

		// Check if import worked
		assertEquals("Check if part values are imported", Visualisation.SHAPE_BOX_NAME,
				reactionWheelVisDefinition.getShape());

		IBeanStructuralElementInstance changedSei = configurationTree
				.getDeepChildren(IBeanStructuralElementInstance.class).stream()
					.filter(sei -> !sei.getUuid().equals(testProductID)).collect(Collectors.toList()).get(0);
		assertTrue("Check if product values are imported",
				changedSei.getFirst(Visualisation.class).getPositionX() == TEST_POS_X_PRODUCT);
		assertTrue("Check if product values are imported",
				changedSei.getFirst(Visualisation.class).getPositionY() == TEST_POS_Y_PRODUCT);
		assertTrue("Check if product values are imported",
				changedSei.getFirst(Visualisation.class).getPositionZ() == TEST_POS_Z_PRODUCT);

	}

	@Test
	public void testTransformProductTreeWithoutVisualisation() {

		JsonObject rootObject = createMappedJsonObjectWithProductAndConfiguration();

		// Add some changes to import in a new configuration element without
		// visualisation
		ElementConfiguration elementConfigurationReactionWheel3 = new ElementConfiguration(conceptPS);
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				subSystemAOCS.add(elementConfigurationReactionWheel3);
			}
		});

		JsonObject rootProduct = rootObject.getMap(CatiaProperties.PRODUCTS);
		JsonArray childProducts = rootProduct.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		JsonObject jsonProductofNewConfiguration = new JsonObject();
		jsonProductofNewConfiguration.put(CatiaProperties.UUID.getKey(), elementConfigurationReactionWheel3.getUuid());
		jsonProductofNewConfiguration.put(CatiaProperties.PRODUCT_POS_X.getKey(), TEST_POS_X_PRODUCT);
		jsonProductofNewConfiguration.put(CatiaProperties.PRODUCT_POS_Y.getKey(), TEST_POS_Y_PRODUCT);
		jsonProductofNewConfiguration.put(CatiaProperties.PRODUCT_POS_Z.getKey(), TEST_POS_Z_PRODUCT);
		childProducts.add(jsonProductofNewConfiguration);

		// Do the import
		CatiaImporter importer = new CatiaImporter();
		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(rootObject, configurationTree);
		Command importCommand = importer.transform(editingDomain, rootObject, mapping);
		VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE
				.getEd(configurationTree.getStructuralElementInstance());
		editingDomain.getVirSatCommandStack().execute(importCommand);

		// Check if import worked
		assertTrue("Check if product values are imported",
				elementConfigurationReactionWheel3.getFirst(Visualisation.class).getPositionX() == TEST_POS_X_PRODUCT);
		assertTrue("Check if product values are imported",
				elementConfigurationReactionWheel3.getFirst(Visualisation.class).getPositionY() == TEST_POS_Y_PRODUCT);
		assertTrue("Check if product values are imported",
				elementConfigurationReactionWheel3.getFirst(Visualisation.class).getPositionZ() == TEST_POS_Z_PRODUCT);

	}

	@Test
	public void testMapJSONtoSEIWithNoUnmappedJSONObject() {

		JsonObject rootObject = createMappedJsonObjectWithProductAndConfiguration();

		CatiaImporter importer = new CatiaImporter();
		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(rootObject, configurationTree);
		List<JsonObject> unmappedElements = importer.getUnmappedJSONObjects(rootObject, mapping);

		// Check map
		assertEquals("Map does not contain element definition",
				elementReactionWheelDefinition.getStructuralElementInstance(),
				mapping.get(elementReactionWheelDefinition.getUuid()));
		assertEquals("Map does not contain first element configuration",
				elementConfigurationReactionWheel1.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel1.getUuid()));
		assertEquals("Map does not contain second element configuration",
				elementConfigurationReactionWheel2.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel2.getUuid()));
		assertEquals("Map does not contain root product", subSystemAOCS.getStructuralElementInstance(),
				mapping.get(subSystemAOCS.getUuid()));

		// Check unmapped elements
		assertEquals("Check that there are no umappable elements in the imported JSON", 0, unmappedElements.size());

	}

	@Test
	public void testMapJSONtoSEIWithUnmappedJSONPart() {

		JsonObject rootObject = createMappedJsonObjectWithProductAndConfiguration();

		// Create new unmapped element
		JsonObject unmappedJsonObject = new JsonObject();
		unmappedJsonObject.put(CatiaProperties.UUID.getKey(), UUID.randomUUID().toString());

		JsonArray partArray = rootObject.getCollection(CatiaProperties.PARTS);
		partArray.add(unmappedJsonObject);

		CatiaImporter importer = new CatiaImporter();
		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(rootObject, configurationTree);
		List<JsonObject> unmappedElements = importer.getUnmappedJSONObjects(rootObject, mapping);

		// Check map
		assertEquals("Map does not contain element definition",
				elementReactionWheelDefinition.getStructuralElementInstance(),
				mapping.get(elementReactionWheelDefinition.getUuid()));
		assertEquals("Map does not contain first element configuration",
				elementConfigurationReactionWheel1.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel1.getUuid()));
		assertEquals("Map does not contain second element configuration",
				elementConfigurationReactionWheel2.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel2.getUuid()));
		assertEquals("Map does not contain root product", subSystemAOCS.getStructuralElementInstance(),
				mapping.get(subSystemAOCS.getUuid()));

		// Check unmapped elements
		assertEquals("Check that there is one umappable element in the imported JSON", 1, unmappedElements.size());
		assertEquals("Expected unmapped part not found", unmappedJsonObject, unmappedElements.get(0));

	}

	@Test
	public void testMapJSONtoSEIWithUnmappedJSONProduct() {

		JsonObject rootObject = createMappedJsonObjectWithProductAndConfiguration();

		// Create new unmapped element
		JsonObject unmappedJsonObject = new JsonObject();
		unmappedJsonObject.put(CatiaProperties.UUID.getKey(), UUID.randomUUID().toString());

		JsonObject rootProduct = rootObject.getMap(CatiaProperties.PRODUCTS);
		JsonArray productArray = rootProduct.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		productArray.add(unmappedJsonObject);

		CatiaImporter importer = new CatiaImporter();
		Map<String, StructuralElementInstance> mapping = importer.mapJSONtoSEI(rootObject, configurationTree);
		List<JsonObject> unmappedElements = importer.getUnmappedJSONObjects(rootObject, mapping);

		// Check map
		assertEquals("Map does not contain element definition",
				elementReactionWheelDefinition.getStructuralElementInstance(),
				mapping.get(elementReactionWheelDefinition.getUuid()));
		assertEquals("Map does not contain first element configuration",
				elementConfigurationReactionWheel1.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel1.getUuid()));
		assertEquals("Map does not contain second element configuration",
				elementConfigurationReactionWheel2.getStructuralElementInstance(),
				mapping.get(elementConfigurationReactionWheel2.getUuid()));
		assertEquals("Map does not contain root product", subSystemAOCS.getStructuralElementInstance(),
				mapping.get(subSystemAOCS.getUuid()));

		// Check unmapped elements
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

		// Create tree structure with inheritance
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {

				repository.getRootEntities().add(productTree.getStructuralElementInstance());
				repository.getRootEntities().add(configurationTree.getStructuralElementInstance());
				repository.getRootEntities().add(assemblyTree.getStructuralElementInstance());

				editingDomain.getResourceSet()
						.getAndAddStructuralElementInstanceResource(productTree.getStructuralElementInstance());
				editingDomain.getResourceSet()
						.getAndAddStructuralElementInstanceResource(configurationTree.getStructuralElementInstance());
				editingDomain.getResourceSet()
						.getAndAddStructuralElementInstanceResource(assemblyTree.getStructuralElementInstance());

				new InheritanceCopier().updateAllInOrder(repository, new NullProgressMonitor());
			}
		});

		assertNotNull("Sanitycheck that the inheritance copier worked as expected",
				reactionWheelOccurence1.getFirst(Visualisation.class));

	}

	/**
	 * Create a simple mapped JSON object with parts and products that are mapped to
	 * elements in the test trees
	 * 
	 * @return the JSON root object
	 */
	protected JsonObject createMappedJsonObjectWithProductAndConfiguration() {

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

	/**
	 * Method to load the test concept
	 * 
	 * @param pluginName
	 *            The name of the plugin from which to load the concept
	 * @return the test concept
	 */
	protected Concept loadConceptFromPlugin(String pluginName) {
		String conceptXmiPluginPath = pluginName + "/concept/concept.xmi";
		Concept concept = ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
		return concept;
	}

}
