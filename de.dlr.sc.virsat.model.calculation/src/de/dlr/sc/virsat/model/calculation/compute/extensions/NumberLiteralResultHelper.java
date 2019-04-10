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

import org.apache.commons.math3.analysis.function.Acos;
import org.apache.commons.math3.analysis.function.Asin;
import org.apache.commons.math3.analysis.function.Atan;
import org.apache.commons.math3.analysis.function.Cos;
import org.apache.commons.math3.analysis.function.Divide;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.commons.math3.analysis.function.Log;
import org.apache.commons.math3.analysis.function.Log10;
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
		NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
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
		} else if (operator.equals(MathOperator.MINUS)) {
			Subtract subtract = new Subtract();
			doubleResult = subtract.value(values[0], values[1]);
		} else if (operator.equals(MathOperator.MULTIPLY)) {
			Product multiply = new Product();
			doubleResult = multiply.evaluate(values);
			resultBaseQuantityKinds = QudvUnitHelper.getInstance().mergeMaps(lhsBaseQuantityKinds, rhsBaseQuantityKinds, QudvCalcMethod.ADD);
		} else if (operator.equals(MathOperator.DIVIDE)) {
			Divide divide = new Divide();
			doubleResult = divide.value(values [0], values [1]);
			resultBaseQuantityKinds = QudvUnitHelper.getInstance().mergeMaps(lhsBaseQuantityKinds, rhsBaseQuantityKinds, QudvCalcMethod.SUBTRACT);
		} else if (operator.equals(MathOperator.POWER)) {
			Power power = new Power(values[1]);
			doubleResult = power.value(values[0]);
		} 
		
		resultLiteral.setValue(Double.toString(doubleResult));
		return new NumberLiteralResult(resultLiteral, resultBaseQuantityKinds);
	}

	/***
	 * Apply an operation on the base literal
	 * @param operator the operation
	 * @return the result of the calculation
	 */
	public NumberLiteralResult applyMathOperator(MathOperator operator) {
		NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();

		double doubleResult = 0;
		Map<AQuantityKind, Double> resultBaseQuantityKinds = new HashMap<>();
		resultBaseQuantityKinds.putAll(numberLiteralResult.getQuantityKinds());
		
		if (operator.equals(MathOperator.COS)) {
			Cos cos = new Cos();
			doubleResult = cos.value(this.getValue());
		} else if (operator.equals(MathOperator.SIN)) {
			Sin sin = new Sin();
			doubleResult = sin.value(this.getValue());
		} else if (operator.equals(MathOperator.TAN)) {
			Tan tan = new Tan();
			doubleResult = tan.value(this.getValue());
		} else if (operator.equals(MathOperator.ATAN)) {
			Atan atan = new Atan();
			doubleResult = atan.value(this.getValue());
		} else if (operator.equals(MathOperator.ACOS)) {
			if (this.getValue() < -1 || this.getValue() > 1) {
				doubleResult = Double.NaN;
			} else {
				Acos acos = new Acos();
				doubleResult = acos.value(this.getValue());
			}
		} else if (operator.equals(MathOperator.ASIN)) {
			if (this.getValue() < -1 || this.getValue() > 1) {
				doubleResult = Double.NaN;
			} else {
				Asin asin = new Asin();
				doubleResult = asin.value(this.getValue());	
			}
		} else if (operator.equals(MathOperator.SQRT)) {
			if (this.getValue() < 0) {
				doubleResult = Double.NaN;
			} else {
				Sqrt sqrt = new Sqrt();
				doubleResult = sqrt.value(this.getValue());
				resultBaseQuantityKinds.replaceAll((qk, factor) -> factor / 2);
			}
		} else if (operator.equals(MathOperator.LOG)) {
			if (this.getValue() > 0) {
				doubleResult = Math.log10(this.getValue());
			} else {
				doubleResult = Double.NaN;
			}
		} else if (operator.equals(MathOperator.LD)) {
			if (this.getValue() > 0) {
				Log10 log = new Log10();
				doubleResult = log.value(this.getValue()) / log.value(2);
			} else {
				doubleResult = Double.NaN;
			}
		} else if (operator.equals(MathOperator.LN)) {
			if (this.getValue() > 0) {
				Log log = new Log();
				doubleResult = log.value(this.getValue());
			} else {
				doubleResult = Double.NaN;
			}
		} else if (operator.equals(MathOperator.EXP)) {
			Exp exp = new Exp();
			doubleResult = exp.value(this.getValue());
		} else if (operator.equals(MathOperator.PLUS)) {
			doubleResult = this.getValue();
		} else if (operator.equals(MathOperator.MINUS)) {
			doubleResult = (-1.0) * this.getValue();
		}

		resultLiteral.setValue(Double.toString(doubleResult));
		return new NumberLiteralResult(resultLiteral, resultBaseQuantityKinds);	
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
