/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.ui.contentassist;

import de.dlr.sc.virsat.model.concept.ui.contentassist.AbstractConceptLanguageProposalProvider;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
@SuppressWarnings("all")
public class ConceptLanguageProposalProvider extends AbstractConceptLanguageProposalProvider {
  @Override
  public void completeFloatProperty_QuantityKindName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeFloatProperty_QuantityKindName(model, assignment, context, acceptor);
    this.addQuantityKindNamesToAutoCompletion(context, acceptor);
  }
  
  @Override
  public void completeIntProperty_QuantityKindName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeIntProperty_QuantityKindName(model, assignment, context, acceptor);
    this.addQuantityKindNamesToAutoCompletion(context, acceptor);
  }
  
  @Override
  public void completeEnumProperty_QuantityKindName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeEnumProperty_QuantityKindName(model, assignment, context, acceptor);
    this.addQuantityKindNamesToAutoCompletion(context, acceptor);
  }
  
  @Override
  public void completeComposedProperty_QuantityKindName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeComposedProperty_QuantityKindName(model, assignment, context, acceptor);
    this.addQuantityKindNamesToAutoCompletion(context, acceptor);
  }
  
  private void addQuantityKindNamesToAutoCompletion(final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    QudvUnitHelper helper = QudvUnitHelper.getInstance();
    SystemOfUnits sou = helper.initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
    EList<AQuantityKind> _quantityKind = sou.getSystemOfQuantities().get(0).getQuantityKind();
    for (final AQuantityKind qk : _quantityKind) {
      {
        String _name = qk.getName();
        String _plus = ("\"" + _name);
        String qkName = (_plus + "\"");
        acceptor.accept(this.createCompletionProposal(qkName, qkName, null, context));
      }
    }
  }
  
  @Override
  public void completeFloatProperty_UnitName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeFloatProperty_UnitName(model, assignment, context, acceptor);
    this.addQudvToAutoCompletion(model, context, acceptor);
  }
  
  @Override
  public void completeIntProperty_UnitName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeIntProperty_UnitName(model, assignment, context, acceptor);
    this.addQudvToAutoCompletion(model, context, acceptor);
  }
  
  @Override
  public void completeComposedProperty_UnitName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeComposedProperty_UnitName(model, assignment, context, acceptor);
    this.addQudvToAutoCompletion(model, context, acceptor);
  }
  
  @Override
  public void completeEnumProperty_UnitName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeEnumProperty_UnitName(model, assignment, context, acceptor);
    this.addQudvToAutoCompletion(model, context, acceptor);
  }
  
  private void addQudvToAutoCompletion(final EObject model, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    QudvUnitHelper helper = QudvUnitHelper.getInstance();
    SystemOfUnits sou = helper.initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
    String quantityKind = this.getQuantityKind(model);
    EList<AUnit> _unit = sou.getUnit();
    for (final AUnit unit : _unit) {
      if (((quantityKind == null) || unit.getQuantityKind().getName().equals(quantityKind))) {
        String _name = unit.getName();
        String _plus = ("\"" + _name);
        String unitName = (_plus + "\"");
        acceptor.accept(this.createCompletionProposal(unitName, unitName, null, context));
      }
    }
  }
  
  private String getQuantityKind(final EObject model) {
    if ((model instanceof AQudvTypeProperty)) {
      return ((AQudvTypeProperty) model).getQuantityKindName();
    } else {
      if ((model instanceof ComposedProperty)) {
        return ((ComposedProperty)model).getQuantityKindName();
      }
    }
    return null;
  }
}
