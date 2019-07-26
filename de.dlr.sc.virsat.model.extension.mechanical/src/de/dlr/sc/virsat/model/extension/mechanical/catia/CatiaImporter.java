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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.mechanical.catia.util.CatiaHelper;

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
	 * @param mapJSONtoSEI
	 *            the mapping of JSON elements to the existing trees
	 */
	public void transform(JsonObject jsonObject, Map<String, StructuralElementInstance> mapJSONtoSEI) {

	}

	/**
	 * Map the imported JSON elements to existing model elements in the Virtual
	 * Satellite model and return the ones for which no mapping can be found
	 * 
	 * @param jsonContent
	 *            the JSON content to be imported
	 * @param existingTree
	 *            a tree element the imported JSON should be mapped to
	 * @return a map of existing tree elements to their UUID in the JSON file
	 */
	public Map<String, StructuralElementInstance> mapJSONtoSEI(JsonObject jsonContent,
			IBeanStructuralElementInstance existingTree) {

		Map<String, StructuralElementInstance> mapExisitingElementtoUUID = new HashMap<String, StructuralElementInstance>();
		Map<String, IBeanStructuralElementInstance> mapSEIsToUuid = createMapOfTreeSEIsToUuid(existingTree);

		for (JsonObject object : CatiaHelper.getListOfAllJSONElements(jsonContent)) {
			String uuid = object.getString(CatiaProperties.UUID);
			IBeanStructuralElementInstance mappedElement = mapSEIsToUuid.get(uuid);
			if (mappedElement != null) {
				mapExisitingElementtoUUID.put(uuid, mappedElement.getStructuralElementInstance());
			}
			
		}

		return mapExisitingElementtoUUID;

	}

	/**
	 * Method to get all unmapped JSON elements that do not have a representation in
	 * the existing trees
	 * 
	 * @param jsonRoot
	 *            the JSON root element to look for
	 * @param mapJSONtoSEI
	 *            the Map of SEIs to JSONObjects created by method
	 *            {@link #mapJSONtoSEI(JsonObject, IBeanStructuralElementInstance)}
	 * @return a list of unmapped elements
	 */
	public List<JsonObject> getUnmappedJSONObjects(JsonObject jsonRoot,
			Map<String, StructuralElementInstance> mapJSONtoSEI) {

		List<JsonObject> unmappedElements = new ArrayList<>();
		
		for (JsonObject object : CatiaHelper.getListOfAllJSONElements(jsonRoot)) {
			String uuid = object.getString(CatiaProperties.UUID);
			if (mapJSONtoSEI.get(uuid) == null) {
				unmappedElements.add(object);
			}
		}

		return unmappedElements;

	}

	/**
	 * Create a map of all structural elements in a tree to their UUID
	 * 
	 * @param existingTree
	 *            the existing tree element in the Virtual Satellite model
	 * @return a map that maps all SEIs to their UUID
	 */
	private Map<String, IBeanStructuralElementInstance> createMapOfTreeSEIsToUuid(
			IBeanStructuralElementInstance existingTree) {

		Map<String, IBeanStructuralElementInstance> mapSEIsToUuid = new HashMap<String, IBeanStructuralElementInstance>();

		for (IBeanStructuralElementInstance sei : existingTree.getDeepChildren(IBeanStructuralElementInstance.class)) {
			mapSEIsToUuid.put(sei.getUuid(), sei);
			
			//Also add relevant elements in other trees (the super elements) to the map
			for (IBeanStructuralElementInstance superSei : sei.getAllSuperSeis(IBeanStructuralElementInstance.class)) {
				mapSEIsToUuid.put(superSei.getUuid(), superSei);
			}
		}

		return mapSEIsToUuid;

	}

}
