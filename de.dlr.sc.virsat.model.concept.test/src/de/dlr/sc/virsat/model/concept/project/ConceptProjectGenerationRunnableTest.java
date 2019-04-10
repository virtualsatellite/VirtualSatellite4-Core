/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.project;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class tests the generation of the plugin, the UI plugin and the feature
 * of a new  data model concept
 * @author fisc_ph
 *
 */
public class ConceptProjectGenerationRunnableTest {

	private static final String TEST_PROJECT_NAME = "de.dlr.virsat.example.concept";
	private static final String TEST_PROJECT_UI_NAME = "de.dlr.virsat.example.concept.ui";
	private static final String TEST_PROJECT_TEST_NAME = "de.dlr.virsat.example.concept.test";
	private static final String TEST_PROJECT_FEATURE_NAME = "de.dlr.virsat.example.concept.feature";
	
	private IRunnableWithProgress conceptGeneratorRunnable;
	
	@Before
	public void setUp() throws Exception {
		conceptGeneratorRunnable = new ConceptProjectGenerationRunnable(null, TEST_PROJECT_NAME, IResource.AVOID_NATURE_CONFIG);
		
		// Prepare the workspace
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).delete(true, new NullProgressMonitor());
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_UI_NAME).delete(true, new NullProgressMonitor());
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_FEATURE_NAME).delete(true, new NullProgressMonitor());
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_TEST_NAME).delete(true, new NullProgressMonitor());
		IWorkspaceDescription desc = ResourcesPlugin.getWorkspace().getDescription();
		desc.setAutoBuilding(false);
		ResourcesPlugin.getWorkspace().setDescription(desc);
	}
	
	@After
	public void tearDown() throws Exception {
		// CleanUp Workspace
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).delete(true, new NullProgressMonitor());
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_UI_NAME).delete(true, new NullProgressMonitor());
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_FEATURE_NAME).delete(true, new NullProgressMonitor());
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_TEST_NAME).delete(true, new NullProgressMonitor());
	}

	@Test
	public void testConceptProjectGenerationRunnable() throws InvocationTargetException, InterruptedException, CoreException {
		// make sure the projects do not yet exist
		assertFalse("Plugin project does not yet exist", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).exists());
		assertFalse("Plugin UI project does not yet exist", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_UI_NAME).exists());
		assertFalse("Feature project does not yet exist", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_FEATURE_NAME).exists());
		assertFalse("Test Fragment project does not yet exist", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_TEST_NAME).exists());
		
		ResourcesPlugin.getWorkspace().run((progress) -> {
			try {
				conceptGeneratorRunnable.run(progress);
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		}, new NullProgressMonitor()); 
		
		assertTrue("Plugin project now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).exists());
		assertTrue("Plugin UI project now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_UI_NAME).exists());
		assertTrue("Feature project now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_FEATURE_NAME).exists());
		assertTrue("Test Fragment project now exist", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_TEST_NAME).exists());
		
		assertTrue(".project now exists in plugin", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFile(".project").exists());
		assertTrue("MANIFEST.MF now exists in plugin", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder("META-INF").getFile("MANIFEST.MF").exists());
		assertTrue("build.properties now exists in plugin", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFile("build.properties").exists());
		assertTrue("concept.concept now exists in plugin", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder("concept").getFile("concept.concept").exists());
		assertTrue("plugin.xml now exists in plugin", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFile("plugin.xml").exists());
		
		assertTrue(".project now exists in plugin ui", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_UI_NAME).getFile(".project").exists());
		assertTrue("MANIFEST.MF now exists in plugin ui", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_UI_NAME).getFolder("META-INF").getFile("MANIFEST.MF").exists());
		assertTrue("build.properties now exists in plugin ui", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_UI_NAME).getFile("build.properties").exists());
		assertTrue("plugin.xml now exists in plugin ui", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_UI_NAME).getFile("plugin.xml").exists());
	
		assertTrue(".project now exists in plugin test", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_TEST_NAME).getFile(".project").exists());
		assertTrue("MANIFEST.MF now exists in plugin test", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_TEST_NAME).getFolder("META-INF").getFile("MANIFEST.MF").exists());
		assertTrue("build.properties now exists in plugin test", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_TEST_NAME).getFile("build.properties").exists());
		assertFalse("plugin.xml not needed for test fragment", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_TEST_NAME).getFile("plugin.xml").exists());
		
		assertTrue(".project now exists in feature", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_FEATURE_NAME).getFile(".project").exists());
		assertTrue("build.properties now exists in feature", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_FEATURE_NAME).getFile("build.properties").exists());
		assertTrue("feature.xml now exists in feature", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_FEATURE_NAME).getFile("feature.xml").exists());
	}
}
