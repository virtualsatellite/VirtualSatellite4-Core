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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * This class tests the Catia exporter.
 *
 */

public class CatiaExporterTest extends AConceptTestCase {

	private static final double EPSILON = 10e-6;
	private Concept conceptPS;
	private Concept conceptVis;
	
	@Before
	public void setUp() {
		conceptPS = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId());
		conceptVis = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId());
		UserRegistry.getInstance().setSuperUser(true);
	}
	
	@Test
	public void testTransformEmptyConfigurationTree() {
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		JsonObject jsonRoot = catiaExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CatiaProperties.PARTS);		
		JsonObject jsonProductRoot = jsonRoot.getMap(CatiaProperties.PRODUCTS);
		
		assertTrue("There should be no part created", jsonParts.isEmpty());
		assertNull("There should be no product created", jsonProductRoot);
	}
	
	@Test
	public void testTransformParts() {
		// Test transformation if there is no attached visualization
		ElementDefinition ed = new ElementDefinition(conceptPS);
		List<ElementDefinition> eds = new ArrayList<>();
		eds.add(ed);
	
		CatiaExporter catiaExporter = new CatiaExporter();
		JsonArray jsonParts = catiaExporter.transformParts(eds);
		
		assertTrue("There should be no part created", jsonParts.isEmpty());
		
		// Test transformation with attached visualization
		Visualisation visualisation = new Visualisation(conceptVis);
		ed.add(visualisation);
		
		jsonParts = catiaExporter.transformParts(eds);
		
		assertEquals(1, jsonParts.size());
		
		JsonObject jsonPart = jsonParts.getMap(0);
		
		assertEquals("Name should be copied", ed.getName(), jsonPart.getString(CatiaProperties.NAME));
		assertEquals("UUID should be copied", ed.getUuid(), jsonPart.getString(CatiaProperties.UUID));
	}
	
	@Test
	public void testTransformElement() {
		ElementDefinition ed = new ElementDefinition(conceptPS);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		JsonObject jsonElement = catiaExporter.transformElement(ed);
		
		assertEquals("Name should be copied", ed.getName(), jsonElement.getString(CatiaProperties.NAME));
		assertEquals("UUID should be copied", ed.getUuid(), jsonElement.getString(CatiaProperties.UUID));
	}

	@Test
	public void testTransformConfigurationTreeWithVisualisation() {
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		Visualisation visualisation = new Visualisation(conceptVis);
		ct.add(visualisation);

		ElementConfiguration ec = new ElementConfiguration(conceptPS);
		ct.add(ec);
		
		fillVisualisationValues(visualisation);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		JsonObject jsonRoot = catiaExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CatiaProperties.PARTS);		
		JsonObject jsonProductRoot = jsonRoot.getMap(CatiaProperties.PRODUCTS);
		
		assertJsonProductEqualsVisualisation(jsonProductRoot, visualisation);

		JsonArray children = jsonProductRoot.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		assertTrue("There should be no children", children.isEmpty());
		assertEquals("Json Product has a correct part UUID", ct.getUuid(), jsonProductRoot.getString(CatiaProperties.PRODUCT_ED_UUID));
		assertEquals("Json Product has a correct part name", ct.getName(), jsonProductRoot.getString(CatiaProperties.PRODUCT_REFERENCE_NAME));
		
		assertEquals("There should be one part created", 1, jsonParts.size());
		JsonObject ctPart = jsonParts.getMap(0);
		assertJsonPartEqualsVisualisation(ctPart, visualisation);
	}

	@Test
	public void testTransformConfigurationTreeWithVisualisationChild() {
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		ElementConfiguration ec = new ElementConfiguration(conceptPS);
		Visualisation visualisation = new Visualisation(conceptVis);
		ec.add(visualisation);
		
		ct.add(ec);
		
		fillVisualisationValues(visualisation);
		
		final String GEOMETRY_FILES_EXPORT_DESTINATION = "X:\\whatever";
		final String GEOMETRY_FILE_NAME = "test.stl";
		final String EXPECTED_GEOMETRY_FILE_PATH = GEOMETRY_FILES_EXPORT_DESTINATION + File.separator + GEOMETRY_FILE_NAME;
		final URI GEOMETRY_FILE_URI = URI.createPlatformResourceURI(GEOMETRY_FILE_NAME, false);
		
		visualisation.setShape(Visualisation.SHAPE_GEOMETRY_NAME);
		visualisation.setGeometryFile(GEOMETRY_FILE_URI);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		catiaExporter.setGeometryFilesPath(GEOMETRY_FILES_EXPORT_DESTINATION);
		JsonObject jsonRoot = catiaExporter.transform(ct);
		
		// Check that the configuration tree was transformed correctly
		JsonObject jsonProductRoot = jsonRoot.getMap(CatiaProperties.PRODUCTS);
		
		assertJsonProductEqualsVisualisation(jsonProductRoot, new Visualisation(conceptVis));
		assertEquals("Json Product has a correct part UUID", ct.getUuid(), jsonProductRoot.getString(CatiaProperties.PRODUCT_ED_UUID));
		assertEquals("Json Product has a correct part name", ct.getName(), jsonProductRoot.getString(CatiaProperties.PRODUCT_REFERENCE_NAME));

		JsonArray children = jsonProductRoot.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		assertEquals("The child element should be copied", 1, children.size());
		
		// Check that the element configuration was transformed correctly
		JsonObject subProduct = children.getMap(0);
		
		assertJsonProductEqualsVisualisation(subProduct, visualisation);
		assertEquals("The Geometry path should be copied", EXPECTED_GEOMETRY_FILE_PATH, subProduct.getString(CatiaProperties.PRODUCT_STL_PATH));
		assertEquals("Json Product has a correct part UUID", ec.getUuid(), subProduct.getString(CatiaProperties.PRODUCT_ED_UUID));
		assertEquals("Json Product has a correct part name", ec.getName(), subProduct.getString(CatiaProperties.PRODUCT_REFERENCE_NAME));
		
		JsonArray jsonParts = jsonRoot.getCollection(CatiaProperties.PARTS);		
		assertEquals("There should be 2 parts", 2, jsonParts.size());
		
		JsonObject ctPart = findByUuid(jsonParts, ct.getUuid());
		JsonObject ecPart = findByUuid(jsonParts, ec.getUuid());

		assertNotNull("CT is in parts", ctPart);
		assertNotNull("EC is in parts", ecPart);
		
		assertJsonPartEqualsVisualisation(ecPart, visualisation);
		//Dummy visualisation part
		assertJsonPartEqualsVisualisation(ctPart, new Visualisation(conceptVis));
	}

	@Test
	public void testTransformPartWithVisualisation() {
		ElementDefinition ed = new ElementDefinition(conceptPS);

		Visualisation visualisation = new Visualisation(conceptVis);
		fillVisualisationValues(visualisation);
		ed.add(visualisation);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		JsonObject jsonPart = catiaExporter.transformPart(ed);
		
		assertEquals("Name should be copied", ed.getName(), jsonPart.getString(CatiaProperties.NAME));
		assertEquals("UUID should be copied", ed.getUuid(), jsonPart.getString(CatiaProperties.UUID));

		assertJsonPartEqualsVisualisation(jsonPart, visualisation);
	}

	@Test
	public void testPartsFromConfigurationTreeWithInheritedVisualisation() {
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		Visualisation ctVis = new Visualisation(conceptVis);
		fillVisualisationValues(ctVis);
		ct.add(ctVis);
		

		ElementConfiguration ec = new ElementConfiguration(conceptPS);
		ct.add(ec);

		ElementDefinition ed = new ElementDefinition(conceptPS);
		Visualisation edVis = new Visualisation(conceptVis);
		fillVisualisationValues(edVis);
		ed.add(edVis);
		
		ec.addSuperSei(ed);
		
		performInheritanceOn(ec.getStructuralElementInstance());
		
		//Overriding some values
		Visualisation visInherited = ec.getFirst(Visualisation.class);
		visInherited.setShape(Visualisation.SHAPE_CONE_NAME);
		visInherited.setPositionX(-1);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		JsonObject jsonRoot = catiaExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CatiaProperties.PARTS);
		
		JsonObject jsonProductRoot = jsonRoot.getMap(CatiaProperties.PRODUCTS);
		assertJsonProductEqualsVisualisation(jsonProductRoot, ctVis);
		assertEquals("Json Product has a correct part UUID", ct.getUuid(), jsonProductRoot.getString(CatiaProperties.PRODUCT_ED_UUID));
		assertEquals("Json Product has a correct part name", ct.getName(), jsonProductRoot.getString(CatiaProperties.PRODUCT_REFERENCE_NAME));

		JsonArray ctChildren = jsonProductRoot.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		JsonObject ecProduct = ctChildren.getMap(0);
		
		assertEquals("2 parts", 2, jsonParts.size());
		JsonObject ctPart = findByUuid(jsonParts, ct.getUuid());
		JsonObject edPart = findByUuid(jsonParts, ed.getUuid());

		assertNotNull("CT is in parts", ctPart);
		assertNotNull("ED is in parts", edPart);
		
		assertJsonPartEqualsVisualisation(ctPart, ctVis);
		assertJsonPartEqualsVisualisation(edPart, edVis);
		assertJsonProductEqualsVisualisation(ecProduct, visInherited);
		assertEquals("Json Product has a correct part UUID", ed.getUuid(), ecProduct.getString(CatiaProperties.PRODUCT_ED_UUID));
		assertEquals("Json Product has a correct part name", ed.getName(), ecProduct.getString(CatiaProperties.PRODUCT_REFERENCE_NAME));
	}

	@Test
	public void testPartsFromConfigurationTreeWithEcVisualisation() {
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		Visualisation ctVis = new Visualisation(conceptVis);
		fillVisualisationValues(ctVis);
		ct.add(ctVis);
		
		ElementConfiguration ec = new ElementConfiguration(conceptPS);
		ct.add(ec);
		Visualisation ecVis = new Visualisation(conceptVis);
		fillVisualisationValues(ecVis);
		ec.add(ecVis);

		ElementDefinition ed = new ElementDefinition(conceptPS);
		ec.addSuperSei(ed);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		JsonObject jsonRoot = catiaExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CatiaProperties.PARTS);
		
		JsonObject jsonProductRoot = jsonRoot.getMap(CatiaProperties.PRODUCTS);
		assertJsonProductEqualsVisualisation(jsonProductRoot, ctVis);
		assertEquals("Json Product has a correct part UUID", ct.getUuid(), jsonProductRoot.getString(CatiaProperties.PRODUCT_ED_UUID));
		assertEquals("Json Product has a correct part name", ct.getName(), jsonProductRoot.getString(CatiaProperties.PRODUCT_REFERENCE_NAME));
		
		JsonArray ctChildren = jsonProductRoot.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		JsonObject ecProduct = ctChildren.getMap(0);
		
		assertEquals("2 parts", 2, jsonParts.size());
		JsonObject ctPart = findByUuid(jsonParts, ct.getUuid());
		JsonObject ecPart = findByUuid(jsonParts, ec.getUuid());

		assertNotNull("CT is in parts", ctPart);
		assertNotNull("EC is in parts", ecPart);
		
		assertJsonPartEqualsVisualisation(ctPart, ctVis);
		assertJsonPartEqualsVisualisation(ecPart, ecVis);
		assertJsonProductEqualsVisualisation(ecProduct, ecVis);
		assertEquals("Json Product has a correct part UUID", ec.getUuid(), ecProduct.getString(CatiaProperties.PRODUCT_ED_UUID));
		assertEquals("Json Product has a correct part name", ec.getName(), ecProduct.getString(CatiaProperties.PRODUCT_REFERENCE_NAME));
	}
	
	/**
	 * copies inherited CAs into the given sei
	 * @param sei 
	 */
	private void performInheritanceOn(StructuralElementInstance sei) {
		new InheritanceCopier() {
			@Override
			public Set<CategoryAssignment> updateStep(StructuralElementInstance subSei) {
				return super.updateStep(subSei);
			}
		}.updateStep(sei);
	}

	/**
	 * Tests that all vis values from json part are the same as in the visualisation bean
	 * @param jsonPart 
	 * @param visualisation 
	 */
	private void assertJsonPartEqualsVisualisation(JsonObject jsonPart, Visualisation visualisation) {
		assertEquals("Size X matches", visualisation.getSizeX(), jsonPart.getDouble(CatiaProperties.PART_LENGTH_X), EPSILON);
		assertEquals("Size Y matches", visualisation.getSizeY(), jsonPart.getDouble(CatiaProperties.PART_LENGTH_Y), EPSILON);
		assertEquals("Size Z matches", visualisation.getSizeZ(), jsonPart.getDouble(CatiaProperties.PART_LENGTH_Z), EPSILON);
		assertEquals("Radius matches", visualisation.getRadius(), jsonPart.getDouble(CatiaProperties.PART_RADIUS), EPSILON);
		assertEquals("Color matches", visualisation.getColor(), (int) jsonPart.getInteger(CatiaProperties.PART_COLOR));
		
		assertEquals("Shape matches", visualisation.getShape(), jsonPart.getString(CatiaProperties.PART_SHAPE));
		if (!visualisation.getShape().equals(Visualisation.SHAPE_GEOMETRY_NAME)) {
			assertFalse("No geometry path if not geometry shape", jsonPart.containsKey(CatiaProperties.PART_STL_PATH.getKey()));
		}
	}

	/**
	 * Tests that all vis values from json part are the same as in the visualisation bean
	 * @param jsonProduct 
	 * @param visualisation 
	 */
	private void assertJsonProductEqualsVisualisation(JsonObject jsonProduct, Visualisation visualisation) {
		assertEquals("The X position should be copied", visualisation.getPositionX(), jsonProduct.getDouble(CatiaProperties.PRODUCT_POS_X), EPSILON);
		assertEquals("The Y position should be copied", visualisation.getPositionY(), jsonProduct.getDouble(CatiaProperties.PRODUCT_POS_Y), EPSILON);
		assertEquals("The Z position should be copied", visualisation.getPositionZ(), jsonProduct.getDouble(CatiaProperties.PRODUCT_POS_Z), EPSILON);

		assertEquals("The X rotation should be copied", visualisation.getRotationX(), jsonProduct.getDouble(CatiaProperties.PRODUCT_ROT_X), EPSILON);
		assertEquals("The Y rotation should be copied", visualisation.getRotationY(), jsonProduct.getDouble(CatiaProperties.PRODUCT_ROT_Y), EPSILON);
		assertEquals("The Z rotation should be copied", visualisation.getRotationZ(), jsonProduct.getDouble(CatiaProperties.PRODUCT_ROT_Z), EPSILON);

		assertEquals("The Shape should be copied", visualisation.getShape(), jsonProduct.getString(CatiaProperties.PRODUCT_SHAPE));

		assertEquals("Shape matches", visualisation.getShape(), jsonProduct.getString(CatiaProperties.PART_SHAPE));
		if (!visualisation.getShape().equals(Visualisation.SHAPE_GEOMETRY_NAME)) {
			assertFalse("No geometry path if not geometry shape", jsonProduct.containsKey(CatiaProperties.PART_STL_PATH.getKey()));
		}
	}
	
	/**
	 * @param jsonArray 
	 * @param uuid 
	 * @return JsonObject that with the given UUID from the given array or null
	 */
	private JsonObject findByUuid(JsonArray jsonArray, String uuid) {
		return jsonArray.stream()
				.filter(x -> ((JsonObject) x).getString(CatiaProperties.UUID).equals(uuid))
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
}
