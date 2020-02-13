/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.calculation.ui.labeling;

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.calculation.compute.ExpressionHelper;
import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.AdditionAndSubtraction;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;
import de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.Parenthesis;
import de.dlr.sc.virsat.model.dvlm.calculation.PowerFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.calculation.ValueE;
import de.dlr.sc.virsat.model.dvlm.calculation.ValuePi;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
@SuppressWarnings("all")
public class EquationDSLLabelProvider extends DefaultEObjectLabelProvider {
  @Inject
  public EquationDSLLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public EquationDSLLabelProvider() {
  }
  
  private final ExpressionHelper expressionHelper = new ExpressionHelper();
  
  public String text(final AdditionAndSubtraction ele) {
    String _literal = ele.getOperator().getLiteral();
    String _plus = (_literal + " (= ");
    IExpressionResult _evaluate = this.expressionHelper.evaluate(ele);
    String _plus_1 = (_plus + _evaluate);
    return (_plus_1 + ")");
  }
  
  public String text(final MultiplicationAndDivision ele) {
    String _literal = ele.getOperator().getLiteral();
    String _plus = (_literal + " (= ");
    IExpressionResult _evaluate = this.expressionHelper.evaluate(ele);
    String _plus_1 = (_plus + _evaluate);
    return (_plus_1 + ")");
  }
  
  public String text(final PowerFunction ele) {
    return ele.getOperator().getLiteral();
  }
  
  public String text(final NumberLiteral ele) {
    return ele.getValue();
  }
  
  public String text(final ValuePi ele) {
    return "pi";
  }
  
  public String text(final ValueE ele) {
    return "e";
  }
  
  public String text(final ReferencedInput ele) {
    return this.expressionHelper.getCompleteExpression(ele);
  }
  
  public String text(final SetFunction ele) {
    String _completeExpression = this.expressionHelper.getCompleteExpression(ele);
    String _plus = (_completeExpression + "(=");
    IExpressionResult _evaluate = this.expressionHelper.evaluate(ele);
    String _plus_1 = (_plus + _evaluate);
    return (_plus_1 + ")");
  }
  
  public String text(final Parenthesis ele) {
    return "(...)";
  }
  
  public String text(final IEquationResult ele) {
    if ((ele instanceof EquationIntermediateResult)) {
      return ((EquationIntermediateResult)ele).getName();
    } else {
      if ((ele instanceof TypeInstanceResult)) {
        final ATypeInstance ref = ((TypeInstanceResult)ele).getReference();
        if ((ref instanceof APropertyInstance)) {
          return ((APropertyInstance)ref).getType().getName();
        } else {
          return this.convertToString(this.getStyledText(ref));
        }
      }
    }
    return null;
  }
  
  public String text(final Equation ele) {
    return this.text(ele.getResult()).concat(" = ").concat(this.expressionHelper.getCompleteExpression(ele.getExpression()));
  }
}
