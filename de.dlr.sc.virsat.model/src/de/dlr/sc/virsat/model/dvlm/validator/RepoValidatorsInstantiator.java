/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.model.dvlm.Activator;
import de.dlr.sc.virsat.model.dvlm.Repository;

/**
 * Class that reads DVLM validators extension point
 * and creates validators that are relevant for a given repository.
 */
public class RepoValidatorsInstantiator {

	public static final String EXTENSION_POINT_ID_CONCEPT_VALIDATOR = "de.dlr.sc.virsat.model.DvlmValidator";
	public static final String CONCEPT_BUNDLE_PREFIX = "de.dlr.sc.virsat.model.extension";

	private Repository repository;
	private List<IStructuralElementInstanceValidator> seiValidators;
	private List<IRepositoryValidator> repoValidators;

	public RepoValidatorsInstantiator(Repository repository) {
		this.repository = repository;
		createValidators();
	}
	
	public List<IRepositoryValidator> getRepoValidators() {
		return repoValidators;
	}
	
	public List<IStructuralElementInstanceValidator> getSeiValidators() {
		return seiValidators;
	}
	
	/**
	 * Reads all registered validator extensions and creates validator objects for applicable validators.
	 */
	private void createValidators() {
		repoValidators = new ArrayList<>();
		seiValidators = new ArrayList<>();
		
		Set<String> activeConceptIds = repository.getActiveConcepts().stream()
				.map(c -> c.getFullQualifiedName()).collect(Collectors.toSet());
		
		Set<String> suppressedValidators = new HashSet<>(repository.getSuppressedValidators());

		IExtensionRegistry registry = Platform.getExtensionRegistry();

		IConfigurationElement[] configElements = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_CONCEPT_VALIDATOR);
		
		for (IConfigurationElement configElement : configElements) {
			for (IConfigurationElement validatorConfigElement : configElement.getChildren()) {
				String contributor = validatorConfigElement.getContributor().getName();
				boolean conceptSpecific = contributor.startsWith(CONCEPT_BUNDLE_PREFIX);
				boolean suppressed = suppressedValidators.contains(validatorConfigElement.getAttribute("class"));
				if (!suppressed && (!conceptSpecific || activeConceptIds.contains(contributor))) {
					createValidator(validatorConfigElement);
				}
			}
		}
	}

	/**
	 * Creates a validator object and adds it to SEI or Repo validators depending on its type 
	 * @param validatorConfigElement validator extension config element
	 */
	private void createValidator(IConfigurationElement validatorConfigElement) {
		try {
			Object validator = validatorConfigElement.createExecutableExtension("class");
			
			if (validator instanceof IStructuralElementInstanceValidator) {
				IStructuralElementInstanceValidator seiValidator = (IStructuralElementInstanceValidator) validator;
				seiValidators.add(seiValidator);
			}
			if (validator instanceof IRepositoryValidator) {
				IRepositoryValidator repoValidator = (IRepositoryValidator) validator;
				repoValidators.add(repoValidator);
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "RepoValidatorsInstantiator: Could not create validator for " + validatorConfigElement, e));
		}
	}
}
