/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.cad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ElementRealization;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * This class tests the Cad exporter.
 *
 */

public class CadExporterTest extends AConceptTestCase {

	private static final double EPSILON = 10e-6;
	private Concept conceptPS;
	private Concept conceptVis;
	private ConfigurationTree ct;
	private ElementConfiguration ec;
	private ElementDefinition ed;
	private ElementOccurence eo;
	private ElementRealization er;
	private Visualisation visualisation;
	private Visualisation edVis;
	private Visualisation ecVis;
	private ElementConfiguration ecSub;
	
	private CadExporter cadExporter;
	
	@Before
	public void setUp() {
		conceptPS = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId());
		conceptVis = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId());
		UserRegistry.getInstance().setSuperUser(true);

		ct = new ConfigurationTree(conceptPS);
		ct.setName("ConfigurationTree");

		ec = new ElementConfiguration(conceptPS);
		ec.setName("ElementConfiguration");
		
		ed = new ElementDefinition(conceptPS);
		ed.setName("ElementDefinition");

		er = new ElementRealization(conceptPS);
		er.setName("ElementRealization");

		eo = new ElementOccurence(conceptPS);
		eo.setName("ElementOccurence");
		
		visualisation = new Visualisation(conceptVis);
		fillVisualisationValues(visualisation);

		cadExporter = new CadExporter();
	}
	
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testTransformEmptyConfigurationTree() {
		JsonObject jsonRoot = cadExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);		
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);
		
		assertTrue("There should be no part created", jsonParts.isEmpty());
		assertNull("There should be no product created", jsonProductRoot);
	}
	
	@Test
	public void testTransformElement() {
		JsonObject jsonElement = cadExporter.transformElement(ed);
		
		assertEquals("Name should be copied", ed.getName(), jsonElement.getString(CadProperties.NAME));
		assertEquals("UUID should be copied", ed.getUuid(), jsonElement.getString(CadProperties.UUID));
	}

	@Test
	public void testTransformConfigurationTreeWithVisualisation() {
		ct.add(visualisation);
		ct.add(ec);
		
		JsonObject jsonRoot = cadExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);		
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);
		
		assertJsonProductEqualsVisualisation(jsonProductRoot, visualisation);

		JsonArray children = jsonProductRoot.getCollection(CadProperties.PRODUCT_CHILDREN);
		assertTrue("There should be no children", children.isEmpty());
		assertEquals("Json Product has a correct part UUID", ct.getUuid(), jsonProductRoot.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product has a correct part name", ct.getName(), jsonProductRoot.getString(CadProperties.PRODUCT_PART_NAME));
		
		assertEquals("There should be one part created", 1, jsonParts.size());
		JsonObject ctPart = jsonParts.getMap(0);
		assertJsonPartEqualsVisualisation(ctPart, visualisation);
	}

	@Test
	public void testTransformConfigurationTreeWithVisualisationChild() {
		ct.add(ec);
		ec.add(visualisation);
		
		final String GEOMETRY_FILES_EXPORT_DESTINATION = "X:\\whatever";
		final String GEOMETRY_FILE_NAME = "test.stl";
		final URI GEOMETRY_FILE_URI = URI.createPlatformResourceURI(GEOMETRY_FILE_NAME, false);
		
		visualisation.setShape(Visualisation.SHAPE_GEOMETRY_NAME);
		visualisation.setGeometryFile(GEOMETRY_FILE_URI);
		
		cadExporter.setGeometryFilesPath(GEOMETRY_FILES_EXPORT_DESTINATION);
		JsonObject jsonRoot = cadExporter.transform(ct);
		
		// Check that the configuration tree was transformed correctly
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);
		
		JsonArray children = jsonProductRoot.getCollection(CadProperties.PRODUCT_CHILDREN);
		assertEquals("The child element should be copied", 1, children.size());
		
		// Check that the element configuration was transformed correctly
		JsonObject subProduct = children.getMap(0);
		
		assertJsonProductEqualsVisualisation(subProduct, visualisation);
		assertEquals("Json Product has a correct part UUID", ec.getUuid(), subProduct.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product has a correct part name", ec.getName(), subProduct.getString(CadProperties.PRODUCT_PART_NAME));
		
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);		
		assertEquals("There should be 1 part", 1, jsonParts.size());
		
		JsonObject ecPart = findByUuid(jsonParts, ec.getUuid());

		assertNotNull("EC is in parts", ecPart);
		
		assertJsonPartEqualsVisualisation(ecPart, visualisation);

		Set<Visualisation> geometryVisualisations = cadExporter.getGeometryVisualisations();
		assertEquals("One geometry", 1, geometryVisualisations.size());
		assertTrue("Geometry comes from the correct visualisation", geometryVisualisations.contains(visualisation));
	}

	@Test
	public void testTransformPartWithVisualisation() {
		ed.add(visualisation);
		
		JsonObject jsonPart = cadExporter.transformPart(ed);
		
		assertEquals("Name should be copied", ed.getName(), jsonPart.getString(CadProperties.NAME));
		assertEquals("UUID should be copied", ed.getUuid(), jsonPart.getString(CadProperties.UUID));

		assertJsonPartEqualsVisualisation(jsonPart, visualisation);
	}

	@Test
	public void testPartsFromConfigurationTreeWithOverriddenVisualisation() {
		ct.add(visualisation);
		ct.add(ec);

		Visualisation edVis = new Visualisation(conceptVis);
		fillVisualisationValues(edVis);
		ed.add(edVis);
		ec.addSuperSei(ed);
		
		InheritanceCopier ic = new InheritanceCopier();
		ic.updateStep(ec.getStructuralElementInstance());
		
		//Overriding some values
		Visualisation visInherited = ec.getFirst(Visualisation.class);
		visInherited.setShape(Visualisation.SHAPE_CONE_NAME);
		visInherited.getShapeBean().getTypeInstance().setOverride(true);
		visInherited.setPositionX(-1);
		visInherited.getPositionXBean().getTypeInstance().setOverride(true);
		
		JsonObject jsonRoot = cadExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);
		
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);
		assertJsonProductEqualsVisualisation(jsonProductRoot, visualisation);
		assertEquals("Json Product has a correct part UUID", ct.getUuid(), jsonProductRoot.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product has a correct part name", ct.getName(), jsonProductRoot.getString(CadProperties.PRODUCT_PART_NAME));

		JsonArray ctChildren = jsonProductRoot.getCollection(CadProperties.PRODUCT_CHILDREN);
		JsonObject ecProduct = ctChildren.getMap(0);
		
		assertEquals("2 parts", 2, jsonParts.size());
		JsonObject ctPart = findByUuid(jsonParts, ct.getUuid());
		JsonObject ecPart = findByUuid(jsonParts, ec.getUuid());

		assertNotNull("CT is in parts", ctPart);
		assertNotNull("EC is in parts", ecPart);
		
		assertJsonPartEqualsVisualisation(ctPart, visualisation);
		assertJsonPartEqualsVisualisation(ecPart, visInherited);
		assertJsonProductEqualsVisualisation(ecProduct, visInherited);
		assertEquals("Json Product has a correct part UUID", ec.getUuid(), ecProduct.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product has a correct part name", ec.getName(), ecProduct.getString(CadProperties.PRODUCT_PART_NAME));
	}

	@Test
	public void testPartsFromConfigurationTreeWithEcVisualisation() {
		ct.add(visualisation);
		ct.add(ec);

		Visualisation ecVis = new Visualisation(conceptVis);
		fillVisualisationValues(ecVis);
		ec.add(ecVis);
		ec.addSuperSei(ed);
		
		JsonObject jsonRoot = cadExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);
		
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);
		assertJsonProductEqualsVisualisation(jsonProductRoot, visualisation);
		assertEquals("Json Product has a correct part UUID", ct.getUuid(), jsonProductRoot.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product has a correct part name", ct.getName(), jsonProductRoot.getString(CadProperties.PRODUCT_PART_NAME));
		
		JsonArray ctChildren = jsonProductRoot.getCollection(CadProperties.PRODUCT_CHILDREN);
		JsonObject ecProduct = ctChildren.getMap(0);
		
		assertEquals("2 parts", 2, jsonParts.size());
		JsonObject ctPart = findByUuid(jsonParts, ct.getUuid());
		JsonObject ecPart = findByUuid(jsonParts, ec.getUuid());

		assertNotNull("CT is in parts", ctPart);
		assertNotNull("EC is in parts", ecPart);
		
		assertJsonPartEqualsVisualisation(ctPart, visualisation);
		assertJsonPartEqualsVisualisation(ecPart, ecVis);
		assertJsonProductEqualsVisualisation(ecProduct, ecVis);
		assertEquals("Json Product has a correct part UUID", ec.getUuid(), ecProduct.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product has a correct part name", ec.getName(), ecProduct.getString(CadProperties.PRODUCT_PART_NAME));
	}

	@Test
	public void testDiamondInheritance() {
		InheritanceCopier ic = new InheritanceCopier();

		ed.add(visualisation);
		
		ec.addSuperSei(ed);
		er.addSuperSei(ed);
		
		eo.addSuperSei(ec);
		eo.addSuperSei(er);

		Arrays.asList(ed, ec, er, eo)
				.forEach(beanSei -> ic.updateStep(beanSei.getStructuralElementInstance()));
		
		JsonObject jsonRoot = cadExporter.transform(eo);
		
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);

		assertJsonProductEqualsVisualisation(jsonProductRoot, visualisation);

		JsonArray children = jsonProductRoot.getCollection(CadProperties.PRODUCT_CHILDREN);
		assertTrue("There should be no children", children.isEmpty());
		assertEquals("Json Part link is set correctly", ed.getUuid(), jsonProductRoot.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Part link is set correctly", ed.getName(), jsonProductRoot.getString(CadProperties.PRODUCT_PART_NAME));
		
		assertEquals("1 part", 1, jsonParts.size());
		JsonObject part = jsonParts.getMap(0);

		assertEquals("Json Part is ED", ed.getUuid(), part.getString(CadProperties.UUID));
		assertJsonPartEqualsVisualisation(part, visualisation);
	}
	
	/**
	 * Tests that all vis values from json part are the same as in the visualisation bean
	 * @param jsonPart 
	 * @param visualisation 
	 */
	private void assertJsonPartEqualsVisualisation(JsonObject jsonPart, Visualisation visualisation) {
		assertEquals("Size X matches", visualisation.getSizeX(), jsonPart.getDouble(CadProperties.PART_LENGTH_X), EPSILON);
		assertEquals("Size Y matches", visualisation.getSizeY(), jsonPart.getDouble(CadProperties.PART_LENGTH_Y), EPSILON);
		assertEquals("Size Z matches", visualisation.getSizeZ(), jsonPart.getDouble(CadProperties.PART_LENGTH_Z), EPSILON);
		assertEquals("Radius matches", visualisation.getRadius(), jsonPart.getDouble(CadProperties.PART_RADIUS), EPSILON);
		assertEquals("Color matches", visualisation.getColor(), (int) jsonPart.getInteger(CadProperties.PART_COLOR));
		
		assertEquals("Shape matches", visualisation.getShape(), jsonPart.getString(CadProperties.PART_SHAPE));
		if (!visualisation.getShape().equals(Visualisation.SHAPE_GEOMETRY_NAME)) {
			assertFalse("No geometry path if not geometry shape", jsonPart.containsKey(CadProperties.PART_STL_PATH.getKey()));
		}
	}

	/**
	 * Tests that all vis values from json part are the same as in the visualisation bean
	 * @param jsonProduct 
	 * @param visualisation 
	 */
	private void assertJsonProductEqualsVisualisation(JsonObject jsonProduct, Visualisation visualisation) {
		assertEquals("The X position should be copied", visualisation.getPositionX(), jsonProduct.getDouble(CadProperties.PRODUCT_POS_X), EPSILON);
		assertEquals("The Y position should be copied", visualisation.getPositionY(), jsonProduct.getDouble(CadProperties.PRODUCT_POS_Y), EPSILON);
		assertEquals("The Z position should be copied", visualisation.getPositionZ(), jsonProduct.getDouble(CadProperties.PRODUCT_POS_Z), EPSILON);

		assertEquals("The X rotation should be copied", visualisation.getRotationX(), jsonProduct.getDouble(CadProperties.PRODUCT_ROT_X), EPSILON);
		assertEquals("The Y rotation should be copied", visualisation.getRotationY(), jsonProduct.getDouble(CadProperties.PRODUCT_ROT_Y), EPSILON);
		assertEquals("The Z rotation should be copied", visualisation.getRotationZ(), jsonProduct.getDouble(CadProperties.PRODUCT_ROT_Z), EPSILON);
	}
	
	/**
	 * @param jsonArray 
	 * @param uuid 
	 * @return JsonObject that with the given UUID from the given array or null
	 */
	private JsonObject findByUuid(JsonArray jsonArray, String uuid) {
		return jsonArray.stream()
				.filter(x -> ((JsonObject) x).getString(CadProperties.UUID).equals(uuid))
				.map(part -> (JsonObject) part)
				.findFirst().orElse(null);
	}
	
	/**
	 * Sets visualisation fields in the given bean to some test numbers
	 * @param visualisation 
	 */
	private void fillVisualisationValues(Visualisation visualisation) {
		final double POSITION_X = 1;
		final double POSITION_Y = 2;
		final double POSITION_Z = 3;

		final double ROTATION_X = 0;
		final double ROTATION_Y = 45;
		final double ROTATION_Z = 90;
		
		final double LENGTH_X = 1;
		final double LENGTH_Y = 2;
		final double LENGTH_Z = 3;

		final double RADIUS = 4;
		final int COLOR = 255;
		
		
		visualisation.setShape(Visualisation.SHAPE_BOX_NAME);
		visualisation.setPositionX(POSITION_X);
		visualisation.setPositionY(POSITION_Y);
		visualisation.setPositionZ(POSITION_Z);

		visualisation.setRotationX(ROTATION_X);
		visualisation.setRotationY(ROTATION_Y);
		visualisation.setRotationZ(ROTATION_Z);
		
		visualisation.setSizeX(LENGTH_X);
		visualisation.setSizeX(LENGTH_Y);
		visualisation.setSizeX(LENGTH_Z);
		
		visualisation.setRadius(RADIUS);
		visualisation.setColor(COLOR);
	}
	
	/**
	 * This method prepares the data for checking the export of a whole configuration tree.
	 * the Configuration is called Satellite, has a SubSystem that contains
	 * The Equipment_1. This Equipment is typed by an ElementDefinition.
	 * This ED contains the Visualization CA which gets inherited into the Configuration Tree.
	 * The Satellite and the SubSystem have a their individual categories. which will be changed
	 * adjusted and checked in this test.
	 */
	private void prepareDataForConfigurationTreeTests() {
		// Setup the root object which is now called Satellite and has a Visualization CA with NONE Shape
		visualisation.setShape(Visualisation.SHAPE_NONE_NAME);
		ct.add(visualisation);
		ct.setName("Satellite");

		// Create the SubSytsem which does not yet have a CA
		ecSub = new ElementConfiguration(conceptPS);
		ecSub.setName("SubSystem");
		ct.add(ecSub);

		// Now add the Visualization CA to the Equipment and run the inheritance copier
		// then make sure it is also added to the EC of the Equipment
		edVis = new Visualisation(conceptVis);
		fillVisualisationValues(edVis);
		ec.addSuperSei(ed);
		ecSub.add(ec);
		ec.setName("Equipment_1");
		ed.setName("Equipment");
		ed.add(edVis);
		new InheritanceCopier().updateStep(ec.getStructuralElementInstance());
		ecVis = ec.getFirst(Visualisation.class);
		assertNotNull("Found a Visualization CA at the ec representing the Equipment", ecVis);
	}

	/**
	 *  Case 1 - No Visualization CA for the Sub System
	 *	The expected result is, that the parts should only contain the part from the element definition
	 *	The product tree which is exported should have the root node from Satellite and a direct child
	 *	to the equipment which references the part.
	 */
	@Test
	public void testConfigurationTreeExportWithSubAssemblyNoCA() {
		prepareDataForConfigurationTreeTests();

		// Now run the transformation on the configuration tree and extract the parts and products
		JsonObject jsonRoot = cadExporter.transform(ct);
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);

		// Check that the root of the product tree is correctly set. Since the Shape is set to None
		// It does not have a corresponding part and thus there should be no references to a part
		assertNull("Json Product Root has no Part UUID, since there is NONE Shape", jsonProductRoot.getString(CadProperties.PRODUCT_PART_UUID));
		assertNull("Json Product Root has no Part Name, since there is NONE Shape", jsonProductRoot.getString(CadProperties.PRODUCT_PART_NAME));

		// First make sure the parts are correct
		assertEquals("There is only one part from the ED", 1, jsonParts.size());
		JsonObject edPart = findByUuid(jsonParts, ed.getUuid());

		assertNotNull("ED is in parts", edPart);
		assertJsonPartEqualsVisualisation(edPart, edVis);

		// than check the products are correct
		assertJsonProductEqualsVisualisation(jsonProductRoot, visualisation);
		JsonArray jsonProductChildren = jsonProductRoot.getCollection(CadProperties.PRODUCT_CHILDREN);
		JsonObject jsonProductEquipment = jsonProductChildren.getMap(0);

		assertJsonProductEqualsVisualisation(jsonProductEquipment, edVis);
		assertJsonProductEqualsVisualisation(jsonProductEquipment, ecVis);
		assertEquals("Json Product has a correct part UUID", ed.getUuid(), jsonProductEquipment.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product has a correct part name", ed.getName(), jsonProductEquipment.getString(CadProperties.PRODUCT_PART_NAME));
	}

	/**
	 * Test Case 2 - Visualization CA for subSystem with NONE shape
	 * This test case checks the export of a whole configuration tree.
	 * the Configuration is called Satellite, has a SubSystem that contains
	 * The Equipment_1. This Equipment is typed by an ElementDefinition.
	 * This ED contains the Visualization CA which gets inherited into the Configuration Tree.
	 * The Satellite and the SubSystem have a their individual categories. which will be changed
	 * adjusted and checked in this test.
	 */
	@Test
	public void testConfigurationTreeExportWithSubAssemblyWithCANoShape() {
		prepareDataForConfigurationTreeTests();

		Visualisation ecSubVis = new Visualisation(conceptVis);
		ecSub.add(ecSubVis);
		ecSubVis.setShape(Visualisation.SHAPE_NONE_NAME);

		// Now run the transformation on the configuration tree and extract the parts and products
		JsonObject jsonRoot = cadExporter.transform(ct);
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);

		// First make sure the parts are correct
		assertEquals("There is only one part from the ED", 1, jsonParts.size());
		JsonObject edPart = findByUuid(jsonParts, ed.getUuid());

		assertNotNull("ED is in parts", edPart);
		assertJsonPartEqualsVisualisation(edPart, edVis);

		// than check the products are correct
		assertJsonProductEqualsVisualisation(jsonProductRoot, visualisation);
		JsonArray jsonProductChildren = jsonProductRoot.getCollection(CadProperties.PRODUCT_CHILDREN);
		JsonObject jsonProductSubSystem = jsonProductChildren.getMap(0);
		JsonArray jsonProductSubSystemChildren = jsonProductSubSystem.getCollection(CadProperties.PRODUCT_CHILDREN);
		JsonObject jsonProductEquipment = jsonProductSubSystemChildren.getMap(0);

		assertJsonProductEqualsVisualisation(jsonProductSubSystem, ecSubVis);
		assertNull("Json Product SubSystem has no Part UUID, since there is NONE Shape", jsonProductSubSystem.getString(CadProperties.PRODUCT_PART_UUID));
		assertNull("Json Product SubSystem has no Part Name, since there is NONE Shape", jsonProductSubSystem.getString(CadProperties.PRODUCT_PART_NAME));

		assertJsonProductEqualsVisualisation(jsonProductEquipment, edVis);
		assertJsonProductEqualsVisualisation(jsonProductEquipment, ecVis);
		assertEquals("Json Product has a correct part UUID", ed.getUuid(), jsonProductEquipment.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product has a correct part name", ed.getName(), jsonProductEquipment.getString(CadProperties.PRODUCT_PART_NAME));
	}

	/**
     * Case 3 - Switching the Shape of the SubSystem to Sphere
	 * This will create a second part. The product subsystem should now also reference this new part
	 */
	@Test
	public void testConfigurationTreeExportWithSubAssemblyWithCaAndShape() {
		prepareDataForConfigurationTreeTests();

		Visualisation ecSubVis = new Visualisation(conceptVis);
		ecSub.add(ecSubVis);
		ecSubVis.setShape(Visualisation.SHAPE_SPHERE_NAME);

		// Now run the transformation on the configuration tree and extract the parts and products
		JsonObject jsonRoot = cadExporter.transform(ct);
		JsonArray jsonParts = jsonRoot.getCollection(CadProperties.PARTS);
		JsonObject jsonProductRoot = jsonRoot.getMap(CadProperties.PRODUCTS);

		// First make sure the parts are correct
		assertEquals("There are now two Parts, One from the ED and one from the EC sub System", 2, jsonParts.size());
		JsonObject edPart = findByUuid(jsonParts, ed.getUuid());
		JsonObject ecPart = findByUuid(jsonParts, ecSub.getUuid());

		assertNotNull("ED is in parts", edPart);
		assertNotNull("EC is in parts", ecPart);
		assertJsonPartEqualsVisualisation(edPart, edVis);
		assertJsonPartEqualsVisualisation(ecPart, ecSubVis);

		// than check the products are correct
		assertJsonProductEqualsVisualisation(jsonProductRoot, visualisation);
		JsonArray jsonProductChildren = jsonProductRoot.getCollection(CadProperties.PRODUCT_CHILDREN);
		JsonObject jsonProductSubSystem = jsonProductChildren.getMap(0);
		JsonArray jsonProductSubSystemChildren = jsonProductSubSystem.getCollection(CadProperties.PRODUCT_CHILDREN);
		JsonObject jsonProductEquipment = jsonProductSubSystemChildren.getMap(0);

		assertJsonProductEqualsVisualisation(jsonProductSubSystem, ecSubVis);
		assertEquals("Json Product SubSystem has a correct part UUID", ecSub.getUuid(), jsonProductSubSystem.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product SubSystem has a correct part name", ecSub.getName(), jsonProductSubSystem.getString(CadProperties.PRODUCT_PART_NAME));

		assertJsonProductEqualsVisualisation(jsonProductEquipment, edVis);
		assertJsonProductEqualsVisualisation(jsonProductEquipment, ecVis);
		assertEquals("Json Product Equipment has a correct part UUID", ed.getUuid(), jsonProductEquipment.getString(CadProperties.PRODUCT_PART_UUID));
		assertEquals("Json Product Equipment has a correct part name", ed.getName(), jsonProductEquipment.getString(CadProperties.PRODUCT_PART_NAME));
	}
}
