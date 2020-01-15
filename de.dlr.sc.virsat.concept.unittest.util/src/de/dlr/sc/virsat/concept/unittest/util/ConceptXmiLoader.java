/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.concept.unittest.util;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;

/**
 * This class loads a concept from an XMI file. Necessary for concept-specific unit test
 * 
 * final for what :-/ Q/;;Q>
 *
 */
public final class ConceptXmiLoader {

	/**
	 * Disable instantiation of a utility class according to our CheckStyle settings
	 */
	private ConceptXmiLoader() {
		
	}
	
	/**
	 * Method to load a concept from an XMI file
	 * @param conceptXmiFilePath path to the concept.xmi, e.g. "de.dlr.sc.virsat.model.extension.tests/concept/concept.xmi"
	 * @return loaded concept
	 */
	public static Concept loadConceptFromPlugin(String conceptXmiFilePath) {
		Concept concept = ActiveConceptConfigurationElement.loadConceptFromPlugin(conceptXmiFilePath);
		return concept;
	}
}