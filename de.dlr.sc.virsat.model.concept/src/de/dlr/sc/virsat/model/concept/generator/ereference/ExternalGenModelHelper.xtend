/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.ereference

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet

class ExternalGenModelHelper {
	
	/**
	 * Get the full qualified java class name of property
	 */
	def getEObjectClass(EReferenceProperty property) {
		var String typeClass = null
		var genPackage = resolveGenPackage(property)
		
		//If package could be found get class name
		if (genPackage !== null) {
			typeClass = ""
			if (genPackage.basePackage !== null) {
				typeClass = genPackage.basePackage + "."
			}
			typeClass += genPackage.NSName
			typeClass += "." + property.referenceType.name
		}
		return typeClass
	}
	
	/**
	 * Try to resolve the GenPackage of a given EReferenceProperty
	 */
	def resolveGenPackage(EReferenceProperty property) {
		val eClassPackage = property.referenceType.eContainer as EPackage
		val concept = property.eResource.contents.get(0) as Concept
		val resource = property.eResource
		val nsURI = eClassPackage.nsURI
		var GenPackage genPackage = null
		
		//Try to resolve package from GenModel import
		for (eImport : concept.ecoreImports) {
			if (eImport.importedGenModel !== null) {
				val genModelURI = URI.createPlatformPluginURI(eImport.importedGenModel, true)
				genPackage = loadGenPackage(genModelURI, nsURI, resource.resourceSet)
			}
		}
		
		//Try to resolve package from EPackage path
		if (genPackage === null && eClassPackage.eResource !== null) {
			val ecoreURI = eClassPackage.eResource.URI
			val genModelURI = ecoreURI.trimFileExtension.appendFileExtension("genmodel")
			genPackage = loadGenPackage(genModelURI, nsURI, resource.resourceSet)
		}
		return genPackage
	}
	
	/*
	 * Load a GenModel package of a specified EPackage from a GenModel URI 
	 */
	def loadGenPackage(URI genModelUri, String nsUri, ResourceSet resourceSet) {
		var Resource genModelResource
		genModelResource = resourceSet.getResource(genModelUri, true)
		if (genModelResource !== null && genModelResource.contents.get(0) instanceof GenModel) {
			var loadedGenModel = genModelResource.contents.get(0) as GenModel
			for (package : loadedGenModel.allGenPackagesWithClassifiers) {
				if (package.NSURI.equals(nsUri)) {
					return package
				}
			}
		}
	}
	
}