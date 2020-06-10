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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.calculation.Activator;
import de.dlr.sc.virsat.model.calculation.compute.extensions.UnresolvedExpressionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.ALiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.Function;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;
import de.dlr.sc.virsat.model.dvlm.calculation.MathOperator;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.Parenthesis;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.calculation.ValueE;
import de.dlr.sc.virsat.model.dvlm.calculation.ValuePi;
import de.dlr.sc.virsat.model.dvlm.calculation.util.CalculationSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.tree.IStructuralElementInstanceTreeTraverserMatcher;
import de.dlr.sc.virsat.model.dvlm.tree.TreeTraverser;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * Evaluates expressions and equations.
 */
public class ExpressionHelper {

	private List<IExpressionEvaluator> evaluators;
	private List<IInputGetter> inputGetters;
	private List<IResultSetter> resultSetters;

	/**
	 * ExpressionHelper with extensions registered by plugins and
	 * per default also for handling number literals.
	 */
	public ExpressionHelper() {
		evaluators = new ArrayList<>();
		inputGetters = new ArrayList<>();
		resultSetters = new ArrayList<>();

		processExtensions();
	}

	/**
	 * Processes extension registered by plugins.
	 */
	private void processExtensions() {
		// Register all plugin extensions

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			List<IConfigurationElement> setterProviders = Arrays.asList(registry.getConfigurationElementsFor("de.dlr.sc.virsat.model.edit.TypeInstanceSetterProvider"));
			Collections.sort(setterProviders, new Comparator<IConfigurationElement>() {
				@Override
				public int compare(IConfigurationElement c1, IConfigurationElement c2) {
					int intValue1 = Integer.valueOf(c1.getAttribute("priority"));
					int intValue2 = Integer.valueOf(c2.getAttribute("priority"));
					return Integer.compare(intValue1, intValue2);
				}
			});

			for (IConfigurationElement configElement : setterProviders) {
				try {
					Object extension = configElement.createExecutableExtension("class");
					if (extension instanceof IExpressionExtender) {
						IExpressionExtender extender = (IExpressionExtender) extension;
						resultSetters.addAll(extender.getTypeInstanceSetters());
						inputGetters.addAll(extender.getInputGetters());
						evaluators.addAll(extender.getExpressionEvaluators());
					}
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not resolve extension points for extending expressions", e));
				}
			}
		}
	}

	/**
	 * Apply the getters to the passed input
	 * @param input the input object onto which a getter will be applied
	 * @return result of an applicable getter call
	 */
	public IExpressionResult performGet(EObject input) {
		if (input == null) {
			return new UnresolvedExpressionResult();
		}

		for (IInputGetter inputGetter : inputGetters) {
			IExpressionResult result = inputGetter.get(input);
			if (result != null) {
				return result;
			}
		}

		return new UnresolvedExpressionResult(input);
	}

	/**
	 * Evaluates a single expression
	 * @param expression The expression to be evaluated
	 * @return The result of the evaluation
	 */
	public IExpressionResult evaluate(AExpression expression) {
		return evaluate(expression, new HashMap<>());
	}

	/**
	 * Evaluates a single expression
	 * @param object The object to be evaluated
	 * @param interimResultMap map of previously computed results
	 * @return The result of the evaluation
	 */
	public IExpressionResult evaluate(EObject object, Map<EObject, IExpressionResult> interimResultMap) {

		if (interimResultMap.containsKey(object)) {
			return interimResultMap.get(object);
		}

		CalculationSwitch<IExpressionResult> calcSwitch = new CalculationSwitch<IExpressionResult>() {

			@Override
			public IExpressionResult caseALiteral(ALiteral object) {
				return performGet(object);
			}

			@Override
			public IExpressionResult caseReferencedInput(ReferencedInput object) {
				IEquationInput equationInput;

				if (object.getDefinition() != null) {
					equationInput = getReferencedDefinitionInput(object);
					if (equationInput == null) {
						return new UnresolvedExpressionResult(object);
					}
				} else {
					equationInput = object.getReference();
				}

				if (interimResultMap.containsKey(equationInput)) {
					return interimResultMap.get(equationInput);
				}

				if (equationInput instanceof EquationIntermediateResult) {
					EquationIntermediateResult eir = (EquationIntermediateResult) equationInput;
					Equation eq = (Equation) eir.eContainer();
					AExpression expression = eq.getExpression();
					IExpressionResult calculatedResult = interimResultMap.get(expression);
					return calculatedResult;
				} else {
					return performGet(equationInput);
				}
			}

			@Override
			public IExpressionResult caseALeftOpRightExpression(ALeftOpRightExpression object) {
				AExpression exprLeft = object.getLeft();
				AExpression exprRight = object.getRight();

				IExpressionResult left = evaluate(exprLeft, interimResultMap);
				IExpressionResult right = evaluate(exprRight, interimResultMap);

				for (IExpressionEvaluator evaluator : evaluators) {
					IExpressionResult result = evaluator.caseALeftOpRightExpression(object, left, right);
					if (result != null) {
						return result;
					}
				}

				return new UnresolvedExpressionResult();
			}

			@Override
			public IExpressionResult caseAdvancedFunction(AdvancedFunction object) {
				List<IExpressionResult> results = new ArrayList<>();

				for (AExpression expression : object.getInputs()) {
					IExpressionResult result = evaluate(expression, interimResultMap);
					results.add(result);
				}

				// Apply the correct evaluator
				for (IExpressionEvaluator evaluator : evaluators) {
					IExpressionResult result = evaluator.caseAAdvancedFunction(object, results);
					if (result != null) {
						return result;
					}
				}

				// No evaluator was applicable
				return new UnresolvedExpressionResult();
			}

			@Override
			public IExpressionResult caseSetFunction(SetFunction object) {
				List<ATypeInstance> typeInstances = getSetFunctionInput(object);
				List<IExpressionResult> getResults = new ArrayList<>();

				for (ATypeInstance typeInstance : typeInstances) {
					if (interimResultMap.containsKey(typeInstance)) {
						getResults.add(interimResultMap.get(typeInstance));
					} else {
						getResults.add(performGet(typeInstance));
					}
				}

				// Apply the correct evaluator
				for (IExpressionEvaluator evaluator : evaluators) {
					IExpressionResult result = evaluator.caseAAdvancedFunction(object, getResults);
					if (result != null) {
						return result;
					}
				}

				// No evaluator was applicable
				return new UnresolvedExpressionResult();
			}

			// This case covers
				// Function
				// Paranthesis
			@Override
			public IExpressionResult caseAOpRightExpression(AOpRightExpression object) {
				AExpression exprRight = object.getRight();

				IExpressionResult right = evaluate(exprRight, interimResultMap);

				for (IExpressionEvaluator evaluator : evaluators) {
					IExpressionResult result = evaluator.caseAOpRightExpression(object, right);
					if (result != null) {
						return result;
					}
				}

				return new UnresolvedExpressionResult();
			}
		};

		IExpressionResult result = calcSwitch.doSwitch(object);
		interimResultMap.put(object, result);

		return result;
	}

	/**
	 * Gets a list of a all type instances that the passed set function would use as input
	 * @param setFunction the set function
	 * @return  a list of all type instances the set function will use for its computation and is thus dependent on
	 */
	private List<ATypeInstance> getSetFunctionInput(SetFunction setFunction) {
		ATypeDefinition typeDefinition = setFunction.getTypeDefinition();
		String filterName = setFunction.getFilterName();
		boolean filterForName = filterName != null && !filterName.equals("");

		// Grab the structural element instance the set function belongs to
		StructuralElementInstance sei = getStructuralElementInstance(setFunction);
		if (sei == null) {
			return new ArrayList<>();
		}

		// Store the inputs to the set function
		List<ATypeInstance> inputs = new ArrayList<>();

		// Find all child seis that also compute the set function
		Set<StructuralElementInstance> childrenWithSetFunction = getChildrenWithSetFunction(sei, setFunction, inputs);
		
		// Get the CA in which the current Set resides. All TypeInstances of this category shall not be
		// part of the inputs to be processed by this SetFunction. Accordingly they have to be removed
		// from the Set's inputs
		CategoryAssignment currentCa = VirSatEcoreUtil.getEContainerOfClass(setFunction, CategoryAssignment.class);
		
		// Find all applicable TypeInstances that are directly or indirectly contained by the sei
		TreeTraverser<StructuralElementInstance> treeTraverser = new TreeTraverser<>();
		treeTraverser.traverse(sei, new IStructuralElementInstanceTreeTraverserMatcher() {
			
			@Override
			public boolean isMatching(StructuralElementInstance treeSei) {
				boolean isMatching = false;
				// Get all nested TypeInstances to the current treeSei and see if one
				// is matching to the definition of what is referenced by the SET function
				List<CategoryAssignment> currentCas = new LinkedList<>(treeSei.getCategoryAssignments());
			
				Collection<ATypeInstance> typeInstances = VirSatEcoreUtil.getAllContentsOfType(currentCas, ATypeInstance.class, true);
				typeInstances.addAll(currentCas);
				
				// Remove the local type instances. This prevents the creation of cyclic input for the set function
				// i.e. it prevents that expressions such as mass = summary{mass} create a cycle by including the local
				// mass property in the inputs of the summary set function
				typeInstances.remove(currentCa);
				typeInstances.removeAll(currentCa.getPropertyInstances());
				
				// Loop over all identified ATypeInstances
				for (ATypeInstance aTypeInstance : typeInstances) {
					boolean correctType = aTypeInstance.getType() == typeDefinition;
					boolean notComputedByChild = !childrenWithSetFunction.contains(treeSei);
					if (correctType && notComputedByChild) {
						// For composed property instances we have to get the category assignment
						// to be able to work with them, thus try to decompose it
						ATypeInstance decomposedTypeInstance = aTypeInstance;
						if (decomposedTypeInstance instanceof ComposedPropertyInstance) {
							decomposedTypeInstance = ((ComposedPropertyInstance) aTypeInstance).getTypeInstance();
						}

						// In case a name filter is set for the Set function make sure to apply it.
						// If all properties are correct, remember the decomposed Type Instance as an input
						// remember that we definitely had a match and continue with all the other items and check
						// if they are potential inputs. Saving the match is important, so that the tree traverser
						// can count the levels of nesting and levels of matches correctly						
						boolean correctName = !filterForName || hasCorrectName(decomposedTypeInstance, filterName);
						if (correctName) {
							inputs.add(decomposedTypeInstance);
							isMatching = true;
						}
					}
				}
				
				// return the result to decide if the current node will be counted
				// for the matching levels or not
				return isMatching;
			}
			
			@Override
			public boolean continueTraverseChildren(StructuralElementInstance treeNode, boolean isMatching, int processedLevel, int matchedLevel) {
				// Compare the depth of found matches with the depth set by the set function
				int targetDepth = setFunction.getDepth();
				boolean isDepthInfinite = targetDepth == AAdvancedFunctionOp.DEPTH_INFINITE;
				boolean isDepthNotReached =  matchedLevel <= targetDepth; 
				return isDepthInfinite || isDepthNotReached;
			}

			@Override
			public void processMatch(StructuralElementInstance treeNode, StructuralElementInstance matchingParent) {
				// Processing is done in the isMatching method
			}
		});

		return inputs;
	}

	/**
	 * Gets child seis that compute the set function for them and
	 * their children. That sei and the corresponding sub tree can then ignored in
	 * the further search for inputs.
	 * @param sei the sei of the set function we want the inputs for
	 * @param setFunction the set function
	 * @param inputs the list of current inputs. 
	 * Instances that compute already set function for a child are added here.
	 * @return all child seis that compute the same set function for their sub trees
	 */
	private Set<StructuralElementInstance> getChildrenWithSetFunction(StructuralElementInstance sei, SetFunction setFunction, List<ATypeInstance> inputs) {
		Set<StructuralElementInstance> childrenWithSetFunction = new HashSet<>();

		if (setFunction.getDepth() == AAdvancedFunctionOp.DEPTH_INFINITE) {
			List<StructuralElementInstance> children = sei.getChildren();
			for (StructuralElementInstance child : children) {
				VirSatEcoreUtil.getAllContentsOfType(child.eResource(), Equation.class, true).forEachRemaining(eObject -> {
					Equation equation = (Equation) eObject;

					// Check if the expression computes the same set function but for the child sei
					AExpression expression = equation.getExpression();
					if (expression instanceof SetFunction && !childrenWithSetFunction.contains(getStructuralElementInstance(expression))) {
						boolean equals = equalSetFunctions(setFunction, (SetFunction) expression);
						boolean isTypeInstanceResult = equation.getResult() instanceof TypeInstanceResult;
						if (equals && isTypeInstanceResult) {
							// Remember the set function that handles the sei & its children
							TypeInstanceResult result = (TypeInstanceResult) equation.getResult();
							inputs.add(result.getReference());
							
							// Remember that all inputs from this sei and its children are covered
							childrenWithSetFunction.add(child);
							childrenWithSetFunction.addAll(child.getDeepChildren());
						}
					}
				});
			}
		}

		return childrenWithSetFunction;
	}

	/**
	 * Checks if the given input has a correct name. This can either be the typeinstance itself,
	 * or the container type instance if the typeinstance itself is not a named object
	 * @param aTypeInstance the typeinstance to check
	 * @param filterName the name to filter for
	 * @return true iff the name is correct
	 */
	private boolean hasCorrectName(ATypeInstance aTypeInstance, String filterName) {
		if (aTypeInstance instanceof IName) {
			// If we have a named type instance we need to check for its name
			IName name = (IName) aTypeInstance;
			return name.getName().equals(filterName);
		} else if (aTypeInstance.eContainer() != null && aTypeInstance.eContainer() instanceof IName) {
			// If the type instance itself doesnt have a name, we have to check its container
			IName name = (IName) aTypeInstance.eContainer();
			return name.getName().equals(filterName);
		}

		// This is not a named object, yet we require a specific name, therefore the name is not correct
		return false;
	}

	/**
	 * Compares two set functions and whether they have the same settings. That is, this comparison
	 * ignores things such as container, uuid, etc. and only considers the parameters the user
	 * inputs into xtext.
	 * @param sf1 one set function
	 * @param sf2 other set function
	 * @return true iff the two set functions have the same settings
	 */
	private static boolean equalSetFunctions(SetFunction sf1, SetFunction sf2) {
		boolean sameOperator = Objects.equals(sf1.getOperator(), sf2.getOperator());
		boolean sameFilter = Objects.equals(sf1.getFilterName(), sf2.getFilterName());
		boolean sameTypeDefinition = Objects.equals(sf1.getTypeDefinition(), sf2.getTypeDefinition());
		return sameOperator && sameFilter && sameTypeDefinition;
	}

	/**
	 * Gets the structural element instance containing this object
	 * @param eObject the object contained by some sei
	 * @return the sei containing this object, null otherwise
	 */
	public StructuralElementInstance getStructuralElementInstance(EObject eObject) {
		EObject container = eObject.eContainer();
		while (!(container instanceof StructuralElementInstance)) {
			if (container == null) {
				return null;
			}
			container = container.eContainer();
		}

		return (StructuralElementInstance) container;
	}

	/**
	 * Resolve a referenced definition input to an instance
	 * @param object the reference to the definition
	 * @return the resolved equation input if it exists, null otherwise
	 */
	public IEquationInput getReferencedDefinitionInput(ReferencedInput object) {
		IEquationDefinitionInput reference = object.getDefinition().getReference();

		// Backtrack as follows:
		// First check if there is an instance of the referenced typeDefinition locally in the category assignment
		// Then go up to the local StructuralElementInstance and check if it has a ca with the reference
		// If not, go to the parent sei and repeat until we are at the root

		EObject container = object.eContainer();
		while (container != null) {

			// Either check the category assignment itself (if container is one) or check the category assignemnts
			// attached to the structural element instance (if container is one)
			List<CategoryAssignment> caList = new ArrayList<CategoryAssignment>();
			if (container instanceof CategoryAssignment) {
				caList.add((CategoryAssignment) container);
			} else if (container instanceof StructuralElementInstance) {
				StructuralElementInstance sei = (StructuralElementInstance) container;
				caList.addAll(sei.getCategoryAssignments());
			}

			for (CategoryAssignment ca : caList) {
				if (reference instanceof EquationIntermediateResult) {
					EquationIntermediateResult interResult = (EquationIntermediateResult) reference;
					// Check if the ca has the referenced equation intermediate result, if so return it
					List<Equation> equations = ca.getEquationSection().getEquations();
					for (Equation equation : equations) {
						IEquationResult result = equation.getResult();
						if (result instanceof EquationIntermediateResult) {
							EquationIntermediateResult other = (EquationIntermediateResult) result;
							if (other.getName().equals(interResult.getName())) {
								return other;
							}
						}
					}
				} else if (reference instanceof AProperty) {
					// Check if the ca has the referenced property, if so return it
					AProperty property = (AProperty) reference;
					CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(ca);
					APropertyInstance instance = caHelper.getPropertyInstance(property);
					if (instance != null) {
						if (instance instanceof ComposedPropertyInstance) {
							return ((ComposedPropertyInstance) instance).getTypeInstance();
						} else {
							return (IEquationInput) instance;
						}
					}
				}
			}

			container = container.eContainer();
		}

		return null;
	}

	/**
	 * Calculates the dependencies that are required to calculate this expression (e.g. sub expressions,
	 * references, etc)
	 * @param expression the expression that is attempted to be evaluated
	 * @return the necessary dependencies
	 */
	public List<EObject> getDependencies(AExpression expression) {

		List<EObject> dependencies = new ArrayList<>();

		CalculationSwitch<List<EObject>> calcSwitch = new CalculationSwitch<List<EObject>>() {
			@Override
			public List<EObject> caseReferencedInput(ReferencedInput object) {
				if (object.getDefinition() != null) {
					IEquationInput input = getReferencedDefinitionInput(object);
					if (input != null) {
						dependencies.add(input);
					}
				} else {
					dependencies.add(object.getReference());
				}

				return dependencies;
			}

			@Override
			public List<EObject> caseALeftOpRightExpression(ALeftOpRightExpression object) {
				AExpression exprLeft = object.getLeft();
				AExpression exprRight = object.getRight();

				dependencies.add(exprLeft);
				dependencies.add(exprRight);

				return dependencies;
			}

			@Override
			public List<EObject> caseAdvancedFunction(AdvancedFunction object) {
				dependencies.addAll(object.getInputs());
				return dependencies;
			}

			@Override
			public List<EObject> caseSetFunction(SetFunction object) {
				dependencies.addAll(getSetFunctionInput(object));
				return dependencies;
			}

			// This case covers
				// Function
				// Paranthesis
			@Override
			public List<EObject> caseAOpRightExpression(AOpRightExpression object) {
				AExpression exprRight = object.getRight();
				dependencies.add(exprRight);

				return dependencies;
			}
		};

		calcSwitch.doSwitch(expression);

		return dependencies;
	}

	/**
	 * Gets the setter applicable for a certain instance
	 * @param instance The instance which to which we want to assign a result
	 * @return The setter applicable for this instance. null if no setter is applicable
	 */

	public IResultSetter getResultSetter(ATypeInstance instance) {
		for (IResultSetter setter : resultSetters) {
			if (setter.isApplicableFor(instance)) {
				return setter;
			}
		}
		return null;
	}

	/**
	 * This method concatenates a String representing the complete expression.
	 * It recursively evaluates the nested expression and assembles a complete statement which can be displayed
	 * @param ex the expression to switch on
	 * @return String the math expression as String
	 */
	public String getCompleteExpression(AExpression ex) {
		CalculationSwitch<String> calcSwitch = new CalculationSwitch<String>() {

			@Override
			public String caseNumberLiteral(NumberLiteral object) {
				return object.getValue();
			}

			@Override
			public String caseValuePi(ValuePi object) {
				return "pi";
			}

			@Override
			public String caseValueE(ValueE object) {
				return "e";
			}

			@Override
			public String caseALeftOpRightExpression(ALeftOpRightExpression ex) {
				AExpression left = ex.getLeft();
				AExpression right = ex.getRight();
				MathOperator op = ex.getOperator();
				String rtnString = getCompleteExpression(left).concat(" ").concat(op.getLiteral().concat(" ")).concat(getCompleteExpression(right));
				return rtnString;
			}

			@Override
			public String caseAOpRightExpression(AOpRightExpression ex) {
				AExpression right = ex.getRight();
				MathOperator op = ex.getOperator();
				String rtnString = op.getLiteral().concat(" ").concat(getCompleteExpression(right));
				return rtnString;
			}

			@Override
			public String caseReferencedInput(ReferencedInput ex) {
				IExpressionResult result = evaluate(ex);
				return result.toString();
			}

			@Override
			public String caseFunction(Function ex) {
				AExpression right = ex.getRight();
				MathOperator op = ex.getOperator();
				String rtnString = op.getLiteral().concat("(").concat(getCompleteExpression(right)).concat(")");
				return rtnString;
			}

			@Override
			public String caseParenthesis(Parenthesis ex) {
				AExpression right = ex.getRight();
				String rtnString = "(".concat(getCompleteExpression(right)).concat(")");
				return rtnString;
			}

			@Override
			public String caseSetFunction(SetFunction ex) {
				ATypeDefinition typeDefinition = ex.getTypeDefinition();
				String op = ex.getOperator();
				String filterNameString = "";
				String filterName = ex.getFilterName();
				if (filterName != null && !filterName.equals("")) {
					filterNameString = ", ".concat(filterName);
				}
				String rtnString = op.concat("(").concat(typeDefinition.getName()).concat(filterNameString).concat(")");
				return rtnString;
			}

			@Override
			public String caseAdvancedFunction(AdvancedFunction ex) {

				StringBuilder sb = new StringBuilder();

				String op = ex.getOperator();
				sb.append(op);
				sb.append("(");

				for (int i = 0; i < ex.getInputs().size(); ++i) {
					String subExpression = getCompleteExpression(ex.getInputs().get(i));
					sb.append(subExpression);
					if (i != ex.getInputs().size() - 1) {
						sb.append(", ");
					}
				}

				sb.append(")");
				return sb.toString();
			}

		};

		return calcSwitch.doSwitch(ex);
	}

	/**
	 * Get the equation associated with this expression
	 * @param expression the expression
	 * @return the equation associated with this expression
	 */
	public Equation getEquation(AExpression expression) {
		EObject container = expression.eContainer();
		while (!(container instanceof Equation)) {
			container = container.eContainer();
		}
		return (Equation) container;
	}
}
