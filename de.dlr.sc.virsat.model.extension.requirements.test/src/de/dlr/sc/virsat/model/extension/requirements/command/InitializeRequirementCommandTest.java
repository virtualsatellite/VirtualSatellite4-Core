/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.model.extension.requirements.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;

/**
 * @author fran_tb
 *
 */
public class InitializeRequirementCommandTest extends AConceptProjectTestCase {

	protected Concept concept;
	private static final String REQUIREMENT_TYPE_NAME = "ReqType";
	private static final String REQUIREMENT_ATTRIBUTE_TYPE_NAME = "ReqAttributeType";
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		concept = loadConceptFromPlugin(Activator.getPluginId());
		addEditingDomainAndRepository();
	}

	@Test
	public void testExecute() {
		Requirement requirement = new Requirement(concept);
		
		RequirementType requirementType = new RequirementType(concept);
		requirementType.setName(REQUIREMENT_TYPE_NAME);
		RequirementAttribute attributeDef = new RequirementAttribute(concept);
		attributeDef.setName(REQUIREMENT_ATTRIBUTE_TYPE_NAME);
		requirementType.getAttributes().add(attributeDef);
		
		Command initCommand = InitializeRequirementCommand.create(editingDomain, requirement.getTypeInstance(), requirementType.getTypeInstance());
		initCommand.execute();
		
		assertEquals("Type has to be set", requirementType, requirement.getReqType());
		assertEquals("Name has to be set", Requirement.REQUIREMENT_NAME_PREFIX + REQUIREMENT_TYPE_NAME, requirement.getName());
		assertEquals("Initial status has to be set", Requirement.STATUS_Open_NAME, requirement.getStatus());
		
		assertFalse("Attribute instance has to be created", requirement.getElements().isEmpty());
		AttributeValue newRequirmentAttributeValue = requirement.getElements().get(0);
		assertEquals("Attribute instance's type has to be set", newRequirmentAttributeValue.getAttType(), attributeDef);
		final String expectedAttributeName = AttributeValue.ATTRIBUTE_NAME_PREFIX + REQUIREMENT_ATTRIBUTE_TYPE_NAME;
		assertEquals("Attribute instance's name has to be set", expectedAttributeName, newRequirmentAttributeValue.getName());
	}
	
	@Test
	public void testExecuteWithBeans() {
		Requirement requirement = new Requirement(concept);
		
		RequirementType requirementType = new RequirementType(concept);
		requirementType.setName(REQUIREMENT_TYPE_NAME);
		RequirementAttribute attributeDef = new RequirementAttribute(concept);
		attributeDef.setName(REQUIREMENT_ATTRIBUTE_TYPE_NAME);
		requirementType.getAttributes().add(attributeDef);
		
		Command initCommand = InitializeRequirementCommand.create(editingDomain, requirement, requirementType);
		initCommand.execute();
		
		assertEquals("Type has to be set", requirementType, requirement.getReqType());
		assertEquals("Name has to be set", Requirement.REQUIREMENT_NAME_PREFIX + REQUIREMENT_TYPE_NAME, requirement.getName());
		assertEquals("Initial status has to be set", Requirement.STATUS_Open_NAME, requirement.getStatus());
		
		assertFalse("Attribute instance has to be created", requirement.getElements().isEmpty());
		AttributeValue newRequirmentAttributeValue = requirement.getElements().get(0);
		assertEquals("Attribute instance's type has to be set", newRequirmentAttributeValue.getAttType(), attributeDef);
		final String expectedAttributeName = AttributeValue.ATTRIBUTE_NAME_PREFIX + REQUIREMENT_ATTRIBUTE_TYPE_NAME;
		assertEquals("Attribute instance's name has to be set", expectedAttributeName, newRequirmentAttributeValue.getName());
		
	}

}
