/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.label;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 */
public class MultilineLabelFormatterTest {
	
	public static final String LABEL_PART_1 = "First";
	public static final String LABEL_PART_2 = "Second";
	public static final String LABEL_PART_1_UPPER = "FIRST";
	public static final String LABEL_PART_2_UPPER = "SECOND";
	
	@Test
	public void testCamelCaseLabelWrap() {
		
		final String TEST_INPUT = LABEL_PART_1 + LABEL_PART_2;
		
		String result = new MultilineLabelFormatter().getLabel(TEST_INPUT);
		
		final String EXPECTED_OUTPUT = LABEL_PART_1 + System.lineSeparator() + LABEL_PART_2;
		assertEquals("Added line break between label parts", EXPECTED_OUTPUT, result);
	}
	
	@Test
	public void testSinglePartConstantName() {
		
		final String TEST_INPUT = LABEL_PART_1_UPPER;
		
		String result = new MultilineLabelFormatter().getLabel(TEST_INPUT);
		
		final String EXPECTED_OUTPUT = LABEL_PART_1_UPPER;
		assertEquals("Single part should not be touched", EXPECTED_OUTPUT, result);
	}
	
	@Test
	public void testConstantName() {
		
		final String TEST_INPUT = LABEL_PART_1_UPPER + "_" + LABEL_PART_2_UPPER;
		
		String result = new MultilineLabelFormatter().getLabel(TEST_INPUT);
		
		final String EXPECTED_OUTPUT = LABEL_PART_1_UPPER + System.lineSeparator() + LABEL_PART_2_UPPER;
		assertEquals("Added line break between label parts", EXPECTED_OUTPUT, result);
	}
	
	@Test
	public void testTwoWordsName() {
		
		final String TEST_INPUT = LABEL_PART_1_UPPER + " " + LABEL_PART_2_UPPER;
		
		String result = new MultilineLabelFormatter().getLabel(TEST_INPUT);
		
		final String EXPECTED_OUTPUT = LABEL_PART_1_UPPER + System.lineSeparator() + LABEL_PART_2_UPPER;
		assertEquals("Added line break between label parts", EXPECTED_OUTPUT, result);
	}

}
