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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.extension.mechanical.catia.CatiaProperties;

/**
 * A util class for accessing the CATIA JSON files
 *
 */
public class CatiaHelper {
	
	/**
	 * Restrict constructor to prevent instantiation
	 */
	private CatiaHelper() { };
	
	/**
	 * Iterate through the JSON file and search for all children of the element,
	 * the object itself is not contained
	 * 
	 * @param rootObject
	 *            the root element
	 * @return a list of all elements within the current JSON Object
	 */
	public static List<JsonObject> getListOfAllJSONElements(JsonObject rootObject) {
		List<JsonObject> jsonObjects = new ArrayList<>();

		jsonObjects.addAll(getListOfAllJSONParts(rootObject));
		jsonObjects.addAll(getListOfAllJSONProducts(rootObject));
		
		return jsonObjects;
	}
	
	/**
	 * Method that returns all parts within a JSON object
	 * 
	 * @param rootObject the root object
	 * @return a list containing all parts within the JSON object
	 */
	public static List<JsonObject> getListOfAllJSONParts(JsonObject rootObject) {
		if (rootObject != null) {
			JsonArray partArray = rootObject.getCollection(CatiaProperties.PARTS);
			if (partArray != null) {
				return partArray.stream().map(object -> (JsonObject) object)
						.collect(Collectors.toList());
			} 
		}
		return new ArrayList<JsonObject>();
	}
	
	
	/**
	 * Method that returns all products within a JSON object
	 * 
	 * @param rootObject the root object
	 * @return a list containing all products within the JSON object
	 */
	public static List<JsonObject> getListOfAllJSONProducts(JsonObject rootObject) {
		List<JsonObject> jsonObjects = new ArrayList<>();
		
		JsonObject rootProduct = rootObject.getMap(CatiaProperties.PRODUCTS);
		if (rootProduct != null) {
			jsonObjects.add(rootProduct);
			JsonArray productArray = rootProduct.getCollection(CatiaProperties.PRODUCT_CHILDREN);
			
			if (productArray != null) {
				for (int i = 0; i < productArray.size(); i++) {
					JsonObject childProduct = productArray.getMap(i);
					jsonObjects.add(childProduct);
					jsonObjects.addAll(getListOfAllJSONProducts(childProduct));
				}
			}
		}
		
		return jsonObjects;
	}
	

}
