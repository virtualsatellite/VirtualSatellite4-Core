/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.test;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.dlr.sc.virsat.model.concept.resources.ConceptResourceLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * Mocks the concept resource loader by handing over test instances
 *
 */
public class MockupConceptResourceLoader extends ConceptResourceLoader {
	
	protected static final String CORE_CONCEPT_PATH = "de.dlr.sc.virsat.model.ext.core/concept/concept.xmi";
	
	protected Map<String, Concept> testConceptInstances = new HashMap<String, Concept>();
	
	public MockupConceptResourceLoader() {
		addTestConceptInstance(CORE_CONCEPT_PATH, loadCoreConceptTest());
	}
	
	@Override
	public Concept loadConceptFromPlugin(String conceptXmiFilePath) {
		if (testConceptInstances.containsKey(conceptXmiFilePath)) {
			return testConceptInstances.get(conceptXmiFilePath);
		}
		return super.loadConceptFromPlugin(conceptXmiFilePath);
	}

	public void addTestConceptInstance(String xmiPath, Concept concept) {
		testConceptInstances.put(xmiPath, concept);
	}
	
	protected Concept loadCoreConceptTest() {
		URI conceptResourceUri = URI.createPlatformPluginURI(CORE_CONCEPT_PATH, true);
		Resource resource = new ResourceSetImpl().getResource(conceptResourceUri, true);
		return (Concept) resource.getContents().get(0);
	}
}
