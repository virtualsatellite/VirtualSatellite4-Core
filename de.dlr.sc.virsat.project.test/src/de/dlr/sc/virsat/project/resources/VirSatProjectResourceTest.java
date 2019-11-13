/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources;


import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Cases for the wrapping VirSatProject Resource
 * @author fisc_ph
 *
 */
public class VirSatProjectResourceTest {

	private IProject project1;
	private IProject project2;
	
	@Before
	public void setUp() throws Exception {
		IWorkspaceRoot ws = ResourcesPlugin.getWorkspace().getRoot();
		
		project1 = ws.getProject("testProject1");
		project2 = ws.getProject("testProject2");
	}

	@Test
	public void testVirSatProjectResource() {
		VirSatProjectResource vsProjectResource = new VirSatProjectResource(project1);
		
		assertEquals("VirSatProjectResource is wrapping correct project", project1, vsProjectResource.getWrappedProject());
	}

	@Test
	public void testSetWrappedProject() {
		VirSatProjectResource vsProjectResource = new VirSatProjectResource(project1);
		vsProjectResource.setWrappedProject(project2);
		
		assertEquals("VirSatProjectResource is wrapping correct project", project2, vsProjectResource.getWrappedProject());
	}
	
	@Test
	public void testGetWrappedProjects() {
		List<IProject> projects = new ArrayList<>();
		Map<IResource, IVirsatWrappedResource> virSatProjectResources = new HashMap<>();
		
		projects.add(project1);
		
		List<IVirsatWrappedResource> wrappedProjects = VirSatProjectResource.getWrappedProjects(projects, virSatProjectResources);
		
		assertEquals("One Project has been wrapped", 1, wrappedProjects.size());
		assertTrue("Map has a cached entry", virSatProjectResources.containsKey(project1));
		assertFalse("Map has not cached project 2", virSatProjectResources.containsKey(project2));
		
		projects.add(project2);
		
		wrappedProjects = VirSatProjectResource.getWrappedProjects(projects, virSatProjectResources);
		
		assertEquals("One Project has been wrapped", 2, wrappedProjects.size());
		assertTrue("Map has a cached entry", virSatProjectResources.containsKey(project1));
		assertTrue("Map has not cached project 2", virSatProjectResources.containsKey(project2));
		
		List<IVirsatWrappedResource> wrappedProjects2 = VirSatProjectResource.getWrappedProjects(projects, virSatProjectResources);
		
		assertThat("Method should return the same instance of wrapped projects", wrappedProjects, hasItems(wrappedProjects2.toArray(new VirSatProjectResource[wrappedProjects2.size()])));
	}
}
