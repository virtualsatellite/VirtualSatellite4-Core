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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.project.test.AProjectTestCase;


/**
 * This class tests the generation of the plugin, the UI plugin and the feature
 * of a new  data model concept
 * @author fisc_ph
 *
 */
public class VirsatAppsInititalizerTest extends AProjectTestCase {

	private VirsatAppsInitializer appsInitializer;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		
		appsInitializer = new VirsatAppsInitializer();
	}
	
	@Test
	public void initializeJirSatExampleApp()  {
		appsInitializer.initializeProject(testProject, repository, new NullProgressMonitor());
		appsInitializer.initializeVirSatExampleApp(testProject, new NullProgressMonitor());
		
		assertTrue("Example script 1 now exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).getFile("AppExample1.java").exists());
		assertFalse("Example script 2 does not exist yet", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).getFile("JirSatExample2.java").exists());
		
		appsInitializer.initializeVirSatExampleApp(testProject, new NullProgressMonitor());
		
		assertTrue("Example script 1 still exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).getFile("AppExample1.java").exists());
		assertTrue("Example script 2 now exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).getFile("AppExample2.java").exists());
	}

	@Test
	public void testInitializeProject() throws InvocationTargetException, InterruptedException, CoreException {
		appsInitializer.initializeProject(testProject, repository, new NullProgressMonitor());
		
		assertTrue("target folder now exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_TARGET).exists());
		assertTrue("classes folder now exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_TARGET).getFolder(VirsatAppsInitializer.FOLDER_NAME_CLASSES).exists());
		assertTrue("scripts folder now exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).exists());
		assertTrue("meta-inf folder now exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_META_INF).exists());
		assertTrue("manifest.mf now exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_META_INF).getFile(VirsatAppsInitializer.FILE_NAME_MANIFEST_MF).exists());
		assertTrue("build.properties now exists", testProject.getFile(VirsatAppsInitializer.FILE_NAME_BUILD_PROPERTIES).exists());
		
		assertEquals("There are not files in the target/classes folder", 0, testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_TARGET).getFolder(VirsatAppsInitializer.FOLDER_NAME_CLASSES).members().length);
	
		// Test that initializing apps in a project with existing folder structure or files is safe
		
		appsInitializer.initializeProject(testProject, repository, new NullProgressMonitor());
		
		assertTrue("target folder still exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_TARGET).exists());
		assertTrue("classes folder still exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_TARGET).getFolder(VirsatAppsInitializer.FOLDER_NAME_CLASSES).exists());
		assertTrue("scripts folder still exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS).exists());
		assertTrue("meta-inf folder still exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_META_INF).exists());
		assertTrue("manifest.mf still exists", testProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_META_INF).getFile(VirsatAppsInitializer.FILE_NAME_MANIFEST_MF).exists());
		assertTrue("build.properties still exists", testProject.getFile(VirsatAppsInitializer.FILE_NAME_BUILD_PROPERTIES).exists());
	}
}
