/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.resources;

import java.io.IOException;
import java.util.Collections;

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
	
	protected static ConceptResourceLoader instance;
	
	/**
	 * Method to load a concept from an XMI file
	 * @param conceptXmiFilePath path to the concept.xmi, e.g. "de.dlr.sc.virsat.model.extension.tests/concept/concept.xmi"
	 * @return loaded concept
	 */
	public Concept loadConceptFromPlugin(String conceptXmiFilePath) {

 		URI conceptResourceUri = getUriFromPath(conceptXmiFilePath);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		//Check if resource can be resolved from workspace, otherwise use plugin reference resource
		Resource resource = resourceSet.createResource(conceptResourceUri);
		try {
			resource.load(Collections.EMPTY_MAP);
		} catch (IOException e) {
			URI pluginURI = URI.createPlatformPluginURI(conceptXmiFilePath, true);
			resource = resourceSet.getResource(pluginURI, true);
		}
		Concept concept = (Concept) resource.getContents().get(0);
		return concept;
	}
	
	/**
	 * Get a platform URI from a path as string
	 * 
	 * @param path the path which should be use to create the URI from; must be of the form: /project-name/path
	 * @return the URI
	 */
	protected URI getUriFromPath(String path) {
		return URI.createPlatformResourceURI(path, true);
	}
	
	/**
	 * Method to load a concept from its XMI file by using the concept name
	 * @param conceptName the concept name
	 * @return the loaded concept
	 */
	public Concept loadConceptByName(String conceptName) {
		return loadConceptFromPlugin(conceptName + PROJECT_CONCEPT_LOCATION_PATH);
	}
	
	/**
	 * Method to get a cocnept's DMF ecore model URI by using the concept name
	 * @param conceptName the concept name
	 * @return the loaded DMF ecore model
	 */
	public URI getConceptDMFResourceUri(String conceptName) {
		Concept loadedConcept = loadConceptFromPlugin(conceptName + PROJECT_CONCEPT_LOCATION_PATH);
		if (loadedConcept != null) {
			return loadedConcept.eResource().getURI().trimFileExtension().appendFileExtension(MODEL_TYPE_DMF_EXTENSION);
		}
		return null;
	}
	
	/**
	 * Hand over instance of resource loader
	 * @return
	 */
	public static ConceptResourceLoader getInstance() {
		if (instance == null) {
			instance = new ConceptResourceLoader();
		}
		return instance;
	}
	
	/**
	 * Method to inject custom resource loader for unit testing
	 * @param injectedInstance the test instance
	 */
	public static void injectInstance(ConceptResourceLoader injectedInstance) {
		instance = injectedInstance;
	}
	
	/**
	 * Singlton constructor - create by getInstnace
	 */
	protected ConceptResourceLoader() {
		Resource.Factory.Registry factoryRegistry = Resource.Factory.Registry.INSTANCE;
		factoryRegistry.getExtensionToFactoryMap().put(MODEL_TYPE_XMI_EXTENSION, new XMIResourceFactoryImpl());
	}

}
