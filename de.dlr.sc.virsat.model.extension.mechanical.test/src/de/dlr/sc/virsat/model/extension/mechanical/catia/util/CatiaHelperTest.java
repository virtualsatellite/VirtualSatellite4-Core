/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.catia.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.extension.mechanical.catia.CatiaProperties;

/**
 * Test class for the CATIA helper class
 *
 */
public class CatiaHelperTest {

	public static final String ELEMENT_1_UUID = UUID.randomUUID().toString();
	public static final String ELEMENT_2_UUID = UUID.randomUUID().toString();
	public static final String ELEMENT_3_UUID = UUID.randomUUID().toString();

	public static final int NUMBER_OBJECTS = 4; // Three elements with UUID and the root product element

	public static final int INDEX_ELEMENT_1 = 0;
	public static final int INDEX_ELEMENT_2 = 2;
	public static final int INDEX_ELEMENT_3 = 3;

	@Test
	public void testGetListOfAllJSONElements() {

		JsonObject rootObject = createJsonObjectWithProductAndConfiguration();

		List<JsonObject> objects = CatiaHelper.getListOfAllJSONElements(rootObject);

		assertEquals("Number of found objects does not match expected", NUMBER_OBJECTS, objects.size());

		assertEquals("Number of found objects does not match expected", ELEMENT_1_UUID,
				objects.get(INDEX_ELEMENT_1).get(CatiaProperties.UUID.getKey()));
		assertEquals("Number of found objects does not match expected", ELEMENT_2_UUID,
				objects.get(INDEX_ELEMENT_2).get(CatiaProperties.UUID.getKey()));
		assertEquals("Number of found objects does not match expected", ELEMENT_3_UUID,
				objects.get(INDEX_ELEMENT_3).get(CatiaProperties.UUID.getKey()));

		assertNotNull("Elements in the list should not be null", objects.get(0));

	}

	/**
	 * Create a simple JSON Object with Part and Products
	 * 
	 * @return an initial JSON object
	 */
	protected JsonObject createJsonObjectWithProductAndConfiguration() {

		JsonObject jsonObjectReactionWheelDefinition = new JsonObject();
		jsonObjectReactionWheelDefinition.put(CatiaProperties.UUID.getKey(), ELEMENT_1_UUID);
		JsonArray partArray = new JsonArray();
		partArray.add(jsonObjectReactionWheelDefinition);

		JsonObject jsonObjectReactionWheel1Configuration = new JsonObject();
		jsonObjectReactionWheel1Configuration.put(CatiaProperties.UUID.getKey(), ELEMENT_2_UUID);
		JsonObject jsonObjectReactionWheel2Configuration = new JsonObject();
		jsonObjectReactionWheel2Configuration.put(CatiaProperties.UUID.getKey(), ELEMENT_3_UUID);
		JsonArray productArray = new JsonArray();
		productArray.add(jsonObjectReactionWheel1Configuration);
		productArray.add(jsonObjectReactionWheel2Configuration);

		JsonObject rootProduct = new JsonObject();
		rootProduct.put(CatiaProperties.PRODUCT_CHILDREN.getKey(), productArray);

		JsonObject rootObject = new JsonObject();
		rootObject.put(CatiaProperties.PARTS.getKey(), partArray);
		rootObject.put(CatiaProperties.PRODUCTS.getKey(), rootProduct);

		return rootObject;
	}

}
