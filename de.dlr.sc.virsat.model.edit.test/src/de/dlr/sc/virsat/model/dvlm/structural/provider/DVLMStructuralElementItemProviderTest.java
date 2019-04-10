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
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test Case for the overridden getText Method of the DVLMStructuralElementInstanceItemProvider
 * @author lobe_el
 *
 */
public class DVLMStructuralElementItemProviderTest {

	ComposedAdapterFactory adapterFactory;
	StructuralElement se;
	
	@Before
	public void setUp() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		se = StructuralFactory.eINSTANCE.createStructuralElement();
	}
	
	@Test
	public void testGetText() {
		DVLMStructuralElementItemProvider dseiip = new DVLMStructuralElementItemProvider(adapterFactory);
		
		final String SE_NAME = "ThisIsA TestStructuralElement";
		final String SE_ABBR = "TIATSE";
		final String SE_ABBR_OTHER = "TSE";
		final String SE_DESCRIPTION = dseiip.getString("_UI_StructuralElement_type");
		final String IQUALIFIEDNAME_NAME_EDEFAULT = "de.dlr.sc.model.dvlm.noid";
		
		String expectedOutput = SE_DESCRIPTION + " " + IQUALIFIEDNAME_NAME_EDEFAULT;
		String output = dseiip.getText(se);
		assertEquals("The output is like expected", expectedOutput, output);

		se.setName(SE_NAME);
		expectedOutput = SE_DESCRIPTION + " " + SE_NAME + " (" + SE_ABBR + ")";
		output = dseiip.getText(se);
		assertEquals("The output is like expected", expectedOutput, output);
		
		se.setShortName(SE_ABBR_OTHER);
		expectedOutput = SE_DESCRIPTION + " " + SE_NAME + " (" + SE_ABBR_OTHER + ")";
		output = dseiip.getText(se);
		assertEquals("The output is like expected", expectedOutput, output);
		
	}
	
}
