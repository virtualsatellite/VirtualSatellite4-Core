/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.builder.resources;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 *  Class for loading concepts from the ConceptIDE workspace. Here,
 *  we do not yet have the extension specification used in the 
 *  ActiveConceptConfigruation element and we use PlatfromResourceURIs 
 *  instead of PlattformPluginURIs
 */
public class ConceptResourceLoader {
	
	private static final String MODEL_TYPE_XMI_EXTENSION = "xmi";
	private static final String MODEL_TYPE_DMF_EXTENSION = "ecore";
	private static final String PROJECT_CONCEPT_LOCATION_PATH = "/concept/concept.xmi";
	
	private ConceptResourceLoader() {
	}
	
	/**
	 * Method to load a concept from an XMI file
	 * @param conceptXmiFilePath path to the concept.xmi, e.g. "de.dlr.sc.virsat.model.extension.tests/concept/concept.xmi"
	 * @return loaded concept
	 */
	public static Concept loadConceptFromPlugin(String conceptXmiFilePath) {
		Resource.Factory.Registry factoryRegistry = Resource.Factory.Registry.INSTANCE;
		factoryRegistry.getExtensionToFactoryMap().put(MODEL_TYPE_XMI_EXTENSION, new XMIResourceFactoryImpl());

		URI conceptResourceUri = URI.createPlatformResourceURI(conceptXmiFilePath, true);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		Resource resource = resourceSet.getResource(conceptResourceUri, true);
		Concept concept = (Concept) resource.getContents().get(0);
		return concept;
	}
	
	/**
	 * Method to load a concept from its XMI file by using the concept name
	 * @param conceptName the concept name
	 * @return the loaded concept
	 */
	public static Concept loadConceptViaConceptName(String conceptName) {
		return loadConceptFromPlugin(conceptName + PROJECT_CONCEPT_LOCATION_PATH);
	}
	
	/**
	 * Method to get a cocnept's DMF ecore model URI by using the concept name
	 * @param conceptName the concept name
	 * @return the loaded DMF ecore model
	 */
	public static URI getConceptDMFResourceUri(String conceptName) {
		Concept loadedConcept = loadConceptFromPlugin(conceptName + PROJECT_CONCEPT_LOCATION_PATH);
		if (loadedConcept != null) {
			return loadedConcept.eResource().getURI().trimFileExtension().appendFileExtension(MODEL_TYPE_DMF_EXTENSION);
		}
		return null;
	}

}
