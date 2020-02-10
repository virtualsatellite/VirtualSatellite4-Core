/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;

public class EReferencePropertyHelper {
	
	/**
	 * Resolve the EClass type proxy of the property definition
	 * @param propertyDefinition the property definition
	 * @return the resolved type
	 */
	public EClass getResolvedEClassType(EReferenceProperty propertyDefinition) {
		
		EClass referencePropertyType = propertyDefinition.getReferenceType();
		if (!referencePropertyType.eIsProxy()) {
			return referencePropertyType;
		}
		Concept concept = ActiveConceptHelper.getConcept(propertyDefinition);
		URI proxyURI =  ((InternalEObject) referencePropertyType).eProxyURI();
		
		for (EcoreImport importedPackage : concept.getEcoreImports()) {
			EPackage registeredPackage = EPackage.Registry.INSTANCE.getEPackage(importedPackage.getImportedNsURI());
			EObject resolvedEClass = registeredPackage.eResource().getEObject(proxyURI.fragment());
			if (resolvedEClass != null) {
				referencePropertyType = (EClass) resolvedEClass;
			}
		}
		
		return referencePropertyType;
	}
	
	public EPackage getEPackageOfType(EReferenceProperty propertyDefinition) {
		return (EPackage) getResolvedEClassType(propertyDefinition).eContainer();
	}
	
	/**
	 * Activate the EClass type so that it is resolvable in the plugin instance. If type is 
	 * a proxy, then the proxy URI needs to be converted to a PlattformPluginURI 
	 * @param type the type to activate
	 * @return the active type
	 */
	public EClass activateEClassType(EClass type) {
		if (type.eIsProxy()) {
			URI proxyURI = ((InternalEObject) type).eProxyURI();
			URI activeURI = URI.createPlatformPluginURI(proxyURI.toPlatformString(true), true);
			activeURI = activeURI.appendFragment(proxyURI.fragment());
			((InternalEObject) type).eSetProxyURI(activeURI);
		}
		return type;
	}
}
