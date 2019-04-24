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
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * This class includes Test Cases for dealing with Names of
 * copied objects.
 * 
 * @author fisc_ph
 *
 */
public class DVLMCopiedNameHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExtractUncopiedName() {
		DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
		
		assertEquals("Extracted correct Name", "TestName1", dcnh.extractUncopiedName("TestName1"));
		assertEquals("Extracted correct Name", "TestName1_", dcnh.extractUncopiedName("TestName1_"));
		assertEquals("Extracted correct Name", "TestName1_a", dcnh.extractUncopiedName("TestName1_a"));
		assertEquals("Extracted correct Name", "TestName1", dcnh.extractUncopiedName("TestName1_1"));
		assertEquals("Extracted correct Name", "TestName1", dcnh.extractUncopiedName("TestName1_456"));
	}

	
	@Test
	public void testExtractCurrentCopyNumber() {
		DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
		
		assertEquals("Extracted correct Number", "1", dcnh.extractCurrentCopyNumber("TestName13"));
		assertEquals("Extracted correct Number", "1", dcnh.extractCurrentCopyNumber("TestName13_"));
		assertEquals("Extracted correct Number", "1", dcnh.extractCurrentCopyNumber("TestName13_a"));
		assertEquals("Extracted correct Number", "15", dcnh.extractCurrentCopyNumber("TestName13_15"));
		assertEquals("Extracted correct Number", "1", dcnh.extractCurrentCopyNumber("TestName13_a15"));
		assertEquals("Extracted correct Number", "1", dcnh.extractCurrentCopyNumber("TestName13_17a"));
		assertEquals("Extracted correct Number", "1", dcnh.extractCurrentCopyNumber("TestName13_78.4"));
	}
	
	@Test
	public void testExtractCurrentCopyValue() {
		DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
		
		//CHECKSTYLE:OFF
		assertEquals("Extracted correct Value", 1, dcnh.extractCurrentCopyValue("TestName13"));
		assertEquals("Extracted correct Value", 1, dcnh.extractCurrentCopyValue("TestName13_"));
		assertEquals("Extracted correct Value", 1, dcnh.extractCurrentCopyValue("TestName13_a"));
		assertEquals("Extracted correct Value", 15, dcnh.extractCurrentCopyValue("TestName13_15"));
		assertEquals("Extracted correct Value", 1, dcnh.extractCurrentCopyValue("TestName13_a15"));
		assertEquals("Extracted correct Value", 1, dcnh.extractCurrentCopyValue("TestName13_17a"));
		assertEquals("Extracted correct Value", 1, dcnh.extractCurrentCopyValue("TestName13_78.4"));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testUpdateCopiedName() {
		DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
		
		//CHECKSTYLE:OFF
		assertEquals("Updated correct Name", "TestName12", dcnh.updateCopiedName("TestName12_13", 0));
		assertEquals("Updated correct Name", "TestName12", dcnh.updateCopiedName("TestName12_13", 1));
		assertEquals("Updated correct Name", "TestName12_02", dcnh.updateCopiedName("TestName12_13", 2));
		
		assertEquals("Updated correct Name", "TestName12", dcnh.updateCopiedName("TestName12", 0));
		assertEquals("Updated correct Name", "TestName12", dcnh.updateCopiedName("TestName12", 1));
		assertEquals("Updated correct Name", "TestName12_2", dcnh.updateCopiedName("TestName12", 2));
		
		assertEquals("Updated correct Name", "TestName12_50", dcnh.updateCopiedName("TestName12", 50));
		assertEquals("Updated correct Name", "TestName12__2", dcnh.updateCopiedName("TestName12_", 2));
		assertEquals("Updated correct Name", "TestName12_a_2", dcnh.updateCopiedName("TestName12_a", 2));
		assertEquals("Updated correct Name", "TestName12_13a_2", dcnh.updateCopiedName("TestName12_13a", 2));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testCreateCopiedName() {
		List<String> existingNames = Arrays.asList(
				"TestName1",
				"TestName2_3",
				"TestName2_4");

		DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
		assertEquals("Extracted correctName", "TestName12", dcnh.createCopiedName(existingNames, "TestName12"));
		assertEquals("Extracted correctName", "TestName1_2", dcnh.createCopiedName(existingNames, "TestName1"));
		assertEquals("Extracted correctName", "TestName1_2", dcnh.createCopiedName(existingNames, "TestName1_1"));
		assertEquals("Extracted correctName", "TestName1_2", dcnh.createCopiedName(existingNames, "TestName1_2"));
		assertEquals("Extracted correctName", "TestName1_2", dcnh.createCopiedName(existingNames, "TestName1_3"));
		assertEquals("Extracted correctName", "TestName2_5", dcnh.createCopiedName(existingNames, "TestName2"));
	}
	
	@Test
	public void testUpdateCopiedNames() {
		List<StructuralElementInstance> existingSeis = Arrays.asList(
				createSei("TestName1"),
				createSei("TestName2"),
				createSei("TestName2_2"));

		List<StructuralElementInstance> copiedSeis = Arrays.asList(
				createSei("TestName1"),
				createSei("TestName2"),
				createSei("TestName2_2"),
				createSei("TestName3"));
		
		DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
		
		Collection<?> copiedNames = dcnh.updateCopiedNames(existingSeis, copiedSeis);

		assertSame("Got back the same list", copiedNames, copiedSeis);
		
		//CHECKSTYLE:OFF
		assertEquals("Extracted correctName", "TestName1_2", copiedSeis.get(0).getName());
		assertEquals("Extracted correctName", "TestName2_3", copiedSeis.get(1).getName());
		assertEquals("Extracted correctName", "TestName2_4", copiedSeis.get(2).getName());
		assertEquals("Extracted correctName", "TestName3", copiedSeis.get(3).getName());
		//CHECKSTYLE:ON
	}
	
	/**
	 * Simple helper method to create a SEI with a given name.
	 * @param fullNamme the fullName to be used
	 * @return A SEI Instance with the given name
	 */
	private StructuralElementInstance createSei(String fullNamme) {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setName(fullNamme);
		return sei;
	}
}