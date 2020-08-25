/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the requirements helper
 *
 */
public class RequirementHelperTest {

	@Test
	public void testCleanEntityName() {
		final String BASE_ENTITY_NAME = "Entity";
		final String ENTITY_NAME_APPENDIX = "Bla";
		
		final String MINUS = "-";
		final String WHITE_SPACE = " ";
		final String UNDERSCORE = "_";
		
		RequirementHelper helper = new RequirementHelper();
		
		assertEquals("Clean name should be left as it is", BASE_ENTITY_NAME,
				helper.cleanEntityName(BASE_ENTITY_NAME));
		
		assertEquals("Minus should be removed", BASE_ENTITY_NAME + ENTITY_NAME_APPENDIX, 
				helper.cleanEntityName(BASE_ENTITY_NAME + MINUS + ENTITY_NAME_APPENDIX));
		
		assertEquals("Whitspace should be removed", BASE_ENTITY_NAME + ENTITY_NAME_APPENDIX, 
				helper.cleanEntityName(BASE_ENTITY_NAME + WHITE_SPACE + ENTITY_NAME_APPENDIX));
		
		assertEquals("Underscore should be removed", BASE_ENTITY_NAME + ENTITY_NAME_APPENDIX, 
				helper.cleanEntityName(BASE_ENTITY_NAME + UNDERSCORE + ENTITY_NAME_APPENDIX));
		
		assertEquals("All invalid characters should be removed", BASE_ENTITY_NAME + ENTITY_NAME_APPENDIX, 
				helper.cleanEntityName(WHITE_SPACE + BASE_ENTITY_NAME + MINUS + ENTITY_NAME_APPENDIX + UNDERSCORE));
	}
	
}
