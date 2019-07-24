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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * This class tests the Catia exporter.
 *
 */

public class CatiaExporterTest extends AConceptTestCase {

	private Concept conceptPS;
	private Concept conceptVis;
	
	@Before
	public void setUp() {
		conceptPS = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.ps");
		conceptVis = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.visualisation");
	}
	
	@Test
	public void testTransformConfigurationTree() {
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
}
