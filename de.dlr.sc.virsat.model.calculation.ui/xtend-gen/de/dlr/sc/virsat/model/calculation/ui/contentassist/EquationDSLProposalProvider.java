/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.calculation.ui.contentassist;

import de.dlr.sc.virsat.model.calculation.compute.extensions.AdvancedFunctionHelper;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource;
import de.dlr.sc.virsat.model.calculation.ui.contentassist.AbstractEquationDSLProposalProvider;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
@SuppressWarnings("all")
public class EquationDSLProposalProvider extends AbstractEquationDSLProposalProvider {
  @Override
  public void completeSetFunction_Operator(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeSetFunction_Operator(model, assignment, context, acceptor);
    final String[] setFunctionOps = AdvancedFunctionHelper.getSetFunctionOps();
    for (final String op : setFunctionOps) {
      acceptor.accept(this.createCompletionProposal(op, op, null, context));
    }
  }
  
  @Override
  public void completeSetFunction_FilterName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.completeSetFunction_FilterName(model, assignment, context, acceptor);
    final Resource resource = model.eResource();
    if ((resource instanceof EquationSectionXtextResource)) {
      final EquationSectionXtextResource equationSectionXtextResource = ((EquationSectionXtextResource)resource);
      ResourceSet _resourceSet = equationSectionXtextResource.getResourceSet();
      final EquationSectionVirSatAwareXtextResourceSet equationSectionContainerVirSatAwareResourceSet = ((EquationSectionVirSatAwareXtextResourceSet) _resourceSet);
      final List<ResourceSet> virSatResourceSet = equationSectionContainerVirSatAwareResourceSet.getReferencedResourceSets();
      final List<ATypeInstance> listTypeInstances = VirSatEcoreUtil.<ATypeInstance>getAllContentsOfTypefromResourceSets(virSatResourceSet, equationSectionXtextResource, ATypeInstance.class, true);
      for (final ATypeInstance aTypeInstance : listTypeInstances) {
        if ((aTypeInstance instanceof IName)) {
          final IName name = ((IName) aTypeInstance);
          acceptor.accept(this.createCompletionProposal(name.getName(), name.getName(), null, context));
        } else {
          if (((aTypeInstance.eContainer() != null) && (aTypeInstance.eContainer() instanceof IName))) {
            final EObject container = aTypeInstance.eContainer();
            final IName name_1 = ((IName) container);
            acceptor.accept(this.createCompletionProposal(name_1.getName(), name_1.getName(), null, context));
          }
        }
      }
    }
  }
}
