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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;

/**
 * The CATIA importer test class
 *
 */
public class CatiaImporterTest extends AConceptTestCase {
	
	private Concept conceptPS;
	private JSONObject jsonObject;
	private static final String INITIAL_JSON_CONTENT = "";
	
	@Before
	public void setUp() {
		conceptPS = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.ps");
		
		JSONParser parser = new JSONParser();
		try {
			jsonObject = (JSONObject) parser.parse(INITIAL_JSON_CONTENT);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testTransformProductTree() {
		
		ProductTree productTree = new ProductTree(conceptPS);
		
		CatiaImporter importer = new CatiaImporter();
		importer.transform(jsonObject, productTree);
		
		
		
	}

}
