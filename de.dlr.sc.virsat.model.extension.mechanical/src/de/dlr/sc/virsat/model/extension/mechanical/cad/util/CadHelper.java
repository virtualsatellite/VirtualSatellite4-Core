/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.cad.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.extension.mechanical.cad.CadProperties;

/**
 * A util class for accessing the CAD JSON files
 *
 */
public class CadHelper {
	
	/**
	 * Restrict constructor to prevent instantiation
	 */
	private CadHelper() { };
	
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
			JsonArray partArray = rootObject.getCollection(CadProperties.PARTS);
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
		JsonArray productArray;
		
		if (rootObject == null) {
			return jsonObjects;
		}
		
		JsonObject rootProduct = rootObject.getMap(CadProperties.PRODUCTS);
		if (rootProduct != null) {
			jsonObjects.add(rootProduct);
			productArray = rootProduct.getCollection(CadProperties.PRODUCT_CHILDREN);
		} else {	
			productArray = rootObject.getCollection(CadProperties.PRODUCT_CHILDREN);
		}
		
		if (productArray != null) {
			for (int i = 0; i < productArray.size(); i++) {
				JsonObject childProduct = productArray.getMap(i);
				if (childProduct != null) {
					jsonObjects.add(childProduct);
					jsonObjects.addAll(getListOfAllJSONProducts(childProduct));
				}	
			}
		}
		
		return jsonObjects;
	}
	

}
