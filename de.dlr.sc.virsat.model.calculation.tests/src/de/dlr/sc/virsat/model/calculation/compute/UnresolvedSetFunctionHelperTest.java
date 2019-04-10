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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralResult;
import de.dlr.sc.virsat.model.calculation.compute.extensions.AdvancedFunctionHelper;
import de.dlr.sc.virsat.model.calculation.compute.extensions.UnresolvedExpressionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;

/**
 * This class tests the UnresolvedSetFunctionHelper
 * @author muel_s8
 *
 */
public class UnresolvedSetFunctionHelperTest {
	@Test
	public void applySetOperator() {
		List<IExpressionResult> expressionResults = new ArrayList<>();
		expressionResults.add(new UnresolvedExpressionResult());
		expressionResults.add(new NumberLiteralResult(CalculationFactory.eINSTANCE.createNumberLiteral()));
		
		NumberLiteralResult resultCount = (NumberLiteralResult) new AdvancedFunctionHelper("count").handleUnresolved(expressionResults);
		IExpressionResult resultSummary = new AdvancedFunctionHelper("summary").handleUnresolved(expressionResults);
		
		assertEquals("2", resultCount.getNumberLiteral().getValue());
		assertTrue(resultSummary instanceof UnresolvedExpressionResult);
	}

}
