/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.ui.contentassist

import de.dlr.sc.virsat.model.concept.ui.contentassist.AbstractConceptLanguageProposalProvider
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
class ConceptLanguageProposalProvider extends AbstractConceptLanguageProposalProvider {
	
	override completeFloatProperty_QuantityKindName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeFloatProperty_QuantityKindName(model, assignment, context, acceptor)
		addQuantityKindNamesToAutoCompletion(context, acceptor)
	}
	
	override completeIntProperty_QuantityKindName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeIntProperty_QuantityKindName(model, assignment, context, acceptor)
		addQuantityKindNamesToAutoCompletion(context, acceptor)
	}
	
	override completeEnumProperty_QuantityKindName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeEnumProperty_QuantityKindName(model, assignment, context, acceptor)
		addQuantityKindNamesToAutoCompletion(context, acceptor)
	}
	
	override completeComposedProperty_QuantityKindName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeComposedProperty_QuantityKindName(model, assignment, context, acceptor)
		addQuantityKindNamesToAutoCompletion(context, acceptor)
	}
	
	private def addQuantityKindNamesToAutoCompletion(ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		var helper = QudvUnitHelper.instance
		var sou = helper.initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de")
		for (qk : sou.systemOfQuantities.get(0).quantityKind) {
			var qkName = "\"" + qk.name + "\""
			acceptor.accept(createCompletionProposal(qkName, qkName, null, context));
		}
	}
	
	override completeFloatProperty_UnitName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeFloatProperty_UnitName(model, assignment, context, acceptor)
		addQudvToAutoCompletion(model, context, acceptor)
	}
	
	override completeIntProperty_UnitName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeIntProperty_UnitName(model, assignment, context, acceptor)
		addQudvToAutoCompletion(model, context, acceptor)
	}
	
	override completeComposedProperty_UnitName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeComposedProperty_UnitName(model, assignment, context, acceptor)
		addQudvToAutoCompletion(model, context, acceptor)
	}
	
	override completeEnumProperty_UnitName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeEnumProperty_UnitName(model, assignment, context, acceptor)
		addQudvToAutoCompletion(model, context, acceptor)
	}
	
	private def addQudvToAutoCompletion(EObject model, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		var helper = QudvUnitHelper.instance
		var sou = helper.initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de")
		var quantityKind = getQuantityKind(model);
	
		for (unit : sou.unit) {
			if (quantityKind === null || unit.quantityKind.getName().equals(quantityKind)) {
				var unitName = "\"" + unit.name + "\""
				acceptor.accept(createCompletionProposal(unitName, unitName, null, context));
			}
		}
	}
	
	private def getQuantityKind(EObject model) {
		if (model instanceof AQudvTypeProperty) {
			return (model as AQudvTypeProperty).quantityKindName;
		} else if (model instanceof ComposedProperty) {
			return model.quantityKindName;
		}
		
		return null;
	}
}
