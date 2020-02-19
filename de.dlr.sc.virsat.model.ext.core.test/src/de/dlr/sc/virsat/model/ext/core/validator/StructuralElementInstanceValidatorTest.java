/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ext.core.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

 
public class StructuralElementInstanceValidatorTest {

	StructuralElementInstance testSei;

	@Before
	public void setup() {
		testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	}
	
	@Test
	public void testValidate() {
		assertTrue("Validator does not do anything yet", new StructuralElementInstanceValidator().validate(testSei));
	}
}
