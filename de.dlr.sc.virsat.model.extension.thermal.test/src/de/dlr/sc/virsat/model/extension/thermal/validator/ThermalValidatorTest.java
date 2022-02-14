/**
 * This file is part of the VirSat project.
 *
 * Copyright (c) 2008-2016
 * German Aerospace Center (DLR), Simulation and Software Technology, Germany
 * All rights reserved
 *
 */
package de.dlr.sc.virsat.model.extension.thermal.validator;

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
 * Concept for thermalents, Phd, Masters, Bachelors and research projects
 * 
 */
public class ThermalValidatorTest extends AThermalValidatorTest {

	@Test
	public void testThermalValidator() {
		ThermalValidator validator = new ThermalValidator();
		assertTrue(validator.validate(testSei));
	}
}
