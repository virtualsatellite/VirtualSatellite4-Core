/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * Use this class for having the correct loading order for the test concept.
 * First it loads the maturity concept, second it loads the test concept referencing it.
 *
 */
public abstract class ATestConceptTestCase extends AConceptProjectTestCase {

	public static final String TEST_CONCEPT_ID = "de.dlr.sc.virsat.model.extension.tests";
	public static final String MATURITY_CONCEPT_ID = "de.dlr.sc.virsat.model.extension.maturity";
	
	protected Concept testConcept;
	protected Concept maturityConcept;
	protected Concept coreConcept;
		
	public void loadTestConcept() { 
		// Load the concept to create the test object
		coreConcept = loadConceptAndInstallToRepository(CONCEPT_ID_CORE);
		maturityConcept = loadConceptAndInstallToRepository(MATURITY_CONCEPT_ID);
		testConcept = loadConceptAndInstallToRepository(TEST_CONCEPT_ID);
	}
}
