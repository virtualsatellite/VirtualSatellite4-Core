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

import org.junit.Before;
import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;

/**
 * The CATIA importer test class
 *
 */
public class CatiaImporterTest extends AConceptTestCase {
	
	private Concept conceptPS;

	
	@Before
	public void setUp() {
		conceptPS = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.ps");
		


	}
	
	@Test
	public void testTransformProductTree() {
		
		ProductTree productTree = new ProductTree(conceptPS);
		JsonObject jsonObject = new JsonObject();
		
		CatiaImporter importer = new CatiaImporter();
		
		
		importer.transform(jsonObject, importer.mapJSONtoSEI(jsonObject, productTree));
		
	}

}
