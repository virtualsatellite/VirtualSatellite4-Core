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
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.extension.mechanical.catia.CatiaProperties;

/**
 * Test class for the CATIA helper class
 *
 */
public class CatiaHelperTest {

	public static final String ELEMENT_PART_UUID = UUID.randomUUID().toString();
	public static final String ELEMENT_PRODUCT_0_UUID = UUID.randomUUID().toString();
	public static final String ELEMENT_PRODUCT_1_UUID = UUID.randomUUID().toString();
	public static final String ELEMENT_ROOT_PRODUCT_UUID = UUID.randomUUID().toString();

	public static final int NUMBER_OBJECTS = 4; 
	public static final int NUMBER_PARTS = 1; 
	public static final int NUMBER_PRODUCTS = 3; 


	@Test
	public void testGetListOfAllJSONElements() {

		JsonObject rootObject = createJsonObjectWithProductAndConfiguration();

		List<JsonObject> objects = CatiaHelper.getListOfAllJSONElements(rootObject);
		List<String> foundUUIDs = objects.stream().map(
				object -> (String) object.get(CatiaProperties.UUID.getKey()))
				.collect(Collectors.toList());

		
		assertEquals("Number of found objects does not match expected", NUMBER_OBJECTS, objects.size());

		assertTrue("Element 1 not found", foundUUIDs.contains(ELEMENT_PART_UUID));
		assertTrue("Element 2 not found", foundUUIDs.contains(ELEMENT_PRODUCT_0_UUID));
		assertTrue("Element 3 not found", foundUUIDs.contains(ELEMENT_PRODUCT_1_UUID));
		assertTrue("Element 4 not found", foundUUIDs.contains(ELEMENT_ROOT_PRODUCT_UUID));

	}
	
	@Test
	public void testGetListOfAllJSONParts() {

		JsonObject rootObject = createJsonObjectWithProductAndConfiguration();

		List<JsonObject> objects = CatiaHelper.getListOfAllJSONParts(rootObject);
		List<String> foundUUIDs = objects.stream().map(
				object -> (String) object.get(CatiaProperties.UUID.getKey()))
				.collect(Collectors.toList());

		
		assertEquals("Number of found objects does not match expected", NUMBER_PARTS, objects.size());

		assertTrue("Element 1 not found", foundUUIDs.contains(ELEMENT_PART_UUID));
		
		
		assertNotNull("Return value should not be null", CatiaHelper.getListOfAllJSONParts(null));
		assertTrue("List should be emplty", CatiaHelper.getListOfAllJSONParts(null).isEmpty());
		
		assertNotNull("Return value should not be null", CatiaHelper.getListOfAllJSONParts(new JsonObject()));
		assertTrue("List should be emplty", CatiaHelper.getListOfAllJSONParts(new JsonObject()).isEmpty());

	}
	
	@Test
	public void testGetListOfAllJSONProducts() {

		JsonObject rootObject = createJsonObjectWithProductAndConfiguration();

		List<JsonObject> objects = CatiaHelper.getListOfAllJSONProducts(rootObject);
		List<String> foundUUIDs = objects.stream().map(
				object -> (String) object.get(CatiaProperties.UUID.getKey()))
				.collect(Collectors.toList());

		
		assertEquals("Number of found objects does not match expected", NUMBER_PRODUCTS, objects.size());

		assertTrue("Product 1 not found", foundUUIDs.contains(ELEMENT_PRODUCT_0_UUID));
		assertTrue("product 2 not found", foundUUIDs.contains(ELEMENT_PRODUCT_1_UUID));
		assertTrue("Root product not found", foundUUIDs.contains(ELEMENT_ROOT_PRODUCT_UUID));
		
		assertNotNull("Return value should not be null", CatiaHelper.getListOfAllJSONProducts(null));
		assertTrue("List should be emplty", CatiaHelper.getListOfAllJSONProducts(null).isEmpty());
		
		assertNotNull("Return value should not be null", CatiaHelper.getListOfAllJSONProducts(new JsonObject()));
		assertTrue("List should be emplty", CatiaHelper.getListOfAllJSONProducts(new JsonObject()).isEmpty());

	}
	
