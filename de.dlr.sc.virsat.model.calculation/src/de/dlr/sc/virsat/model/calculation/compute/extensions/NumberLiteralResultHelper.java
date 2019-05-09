/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute.extensions;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Acos;
import org.apache.commons.math3.analysis.function.Asin;
import org.apache.commons.math3.analysis.function.Atan;
import org.apache.commons.math3.analysis.function.Cos;
import org.apache.commons.math3.analysis.function.Divide;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.commons.math3.analysis.function.Identity;
import org.apache.commons.math3.analysis.function.Log;
import org.apache.commons.math3.analysis.function.Log10;
import org.apache.commons.math3.analysis.function.Minus;
import org.apache.commons.math3.analysis.function.Power;
import org.apache.commons.math3.analysis.function.Sin;
import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.analysis.function.Subtract;
import org.apache.commons.math3.analysis.function.Tan;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.MathOperator;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper.QudvCalcMethod;

/**
 * Helper class for applying operations to number literals
 * @author muel_s8
 *
 */

public class NumberLiteralResultHelper {

	private NumberLiteralResult numberLiteralResult;
	
	/**
	 * Create a new helper
	 * @param numberLiteralResult The number literal this helper will be used for
	 */
	
	public NumberLiteralResultHelper(NumberLiteralResult numberLiteralResult) {
		this.numberLiteralResult = numberLiteralResult;
	}
	
	/**
	 * Get the value of the literal, while respecting the case of being not a number
	 * @return value of the literal
	 */
	public double getValue() {
		try {
			double doubleValue = Double.parseDouble(numberLiteralResult.getNumberLiteral().getValue());
			return doubleValue;
		} catch (NullPointerException e) {
			return Double.NaN;
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
	}
	
	/**
	 * Apply 2 input operation to the number literal results together with a passed rhs literal result
	 * @param operator The operation
	 * @param rhsResult The right hand side result
	 * @return the result of the calculation
	 */
	public NumberLiteralResult applyMathOperator(MathOperator operator, NumberLiteralResult rhsResult) {
		NumberLiteralResultHelper rhsHelper = new NumberLiteralResultHelper(rhsResult);
		
		double doubleResult = 0;
		Map<AQuantityKind, Double> resultBaseQuantityKinds = numberLiteralResult.getQuantityKinds();
		
		double [] values = new double[2];
		values [0] = this.getValue();
		values [1] = rhsHelper.getValue();
		
		Map<AQuantityKind, Double> lhsBaseQuantityKinds = numberLiteralResult.getQuantityKinds();
		Map<AQuantityKind, Double> rhsBaseQuantityKinds = rhsResult.getQuantityKinds();
		
		if (operator.equals(MathOperator.PLUS)) {
			Sum sum = new Sum();
			doubleResult = sum.evaluate(values);
			if (!QudvUnitHelper.getInstance().haveSameQuantityKind(lhsBaseQuantityKinds, rhsBaseQuantityKinds)) {
				resultBaseQuantityKinds = QudvUnitHelper.getInstance().createUndefinedQKMap();
			}
		} else if (operator.equals(MathOperator.MINUS)) {
			Subtract subtract = new Subtract();
			doubleResult = subtract.value(values[0], values[1]);
			if (!QudvUnitHelper.getInstance().haveSameQuantityKind(lhsBaseQuantityKinds, rhsBaseQuantityKinds)) {
				resultBaseQuantityKinds = QudvUnitHelper.getInstance().createUndefinedQKMap();
			}
		} else if (operator.equals(MathOperator.MULTIPLY)) {
			Product multiply = new Product();
			doubleResult = multiply.evaluate(values);
			resultBaseQuantityKinds = QudvUnitHelper.getInstance().mergeMaps(lhsBaseQuantityKinds, rhsBaseQuantityKinds, QudvCalcMethod.ADD);
		} else if (operator.equals(MathOperator.DIVIDE)) {
			Divide divide = new Divide();
			doubleResult = divide.value(values[0], values[1]);
			resultBaseQuantityKinds = QudvUnitHelper.getInstance().mergeMaps(lhsBaseQuantityKinds, rhsBaseQuantityKinds, QudvCalcMethod.SUBTRACT);
		} else if (operator.equals(MathOperator.POWER)) {
			Power power = new Power(values[1]);
			doubleResult = power.value(values[0]);
		} 
		
		NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
		resultLiteral.setValue(Double.toString(doubleResult));
		return new NumberLiteralResult(resultLiteral, resultBaseQuantityKinds);
	}

	/***
	 * Apply an operation on the base literal
	 * @param operator the operation
	 * @return the result of the calculation
	 */
	public NumberLiteralResult applyMathOperator(MathOperator operator) {
		double doubleResult = 0;
		Map<AQuantityKind, Double> resultBaseQuantityKinds = new HashMap<>();
		resultBaseQuantityKinds.putAll(numberLiteralResult.getQuantityKinds());
		
		boolean inRange = inRange(operator);
		if (inRange) {
			UnivariateFunction function = getFunction(operator);
			doubleResult = function.value(this.getValue());
		} else {
			doubleResult = Double.NaN;
		}
		
		// Compute the updated quantity kinds
		switch (operator) {
			case SQRT:
				resultBaseQuantityKinds.replaceAll((qk, factor) -> factor / 2);
				break;
			default:
				break;
		}

		NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
		resultLiteral.setValue(Double.toString(doubleResult));
		return new NumberLiteralResult(resultLiteral, resultBaseQuantityKinds);	
	}
	
	/**
	 * Checks if the value is in the range of the operator
	 * @param operator the operator
	 * @return true iff the range is in the range of the operator
	 */
	private boolean inRange(MathOperator operator) {
		switch (operator) {
			case ACOS:
			case ASIN:
				return this.getValue() >= -1 && this.getValue() <= 1;
			case SQRT:
				return this.getValue() >= 0;
			case LOG:
			case LD:
			case LN:
				return this.getValue() > 0;
			default:
				return true;
		}
	}
	
	/**
	 * Converts a given math operator to the respective computation routine
	 * @param operator the math operator
	 * @return the computation to evaluate the operator
	 */
	private UnivariateFunction getFunction(MathOperator operator) {
		switch (operator) {
			case COS:
				return new Cos();
			case SIN:
				return new Sin();
			case TAN:
				return new Tan();
			case ATAN:
				return new Atan();
			case ACOS:
				return new Acos();
			case ASIN:
				return new Asin();
			case SQRT:
				return new Sqrt();
			case LOG:
				return new Log10();
			case LD:
				return new UnivariateFunction() {
					@Override
					public double value(double input) {
						Log10 log = new Log10();
						return log.value(input) / log.value(2);
					}
				};
			case LN:
				return new Log();
			case EXP:
				return new Exp();
			case PLUS:
				return new Identity();
			case MINUS:
				return new Minus();
			default:
				throw new RuntimeException("Cannot convert Math Operator: " +  operator + " to univariate function!");
		}
	}
	
	/**
	 * this method is used in case of an - before ()
	 * @param operator The operater to be applied
	 * @return The number literal after applying the operation (operator n1)
	 */
	public NumberLiteralResult applyMathOperator(String operator) {
		NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();

		double doubleResult = 0;
		Map<AQuantityKind, Double> resultBaseQuantityKinds = numberLiteralResult.getQuantityKinds();
		
		if (operator.equals("-")) {
			doubleResult = -1 * this.getValue();
		}

		resultLiteral.setValue(Double.toString(doubleResult));
		return new NumberLiteralResult(resultLiteral, resultBaseQuantityKinds);	
	}
}
