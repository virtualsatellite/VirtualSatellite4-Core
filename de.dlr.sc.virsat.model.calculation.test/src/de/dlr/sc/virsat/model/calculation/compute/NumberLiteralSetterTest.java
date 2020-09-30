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
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralResult;
import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralSetter;
import de.dlr.sc.virsat.model.calculation.compute.extensions.UnresolvedExpressionResult;
import de.dlr.sc.virsat.model.calculation.compute.problem.EvaluationProblem;
import de.dlr.sc.virsat.model.calculation.compute.problem.UnknownExpressionProblem;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;

/**
 * This class tests the methods of the NumberLiteralSetter class.
 * @author muel_s8
 *
 */

public class NumberLiteralSetterTest {
	
	@Test
	public void testSetDoubleExpressionResultOnFloatProperty() {
		NumberLiteralSetter nls = new NumberLiteralSetter();
		
		final String VALUE = "1.5";
		
		NumberLiteral nl = CalculationFactory.eINSTANCE.createNumberLiteral();
		nl.setValue(VALUE);
		NumberLiteralResult nlr = new NumberLiteralResult(nl);
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		FloatProperty fp = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		vpi.setType(fp);
		
		List<EvaluationProblem> problems = nls.set(vpi, nlr);
		
		assertTrue("No problems occurred during the set operation", problems.isEmpty());
		assertEquals("The value has been set correctly", VALUE, vpi.getValue());
	}
	
	@Test
	public void testSetDoubleExpressionResultOnIntProperty() {
		NumberLiteralSetter nls = new NumberLiteralSetter();
		
		final String VALUE = "1.5";
		final String EXPECTED_VALUE = "1";
		
		NumberLiteral nl = CalculationFactory.eINSTANCE.createNumberLiteral();
		nl.setValue(VALUE);
		NumberLiteralResult nlr = new NumberLiteralResult(nl);
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		IntProperty ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		vpi.setType(ip);
		
		List<EvaluationProblem> problems = nls.set(vpi, nlr);
		
		assertTrue("No problems occurred during the set operation", problems.isEmpty());
		assertEquals("The value has been set correctly", EXPECTED_VALUE, vpi.getValue());
	}
	
	@Test
	public void testSetUnknownExpressionResult() {
		NumberLiteralSetter nls = new NumberLiteralSetter();
		UnresolvedExpressionResult uer = new UnresolvedExpressionResult();
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		
		List<EvaluationProblem> problems = nls.set(vpi, uer);
		
		assertEquals("There was a problem when performing the set", 1, problems.size());
		
		EvaluationProblem eep = problems.get(0);
		
		assertTrue("The problem was that the setter cannot handle the given expression result", eep instanceof UnknownExpressionProblem);
	}
}
