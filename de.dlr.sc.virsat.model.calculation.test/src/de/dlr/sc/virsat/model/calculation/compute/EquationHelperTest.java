/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.datastructures.DependencyTree;
import de.dlr.sc.virsat.model.calculation.compute.problem.EvaluationProblem;
import de.dlr.sc.virsat.model.calculation.compute.problem.IncompatibleQuantityKindsProblem;
import de.dlr.sc.virsat.model.calculation.test.util.ExpressionUtil;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.MathOperator;
import de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test cases for the dependency tree.
 * @author muel_s8
 *
 */

public class EquationHelperTest extends AEquationTest {
	
	private EquationHelper helper;
	private StructuralElementInstance sei;
	private Category cat;
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		helper = new EquationHelper();

		// Create some test data
		cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName("Parameter");
		cat.setIsApplicableForAll(true);
		contents.add(cat);
		
		IntProperty value = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		value.setName("value");
		cat.getProperties().add(value);
		
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("Equipment");
		
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setName("equipment");
		sei.setType(se);
		contents.add(sei);
		
		createResources();
	}

	@Test
	public void testEvaluate() {
		
		CategoryAssignment caMass = new CategoryInstantiator().generateInstance(cat, "mass");
		CategoryAssignment caMargin = new CategoryInstantiator().generateInstance(cat, "margin");
		CategoryAssignment caMassWithMargin = new CategoryInstantiator().generateInstance(cat, "massWithMargin");
		CategoryAssignment caTotalMass = new CategoryInstantiator().generateInstance(cat, "totalMass");
		
		// Set the properties as inherited to also check that the override flag is propagated correctly
		UnitValuePropertyInstance vpiMass = (UnitValuePropertyInstance) caMass.getPropertyInstances().get(0);
		UnitValuePropertyInstance vpiMargin = (UnitValuePropertyInstance) caMass.getPropertyInstances().get(0);
		UnitValuePropertyInstance vpiMassWithMargin = (UnitValuePropertyInstance) caMass.getPropertyInstances().get(0);
		UnitValuePropertyInstance vpiTotalMass = (UnitValuePropertyInstance) caMass.getPropertyInstances().get(0);
		
		// Set an incompatible quantity kind
		AQuantityKind qk = QudvUnitHelper.getInstance().createSimpleQuantityKind("Q", "q", "", "");
		AUnit unit = QudvUnitHelper.getInstance().createSimpleUnit("A", "a", "", "", qk);
		vpiTotalMass.setUnit(unit);
		
		vpiMass.setIsInherited(true);
		vpiMargin.setIsInherited(true);
		vpiMassWithMargin.setIsInherited(true);
		vpiTotalMass.setIsInherited(true);
		
		sei.getCategoryAssignments().add(caMass);
		sei.getCategoryAssignments().add(caMargin);
		sei.getCategoryAssignments().add(caMassWithMargin);
		sei.getCategoryAssignments().add(caTotalMass);
		
		// Create some test equations
		
		List<Equation> equations = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, 
					"Ref: equipment.margin.value = 0.1;"
				+	"Ref: equipment.massWithMargin.value = equipment.mass.value * (1 + equipment.margin.value);"
				+	"Ref: equipment.totalMass.value = fuel + equipment.massWithMargin.value;"
				+	"Calc: fuel = 20;"
				+	"Ref: equipment.mass.value = 10;"
		);
		
		DependencyTree<EObject> tree = helper.createDependencyTree(equations);
		List<EvaluationProblem> evalProblems = helper.evaluate(tree);
		
		// Verify correct computed result
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(caTotalMass);
		ValuePropertyInstance piValue = (ValuePropertyInstance) caHelper.getPropertyInstance("value");
		
		final int EXPECTED_RESULT = 31;
		assertEquals("End result correct", new Double(EXPECTED_RESULT), Double.valueOf(piValue.getValue()));
		
		// Verify correct propagation of override flag
		assertTrue("Override flag propagation is correct", vpiMass.isOverride());
		assertTrue("Override flag propagation is correct", vpiMargin.isOverride());
		assertTrue("Override flag propagation is correct", vpiMassWithMargin.isOverride());
		assertTrue("Override flag propagation is correct", vpiTotalMass.isOverride());
		
		// Verify correct computed evaluation problems
		assertEquals("There is one evaluation problems", 1, evalProblems.size());
		EvaluationProblem evalProblem = evalProblems.get(0);
		assertTrue("Its an incompatible quantity kinds problem", evalProblem instanceof IncompatibleQuantityKindsProblem);
	}
	
	@Test
	public void testEvaluateWithSelfreferencingSetFunction() {
		// Previously a Set was creating a cyclic dependency when referencing the property of itself
		// this cyclic dependency should now be avoided
		
		CategoryAssignment caMass = new CategoryInstantiator().generateInstance(cat, "mass");
		CategoryAssignment caMass2 = new CategoryInstantiator().generateInstance(cat, "mass2");
		sei.getCategoryAssignments().add(caMass);
		sei.getCategoryAssignments().add(caMass2);

		// Create some test equations
		List<Equation> equations = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, 
					"Ref: value = summary {Parameter.value};"
		);
		EquationSection es = CalculationFactory.eINSTANCE.createEquationSection();
		caMass.setEquationSection(es);
		es.getEquations().addAll(equations);
		
		DependencyTree<EObject> tree = helper.createDependencyTree(equations);
		assertNull("There are no cycles in the given equation", tree.hasCycle());
	}

	@Test
	public void testHandleCycles() {
		CategoryAssignment caMass = new CategoryInstantiator().generateInstance(cat, "mass");
		CategoryAssignment caMargin = new CategoryInstantiator().generateInstance(cat, "margin");
		CategoryAssignment caMassWithMargin = new CategoryInstantiator().generateInstance(cat, "massWithMargin");

		sei.getCategoryAssignments().add(caMass);
		sei.getCategoryAssignments().add(caMargin);
		sei.getCategoryAssignments().add(caMassWithMargin);
		
		// Create some test equations
		
		List<Equation> equations = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, 
					"Ref: equipment.margin.value = 0.1;"
				+	"Ref: equipment.massWithMargin.value = equipment.mass.value * equipment.margin.value;"
				+	"Ref: equipment.mass.value = equipment.massWithMargin.value / equipment.margin.value;"
		);
		
		DependencyTree<EObject> tree = helper.createDependencyTree(equations);
		
		// Check if removing cycles work
		tree.removeCycles((node) -> { });
		assertTrue("All cycles removed", tree.hasCycle() == null);
		
		// Check that after removing cycles, elements not involved in the cycle have still been correctly
		// evaluated.
		helper.evaluate(tree);
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(caMargin);
		ValuePropertyInstance piValue = (ValuePropertyInstance) caHelper.getPropertyInstance("value");
		
		final double EXPECTED_RESULT = 0.1;
		assertEquals("Value not involed in cycle also correctly assigned.", new Double(EXPECTED_RESULT), Double.valueOf(piValue.getValue()));
	}
	
	@Test
	public void testEvaluateEquationsInstantiatedFromEquationDefinitions() {
		// Create a common root category
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName("Mass");
		
		IntProperty massWithMargin = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		massWithMargin.setName("massWithMargin");
		cat.getProperties().add(massWithMargin);
		
		IntProperty margin = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		margin.setName("margin");
		cat.getProperties().add(margin);
		
		IntProperty mass = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		mass.setName("mass");
		cat.getProperties().add(mass);

		// Setup a simple equation definition massWithMargin = margin * mass
		EquationDefinition eqDef = CalculationFactory.eINSTANCE.createEquationDefinition();
		TypeDefinitionResult tdr = CalculationFactory.eINSTANCE.createTypeDefinitionResult();
		tdr.setReference(massWithMargin);
		eqDef.setResult(tdr);
		
		MultiplicationAndDivision expression = CalculationFactory.eINSTANCE.createMultiplicationAndDivision();
		expression.setOperator(MathOperator.MULTIPLY);
		eqDef.setExpression(expression);
		
		ReferencedDefinitionInput rdMass = CalculationFactory.eINSTANCE.createReferencedDefinitionInput();
		rdMass.setReference(mass);
		
		ReferencedDefinitionInput rdMargin = CalculationFactory.eINSTANCE.createReferencedDefinitionInput();
		rdMargin.setReference(margin);
		
		expression.setLeft(rdMargin);
		expression.setRight(rdMass);
		
		cat.getEquationDefinitions().add(eqDef);
		
		// Perform the instantiation
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, "massParameters");
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(ca);
		ValuePropertyInstance vpiMassWithMargin = (ValuePropertyInstance) caHelper.getPropertyInstance("massWithMargin");
		ValuePropertyInstance vpiMass = (ValuePropertyInstance) caHelper.getPropertyInstance("mass");
		ValuePropertyInstance vpiMargin = (ValuePropertyInstance) caHelper.getPropertyInstance("margin");
		
		final double MASS = 20;
		final double MARGIN = 1.2;
		final double EXPECTED = MASS * MARGIN;
		
		vpiMass.setValue(String.valueOf(MASS));
		vpiMargin.setValue(String.valueOf(MARGIN));
		
		EquationHelper dtHelper = new EquationHelper();
		dtHelper.evaluate(ca.getEquationSection().getEquations());
		
		assertEquals("Instantiated equation performs correctly", EXPECTED, Double.valueOf(vpiMassWithMargin.getValue()), EPSILON);
	}

}
