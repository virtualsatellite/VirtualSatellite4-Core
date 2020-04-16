/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Checks if the structural element instance can be validated
 * 
 * @author bell_er
 *
 */
public class DvlmResolvedStructuralElementInstanceValidatorTest extends ABuilderTest {

	@Test
	public void testResolvedStructuralElementInstance() throws CoreException, IOException {
		DvlmResolvedStructuralElementInstanceValidator validator = new DvlmResolvedStructuralElementInstanceValidator();
		assertTrue("root SEI can be resolved", validator.validate(seiEdSc));

		Resource rootSeiResource = resSet.getStructuralElementInstanceResource(seiEdSc);
		rootSeiResource.getContents().remove(seiEdSc);
		resSet.saveResource(rootSeiResource, UserRegistry.getInstance());

		VirSatResourceSet reloadedResourceSet = VirSatResourceSet.createUnmanagedResourceSet(project);
		Repository repoWithDanglingReference = reloadedResourceSet.getRepository();
		StructuralElementInstance unresolvableProxyOfRootSei = repoWithDanglingReference.getRootEntities().get(0);

		assertFalse("root SEI cannot be resolved", validator.validate(unresolvableProxyOfRootSei));
	}
}
