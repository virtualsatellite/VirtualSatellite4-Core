/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.migrator;

// *****************************************************************
// * Import Statements
// *****************************************************************


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.ecore.util.EcoreUtil;


// *****************************************************************
// * Class Declaration
// *****************************************************************

import org.junit.Before;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Extension for Requirement Specification
 * 
 */	
public abstract class AMigrator0v3Test {
	protected Concept conceptMigrateTo;
	protected Concept conceptMigrateFromRepository;
	protected Concept conceptMigrateFrom;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPathMigrateTo = "de.dlr.sc.virsat.model.extension.requirements/concept/concept_v0_3.xmi";
		String conceptXmiPluginPathMigrateFrom = "de.dlr.sc.virsat.model.extension.requirements/concept/concept_v0_2.xmi";
		
		conceptMigrateTo = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPathMigrateTo);
		conceptMigrateFromRepository = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPathMigrateFrom);
		conceptMigrateFrom = EcoreUtil.copy(conceptMigrateFromRepository);
	}
}
