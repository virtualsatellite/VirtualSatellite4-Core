/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Very simple test class for the validator.
 * Currently regarded as entry point for writing
 * more sophisticated test cases.
 * @author fisc_ph
 *
 */
public class StructuralElementInstanceValidatorTest {

	@Test
	public void testValidate() {
		StructuralElementInstanceValidator validator = new StructuralElementInstanceValidator();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		boolean result = validator.validate(sei);
		
		assertTrue("The standard implementation of the Validator does not complain", result);
	}
}
