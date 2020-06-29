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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.jar.Manifest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;

/**
 * test cases for the manifest file updater
 * @author fisc_ph
 *
 */
public class ManifestRequiredBundlesConceptUpdaterTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdateDependencies() {
		final String DEP_1 = "de.dlr.sc.virsat.apps"; 
		final String DEP_2 = "de.dlr.sc.virsat.model.extension.ps"; 
		final String DEP_3 = "de.dlr.sc.virsat.model.extension.funcelectrical";

		Manifest manifest = new Manifest();
		manifest.getMainAttributes().putValue(ManifestRequiredBundlesConceptUpdater.MANIFEST_REQ_BUNDLE, DEP_1);
		
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		Concept concept1 = ConceptsFactory.eINSTANCE.createConcept();
		Concept concept2 = ConceptsFactory.eINSTANCE.createConcept();
		concept1.setName(DEP_2);
		concept2.setName(DEP_3);
		
		repo.getActiveConcepts().add(concept1);
		
		Manifest manifestRet1 = ManifestRequiredBundlesConceptUpdater.updateDependencies(repo, manifest);
		assertEquals("Isntance has not changed", manifestRet1, manifest);
		List<String> split1 = ManifestRequiredBundlesConceptUpdater.splitRequiredBundlesString(manifest.getMainAttributes().getValue(ManifestRequiredBundlesConceptUpdater.MANIFEST_REQ_BUNDLE));
		assertThat("Contains correct plugins", split1, hasItems(DEP_1, DEP_2));
		
		repo.getActiveConcepts().add(concept2);
		Manifest manifestRet2 = ManifestRequiredBundlesConceptUpdater.updateDependencies(repo, manifest);
		assertEquals("Isntance has not changed", manifestRet2, manifest);
		List<String> split2 = ManifestRequiredBundlesConceptUpdater.splitRequiredBundlesString(manifest.getMainAttributes().getValue(ManifestRequiredBundlesConceptUpdater.MANIFEST_REQ_BUNDLE));
		assertThat("Contains correct plugins", split2, hasItems(DEP_1, DEP_2, DEP_3));
	}

	@Test
	public void testSplitRequiredBundlesString() {
		final String TEST_1 = "de.dlr.sc.virsat.apps,de.dlr.sc.virsat.model.extension.ps;bundle-version=\"[3.0.0,5.0.0)\";resolution:=optional;visibility:=reexport,de.dlr.sc.virsat.model.extension.funcelectrical;bundle-version=\"4.4.0\"";
		final String TEST_2 = "de.dlr.sc.virsat.apps,de.dlr.sc.virsat.model.extension.ps;bundle-version=\"[3.0.0,5.0.0)\";resolution:=optional;visibility:=reexport,de.dlr.sc.virsat.model.extension.funcelectrical";
		final String TEST_3 = "de.dlr.sc.virsat.apps,de.dlr.sc.virsat.model.extension.ps";

		final String DEP_1 = "de.dlr.sc.virsat.apps"; 
		final String DEP_2 = "de.dlr.sc.virsat.model.extension.ps"; 
		final String DEP_3 = "de.dlr.sc.virsat.model.extension.funcelectrical";
		
		List<String> split1 = ManifestRequiredBundlesConceptUpdater.splitRequiredBundlesString(TEST_1);
		assertThat("Contains correct plugins", split1, hasItems(DEP_1, DEP_2, DEP_3));

		List<String> split2 = ManifestRequiredBundlesConceptUpdater.splitRequiredBundlesString(TEST_2);
		assertThat("Contains correct plugins", split2, hasItems(DEP_1, DEP_2, DEP_3));

		List<String> split3 = ManifestRequiredBundlesConceptUpdater.splitRequiredBundlesString(TEST_3);
		assertThat("Contains correct plugins", split3, hasItems(DEP_1, DEP_2));
	}
}