	@Test
	public void testGetListOfAllJSONProductsFromRootProduct() {

		JsonObject rootObject = createJsonObjectWithProductAndConfiguration(false);

		List<JsonObject> objects = CatiaHelper.getListOfAllJSONProducts(rootObject);
		List<String> foundUUIDs = objects.stream().map(
				object -> (String) object.get(CatiaProperties.UUID.getKey()))
				.collect(Collectors.toList());

		
		assertEquals("Number of found objects does not match expected", NUMBER_PRODUCTS, objects.size());

		assertTrue("Product 1 not found", foundUUIDs.contains(ELEMENT_PRODUCT_0_UUID));
		assertTrue("product 2 not found", foundUUIDs.contains(ELEMENT_PRODUCT_1_UUID));
		assertTrue("Root product not found", foundUUIDs.contains(ELEMENT_ROOT_PRODUCT_UUID));
		
		assertNotNull("Return value should not be null", CatiaHelper.getListOfAllJSONProducts(null));
		assertTrue("List should be emplty", CatiaHelper.getListOfAllJSONProducts(null).isEmpty());
		
		assertNotNull("Return value should not be null", CatiaHelper.getListOfAllJSONProducts(new JsonObject()));
		assertTrue("List should be emplty", CatiaHelper.getListOfAllJSONProducts(new JsonObject()).isEmpty());

	}

	
	/**
	 * Create a simple JSON Object with Part and Products
	 * 
	 * @return an initial JSON object
	 */
	protected JsonObject createJsonObjectWithProductAndConfiguration() {
		return createJsonObjectWithProductAndConfiguration(true);
	}
	
	/**
	 * Create a simple JSON Object with Part and Products
	 * 
	 * @param isRootProduct specifies if the root object should be a root product or not
	 * @return an initial JSON object
	 */
	protected JsonObject createJsonObjectWithProductAndConfiguration(boolean isRootProduct) {

		JsonObject jsonObjectReactionWheelDefinition = new JsonObject();
		jsonObjectReactionWheelDefinition.put(CatiaProperties.UUID.getKey(), ELEMENT_PART_UUID);
		JsonArray partArray = new JsonArray();
		partArray.add(jsonObjectReactionWheelDefinition);

		JsonObject jsonObjectReactionWheel1Configuration = new JsonObject();
		jsonObjectReactionWheel1Configuration.put(CatiaProperties.UUID.getKey(), ELEMENT_PRODUCT_0_UUID);
		JsonObject jsonObjectReactionWheel2Configuration = new JsonObject();
		jsonObjectReactionWheel2Configuration.put(CatiaProperties.UUID.getKey(), ELEMENT_PRODUCT_1_UUID);
		JsonArray productArray = new JsonArray();
		productArray.add(jsonObjectReactionWheel1Configuration);
		productArray.add(jsonObjectReactionWheel2Configuration);

		JsonObject rootProduct = new JsonObject();
		rootProduct.put(CatiaProperties.UUID.getKey(), ELEMENT_ROOT_PRODUCT_UUID);
		
		rootProduct.put(CatiaProperties.PRODUCT_CHILDREN.getKey(), productArray);

		JsonObject rootObject = new JsonObject();
		rootObject.put(CatiaProperties.PARTS.getKey(), partArray);
		if (isRootProduct) {
			rootObject.put(CatiaProperties.PRODUCTS.getKey(), rootProduct);
		} else {
			JsonArray children = new JsonArray();
			children.add(rootProduct);
			rootObject.put(CatiaProperties.PRODUCT_CHILDREN.getKey(), children);
		}
		

		return rootObject;
	}

}
