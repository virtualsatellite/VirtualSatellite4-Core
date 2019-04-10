/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.provider;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test Case for the overridden getText Method of the DVLMStructuralElementInstanceItemProvider
 * @author lobe_el
 *
 */
public class DVLMStructuralElementInstanceItemProviderTest {

	ComposedAdapterFactory adapterFactory;
	StructuralElement se;
	StructuralElementInstance sei;
	
	@Before
	public void setUp() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
	}
	
	@Test
	public void testGetText() {
		DVLMStructuralElementInstanceItemProvider dseiip = new DVLMStructuralElementInstanceItemProvider(adapterFactory);
		
		final String SE_NAME = "ThisIsA TestStructuralElement";
		final String SEI_NAME = "ThisIsA TestStructuralElementInstance";
		final String SE_ABBR = "TIATSE";
		final String SE_ABBR_OTHER = "TSE";
		
		se.setName(SE_NAME);
		String expectedOutput = SE_ABBR + ":";
		String output = dseiip.getText(sei);
		assertEquals("The output is like expected", expectedOutput, output);
		
		sei.setName(SEI_NAME);
		expectedOutput = SE_ABBR + ": " + SEI_NAME;
		output = dseiip.getText(sei);
		assertEquals("The output is like expected", expectedOutput, output);
		
		se.setShortName(SE_ABBR_OTHER);
		expectedOutput = SE_ABBR_OTHER + ": " + SEI_NAME;
		output = dseiip.getText(sei);
		assertEquals("The output is like expected", expectedOutput, output);
		
	}
	
}
