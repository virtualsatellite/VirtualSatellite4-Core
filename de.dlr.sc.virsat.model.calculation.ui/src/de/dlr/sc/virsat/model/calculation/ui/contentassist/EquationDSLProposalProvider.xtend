/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.contentassist

import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance
import de.dlr.sc.virsat.model.dvlm.general.IName
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import de.dlr.sc.virsat.model.calculation.compute.extensions.AdvancedFunctionHelper

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
class EquationDSLProposalProvider extends AbstractEquationDSLProposalProvider {
	
	override completeSetFunction_Operator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeSetFunction_Operator(model, assignment, context, acceptor)
		
		val setFunctionOps = AdvancedFunctionHelper.getSetFunctionOps();
		
		// for each element, provide auto-completion by setting the label and id to the acceptor
		for (String op : setFunctionOps) {
			acceptor.accept(createCompletionProposal(op, op, null, context));
		}		
	}
	
	override completeSetFunction_FilterName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeSetFunction_FilterName(model, assignment, context, acceptor)

		// Use existing instances to provide sensible name propositions

		val resource = model.eResource;
		if (resource instanceof EquationSectionXtextResource) {
			val EquationSectionXtextResource equationSectionXtextResource = resource;
			val equationSectionContainerVirSatAwareResourceSet = equationSectionXtextResource.resourceSet as EquationSectionVirSatAwareXtextResourceSet;
			val virSatResourceSet = equationSectionContainerVirSatAwareResourceSet.referencedResourceSets;
			
			val listTypeInstances = VirSatEcoreUtil.getAllContentsOfTypefromResourceSets(virSatResourceSet, equationSectionXtextResource, ATypeInstance, true);
			for (ATypeInstance aTypeInstance : listTypeInstances) {
				if (aTypeInstance instanceof IName) {
					val name = aTypeInstance as IName;
					acceptor.accept(createCompletionProposal(name.name, name.name, null, context));
				} else if (aTypeInstance.eContainer !== null && aTypeInstance.eContainer instanceof IName) {
					val container = aTypeInstance.eContainer;
					val name = container as IName;
					acceptor.accept(createCompletionProposal(name.name, name.name, null, context));
				}
			}
		}
	}
	
}
