/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class BeanPropertyTypeTest {
	
	@Test
	public void testFromString() {
		BeanPropertyType testType = BeanPropertyType.fromString(BeanPropertyType.BOOLEAN.getType());
		assertEquals("Returned the right type", BeanPropertyType.BOOLEAN, testType);
		
		assertThrows("Empty string throws exception", IllegalArgumentException.class, () -> {
			BeanPropertyType.fromString("");
		});
		
		assertThrows("Null string throws exception", IllegalArgumentException.class, () -> {
			BeanPropertyType.fromString(null);
		});
	}

}
