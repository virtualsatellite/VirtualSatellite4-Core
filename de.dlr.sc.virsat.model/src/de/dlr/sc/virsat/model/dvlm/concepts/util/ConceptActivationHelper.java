/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferencePropertyHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;

/**
 * This class helps activating concepts. It redirects references
 * so that these can be resolved from a platform plugin. References to
 * concepts are redirected to their active version within the repository
 *
 */
public class ConceptActivationHelper {
	
	protected Repository repository;
	
	public ConceptActivationHelper(Repository repository) {
		this.repository = repository;
	}
	
	public ConceptActivationHelper(Concept concept) {
		if (concept.eContainer() != null && concept.eContainer() instanceof Repository) {
			this.repository = ((Repository) concept.eContainer());
		}
	}
	
	/**
	 * Activate type by redirecting it to type in the repository
	 * @param type the type to be activates
	 * @return the activated type
	 */
	public EObject getActiveType(EObject type) {
		
		// For EReferences to external EClasses ignore concept 
		// activation and convert URIs instead. The URI needs to be
		// transformed from a PluginResourceURI to a PlattformPluginURI so 
		// that it can be resolved from the VirSat project in the eclipse runtime
		// instance.
		// VirSat does not ensure external model's storage.
		// We only enable non-containment references.
		if (type instanceof EClass) {
			return new EReferencePropertyHelper().activateEClassType((EClass) type);
		}

		// In case we try to create a reference to an object which was not copied
		// we should try to redirect that reference to an already active and existing concept
		if (repository != null && type instanceof IConceptTypeDefinition) {
			IConceptTypeDefinition typeDefinition = (IConceptTypeDefinition) type;
	
			// Get the fragment URI of the concept we want to reference to
			String uriFragment = EcoreUtil.getURI(typeDefinition).fragment();

			// ask the repository if there is such an object with the given URI fragment
			Resource repoResource = repository.eResource();
			EObject repoTypeDefinition = repoResource.getEObject(uriFragment);

			// If not throw a warning that there is something missing
			if (repoTypeDefinition == null) {
				String fqId = ActiveConceptHelper.getFullQualifiedId(typeDefinition);
			
				throw new RuntimeException("Install missing concept first! Missing concept: " + fqId);
			}
			return repoTypeDefinition;
		}
		
		return type;
	}

}
