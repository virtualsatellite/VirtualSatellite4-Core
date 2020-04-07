/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.scoping

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.scoping.impl.ImportNormalizer
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider

/**
 * Class that implements the scoping of the concept language. Base class divers from
 * generated stub class to enable implicit imports for core language features 
 */
class ConceptLanguageScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {

	override getScope(EObject context, EReference reference) {
		super.getScope(context, reference)
	}
	
	/**
	 * Method that adds a reference to the concept language core concept as implicit import
	 */
	override List<ImportNormalizer> getImplicitImports(boolean ignoreCase) {
		var qualifiedNamespace = (new IQualifiedNameConverter.DefaultImpl).toQualifiedName(
			de.dlr.sc.virsat.model.ext.core.Activator.pluginId)
		var coreNamespace = new ImportNormalizer(qualifiedNamespace, true, true);
		var implicitImports = new ArrayList<ImportNormalizer>
		implicitImports.add(coreNamespace)
		return implicitImports;
	}

}
