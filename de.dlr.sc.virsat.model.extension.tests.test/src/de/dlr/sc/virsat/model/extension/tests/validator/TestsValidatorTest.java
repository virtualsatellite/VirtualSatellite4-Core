/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.validator;

// *****************************************************************
// * Import Statements
// *****************************************************************



// *****************************************************************
// * Class Declaration
// *****************************************************************

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * A pure test concept to check that everything is working as expected
 * 
 */
public class TestsValidatorTest extends ATestsValidatorTest {

	@Test
	public void testTestsValidator() {
		TestsValidator validator = new TestsValidator();
		assertTrue(validator.validate(testSei));
	}
}
