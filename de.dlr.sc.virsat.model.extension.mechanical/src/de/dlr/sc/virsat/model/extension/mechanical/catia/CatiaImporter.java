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

import java.util.ArrayList;
import java.util.List;

import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;

/**
 * This class imports a JSON representation of a product tree
 *
 */
public class CatiaImporter {

	/**
	 * Main method that creates the JSON representation of a configuration tree
	 * 
	 * @param jsonObject
	 *            the Json Object
	 * @param productTree
	 *            the product tree
	 */
	public void transform(JsonObject jsonObject, ProductTree productTree) {

	}

	/**
	 * Map the imported JSON elements to existing model elements in the Virtual
	 * Satellite model and return the ones for which no mapping can be found
	 * 
	 * @param jsonContent the JSON content to be imported
	 * @param configurationTree the configuration tree the imported JSON should be mapped to
	 * @return a collection of elements that could not be mapped
	 */
	public List<JsonObject> mapJSONtoSEI(JsonObject jsonContent, ConfigurationTree configurationTree) {
		
		List<JsonObject> unmappedElements = new ArrayList<>();
		
		return unmappedElements;

	}

}
