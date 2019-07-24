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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
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
		conceptPS = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.ps");
		conceptVis = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.visualisation");
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
		
		JsonObject jsonPart = (JsonObject) jsonParts.get(0);
		
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
		
		final double POSITION_X = 1;
		final double POSITION_Y = 2;
		final double POSITION_Z = 3;

		final double ROTATION_X = 0;
		final double ROTATION_Y = 45;
		final double ROTATION_Z = 90;
		
		visualisation.setShape(Visualisation.SHAPE_BOX_NAME);
		visualisation.setPositionX(POSITION_X);
		visualisation.setPositionY(POSITION_Y);
		visualisation.setPositionZ(POSITION_Z);

		visualisation.setRotationX(ROTATION_X);
		visualisation.setRotationY(ROTATION_Y);
		visualisation.setRotationZ(ROTATION_Z);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		JsonObject jsonRoot = catiaExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CatiaProperties.PARTS);		
		JsonObject jsonProductRoot = jsonRoot.getMap(CatiaProperties.PRODUCTS);
		
		assertTrue("There should be no part created", jsonParts.isEmpty());
		
		assertEquals(POSITION_X, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_POS_X), EPSILON);
		assertEquals(POSITION_Y, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_POS_Y), EPSILON);
		assertEquals(POSITION_Z, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_POS_Z), EPSILON);
		
		assertEquals(ROTATION_X, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_ROT_X), EPSILON);
		assertEquals(ROTATION_Y, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_ROT_Y), EPSILON);
		assertEquals(ROTATION_Z, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_ROT_Z), EPSILON);

		assertEquals(Visualisation.SHAPE_BOX_NAME, jsonProductRoot.getString(CatiaProperties.PRODUCT_SHAPE));
		assertFalse(jsonProductRoot.containsKey(CatiaProperties.PRODUCT_STL_PATH.getKey()));

		JsonArray children = jsonProductRoot.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		assertTrue(children.isEmpty());
	}

	@Test
	public void testTransformConfigurationTreeWithVisualisationChild() {
		ConfigurationTree ct = new ConfigurationTree(conceptPS);
		ElementConfiguration ec = new ElementConfiguration(conceptPS);
		Visualisation visualisation = new Visualisation(conceptVis);
		ec.add(visualisation);
		
		ct.add(ec);
		
		final double POSITION_X = 1;
		final double POSITION_Y = 2;
		final double POSITION_Z = 3;

		final double ROTATION_X = 0;
		final double ROTATION_Y = 45;
		final double ROTATION_Z = 90;
		
		final String GEOMETRY_FILES_EXPORT_DESTINATION = "X:\\whatever";
		final String GEOMETRY_FILE_NAME = "test.stl";
		final String EXPECTED_GEOMETRY_FILE_PATH = GEOMETRY_FILES_EXPORT_DESTINATION + "\\" + GEOMETRY_FILE_NAME;
		final URI GEOMETRY_FILE_URI = URI.createPlatformResourceURI(GEOMETRY_FILE_NAME, false);
		
		visualisation.setShape(Visualisation.SHAPE_GEOMETRY_NAME);
		visualisation.setGeometryFile(GEOMETRY_FILE_URI);
		visualisation.setPositionX(POSITION_X);
		visualisation.setPositionY(POSITION_Y);
		visualisation.setPositionZ(POSITION_Z);

		visualisation.setRotationX(ROTATION_X);
		visualisation.setRotationY(ROTATION_Y);
		visualisation.setRotationZ(ROTATION_Z);
		
		CatiaExporter catiaExporter = new CatiaExporter();
		catiaExporter.setGeometryFilesPath(GEOMETRY_FILES_EXPORT_DESTINATION);
		JsonObject jsonRoot = catiaExporter.transform(ct);
		
		JsonArray jsonParts = jsonRoot.getCollection(CatiaProperties.PARTS);		
		JsonObject jsonProductRoot = jsonRoot.getMap(CatiaProperties.PRODUCTS);
		
		assertTrue("There should be no part created", jsonParts.isEmpty());
		
		assertEquals(0, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_POS_X), EPSILON);
		assertEquals(0, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_POS_Y), EPSILON);
		assertEquals(0, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_POS_Z), EPSILON);
		
		assertEquals(0, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_ROT_X), EPSILON);
		assertEquals(0, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_ROT_Y), EPSILON);
		assertEquals(0, jsonProductRoot.getDouble(CatiaProperties.PRODUCT_ROT_Z), EPSILON);
		assertEquals(Visualisation.SHAPE_NONE_NAME, jsonProductRoot.getString(CatiaProperties.PRODUCT_SHAPE));

		JsonArray children = jsonProductRoot.getCollection(CatiaProperties.PRODUCT_CHILDREN);
		assertEquals(1, children.size());
		JsonObject subProduct = children.getMap(0);
		
		assertEquals(POSITION_X, subProduct.getDouble(CatiaProperties.PRODUCT_POS_X), EPSILON);
		assertEquals(POSITION_Y, subProduct.getDouble(CatiaProperties.PRODUCT_POS_Y), EPSILON);
		assertEquals(POSITION_Z, subProduct.getDouble(CatiaProperties.PRODUCT_POS_Z), EPSILON);
		
		assertEquals(ROTATION_X, subProduct.getDouble(CatiaProperties.PRODUCT_ROT_X), EPSILON);
		assertEquals(ROTATION_Y, subProduct.getDouble(CatiaProperties.PRODUCT_ROT_Y), EPSILON);
		assertEquals(ROTATION_Z, subProduct.getDouble(CatiaProperties.PRODUCT_ROT_Z), EPSILON);
		assertEquals(Visualisation.SHAPE_GEOMETRY_NAME, subProduct.getString(CatiaProperties.PRODUCT_SHAPE));
		assertEquals(EXPECTED_GEOMETRY_FILE_PATH, subProduct.getString(CatiaProperties.PRODUCT_STL_PATH));
	}
}
