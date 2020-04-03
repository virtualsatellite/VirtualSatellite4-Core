/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.initializer;

import java.util.Arrays;
import java.util.List;
import java.util.jar.Manifest;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * This class provides some additional methods to correctly set up the
 * manifest file, in case an concept has been added and is not yet registered
 * as required bundle to correctly execute an APP
 * @author fisc_ph
 *
 */
public class ManifestRequiredBundlesConceptUpdater {

	/**
	 * Hidden constructor since tool class
	 */
	private ManifestRequiredBundlesConceptUpdater() {
	}
	
	public static final String MANIFEST_REQ_BUNDLE = "Require-Bundle";
	
	/**
	 * Call this method to update the dependencies stated in the manifest file
	 * @param repo the repo which contains the active concepts which shall be registered
	 * @param manifest the manifest file to be updated
	 * @return the manifest file obejct which has been changed
	 */
	public static Manifest updateDependencies(Repository repo, Manifest manifest) {
		// get the already existing dependencies
		String requiredBundles = manifest.getMainAttributes().getValue(MANIFEST_REQ_BUNDLE);
		List<String> requiredBundlesSplit = splitRequiredBundlesString(requiredBundles);
		
		// add the concepts as dependencies to the manifest file
		StringBuilder newRequiredBundles = new StringBuilder();
		for (Concept concept : repo.getActiveConcepts()) {
			String conceptName = concept.getFullQualifiedName();
			if (!requiredBundlesSplit.contains(conceptName)) {
				newRequiredBundles.append(",");
				newRequiredBundles.append(conceptName);
			}
		}
		
		manifest.getMainAttributes().putValue(MANIFEST_REQ_BUNDLE, requiredBundles + newRequiredBundles.toString());
		
		return manifest;
	}

	/**
	 * this method just splits the string of bundles correctly
	 * @param requiredBundles the string representing all  bundles
	 * @return the list of identified bundles without version and additional settings
	 */
	protected static List<String> splitRequiredBundlesString(String requiredBundles) {
		// A Plugin as dependency is always defined as a set of names delimited by a point. a second Plugin is usually introduced when the other one has been defined
		// this happens by a comma. if there is a ; after the Plugin name it means there are further definitions, in particular the version range may
		// contain another comma what we have to deal with. the last Plugin that is defined does not have a comma so it will end with EOL
		List<String> requiredBundlesSplit = Arrays.asList(requiredBundles.split("(,|;.*(,)?.*,|;.*(,)?.*$)"));
		return requiredBundlesSplit;
	}
}
