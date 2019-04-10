/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.provider;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;

/**
 * Test Case for the overridden getText Method of the DVLMCategoryAssignmentItemProvider
 * @author lobe_el
 *
 */
public class DVLMCategoryItemProviderTest {
	
	ComposedAdapterFactory adapterFactory;
	Category category;
	
	@Before
	public void setUp() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		category = CategoriesFactory.eINSTANCE.createCategory();
	}
	
	@Test
	public void testGetText() {
		DVLMCategoryItemProvider dcaip = new DVLMCategoryItemProvider(adapterFactory);
		
		final String C_NAME = "ThisIsA TestCategory";
		final String C_ABBR = "TIATC";
		final String C_ABBR_OTHER = "TC";
		final String C_DESCRIPTION = dcaip.getString("_UI_Category_type");
		final String IQUALIFIEDNAME_NAME_EDEFAULT = "de.dlr.sc.model.dvlm.noid";
		
		String expectedOutput = C_DESCRIPTION + " " + IQUALIFIEDNAME_NAME_EDEFAULT;
		String output = dcaip.getText(category);
		assertEquals("The output is like expected", expectedOutput, output);
		
		category.setName(C_NAME);
		expectedOutput = C_DESCRIPTION + " " + C_NAME + " (" + C_ABBR + ")";
		output = dcaip.getText(category);
		assertEquals("The output is like expected", expectedOutput, output);
		
		category.setShortName(C_ABBR_OTHER);
		expectedOutput = C_DESCRIPTION + " " + C_NAME + " (" + C_ABBR_OTHER + ")";
		output = dcaip.getText(category);
		assertEquals("The output is like expected", expectedOutput, output);
	}
}
