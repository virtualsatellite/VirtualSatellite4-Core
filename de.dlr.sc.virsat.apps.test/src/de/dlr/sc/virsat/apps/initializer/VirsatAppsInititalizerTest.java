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


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;


/**
 * This class tests the generation of the plugin, the UI plugin and the feature
 * of a new  data model concept
 * @author fisc_ph
 *
 */
public class VirsatAppsInititalizerTest {

	private static final String TEST_PROJECT_NAME = "de.dlr.virsat.script.test.project";
	
	private VirsatAppsInitializer appsInitializer;
	private IProject project;
	
	@Before
	public void setUp() throws Exception {
		appsInitializer = new VirsatAppsInitializer();
		
		// Prepare the workspace
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).delete(true, new NullProgressMonitor());
		IWorkspaceDescription desc = ResourcesPlugin.getWorkspace().getDescription();
		desc.setAutoBuilding(false);
		ResourcesPlugin.getWorkspace().setDescription(desc);
		
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME);
		project.create(new NullProgressMonitor());
		project.open(new NullProgressMonitor());
	}
	
	@After
	public void tearDown() throws Exception {
		// CleanUp Workspace
		
		ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).delete(true, new NullProgressMonitor());
	}
	
	@Test
	public void initializeJirSatExampleApp()  {
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		appsInitializer.initializeProject(project, repo, new NullProgressMonitor());
		appsInitializer.initializeVirSatExampleApp(project, new NullProgressMonitor());
		
		assertTrue("Example script 1 now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).getFile("AppExample1.java").exists());
		assertFalse("Example script 2 does not exist yet", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).getFile("JirSatExample2.java").exists());
		
		appsInitializer.initializeVirSatExampleApp(project, new NullProgressMonitor());
		
		assertTrue("Example script 1 still exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).getFile("AppExample1.java").exists());
		assertTrue("Example script 2 now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).getFile("AppExample2.java").exists());
	}

	@Test
	public void testInitializeProject() throws InvocationTargetException, InterruptedException {
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		appsInitializer.initializeProject(project, repo, new NullProgressMonitor());
		
		assertTrue("bin folder now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder(VirsatAppsInitializer.FOLDER_NAME_BIN).exists());
		assertTrue("scripts folder now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).exists());
		assertTrue("meta-inf folder now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder(VirsatAppsInitializer.FOLDER_NAME_META_INF).exists());
		assertTrue("manifest.mf now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFolder(VirsatAppsInitializer.FOLDER_NAME_META_INF).getFile(VirsatAppsInitializer.FILE_NAME_MANIFEST_MF).exists());
		assertTrue("build.properties now exists", ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).getFile(VirsatAppsInitializer.FILE_NAME_BUILD_PROPERTIES).exists());
	}
}
