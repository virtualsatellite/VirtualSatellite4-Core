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

import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * This class creates the JSON representation of a product structure tree.
 *
 */

public class CatiaExporter {
	
	/**
	 * Main method that creates the JSON representation of a configuration tree
	 * @param configurationTree the configuration tree
	 * @return the json root object
	 */
	public JSONObject transform(ConfigurationTree configurationTree) {
		return null;
	}
	
	/**
	 * Creates the JSON representation for a collection of element definitions
	 * @param elementDefinitions the element definitions
	 * @return the json representation
	 */
	@SuppressWarnings("unchecked")
	public JSONArray transformParts(Collection<ElementDefinition> elementDefinitions) {
		JSONArray jsonParts = new JSONArray();
		
		for (ElementDefinition ed : elementDefinitions) {
			Visualisation vis = ed.getFirst(Visualisation.class);
			
			if (vis != null) {
				JSONObject jsonPart = new JSONObject();
				jsonParts.add(jsonPart);
			
				jsonPart.put(CatiaProperties.NAME, ed.getName());
				jsonPart.put(CatiaProperties.UUID, ed.getUuid());
			}
		}
		
		return jsonParts;
	}
}
