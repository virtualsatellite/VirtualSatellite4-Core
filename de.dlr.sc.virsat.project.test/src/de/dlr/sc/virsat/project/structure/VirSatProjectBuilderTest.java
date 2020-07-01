/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * test class to test the builder for VirSat projects
 */
public class VirSatProjectBuilderTest extends AProjectTestCase {

	@Override
	protected void addProjectFileStructure() {
	}
	
	@Test
	public void testCreateProjectStructure() {
		VirSatProjectCommons virSatProject = new VirSatProjectCommons(testProject); 
		
		assertFalse("Folder does not yet exist", testProject.getFolder(VirSatProjectCommons.FOLDERNAME_DATA).exists());
		assertFalse("Folder does not yet exist", testProject.getFolder(VirSatProjectCommons.FOLDERNAME_UNVERSIONED).exists());
		
		boolean result = virSatProject.createProjectStructure(null);
		
		assertTrue("Method was susccesfully executed", result);
		assertTrue("Folder now exist", testProject.getFolder(VirSatProjectCommons.FOLDERNAME_DATA).exists());
		assertTrue("Folder now exist", testProject.getFolder(VirSatProjectCommons.FOLDERNAME_UNVERSIONED).exists());
	}
	
	@Test
	public void testAttachProjectNature() throws CoreException {
		VirSatProjectCommons virSatProject = new VirSatProjectCommons(testProject); 

		boolean result = virSatProject.attachProjectNature();
		
		assertTrue("Method was susccesfully executed", result);
		
		List<String> natures = Arrays.asList(testProject.getDescription().getNatureIds());
		
		assertThat("Nature is in the list", natures, hasItem(VirSatProjectNature.NATURE_ID));
	}
}
