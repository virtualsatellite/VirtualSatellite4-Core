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

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.Activator;

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
public class RequirementGroupTest extends ARequirementGroupTest {
	
	public static final String GROUP_NAME = "GROUP_1";
	
	@Test
	public void testGetIdentifier() {
		String conceptXmiPluginPath = Activator.getPluginId() + "/concept/concept.xmi";
		Concept concept = ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
		
		RequirementGroup group = new RequirementGroup(concept);
		group.setName(GROUP_NAME);
		
		assertEquals("Name should be used as ID for groups", GROUP_NAME, group.getIdentifier());
	}
	


}
