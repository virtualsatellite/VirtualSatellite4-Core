/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.migrator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.ValueE;
import de.dlr.sc.virsat.model.dvlm.calculation.ValuePi;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;

/**
 * Tests the utility functions provided in ConceptMigratorUtil
 * @author muel_s8
 *
 */

public class ConceptMigrationHelperTest extends AConceptMigratorTest {
	
	private ConceptMigrationHelper cmHelper;
	
	@Before
	@Override
	public void setup() throws Exception {
		super.setup();
		cmHelper = new ConceptMigrationHelper(oldConcept);
	}
	
	@Test
	public void addEquationDefinitionTest() {
		EquationDefinition eqDefinition = CalculationFactory.eINSTANCE.createEquationDefinition();
		eqDefinition.setExpression(CalculationFactory.eINSTANCE.createValuePi());
		
		caB.setEquationSection(CalculationFactory.eINSTANCE.createEquationSection());
		cmHelper.addEquationDefinition(eqDefinition, categoryB);
		assertFalse("Equation correctly added to categoryAssignments", caB.getEquationSection().getEquations().isEmpty());
	}
	
	@Test
	public void removeEquationDefinitionTest() {
		assertFalse("Equation correctly added to categoryAssignments", caA.getEquationSection().getEquations().isEmpty());
		cmHelper.removeEquationDefinition(eqDefinition);
		assertTrue("Equation correctly added to categoryAssignments", caA.getEquationSection().getEquations().isEmpty());
	}
	
	@Test
	public void changeEquationDefinitionTest() {
		Equation eq = caA.getEquationSection().getEquations().get(0);
		assertTrue("Originally the expression is set to constant e", eq.getExpression() instanceof ValueE);
		eqDefinition.setExpression(CalculationFactory.eINSTANCE.createValuePi());
		cmHelper.changeEquationDefinition(eqDefinition, eqDefinition);
		eq = caA.getEquationSection().getEquations().get(0);
		assertTrue("Now the expression has been updated to constant pi", eq.getExpression() instanceof ValuePi);
	}
	
	@Test
	public void addPropertyTest() {
		AProperty property = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		property.setName("x");
	
		cmHelper.addProperty(property, categoryA);
		
		assertNotNull("Property x correctly added to category", ActiveConceptHelper.getProperty(categoryA, "x"));
		APropertyInstance pi = caA.getPropertyInstances().get(1);
		assertEquals("Property instance correctly added to categoryAssignments", "x", pi.getType().getName());
	}
	
	@Test
	public void movePropertyTest() {
		AProperty property = (AProperty) vpi.getType();
		cmHelper.moveProperty(property, categoryB);
		
		assertNotNull("Property 'a' correctly moved to new category", ActiveConceptHelper.getProperty(categoryB, "a"));
		assertNull("Property 'a' correctly left old category", ActiveConceptHelper.getProperty(categoryA, "a"));
	}
	
	@Test
	public void removePropertyTest() {
		AProperty property = (AProperty) vpi.getType();
		cmHelper.removeProperty(property);
		assertNull("Property 'a' removed from old category", ActiveConceptHelper.getProperty(categoryA, "a"));
	}
	
	@Test
	public void changePropertyTypeTest() {
		AProperty propertyC = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		propertyC.setName("c");
		
		cmHelper.changePropertyType(cmHelper.getProperty("a", "CategoryA"), propertyC);
		assertNull("Property 'a' properly gone", ActiveConceptHelper.getProperty(categoryA, "a"));
		assertNotNull("Property name correctly changed", ActiveConceptHelper.getProperty(categoryA, "c"));
		APropertyInstance pi = caA.getPropertyInstances().get(0);
		assertTrue("Property type changed correctly", pi.getType() instanceof FloatProperty);
	}
	
	@Test
	public void testRemoveStructuralElement() {
		assertTrue("Structural element instance is still there", repository.getRootEntities().contains(seiA));
		StructuralElement seA = (StructuralElement) seiA.getType();
		cmHelper.removeStructuralElement(seA);
		assertFalse("Structural element instances have been cleaned up", repository.getRootEntities().contains(seiA));
	}
	
	@Test
	public void testRemoveCategory() {
		assertTrue("Category Assignment is still there", seiA.getCategoryAssignments().contains(caA));
		Category cA = (Category) caA.getType();
		cmHelper.removeCategory(cA);
		assertFalse("Category Assignments have been cleaned up", seiA.getCategoryAssignments().contains(caA));
	}
	
	@Test
	public void compareVersionsTest() {
		String v1 = "1.0";
		String v2 = "1.1";
		
		assertEquals("1.1 is newer than 1.0", -1, ConceptMigrationHelper.compareVersions(v1, v2));
		
		v1 = "11.1";
		v2 = "3.7";
		
		assertEquals("11.1 is newer than 3.7", 1, ConceptMigrationHelper.compareVersions(v1, v2));
		
		v1 = "1.0";
		v2 = "1.0.1";
		
		assertEquals("1.0.1 is newer than 1.0", -1, ConceptMigrationHelper.compareVersions(v1, v2));
	}
}
