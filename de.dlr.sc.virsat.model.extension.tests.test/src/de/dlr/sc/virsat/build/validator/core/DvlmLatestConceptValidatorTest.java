/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;

/**
 * TestCase for latest concept validator
 * 
 * @author bell_er
 *
 */
public class DvlmLatestConceptValidatorTest extends ATestConceptTestCase {
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addResourceSetAndRepository();
		loadTestConcept();
	}
	
	@Test
	public void testValidateLatestConcept() throws CoreException, IOException {
		DvlmLatestConceptValidator validator = new DvlmLatestConceptValidator();

		assertTrue("concept is up to date", validator.validate(repository));

		// we decrease the version of the concept
		for (Concept concept : repository.getActiveConcepts()) {
			concept.setVersion("0.001");
		}

		assertFalse("concept is not up to date", validator.validate(repository));
	}
}
