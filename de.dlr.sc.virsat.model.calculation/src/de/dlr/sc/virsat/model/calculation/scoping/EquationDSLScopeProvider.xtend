/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.scoping

import com.google.inject.Inject
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource
import de.dlr.sc.virsat.model.dvlm.calculation.Import
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.dvlm.general.IInstance
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider

/**
 * This class creates the correct scopes for the References into the DVLM data model.
 * This class is important for the cross linking in the editor to provide meaningful names.
 * but it is also important for linking the names back to the actual objects once the 
 * textual representation of the equations is deserialized back into the DVLM model.
 * This class makes use of the EquationSeciton Resource and ResourceSet which allow 
 * the cross linking across other ResourceSets.
 */
class EquationDSLScopeProvider extends AbstractDeclarativeScopeProvider {

	@Inject
	var IQualifiedNameProvider qualifiedNameProvider;	

	override getScope(EObject context, EReference reference) {
		if (context instanceof Import) {
			val resource = context.eResource;
			if (resource instanceof EquationSectionXtextResource) {
				val EquationSectionXtextResource equationSectionXtextResource = resource;
				val equationSectionContainerVirSatAwareResourceSet = equationSectionXtextResource.resourceSet as EquationSectionVirSatAwareXtextResourceSet;
				val virSatResourceSet = equationSectionContainerVirSatAwareResourceSet.referencedResourceSets;
				
				val listOfLocalValueProeprtyInstances = VirSatEcoreUtil.getAllContentsOfTypefromResourceSets(virSatResourceSet, equationSectionXtextResource, IInstance, true);
				return Scopes.scopeFor(listOfLocalValueProeprtyInstances, qualifiedNameProvider, IScope.NULLSCOPE);
			}
		} else if (context instanceof SetFunction) {
			val resource = context.eResource;
			if (resource instanceof EquationSectionXtextResource) {
				val EquationSectionXtextResource equationSectionXtextResource = resource;
				val equationSectionContainerVirSatAwareResourceSet = equationSectionXtextResource.resourceSet as EquationSectionVirSatAwareXtextResourceSet;
				val virSatResourceSet = equationSectionContainerVirSatAwareResourceSet.referencedResourceSets;
				
				val listOfTypeDefinitions = VirSatEcoreUtil.getAllContentsOfTypefromResourceSets(virSatResourceSet, equationSectionXtextResource, ATypeDefinition, true);
				val scopes = Scopes.scopeFor(listOfTypeDefinitions, qualifiedNameProvider, IScope.NULLSCOPE);
				
				return scopes;
			}
		} 
		
		val scopes = super.getScope(context, reference);
		return scopes;
	}
	
}
