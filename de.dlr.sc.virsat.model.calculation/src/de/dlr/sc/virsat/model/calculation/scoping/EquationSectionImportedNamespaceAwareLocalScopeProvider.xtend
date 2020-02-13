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

import java.lang.NullPointerException
import com.google.common.collect.Iterables
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput
import de.dlr.sc.virsat.model.dvlm.calculation.Import
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.resource.ISelectable
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider
import org.eclipse.xtext.scoping.impl.MultimapBasedSelectable
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper

/**
 * Implementation for the namespace aware importer. This overrides the get local names
 * which is combining the names of the current resource plus the names of the resource
 * where the current EquationSection is embedded in. This means the inputs form the
 * ContainerStructuralElement
 */
class EquationSectionImportedNamespaceAwareLocalScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {
	
	override protected getLocalElementsScope(IScope parent, EObject context, EReference reference) {
		super.getLocalElementsScope(parent, context, reference)
	}

		
	override ISelectable internalGetAllDescriptions(Resource resource) {
		var Iterable<EObject> combinedContents;
		
		/*
		 * Create an iterable over the xtextresource which is the one currently open in the editor
		 */
		val allContents = new Iterable<EObject>(){
			override iterator() {
				return EcoreUtil.getAllContents(resource, false);
			}
		};
		
		/*
		 * In case it is an EquationSection Resource we will now try to create
		 * an iterable over the resource of the StructuralElementInstance as well
		 * we create the iterator such it will hand back all ValuePropertyInstances
		 */
		if (resource instanceof EquationSectionXtextResource) {
			val equationSectionXtextResource = resource as EquationSectionXtextResource;
			val allVirSatContents = new Iterable<EObject>(){
			override iterator() {
				return VirSatEcoreUtil.getAllContentsOfType(equationSectionXtextResource.containerResource, IEquationInput, true);
				}
			};			
			
			/*
			 * Finally create a Combined scope that will hopefully remove the duplicates
			 */
			combinedContents = Iterables.concat(allContents, allVirSatContents);
		} else {
			combinedContents  = Iterables.concat(allContents);
		}

		val allDescriptions = Scopes.scopedElementsFor(combinedContents, qualifiedNameProvider);
		return new MultimapBasedSelectable(allDescriptions);
	}
	
	override String getImportedNamespace(EObject object) {
		if (object instanceof Import) {
			val import = object as Import;
			val importedNameSpace = import.importedNamespace;
			return try {
				importedNameSpace.fullQualifiedInstanceName + ActiveConceptHelper.FQID_DELIMITER + wildCard;
			} catch (NullPointerException e) {
			}
		}
		return super.getImportedNamespace(object);
	}
}