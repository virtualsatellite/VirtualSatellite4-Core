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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.commons.datastructures.DependencyTree;
import de.dlr.sc.virsat.model.calculation.compute.problem.EvaluationProblem;
import de.dlr.sc.virsat.model.calculation.compute.problem.OutOfDateProblem;
import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;

/**
 * Provides several helper functions for solving equations.
 * @author muel_s8
 *
 */

public class EquationHelper {
	
	public static final double EPS = 0.000000001d;
	
	private ExpressionHelper exprHelper;
	
	/**
	 * Public constructor
	 * @param exprHelper helper for determining dependencies of expressions
	 */
	
	public EquationHelper(ExpressionHelper exprHelper) {
		this.exprHelper = exprHelper;
	}
	
	/**
	 * Public constructor
	 */
	
	public EquationHelper() {
		this(new ExpressionHelper());
	}
	
	/**
	 * Creates a dependency tree from a list of equations. The result may have cycles if the equations
	 * have cyclic dependencies. It should be guaranteed that linearization of the tree
	 * after removing the cycles provides a correct evaluation order of the equations.
	 * @param equations the equations that will be used to build the tree
	 * @return the created dependency tree
	 */
	public DependencyTree<EObject> createDependencyTree(List<Equation> equations) {
		DependencyTree<EObject> tree = new DependencyTree<>();
		List<AExpression> dependencies = new ArrayList<>(); 
		
		// For all equations create dependencies of the form
		for (Equation equation : equations) {
			IEquationResult equationResult = equation.getResult();
			AExpression expression = equation.getExpression();
			
			if (equationResult instanceof EquationIntermediateResult) {
				// equationresult -> expression if equationresult is an intermediate result and
				tree.addDependencies(equationResult, new AExpression[] { expression });
			} else {
				// equationresult -> typeinstance -> expression if equationresult is a typeinstance result
				// this way we can map back from the linearization to the equations
				TypeInstanceResult tir = (TypeInstanceResult) equationResult;
				ATypeInstance ti = tir.getReference();
				tree.addDependencies(ti, new IEquationResult[] { equationResult });
				tree.addDependencies(equationResult, new AExpression[] { expression });
			}
			
			if (!dependencies.contains(expression)) {
				dependencies.add(expression);
			}
		}
		
		// Backtrack all dependencies until we only have dependencies on literals we can directly evaluate
		while (!dependencies.isEmpty()) {
			AExpression expression = dependencies.iterator().next();
			dependencies.remove(expression);
			
			List<EObject> localDependencies = exprHelper.getDependencies(expression);
			if (localDependencies.size() > 0) {
				tree.addDependencies(expression, localDependencies);
				
				localDependencies.forEach((dependency) -> {
					if (dependency instanceof AExpression && !dependencies.contains(dependency)) {
						dependencies.add((AExpression) dependency);
					}
				});
			}
		}
		
		return tree;
	}
	
	/**
	 * Creates a dependency tree from a list of equations. The result may have cycles if the equations
	 * have cyclic dependencies. It should be guaranteed that linearization of the tree
	 * after removing the cycles provides a correct evaluation order of the equations.
	 * @param equation the equation that will be used to build the tree
	 * @return the created dependency tree
	 */
	public DependencyTree<EObject> createDependencyTree(Equation equation) {
		return createDependencyTree(Arrays.asList(equation));
	}
	
	/**
	 * Evaluates a dependency tree according to its linearization
	 * @param tree the dependency tree to evaluate
	 * @return a list of problems that have occurred during the evaluation
	 */
	
	public List<EvaluationProblem> evaluate(DependencyTree<EObject> tree) {
		List<EObject> linear = tree.getLinearOrder();
		List<EvaluationProblem> equationProblems = new ArrayList<>();
		Map<EObject, IExpressionResult> mapExpressionToResult = new HashMap<>();
		
		// Evaluate the expressions according to the linearization
		for (EObject object : linear) {
			if (object instanceof AExpression) {
				exprHelper.evaluate((AExpression) object, mapExpressionToResult);
				
				// Check if its a referenced input with a definition attached to it
				// If so, update the referenced instance
				if (object instanceof ReferencedInput) {
					ReferencedInput refInput = (ReferencedInput) object;
					if (refInput.getDefinition() != null) {
						IEquationInput eqInput = exprHelper.getReferencedDefinitionInput(refInput);
						refInput.setReference(eqInput);
					}
				}
			} else if (object instanceof IEquationResult) {
				IEquationResult equationResult = (IEquationResult) object;

				Equation equation = (Equation) equationResult.eContainer();
				IExpressionResult result = exprHelper.evaluate(equation.getExpression(), mapExpressionToResult);

				boolean hasWritePermissionResult = RightsHelper.hasSystemUserWritePermission(equationResult);
				
				boolean isTypeInstance = equationResult instanceof TypeInstanceResult;
				
				// Assign the result according to the registrated setter
				if (isTypeInstance) {
					TypeInstanceResult instanceResult = (TypeInstanceResult) equationResult;
					ATypeInstance instance = (ATypeInstance) instanceResult.getReference();
					IResultSetter setter = exprHelper.getResultSetter(instance);
					
					mapExpressionToResult.put(instance, result);
					
					if (hasWritePermissionResult) {
						updateOverrideFlag(instance);
						equationProblems.addAll(setter.set(instance, result));
					} else if (setter != null) {
						boolean isChange = !exprHelper.performGet(instance).equals(result, EPS);
						if (isChange) {
							// Mark all the affected type instances as out of date
							for (ATypeInstance ti : setter.getAffectedTypeInstances(instance)) {
								equationProblems.add(new OutOfDateProblem(ti, result.toString()));
							}
						}
					}
				} 
				
				boolean hasWritePermissionEquation = RightsHelper.hasSystemUserWritePermission(equation);
				if (hasWritePermissionEquation) {
					if (equation.isIsInherited()) {
						equation.setOverride(true);
					}
					
					String resultText = result.toString();
					equation.setResultText(resultText);
				} 
			
				mapExpressionToResult.put(equation, result);
				mapExpressionToResult.put(equation.getExpression(), result);
			}
		}
		
		return equationProblems;
	}
	
	
	/**
	 * This updates the override flag of the passed instance if necessary
	 * @param instance the passed instance
	 */
	private void updateOverrideFlag(ATypeInstance instance) {
		if (instance.isIsInherited() && instance instanceof IOverridableInheritanceLink) {
			IOverridableInheritanceLink overrideable = (IOverridableInheritanceLink) instance;
			overrideable.setOverride(true);
		}
	}
	
	/**
	 * Evaluates a collection of equations using a dependency tree. Does no cycle analysis, etc.
	 * This method is primarily intended for the use in test cases.
	 * @param equations the equations to evaluate
	 * @return the dependency tree created internally
	 */
	public DependencyTree<EObject> evaluate(List<Equation> equations) {
		DependencyTree<EObject> dt = createDependencyTree(equations);
		evaluate(dt);
		return dt;
	}
}
