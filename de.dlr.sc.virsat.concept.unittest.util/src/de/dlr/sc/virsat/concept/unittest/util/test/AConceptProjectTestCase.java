/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.concept.unittest.util.test;


import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * This class can be used to create a test case where the test concept needs to be loaded
 * @author fisc_ph
 *
 */
public abstract class AConceptProjectTestCase extends AProjectTestCase {

	protected static final String CONCEPT_ID_CORE = "de.dlr.sc.virsat.model.ext.core";
	
	/**
	 * Method to load the test concept
	 * @param pluginName The name of the plugin from which to load the concept 
	 * @return the test concept
	 */
	protected Concept loadConceptFromPlugin(String pluginName) {
		String conceptXmiPluginPath = pluginName + "/concept/concept.xmi";
		Concept concept = ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
		return concept;
	}
	
	protected Concept loadConceptAndInstallToRepository(String conceptId) {
		return loadConceptAndInstallToRepository(conceptId, repository);
	}
	
	protected Concept loadConceptAndInstallToRepository(String conceptId, Repository repository) {
		Concept concept = loadConceptFromPlugin(conceptId);
		concept = ActiveConceptConfigurationElement.createCopyConceptToRepository(concept, repository);
		return concept;
	}
	
	/**
	 * Adds the language core concept to the repository.
	 * 
	 * Requires addEditingDomainAndRepository() from super class to be executed before
	 */
	protected void activateCoreConcept() {
		executeAsCommand(() -> loadConceptAndInstallToRepository(CONCEPT_ID_CORE));
	}
	
}