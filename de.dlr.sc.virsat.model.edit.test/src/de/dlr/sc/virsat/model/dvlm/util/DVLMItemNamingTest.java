/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test Case for the Naming of the Items with the corresponding abbreviation
 * @author lobe_el
 *
 */
public class DVLMItemNamingTest {

	@Test
	public void testGetAbbreviation() {
		
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		String defaultOutput = "DEFAULT";
		
		se.setName("IDontKnowAGoodName");
		String outputSe1 = "IDKAGN";
		assertEquals("The Abbreviation should be correct", outputSe1, DVLMItemNaming.getAbbreviation(se, defaultOutput));	

		se.setName("This is A very strange Name");
		String outputSe2 = "TAN";
		assertEquals("The Abbreviation should be correct", outputSe2, DVLMItemNaming.getAbbreviation(se, defaultOutput));	

		se.setName("$/%/&(%)_X'/*~()=");
		String outputSe3 = "X";
		assertEquals("The Abbreviation should be correct", outputSe3, DVLMItemNaming.getAbbreviation(se, defaultOutput));
		
		se.setName("this has no abbreviation");
		assertEquals("The Default Value should be returned", defaultOutput, DVLMItemNaming.getAbbreviation(se, defaultOutput));	
		
		String shortName = "SE";
		se.setShortName(shortName);
		assertEquals("The Abbreviation should be the shortName", shortName, DVLMItemNaming.getAbbreviation(se, defaultOutput));

		se.setName("This Has An Abbreviation");
		assertEquals("The Abbreviation should be the shortName", shortName, DVLMItemNaming.getAbbreviation(se, defaultOutput));
	}
	
}
