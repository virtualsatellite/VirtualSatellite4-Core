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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralResult;
import de.dlr.sc.virsat.model.calculation.compute.extensions.UnresolvedExpressionResult;
import de.dlr.sc.virsat.model.calculation.test.util.ExpressionUtil;
import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.Function;
import de.dlr.sc.virsat.model.dvlm.calculation.MathOperator;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * test Cases for the expression helper
 * @author fisc_ph
 *
 */
public class ExpressionHelperTest extends AEquationTest {
	
	/**
	 * Create a category with the specified name that can be applied to any sei
	 * and put it into the resource contents
	 * @param name the category name
	 * @return the category
	 */
	private Category createCategory(String name) {
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName(name);
		cat.setIsApplicableForAll(true);
		contents.add(cat);
		return cat;
	}
	
	
	/**
	 * Generate a new category assignment of the passed structural element and add it to the
	 * resource contents
	 * @param name the name of the category assignment
	 * @param cat the category element type
	 * @return a new category assignment instance of the given type
	 */
	private CategoryAssignment createCategoryAssignment(Category cat, String name) {
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, name);
		contents.add(ca);
		return ca;
	}
	
	/**
	 * Generate a new structural element instance of the passed structural element and add it to the
	 * resource contents
	 * @param name the name of the structural element instance
	 * @param se the structural element type
	 * @return a new structural element instance of the given type
	 */
	private StructuralElementInstance createStructuralElementInstance(String name, StructuralElement se) {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setName(name);
		sei.setType(se);
		contents.add(sei);
		return sei;
	}
	

	//CHECKSTYLE:OFF
	//because of too many magic numbers
	private ExpressionHelper exprHelper;
	private StructuralElement se;
	
	/**
	 * 
	 * @param resultString
	 * @return
	 */
	private double getDoubleValueOfResult (String resultString) {
		double result = Double.parseDouble(resultString);
		return result;
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		exprHelper = new ExpressionHelper();
		
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
	}

	@Test
	public void testNumberLiteral() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=20.3;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", 20.3, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}

	@Test
	public void testPi() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=pi;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", Math.PI, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}

	@Test
	public void testE() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=e;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", 2.718281828459045, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}

	@Test
	public void testAddition() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=20.3 + 80;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", 100.3, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}

	@Test
	public void testSubtraction() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=20.3 - 80;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", -59.7, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}

	@Test
	public void testMultiply() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=20.3 * 80.91;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", 1642.473, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}

	@Test
	public void testDivide() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=20.3 / (-80);");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", -0.25375, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}

	@Test
	public void testComplexEquation1() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=20.3 / (-10)+90 * 2;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", 177.97, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}

	@Test
	public void testComplexEquation2() {
		AExpression testExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=-(20/(-10)+90) * 2;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression);
		NumberLiteral result = expressionResult.getNumberLiteral();
		assertEquals("", -176, getDoubleValueOfResult(result.getValue()), EPSILON);		
	}


	@Test
	public void testDistributiveProperty1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=(3-5) * 2;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 * 2 -5 * 2;");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertEquals("", -4.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
		assertEquals("", getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);		
	}

	@Test
	public void testDistributiveProperty2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=(3-5) / 2;");
		NumberLiteralResult expressionResult = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 / 2 -5 / 2;");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertEquals("", -1.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
		assertEquals("", getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);		
	}

	@Test
	public void testDistributiveProperty3() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=(3-5) + 2;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 + 2 -5 + 2;");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertNotEquals(getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);
	}

	@Test
	public void testDistributiveProperty4() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=(3-5) - 2;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 - 2 -5 - 2;");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertNotEquals(getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);
	}

	@Test
	public void testCommutativeProperty1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=5 * 2;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=2 * 5;");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertEquals("", 10.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
		assertEquals("", getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);		
	}

	@Test
	public void testCommutativeProperty2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=5 + 2;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=2 + 5;");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertEquals("", 7.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
		assertEquals("", getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);		
	}

	@Test
	public void testCommutativeProperty3() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=5 / 2;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=2 / 5;");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertNotEquals(getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);
	}

	@Test
	public void testCommutativeProperty4() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=5 - 2;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=2 - 5;");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertNotEquals(getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);
	}

	@Test
	public void testAssociativeProperty1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=(3 + 2) + 7;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression3 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=-(3 + 2) + 7;");
		NumberLiteralResult expressionResult3 = (NumberLiteralResult) exprHelper.evaluate(testExpression3);
		NumberLiteral result3 = expressionResult3.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 + (2 + 7);");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		AExpression testExpression4 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 + (-(2 + 7));");
		NumberLiteralResult expressionResult4 = (NumberLiteralResult) exprHelper.evaluate(testExpression4);
		NumberLiteral result4 = expressionResult4.getNumberLiteral();

		assertEquals("", 12.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
		assertEquals("", -6.0, getDoubleValueOfResult(result4.getValue()), EPSILON);
		assertEquals("", 2.0, getDoubleValueOfResult(result3.getValue()), EPSILON);
		assertEquals("", getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);		
	}

	@Test
	public void testAssociativeProperty2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=(3 * 2) * 7;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 * (2 * 7);");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertEquals("", 42.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
		assertEquals("", getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);		
	}

	@Test
	public void testAssociativeProperty3() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=(3 / 2) / 7;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 / (2 / 7);");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertNotEquals(getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);
	}

	@Test
	public void testAssociativeProperty4() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=(3 - 2) - 7;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		AExpression testExpression2 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=3 - (2 - 7);");
		NumberLiteralResult expressionResult2 = (NumberLiteralResult) exprHelper.evaluate(testExpression2);
		NumberLiteral result2 = expressionResult2.getNumberLiteral();

		assertNotEquals(getDoubleValueOfResult(result1.getValue()), getDoubleValueOfResult(result2.getValue()), EPSILON);
	}

	@Test
	public void testPowerFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=2^7;");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 128.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testPowerFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=2 ^ (7/8);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 1.8340, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	//trigonometric functions
	@Test
	public void testCosFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=cos(5);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 0.2836, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testCosFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=cos(3.14^2);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", -0.9069, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testCosPiFunktion () {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=cos(pi);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", -1.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testCosFunction3() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=cos(pi);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", -1.0, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testSinFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=sin(3.14);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 0.00159, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testSinFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=sin(3.14^9);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", -0.8545, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testSinFunction3() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=sin(3.14-9);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 0.41066, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testTanFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=tan(-18);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 1.13731, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testTanFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=tan(6.47*3.1);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 2.62995, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testATanFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=atan(6);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 1.40564, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testATanFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=atan(6.47-3.1);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 1.28233, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testACosFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=acos(0.5);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 1.04719, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testACosFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=acos(3.5);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", Double.NaN, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testASinFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=asin(-0.5);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", -0.5235, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testASinFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=asin(-1.000005);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", Double.NaN, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testSqrtFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=sqrt(16.5);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 4.06201, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testSqrtFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=sqrt(-16.5);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", Double.NaN, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testLogFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=log(20.0);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 1.30102, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testLogFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=log(0.0);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", Double.NaN, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testLdFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=ld(20.0);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 4.32192, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testLdFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=ld(0.0);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", Double.NaN, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testLnFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=ln(20.0);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 2.99573, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testLnFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=ln(0.0);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", Double.NaN, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testExpFunction1() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=exp(4.5);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 90.0171, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	@Test
	public void testExpFunction2() {
		AExpression testExpression1 = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "test=exp(-4.5);");
		NumberLiteralResult expressionResult1 = (NumberLiteralResult) exprHelper.evaluate(testExpression1);
		NumberLiteral result1 = expressionResult1.getNumberLiteral();

		assertEquals("", 0.01110, getDoubleValueOfResult(result1.getValue()), EPSILON);
	}

	/**
	 * // this test checks that in the equation language we correctly refer to links to the data model
	 */
	@Test
	public void testValuePropertyRefFunction() {

		// first we need to do some setup work in the data model, so we have object we can refer to.

		// Create a common root category
		Category cat = createCategory("Dimensions");

		// Add two simple properties defining the size xyz that will convert into ValueInstanceProperties later
		IntProperty sizeX = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		sizeX.setName("sizeX");
		IntProperty sizeY = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		sizeY.setName("sizeY");
		IntProperty sizeZ = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		sizeZ.setName("sizeZ");
		IntProperty volume = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		volume.setName("volume");
		cat.getProperties().add(sizeX);
		cat.getProperties().add(sizeY);
		cat.getProperties().add(sizeZ);
		cat.getProperties().add(volume);

		// Now create a CategoryAssigment for our dimension category
		CategoryAssignment ca = createCategoryAssignment(cat, "dimension");

		//now we need to set some values for the dimensions so that the test can resolve them and does some calculation with them 
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(ca);
		APropertyInstance piSizeX = caHelper.getPropertyInstance("sizeX");
		APropertyInstance piSizeY = caHelper.getPropertyInstance("sizeY");
		APropertyInstance piSizeZ = caHelper.getPropertyInstance("sizeZ");
		//APropertyInstance piVolume = caHelper.getPropertyInstance("volume");
		
		//int property dimension will be a ValuePropertyInstance
		ValuePropertyInstance vpiSizeX = (ValuePropertyInstance) piSizeX;
		vpiSizeX.setValue("3");
		ValuePropertyInstance vpiSizeY = (ValuePropertyInstance) piSizeY;
		vpiSizeY.setValue("4");
		ValuePropertyInstance vpiSizeZ = (ValuePropertyInstance) piSizeZ;
		vpiSizeZ.setValue("5");
		
		createResources();

		// --------------------------------------------------------------------------------------------------------------------------
		// setup the expression to calculate and assert the result
		//---------------------------------------------------------------------------------------------------------------------------
		List<Equation> testVolumeExpression = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "volume = dimension.sizeX * dimension.sizeY * dimension.sizeZ;");
		NumberLiteralResult resultExpression1 = (NumberLiteralResult) exprHelper.evaluate(testVolumeExpression.get(0).getExpression());
		NumberLiteral result1 = resultExpression1.getNumberLiteral();
		
		assertEquals("ValuePropertyInstanceReference to calculate the volume through EquationEditor", 60.0, getDoubleValueOfResult(result1.getValue()), EPSILON);

	}
	
	@Test
	public void testUnresolvedExpression() {
		// Create a common root category
		Category cat = createCategory("Dimensions");
		
		// Now create a CategoryAssigment for our dimension category
		createCategoryAssignment(cat, "dimension");
		
		createResources();
		
		// --------------------------------------------------------------------------------------------------------------------------
		// setup the expression to calculate and assert the result
		//---------------------------------------------------------------------------------------------------------------------------
		List<Equation> equations1 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "unresolved = dimension;");
		IExpressionResult resultExpression1 = exprHelper.evaluate(equations1.get(0).getExpression());
		assertTrue("Result is unresolved as expected", resultExpression1 instanceof UnresolvedExpressionResult);
	}
	
	@Test
	public void testCount() {
		// Create a common root category
		Category cat = createCategory("Dimensions");
		
		// Now create a CategoryAssigment for our dimension category
		CategoryAssignment caDimension = new CategoryInstantiator().generateInstance(cat, "dimension");
		CategoryAssignment caCount = new CategoryInstantiator().generateInstance(cat, "count");
		
		//Let's add this assignment to a structural element instance in the system composition tree
		// Now add the Category assignment to a StructuralElementInstance
		StructuralElementInstance sei1 = new StructuralInstantiator().generateInstance(se, "ReactionWheel1");
		sei1.getCategoryAssignments().add(caDimension);
		sei1.getCategoryAssignments().add(caCount);
		caCount.setEquationSection(CalculationFactory.eINSTANCE.createEquationSection());
		
		createResources();
		
		// --------------------------------------------------------------------------------------------------------------------------
		// setup the expression to calculate and assert the result
		//---------------------------------------------------------------------------------------------------------------------------
		List<Equation> equations1 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "count = count{Dimensions};");
		Equation equation = equations1.get(0);
		caCount.getEquationSection().getEquations().add(equation);
		NumberLiteralResult resultExpression1 = (NumberLiteralResult) exprHelper.evaluate(equation.getExpression());
		assertEquals("Count on 1 element is correct", 1, Double.valueOf(resultExpression1.getNumberLiteral().getValue()), EPSILON);
		
		// Now create a second sei2 and add sei2 to sei1
		// Also create a ca and attach it to sei 2
		StructuralElementInstance sei2 = createStructuralElementInstance("ReactionWheel2", se);
		CategoryAssignment ca2 = new CategoryInstantiator().generateInstance(cat, "dimension");
		sei2.getCategoryAssignments().add(ca2);
		sei1.getChildren().add(sei2);
		createResources();
		
		NumberLiteralResult resultExpression2 = (NumberLiteralResult) exprHelper.evaluate(equation.getExpression());
		assertEquals("Count on element with child is correct", 2, Double.valueOf(resultExpression2.getNumberLiteral().getValue()), EPSILON);
	}

	@Test
	public void testCountWithDefinedLevels() {
		// Create a common root category
		Category cat = createCategory("Dimensions");
		
		// The ca that will hold the equation
		CategoryAssignment caCount = new CategoryInstantiator().generateInstance(cat, "count");
		caCount.setEquationSection(CalculationFactory.eINSTANCE.createEquationSection());

		// Now create a CategoryAssigment for our dimension category
		CategoryAssignment ca0 = new CategoryInstantiator().generateInstance(cat, "dimension");
		CategoryAssignment ca2 = new CategoryInstantiator().generateInstance(cat, "dimension");
		CategoryAssignment ca3 = new CategoryInstantiator().generateInstance(cat, "dimension");
		
		//Let's add this assignment to a structural element instance in the system composition tree
		// Now add the Category assignment to a StructuralElementInstance
		StructuralElementInstance sei0 = new StructuralInstantiator().generateInstance(se, "SeiLevel0Semantic0");
		StructuralElementInstance sei1 = new StructuralInstantiator().generateInstance(se, "SeiLevel1Semantic0");
		StructuralElementInstance sei2 = new StructuralInstantiator().generateInstance(se, "SeiLevel2Semantic1");
		StructuralElementInstance sei3 = new StructuralInstantiator().generateInstance(se, "SeiLevel3Semantic2");
		sei0.getChildren().add(sei1);
		sei1.getChildren().add(sei2);
		sei2.getChildren().add(sei3);
		
		sei0.getCategoryAssignments().add(caCount);
		sei0.getCategoryAssignments().add(ca0);
		sei2.getCategoryAssignments().add(ca2);
		sei3.getCategoryAssignments().add(ca3);
		
		createResources();
		
		// --------------------------------------------------------------------------------------------------------------------------
		// Count only on the current level
		//---------------------------------------------------------------------------------------------------------------------------
		List<Equation> equations = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "count = count{Dimensions, 0};");
		Equation equation = equations.get(0);
		caCount.getEquationSection().getEquations().add(equation);
		NumberLiteralResult resultExpression = (NumberLiteralResult) exprHelper.evaluate(equation.getExpression());
		assertEquals("Count on semantic level zero expects 1", 1, Double.valueOf(resultExpression.getNumberLiteral().getValue()), EPSILON);

		// --------------------------------------------------------------------------------------------------------------------------
		// Count on semantic level 1 which should be 2
		//---------------------------------------------------------------------------------------------------------------------------
		equations = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "count = count{Dimensions, 1};");
		equation = equations.get(0);
		caCount.getEquationSection().getEquations().clear();
		caCount.getEquationSection().getEquations().add(equation);
		resultExpression = (NumberLiteralResult) exprHelper.evaluate(equation.getExpression());
		assertEquals("Count on semantic level 1 is correct", 2, Double.valueOf(resultExpression.getNumberLiteral().getValue()), EPSILON);

		// --------------------------------------------------------------------------------------------------------------------------
		// Count on semantic level 2 which should be 3
		//---------------------------------------------------------------------------------------------------------------------------
		equations = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "count = count{Dimensions, 2};");
		equation = equations.get(0);
		caCount.getEquationSection().getEquations().clear();
		caCount.getEquationSection().getEquations().add(equation);
		resultExpression = (NumberLiteralResult) exprHelper.evaluate(equation.getExpression());
		assertEquals("Count on semantic level 2 is correct", 3, Double.valueOf(resultExpression.getNumberLiteral().getValue()), EPSILON);

		// --------------------------------------------------------------------------------------------------------------------------
		// Count on semantic level with infinite depth which should be 3 as well
		//---------------------------------------------------------------------------------------------------------------------------
		equations = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "count = count{Dimensions};");
		equation = equations.get(0);
		caCount.getEquationSection().getEquations().clear();
		caCount.getEquationSection().getEquations().add(equation);
		resultExpression = (NumberLiteralResult) exprHelper.evaluate(equation.getExpression());
		assertEquals("Count on 1 element is correct", 3, Double.valueOf(resultExpression.getNumberLiteral().getValue()), EPSILON);
	}
	
	@Test
	public void testSetFunctions() {
		// Create a common root category
		Category cat = createCategory("Mass");
		IntProperty value = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		value.setName("value");
		cat.getProperties().add(value);
		
		// Now create CategoryAssigments for our masses category
		CategoryAssignment ca1 = new CategoryInstantiator().generateInstance(cat, "mass1");

		CategoryAssignment ca2 = new CategoryInstantiator().generateInstance(cat, "mass2");
		CategoryAssignmentHelper caHelper2 = new CategoryAssignmentHelper(ca2);
		ValuePropertyInstance vpiValue2 = (ValuePropertyInstance) caHelper2.getPropertyInstance("value");
		vpiValue2.setValue("30");
		
		CategoryAssignment ca3 = new CategoryInstantiator().generateInstance(cat, "mass3");
		CategoryAssignmentHelper caHelper3 = new CategoryAssignmentHelper(ca3);
		ValuePropertyInstance vpiValue3 = (ValuePropertyInstance) caHelper3.getPropertyInstance("value");
		vpiValue3.setValue("10");
		
		//Let's add this assignment to a structural element instance in the system composition tree
		// Now add the Category assignment to a StructuralElementInstance
		StructuralElementInstance sei1 = createStructuralElementInstance("AOCS", se);
		sei1.getCategoryAssignments().add(ca1);
		sei1.getCategoryAssignments().add(ca2);
		sei1.getCategoryAssignments().add(ca3);
		ca1.setEquationSection(CalculationFactory.eINSTANCE.createEquationSection());
		
		createResources();
		
		// --------------------------------------------------------------------------------------------------------------------------
		// setup the expression to calculate and assert the result
		//---------------------------------------------------------------------------------------------------------------------------
		List<Equation> equations1 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "result = summary{Mass.value};");
		Equation equation1 = equations1.get(0);
		ca1.getEquationSection().getEquations().add(equation1);
		NumberLiteralResult resultExpression1 = (NumberLiteralResult) exprHelper.evaluate(equation1.getExpression());
		assertEquals("Summary correct", 40, Double.valueOf(resultExpression1.getNumberLiteral().getValue()), EPSILON);
		
		createResources();
		
		List<Equation> equations2 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "result = mean{Mass.value};");
		Equation equation2 = equations2.get(0);
		ca1.getEquationSection().getEquations().add(equation2);
		NumberLiteralResult resultExpression2 = (NumberLiteralResult) exprHelper.evaluate(equation2.getExpression());
		assertEquals("Mean correct", 20, Double.valueOf(resultExpression2.getNumberLiteral().getValue()), EPSILON);
		
		createResources();
		
		List<Equation> equations3 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "result = max{Mass.value};");
		Equation equation3 = equations3.get(0);
		ca1.getEquationSection().getEquations().add(equation3);
		NumberLiteralResult resultExpression3 = (NumberLiteralResult) exprHelper.evaluate(equation3.getExpression());
		assertEquals("Max correct", 30, Double.valueOf(resultExpression3.getNumberLiteral().getValue()), EPSILON);
		
		createResources();
		
		List<Equation> equations4 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "result = min{Mass.value};");
		Equation equation4 = equations4.get(0);
		ca1.getEquationSection().getEquations().add(equation4);
		NumberLiteralResult resultExpression4 = (NumberLiteralResult) exprHelper.evaluate(equation4.getExpression());
		assertEquals("Min correct", 10, Double.valueOf(resultExpression4.getNumberLiteral().getValue()), EPSILON);
	}
	
	@Test
	public void testNestedSeisSetFunction() {
		// Create a common root category
		Category cat = createCategory("Mass");
		IntProperty value = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		value.setName("value");
		cat.getProperties().add(value);
		
		// Setup a summary equation in the category
		EquationDefinition eqDef = CalculationFactory.eINSTANCE.createEquationDefinition();
		TypeDefinitionResult tdResult = CalculationFactory.eINSTANCE.createTypeDefinitionResult();
		tdResult.setReference(value);
		
		SetFunction summary = CalculationFactory.eINSTANCE.createSetFunction();
		summary.setOperator("summary");
		summary.setTypeDefinition(value);
		
		eqDef.setResult(tdResult);
		eqDef.setExpression(summary);
		cat.getEquationDefinitions().add(eqDef);
		
		// Setup the data model:
		// root
		//	-- CA: massRoot
		//	-- Eq: massRoot = summary{Mass.value}
		//	-- Sei: Mid
		//		-- CA: massMid
		//		-- Eq: massMid = summary{Mass.value}
		//		-- Sei: Leaf
		//			-- CA: massLeaf
		StructuralElementInstance seiRoot = createStructuralElementInstance("System", se);
		CategoryAssignment caRoot = new CategoryInstantiator().generateInstance(cat, "massRoot");
		seiRoot.getCategoryAssignments().add(caRoot);
		
		StructuralElementInstance seiMid = createStructuralElementInstance("AOCS", se);
		CategoryAssignment caMid = new CategoryInstantiator().generateInstance(cat, "massMid");
		// For the test case assume caMid has already been computed
		((ValuePropertyInstance) caMid.getPropertyInstances().get(0)).setValue("1");
		seiMid.getCategoryAssignments().add(caMid);
		seiRoot.getChildren().add(seiMid);
		
		StructuralElementInstance seiLeaf = createStructuralElementInstance("ReactionWheel", se);
		CategoryAssignment caLeaf = new CategoryInstantiator().generateInstance(cat, "massLeaf");
		// caLeaf just has a value and not equations
		caLeaf.getEquationSection().getEquations().clear();
		((ValuePropertyInstance) caRoot.getPropertyInstances().get(0)).setValue("1");
		seiLeaf.getCategoryAssignments().add(caLeaf);
		seiMid.getChildren().add(seiLeaf);
		
		// Cant use the common test resource setup since in this test case
		// we need multiple seis each properly inserted into its own resource
		ResourceSet resSet = new ResourceSetImpl();
		Resource resRoot = resSet.createResource(URI.createURI("resRoot"));
		resRoot.getContents().add(seiRoot);
		Resource resMid = resSet.createResource(URI.createURI("resMid"));
		resMid.getContents().add(seiMid);
		Resource resLeaf = resSet.createResource(URI.createURI("resLeaf"));
		resLeaf.getContents().add(seiLeaf);
		
		NumberLiteralResult resultExpression1 = (NumberLiteralResult) exprHelper.evaluate(caRoot.getEquationSection().getEquations().get(0).getExpression());
		assertEquals("Summary correct", 1, Double.valueOf(resultExpression1.getNumberLiteral().getValue()), EPSILON);
	}
	
	@Test
	public void testNestedCasSetFunction() {
		// Create a contained category
		Category catPower = createCategory("Power");
		IntProperty value = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		value.setName("value");
		value.setDefaultValue("1");
		catPower.getProperties().add(value);
		
		// Create a container caetgory
		Category catState = createCategory("State");
		IntProperty totalValue = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		ComposedProperty powerProperty = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		powerProperty.setType(catPower);
		totalValue.setName("totalValue");
		catState.getProperties().add(totalValue);
		catState.getProperties().add(powerProperty);
		
		// Setup a summary equation in the category
		EquationDefinition eqDef = CalculationFactory.eINSTANCE.createEquationDefinition();
		TypeDefinitionResult tdResult = CalculationFactory.eINSTANCE.createTypeDefinitionResult();
		tdResult.setReference(value);
		
		SetFunction summary = CalculationFactory.eINSTANCE.createSetFunction();
		summary.setOperator("summary");
		summary.setTypeDefinition(value);
		
		eqDef.setResult(tdResult);
		eqDef.setExpression(summary);
		catState.getEquationDefinitions().add(eqDef);
		
		// Setup the data model:
		// root
		//	-- CA: State
		//	-- PI: totalValue
		//	-- Eq: totalValue = summary{Power.value}
		//	---- CA: Power
		//	------ PI: value
		StructuralElementInstance seiRoot = createStructuralElementInstance("System", se);
		CategoryAssignment caRoot = new CategoryInstantiator().generateInstance(catState, "power");
		seiRoot.getCategoryAssignments().add(caRoot);
		
		createResources();
		
		NumberLiteralResult resultExpression = (NumberLiteralResult) exprHelper.evaluate(caRoot.getEquationSection().getEquations().get(0).getExpression());
		assertEquals("Summary correct", 1, Double.valueOf(resultExpression.getNumberLiteral().getValue()), EPSILON);
	}
	
	@Test
	public void testGetReferencedDefinitionInput() {
		ReferencedInput ri = CalculationFactory.eINSTANCE.createReferencedInput();
		ReferencedDefinitionInput rdi = CalculationFactory.eINSTANCE.createReferencedDefinitionInput();
		
		EquationIntermediateResult eirDefinition = CalculationFactory.eINSTANCE.createEquationIntermediateResult();
		EquationIntermediateResult eirInstance = CalculationFactory.eINSTANCE.createEquationIntermediateResult();
		eirDefinition.setName("TEST");
		eirInstance.setName("TEST");
		
		ri.setDefinition(rdi);
		rdi.setReference(eirDefinition);
		
		assertNull("Cannot find referenced equation definition input", exprHelper.getReferencedDefinitionInput(ri));
		
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca.setEquationSection(CalculationFactory.eINSTANCE.createEquationSection());
		Equation equation = CalculationFactory.eINSTANCE.createEquation();
		equation.setResult(eirInstance);
		equation.setExpression(ri);
		ca.getEquationSection().getEquations().add(equation);
		
		assertEquals("Found referenced equation definition input", eirInstance, exprHelper.getReferencedDefinitionInput(ri));
	
		ComposedProperty cp = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		ComposedPropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		cpi.setType(cp);
		cpi.setTypeInstance(ca);
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cp.setType(cat);
		ca.setType(cat);
		ca.getPropertyInstances().add(cpi);
		rdi.setReference(cp);
		
		assertEquals("Found referenced equation definition input", ca, exprHelper.getReferencedDefinitionInput(ri));
	}
	
	@Test
	public void testGetEnum() {
		
		final String TEST_VALUE = "5.5";
		
		// Build a category with a enum property which also has some enum value definition
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		EnumProperty ep = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		EnumValueDefinition epv = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		epv.setValue(TEST_VALUE);
		ep.getValues().add(epv);
		cat.getProperties().add(ep);
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, "caEnum");
		EnumUnitPropertyInstance eupi = (EnumUnitPropertyInstance) ca.getPropertyInstances().get(0);
		
		// Build an expression with an enum instance
		ReferencedInput expression = CalculationFactory.eINSTANCE.createReferencedInput();
		expression.setReference(eupi);
		
		IExpressionResult result = exprHelper.evaluate(expression);
		
		assertTrue("Result is a number literal", result instanceof NumberLiteralResult);
		
		NumberLiteralResult nlr = (NumberLiteralResult) result;
		
		assertEquals("Result has correct value", "NaN", nlr.getNumberLiteral().getValue());
		
		eupi.setValue(epv);
		result = exprHelper.evaluate(expression);
		nlr = (NumberLiteralResult) result;
		
		assertEquals("Result has correct value", TEST_VALUE, nlr.getNumberLiteral().getValue());
	}
	
	@Test
	public void testAdvancedFunctions() {
		// Create a common root category
		Category cat = createCategory("Mass");
		IntProperty value = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		value.setName("value");
		cat.getProperties().add(value);
		
		// Now create CategoryAssigments for our masses category
		CategoryAssignment ca1 = new CategoryInstantiator().generateInstance(cat, "mass1");
		CategoryAssignmentHelper caHelper1 = new CategoryAssignmentHelper(ca1);
		ValuePropertyInstance vpiValue1 = (ValuePropertyInstance) caHelper1.getPropertyInstance("value");
		vpiValue1.setValue("10");
		
		CategoryAssignment ca2 = new CategoryInstantiator().generateInstance(cat, "mass2");
		CategoryAssignmentHelper caHelper2 = new CategoryAssignmentHelper(ca2);
		ValuePropertyInstance vpiValue2 = (ValuePropertyInstance) caHelper2.getPropertyInstance("value");
		vpiValue2.setValue("30");
		
		//Let's add this assignment to a structural element instance in the system composition tree
		// Now add the Category assignment to a StructuralElementInstance
		StructuralElementInstance sei1 = createStructuralElementInstance("ReactionWheel1", se);
		sei1.getCategoryAssignments().add(ca1);
		sei1.getCategoryAssignments().add(ca2);
		ca1.setEquationSection(CalculationFactory.eINSTANCE.createEquationSection());
		
		createResources();
		
		// --------------------------------------------------------------------------------------------------------------------------
		// setup the expression to calculate and assert the result
		//---------------------------------------------------------------------------------------------------------------------------
		List<Equation> equations1 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "result = summary(ReactionWheel1.mass1.value, ReactionWheel1.mass2.value);");
		Equation equation1 = equations1.get(0);
		ca1.getEquationSection().getEquations().add(equation1);
		NumberLiteralResult resultExpression1 = (NumberLiteralResult) exprHelper.evaluate(equation1.getExpression());
		assertEquals("Summary correct", 40, Double.valueOf(resultExpression1.getNumberLiteral().getValue()), EPSILON);
		
		createResources();
		
		List<Equation> equations2 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "result = mean(ReactionWheel1.mass1.value, ReactionWheel1.mass2.value);");
		Equation equation2 = equations2.get(0);
		ca1.getEquationSection().getEquations().add(equation2);
		NumberLiteralResult resultExpression2 = (NumberLiteralResult) exprHelper.evaluate(equation2.getExpression());
		assertEquals("Mean correct", 20, Double.valueOf(resultExpression2.getNumberLiteral().getValue()), EPSILON);
		
		createResources();
		
		List<Equation> equations3 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "result = max(ReactionWheel1.mass1.value, ReactionWheel1.mass2.value);");
		Equation equation3 = equations3.get(0);
		ca1.getEquationSection().getEquations().add(equation3);
		NumberLiteralResult resultExpression3 = (NumberLiteralResult) exprHelper.evaluate(equation3.getExpression());
		assertEquals("Max correct", 30, Double.valueOf(resultExpression3.getNumberLiteral().getValue()), EPSILON);
		
		createResources();
		
		List<Equation> equations4 = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, CALC_STRING + "result = min(ReactionWheel1.mass1.value, ReactionWheel1.mass2.value);");
		Equation equation4 = equations4.get(0);
		ca1.getEquationSection().getEquations().add(equation4);
		NumberLiteralResult resultExpression4 = (NumberLiteralResult) exprHelper.evaluate(equation4.getExpression());
		assertEquals("Min correct", 10, Double.valueOf(resultExpression4.getNumberLiteral().getValue()), EPSILON);
	}

	@Test
	public void testGetCompleteExpression() {
		// Let's setup some expressions, call the method and check for the correct Strings
		
		//simple NumberLiteral case
		AExpression testNumberLiteralExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "Dong = 45.56");
		String resultNumberLiteral = exprHelper.getCompleteExpression(testNumberLiteralExpression);
		assertEquals("getCompleteExpression returns correct Value", "45.56", resultNumberLiteral);

		//generic AOpRightExpression
		AExpression testCosExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "Ding = cos(45.76)");
		String resultCosLiteral = exprHelper.getCompleteExpression(testCosExpression);
		assertEquals("getCompleteExpression returns correct Value", "cos(45.76)", resultCosLiteral);
		
		//check function with brackets and whitespaces
		AExpression testSinExpression = ExpressionUtil.getFirstExpressionFrom(CALC_STRING + "Dong = sin ( 45.76 )");
		String resultSinLiteral = exprHelper.getCompleteExpression(testSinExpression);
		assertEquals("getCompleteExpression returns correct Value", "sin(45.76)", resultSinLiteral);
	}
	
	@Test
	public void testGetEquation() {
		Equation equation = CalculationFactory.eINSTANCE.createEquation();
		AExpression value = CalculationFactory.eINSTANCE.createValuePi();
		Function function = CalculationFactory.eINSTANCE.createFunction();
		function.setOperator(MathOperator.LOG);
		function.setRight(value);
		equation.setExpression(function);
		
		assertEquals("Correct equation obtained from sub expression", equation, exprHelper.getEquation(value));
	}
}
