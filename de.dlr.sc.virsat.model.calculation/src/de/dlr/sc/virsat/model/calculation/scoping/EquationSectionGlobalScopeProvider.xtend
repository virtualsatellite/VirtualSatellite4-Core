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

import com.google.common.base.Predicate
import com.google.inject.Inject
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.scoping.impl.DefaultGlobalScopeProvider
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource
import de.dlr.sc.virsat.model.dvlm.general.IInstance
import org.eclipse.emf.ecore.EObject

class EquationSectionGlobalScopeProvider extends DefaultGlobalScopeProvider {

	@Inject
	var IQualifiedNameProvider qualifiedNameProvider;	

	override getScope(Resource resource, EReference reference, Predicate<IEObjectDescription> filter) {

		val referenceType = reference.EReferenceType;
		
		val eObjectClazz = referenceType.instanceClass;
		if (eObjectClazz.isAssignableFrom(IEquationInput) || eObjectClazz.isAssignableFrom(IInstance)) {
			if (resource instanceof EquationSectionXtextResource) {
				val EquationSectionXtextResource equationSectionXtextResource = resource;
				val equationSectionVirSatResource = equationSectionXtextResource.containerResource;
				val equationSectionXtextResourceSet = resource.resourceSet as EquationSectionVirSatAwareXtextResourceSet;
				val virSatResourceSets = equationSectionXtextResourceSet.referencedResourceSets
				
	            val listOfGlobalScopes = VirSatEcoreUtil.getAllContentsOfTypefromResourceSets(virSatResourceSets, equationSectionVirSatResource, referenceType.instanceClass as Class<EObject>, true);
				val scopes = Scopes.scopeFor(listOfGlobalScopes, qualifiedNameProvider, IScope.NULLSCOPE);
				return scopes;
			}
		}
		
		val scope = super.getScope(resource, reference, filter);
		return scope
	}
}