/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// *****************************************************************
// * Import Statements
// *****************************************************************



// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class DefaultVerificationTest extends ADefaultVerificationTest {
	
	public static final String TYPE_NAME = "DType";
	
	@Test
	public void testSetVerificationType() {
		VerificationType type = new VerificationType(concept);
		type.setName(TYPE_NAME);
		DefaultVerification verification = new DefaultVerification(concept);
		
		verification.setVerificationType(type);
		
		assertEquals("Type name should be added to verification isntance", TYPE_NAME, verification.getName());
	}

}
