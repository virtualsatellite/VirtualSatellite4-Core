/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model;


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * This class can be used to create a test case where the test concept needs to be loaded
 * @author fisc_ph
 *
 */
public abstract class AConceptTestCase extends de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase {

	/**
	 * Method to load the test concept 
	 * @return the test concept
	 */
	protected Concept loadConceptFromPlugin() {
	    return super.loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.tests");
	}
}