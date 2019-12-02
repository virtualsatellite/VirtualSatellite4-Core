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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
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
public class RequirementTest extends AConceptProjectTestCase {
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
	}
	
	private static final String INITIAL_REQUIREMENT_NAME = "Requirement1";
	private static final String IDENTIFIER_ATTRIBUTE_VALUE = "Attribute1";
	private static final String STRING_ATTRIBUTE_VALUE = "Attribute1";
	
	Concept concept = loadConceptFromPlugin(Activator.getPluginId());
	
	@Test
	public void testUpdateNameFromAttributes() {
		Requirement testRequirement = new Requirement(concept);
		testRequirement.setName(INITIAL_REQUIREMENT_NAME);
		AttributeValue attValue = new AttributeValue(concept);
		RequirementAttribute attDefinition = new RequirementAttribute(concept);
		attDefinition.setType(RequirementAttribute.TYPE_Identifier_NAME);
		attValue.setAttType(attDefinition);
		attValue.setValue(IDENTIFIER_ATTRIBUTE_VALUE);
		testRequirement.getElements().add(attValue);
		
		assertEquals(testRequirement.getName(), INITIAL_REQUIREMENT_NAME);
		testRequirement.updateNameFromAttributes();
		assertEquals(testRequirement.getName(), Requirement.REQUIREMENT_NAME_PREFIX + IDENTIFIER_ATTRIBUTE_VALUE);
	}
	
	@Test
	public void testUpdateNameFromMultipleAttributes() {
		Requirement testRequirement = new Requirement(concept);
		testRequirement.setName(INITIAL_REQUIREMENT_NAME);
		
		AttributeValue attValue1 = new AttributeValue(concept);
		RequirementAttribute attDefinition1 = new RequirementAttribute(concept);
		attDefinition1.setType(RequirementAttribute.TYPE_Identifier_NAME);
		attValue1.setAttType(attDefinition1);
		attValue1.setValue(IDENTIFIER_ATTRIBUTE_VALUE);
		testRequirement.getElements().add(attValue1);
		
		AttributeValue attValue2 = new AttributeValue(concept);
		RequirementAttribute attDefinition2 = new RequirementAttribute(concept);
		attDefinition2.setType(RequirementAttribute.TYPE_Identifier_NAME);
		attValue2.setAttType(attDefinition2);
		attValue2.setValue(IDENTIFIER_ATTRIBUTE_VALUE);
		testRequirement.getElements().add(attValue2);
		
		AttributeValue attValue3 = new AttributeValue(concept);
		RequirementAttribute attDefinition3 = new RequirementAttribute(concept);
		attDefinition3.setType(RequirementAttribute.TYPE_String_NAME);
		attValue3.setAttType(attDefinition3);
		attValue3.setValue(STRING_ATTRIBUTE_VALUE);
		testRequirement.getElements().add(attValue3);
		
		assertEquals(testRequirement.getName(), INITIAL_REQUIREMENT_NAME);
		testRequirement.updateNameFromAttributes();
		assertEquals(testRequirement.getName(), Requirement.REQUIREMENT_NAME_PREFIX + IDENTIFIER_ATTRIBUTE_VALUE + IDENTIFIER_ATTRIBUTE_VALUE);
	}
	
	@Test
	public void testUpdateNameFromAttributesWithCommand() {
		Requirement testRequirement = new Requirement(concept);
		testRequirement.setName(INITIAL_REQUIREMENT_NAME);
		AttributeValue attValue = new AttributeValue(concept);
		RequirementAttribute attDefinition = new RequirementAttribute(concept);
		attDefinition.setType(RequirementAttribute.TYPE_Identifier_NAME);
		attValue.setAttType(attDefinition);
		attValue.setValue(IDENTIFIER_ATTRIBUTE_VALUE);
		testRequirement.getElements().add(attValue);
		
		assertEquals(testRequirement.getName(), INITIAL_REQUIREMENT_NAME);
		Command command = testRequirement.updateNameFromAttributes(editingDomain);
		command.execute();
		assertEquals(testRequirement.getName(), Requirement.REQUIREMENT_NAME_PREFIX + IDENTIFIER_ATTRIBUTE_VALUE);
	}

}
