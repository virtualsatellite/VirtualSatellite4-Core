/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation

import com.google.inject.name.Names
import de.dlr.sc.virsat.model.calculation.formatting.EquationDSLFormatter
import de.dlr.sc.virsat.model.calculation.parser.antlr.EquationSectionNullUnloader
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource
import de.dlr.sc.virsat.model.calculation.scoping.EquationSectionGlobalScopeProvider
import de.dlr.sc.virsat.model.calculation.scoping.EquationSectionImportedNamespaceAwareLocalScopeProvider
import de.dlr.sc.virsat.model.calculation.scoping.EquationSectionNamesProvider
import de.dlr.sc.virsat.model.calculation.serializer.EquationTransientValueService
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider
import com.google.inject.Binder
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader
import de.dlr.sc.virsat.model.calculation.serializer.SafeEquationDSLSemanticSequencer

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class EquationDSLRuntimeModule extends AbstractEquationDSLRuntimeModule {

	override bindITransientValueService() {
		// Registering the TransientValueService that knows how to deal with the UUIDs
		// in the DVLM model and their serialization in the Xtext based language
		return EquationTransientValueService
	}
	
	override bindIQualifiedNameProvider() {
		return EquationSectionNamesProvider
	}
	
	override bindResourceSet() {
		return EquationSectionVirSatAwareXtextResourceSet
	}
	
	/**
	 * Binding the unloader which takes care of unloading Equations in the EquatioNSection
	 * when the partial parser is called. Actually this Unloader is not doing anything. This 
	 * behavior was implemented by Xtext before the Oxygen Release, but with Oxygen they changed
	 * their behavior to use their GenericUnloader. Still unloading our Equations within the Xtext 
	 * context creates issues, since reloading them is not an implemented standard behavior. In our Xtext 
	 * context we actually have a non persisted copy of the EquationSection in the resource, thus it cannot
	 * be reloaded. On a reload an equation would need to be copied again from the DVLM VirSat Model and
	 * would then need deeper considerations in cases such as other equations referencing the one that was 
	 * just copied. At the moment it looks like the unloading is just done for performance considerations.
	 * Hence we don't need it for the moment.
	 */
	def Class<? extends IReferableElementsUnloader> bindIReferableElementsUnloader() {
		return EquationSectionNullUnloader
	}
	
	override bindXtextResourceSet() {
		return EquationSectionVirSatAwareXtextResourceSet
	}
	
	override bindXtextResource() {
		return EquationSectionXtextResource
	}
	
	override bindIGlobalScopeProvider() {
		return EquationSectionGlobalScopeProvider
	}
	
	override bindIFormatter() {
		return EquationDSLFormatter
	}
	
	override bindISemanticSequencer() {
		return SafeEquationDSLSemanticSequencer
	}
	
	override configureIScopeProviderDelegate(Binder binder) {
		binder
			.bind(IScopeProvider)
			.annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE))
			.to(EquationSectionImportedNamespaceAwareLocalScopeProvider);
	}
}
