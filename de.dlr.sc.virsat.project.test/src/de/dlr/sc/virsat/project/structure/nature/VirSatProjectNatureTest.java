/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure.nature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import de.dlr.sc.virsat.project.test.AProjectTestCase;



/**
 * Test Cases for the VirSat Project Nature
 */
public class VirSatProjectNatureTest extends AProjectTestCase {

	@Test
	public void testConfigure() throws CoreException {
		VirSatProjectNature virSatNature = new VirSatProjectNature();
		virSatNature.setProject(testProject);
		virSatNature.configure();
		
		List<String> natures = new ArrayList<>();
		
		for (ICommand command : testProject.getDescription().getBuildSpec()) {
			natures.add(command.getBuilderName());
		}
		
		assertThat("Natures Builder is in the list", natures, hasItem(VirSatProjectNature.BUILDER_VALIDATOR_ID));
	}

	@Test
	public void testDeconfigure() throws CoreException {
		VirSatProjectNature virSatNature = new VirSatProjectNature();
		virSatNature.setProject(testProject);
		virSatNature.configure();
		virSatNature.deconfigure();
		
		List<String> natures = new ArrayList<>();
		
		for (ICommand command : testProject.getDescription().getBuildSpec()) {
			natures.add(command.getBuilderName());
		}
		
		assertThat("Nature should be still tehre. Deconfigure is not yet implemented", natures, hasItem(VirSatProjectNature.BUILDER_VALIDATOR_ID));
	}

	@Test
	public void testGetProject() {
		VirSatProjectNature virSatNature = new VirSatProjectNature();
		assertNull("Project has been correctly set", virSatNature.getProject());

		virSatNature.setProject(testProject);
		assertEquals("Project has been correctly set", testProject, virSatNature.getProject());
	}

	@Test
	public void testSetProject() {
		VirSatProjectNature virSatNature = new VirSatProjectNature();
		virSatNature.setProject(testProject);
		
		assertEquals("Project has been correctly set", testProject, virSatNature.getProject());

		virSatNature.setProject(null);
		assertNull("Project has been correctly set", virSatNature.getProject());
	}
}
